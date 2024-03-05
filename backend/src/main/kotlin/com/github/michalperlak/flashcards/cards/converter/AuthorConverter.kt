package com.github.michalperlak.flashcards.cards.converter

import com.github.michalperlak.flashcards.cards.model.Author
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class AuthorConverter : Converter<String, Author> {
    override fun convert(source: String): Author = Author(name = source)
}