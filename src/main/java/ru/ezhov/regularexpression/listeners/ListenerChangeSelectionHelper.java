/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.listeners;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ru.ezhov.regularexpression.ApplicationObject;
import ru.ezhov.regularexpression.frame.SingletonJFrameHelper;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ListenerChangeSelectionHelper implements ListSelectionListener{

    @Override
    public void valueChanged(ListSelectionEvent e) {
        final ApplicationObject applicationObject = 
                (ApplicationObject) SingletonJFrameHelper.getInstance().getListHelper().getSelectedValue();
        
        final JLabel label = SingletonJFrameHelper.getInstance().getLabelInfo();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                label.setText(applicationObject.getDescription());
            }
        });
    }
    
}
