package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Abonnement;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.PatientAbonnement;
import fr.limayrac.pfeback.repository.AbonnementRepository;
import fr.limayrac.pfeback.repository.PatientAbonnementRepository;
import fr.limayrac.pfeback.repository.PatientRepository;
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
    @Autowired
    private PatientRepository patientRepository;

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
        // On vérifie que le propriétaire ne soit pas qu'un abonnement simple
        if (owner.getAbonnement().getMaxAbonnement() > 1) {
            Abonnement abonnement = findById(abonnementId);

            int nbUsers = patientAbonnementRepository.countProprietaireAbonnement(abonnement, owner.getLogin());

            if (nbUsers < abonnement.getMaxAbonnement()) {
                patient.setAbonnement(abonnement);
                patient = patientRepository.save(patient);

                PatientAbonnement patientAbonnement = new PatientAbonnement();
                patientAbonnement.setAbonnement(abonnement);
                patientAbonnement.setPatient(patient);
                patientAbonnement.setProprietaire(owner);
                patientAbonnement.setValide(false);
                patientAbonnementRepository.save(patientAbonnement);
                return true;
            } else {
                // On vérifie que l'abonnement ne soit pas complet
                return false;
            }
        } else {
            // Abo simple
            return false;
        }
    }

    @Override
    public Patient findPatientProprietaireByLogin(Patient patient, String mail) {
        return patientAbonnementRepository.findProprietaireByPatientAndProprietaireMail(patient, mail);
    }

    @Override
    public void createProprietaire(PatientAbonnement patientAbonnement) {
        patientAbonnementRepository.save(patientAbonnement);
    }

    @Override
    public PatientAbonnement findFirstByPatientAndAbonnement(Patient proprietaire, Abonnement abonnement) {
        return patientAbonnementRepository.findFirstByPatientAndAbonnement(proprietaire, abonnement);
    }

    @Override
    public PatientAbonnement findFirstByPatientAndProprietaireAndAbonnement(Patient patient, Patient proprietaire, Abonnement abonnement) {
        return patientAbonnementRepository.findFirstByPatientAndProprietaireAndAbonnement(patient, proprietaire, abonnement);
    }

    @Override
    public List<PatientAbonnement> findByProprietaire(Patient proprietaire) {
        return patientAbonnementRepository.findByProprietaire(proprietaire);
    }

    @Override
    public PatientAbonnement savePatientAbonnement(PatientAbonnement patientAbonnement) {
        return patientAbonnementRepository.save(patientAbonnement);
    }

    @Override
    public PatientAbonnement findPatientAbonnementByPatientProprietaireAbonnement(Patient patient, Patient owner, Abonnement abonnement) {
        return patientAbonnementRepository.findByPatientAndProprietaireAndAbonnement(patient, owner, abonnement);
    }

    @Override
    public void deletePatientAbonnement(PatientAbonnement patientAbonnement) {
        patientAbonnementRepository.delete(patientAbonnement);
    }
}
