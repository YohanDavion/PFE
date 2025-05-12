package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Paiement;
import fr.limayrac.pfeback.repository.PaiementRepository;
import fr.limayrac.pfeback.service.IPaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementServiceImpl implements IPaiementService {
    @Autowired
    private PaiementRepository paiementRepository;
    @Override
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }

    @Override
    public Paiement findById(Long id) {
        return paiementRepository.findById(id).orElse(null);
    }

    @Override
    public Paiement save(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public void delete(Paiement paiement) {
        paiementRepository.delete(paiement);
    }

    @Override
    public void delete(Long id) {
        paiementRepository.deleteById(id);
    }
}
