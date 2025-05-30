package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Setter
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mimetype;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    public Long getId() {
        return id;
    }

    public String getMimetype() {
        return mimetype;
    }

    public byte[] getData() {
        return data;
    }

//    @Transient
//    public String getDataBase64() {
//        byte[] bytes = data.getBytes();
//        return Base64.getEncoder().encodeToString(data.getBytes());
//    }
}
