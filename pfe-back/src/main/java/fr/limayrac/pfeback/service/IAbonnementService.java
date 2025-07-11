package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.Abonnement;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.PatientAbonnement;

public interface IAbonnementService extends IAbstractService<Abonnement> {
    Boolean rejoindreAbonnement(Long abonnementId, Patient patient, Patient owner);

    Patient findPatientProprietaireByLogin(String mail);

    void createProprietaire(PatientAbonnement patientAbonnement);
}
