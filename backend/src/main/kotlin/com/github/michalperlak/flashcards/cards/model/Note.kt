package com.github.michalperlak.flashcards.cards.model

import java.time.OffsetDateTime

data class Note(
    val text: String,
    val created: OffsetDateTime,
    val author: Author
)