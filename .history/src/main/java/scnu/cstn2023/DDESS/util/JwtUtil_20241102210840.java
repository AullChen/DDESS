package scnu.cstn2023.DDESS.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "123456";
    private final long EXPIRATION_TIME = 86400000; // 24 hours

    @SuppressWarnings("deprecation")
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build() // 创建 JwtParser
                .parseClaimsJws(token) // 解析 JWT
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build() // 创建 JwtParser
                .parseClaimsJws(token) // 解析 JWT
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
