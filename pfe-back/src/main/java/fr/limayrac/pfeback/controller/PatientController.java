package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Administrateur;
import fr.limayrac.pfeback.model.Orthophoniste;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.User;
import fr.limayrac.pfeback.security.CustomUserDetails;
import fr.limayrac.pfeback.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Patient create(Patient entity) {
        return patientService.save(entity);
    }

    @Override
    public void delete(Long entity) {
        patientService.delete(entity);
    }

    @Override
    public Patient put(Patient entity) {
        return patientService.save(entity);
    }

    @Override
    public Patient patch(Patient entity) {
        return null;
    }
}
