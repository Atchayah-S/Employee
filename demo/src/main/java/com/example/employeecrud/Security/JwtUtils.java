package com.example.employeecrud.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
@Component
public class JwtUtils {
    private static final String SECRET_KEY_STRING="kd2ZQSDeneDM8E22swJ7qbAAftwdwsWm";
    private final SecretKey SECRET_KEY= Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
    public String generateJwtToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SECRET_KEY,Jwts.SIG.HS256)
                .compact();
    }
    public boolean validateToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
