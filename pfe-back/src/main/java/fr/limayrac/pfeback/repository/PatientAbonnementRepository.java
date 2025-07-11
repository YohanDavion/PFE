package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.Abonnement;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.PatientAbonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAbonnementRepository extends JpaRepository<PatientAbonnement, Long> {
    @Query("select pa.patient from PatientAbonnement pa where pa.proprietaire.login = :mail")
    Patient findProprietaireByMail(String mail);
    @Query("select count(*) from PatientAbonnement pa where pa.abonnement = :abonnement and pa.proprietaire.login = :mailOwner")
    Integer countProprietaireAbonnement(Abonnement abonnement, String mailOwner);
}
