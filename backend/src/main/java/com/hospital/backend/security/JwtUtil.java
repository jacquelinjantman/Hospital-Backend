package com.hospital.backend.security;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String generarToken(String email, String rol){
        return Jwts.builder()
                .subject(email)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    public String extraerEmail(String token){
        return Jwts.parser()
                   .verifyWith(secretKey)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();

    }

    public boolean validarToken(String token){
        try{
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public String extraerRol(String token) {
    return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .get("rol", String.class);
}
}
