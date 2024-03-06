package com.github.michalperlak.flashcards.users.application

import com.github.michalperlak.flashcards.users.dto.NewUserDto
import com.github.michalperlak.flashcards.users.error.UsersError
import com.github.michalperlak.flashcards.users.model.User
import com.github.michalperlak.flashcards.users.model.UserId
import com.github.michalperlak.flashcards.users.repository.UsersRepository
import io.vavr.control.Either
import org.springframework.stereotype.Service

@Service
class UserManagementService(
    private val usersRepository: UsersRepository
) {
    fun createUser(newUser: NewUserDto): Either<UsersError, User> =
        usersRepository
            .getByName(newUser.name)
            .map { userAlreadyExists(it) }
            .getOrElse(createNewUser(newUser))

    private fun createNewUser(newUser: NewUserDto): Either<UsersError, User> {
        val user = User(
            id = UserId.generate(),
            name = newUser.name
        )
        return Either.right(
            usersRepository.addUser(user)
        )
    }

    private fun userAlreadyExists(user: User): Either<UsersError, User> =
        Either.left(
            UsersError("User with username: ${user.name} already exists!")
        )

    fun getAll(): List<UserId> = usersRepository
        .getAll()
        .map { it.id }
}