package com.github.michalperlak.flashcards.cards

import com.github.michalperlak.flashcards.cards.dto.NewCardDto
import com.github.michalperlak.flashcards.cards.error.CardsError
import com.github.michalperlak.flashcards.cards.model.Card
import io.vavr.control.Either
import org.springframework.stereotype.Service

@Service
class CardsManagementService(
    private val cardsRepository: CardsRepository
) {
    fun createCard(newCard: NewCardDto): Either<CardsError, Card> {
        TODO()
    }
}