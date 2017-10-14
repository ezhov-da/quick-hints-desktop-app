package ru.ezhov.regularexpression.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import ru.ezhov.regularexpression.RegularExpression;

/**
 * @author RRNDeonisiusEZH
 */
public class SettingsFrame {
    private final static Insets INSETS = new Insets(3, 3, 3, 3);


    //public static final Color BUTTON_BASIC_COLOR = new Color(101,101,101);
    public static final Color BUTTON_BASIC_COLOR = new Color(0, 139, 139);
    //public static final Color panelBasicColor = new Color(84,84,78);
    public static final Color PANEL_BASIC_COLOR = new Color(169, 169, 169);
    public static final Font BUTTON_BASIC_FONT = new Font("Courier New", Font.BOLD, 14);
    public static final Font LIST_BASIC_FONT = new Font("Courier New", Font.PLAIN, 11);
    public static final Color BUTTON_BASIC_FOREGROUND = Color.WHITE;
    public static final Insets INSETS_COMPONENT = INSETS;
    public static final Icon CLOSE = new ImageIcon(RegularExpression.class.getResource("/close.png"));
    public static final Icon HIDE = new ImageIcon(RegularExpression.class.getResource("/minimize.png"));
    public static final Icon BACK = new ImageIcon(RegularExpression.class.getResource("/back.png"));
    public static final Icon DELETE = new ImageIcon(RegularExpression.class.getResource("/delete.png"));
    public static final Image TRAY_ICON = new ImageIcon(RegularExpression.class.getResource("/fast.png")).getImage();


    public static final int STEP_SHOW = 20;
    public static final int MILLISECONDS = 1;
}
