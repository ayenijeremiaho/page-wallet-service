package com.page_assessment_wallet_system.controller;

import com.page_assessment_wallet_system.authentication.dto.AuthenticationRequest;
import com.page_assessment_wallet_system.authentication.dto.AuthenticationResponse;
import com.page_assessment_wallet_system.authentication.service.AuthenticationService;
import com.page_assessment_wallet_system.controller.dto.Response;
import com.page_assessment_wallet_system.util.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping(value = "api/")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("hello")
    private String getString(){
        return "Hello, Welcome!!!";
    }

    @PostMapping("authenticate")
    private ResponseEntity<?> getToken(@RequestBody AuthenticationRequest authenticationRequest) {
        String token = authenticationService.getToken(authenticationRequest);
        Response response = GeneralService.prepareResponse(new AuthenticationResponse(token));
        return ResponseEntity.ok(response);
    }
}
