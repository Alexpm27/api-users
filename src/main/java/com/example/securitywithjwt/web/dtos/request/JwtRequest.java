package com.example.securitywithjwt.web.dtos.request;

import jakarta.validation.constraints.Email;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {

    @Email
    private String email;

    private String password;

}
