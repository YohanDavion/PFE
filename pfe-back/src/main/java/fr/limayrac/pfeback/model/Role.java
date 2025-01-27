package fr.limayrac.pfeback.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
    PROFESSEUR(0, "Professeur"),
    ADMIN(1, "Administrateur");

    private int id;
    private String libelle;

    Role(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
