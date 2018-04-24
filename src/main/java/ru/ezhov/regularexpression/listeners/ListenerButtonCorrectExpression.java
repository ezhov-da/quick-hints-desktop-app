package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.*;
import ru.ezhov.regularexpression.frame.*;

import java.awt.event.*;


/**
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonCorrectExpression extends MouseAdapter {


	@Override
	public void mouseReleased(MouseEvent e) {
		if (ListenerChangeText.isWrong()) return;
		EditWindow editWindow = SingletonCorrectWindow.getInstance();
		BasicPanel basicPanel = SingletonBasicPanel.getInstance();
		ApplicationObject applicationObject = (ApplicationObject) basicPanel.getList().getSelectedValue();

		if (basicPanel.getList().isSelectionEmpty()) return;

		int id = applicationObject.getId();
		String text = editWindow.getText().getText();
		String comment = editWindow.getComment().getText();
		String valueKey = (String) editWindow.getComboBox().getSelectedItem();

		TreatmentData treatmentData = new TreatmentData();
		treatmentData.update(id, valueKey, text, comment);

		editWindow.setVisible(false);
	}
}



