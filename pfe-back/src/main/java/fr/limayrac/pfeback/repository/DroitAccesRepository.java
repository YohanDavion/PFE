package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.DroitAcces;
import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DroitAccesRepository extends JpaRepository<DroitAcces, Long> {
    List<DroitAcces> findByPatient(Patient patient);
    @Query("select da.serie from DroitAcces da where da.patient = :patient")
    Collection<Serie> findSeriesByPatient(Patient patient);
}
