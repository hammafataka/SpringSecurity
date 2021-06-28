package com.example.demo.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class jwtSecretKey {
    private final jwtConfig jwtConfig;

    @Autowired
    public jwtSecretKey(jwtConfig jwtconfig) {
        this.jwtConfig = jwtconfig;
    }

    @Bean
    public SecretKey SecretKey(){
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }
}
