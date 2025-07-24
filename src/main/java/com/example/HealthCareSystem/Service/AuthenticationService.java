package com.example.HealthCareSystem.Service;

import com.example.HealthCareSystem.Dto.Request.AuthenticationReq;
import com.example.HealthCareSystem.Dto.Request.IntrospectReq;
import com.example.HealthCareSystem.Dto.Request.LogoutReq;
import com.example.HealthCareSystem.Dto.Request.RefreshReq;
import com.example.HealthCareSystem.Dto.Response.AuthenticationRes;
import com.example.HealthCareSystem.Dto.Response.IntrospectRes;
import com.example.HealthCareSystem.Entity.InvalidatedToken;
import com.example.HealthCareSystem.Entity.Role;
import com.example.HealthCareSystem.Entity.User;
import com.example.HealthCareSystem.Exception.AppException;
import com.example.HealthCareSystem.Exception.ErrorCode;
import com.example.HealthCareSystem.Repository.InvalidatedRepository;
import com.example.HealthCareSystem.Repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    InvalidatedRepository invalidatedRepository;

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
            throw new AppException(ErrorCode.INVALID_TOKEN);
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
            throw new AppException(ErrorCode.TOKEN_GENERATION_FAILED);
        }
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime  = (isRefresh) ?
                new Date(
                        signedJWT.getJWTClaimsSet()
                                .getIssueTime()
                                .toInstant()
                                .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                                .toEpochMilli()) : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if(!verified && expiryTime.after(new Date())) throw new AppException(ErrorCode.INVALID_TOKEN);
        if(invalidatedRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())){
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        return signedJWT;
    }

    // Login method
    @Transactional
    public AuthenticationRes authenticate(AuthenticationReq request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated) throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        var token = generateToken(user);
        return AuthenticationRes.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
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

    // Logout method
    @Transactional
    public void logout(LogoutReq request){
        if(request == null || request.getToken() == null || request.getToken().trim().isEmpty()){
            log.info("Logout attempted with null or empty token");
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        try {
            var signToken = verifyToken(request.getToken(), true);
            String jti = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();
            invalidateToken(jti, expiryTime);
        } catch (ParseException | JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthenticationRes refresh(RefreshReq request) throws ParseException, JOSEException {
        if (request == null || request.getToken() == null || request.getToken().trim().isEmpty()){
            log.info("Refresh attempted with null or empty token");
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        var signedJWT = verifyToken(request.getToken(), true);
        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        invalidateToken(jit, expiryTime);

        var username = signedJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        var token = generateToken(user);
        return AuthenticationRes.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }

    private void invalidateToken(String jti, Date expiryTime){
        if(!invalidatedRepository.existsById(jti)){
            try {
                InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                        .id(jti)
                        .expiryDate(expiryTime)
                        .build();
                invalidatedRepository.save(invalidatedToken);
                log.debug("Token with ID: {} has been invalidated", jti);
            } catch (ObjectOptimisticLockingFailureException e) {
                log.error("Failed to invalidate token with ID: {} due to optimistic locking failure", jti);
                throw new AppException(ErrorCode.INVALID_TOKEN);
            }
        }
    }
}
