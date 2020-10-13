package ru.ezhov.note.domain;

import java.util.List;

public interface NoteRepository {
    List<Note> all() throws HintsRepositoryException;

    List<Note> byCondition(String condition) throws HintsRepositoryException;

    void change(NoteId id, String text, String description) throws HintsRepositoryException;

    void add(NoteId noteId, NewNote newNote) throws HintsRepositoryException;

    void remove(NoteId id) throws HintsRepositoryException;
}
