package com.github.michalperlak.flashcards.cards.dto

import com.github.michalperlak.flashcards.cards.model.Category

data class NewCardDto(
    val question: String,
    val answer: String,
    val category: Category
)