package com.github.michalperlak.flashcards.cards.model

import com.github.michalperlak.flashcards.cards.model.Category

data class Question(
    val text: String,
    val category: Category
)