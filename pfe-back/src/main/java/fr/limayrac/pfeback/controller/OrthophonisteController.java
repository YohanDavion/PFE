package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Orthophoniste;
import fr.limayrac.pfeback.service.IOrthophonisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/orthophoniste")
public class OrthophonisteController implements IApiRestController<Orthophoniste, Long> {
    @Autowired
    private IOrthophonisteService orthophonisteService;
    @Override
    @GetMapping("/{id}")
    public Orthophoniste findById(@PathVariable Long id) {
        return orthophonisteService.findById(id);
    }

    @Override
    @GetMapping("/all")
    public Collection<Orthophoniste> findAll() {
        return orthophonisteService.findAll();
    }

    @Override
    @PostMapping
    public Orthophoniste create(@RequestBody Orthophoniste entity) {
        return orthophonisteService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orthophonisteService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Orthophoniste put(@RequestBody Orthophoniste entity) {
        entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
        return orthophonisteService.save(entity);
    }

    @Override
    @PatchMapping("/{id}")
    public Orthophoniste patch(Orthophoniste entity) {
        return null;
    }
}
