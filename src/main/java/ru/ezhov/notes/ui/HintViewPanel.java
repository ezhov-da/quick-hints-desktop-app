package ru.ezhov.notes.ui;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.notes.domain.Hint;
import ru.ezhov.notes.domain.HintRepository;
import ru.ezhov.notes.template.domain.Engine;
import ru.ezhov.notes.template.ui.swing.PanelEngine;
import ru.ezhov.notes.ui.editor.EditWindow;
import ru.ezhov.notes.ui.event.infrastructure.UiEventPublisherFactory;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HintViewPanel extends JPanel {
    private RSyntaxTextArea textArea;
    private PanelEngine panelEngine;
    private final Engine engine;
    private Hint hint;
    private final JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private JPanel panelStub;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private String finalText = "";
    private HintRepository hintRepository;

    public HintViewPanel(HintRepository hintRepository, Engine engine, Hint hint) {
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

        textArea = new RSyntaxTextArea();
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        textArea.setCodeFoldingEnabled(true);
        textArea.setText(finalText);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        textArea.setEditable(false);

        panel.add(sp, BorderLayout.CENTER);

        tabbedPane.add("origin", panel);
        add(toolBar, BorderLayout.NORTH);

        JPanel panelCenter = new JPanel(new BorderLayout());
        if (panelEngine != null) {
            panelCenter.add(panelEngine, BorderLayout.NORTH);
        }
        panelCenter.add(tabbedPane, BorderLayout.CENTER);

        add(panelCenter, BorderLayout.CENTER);


        this.revalidate();
    }

    public JPanel addPanelEngine(List<String> words) {
        if (panelEngine != null) {
            remove(panelEngine);
            revalidate();
        }
        panelEngine = new PanelEngine(words);

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(panelEngine, BorderLayout.CENTER);
        JButton button = new JButton("apply template");
        JPanel panelButton = new JPanel();
        panelButton.add(button);
        panel.add(panelButton, BorderLayout.SOUTH);

        button.addActionListener(e ->
                SwingUtilities.invokeLater(() -> {
                            final Map<String, String> stringStringMap = panelEngine.apply();

                            final String textEngine = engine.apply(
                                    HintViewPanel.this.textArea.getText(),
                                    stringStringMap
                            );

                            final int index = tabbedPane.indexOfTab("apply");
                            if (index == -1) {
                                final ApplyPanel applyPanel = new ApplyPanel(textEngine);
                                tabbedPane.addTab("apply", applyPanel);
                                tabbedPane.setSelectedComponent(applyPanel);
                            } else {
                                ApplyPanel applyPanelFrom = (ApplyPanel) tabbedPane.getComponentAt(index);
                                applyPanelFrom.setText(textEngine);
                                tabbedPane.setSelectedIndex(index);
                            }
                        }
                )
        );


        return panel;
    }

    private class ApplyPanel extends JPanel {

        private final RSyntaxTextArea textPane;

        public ApplyPanel(String text) {
            setLayout(new BorderLayout());

            textPane = new RSyntaxTextArea();
            textPane.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
            textPane.setCodeFoldingEnabled(true);
            RTextScrollPane sp = new RTextScrollPane(textPane);
            textPane.setText(text);

            add(sp, BorderLayout.CENTER);
        }

        public void setText(String text) {
            SwingUtilities.invokeLater(() -> textPane.setText(text));
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
                HintViewPanel.this.finalText = get();
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
