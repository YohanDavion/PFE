package fr.limayrac.pfeback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
public class Animation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @ManyToOne
    @JoinColumn(name = "gif")
    private Media gif;
    @ManyToOne
    @JoinColumn(name = "image")
    private Media image;
    @ManyToOne
    @JoinColumn(name = "son")
    private Media son;
    @ManyToMany(mappedBy = "animations")
    @JsonIgnore
    private List<Serie> series;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Media getGif() {
        return gif;
    }

    public Media getImage() {
        return image;
    }

    public Media getSon() {
        return son;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public Boolean getActive() {
        return active;
    }
}
