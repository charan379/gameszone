package com.ctytech.gameszone.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ctytech.gameszone.constants.UserStatus;
import com.ctytech.gameszone.dto.RoleDTO;
import com.ctytech.gameszone.dto.UserDTO;

public class CustomUserDetails implements UserDetails {

    private UserDTO user;

    public CustomUserDetails(UserDTO user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> userAuthorities = new ArrayList<>();

        Set<RoleDTO> roles = user.getRoles();

        if (roles != null)
            for (RoleDTO role : roles) {
                userAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role.getRoleName())));
            }
        System.out.println(userAuthorities);
        return userAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getStatus().equals(UserStatus.SUSPENDED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(UserStatus.ACTIVE);
    }

}
