package com.example.authenticationservice.jwt;

import com.example.authenticationservice.entity.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider implements Serializable {

    private String JWT_SECRET_TOKEN = "secret";

    private long JWT_EXPIRATION_TOKEN = 5184000000L;

    public String generateAccessToken(User user) {
        return Jwts.builder()
                .setHeader(header())
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_TOKEN)
                .setClaims(getClaims(user))
                .setSubject(user.getUsername())
                .setExpiration(getExpiredTokenTime())
                .setIssuedAt(getTimeNow())
                .compact();
    }

    public String generateRefreshToken(User user){
        String username = user.getUsername();
        return Jwts.builder()
                .setClaims(stringRandomGenerator())
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET_TOKEN)
                .compact();
    }

    public String getUsernameFromSubjectJWT(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(JWT_SECRET_TOKEN)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET_TOKEN).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }

    private Date getTimeNow(){
        return new Date();
    }

    private Date getExpiredTokenTime(){
        return new Date(System.currentTimeMillis() + JWT_EXPIRATION_TOKEN);
    }

    private Map<String, Object> getClaims(User user){
        Map<String, Object> mClaims = new HashMap<>();
        mClaims.put("email", user.getEmail());
        mClaims.put("phone_number", user.getPhoneNumber());
        mClaims.put("role", user.getUserRole());
        mClaims.put("uid", user.getUid());
        mClaims.put("full_name",user.getFullName());
        mClaims.put("username", user.getUsername());
        return mClaims;
    }

    private Map<String, Object> header(){
        Map<String, Object> map = new HashMap<>();
        map.put("typ", "JWT");
        return map;
    }

    private Map<String, Object> stringRandomGenerator(){
        Map<String, Object> mClaims = new HashMap<>();
        String randomString = RandomStringUtils.randomAlphabetic(60);
        mClaims.put("info", randomString);
        return mClaims;
    }
}
