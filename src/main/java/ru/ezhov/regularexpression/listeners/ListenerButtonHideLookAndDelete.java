package ru.ezhov.regularexpression.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import ru.ezhov.regularexpression.frame.LookAndDeletePanel;
import ru.ezhov.regularexpression.frame.SettingsFrame;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonLookAndDeleteWindow;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonHideLookAndDelete extends MouseAdapter {
private Timer timer;
private final int STEP = SettingsFrame.STEP_SHOW;
private final int MILLISECONDS = SettingsFrame.MILLISECONDS;
private static boolean flagClick;


    @Override
    public void mouseReleased(MouseEvent e) {
        if (flagClick) return;
            LookAndDeletePanel lookAndDeletePanel = 
                         SingletonLookAndDeleteWindow.getInstance(
                                                                                 SingletonBasicPanel.getInstance().getList().getLocationOnScreen().x,
                                                                                     SingletonBasicPanel.getInstance().getScrollPane().getLocationOnScreen().y+3 );
               
               flagClick = true;                                                                                     
               timer = new Timer(MILLISECONDS,  getActionListenerHide(lookAndDeletePanel));

               timer.start();
    }
    
    
    
    
private ActionListener getActionListenerHide(final LookAndDeletePanel lookAndDeletePanel){
    ActionListener actionListenerHide = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int i;
                     i = lookAndDeletePanel.getWidth();
                     i-=STEP;
                    lookAndDeletePanel.setSize(i, lookAndDeletePanel.getHeight());
                                    if (lookAndDeletePanel.getWidth()<0){
                                                    lookAndDeletePanel.setVisible(false);
                                                    flagClick = false;
                                                     timer.stop();
                                    }        
    }
    };
    
    return actionListenerHide;
}    
}
