package ru.ezhov.regularexpression.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import ru.ezhov.regularexpression.ApplicationObject;
import ru.ezhov.regularexpression.TreatmentData;
import ru.ezhov.regularexpression.frame.BasicPanel;
import ru.ezhov.regularexpression.frame.CorrectWindow;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;
import ru.ezhov.regularexpression.frame.SingletonCorrectWindow;



/**
 *
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonCorrectExpression extends MouseAdapter {
    
    
    @Override
    public void mouseReleased(MouseEvent e) {     
        if (ListenerChangeText.isWrong()) return;
                CorrectWindow correctWindow = SingletonCorrectWindow.getInstance();
                BasicPanel basicPanel = SingletonBasicPanel.getInstance();
                ApplicationObject applicationObject = (ApplicationObject)basicPanel.getList().getSelectedValue();
                
                if (basicPanel.getList().isSelectionEmpty()) return;
                
                int id = applicationObject.getId();
                String text = correctWindow.getText().getText();
                String comment = correctWindow.getComment().getText();
                String valueKey =(String) correctWindow.getComboBox().getSelectedItem();
                
                TreatmentData treatmentData = new TreatmentData();
                treatmentData.update(id, valueKey, text, comment);
                
                correctWindow.setVisible(false);
    }   
}



