package org.example.securityconfig.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.securityconfig.dto.LoginDto;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    public String generateToken(LoginDto loginDto) {
        return Jwts.builder()
                .subject(loginDto.getEmail())
                .claim("email",loginDto.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        byte[] bytes = Decoders.BASE64.decode("1234567891234567891234567891234567891234567891234567891234567891");
        return Keys.hmacShaKeyFor(bytes);
    }
}
