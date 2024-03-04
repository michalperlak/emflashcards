package com.github.michalperlak.flashcards.notes.model

import java.time.OffsetDateTime

data class Note(
    val text: String,
    val created: OffsetDateTime,
    val author: Author
)