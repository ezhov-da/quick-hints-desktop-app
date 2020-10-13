package ru.ezhov.note.ui.editor;

import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.event.domain.UiEvent;

public class NoteEditedUiEvent implements UiEvent {
    private NoteId id;
    private String name;
    private String text;

    public NoteEditedUiEvent(NoteId id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public NoteId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String text() {
        return text;
    }
}
