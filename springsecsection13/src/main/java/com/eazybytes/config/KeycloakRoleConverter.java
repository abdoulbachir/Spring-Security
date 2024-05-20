package com.eazybytes.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        // Get the "real_access" claim from the JWT
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        // Check if "real_access" claim is missing or empty
        if(realmAccess == null || realmAccess.isEmpty()){
            return new ArrayList<>(); // No roles found, return an empty collection
        }

        // Get the "roles" list from the "real_access" claim
        List<String> roleNames = (List<String>) realmAccess.get("roles");

        // Convert role names to GrantedAuthority objects with "ROLE_" prefix
        Collection<GrantedAuthority> returnValue = roleNames
                .stream().map(roleName -> "ROLE_" + roleName) // Prepend "ROLE_" to each role
                .map(SimpleGrantedAuthority::new)  // Create SimpleGrantedAuthority objects
                .collect(Collectors.toList()); // Collect the converted roles into a list

        // Return the collection of GrantedAuthority objects representing the user's roles
        return returnValue;
    }
}
