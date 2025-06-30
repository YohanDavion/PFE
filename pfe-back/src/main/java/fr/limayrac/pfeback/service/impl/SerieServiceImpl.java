package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.Serie;
import fr.limayrac.pfeback.repository.DroitAccesRepository;
import fr.limayrac.pfeback.repository.SerieRepository;
import fr.limayrac.pfeback.service.ISerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SerieServiceImpl implements ISerieService {
    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private DroitAccesRepository droitAccesRepository;
    @Override
    public List<Serie> findAll() {
        return serieRepository.findAll();
    }

    @Override
    public Serie findById(Long id) {
        return serieRepository.findById(id).orElse(null);
    }

    @Override
    public Serie save(Serie serie) {
        return serieRepository.save(serie);
    }

    @Override
    public void delete(Serie serie) {
        serieRepository.delete(serie);
    }

    @Override
    public void delete(Long id) {
        serieRepository.deleteById(id);
    }

    @Override
    public Collection<Serie> findByPatient(Patient patient) {
        return droitAccesRepository.findSeriesByPatient(patient);
    }
}
