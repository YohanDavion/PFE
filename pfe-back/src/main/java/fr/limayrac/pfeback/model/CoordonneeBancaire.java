package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Entity
public class CoordonneeBancaire {
    private Long id;
    @Getter
    private String code;
    @Getter
    private String dateExpiration;
    @Getter
    private String crypto;
    @Getter
    private String titulaire;
    private User user;
//    private Orthophoniste orthophoniste;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }


}
