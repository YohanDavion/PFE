package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.Serie;
import fr.limayrac.pfeback.model.SerieStatus;

import java.util.List;

public interface ISerieStatusService extends IAbstractService<SerieStatus> {
    List<Serie> findByPatient(Patient patient);
}
