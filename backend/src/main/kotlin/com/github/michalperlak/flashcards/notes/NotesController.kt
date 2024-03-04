package com.github.michalperlak.flashcards.notes

import com.github.michalperlak.flashcards.notes.dto.NewNoteDto
import com.github.michalperlak.flashcards.notes.dto.NoteDto
import com.github.michalperlak.flashcards.notes.model.Note
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/notes")
class NotesController(
    private val notesFacade: NotesFacade
) {

    @PostMapping
    fun createNote(@RequestBody newNote: NewNoteDto): ResponseEntity<*> =
        notesFacade.createNote(newNote)
            .map { toResponseEntity(it) }
            .getOrElseGet { ResponseEntity.badRequest().body(it) }

    private fun toResponseEntity(note: Note): ResponseEntity<*> =
        ResponseEntity.ok(NoteDto.from(note))
}