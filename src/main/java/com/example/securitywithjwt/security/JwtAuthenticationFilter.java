package com.example.securitywithjwt.security;

import com.example.securitywithjwt.security.credentials.CredentialDetailsServiceImpls;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);
    @Autowired
    private JwtHelper jwtHelper;


    @Autowired
    private CredentialDetailsServiceImpls userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // Authorization
            String requestHeader = request.getHeader("Authorization");
            // Bearer 2352345235sdfrsfgsdfsdf
            logger.info("Header: {}", requestHeader);
            String username = null;
            String token = null;

            if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
                // looking good
                token = requestHeader.substring(7);
                try {
                    username = this.jwtHelper.getUsernameFromToken(token);
                } catch (IllegalArgumentException e) {
                    logger.info("Illegal Argument while fetching the username !!");
                    throw new ServletException("Illegal Argument while fetching the username", e);
                } catch (ExpiredJwtException e) {
                    logger.info("Given jwt token is expired !!");
                    throw new ServletException("Given jwt token is expired", e);
                } catch (MalformedJwtException e) {
                    logger.info("Some change has been made in token !! Invalid Token");
                    throw new ServletException("Invalid Token", e);
                } catch (Exception e) {
                    throw new ServletException("Error occurred while processing the token", e);
                }
            } else {
                logger.info("Invalid Header Value !!");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // fetch user detail from username
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);
                if (validateToken) {
                    // set the authentication
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    logger.info("Validation fails !!");
                }
            }

            filterChain.doFilter(request, response);

        } catch (AuthenticationException ex) {
            // delegate to authentication entry point for authentication exceptions
            SecurityContextHolder.clearContext();
            this.authenticationEntryPoint.commence(request, response, ex);
        } catch (Exception ex) {
            // rethrow other exceptions to be handled by the global exception handler
            throw new ServletException(ex);
        }
    }
}
