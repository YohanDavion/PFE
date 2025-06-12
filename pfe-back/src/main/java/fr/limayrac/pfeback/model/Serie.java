package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Collection;

@Entity
@Getter
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @ManyToMany(mappedBy = "series")
//    @JoinTable(
//            name = "serie_animations",
//            joinColumns = @JoinColumn(name = "series_id"),
//            inverseJoinColumns = @JoinColumn(name = "animations_id"))
    // Le lien peut être utile s'il y a des problèmes pour récupérer le JSON
    // https://stackoverflow.com/questions/76869015/bidirectional-many-to-many-relationships-in-spring-jpa-hibernate
    private Collection<Animation> animations;
    private Boolean active;

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
}
