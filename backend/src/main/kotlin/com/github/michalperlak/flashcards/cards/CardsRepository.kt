package com.github.michalperlak.flashcards.cards

import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Repository
class CardsRepository {
    private val cards: ConcurrentMap<CardId, Card> = ConcurrentHashMap()
}