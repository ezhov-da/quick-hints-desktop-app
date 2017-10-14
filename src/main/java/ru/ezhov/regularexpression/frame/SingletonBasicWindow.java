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
public class SingletonBasicWindow {
            private static BasicWindow basicWindow;
            
            private SingletonBasicWindow(){}
            
            public static BasicWindow getInstance(){
                    if (basicWindow==null) basicWindow = new BasicWindow();
                return basicWindow;
                
            }
}
