package com.github.michalperlak.flashcards.cards.model

import java.time.OffsetDateTime

data class Card(
    val id: CardId,
    val category: Category,
    val question: Question,
    val answer: Answer,
    val notes: List<Note>,
    val created: OffsetDateTime,
    val learningState: LearningState
) {
    fun addNote(note: Note): Card = copy(notes = notes + note)
}

