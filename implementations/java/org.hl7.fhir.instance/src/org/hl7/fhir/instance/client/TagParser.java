package org.hl7.fhir.instance.client;


/*
  Copyright (c) 2011+, HL7, Inc.
  All rights reserved.
  
  Redistribution and use in source and binary forms, with or without modification, 
  are permitted provided that the following conditions are met:
  
   * Redistributions of source code must retain the above copyright notice, this 
     list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above copyright notice, 
     this list of conditions and the following disclaimer in the documentation 
     and/or other materials provided with the distribution.
   * Neither the name of HL7 nor the names of its contributors may be used to 
     endorse or promote products derived from this software without specific 
     prior written permission.
  
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
  IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
  INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
  POSSIBILITY OF SUCH DAMAGE.
  
*/


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hl7.fhir.instance.model.Coding;

public class TagParser {
	
	public static final String LABEL = "label";
	public static final String SCHEME = "scheme";
	
	public static final String REGEX_CATEGORY = "((.+?)([;,]))";

	public List<Coding> parse(String categoryHeader) {
		List<Coding> tags = new ArrayList<Coding>();
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
				Coding category = new Coding(); // todo-bundle arguments[0], arguments[1], arguments[2]);
				tags.add(category);
				arguments = new String[3];
			}
		}
		if(high < categoryHeader.length()) {
			handleSentence(categoryHeader.substring(high).trim(), arguments);
		}
		Coding category = new Coding(); // todo-bundle arguments[0], arguments[1], arguments[2]);
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
