package com.ecommerce.memberservice.service;

import com.ecommerce.memberservice.exception.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secret_key;

    @Value("${application.security.jwt.access-token.expiration}")
    private long accessExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;


    // Determine whether token is for userDetails
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // validate the token is written with the secret key
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public void isAccessToken(String token) {
        String tokenType = extractTokenType(token);
        if(!Objects.equals(tokenType, "access")) {
            throw new AuthenticationException("Token Type is not access", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }

    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Duration liefDuration(String token) {
        long duration = new Date().getTime() - extractClaim(token, Claims::getExpiration).getTime();
        return Duration.ofMillis(duration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String extractTokenType(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.get("tokenType",String.class);
    }
    public String extractRole(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.get("role",String.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    // extract all claims from the token using secret key

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateAccessToken(UserDetails userDetails) {
        HashMap<String,Object> claims = new HashMap<>();
        claims.put("tokenType", "access");
        if (!userDetails.getAuthorities().isEmpty()) {
            claims.put("role", (String) userDetails.getAuthorities().iterator().next().getAuthority());
        }

        return buildToken(claims, userDetails, accessExpiration);
    }




    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        HashMap<String,Object> claims = new HashMap<>();
        claims.put("tokenType", "refresh");

        return buildToken(claims, userDetails, refreshExpiration);

    }

    private String buildToken (
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignInKey() {
        byte[] keyBytes =  Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
