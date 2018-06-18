package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.FillText;
import ru.ezhov.regularexpression.frame.LookAndDeletePanel;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerList extends MouseAdapter implements ListSelectionListener {

    private LookAndDeletePanel lookAndDeletePanel;

    public ListenerList(LookAndDeletePanel lookAndDeletePanel) {
        this.lookAndDeletePanel = lookAndDeletePanel;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//		if (e.getButton() == MouseEvent.BUTTON3) {
//			if (flagClick) return;
//			showDetailWindow(e);
//		} else if (e.getButton() == MouseEvent.BUTTON1) {
//			new TreatmentHotKey().getTextFromList(SingletonBasicPanel.getInstance().getList());
//		}
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList list = SingletonBasicPanel.getInstance().getList();
        int index = list.getSelectedIndex();
        FillText.fillText(index, lookAndDeletePanel);
    }
}






        

    
    
    
    

    
    
    