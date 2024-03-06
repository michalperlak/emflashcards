package com.github.michalperlak.flashcards.cards.application

import com.github.michalperlak.flashcards.cards.dto.RateCardDto
import com.github.michalperlak.flashcards.cards.error.CardsError
import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import com.github.michalperlak.flashcards.cards.model.LearningState
import com.github.michalperlak.flashcards.cards.repository.CardsRepository
import com.github.michalperlak.flashcards.time.TimeService
import com.github.mpps.fsrs.algorithm.FSRS
import com.github.mpps.fsrs.model.Rating
import io.vavr.control.Either
import io.vavr.control.Option
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import com.github.mpps.fsrs.model.Card as FsrsCard

fun main() {
    val fsrs = FSRS()
    val now = OffsetDateTime.now()
    val fsrsCard = FsrsCard.createEmpty(now.minusDays(2))
    val log = fsrs.repeat(fsrsCard, now)
    println(log[Rating.Hard])
    println(log[Rating.Good])
    println(log[Rating.Again])
    println(log[Rating.Easy])
}

@Service
class CardsRatingService(
    private val timeService: TimeService,
    private val cardsRepository: CardsRepository
) {
    fun rateCard(cardId: CardId, rate: RateCardDto): Either<CardsError, Card> =
        cardsRepository
            .getCard(cardId)
            .toEither { CardsError("Card for id: $cardId not found!") }
            .map { updateRate(it, rate.rating) }

    private fun updateRate(card: Card, rating: Rating): Card {
        val fsrs = FSRS()
        val fsrsCard = toFsrsCard(card)
        val now = timeService.getCurrentTimestamp()
        val log = fsrs.repeat(fsrsCard, now)
        val fsrsLearningState =
            log[rating]?.card ?: throw IllegalStateException("Rating not found in logs!")
        return cardsRepository.save(
            card.copy(learningState = toLearningState(fsrsLearningState, now))
        )
    }

    private fun toFsrsCard(card: Card): FsrsCard =
        FsrsCard(
            due = card.learningState.due,
            elapsedDays = card.learningState.elapsedDays,
            scheduledDays = card.learningState.scheduledDays,
            reps = card.learningState.reps,
            lapses = card.learningState.lapses,
            state = card.learningState.state,
            stability = card.learningState.stability,
            difficulty = card.learningState.difficulty,
            lastReview = card.learningState.lastReview.orNull
        )

    private fun toLearningState(fsrsCard: FsrsCard, now: OffsetDateTime): LearningState =
        LearningState(
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