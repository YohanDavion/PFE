package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.Patient;
import fr.limayrac.pfeback.model.Serie;
import fr.limayrac.pfeback.model.SerieStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieStatusRepository extends JpaRepository<SerieStatus, Long> {
    @Query("select serie.serie from SerieStatus serie where serie.patient = :patient order by serie.serie.libelle")
    List<Serie> findSerieByPatient(Patient patient);
    List<SerieStatus> findByPatient(Patient patient);
    @Modifying
    @Query("DELETE from SerieStatus ss where ss.patient = :patient")
    void deleteByPatient(Patient patient);
}
