package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Serie;
import fr.limayrac.pfeback.service.ISerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/serie")
public class SerieController implements IApiRestController<Serie, Long> {
    @Autowired
    private ISerieService serieService;

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
    @PostMapping
    public Serie create(Serie entity) {
        return serieService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(Long entity) {
        serieService.delete(entity);
    }

    @Override
    @PutMapping("/{id}")
    public Serie put(Serie entity) {
        return serieService.save(entity);
    }

    @Override
    @PatchMapping("/{id}")
    public Serie patch(Serie entity) {
        return null;
    }
}
