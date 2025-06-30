package fr.limayrac.pfeback.security;

import fr.limayrac.pfeback.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private User user;
    public CustomUserDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(user.getRole());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getActif();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getActif();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getActif();
    }

    @Override
    public boolean isEnabled() {
        return user.getActif();
    }

    public User getUser() {
        return user;
    }
}
