package fr.limayrac.pfeback.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double montant;
    private LocalDateTime datePaiement;
    // Voir pour faire une enum si évolution
    private String moyenPaiement;
    @ManyToOne
    private Abonnement abonnement;
    @ManyToOne
    private CoordonneeBancaire coordonneeBancaire;
}
