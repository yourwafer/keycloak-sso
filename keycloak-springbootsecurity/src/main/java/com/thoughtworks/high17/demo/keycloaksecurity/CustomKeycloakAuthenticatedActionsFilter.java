package com.thoughtworks.high17.demo.keycloaksecurity;

import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.AuthenticatedActionsHandler;
import org.keycloak.adapters.OIDCHttpFacade;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticatedActionsFilter;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomKeycloakAuthenticatedActionsFilter extends KeycloakAuthenticatedActionsFilter {
    private final Logger log = LoggerFactory.getLogger(CustomKeycloakAuthenticatedActionsFilter.class);
    protected ApplicationContext context;
    protected AdapterDeploymentContext deploymentContext;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof KeycloakAuthenticationToken) {
            HttpFacade facade = new CustomFacade((HttpServletRequest) request, (HttpServletResponse) response);
            AuthenticatedActionsHandler handler = new AuthenticatedActionsHandler(deploymentContext.resolveDeployment(facade), (OIDCHttpFacade) facade);
            boolean handled = handler.handledRequest();
            if (handled) {
                log.debug("Authenticated filter handled request: {}", ((HttpServletRequest) request).getRequestURI());
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    protected void initFilterBean() throws ServletException {
        deploymentContext = context.getBean(AdapterDeploymentContext.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
