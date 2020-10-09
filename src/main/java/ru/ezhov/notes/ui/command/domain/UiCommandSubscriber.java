package ru.ezhov.notes.ui.command.domain;

import java.util.List;

public interface UiCommandSubscriber<T extends UiCommand> {
    void doOnCommand(T event);

    List<Class<T>> subscribeOn();
}
