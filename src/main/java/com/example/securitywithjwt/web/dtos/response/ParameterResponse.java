package com.example.securitywithjwt.web.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ParameterResponse {

    private Long id;

    private Float humidityAbove;

    private Float humidityBelow;

    private Float temperature;

    private Float lux;

    private String date;

    private String status;

}
