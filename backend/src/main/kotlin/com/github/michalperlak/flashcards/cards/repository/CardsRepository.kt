package com.github.michalperlak.flashcards.cards.repository

import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import io.vavr.control.Option
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Repository
class CardsRepository {
    private val cards: ConcurrentMap<CardId, Card> = ConcurrentHashMap()

    fun getCard(cardId: CardId): Option<Card> = Option.of(cards[cardId])
    fun save(card: Card) = card.apply { cards[id] = this }
}