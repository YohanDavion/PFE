package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.dto.UserUpdateDTO;
import fr.limayrac.pfeback.model.Orthophoniste;
import fr.limayrac.pfeback.model.Patient;

import java.util.List;

public interface IPatientService extends IAbstractService<Patient> {
    List<Patient> findAll();

    Patient updatePatient(UserUpdateDTO userDto);

    List<Patient> findByOrthophoniste(Orthophoniste orthophoniste);

    Patient findByMail(String mail);

    List<Patient> saveAll(List<Patient> update);
}
