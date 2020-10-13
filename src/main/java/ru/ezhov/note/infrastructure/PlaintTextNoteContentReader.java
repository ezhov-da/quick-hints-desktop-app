package ru.ezhov.note.infrastructure;

import ru.ezhov.note.domain.Note;
import ru.ezhov.note.domain.NoteContent;
import ru.ezhov.note.domain.NoteContentReader;

public class PlaintTextNoteContentReader implements NoteContentReader {
    @Override
    public NoteContent read(Note note) {
        return new NoteContent(note.text().value());
    }
}
