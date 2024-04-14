package com.br.bussticket.busticket.security;

import com.auth0.jwt.JWT;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.br.bussticket.busticket.model.User;

import jakarta.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    
    private static final String ISSUER = "auth-api";
    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(User user){
        try{
            String token = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token", exception);
        }
    }

    public String validateToken(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Erro ao validar o token", exception);
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

