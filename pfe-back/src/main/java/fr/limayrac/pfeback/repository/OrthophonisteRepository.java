package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.Orthophoniste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrthophonisteRepository extends JpaRepository<Orthophoniste, Long> {
}
