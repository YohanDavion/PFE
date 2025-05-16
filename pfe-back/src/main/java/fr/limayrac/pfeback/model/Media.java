package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.Base64;

@Entity
@Setter
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mimetype;
    @Column(columnDefinition = "LONGBLOB")
    private String data;

    public Long getId() {
        return id;
    }

    public String getMimetype() {
        return mimetype;
    }

    public String getData() {
        return data;
    }

    @Transient
    public String getDataBase64() {
        byte[] bytes = data.getBytes();
        return Base64.getEncoder().encodeToString(data.getBytes());
    }
}
