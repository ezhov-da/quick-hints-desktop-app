package ru.ezhov.notes.ui;

import ru.ezhov.notes.domain.HintRepository;
import ru.ezhov.notes.template.domain.Engine;
import ru.ezhov.notes.ui.notespanel.NotesPanel;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class BasicPanel extends JPanel {
    private JSplitPane splitPane;
    private TabbedPane tabbedPane;

    private final Engine engine;
    private HintRepository hintRepository;

    public BasicPanel(HintRepository hintRepository, Engine engine) {
        this.hintRepository = hintRepository;
        this.engine = engine;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        JPanel panelCenter = new JPanel(new BorderLayout());

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        splitPane.setLeftComponent(new NotesPanel(hintRepository));

        tabbedPane = new TabbedPane(hintRepository, engine);
        splitPane.setRightComponent(tabbedPane);
        splitPane.setResizeWeight(0.2);
        splitPane.setDividerLocation(0.2);

        panelCenter.add(splitPane, BorderLayout.CENTER);
        add(panelCenter, BorderLayout.CENTER);
    }
}
