package ru.ezhov.note.infrastructure.event;

import ru.ezhov.note.domain.NoteEventPublisher;

public class NoteEventPublisherFactory {
    public static NoteEventPublisher inMemory() {
        return InMemoryNoteEventPublisher.getInstance();
    }
}
