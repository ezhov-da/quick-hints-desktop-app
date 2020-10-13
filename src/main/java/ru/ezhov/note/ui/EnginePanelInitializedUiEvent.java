package ru.ezhov.note.ui;

import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.event.domain.UiEvent;

import java.util.List;

public class EnginePanelInitializedUiEvent implements UiEvent {
    private NoteId noteId;
    private List<String> words;

    public EnginePanelInitializedUiEvent(NoteId noteId, List<String> words) {
        this.noteId = noteId;
        this.words = words;
    }

    public NoteId noteId() {
        return noteId;
    }

    public List<String> words() {
        return words;
    }
}
