package fr.limayrac.pfeback.repository;

import fr.limayrac.pfeback.model.Role;
import fr.limayrac.pfeback.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findByRole(Role role);
    
    @Query("SELECT rp.permission.name FROM RolePermission rp WHERE rp.role = :role")
    List<String> findPermissionNamesByRole(Role role);
}