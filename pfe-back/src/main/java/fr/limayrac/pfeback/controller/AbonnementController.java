package fr.limayrac.pfeback.controller;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import fr.limayrac.pfeback.UserService;
import fr.limayrac.pfeback.model.Abonnement;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.PatientAbonnement;
import fr.limayrac.pfeback.security.CustomUserDetails;
import fr.limayrac.pfeback.service.IAbonnementService;
import fr.limayrac.pfeback.service.IPatientService;
import fr.limayrac.pfeback.service.StripeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/abonnement")
public class AbonnementController implements IApiRestController<Abonnement, Long> {
    @Autowired
    private StripeService stripeService;
    @Autowired
    private IAbonnementService abonnementService;
    @Autowired
    private IPatientService patientService;
    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/{id}")
    public Abonnement findById(@PathVariable Long id) {
        return abonnementService.findById(id);
    }

    @Override
    @GetMapping("/all")
    public Collection<Abonnement> findAll() {
        return abonnementService.findAll();
    }

    @Override
    @PostMapping
    public Abonnement create(Abonnement entity) {
        return abonnementService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        abonnementService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Abonnement put(Abonnement entity) {
        return abonnementService.save(entity);
    }

    @Override
    @PatchMapping("/{id}")
    public Abonnement patch(Abonnement entity) {
        return null;
    }

    @GetMapping("/checkout/{abonnementId}")
    public Map<String, String> createCheckoutSession(@PathVariable Long abonnementId) {
        try {
            String checkoutUrl = stripeService.createCheckoutSession(abonnementId);
            Map<String, String> map = new HashMap<>();
            map.put("checkoutUrl", checkoutUrl);
            return map;
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("checkoutUrl", null);
            return map;
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleStripeWebhook(HttpServletRequest request) throws IOException {
        String payload = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        String sigHeader = request.getHeader("Stripe-Signature");
        String endpointSecret = "whsec_BZyTdPA7arubScKZNSAZzcoOHv77497z";

        Event event;
        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            // Signature invalide
            return ResponseEntity.badRequest().body("Invalid signature");
        }

        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();
            // ici tu sais que la session est terminée
            System.out.println("Paiement complété pour session : " + session.getId());
            // Mets à jour ta base de données pour ce client
        }

        return ResponseEntity.ok("");
    }

    @GetMapping("/affectAbonnement")
    public Map<String, String> affectAbonnement(@RequestParam Long abonnementId) {
        Patient patient = (Patient) ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Abonnement abonnement = abonnementService.findById(abonnementId);
        // Le patient n'a pas d'abonnement
        patient.setDatePaiement(LocalDate.now());
        patient = patientService.save(patient);

        PatientAbonnement patientAbonnement = new PatientAbonnement();

        if (patient.getAbonnement() == null) {
            patient.setAbonnement(abonnement);
            patient = patientService.save(patient);

            patientAbonnement.setAbonnement(abonnement);
            patientAbonnement.setPatient(patient);
            patientAbonnement.setProprietaire(patient);
            patientAbonnement.setValide(true);
        } else {
            patientAbonnement = abonnementService.findFirstByPatientAndProprietaireAndAbonnement(patient, patient, patient.getAbonnement());
            patientAbonnement.setAbonnement(abonnement);
        }
        abonnementService.createProprietaire(patientAbonnement);

        Map<String, String> map = new HashMap<>();
        map.put("abonnement", "Abonnement effectué avec succès");
        return map;
    }
    @GetMapping("/rejoindreAbonnement")
    public Map<String, Object> rejoindreAbonnement(@RequestParam Long abonnementId, @RequestParam String mail) {
        Patient patient = (Patient) ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Patient owner = patientService.findByMail(mail);
        Map<String, Object> map = new HashMap<>();
        if (owner != null) {
            PatientAbonnement patientAbonnement = abonnementService.findPatientAbonnementByPatientProprietaireAbonnement(patient, owner, owner.getAbonnement());
            if (patientAbonnement != null) {
                map.put("message", "Votre demande a déjà été envoyée");
                map.put("ok", false);
            } else {
                boolean result = abonnementService.rejoindreAbonnement(abonnementId, patient, owner);
                if (result) {
                    map.put("message", "Dans l'attente de la validation du propriétaire");
                    map.put("ok", true);
                } else {
                    map.put("message", "Abonnement complet");
                    map.put("ok", false);
                }
            }
        } else {
            map.put("message", "Aucun utilisateur ne paye un abonnement avec ce mail");
            map.put("ok", false);
        }
        return map;
    }

    @GetMapping("/patients-abonnements")
    public List<PatientAbonnement> patientsAbonnements() {
        Patient patient = (Patient) ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        List<PatientAbonnement> pa = abonnementService.findByProprietaire(patient);
        PatientAbonnement paAbonnementToRemove = null;
        for (PatientAbonnement paAbonnement : pa) {
            if (paAbonnement.getPatient().equals(patient)) {
                paAbonnementToRemove = paAbonnement;
            }
        }
        pa.remove(paAbonnementToRemove);
        return pa;
    }

    @PostMapping("/update-patients-abonnements")
    public PatientAbonnement update(@RequestBody PatientAbonnement patientAbonnement) {
        return abonnementService.savePatientAbonnement(patientAbonnement);
    }

    @PostMapping("/joinAbonnement")
    public Map<String, Object> joinAbonnement(@RequestBody PatientAbonnement patientAbonnement) {
        Map<String, Object> map = new HashMap<>();
        if (patientAbonnement.getProprietaire().getDelaiPatient() == null || patientAbonnement.getProprietaire().getDelaiPatient().plusWeeks(2).isBefore(LocalDate.now())) {
            patientAbonnement.setValide(true);
            Patient patient = patientAbonnement.getPatient();
            patient.setAbonnement(patientAbonnement.getProprietaire().getAbonnement());
            patient.setDatePaiement(patientAbonnement.getProprietaire().getDatePaiement());
            patient = patientService.save(patient);
            patientAbonnement.setPatient(patient);
            patientAbonnement = abonnementService.savePatientAbonnement(patientAbonnement);
            map.put("message", "Patient activé avec succès");
            map.put("ok", true);
            map.put("patientAbonnement", patientAbonnement);
            return map;
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = dtf.format(patientAbonnement.getProprietaire().getDelaiPatient().plusWeeks(2));
            map.put("message", "Vous pourrez ajouter un nouveau membre à votre famille le : " + date);
            map.put("ok", false);
            return map;
        }
    }

    @PostMapping("/retrieveAbonnement")
    public Map<String, Object> retrieveAbonnement(@RequestBody PatientAbonnement patientAbonnement) {
        Map<String, Object> map = new HashMap<>();
        Patient owner = patientService.findById(patientAbonnement.getProprietaire().getId());
        if (owner.getDelaiPatient() == null || owner.getDelaiPatient().plusWeeks(2).isBefore(LocalDate.now())) {
            Patient patient = patientAbonnement.getPatient();
            patient.setAbonnement(null);
            patient.setDatePaiement(null);
            patientService.save(patient);
            owner.setDelaiPatient(LocalDate.now());
            patientService.save(owner);
            abonnementService.deletePatientAbonnement(patientAbonnement);
            map.put("message", "Patient supprimé du groupe avec succès");
            map.put("ok", true);
            return map;
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = dtf.format(owner.getDelaiPatient().plusWeeks(2));
            map.put("message", "Vous pourrez supprimer un nouveau membre de votre groupe le : " + date);
            map.put("ok", false);
            return map;
        }
    }
}
