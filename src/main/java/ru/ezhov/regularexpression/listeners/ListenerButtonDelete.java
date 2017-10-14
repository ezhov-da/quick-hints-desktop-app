package ru.ezhov.regularexpression.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import ru.ezhov.regularexpression.ApplicationObject;
import ru.ezhov.regularexpression.TreatmentData;
import ru.ezhov.regularexpression.frame.SingletonBasicPanel;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonDelete extends MouseAdapter {
    private static int index;
    @Override
    public void mouseReleased(MouseEvent e) {
        TreatmentData treatmentData = new TreatmentData();
        final JList list = SingletonBasicPanel.getInstance().getList();
        
        if ( list.getSelectedIndex()==-1) return;
         index = list.getSelectedIndex();
        
        ApplicationObject applicationObject = (ApplicationObject)list.getSelectedValue();

        treatmentData.delete(applicationObject.getId());
       
             if (list.indexToLocation(index)==null) {
                    if (list.indexToLocation(list.getModel().getSize()-1)==null){
                                return;
                    }  else {
                        index = list.getModel().getSize()-1;
                    }       
             }
             
             SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    list.setSelectedIndex(index);
                }
            });
            
             
    }
}
