package fr.limayrac.pfeback.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

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
    private Orthophoniste orthophoniste;
    private Abonnement abonnement;
    private LocalDate datePaiement;
    private LocalDate accesGratuit;
    private LocalDate delaiPatient;

    public Patient() {
        super();
        setRole(Role.PATIENT);
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

    @ManyToOne
    @JoinColumn(name = "orthophoniste")
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

    @ManyToOne
    @JoinColumn(name = "abonnement")
    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }

    @Column(name = "acces_gratuit")
    public LocalDate getAccesGratuit() {
        return accesGratuit;
    }

    public void setAccesGratuit(LocalDate accesGratuit) {
        this.accesGratuit = accesGratuit;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public LocalDate getDelaiPatient() {
        return delaiPatient;
    }

    public void setDelaiPatient(LocalDate delaiPatient) {
        this.delaiPatient = delaiPatient;
    }

    @Transient
    public LocalDate getDateExpirationAbonnement() {
        if (datePaiement != null) {
            return datePaiement.plusMonths(1);
        } else {
            return null;
        }
    }
    @Transient
    public Boolean getAbonnementStatut() {
        boolean abonnement = this.abonnement != null;
        boolean datePaiement = false;
        if (this.datePaiement != null) {
             datePaiement = this.datePaiement.plusMonths(1).isAfter(LocalDate.now());
        }
        return abonnement && datePaiement;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Patient) {
            return Objects.equals(getId(), ((Patient) obj).getId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.getId() == null ? 0 : this.getId().intValue());
        return result;
    }
}
