package com.github.michalperlak.flashcards.cards.controller

import com.github.michalperlak.flashcards.cards.CardsFacade
import com.github.michalperlak.flashcards.cards.dto.*
import com.github.michalperlak.flashcards.cards.model.Author
import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import com.github.michalperlak.flashcards.cards.model.Note
import com.github.michalperlak.flashcards.users.model.User
import com.github.mpps.fsrs.model.State
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/cards")
class CardsController(
    private val cardsFacade: CardsFacade
) {

    @GetMapping
    fun getCardsForToday(
        @RequestParam(
            name = "states",
            required = false
        ) states: Set<State> = EnumSet.allOf(State::class.java),
        @RequestParam(name = "limit") limit: Int = Int.MAX_VALUE,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<List<CardDto>> =
        ResponseEntity.ok(
            cardsFacade
                .getForToday(userId(userDetails), states, limit)
                .map { CardDto.from(it) }
        )

    @PutMapping("/{cardId}")
    fun rateCard(
        @PathVariable cardId: CardId,
        @RequestBody rate: RateCardDto,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<*> =
        cardsFacade
            .rateCard(cardId, userId(userDetails), rate)
            .map { toResponseEntity(it) }
            .getOrElseGet { ResponseEntity.badRequest().body(it) }

    @PostMapping
    fun createCard(@RequestBody newCard: NewCardDto): ResponseEntity<*> =
        cardsFacade
            .createCard(newCard)
            .map { toResponseEntity(it) }
            .getOrElseGet { ResponseEntity.badRequest().body(it) }

    @DeleteMapping("/{cardId}")
    fun deleteCard(@PathVariable cardId: CardId): ResponseEntity<*> =
        cardsFacade
            .deleteCard(cardId)
            .map { toResponseEntity(cardId) }
            .getOrElseGet { ResponseEntity.badRequest().body(it) }

    @PostMapping("/{cardId}/notes")
    fun createNote(
        @PathVariable cardId: CardId,
        @RequestBody newNote: NewNoteDto,
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<*> =
        cardsFacade
            .createNote(Author.fromUserDetails(userDetails), cardId, newNote)
            .map { toResponseEntity(it) }
            .getOrElseGet { ResponseEntity.badRequest().body(it) }

    private fun userId(userDetails: UserDetails) =
        (userDetails as User).id

    private fun toResponseEntity(note: Note): ResponseEntity<*> =
        ResponseEntity.ok(NoteDto.from(note))

    private fun toResponseEntity(card: Card): ResponseEntity<*> =
        ResponseEntity.ok(CardDto.from(card))

    private fun toResponseEntity(cardId: CardId): ResponseEntity<*> = ResponseEntity.ok(cardId.id)

}