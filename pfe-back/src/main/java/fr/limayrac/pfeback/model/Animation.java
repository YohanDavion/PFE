package fr.limayrac.pfeback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
public class Animation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gif")
    private Media gif;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image")
    private Media image;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "son")
    private Media son;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "serie_animations",
            joinColumns = @JoinColumn(name = "animations_id"),
            inverseJoinColumns = @JoinColumn(name = "series_id")
    )
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setGif(Media gif) {
        this.gif = gif;
    }

    public void setImage(Media image) {
        this.image = image;
    }

    public void setSon(Media son) {
        this.son = son;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animation) {
            return Objects.equals(id, ((Animation) obj).id);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.id == null ? 0 : this.id.intValue());
        return result;
    }
}