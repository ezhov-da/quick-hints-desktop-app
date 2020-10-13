package ru.ezhov.note.ui.event.domain;

import java.util.List;

public interface UiEventSubscriber<T extends UiEvent> {
    void doOnEvent(T event);

    List<Class<T>> subscribeOn();
}
