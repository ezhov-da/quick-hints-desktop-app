package ru.ezhov.notes.domain._new;

public class HintDeletedEvent extends AbstractHintEvent {
    public HintDeletedEvent(HintId id) {
        super(id);
    }
}
