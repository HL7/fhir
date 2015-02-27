package org.hl7.fhir.tools.publisher;
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
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.utilities.CSFileInputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Navigation {
  
  public static class Entry {
    private String name;    
    private String link;   
    private String index;
    private List<Entry> entries = new ArrayList<Entry>();
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getLink() {
      return link;
    }
    public void setLink(String link) {
      this.link = link;
    }
    public List<Entry> getEntries() {
      return entries;
    }
    public String getIndex() {
      return index;
    }
    public void setIndex(String index) {
      this.index = index;
    }
    
  }
  public static class Category {
    private String name;
    private String link;
    private String mode;
    private String index;
    private List<Entry> entries = new ArrayList<Entry>();
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getMode() {
      return mode;
    }
    public void setMode(String mode) {
      this.mode = mode;
    }
    public List<Entry> getEntries() {
      return entries;
    }
    public String getLink() {
      return link;
    }
    public void setLink(String link) {
      this.link = link;
    }
    public String getIndex() {
      return index;
    }
    public void setIndex(String index) {
      this.index = index;
    }
    
  }

  private List<Category> categories = new ArrayList<Category>();

  public List<Category> getCategories() {
    return categories;
  }
  
  public void parse(String file) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true); 
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(new CSFileInputStream(file));
    Element root = xdoc.getDocumentElement();
    if (root.getNodeName().equals("Navigation"))
      parseNavigation(root);
    else
      throw new Exception("Unexpected node "+root.getNodeName());

  }

  private void parseNavigation(Element root) {
    Node child = root.getFirstChild();
    while (child != null) {
      if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("category")) {
        Category c = parseCategory(child);
        getCategories().add(c);
      }
      child = child.getNextSibling();
    }
  }

  private Category parseCategory(Node node) {
   Category c = new Category();
   Node child = node.getFirstChild();
   while (child != null) {
     if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("name")) {
       c.setName(child.getTextContent());
     }
     if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("link")) {
       c.setLink(child.getTextContent());
     }
     if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("mode")) {
       c.setMode(child.getTextContent());
     }
     if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("entry")) {
       c.getEntries().add(parseEntry(child));
     }
     if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("index")) {
       c.setIndex(child.getTextContent());
     }
     child = child.getNextSibling();
   }
   return c;    
  }

  private Entry parseEntry(Node node) {
    Entry c = new Entry();
    Node child = node.getFirstChild();
    while (child != null) {
      if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("name")) {
        c.setName(child.getTextContent());
      }
      if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("index")) {
        c.setIndex(child.getTextContent());
      }
      if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("link")) {
        c.setLink(child.getTextContent());
      }
      if (child.getNodeType() == Node.ELEMENT_NODE && child.getNodeName().equals("entry")) {
        c.getEntries().add(parseEntry(child));
      }
      child = child.getNextSibling();
    }
    return c;    
  }

  public String getIndexPrefixForFile(String file) {
    for (Category c : getCategories()) {
      if (c.getLink() != null && file.equals(c.getLink()+".html"))
        return c.getIndex();
      for (Entry e : c.getEntries()) {
        if (e.getLink() != null && file.equals(e.getLink()+".html"))
          return e.getIndex();
      }
    }
    return null;
  }
  
}