package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.dto.UserUpdateDTO;
import fr.limayrac.pfeback.model.DroitAcces;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.Serie;
import fr.limayrac.pfeback.model.User;
import fr.limayrac.pfeback.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.thymeleaf.util.StringUtils.isEmpty;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IOrthophonisteService orthophonisteService;

    @Autowired
    private IAdministrateurService administrateurService;
    @Autowired
    private IDroitAccesService droitAccesService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userDto) {

        if (userDto.getId() == null) {
            return ResponseEntity.badRequest().body("Rôle ou ID manquant");
        }

        switch (userDto.getRole().toUpperCase()) {
            case "PATIENT":
                if (isEmpty(userDto.getAdresse()) || isEmpty(userDto.getNomParent()) || isEmpty(userDto.getPrenomParent())) {
                    return ResponseEntity.badRequest().body(Map.of("error", "Champs obligatoires manquants pour un patient"));
                }
                Patient patient = patientService.updatePatient(userDto);
                for (Serie serie : userDto.getSeries()) {
                    DroitAcces droitAcces = new DroitAcces();
                    droitAcces.setSerie(serie);
                    droitAcces.setPatient(patient);
                    droitAcces.setValide(true);
                    droitAccesService.save(droitAcces);
                }
                return ResponseEntity.ok(Map.of("message", "Enregistrement du patient ok"));

            case "ORTHOPHONISTE":
                if (isEmpty(userDto.getAdresse()) || isEmpty(userDto.getRpps()) || isEmpty(userDto.getSiret())) {
                    return ResponseEntity.badRequest().body(Map.of("error", "Champs obligatoires manquants pour un orthophoniste"));
                }
                orthophonisteService.updateOrthophoniste(userDto);
                return ResponseEntity.ok(Map.of("message", "Enregistrement de l'orthophoniste ok"));

            case "ADMINISTRATEUR":
                if (isEmpty(userDto.getNom()) || isEmpty(userDto.getPrenom())) {
                    return ResponseEntity.badRequest().body(Map.of("error", "Champs obligatoires manquants pour un administrateur"));
                }
                administrateurService.updateAdministrateur(userDto);
                return ResponseEntity.ok(Map.of("message", "Enregistrement de l'administrateur ok"));

            default:
                return ResponseEntity.badRequest().body(Map.of("error", "Rôle non reconnu"));
        }

    }
}