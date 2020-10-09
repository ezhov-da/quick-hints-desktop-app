package ru.ezhov.notes.ui.template;

import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.ui.event.infrastructure.UiEventPublisherFactory;
import ru.ezhov.notes.ui.template.event.ExecutedWordsEngineUiEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.util.List;

public class HintPanelEngine extends JPanel {
    private PanelEngine panelEngine;
    private HintId hintId;

    public HintPanelEngine(HintId hintId, List<String> words) {
        super(new BorderLayout());
        this.hintId = hintId;
        this.panelEngine = new PanelEngine(words);
        add(panelEngine, BorderLayout.CENTER);

        JButton button = new JButton("apply template");
        JPanel panelButton = new JPanel();
        panelButton.add(button);
        add(panelButton, BorderLayout.SOUTH);

        button.addActionListener(e ->
                SwingUtilities.invokeLater(() ->
                        UiEventPublisherFactory.inMemory().publish(new ExecutedWordsEngineUiEvent(hintId, panelEngine.apply())))
        );
    }
}
