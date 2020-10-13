package ru.ezhov.note.infrastructure.event;

import ru.ezhov.note.domain.NoteEvent;
import ru.ezhov.note.domain.NoteEventPublisher;
import ru.ezhov.note.domain.NoteEventSubscriber;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryNoteEventPublisher implements NoteEventPublisher {

    private static InMemoryNoteEventPublisher inMemoryHintEventPublisher;
    private List<NoteEventSubscriber> noteEventSubscribers = new CopyOnWriteArrayList<>();

    private InMemoryNoteEventPublisher() {
    }

    public static InMemoryNoteEventPublisher getInstance() {
        if (inMemoryHintEventPublisher == null) {
            inMemoryHintEventPublisher = new InMemoryNoteEventPublisher();
        }

        return inMemoryHintEventPublisher;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends NoteEvent> void publish(List<T> events) {
        for (NoteEventSubscriber s : noteEventSubscribers) {
            for (T e : events)
                if (s.subscribedToEventType() == e.getClass()) {
                    s.handleEvent(e);
                }
        }
    }

    @Override
    public <T extends NoteEvent> void subscribe(NoteEventSubscriber<T> noteEventSubscriber) {
        noteEventSubscribers.add(noteEventSubscriber);
    }

    @Override
    public <T extends NoteEvent> void unsubscribe(NoteEventSubscriber<T> noteEventSubscriber) {
        noteEventSubscribers.remove(noteEventSubscriber);
    }
}
