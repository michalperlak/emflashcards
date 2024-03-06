package com.github.michalperlak.flashcards.users.config.auth

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.TimeUnit
import javax.crypto.spec.SecretKeySpec


@Component
class JwtGenerator {
    fun createToken(username: String): String {
        val claims = Jwts
            .claims()
            .subject(username)
            .build()
        val tokenCreateTime = Date()
        val tokenValidity = Date(tokenCreateTime.time + TimeUnit.MINUTES.toMillis(TOKEN_VALIDITY))
        val secretKeySpec = SecretKeySpec(SECRET_KEY, "HmacSHA256")
        return Jwts.builder()
            .claims(claims)
            .expiration(tokenValidity)
            .signWith(secretKeySpec, Jwts.SIG.HS256)
            .compact()
    }

    companion object {
        private const val TOKEN_VALIDITY = (60 * 60 * 1000).toLong()
        val SECRET_KEY: ByteArray = System.getenv("JWT_SECRET").encodeToByteArray()
    }
}