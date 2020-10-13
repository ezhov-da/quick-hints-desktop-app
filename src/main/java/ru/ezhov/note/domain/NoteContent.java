package ru.ezhov.note.domain;

public class NoteContent {
    private String value;

    public NoteContent(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
