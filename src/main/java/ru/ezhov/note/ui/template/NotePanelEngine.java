package ru.ezhov.note.ui.template;

import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.ApplyVariableToSelectedTabCommand;
import ru.ezhov.note.ui.TextAreaPanel;
import ru.ezhov.note.ui.command.domain.UiCommand;
import ru.ezhov.note.ui.command.domain.UiCommandSubscriber;
import ru.ezhov.note.ui.command.infrastructure.UiCommandFactory;
import ru.ezhov.note.ui.event.infrastructure.UiEventPublisherFactory;
import ru.ezhov.note.ui.template.event.ExecutedWordsEngineUiEvent;
import ru.ezhov.note.ui.variable.ApplyVariableCommand;
import ru.ezhov.note.variable.domain.Variable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NotePanelEngine extends JPanel {
    private PanelEngine panelEngine;
    private NoteId noteId;

    public NotePanelEngine(NoteId noteId, List<String> words) {
        super(new BorderLayout());
        this.noteId = noteId;
        this.panelEngine = new PanelEngine(words, new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SwingUtilities.invokeLater(() ->
                            UiEventPublisherFactory.inMemory().publish(new ExecutedWordsEngineUiEvent(noteId, panelEngine.apply())));
                }
            }
        });
        add(panelEngine, BorderLayout.CENTER);

        JButton button = new JButton("apply template");
        JPanel panelButton = new JPanel();
        panelButton.add(button);
        add(panelButton, BorderLayout.SOUTH);

        button.addActionListener(e ->
                SwingUtilities.invokeLater(() ->
                        UiEventPublisherFactory.inMemory().publish(new ExecutedWordsEngineUiEvent(noteId, panelEngine.apply())))
        );

        UiCommandFactory.inMemory().register(new UiCommandSubscriber() {
            @Override
            public void doOnCommand(UiCommand command) {
                ApplyVariableToSelectedTabCommand variable = (ApplyVariableToSelectedTabCommand) command;
                if (variable.noteId().equals(noteId)) {
                    SwingUtilities.invokeLater(() -> {
                        panelEngine.initVariables(
                                variable.
                                        variable().stream()
                                        .collect(
                                                Collectors.toMap(
                                                        v -> v.name().value(),
                                                        v -> v.value().value()
                                                )
                                        )
                        );

                    });
                }
            }

            @Override
            public List<Class> subscribeOn() {
                return Arrays.asList(ApplyVariableToSelectedTabCommand.class);
            }
        });
    }
}
