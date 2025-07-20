package fr.limayrac.pfeback.service;

import fr.limayrac.pfeback.model.Permission;
import fr.limayrac.pfeback.model.Role;
import fr.limayrac.pfeback.model.RolePermission;

import java.util.List;

public interface IRolePermissionService {
    List<Permission> getPermissionsByRole(Role role);
    List<String> getPermissionNamesByRole(Role role);
    boolean hasPermission(Role role, String permissionName);
    RolePermission assignPermissionToRole(Role role, String permissionName);
    void removePermissionFromRole(Role role, String permissionName);
    List<RolePermission> getAllRolePermissions();
}