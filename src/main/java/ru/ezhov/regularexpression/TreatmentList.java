/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression;

import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import ru.ezhov.regularexpression.frame.BasicPanel;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class TreatmentList {
                public static void setList(final Vector<ApplicationObject> vector){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            BasicPanel basicPanel = SingletonBasicPanel.getInstance();
                            JList list = basicPanel.getList();
                            DefaultListModel defaultListModel = (DefaultListModel)list.getModel();
                            for (ApplicationObject applicationObject : vector){
                                defaultListModel.addElement(applicationObject);
                            }
                        }
                    });
                }
                
                public static void clearList(){
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {                    
                            BasicPanel basicPanel = SingletonBasicPanel.getInstance();
                            JList list = basicPanel.getList();
                            DefaultListModel defaultListModel = (DefaultListModel)list.getModel();
                            defaultListModel.removeAllElements();
                        }
                    });                            
                }                
}
