package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.Abonnement;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.PatientAbonnement;

import java.util.List;

public interface IAbonnementService extends IAbstractService<Abonnement> {
    Boolean rejoindreAbonnement(Long abonnementId, Patient patient, Patient owner);

    Patient findPatientProprietaireByLogin(Patient patient, String mail);

    void createProprietaire(PatientAbonnement patientAbonnement);

    PatientAbonnement findFirstByPatientAndAbonnement(Patient proprietaire, Abonnement abonnement);

    PatientAbonnement findFirstByPatientAndProprietaireAndAbonnement(Patient patient, Patient proprietaire, Abonnement abonnement);
    List<PatientAbonnement> findByProprietaire(Patient proprietaire);

    PatientAbonnement savePatientAbonnement(PatientAbonnement patientAbonnement);

    PatientAbonnement findPatientAbonnementByPatientProprietaireAbonnement(Patient patient, Patient owner, Abonnement abonnement);

    void deletePatientAbonnement(PatientAbonnement patientAbonnement);
}
