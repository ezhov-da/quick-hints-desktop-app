package ru.ezhov.note.application;

import ru.ezhov.note.domain.Note;
import ru.ezhov.note.domain.NoteEventPublisher;
import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.domain.NoteRepository;
import ru.ezhov.note.domain.NoteService;
import ru.ezhov.note.domain.NewNote;

import java.util.List;

public class NoteApplicationService {
    private NoteEventPublisher noteEventPublisher;
    private final NoteRepository noteRepository;

    NoteApplicationService(
            NoteEventPublisher noteEventPublisher,
            NoteRepository noteRepository
    ) {
        this.noteEventPublisher = noteEventPublisher;
        this.noteRepository = noteRepository;
    }

    public List<Note> all() throws Exception {
        return noteRepository.all();
    }

    public void create(String name, String text) throws Exception {
        NoteService noteService = new NoteService(noteRepository);
        noteEventPublisher.publish(
                noteService.createHint(new NewNote(name, text))
        );
    }

    public void change(NoteId id, String name, String text) throws Exception {
        NoteService noteService = new NoteService(noteRepository);
        noteService.change(id, name, text);
    }

    public void remove(NoteId id) throws Exception {
        NoteService noteService = new NoteService(noteRepository);
        noteService.remove(id);
    }
}
