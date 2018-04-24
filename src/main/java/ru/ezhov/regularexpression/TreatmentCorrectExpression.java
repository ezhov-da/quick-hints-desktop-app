package ru.ezhov.regularexpression;

import ru.ezhov.regularexpression.frame.*;

import javax.swing.*;

/**
 * @author RRNDeonisiusEZH
 */
public class TreatmentCorrectExpression {
	public static void refresh() {
		JList list = SingletonBasicPanel.getInstance().getList();
		ApplicationObject applicationObject = (ApplicationObject) list.getSelectedValue();

		EditWindow editWindow = SingletonCorrectWindow.getInstance();

		JComboBox comboBox = editWindow.getComboBox();
		JTextField textField = editWindow.getComment();
		JTextPane textPane = editWindow.getText();


		if (list.isSelectionEmpty()) {
			textField.setText("");
			textPane.setText("");
			comboBox.setSelectedItem("-");
			return;
		}


		textField.setText(applicationObject.getDescription());
		textPane.setText(applicationObject.getText());

		if (applicationObject.getIdKey() != null) {
			comboBox.setSelectedItem(applicationObject.getIdKey());
		}
	}
}
