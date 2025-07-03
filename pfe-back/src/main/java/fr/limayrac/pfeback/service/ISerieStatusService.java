package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.Serie;
import fr.limayrac.pfeback.model.SerieStatus;

import java.util.List;

public interface ISerieStatusService extends IAbstractService<SerieStatus> {
    List<Serie> findSerieByPatient(Patient patient);
    List<SerieStatus> findByPatient(Patient patient);

    void deleteByPatient(Patient patient);

    void deleteAll(List<SerieStatus> serieStatuses);
}
