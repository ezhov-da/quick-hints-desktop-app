/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.TreatmentHotKey;
import ru.ezhov.regularexpression.domain.Hint;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonBasicWindow;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerListHelperKey extends KeyAdapter {
    private int selectionIndex;

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                SingletonBasicWindow.getInstance().setVisible(false);
                break;

            case KeyEvent.VK_ENTER:
                Hint hint = (Hint) SingletonBasicPanel.getInstance().getList().getSelectedValue();
                if (hint != null) {
                    new TreatmentHotKey().getTextFromList(hint);
                    SingletonBasicWindow.getInstance().setVisible(false);
                }
                break;

            case KeyEvent.VK_UP:
                selectionIndex = SingletonBasicPanel.getInstance().getList().getSelectedIndex();
                if (selectionIndex == 0) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            SingletonBasicPanel.getInstance().getList().setSelectedIndex(
                                    SingletonBasicPanel.getInstance().getList().getModel().getSize() - 1);
                        }
                    });
                }
                break;

            case KeyEvent.VK_DOWN:
                selectionIndex = SingletonBasicPanel.getInstance().getList().getSelectedIndex();
                if (selectionIndex == SingletonBasicPanel.getInstance().getList().getModel().getSize() - 1) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            SingletonBasicPanel.getInstance().getList().setSelectedIndex(0);
                        }
                    });
                }
                break;
        }
    }

}





