package ru.ezhov.notes.ui.command.domain;

public interface UiCommandPublisher {
    void register(UiCommandSubscriber sudscriber);

    void publish(UiCommand event);
}
