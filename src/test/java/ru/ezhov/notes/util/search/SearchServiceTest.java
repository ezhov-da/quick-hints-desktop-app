package ru.ezhov.notes.util.search;

import org.junit.jupiter.api.Test;
import ru.ezhov.notes.util.search.SearchService;

import java.util.List;

class SearchServiceTest {
    @Test
    public void text() {
        SearchService searchService = new SearchService("test test");

        final List<Part> test = searchService.parts("test");

        System.out.println(test);
    }

}