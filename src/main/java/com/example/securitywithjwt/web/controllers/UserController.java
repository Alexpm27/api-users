package com.example.securitywithjwt.web.controllers;

import com.example.securitywithjwt.services.IUserService;
import com.example.securitywithjwt.web.dtos.request.SignUpRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("{email}")
    public ResponseEntity<BaseResponse> get(@PathVariable String email){
        return new ResponseEntity<>(service.getUserByEmail(email), HttpStatus.OK);
    }

}
