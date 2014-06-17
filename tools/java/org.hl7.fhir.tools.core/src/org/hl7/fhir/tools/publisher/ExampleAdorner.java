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
package org.hl7.fhir.tools.publisher;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XhtmlGenerator;
import org.hl7.fhir.utilities.xml.XhtmlGeneratorAdorner;
import org.w3c.dom.Element;

public class ExampleAdorner implements XhtmlGeneratorAdorner {


  public enum State {
    Unknown,
    Feed, 
    Element,
    Reference
  }

  private class ExampleAdornerState extends XhtmlGeneratorAdornerState {

    private State state;
    private ElementDefn definition;

    public ExampleAdornerState(State state, ElementDefn definition, String prefix, String suffix) {
      super(prefix, suffix);
      this.state = state;
      this.definition = definition;
      if (definition != null)
        definition.setCoveredByExample(true);
    }

    public ExampleAdornerState(State state, ElementDefn definition, String suppressionMessage) {
      super(suppressionMessage);
      this.state = state;
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
    if (parts[1].length() < 1 || parts[1].length() > 36)
      throw new Exception("Invalid syntax: "+parts[1]);
    if (!parts[1].matches("[a-z0-9\\-\\.]{1,36}"))
      return null;
    if (parts.length > 3) {
      if (!parts[2].equals("history"))
        return null;
      if (parts.length != 4 || parts[3].startsWith("@")) 
        throw new Exception("Invalid syntax: "+parts[3]);
      if (parts[3].length() < 1 || parts[3].length() > 36)
        throw new Exception("Invalid syntax: "+parts[3]);
      if (!parts[3].matches("[a-z0-9\\-\\.]{1,36}"))
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
        return new ExampleAdornerState(State.Unknown, null, "", "");
      else if (node.getLocalName().equals("feed"))
        return new ExampleAdornerState(State.Feed, null, "", "");
      else if (definitions.hasResource(node.getLocalName()))
        return new ExampleAdornerState(State.Element, definitions.getResourceByName(node.getLocalName()).getRoot(), "", "");
      else 
        return new ExampleAdornerState(State.Unknown, null, "", "");
    } else {
      ExampleAdornerState s = (ExampleAdornerState) state;
      if (s.getState() == State.Feed) {
        if (definitions.hasResource(node.getLocalName()))
          return new ExampleAdornerState(State.Element, definitions.getResourceByName(node.getLocalName()).getRoot(), "", "");
        else if (node.getLocalName().equals("reference") && !node.getAttribute("value").startsWith("http://"))
          return new ExampleAdornerState(State.Feed, null, "<a name=\""+extractId(node.getAttribute("value"), null)+"\">...</a>", "");
        else
          return new ExampleAdornerState(State.Feed, null, "", "");
      } else if (s.getState() == State.Element) {
        ElementDefn e = s.getDefinition().getElementByName(node.getLocalName());
        if (e == null && definitions.hasElementDefn(s.getDefinition().typeCode())) {
          ElementDefn t = definitions.getElementDefn(s.getDefinition().typeCode());
          e = t.getElementByName(node.getLocalName());
        }
        if (e == null)
          return new ExampleAdornerState(State.Unknown, null, "", "");
        if (!e.isBaseResourceElement() && e.typeCode().contains("Resource"))
          return new ExampleAdornerState(State.Reference, e, "", "");
        else
          return new ExampleAdornerState(State.Element, e, "", "");
      } else if (s.getState() == State.Reference) {
        if (node.getLocalName().equals("type"))
          return new ExampleAdornerState(State.Reference, s.getDefinition(), "<a href=\""+node.getAttribute("value").toLowerCase()+".html\">", "</a>");
        if (node.getLocalName().equals("reference"))
        {
          String type = extractType(node.getAttribute("value"));
          String id = extractId(node.getAttribute("value"), type);
          if (id == null)
            return new ExampleAdornerState(State.Element, null, "", "");
          ResourceDefn r = definitions.getResourceByName(type);
          if (r == null) 
            throw new Exception("unable to find type "+type);
          for (Example e : r.getExamples()) {
            if (id.equals(e.getId()))
              return new ExampleAdornerState(State.Reference, s.getDefinition(), "<a href=\""+e.getFileTitle()+".xml.html\">", "</a>");
            if (e.getXml() != null && e.getXml().getDocumentElement().getLocalName().equals("feed")) {
              List<Element> entries = new ArrayList<Element>();
              XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", entries);
              String url = "http://hl7.org/fhir/"+type+"/"+id;
              for (Element c : entries) {
                String t = XMLUtil.getNamedChild(c, "id").getAttribute("value");
                if (url.equals(t))
                  return new ExampleAdornerState(State.Reference, s.getDefinition(), "<a href=\""+e.getFileTitle()+".xml.html#"+id+"\">", "</a>");
              }
            }
          }
          return new ExampleAdornerState(State.Reference, s.getDefinition(), "<font color=\"red\">", "</font>");
        }
        else
          return new ExampleAdornerState(State.Reference, s.getDefinition(), "", "");
      } else // if (s.getState() == State.Unknown) {
        if (node.getNamespaceURI().equals("http://www.w3.org/1999/xhtml"))
          return new ExampleAdornerState(State.Unknown, null, "Snipped for brevity");
        else
          return new ExampleAdornerState(State.Unknown, null, "", "");
    }
  }

  @Override
  public XhtmlGeneratorAdornerState getAttributeMarkup(XhtmlGenerator xhtmlGenerator, XhtmlGeneratorAdornerState state, Element node, String nodeName, String textContent) throws Exception {
    ExampleAdornerState s = (ExampleAdornerState) state;
    if (s != null && s.getState() == ExampleAdorner.State.Reference && node.getNodeName().equals("type") && nodeName.equals("value")) 
      return new ExampleAdornerState(State.Unknown, null, state.getPrefix(), state.getSuffix());
    else if (s != null && s.getState() == ExampleAdorner.State.Reference && node.getNodeName().equals("reference") && nodeName.equals("value")) 
      return new ExampleAdornerState(State.Unknown, null, state.getPrefix(), state.getSuffix());
    else
      return new ExampleAdornerState(State.Unknown, null, "", "");
  }

}
