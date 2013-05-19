package org.hl7.fhir.tools.validator;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.hl7.fhir.utilities.SchemaInputSource;
import org.hl7.fhir.utilities.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.SAXException;

/**
 * A service that will validate one or more FHIR resources against 
 * the specification
 * 
 * @author Grahame
 *
 */
public class Validator {

  public static void main(String[] args) throws Exception {
    String output = null;
    if (args.length == 0) {
      System.out.println("FHIR Validation tool. ");
      System.out.println("Usage: FHIRValidator.jar [source] (-defn [definitions]) (-output [output]) where: ");
      System.out.println("* [source] is a file name or url of the resource or atom feed to validate");
      System.out.println("* [definitions] is a folder name or url of the FHIR source (http://hl7.org/fhir is default)");
      System.out.println("* [output] is a filename for the results. If no output, they are just sent to the std out.");
    } else {
      Validator exe = new Validator();
      exe.setSource(args[0]);
      for (int i = 1; i < args.length - 1; i++) {
        if (args[i].equals("-defn"))
          exe.setDefinitions(args[i+1]);
        if (args[i].equals("-output"))
          output = args[i+1];
      }
      exe.process();
      if (output == null) {
        System.out.println("Validating "+args[0]+": "+Integer.toString(exe.getOutputs().size())+" messages");
        for (ValidationOutput v : exe.getOutputs()) {
          System.out.println(v.summary());
        }
        if (exe.getOutputs().size() == 0)
          System.out.println(" ...success");
        else
          System.out.println(" ...failure");
      } else {
        OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(output));
        w.append("<validation>\r\n");
        for (ValidationOutput v : exe.getOutputs()) 
          v.xml(w);
        w.append("</validation>\r\n"); 
      }
    }
  }

  /**
   * The source (file name, folder name, url) of the FHIR source. This can be the 
   * fhir url, an alternative url of a local copy of the fhir spec, the name of 
   * a zip file containing the fhir spec, the name of a directory containing the
   * fhir spec 
   */
  private String definitions;
  
  /**
   * The name of the resource/feed to validate. this can be the actual source as json or xml, a file name, a zip file, 
   * or a url. If the source identifies a collection of resources and/or feeds, they
   * will all be validated
   */
  private String source;
  
  private Map<String, byte[]> files = new HashMap<String, byte[]>();
  
  /**
   * A list of output messages from the validator
   */
  private List<ValidationOutput> outputs;
  
  
  static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  public class ValidatorResourceResolver implements LSResourceResolver {

    private Map<String, byte[]> files;

    public ValidatorResourceResolver(Map<String, byte[]> files) {
      this.files = files;
    }

    @Override
	public LSInput resolveResource(final String type, final String namespaceURI, final String publicId, String systemId, final String baseURI) {
//      if (!(namespaceURI.equals("http://hl7.org/fhir"))) //|| namespaceURI.equals("http://www.w3.org/1999/xhtml")))
      if (!files.containsKey(systemId))
        return null;
      return new SchemaInputSource(new ByteArrayInputStream(files.get(systemId)), publicId, systemId, namespaceURI);
    }
  }

  public void process() throws Exception {
    outputs = new ArrayList<ValidationOutput>();
    // first, locate the source
    if (source == null)
      throw new Exception("no source provided");
    byte[] src = loadSource();
    byte[] defn = loadDefinitions();
    readDefinitions(defn);
    Schema schema = readSchema();
    // ok all loaded
    
    // 1. schema validation 
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    factory.setValidating(false);
    factory.setSchema(schema);
    DocumentBuilder builder = factory.newDocumentBuilder();
    builder.setErrorHandler(new ValidationErrorHandler(outputs));
    Document doc = builder.parse(new ByteArrayInputStream(src));

    // 2. schematron validation
    String sch = doc.getDocumentElement().getNodeName().toLowerCase();
    if (sch.equals("feed"))
      sch = "fhir-atom";
    byte[] tmp = Utilities.transform(files, files.get(sch+".sch"), files.get("iso_svrl_for_xslt1.xsl"));
    byte[] out = Utilities.transform(files, src, tmp);
    processSchematronOutput(out);    
      
    // second, locate the definitions
    // now, run against
    // * schema
    // * schematron
    // * java parser
    // * internal rules
    
  }

  private void processSchematronOutput(byte[] out)
      throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;
    factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    builder = factory.newDocumentBuilder();
    doc = builder.parse(new ByteArrayInputStream(out));
    NodeList nl = doc.getDocumentElement().getElementsByTagNameNS("http://purl.oclc.org/dsdl/svrl", "failed-assert");
    if (nl.getLength() > 0) {
      for (int i = 0; i < nl.getLength(); i++) {
        Element e = (Element) nl.item(i);
        ValidationOutput o = new ValidationOutput();
        o.setLevel(ValidationOutput.Strength.Error);
        o.setLocation(e.getAttribute("location"));
        o.setMessage(e.getTextContent());
        outputs.add(o);
      }
    }
  }

  private Schema readSchema() throws SAXException {
//    int t = 0;
//    for (String n : files.keySet())
//      if (n.endsWith(".xsd") && !n.equals("xml.xsd"))
//        t++;
//
//    StreamSource[] sources = new StreamSource[t];
//    int i = 0;
//    for (String n : files.keySet()) {
//      if (n.endsWith(".xsd") && !n.equals("xml.xsd")) {
//        sources[i] = new StreamSource(new ByteArrayInputStream(files.get(n)));
//        i++;
//      }
//    }
    StreamSource[] sources = new StreamSource[2];
    sources[0] = new StreamSource(new ByteArrayInputStream(files.get("fhir-all.xsd")));
    sources[1] = new StreamSource(new ByteArrayInputStream(files.get("fhir-atom.xsd")));

    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    schemaFactory.setErrorHandler(new ValidationErrorHandler(outputs));
    schemaFactory.setResourceResolver(new ValidatorResourceResolver(files));
    Schema schema = schemaFactory.newSchema(sources);
    return schema;
  }

  private void readDefinitions(byte[] defn) throws IOException,
      FileNotFoundException, ZipException {
    File tmp = File.createTempFile("fhir-val", ".zip");
    tmp.deleteOnExit();
    FileOutputStream out = new FileOutputStream(tmp);
    out.write(defn);
    out.close();
    ZipFile zf = new ZipFile(tmp);
    try {
      for (Enumeration<? extends ZipEntry> e = zf.entries(); e.hasMoreElements();) {
        ZipEntry ze = e.nextElement();
        String name = ze.getName();
        InputStream in = zf.getInputStream(ze);
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        int n;
        byte[] buf = new byte[1024];
        while ((n = in.read(buf, 0, 1024)) > -1) {
          b.write(buf, 0, n);
        }        
        files.put(name, b.toByteArray());

      }
    } finally {
      zf.close();
    }
  }

  private byte[] loadDefinitions() throws Exception {
    byte[] defn;
    if (Utilities.noString(definitions) || definitions.startsWith("https:") || definitions.startsWith("http:")) {
      if (Utilities.noString(definitions) || definitions.equals("http://hl7.org/fhir"))
        definitions = "http://hl7.org/implement/standards/fhir";
      definitions = definitions+"/fhir-all-xsd.zip";
      if (definitions.equals("http://hl7.org/implement/standards/fhir"))
        definitions = "http://hl7.org/documentcenter/public/standards/FHIR/fhir-all-xsd.zip";
      else
        definitions = definitions+"/fhir-all-xsd.zip";
      defn = loadFromUrl(definitions);
    } else if (new File(Utilities.appendSlash(definitions)+"fhir-all-xsd.zip").exists())
      defn = loadFromFile(Utilities.appendSlash(definitions)+"fhir-all-xsd.zip");
    else
      throw new Exception("Unable to find FHIR specification at "+definitions);
    return defn;
  }

  private byte[] loadSource() throws Exception {
    byte[] src;
    if (new File(source).exists())
      src = loadFromFile(source);
    else if (source.startsWith("https:") || source.startsWith("http:"))
      src = loadFromUrl(source);
    else 
      src = source.getBytes();
    return src;
  }

  private byte[] loadFromUrl(String src) throws Exception {
    URL url = new URL(src);
    InputStream in = url.openStream();
    byte[] b = new byte[in.available()];
    in.read(b);
    return b;
  }

  private byte[] loadFromFile(String src) throws Exception {
    FileInputStream in = new FileInputStream(src);
    byte[] b = new byte[in.available()];
    in.read(b);
    in.close();
    return b;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }


  public List<ValidationOutput> getOutputs() {
    return outputs;
  }

  public String getDefinitions() {
    return definitions;
  }

  public void setDefinitions(String definitions) {
    this.definitions = definitions;
  }
  
  
}
