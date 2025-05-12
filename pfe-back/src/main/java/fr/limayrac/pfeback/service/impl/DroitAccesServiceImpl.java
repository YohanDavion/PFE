package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.DroitAcces;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.repository.DroitAccesRepository;
import fr.limayrac.pfeback.service.IDroitAccesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DroitAccesServiceImpl implements IDroitAccesService {
    @Autowired
    private DroitAccesRepository droitAccesRepository;
    @Override
    public List<DroitAcces> findAll() {
        return droitAccesRepository.findAll();
    }

    @Override
    public DroitAcces findById(Long id) {
        return droitAccesRepository.findById(id).orElse(null);
    }

    @Override
    public DroitAcces save(DroitAcces droitAcces) {
        return droitAccesRepository.save(droitAcces);
    }

    @Override
    public void delete(DroitAcces droitAcces) {
        droitAccesRepository.delete(droitAcces);
    }

    @Override
    public void delete(Long id) {
        droitAccesRepository.deleteById(id);
    }

    @Override
    public Collection<DroitAcces> findByPatient(Patient patient) {
        return droitAccesRepository.findByPatient(patient);
    }
}
