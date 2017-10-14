package ru.ezhov.regularexpression.frame;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class SingletonCorrectWindow {
        private static CorrectWindow correctWindow;
        
        private SingletonCorrectWindow(){}
        
        public static CorrectWindow getInstance(){
            if (correctWindow==null) correctWindow = new CorrectWindow(SingletonBasicWindow.getInstance());
            
            return correctWindow;
        }
}
