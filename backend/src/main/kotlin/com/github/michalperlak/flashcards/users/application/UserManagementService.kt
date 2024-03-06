package com.github.michalperlak.flashcards.users.application

import com.github.michalperlak.flashcards.users.dto.NewUserDto
import com.github.michalperlak.flashcards.users.error.UsersError
import com.github.michalperlak.flashcards.users.model.User
import com.github.michalperlak.flashcards.users.model.UserId
import com.github.michalperlak.flashcards.users.repository.UsersRepository
import io.vavr.control.Either
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserManagementService(
    private val usersRepository: UsersRepository
) : UserDetailsService {
    fun createUser(newUser: NewUserDto): Either<UsersError, User> =
        usersRepository
            .getByName(newUser.name)
            .map { userAlreadyExists(it) }
            .getOrElse(createNewUser(newUser))

    fun getAll(): List<UserId> = usersRepository
        .getAll()
        .map { it.id }

    override fun loadUserByUsername(username: String?): UserDetails =
        username?.let { usersRepository.getByName(it) }?.orNull
            ?: throw IllegalArgumentException("User with username: $username not found")

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
}