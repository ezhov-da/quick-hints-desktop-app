package ru.ezhov.note.domain;

public interface NoteContentReaderFactory {
    NoteContentReader by(NoteType noteType) throws NoteContentReaderFactoryException;
}
