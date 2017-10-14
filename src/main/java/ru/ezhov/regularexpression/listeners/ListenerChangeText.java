package ru.ezhov.regularexpression.listeners;

import java.awt.Color;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.JTextComponent;
import ru.ezhov.regularexpression.ApplicationTooltips;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class ListenerChangeText implements CaretListener   {
    enum ComponentText{DESCRIPTION, TEXT}
    
    private static final int LENGTH_DESCRIPTION = 100;
    private static final int LENGTH_TEXT = 8000;
    private JTextComponent textComponent;
    
    private final ComponentText component;
    private static boolean wrong;
    
    
    
    public ListenerChangeText(String component) {
       this.component = ComponentText.valueOf(component.toUpperCase());
    }
    

    @Override
    public void caretUpdate(CaretEvent e) {
        textComponent = (JTextComponent) e.getSource();
        int length;
                    switch (component){
                        case DESCRIPTION:
                            length = checkLength(LENGTH_DESCRIPTION);
                            textComponent.setToolTipText("<html><font size=\"3\" color=\"black\" face=\"Courier New\">" 
                                                                                +ApplicationTooltips.DESCRIPTION +" now ---> " + length+"</font>");
                        break;
                            
                        case TEXT:
                            length = checkLength(LENGTH_TEXT);
                            textComponent.setToolTipText("<html><font size=\"3\" color=\"black\" face=\"Courier New\">" 
                                                                                                                    +ApplicationTooltips.TEXT +" now ---> " + length+"</font>");
                        break;                            
                    }
                    
      
                    
    }
    
    
    
    
                    
    private  int  checkLength(int mustLength){
            if (textComponent.getDocument().getLength()>mustLength){
                    textComponent.setForeground(Color.red);
                    wrong = true;
            } else {
                textComponent.setForeground(Color.black);
                wrong = false;
            }
            
            return textComponent.getDocument().getLength();
    }         

    public static boolean isWrong() {
        return wrong;
    }
    
    
    
}
