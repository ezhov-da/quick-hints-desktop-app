package ru.ezhov.note.domain;

public class NoteOriginalText {
    private final String value;

    public NoteOriginalText(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
