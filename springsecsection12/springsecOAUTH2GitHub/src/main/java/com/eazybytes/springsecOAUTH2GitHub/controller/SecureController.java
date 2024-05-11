package com.eazybytes.springsecOAUTH2GitHub.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {

/**
   This method takes an OAuth2AuthenticationToken object named token as a parameter.
  This token represents the authentication details of the current user obtained through OAuth2.
 */
    @GetMapping("/")
    public String main(OAuth2AuthenticationToken token) {
            System.out.println(token.getPrincipal()); // Print details about the authenticated user (for debugging purposes)
            return "secure.html"; // Return the name of the view to render after successful authentication
    }
}

