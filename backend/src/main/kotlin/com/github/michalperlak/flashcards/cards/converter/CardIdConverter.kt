package com.github.michalperlak.flashcards.cards.converter

import com.github.michalperlak.flashcards.cards.model.CardId
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CardIdConverter : Converter<String, CardId> {
    override fun convert(source: String): CardId = CardId(source)
}