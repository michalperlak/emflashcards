package com.github.michalperlak.flashcards

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmFlashcardsApplication

fun main(args: Array<String>) {
	runApplication<EmFlashcardsApplication>(*args)
}
