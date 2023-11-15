package com.Microservices.AuthServer.services;

import com.Microservices.AuthServer.config.AuthConfig;
import com.Microservices.AuthServer.model.UserCredential;
import com.Microservices.AuthServer.repository.UserCredentialRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserCredentialRepo userCredentialRepo;
    private final AuthConfig authConfig;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public String saveUser(UserCredential credential){
        if(userCredentialRepo.findByemail(credential.getEmail()).isEmpty()){
            credential.setPassword(authConfig.passwordEncoder().encode(credential.getPassword()));
            userCredentialRepo.save(credential);
            return "user created";
        }
        return "user already exist";


    }

    public String generateToken(UserCredential credential){


        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(credential.getEmail(),credential.getPassword());
        try{
            log.info(String.valueOf(authenticationManager.authenticate(authenticationToken).isAuthenticated()));
            if(authenticationManager.authenticate(authenticationToken).isAuthenticated()){
                return jwtService.generateToken(credential.getName());
            }

        }
        catch (BadCredentialsException e){
            log.info("bad credentials "+e);

        }


        return "bad credentials";

    }






}
