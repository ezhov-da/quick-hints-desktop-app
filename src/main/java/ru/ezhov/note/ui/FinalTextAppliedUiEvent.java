package ru.ezhov.note.ui;

import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.event.domain.UiEvent;

public class FinalTextAppliedUiEvent implements UiEvent {
    private NoteId noteId;
    private String text;

    public FinalTextAppliedUiEvent(NoteId noteId, String text) {
        this.noteId = noteId;
        this.text = text;
    }

    public NoteId hintId() {
        return noteId;
    }

    public String text() {
        return text;
    }
}
