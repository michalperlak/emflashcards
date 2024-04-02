package com.github.michalperlak.flashcards.cards.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.Executors

@Configuration
class RepositoryConfig {

    @Bean
    fun cardsRepository(environment: Environment, mapper: ObjectMapper): CardsRepository {
        val file = Paths.get(environment.getRequiredProperty("cards.path"))
        val cards = if (Files.exists(file)) loadCards(file, mapper) else ConcurrentHashMap()
        return PersistentCardsRepository(
            cards = cards,
            ioExecutor = Executors.newSingleThreadExecutor(),
            file = file,
            mapper = mapper
        )
    }

    private fun loadCards(path: Path, mapper: ObjectMapper): ConcurrentMap<CardId, Card> =
        ConcurrentHashMap(
            Files.newInputStream(path)
                .use { mapper.readValue<Array<Card>>(path.toFile()) }.groupBy { it.id }
                .mapValues { it.value.first() }
        )


}