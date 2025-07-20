package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String name;
    
    private String description;
    
    @Enumerated(EnumType.STRING)
    private PermissionType type;
    
    public enum PermissionType {
        READ, write, delete, admin
    }
}