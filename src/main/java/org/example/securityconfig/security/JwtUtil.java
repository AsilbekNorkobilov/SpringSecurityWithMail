package org.example.securityconfig.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.securityconfig.dto.ReqDto;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    public String generateToken(ReqDto reqDto) {
        return Jwts.builder()
                .subject(reqDto.getEmail())
                //.claim("email",loginDto.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getKey())
                .compact();
    }
    public String generateConfirmToken(ReqDto reqDto, Integer code) {
        return Jwts.builder()
                .claim("mailCode",code)
                .claim("email",reqDto.getEmail())
                .claim("password",reqDto.getPassword())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        String secretKey = "1234567891234567891234567891234567891234567891234567891234567891";
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getEmail(String authorization) {
        Claims claims = getClaims(authorization);
        return claims.getSubject();
    }



    public Integer getMailCode(String confirm){
        Claims claims = getClaims(confirm);
        return claims.get("mailCode",Integer.class);
    }

    public ReqDto getReqDto(String confirm){
        Claims claims = getClaims(confirm);
        return new ReqDto(claims.get("email", String.class),claims.get("password", String.class));
    }
}
