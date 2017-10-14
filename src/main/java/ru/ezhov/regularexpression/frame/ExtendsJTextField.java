/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JTextField;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ExtendsJTextField extends JTextField {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setFont(SettingsFrame.LIST_BASIC_FONT);
        if (getText().length() == 0){
            graphics2D.setPaint(Color.GRAY);
            graphics2D.drawString("Начните вводить текст для поиска...", 8, 15);
        }
    }
    
}
