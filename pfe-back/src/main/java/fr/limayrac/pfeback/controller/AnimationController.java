package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Animation;
import fr.limayrac.pfeback.service.IAnimationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animation")
public class AnimationController implements IApiRestController<Animation, Long>{
    @Autowired
    private IAnimationService animationService;

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
    public Animation put(Animation entity) {
        return animationService.save(entity);
    }

    @Override
    @PatchMapping("/{id}")
    public Animation patch(Animation entity) {
        // A voir si c'est réellement utile
        return null;
    }
}
