package com.example.securitywithjwt.web.dtos.response;

import com.example.securitywithjwt.persistence.models.Parameter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class StatisticResponse {

    private Long id;

    private Float median;

    private String date;
}
