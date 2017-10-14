package ru.ezhov.regularexpression.listeners;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import ru.ezhov.regularexpression.frame.AddWindow;
import ru.ezhov.regularexpression.frame.BasicPanel;
import ru.ezhov.regularexpression.frame.CorrectWindow;
import ru.ezhov.regularexpression.frame.SettingsFrame;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonCorrectWindow;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonCorrect extends MouseAdapter{
private Timer timer;
private final int STEP = SettingsFrame.STEP_SHOW;
private final int MILLISECONDS = SettingsFrame.MILLISECONDS;
private static boolean flagClick;




    @Override
    public void mouseReleased(MouseEvent e) {
        if (flagClick) return;
                            CorrectWindow correctWindow = SingletonCorrectWindow.getInstance();
                                BasicPanel basicPanel = SingletonBasicPanel.getInstance();
                                correctWindow.setSize(basicPanel.getCorrect().getWidth(), CorrectWindow.SIZE_HEIGTH);
                                Point point = basicPanel.getCorrect().getLocationOnScreen();
                                //correctWindow.setLocation(point.x,point.y-CorrectWindow.SIZE_HEIGTH-CorrectWindow.MINUS_HEIGTH);
                            
     flagClick = true;
                        if (correctWindow.isVisible()){
                                  timer = new Timer(MILLISECONDS, getActionListenerHide(point));
                        }  else {
                                   correctWindow.setSize(correctWindow.getWidth(), 0);
                                   correctWindow.setVisible(true);
                                  timer = new Timer(MILLISECONDS, getActionListenerShow(point));
                        }                    
        
        timer.start();
    }
    
    
 
private ActionListener getActionListenerHide(final Point point){
                                ActionListener actionListenerHide = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                CorrectWindow correctWindow = SingletonCorrectWindow.getInstance();
                                    int i;
                                                 i = correctWindow.getHeight();
                                                 i-=STEP;
                                                correctWindow.setSize(correctWindow.getWidth(), i);
                                                correctWindow.setLocation(point.x,point.y-correctWindow.getHeight()-CorrectWindow.MINUS_HEIGTH);
                                                                if (correctWindow.getHeight() <0){
                                                                                correctWindow.setVisible(false);
                                                                                flagClick = false;
                                                                                 timer.stop();
                                                                }        
                                }
                            };
    
    return actionListenerHide;
}
    



private ActionListener getActionListenerShow(final Point point){
      ActionListener actionListenerShow = new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                CorrectWindow correctWindow = SingletonCorrectWindow.getInstance();
                                    int i;
                                                 i = correctWindow.getHeight();
                                                 i+=STEP;
                                                correctWindow.setSize(correctWindow.getWidth(), i);
                                                correctWindow.setLocation(point.x,point.y-correctWindow.getHeight()-CorrectWindow.MINUS_HEIGTH);                                                                                            
                                                                if (correctWindow.getHeight()>AddWindow.SIZE_HEIGTH){
                                                                                correctWindow.setSize(correctWindow.getWidth(), correctWindow.getHeight()-CorrectWindow.MINUS_HEIGTH);
                                                                                correctWindow.setLocation(point.x,point.y-correctWindow.getHeight()-CorrectWindow.MINUS_HEIGTH);  
                                                                                flagClick = false;
                                                                                 timer.stop();
                                                                }        
                                }
                            };
    
    return actionListenerShow;    
}        
}
