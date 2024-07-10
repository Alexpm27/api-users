package com.example.securitywithjwt.services;

import com.example.securitywithjwt.persistence.models.User;
import com.example.securitywithjwt.web.dtos.request.SignUpRequest;
import com.example.securitywithjwt.web.dtos.request.UpdatePasswordRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import com.example.securitywithjwt.web.dtos.response.CreateUserResponse;

public interface IUserService {

    CreateUserResponse create(SignUpRequest request);

    User get(String email);

    BaseResponse getUserByEmail(String email);

    BaseResponse updatePassword(UpdatePasswordRequest request);

    BaseResponse getUserWithInterests(Long userId);

    BaseResponse getAllByCity(String city);

    User findAndEnsureExists(Long id);
}
