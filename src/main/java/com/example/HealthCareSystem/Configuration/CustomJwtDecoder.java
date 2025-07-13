package com.example.HealthCareSystem.Configuration;

import lombok.Value;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${jwt.signerKey}")
    private String signerKey;


    public CustomJwtDecoder() {
        this.
    }
}
