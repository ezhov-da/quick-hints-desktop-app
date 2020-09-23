package ru.ezhov.notes.domain._new;

public class NewName {
    private final String value;

    private NewName(String value) {
        this.value = value;
    }

    public static NewName create(String value) {
        return new NewName(value);
    }

    public String value() {
        return value;
    }
}
