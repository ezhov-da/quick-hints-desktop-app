package ru.ezhov.notes.domain._new;

public class HintName {
    private String value;

    private HintName(String value) {
        this.value = value;
    }

    public static HintName create(String value) {
        return new HintName(value);
    }
}
