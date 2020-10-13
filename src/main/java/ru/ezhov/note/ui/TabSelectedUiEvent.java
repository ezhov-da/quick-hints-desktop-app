package ru.ezhov.note.ui;

import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.event.domain.UiEvent;

public class TabSelectedUiEvent implements UiEvent {
    private NoteId noteId;

    public TabSelectedUiEvent(NoteId noteId) {
        this.noteId = noteId;
    }

    public NoteId hintId() {
        return noteId;
    }
}
