package com.example.securitywithjwt.web.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class SignUpRequest {

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password should have at least 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password must contain at least one digit, one lowercase letter, and one uppercase letter")
    private String password;

    @Min(value = 0, message = "Age must be greater than or equal to 0")
    @Max(value = 120, message = "Age must be less than or equal to 120")
    private int age;

    @NotNull(message = "Phone cannot be null")
    //@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private Long phone;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    private String city;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "M|F|O", message = "Gender must be 'M', 'F', or 'O'")
    private String gender;

    private String imageUrl;

    private String frontPageUrl;
}
