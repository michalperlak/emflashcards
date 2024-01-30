package com.github.michalperlak.flashcards.cards

import com.github.michalperlak.flashcards.cards.dto.CardDto
import com.github.michalperlak.flashcards.cards.dto.NewCardDto
import com.github.michalperlak.flashcards.cards.model.Card
import com.github.michalperlak.flashcards.cards.model.CardId
import com.github.michalperlak.flashcards.users.UserId
import com.github.mpps.fsrs.model.State
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cards")
class CardsController(
    private val cardsService: CardsService
) {

    @GetMapping
    fun getForReview(
        @RequestParam(name = "userId") userId: UserId,
        @RequestParam(name = "states") states: Set<State>,
        @RequestParam(name = "limit") limit: Int = Int.MAX_VALUE
    ): ResponseEntity<List<CardDto>> =
        ResponseEntity.ok(
            cardsService
                .getForToday(states, limit)
                .map { CardDto.from(it) }
        )


    @GetMapping("/{id}")
    fun getCard(@PathVariable(name = "id") id: CardId): ResponseEntity<CardDto> =
        cardsService
            .getById(id)
            .map { toResponseEntity(it) }
            .getOrElse { ResponseEntity.notFound().build() }

    @PostMapping
    fun createCard(@RequestBody newCard: NewCardDto): ResponseEntity<*> =
        cardsService
            .createCard(newCard)
            .map { toResponseEntity(it) as ResponseEntity<*> }
            .getOrElseGet { ResponseEntity.badRequest().body(it) }

    private fun toResponseEntity(it: Card) = ResponseEntity.ok(CardDto.from(it))

}