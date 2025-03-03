package fr.limayrac.pfeback.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMINISTRATEUR")
public class Administrateur extends User {
    private String nom;
    private String prenom;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
