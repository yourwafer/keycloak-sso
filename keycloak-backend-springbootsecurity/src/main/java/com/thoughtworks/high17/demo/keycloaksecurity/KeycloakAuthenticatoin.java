package com.thoughtworks.high17.demo.keycloaksecurity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hwwei on 2017/2/24.
 */
public class KeycloakAuthenticatoin implements Authentication {

    @JsonIgnore
    private KeycloakAuthenticationToken authenticationToken;

    public KeycloakAuthenticatoin(KeycloakAuthenticationToken authenticationToken) {

        this.authenticationToken = authenticationToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authenticationToken.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        SimpleKeycloakAccount details = (SimpleKeycloakAccount) authenticationToken.getDetails();
        return details.getKeycloakSecurityContext().getToken();
    }

    @Override
    public Object getPrincipal() {
        KeycloakPrincipal<? extends KeycloakSecurityContext> principal = (KeycloakPrincipal<? extends KeycloakSecurityContext>) authenticationToken.getPrincipal();
        Map<String, String> result = new HashMap<>();
        result.put("name", principal.getName());
        RefreshableKeycloakSecurityContext keycloakSecurityContext = (RefreshableKeycloakSecurityContext) principal.getKeycloakSecurityContext();
        result.put("refreshToken", keycloakSecurityContext.getRefreshToken());
        result.put("tokenString", keycloakSecurityContext.getTokenString());
        result.put("refreshToken", keycloakSecurityContext.getRefreshToken());
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticationToken.isAuthenticated();
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticationToken.setAuthenticated(isAuthenticated);
    }

    @Override
    public String getName() {
        SimpleKeycloakAccount details = (SimpleKeycloakAccount) authenticationToken.getDetails();
        return details.getKeycloakSecurityContext().getToken().getPreferredUsername();
    }
}
