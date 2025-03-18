package fr.limayrac.pfeback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;

@Getter
@Entity
//@DiscriminatorValue("ADMINISTRATEUR")
@PrimaryKeyJoinColumn(name = "userId")
public class Administrateur extends User {
    private String nom;
    private String prenom;

}
