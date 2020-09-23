package ru.ezhov.notes.ui;

import ru.ezhov.notes.domain.Hint;
import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.domain.HintName;
import ru.ezhov.notes.domain.HintRepository;
import ru.ezhov.notes.domain.HintText;
import ru.ezhov.notes.template.domain.Engine;
import ru.ezhov.notes.ui.editor.HintEditedUiEvent;
import ru.ezhov.notes.ui.event.domain.UiEvent;
import ru.ezhov.notes.ui.event.domain.UiEventSubscriber;
import ru.ezhov.notes.ui.event.infrastructure.UiEventPublisherFactory;
import ru.ezhov.notes.ui.notespanel.event.HintSelectedUiEvent;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Component;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TabbedPane extends JTabbedPane {
    private final Map<HintId, Panel> map = new ConcurrentHashMap<>();
    private final Engine engine;
    private HintRepository hintRepository;

    public TabbedPane(HintRepository hintRepository, Engine engine) {
        this.hintRepository = hintRepository;
        this.engine = engine;

        UiEventPublisherFactory.inMemory().register(new UiEventSubscriberImpl());
        UiEventPublisherFactory.inMemory().register(new NotesPanelUiEventSubscriber());

        ChangeListener changeListener = changeEvent -> {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            if (index != -1) {
                final Component tab = sourceTabbedPane.getComponentAt(index);
                final Optional<Map.Entry<HintId, Panel>> entry = map.entrySet().stream().filter(e -> e.getValue().tabPanel == tab).findFirst();
                entry.ifPresent(hintIdPanelEntry -> UiEventPublisherFactory.inMemory().publish(new TabSelectedUiEvent(hintIdPanelEntry.getKey())));
            }
        };
        addChangeListener(changeListener);
    }

    private void add(Hint hint) {
        final Panel panelIn = map.get(hint.id());
        if (panelIn == null) {
            Panel panel = new Panel(hint, new TabPanel(hintRepository, engine, hint));
            map.put(hint.id(), panel);
            add(hint.name().value(), panel.tabPanel);
            setSelectedComponent(panel.tabPanel);
        } else {
            final Optional<Component> component = Arrays.stream(getComponents()).filter(c -> c == panelIn.tabPanel).findFirst();
            if (component.isPresent()) {
                setSelectedComponent(panelIn.tabPanel);
            } else {
                map.remove(hint.id());
                Panel panel = new Panel(hint, new TabPanel(hintRepository, engine, hint));
                map.put(hint.id(), panel);
                add(hint.name().value(), panel.tabPanel);
                setSelectedComponent(panel.tabPanel);
            }
        }
    }

    private class Panel {
        private Hint hint;
        private TabPanel tabPanel;

        public Panel(Hint hint, TabPanel tabPanel) {
            this.hint = hint;
            this.tabPanel = tabPanel;
        }

        public Hint getHint() {
            return hint;
        }

        public TabPanel getTabPanel() {
            return tabPanel;
        }
    }

    public class UiEventSubscriberImpl implements UiEventSubscriber {
        @Override
        public void doOnEvent(UiEvent event) {
            if (event.getClass() == HintDeletedUiEvent.class) {
                SwingUtilities.invokeLater(() -> {
                    HintDeletedUiEvent e = (HintDeletedUiEvent) event;
                    final Panel panel = map.get(e.id());
                    if (panel != null) {
                        remove(panel.tabPanel);
                        map.remove(e.id());
                    }
                });
            } else if (event.getClass() == HintEditedUiEvent.class) {
                HintEditedUiEvent e = (HintEditedUiEvent) event;
                SwingUtilities.invokeLater(() -> {
                    final Panel panel = map.get(e.id());
                    if (panel != null) {
                        remove(panel.tabPanel);
                        map.remove(e.id());
                    }

                    add(new Hint(e.id(), new HintName(e.name()), new HintText(e.text())));
                });
            }
        }

        @Override
        public List<Class> subscribeOn() {
            return Arrays.asList(HintDeletedUiEvent.class, HintEditedUiEvent.class);
        }
    }

    public class NotesPanelUiEventSubscriber implements UiEventSubscriber {
        @Override
        public void doOnEvent(UiEvent event) {
            if (event.getClass() == HintSelectedUiEvent.class) {
                HintSelectedUiEvent e = (HintSelectedUiEvent) event;
                SwingUtilities.invokeLater(() -> {
                    add(e.selectedHint());
                });
            }
        }

        @Override
        public List<Class> subscribeOn() {
            return Arrays.asList(HintSelectedUiEvent.class);
        }
    }
}
