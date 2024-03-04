package com.github.michalperlak.flashcards.users.converter

import com.github.michalperlak.flashcards.users.model.UserId
import org.springframework.core.convert.converter.Converter

class UserIdConverter : Converter<String, UserId> {
    override fun convert(source: String): UserId = UserId(source)
}