package com.github.michalperlak.flashcards.cards.model

import com.github.mpps.fsrs.model.State
import io.vavr.control.Option
import java.time.OffsetDateTime

data class UserLearningState(
    var due: OffsetDateTime,
    var elapsedDays: Long,
    var scheduledDays: Long,
    var reps: Long,
    var lapses: Long,
    var state: State,
    var stability: Double,
    var difficulty: Double,
    var lastReview: Option<OffsetDateTime>
)