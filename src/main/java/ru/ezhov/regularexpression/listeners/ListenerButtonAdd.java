package ru.ezhov.regularexpression.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import ru.ezhov.regularexpression.frame.SingletonAddWindow;
import ru.ezhov.regularexpression.frame.AddWindow;
import ru.ezhov.regularexpression.frame.SettingsFrame;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonAdd extends MouseAdapter{
private Timer timer;
private final int STEP = SettingsFrame.STEP_SHOW;
private final int MILLISECONDS = SettingsFrame.MILLISECONDS;
private static boolean flagClick;
    
    @Override
    public void mouseReleased(MouseEvent e) {        
        if (flagClick) return;
        
        
     AddWindow addWindow = SingletonAddWindow.getInstance();
     flagClick = true;
                        if (addWindow.isVisible()){
                                  timer = new Timer(MILLISECONDS, getActionListenerHide());
                        }  else {
                                   addWindow.setSize(addWindow.getWidth(), 0);
                                    addWindow.setVisible(true);
                                  timer = new Timer(MILLISECONDS, getActionListenerShow());
                        }                    
        
        timer.start();
    }
    
    
    
    
private ActionListener getActionListenerHide(){
                                ActionListener actionListenerHide = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                AddWindow addWindow = SingletonAddWindow.getInstance();
                                    int i;
                                                 i = addWindow.getHeight();
                                                 i-=STEP;
                                                addWindow.setSize(addWindow.getWidth(), i);
                                                                if (addWindow.getHeight() <0){
                                                                                addWindow.setVisible(false);
                                                                                flagClick = false;
                                                                                 timer.stop();
                                                                }        
                                }
                            };
    
    return actionListenerHide;
}
    



private ActionListener getActionListenerShow(){
      ActionListener actionListenerShow = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                AddWindow addWindow = SingletonAddWindow.getInstance();
                                    int i;
                                                 i = addWindow.getHeight();
                                                 i+=STEP;
                                                addWindow.setSize(addWindow.getWidth(), i);
                                                                if (addWindow.getHeight()>AddWindow.SIZE_HEIGTH){
                                                                                addWindow.setSize(addWindow.getWidth(), addWindow.getHeight());
                                                                                flagClick = false;
                                                                                 timer.stop();
                                                                }        
                                }
                            };
    
    return actionListenerShow;    
}    
    
    
    
    
    
    
}
