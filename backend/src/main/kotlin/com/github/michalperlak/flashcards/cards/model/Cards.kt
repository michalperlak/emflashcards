package com.github.michalperlak.flashcards.cards.model

data class Cards(
    var all: MutableSet<Card> = mutableSetOf()
)