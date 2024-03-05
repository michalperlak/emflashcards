package com.github.michalperlak.flashcards.users

import com.github.michalperlak.flashcards.users.application.UserManagementService
import com.github.michalperlak.flashcards.users.dto.NewUserDto
import com.github.michalperlak.flashcards.users.error.UsersError
import com.github.michalperlak.flashcards.users.model.User
import io.vavr.control.Either
import org.springframework.stereotype.Component

@Component
class UsersFacade(
    private val userManagementService: UserManagementService
) {

    fun createUser(newUser: NewUserDto): Either<UsersError, User> = userManagementService.createUser(newUser)
}