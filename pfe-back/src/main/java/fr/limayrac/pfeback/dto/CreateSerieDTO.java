package fr.limayrac.pfeback.dto;

import java.util.Collection;

public class CreateSerieDTO {
    private String libelle;
    private Collection<Long> animationIds;

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
