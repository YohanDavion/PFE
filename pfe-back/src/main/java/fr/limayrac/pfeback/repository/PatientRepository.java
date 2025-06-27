package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.Orthophoniste;
import fr.limayrac.pfeback.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByOrthophoniste(Orthophoniste orthophoniste);
}
