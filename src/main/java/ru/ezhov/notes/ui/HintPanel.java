package ru.ezhov.notes.ui;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.notes.config.ApplicationConfiguration;
import ru.ezhov.notes.domain.Hint;
import ru.ezhov.notes.domain.HintRepository;
import ru.ezhov.notes.template.domain.Engine;
import ru.ezhov.notes.ui.template.HintPanelEngine;
import ru.ezhov.notes.ui.command.infrastructure.UiCommandFactory;
import ru.ezhov.notes.ui.event.domain.UiEvent;
import ru.ezhov.notes.ui.event.domain.UiEventSubscriber;
import ru.ezhov.notes.ui.template.event.ExecutedWordsEngineUiEvent;
import ru.ezhov.notes.ui.terminal.ExecuteTerminalUiCommand;
import ru.ezhov.notes.ui.terminal.HintTerminalPanel;
import ru.ezhov.notes.ui.editor.EditWindow;
import ru.ezhov.notes.ui.event.infrastructure.UiEventPublisherFactory;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HintPanel extends JPanel {
    private TextAreaPanel textAreaOriginal;
    private HintPanelEngine panelEngine;
    private final Engine engine;
    private Hint hint;
    private final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private JPanel panelStub;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private String finalText = "";
    private HintRepository hintRepository;

    private HintTerminalPanel terminalPanel;

    public HintPanel(HintRepository hintRepository, Engine engine, Hint hint) {
        this.engine = engine;
        this.hint = hint;
        this.hintRepository = hintRepository;
        setLayout(new BorderLayout());
        init();
    }

    private void init() {
        removeAll();
        initDefault();
        new Loader().execute();
    }

    private void initDefault() {
        panelStub = new JPanel(new BorderLayout());
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(this.getClass().getResource("/loader.gif")));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panelStub.add(label, BorderLayout.CENTER);
        add(panelStub, BorderLayout.CENTER);
    }

    private void initFinal() {
        if (panelStub != null) {
            remove(panelStub);
        }

        List<String> words = engine.words(finalText);

        JPanel panelEngine = null;

        if (!words.isEmpty()) {
            panelEngine = addPanelEngine(words);
        }

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JButton edit = new JButton();
        edit.setToolTipText("edit");
        edit.addMouseListener(new ListenerButtonCorrect());
        edit.setIcon(new ImageIcon(getClass().getResource("/edit_16x16.png")));
        edit.setAlignmentX(JButton.CENTER_ALIGNMENT);

        JButton delete = new JButton();
        delete.setToolTipText("delete");
        delete.setIcon(new ImageIcon(getClass().getResource("/delete_16x16.png")));
        delete.setAlignmentX(JButton.CENTER_ALIGNMENT);
        delete.addMouseListener(new ListenerButtonDelete());

        toolBar.add(edit);
        toolBar.add(Box.createGlue());
        toolBar.add(delete);


        JPanel panel = new JPanel(new BorderLayout());

        textAreaOriginal = new TextAreaPanel(hint.id(), finalText, false);


        String type;
        switch (hint.type()) {
            case URL:
                type = hint.text().value();
                break;
            default:
                type = hint.type().name();
        }

        JLabel labelSource = new JLabel("Source: " + type);

        panel.add(textAreaOriginal, BorderLayout.CENTER);
        panel.add(labelSource, BorderLayout.SOUTH);

        tabbedPane.add("origin", panel);
        add(toolBar, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel(new BorderLayout());
        if (panelEngine != null) {
            panelCenter.add(panelEngine, BorderLayout.NORTH);
        }
        panelCenter.add(tabbedPane, BorderLayout.CENTER);


        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(panelCenter);


        terminalPanel = new HintTerminalPanel(hint.id(), ApplicationConfiguration.shellExecuteCommand());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Terminal", terminalPanel);
        tabbedPane.setTabComponentAt(0, new TabHeader("Terminal", tabbedPane, false));

        splitPane.setBottomComponent(tabbedPane);

        add(splitPane, BorderLayout.CENTER);

        this.revalidate();
    }

    public JPanel addPanelEngine(List<String> words) {
        if (panelEngine != null) {
            remove(panelEngine);
            revalidate();
        }

        UiEventPublisherFactory.inMemory().register(new UiEventSubscriber() {
            @Override
            public void doOnEvent(UiEvent event) {
                ExecutedWordsEngineUiEvent uiEvent = (ExecutedWordsEngineUiEvent) event;
                if (uiEvent.hintId().equals(hint.id())) {
                    SwingUtilities.invokeLater(() -> {
                                final String textEngine = engine.apply(
                                        HintPanel.this.textAreaOriginal.text(),
                                        uiEvent.map()
                                );

                                final int index = tabbedPane.indexOfTab("apply");
                                if (index == -1) {
                                    final ApplyPanel applyPanel = new ApplyPanel(textEngine);
                                    tabbedPane.addTab("apply", applyPanel);
                                    tabbedPane.setSelectedComponent(applyPanel);
                                } else {
                                    tabbedPane.setSelectedIndex(index);
                                    UiEventPublisherFactory.inMemory().publish(new FinalTextAppliedUiEvent(hint.id(), textEngine));
                                }
                            }
                    );
                }
            }

            @Override
            public List<Class> subscribeOn() {
                return Arrays.asList(ExecutedWordsEngineUiEvent.class);
            }
        });
        return new HintPanelEngine(hint.id(), words);
    }

    private class ApplyPanel extends JPanel {
        private final TextAreaPanel textAreaPanel;

        public ApplyPanel(String text) {
            setLayout(new BorderLayout());
            textAreaPanel = new TextAreaPanel(hint.id(), text, true);
            add(textAreaPanel, BorderLayout.CENTER);

            UiEventPublisherFactory.inMemory().register(new UiEventSubscriber() {
                @Override
                public void doOnEvent(UiEvent event) {
                    FinalTextAppliedUiEvent uiEvent = (FinalTextAppliedUiEvent) event;
                    if(uiEvent.hintId().equals(hint.id())){
                        textAreaPanel.setText(uiEvent.text());
                    }
                }

                @Override
                public List<Class> subscribeOn() {
                    return Arrays.asList(FinalTextAppliedUiEvent.class);
                }
            });
        }
    }

    private String checkAndLoad(String text) {
        final String[] split = text.split("\n");
        String returnText = text;
        if (split.length > 0) {
            String firstRow = split[0];
            try {
                URL u = new URL(firstRow);
                u.toURI();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(firstRow)
                        .build();
                try (Response response = client.newCall(request).execute()) {
                    returnText = Objects.requireNonNull(response.body()).string();
                }
            } catch (MalformedURLException e) {
                //no matter
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return returnText;
    }

    private class Loader extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() {
            return checkAndLoad(hint.text().value());
        }

        @Override
        protected void done() {
            try {
                HintPanel.this.finalText = get();
                initFinal();
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: process
            }
        }
    }

    private class ListenerButtonCorrect extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            EditWindow editWindow = new EditWindow(hintRepository, hint);
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            editWindow.setSize(
                    new Dimension(
                            dimension.width * 60 / 100,
                            dimension.height * 60 / 100
                    )
            );
            editWindow.setLocationRelativeTo(SingletonBasicWindow.getInstance());
            editWindow.setVisible(true);
        }
    }

    public class ListenerButtonDelete extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            try {
                hintRepository.remove(hint.id());

                UiEventPublisherFactory.inMemory().publish(new HintDeletedUiEvent(hint.id()));
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "Не удалось удалить запись",
                        "Ошибка удаления",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

}
