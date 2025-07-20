package com.example.HealthCareSystem.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.signerKey}")
    protected String signerKey;


    public CustomJwtDecoder() {

    }

    @Override
    public Jwt decode(String token) throws JwtException {
        return null;
    }
}
