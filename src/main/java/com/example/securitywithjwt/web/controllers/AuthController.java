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

    @PostMapping("signUp")
    public ResponseEntity<BaseResponse> signUp(@Valid @RequestBody SignUpRequest request){
        BaseResponse baseResponse = service.signUp(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!" ;
    }

}
