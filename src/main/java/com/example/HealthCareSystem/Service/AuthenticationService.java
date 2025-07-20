package com.example.HealthCareSystem.Service;

import com.example.HealthCareSystem.Dto.Request.IntrospectReq;
import com.example.HealthCareSystem.Dto.Response.IntrospectRes;
import com.example.HealthCareSystem.Entity.Role;
import com.example.HealthCareSystem.Entity.User;
import com.example.HealthCareSystem.Exception.AppException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.validDuration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshableDuration}")
    protected long REFRESHABLE_DURATION;

    public IntrospectRes introspect(IntrospectReq request){
        if(request == null || request.getToken() == null || request.getToken().trim().isEmpty()){
            return IntrospectRes.builder().valid(false).build();
        }

        boolean isValid = true;

        try {
            validateTokenNotEmpty(request.getToken(), "Token Introspection");
        } catch (Exception e) {
            isValid = false;
        }
        return IntrospectRes.builder().valid(isValid).build();
    }

    private void validateTokenNotEmpty(String token, String methodName){
        if (token == null || token.trim().isEmpty()){
            log.info("{} attempted with null or empty token", methodName);
            throw new AppException("INVALID_TOKEN");
        }
    }

    private String generateToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + VALID_DURATION);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("HealthCareSystem")
                .issueTime(now)
                .expirationTime(expirationTime)
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (Exception e) {
            log.error("Error generating token: {}", e.getMessage());
            throw new AppException("TOKEN_GENERATION_FAILED");
        }
    }

    private String buildScope(User user){
        StringJoiner joiner = new StringJoiner(" ");

        if(!CollectionUtils.isEmpty(user.getRoles())){
            for(Role role : user.getRoles()){
                joiner.add("ROLE_" + role.getRoleName());
                if(!CollectionUtils.isEmpty(role.getPermissions())){
                    role.getPermissions().forEach(permission -> joiner.add(permission.getPermissionName()));
                }
            }
        }
        return joiner.toString();
    }
}
