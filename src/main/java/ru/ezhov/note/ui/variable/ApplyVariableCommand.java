package ru.ezhov.note.ui.variable;

import ru.ezhov.note.ui.command.domain.UiCommand;
import ru.ezhov.note.variable.domain.Variable;

import java.util.Collections;
import java.util.List;

public class ApplyVariableCommand implements UiCommand {
    private List<Variable> variables;

    public ApplyVariableCommand(List<Variable> variables) {
        this.variables = Collections.unmodifiableList(variables);
    }

    public List<Variable> variable() {
        return variables;
    }
}
