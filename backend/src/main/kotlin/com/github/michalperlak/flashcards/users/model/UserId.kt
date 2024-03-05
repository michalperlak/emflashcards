package com.github.michalperlak.flashcards.users.model

import java.util.*

data class UserId(
    private val id: String
) {
    companion object {
        fun generate(): UserId = UserId(UUID.randomUUID().toString())
    }
}