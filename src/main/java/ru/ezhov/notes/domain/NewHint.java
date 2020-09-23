package ru.ezhov.notes.domain;

public class NewHint {
    private String name;
    private String text;

    public NewHint(String name, String text) {
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
