package fr.limayrac.pfeback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Animation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photo;
    private String dessin;
    private String son;
    @ManyToMany(mappedBy = "animations")
    @JsonIgnore
    private List<Serie> series;
    private Boolean active;
}
