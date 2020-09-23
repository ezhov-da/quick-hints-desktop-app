package ru.ezhov.notes.domain._new;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HintService {
    private final HintRepository hintRepository;
    private final HintTextRepository hintTextRepository;

    public HintService(HintRepository hintRepository, HintTextRepository hintTextRepository) {
        this.hintRepository = hintRepository;
        this.hintTextRepository = hintTextRepository;
    }

    public List<HintEvent> createHint(NewHint newHint) throws Exception {
        hintRepository.create(newHint);
        hintTextRepository.create(newHint.id(), newHint.text());

        return Collections.unmodifiableList(
                Arrays.asList(
                        new HintCreatedEvent(newHint.id()),
                        new HintTextCreatedEvent(newHint.id())
                )
        );
    }

    public List<HintEvent> deleteHint(HintId id) throws Exception {
        hintRepository.delete(id);
        hintTextRepository.delete(id);

        return Collections.singletonList(new HintDeletedEvent(id));
    }
}
