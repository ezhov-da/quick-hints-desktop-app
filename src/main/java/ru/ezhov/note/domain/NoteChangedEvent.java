package ru.ezhov.note.domain;

public class NoteChangedEvent extends AbstractNoteEvent {
    public NoteChangedEvent(NoteId id) {
        super(id);
    }
}
