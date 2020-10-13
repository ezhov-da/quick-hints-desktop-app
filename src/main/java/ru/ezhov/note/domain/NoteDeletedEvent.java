package ru.ezhov.note.domain;

public class NoteDeletedEvent extends AbstractNoteEvent {
    public NoteDeletedEvent(NoteId id) {
        super(id);
    }
}
