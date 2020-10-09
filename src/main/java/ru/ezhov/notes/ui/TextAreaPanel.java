package ru.ezhov.notes.ui;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.ui.command.infrastructure.UiCommandFactory;
import ru.ezhov.notes.ui.event.domain.UiEvent;
import ru.ezhov.notes.ui.event.domain.UiEventSubscriber;
import ru.ezhov.notes.ui.search.SearchPanel;
import ru.ezhov.notes.ui.search.TextProducer;
import ru.ezhov.notes.ui.search.event.FindedPartsUiEvent;
import ru.ezhov.notes.ui.search.event.SelectedPartUiEvent;
import ru.ezhov.notes.ui.terminal.ExecuteTerminalUiCommand;
import ru.ezhov.notes.util.search.Part;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class TextAreaPanel extends JPanel {
    private RSyntaxTextArea textArea;
    private SearchPanel searchPanel;
    private HintId hintId;

    public TextAreaPanel(HintId hintId, String text, boolean editable) {
        super(new BorderLayout());
        this.hintId = hintId;

        textArea = new RSyntaxTextArea();
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        textArea.setCodeFoldingEnabled(true);
        textArea.setText(text);

        textArea.setEditable(editable);

        TextAreaKeyListener textAreaKeyListener = new TextAreaKeyListener();
        textArea.addKeyListener(textAreaKeyListener);
        addKeyListener(textAreaKeyListener);

        RTextScrollPane sp = new RTextScrollPane(textArea);

        searchPanel = new SearchPanel(() -> textArea.getText());


        add(sp, BorderLayout.CENTER);


        textArea.getPopupMenu().add(new JMenuItem(new AbstractAction() {
            {
                putValue(Action.NAME, "Add and execute to terminal");
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                UiCommandFactory.inMemory().publish(new ExecuteTerminalUiCommand(hintId, textArea.getSelectedText()));
            }
        }));
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    UiCommandFactory.inMemory().publish(new ExecuteTerminalUiCommand(hintId, textArea.getSelectedText()));
                }
            }
        });

        searchPanel.register(new UiEventSubscriber() {
            @Override
            public void doOnEvent(UiEvent event) {
                if (event.getClass() == FindedPartsUiEvent.class) {
                    FindedPartsUiEvent uiEvent = (FindedPartsUiEvent) event;
                    SwingUtilities.invokeLater(() -> {
                        textArea.getHighlighter().removeAllHighlights();
                        final List<Part> parts = uiEvent.parts();
                        parts.forEach(p -> {
                            DefaultHighlighter.DefaultHighlightPainter highlightPainter =
                                    new DefaultHighlighter.DefaultHighlightPainter(Color.MAGENTA);
                            try {
                                textArea.getHighlighter().addHighlight(p.startPos(), p.length(), highlightPainter);
                            } catch (BadLocationException ex) {
                                ex.printStackTrace();
                            }
                        });
                    });
                } else if (event.getClass() == SelectedPartUiEvent.class) {
                    SelectedPartUiEvent uiEvent = (SelectedPartUiEvent) event;
                    SwingUtilities.invokeLater(() -> {
                        Part part = uiEvent.part();
                        textArea.setCaretPosition(part.startPos());
                    });
                }
            }

            @Override
            public List<Class> subscribeOn() {
                return Arrays.asList(
                        FindedPartsUiEvent.class,
                        SelectedPartUiEvent.class
                );
            }
        });
    }

    public String text() {
        return textArea.getText();
    }

    //TODO: remove
    public void setText(String text) {
        textArea.setText(text);
    }

    private class TextAreaKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_F) {
                SwingUtilities.invokeLater(() -> {
                    TextAreaPanel.this.add(searchPanel, BorderLayout.NORTH);
                    TextAreaPanel.this.revalidate();
                });
            }
        }
    }


}
