package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Serie {
    private Long id;
    private String libelle;
//    @JoinTable(
//            name = "serie_animations",
//            joinColumns = @JoinColumn(name = "series_id"),
//            inverseJoinColumns = @JoinColumn(name = "animations_id"))
    // Le lien peut être utile s'il y a des problèmes pour récupérer le JSON
    // https://stackoverflow.com/questions/76869015/bidirectional-many-to-many-relationships-in-spring-jpa-hibernate
    private Collection<Animation> animations;
    private Boolean active;
    private Statut statut;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    @ManyToMany(mappedBy = "series")
    public Collection<Animation> getAnimations() {
        return animations;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setAnimations(Collection<Animation> animations) {
        this.animations = animations;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Transient
    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Serie) {
            return Objects.equals(id, ((Serie) obj).id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.id == null ? 0 : this.id.intValue());
        return result;
    }
}
