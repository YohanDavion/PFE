package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.DroitAcces;
import fr.limayrac.pfeback.model.Patient;

import java.util.Collection;

public interface IDroitAccesService extends IAbstractService<DroitAcces> {
    Collection<DroitAcces> findByPatient(Patient patient);
}
