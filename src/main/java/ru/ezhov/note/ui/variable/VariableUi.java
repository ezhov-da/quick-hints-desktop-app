package ru.ezhov.note.ui.variable;

import ru.ezhov.note.variable.domain.Variable;

import java.util.Objects;

public class VariableUi {
    private Variable variable;

    public VariableUi(Variable variable) {
        this.variable = variable;
    }

    public Variable variable() {
        return variable;
    }

    @Override
    public String toString() {
        return variable.name().value() + " : " + variable.value().value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableUi that = (VariableUi) o;
        return Objects.equals(variable, that.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable);
    }
}
