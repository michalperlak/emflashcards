package com.github.michalperlak.flashcards.users.repository

import com.github.michalperlak.flashcards.users.model.User
import com.github.michalperlak.flashcards.users.model.UserId
import io.vavr.control.Option
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Repository
class UsersRepository(
    private val users: ConcurrentMap<UserId, User> = ConcurrentHashMap()
) {

    fun getByName(username: String): Option<User> =
        Option.ofOptional(
            users.values.stream()
                .filter { it.name == username }
                .findFirst()
        )

    fun addUser(user: User): User = user.apply { users[id] = this }
}