/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.domain.Hint;
import ru.ezhov.regularexpression.frame.SingletonJFrameHelper;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerChangeSelectionHelper implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
        final Hint hint =
                (Hint) SingletonJFrameHelper.getInstance().getListHelper().getSelectedValue();

        final JLabel label = SingletonJFrameHelper.getInstance().getLabelInfo();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                label.setText(hint.getDescription());
            }
        });
    }

}
