package ru.ezhov.notes.domain._new;

import java.util.Objects;

public class NewHint {
    private HintId id;
    private HintType type;
    private NewName name;
    private NewText text;

    public NewHint(HintId id, HintType type, NewName name, NewText text) {
        Objects.requireNonNull(id, "id require non null");
        Objects.requireNonNull(name, "name require non null");
        Objects.requireNonNull(text, "text require non null");
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public static NewHint create(HintId id, HintType type, NewName name, NewText text) {
        return new NewHint(id, type, name, text);
    }

    public HintId id() {
        return id;
    }

    public NewName name() {
        return name;
    }

    public HintType type() {
        return type;
    }

    public NewText text() {
        return text;
    }
}
