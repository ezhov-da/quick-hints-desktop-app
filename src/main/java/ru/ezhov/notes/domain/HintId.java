package ru.ezhov.notes.domain;

import java.util.Objects;

public class HintId {
    private String value;

    public HintId(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HintId hintId = (HintId) o;
        return Objects.equals(value, hintId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
