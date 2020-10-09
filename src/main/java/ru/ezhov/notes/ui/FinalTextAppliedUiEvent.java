package ru.ezhov.notes.ui;

import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.ui.event.domain.UiEvent;

public class FinalTextAppliedUiEvent implements UiEvent {
    private HintId hintId;
    private String text;

    public FinalTextAppliedUiEvent(HintId hintId, String text) {
        this.hintId = hintId;
        this.text = text;
    }

    public HintId hintId() {
        return hintId;
    }

    public String text() {
        return text;
    }
}
