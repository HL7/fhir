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
package org.hl7.fhir.instance.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.instance.client.IFHIRClient;
import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Constants;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.WorkerContext;
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
      if (args.length == 0) 
        throw new Exception("Missing Command Parameter. Valid Commands: round, json, version, fragments, snapshot-maker");
      if (args[0].equals("round")) 
        self.executeRoundTrip(args);
      else if (args[0].equals("json")) 
        self.executeJson(args);
      else if (args[0].equals("cxml")) 
        self.executeCanonicalXml(args);
      else if (args[0].equals("version")) 
        self.executeVersion(args);
      else if (args[0].equals("fragments")) 
          self.executeFragments(args);
      else if (args[0].equals("snapshot-maker")) 
        self.generateSnapshots(args);
      else 
        throw new Exception("Unknown command '"+args[0]+"'. Valid Commands: round, json, version, fragments, snapshot-maker");
    } catch (Throwable e) {
      try {
        e.printStackTrace();
        TextFile.stringToFile(e.toString(), (args.length == 0 ? "tools" : args[0])+".err");
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
  }

	private void generateSnapshots(String[] args) throws Exception {
		if (args.length == 1) {
			System.out.println("tools.jar snapshot-maker [source] -defn [definitions]");
			System.out.println("");
			System.out.println("Generates a snapshot from a differential. The nominated profile must have a single struture that has a differential");
			System.out.println("");
			System.out.println("source - the profile to generate the snapshot for. Maybe a file name, or a URL reference to a server running FHIR RESTful API");
			System.out.println("definitions - filename for local copy of the validation.zip file");			
		}
	  String address = args[1];
	  String definitions = args[3];
	  
    WorkerContext context = WorkerContext.fromDefinitions(getDefinitions(definitions));

    if (address.startsWith("http:") || address.startsWith("http:")) {
    	// this is on a restful interface
    	String[] parts = address.split("\\/Profile\\/");
    	if (parts.length != 2)
    		throw new Exception("Unable to understand address of profile");
    	IFHIRClient client = new FHIRSimpleClient();
    	client.initialize(parts[0]);
    	Profile profile = client.read(Profile.class, parts[1]);
			ProfileUtilities utils = new ProfileUtilities(context);
    	Profile base = utils.getProfile(profile, profile.getBase());
			if (base == null)
				throw new Exception("Unable to resolve profile "+profile.getBase());
			utils.generateSnapshot(base, profile, address, profile.getName(), null);
			client.update(Profile.class, profile, parts[1]);
    } else {
    	throw new Exception("not done yet (address = "+address+")");
    }
    
	}

  private Map<String, byte[]> getDefinitions(String definitions) throws Exception {
  	Map<String, byte[]> results = new HashMap<String, byte[]>();
  	readDefinitions(results, loadDefinitions(definitions));
	  return results;
  }

	private void readDefinitions(Map<String, byte[]> map, byte[] defn) throws Exception {
    ZipInputStream zip = new ZipInputStream(new ByteArrayInputStream(defn));
    ZipEntry ze;
    while ((ze = zip.getNextEntry()) != null) {
      if (!ze.getName().endsWith(".zip") && !ze.getName().endsWith(".jar") ) { // skip saxon .zip
        String name = ze.getName();
        InputStream in = zip;
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        int n;
        byte[] buf = new byte[1024];
        while ((n = in.read(buf, 0, 1024)) > -1) {
          b.write(buf, 0, n);
        }        
        map.put(name, b.toByteArray());
      }
      zip.closeEntry();
    }
    zip.close();    
  }

  private byte[] loadDefinitions(String definitions) throws Exception {
    byte[] defn;
//    if (Utilities.noString(definitions)) {
//      defn = loadFromUrl(MASTER_SOURCE);
//    } else 
    if (definitions.startsWith("https:") || definitions.startsWith("http:")) {
      defn = loadFromUrl(definitions);
    } else if (new File(definitions).exists()) {
      defn = loadFromFile(definitions);      
    } else
      throw new Exception("Unable to find FHIR validation Pack (source = "+definitions+")");
    return defn;
  }

  private byte[] loadFromUrl(String src) throws Exception {
  	URL url = new URL(src);
    byte[] str = IOUtils.toByteArray(url.openStream());
    return str;
  }

  private byte[] loadFromFile(String src) throws Exception {
    FileInputStream in = new FileInputStream(src);
    byte[] b = new byte[in.available()];
    in.read(b);
    in.close();
    return b;
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
    if (args.length >= 4) {
    	Utilities.copyFile(args[1], args[3]);
    }

    if (!source.exists())        
      throw new Exception("Source File \""+source.getAbsolutePath()+"\" not found");
    in = new CSFileInputStream(source);
    XmlParser p = new XmlParser();
    JsonParser parser = new JsonParser();
    JsonParser pj = parser;
    Resource rf = p.parse(in);
    ByteArrayOutputStream json = new ByteArrayOutputStream();
    parser.setOutputStyle(OutputStyle.PRETTY);
    parser.compose(json, rf);
    TextFile.stringToFile(new String(json.toByteArray()), Utilities.changeFileExt(dest.getAbsolutePath(), ".json"));
    rf = pj.parse(new ByteArrayInputStream(json.toByteArray()));
    new XmlParser().compose(new FileOutputStream(dest), rf, true);
  }

  public String executeJson(String[] args) throws Exception {
    FileInputStream in;
    File source = new CSFile(args[1]);
    File dest = new CSFile(args[2]);
    File destc = new CSFile(Utilities.changeFileExt(args[2], ".canonical.json"));
    File destt = new CSFile(args[2]+".tmp");

    if (!source.exists())        
      throw new Exception("Source File \""+source.getAbsolutePath()+"\" not found");
    in = new CSFileInputStream(source);
    XmlParser p = new XmlParser();
    Resource rf = p.parse(in);
    JsonParser json = new JsonParser();
    json.setOutputStyle(OutputStyle.PRETTY);
    json.compose(new FileOutputStream(dest), rf);
    json.setOutputStyle(OutputStyle.CANONICAL);
    json.compose(new FileOutputStream(destc), rf);
    json.setSuppressXhtml("Snipped for Brevity");
    json.setOutputStyle(OutputStyle.PRETTY);
    json.compose(new FileOutputStream(destt), rf);
    return TextFile.fileToString(destt.getAbsolutePath());
  }

  public void executeCanonicalXml(String[] args) throws Exception {
    FileInputStream in;
    File source = new CSFile(args[1]);
    File dest = new CSFile(args[2]);

    if (!source.exists())        
      throw new Exception("Source File \""+source.getAbsolutePath()+"\" not found");
    in = new CSFileInputStream(source);
    XmlParser p = new XmlParser();
    Resource rf = p.parse(in);
    XmlParser cxml = new XmlParser();
    cxml.setOutputStyle(OutputStyle.NORMAL);
    cxml.compose(new FileOutputStream(dest), rf);
  }

  private void executeVersion(String[] args) throws Exception {
    TextFile.stringToFile(org.hl7.fhir.instance.utils.Version.VERSION+":"+Constants.VERSION, args[1]);
  }

}
