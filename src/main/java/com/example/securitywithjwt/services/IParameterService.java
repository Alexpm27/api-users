package com.example.securitywithjwt.services;

import com.example.securitywithjwt.web.dtos.response.BaseResponse;

public interface IParameterService {

    BaseResponse getHistoryByUserId(Long id);

    BaseResponse getStatisticsByUserId(Long id);

}
