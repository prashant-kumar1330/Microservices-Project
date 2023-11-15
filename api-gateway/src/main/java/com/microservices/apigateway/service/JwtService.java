package com.microservices.apigateway.service;

import com.microservices.apigateway.repository.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@Slf4j
public class JwtService {
    @Autowired
    private static UserRepo userRepo;

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public static Boolean validateToken(String token){
        try{
            Claims  claims = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            if(userRepo.findByname(username).isEmpty() || claims.getExpiration().before(new Date())){
                return false; //user not valid
            }
            return true;
        }
        catch (Exception e){
            log.info("invalid token");
            return false;
        }

    }

    private static Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(key);
    }

}
