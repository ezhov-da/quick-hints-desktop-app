package ru.ezhov.regularexpression;

import ru.ezhov.regularexpression.frame.BasicWindow;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonBasicWindow;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class AppHints {
    private static final Logger logger = Logger.getLogger(AppHints.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    AppHints.class.getResourceAsStream("/log.properties")
            );
        } catch (Exception ex) {
            Logger.getLogger(AppHints.class.getName())
                    .log(Level.SEVERE, "Не удалось установить настройки логгера", ex);
        }
        logger.log(Level.INFO, "run application");
        try {
            ru.ezhov.regularexpression.util.LookAndFeel.setLookAndFeel();
            new RunApplicationTray().runTray();
            setUIFont(new javax.swing.plaf.FontUIResource("Courier New", Font.PLAIN, 11));
            SwingUtilities.invokeAndWait(() -> {
                BasicWindow window = SingletonBasicWindow.getInstance();
                window.setLayout(new BorderLayout());
                window.getContentPane().add(SingletonBasicPanel.getInstance(), BorderLayout.CENTER);
                window.setVisible(true);
            });
        } catch (Exception ex) {
            logger.log(Level.WARNING, null, ex);
        }
    }

    private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
}