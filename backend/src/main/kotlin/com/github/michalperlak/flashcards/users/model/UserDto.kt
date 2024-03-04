package com.github.michalperlak.flashcards.users.model

class UserDto {

    companion object {
        fun from(user: User): UserDto = UserDto()
    }
}
