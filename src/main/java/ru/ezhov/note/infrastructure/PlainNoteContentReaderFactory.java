package ru.ezhov.note.infrastructure;

import ru.ezhov.note.domain.NoteContentReader;
import ru.ezhov.note.domain.NoteContentReaderFactory;
import ru.ezhov.note.domain.NoteContentReaderFactoryException;
import ru.ezhov.note.domain.NoteType;

public class PlainNoteContentReaderFactory implements NoteContentReaderFactory {
    private static NoteContentReaderFactory noteContentReaderFactory;

    private PlainNoteContentReaderFactory() {

    }

    public static NoteContentReaderFactory instance(){
        if(noteContentReaderFactory == null){
            noteContentReaderFactory = new PlainNoteContentReaderFactory();
        }
        return noteContentReaderFactory;
    }

    @Override
    public NoteContentReader by(NoteType noteType) throws NoteContentReaderFactoryException {
        NoteContentReader noteContentReader;
        switch (noteType){
            case TEXT:
                noteContentReader =  new PlaintTextNoteContentReader();
                break;
            case URL:
                noteContentReader =  new UrlNoteContentReader();
                break;
            case FILE:
                noteContentReader = new FileNoteContentReader();
                break;
            default:
                throw new NoteContentReaderFactoryException("Unsupported hint type reader '" + noteType + "'");

        }
        return noteContentReader;
    }
}
