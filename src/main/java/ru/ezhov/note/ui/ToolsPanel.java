package ru.ezhov.note.ui;

import ru.ezhov.note.ui.variable.VariablePanel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class ToolsPanel extends JPanel {
    private VariablePanel variablePanel;

    public ToolsPanel() {
        super(new BorderLayout());

        variablePanel = new VariablePanel();
        add(variablePanel, BorderLayout.CENTER);

        //TODO: create show hide panel
//        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
//        toolBar.setFloatable(false);
//
//        JButton variables = new JButton(new ImageIcon(ToolsPanel.class.getResource("/variable_16x16.png")));
//        variables.addActionListener(e -> {
//            SwingUtilities.invokeLater(() -> {
//                if (variablePanel == null) {
//                    variablePanel = new VariablePanel();
//                }
//
//                if (variablePanel.isShowing()) {
//                    remove(variablePanel);
//                } else {
//                    add(variablePanel, BorderLayout.CENTER);
//                }
//
//                repaint();
//                revalidate();
//            });
//        });
//
//        toolBar.add(variables);
//
//        add(toolBar, BorderLayout.EAST);
    }
}
