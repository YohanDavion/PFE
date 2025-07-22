package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
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
    public Boolean getValide() {
        return valide;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }
}
