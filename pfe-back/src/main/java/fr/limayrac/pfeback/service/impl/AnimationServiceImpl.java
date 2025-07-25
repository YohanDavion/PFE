package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Animation;
import fr.limayrac.pfeback.model.Serie;
import fr.limayrac.pfeback.repository.AnimationRepository;
import fr.limayrac.pfeback.service.IAnimationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AnimationServiceImpl implements IAnimationService {
    @Autowired
    private AnimationRepository animationRepository;

    @Override
    public List<Animation> findAll() {
        return animationRepository.findAll();
    }

    @Override
    public Animation findById(Long id) {
        return animationRepository.findById(id).orElse(null);
    }

    @Override
    public Animation save(Animation patient) {
        return animationRepository.save(patient);
    }

    @Override
    public void delete(Animation patient) {
        animationRepository.delete(patient);
    }

    @Override
    public void delete(Long id) {
        animationRepository.deleteById(id);
    }

    @Override
    public Collection<Animation> findBySerie(Serie serie) {
        return animationRepository.findBySeries(List.of(serie));
    }

    @Override
    public Collection<Animation> findByIds(Collection<Long> ids) {
        return animationRepository.findAllById(ids);
    }

    @Override
    public List<Animation> saveAll(List<Animation> animations) {
        return animationRepository.saveAll(animations);
    }
}
