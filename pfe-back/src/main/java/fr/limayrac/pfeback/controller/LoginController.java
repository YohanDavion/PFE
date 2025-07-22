package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.UserService;
import fr.limayrac.pfeback.config.JWTService;
import fr.limayrac.pfeback.dto.LoginRequest;
import fr.limayrac.pfeback.dto.LoginResponse;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.PatientAbonnement;
import fr.limayrac.pfeback.model.User;
import fr.limayrac.pfeback.security.CustomUserDetails;
import fr.limayrac.pfeback.service.IAbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private IAbonnementService abonnementService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword())
            );

            if (authentication.isAuthenticated()) {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                String token = jwtService.generateToken(loginRequest.getLogin());
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setToken(token);
                loginResponse.setRole(userDetails.getUser().getRole());
                if (userDetails.getUser() instanceof Patient patient) {
                    PatientAbonnement patientAbonnement = abonnementService.findFirstByPatientAndAbonnement(patient, patient.getAbonnement());
                    boolean datePaiement = false;
                    if (patientAbonnement != null) {
                        datePaiement = patient.getDateExpirationAbonnement().isAfter(LocalDate.now()) && patientAbonnement.getValide();
                    }
                    boolean abonnementOk = patient.getAbonnement() != null && datePaiement;
                    boolean accesGratuitOk = false;
                    if (patient.getAccesGratuit() != null) {
                        accesGratuitOk = patient.getAccesGratuit().isAfter(LocalDate.now())
                        || patient.getAccesGratuit().equals(LocalDate.now());
                    }
                    loginResponse.setAbonnementOk(abonnementOk || accesGratuitOk);
                }
                return ResponseEntity.ok(loginResponse);
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return null;
    }

    @GetMapping("/user")
    public User currentUser() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }
}