package ru.ezhov.notes.infrastructure.event;

import ru.ezhov.notes.domain._new.HintEventPublisher;

public class HintEventPublisherFactory {
    public static HintEventPublisher inMemory() {
        return InMemoryHintEventPublisher.getInstance();
    }
}
