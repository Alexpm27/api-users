package com.example.securitywithjwt.services;

import com.example.securitywithjwt.web.dtos.request.SignUpRequest;
import com.example.securitywithjwt.web.dtos.request.JwtRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import com.example.securitywithjwt.web.dtos.response.JwtResponse;

public interface IAuthService {
    JwtResponse signIn(JwtRequest request);

    BaseResponse signUp(SignUpRequest request);
}
