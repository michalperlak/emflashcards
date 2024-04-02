package com.github.michalperlak.flashcards.cards.application

import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.UserLearningState
import com.github.michalperlak.flashcards.cards.repository.CardsRepository
import com.github.michalperlak.flashcards.time.TimeService
import com.github.michalperlak.flashcards.users.model.UserId
import com.github.mpps.fsrs.model.State
import org.springframework.stereotype.Service
import java.time.LocalTime
import java.time.OffsetDateTime

@Service
class CardsQueryService(
    private val cardsRepository: CardsRepository,
    private val timeService: TimeService
) {
    fun getForToday(userId: UserId, states: Set<State>, limit: Int): List<Card> {
        println(userId)
        println(cardsRepository.getAll())
        return cardsRepository
            .getAll()
            .distinctBy { it.id }
            .filter { states.contains(it.learningState[userId].state) && isForNextReview(it.learningState[userId]) }
            .sortedBy { it.learningState[userId].due }
            .take(limit)
    }


    private fun isForNextReview(learningState: UserLearningState): Boolean {
        val now = timeService.getCurrentTimestamp()
        val midnight = OffsetDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT, now.offset)
        return learningState.due.isBefore(midnight.plusDays(7))
    }
}
