package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.domain.Hint;
import ru.ezhov.regularexpression.frame.BasicPanel;
import ru.ezhov.regularexpression.frame.EditWindow;
import ru.ezhov.regularexpression.frame.SettingsFrame;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonCorrectWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonCorrect extends MouseAdapter {
    private static boolean flagClick;
    private final int STEP = SettingsFrame.STEP_SHOW;
    private final int MILLISECONDS = SettingsFrame.MILLISECONDS;
    private Timer timer;

    @Override
    public void mouseReleased(MouseEvent e) {
        refresh();
        EditWindow editWindow = SingletonCorrectWindow.getInstance();
        BasicPanel basicPanel = SingletonBasicPanel.getInstance();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        editWindow.setSize(
                new Dimension(
                        dimension.width * 60 / 100,
                        dimension.height * 60 / 100
                )
        );
        editWindow.setLocationRelativeTo(basicPanel);
        editWindow.setVisible(true);
    }

    private void refresh() {
        JList list = SingletonBasicPanel.getInstance().getList();
        Hint hint = (Hint) list.getSelectedValue();
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
        textField.setText(hint.getDescription());
        textPane.setText(hint.getText());
        if (hint.getIdKey() != null) {
            comboBox.setSelectedItem(hint.getIdKey());
        }
    }
}
