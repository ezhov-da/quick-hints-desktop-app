package ru.ezhov.notes;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import ru.ezhov.notes.infrastructure.old.YamlHintRepository;
import ru.ezhov.notes.template.infrastructure.VelocityEngineImpl;
import ru.ezhov.notes.ui.BasicPanel;
import ru.ezhov.notes.ui.BasicWindow;
import ru.ezhov.notes.ui.SingletonBasicWindow;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.io.File;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class AppHints {
    private static final Logger LOG = Logger.getLogger(AppHints.class.getName());

    public static void main(String[] args) {
        String filePathRepository = System.getProperty("notes.file.repository", System.getenv("NOTES_FILE_REPOSITORY"));
        if (filePathRepository == null) {
            filePathRepository = System.getProperty("user.home") + File.separator + "notes.yml";

            LOG.log(Level.INFO, "set storage as {0}", filePathRepository);
        }

        try {
            LogManager.getLogManager().readConfiguration(
                    AppHints.class.getResourceAsStream("/log.properties")
            );
        } catch (Exception ex) {
            Logger.getLogger(AppHints.class.getName())
                    .log(Level.SEVERE, "Не удалось установить настройки логгера", ex);
        }
        LOG.log(Level.INFO, "run application");
        try {
            ru.ezhov.notes.util.LookAndFeel.setLookAndFeel();
            setUIFont(new javax.swing.plaf.FontUIResource(new RSyntaxTextArea().getFont()));
            String finalFilePathRepository = filePathRepository;
            SwingUtilities.invokeAndWait(() -> {
                BasicWindow window = SingletonBasicWindow.getInstance();
                window.setLayout(new BorderLayout());
                window.getContentPane().add(
                        new BasicPanel(
                                new YamlHintRepository(new File(finalFilePathRepository)),
                                new VelocityEngineImpl()
                        ),
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