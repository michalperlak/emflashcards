package com.github.michalperlak.flashcards.cards.model

import org.springframework.security.core.userdetails.UserDetails

data class Author(
    val name: String
) {
    companion object {
        fun fromUserDetails(userDetails: UserDetails): Author =
            Author(name = userDetails.username)
    }
}