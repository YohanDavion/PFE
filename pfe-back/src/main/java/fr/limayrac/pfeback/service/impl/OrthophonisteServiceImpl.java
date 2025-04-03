package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Orthophoniste;
import fr.limayrac.pfeback.repository.OrthophonisteRepository;
import fr.limayrac.pfeback.service.IOrthophonisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrthophonisteServiceImpl implements IOrthophonisteService {
    @Autowired
    private OrthophonisteRepository orthophonisteRepository;
    @Override
    public List<Orthophoniste> findAll() {
        return orthophonisteRepository.findAll();
    }

    @Override
    public Orthophoniste findById(Long id) {
        return orthophonisteRepository.findById(id).orElse(null);
    }

    @Override
    public Orthophoniste save(Orthophoniste orthophoniste) {
        return orthophonisteRepository.save(orthophoniste);
    }

    @Override
    public void delete(Orthophoniste orthophoniste) {
        orthophonisteRepository.delete(orthophoniste);
    }

    @Override
    public void delete(Long id) {
        orthophonisteRepository.deleteById(id);
    }
}
