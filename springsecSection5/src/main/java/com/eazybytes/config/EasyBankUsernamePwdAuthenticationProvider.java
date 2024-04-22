package com.eazybytes.config;

import com.eazybytes.model.Customer;
import com.eazybytes.repository.CustomerRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EasyBankUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    CustomerRepository customerRepository;

    PasswordEncoder passwordEncoder;

    public EasyBankUsernamePwdAuthenticationProvider(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName(); // Get username from the Authentication object
        String password = authentication.getCredentials().toString();  // Get password from the Authentication object
        List<Customer> customers = customerRepository.findByEmail(username);  // Get customer from database

        if (!customers.isEmpty()) { // If customer exists ....
            if(passwordEncoder.matches(password, customers.get(0).getPwd())){ // If the password provided matches password from database ....
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities); // create new authentication token based on credentials
            }else {
                throw new BadCredentialsException("Invalid password");
            }
        }else {
            throw new BadCredentialsException("User not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
