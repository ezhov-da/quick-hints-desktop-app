package ru.ezhov.note.ui.search.event;

import ru.ezhov.note.ui.event.domain.UiEvent;
import ru.ezhov.note.util.search.Part;

public class SelectedPartUiEvent implements UiEvent {
    private Part part;

    public SelectedPartUiEvent(Part part) {
        this.part = part;
    }

    public Part part() {
        return part;
    }
}
