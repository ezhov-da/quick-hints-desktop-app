package ru.ezhov.notes.domain._new;

import java.util.List;

public interface HintRepository {
    List<Hint> all() throws Exception;

    void create(NewHint newHint) throws Exception;

    void save(Hint hint) throws Exception;

    void delete(HintId hintId) throws Exception;
}
