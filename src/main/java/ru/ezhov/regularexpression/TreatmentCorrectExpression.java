package ru.ezhov.regularexpression;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import ru.ezhov.regularexpression.frame.CorrectWindow;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonCorrectWindow;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class TreatmentCorrectExpression {
            public static void refresh(){
                        JList list = SingletonBasicPanel.getInstance().getList();
                        ApplicationObject applicationObject = (ApplicationObject)list.getSelectedValue();
                        SingletonBasicPanel.getInstance().getInfo().setText("");
                       
                        CorrectWindow correctWindow = SingletonCorrectWindow.getInstance();
                        
                        JComboBox comboBox = correctWindow.getComboBox();
                        JTextField textField = correctWindow.getComment();
                        JTextPane textPane = correctWindow.getText();
                        
                        
                        if (list.isSelectionEmpty())   {
                                textField.setText("");
                                 textPane.setText("");
                                 comboBox.setSelectedItem("-");
                            return;
                        }
                        
                       
                        
                        textField.setText(applicationObject.getDescription());
                        textPane.setText(applicationObject.getText());
                        
                        if  (applicationObject.getIdKey()!=null){
                                comboBox.setSelectedItem(applicationObject.getIdKey());
                        }
            }
}
