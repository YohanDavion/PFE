package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.Animation;
import fr.limayrac.pfeback.model.Serie;

import java.util.Collection;

public interface IAnimationService extends IAbstractService<Animation> {
    Collection<Animation> findBySerie(Serie serie);
}
