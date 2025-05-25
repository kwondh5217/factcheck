package com.extension.factcheck.config.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date
import javax.crypto.SecretKey

@Service
class TokenProvider {

    @Value("\${jwt.secret}")
    private lateinit var secretKeyString: String

    private lateinit var secretKey: SecretKey

    @PostConstruct
    fun init() {
        val keyBytes = secretKeyString.toByteArray(StandardCharsets.UTF_8)
        require(keyBytes.size >= 32) { "JWT secret key must be at least 256 bits (32 bytes) long." }
        secretKey = Keys.hmacShaKeyFor(keyBytes)
    }

    fun create(userId: String, grade: String): String {
        val expiryDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS))

        return Jwts.builder()
            .subject(userId)
            .claim("role", "ROLE_$grade")
            .issuedAt(Date())
            .expiration(expiryDate)
            .signWith(secretKey)
            .compact()
    }

    fun validateAndGetClaims(token: String): JwtPayload {
        val claims = Jwts
            .parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload

        val userId = claims.subject
        val role = claims["role"] as? String ?: "ROLE_FREE"

        return JwtPayload(userId, role)
    }
}

data class JwtPayload(
    val userId: String,
    val role: String
)
