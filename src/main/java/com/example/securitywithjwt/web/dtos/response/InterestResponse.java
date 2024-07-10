package com.example.securitywithjwt.web.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder @Getter @Setter
public class InterestResponse {
    private Long id;

    private String description;

    private List<String> activities;
}
