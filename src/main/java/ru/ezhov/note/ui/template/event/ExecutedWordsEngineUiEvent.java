package ru.ezhov.note.ui.template.event;

import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.event.domain.UiEvent;

import java.util.Map;

public class ExecutedWordsEngineUiEvent implements UiEvent {
    private NoteId noteId;
    private Map<String, String> map;

    public ExecutedWordsEngineUiEvent(NoteId noteId, Map<String, String> map) {
        this.noteId = noteId;
        this.map = map;
    }

    public Map<String, String> map() {
        return map;
    }

    public NoteId id() {
        return noteId;
    }
}