package ru.ezhov.note.ui.search.event;

import ru.ezhov.note.ui.event.domain.UiEvent;
import ru.ezhov.note.util.search.Part;

import java.util.Collections;
import java.util.List;

public class FindedPartsUiEvent implements UiEvent {
    private List<Part> parts;

    public FindedPartsUiEvent(List<Part> parts) {
        this.parts = Collections.unmodifiableList(parts);
    }

    public List<Part> parts() {
        return parts;
    }
}
