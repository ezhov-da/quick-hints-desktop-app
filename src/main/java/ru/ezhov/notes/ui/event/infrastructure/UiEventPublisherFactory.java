package ru.ezhov.notes.ui.event.infrastructure;

import ru.ezhov.notes.ui.event.domain.UiEventPublisher;

public abstract class UiEventPublisherFactory {
    private static InMemoryUiEventPublisher inMemoryUiEventPublisher;

    public static synchronized UiEventPublisher inMemory() {
        if (inMemoryUiEventPublisher == null) {
            inMemoryUiEventPublisher = new InMemoryUiEventPublisher();
        }
        return inMemoryUiEventPublisher;
    }
}
