package ru.ezhov.notes.ui.template.event;

import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.ui.event.domain.UiEvent;

import java.util.Map;

public class ExecutedWordsEngineUiEvent implements UiEvent {
    private HintId hintId;
    private Map<String, String> map;

    public ExecutedWordsEngineUiEvent(HintId hintId, Map<String, String> map) {
        this.hintId = hintId;
        this.map = map;
    }

    public Map<String, String> map() {
        return map;
    }

    public HintId hintId() {
        return hintId;
    }
}