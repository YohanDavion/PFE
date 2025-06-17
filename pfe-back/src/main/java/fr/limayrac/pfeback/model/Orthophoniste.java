package fr.limayrac.pfeback.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@DiscriminatorValue("ORTHOPHONISTE")
public class Orthophoniste extends User implements Serializable {
    private String nom;
    private String prenom;
    private String adresse;
    private String SIRET;
    private String RPPS;
    private byte[] photo;
    private Collection<CoordonneeBancaire> coordonneeBancaires;

    public Orthophoniste() {
        super();
        setRole(Role.ORTHOPHONISTE);
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

    @Lob
    @Column(name = "photo_orthophoniste", columnDefinition = "LONGBLOB")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setCoordonneeBancaires(Collection<CoordonneeBancaire> coordonneeBancaires) {
        this.coordonneeBancaires = coordonneeBancaires;
    }

    @OneToMany(mappedBy = "user")
    public Collection<CoordonneeBancaire> getCoordonneeBancaires() {
        return coordonneeBancaires;
    }
}
