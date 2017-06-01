package com.keycloak.saml.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("logout")
public class LogoutController {

    @RequestMapping(method = RequestMethod.POST)
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/session/main.html";
    }
}
