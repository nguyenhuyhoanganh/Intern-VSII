package com.example.base.security;


import com.example.base.dto.TokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author HungDV
 * Class xử lý logic của token
 */
@Component
public class JwtService {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public static final int TOKEN_EXPIRATION = 1000 * 60 * 30;

    /**
     * Gen token
     * @param userName userName từ token
     * @return thành công trả về token
     */
    public TokenDTO generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return TokenDTO.builder().token(createToken(claims, userName)).build();
    }

    /**
     * Tạo token
     * @param claims
     * @param userName
     * @return
     */
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.TOKEN_EXPIRATION))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Lấy ra key từ secret
     * @return Key
     */
    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * get username từ token
     * @param token
     * @return
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * get thời hạn của token
     * @param token
     * @return
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Lấy ra 1 Claim từ token
     * @param token
     * @param claimsResolver
     * @return
     * @param <T> kiểu dữ liệu truyền vào và trả về
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Lấy danh sách claim từ token
     * @param token
     * @return
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Kểm tra token còn hạn không
     * @param token String
     * @return true nếu còn, false nếu hết
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Kiểm tra token còn hợp lệ không
     * @param token String
     * @param userDetails
     * @return true nếu token hợp lệ , false ngợc lại
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
