package com.Microservices.AuthServer.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

     public String generateToken(String username){
         Map<String , Object> claims = new HashMap<>(); // each token has 3 component first is the algorithms whcih is used , user credential and the sercret key and each of therese are known as claims
         
         return createToken(claims,username);
     }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String, Object> claims, String username) {

         return Jwts.builder()
                 .setClaims(claims)
                 .setSubject(username)
                 .setIssuedAt(new Date(System.currentTimeMillis()))
                 .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                 .signWith(getSignKey(), SignatureAlgorithm.HS256) // here we are giving the secret key and the algorithms we want to use
                 .compact();
    }

    private Key getSignKey() {
         byte[] key = Decoders.BASE64.decode(SECRET);
         return Keys.hmacShaKeyFor(key);
    }


}
