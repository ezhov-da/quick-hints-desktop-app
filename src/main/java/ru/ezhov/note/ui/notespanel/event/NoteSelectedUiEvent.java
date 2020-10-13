package ru.ezhov.note.ui.notespanel.event;

import ru.ezhov.note.domain.Note;
import ru.ezhov.note.ui.event.domain.UiEvent;

public class NoteSelectedUiEvent implements UiEvent {
    private Note note;

    public NoteSelectedUiEvent(Note note) {
        this.note = note;
    }

    public Note selectedNote() {
        return note;
    }
}
