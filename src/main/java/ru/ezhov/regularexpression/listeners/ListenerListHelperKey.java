/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import ru.ezhov.regularexpression.TreatmentHotKey;
import ru.ezhov.regularexpression.frame.SingletonJFrameHelper;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ListenerListHelperKey extends KeyAdapter {
    private int selectionIndex;
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                SingletonJFrameHelper.getInstance().setVisible(false);
            break;
                
            case KeyEvent.VK_ENTER:
                new TreatmentHotKey().getTextFromList(SingletonJFrameHelper.getInstance().getListHelper());
                SingletonJFrameHelper.getInstance().setVisible(false);
            break;
                
            case KeyEvent.VK_UP:
                selectionIndex = SingletonJFrameHelper.getInstance().getListHelper().getSelectedIndex();
                if (selectionIndex == 0){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            SingletonJFrameHelper.getInstance().getListHelper().setSelectedIndex(
                                    SingletonJFrameHelper.getInstance().getListHelper().getModel().getSize() - 1);
                        }
                    });
                }
            break;
                
            case KeyEvent.VK_DOWN:
                selectionIndex = SingletonJFrameHelper.getInstance().getListHelper().getSelectedIndex();
                if (selectionIndex == SingletonJFrameHelper.getInstance().getListHelper().getModel().getSize() - 1){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            SingletonJFrameHelper.getInstance().getListHelper().setSelectedIndex(0);
                        }
                    });
                }
            break;                
        }
    }
    
}





