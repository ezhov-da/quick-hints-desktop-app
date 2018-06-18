/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.frame;

import ru.ezhov.regularexpression.listeners.ListenerChangeSelectionHelper;
import ru.ezhov.regularexpression.listeners.ListenerListHelperKey;
import ru.ezhov.regularexpression.listeners.ListenerListHelperMouse;
import ru.ezhov.regularexpression.listeners.ListenerTextFieldSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author RRNDeonisiusEZH
 */
public class JFrameHelper extends JDialog {
    private JList listHelper;
    private TextFieldWithText textFieldSearch;
    private JLabel labelInfo;


    public JFrameHelper() {
        initFrame();
        setListeners();
    }

    private void initFrame() {
        BasicPanel basicPanel = SingletonBasicPanel.getInstance();
        JList list = basicPanel.getList();
        listHelper = new JList();
        listHelper.setModel(list.getModel());

        textFieldSearch = new TextFieldWithText("Начните вводить текст для поиска...");
        labelInfo = new JLabel();
        labelInfo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3),
                BorderFactory.createLineBorder(Color.BLACK)));

        add(textFieldSearch, BorderLayout.NORTH);
        add(new JScrollPane(listHelper), BorderLayout.CENTER);
        add(labelInfo, BorderLayout.SOUTH);

        setIconImage(SettingsFrame.TRAY_ICON);
        setSize(400, 500);
        setUndecorated(true);
    }

    private void setListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    JFrameHelper frameHelper = (JFrameHelper) e.getSource();
                    frameHelper.setVisible(false);
                }
            }
        });

        textFieldSearch.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
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

    public TextFieldWithText getTextFieldSearch() {
        return textFieldSearch;
    }

    public JLabel getLabelInfo() {
        return labelInfo;
    }


}
