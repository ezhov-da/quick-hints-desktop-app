package ru.ezhov.note.variable.domain;

public class VariableValue {
    private final String value;

    public VariableValue(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
