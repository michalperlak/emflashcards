package com.github.michalperlak.flashcards.cards.repository

import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import com.github.michalperlak.flashcards.cards.model.Cards
import io.vavr.control.Option
import org.eclipse.store.integrations.spring.boot.types.concurrent.Read
import org.eclipse.store.integrations.spring.boot.types.concurrent.Write
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager
import org.springframework.stereotype.Repository

@Repository
class PersistentCardsRepository(
    private val embeddedStorageManager: EmbeddedStorageManager
) : CardsRepository {

    @Read
    override fun getCard(cardId: CardId): Option<Card> =
        Option.of(getCards().all.find { it.id == cardId })

    @Write
    override fun save(card: Card): Card = card.apply {
        val root = getCards()
        root.all.add(card)
        val storer = embeddedStorageManager.createEagerStorer()
        storer.store(root)
        storer.commit()
    }

    @Write
    override fun delete(card: Card): Card = card.apply {
        val root = getCards()
        root.all.remove(card)
        val storer = embeddedStorageManager.createEagerStorer()
        storer.store(root)
        storer.commit()
    }

    @Read
    override fun getAll(): List<Card> = getCards().all.toList()

    private fun getCards(): Cards = embeddedStorageManager.root() as Cards

}