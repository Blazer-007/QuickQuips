package com.blazer.quickquips.userprofile.service;

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

@Component
public class JwtService {

    private String SECRET_KEY = "KhZ3Wgp3b2k/NQpzTckJxw8SjzeqIBGhizP+n1pCA+toBw7YytM9ZxrFvGybXn4aL+XmccBmXqgvm3fAsJ8vAm2mnyvtl00B10LiTAVJddlOvJm49+X0xzOPBf1px9HAG4jTkSsl0Go+5a4fmg0GiBYRKfg0K26SGchl+PQ7vpVwcVJ3JtyO4407amvt+kUw+vpr1K2X4pHZVtDyEEMIm4Z/zNiSIwPYa3qYrbNaBJMNcxYuphSFl0iunpuU83p8tL1M5pSBGCCMBHXa8eJL2Oct+/h1pmWbReSX/LkM/xuHuQb5bO8G5Nz3X+DLkEQmHFprxJlRpRgwee9hWij02R6QQz61HgGNCTrpvQuAclM=";

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
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
