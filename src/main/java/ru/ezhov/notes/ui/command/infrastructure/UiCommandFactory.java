package ru.ezhov.notes.ui.command.infrastructure;

import ru.ezhov.notes.ui.command.domain.UiCommandPublisher;

public abstract class UiCommandFactory {
    private static InMemoryUiCommandPublisher inMemoryUiEventPublisher;

    public static synchronized UiCommandPublisher inMemory() {
        if (inMemoryUiEventPublisher == null) {
            inMemoryUiEventPublisher = new InMemoryUiCommandPublisher();
        }
        return inMemoryUiEventPublisher;
    }
}
