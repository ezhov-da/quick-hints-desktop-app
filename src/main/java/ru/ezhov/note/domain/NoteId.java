package ru.ezhov.note.domain;

import java.util.Objects;
import java.util.UUID;

public class NoteId {
    private String value;

    public NoteId(String value) {
        this.value = value;
    }

    public static NoteId generate() {
        return new NoteId(UUID.randomUUID().toString());
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteId noteId = (NoteId) o;
        return Objects.equals(value, noteId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
