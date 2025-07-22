package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.Abonnement;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.PatientAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientAbonnementRepository extends JpaRepository<PatientAbonnement, Long> {
    @Query("select pa.patient from PatientAbonnement pa where pa.patient = :patient and pa.proprietaire.login = :mail")
    Patient findProprietaireByPatientAndProprietaireMail(Patient patient, String mail);
    @Query("select count(*) from PatientAbonnement pa where pa.abonnement = :abonnement and pa.proprietaire.login = :mailOwner")
    Integer countProprietaireAbonnement(Abonnement abonnement, String mailOwner);
    PatientAbonnement findFirstByPatientAndAbonnement(Patient proprietaire, Abonnement abonnement);
    PatientAbonnement findFirstByPatientAndProprietaireAndAbonnement(Patient patient, Patient proprietaire, Abonnement abonnement);
    List<PatientAbonnement> findByProprietaire(Patient proprietaire);
    PatientAbonnement findByPatientAndProprietaireAndAbonnement(Patient patient, Patient proprietaire, Abonnement abonnement);
}
