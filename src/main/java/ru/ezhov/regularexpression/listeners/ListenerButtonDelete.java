package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.domain.DeleteHintException;
import ru.ezhov.regularexpression.domain.Hint;
import ru.ezhov.regularexpression.domain.Hints;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonDelete extends MouseAdapter {
    private static int index;


    //TODO: переписать, так как происходит адская работа
    @Override
    public void mouseReleased(MouseEvent e) {
        Hints hints = new Hints();
        final JList list = SingletonBasicPanel.getInstance().getList();
        if (list.getSelectedIndex() == -1) return;
        index = list.getSelectedIndex();
        Hint hint = (Hint) list.getSelectedValue();
        try {
            hints.remove(hint.getId());
            if (list.indexToLocation(index) == null) {
                if (list.indexToLocation(list.getModel().getSize() - 1) == null) {
                    return;
                } else {
                    index = list.getModel().getSize() - 1;
                }
            }
            SingletonBasicPanel.getInstance().reloadList();
            SwingUtilities.invokeLater(() -> list.setSelectedIndex(index));
        } catch (DeleteHintException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "Не удалось удалить запись",
                    "Ошибка удаления",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
