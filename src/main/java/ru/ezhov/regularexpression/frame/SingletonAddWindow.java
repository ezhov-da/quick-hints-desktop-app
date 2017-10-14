/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ezhov.regularexpression.frame;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class SingletonAddWindow {
        private static AddWindow addWindow;
        
        private SingletonAddWindow(){}
        
        public static AddWindow getInstance(){
            if (addWindow==null) addWindow = new AddWindow(SingletonBasicWindow.getInstance());
            
            return addWindow;
        }
}
