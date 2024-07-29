package com.example.securitywithjwt.services.Impl;

import com.example.securitywithjwt.mapper.InterestDtoMapper;
import com.example.securitywithjwt.persistence.exceptions.NotFoundException;
import com.example.securitywithjwt.persistence.models.User;
import com.example.securitywithjwt.persistence.repositories.IUserRepository;
import com.example.securitywithjwt.services.IUserService;
import com.example.securitywithjwt.services.aws.S3Service;
import com.example.securitywithjwt.web.dtos.request.SignUpRequest;
import com.example.securitywithjwt.web.dtos.request.UpdatePasswordRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import com.example.securitywithjwt.web.dtos.response.CreateUserResponse;
import com.example.securitywithjwt.web.dtos.response.UserResponse;
import com.example.securitywithjwt.web.dtos.response.UsersInterestsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    InterestDtoMapper interestDtoMapper;
    @Autowired
    private S3Service s3Service;

    @Override
    public CreateUserResponse create(SignUpRequest request, MultipartFile file, MultipartFile frontPage) {
        User user = from(request);
        if (request.getImageUrl() == null){

            String imageUrl = s3Service.uploadFile(file);
            String frontPageUrl = s3Service.uploadFile(frontPage);
            user.setImageUrl(imageUrl);
            user.setFrontPageUrl(frontPageUrl);

        }else {
            user.setImageUrl(request.getImageUrl());
            user.setFrontPageUrl(request.getFrontPageUrl());
        }
        return from(repository.save(user));
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
    public BaseResponse getById(Long id) {
        return BaseResponse.builder()
                .data(toUserResponse(findAndEnsureExists(id)))
                .message("User get successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse updatePassword(UpdatePasswordRequest request) {
        return null;
    }

    @Override
    public BaseResponse getUserWithInterests(Long userId) {
        User user = findAndEnsureExists(userId);
        return BaseResponse.builder()
                .data(toDto(user))
                .message("Get user successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .statusCode(200)
                .build();
    }

    @Override
    public BaseResponse getAllByCity(String city) {
        List<User> users = repository.findAllByCity(city);
        List<UsersInterestsResponse> usersResponses = users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(usersResponses)
                .message("Get all users from " + city + " successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .statusCode(200)
                .build();
    }

    private UsersInterestsResponse toDto(User user){
        return UsersInterestsResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .age(user.getAge())
                .description(user.getDescription())
                .gender(user.getGender())
                .city(user.getCity())
                .profileUrl(user.getImageUrl())
                .frontPageUrl(user.getFrontPageUrl())
                .interests(interestDtoMapper.toDtoList(user.getInterests()))
                .build();
    }

    private User from(SignUpRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setDescription(request.getDescription());
        user.setAge(request.getAge());
        user.setCity(request.getCity());
        user.setGender(request.getGender());
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
