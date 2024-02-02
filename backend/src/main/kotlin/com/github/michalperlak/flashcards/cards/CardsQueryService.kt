package com.github.michalperlak.flashcards.cards

import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.users.model.UserId
import com.github.mpps.fsrs.model.State
import org.springframework.stereotype.Service

@Service
class CardsQueryService {
    fun getForToday(userId: UserId, states: Set<State>, limit: Int): List<Card> {
        TODO("Not yet implemented")
    }

}
