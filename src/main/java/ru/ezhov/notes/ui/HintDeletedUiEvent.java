package ru.ezhov.notes.ui;

import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.ui.event.domain.UiEvent;

public class HintDeletedUiEvent implements UiEvent {
    private HintId hintId;

    public HintDeletedUiEvent(HintId hintId) {
        this.hintId = hintId;
    }

    public HintId id() {
        return hintId;
    }
}
