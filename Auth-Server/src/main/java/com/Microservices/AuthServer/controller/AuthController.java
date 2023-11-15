package com.Microservices.AuthServer.controller;


import com.Microservices.AuthServer.config.AuthConfig;
import com.Microservices.AuthServer.model.UserCredential;
import com.Microservices.AuthServer.repository.UserCredentialRepo;
import com.Microservices.AuthServer.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final UserCredentialRepo userCredentialRepo;


    public AuthController(AuthService authService, UserCredentialRepo userCredentialRepo, AuthenticationManager authenticationManager, AuthConfig authConfig) {
        this.authService = authService;
        this.userCredentialRepo = userCredentialRepo;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody UserCredential userCredential){
       return authService.saveUser(userCredential);

    }

    @GetMapping("/getToken")
    @ResponseStatus(HttpStatus.OK)
    public String  getToken(@RequestBody UserCredential userCredential){
     return    authService.generateToken(userCredential);

    }




}
