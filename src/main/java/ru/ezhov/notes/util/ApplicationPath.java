package ru.ezhov.notes.util;

import ru.ezhov.notes.AppHints;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author RRNDeonisiusEZH
 */
public class ApplicationPath {
    public static final String ENC = "UTF-8";

    public static String getPath() throws UnsupportedEncodingException {
        String path = new File(AppHints.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + "/";
        return URLDecoder.decode(path, ENC);
    }
}
