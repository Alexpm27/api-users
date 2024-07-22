package com.example.securitywithjwt.web.controllers;

import com.example.securitywithjwt.services.IAuthService;
import com.example.securitywithjwt.web.dtos.request.SignUpRequest;
import com.example.securitywithjwt.web.dtos.request.AuthRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import com.example.securitywithjwt.web.dtos.response.AuthResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class AuthController {

    @Autowired
    private IAuthService service;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/signIn")
        public ResponseEntity<AuthResponse> signIn(@RequestBody AuthRequest request) {

        return new ResponseEntity<>(service.signIn(request), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<BaseResponse> signUp(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "frontPage", required = false) MultipartFile frontPage,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone,
            @RequestParam("age") String age,
            @RequestParam("gender") String gender,
            @RequestParam("city") String city,
            @RequestParam(value = "imageUrl", required = false) String imageUrl,
            @RequestParam(value = "frontPageUrl", required = false) String frontPageUrl) {

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .name(name)
                .email(email)
                .password(password)
                .phone(Long.parseLong(phone))
                .age(Integer.parseInt(age))
                .gender(gender)
                .city(city)
                .imageUrl(imageUrl)
                .frontPageUrl(frontPageUrl)
                .build();

        BaseResponse baseResponse = service.signUp(signUpRequest, file, frontPage);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!" ;
    }

}
