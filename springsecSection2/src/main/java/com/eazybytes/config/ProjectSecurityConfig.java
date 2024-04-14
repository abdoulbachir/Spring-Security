package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

       // Configuration to deny ask the requests

/*        http.authorizeHttpRequests((requests) -> requests
                .anyRequest().denyAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();*/


        // Configuration to deny ask the requests
/*        http.authorizeHttpRequests((requests) -> requests
                .anyRequest().permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();*/

         // Custom Security Configuration
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
                .requestMatchers("/contact","/notices").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
}
