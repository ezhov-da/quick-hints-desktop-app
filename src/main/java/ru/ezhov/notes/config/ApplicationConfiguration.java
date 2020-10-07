package ru.ezhov.notes.config;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationConfiguration {
    private static final Logger LOG = Logger.getLogger(ApplicationConfiguration.class.getName());


    public static String filePathRepository() {
        String property = System.getProperty(
                "notes.file.repository",
                System.getenv("NOTES_FILE_REPOSITORY")
        );
        if (property == null) {
            property = System.getProperty("user.home") + File.separator + "notes.yml";

            LOG.log(Level.INFO, "notes.file.repository: {0}", property);
        }

        return property;
    }

    public static String shellExecuteCommand() {
        String property = System.getProperty(
                "notes.shell.command",
                System.getenv("NOTES_SHELL_COMMAND")
        );
        LOG.log(Level.INFO, "notes.shell.command: {0}", property);

        return property;
    }
}
