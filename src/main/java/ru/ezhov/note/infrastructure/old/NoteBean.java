package ru.ezhov.note.infrastructure.old;

import ru.ezhov.note.domain.Note;
import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.domain.NoteName;
import ru.ezhov.note.domain.NoteOriginalText;

public class NoteBean {
    private String id;
    private String name;
    private String text;

    NoteBean() {
    }

    public NoteBean(String id, String name, String text) {
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

    Note from() {
        return new Note(new NoteId(id), new NoteName(name), new NoteOriginalText(text));
    }
}
