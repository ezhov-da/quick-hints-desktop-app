package ru.ezhov.note.ui.editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.note.application.NoteApplicationServiceFactory;
import ru.ezhov.note.domain.Note;
import ru.ezhov.note.ui.SingletonBasicWindow;
import ru.ezhov.note.ui.event.infrastructure.UiEventPublisherFactory;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditWindow extends JDialog {
    private JPanel panel;
    private JTextField name;
    private RSyntaxTextArea text;
    private JButton basicButtonEdit;
    private Note note;
    private NoteApplicationServiceFactory noteApplicationServiceFactory;

    public EditWindow(NoteApplicationServiceFactory noteApplicationServiceFactory, Note note) {
        super(SingletonBasicWindow.getInstance());
        this.noteApplicationServiceFactory = noteApplicationServiceFactory;
        this.note = note;
        init();
    }

    private void init() {
        setTitle("correct");
        setLayout(new BorderLayout());
        setFocusableWindowState(true);

        /*init components*/
        panel = new JPanel(new BorderLayout());
        name = new JTextField(40);
        name.setText(note.name().value());
        text = new RSyntaxTextArea();
        text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        text.setCodeFoldingEnabled(true);
        text.setText(note.text().value());
        RTextScrollPane sp = new RTextScrollPane(text);
        basicButtonEdit = new JButton("edit expression");
        basicButtonEdit.addMouseListener(create());

        JPanel panelNorth = new JPanel(new BorderLayout());
        panelNorth.add(name, BorderLayout.CENTER);
        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(sp, BorderLayout.CENTER);
        JPanel panelSouth = new JPanel();
        panelSouth.add(basicButtonEdit, BorderLayout.WEST);
        panel.add(panelSouth, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }

    private MouseAdapter create() {
        return new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        String name = EditWindow.this.name.getText();
                        String text = EditWindow.this.text.getText();
                        noteApplicationServiceFactory.commonService().change(note.id(), name, text);
                        EditWindow.this.setVisible(false);

                        UiEventPublisherFactory.inMemory().publish(new NoteEditedUiEvent(note.id(), name, text));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
        };
    }
}

