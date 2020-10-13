package ru.ezhov.note.domain;

public class NewNote {
    private String name;
    private String text;

    public NewNote(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String name() {
        return name;
    }

    public String text() {
        return text;
    }
}
