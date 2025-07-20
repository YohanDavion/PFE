package fr.limayrac.pfeback.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PermissionAspect {
    
    @Autowired
    private PermissionEvaluator permissionEvaluator;
    
    @Around("@annotation(permissionRequired)")
    public Object checkPermission(ProceedingJoinPoint joinPoint, PermissionRequired permissionRequired) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("User not authenticated");
        }
        
        String[] requiredPermissions = permissionRequired.value();
        boolean requireAll = permissionRequired.requireAll();
        
        boolean hasAccess;
        if (requireAll) {
            hasAccess = permissionEvaluator.hasAllPermissions(authentication, requiredPermissions);
        } else {
            hasAccess = permissionEvaluator.hasAnyPermission(authentication, requiredPermissions);
        }
        
        if (!hasAccess) {
            throw new AccessDeniedException("Insufficient permissions");
        }
        
        return joinPoint.proceed();
    }
}