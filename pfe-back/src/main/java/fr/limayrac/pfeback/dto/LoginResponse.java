package fr.limayrac.pfeback.dto;

import fr.limayrac.pfeback.model.Role;

public class LoginResponse {

    private String token;
    private Role role;
    private Boolean abonnementOk;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getAbonnementOk() {
        return abonnementOk;
    }

    public void setAbonnementOk(Boolean abonnementOk) {
        this.abonnementOk = abonnementOk;
    }
}