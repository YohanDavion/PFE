package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.Serie;
import fr.limayrac.pfeback.model.SerieStatus;
import fr.limayrac.pfeback.repository.SerieStatusRepository;
import fr.limayrac.pfeback.service.ISerieStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieStatusService implements ISerieStatusService {
    @Autowired
    private SerieStatusRepository serieStatusRepository;
    @Override
    public List<Serie> findSerieByPatient(Patient patient) {
        return serieStatusRepository.findSerieByPatient(patient);
    }

    @Override
    public List<SerieStatus> findByPatient(Patient patient) {
        return serieStatusRepository.findByPatient(patient);
    }

    @Override
    public List<SerieStatus> findAll() {
        return serieStatusRepository.findAll();
    }

    @Override
    public SerieStatus findById(Long id) {
        return serieStatusRepository.findById(id).orElse(null);
    }

    @Override
    public SerieStatus save(SerieStatus serieStatus) {
        return serieStatusRepository.save(serieStatus);
    }

    @Override
    public void delete(SerieStatus serieStatus) {
        serieStatusRepository.delete(serieStatus);
    }

    @Override
    public void delete(Long id) {
        serieStatusRepository.deleteById(id);
    }

    @Override
    public void deleteByPatient(Patient patient) {
        serieStatusRepository.deleteByPatient(patient);
    }

    @Override
    public void deleteAll(List<SerieStatus> serieStatuses) {
        serieStatusRepository.deleteAll(serieStatuses);
    }
}
