package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Setter;

@Setter
@Entity
@Table(name = "serie_status")
public class SerieStatus {
    private Long id;
    private Serie serie;
    private Patient patient;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @ManyToOne
    public Serie getSerie() {
        return serie;
    }

    @ManyToOne
    public Patient getPatient() {
        return patient;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
