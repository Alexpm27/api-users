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

    private Long phone;

    private int age;

    private String gender;

    private  String city;

    private String description;

    private String profileUrl;

    private String frontPageUrl;

    private List<InterestResponse> interests;
}
