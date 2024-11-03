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

    @SuppressWarnings("deprecation")
    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Invalid JWT signature");
        } catch (MalformedJwtException e) {
            throw new IllegalArgumentException("Invalid JWT token");
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("Unsupported JWT token");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("JWT claims string is empty.");
        }
    }

    public boolean validateToken(String token, String username) {
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }

    @SuppressWarnings("deprecation")
    private boolean isTokenExpired(String token) {
        try {
            return ((Object) Jwts.parser()
                                .setSigningKey(SECRET_KEY))
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .before(new Date());
        } catch (ExpiredJwtException e) {
            return true; // Token is expired
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT token");
        }
    }
}