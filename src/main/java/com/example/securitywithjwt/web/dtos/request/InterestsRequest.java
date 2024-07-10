package com.example.securitywithjwt.web.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class InterestsRequest {
    private List<InterestRequest> interests;
}
