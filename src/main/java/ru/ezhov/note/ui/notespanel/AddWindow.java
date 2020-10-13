package ru.ezhov.note.ui.notespanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.note.application.NoteApplicationServiceFactory;
import ru.ezhov.note.domain.NewNote;
import ru.ezhov.note.ui.SingletonBasicWindow;
import ru.ezhov.note.ui.TextFieldWithText;
import ru.ezhov.note.ui.event.infrastructure.UiEventPublisherFactory;
import ru.ezhov.note.ui.notespanel.event.HintAddedUiEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddWindow extends JDialog {
    private JPanel panel;
    private JTextField name;
    private RSyntaxTextArea text;
    private JButton basicButtonAdd;
    private NoteApplicationServiceFactory noteApplicationServiceFactory;

    public AddWindow(NoteApplicationServiceFactory noteApplicationServiceFactory) {
        super(SingletonBasicWindow.getInstance());
        this.noteApplicationServiceFactory = noteApplicationServiceFactory;
        init();
    }

    private void init() {
        setModal(true);
        setTitle("add");
        setLayout(new BorderLayout());
        setFocusableWindowState(true);

        panel = new JPanel(new BorderLayout());
        name = new TextFieldWithText("Input name");
        text = new RSyntaxTextArea();
        text.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        text.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(text);

        basicButtonAdd = new JButton("add expression");

        basicButtonAdd.addMouseListener(new ListenerButtonAddExpression());
        panel.add(name, BorderLayout.NORTH);
        panel.add(sp, BorderLayout.CENTER);

        JPanel panelButton = new JPanel();
        panelButton.add(basicButtonAdd);
        panel.add(panelButton, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(SingletonBasicWindow.getInstance());
    }

    private class ListenerButtonAddExpression extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            String text = AddWindow.this.name.getText();
            String comment = AddWindow.this.text.getText();
            try {
                final NewNote newNote = new NewNote(text, comment);
                noteApplicationServiceFactory.commonService().create(text, comment);
                AddWindow.this.setVisible(false);

                UiEventPublisherFactory.inMemory().publish(new HintAddedUiEvent(newNote));
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "При добавлении подсказки возникла ошибка",
                        "Ошибка добавления подсказки",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }
}
