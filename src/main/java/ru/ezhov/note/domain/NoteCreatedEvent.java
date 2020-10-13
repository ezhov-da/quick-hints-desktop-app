package ru.ezhov.note.domain;

public class NoteCreatedEvent extends AbstractNoteEvent {
    public NoteCreatedEvent(NoteId id) {
        super(id);
    }
}
