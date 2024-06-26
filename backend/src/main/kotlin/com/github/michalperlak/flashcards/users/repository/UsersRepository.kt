package com.github.michalperlak.flashcards.users.repository

import com.github.michalperlak.flashcards.users.UsersLoader
import com.github.michalperlak.flashcards.users.model.User
import com.github.michalperlak.flashcards.users.model.UserId
import io.vavr.control.Option
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Repository
class UsersRepository(
    usersLoader: UsersLoader,
    private val users: ConcurrentMap<UserId, User> = ConcurrentHashMap(
        usersLoader
            .load()
            .groupBy { it.userId }
            .mapValues { it.value.first() }
    )
) {

    fun getByName(username: String): Option<User> =
        Option.ofOptional(
            users.values.stream()
                .filter { it.name == username }
                .findFirst()
        )

    fun addUser(user: User): User = user.apply { users[userId] = this }
    fun getAll(): List<User> = users.values.toList()
}