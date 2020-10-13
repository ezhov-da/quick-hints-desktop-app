package ru.ezhov.note.infrastructure;

import ru.ezhov.note.domain.Note;
import ru.ezhov.note.domain.NoteContent;
import ru.ezhov.note.domain.NoteContentReader;
import ru.ezhov.note.domain.NoteContentReaderException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileNoteContentReader implements NoteContentReader {
    @Override
    public NoteContent read(Note note) throws NoteContentReaderException {
        File file = new File(note.text().value());
        try {
            return new NoteContent(new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new NoteContentReaderException("Error read file '" + file.getAbsolutePath() + "'", e);
        }
    }
}
