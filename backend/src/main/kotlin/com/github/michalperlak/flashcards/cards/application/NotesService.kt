package com.github.michalperlak.flashcards.cards.application

import com.github.michalperlak.flashcards.cards.dto.NewNoteDto
import com.github.michalperlak.flashcards.cards.error.CardsError
import com.github.michalperlak.flashcards.cards.model.CardId
import com.github.michalperlak.flashcards.cards.model.Note
import io.vavr.control.Either

class NotesService {
    fun createNote(cardId: CardId, newNote: NewNoteDto): Either<CardsError, Note> {
        TODO("Not yet implemented")
    }


}