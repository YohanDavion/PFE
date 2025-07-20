package fr.limayrac.pfeback.service.impl;

import fr.limayrac.pfeback.model.Permission;
import fr.limayrac.pfeback.model.Role;
import fr.limayrac.pfeback.model.RolePermission;
import fr.limayrac.pfeback.repository.PermissionRepository;
import fr.limayrac.pfeback.repository.RolePermissionRepository;
import fr.limayrac.pfeback.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RolePermissionServiceImpl implements IRolePermissionService {
    
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    
    @Autowired
    private PermissionRepository permissionRepository;
    
    @Override
    public List<Permission> getPermissionsByRole(Role role) {
        return rolePermissionRepository.findByRole(role)
                .stream()
                .map(RolePermission::getPermission)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<String> getPermissionNamesByRole(Role role) {
        return rolePermissionRepository.findPermissionNamesByRole(role);
    }
    
    @Override
    public boolean hasPermission(Role role, String permissionName) {
        return getPermissionNamesByRole(role).contains(permissionName);
    }
    
    @Override
    public RolePermission assignPermissionToRole(Role role, String permissionName) {
        Permission permission = permissionRepository.findByName(permissionName)
                .orElseThrow(() -> new RuntimeException("Permission not found: " + permissionName));
        
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRole(role);
        rolePermission.setPermission(permission);
        
        return rolePermissionRepository.save(rolePermission);
    }
    
    @Override
    public void removePermissionFromRole(Role role, String permissionName) {
        List<RolePermission> rolePermissions = rolePermissionRepository.findByRole(role);
        rolePermissions.stream()
                .filter(rp -> rp.getPermission().getName().equals(permissionName))
                .forEach(rolePermissionRepository::delete);
    }
    
    @Override
    public List<RolePermission> getAllRolePermissions() {
        return rolePermissionRepository.findAll();
    }
}