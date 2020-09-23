package ru.ezhov.notes.ui.notespanel.event;

import ru.ezhov.notes.domain.NewHint;
import ru.ezhov.notes.ui.event.domain.UiEvent;

public class HintAddedUiEvent implements UiEvent {
    private NewHint newHint;

    public HintAddedUiEvent(NewHint newHint) {
        this.newHint = newHint;
    }

    public NewHint newHint() {
        return newHint;
    }
}
