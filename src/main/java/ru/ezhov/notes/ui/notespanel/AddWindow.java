package ru.ezhov.notes.ui.notespanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.notes.domain.AddHintException;
import ru.ezhov.notes.domain.HintRepository;
import ru.ezhov.notes.domain.NewHint;
import ru.ezhov.notes.ui.SingletonBasicWindow;
import ru.ezhov.notes.ui.TextFieldWithText;
import ru.ezhov.notes.ui.event.infrastructure.UiEventPublisherFactory;
import ru.ezhov.notes.ui.notespanel.event.HintAddedUiEvent;

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
    private HintRepository hintRepository;

    public AddWindow(HintRepository hintRepository) {
        super(SingletonBasicWindow.getInstance());
        this.hintRepository = hintRepository;
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
                final NewHint newHint = new NewHint(text, comment);
                hintRepository.add(newHint);
                AddWindow.this.setVisible(false);

                UiEventPublisherFactory.inMemory().publish(new HintAddedUiEvent(newHint));
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
