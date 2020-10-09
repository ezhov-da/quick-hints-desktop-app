package ru.ezhov.notes.ui.terminal;

import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.ui.command.domain.UiCommand;
import ru.ezhov.notes.ui.command.domain.UiCommandSubscriber;
import ru.ezhov.notes.ui.command.infrastructure.UiCommandFactory;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

public class HintTerminalPanel extends JPanel {
    private HintId hintId;
    private TerminalPanel terminalPanel;

    public HintTerminalPanel(HintId hintId, String shellCommand) {
        super(new BorderLayout());
        terminalPanel = new TerminalPanel(shellCommand);
        add(terminalPanel, BorderLayout.CENTER);

        UiCommandFactory.inMemory().register(new UiCommandSubscriber() {
            @Override
            public void doOnCommand(UiCommand event) {
                if (event.getClass() == ExecuteTerminalUiCommand.class) {
                    ExecuteTerminalUiCommand uiCommand = (ExecuteTerminalUiCommand) event;
                    if(uiCommand.hintId().equals(hintId)){
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
