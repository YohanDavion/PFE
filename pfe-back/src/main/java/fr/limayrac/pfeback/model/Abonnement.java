package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
