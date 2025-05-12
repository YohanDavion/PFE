package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Setter
//@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    private Long id;
    private String login;
    private String password;
    private Role role;
    @Getter
    private String telephone;
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
}
