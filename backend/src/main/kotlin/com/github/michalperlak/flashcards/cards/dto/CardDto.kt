package com.github.michalperlak.flashcards.cards.dto

import com.github.michalperlak.flashcards.cards.model.Card

class CardDto {

    companion object {
        fun from(card: Card): CardDto = CardDto()
    }
}