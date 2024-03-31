package com.github.michalperlak.flashcards.cards.application

import com.github.michalperlak.flashcards.cards.dto.RateCardDto
import com.github.michalperlak.flashcards.cards.error.CardsError
import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import com.github.michalperlak.flashcards.cards.model.UserLearningState
import com.github.michalperlak.flashcards.cards.repository.CardsRepository
import com.github.michalperlak.flashcards.time.TimeService
import com.github.michalperlak.flashcards.users.model.UserId
import com.github.mpps.fsrs.algorithm.FSRS
import com.github.mpps.fsrs.algorithm.FSRSParameters
import com.github.mpps.fsrs.model.Rating
import io.vavr.control.Either
import io.vavr.control.Option
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import com.github.mpps.fsrs.model.Card as FsrsCard

@Service
class CardsRatingService(
    private val timeService: TimeService,
    private val cardsRepository: CardsRepository
) {
    fun rateCard(cardId: CardId, userId: UserId, rate: RateCardDto): Either<CardsError, Card> =
        cardsRepository
            .getCard(cardId)
            .toEither { CardsError("Card for id: $cardId not found!") }
            .map { updateRate(it, userId, rate.rating) }

    private fun updateRate(card: Card, userId: UserId, rating: Rating): Card {
        val fsrs = FSRS(FSRSParameters())
        val userLearningState = card.learningState[userId]
        val fsrsCard = toFsrsCard(userLearningState)
        val now = timeService.getCurrentTimestamp()
        val log = fsrs.repeat(fsrsCard, now)
        val fsrsLearningState =
            log[rating]?.card ?: throw IllegalStateException("Rating not found in logs!")
        return cardsRepository.save(
            card.copy(
                learningState = card.learningState.copy(
                    usersLearningState = card.learningState.usersLearningState +
                            mapOf(userId to toLearningState(fsrsLearningState, now))
                )
            )
        )
    }

    private fun toFsrsCard(learningState: UserLearningState): FsrsCard =
        FsrsCard(
            due = learningState.due,
            elapsedDays = learningState.elapsedDays,
            scheduledDays = learningState.scheduledDays,
            reps = learningState.reps,
            lapses = learningState.lapses,
            state = learningState.state,
            stability = learningState.stability,
            difficulty = learningState.difficulty,
            lastReview = learningState.lastReview.orNull
        )

    private fun toLearningState(fsrsCard: FsrsCard, now: OffsetDateTime): UserLearningState =
        UserLearningState(
            due = fsrsCard.due,
            elapsedDays = fsrsCard.elapsedDays,
            scheduledDays = fsrsCard.scheduledDays,
            reps = fsrsCard.reps,
            lapses = fsrsCard.lapses,
            state = fsrsCard.state,
            stability = fsrsCard.stability,
            difficulty = fsrsCard.difficulty,
            lastReview = Option.of(now)
        )

}