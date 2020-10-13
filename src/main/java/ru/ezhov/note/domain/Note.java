package ru.ezhov.note.domain;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class Note {
    private final NoteId id;
    private final NoteName name;
    private final NoteOriginalText text;

    public Note(NoteId id, NoteName name, NoteOriginalText text) {
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public NoteId id() {
        return id;
    }

    public NoteName name() {
        return name;
    }

    public NoteOriginalText text() {
        return text;
    }

    public NoteType type() {
        NoteType type = NoteType.TEXT;
        final String[] split = text.value().split("\n");
        if (split.length > 0) {
            String firstRow = split[0];
            try {
                URL u = new URL(firstRow);
                u.toURI();
                type = NoteType.URL;
            } catch (MalformedURLException | URISyntaxException e) {
                File file = new File(firstRow);
                if (file.exists()) type = NoteType.FILE;
            }
        }
        return type;
    }

    @Override
    public String toString() {
        return name.value();
    }
}
