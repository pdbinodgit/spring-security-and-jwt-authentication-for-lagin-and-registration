package com.internshipSathi.internshipsathiLogin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    UserServiceDetailImpl userServiceDetail;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

       try {


    String authorization = httpServletRequest.getHeader("Authorization");

    String token = null;
    String username = null;

    if (authorization != null && authorization.startsWith("Bearer ")) {

        token = authorization.substring(7);
        username = jwtProvider.getEmailJwtToken(token);
        if (token != null && jwtProvider.validateJwtToken(token)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authentication
                    = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()

            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}catch (Exception e){
    System.out.println("error");
}

   filterChain.doFilter(httpServletRequest,httpServletResponse);

    }
}
