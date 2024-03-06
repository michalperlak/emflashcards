package com.github.michalperlak.flashcards.users.config.auth

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.michalperlak.flashcards.users.repository.UsersRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.util.*
import javax.crypto.spec.SecretKeySpec


@Component
class JwtTokenFilter(
    private val mapper: ObjectMapper,
    private val usersRepository: UsersRepository
) : GenericFilterBean() {

    private val jwtParser: JwtParser =
        Jwts
            .parser()
            .verifyWith(SecretKeySpec(JwtGenerator.SECRET_KEY, "HmacSHA256"))
            .build();

    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        filterChain: FilterChain
    ) {
        doFilterInternal(
            request as HttpServletRequest,
            response as HttpServletResponse,
            filterChain
        )
    }

    private fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val errorDetails: MutableMap<String, Any?> = HashMap()

        try {
            val accessToken = resolveToken(request)
            if (accessToken == null) {
                filterChain.doFilter(request, response)
                return
            }
            val claims = resolveClaims(request)
            if (claims != null && validateClaims(claims)) {
                val username = claims.subject
                usersRepository
                    .getByName(username)
                    .map { UsernamePasswordAuthenticationToken(it, "", listOf()) }
                    .forEach { SecurityContextHolder.getContext().authentication = it }
            }
        } catch (e: Exception) {
            errorDetails["message"] = "Authentication Error"
            errorDetails["details"] = e.message
            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = MediaType.APPLICATION_JSON_VALUE

            mapper.writeValue(response.writer, errorDetails)
        }
        filterChain.doFilter(request, response)
    }

    private fun parseJwtClaims(token: String): Claims {
        return jwtParser.parseSignedClaims(token).payload
    }

    fun resolveClaims(req: HttpServletRequest): Claims? {
        try {
            val token = resolveToken(req)
            if (token != null) {
                return parseJwtClaims(token)
            }
            return null
        } catch (ex: ExpiredJwtException) {
            req.setAttribute("expired", ex.message)
            throw ex
        } catch (ex: java.lang.Exception) {
            req.setAttribute("invalid", ex.message)
            throw ex
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(TOKEN_HEADER)
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substringAfter(TOKEN_PREFIX)
        }
        return null
    }

    fun validateClaims(claims: Claims): Boolean {
        try {
            return claims.expiration.after(Date())
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    companion object {
        private const val TOKEN_HEADER = "Authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }
}