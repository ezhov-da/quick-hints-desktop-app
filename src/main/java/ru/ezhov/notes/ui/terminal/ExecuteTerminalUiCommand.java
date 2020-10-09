package ru.ezhov.notes.ui.terminal;

import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.ui.command.domain.UiCommand;

public class ExecuteTerminalUiCommand implements UiCommand {
    private HintId hintId;
    private String command;

    public ExecuteTerminalUiCommand(HintId hintId, String command) {
        this.hintId = hintId;
        this.command = command;
    }

    public String command() {
        return command;
    }

    public HintId hintId() {
        return hintId;
    }
}
