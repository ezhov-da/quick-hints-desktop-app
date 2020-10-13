package ru.ezhov.note.ui;

import ru.ezhov.note.application.NoteApplicationServiceFactory;
import ru.ezhov.note.ui.event.domain.UiEvent;
import ru.ezhov.note.ui.event.domain.UiEventSubscriber;
import ru.ezhov.note.ui.event.infrastructure.UiEventPublisherFactory;
import ru.ezhov.note.ui.notespanel.event.NoteSelectedUiEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

public class TabbedManagedPanel extends JPanel {
    private JPanel panelStub;
    private TabbedPane tabbedPane;

    public TabbedManagedPanel(NoteApplicationServiceFactory noteApplicationServiceFactory) {
        super(new BorderLayout());

        panelStub = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Create or select note...");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panelStub.add(label);

        this.add(panelStub, BorderLayout.CENTER);

        UiEventPublisherFactory.inMemory().register(new UiEventSubscriber() {
            @Override
            public void doOnEvent(UiEvent event) {
                if (event.getClass() == NoteSelectedUiEvent.class) {
                    NoteSelectedUiEvent e = (NoteSelectedUiEvent) event;
                    if (tabbedPane == null) {
                        tabbedPane = new TabbedPane(noteApplicationServiceFactory, e.selectedNote());
                        //TODO: do load wait
                        SwingUtilities.invokeLater(() -> {
                            TabbedManagedPanel.this.remove(panelStub);
                            TabbedManagedPanel.this.add(tabbedPane, BorderLayout.CENTER);
                        });
                    }
                }
            }

            @Override
            public List<Class> subscribeOn() {
                return Arrays.asList(NoteSelectedUiEvent.class);
            }
        });

    }
}
