package com.dinoryn.operion.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 72)
                )
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {

        return extractAllClaims(token)
                .getSubject();
    }

    private Claims extractAllClaims(String token){

        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(
            String token,
            UserDetails userDetails
    ){

        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                &&
                !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){

        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
}