package ru.ezhov.notes.domain._new;

public class HintCreatedEvent extends AbstractHintEvent {
    public HintCreatedEvent(HintId id) {
        super(id);
    }
}
