package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Administrateur;
import fr.limayrac.pfeback.service.IAdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdministrateurController implements IApiRestController<Administrateur, Long> {
    @Autowired
    private IAdministrateurService administrateurService;

    @Override
    public Administrateur findById(Long aLong) {
        return administrateurService.findById(aLong);
    }

    @Override
    public Collection<Administrateur> findAll() {
        return administrateurService.findAll();
    }

    @Override
    public Administrateur create(Administrateur entity) {
        return administrateurService.save(entity);
    }

    @Override
    public void delete(Long entity) {
        administrateurService.delete(entity);
    }

    @Override
    public Administrateur put(Administrateur entity) {
        return administrateurService.save(entity);
    }

    @Override
    public Administrateur patch(Administrateur entity) {
        return null;
    }
}
