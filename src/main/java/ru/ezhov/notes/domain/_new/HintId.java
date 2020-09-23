package ru.ezhov.notes.domain._new;

import java.util.Objects;
import java.util.UUID;

public class HintId {
    private String value;

    private HintId(String value) {
        this.value = value;
    }

    public static HintId generate() {
        return new HintId(UUID.randomUUID().toString());
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
