package com.example.securitywithjwt.web.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
public class UsersInterestsResponse {
    private Long id;

    private String name;

    private int age;

    private String gender;

    private  String city;

    private List<InterestResponse> interests;
}
