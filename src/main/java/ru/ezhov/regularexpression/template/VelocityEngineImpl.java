package ru.ezhov.regularexpression.template;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VelocityEngineImpl implements Engine {
    private static final Logger LOG = Logger.getLogger(VelocityEngineImpl.class.getName());

    private String text;

    public VelocityEngineImpl(String text) {
        this.text = text;
    }

    public List<String> words() {
        List<String> words = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\$\\w+");
        Matcher matcher = pattern.matcher(text.replace("\n\r", ""));
        while (matcher.find()) {
            String w = matcher.group();
            if (!words.contains(w)) {
                words.add(matcher.group());
            }
        }
        return words;
    }
}
