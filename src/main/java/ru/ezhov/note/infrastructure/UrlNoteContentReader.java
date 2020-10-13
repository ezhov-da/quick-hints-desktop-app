package ru.ezhov.note.infrastructure;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.ezhov.note.domain.Note;
import ru.ezhov.note.domain.NoteContent;
import ru.ezhov.note.domain.NoteContentReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class UrlNoteContentReader implements NoteContentReader {
    @Override
    public NoteContent read(Note note) {
        String returnText = note.text().value();
        final String[] split = returnText.split("\n");
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

        return new NoteContent(returnText);
    }
}
