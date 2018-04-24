package ru.ezhov.regularexpression;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author RRNDeonisiusEZH
 */
public class TreatmentHotKey {

	private static final Logger logger = Logger.getLogger(TreatmentHotKey.class.getName());
	private final Clipboard clipboard;
	private String resultText;
	private String testFromBuffer;

	public void getTextFromList(JList list) {
		if (list.getSelectedValue() == null) return;
		ApplicationObject applicationObject = (ApplicationObject) list.getSelectedValue();
		resultText = applicationObject.getText();
		try {
			getBufferText();
			replaceText();
			setBufferText();
		} catch (UnsupportedFlavorException ex) {
			logger.throwing(FillText.class.getName(), "getTextFromList", ex);
		} catch (IOException ex) {
			logger.throwing(FillText.class.getName(), "getTextFromList", ex);
		}
	}

	public void getTextHotKey(int idKey) {
		TreatmentData treatmentData = new TreatmentData();
		resultText = treatmentData.selectKey(idKey);
		try {
			getBufferText();
			replaceText();
			setBufferText();
		} catch (UnsupportedFlavorException ex) {
			logger.throwing(FillText.class.getName(), "getTextHotKey", ex);
		} catch (IOException ex) {
			logger.throwing(FillText.class.getName(), "getTextHotKey", ex);
		}
	}

	private void getBufferText() throws UnsupportedFlavorException, IOException {
		if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
			testFromBuffer = (String) clipboard.getData(DataFlavor.stringFlavor);
		}
	}

	private void replaceText() {
		int find = resultText.indexOf(SettingsApplication.PATTERN_REPLACE);
		if (find == -1) return;
		resultText = resultText.replaceAll(SettingsApplication.PATTERN_REPLACE, testFromBuffer);

	}

	private void setBufferText() {
		clipboard.setContents(new StringSelection(resultText), null);
	}

	{
		clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	}
}
