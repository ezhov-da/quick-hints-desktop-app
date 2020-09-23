package ru.ezhov.notes.domain._new;

public interface HintTextRepository {
    void create(HintId hintId, NewText text);

    HintText by(HintId hintId);

    void delete(HintId id);
}
