package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Administrateur;
import fr.limayrac.pfeback.model.Orthophoniste;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.User;
import fr.limayrac.pfeback.security.CustomUserDetails;
import fr.limayrac.pfeback.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController implements IApiRestController<Patient, Long>{
    @Autowired
    private IPatientService patientService;

    @Override
    @GetMapping("/all")
    public List<Patient> findAll() {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = currentUser.getUser();
        if (user instanceof Administrateur) {
            return patientService.findAll();
        } else if (user instanceof Orthophoniste) {
            return patientService.findByOrthophoniste((Orthophoniste) user);
        } else {
            return null;
        }
    }

    @Override
    @GetMapping("/{id}")
    public Patient findById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @Override
    @PostMapping
    public Patient create(@RequestBody Patient entity) {
        // Chiffrage du mdp
        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        // Actif par défaut à la création
        entity.setActif(true);
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = currentUser.getUser();
        if (user instanceof Orthophoniste) {
            entity.setOrthophoniste((Orthophoniste) user);
        }
        return patientService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Patient put(@RequestBody Patient entity) {
        // TODO récupérer les coordonnées bancaire pour ne pas les supprimer
        return patientService.save(entity);
    }

    @Override
    public Patient patch(Patient entity) {
        return null;
    }
}
