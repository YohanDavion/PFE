package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.Animation;
import fr.limayrac.pfeback.model.Serie;

import java.util.Collection;
import java.util.List;

public interface IAnimationService extends IAbstractService<Animation> {
    Collection<Animation> findBySerie(Serie serie);

    Collection<Animation> findByIds(Collection<Long> ids);

    List<Animation> saveAll(List<Animation> animations);
}
