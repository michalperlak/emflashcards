package com.github.michalperlak.flashcards.cards.dto

import com.github.michalperlak.flashcards.cards.model.LearningState
import com.github.mpps.fsrs.model.State
import java.time.OffsetDateTime

data class LearningStateDto(
    val due: OffsetDateTime,
    val elapsedDays: Long,
    val scheduledDays: Long,
    val reps: Long,
    val lapses: Long,
    val state: State,
    val stability: Double,
    val difficulty: Double,
    val lastReview: OffsetDateTime?
) {
    companion object {
        fun from(learningState: LearningState): LearningStateDto =
            LearningStateDto(
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
    }
}