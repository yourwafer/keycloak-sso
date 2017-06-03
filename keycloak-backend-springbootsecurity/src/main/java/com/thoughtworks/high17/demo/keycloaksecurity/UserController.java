package com.thoughtworks.high17.demo.keycloaksecurity;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hwwei on 2017/2/23.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping
    public Authentication getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof KeycloakAuthenticationToken) {
            return new KeycloakAuthenticatoin((KeycloakAuthenticationToken) authentication);
        }
        return authentication;
    }
}
