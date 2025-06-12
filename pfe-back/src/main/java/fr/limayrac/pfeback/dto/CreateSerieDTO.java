package fr.limayrac.pfeback.dto;

import java.util.Collection;

public class CreateSerieDTO {
    private Long id;
    private String libelle;
    private Collection<Long> animationIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Collection<Long> getAnimationIds() {
        return animationIds;
    }

    public void setAnimationIds(Collection<Long> animationIds) {
        this.animationIds = animationIds;
    }
}
