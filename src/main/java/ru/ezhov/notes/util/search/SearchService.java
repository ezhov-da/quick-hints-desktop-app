package ru.ezhov.notes.util.search;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private final String inputText;

    public SearchService(String inputText) {
        this.inputText = inputText;
    }

    public List<Part> parts(String search) {
        List<Part> parts = new ArrayList<>();
        int currentIndex = 0;
        while (currentIndex < inputText.length()) {
            if (!(inputText.length() < currentIndex + search.length())) {
                final String substring = inputText.substring(currentIndex, currentIndex + search.length());
                if (substring.equals(search)) {
                    parts.add(new Part(currentIndex, currentIndex + search.length()));
                    currentIndex = currentIndex + search.length();
                    continue;
                }
            }
            currentIndex++;
        }

        return parts;
    }
}
