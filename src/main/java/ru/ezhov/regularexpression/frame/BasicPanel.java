package ru.ezhov.regularexpression.frame;

import ru.ezhov.regularexpression.domain.AllHintsException;
import ru.ezhov.regularexpression.domain.Hint;
import ru.ezhov.regularexpression.domain.Hints;
import ru.ezhov.regularexpression.listeners.ListenerButtonAdd;
import ru.ezhov.regularexpression.listeners.ListenerList;
import ru.ezhov.regularexpression.listeners.ListenerListHelperKey;
import ru.ezhov.regularexpression.listeners.ListenerTextFieldSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @author RRNDeonisiusEZH
 */
public class BasicPanel extends JPanel {
    private BasicButton add;
    private JList list;
    private JScrollPane scrollPane;
    private TextFieldWithText textFieldSearch;

    private LookAndDeletePanel lookAndDeletePanel;


    public BasicPanel() {
        init();
    }


    private void init() {
        lookAndDeletePanel = new LookAndDeletePanel();
        setLayout(new BorderLayout());
        add = new BasicButton("add");
        list = new JList();
        Dimension dimensionHide = new Dimension(25, 25);
        list.setModel(new DefaultListModel());
        add.addMouseListener(new ListenerButtonAdd());
        list.addListSelectionListener(new ListenerList(lookAndDeletePanel));
        list.addMouseListener(new ListenerList(lookAndDeletePanel));
        JPanel panelCenter = new JPanel(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        JPanel panelList = new JPanel(new BorderLayout());
        textFieldSearch = new TextFieldWithText("Начните вводить текст для поиска...");
        panelList.add(textFieldSearch, BorderLayout.NORTH);
        panelList.add(new JScrollPane(list), BorderLayout.CENTER);
        JPanel panelButtonAdd = new JPanel();
        panelButtonAdd.add(add);
        panelList.add(panelButtonAdd, BorderLayout.SOUTH);
        splitPane.setLeftComponent(panelList);
        splitPane.setRightComponent(lookAndDeletePanel);
        splitPane.setResizeWeight(0.2);
        splitPane.setDividerLocation(0.2);
        panelCenter.add(splitPane, BorderLayout.CENTER);
        add(panelCenter, BorderLayout.CENTER);
        setListeners();
        reloadList();
    }

    private void setListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    JFrameHelper frameHelper = (JFrameHelper) e.getSource();
                    frameHelper.setVisible(false);
                }
            }
        });

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
    }

    public BasicButton getAdd() {
        return add;
    }

    //TODO: удалить этот метод
    public JList getList() {
        return list;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setList(final List<Hint> hintsList) {
        SwingUtilities.invokeLater(() -> {
            DefaultListModel defaultListModel = (DefaultListModel) list.getModel();
            for (Hint hint : hintsList) {
                defaultListModel.addElement(hint);
            }
        });
    }

    public void clearList() {
        SwingUtilities.invokeLater(() -> {
            BasicPanel basicPanel = SingletonBasicPanel.getInstance();
            JList list = basicPanel.getList();
            DefaultListModel defaultListModel = (DefaultListModel) list.getModel();
            defaultListModel.removeAllElements();
        });
    }

    public void reloadList() {
        try {
            List<Hint> hints = new Hints().all();
            clearList();
            setList(hints);
        } catch (AllHintsException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Не удалось обновить список",
                    "Ошибка обновления списка",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public void reloadListWithCondition(String condition) {
        try {
            List<Hint> hints = new Hints().all(condition);
            clearList();
            setList(hints);
        } catch (AllHintsException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "Не удалось обновить список",
                    "Ошибка обновления списка",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}
