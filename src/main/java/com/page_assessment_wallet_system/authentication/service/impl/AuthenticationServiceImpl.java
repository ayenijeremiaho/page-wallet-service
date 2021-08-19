package com.page_assessment_wallet_system.authentication.service.impl;

import com.page_assessment_wallet_system.authentication.dto.AuthenticationRequest;
import com.page_assessment_wallet_system.authentication.service.AuthenticationService;
import com.page_assessment_wallet_system.authentication.service.ThisUserDetailsService;
import com.page_assessment_wallet_system.exception.WalletException;
import com.page_assessment_wallet_system.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JWTUtil jwtUtil;
    private final ThisUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(JWTUtil jwtUtil, ThisUserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String getToken(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new WalletException("Incorrect username and password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        return jwtUtil.generateToken(userDetails);
    }
}
