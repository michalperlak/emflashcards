package com.github.michalperlak.flashcards.cards

import com.github.michalperlak.flashcards.cards.dto.RateCardDto
import com.github.michalperlak.flashcards.cards.error.CardsError
import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import io.vavr.control.Either
import org.springframework.stereotype.Service

@Service
class CardsRatingService {
    fun rateCard(cardId: CardId, rate: RateCardDto): Either<CardsError, Card> {
        TODO("Not yet implemented")
    }
}