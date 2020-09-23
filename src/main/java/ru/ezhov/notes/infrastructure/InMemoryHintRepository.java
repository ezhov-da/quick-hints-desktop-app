package ru.ezhov.notes.infrastructure;

import ru.ezhov.notes.domain._new.Hint;
import ru.ezhov.notes.domain._new.HintId;
import ru.ezhov.notes.domain._new.HintName;
import ru.ezhov.notes.domain._new.HintRepository;
import ru.ezhov.notes.domain._new.NewHint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryHintRepository implements HintRepository {
    private Map<HintId, Hint> map = new ConcurrentHashMap<>();

    @Override
    public List<Hint> all() throws Exception {
        return Collections.unmodifiableList(new ArrayList<>(map.values()));
    }

    @Override
    public void create(NewHint newHint) throws Exception {
        map.put(
                newHint.id(),
                Hint.create(
                        newHint.id(),
                        newHint.type(),
                        HintName.create(newHint.name().value())
                )
        );
    }

    @Override
    public void save(Hint hint) throws Exception {
        map.put(hint.id(), hint);
    }

    @Override
    public void delete(HintId hintId) throws Exception {
        map.remove(hintId);
    }
}
