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
public class SingletonBasicPanel {
            private static BasicPanel basicPanel;
            
            private SingletonBasicPanel(){}
            
            public static BasicPanel getInstance(){
                    if (basicPanel==null) basicPanel = new BasicPanel();
                return basicPanel; 
            }
}
