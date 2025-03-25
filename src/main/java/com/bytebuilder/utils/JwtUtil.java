package com.bytebuilder.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "7a3e1f9d4c8b0a2e5f6d7c3b9a8e1f2d4c5b6a7e8f9d0c1b2a3e4f5d6c7b8a9"; // Use a 256-bit key
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private static final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generate JWT Token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate JWT and extract username
    public String extractEmail(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null; // Invalid token
        }
    }
}
