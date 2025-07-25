package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.dto.CreateSerieDTO;
import fr.limayrac.pfeback.model.*;
import fr.limayrac.pfeback.security.CustomUserDetails;
import fr.limayrac.pfeback.service.IAnimationService;
import fr.limayrac.pfeback.service.IDroitAccesService;
import fr.limayrac.pfeback.service.ISerieService;
import fr.limayrac.pfeback.service.ISerieStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
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
    @Autowired
    private ISerieStatusService serieStatusService;

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

    @GetMapping("/all-patient")
    public Collection<Serie> findAllPatient() {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal != null) {
            List<Serie> allSeries = serieService.findAll();
            //Trie par ordre alphabétique
            allSeries.sort(Comparator.comparing(Serie::getLibelle));
            List<Serie> serieToReturn = new ArrayList<>();
            if (principal.getUser() instanceof Patient patient) {
                List<Serie> serieEffectue = serieStatusService.findSerieByPatient(patient);
                if (patient.getOrthophoniste() != null) {
                    // On affiche seulement les séries dont il a les droits
                    Collection<DroitAcces> droitAcces = droitAccesService.findByPatient(patient);
                    for (Serie serie : allSeries) {
                        for (DroitAcces droitAccesLooped : droitAcces) {
                            // S'il à les droits pour la série, on l'ajoute à la liste à afficher
                            if (serie.equals(droitAccesLooped.getSerie())) {
                                if (serieEffectue.contains(serie)) {
                                    serie.setStatut(Statut.TERMINE);
                                } else {
                                    serie.setStatut(Statut.EN_COURS);
                                }
                                serieToReturn.add(serie);
                                break;
                            }
                        }
                    }
                    return serieToReturn;
                } else {
                    // Patient public
                    Serie derniereTermine = null;
                    for (Serie serie : allSeries) {
                        // On récupère la dernière série terminée
                        if (serieEffectue.contains(serie)) {
                            derniereTermine = serie;
                            serie.setStatut(Statut.TERMINE);
                        } else {
                            serie.setStatut(Statut.A_FAIRE);
                        }
                        serieToReturn.add(serie);
                    }

                    int index = allSeries.indexOf(derniereTermine);
                    if (index < allSeries.size() - 2) {
                        allSeries.get(index+1).setStatut(Statut.EN_COURS);
                    }

                    return serieToReturn;
                }
            } else {
//                for (Serie serie : allSeries) {
//                    serie.setStatut(Statut.TERMINE);
//                }
//                return allSeries;
            }
        }
        return null;
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

        List<Animation> animations = new ArrayList<>();
        for (Animation animation : animationService.findAll()) {
            if (entity.getAnimations().contains(animation)) {
                // On retire la liaison entre la série et l'animation
                //On évite d'ajouter un doublon
                if (!animation.getSeries().contains(serie)) {
                    animation.getSeries().add(serie);
                }
            } else {
                animation.getSeries().remove(serie);
            }
            animations.add(animation);
        }
        animationService.saveAll(animations);
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


    @GetMapping("/{id}/validate")
    public Serie validateSerie(@PathVariable Long id) {
        Serie serie = serieService.findById(id);
        Patient patient = (Patient) ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        SerieStatus serieStatus = new SerieStatus();
        serieStatus.setSerie(serie);
        serieStatus.setPatient(patient);
        serieStatusService.save(serieStatus);

        return serie;
    }
}
