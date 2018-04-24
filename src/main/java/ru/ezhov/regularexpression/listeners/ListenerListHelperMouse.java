/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.listeners;

import ru.ezhov.regularexpression.TreatmentHotKey;
import ru.ezhov.regularexpression.frame.SingletonJFrameHelper;

import java.awt.event.*;

/**
 * @author RRNDeonisiusEZH
 */
public class ListenerListHelperMouse extends MouseAdapter {

	@Override
	public void mouseReleased(MouseEvent e) {
		new TreatmentHotKey().getTextFromList(SingletonJFrameHelper.getInstance().getListHelper());
		SingletonJFrameHelper.getInstance().setVisible(false);
	}

}
