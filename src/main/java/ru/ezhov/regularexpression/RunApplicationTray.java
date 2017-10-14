package ru.ezhov.regularexpression;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import ru.ezhov.regularexpression.frame.BasicWindow;
import ru.ezhov.regularexpression.frame.JFrameHelper;
import ru.ezhov.regularexpression.frame.SettingsFrame;
import ru.ezhov.regularexpression.frame.SingletonBasicWindow;
import ru.ezhov.regularexpression.frame.SingletonJFrameHelper;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class RunApplicationTray {
    private  TrayIcon trayIcon;
    private Provider provider;
    private TreatmentHotKey treatmentHotKey;
    
    
            public void runTray() throws AWTException{
                     trayIcon = new TrayIcon(SettingsFrame.TRAY_ICON, "fast paste");
                            if ( SystemTray.isSupported()){               
                                setProvider();
                                trayIcon.addMouseListener(getListenerMouse());
                                treatmentHotKey =  new TreatmentHotKey();
                                SystemTray.getSystemTray().add(trayIcon);                  
                            }
                }
            
                
    private void showFrame(int x, int y){
                    BasicWindow window = SingletonBasicWindow.getInstance();
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                    Rectangle rect = ge.getMaximumWindowBounds();   
                    
                    /*x*/
                    if (rect.width<screenSize.width){
                        y = y- BasicWindow.HEIGHT_MY;
                                        if (x<screenSize.width/2){
                                                    x = screenSize.width - rect.width;
                                        } else {
                                                x = rect.width-BasicWindow.WIDTH_MY;
                                        }
                     /*y*/   
                    } else if(rect.height<screenSize.height){
                                        if (y<screenSize.height/2){
                                                    y = screenSize.height - rect.height;
                                        } else {
                                                    y = rect.height-BasicWindow.HEIGHT_MY;
                                        } 
                                        x = rect.width - BasicWindow.WIDTH_MY;
                    }
                    
                    
                    
                    window.setLocation(x, y);
                    window.setVisible(!window.isVisible());
    }        
    
    
    
    private MouseAdapter getListenerMouse(){
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {   
                        if (e.getButton()==MouseEvent.BUTTON1){
                                showFrame( e.getX(),  e.getY());
                        } else if (e.getButton()==MouseEvent.BUTTON3){
                                stopProvider();
                                System.exit(0);
                        }
            }
        };
        return mouseAdapter;
        
    }

     
    private void setProvider(){
                    HotKeyListener hotKeyListener = getHotKeyListener();
                    provider =    Provider.getCurrentProvider(true);       
                    for (int i = 0; i<10; i++){
                        provider.register(KeyStroke.getKeyStroke("ctrl alt NUMPAD" + i), hotKeyListener);       
                    }             
                    provider.register(KeyStroke.getKeyStroke("ctrl alt SPACE"), hotKeyListener);
                    
    }
    
    
    private HotKeyListener getHotKeyListener(){
        HotKeyListener hotKeyListener = new HotKeyListener() {
            @Override
            public void onHotKey(HotKey hotkey) {
                /* 
                 * Здесь мы добавляем проверку на срабатываеи
                 *  ctrl + alt + space - это отображение окна помощника.
                 * В противном случае, выполняем  рабочее сочетание клавиш.
                 */
                if (hotkey.keyStroke.getKeyCode() == 32){
                    JFrameHelper frame = SingletonJFrameHelper.getInstance();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } else {
                    int key = hotkey.keyStroke.getKeyCode() - 96;
                    treatmentHotKey.getTextHotKey(key);  
                }
            }
        };
            return hotKeyListener;        
    }
    

    private void stopProvider(){
            provider.reset();
            provider.stop();
    }
}


