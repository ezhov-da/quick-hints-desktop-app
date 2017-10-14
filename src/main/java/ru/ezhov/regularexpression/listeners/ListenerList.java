package ru.ezhov.regularexpression.listeners;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import ru.ezhov.regularexpression.FillText;
import ru.ezhov.regularexpression.TreatmentCorrectExpression;
import ru.ezhov.regularexpression.TreatmentHotKey;
import ru.ezhov.regularexpression.frame.LookAndDeletePanel;
import ru.ezhov.regularexpression.frame.SettingsFrame;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonLookAndDeleteWindow;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ListenerList extends MouseAdapter implements ListSelectionListener {
private Timer timer;
private final int STEP = SettingsFrame.STEP_SHOW;
private final int MILLISECONDS = SettingsFrame.MILLISECONDS;
private static boolean flagClick;
    
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton()==MouseEvent.BUTTON3){
              if (flagClick) return;
                        showDetailWindow(e); 
        } else if (e.getButton()==MouseEvent.BUTTON1){
            new TreatmentHotKey().getTextFromList(SingletonBasicPanel.getInstance().getList());
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
            TreatmentCorrectExpression.refresh();
                    JList list = SingletonBasicPanel.getInstance().getList();
                    int index = list.getSelectedIndex(); 
            FillText.fillText(index);
    }
    
    private void showDetailWindow(MouseEvent e){                
               LookAndDeletePanel lookAndDeletePanel = 
                                            SingletonLookAndDeleteWindow.getInstance(
                                                                                                    SingletonBasicPanel.getInstance().getList().getLocationOnScreen().x,
                                                                                                        SingletonBasicPanel.getInstance().getScrollPane().getLocationOnScreen().y+3 );

                    lookAndDeletePanel.setSize(0, lookAndDeletePanel.getHeight());
                    lookAndDeletePanel.setVisible(true);
                   timer = new Timer(MILLISECONDS, getActionListenerShow(lookAndDeletePanel));            
        
        timer.start();
    }
    
private ActionListener getActionListenerShow(final LookAndDeletePanel lookAndDeletePanel){
      ActionListener actionListenerShow = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                             i = lookAndDeletePanel.getWidth();
                             i+=STEP;
                            lookAndDeletePanel.setSize(i,lookAndDeletePanel.getHeight());
                                            if (lookAndDeletePanel.getWidth() > SingletonBasicPanel.getInstance().getScrollPane().getWidth()){
                                                            lookAndDeletePanel.setSize(
                                                                SingletonBasicPanel.getInstance().getScrollPane().getWidth()
                                                                , SingletonBasicPanel.getInstance().getList().getHeight()
                                                            );
                                                            flagClick = false;
                                                             timer.stop();
                                            }        
            }
        };
    
    return actionListenerShow;    
}    
        
}






        

    
    
    
    

    
    
    