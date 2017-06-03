package com.thoughtworks.high17.demo.keycloaksecurity;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomFacade extends SimpleHttpFacade {

    public CustomFacade(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public KeycloakSecurityContext getSecurityContext() {
        Object details = getAuthentication(SecurityContextHolder.getContext());
        if (details != null) {
            if(details instanceof KeycloakAuthenticationToken){
                KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) details;
                KeycloakPrincipal principal = (KeycloakPrincipal)token.getPrincipal();
                return principal.getKeycloakSecurityContext();
            } else if(details instanceof KeycloakSecurityContext){
                return (KeycloakSecurityContext) details;
            } else if (details instanceof OidcKeycloakAccount) {
                return ((OidcKeycloakAccount) details).getKeycloakSecurityContext();
            }
        }
        return null;
    }

    Object getAuthentication(SecurityContext context) {
        return context.getAuthentication();
    }
}
