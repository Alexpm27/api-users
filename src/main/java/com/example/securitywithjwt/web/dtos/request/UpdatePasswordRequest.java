package com.example.securitywithjwt.web.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {
    @NotNull
    private Long id;

    @NotNull
    private String oldPassword;

    @NotNull
    @Min(value = 8, message = "Password should not be less than 8 characters")
    private String newPassword;

}
