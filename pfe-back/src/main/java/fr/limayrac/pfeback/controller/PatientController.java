package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private IPatientService patientService;

    @GetMapping("/all")
    public List<Patient> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public Patient findById(@PathVariable Long id) {
        return patientService.findById(id);
    }
}
