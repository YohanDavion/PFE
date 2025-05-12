package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Abonnement;
import fr.limayrac.pfeback.service.IAbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/abonnement")
public class AbonnementController implements IApiRestController<Abonnement, Long> {
    @Autowired
    private IAbonnementService abonnementService;
    @Override
    @GetMapping("/{id}")
    public Abonnement findById(@PathVariable Long id) {
        return abonnementService.findById(id);
    }

    @Override
    @GetMapping("/all")
    public Collection<Abonnement> findAll() {
        return abonnementService.findAll();
    }

    @Override
    @PostMapping
    public Abonnement create(Abonnement entity) {
        return abonnementService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        abonnementService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Abonnement put(Abonnement entity) {
        return abonnementService.save(entity);
    }

    @Override
    @PatchMapping("/{id}")
    public Abonnement patch(Abonnement entity) {
        return null;
    }
}
