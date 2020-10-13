package ru.ezhov.note;

import com.formdev.flatlaf.FlatLightLaf;
import ru.ezhov.note.application.NoteApplicationServiceFactory;
import ru.ezhov.note.config.ApplicationConfiguration;
import ru.ezhov.note.infrastructure.PlainNoteContentReaderFactory;
import ru.ezhov.note.infrastructure.event.InMemoryNoteEventPublisher;
import ru.ezhov.note.infrastructure.old.YamlNoteRepository;
import ru.ezhov.note.template.infrastructure.VelocityEngineImpl;
import ru.ezhov.note.ui.BasicPanel;
import ru.ezhov.note.ui.BasicWindow;
import ru.ezhov.note.ui.SingletonBasicWindow;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.io.File;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class AppNotes {
    private static final Logger LOG = Logger.getLogger(AppNotes.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    AppNotes.class.getResourceAsStream("/log.properties")
            );
        } catch (Exception ex) {
            Logger.getLogger(AppNotes.class.getName())
                    .log(Level.SEVERE, "Не удалось установить настройки логгера", ex);
        }
        LOG.log(Level.INFO, "run application");
        try {

            UIManager.setLookAndFeel(new FlatLightLaf());

//            ru.ezhov.note.util.LookAndFeel.setLookAndFeel();
//            setUIFont(new javax.swing.plaf.FontUIResource(new RSyntaxTextArea().getFont()));

            String finalFilePathRepository = ApplicationConfiguration.filePathRepository();

            NoteApplicationServiceFactory noteApplicationServiceFactory = new NoteApplicationServiceFactory(
                    InMemoryNoteEventPublisher.getInstance(),
                    new YamlNoteRepository(new File(finalFilePathRepository)),
                    PlainNoteContentReaderFactory.instance(),
                    new VelocityEngineImpl()
            );


            SwingUtilities.invokeAndWait(() -> {
                BasicWindow window = SingletonBasicWindow.getInstance();
                window.setLayout(new BorderLayout());
                window.getContentPane().add(
                        new BasicPanel(noteApplicationServiceFactory),
                        BorderLayout.CENTER
                );
                window.setVisible(true);
            });
        } catch (Exception ex) {
            LOG.log(Level.WARNING, null, ex);
        }
    }

    private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }
}