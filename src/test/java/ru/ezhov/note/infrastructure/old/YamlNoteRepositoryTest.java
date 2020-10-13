package ru.ezhov.note.infrastructure.old;

import org.junit.Test;
import ru.ezhov.note.domain.NewNote;
import ru.ezhov.note.domain.NoteId;

import java.io.File;

public class YamlNoteRepositoryTest {

    @Test
    public void add() throws Exception {
        YamlNoteRepository yamlHintRepository = new YamlNoteRepository(new File("."));

        yamlHintRepository.add(NoteId.generate(), new NewNote("1", "2"));
    }
}