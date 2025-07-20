package fr.limayrac.pfeback.controller;

import fr.limayrac.pfeback.model.Permission;
import fr.limayrac.pfeback.model.Role;
import fr.limayrac.pfeback.model.RolePermission;
import fr.limayrac.pfeback.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/role-permissions")
public class RolePermissionController {
    
    @Autowired
    private IRolePermissionService rolePermissionService;
    
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Permission>> getPermissionsByRole(@PathVariable Role role) {
        List<Permission> permissions = rolePermissionService.getPermissionsByRole(role);
        return ResponseEntity.ok(permissions);
    }
    
    @GetMapping("/role/{role}/names")
    public ResponseEntity<List<String>> getPermissionNamesByRole(@PathVariable Role role) {
        List<String> permissionNames = rolePermissionService.getPermissionNamesByRole(role);
        return ResponseEntity.ok(permissionNames);
    }
    
    @GetMapping("/check")
    public ResponseEntity<Map<String, Boolean>> checkPermission(
            @RequestParam Role role, 
            @RequestParam String permission) {
        boolean hasPermission = rolePermissionService.hasPermission(role, permission);
        return ResponseEntity.ok(Map.of("hasPermission", hasPermission));
    }
    
    @PostMapping("/assign")
    public ResponseEntity<RolePermission> assignPermission(
            @RequestParam Role role, 
            @RequestParam String permissionName) {
        RolePermission rolePermission = rolePermissionService.assignPermissionToRole(role, permissionName);
        return ResponseEntity.ok(rolePermission);
    }
    
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removePermission(
            @RequestParam Role role, 
            @RequestParam String permissionName) {
        rolePermissionService.removePermissionFromRole(role, permissionName);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<RolePermission>> getAllRolePermissions() {
        List<RolePermission> rolePermissions = rolePermissionService.getAllRolePermissions();
        return ResponseEntity.ok(rolePermissions);
    }
}