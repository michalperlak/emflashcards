package com.github.michalperlak.flashcards.cards.application

import com.github.michalperlak.flashcards.cards.dto.NewNoteDto
import com.github.michalperlak.flashcards.cards.error.CardsError
import com.github.michalperlak.flashcards.cards.model.Author
import com.github.michalperlak.flashcards.cards.model.CardId
import com.github.michalperlak.flashcards.cards.model.Note
import com.github.michalperlak.flashcards.cards.repository.CardsRepository
import com.github.michalperlak.flashcards.time.TimeService
import io.vavr.control.Either
import org.springframework.stereotype.Service

@Service
class NotesService(
    private val timeService: TimeService,
    private val cardsRepository: CardsRepository
) {

    fun createNote(author: Author, cardId: CardId, newNote: NewNoteDto): Either<CardsError, Note> {
        val note = Note(
            text = newNote.text,
            author = author,
            created = timeService.getCurrentTimestamp()
        )
        return cardsRepository
            .getCard(cardId)
            .toEither { cardNotFound(cardId) }
            .map { it.addNote(note) }
            .map { cardsRepository.save(it) }
            .map { note }
    }

    private fun cardNotFound(cardId: CardId) =
        CardsError("Card for id: $cardId not found!")
}