package com.github.michalperlak.flashcards.time

import org.springframework.stereotype.Service
import java.time.Clock
import java.time.OffsetDateTime

@Service
class TimeService(
    private val clock: Clock
) {
    fun getCurrentTimestamp(): OffsetDateTime = OffsetDateTime.now(clock)
}