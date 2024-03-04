package com.github.michalperlak.flashcards.notes

import com.github.michalperlak.flashcards.notes.dto.NewNoteDto
import com.github.michalperlak.flashcards.notes.error.NotesError
import com.github.michalperlak.flashcards.notes.model.Note
import io.vavr.control.Either
import org.springframework.stereotype.Component

@Component
class NotesFacade {

    fun createNote(newNoteDto: NewNoteDto): Either<NotesError, Note> = TODO()
}