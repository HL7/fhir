package org.hl7.fhir.tools.implementations.csharp;
/*
Copyright (c) 2011-2014, HL7, Inc
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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.TextFile;

public class CSharpProjectGenerator 
{	
	public static void buildProjectFile( String destDir, List<String> cSharpProjectFiles ) throws IOException
	{
		if( !destDir.endsWith(File.separator) )
			destDir += File.separator;

		// Generate "legacy" .NET 4.0 project
		List<String> templateContents = TextFile.readAllLines(destDir + "Hl7.Fhir.Model.csproj.template"); 	
		List<String> itemGroup = buildItemGroupContents(cSharpProjectFiles);
		List<String> outputLines = replaceTemplateVar( templateContents, "@@@MODELFILES@@@", itemGroup);
		TextFile.writeAllLines(destDir + "Hl7.Fhir.Model.csproj", outputLines);
		
		// Generate "up-to-date" .NET 4.5 project
		templateContents = TextFile.readAllLines(destDir + "Hl7.Fhir.Model.Net45.csproj.template"); 	
		outputLines = replaceTemplateVar( templateContents, "@@@MODELFILES@@@", itemGroup);
		TextFile.writeAllLines(destDir + "Hl7.Fhir.Model.Net45.csproj", outputLines);
		
		// Generate Portable45 project (profile 259)
		templateContents = TextFile.readAllLines(destDir + "Hl7.Fhir.Model.Portable45.csproj.template"); 	
		outputLines = replaceTemplateVar( templateContents, "@@@MODELFILES@@@", itemGroup);
		TextFile.writeAllLines(destDir + "Hl7.Fhir.Model.Portable45.csproj", outputLines);
	}
	
	
	public static void setAssemblyVersionInProperties(String destDir, String version, String svnRevision) throws IOException
	{
	  if( !destDir.endsWith(File.separator) )
      destDir += File.separator;
	  
	  String filename = destDir + "AssemblyInfo.cs";
	  List<String> assemblyInfoContents = TextFile.readAllLines(filename + ".template");  
    List<String> outputLines = replaceAssemblyVersion(assemblyInfoContents, version, svnRevision);
    TextFile.writeAllLines(filename, outputLines);
	}

	private static List<String> replaceAssemblyVersion(List<String> source, String version, String svnRevision)
	{
	  String generatedVersion = version + "." + svnRevision;
	  List<String> result = new ArrayList<String>();
	  
	  for( String line : source)
    {
	    if(line.trim().startsWith("[assembly: AssemblyVersion"))
	      result.add("[assembly: AssemblyVersion(\"" + generatedVersion + "\")]");
	    else if(line.trim().startsWith("[assembly: AssemblyFileVersion"))
	      result.add("[assembly: AssemblyFileVersion(\"" + generatedVersion + "\")]");
	    else
	      result.add(line);
    }
	  
	  return result;
	}
	
	private static List<String> replaceTemplateVar( List<String> source, String template, List<String> contents)
	{
		List<String> result = new ArrayList<String>();

		for( String line : source)
		{
			if( !line.trim().equals(template) )
				result.add(line);
			else
				result.addAll(contents);
		}
		
		return result;
	}
	
	private static List<String> buildItemGroupContents(List<String> files)
	{
		List<String> result = new ArrayList<String>();
		
		for( String fileName : files)
		{
			StringBuilder b = new StringBuilder();
			b.append("\t<Compile Include=\"");
			b.append(fileName);
			b.append("\" />");
			
			result.add(b.toString());
		}
	
		return result;
	}
}
