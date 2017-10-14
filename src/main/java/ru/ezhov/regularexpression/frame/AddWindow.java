package ru.ezhov.regularexpression.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import ru.ezhov.regularexpression.listeners.ListenerButtonAddExpression;
import ru.ezhov.regularexpression.listeners.ListenerChangeText;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class AddWindow extends JWindow {
private static  final int PLUS_HEIGTH =   3;
public static  final int SIZE_HEIGTH =   248;
    
private JPanel panel;
private JTextField comment;
private JTextPane text;
private JScrollPane scrollPane;
private BasicButton basicButtonAdd;
    
    
    public AddWindow(JFrame frame) {
        super(frame);
        init();
    }
        
    
   private void init(){
       BasicPanel basicPanel = SingletonBasicPanel.getInstance();
       /* width this  window*/
       setSize(basicPanel.getAdd().getWidth(), SIZE_HEIGTH);
       setLayout(new BorderLayout());
       Point point = basicPanel.getAdd().getLocationOnScreen();
       setLocation(point.x,point.y+basicPanel.getAdd().getHeight()+PLUS_HEIGTH);
        setFocusableWindowState(true);
       
       /*init components*/
       panel = new JPanel(new GridBagLayout());
       comment = new JTextField(40);
       text = new JTextPane();
       basicButtonAdd = new BasicButton("add expression");
       scrollPane = new JScrollPane();
       scrollPane.setViewportView(text);
       
       /*set backgroung*/
       panel.setBackground(SettingsFrame.PANEL_BASIC_COLOR);
       basicButtonAdd.setBackground(SettingsFrame.BUTTON_BASIC_COLOR);
       
       basicButtonAdd.setFont(SettingsFrame.BUTTON_BASIC_FONT);
       comment.setFont(SettingsFrame.LIST_BASIC_FONT);
       text.setFont(SettingsFrame.LIST_BASIC_FONT);
       basicButtonAdd.setForeground(SettingsFrame.BUTTON_BASIC_FOREGROUND);
       
       panel.setBorder(BorderFactory.createLineBorder(Color.black));
       
       basicButtonAdd.addMouseListener(new ListenerButtonAddExpression());
       text.addCaretListener(new ListenerChangeText("TEXT"));
       comment.addCaretListener(new ListenerChangeText("DESCRIPTION"));
       
                                    /*set aligment*/
                                    GridBagConstraints constraints = new GridBagConstraints();

                                    constraints.anchor = GridBagConstraints.NORTH;
                                    constraints.fill = GridBagConstraints.BOTH;                                    
                                    constraints.gridx = 1;
                                    constraints.gridy = 1;
                                    constraints.weightx = 1;
                                    constraints.weighty = 0;
                                    constraints.insets = SettingsFrame.INSETS_COMPONENT; 
                                    panel.add(comment,constraints);
                                    
                                    constraints.anchor = GridBagConstraints.CENTER;
                                    constraints.fill = GridBagConstraints.BOTH;
                                    constraints.gridx = 1;
                                    constraints.gridy = 2;
                                    constraints.weightx = 1;
                                    constraints.weighty = 1;
                                    constraints.insets =SettingsFrame.INSETS_COMPONENT; 
                                    panel.add(scrollPane,constraints);
       
                                    constraints.anchor = GridBagConstraints.SOUTH;
                                    constraints.gridx = 1;
                                    constraints.gridy = 3;
                                    constraints.weightx = 1;
                                    constraints.weighty = 0;
                                    constraints.insets =SettingsFrame.INSETS_COMPONENT; 
                                    panel.add(basicButtonAdd,constraints);
                                    
        add(panel, BorderLayout.CENTER);
   } 

    public JTextField getComment() {
        return comment;
    }

    public JTextPane getText() {
        return text;
    }

    public BasicButton getBasicButtonAdd() {
        return basicButtonAdd;
    }
   
   
   
}
