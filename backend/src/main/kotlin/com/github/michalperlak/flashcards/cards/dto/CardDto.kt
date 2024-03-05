package com.github.michalperlak.flashcards.cards.dto

import com.github.michalperlak.flashcards.cards.model.Card
import java.time.OffsetDateTime

data class CardDto(
    val id: String,
    val question: String,
    val answer: String,
    val notes: List<NoteDto>,
    val created: OffsetDateTime,
    val learningState: LearningStateDto
) {

    companion object {
        fun from(card: Card): CardDto = CardDto(
            id = card.id.id,
            question = card.question.text,
            answer = card.answer.text,
            notes = card.notes.map { NoteDto.from(it) },
            created = card.created,
            learningState = LearningStateDto.from(card.learningState)
        )
    }
}