package ru.ezhov.note.domain;

public interface NoteEventSubscriber<T extends NoteEvent> {
    void handleEvent(T event);

    Class<T> subscribedToEventType();
}
