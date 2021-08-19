package com.page_assessment_wallet_system.authentication.service;

import com.page_assessment_wallet_system.authentication.dto.AuthenticationRequest;

public interface AuthenticationService {
    String getToken(AuthenticationRequest authenticationRequest);
}
