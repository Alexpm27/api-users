package com.example.securitywithjwt.web.controllers;

import com.example.securitywithjwt.services.IUserService;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
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

    @GetMapping("/interest/{userId}")
    public ResponseEntity<BaseResponse> getUserWithInterest(@PathVariable Long userId){
        return new ResponseEntity<>(service.getUserWithInterests(userId), HttpStatus.OK);
    }

    @GetMapping("/users/{city}")
    public ResponseEntity<BaseResponse> getAllByCity(@PathVariable String city){
        return new ResponseEntity<>(service.getAllByCity(city), HttpStatus.OK);
    }
}
