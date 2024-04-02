package com.github.michalperlak.flashcards.users.model

import java.util.*

data class UserId(
    val id: String
) {
    override fun toString(): String = id

    companion object {
        fun generate(): UserId = UserId(UUID.randomUUID().toString())
    }
}