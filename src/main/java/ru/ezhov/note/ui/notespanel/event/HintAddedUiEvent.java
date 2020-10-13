package ru.ezhov.note.ui.notespanel.event;

import ru.ezhov.note.domain.NewNote;
import ru.ezhov.note.ui.event.domain.UiEvent;

public class HintAddedUiEvent implements UiEvent {
    private NewNote newNote;

    public HintAddedUiEvent(NewNote newNote) {
        this.newNote = newNote;
    }

    public NewNote newHint() {
        return newNote;
    }
}
