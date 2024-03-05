package com.github.michalperlak.flashcards.cards.dto

import com.github.michalperlak.flashcards.cards.model.Note
import java.time.OffsetDateTime

data class NoteDto(
    val text: String,
    val created: OffsetDateTime,
    val author: String
) {

    companion object {
        fun from(note: Note): NoteDto = NoteDto(
            text = note.text,
            author = note.author.name,
            created = note.created
        )
    }
}