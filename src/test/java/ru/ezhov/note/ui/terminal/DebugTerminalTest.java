package ru.ezhov.note.ui.terminal;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.BorderLayout;

public class DebugTerminalTest {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        TerminalPanel terminalPanel = new TerminalPanel("D:\\programs\\Git-2.28.0-64\\bin\\sh.exe");


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(terminalPanel, BorderLayout.CENTER);
        frame.setSize(675, 300);
        frame.setVisible(true);
    }
}
