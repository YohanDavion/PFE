package fr.limayrac.pfeback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Orthophoniste extends User {
    private String nom;
    private String prenom;
    private String adresse;
    private String SIRET;
    private String RPPS;
    private String photo;
    private Collection<CoordonneeBancaire> coordonneeBancaires;

    @OneToMany(mappedBy = "user")
    public Collection<CoordonneeBancaire> getCoordonneeBancaires() {
        return coordonneeBancaires;
    }
}
