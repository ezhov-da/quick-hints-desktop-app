package ru.ezhov.notes.domain._new;

public abstract class AbstractHintEvent implements HintEvent {
    public HintId id;

    public AbstractHintEvent(HintId id) {
        this.id = id;
    }
}
