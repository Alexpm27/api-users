package com.example.securitywithjwt.web.dtos.response;

import lombok.*;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthResponse {

    private String jwtToken;

    private String username;
}
