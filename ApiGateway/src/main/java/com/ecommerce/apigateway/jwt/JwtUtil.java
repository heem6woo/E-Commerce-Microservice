package com.ecommerce.apigateway.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

@Component
@Slf4j
public class JwtUtil {

    @Value("${application.security.jwt.secret-key}")
    private String secret_key;


    // Determine whether token is for userDetails
    public boolean isTokenValid(String token) {
        final Claims claims = extractAllClaims(token); // validate the token is written with the secret key
        isAccessToken(token);
        return isTokenExpired(token);
    }

    public void isAccessToken(String token) {
        String tokenType = extractTokenType(token);
        if(!Objects.equals(tokenType, "access")) {
            throw new HttpStatusCodeException(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "Token Type is not access") {
            };
        }

    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
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

    private Key getSignInKey() {
        byte[] keyBytes =  Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
