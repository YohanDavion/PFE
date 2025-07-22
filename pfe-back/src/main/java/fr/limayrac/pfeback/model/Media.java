package fr.limayrac.pfeback.model;

import jakarta.persistence.*;

@Entity
public class Media {
    private Long id;
    private String mimetype;
    private byte[] data;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public String getMimetype() {
        return mimetype;
    }
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    public byte[] getData() {
        return data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
