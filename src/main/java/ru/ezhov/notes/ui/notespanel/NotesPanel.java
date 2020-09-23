package ru.ezhov.notes.ui.notespanel;

import ru.ezhov.notes.domain.Hint;
import ru.ezhov.notes.domain.HintRepository;
import ru.ezhov.notes.ui.HintDeletedUiEvent;
import ru.ezhov.notes.ui.SingletonBasicWindow;
import ru.ezhov.notes.ui.TabSelectedUiEvent;
import ru.ezhov.notes.ui.TextFieldWithText;
import ru.ezhov.notes.ui.editor.HintEditedUiEvent;
import ru.ezhov.notes.ui.event.domain.UiEvent;
import ru.ezhov.notes.ui.event.domain.UiEventSubscriber;
import ru.ezhov.notes.ui.event.infrastructure.UiEventPublisherFactory;
import ru.ezhov.notes.ui.notespanel.event.HintAddedUiEvent;
import ru.ezhov.notes.ui.notespanel.event.HintSelectedUiEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class NotesPanel extends JPanel {
    private JButton add;
    private JList<Hint> list;
    private TextFieldWithText textFieldSearch;
    private HintRepository hintRepository;

    public NotesPanel(HintRepository hintRepository) {
        this.hintRepository = hintRepository;
        setLayout(new BorderLayout());

        add = new JButton();
        add.setToolTipText("add");
        add.setIcon(new ImageIcon(getClass().getResource("/add_16x16.png")));

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(add);

        list = new JList<>();
        list.setModel(new DefaultListModel<>());
        add.addMouseListener(new ListenerButtonAdd());
        list.addListSelectionListener(new ListenerList());

        JPanel panel = new JPanel(new BorderLayout());
        textFieldSearch = new TextFieldWithText("Start typing text to search ...");
        panel.add(textFieldSearch, BorderLayout.NORTH);
        panel.add(new JScrollPane(list), BorderLayout.CENTER);

        add(toolBar, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        textFieldSearch.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    SingletonBasicWindow.getInstance().setVisible(false);
                }
            }
        });

        textFieldSearch.addCaretListener(new ListenerTextFieldSearch());
        list.addKeyListener(new ListenerListHelperKey());

        UiEventPublisherFactory.inMemory().register(new NotesPanelUiEventSubscriber());


        reloadList();

    }

    private void clearList() {
        SwingUtilities.invokeLater(() -> {
            DefaultListModel<Hint> defaultListModel = (DefaultListModel<Hint>) list.getModel();
            defaultListModel.removeAllElements();
        });
    }

    private void reloadList() {
        try {
            List<Hint> hints = hintRepository.all();
            clearList();
            setList(hints);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Не удалось обновить список",
                    "Ошибка обновления списка",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void setList(final List<Hint> hintsList) {
        SwingUtilities.invokeLater(() -> {
            DefaultListModel<Hint> defaultListModel = (DefaultListModel<Hint>) list.getModel();
            List<Hint> hints = new ArrayList<>(hintsList);
            hints.sort(Comparator.comparing(h -> h.name().value()));

            for (Hint hint : hints) {
                defaultListModel.addElement(hint);
            }
        });
    }

    private class ListenerButtonAdd extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            AddWindow addWindow = new AddWindow(hintRepository);
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            addWindow.setSize(
                    new Dimension(
                            dimension.width * 60 / 100,
                            dimension.height * 60 / 100
                    )
            );
            addWindow.setLocationRelativeTo(SingletonBasicWindow.getInstance());
            addWindow.setVisible(true);
        }
    }

    private class ListenerTextFieldSearch implements CaretListener {
        @Override
        public void caretUpdate(CaretEvent e) {
            TextFieldWithText textFieldWithText = (TextFieldWithText) e.getSource();
            String text = textFieldWithText.getText();
            reloadListWithCondition(text);
        }
    }

    private void reloadListWithCondition(String condition) {
        SwingUtilities.invokeLater(() -> {
            try {
                List<Hint> hints = hintRepository.byCondition(condition);
                clearList();
                setList(hints);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        "Не удалось обновить список",
                        "Ошибка обновления списка",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
    }

    private class ListenerList implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                SwingUtilities.invokeLater(() -> {
                    int index = list.getSelectedIndex();
                    if (index == -1) {
                        return;
                    }
                    Hint hint = list.getSelectedValue();
                    UiEventPublisherFactory.inMemory().publish(new HintSelectedUiEvent(hint));
                });
            }
        }
    }

    public class NotesPanelUiEventSubscriber implements UiEventSubscriber {
        @Override
        public void doOnEvent(UiEvent event) {
            if (event.getClass() == TabSelectedUiEvent.class) {
                SwingUtilities.invokeLater(() -> {
                    TabSelectedUiEvent e = (TabSelectedUiEvent) event;

                    final ListModel<Hint> model = list.getModel();
                    for (int i = 0; i < model.getSize(); i++) {
                        final Hint element = model.getElementAt(i);
                        if (element.id().equals(e.hintId())) {
                            list.setSelectedValue(element, true);
                        }
                    }
                });
            } else {
                SwingUtilities.invokeLater(NotesPanel.this::reloadList);
            }
        }

        @Override
        public List<Class> subscribeOn() {
            return Arrays.asList(
                    HintAddedUiEvent.class,
                    HintEditedUiEvent.class,
                    HintDeletedUiEvent.class,
                    TabSelectedUiEvent.class
            );
        }
    }

    private class ListenerListHelperKey extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    int selectionIndex = list.getSelectedIndex();
                    if (selectionIndex == 0) {
                        SwingUtilities.invokeLater(() -> list.setSelectedIndex(
                                list.getModel().getSize() - 1));
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    selectionIndex = list.getSelectedIndex();
                    if (selectionIndex == list.getModel().getSize() - 1) {
                        SwingUtilities.invokeLater(() -> list.setSelectedIndex(0));
                    }
                    break;
            }
        }

    }

}
