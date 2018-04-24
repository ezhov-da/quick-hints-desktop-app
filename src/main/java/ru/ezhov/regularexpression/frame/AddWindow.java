package ru.ezhov.regularexpression.frame;

import ru.ezhov.regularexpression.listeners.ListenerButtonAddExpression;
import ru.ezhov.regularexpression.listeners.ListenerChangeText;

import javax.swing.*;
import java.awt.*;

/**
 * @author RRNDeonisiusEZH
 */
public class AddWindow extends JDialog {
    private JPanel panel;
    private JTextField comment;
    private JTextPane text;
    private JScrollPane scrollPane;
    private BasicButton basicButtonAdd;


    public AddWindow(JFrame frame) {
        super(frame);
        init();
    }

    private void init() {
        setModal(true);
        setTitle("add");
        BasicPanel basicPanel = SingletonBasicPanel.getInstance();
        setLayout(new BorderLayout());
        Point point = basicPanel.getAdd().getLocationOnScreen();
        setFocusableWindowState(true);

       /*init components*/
        panel = new JPanel(new BorderLayout());
        comment = new JTextField(40);
        text = new JTextPane();
        basicButtonAdd = new BasicButton("add expression");
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(text);

        basicButtonAdd.addMouseListener(new ListenerButtonAddExpression());
        text.addCaretListener(new ListenerChangeText("TEXT"));
        comment.addCaretListener(new ListenerChangeText("DESCRIPTION"));
        panel.add(comment, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel panelButton = new JPanel();
        panelButton.add(basicButtonAdd);
        panel.add(panelButton, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(basicPanel);
    }

    public JTextField getComment() {
        return comment;
    }

    public JTextPane getText() {
        return text;
    }

    public BasicButton getBasicButtonAdd() {
        return basicButtonAdd;
    }


}
