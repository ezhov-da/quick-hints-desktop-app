package ru.ezhov.note.application;

import ru.ezhov.note.domain.Note;
import ru.ezhov.note.domain.NoteContent;
import ru.ezhov.note.domain.NoteContentReader;
import ru.ezhov.note.domain.NoteContentReaderFactory;

public class NoteContentApplicationService {
    private NoteContentReaderFactory noteContentReaderFactory;

    NoteContentApplicationService(NoteContentReaderFactory noteContentReaderFactory) {
        this.noteContentReaderFactory = noteContentReaderFactory;
    }

    NoteContent by(Note note) throws Exception{
        final NoteContentReader reader = noteContentReaderFactory.by(note.type());
        return reader.read(note);
    }
}
