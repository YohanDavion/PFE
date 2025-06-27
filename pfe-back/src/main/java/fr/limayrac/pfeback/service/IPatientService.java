package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.dto.UserUpdateDTO;
import fr.limayrac.pfeback.model.Patient;

import java.util.List;

public interface IPatientService extends IAbstractService<Patient> {
    List<Patient> findAll();

    void updatePatient(UserUpdateDTO userDto);
}
