package ru.ezhov.note.ui;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TextAreaWithText extends JTextArea {

    private String text;

    public TextAreaWithText(String text) {
        this.text = text;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        if (getText().length() == 0) {
            graphics2D.setPaint(Color.GRAY);
            graphics2D.drawString(text, 8, 15);
        }
    }
}
