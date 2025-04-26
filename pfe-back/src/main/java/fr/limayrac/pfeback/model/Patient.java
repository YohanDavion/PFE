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
public class Patient extends User {
    private String nom;
    private String prenom;
    private String nomParent;
    private String prenomParent;
    private String adresse;
    private String photo;
    private Collection<CoordonneeBancaire> coordonneeBancaires;

    @OneToMany(mappedBy = "user")
    public Collection<CoordonneeBancaire> getCoordonneeBancaires() {
        return coordonneeBancaires;
    }
}
