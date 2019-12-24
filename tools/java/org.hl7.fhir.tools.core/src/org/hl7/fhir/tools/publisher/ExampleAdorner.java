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

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.r5.formats.FormatUtilities;
import org.hl7.fhir.r5.model.IdType;
import org.hl7.fhir.utilities.Utilities;
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

    public ExampleAdornerState(State state, String path, ElementDefn definition, String prefix, String suffix) {
      super(path, prefix, suffix);
      this.state = state;
      this.definition = definition;
      if (definition != null)
        definition.setCoveredByExample(true);
    }

    public ExampleAdornerState(State state, String path, ElementDefn definition, String suppressionMessage) {
      super(path, suppressionMessage);
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
  private String prefix; 

  public ExampleAdorner(Definitions definitions, String prefix) {
    this.definitions = definitions;
    this.prefix = prefix;
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
        if (s.definition == null) {
          System.out.println("??");
        }
        if (s.definition != null && "Resource".equals(s.definition.typeCode()) && (s.getPath().endsWith(".entry.resource") || s.getPath().endsWith(".contained")) && definitions.isResource(node.getNodeName())) // account for extra element
          return new ExampleAdornerState(State.Element, s.getPath(), definitions.getResourceByName(node.getNodeName()).getRoot(), "", "");
        String p = s.getPath()+"."+node.getNodeName();
        ElementDefn e = s.getDefinition().getElementByName(node.getLocalName(), true, definitions, "adorn example", false);
        if (e == null && definitions.hasElementDefn(s.getDefinition().typeCodeNoParams())) {
          // well, we see if it's inherited...
          ElementDefn t = definitions.getElementDefn(s.getDefinition().typeCodeNoParams());
          while (t != null && e == null) {
            e = t.getElementByName(node.getLocalName(), true, definitions, "adorn example", false);
            if (e != null)
              p = t.getName()+"."+e.getName();
            else if (definitions.hasElementDefn(t.typeCodeNoParams()))
              t = definitions.getElementDefn(t.typeCodeNoParams());
            else
              t = null;            
          }
        } else if (e != null)
          p = s.getPath()+"."+e.getName();
          
        if (e == null)
          return new ExampleAdornerState(State.Unknown, s.getPath(), null, "", "");
        if (!e.isBaseResourceElement() && e.typeCode().contains("Reference") && !e.typeCode().contains("CodeableReference"))
          return new ExampleAdornerState(State.Reference, p, e, "", "");
        else if (!e.isBaseResourceElement() && e.typeCode().contains("canonical"))
          return new ExampleAdornerState(State.Reference, p, e, "", "");
        else if (!e.isBaseResourceElement() && e.typeCode().equals("uri"))
          return new ExampleAdornerState(State.Reference, p, e, "", "");
        else
          return new ExampleAdornerState(State.Element, p, e, "", "");
      } else if (s.getState() == State.Reference) {
        if (node.getLocalName().equals("reference"))
        {
          String type = extractType(node.getAttribute("value"));
          String id = extractId(node.getAttribute("value"), type);
          if (id == null)
            return new ExampleAdornerState(State.Element, s.getPath()+".reference", null, "", "");
          ResourceDefn r = definitions.getResourceByName(type);
          if (r == null) 
            throw new Exception("unable to find type "+type);
          for (Example e : r.getExamples()) {
            if (id.equals(e.getId()))
              if (Utilities.noString(e.getIg())) {
                return new ExampleAdornerState(State.Reference, s.getPath()+".reference", s.getDefinition(), "<a href=\""+prefix+e.getTitle()+".xml.html\">", "</a>");
              } else {
                ImplementationGuideDefn ig = definitions.getIgs().get(e.getIg());
                return new ExampleAdornerState(State.Reference, s.getPath()+".reference", s.getDefinition(), "<a href=\""+prefix+ig.getPrefix()+e.getTitle()+".xml.html\">", "</a>");
              }
            if (e.getXml() != null && e.getXml().getDocumentElement().getLocalName().equals("feed")) {
              List<Element> entries = new ArrayList<Element>();
              XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", entries);
              String url = "http://hl7.org/fhir/"+type+"/"+id;
              for (Element c : entries) {
                String t = XMLUtil.getNamedChild(c, "id").getAttribute("value");
                if (url.equals(t))
                  return new ExampleAdornerState(State.Reference, s.getPath()+".reference", s.getDefinition(), "<a href=\""+prefix+e.getTitle()+".xml.html#"+id+"\">", "</a>");
              }
            }
          }
          return new ExampleAdornerState(State.Reference, s.getPath()+".reference", s.getDefinition(), "<font color=\"red\">", "</font>");
        }
        else
          return new ExampleAdornerState(State.Reference, s.getPath(), s.getDefinition(), "", "");
      } else // if (s.getState() == State.Unknown) {
//        if (node.getNamespaceURI().equals("http://www.w3.org/1999/xhtml"))
//          return new ExampleAdornerState(State.Unknown, s.getPath(), null, "Snipped for brevity");
//        else
          return new ExampleAdornerState(State.Unknown, s.getPath(), null, "", "");
    }
  }

  @Override
  public XhtmlGeneratorAdornerState getAttributeMarkup(XhtmlGenerator xhtmlGenerator, XhtmlGeneratorAdornerState state, Element node, String nodeName, String textContent) throws Exception {
    ExampleAdornerState s = (ExampleAdornerState) state;
    if (s != null && s.getState() == ExampleAdorner.State.Reference && node.getNodeName().equals("type") && nodeName.equals("value")) 
      return new ExampleAdornerState(State.Unknown, s.getPath(), null, state.getPrefix(), state.getSuffix());
    else if (s != null && s.getState() == ExampleAdorner.State.Reference && node.getNodeName().equals("reference") && nodeName.equals("value")) 
      return new ExampleAdornerState(State.Unknown, s.getPath(), null, state.getPrefix(), state.getSuffix());
    else if (s != null && s.getState() == ExampleAdorner.State.Reference && node.getNodeName().equals("url") && nodeName.equals("value")) 
      return new ExampleAdornerState(State.Unknown, s.getPath(), null, state.getPrefix(), state.getSuffix());
    else
      return new ExampleAdornerState(State.Unknown, s.getPath(), null, "", "");
  }

  @Override
  public String getLink(XhtmlGenerator ref, XhtmlGeneratorAdornerState state, Element node) throws Exception {
    if (state == null) {
      if (node == null || !definitions.hasResource(node.getLocalName()))
        return null;
      else {
        definitions.getResourceByName(node.getLocalName()).getRoot().setCoveredByExample(true);
        return prefix+node.getLocalName().toLowerCase()+"-definitions.html";
      }
    } else {
      ExampleAdornerState s = (ExampleAdornerState) state;
      if (s.definition == null)
        if (node.getNamespaceURI().equals("http://www.w3.org/1999/xhtml"))
          return prefix+"narrative.html";
        else 
          return null;
      ElementDefn t = s.definition;
      if (t.typeCode().equals("Resource") && (s.getPath().endsWith(".entry.resource") || s.getPath().endsWith(".contained")) && definitions.isResource(node.getNodeName()))
        return null;        
      ElementDefn child = t.getElementByName(node.getNodeName(), true, definitions, "adorn example", false);
      String p = child == null ? null : s.getPath()+"."+child.getName();
      while (child == null && t != null && definitions.hasElementDefn(t.typeCodeNoParams())) {
        t = definitions.getElementDefn(t.typeCodeNoParams());
        child = t.getElementByName(node.getNodeName(), true, definitions, "adorn example", false);
        if (child != null) {
          p = t.getName()+"."+child.getName();
        }
      }
      if (child == null) {
        if (node.getNamespaceURI().equals("http://www.w3.org/1999/xhtml"))
          return prefix+"narrative.html";
        else 
          return null;
      } else {
        child.setCoveredByExample(true);
        String r = p.contains(".") ? p.substring(0, p.indexOf(".")) : p;
        return prefix+definitions.getSrcFile(r)+"-definitions.html#"+p;
      }
    }
  }

  @Override
  public String getNodeId(XhtmlGeneratorAdornerState state, Element node) {
    return  state == null ? node.getNodeName() : state.getPath()+"."+node.getNodeName();
  }

}
