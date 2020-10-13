package ru.ezhov.note.infrastructure.old;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ru.ezhov.note.domain.Note;
import ru.ezhov.note.domain.NoteId;
import ru.ezhov.note.domain.NoteRepository;
import ru.ezhov.note.domain.HintsRepositoryException;
import ru.ezhov.note.domain.NewNote;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class YamlNoteRepository implements NoteRepository {

    private File notesFile;

    public YamlNoteRepository(File repositoryPath) {
        this.notesFile = repositoryPath;
    }

    @Override
    public List<Note> all() throws HintsRepositoryException {
        return get().getHints().stream().map(NoteBean::from).collect(Collectors.toList());
    }

    private HintsBean get() throws HintsRepositoryException {
        try {
            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            if (notesFile.exists() && notesFile.length() > 0) {
                return om.readValue(notesFile, HintsBean.class);
            } else {
                return new HintsBean();
            }
        } catch (IOException e) {
            throw new HintsRepositoryException(e);
        }
    }

    @Override
    public List<Note> byCondition(String condition) throws HintsRepositoryException {
        try {
            return all()
                    .stream()
                    .filter(h -> h.name().value().contains(condition))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new HintsRepositoryException(e);
        }
    }

    @Override
    public void change(NoteId id, String name, String text) throws HintsRepositoryException {
        try {
            HintsBean hintsBean = get();
            final Iterator<NoteBean> iterator = hintsBean.getHints().iterator();
            while (iterator.hasNext()) {
                final NoteBean hint = iterator.next();
                if (hint.getId().equals(id.value())) {
                    iterator.remove();
                    break;
                }
            }
            hintsBean.getHints().add(new NoteBean(id.value(), name, text));

            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            om.writeValue(notesFile, hintsBean);
        } catch (IOException e) {
            throw new HintsRepositoryException(e);
        }
    }

    @Override
    public void add(NoteId noteId, NewNote newNote) throws HintsRepositoryException {
        try {
            HintsBean hintsBean = get();
            hintsBean.getHints().add(new NoteBean(noteId.value(), newNote.name(), newNote.text()));

            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            om.writeValue(notesFile, hintsBean);
        } catch (IOException e) {
            throw new HintsRepositoryException(e);
        }
    }

    @Override
    public void remove(NoteId id) throws HintsRepositoryException {
        try {
            HintsBean hintsBean = get();
            final Iterator<NoteBean> iterator = hintsBean.getHints().iterator();
            while (iterator.hasNext()) {
                final NoteBean hint = iterator.next();
                if (hint.getId().equals(id.value())) {
                    iterator.remove();
                    break;
                }
            }

            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            om.writeValue(notesFile, hintsBean);
        } catch (IOException e) {
            throw new HintsRepositoryException(e);
        }
    }
}
