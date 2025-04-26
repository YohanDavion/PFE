package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Administrateur;
import fr.limayrac.pfeback.service.IAdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/administrateur")
public class AdministrateurController implements IApiRestController<Administrateur, Long> {
    @Autowired
    private IAdministrateurService administrateurService;

    @Override
    @GetMapping("/{id}")
    public Administrateur findById(@PathVariable Long id) {
        return administrateurService.findById(id);
    }

    @Override
    @GetMapping("/all")
    public Collection<Administrateur> findAll() {
        return administrateurService.findAll();
    }

    @Override
    @PostMapping
    public Administrateur create(Administrateur entity) {
        return administrateurService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        administrateurService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Administrateur put(Administrateur entity) {
        return administrateurService.save(entity);
    }

    @Override
    @PatchMapping("/{id}")
    public Administrateur patch(Administrateur entity) {
        return null;
    }
}
