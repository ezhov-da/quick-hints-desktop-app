package ru.ezhov.regularexpression.frame;

/**
 * @author RRNDeonisiusEZH
 */
public class SingletonLookAndDeleteWindow {
    private static LookAndDeletePanel addWindow;

    private SingletonLookAndDeleteWindow() {
    }

    public static LookAndDeletePanel getInstance(int x, int y) {
        if (addWindow == null) addWindow = new LookAndDeletePanel();
        addWindow.setLocation(x, y);
        return addWindow;
    }
}