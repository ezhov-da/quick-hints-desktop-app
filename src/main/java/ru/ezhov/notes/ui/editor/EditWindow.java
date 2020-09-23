package ru.ezhov.notes.ui.editor;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.notes.domain.Hint;
import ru.ezhov.notes.domain.HintRepository;
import ru.ezhov.notes.domain.HintsRepositoryException;
import ru.ezhov.notes.ui.SingletonBasicWindow;
import ru.ezhov.notes.ui.event.infrastructure.UiEventPublisherFactory;

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
    private Hint hint;
    private HintRepository hintRepository;

    public EditWindow(HintRepository hintRepository, Hint hint) {
        super(SingletonBasicWindow.getInstance());
        this.hintRepository = hintRepository;
        this.hint = hint;
        init();
    }

    private void init() {
        setTitle("correct");
        setLayout(new BorderLayout());
        setFocusableWindowState(true);

        /*init components*/
        panel = new JPanel(new BorderLayout());
        name = new JTextField(40);
        name.setText(hint.name().value());
        text = new RSyntaxTextArea();
        text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        text.setCodeFoldingEnabled(true);
        text.setText(hint.text().value());
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
                        hintRepository.change(hint.id(), name, text);
                        EditWindow.this.setVisible(false);

                        UiEventPublisherFactory.inMemory().publish(new HintEditedUiEvent(hint.id(), name, text));
                    } catch (HintsRepositoryException hintsRepositoryException) {
                        hintsRepositoryException.printStackTrace();
                    }
                });
            }
        };
    }
}

