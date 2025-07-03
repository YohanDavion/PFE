package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.DroitAcces;
import fr.limayrac.pfeback.model.Patient;

import java.util.List;

public interface IDroitAccesService extends IAbstractService<DroitAcces> {
    List<DroitAcces> findByPatient(Patient patient);

    void deleteAll(List<DroitAcces> droitAcces);
}
