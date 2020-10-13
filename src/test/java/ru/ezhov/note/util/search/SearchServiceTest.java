package ru.ezhov.note.util.search;

import org.junit.jupiter.api.Test;

import java.util.List;

class SearchServiceTest {
    @Test
    public void text() {
        SearchService searchService = new SearchService("test test");

        final List<Part> test = searchService.parts("test");

        System.out.println(test);
    }

}