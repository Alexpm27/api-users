package com.example.securitywithjwt.web.controllers;

import com.example.securitywithjwt.services.IInterestService;
import com.example.securitywithjwt.web.dtos.request.InterestsRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("interest")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class InterestController {
    @Autowired
    private IInterestService service;

    @PostMapping("interests/{userId}")
    public ResponseEntity<BaseResponse> create(@RequestBody InterestsRequest request, @PathVariable Long userId){
        return new ResponseEntity<>(service.createInterest(request, userId), HttpStatus.OK);
    }
}
