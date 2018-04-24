/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.ApplicationObject;
import ru.ezhov.regularexpression.frame.SingletonJFrameHelper;

import javax.swing.*;
import javax.swing.event.*;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerChangeSelectionHelper implements ListSelectionListener {

	@Override
	public void valueChanged(ListSelectionEvent e) {
		final ApplicationObject applicationObject =
			(ApplicationObject) SingletonJFrameHelper.getInstance().getListHelper().getSelectedValue();

		final JLabel label = SingletonJFrameHelper.getInstance().getLabelInfo();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				label.setText(applicationObject.getDescription());
			}
		});
	}

}
