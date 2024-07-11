package com.example.securitywithjwt.web.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(min = 8, message = "Password should have at least 8 characters")
    private String password;

    @NotNull
    private int age;

    @NotNull
    private Long phone;

    @NotNull
    private String city;

    @NotNull
    private String gender;

}
