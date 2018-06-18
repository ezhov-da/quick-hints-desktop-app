package ru.ezhov.regularexpression;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import ru.ezhov.regularexpression.frame.BasicWindow;
import ru.ezhov.regularexpression.frame.SettingsFrame;
import ru.ezhov.regularexpression.frame.SingletonBasicWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RunApplicationTray {
    private TrayIcon trayIcon;
    private Provider provider;
    private TreatmentHotKey treatmentHotKey;

    public void runTray() throws AWTException {
        trayIcon = new TrayIcon(SettingsFrame.TRAY_ICON, "fast paste");
        if (SystemTray.isSupported()) {
            setProvider();
            trayIcon.addMouseListener(getListenerMouse());
            treatmentHotKey = new TreatmentHotKey();
            SystemTray.getSystemTray().add(trayIcon);
        }
    }

    private void showFrame(int x, int y) {
        BasicWindow window = SingletonBasicWindow.getInstance();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rect = ge.getMaximumWindowBounds();
        window.setVisible(!window.isVisible());
    }

    private MouseAdapter getListenerMouse() {
        MouseAdapter mouseAdapter = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    showFrame(e.getX(), e.getY());
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    stopProvider();
                    System.exit(0);
                }
            }
        };
        return mouseAdapter;
    }

    private void setProvider() {
        HotKeyListener hotKeyListener = getHotKeyListener();
        provider = Provider.getCurrentProvider(true);
        for (int i = 0; i < 10; i++) {
            provider.register(KeyStroke.getKeyStroke("ctrl alt NUMPAD" + i), hotKeyListener);
        }
        provider.register(KeyStroke.getKeyStroke("ctrl alt SPACE"), hotKeyListener);
    }

    private HotKeyListener getHotKeyListener() {
        HotKeyListener hotKeyListener = new HotKeyListener() {
            @Override
            public void onHotKey(HotKey hotkey) {
                if (hotkey.keyStroke.getKeyCode() == 32) {
                    BasicWindow frame = SingletonBasicWindow.getInstance();
                    frame.setVisible(true);
                } else {
                    int key = hotkey.keyStroke.getKeyCode() - 96;
                    treatmentHotKey.getTextHotKey(key);
                }
            }
        };
        return hotKeyListener;
    }

    private void stopProvider() {
        provider.reset();
        provider.stop();
    }
}


