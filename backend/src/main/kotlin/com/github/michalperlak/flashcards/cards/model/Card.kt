package com.github.michalperlak.flashcards.cards.model

import com.github.michalperlak.flashcards.notes.model.Note
import java.time.OffsetDateTime

data class Card(
    val id: CardId,
    val question: Question,
    val answer: Answer,
    val notes: List<Note>,
    val created: OffsetDateTime,
    val learningState: LearningState
)