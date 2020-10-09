package ru.ezhov.notes.util.search;

public class Part {
    private int startPos;
    private int length;

    public Part(int startPos, int length) {
        this.startPos = startPos;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Part{" +
                "startPos=" + startPos +
                ", lenght=" + length +
                '}';
    }

    public int startPos() {
        return startPos;
    }

    public int length() {
        return length;
    }
}