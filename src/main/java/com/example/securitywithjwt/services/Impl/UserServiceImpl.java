package com.example.securitywithjwt.services.Impl;

import com.example.securitywithjwt.persistence.exceptions.NotFoundException;
import com.example.securitywithjwt.persistence.models.User;
import com.example.securitywithjwt.persistence.repositories.IUserRepository;
import com.example.securitywithjwt.services.IUserService;
import com.example.securitywithjwt.web.dtos.request.SignUpRequest;
import com.example.securitywithjwt.web.dtos.request.UpdatePasswordRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import com.example.securitywithjwt.web.dtos.response.CreateUserResponse;
import com.example.securitywithjwt.web.dtos.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public CreateUserResponse create(SignUpRequest request) {
        return from(repository.save(from(request)));
    }

    @Override
    public User get(String email) {
        return findUserByEmailAndEnsureExists(email);
    }

    @Override
    public BaseResponse getUserByEmail(String email) {
        return BaseResponse.builder()
                .data(toUserResponse(findUserByEmailAndEnsureExists(email)))
                .message("User get successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse updatePassword(UpdatePasswordRequest request) {
        return null;
    }

    private User from(SignUpRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    private CreateUserResponse from(User user){
        CreateUserResponse response = new CreateUserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    private UserResponse toUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    public User findAndEnsureExists(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    private User findUserByEmailAndEnsureExists(String email) {
        return repository.findByEmail(email).orElseThrow(NotFoundException::new);
    }

}
