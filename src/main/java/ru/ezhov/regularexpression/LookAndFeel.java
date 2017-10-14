package ru.ezhov.regularexpression;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class LookAndFeel {
    private static final Logger logger = Logger.getLogger(LookAndFeel.class.getName());
    
    public static void setLookAndFeel(){
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ex) {
                    logger.log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
                    logger.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
                    logger.log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
                    logger.log(Level.SEVERE, null, ex);
        }              
    }
    
}
