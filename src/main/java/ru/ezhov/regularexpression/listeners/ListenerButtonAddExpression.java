/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.domain.AddHintException;
import ru.ezhov.regularexpression.domain.Hints;
import ru.ezhov.regularexpression.domain.NewHint;
import ru.ezhov.regularexpression.frame.SingletonAddWindow;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonAddExpression extends MouseAdapter {

    @Override
    public void mouseReleased(MouseEvent e) {
        if (ListenerChangeText.isWrong()) return;
        String text = SingletonAddWindow.getInstance().getText().getText();
        String comment = SingletonAddWindow.getInstance().getComment().getText();
        Hints hints = new Hints();
        try {
            hints.add(new NewHint(text, comment));
            SingletonBasicPanel.getInstance().reloadList();
            SingletonAddWindow.getInstance().setVisible(false);
        } catch (AddHintException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "При добавлении подсказки возникла ошибка",
                    "Ошибка добавления подсказки",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

}
