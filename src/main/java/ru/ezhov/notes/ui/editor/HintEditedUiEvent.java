package ru.ezhov.notes.ui.editor;

import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.ui.event.domain.UiEvent;

public class HintEditedUiEvent implements UiEvent {
    private HintId id;
    private String name;
    private String text;

    public HintEditedUiEvent(HintId id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public HintId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String text() {
        return text;
    }
}
