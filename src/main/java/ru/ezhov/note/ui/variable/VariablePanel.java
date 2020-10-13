package ru.ezhov.note.ui.variable;

import ru.ezhov.note.ui.EnginePanelInitializedUiEvent;
import ru.ezhov.note.ui.command.infrastructure.UiCommandFactory;
import ru.ezhov.note.ui.event.domain.UiEvent;
import ru.ezhov.note.ui.event.domain.UiEventSubscriber;
import ru.ezhov.note.ui.event.infrastructure.UiEventPublisherFactory;
import ru.ezhov.note.ui.template.event.ExecutedWordsEngineUiEvent;
import ru.ezhov.note.variable.domain.Variable;
import ru.ezhov.note.variable.domain.VariableName;
import ru.ezhov.note.variable.domain.VariableValue;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VariablePanel extends JPanel {
    private DefaultListModel<VariableUi> defaultListModel = new DefaultListModel<>();
    private JList<VariableUi> list = new JList<>(defaultListModel);
    private List<VariableUi> variableUis = new ArrayList<>();


    public VariablePanel() {
        super(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Variables"));
        add(new JScrollPane(list), BorderLayout.CENTER);

        UiEventPublisherFactory.inMemory().register(new UiEventSubscriber() {
            @Override
            public void doOnEvent(UiEvent event) {
                SwingUtilities.invokeLater(() -> {
                    ExecutedWordsEngineUiEvent uiEvent = (ExecutedWordsEngineUiEvent) event;
                    final List<VariableUi> collect = uiEvent
                            .map()
                            .entrySet()
                            .stream()
                            .map(
                                    e -> new VariableUi(
                                            new Variable(
                                                    uiEvent.id(),
                                                    new VariableName(e.getKey()),
                                                    new VariableValue(e.getValue())
                                            )
                                    )
                            )
                            .collect(Collectors.toList());

                    collect.forEach(c -> {
                        variableUis.remove(c);
                        variableUis.add(c);
                    });

                    defaultListModel.clear();
                    variableUis.forEach(e -> defaultListModel.addElement(e));
                });
            }

            @Override
            public List<Class> subscribeOn() {
                return Arrays.asList(ExecutedWordsEngineUiEvent.class);
            }
        });

        UiEventPublisherFactory.inMemory().register(new UiEventSubscriber() {
            @Override
            public void doOnEvent(UiEvent event) {
                SwingUtilities.invokeLater(() -> {
                    EnginePanelInitializedUiEvent uiEvent = (EnginePanelInitializedUiEvent) event;

                });
            }

            @Override
            public List<Class> subscribeOn() {
                return Arrays.asList(EnginePanelInitializedUiEvent.class);
            }
        });

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    final List<Variable> selectedValuesList = list
                            .getSelectedValuesList()
                            .stream()
                            .map(VariableUi::variable)
                            .collect(Collectors.toList());
                    if (!selectedValuesList.isEmpty()) {
                        UiCommandFactory.inMemory().publish(new ApplyVariableCommand(selectedValuesList));
                    }
                }
            }
        });
    }
}
