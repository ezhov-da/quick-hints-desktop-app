package ru.ezhov.note.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

public class TabHeader extends JPanel {
    private JLabel label;
    private JButton buttonFly;
    private JButton buttonClose;
    private JTabbedPane tabbedPane;
    private String text;

    public TabHeader(final String text, final JTabbedPane tabbedPane, boolean isClosed) {
        this.tabbedPane = tabbedPane;
        this.text = text;
        setOpaque(false);
        label = new JLabel(text);

        buttonFly = new JButton(new ImageIcon(this.getClass().getResource("/not_fly_16x16.png")));
        buttonFly.setToolTipText("Unpin");
        buttonFly.setOpaque(false);
        setDefaultButtonProperties(buttonFly);

        buttonClose = new JButton("x");
        setDefaultButtonProperties(buttonClose);
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.setOpaque(false);
        panelButtons.add(buttonFly);
        if (isClosed) {
            panelButtons.add(buttonClose);
        }
        add(panelButtons, BorderLayout.EAST);
        buttonClose.addActionListener(e -> tabbedPane.remove(tabbedPane.indexOfTab(text)));

        buttonFly.addActionListener(e -> {
            int index = tabbedPane.indexOfTab(text);
            Component component = tabbedPane.getComponentAt(index);
            tabbedPane.remove(index);
            new FlyFrame(text, tabbedPane, component);
        });
    }

    private void setDefaultButtonProperties(JButton button) {
        Dimension dimension = new Dimension(20, 20);
        button.setMaximumSize(dimension);
        button.setMinimumSize(dimension);
        button.setBorder(null);
        button.setPreferredSize(dimension);
        button.setSize(dimension);
        button.setFont(new Font(new JLabel().getFont().getName(), Font.PLAIN, 8));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabHeader tabHeader = (TabHeader) o;
        return text != null ? text.equals(tabHeader.text) : tabHeader.text == null;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }
}
