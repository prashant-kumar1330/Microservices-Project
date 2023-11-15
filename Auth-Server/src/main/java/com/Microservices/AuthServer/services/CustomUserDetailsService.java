package com.Microservices.AuthServer.services;

import com.Microservices.AuthServer.config.UserInfoDetails;
import com.Microservices.AuthServer.model.UserCredential;
import com.Microservices.AuthServer.repository.UserCredentialRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserCredentialRepo userCredentialRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<UserCredential> userCredential = userCredentialRepo.findByemail(username);

        if(userCredential.isEmpty()){
            throw new UsernameNotFoundException("user not found with username "+ username);
        }

        return new UserInfoDetails(userCredential.get());

    }
}

