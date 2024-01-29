package com.github.michalperlak.flashcards.cards

import com.github.michalperlak.flashcards.cards.dto.NewCardDto
import com.github.michalperlak.flashcards.cards.validation.CardValidationError
import com.github.mpps.fsrs.model.State
import io.vavr.control.Either
import io.vavr.control.Option
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Service
class CardsService {
    private val cards: ConcurrentMap<CardId, Card> = ConcurrentHashMap()
    fun getById(id: CardId): Option<Card> = Option.of(cards[id])

    fun getForToday(states: Set<State>, limit: Int): List<Card> = listOf()
    fun createCard(newCard: NewCardDto): Either<CardValidationError, Card> {
        TODO()
    }
}