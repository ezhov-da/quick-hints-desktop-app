/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression;

import java.awt.Color;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import ru.ezhov.regularexpression.frame.LookAndDeletePanel;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonLookAndDeleteWindow;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class FillText {
    private static final Logger logger = Logger.getLogger(RegularExpression.class.getName());
       
    
    private static JTextPane textPane;
    
            public static void fillText(int index){
                               LookAndDeletePanel lookAndDeletePanel = 
                                            SingletonLookAndDeleteWindow.getInstance(
                                                                                                    SingletonBasicPanel.getInstance().getList().getLocationOnScreen().x,
                                                                                                        SingletonBasicPanel.getInstance().getScrollPane().getLocationOnScreen().y+3 );
                              

                              JList list = SingletonBasicPanel.getInstance().getList();
                                                         
                              if (index ==-1) {
                                            lookAndDeletePanel.getText().setText("");
                                  return;
                              }
                              
                               ApplicationObject applicationObject = (ApplicationObject)list.getSelectedValue();
                               
                               
                              textPane =  lookAndDeletePanel.getText();
                              textPane.setText("");
                              
                              getDefaultStyledDocument(
                                                applicationObject.getIdKey(),
                                                applicationObject.getDescription(),
                                                applicationObject.getText()
                              );
                              
                              textPane.setCaretPosition(0);
            }
            
            
            
            
            
            
            
    private static  void getDefaultStyledDocument(String idKey, String comment, String text){
         final String TEXT_ID_KEY = "key ->\n".toUpperCase();
         final String TEXT_COMMENT = "comment ->\n".toUpperCase();
         final String TEXT = "text ->\n".toUpperCase();
        
        StyledDocument doc = textPane.getStyledDocument();
        Style styleIdKey = textPane.addStyle("styleIdKey", null);
        Style styleComment = textPane.addStyle("styleComment", null);
        Style styletext = textPane.addStyle("styletext", null);
        
        StyleConstants.setFontSize(styleIdKey, 12);
        StyleConstants.setForeground(styleIdKey, Color.red);
        StyleConstants.setBold(styleIdKey, true);
        
        StyleConstants.setFontSize(styleComment, 12);
        StyleConstants.setForeground(styleComment, Color.blue);
        StyleConstants.setBold(styleComment, true);
        
        StyleConstants.setFontSize(styletext, 12);
        StyleConstants.setForeground(styletext, new Color(218, 165, 32 ));        
        StyleConstants.setBold(styletext, true);
        
        try {
                        doc.insertString(doc.getLength(),TEXT_ID_KEY ,styleIdKey);
                        doc.insertString(doc.getLength(), idKey + "\n",null);
                        doc.insertString(doc.getLength(),TEXT_COMMENT ,styleComment);
                        doc.insertString(doc.getLength(), comment + "\n",null);
                        doc.insertString(doc.getLength(),TEXT,styletext);
                        doc.insertString(doc.getLength(), text + "\n",null);
        } catch (BadLocationException ex) {
                        logger.throwing(FillText.class.getName(), "getDefaultStyledDocument", ex);
        }
        
        
    }            
}
