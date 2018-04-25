package ru.ezhov.regularexpression.template;

import java.util.*;
import java.util.logging.Logger;
import java.util.regex.*;

/**
 * Created by ezhov_da on 24.04.2018.
 */
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
