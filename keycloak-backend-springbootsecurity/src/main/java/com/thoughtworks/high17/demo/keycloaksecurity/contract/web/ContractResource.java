package com.thoughtworks.high17.demo.keycloaksecurity.contract.web;


import com.thoughtworks.high17.demo.keycloaksecurity.KeycloakAuthenticatoin;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/contracts")
public class ContractResource {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Authentication getContracts() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication instanceof KeycloakAuthenticationToken) {
			return new KeycloakAuthenticatoin((KeycloakAuthenticationToken) authentication);
		}
		return authentication;
	}
}
