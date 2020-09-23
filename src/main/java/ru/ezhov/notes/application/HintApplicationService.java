package ru.ezhov.notes.application;

import ru.ezhov.notes.domain._new.Hint;
import ru.ezhov.notes.domain._new.HintEventPublisher;
import ru.ezhov.notes.domain._new.HintId;
import ru.ezhov.notes.domain._new.HintRepository;
import ru.ezhov.notes.domain._new.HintService;
import ru.ezhov.notes.domain._new.HintTextRepository;
import ru.ezhov.notes.domain._new.HintType;
import ru.ezhov.notes.domain._new.NewHint;
import ru.ezhov.notes.domain._new.NewName;
import ru.ezhov.notes.domain._new.NewText;

import java.util.List;

public class HintApplicationService {
    private HintEventPublisher hintEventPublisher;
    private final HintRepository hintRepository;
    private final HintTextRepository hintTextRepository;

    public HintApplicationService(
            HintEventPublisher hintEventPublisher,
            HintRepository hintRepository,
            HintTextRepository hintTextRepository
    ) {
        this.hintEventPublisher = hintEventPublisher;
        this.hintRepository = hintRepository;
        this.hintTextRepository = hintTextRepository;
    }

    public List<Hint> all() throws Exception {
        return hintRepository.all();
    }

    public void create(String name, HintType hintType, String text) throws Exception {
        HintService hintService = new HintService(hintRepository, hintTextRepository);
        hintEventPublisher.publish(
                hintService.createHint(
                        NewHint.create(
                                HintId.generate(),
                                hintType,
                                NewName.create(name),
                                NewText.create(text)
                        )
                )
        );
    }

    public void update(HintId id, HintType hintType, String name, String text) {
        HintService hintService = new HintService(hintRepository, hintTextRepository);
//        hintService.createHint(
//                NewHint.create(
//                        HintId.generate(),
//                        hintType,
//                        NewName.create(name),
//                        NewText.create(text)
//                )
//        );
    }
}
