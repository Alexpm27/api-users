package com.example.securitywithjwt.web.dtos.response;

import com.example.securitywithjwt.persistence.models.Parameter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class StatisticResponse {

    private Long id;

    private Float humidityAbove;

    private Float humidityBelow;

    private Float temperature;

    private Float lux;

    private String status;

    private Float median;

    private String date;
}
