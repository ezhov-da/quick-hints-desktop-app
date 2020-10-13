package ru.ezhov.note.util;

import javax.swing.UIManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author RRNDeonisiusEZH
 */
public class LookAndFeel {
    private static final Logger logger = Logger.getLogger(LookAndFeel.class.getName());

    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

}
