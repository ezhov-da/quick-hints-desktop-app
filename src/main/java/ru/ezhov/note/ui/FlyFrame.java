package ru.ezhov.note.ui;

import ru.ezhov.note.ui.editor.NoteEditedUiEvent;
import ru.ezhov.note.ui.event.domain.UiEvent;
import ru.ezhov.note.ui.event.domain.UiEventSubscriber;
import ru.ezhov.note.ui.event.infrastructure.UiEventPublisherFactory;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;

public class FlyFrame {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private String text;
    private Component component;

    public FlyFrame(final String text, final JTabbedPane tabbedPane, final Component component) {
        this.tabbedPane = tabbedPane;
        this.text = text;
        this.component = component;

        UiEventPublisherFactory.inMemory().register(new UiEventSubscriber() {
            @Override
            public void doOnEvent(UiEvent event) {
                SwingUtilities.invokeLater(() -> FlyFrame.this.frame.dispose());
            }

            @Override
            public List<Class> subscribeOn() {
                return Arrays.asList(NoteEditedUiEvent.class);
            }
        });

        frame = new JFrame(text);
        frame.setIconImage(new ImageIcon(this.getClass().getResource("/notes.png")).getImage());
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                frame.remove(component);
                tabbedPane.addTab(text, component);
                tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, new TabHeader(text, tabbedPane, false));
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(component);
        frame.setSize(new PercentScreenDimension(70).dimension());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
