package ru.ezhov.regularexpression.frame;

import ru.ezhov.regularexpression.RegularExpression;

import javax.swing.*;
import java.awt.*;

/**
 * @author RRNDeonisiusEZH
 */
public class SettingsFrame {
	public static final Icon CLOSE = new ImageIcon(RegularExpression.class.getResource("/close.png"));
	public static final Icon HIDE = new ImageIcon(RegularExpression.class.getResource("/minimize.png"));
	public static final Icon BACK = new ImageIcon(RegularExpression.class.getResource("/back.png"));
	public static final Icon DELETE = new ImageIcon(RegularExpression.class.getResource("/delete_16x16.png"));
	public static final Image TRAY_ICON = new ImageIcon(RegularExpression.class.getResource("/fast.png")).getImage();
	public static final int STEP_SHOW = 20;
	public static final int MILLISECONDS = 1;
	private final static Insets INSETS = new Insets(3, 3, 3, 3);
	public static final Insets INSETS_COMPONENT = INSETS;
}
