package ru.ezhov.note.ui.terminal;

import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.command.domain.UiCommand;
import ru.ezhov.note.ui.command.domain.UiCommandSubscriber;
import ru.ezhov.note.ui.command.infrastructure.UiCommandFactory;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

public class HintTerminalPanel extends JPanel {
    private NoteId noteId;
    private TerminalPanel terminalPanel;

    public HintTerminalPanel(NoteId noteId, String shellCommand) {
        super(new BorderLayout());
        terminalPanel = new TerminalPanel(shellCommand);
        add(terminalPanel, BorderLayout.CENTER);

        UiCommandFactory.inMemory().register(new UiCommandSubscriber() {
            @Override
            public void doOnCommand(UiCommand event) {
                if (event.getClass() == ExecuteTerminalUiCommand.class) {
                    ExecuteTerminalUiCommand uiCommand = (ExecuteTerminalUiCommand) event;
                    if(uiCommand.hintId().equals(noteId)){
                        terminalPanel.setCommandAndExecute(uiCommand.command());
                    }
                }

            }

            @Override
            public List<Class> subscribeOn() {
                return Arrays.asList(ExecuteTerminalUiCommand.class);
            }
        });
    }
}
