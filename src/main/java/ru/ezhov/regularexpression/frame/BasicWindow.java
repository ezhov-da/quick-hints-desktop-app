/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.frame;

import ru.ezhov.regularexpression.SettingsApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author RRNDeonisiusEZH
 */
public class BasicWindow extends JFrame {
	public BasicWindow() {
		initComponents();
	}

	private void initComponents() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(
			new Dimension(
				dimension.width * 70 / 100,
				dimension.height * 70 / 100
			)
		);
		setLayout(new BorderLayout());
		setIconImage(SettingsFrame.TRAY_ICON);
		setTitle("fast paste [" + SettingsApplication.APP_VERSION + "]");
		addWindowStateListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				setVisible(false);
			}
		});
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
	}
}
