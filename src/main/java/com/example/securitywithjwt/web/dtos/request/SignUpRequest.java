package com.example.securitywithjwt.web.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpRequest {

    @NotNull
    private String name;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    private String password;

}
