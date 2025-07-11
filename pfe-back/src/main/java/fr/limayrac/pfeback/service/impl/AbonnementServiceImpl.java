package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Abonnement;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.PatientAbonnement;
import fr.limayrac.pfeback.repository.AbonnementRepository;
import fr.limayrac.pfeback.repository.PatientAbonnementRepository;
import fr.limayrac.pfeback.service.IAbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbonnementServiceImpl implements IAbonnementService {
    @Autowired
    private AbonnementRepository abonnementRepository;
    @Autowired
    private PatientAbonnementRepository patientAbonnementRepository;
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

    @Override
    public Boolean rejoindreAbonnement(Long abonnementId, Patient patient, Patient owner) {
        Abonnement abonnement = findById(abonnementId);
        // On vérifie que l'abonnement ne soit pas complet
        int nbUsers = patientAbonnementRepository.countProprietaireAbonnement(abonnement, owner.getLogin());
        if (nbUsers < abonnement.getMaxAbonnement()) {
            PatientAbonnement patientAbonnement = new PatientAbonnement();
            patientAbonnement.setAbonnement(abonnement);
            patientAbonnement.setPatient(patient);
            patientAbonnement.setProprietaire(owner);
            patientAbonnementRepository.save(patientAbonnement);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Patient findPatientProprietaireByLogin(String mail) {
        return patientAbonnementRepository.findProprietaireByMail(mail);
    }

    @Override
    public void createProprietaire(PatientAbonnement patientAbonnement) {
        patientAbonnementRepository.save(patientAbonnement);
    }
}
