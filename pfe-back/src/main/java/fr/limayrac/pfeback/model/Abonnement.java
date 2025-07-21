package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String description;
    private Double montant;
//    private Boolean multiAbonnement;
    @Column(name = "max_abonnement")
    private Integer maxAbonnement;
    @ManyToOne
    private Patient proprietaire;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Abonnement) {
            return Objects.equals(id, ((Abonnement) obj).id);
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
