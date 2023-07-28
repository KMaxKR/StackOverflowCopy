package ks.msx.SpringSecurity.security;

import ks.msx.SpringSecurity.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor

public class SecurityUser implements UserDetails {
    private final User user;

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities()
                .stream()
                .map(SecurityAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccount_non_locked();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccount_non_locked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isAccount_non_locked();
    }

    @Override
    public boolean isEnabled() {
        return user.isAccount_non_locked();
    }
}
