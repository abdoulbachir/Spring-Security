package com.eazybytes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
         // Custom Security Configuration
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
                .requestMatchers("/contact","/notices").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

/*    Approach 1 where we use withDefaultPasswordEncoder method
    while creating the user details*/
/*    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("1234")
                .roles("admin")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("1234")
                .roles("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user); // We can add as many user as we want in this constructor
    }*/

    /*    Approach 2 where we use NoOpPasswordEncoder Bean
    while creating the user details*/
/*    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails admin = User.withUsername("admin")
                .password("1234")
                .roles("admin")
                .build();

        UserDetails user = User.withUsername("user")
                .password("1234")
                .roles("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user); // We can add as many user as we want in this constructor
    }*/

    @Bean
    public UserDetailsService userDetailsService (DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();  // This is not recommend as it doesn't encrypt or hash our password
    }


}
