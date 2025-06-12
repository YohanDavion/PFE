package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.dto.CreateSerieDTO;
import fr.limayrac.pfeback.model.*;
import fr.limayrac.pfeback.service.IAnimationService;
import fr.limayrac.pfeback.service.IDroitAccesService;
import fr.limayrac.pfeback.service.ISerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/serie")
public class SerieController implements IApiRestController<Serie, Long> {
    @Autowired
    private ISerieService serieService;
    @Autowired
    private IAnimationService animationService;
    @Autowired
    private IDroitAccesService droitAccesService;

    @Override
    @GetMapping("/{id}")
    public Serie findById(@PathVariable(value = "id") Long id) {
        return serieService.findById(id);
    }

    @Override
    @GetMapping("/all")
    public Collection<Serie> findAll() {
        return serieService.findAll();
    }

    @Override
//    @PostMapping
    public Serie create(Serie entity) {
        return serieService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Serie serie = serieService.findById(id);
        //On enlève toute les combinaisons des animations
        for (Animation animation : serie.getAnimations()) {
            animation.getSeries().remove(serie);
//            if (animation.getSeries().isEmpty()) {
//                animationService.delete(animation);
//            } else {
            // TODO Demander si l'on supprime une série et qu'une animation est relié qu'à cette série, la supprime-t-on ?
//            }
            animationService.save(animation);
        }
        serieService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Serie put(@RequestBody Serie entity) {
        Serie serie = serieService.findById(entity.getId());
        serie.setLibelle(entity.getLibelle());
        serie.setActive(serie.getActive());

        for (Animation animation : animationService.findBySerie(serie)) {
            // TODO retrer si jamais
        }
        return serieService.save(entity);
    }

    @Override
    @PatchMapping("/{id}")
    public Serie patch(Serie entity) {
        return null;
    }

    @GetMapping("/user")
    public Collection<Serie> findByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = (Patient) user;
        Collection<DroitAcces> droitAcces = droitAccesService.findByPatient(patient);
        return droitAcces.stream().map(DroitAcces::getSerie).collect(Collectors.toList());
    }

    @GetMapping("/user/{serieId}/animations")
    public Collection<Animation> findByUserAnim(@PathVariable Long serieId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = (Patient) user;
        Serie serie = serieService.findById(serieId);
        Collection<DroitAcces> droitAcces = droitAccesService.findByPatient(patient);
        if (droitAcces.stream().map(DroitAcces::getSerie).toList().contains(serie)) {
            return serie.getAnimations();
        } else {
            // L'utilisateur n'a pas les droits pour cette série
            return null;
        }
    }

    @PostMapping
    public Serie createSerie(@RequestBody CreateSerieDTO dto) {
        Serie serie = new Serie();
        serie.setActive(true);
        serie.setLibelle(dto.getLibelle());

        Collection<Animation> animations = animationService.findByIds(dto.getAnimationIds());
        serie.setAnimations(animations);

        return serieService.save(serie);
    }
}
