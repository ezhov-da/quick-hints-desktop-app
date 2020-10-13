package ru.ezhov.note.domain;


public abstract class AbstractNoteEvent implements NoteEvent {
    public ru.ezhov.note.domain.NoteId id;

    public AbstractNoteEvent(NoteId id) {
        this.id = id;
    }
}
