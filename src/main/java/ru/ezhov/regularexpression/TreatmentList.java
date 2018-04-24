/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression;

import ru.ezhov.regularexpression.frame.*;

import javax.swing.*;
import java.util.Vector;

/**
 * @author RRNDeonisiusEZH
 */
public class TreatmentList {
	public static void setList(final Vector<ApplicationObject> vector) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BasicPanel basicPanel = SingletonBasicPanel.getInstance();
				JList list = basicPanel.getList();
				DefaultListModel defaultListModel = (DefaultListModel) list.getModel();
				for (ApplicationObject applicationObject : vector) {
					defaultListModel.addElement(applicationObject);
				}
			}
		});
	}

	public static void clearList() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				BasicPanel basicPanel = SingletonBasicPanel.getInstance();
				JList list = basicPanel.getList();
				DefaultListModel defaultListModel = (DefaultListModel) list.getModel();
				defaultListModel.removeAllElements();
			}
		});
	}
}
