package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Permission;
import fr.limayrac.pfeback.model.Role;
import fr.limayrac.pfeback.model.RolePermission;
import fr.limayrac.pfeback.model.User;
import fr.limayrac.pfeback.repository.PermissionRepository;
import fr.limayrac.pfeback.security.PermissionRequired;
import fr.limayrac.pfeback.service.IRolePermissionService;
import fr.limayrac.pfeback.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/roles")
public class AdminRoleController {
    
    @Autowired
    private IRolePermissionService rolePermissionService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @GetMapping("/all")
    @PermissionRequired("role.manage")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = Arrays.asList(Role.values());
        return ResponseEntity.ok(roles);
    }
    
    @GetMapping("/permissions")
    @PermissionRequired("role.manage")
    public ResponseEntity<List<Permission>> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return ResponseEntity.ok(permissions);
    }
    
    @GetMapping("/{role}/permissions")
    @PermissionRequired("role.manage")
    public ResponseEntity<List<Permission>> getRolePermissions(@PathVariable Role role) {
        List<Permission> permissions = rolePermissionService.getPermissionsByRole(role);
        return ResponseEntity.ok(permissions);
    }
    
    @PostMapping("/{role}/permissions/{permissionName}")
    @PermissionRequired("role.manage")
    public ResponseEntity<Map<String, String>> assignPermissionToRole(
            @PathVariable Role role, 
            @PathVariable String permissionName) {
        try {
            rolePermissionService.assignPermissionToRole(role, permissionName);
            return ResponseEntity.ok(Map.of("message", "Permission assignée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @DeleteMapping("/{role}/permissions/{permissionName}")
    @PermissionRequired("role.manage")
    public ResponseEntity<Map<String, String>> removePermissionFromRole(
            @PathVariable Role role, 
            @PathVariable String permissionName) {
        try {
            rolePermissionService.removePermissionFromRole(role, permissionName);
            return ResponseEntity.ok(Map.of("message", "Permission supprimée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @PutMapping("/user/{userId}/role")
    @PermissionRequired("role.manage")
    public ResponseEntity<Map<String, String>> changeUserRole(
            @PathVariable Long userId, 
            @RequestParam Role newRole) {
        try {
            User user = userService.findById(userId);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            
            user.setRole(newRole);
            userService.save(user);
            
            return ResponseEntity.ok(Map.of("message", "Rôle modifié avec succès"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/matrix")
    @PermissionRequired("role.manage")
    public ResponseEntity<Map<String, Object>> getRolePermissionMatrix() {
        List<Role> roles = Arrays.asList(Role.values());
        List<Permission> permissions = permissionRepository.findAll();
        
        Map<String, Object> matrix = Map.of(
            "roles", roles,
            "permissions", permissions,
            "assignments", rolePermissionService.getAllRolePermissions()
        );
        
        return ResponseEntity.ok(matrix);
    }
}