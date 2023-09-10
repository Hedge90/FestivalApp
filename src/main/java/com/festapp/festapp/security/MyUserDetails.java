package com.festapp.festapp.security;

import com.festapp.festapp.entities.Organizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private final Long id;
    private final String email;
    private final String password;
    private final List<GrantedAuthority> grantedAuthorityList;


    public MyUserDetails(Organizer user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.grantedAuthorityList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private void addRole() {
        grantedAuthorityList.add(new SimpleGrantedAuthority("ORGANISER"));
    }
}
