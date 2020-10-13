package ru.ezhov.note.ui;

import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.command.domain.UiCommand;
import ru.ezhov.note.variable.domain.Variable;

import java.util.Collections;
import java.util.List;

public class ApplyVariableToSelectedTabCommand implements UiCommand {
    private NoteId noteId;
    private List<Variable> variables;

    public ApplyVariableToSelectedTabCommand(NoteId noteId, List<Variable> variables) {
        this.noteId = noteId;
        this.variables = Collections.unmodifiableList(variables);
    }

    public List<Variable> variable() {
        return variables;
    }

    public NoteId noteId() {
        return noteId;
    }
}
