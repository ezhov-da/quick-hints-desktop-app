package ru.ezhov.regularexpression;

import java.awt.AWTException;

import ru.ezhov.regularexpression.frame.SingletonBasicWindow;

import java.awt.BorderLayout;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

import ru.ezhov.regularexpression.connection.Querys;
import ru.ezhov.regularexpression.frame.BasicWindow;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;


/**
 * @author RRNDeonisiusEZH
 */
public class RegularExpression {
    private static final Logger logger = Logger.getLogger(RegularExpression.class.getName());

    /**
     * @param args the command line arguments
     */
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
                        /*set look and feel*/
            LookAndFeel.setLookAndFeel();

            try {
                SwingUtilities.invokeAndWait(new Runnable() {

                    @Override
                    public void run() {
                        BasicWindow window = SingletonBasicWindow.getInstance();
                        window.setLayout(new BorderLayout());
                        window.getContentPane().add(SingletonBasicPanel.getInstance(), BorderLayout.CENTER);
                        new TreatmentData().select(Querys.SELECT);

                        try {
                            new RunApplicationTray().runTray();
                        } catch (AWTException ex) {
                            logger.log(Level.SEVERE, null, ex);
                        }
                    }
                });
            } catch (InterruptedException ex) {
                logger.log(Level.WARNING, null, ex);
            } catch (InvocationTargetException ex) {
                logger.log(Level.WARNING, null, ex);
            }
        } catch (Exception ex) {
            /*this exception wtrite file if i not catch special exception*/
            logger.log(Level.WARNING, null, ex);
        }
    }
}
