package fr.limayrac.pfeback.security;

import fr.limayrac.pfeback.model.Role;
import fr.limayrac.pfeback.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class PermissionEvaluator {
    
    @Autowired
    private IRolePermissionService rolePermissionService;
    
    public boolean hasPermission(Authentication authentication, String permission) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Role userRole = userDetails.getUser().getRole();
        
        return rolePermissionService.hasPermission(userRole, permission);
    }
    
    public boolean hasAnyPermission(Authentication authentication, String... permissions) {
        for (String permission : permissions) {
            if (hasPermission(authentication, permission)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasAllPermissions(Authentication authentication, String... permissions) {
        for (String permission : permissions) {
            if (!hasPermission(authentication, permission)) {
                return false;
            }
        }
        return true;
    }
}