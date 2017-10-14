package ru.ezhov.regularexpression.frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import ru.ezhov.regularexpression.SettingsApplication;
import ru.ezhov.regularexpression.listeners.ListenerButtonAdd;
import ru.ezhov.regularexpression.listeners.ListenerButtonCorrect;
import ru.ezhov.regularexpression.listeners.ListenerList;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class BasicPanel extends JPanel{
private  JLabel pattern;
private  JLabel version;
private BasicButton hide;
private BasicButton add;
private BasicButton correct;
private JList list;    
private JScrollPane scrollPane;
/**
 * @deprecated 
 */
private JLabel info;


    public BasicPanel() {
        init();
    }
    
    
    private void init(){
            setLayout(new GridBagLayout());
            setBorder(BorderFactory.createLineBorder(Color.black));
            setBackground(SettingsFrame.PANEL_BASIC_COLOR);
            setCursor(new  Cursor(Cursor.HAND_CURSOR));
            
            /*initial varible*/
            version = new JLabel(SettingsApplication.APP_VERSION);
            hide = new BasicButton(SettingsFrame.HIDE);
            add = new BasicButton("add");
            correct = new BasicButton("correct");            
            list = new JList();
            scrollPane = new JScrollPane();
            scrollPane.setViewportView(list);
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            info = new JLabel("");
            pattern = new JLabel(" pattern for replace ---> " + SettingsApplication.PATTERN_REPLACE);
            /*set background*/
            hide.setBackground(SettingsFrame.BUTTON_BASIC_COLOR);
            add.setBackground(SettingsFrame.BUTTON_BASIC_COLOR);
            correct.setBackground(SettingsFrame.BUTTON_BASIC_COLOR);
            version.setBackground(SettingsFrame.PANEL_BASIC_COLOR);
            
            /*set font*/
            add.setFont(SettingsFrame.BUTTON_BASIC_FONT);
            correct.setFont(SettingsFrame.BUTTON_BASIC_FONT);
            add.setForeground(SettingsFrame.BUTTON_BASIC_FOREGROUND);
            correct.setForeground(SettingsFrame.BUTTON_BASIC_FOREGROUND);
            list.setFont(SettingsFrame.LIST_BASIC_FONT);
            info.setFont(SettingsFrame.LIST_BASIC_FONT);
            info.setForeground(SettingsFrame.BUTTON_BASIC_FOREGROUND);
            pattern.setFont(SettingsFrame.LIST_BASIC_FONT);
            pattern.setForeground(SettingsFrame.BUTTON_BASIC_FOREGROUND);
            version.setFont(SettingsFrame.LIST_BASIC_FONT);
            version.setForeground(SettingsFrame.BUTTON_BASIC_FOREGROUND);
            
            /*set size*/
            Dimension dimensionHide = new Dimension(25, 25);
            hide.setPreferredSize(dimensionHide);
            hide.setMaximumSize(dimensionHide);
            hide.setMinimumSize(dimensionHide);
            
            
            list.setModel(new DefaultListModel());
            /*add listener*/
                hide.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                                SingletonBasicWindow.getInstance().setVisible(false);
                    }
                });
            
            add.addMouseListener(new ListenerButtonAdd());
            correct.addMouseListener(new ListenerButtonCorrect());
            list.addListSelectionListener(new ListenerList());
            list.addMouseListener(new ListenerList());
            
            
                                    /*set aligment*/
                                    GridBagConstraints constraints = new GridBagConstraints();

                                    constraints.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
                                    constraints.gridx = 1;
                                    constraints.gridy = 1;
                                    constraints.weightx = 0;
                                    constraints.weighty = 0;
                                    constraints.insets = SettingsFrame.INSETS_COMPONENT; 
                                    add(version,constraints);                                    
                                    
                                    constraints.anchor = GridBagConstraints.CENTER;
                                    constraints.gridx = 1;
                                    constraints.gridy = 1;
                                    constraints.weightx = 1;
                                    constraints.weighty = 0;
                                    constraints.insets = SettingsFrame.INSETS_COMPONENT; 
                                    add(pattern,constraints);
                                    
                                    constraints.anchor = GridBagConstraints.NORTHEAST;
                                    constraints.gridx = 1;
                                    constraints.gridy = 1;
                                    constraints.weightx = 0;
                                    constraints.weighty = 0;
                                    constraints.insets = SettingsFrame.INSETS_COMPONENT; 
                                    add(hide,constraints);

                                    constraints.anchor = GridBagConstraints.NORTH;
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.gridx = 1;
                                    constraints.gridy = 2;
                                    constraints.weightx = 1;
                                    constraints.weighty = 0;
                                    constraints.gridwidth = GridBagConstraints.REMAINDER;
                                    constraints.insets = SettingsFrame.INSETS_COMPONENT;
                                    add(add,constraints);

                                    constraints.anchor = GridBagConstraints.NORTH;
                                    constraints.fill = GridBagConstraints.BOTH;
                                    constraints.gridx = 1;
                                    constraints.gridy = 3;
                                    constraints.weightx = 1;
                                    constraints.weighty = 1;
                                    constraints.gridwidth = GridBagConstraints.REMAINDER;
                                    constraints.insets = SettingsFrame.INSETS_COMPONENT;
                                    add(scrollPane,constraints);

                                    constraints.gridx = 1;
                                    constraints.gridy = 4;
                                    constraints.gridwidth = GridBagConstraints.REMAINDER;
                                    constraints.weightx = 1;
                                    constraints.weighty = 0;
                                    constraints.anchor = GridBagConstraints.SOUTH;
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.insets = SettingsFrame.INSETS_COMPONENT;
                                    add(correct,constraints);
                                    
                                    constraints.anchor = GridBagConstraints.SOUTH;
                                    constraints.fill = GridBagConstraints.HORIZONTAL;
                                    constraints.gridx = 1;
                                    constraints.gridy = 5;
                                    constraints.weightx = 1;
                                    constraints.weighty = 0;
                                    constraints.gridwidth = GridBagConstraints.REMAINDER;
                                    constraints.insets = SettingsFrame.INSETS_COMPONENT;
                                    add(info,constraints);
                                    
    }

    public BasicButton getHide() {
        return hide;
    }


    public BasicButton getAdd() {
        return add;
    }

    public BasicButton getCorrect() {
        return correct;
    }

    public JList getList() {
        return list;
    }

    public JLabel getInfo() {
        return info;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
    
    
    
}
