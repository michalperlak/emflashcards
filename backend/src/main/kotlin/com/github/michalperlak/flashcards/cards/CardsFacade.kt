package com.github.michalperlak.flashcards.cards

import com.github.michalperlak.flashcards.cards.dto.NewCardDto
import com.github.michalperlak.flashcards.cards.error.CardsError
import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import com.github.mpps.fsrs.model.State
import io.vavr.control.Either

class CardsFacade(
    private val cardsManagementService: CardsManagementService
) {

    fun getForToday(states: Set<State>, limit: Int): List<Card> = listOf()

    fun createCard(newCard: NewCardDto): Either<CardsError, Card> {
        TODO()
    }

    fun deleteCard(cardId: CardId): Either<CardsError, Unit> {
        TODO()
    }
}