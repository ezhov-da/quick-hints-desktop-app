package ru.ezhov.note.application;

import ru.ezhov.note.domain.Note;
import ru.ezhov.note.domain.NoteRepository;

import java.util.List;

public class NoteSearchApplicationService {
    private final NoteRepository noteRepository;

    NoteSearchApplicationService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> byCondition(String condition) throws Exception {
        return noteRepository.byCondition(condition);
    }
}
