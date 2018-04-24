package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.frame.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonCorrect extends MouseAdapter {
	private static boolean flagClick;
	private final int STEP = SettingsFrame.STEP_SHOW;
	private final int MILLISECONDS = SettingsFrame.MILLISECONDS;
	private Timer timer;

	@Override
	public void mouseReleased(MouseEvent e) {
		EditWindow editWindow = SingletonCorrectWindow.getInstance();
		BasicPanel basicPanel = SingletonBasicPanel.getInstance();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		editWindow.setSize(
			new Dimension(
				dimension.width * 60 / 100,
				dimension.height * 60 / 100
			)
		);
		editWindow.setLocationRelativeTo(basicPanel);
		editWindow.setVisible(true);
	}
}
