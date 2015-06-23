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
package org.hl7.fhir.tools.publisher;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.generators.specification.BaseGenerator;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.instance.formats.FormatUtilities;
import org.hl7.fhir.instance.model.IdType;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XhtmlGenerator;
import org.hl7.fhir.utilities.xml.XhtmlGeneratorAdorner;
import org.w3c.dom.Element;

public class ExampleAdorner implements XhtmlGeneratorAdorner {


  public enum State {
    Unknown,
    Element,
    Reference
  }

  private class ExampleAdornerState extends XhtmlGeneratorAdornerState {

    private State state;
    private ElementDefn definition;
    private String path;

    public ExampleAdornerState(State state, String path, ElementDefn definition, String prefix, String suffix) {
      super(prefix, suffix);
      this.state = state;
      this.path = path;
      this.definition = definition;
      if (definition != null)
        definition.setCoveredByExample(true);
    }

    public ExampleAdornerState(State state, String path, ElementDefn definition, String suppressionMessage) {
      super(suppressionMessage);
      this.state = state;
      this.path = path;
      this.definition = definition;
    }
    
    public State getState() {
      return state;
    }

    public ElementDefn getDefinition() {
      return definition;
    }

  }

  private Definitions definitions;

  public ExampleAdorner(Definitions definitions) {
    this.definitions = definitions;
  }

  private String extractId(String id, String type) throws Exception {
    String[] parts = id.split("/");
    if (parts.length < 2)
      return null;
    if (type != null && !parts[0].equals(type))
      return null;
    if (parts[0].equals("http:") || parts[0].equals("https:"))
      return null;
    if (!definitions.hasResource(parts[0]))
      throw new Exception("Type unknown: "+parts[0]);
    if (parts[1].startsWith("@"))
      throw new Exception("Invalid syntax: "+parts[1]);
    if (parts[1].length() < 1 || parts[1].length() > IdType.MAX_LENGTH)
      throw new Exception("Invalid syntax: "+parts[1]);
    if (!parts[1].matches(FormatUtilities.ID_REGEX))
      return null;
    if (parts.length > 3) {
      if (!parts[2].equals("history"))
        return null;
      if (parts.length != 4 || parts[3].startsWith("@")) 
        throw new Exception("Invalid syntax: "+parts[3]);
      if (parts[3].length() < 1 || parts[3].length() > IdType.MAX_LENGTH)
        throw new Exception("Invalid syntax: "+parts[3]);
      if (!parts[3].matches(FormatUtilities.ID_REGEX))
        throw new Exception("Invalid syntax: "+parts[3]);
    }
    return parts[1];
  }

  private String extractType(String id) throws Exception {
    String[] parts = id.split("/");
    if (parts.length < 2)
      return null;
    if (parts[0].equals("http:") || parts[0].equals("https:"))
      return null;
    if (!definitions.hasResource(parts[0]))
      throw new Exception("Type unknown: "+parts[0]);
    return parts[0];
  }

  @Override
  public XhtmlGeneratorAdornerState getState(XhtmlGenerator ref, XhtmlGeneratorAdornerState state, Element node) throws Exception {
    if (state == null) {
      if (node == null)
        return new ExampleAdornerState(State.Unknown, "", null, "", "");
      else if (definitions.hasResource(node.getLocalName()))
        return new ExampleAdornerState(State.Element, node.getLocalName(), definitions.getResourceByName(node.getLocalName()).getRoot(), "", ""); 
      else 
        return new ExampleAdornerState(State.Unknown, "", null, "", "");
    } else {
      ExampleAdornerState s = (ExampleAdornerState) state;
      if (s.getState() == State.Element) {
        String p = s.path+"."+node.getNodeName();
        ElementDefn e = s.getDefinition().getElementByName(node.getLocalName(), true, definitions);
        if (e == null && definitions.hasElementDefn(s.getDefinition().typeCode())) {
          // well, we see if it's inherited...
          ElementDefn t = definitions.getElementDefn(s.getDefinition().typeCode());
          while (t != null && e == null) {
            e = t.getElementByName(node.getLocalName(), true, definitions);
            if (e != null)
              p = t.getName()+"."+e.getName();
            else if (definitions.hasElementDefn(t.typeCode()))
              t = definitions.getElementDefn(t.typeCode());
            else
              t = null;            
          }
        } else if (e != null)
          p = s.path+"."+e.getName();
          
        if (e == null)
          return new ExampleAdornerState(State.Unknown, s.path, null, "", "");
        if (!e.isBaseResourceElement() && e.typeCode().contains("Reference"))
          return new ExampleAdornerState(State.Reference, p, e, "", "");
        else
          return new ExampleAdornerState(State.Element, p, e, "", "");
      } else if (s.getState() == State.Reference) {
        if (node.getLocalName().equals("reference"))
        {
          String type = extractType(node.getAttribute("value"));
          String id = extractId(node.getAttribute("value"), type);
          if (id == null)
            return new ExampleAdornerState(State.Element, s.path+".reference", null, "", "");
          ResourceDefn r = definitions.getResourceByName(type);
          if (r == null) 
            throw new Exception("unable to find type "+type);
          for (Example e : r.getExamples()) {
            if (id.equals(e.getId()))
              return new ExampleAdornerState(State.Reference, s.path+".reference", s.getDefinition(), "<a href=\""+e.getFileTitle()+".xml.html\">", "</a>");
            if (e.getXml() != null && e.getXml().getDocumentElement().getLocalName().equals("feed")) {
              List<Element> entries = new ArrayList<Element>();
              XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", entries);
              String url = "http://hl7.org/fhir/"+type+"/"+id;
              for (Element c : entries) {
                String t = XMLUtil.getNamedChild(c, "id").getAttribute("value");
                if (url.equals(t))
                  return new ExampleAdornerState(State.Reference, s.path+".reference", s.getDefinition(), "<a href=\""+e.getFileTitle()+".xml.html#"+id+"\">", "</a>");
              }
            }
          }
          return new ExampleAdornerState(State.Reference, s.path+".reference", s.getDefinition(), "<font color=\"red\">", "</font>");
        }
        else
          return new ExampleAdornerState(State.Reference, s.path, s.getDefinition(), "", "");
      } else // if (s.getState() == State.Unknown) {
        if (node.getNamespaceURI().equals("http://www.w3.org/1999/xhtml"))
          return new ExampleAdornerState(State.Unknown, s.path, null, "Snipped for brevity");
        else
          return new ExampleAdornerState(State.Unknown, s.path, null, "", "");
    }
  }

  @Override
  public XhtmlGeneratorAdornerState getAttributeMarkup(XhtmlGenerator xhtmlGenerator, XhtmlGeneratorAdornerState state, Element node, String nodeName, String textContent) throws Exception {
    ExampleAdornerState s = (ExampleAdornerState) state;
    if (s != null && s.getState() == ExampleAdorner.State.Reference && node.getNodeName().equals("type") && nodeName.equals("value")) 
      return new ExampleAdornerState(State.Unknown, s.path, null, state.getPrefix(), state.getSuffix());
    else if (s != null && s.getState() == ExampleAdorner.State.Reference && node.getNodeName().equals("reference") && nodeName.equals("value")) 
      return new ExampleAdornerState(State.Unknown, s.path, null, state.getPrefix(), state.getSuffix());
    else
      return new ExampleAdornerState(State.Unknown, s.path, null, "", "");
  }

  @Override
  public String getLink(XhtmlGenerator ref, XhtmlGeneratorAdornerState state, Element node) throws Exception {
    if (state == null) {
      if (node == null || !definitions.hasResource(node.getLocalName()))
        return null;
      else {
        definitions.getResourceByName(node.getLocalName()).getRoot().setCoveredByExample(true);
        return node.getLocalName().toLowerCase()+"-definitions.html";
      }
    } else {
      ExampleAdornerState s = (ExampleAdornerState) state;
      if (s.definition == null)
        if (node.getNamespaceURI().equals("http://www.w3.org/1999/xhtml"))
          return "narrative.html";
        else 
          return null;
      ElementDefn t = s.definition;
      ElementDefn child = t.getElementByName(node.getNodeName(), true, definitions);
      String p = child == null ? null : s.path+"."+child.getName();
      while (child == null && t != null && definitions.hasElementDefn(t.typeCode())) {
        t = definitions.getElementDefn(t.typeCode());
        child = t.getElementByName(node.getNodeName(), true, definitions);
        if (child != null) {
          p = t.getName()+"."+child.getName();
        }
      }
      if (child == null)
        if (node.getNamespaceURI().equals("http://www.w3.org/1999/xhtml"))
          return "narrative.html";
        else 
          return null;
      else {
        child.setCoveredByExample(true);
        String r = p.contains(".") ? p.substring(0, p.indexOf(".")) : p;
        return definitions.getSrcFile(r)+"-definitions.html#"+p;
      }
    }
  }

}
