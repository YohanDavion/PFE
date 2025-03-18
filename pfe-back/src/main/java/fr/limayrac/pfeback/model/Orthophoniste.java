package fr.limayrac.pfeback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "userId")
public class Orthophoniste extends User {
    private String nom;
    private String prenom;
    private String adresse;
    private String SIRET;
    private String RPPS;
    private String photo;

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSIRET() {
        return SIRET;
    }

    public void setSIRET(String SIRET) {
        this.SIRET = SIRET;
    }

    public String getRPPS() {
        return RPPS;
    }

    public void setRPPS(String RPPS) {
        this.RPPS = RPPS;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
