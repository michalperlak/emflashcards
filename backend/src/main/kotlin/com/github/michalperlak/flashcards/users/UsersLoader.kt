package com.github.michalperlak.flashcards.users

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.michalperlak.flashcards.users.model.User
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths

@Component
class UsersLoader(
    private val environment: Environment,
    private val objectMapper: ObjectMapper
) {
    fun load(): List<User> {
        val usersFile = Paths.get(
            environment.getRequiredProperty("users.path")
        )
        return Files
            .newInputStream(usersFile)
            .use { objectMapper.readValue(it) }
    }
}