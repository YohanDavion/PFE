package fr.limayrac.pfeback.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.STRING)
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(unique = true, nullable = false, length = 50)
    private String login;
    @Column(nullable = false, length = 200)
    private String password;
    @Column(name = "role")
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String email) {
        this.login = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
