package scnu.cstn2023.DDESS.Auth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key JWT_SECRET_KEY; // 使用 Key 对象作为密钥
    private final long JWT_EXPIRATION = 604800000L; // JWT有效期，单位为毫秒（7天）

    // 初始化密钥
    public JwtTokenProvider() {
        this.JWT_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // HS512 加密算法
    }

    // 创建 JWT 令牌
    public String createToken(String username, int role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) // 将角色信息添加到 JWT
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(JWT_SECRET_KEY) // 使用 Key 对象进行签名
                .compact();
    }

    // 从 JWT 中获取用户名
    public String getUsernameFromToken(String token) {
        Claims claims = parseClaims(token);
        return claims.getSubject();
    }

    // 从 JWT 中获取角色
    public int getRoleFromToken(String token) {
        Claims claims = parseClaims(token);
        return (int) claims.get("role");
    }

    // 验证 JWT 令牌
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 解析 JWT 令牌的 Claims
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
