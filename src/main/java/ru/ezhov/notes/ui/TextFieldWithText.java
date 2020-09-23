package ru.ezhov.notes.ui;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TextFieldWithText extends JTextField {

    private String text;

    public TextFieldWithText(String text) {
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
