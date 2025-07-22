package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
//@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private String telephone;
    private Boolean actif;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long getId() {
        return id;
    }
    @Column(unique = true, nullable = false, length = 50)
    public String getLogin() {
        return login;
    }
    @Column(nullable = false, length = 200)
    public String getPassword() {
        return password;
    }
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    @Column(name = "telephone")
    public String getTelephone() {
        return telephone;
    }

    @Column(name = "actif")
    public Boolean getActif() {
        return actif;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }
}
