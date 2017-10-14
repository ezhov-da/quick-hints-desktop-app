/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.listeners;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import ru.ezhov.regularexpression.TreatmentData;
import ru.ezhov.regularexpression.connection.Querys;
import ru.ezhov.regularexpression.frame.ExtendsJTextField;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ListenerTextFieldSearch implements CaretListener{
    @Override
    public void caretUpdate(CaretEvent e) {
        ExtendsJTextField extendsJTextField = (ExtendsJTextField) e.getSource();
        String text = extendsJTextField.getText();
        if (text.length() > 0){
            text = Querys.SELECT_SEARCH.replace("-", text.toUpperCase());
        } else {
            text = Querys.SELECT_SEARCH.replace("-", "");
        }
        
        new TreatmentData().select(text);
    }
}
