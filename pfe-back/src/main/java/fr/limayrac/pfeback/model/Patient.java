package fr.limayrac.pfeback.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {
    private String nom;
    private String prenom;
    private String nomParent;
    private String prenomParent;

    public String getNomPatient() {
        return nom;
    }

    public void setNomPatient(String nomPatient) {
        this.nom = nomPatient;
    }

    public String getPrenomPatient() {
        return prenom;
    }

    public void setPrenomPatient(String prenomPatient) {
        this.prenom = prenomPatient;
    }

    public String getNomParent() {
        return nomParent;
    }

    public void setNomParent(String nomParent) {
        this.nomParent = nomParent;
    }

    public String getPrenomParent() {
        return prenomParent;
    }

    public void setPrenomParent(String prenomParent) {
        this.prenomParent = prenomParent;
    }
}
