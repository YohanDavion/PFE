package fr.limayrac.pfeback.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter @Setter
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @ManyToMany
    @JsonManagedReference
    // Le lien peut être utile s'il y a des problèmes pour récupérer le JSON
    // https://stackoverflow.com/questions/76869015/bidirectional-many-to-many-relationships-in-spring-jpa-hibernate
    private Collection<Animation> animations;
    private Boolean active;
}
