package com.github.michalperlak.flashcards.cards

import com.github.michalperlak.flashcards.cards.application.CardsManagementService
import com.github.michalperlak.flashcards.cards.application.CardsQueryService
import com.github.michalperlak.flashcards.cards.application.CardsRatingService
import com.github.michalperlak.flashcards.cards.application.NotesService
import com.github.michalperlak.flashcards.cards.dto.NewCardDto
import com.github.michalperlak.flashcards.cards.dto.NewNoteDto
import com.github.michalperlak.flashcards.cards.dto.RateCardDto
import com.github.michalperlak.flashcards.cards.error.CardsError
import com.github.michalperlak.flashcards.cards.model.Author
import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import com.github.michalperlak.flashcards.cards.model.Note
import com.github.michalperlak.flashcards.users.model.UserId
import com.github.mpps.fsrs.model.State
import io.vavr.control.Either
import org.springframework.stereotype.Component

@Component
class CardsFacade(
    private val cardsQueryService: CardsQueryService,
    private val cardsManagementService: CardsManagementService,
    private val cardsRatingService: CardsRatingService,
    private val notesService: NotesService
) {

    fun getForToday(userId: UserId, states: Set<State>, limit: Int): List<Card> =
        cardsQueryService.getForToday(userId, states, limit)

    fun createCard(newCard: NewCardDto): Either<CardsError, Card> =
        cardsManagementService.createCard(newCard)

    fun deleteCard(cardId: CardId): Either<CardsError, Unit> =
        cardsManagementService.deleteCard(cardId)

    fun rateCard(cardId: CardId, rate: RateCardDto): Either<CardsError, Card> =
        cardsRatingService.rateCard(cardId, rate)

    fun createNote(author: Author, cardId: CardId, newNote: NewNoteDto): Either<CardsError, Note> =
        notesService.createNote(author, cardId, newNote)
}