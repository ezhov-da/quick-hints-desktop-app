/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression;

import ru.ezhov.regularexpression.frame.*;
import ru.ezhov.regularexpression.template.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author RRNDeonisiusEZH
 */
public class FillText {
	private static final Logger logger = Logger.getLogger(RegularExpression.class.getName());


	private static JTextPane textPane;

	public static void fillText(int index, LookAndDeletePanel lookAndDeletePanel) {
		JList list = SingletonBasicPanel.getInstance().getList();

		if (index == -1) {
			lookAndDeletePanel.getText().setText("");
			return;
		}

		ApplicationObject applicationObject = (ApplicationObject) list.getSelectedValue();

		textPane = lookAndDeletePanel.getText();
		textPane.setText("");

		getDefaultStyledDocument(
			applicationObject.getIdKey(),
			applicationObject.getDescription(),
			applicationObject.getText()
		);

		textPane.setCaretPosition(0);

		Engine engine = new VelocityEngineImpl(applicationObject.getText());
		List<String> words = engine.words();
		if (!words.isEmpty()) {
			lookAndDeletePanel.addPanelEngine(words, applicationObject.getText());
		} else {
			lookAndDeletePanel.removePanelEngine();
		}
	}

	private static void getDefaultStyledDocument(String idKey, String comment, String text) {
		final String TEXT_ID_KEY = "key ->\n".toUpperCase();
		final String TEXT_COMMENT = "comment ->\n".toUpperCase();
		final String TEXT = "text ->\n".toUpperCase();

		StyledDocument doc = textPane.getStyledDocument();
		Style styleIdKey = textPane.addStyle("styleIdKey", null);
		Style styleComment = textPane.addStyle("styleComment", null);
		Style styletext = textPane.addStyle("styletext", null);

		StyleConstants.setFontSize(styleIdKey, 12);
		StyleConstants.setForeground(styleIdKey, Color.red);
		StyleConstants.setBold(styleIdKey, true);

		StyleConstants.setFontSize(styleComment, 12);
		StyleConstants.setForeground(styleComment, Color.blue);
		StyleConstants.setBold(styleComment, true);

		StyleConstants.setFontSize(styletext, 12);
		StyleConstants.setForeground(styletext, new Color(218, 165, 32));
		StyleConstants.setBold(styletext, true);

		try {
			doc.insertString(doc.getLength(), TEXT_ID_KEY, styleIdKey);
			doc.insertString(doc.getLength(), idKey + "\n", null);
			doc.insertString(doc.getLength(), TEXT_COMMENT, styleComment);
			doc.insertString(doc.getLength(), comment + "\n", null);
			doc.insertString(doc.getLength(), TEXT, styletext);
			doc.insertString(doc.getLength(), text + "\n", null);
		} catch (BadLocationException ex) {
			logger.throwing(FillText.class.getName(), "getDefaultStyledDocument", ex);
		}


	}
}
