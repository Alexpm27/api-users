package com.example.securitywithjwt.services.Impl;

import com.example.securitywithjwt.security.JwtHelper;
import com.example.securitywithjwt.security.credentials.CredentialDetailsServiceImpls;
import com.example.securitywithjwt.services.IAuthService;
import com.example.securitywithjwt.services.IUserService;
import com.example.securitywithjwt.web.dtos.request.AuthRequest;
import com.example.securitywithjwt.web.dtos.request.SignUpRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import com.example.securitywithjwt.web.dtos.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private CredentialDetailsServiceImpls userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private IUserService userService;
    @Override
    public AuthResponse signIn(AuthRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        return toJwtResponse(token, userDetails.getUsername());
    }

    @Override
    public BaseResponse signUp(SignUpRequest request) {

        return BaseResponse.builder()
                .data(userService.create(request))
                .message("User created successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .statusCode(200)
                .build();

    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!" );
        }

    }

    private AuthResponse toJwtResponse(String token, String username){
        AuthResponse response = AuthResponse.builder()
                .jwtToken(token)
                .username(username).build();
        return response;
    }
}


