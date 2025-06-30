package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.Serie;

import java.util.Collection;

public interface ISerieService extends IAbstractService<Serie> {
    Collection<Serie> findByPatient(Patient patient);
}
