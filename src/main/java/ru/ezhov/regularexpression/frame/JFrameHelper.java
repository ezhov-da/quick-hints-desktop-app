/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import ru.ezhov.regularexpression.listeners.ListenerChangeSelectionHelper;
import ru.ezhov.regularexpression.listeners.ListenerListHelperKey;
import ru.ezhov.regularexpression.listeners.ListenerListHelperMouse;
import ru.ezhov.regularexpression.listeners.ListenerTextFieldSearch;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class JFrameHelper extends JDialog {
    private JList listHelper;
    private ExtendsJTextField textFieldSearch;
    private JLabel labelInfo;
    
    
    public JFrameHelper(){
        initFrame();
        setListeners();
    }
    
    private  void initFrame(){
        BasicPanel basicPanel = SingletonBasicPanel.getInstance();
        JList list = basicPanel.getList();
        listHelper = new JList();
        listHelper.setFont(SettingsFrame.LIST_BASIC_FONT);
        listHelper.setModel(list.getModel());
        
        textFieldSearch = new ExtendsJTextField();
        textFieldSearch.setFont(SettingsFrame.LIST_BASIC_FONT);
        labelInfo = new JLabel();
        labelInfo.setFont(SettingsFrame.BUTTON_BASIC_FONT);
        labelInfo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3),
                BorderFactory.createLineBorder(Color.BLACK)));
        
        add(textFieldSearch, BorderLayout.NORTH);
        add(new JScrollPane(listHelper), BorderLayout.CENTER);
        add(labelInfo, BorderLayout.SOUTH);
        
        setIconImage(SettingsFrame.TRAY_ICON);
        setSize(400, 500);
        setUndecorated(true);
    }
    
    private void setListeners(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    JFrameHelper frameHelper = (JFrameHelper) e.getSource();
                    frameHelper.setVisible(false);
                }
            }
        });
        
        textFieldSearch.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() ==  KeyEvent.VK_ESCAPE){
                        SingletonJFrameHelper.getInstance().setVisible(false);
                }
            }
        });
        
        listHelper.addKeyListener(new ListenerListHelperKey());
        listHelper.addMouseListener(new ListenerListHelperMouse());
        listHelper.addListSelectionListener(new ListenerChangeSelectionHelper());
        
        textFieldSearch.addCaretListener(new ListenerTextFieldSearch());
    }

    public JList getListHelper() {
        return listHelper;
    }

    public ExtendsJTextField getTextFieldSearch() {
        return textFieldSearch;
    }

    public JLabel getLabelInfo() {
        return labelInfo;
    }
    
    
}
