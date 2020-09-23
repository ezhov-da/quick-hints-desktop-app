package ru.ezhov.notes.domain._new;

public class Hint {
    private final HintId id;
    private final HintType type;
    private final HintName name;

    private Hint(HintId id, HintType type, HintName name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Hint change(HintType type, HintName name) {
        return new Hint(id, type, name);
    }

    public static Hint create(HintId id, HintType type, HintName name) {
        return new Hint(id, type, name);
    }

    public HintId id() {
        return id;
    }

    public HintName name() {
        return name;
    }
}
