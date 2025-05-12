package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @OneToOne // Si un seul abonnement
//    @ManyToOne // Si plusieurs abonnements par patients
    private Patient patient;
}
