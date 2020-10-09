package ru.ezhov.notes.ui.search.event;

import ru.ezhov.notes.ui.event.domain.UiEvent;
import ru.ezhov.notes.util.search.Part;

public class SelectedPartUiEvent implements UiEvent {
    private Part part;

    public SelectedPartUiEvent(Part part) {
        this.part = part;
    }

    public Part part() {
        return part;
    }
}
