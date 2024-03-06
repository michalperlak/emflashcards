package com.github.michalperlak.flashcards.cards.model

import com.github.michalperlak.flashcards.users.model.UserId

data class LearningState(
    val usersLearningState: Map<UserId, UserLearningState>
) {
    operator fun get(userId: UserId): UserLearningState = usersLearningState[userId]
        ?: throw IllegalArgumentException("Learning state for user not found!")
}