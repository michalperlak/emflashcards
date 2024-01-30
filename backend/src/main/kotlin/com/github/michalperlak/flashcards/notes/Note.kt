package com.github.michalperlak.flashcards.notes

import java.time.OffsetDateTime

data class Note(
    val text: String,
    val created: OffsetDateTime,
    val author: Author
)