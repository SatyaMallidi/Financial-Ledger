package com.example.dataworks.financialledger.controller;
import com.example.dataworks.financialledger.entity.AuthenticationResponse;
import com.example.dataworks.financialledger.entity.User;
import com.example.dataworks.financialledger.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody User request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody User request) {
        return authenticationService.authenticate(request);
    }

    // @PostMapping("/refresh_token")
    // public ResponseEntity refreshToken(
    //         HttpRequest request,
    //         HttpServletResponse response
    // ) {
    //     return authenticationService.refreshToken(request, response);
    // }
}
