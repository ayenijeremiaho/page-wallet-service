package com.page_assessment_wallet_system.authentication.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private final String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

}
