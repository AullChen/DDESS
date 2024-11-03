package scnu.cstn2023.DDESS.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    //@Value("${jwt.secret}")
    private String secretKeyBase64 = "MzB4QWpJNk1qZGpZak5sWm1GbFpXWTBOVGxkU1dWek5UQXlPRGRwWld4bFpXWmtaV05sY21Wek9EQT0=";

    private final Key SECRET_KEY;

    public JwtUtil() {
        try {
            if (secretKeyBase64 == null || secretKeyBase64.isEmpty()) {
                logger.error("JWT secret key is not configured or is empty");
                throw new IllegalArgumentException("JWT secret key is not configured");
            }
            SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKeyBase64));
            logger.info("JWT secret key initialized successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Failed to initialize JwtUtil with secret key: {}", secretKeyBase64, e);
            throw new RuntimeException("Failed to initialize JwtUtil", e);
        } catch (Exception e) {
            logger.error("Unexpected error initializing JwtUtil with secret key: {}", secretKeyBase64, e);
            throw new RuntimeException("Failed to initialize JwtUtil", e);
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        // 通过解析器解析JWT并获取Claims
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10小时有效期
                .signWith(SECRET_KEY) // 使用密钥签名
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}