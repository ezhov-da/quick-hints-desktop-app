package ru.ezhov.note.ui.command.infrastructure;

import ru.ezhov.note.ui.command.domain.UiCommand;
import ru.ezhov.note.ui.command.domain.UiCommandPublisher;
import ru.ezhov.note.ui.command.domain.UiCommandSubscriber;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class InMemoryUiCommandPublisher implements UiCommandPublisher {
    private List<UiCommandSubscriber> sudscribers = new CopyOnWriteArrayList<>();


    @Override
    public void register(UiCommandSubscriber subscriber) {
        sudscribers.add(subscriber);
    }

    @Override
    public void publish(UiCommand uiEvent) {
        for (UiCommandSubscriber sudscriber : sudscribers) {
            final List<Class> list = sudscriber.subscribeOn();
            for (Class aClass : list) {
                if (uiEvent.getClass() == aClass) {
                    sudscriber.doOnCommand(uiEvent);
                }
            }
        }
    }
}
