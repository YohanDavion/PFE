package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Paiement;
import fr.limayrac.pfeback.service.IPaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/paiement")
public class PaiementController implements IApiRestController<Paiement, Long> {
    @Autowired
    private IPaiementService paiementService;

    @Override
    @GetMapping("/{id}")
    public Paiement findById(@PathVariable Long id) {
        return paiementService.findById(id);
    }

    @Override
    @GetMapping("/all")
    public Collection<Paiement> findAll() {
        return paiementService.findAll();
    }

    @Override
    @PostMapping
    public Paiement create(Paiement entity) {
        return paiementService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paiementService.delete(id);
    }

    @Override
    @PutMapping("/{id}")
    public Paiement put(Paiement entity) {
        return paiementService.save(entity);
    }

    @Override
    @PatchMapping("/{id}")
    public Paiement patch(Paiement entity) {
        return null;
    }
}
