package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.repository.PatientRepository;
import fr.limayrac.pfeback.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements IPatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}
