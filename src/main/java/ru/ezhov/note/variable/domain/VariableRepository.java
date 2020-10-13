package ru.ezhov.note.variable.domain;

import java.util.Optional;

public interface VariableRepository {
    Optional<Variable> by(VariableName name);
}
