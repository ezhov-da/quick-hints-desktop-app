package ru.ezhov.note.domain;

public interface NoteContentReader {
    NoteContent read(Note note) throws NoteContentReaderException;
}
