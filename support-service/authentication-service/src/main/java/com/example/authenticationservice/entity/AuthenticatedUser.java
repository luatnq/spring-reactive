package com.example.authenticationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUser implements Authentication {

	private static final long serialVersionUID = 6861381095901879822L;
	private String username;
    private boolean authenticated = true;
    private List<SimpleGrantedAuthority> authorities;

    public AuthenticatedUser(String uid, List<SimpleGrantedAuthority> authorities){
        this.username = uid;
        this.authorities = authorities;
    }
    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Object getCredentials() {
        return this.username;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return this.username;
    }
}