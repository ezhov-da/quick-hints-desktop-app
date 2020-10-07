package ru.ezhov.notes.domain;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

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

    public HintType type() {
        HintType type = HintType.TEXT;
        final String[] split = text.value().split("\n");
        if (split.length > 0) {
            String firstRow = split[0];
            try {
                URL u = new URL(firstRow);
                u.toURI();
                type = HintType.URL;
            } catch (MalformedURLException | URISyntaxException e) {
                //no matter
            }
        }
        return type;
    }

    @Override
    public String toString() {
        return name.value();
    }
}
