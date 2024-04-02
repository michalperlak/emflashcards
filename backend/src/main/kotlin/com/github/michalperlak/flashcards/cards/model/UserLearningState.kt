package com.github.michalperlak.flashcards.cards.model

import com.github.mpps.fsrs.model.State
import java.time.OffsetDateTime

data class UserLearningState(
    val due: OffsetDateTime,
    val elapsedDays: Long,
    val scheduledDays: Long,
    val reps: Long,
    val lapses: Long,
    val state: State,
    val stability: Double,
    val difficulty: Double,
    val lastReview: OffsetDateTime?
)