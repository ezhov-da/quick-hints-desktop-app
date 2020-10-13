package ru.ezhov.note.variable.domain;

import ru.ezhov.note.domain.NoteId;

import java.util.Objects;

public class Variable {
    private NoteId noteId;
    private VariableName name;
    private VariableValue value;

    public Variable(NoteId noteId, VariableName name, VariableValue value) {
        this.noteId = noteId;
        this.name = name;
        this.value = value;
    }

    public NoteId noteId() {
        return noteId;
    }

    public VariableName name() {
        return name;
    }

    public VariableValue value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(noteId, variable.noteId) &&
                Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noteId, name);
    }
}
