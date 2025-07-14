package com.locquest.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    // application.properties 에 정의된 비밀키 (Base64로 인코딩되어 있어야 합니다)
    @Value("${jwt.secret}")
    private String secretKey;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 토큰에서 subject(claims.getSubject()) 값을 Long 으로 꺼냅니다.
     * 일반적으로 subject에 userId를 넣어두었다고 가정합니다.
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.valueOf(claims.getSubject());
    }

    /**
     * 토큰이 유효한지 검증합니다.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            // 토큰 만료, 서명오류 등 모든 예외를 여기서 캐치
            return false;
        }
    }
}

