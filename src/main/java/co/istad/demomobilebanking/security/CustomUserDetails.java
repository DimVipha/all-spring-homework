package co.istad.demomobilebanking.security;

import co.istad.demomobilebanking.domain.User;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
            role.getAuthorities().forEach(authority -> {
                authorities.add((new SimpleGrantedAuthority(authority.getName())));
            });
        });
        return authorities;
        //return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return !user.getIsDeleted();
    }
}
