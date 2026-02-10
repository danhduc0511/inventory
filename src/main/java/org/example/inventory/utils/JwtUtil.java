package org.example.inventory.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.inventory.dtos.respon.JwtInfo;
import org.example.inventory.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private  String jwtSecret;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    //String->byte[]->Key
    public Key getJwtKey() {
        byte[] keyBytes= Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    //xay dung generateToken
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        return  createToken(claims,user);
    }

    private String createToken(Map<String, Object> claims, User user) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration*1000))
                .setId(UUID.randomUUID().toString())
                .signWith(getJwtKey(),SignatureAlgorithm.HS256)
                .compact();
    }
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getJwtKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        //áp dụng hàm để lấy claim cụ thể
        return claimsResolver.apply(getAllClaimsFromToken(token));
    }
    //lay email tu token
    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //kiem tra token con hieu luc khong
    public Boolean isTokenExpired(String token) {
        return getClaimFromToken(token, Claims::getExpiration)
                .before(new Date());
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        return !isTokenExpired(token)||getEmailFromToken(token).equals(userDetails.getUsername());
    }

    public JwtInfo parseToken(String token) {
        String tokenId = getClaimFromToken(token, Claims::getId);
        Date issueTime = getClaimFromToken(token, Claims::getIssuedAt);
        Date expirationTime = getClaimFromToken(token, Claims::getExpiration);
        return JwtInfo.builder()
                .tokenId(tokenId)
                .issueTime(issueTime)
                .expirationTime(expirationTime)
                .build();
    }
}

//ID cho token = jti
//Sinh bằng UUID
//Dùng cho logout / revoke / blacklist
//Lưu Redis với TTL theo exp
