package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.*;
import ru.ezhov.regularexpression.frame.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

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
		TreatmentCorrectExpression.refresh();
		JList list = SingletonBasicPanel.getInstance().getList();
		int index = list.getSelectedIndex();
		FillText.fillText(index, lookAndDeletePanel);
	}
}






        

    
    
    
    

    
    
    