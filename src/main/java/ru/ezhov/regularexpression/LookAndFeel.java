package ru.ezhov.regularexpression;

import javax.swing.*;
import java.util.logging.*;

/**
 * @author RRNDeonisiusEZH
 */
public class LookAndFeel {
	private static final Logger logger = Logger.getLogger(LookAndFeel.class.getName());

	public static void setLookAndFeel() {

		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

}
