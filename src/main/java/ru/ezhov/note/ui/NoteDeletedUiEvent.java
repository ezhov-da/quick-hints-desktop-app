package ru.ezhov.note.ui;

import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.event.domain.UiEvent;

public class NoteDeletedUiEvent implements UiEvent {
    private NoteId noteId;

    public NoteDeletedUiEvent(NoteId noteId) {
        this.noteId = noteId;
    }

    public NoteId id() {
        return noteId;
    }
}
