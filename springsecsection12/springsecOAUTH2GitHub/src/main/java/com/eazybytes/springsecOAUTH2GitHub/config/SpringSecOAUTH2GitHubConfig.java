package com.eazybytes.springsecOAUTH2GitHub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecOAUTH2GitHubConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated()) // This line defines that all requests to the application need to be authenticated
                .oauth2Login(Customizer.withDefaults()); // This line enables OAuth2 login with default configuration
        return http.build();
    }
//
//    @Bean
//    public ClientRegistrationRepository clientRepository() {
//        ClientRegistration clientReg = clientRegistration(); // This line calls the helper method to create a ClientRegistration object
//        return new InMemoryClientRegistrationRepository(clientReg); // This line creates a repository containing the client registration for GitHub
//    }
//
//    // This section configures the ClientRegistration for GitHub with details like client ID and secret
//    private ClientRegistration clientRegistration() {
//        return CommonOAuth2Provider.GITHUB.getBuilder("github")
//                .clientId("Ov23liToK650X2ylf7x4")
//                .clientSecret("9ee24606c11ca8028bffc76e562c2f380a85f68e")
//                .build();
//    }
}

