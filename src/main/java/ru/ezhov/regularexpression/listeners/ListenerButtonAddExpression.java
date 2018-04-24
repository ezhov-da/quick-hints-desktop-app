/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.TreatmentData;
import ru.ezhov.regularexpression.frame.SingletonAddWindow;

import java.awt.event.*;


/**
 * @author RRNDeonisiusEZH
 */
public class ListenerButtonAddExpression extends MouseAdapter {


	@Override
	public void mouseReleased(MouseEvent e) {
		if (ListenerChangeText.isWrong()) return;
		String text = SingletonAddWindow.getInstance().getText().getText();
		String comment = SingletonAddWindow.getInstance().getComment().getText();
		TreatmentData treatmentData = new TreatmentData();
		treatmentData.insert(text, comment);
	}

}
