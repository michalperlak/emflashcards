package com.github.michalperlak.flashcards.cards.dto

data class NewCardDto(
    val question: String,
    val answer: String,
    val category: String
)