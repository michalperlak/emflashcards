package com.github.michalperlak.flashcards.cards.repository

import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import io.vavr.control.Option

interface CardsRepository {
    fun getCard(cardId: CardId): Option<Card>
    fun save(card: Card): Card
    fun delete(card: Card): Card
    fun getAll(): List<Card>
}