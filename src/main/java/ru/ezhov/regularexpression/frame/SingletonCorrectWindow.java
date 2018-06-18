package ru.ezhov.regularexpression.frame;

/**
 * @author RRNDeonisiusEZH
 */
public class SingletonCorrectWindow {
    private static EditWindow editWindow;

    private SingletonCorrectWindow() {
    }

    public static EditWindow getInstance() {
        if (editWindow == null) editWindow = new EditWindow(SingletonBasicWindow.getInstance());

        return editWindow;
    }
}
