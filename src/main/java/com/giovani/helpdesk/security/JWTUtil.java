package com.giovani.helpdesk.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

import static java.lang.System.currentTimeMillis;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey signKey;

    @PostConstruct
    public void init() {
        // Decodifica a chave Base64
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        if (keyBytes.length < 64) { // HS512 requires at least 512 bits (64 bytes)
            throw new IllegalArgumentException("A chave secreta deve ter pelo menos 512 bits (64 bytes) para HS512.");
        }
        signKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .expiration(new Date(currentTimeMillis() + expiration))
                .signWith(signKey)
                .compact();
    }

    public boolean tokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(currentTimeMillis());
            return username != null && expirationDate != null && now.before(expirationDate);
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(signKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }
}