package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.Patient;

import java.util.List;

public interface IPatientService {
    List<Patient> findAll();
}
