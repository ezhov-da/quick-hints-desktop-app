package ru.ezhov.notes.domain._new;

import java.util.List;

public interface HintEventPublisher {
    <T extends HintEvent> void publish(List<T> events);

    <T extends HintEvent> void subscribe(HintEventSubscriber<T> hintEventSubscriber);

    <T extends HintEvent> void unsubscribe(HintEventSubscriber<T> hintEventSubscriber);
}
