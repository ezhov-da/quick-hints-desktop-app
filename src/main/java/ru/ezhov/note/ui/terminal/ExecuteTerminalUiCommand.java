package ru.ezhov.note.ui.terminal;

import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.command.domain.UiCommand;

public class ExecuteTerminalUiCommand implements UiCommand {
    private NoteId noteId;
    private String command;

    public ExecuteTerminalUiCommand(NoteId noteId, String command) {
        this.noteId = noteId;
        this.command = command;
    }

    public String command() {
        return command;
    }

    public NoteId hintId() {
        return noteId;
    }
}
