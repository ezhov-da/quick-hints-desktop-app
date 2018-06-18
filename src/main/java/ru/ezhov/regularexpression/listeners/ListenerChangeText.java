package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.config.ApplicationTooltips;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerChangeText implements CaretListener {
    private static final int LENGTH_DESCRIPTION = 100;
    private static final int LENGTH_TEXT = 8000;
    private static boolean wrong;
    private final ComponentText component;
    private JTextComponent textComponent;

    public ListenerChangeText(String component) {
        this.component = ComponentText.valueOf(component.toUpperCase());
    }

    public static boolean isWrong() {
        return wrong;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        textComponent = (JTextComponent) e.getSource();
        int length;
        switch (component) {
            case DESCRIPTION:
                length = checkLength(LENGTH_DESCRIPTION);
                textComponent.setToolTipText("<html><font size=\"3\" color=\"black\" face=\"Courier New\">"
                        + ApplicationTooltips.DESCRIPTION + " now ---> " + length + "</font>");
                break;

            case TEXT:
                length = checkLength(LENGTH_TEXT);
                textComponent.setToolTipText("<html><font size=\"3\" color=\"black\" face=\"Courier New\">"
                        + ApplicationTooltips.TEXT + " now ---> " + length + "</font>");
                break;
        }
    }


    private int checkLength(int mustLength) {
        if (textComponent.getDocument().getLength() > mustLength) {
            textComponent.setForeground(Color.red);
            wrong = true;
        } else {
            textComponent.setForeground(Color.black);
            wrong = false;
        }

        return textComponent.getDocument().getLength();
    }

    enum ComponentText {DESCRIPTION, TEXT}


}
