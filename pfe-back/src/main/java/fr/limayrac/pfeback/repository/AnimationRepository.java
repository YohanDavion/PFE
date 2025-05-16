package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.Animation;
import fr.limayrac.pfeback.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AnimationRepository extends JpaRepository<Animation, Long> {
    Collection<Animation> findBySeries(List<Serie> serie);
}
