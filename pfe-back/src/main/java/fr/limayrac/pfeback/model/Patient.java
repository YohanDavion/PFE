package fr.limayrac.pfeback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@PrimaryKeyJoinColumn(name = "user_id")
@DiscriminatorValue("PATIENT")
public class Patient extends User {
    private String nom;
    private String prenom;
    private String nomParent;
    private String prenomParent;
    private String adresse;
    private byte[] photo;
    @JsonIgnore
    private Collection<CoordonneeBancaire> coordonneeBancaires;
    private Orthophoniste orthophoniste;

    public Patient() {
        super();
        setRole(Role.PATIENT);
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    public Collection<CoordonneeBancaire> getCoordonneeBancaires() {
        return coordonneeBancaires;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Lob
    @Column(name = "photo_patient", columnDefinition = "LONGBLOB")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setCoordonneeBancaires(Collection<CoordonneeBancaire> coordonneeBancaires) {
        this.coordonneeBancaires = coordonneeBancaires;
    }

    @ManyToOne
    @JoinColumn(name = "orthophoniste", columnDefinition = "BIGINT")
    public Orthophoniste getOrthophoniste() {
        return orthophoniste;
    }

    public void setOrthophoniste(Orthophoniste orthophoniste) {
        this.orthophoniste = orthophoniste;
    }

    @Override
    public String getTelephone() {
        return super.getTelephone();
    }

    @Override
    public void setTelephone(String telephone) {
        super.setTelephone(telephone);
    }
}
