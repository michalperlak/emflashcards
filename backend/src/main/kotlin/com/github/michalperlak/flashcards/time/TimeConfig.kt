package com.github.michalperlak.flashcards.time

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.ZoneId

@Configuration
class TimeConfig {

    @Bean
    fun clock(): Clock = Clock.system(ZoneId.of("Europe/Warsaw"))
}