package ru.ezhov.notes.domain;

import java.util.List;

public interface HintRepository {
    List<Hint> all() throws HintsRepositoryException;

    List<Hint> byCondition(String condition) throws HintsRepositoryException;

    void change(HintId id, String text, String description) throws HintsRepositoryException;

    void add(NewHint newHint) throws HintsRepositoryException;

    void remove(HintId id) throws HintsRepositoryException;
}
