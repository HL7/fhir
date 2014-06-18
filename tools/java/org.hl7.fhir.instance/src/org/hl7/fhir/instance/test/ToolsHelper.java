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
package org.hl7.fhir.instance.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.hl7.fhir.instance.formats.JsonComposer;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.formats.ParserBase.ResourceOrFeed;
import org.hl7.fhir.instance.model.Constants;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class ToolsHelper {

  public static void main(String[] args) {   
    try {
      ToolsHelper self = new ToolsHelper();
      if (args[0].equals("round")) 
        self.executeRoundTrip(args);
      else if (args[0].equals("json")) 
        self.executeJson(args);
      else if (args[0].equals("version")) 
        self.executeVersion(args);
      else if (args[0].equals("fragments")) 
          self.executeFragments(args);
      else 
        throw new Exception("Unknown command '"+args[0]+"'");
    } catch (Throwable e) {
      try {
        e.printStackTrace();
        TextFile.stringToFile(e.toString(), args[1]+".err");
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
  }

	protected XmlPullParser loadXml(InputStream stream) throws Exception {
	BufferedInputStream input = new BufferedInputStream(stream);
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance(System.getProperty(XmlPullParserFactory.PROPERTY_NAME), null);
    factory.setNamespaceAware(true);
    XmlPullParser xpp = factory.newPullParser();
    xpp.setInput(input, "UTF-8");
    xpp.next();
    return xpp;
  }
	 
  protected int nextNoWhitespace(XmlPullParser xpp) throws Exception {
    int eventType = xpp.getEventType();
    while (eventType == XmlPullParser.TEXT && xpp.isWhitespace())
      eventType = xpp.next();
    return eventType;
  }
  
  public void executeFragments(String[] args) throws Exception {
    try {
      File source = new CSFile(args[1]);
      if (!source.exists())        
        throw new Exception("Source File \""+source.getAbsolutePath()+"\" not found");
      XmlPullParser xpp = loadXml(new FileInputStream(source));
      nextNoWhitespace(xpp);
      if (!xpp.getName().equals("tests"))
        throw new Exception("Unable to parse file - starts with "+xpp.getName());
      xpp.next();
      nextNoWhitespace(xpp);
      StringBuilder s = new StringBuilder();
      s.append("<results>\r\n");
      int fail = 0;
      while (xpp.getEventType() == XmlPullParser.START_TAG && xpp.getName().equals("test")) {
        String id = xpp.getAttributeValue(null, "id");
        String type = xpp.getAttributeValue(null, "type");
        // test
        xpp.next();
        nextNoWhitespace(xpp);
        // pre
        xpp.next();
        nextNoWhitespace(xpp);
        XmlParser p = new XmlParser();
        try {
          p.parseFragment(xpp, type);
          s.append("<result id=\""+id+"\" outcome=\"ok\"/>\r\n");
          nextNoWhitespace(xpp);
        } catch (Exception e) {
          s.append("<result id=\""+id+"\" outcome=\"error\" msg=\""+Utilities.escapeXml(e.getMessage())+"\"/>\r\n");
          fail++;
        }
        while (xpp.getEventType() != XmlPullParser.END_TAG || !xpp.getName().equals("pre")) 
          xpp.next();
        xpp.next();
        nextNoWhitespace(xpp);
        xpp.next();
        nextNoWhitespace(xpp);
      }
      s.append("</results>\r\n");

      System.out.println("done (fail = "+Integer.toString(fail)+")");
      TextFile.stringToFile(s.toString(), args[2]);
    } catch (Exception e) {
      e.printStackTrace();
      TextFile.stringToFile(e.getMessage(), args[2]);
    }
  }

  public void executeRoundTrip(String[] args) throws Exception {
	FileInputStream in;
	File source = new CSFile(args[1]);
    File dest = new CSFile(args[2]);

    if (!source.exists())        
      throw new Exception("Source File \""+source.getAbsolutePath()+"\" not found");
    in = new CSFileInputStream(source);
    XmlParser p = new XmlParser();
    JsonParser pj = new JsonParser();
    ResourceOrFeed rf = p.parseGeneral(in);
    ByteArrayOutputStream json = new ByteArrayOutputStream();
    if (rf.getFeed() != null) {
      new JsonComposer().compose(json, rf.getFeed(), true);
      rf = pj.parseGeneral(new ByteArrayInputStream(json.toByteArray()));
      new XmlComposer().compose(new FileOutputStream(dest), rf.getFeed(), true);
    } else {
      new JsonComposer().compose(json, rf.getResource(), true);
      rf = pj.parseGeneral(new ByteArrayInputStream(json.toByteArray()));
      new XmlComposer().compose(new FileOutputStream(dest), rf.getResource(), true);
    }
  }

  public String executeJson(String[] args) throws Exception {
    FileInputStream in;
    File source = new CSFile(args[1]);
    File dest = new CSFile(args[2]);
    File destt = new CSFile(args[2]+".tmp");

    if (!source.exists())        
      throw new Exception("Source File \""+source.getAbsolutePath()+"\" not found");
    in = new CSFileInputStream(source);
    XmlParser p = new XmlParser();
    ResourceOrFeed rf = p.parseGeneral(in);
    JsonComposer json = new JsonComposer();
    if (rf.getFeed() != null) {
      json.compose(new FileOutputStream(dest), rf.getFeed(), false);
      json.setSuppressXhtml("Snipped for Brevity");
      json.compose(new FileOutputStream(destt), rf.getFeed(), true);
    } else {
      json.compose(new FileOutputStream(dest), rf.getResource(), false);
      json.setSuppressXhtml("Snipped for Brevity");
      json.compose(new FileOutputStream(destt), rf.getResource(), true);
    }
    return TextFile.fileToString(destt.getAbsolutePath());
  }

  private void executeVersion(String[] args) throws Exception {
    TextFile.stringToFile(org.hl7.fhir.instance.utils.Version.VERSION+":"+Constants.VERSION, args[1]);
  }

}
