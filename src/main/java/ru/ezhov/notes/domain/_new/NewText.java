package ru.ezhov.notes.domain._new;

public class NewText {
    private final String value;

    private NewText(String value) {
        this.value = value;
    }

    public static NewText create(String value) {
        return new NewText(value);
    }

    public String value() {
        return value;
    }
}
