package fr.limayrac.pfeback.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
    ADMINISTRATEUR(0, "Administrateur"),
    ORTHOPHONISTE(1, "Orthophoniste"),
    PATIENT(1, "Patient");

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
