package com.eazybytes.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CsrfCookieFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName()); // Read the csrf token available inside `HttpServletRequest`.
        if(csrfToken.getHeaderName() != null){
            response.setHeader(csrfToken.getHeaderName(), csrfToken.getToken()); // Populating the same header name and its token value inside the response header
        }
        filterChain.doFilter(request, response); // Same response is handed over to the next filter inside the filter chain. This way eventually, my csrf token value will be present inside the header.
    }
}
