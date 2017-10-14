/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import ru.ezhov.regularexpression.listeners.ListenerButtonCorrectExpression;
import ru.ezhov.regularexpression.listeners.ListenerChangeText;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class CorrectWindow extends JWindow {
public static  final int MINUS_HEIGTH =   3;
public static  final int SIZE_HEIGTH =   248;
    
private JPanel panel;
private final JComboBox comboBox;
private JTextField comment;
private JTextPane text;
private JScrollPane scrollPaneText;
private BasicButton basicButtonCorrect;

/*init list*/
{
        comboBox = new JComboBox(new String[] {"-","0","1","2","3","4","5","6","7","8","9"});
    comboBox.setMaximumRowCount(11);
}
    



    public CorrectWindow(JFrame frame) {
        super(frame);
        init();
    }
        
    
   private void init(){
       BasicPanel basicPanel = SingletonBasicPanel.getInstance();
       /* width this  window*/
       setSize(basicPanel.getCorrect().getWidth(), SIZE_HEIGTH);
       setLayout(new BorderLayout());
       Point point = basicPanel.getCorrect().getLocationOnScreen();
       setLocation(point.x,point.y-SIZE_HEIGTH-MINUS_HEIGTH);
       setFocusableWindowState(true);
       
       
       /*init components*/
       panel = new JPanel(new GridBagLayout());
       comment = new JTextField(40);
       text = new JTextPane();
       basicButtonCorrect = new BasicButton("correct expression");
       scrollPaneText = new JScrollPane();
       scrollPaneText.setViewportView(text);

       
       /*set backgroung*/
       panel.setBackground(SettingsFrame.PANEL_BASIC_COLOR);
       basicButtonCorrect.setBackground(SettingsFrame.BUTTON_BASIC_COLOR);
       
       basicButtonCorrect.setFont(SettingsFrame.BUTTON_BASIC_FONT);
        comment.setFont(SettingsFrame.LIST_BASIC_FONT);
        text.setFont(SettingsFrame.LIST_BASIC_FONT);
        comboBox.setFont(SettingsFrame.LIST_BASIC_FONT);
       basicButtonCorrect.setForeground(SettingsFrame.BUTTON_BASIC_FOREGROUND);
       
       panel.setBorder(BorderFactory.createLineBorder(Color.black));
       
       basicButtonCorrect.addMouseListener(new ListenerButtonCorrectExpression());
        text.addCaretListener(new ListenerChangeText("TEXT"));
        comment.addCaretListener(new ListenerChangeText("DESCRIPTION"));
                                    /*set aligment*/
                                    GridBagConstraints constraints = new GridBagConstraints();

                                    constraints.gridx = 1;
                                    constraints.gridy = 1;         
                                    constraints.gridwidth = 1;
                                    constraints.gridheight = 1;
                                    constraints.weightx = 0;
                                    constraints.weighty = 0;                                       
                                    constraints.anchor = GridBagConstraints.NORTH;   
                                    constraints.fill = GridBagConstraints.NONE;
                                    constraints.insets =SettingsFrame.INSETS_COMPONENT; 
                                    panel.add(comboBox,constraints);
                                    
                                    
                                    constraints.gridx = 2;
                                    constraints.gridy = 1;              
                                    constraints.gridwidth = 1;
                                    constraints.gridheight = 1;   
                                    constraints.weightx = 1;
                                    constraints.weighty = 0;                                       
                                    constraints.anchor = GridBagConstraints.NORTH;
                                    constraints.fill = GridBagConstraints.HORIZONTAL;  
                                    constraints.insets =SettingsFrame.INSETS_COMPONENT; 
                                    panel.add(comment,constraints);
                                    
                                    constraints.gridx = 1;
                                    constraints.gridy = 2;     
                                    constraints.gridwidth = 2;
                                    constraints.gridheight =  GridBagConstraints.RELATIVE;
                                    constraints.weightx = 1;
                                    constraints.weighty = 1;                                    
                                    constraints.anchor = GridBagConstraints.CENTER;
                                    constraints.fill = GridBagConstraints.BOTH;
                                    constraints.insets = SettingsFrame.INSETS_COMPONENT; 
                                    panel.add(scrollPaneText,constraints);
       
                                    constraints.gridx = 1;
                                    constraints.gridy = 3;     
                                    constraints.gridwidth = 2;
                                    constraints.gridheight =  1;
                                    constraints.weightx = 1;
                                    constraints.weighty = 0;                                    
                                   constraints.anchor = GridBagConstraints.SOUTH;
                                   constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.insets = SettingsFrame.INSETS_COMPONENT; 
                                   panel.add(basicButtonCorrect,constraints);
                                    
        add(panel, BorderLayout.CENTER);
   } 

    public JComboBox getComboBox() {
        return comboBox;
    }

    public JTextField getComment() {
        return comment;
    }

    public JTextPane getText() {
        return text;
    }

    public BasicButton getBasicButtonCorrect() {
        return basicButtonCorrect;
    }
   
   
}

