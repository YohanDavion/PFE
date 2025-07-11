package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PatientAbonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Abonnement abonnement;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    public Patient proprietaire;
}
