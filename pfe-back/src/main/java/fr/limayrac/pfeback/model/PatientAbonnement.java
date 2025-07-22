package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
public class PatientAbonnement {

    private Long id;
    private Abonnement abonnement;
    private Patient patient;
    private Patient proprietaire;
    private Boolean valide;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    @ManyToOne
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne
    public Patient getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Patient proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

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
