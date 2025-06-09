package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Animation;
import fr.limayrac.pfeback.model.Media;
import fr.limayrac.pfeback.model.Serie;
import fr.limayrac.pfeback.service.IAnimationService;
import fr.limayrac.pfeback.service.ISerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/animation")
//@CrossOrigin(origins = "http://localhost:4200")
public class AnimationController implements IApiRestController<Animation, Long>{
    @Autowired
    private IAnimationService animationService;
    @Autowired
    private ISerieService serieService;

    @Override
    @GetMapping("/all")
    public List<Animation> findAll() {
        return animationService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Animation findById(@PathVariable Long id) {
        return animationService.findById(id);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Animation create(@RequestBody Animation entity) {
        return animationService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        animationService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Animation put(@RequestBody Animation entity) {
        // On ne souhaite pas perdre la liaison avec les séries
        Animation animation = animationService.findById(entity.getId());
        animation.setLibelle(entity.getLibelle());
        animation.setActive(entity.getActive());
        animation.setImage(entity.getImage());
        animation.setGif(entity.getGif());
        animation.setSon(entity.getSon());
        return animationService.save(entity);
    }

    @Override
    @PatchMapping("/{id}")
    public Animation patch(Animation entity) {
        // A voir si c'est réellement utile
        return null;
    }

    @GetMapping("/serie/{serieId}")
    public Collection<Animation> getAnimationsBySeriesId(@PathVariable Long serieId) {
        Serie serie = serieService.findById(serieId);
        //TODO Check si l'utilisateur connecté à le droit de voir cette série
        return animationService.findBySerie(serie);
    }

    @PostMapping/*("/api/animations")*/
    public ResponseEntity<Animation> uploadAnimation(
            @RequestParam(value = "libelle") String libelle,
            @RequestParam(value = "gif") MultipartFile gif,
            @RequestParam(value = "dessin") MultipartFile dessin,
            @RequestParam(value = "son") MultipartFile son) throws IOException {

        Animation animation = new Animation();
        animation.setLibelle(libelle);

        Media media = new Media();
        media.setData(gif.getBytes());
        media.setMimetype(gif.getContentType());
        animation.setGif(media);

        media = new Media();
        media.setData(dessin.getBytes());
        media.setMimetype(dessin.getContentType());
        animation.setImage(media);

        media = new Media();
        media.setData(son.getBytes());
        media.setMimetype(son.getContentType());
        animation.setSon(media);
        //Par défaut, à la création les animations sont actives
        animation.setActive(true);

        animation = animationService.save(animation);


        return ResponseEntity.ok(animation);
    }

}
