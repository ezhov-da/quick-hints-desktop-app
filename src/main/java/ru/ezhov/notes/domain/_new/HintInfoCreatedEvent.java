package ru.ezhov.notes.domain._new;

public class HintInfoCreatedEvent extends AbstractHintEvent {
    public HintInfoCreatedEvent(HintId id) {
        super(id);
    }
}
