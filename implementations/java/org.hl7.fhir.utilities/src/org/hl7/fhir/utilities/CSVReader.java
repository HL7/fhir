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
package org.hl7.fhir.utilities;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Baseclass for readers that read data from files in comma separated file format
 * @author Ewout
 *
 */
public class CSVReader extends InputStreamReader {
	
	public CSVReader(InputStream in) throws UnsupportedEncodingException {
		super(in, "UTF-8");
	}

	protected boolean parseBoolean(String column) {
		if (column == null)
			return false;
		else if (column.equalsIgnoreCase("y") || column.equalsIgnoreCase("yes") || column.equalsIgnoreCase("true") || column.equalsIgnoreCase("1"))
			return true;
		else
			return false;
	}

	protected static String getColumn(String[] titles, String[] values, String column) throws Exception {
		int c = -1;
	//	String s = "";
		for (int i = 0; i < titles.length; i++) {
		//	s = s + ","+titles[i];
			if (titles[i].equalsIgnoreCase(column))
				c = i;
		}
		if (c == -1)
			return ""; // throw new Exception("unable to find column "+column+" in "+s.substring(1));
		else if (values.length <= c)
			return "";
		else
			return values[c];
	}

	
	/**
	 * Split one line in a CSV file into its rows. Comma's appearing in double quoted strings will
	 * not be seen as a separator.
	 * @return
	 * @throws Exception
	 */
	protected String[] parseLine() throws Exception {
		List<String> res = new ArrayList<String>();
		StringBuilder b = new StringBuilder();
		boolean inQuote = false;

		while (inQuote || (peek() != '\r' && peek() != '\n')) {
			char c = peek();
			next();
			if (c == '"') 
				inQuote = !inQuote;
			else if (!inQuote && c == ',') {
				res.add(b.toString().trim());
				b = new StringBuilder();
			}
			else 
				b.append(c);
		}
		res.add(b.toString().trim());
		while (ready() && (peek() == '\r' || peek() == '\n')) {
			next();
		}
		
		String[] r = new String[] {};
		r = res.toArray(r);
		return r;
		
	}

	private int state = 0;
	private char pc;
	
	private char peek() throws Exception
	{
	  if (state == 0)
		  next();
	  if (state == 1)
		  return pc;
	  else
		  throw new Exception("read past end of source");
	}
	
	private void next() throws Exception
	{
		  if (state == 2)
			  throw new Exception("read past end of source");
          state = 1;
		  int i = read();
		  if (i == -1)
			  state = 2;
		  else 
			  pc = (char) i;
	}

}
