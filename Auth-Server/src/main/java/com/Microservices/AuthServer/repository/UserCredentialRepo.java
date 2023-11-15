package com.Microservices.AuthServer.repository;

import com.Microservices.AuthServer.model.UserCredential;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepo extends JpaRepository<UserCredential,Integer> {

    Optional<UserCredential> findByemail(String email);

}
