package com.github.michalperlak.flashcards.cards.application

import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.repository.CardsRepository
import com.github.michalperlak.flashcards.users.model.UserId
import com.github.mpps.fsrs.model.State
import org.springframework.stereotype.Service

@Service
class CardsQueryService(
    private val cardsRepository: CardsRepository
) {
    fun getForToday(userId: UserId, states: Set<State>, limit: Int): List<Card> =
        cardsRepository
            .getAll()
            .filter { states.contains(it.learningState[userId].state) }
            .sortedBy { it.learningState[userId].due }
            .take(limit)
}
