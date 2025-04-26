package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DroitAcces {
    private Long id;
    private Patient patient;
    private Serie serie;
    // Indique si le patient a validé la série
    private Boolean valide;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    @ManyToOne
    public Patient getPatient() {
        return patient;
    }
    @ManyToOne
    public Serie getSerie() {
        return serie;
    }
}
