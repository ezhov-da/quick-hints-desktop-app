/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class BasicWindow extends JFrame {
public static final int WIDTH_MY = 300;
public static final int HEIGHT_MY = 650;
    
    
    public BasicWindow() {
        initComponents();
    }
    
    private void initComponents(){
                setSize(new Dimension(WIDTH_MY,HEIGHT_MY));
                setLayout(new BorderLayout());
                setIconImage(SettingsFrame.TRAY_ICON);
                setTitle("fast paste");
                setUndecorated(true);
                setAlwaysOnTop(true);
    }
}
