package ru.ezhov.note.ui;


public class SingletonBasicWindow {
    private static BasicWindow basicWindow;

    private SingletonBasicWindow() {
    }

    public static BasicWindow getInstance() {
        if (basicWindow == null) basicWindow = new BasicWindow();
        return basicWindow;
    }
}
