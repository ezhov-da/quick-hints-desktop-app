package ru.ezhov.note.ui.search;

import ru.ezhov.note.ui.event.domain.UiEvent;
import ru.ezhov.note.ui.event.domain.UiEventSubscriber;
import ru.ezhov.note.ui.search.event.FindedPartsUiEvent;
import ru.ezhov.note.ui.search.event.SelectedPartUiEvent;
import ru.ezhov.note.util.search.Part;
import ru.ezhov.note.util.search.SearchService;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SearchPanel extends JPanel {
    private JTextField textField;
    private TextProducer textProducer;
    private List<UiEventSubscriber> subscribers = new ArrayList<>();
    private List<Part> parts = new ArrayList<>();
    private int currentIndex = 0;

    public SearchPanel(TextProducer textProducer) {
        super(new BorderLayout());
        this.textProducer = textProducer;

        textField = new JTextField();

        add(textField, BorderLayout.CENTER);

        JButton buttonNext = new JButton("next");
        add(buttonNext, BorderLayout.EAST);

        buttonNext.addActionListener(e -> {
            if (currentIndex >= parts.size()) {
                currentIndex = 0;
            }

            if (!parts.isEmpty()) {
                final Part part = parts.get(currentIndex);

                notifyAll(new SelectedPartUiEvent(part));

                currentIndex++;
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (!"".equals(textField.getText())) {
                    SearchService searchService = new SearchService(textProducer.text());
                    List<Part> parts = searchService.parts(textField.getText());

                    initNewParts(parts);

                    SearchPanel.this.notifyAll(new FindedPartsUiEvent(parts));
                }
            }
        });
    }

    private void initNewParts(List<Part> parts) {
        this.parts = parts;
        this.currentIndex = 0;
    }

    public void register(UiEventSubscriber eventSubscriber) {
        subscribers.add(eventSubscriber);
    }

    private void notifyAll(UiEvent event) {
        subscribers.forEach(s -> s.doOnEvent(event));
    }
}