package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Orthophoniste;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.service.IOrthophonisteService;
import fr.limayrac.pfeback.service.impl.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/orthophoniste")
public class OrthophonisteController implements IApiRestController<Orthophoniste, Long> {
    @Autowired
    private IOrthophonisteService orthophonisteService;
    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @Override
    @GetMapping("/{id}")
    public Orthophoniste findById(@PathVariable Long id) {
        return orthophonisteService.findById(id);
    }

    @Override
    @GetMapping("/all")
    public Collection<Orthophoniste> findAll() {
        return orthophonisteService.findAll();
    }

    @Override
    @PostMapping
    public Orthophoniste create(@RequestBody Orthophoniste entity) {
        return orthophonisteService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        List<Patient> patients = patientServiceImpl.findByOrthophoniste(orthophonisteService.findById(id));
        List<Patient> update = new ArrayList<>();
        for (Patient patient : patients) {
            patient.setOrthophoniste(null);
            update.add(patient);
        }
        patientServiceImpl.saveAll(update);
        orthophonisteService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Orthophoniste put(@RequestBody Orthophoniste entity) {
        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        return orthophonisteService.save(entity);
    }

    @Override
    @PatchMapping("/{id}")
    public Orthophoniste patch(Orthophoniste entity) {
        return null;
    }
}
