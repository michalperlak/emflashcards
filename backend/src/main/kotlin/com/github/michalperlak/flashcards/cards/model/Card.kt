package com.github.michalperlak.flashcards.cards.model

import java.time.OffsetDateTime

data class Card(
    var id: CardId,
    var category: Category,
    var question: Question,
    var answer: Answer,
    var notes: List<Note>,
    var created: OffsetDateTime,
    var learningState: LearningState
) {
    fun addNote(note: Note): Card = copy(notes = notes + note)
}

