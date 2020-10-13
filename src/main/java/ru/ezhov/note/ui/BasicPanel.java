package ru.ezhov.note.ui;

import ru.ezhov.note.application.NoteApplicationServiceFactory;
import ru.ezhov.note.ui.notespanel.NotesPanel;
import ru.ezhov.note.ui.variable.VariablePanel;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;

public class BasicPanel extends JPanel {
    private NoteApplicationServiceFactory noteApplicationServiceFactory;

    public BasicPanel(NoteApplicationServiceFactory noteApplicationServiceFactory) {
        this.noteApplicationServiceFactory = noteApplicationServiceFactory;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());

        TabbedManagedPanel tabbedManagedPanel = new TabbedManagedPanel(noteApplicationServiceFactory);
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        splitPane2.setLeftComponent(tabbedManagedPanel);

        ToolsPanel toolsPanel = new ToolsPanel();

        splitPane2.setRightComponent(toolsPanel);

        splitPane2.setResizeWeight(0.9);
        splitPane2.setDividerLocation(0.8);

        JPanel panelCenter = new JPanel(new BorderLayout());

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        splitPane.setLeftComponent(new NotesPanel(noteApplicationServiceFactory));

        splitPane.setRightComponent(splitPane2);
        splitPane.setResizeWeight(0.2);
        splitPane.setDividerLocation(0.2);

        panelCenter.add(splitPane, BorderLayout.CENTER);

        add(splitPane, BorderLayout.CENTER);
    }
}
