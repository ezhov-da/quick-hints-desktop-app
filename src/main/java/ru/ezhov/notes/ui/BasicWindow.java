package ru.ezhov.notes.ui;

import ru.ezhov.notes.SettingsApplication;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class BasicWindow extends JFrame {
    public BasicWindow() {
        initComponents();
    }

    private void initComponents() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(
                new Dimension(
                        dimension.width * 90 / 100,
                        dimension.height * 70 / 100
                )
        );
        setLayout(new BorderLayout());
        setIconImage(new ImageIcon(getClass().getResource("/notes.png")).getImage());
        setTitle("notes [" + SettingsApplication.APP_VERSION + "]");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
