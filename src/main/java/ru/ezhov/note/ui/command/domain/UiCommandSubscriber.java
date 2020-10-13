package ru.ezhov.note.ui.command.domain;

import java.util.List;

public interface UiCommandSubscriber<T extends UiCommand> {
    void doOnCommand(T command);

    List<Class<T>> subscribeOn();
}
