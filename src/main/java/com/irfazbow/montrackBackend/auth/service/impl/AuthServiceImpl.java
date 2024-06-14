package com.irfazbow.montrackBackend.auth.service.impl;

import com.irfazbow.montrackBackend.auth.repository.RedisTokenRepository;
import com.irfazbow.montrackBackend.auth.service.AuthService;
import com.irfazbow.montrackBackend.user.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

@Log
@Service
public class AuthServiceImpl implements AuthService {

    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RedisTokenRepository redisTokenRepository;
    private final JwtDecoder jwtDecoder;

    public AuthServiceImpl(JwtEncoder jwtEncoder, PasswordEncoder passwordEncoder, UserRepository userRepository, RedisTokenRepository redisTokenRepository, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.redisTokenRepository = redisTokenRepository;
        this.jwtDecoder = jwtDecoder;
    }

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        String existingToken = redisTokenRepository.getToken(email);

//      if (existingToken != null && !isTokenExpired(existingToken)) {
        if (existingToken != null) {
            return existingToken;
        }

        Instant now = Instant.now();
        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(24, ChronoUnit.HOURS))
                .subject(email)
                .claim("scope", scope)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        // Store the new token in Redis with expiration time
        redisTokenRepository.saveToken(email, token, 24);

        return token;
    }

//    private boolean isTokenExpired(String token) {
//        try {
//            Jwt decodedJwt = jwtDecoder.decode(token);
//            Map<String, Object> claims = decodedJwt.getClaims();
//            Instant expiresAt = Instant.ofEpochSecond((Long) claims.get("exp"));
//
//            return Instant.now().isAfter(expiresAt);
//        } catch (Exception e) {
//            return true;
//        }
//    }
}
