/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.frame;

import ru.ezhov.regularexpression.listeners.ListenerButtonCorrectExpression;
import ru.ezhov.regularexpression.listeners.ListenerChangeText;

import javax.swing.*;
import java.awt.*;

/**
 * @author RRNDeonisiusEZH
 */
public class EditWindow extends JDialog {
    public static final int MINUS_HEIGTH = 3;
    public static final int SIZE_HEIGTH = 248;
    private JComboBox<String> comboBox;
    private JPanel panel;
    private JTextField comment;
    private JTextPane text;
    private JScrollPane scrollPaneText;
    private BasicButton basicButtonEdit;

    public EditWindow(JFrame frame) {
        super(frame);
        init();
    }

    private void init() {
        comboBox = new JComboBox<>(new String[]{"-", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        comboBox.setMaximumRowCount(11);

        setTitle("correct");
        setLayout(new BorderLayout());
        setFocusableWindowState(true);

       /*init components*/
        panel = new JPanel(new BorderLayout());
        comment = new JTextField(40);
        text = new JTextPane();
        basicButtonEdit = new BasicButton("edit expression");
        scrollPaneText = new JScrollPane();
        scrollPaneText.setViewportView(text);

        basicButtonEdit.addMouseListener(new ListenerButtonCorrectExpression());
        text.addCaretListener(new ListenerChangeText("TEXT"));
        comment.addCaretListener(new ListenerChangeText("DESCRIPTION"));

        JPanel panelNorth = new JPanel(new BorderLayout());
        panelNorth.add(comboBox, BorderLayout.WEST);
        panelNorth.add(comment, BorderLayout.CENTER);
        panel.add(panelNorth, BorderLayout.NORTH);
        panel.add(scrollPaneText, BorderLayout.CENTER);
        JPanel panelSouth = new JPanel();
        panelSouth.add(basicButtonEdit, BorderLayout.WEST);
        panel.add(panelSouth, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
    }

    public JComboBox getComboBox() {
        return comboBox;
    }

    public JTextField getComment() {
        return comment;
    }

    public JTextPane getText() {
        return text;
    }

    public BasicButton getBasicButtonEdit() {
        return basicButtonEdit;
    }
}

