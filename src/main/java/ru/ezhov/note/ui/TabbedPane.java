package ru.ezhov.note.ui;

import ru.ezhov.note.application.NoteApplicationServiceFactory;
import ru.ezhov.note.domain.Note;
import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.domain.NoteName;
import ru.ezhov.note.domain.NoteOriginalText;
import ru.ezhov.note.ui.command.domain.UiCommand;
import ru.ezhov.note.ui.command.domain.UiCommandSubscriber;
import ru.ezhov.note.ui.command.infrastructure.UiCommandFactory;
import ru.ezhov.note.ui.editor.NoteEditedUiEvent;
import ru.ezhov.note.ui.event.domain.UiEvent;
import ru.ezhov.note.ui.event.domain.UiEventSubscriber;
import ru.ezhov.note.ui.event.infrastructure.UiEventPublisherFactory;
import ru.ezhov.note.ui.notespanel.command.NoteReloadUiCommand;
import ru.ezhov.note.ui.notespanel.event.NoteSelectedUiEvent;
import ru.ezhov.note.ui.variable.ApplyVariableCommand;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TabbedPane extends JTabbedPane {
    private final Map<NoteId, Panel> map = new ConcurrentHashMap<>();
    private NoteApplicationServiceFactory noteApplicationServiceFactory;

    public TabbedPane(NoteApplicationServiceFactory noteApplicationServiceFactory, Note note) {
        this.noteApplicationServiceFactory = noteApplicationServiceFactory;

        add(note);

        UiEventPublisherFactory.inMemory().register(new UiEventSubscriberImpl());
        UiEventPublisherFactory.inMemory().register(new NotesPanelUiEventSubscriber());

        UiCommandFactory.inMemory().register(new UiCommandSubscriberImpl());

        ChangeListener changeListener = changeEvent -> {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            if (index != -1) {
                final Component tab = sourceTabbedPane.getComponentAt(index);
                final Optional<Map.Entry<NoteId, Panel>> entry = map.entrySet().stream().filter(e -> e.getValue().tabPanel == tab).findFirst();
                entry.ifPresent(noteIdPanelEntry -> UiEventPublisherFactory.inMemory().publish(new TabSelectedUiEvent(noteIdPanelEntry.getKey())));
            }
        };
        addChangeListener(changeListener);

        UiCommandFactory.inMemory().register(new UiCommandSubscriber() {
            @Override
            public void doOnCommand(UiCommand command) {
                ApplyVariableCommand variableCommand = (ApplyVariableCommand) command;

                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                if (index != -1) {
                    final Component tab = sourceTabbedPane.getComponentAt(index);
                    final Optional<Map.Entry<NoteId, Panel>> entry = map.entrySet().stream().filter(e -> e.getValue().tabPanel == tab).findFirst();

                    entry.ifPresent(
                            notePanelEntry ->
                                    UiCommandFactory.inMemory().publish(
                                            new ApplyVariableToSelectedTabCommand(notePanelEntry.getKey(), variableCommand.variable())
                                    )
                    );

                }
            }

            @Override
            public List<Class> subscribeOn() {
                return Arrays.asList(ApplyVariableCommand.class);
            }
        });
    }

    private void add(Note note) {
        final Panel panelIn = map.get(note.id());
        if (panelIn == null) {
            Panel panel = new Panel(note, new TabPanel(noteApplicationServiceFactory, note));
            map.put(note.id(), panel);
            add(note.name().value(), panel.tabPanel);
            setSelectedComponent(panel.tabPanel);
        } else {
            final Optional<Component> component = Arrays.stream(getComponents()).filter(c -> c == panelIn.tabPanel).findFirst();
            if (component.isPresent()) {
                setSelectedComponent(panelIn.tabPanel);
            } else {
                map.remove(note.id());
                Panel panel = new Panel(note, new TabPanel(noteApplicationServiceFactory, note));
                map.put(note.id(), panel);
                add(note.name().value(), panel.tabPanel);
                setSelectedComponent(panel.tabPanel);
            }
        }
    }

    private class Panel {
        private Note note;
        private TabPanel tabPanel;

        public Panel(Note note, TabPanel tabPanel) {
            this.note = note;
            this.tabPanel = tabPanel;
        }

        public Note getHint() {
            return note;
        }

        public TabPanel getTabPanel() {
            return tabPanel;
        }
    }

    public class UiCommandSubscriberImpl implements UiCommandSubscriber {

        @Override
        public void doOnCommand(UiCommand command) {
            NoteReloadUiCommand uiCommand = (NoteReloadUiCommand) command;
            SwingUtilities.invokeLater(() -> {
                final Panel panel = map.get(uiCommand.note().id());
                if (panel != null) {
                    remove(panel.tabPanel);
                    map.remove(uiCommand.note().id());
                }

                add(uiCommand.note());
            });
        }

        @Override
        public List<Class> subscribeOn() {
            return Arrays.asList(NoteReloadUiCommand.class);
        }
    }

    public class UiEventSubscriberImpl implements UiEventSubscriber {
        @Override
        public void doOnEvent(UiEvent event) {
            if (event.getClass() == NoteDeletedUiEvent.class) {
                SwingUtilities.invokeLater(() -> {
                    NoteDeletedUiEvent e = (NoteDeletedUiEvent) event;
                    final Panel panel = map.get(e.id());
                    if (panel != null) {
                        remove(panel.tabPanel);
                        map.remove(e.id());
                    }
                });
            } else if (event.getClass() == NoteEditedUiEvent.class) {
                NoteEditedUiEvent e = (NoteEditedUiEvent) event;
                SwingUtilities.invokeLater(() -> {
                    final Panel panel = map.get(e.id());
                    if (panel != null) {
                        remove(panel.tabPanel);
                        map.remove(e.id());
                    }

                    add(new Note(e.id(), new NoteName(e.name()), new NoteOriginalText(e.text())));
                });
            }
        }

        @Override
        public List<Class> subscribeOn() {
            return Arrays.asList(NoteDeletedUiEvent.class, NoteEditedUiEvent.class);
        }
    }

    public class NotesPanelUiEventSubscriber implements UiEventSubscriber {
        @Override
        public void doOnEvent(UiEvent event) {
            if (event.getClass() == NoteSelectedUiEvent.class) {
                NoteSelectedUiEvent e = (NoteSelectedUiEvent) event;
                SwingUtilities.invokeLater(() -> {
                    add(e.selectedNote());
                });
            }
        }

        @Override
        public List<Class> subscribeOn() {
            return Arrays.asList(NoteSelectedUiEvent.class);
        }
    }
}
