package com.keycloak.saml.demo;

import org.keycloak.adapters.saml.SamlPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("admin/core")
public class UserController {

    @GetMapping
    public SamlPrincipal req(HttpServletRequest request){
        Principal userPrincipal = request.getUserPrincipal();
        SamlPrincipal samlPrincipal = (SamlPrincipal) userPrincipal;
        return samlPrincipal;
    }
}
