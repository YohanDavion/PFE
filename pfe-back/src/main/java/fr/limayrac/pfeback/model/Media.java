package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mimetype;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
    @Transient
    private String dataBase64;
}
