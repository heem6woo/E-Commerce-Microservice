package com.example.login.security.jwt;

import java.security.Key;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.Decoders;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.example.login.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.security.auth.Subject;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
  private final ObjectMapper objectMapper;
  @Value("${jwt.key.jwtSecret}")
  private String jwtSecret;

  @Value("${jwt.live.rtk}")
  private int rtkExpirationMs;

  @Value("${jwt.name.rtk}")
  private String rtkCookie;


  @Value("${jwt.live.atk}")
  private int atkExpirationMs;

  @Value("${jwt.name.atk}")
  private String atkCookie;

  public JwtUtils(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public String getJwtFromCookies(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, atkCookie);
    if (cookie != null) {
      System.out.println("쿠키있음");
      return cookie.getValue();
    } else {
      System.out.println("쿠키없음");
      return null;
    }
  }

  public ResponseCookie generateJwtCookieAtk(UserDetailsImpl userPrincipal) {
    String jwt = generateTokenFromUsernameAtk(userPrincipal.getUsername());
      return ResponseCookie.from(atkCookie, jwt).path("/api").maxAge(atkExpirationMs).httpOnly(true).build();
  }

  public ResponseCookie generateJwtCookieRtk(UserDetailsImpl userPrincipal) {
    String jwt = generateTokenFromUsernameRtk(userPrincipal.getUsername());
      return ResponseCookie.from(rtkCookie, jwt).path("/api").maxAge(rtkExpirationMs).httpOnly(true).build();
  }
  public ResponseCookie getCleanJwtCookieAtk() {
    ResponseCookie cookie = ResponseCookie.from(atkCookie, null).path("/api").build();
    return cookie;
  }
  public ResponseCookie getCleanJwtCookieRtk() {
    ResponseCookie cookie = ResponseCookie.from(rtkCookie, null).path("/api").build();
    return cookie;
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parserBuilder().setSigningKey(key()).build()
        .parseClaimsJws(token).getBody().getSubject();
  }

  private Key key() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }


  public boolean validateJwtToken(String authToken) {
    try {
      System.out.println(authToken);
      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
      System.out.println("토큰유효함");
      return true;
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }
    System.out.println("토큰 안 유효함");
    return false;
  }
  
  public String generateTokenFromUsernameAtk(String username) {
    return Jwts.builder()
              .setSubject(username)
              .setIssuedAt(new Date())
              .setExpiration(new Date((new Date()).getTime() + atkExpirationMs))
              .signWith(key(), SignatureAlgorithm.HS256)
              .compact();
  }

  public String generateTokenFromUsernameRtk(String username) {
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + rtkExpirationMs))
            .signWith(key(), SignatureAlgorithm.HS256)
            .compact();
  }
}
