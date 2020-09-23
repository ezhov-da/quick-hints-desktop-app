package ru.ezhov.notes.domain._new;

public interface HintEventSubscriber<T extends HintEvent> {
    void handleEvent(T event);

    Class<T> subscribedToEventType();
}
