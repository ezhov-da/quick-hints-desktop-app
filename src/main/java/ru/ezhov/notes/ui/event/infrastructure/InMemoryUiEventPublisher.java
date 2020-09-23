package ru.ezhov.notes.ui.event.infrastructure;

import ru.ezhov.notes.ui.event.domain.UiEvent;
import ru.ezhov.notes.ui.event.domain.UiEventPublisher;
import ru.ezhov.notes.ui.event.domain.UiEventSubscriber;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class InMemoryUiEventPublisher implements UiEventPublisher {
    private List<UiEventSubscriber> sudscribers = new CopyOnWriteArrayList<>();


    @Override
    public void register(UiEventSubscriber subscriber) {
        sudscribers.add(subscriber);
    }

    @Override
    public void publish(UiEvent uiEvent) {
        for (UiEventSubscriber sudscriber : sudscribers) {
            final List<Class> list = sudscriber.subscribeOn();
            for (Class aClass : list) {
                if (uiEvent.getClass() == aClass) {
                    sudscriber.doOnEvent(uiEvent);
                }
            }
        }
    }
}
