package com.github.michalperlak.flashcards.users.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class UserId @JsonCreator constructor(
    @JsonProperty("id") private val id: String
) {
    companion object {
        fun generate(): UserId = UserId(UUID.randomUUID().toString())
    }
}