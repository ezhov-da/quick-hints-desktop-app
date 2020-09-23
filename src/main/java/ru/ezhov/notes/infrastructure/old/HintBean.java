package ru.ezhov.notes.infrastructure.old;

import ru.ezhov.notes.domain.Hint;
import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.domain.HintName;
import ru.ezhov.notes.domain.HintText;

public class HintBean {
    private String id;
    private String name;
    private String text;

    HintBean() {
    }

    public HintBean(String id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    Hint from() {
        return new Hint(new HintId(id), new HintName(name), new HintText(text));
    }
}
