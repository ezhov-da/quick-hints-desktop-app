package ru.ezhov.regularexpression.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import ru.ezhov.regularexpression.listeners.ListenerButtonDelete;
import ru.ezhov.regularexpression.listeners.ListenerButtonHideLookAndDelete;

/**
 *
 * @author RRNDeonisiusEZH
 */
public class LookAndDeletePanel extends JWindow{
    private JPanel panel;
    private CircleButton back;
    private JTextPane text;
    private CircleButton delete;
    private JScrollPane scrollPane;
    

    public LookAndDeletePanel(Frame owner) {
        super(owner);
        init();
    }

    
    private void init(){
        setSize(SingletonBasicPanel.getInstance().getList().getWidth(),
                                                                                    SingletonBasicPanel.getInstance().getScrollPane().getHeight()-5);
        
        setLayout(new BorderLayout());
        
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setBackground(SettingsFrame.PANEL_BASIC_COLOR);
        
        back = new CircleButton();
        back.setPreferredSize(new Dimension(25, 25));
        back.setIcon(SettingsFrame.BACK);
        
        scrollPane = new JScrollPane();
        text = new JTextPane();
        scrollPane.setViewportView(text);
        
        delete = new CircleButton();
        delete.setPreferredSize(new Dimension(28, 37));
        delete.setIcon(SettingsFrame.DELETE);
        delete.setAlignmentX(CircleButton.CENTER_ALIGNMENT);
        delete.setBackground(SettingsFrame.PANEL_BASIC_COLOR);
        
        text.setFont(SettingsFrame.LIST_BASIC_FONT);
        text.setEditable(false);
        
        /*add listener*/
        back.addMouseListener(new ListenerButtonHideLookAndDelete());  
        delete.addMouseListener(new ListenerButtonDelete());
        
        
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.weightx = 1;
                gridBagConstraints.weighty = 0;
                gridBagConstraints.anchor = GridBagConstraints.NORTHEAST;  
                gridBagConstraints.insets = SettingsFrame.INSETS_COMPONENT;
        panel.add(back,gridBagConstraints);
        
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 2;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.weightx = GridBagConstraints.RELATIVE;
                gridBagConstraints.weighty = 1;
                gridBagConstraints.anchor = GridBagConstraints.NORTH;  
                gridBagConstraints.fill = GridBagConstraints.BOTH;  
                gridBagConstraints.insets = SettingsFrame.INSETS_COMPONENT;
       panel.add(scrollPane,gridBagConstraints);

                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 3;
                gridBagConstraints.gridheight = 1;
                gridBagConstraints.gridwidth = 1;
                gridBagConstraints.weightx =1;
                gridBagConstraints.weighty = 0;
                gridBagConstraints.anchor = GridBagConstraints.SOUTHEAST;  
                gridBagConstraints.fill = GridBagConstraints.NONE;                 
                gridBagConstraints.insets = SettingsFrame.INSETS_COMPONENT;
        panel.add(delete,gridBagConstraints);
        
        add(panel,BorderLayout.CENTER);
    }

    public CircleButton getBack() {
        return back;
    }

    public JTextPane getText() {
        return text;
    }

    public CircleButton getDelete() {
        return delete;
    }
   
    
}
