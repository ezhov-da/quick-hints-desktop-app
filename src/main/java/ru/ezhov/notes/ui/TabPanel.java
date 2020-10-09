package ru.ezhov.notes.ui;

import ru.ezhov.notes.domain.Hint;
import ru.ezhov.notes.domain.HintRepository;
import ru.ezhov.notes.template.domain.Engine;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class TabPanel extends JPanel {
    private final Hint hint;
    private final Engine engine;

    public TabPanel(HintRepository hintRepository, Engine engine, Hint hint) {
        this.hint = hint;
        this.engine = engine;

        setLayout(new BorderLayout());

        HintPanel hintPanel = new HintPanel(hintRepository, engine, hint);

        add(hintPanel, BorderLayout.CENTER);
    }
}
