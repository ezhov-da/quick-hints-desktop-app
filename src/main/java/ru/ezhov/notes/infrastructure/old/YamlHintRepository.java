package ru.ezhov.notes.infrastructure.old;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import ru.ezhov.notes.domain.Hint;
import ru.ezhov.notes.domain.HintId;
import ru.ezhov.notes.domain.HintRepository;
import ru.ezhov.notes.domain.HintsRepositoryException;
import ru.ezhov.notes.domain.NewHint;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class YamlHintRepository implements HintRepository {

    private File notesFile;

    public YamlHintRepository(File repositoryPath) {
        this.notesFile = repositoryPath;
    }

    @Override
    public List<Hint> all() throws HintsRepositoryException {
        return get().getHints().stream().map(HintBean::from).collect(Collectors.toList());
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
    public List<Hint> byCondition(String condition) throws HintsRepositoryException {
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
    public void change(HintId id, String name, String text) throws HintsRepositoryException {
        try {
            HintsBean hintsBean = get();
            final Iterator<HintBean> iterator = hintsBean.getHints().iterator();
            while (iterator.hasNext()) {
                final HintBean hint = iterator.next();
                if (hint.getId().equals(id.value())) {
                    iterator.remove();
                    break;
                }
            }
            hintsBean.getHints().add(new HintBean(id.value(), name, text));

            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            om.writeValue(notesFile, hintsBean);
        } catch (IOException e) {
            throw new HintsRepositoryException(e);
        }
    }

    @Override
    public void add(NewHint newHint) throws HintsRepositoryException {
        try {
            HintsBean hintsBean = get();
            hintsBean.getHints().add(new HintBean(UUID.randomUUID().toString(), newHint.name(), newHint.text()));

            ObjectMapper om = new ObjectMapper(new YAMLFactory());
            om.writeValue(notesFile, hintsBean);
        } catch (IOException e) {
            throw new HintsRepositoryException(e);
        }
    }

    @Override
    public void remove(HintId id) throws HintsRepositoryException {
        try {
            HintsBean hintsBean = get();
            final Iterator<HintBean> iterator = hintsBean.getHints().iterator();
            while (iterator.hasNext()) {
                final HintBean hint = iterator.next();
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
