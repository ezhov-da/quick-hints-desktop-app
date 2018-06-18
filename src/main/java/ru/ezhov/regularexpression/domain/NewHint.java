package ru.ezhov.regularexpression.domain;

public class NewHint {
    private String text;
    private String description;

    public NewHint(String text, String description) {
        if (description == null || "".equals(description)) {
            throw new IllegalArgumentException("Description can't be null");
        }
        this.text = text;
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public String getDescription() {
        return description;
    }
}
