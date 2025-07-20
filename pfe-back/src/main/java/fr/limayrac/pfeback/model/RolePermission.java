package fr.limayrac.pfeback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "role_permissions")
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
}