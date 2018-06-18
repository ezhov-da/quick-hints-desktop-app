package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.frame.AddWindow;
import ru.ezhov.regularexpression.frame.BasicPanel;
import ru.ezhov.regularexpression.frame.SettingsFrame;
import ru.ezhov.regularexpression.frame.SingletonAddWindow;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonAdd extends MouseAdapter {
    private static boolean flagClick;
    private final int STEP = SettingsFrame.STEP_SHOW;
    private final int MILLISECONDS = SettingsFrame.MILLISECONDS;
    private Timer timer;

    @Override
    public void mouseReleased(MouseEvent e) {
        AddWindow addWindow = SingletonAddWindow.getInstance();
        BasicPanel basicPanel = SingletonBasicPanel.getInstance();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        addWindow.setSize(
                new Dimension(
                        dimension.width * 60 / 100,
                        dimension.height * 60 / 100
                )
        );
        addWindow.setLocationRelativeTo(basicPanel);
        addWindow.setVisible(true);
    }
}
