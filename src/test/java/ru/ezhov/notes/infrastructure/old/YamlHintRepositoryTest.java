package ru.ezhov.notes.infrastructure.old;

import org.junit.Test;
import ru.ezhov.notes.domain.NewHint;

import java.io.File;

public class YamlHintRepositoryTest {

    @Test
    public void add() throws Exception {
        YamlHintRepository yamlHintRepository = new YamlHintRepository(new File("."));

        yamlHintRepository.add(new NewHint("1", "2"));
    }
}