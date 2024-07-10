package com.example.securitywithjwt.services;

import com.example.securitywithjwt.web.dtos.request.InterestsRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;

public interface IInterestService {

    BaseResponse getInterestsByUserId(Long userId);

    BaseResponse createInterest(InterestsRequest request, Long userId);
}
