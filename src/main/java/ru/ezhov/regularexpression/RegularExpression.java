package ru.ezhov.regularexpression;

import ru.ezhov.regularexpression.connection.Querys;
import ru.ezhov.regularexpression.frame.BasicWindow;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonBasicWindow;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class RegularExpression {
    private static final Logger logger = Logger.getLogger(RegularExpression.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    RegularExpression.class.getResourceAsStream("/log.properties")
            );
        } catch (IOException ex) {
            Logger.getLogger(RegularExpression.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(RegularExpression.class.getName()).log(Level.SEVERE, null, ex);
        }

        logger.log(Level.INFO, "run application");

        try {
            LookAndFeel.setLookAndFeel();
            setUIFont(new javax.swing.plaf.FontUIResource("Courier New", Font.PLAIN, 11));

            try {
                SwingUtilities.invokeAndWait(new Runnable() {
                    public void run() {
                        BasicWindow window = SingletonBasicWindow.getInstance();
                        window.setLayout(new BorderLayout());
                        window.getContentPane().add(SingletonBasicPanel.getInstance(), BorderLayout.CENTER);
                        new TreatmentData().select(Querys.SELECT);


                        window.setVisible(true);
                        try {
                            new RunApplicationTray().runTray();
                        } catch (AWTException ex) {
                            logger.log(Level.SEVERE, null, ex);
                        }
                    }
                });
            } catch (Exception ex) {
                logger.log(Level.WARNING, null, ex);
            }
        } catch (Exception ex) {
            logger.log(Level.WARNING, null, ex);
        }
    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
}
