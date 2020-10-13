package ru.ezhov.note.application;

import ru.ezhov.note.domain.NoteContentReaderFactory;
import ru.ezhov.note.domain.NoteEventPublisher;
import ru.ezhov.note.domain.NoteRepository;
import ru.ezhov.note.template.domain.Engine;

public class NoteApplicationServiceFactory {
    private final NoteEventPublisher noteEventPublisher;
    private final NoteRepository noteRepository;
    private final NoteContentReaderFactory noteContentReaderFactory;
    private final Engine engine;

    public NoteApplicationServiceFactory(
            NoteEventPublisher noteEventPublisher,
            NoteRepository noteRepository,
            NoteContentReaderFactory noteContentReaderFactory,
            Engine engine
    ) {
        this.noteEventPublisher = noteEventPublisher;
        this.noteRepository = noteRepository;
        this.noteContentReaderFactory = noteContentReaderFactory;
        this.engine = engine;
    }

    public NoteApplicationService commonService() {
        return new NoteApplicationService(noteEventPublisher, noteRepository);
    }

    public NoteContentApplicationService contentService() {
        return new NoteContentApplicationService(noteContentReaderFactory);
    }

    public NoteSearchApplicationService searchService() {
        return new NoteSearchApplicationService(noteRepository);
    }

    public Engine engine() {
        return engine;
    }
}
