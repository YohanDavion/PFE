package fr.limayrac.pfeback.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@DiscriminatorValue("ADMINISTRATEUR")
public class Administrateur extends User {
    private String nom;
    private String prenom;

    public Administrateur() {
        super();
        setRole(Role.ADMINISTRATEUR);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
