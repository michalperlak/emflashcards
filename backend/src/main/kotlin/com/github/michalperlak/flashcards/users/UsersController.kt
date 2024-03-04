package com.github.michalperlak.flashcards.users

import com.github.michalperlak.flashcards.users.dto.NewUserDto
import com.github.michalperlak.flashcards.users.model.User
import com.github.michalperlak.flashcards.users.model.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UsersController(
    private val usersFacade: UsersFacade
) {

    @PostMapping
    fun createUser(@RequestBody newUser: NewUserDto): ResponseEntity<*> =
        usersFacade
            .createUser(newUser)
            .map { toResponseEntity(it) }
            .getOrElseGet { ResponseEntity.badRequest().body(it) }

    private fun toResponseEntity(user: User): ResponseEntity<*> =
        ResponseEntity.ok(UserDto.from(user))
}