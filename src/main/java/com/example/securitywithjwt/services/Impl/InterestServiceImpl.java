package com.example.securitywithjwt.services.Impl;

import com.example.securitywithjwt.mapper.InterestDtoMapper;
import com.example.securitywithjwt.persistence.exceptions.NotFoundException;
import com.example.securitywithjwt.persistence.models.Interest;
import com.example.securitywithjwt.persistence.repositories.IInterestRepository;
import com.example.securitywithjwt.services.IInterestService;
import com.example.securitywithjwt.services.IUserService;
import com.example.securitywithjwt.web.dtos.request.InterestRequest;
import com.example.securitywithjwt.web.dtos.request.InterestsRequest;
import com.example.securitywithjwt.web.dtos.response.BaseResponse;
import com.example.securitywithjwt.web.dtos.response.InterestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterestServiceImpl implements IInterestService {
    @Autowired
    private IInterestRepository repository;
    @Autowired
    private InterestDtoMapper dtoMapper;
    @Autowired
    private IUserService userService;
    @Override
    public BaseResponse getInterestsByUserId(Long userId) {
        return null;
    }

    @Override
    public BaseResponse createInterest(InterestsRequest request, Long userId) {
        List<Interest> interests = request.getInterests()
                .stream()
                .map(x -> from(x, userId))
                .collect(Collectors.toList());
        List<Interest> interestsSaved = repository.saveAll(interests);

        List<InterestResponse> interestResponses = interestsSaved.stream()
                .map(dtoMapper::toDto)
                .collect(Collectors.toList());

        return BaseResponse.builder()
                .message("Interest Created Correctly")
                .success(Boolean.TRUE)
                .data(interestResponses)
                .statusCode(200)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private InterestResponse toDto(Interest interest){
        return InterestResponse.builder()
                .id(interest.getId())
                .description(interest.getDescription())
                .activities(interest.getActivities())
                .build();
    }

    private Interest from(InterestRequest request, Long userId){
        Interest interest = new Interest();
        interest.setDescription(request.getDescription());
        interest.setActivities(request.getActivities());
        interest.setUser(userService.findAndEnsureExists(userId));
        return interest;
    }

    private Interest findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }
}
