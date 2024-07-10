package com.example.securitywithjwt.web.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class InterestRequest {
    private String description;

    private List<String> activities;
}
