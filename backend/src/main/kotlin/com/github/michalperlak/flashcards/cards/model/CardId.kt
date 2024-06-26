package com.github.michalperlak.flashcards.cards.model

import java.util.*

data class CardId(
    val id: String
) {
    override fun toString(): String = id

    companion object {
        fun generate(): CardId = CardId(UUID.randomUUID().toString())
    }
}