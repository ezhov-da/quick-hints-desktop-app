/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.TextFieldWithText;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class ListenerTextFieldSearch implements CaretListener {
    @Override
    public void caretUpdate(CaretEvent e) {
        TextFieldWithText textFieldWithText = (TextFieldWithText) e.getSource();
        String text = textFieldWithText.getText();
        SingletonBasicPanel.getInstance().reloadListWithCondition(text);
    }
}
