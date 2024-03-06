package com.github.michalperlak.flashcards.users.config.auth

import com.github.michalperlak.flashcards.users.config.auth.dto.LoginDto
import com.github.michalperlak.flashcards.users.config.auth.dto.Token
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val jwtGenerator: JwtGenerator
) {
    @PostMapping("/login")
    fun login(@RequestBody login: LoginDto): ResponseEntity<*> {
        try {
            val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    login.username,
                    login.password
                )
            )
            val token: String = jwtGenerator.createToken(authentication.name)
            val loginRes = Token(token)
            return ResponseEntity.ok<Any>(loginRes)
        } catch (e: BadCredentialsException) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid username or password")
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.message)
        }
    }
}