package ru.ezhov.note.variable.infrastructure;

import ru.ezhov.note.variable.domain.Variable;
import ru.ezhov.note.variable.domain.VariableName;
import ru.ezhov.note.variable.domain.VariableRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryVariableRepository implements VariableRepository {
    private Map<VariableName, Variable> map = new ConcurrentHashMap<>();

    @Override
    public Optional<Variable> by(VariableName name) {
        return Optional.ofNullable(map.get(name));
    }
}
