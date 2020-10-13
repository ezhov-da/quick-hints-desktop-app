package ru.ezhov.note.ui.notespanel.command;

import ru.ezhov.note.domain.Note;
import ru.ezhov.note.ui.command.domain.UiCommand;

public class NoteReloadUiCommand implements UiCommand {
    private Note note;

    public NoteReloadUiCommand(Note note) {
        this.note = note;
    }

    public Note note() {
        return note;
    }
}
