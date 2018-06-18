package ru.ezhov.regularexpression;

import ru.ezhov.regularexpression.domain.Hint;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Logger;

public class TreatmentHotKey {

    private static final Logger logger = Logger.getLogger(TreatmentHotKey.class.getName());
    private final Clipboard clipboard;

    {
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public void getTextFromList(Hint hint) {
        if (hint == null) {
            throw new IllegalArgumentException("Подсказка не может быть null");
        }
        try {
            String text = hint.getText();
            setBufferText(replaceText(text, getBufferText()));
        } catch (Exception ex) {
            logger.throwing(FillText.class.getName(), "getTextFromList", ex);
        }
    }

    private String getBufferText() throws UnsupportedFlavorException, IOException {
        if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            return (String) clipboard.getData(DataFlavor.stringFlavor);
        } else {
            return "";
        }
    }

    private String replaceText(String text, String textForReplace) {
        int find = text.indexOf(SettingsApplication.PATTERN_REPLACE);
        if (find == -1) {
            return text;
        } else {
            return text.replaceAll(SettingsApplication.PATTERN_REPLACE, textForReplace);
        }
    }

    private void setBufferText(String text) {
        clipboard.setContents(new StringSelection(text), null);
    }
}
