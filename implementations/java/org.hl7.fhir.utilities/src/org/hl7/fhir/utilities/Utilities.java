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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Utilities {

	 private static final String TOKEN_REGEX = "^a-z[A-Za-z0-9]*$";


  /**
     * Returns the plural form of the word in the string.
     * 
     * Examples:
     * 
     * <pre>
     *   inflector.pluralize(&quot;post&quot;)               #=&gt; &quot;posts&quot;
     *   inflector.pluralize(&quot;octopus&quot;)            #=&gt; &quot;octopi&quot;
     *   inflector.pluralize(&quot;sheep&quot;)              #=&gt; &quot;sheep&quot;
     *   inflector.pluralize(&quot;words&quot;)              #=&gt; &quot;words&quot;
     *   inflector.pluralize(&quot;the blue mailman&quot;)   #=&gt; &quot;the blue mailmen&quot;
     *   inflector.pluralize(&quot;CamelOctopus&quot;)       #=&gt; &quot;CamelOctopi&quot;
     * </pre>
     * 
     * 
     * 
     * Note that if the {@link Object#toString()} is called on the supplied object, so this method works for non-strings, too.
     * 
     * 
     * @param word the word that is to be pluralized.
     * @return the pluralized form of the word, or the word itself if it could not be pluralized
     * @see #singularize(Object)
     */
    public static String pluralizeMe( String word ) {
    	Inflector inf = new Inflector();
    	return inf.pluralize(word);
    }
    
  
	public static boolean IsInteger(String string) {
		try {
			int i = Integer.parseInt(string);
			return i != i+1;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String camelCase(String value) {
	  return new Inflector().camelCase(value.trim().replace(" ", "_"), false);
	}
	
	public static String escapeXml(String doco) {
		if (doco == null)
			return "";
		
		StringBuilder b = new StringBuilder();
		for (char c : doco.toCharArray()) {
		  if (c == '<')
			  b.append("&lt;");
		  else if (c == '>')
			  b.append("&gt;");
		  else if (c == '&')
			  b.append("&amp;");
      else if (c == '"')
        b.append("&quot;");
		  else 
			  b.append(c);
		}		
		return b.toString();
	}

	
	public static String capitalize(String s)
	{
		if( s == null ) return null;
		if( s.length() == 0 ) return s;
		if( s.length() == 1 ) return s.toUpperCase();
		
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
  public static void copyDirectory(String sourceFolder, String destFolder) throws Exception {
    CSFile src = new CSFile(sourceFolder);
    if (!src.exists())
      throw new Exception("Folder " +sourceFolder+" not found");
    createDirectory(destFolder);
    
   String[] files = src.list();
   for (String f : files) {
     if (new CSFile(sourceFolder+File.separator+f).isDirectory()) {
       copyDirectory(sourceFolder+File.separator+f, destFolder+File.separator+f);
     } else
       copyFile(new CSFile(sourceFolder+File.separator+f), new CSFile(destFolder+File.separator+f));
   }
  }
	
  public static void copyFile(String source, String dest) throws IOException {
    copyFile(new File(source), new File(dest));
  }

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if(!destFile.exists()) {
			if (!new CSFile(destFile.getParent()).exists()) {
				createDirectory(destFile.getParent());
			}
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		finally {
			if(source != null) {
				source.close();
			}
			if(destination != null) {
				destination.close();
			}
		}
	}

  public static boolean checkFolder(String dir, List<String> errors) {
    if (!new CSFile(dir).exists()) {
      errors.add("Unable to find directory "+dir);
      return false;
    } else {
      return true;
    }
  }

  public static boolean checkFile(String purpose, String dir, String file, List<String> errors) {
    if (!new CSFile(dir+file).exists()) {
      errors.add("Unable to find "+purpose+" file "+file+" in "+dir);
      return false;
    } else {
      return true;
    }
  }

  public static String asCSV(List<String> strings) {
    StringBuilder s = new StringBuilder();
    boolean first = true;
    for (String n : strings) {
      if (!first)
        s.append(",");
      s.append(n);
      first = false;
    }
    return s.toString();
  }

  public static String asHtmlBr(String prefix, List<String> strings) {
    StringBuilder s = new StringBuilder();
    boolean first = true;
    for (String n : strings) {
      if (!first)
        s.append("<br/>");
      s.append(prefix);
      s.append(n);
      first = false;
    }
    return s.toString();
  }

  public static void clearDirectory(String folder) {
	  String[] files = new CSFile(folder).list();
	  if (files != null) {
		  for (String f : files) {
			  File fh = new CSFile(folder+File.separatorChar+f);
			  if (fh.isDirectory()) 
				  clearDirectory(fh.getAbsolutePath());
			  fh.delete();
		  }
	  }
  }

  public static void createDirectory(String path) {
    new CSFile(path).mkdirs();    
  }

  public static String changeFileExt(String name, String ext) {
    if (name.lastIndexOf('.') > -1)
      return name.substring(0, name.lastIndexOf('.')) + ext;
    else
      return name+ext;
  }
  
  public static String cleanupTextString( String contents )
  {
	  if( contents == null || contents.trim().equals("") )
		  return null;
	  else
		  return contents.trim();
  }


  public static boolean noString(String v) {
    return v == null || v.equals("");
  }


  public static byte[] transform(Map<String, byte[]> files, byte[] source, byte[] xslt) throws Exception {
    TransformerFactory f = TransformerFactory.newInstance();
    StreamSource xsrc = new StreamSource(new ByteArrayInputStream(xslt));
    f.setURIResolver(new ZipURIResolver(files));
    Transformer t = f.newTransformer(xsrc);

    t.setURIResolver(new ZipURIResolver(files));
    StreamSource src = new StreamSource(new ByteArrayInputStream(source));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    StreamResult res = new StreamResult(out);
    t.transform(src, res);
    return out.toByteArray();    
  }
  
  public static void bytesToFile(byte[] content, String filename) throws Exception {
    FileOutputStream out = new FileOutputStream(filename);
    out.write(content);
    out.close();
    
  }


  public static void transform(String xsltDir, String source, String xslt, String dest) throws Exception {
    /* default java approach, but this doesn't support xslt2
    TransformerFactory f = TransformerFactory.newInstance();
    StreamSource xsrc = new StreamSource(new FileInputStream(xslt));
    f.setURIResolver(new MyURIResolver(xsltDir));
    Transformer t = f.newTransformer(xsrc);

    t.setURIResolver(new MyURIResolver(xsltDir));
    StreamSource src = new StreamSource(new FileInputStream(source));
    StreamResult res = new StreamResult(new FileOutputStream(dest));
    t.transform(src, res);
    */
    TransformerFactory f = TransformerFactory.newInstance();
    StreamSource xsrc = new StreamSource(new FileInputStream(xslt));
    f.setURIResolver(new MyURIResolver(xsltDir));
    Transformer t = f.newTransformer(xsrc);

    t.setURIResolver(new MyURIResolver(xsltDir));
    StreamSource src = new StreamSource(new FileInputStream(source));
    StreamResult res = new StreamResult(new FileOutputStream(dest));
    t.transform(src, res);
    
  }


  public static String appendSlash(String definitions) {
    return definitions.endsWith(File.separator) ? definitions : definitions+File.separator;
  }


  public static String fileTitle(String file) {
    String s = new File(file).getName();
    return s.substring(0, s.indexOf("."));
  }


  public static String systemEol()
  {
	  return System.getProperty("line.separator");
  }

  public static String normaliseEolns(String value) {
    return value.replace("\r\n", "\r").replace("\n", "\r").replace("\r", "\r\n");
  }


  public static String unescapeXml(String xml) throws Exception {
    if (xml == null)
      return null;
    
    StringBuilder b = new StringBuilder();
    int i = 0;
    while (i < xml.length()) {
      if (xml.charAt(i) == '&') {
        StringBuilder e = new StringBuilder();
        i++;
        while (xml.charAt(i) != ';') {
          e.append(xml.charAt(i));
          i++;
        }
        if (e.toString().equals("lt")) 
          b.append("<");
        else if (e.toString().equals("gt")) 
          b.append(">");
        else if (e.toString().equals("amp")) 
          b.append("&");
        else if (e.toString().equals("quot")) 
          b.append("\"");
        else if (e.toString().equals("mu"))
          b.append((char)956);          
        else
          throw new Exception("unknown XML entity \""+e.toString()+"\"");
      }  else
        b.append(xml.charAt(i));
      i++;
    }   
    return b.toString();
  }


  public static boolean isPlural(String word) {
    word = word.toLowerCase();
    if ("restricts".equals(word) || "data".equals(word))
      return false;
    Inflector inf = new Inflector();
    return !inf.singularize(word).equals(word);
  }


  public static String padLeft(String src, char c, int len) {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < len - src.length(); i++)
      s.append(c);
    s.append(src);
    return s.toString();
    
  }


  public static String path(String... args) {
    StringBuilder s = new StringBuilder();
    boolean d = false;
    for(String arg: args) {
      if (!d)
        d = true;
      else if (!s.toString().endsWith(File.separator))
        s.append(File.separator);
      s.append(arg);
    }
    return s.toString();
  }



//  public static void checkCase(String filename) {
//    File f = new CSFile(filename);
//    if (!f.getName().equals(filename))
//      throw new Exception("Filename  ")
//    
//  }

  public static String nmtokenize(String cs) {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < cs.length(); i++) {
      char c = cs.charAt(i);
      if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '-' || c == '_')
        s.append(c);
      else if (c != ' ')
        s.append("."+Integer.toString(c));
    }
    return s.toString();
  }


  public static boolean isToken(String tail) {
    if (tail == null || tail.length() == 0)
      return false;
    boolean result = isAlphabetic(tail.charAt(0));
    for (int i = 1; i < tail.length(); i++) {
    	result = result && (isAlphabetic(tail.charAt(i)) || isDigit(tail.charAt(i)) || (tail.charAt(i) == '_')  || (tail.charAt(i) == '[') || (tail.charAt(i) == ']'));
    }
    return result;
  }


  private static boolean isDigit(char c) {
    return (c >= '0') && (c <= '9');
  }


  private static boolean isAlphabetic(char c) {
    return ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
  }


  public static String getDirectoryFoFile(String filepath) {
    int i = filepath.lastIndexOf(File.separator);
    if (i == -1)
      return filepath;
    else
      return filepath.substring(0, i);
  }
	
}
