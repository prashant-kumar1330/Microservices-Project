package com.microservices.apigateway.repository;

import com.microservices.apigateway.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByname(String name);
}
