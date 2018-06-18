package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.domain.Hint;
import ru.ezhov.regularexpression.domain.Hints;
import ru.ezhov.regularexpression.frame.BasicPanel;
import ru.ezhov.regularexpression.frame.EditWindow;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonCorrectWindow;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonCorrectExpression extends MouseAdapter {


    @Override
    public void mouseReleased(MouseEvent e) {
        if (ListenerChangeText.isWrong()) return;

        EditWindow editWindow = SingletonCorrectWindow.getInstance();
        BasicPanel basicPanel = SingletonBasicPanel.getInstance();
        Hint hint = (Hint) basicPanel.getList().getSelectedValue();

        if (basicPanel.getList().isSelectionEmpty()) return;

        int id = hint.getId();
        String text = editWindow.getText().getText();
        String comment = editWindow.getComment().getText();
        String valueKey = (String) editWindow.getComboBox().getSelectedItem();

        Hints hints = new Hints();
        hints.update(id, valueKey, text, comment);
        basicPanel.reloadList();
        editWindow.setVisible(false);
    }
}



