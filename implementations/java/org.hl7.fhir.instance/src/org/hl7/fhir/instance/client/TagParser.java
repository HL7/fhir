package org.hl7.fhir.instance.client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hl7.fhir.instance.model.AtomCategory;

public class TagParser {
	
	public static final String LABEL = "label";
	public static final String SCHEME = "scheme";
	
	public static final String REGEX_CATEGORY = "((.+?)([;,]))";

	public List<AtomCategory> parse(String categoryHeader) {
		List<AtomCategory> tags = new ArrayList<AtomCategory>();
		Matcher matcher = loadRegexMatcher(categoryHeader);
		int high = -1;
		String[] arguments = new String[3];
		while(matcher.find()) {
			String sentence = matcher.group().trim();
			boolean isLastSentenceInCategory = lastIsComma(sentence);
			sentence = stripPunctuation(sentence);
			handleSentence(sentence, arguments);
			if(high < matcher.end()) {
				high = matcher.end();
			}
			if(isLastSentenceInCategory){
				AtomCategory category = new AtomCategory(arguments[0], arguments[1], arguments[2]);
				tags.add(category);
				arguments = new String[3];
			}
		}
		if(high < categoryHeader.length()) {
			handleSentence(categoryHeader.substring(high).trim(), arguments);
		}
		AtomCategory category = new AtomCategory(arguments[0], arguments[1], arguments[2]);
		tags.add(category);
		return tags;
	}
	
	protected Matcher loadRegexMatcher(String categoryHeader) {
		Pattern pattern = Pattern.compile(REGEX_CATEGORY);
		return pattern.matcher(categoryHeader);
	}
	
	protected void handleSentence(String sentence, String[] arguments) {
		if(isLabel(sentence)) {
			arguments[2] = getLabel(sentence);
		} else if(isScheme(sentence)) {
			arguments[0] = getScheme(sentence);
		} else {
			arguments[1] = sentence;
		}
	}
	
	protected boolean lastIsComma(String sentence) {
		return sentence.substring(sentence.length() -1).equals(",");
	}
	
	protected boolean isLabel(String sentence) {
		return sentence.startsWith(LABEL);
	}
	
	protected String getLabel(String sentence) {
		return sentence.substring(sentence.indexOf('=') + 1);
	}
	
	protected String getScheme(String sentence) {
		return sentence.substring(sentence.indexOf('=') + 1);
	}
	
	protected boolean isScheme(String sentence) {
		return sentence.startsWith(SCHEME);
	}
	
	protected String stripPunctuation(String sentence) {
		if(lastCharacterIsPunctuation(sentence)) {
			return stripLastCharacter(sentence);
		} else {
			return sentence;
		}
	}
	
	protected boolean lastCharacterIsPunctuation(String sentence) {
		boolean isPunctuation = false;
		if(sentence.lastIndexOf(';') == sentence.length() - 1) {
			isPunctuation = true;
		} else if(sentence.lastIndexOf(',') == sentence.length() - 1) {
			isPunctuation = true;
		}
		return isPunctuation;
	}
	
	protected String stripLastCharacter(String sentence) {
		return sentence.substring(0, sentence.length() - 1);
	}
}
