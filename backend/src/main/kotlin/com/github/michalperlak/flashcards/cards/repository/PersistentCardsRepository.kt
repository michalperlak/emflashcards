package com.github.michalperlak.flashcards.cards.repository

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import io.vavr.control.Option
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.Executor


class PersistentCardsRepository(
    private val cards: ConcurrentMap<CardId, Card>,
    private val ioExecutor: Executor,
    private val file: Path,
    private val mapper: ObjectMapper
) : CardsRepository {

    override fun getCard(cardId: CardId): Option<Card> = Option.of(cards[cardId])

    override fun save(card: Card): Card = card.apply {
        cards[card.id] = card
        store()
    }

    override fun delete(card: Card): Card = card.apply {
        cards.remove(card.id)
        store()
    }

    override fun getAll(): List<Card> = cards.values.toList()

    private fun store() {
        ioExecutor.execute {
            Files.write(
                file,
                mapper.writeValueAsBytes(cards.values), StandardOpenOption.CREATE
            )
        }
    }
}