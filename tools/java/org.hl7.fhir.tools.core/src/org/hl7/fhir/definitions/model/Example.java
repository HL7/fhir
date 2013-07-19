package org.hl7.fhir.definitions.model;
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
import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.CSVProcessor;
import org.hl7.fhir.utilities.Utilities;
import org.w3c.dom.Document;

public class Example {
  private String name;
  private String id;
  private String description;
  private File path;
  private String xhtm;
  private String json;
  private ExampleType type;
  private boolean inBook;
  private Document xml;
  
  public enum ExampleType {
	    XmlFile,
	    CsvFile,
	    Tool
	  }
  
  
  public Example(String name, String id, String description, File path, ExampleType type, boolean inBook) throws Exception {
    super();
    this.name = name;
    this.id = id;
    this.description = description;
    this.path = path;
    this.type = type;
    this.inBook = inBook;
    
    if( type == ExampleType.CsvFile ) {
      CSVProcessor csv = new CSVProcessor();
      csv.setSource(new CSFileInputStream(path));
      csv.setData(new CSFileInputStream(Utilities.changeFileExt(path.getAbsolutePath(), ".csv")));
      File tmp = File.createTempFile("fhir", "xml");
      tmp.deleteOnExit();
      csv.setOutput(new FileOutputStream(tmp));
      csv.process();
      path = tmp;
    }
    
    if (type != ExampleType.Tool) {//profiles-resources is going to produced later and is a feed
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      factory.setNamespaceAware(true);
      try {
        DocumentBuilder builder = factory.newDocumentBuilder();
        xml = builder.parse(new CSFileInputStream(path.getAbsolutePath()));
      } catch (Exception e) {
        throw new Exception("unable to read "+path.getAbsolutePath()+": "+e.getMessage(), e);
      }
    }
    if (Utilities.noString(id) && xml != null) { 
      if (!xml.getDocumentElement().getLocalName().equals("feed"))
        throw new Exception("unidentified resource "+path);
    }
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public File getPath() {
    return path;
  }
  public void setPath(File path) {
    this.path = path;
  }
  public String getFileTitle() {
    String s = path.getName();
    return s.substring(0, s.indexOf("."));
  }
  public void setXhtm(String content) {
   xhtm = content;
    
  }
  public String getXhtm() {
    return xhtm;
  }
  public ExampleType getType() {
    return type;
  }
  public void setType(ExampleType type) {
    this.type = type;
  }
  public boolean isInBook() {
    return inBook;
  }
  public void setInBook(boolean inBook) {
    this.inBook = inBook;
  }
  public String getId() {
    return id;
  }
  public Document getXml() {
    return xml;
  }
  public String getJson() {
    return json;
  }
  public void setJson(String json) {
    this.json = json;
  }
  
  
}
