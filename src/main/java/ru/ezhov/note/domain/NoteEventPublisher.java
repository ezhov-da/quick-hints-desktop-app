package ru.ezhov.note.domain;

import java.util.List;

public interface NoteEventPublisher {
    <T extends NoteEvent> void publish(List<T> events);

    <T extends NoteEvent> void subscribe(NoteEventSubscriber<T> noteEventSubscriber);

    <T extends NoteEvent> void unsubscribe(NoteEventSubscriber<T> noteEventSubscriber);
}
