package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Abonnement;
import fr.limayrac.pfeback.repository.AbonnementRepository;
import fr.limayrac.pfeback.service.IAbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbonnementService implements IAbonnementService {
    @Autowired
    private AbonnementRepository abonnementRepository;
    @Override
    public List<Abonnement> findAll() {
        return abonnementRepository.findAll();
    }

    @Override
    public Abonnement findById(Long id) {
        return abonnementRepository.findById(id).orElse(null);
    }

    @Override
    public Abonnement save(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    @Override
    public void delete(Abonnement abonnement) {
        abonnementRepository.delete(abonnement);
    }

    @Override
    public void delete(Long id) {
        abonnementRepository.deleteById(id);
    }
}
