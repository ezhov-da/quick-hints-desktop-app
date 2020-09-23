package ru.ezhov.notes.ui.notespanel.event;

import ru.ezhov.notes.domain.Hint;
import ru.ezhov.notes.ui.event.domain.UiEvent;

public class HintSelectedUiEvent implements UiEvent {
    private Hint hint;

    public HintSelectedUiEvent(Hint hint) {
        this.hint = hint;
    }

    public Hint selectedHint() {
        return hint;
    }
}
