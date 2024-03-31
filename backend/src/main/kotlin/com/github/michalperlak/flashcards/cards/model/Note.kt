package com.github.michalperlak.flashcards.cards.model

import java.time.OffsetDateTime

data class Note(
    var text: String,
    var created: OffsetDateTime,
    var author: Author
)