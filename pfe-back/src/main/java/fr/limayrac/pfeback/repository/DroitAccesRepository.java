package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.DroitAcces;
import fr.limayrac.pfeback.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DroitAccesRepository extends JpaRepository<DroitAcces, Long> {
    Collection<DroitAcces> findByPatient(Patient patient);
}
