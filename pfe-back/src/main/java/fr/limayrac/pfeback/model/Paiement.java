package fr.limayrac.pfeback.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime datePaiement;
    @ManyToOne
    private Abonnement abonnement;
    @ManyToOne
    private CoordonneeBancaire coordonneeBancaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDateTime datePaiement) {
        this.datePaiement = datePaiement;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    public CoordonneeBancaire getCoordonneeBancaire() {
        return coordonneeBancaire;
    }

    public void setCoordonneeBancaire(CoordonneeBancaire coordonneeBancaire) {
        this.coordonneeBancaire = coordonneeBancaire;
    }
}
