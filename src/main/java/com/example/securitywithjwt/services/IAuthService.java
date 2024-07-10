package com.example.securitywithjwt.services;

import com.example.securitywithjwt.web.dtos.request.SignUpRequest;
import com.example.securitywithjwt.web.dtos.request.AuthRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import com.example.securitywithjwt.web.dtos.response.AuthResponse;

public interface IAuthService {
    AuthResponse signIn(AuthRequest request);

    BaseResponse signUp(SignUpRequest request);
}
