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
    @Column(columnDefinition = "LONGTEXT")
    private String photo;
    @Column(columnDefinition = "LONGTEXT")
    private String dessin;
    @Column(columnDefinition = "LONGTEXT")
    private String son;
    @ManyToMany(mappedBy = "animations")
    @JsonIgnore
    private List<Serie> series;
    private Boolean active;
}
