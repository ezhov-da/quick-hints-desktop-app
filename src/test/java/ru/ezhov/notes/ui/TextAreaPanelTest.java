package ru.ezhov.notes.ui;

import ru.ezhov.notes.domain.HintId;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TextAreaPanelTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
        {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Throwable ex) {
                //
            }
            JFrame frame = new JFrame("_________");
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new TextAreaPanel(new HintId("1"), "test text", true));
            frame.setVisible(true);
        });
    }
}