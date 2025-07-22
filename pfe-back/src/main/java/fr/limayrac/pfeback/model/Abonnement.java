package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
public class Abonnement {
    private Long id;
    private String libelle;
    private String description;
    private Double montant;
    private Integer maxAbonnement;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    @Column(name = "max_abonnement")
    public Integer getMaxAbonnement() {
        return maxAbonnement;
    }

    public void setMaxAbonnement(Integer maxAbonnement) {
        this.maxAbonnement = maxAbonnement;
    }

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
