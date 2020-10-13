package ru.ezhov.note.domain;

import java.util.Collections;
import java.util.List;

public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteEvent> createHint(NewNote newNote) throws Exception {
        NoteId noteId = NoteId.generate();
        noteRepository.add(noteId, newNote);

        return Collections.singletonList(new NoteCreatedEvent(noteId));
    }

    public List<NoteEvent> remove(NoteId id) throws Exception {
        noteRepository.remove(id);
        return Collections.singletonList(new NoteDeletedEvent(id));
    }

    public void change(NoteId id, String text, String description) throws Exception{
        noteRepository.change(id, text, description);
    }
}
