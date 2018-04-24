package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.*;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;

import javax.swing.*;
import java.awt.event.*;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonDelete extends MouseAdapter {
	private static int index;

	@Override
	public void mouseReleased(MouseEvent e) {
		TreatmentData treatmentData = new TreatmentData();
		final JList list = SingletonBasicPanel.getInstance().getList();

		if (list.getSelectedIndex() == -1) return;
		index = list.getSelectedIndex();

		ApplicationObject applicationObject = (ApplicationObject) list.getSelectedValue();

		treatmentData.delete(applicationObject.getId());

		if (list.indexToLocation(index) == null) {
			if (list.indexToLocation(list.getModel().getSize() - 1) == null) {
				return;
			} else {
				index = list.getModel().getSize() - 1;
			}
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				list.setSelectedIndex(index);
			}
		});


	}
}
