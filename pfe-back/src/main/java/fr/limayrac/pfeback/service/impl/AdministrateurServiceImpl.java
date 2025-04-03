package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Administrateur;
import fr.limayrac.pfeback.repository.AdministrateurRepository;
import fr.limayrac.pfeback.service.IAdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministrateurServiceImpl implements IAdministrateurService {
    @Autowired
    private AdministrateurRepository administrateurRepository;
    @Override
    public List<Administrateur> findAll() {
        return administrateurRepository.findAll();
    }

    @Override
    public Administrateur findById(Long id) {
        return administrateurRepository.findById(id).orElse(null);
    }

    @Override
    public Administrateur save(Administrateur administrateur) {
        return administrateurRepository.save(administrateur);
    }

    @Override
    public void delete(Administrateur administrateur) {
        administrateurRepository.delete(administrateur);
    }

    @Override
    public void delete(Long id) {
        administrateurRepository.deleteById(id);
    }
}
