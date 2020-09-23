package ru.ezhov.notes.infrastructure.event;

import ru.ezhov.notes.domain._new.HintEvent;
import ru.ezhov.notes.domain._new.HintEventPublisher;
import ru.ezhov.notes.domain._new.HintEventSubscriber;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryHintEventPublisher implements HintEventPublisher {

    private static InMemoryHintEventPublisher inMemoryHintEventPublisher;
    private List<HintEventSubscriber> hintEventSubscribers = new CopyOnWriteArrayList<>();

    private InMemoryHintEventPublisher() {
    }

    public static InMemoryHintEventPublisher getInstance() {
        if (inMemoryHintEventPublisher == null) {
            inMemoryHintEventPublisher = new InMemoryHintEventPublisher();
        }

        return inMemoryHintEventPublisher;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends HintEvent> void publish(List<T> events) {
        for (HintEventSubscriber s : hintEventSubscribers) {
            for (T e : events)
                if (s.subscribedToEventType() == e.getClass()) {
                    s.handleEvent(e);
                }
        }
    }

    @Override
    public <T extends HintEvent> void subscribe(HintEventSubscriber<T> hintEventSubscriber) {
        hintEventSubscribers.add(hintEventSubscriber);
    }

    @Override
    public <T extends HintEvent> void unsubscribe(HintEventSubscriber<T> hintEventSubscriber) {
        hintEventSubscribers.remove(hintEventSubscriber);
    }
}
