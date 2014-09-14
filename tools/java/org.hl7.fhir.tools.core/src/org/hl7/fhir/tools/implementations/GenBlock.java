package org.hl7.fhir.tools.implementations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.hl7.fhir.utilities.TextFile;

/*
Copyright (c) 2011+, HL7, Inc
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
public class GenBlock {
	
	protected Stack<Integer> marginHistory = new Stack<Integer>();
	protected Stack<Integer> scopeHistory = new Stack<Integer>();
	
	protected int margin = 0;
	
	protected List<String> lines = new ArrayList<String>();
			
	public void begin()
	{
		scopeHistory.push( lines.size() );
	}
 
	public GenBlock end()
	{
		int scopeBegin = 0;
		
		if( scopeHistory.isEmpty() )
			ln("?unmatched begin/end: end found without begin?");
		else
			scopeBegin = scopeHistory.pop();
		
		GenBlock result = new GenBlock();
			
		for( int linenr = scopeBegin; linenr < lines.size(); linenr++ )
				result.addLine(lines.get(linenr));

		return result;
	}
	
	public void inc(GenBlock other)
	{
		if( other == this )
			ln("?circular include?");
		
		for( String line : other.lines )
			ln(line);
	}
	
	public void bs()
	{
		marginHistory.push(margin);
		margin += 4;		
	}
	
	public void bs(String start)
	{
		ln(start);
		bs();
	}
	
	public void es()
	{
		if( marginHistory.isEmpty() )
			ln( "?Unmatched number of bs/es blocks: es without bs?");
		else
			margin = marginHistory.pop();
	}
	
	public void es(String end)
	{
		es();
		ln(end);
	}
	
	
	public void ln(String line)
	{	
		addLine(genMargin(margin));
		
		nl(line);
	}
	
	public void ln()
	{
		ln("");
	}
	
	public void nl(String literal)
	{
		if( literal == null ) literal = "?null?";
		
		if( lines.size() == 0 ) ln();
		
		concat(literal);		
	}
	
	protected void addLine(String line)
	{
		lines.add(line);
	}
	
	protected void concat(String literal)
	{
		lines.set(lines.size()-1, 
				lines.get(lines.size()-1).concat(literal));
	}
	
	@Override
	public String toString()
	{
		if( !marginHistory.isEmpty() )
			ln( "?Unmatched number of bs/es blocks: bs without es?" );

		if( lines.size() == 0) return "";
		
		StringBuffer result = new StringBuffer();
			
		for( String line : lines)
			result.append(line + "\r\n");
				
		return result.toString();
	}
	
	public void toFile(String path) throws Exception
	{
		TextFile.stringToFile(this.toString(),path);
	}
	
	private static String genMargin(int size)
	{
		if( size == 0 ) return "";
		
		char[] charArray = new char[size];
		Arrays.fill(charArray,' ');
		return new String(charArray);
	}
}
