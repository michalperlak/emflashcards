package com.github.michalperlak.flashcards.cards.application

import com.github.michalperlak.flashcards.cards.dto.NewCardDto
import com.github.michalperlak.flashcards.cards.error.CardsError
import com.github.michalperlak.flashcards.cards.model.*
import com.github.michalperlak.flashcards.cards.repository.CardsRepository
import com.github.michalperlak.flashcards.time.TimeService
import com.github.michalperlak.flashcards.users.UsersFacade
import io.vavr.control.Either
import io.vavr.control.Option
import org.springframework.stereotype.Service
import java.time.OffsetDateTime
import com.github.mpps.fsrs.model.Card as FsrsCard

@Service
class CardsManagementService(
    private val timeService: TimeService,
    private val usersFacade: UsersFacade,
    private val cardsRepository: CardsRepository
) {
    fun createCard(newCard: NewCardDto): Either<CardsError, Card> =
        Either.right(
            cardsRepository.save(
                createNewCard(newCard, timeService.getCurrentTimestamp())
            )
        )

    fun deleteCard(cardId: CardId): Either<CardsError, Card> =
        cardsRepository
            .getCard(cardId)
            .toEither { CardsError("Card with id: $cardId does not exist!") }
            .map { cardsRepository.delete(it) }

    private fun createNewCard(newCard: NewCardDto, now: OffsetDateTime): Card =
        Card(
            id = CardId.generate(),
            category = newCard.category,
            question = Question(newCard.question),
            answer = Answer(newCard.answer),
            notes = listOf(),
            created = now,
            learningState = newLearningState(now)
        )

    private fun newLearningState(now: OffsetDateTime): LearningState {
        val startLearningState = toLearningState(FsrsCard.createEmpty(now))
        val learningStates = usersFacade
            .getAll()
            .map { it to startLearningState }
            .toMap()
        return LearningState(learningStates)
    }

    private fun toLearningState(fsrsCard: FsrsCard): UserLearningState =
        UserLearningState(
            due = fsrsCard.due,
            elapsedDays = fsrsCard.elapsedDays,
            scheduledDays = fsrsCard.scheduledDays,
            reps = fsrsCard.reps,
            lapses = fsrsCard.lapses,
            state = fsrsCard.state,
            stability = fsrsCard.stability,
            difficulty = fsrsCard.difficulty,
            lastReview = Option.none()
        )
}