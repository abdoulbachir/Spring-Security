package com.eazybytes.filter;

import com.eazybytes.constants.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder()
                    .issuer("Easy Bank") // Person or organization issuing the token
                    .subject("JWT Token") //
                    .claim("username",authentication.getName()) // .claim() is used populate the username
                    .claim("authorities",populateAuthorities(authentication.getAuthorities())) // .claim() is used populate the authorities
                    .issuedAt(new Date()) // Date when JWT Token was issued
                    .expiration(new Date((new Date()).getTime() + 300000000)) // Date when JWT Token will expire ~ here around 8hrs.
                    .signWith(key).compact(); // Digitally sign the JWT Token using our secret key

            response.setHeader(SecurityConstants.JWT_HEADER, jwt); // Send the JWT Token we created above inside the response by setting it in the header called "Authorization".
                                                                    // Header name needs to match what we have in our security config class.
        }
        filterChain.doFilter(request, response);
    }

    /**
     * This filter should only execute during the login stage when the path is "/user".
     * DO NOT execute any other path
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/user");
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}
