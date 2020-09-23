package ru.ezhov.notes.ui;

import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.domain.NewHint;
import ru.ezhov.notes.ui.event.domain.UiEvent;

public class TabSelectedUiEvent implements UiEvent {
    private HintId hintId;

    public TabSelectedUiEvent(HintId hintId) {
        this.hintId = hintId;
    }

    public HintId hintId() {
        return hintId;
    }
}
