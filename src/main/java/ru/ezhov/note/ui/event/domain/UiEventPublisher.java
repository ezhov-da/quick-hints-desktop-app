package ru.ezhov.note.ui.event.domain;

public interface UiEventPublisher {
    void register(UiEventSubscriber sudscriber);

    void publish(UiEvent event);
}
