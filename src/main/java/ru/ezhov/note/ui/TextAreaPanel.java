package ru.ezhov.note.ui;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.ui.command.infrastructure.UiCommandFactory;
import ru.ezhov.note.ui.event.domain.UiEvent;
import ru.ezhov.note.ui.event.domain.UiEventSubscriber;
import ru.ezhov.note.ui.search.SearchPanel;
import ru.ezhov.note.ui.search.event.FindedPartsUiEvent;
import ru.ezhov.note.ui.search.event.SelectedPartUiEvent;
import ru.ezhov.note.ui.terminal.ExecuteTerminalUiCommand;
import ru.ezhov.note.util.search.Part;

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
    private NoteId noteId;

    public TextAreaPanel(NoteId noteId, String text, boolean editable) {
        super(new BorderLayout());
        this.noteId = noteId;

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
                UiCommandFactory.inMemory().publish(new ExecuteTerminalUiCommand(noteId, textArea.getSelectedText()));
            }
        }));
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String selectedText = textArea.getSelectedText();
                    if (selectedText == null || "".equals(textArea.getSelectedText())) {
                        try {
                            int lineNum = textArea.getCaretLineNumber();
                            int startOffset = textArea.getLineStartOffset(lineNum);
                            int endOffset = textArea.getLineEndOffset(lineNum);

                            String currentLineText = textArea.getText(startOffset, endOffset - startOffset);

                            if (!"".equals(currentLineText)) {
                                UiCommandFactory.inMemory().publish(new ExecuteTerminalUiCommand(noteId, currentLineText));
                            }
                        } catch (BadLocationException badLocationException) {
                            //nothing
                        }
                    } else {
                        UiCommandFactory.inMemory().publish(new ExecuteTerminalUiCommand(noteId, selectedText));
                    }
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
