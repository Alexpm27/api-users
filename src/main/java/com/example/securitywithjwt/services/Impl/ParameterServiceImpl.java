package com.example.securitywithjwt.services.Impl;

import com.example.securitywithjwt.persistence.models.Parameter;
import com.example.securitywithjwt.persistence.models.Statistic;
import com.example.securitywithjwt.persistence.models.projections.StatisticsProjection;
import com.example.securitywithjwt.persistence.repositories.IParameterRepository;
import com.example.securitywithjwt.services.IParameterService;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import com.example.securitywithjwt.web.dtos.response.ParameterResponse;
import com.example.securitywithjwt.web.dtos.response.StatisticResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ParameterServiceImpl implements IParameterService {

    @Autowired
    private IParameterRepository repository;
    @Override
    public BaseResponse getHistoryByUserId(Long id) {
        return BaseResponse.builder()
                .data(repository.findAllByUserId(id).stream().map(this::toResponse).toList())
                .message("get History successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse getStatisticsByUserId(Long id) {
        return BaseResponse.builder()
                .data(repository.findAllStatisticsByUserId(id).stream().map(this::toResponse).toList())
                .message("get History successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private ParameterResponse toResponse(Parameter parameter){
        return ParameterResponse.builder()
                .id(parameter.getId())
                .humidityAbove(parameter.getHumidityAbove())
                .humidityBelow(parameter.getHumidityBelow())
                .temperature(parameter.getTemperature())
                .lux(parameter.getLux())
                .date(parameter.getDate())
                .status(parameter.getStatus())
                .build();
    }

    private StatisticResponse toResponse(StatisticsProjection statistic){
        return StatisticResponse.builder()
                .id(statistic.getId())
                .median(statistic.getMedian())
                .date(statistic.getDate())
                .build();
    }
}
