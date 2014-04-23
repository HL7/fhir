package org.hl7.fhir.definitions.parsers;
/*
Copyright (c) 2011-2013, HL7, Inc
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

import org.hl7.fhir.definitions.model.TypeRef;

public class TypeParser {

	public List<TypeRef> parse(String n) throws Exception {
		ArrayList<TypeRef> a = new ArrayList<TypeRef>();
		
		Tokeniser tkn = new Tokeniser(n);
		while (tkn.more()) {
			TypeRef t = new TypeRef();
			t.setName(tkn.next());
			String p = tkn.next();
			if (p.equals("(")) {
				t.getParams().add(tkn.next());
				p = tkn.next();
				while (p.equals("|")) {
					t.getParams().add(tkn.next());
					p = tkn.next();
				}
				if (!p.equals(")"))
					throw new Exception("Unexpected token '"+p+"' in type "+n);
				p = tkn.next();
			}
			if (!p.equals("|") && !p.equals(""))
				throw new Exception("Unexpected token '"+p+"' in type "+n);
			a.add(t);
		}
		
		return a;
	}

	private class Tokeniser {
		private String source;
		private int cursor;
		
		private Tokeniser(String src) {
			source = src;
			cursor = 0;
		}
		
		private boolean more() {
			return cursor < source.length();
		}
		
		private String next() throws Exception {
			while (more() && Character.isWhitespace(source.charAt(cursor)))
				cursor++;
			if (!more())
				return "";
			char c = source.charAt(cursor);
			cursor++;
			if (c == '(' || c == ')' || c == '|' || c == '*')
				return String.valueOf(c);
			else if (Character.isLetter(c) || c == '['  || c == '='  || c == '@') {
				StringBuilder b = new StringBuilder();
				b.append(c);
				while (more() && (Character.isLetter(source.charAt(cursor)) || Character.isDigit(source.charAt(cursor)) || source.charAt(cursor) == ':' 
						|| source.charAt(cursor) == '[' || source.charAt(cursor) == ']' || source.charAt(cursor) == '@' || source.charAt(cursor) == '=' || source.charAt(cursor) == '.')) {
					b.append(source.charAt(cursor));
					cursor++;
				}
				return b.toString();
			} else 
				throw new Exception("Unexpected token '"+c+"' in type "+source);
		}
	}
	
}
