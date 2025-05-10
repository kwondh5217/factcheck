package com.extension.factcheck.config.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenProvider {

  @Value("${jwt.secret}")
  private String secretKeyString;

  private SecretKey secretKey;

  @PostConstruct
  public void init() {
    this.secretKey = secretKey();
  }

  public String validateAndGetUserId(String token) {
    return Jwts
        .parser()
        .verifyWith(secretKey())
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .getSubject();
  }

  public String create(String userId) {
    SecretKey key = secretKey();

    Date expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

    return Jwts.builder()
        .subject(userId)
        .issuedAt(new Date())
        .expiration(expiryDate)
        .signWith(key)
        .compact();
  }

  private SecretKey secretKey() {
    String encoded = Base64.getEncoder()
        .encodeToString(secretKeyString.getBytes(StandardCharsets.UTF_8));
    byte[] keyBytes = Base64.getDecoder().decode(encoded);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}