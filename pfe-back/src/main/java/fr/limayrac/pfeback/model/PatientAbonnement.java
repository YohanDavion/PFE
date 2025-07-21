package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class PatientAbonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Abonnement abonnement;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Patient proprietaire;
    private Boolean valide;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PatientAbonnement) {
            return Objects.equals(id, ((PatientAbonnement) obj).id);
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
