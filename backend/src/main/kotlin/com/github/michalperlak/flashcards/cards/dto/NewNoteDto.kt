package com.github.michalperlak.flashcards.cards.dto

import com.github.michalperlak.flashcards.cards.model.CardId

data class NewNoteDto(
    val text: String,
    val author: String
)