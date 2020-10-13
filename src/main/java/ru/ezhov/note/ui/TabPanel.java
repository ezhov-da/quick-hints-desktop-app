package ru.ezhov.note.ui;

import ru.ezhov.note.application.NoteApplicationServiceFactory;
import ru.ezhov.note.domain.Note;
import ru.ezhov.note.template.domain.Engine;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class TabPanel extends JPanel {
    private final Note note;

    public TabPanel(NoteApplicationServiceFactory noteApplicationServiceFactory, Note note) {
        this.note = note;

        setLayout(new BorderLayout());

        NotePanel notePanel = new NotePanel(noteApplicationServiceFactory, note);

        add(notePanel, BorderLayout.CENTER);
    }
}
