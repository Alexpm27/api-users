package com.example.securitywithjwt.mapper;

import com.example.securitywithjwt.persistence.models.Interest;
import com.example.securitywithjwt.web.dtos.response.InterestResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InterestDtoMapper {
    public InterestResponse toDto(Interest interest){
        return InterestResponse.builder()
                .id(interest.getId())
                .description(interest.getDescription())
                .activities(interest.getActivities())
                .build();
    }

    public List<InterestResponse> toDtoList(List<Interest> interests) {
        return interests.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
