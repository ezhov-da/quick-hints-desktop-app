package ru.ezhov.notes.domain;

public class Hint {
    private HintId id;
    private HintName name;
    private HintText text;

    public Hint(HintId id, HintName name, HintText text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public HintId id() {
        return id;
    }


    public HintName name() {
        return name;
    }


    public HintText text() {
        return text;
    }

    @Override
    public String toString() {
        return name.value();
    }
}
