package org.hl7.fhir.instance.formats;
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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.IXMLWriter;
import org.hl7.fhir.utilities.xml.XMLWriter;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * General parser for XML content. You instantiate an XmlParser of these, but you 
 * actually use parse or parseGeneral defined on this class
 * 
 * The two classes are separated to keep generated and manually maintained code apart.
 */
public abstract class XmlParserBase extends ParserBase implements IParser {

  @Override
  public ParserType getType() {
	  return ParserType.XML;
  }

  // -- in descendent generated code --------------------------------------
  
  abstract protected Resource parseResource(XmlPullParser xpp) throws Exception;
  abstract protected Type parseType(XmlPullParser xml, String type) throws Exception;
  abstract protected void composeType(String prefix, Type type) throws Exception;
 
  /* -- entry points --------------------------------------------------- */
  
  /**
   * Parse content that is known to be a resource
   */
  @Override
  public Resource parse(InputStream input) throws Exception {
    XmlPullParser xpp = loadXml(input);
    return parse(xpp);
  }

  /**
   * parse xml that is known to be a resource, and that is already being read by an XML Pull Parser
   * This is if a resource is in a bigger piece of XML.   
   */
  public Resource parse(XmlPullParser xpp) throws Exception {
    if (xpp.getNamespace() == null)
      throw new Exception("This does not appear to be a FHIR resource (no namespace '"+xpp.getNamespace()+"') (@ /) "+Integer.toString(xpp.getEventType()));
    if (!xpp.getNamespace().equals(FHIR_NS))
      throw new Exception("This does not appear to be a FHIR resource (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return parseResource(xpp);
  }

  @Override
  public Type parseType(InputStream input, String knownType) throws Exception {
    XmlPullParser xml = loadXml(input);
    return parseType(xml, knownType);
  }


  /**
   * Compose a resource to a stream, possibly using pretty presentation for a human reader (used in the spec, for example, but not normally in production)
   */
  @Override
  public void compose(OutputStream stream, Resource resource) throws Exception {
    XMLWriter writer = new XMLWriter(stream, "UTF-8");
    writer.setPretty(style == OutputStyle.PRETTY);
    writer.start();
    compose(writer, resource, writer.isPretty());
    writer.close();
  }

  /**
   * Compose a resource to a stream, possibly using pretty presentation for a human reader, and maybe a different choice in the xhtml narrative (used in the spec in one place, but should not be used in production)
   */
  public void compose(OutputStream stream, Resource resource, boolean htmlPretty) throws Exception {
    XMLWriter writer = new XMLWriter(stream, "UTF-8");
    writer.setPretty(style == OutputStyle.PRETTY);
    writer.start();
    compose(writer, resource, htmlPretty);
    writer.close();
  }

  
  /**
   * Compose a type to a stream (used in the spec, for example, but not normally in production)
   */
  public void compose(OutputStream stream, String rootName, Type type) throws Exception {
    xml = new XMLWriter(stream, "UTF-8");
    xml.setPretty(style == OutputStyle.PRETTY);
    xml.start();
    xml.setDefaultNamespace(FHIR_NS);
    composeType(Utilities.noString(rootName) ? "value" : rootName, type);
    xml.close();
  }

  @Override
  public void compose(OutputStream stream, Type type, String rootName) throws Exception {
    xml = new XMLWriter(stream, "UTF-8");
    xml.setPretty(style == OutputStyle.PRETTY);
    xml.start();
    xml.setDefaultNamespace(FHIR_NS);
    composeType(Utilities.noString(rootName) ? "value" : rootName, type);
    xml.close();
  }


  
  /* -- xml routines --------------------------------------------------- */
  
  protected XmlPullParser loadXml(String source) throws Exception {
    return loadXml(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)));
  }
  
  protected XmlPullParser loadXml(InputStream stream) throws Exception {
    BufferedInputStream input = new BufferedInputStream(stream);
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance(System.getProperty(XmlPullParserFactory.PROPERTY_NAME), null);
    factory.setNamespaceAware(true);
    XmlPullParser xpp = factory.newPullParser();
    xpp.setInput(input, "UTF-8");
    next(xpp);
    nextNoWhitespace(xpp);
    comments.clear();
    
    return xpp;
  }
 
	protected int next(XmlPullParser xpp) throws Exception {
	  if (handleComments)
	    return xpp.nextToken();
	  else
	    return xpp.next();    
  }

  private List<String> comments = new ArrayList<String>();
	
  protected int nextNoWhitespace(XmlPullParser xpp) throws Exception {
    int eventType = xpp.getEventType();
    while ((eventType == XmlPullParser.TEXT && xpp.isWhitespace()) || (eventType == XmlPullParser.COMMENT) 
        || (eventType == XmlPullParser.CDSECT) || (eventType == XmlPullParser.IGNORABLE_WHITESPACE)
        || (eventType == XmlPullParser.PROCESSING_INSTRUCTION) || (eventType == XmlPullParser.DOCDECL)) {
      if (eventType == XmlPullParser.COMMENT) {
        comments.add(xpp.getText());
      }
      eventType = next(xpp);
    }
    return eventType;
  }


	protected void skipElementWithContent(XmlPullParser xpp)  throws Exception {
  	// when this is called, we are pointing an element that may have content
    while (xpp.getEventType() != XmlPullParser.END_TAG) {
  		next(xpp);
    	if (xpp.getEventType() == XmlPullParser.START_TAG) 
    		skipElementWithContent(xpp);
    }
    next(xpp);
  }
  
  protected void skipEmptyElement(XmlPullParser xpp) throws Exception {
    while (xpp.getEventType() != XmlPullParser.END_TAG) 
      next(xpp);
    next(xpp);
  }

  protected IXMLWriter xml;
  protected boolean htmlPretty;
   


  /* -- worker routines --------------------------------------------------- */
  
  protected void parseTypeAttributes(XmlPullParser xpp, Type t) throws Exception {
    parseElementAttributes(xpp, t);
  }

  protected void parseElementAttributes(XmlPullParser xpp, Element e) throws Exception {
    if (xpp.getAttributeValue(null, "id") != null) {
      e.setId(xpp.getAttributeValue(null, "id"));
      idMap.put(e.getId(), e);
    }
    if (!comments.isEmpty()) {
      e.getFormatComments().addAll(comments);
      comments.clear();
    }
  }

  protected void parseBackboneAttributes(XmlPullParser xpp, Element e) throws Exception {
  	parseElementAttributes(xpp, e);
  }

  private String pathForLocation(XmlPullParser xpp) {
    return xpp.getPositionDescription();
  }
  
  
  protected void unknownContent(XmlPullParser xpp) throws Exception {
    if (!isAllowUnknownContent())
      throw new Exception("Unknown Content "+xpp.getName()+" @ "+pathForLocation(xpp));
  }
  
  protected XhtmlNode parseXhtml(XmlPullParser xpp) throws Exception {
    XhtmlParser prsr = new XhtmlParser();
    return prsr.parseHtmlNode(xpp);
  }

  private String parseString(XmlPullParser xpp) throws Exception {
    StringBuilder res = new StringBuilder();
    next(xpp);
    while (xpp.getEventType() == XmlPullParser.TEXT || xpp.getEventType() == XmlPullParser.IGNORABLE_WHITESPACE || xpp.getEventType() == XmlPullParser.ENTITY_REF) {
      res.append(xpp.getText());
      next(xpp);
    }
    if (xpp.getEventType() != XmlPullParser.END_TAG)
      throw new Exception("Bad String Structure - parsed "+res.toString()+" now found "+Integer.toString(xpp.getEventType()));
    next(xpp);
    return res.length() == 0 ? null : res.toString();
  }
  
  private int parseInt(XmlPullParser xpp) throws Exception {
    int res = -1;
    String textNode = parseString(xpp);
    res = java.lang.Integer.parseInt(textNode);
    return res;
  }
  
  protected DomainResource parseDomainResourceContained(XmlPullParser xpp) throws Exception {
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    if (eventType == XmlPullParser.START_TAG) { 
      DomainResource dr = (DomainResource) parseResource(xpp);
      nextNoWhitespace(xpp);
      next(xpp);
      return dr;
    } else {
      unknownContent(xpp);
      return null;
    }
  } 
  protected Resource parseResourceContained(XmlPullParser xpp) throws Exception {
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    if (eventType == XmlPullParser.START_TAG) { 
      Resource r = (Resource) parseResource(xpp);
      nextNoWhitespace(xpp);
      next(xpp);
      return r;
    } else {
      unknownContent(xpp);
      return null;
    }
  } 
  
	public void compose(IXMLWriter writer, Resource resource, boolean htmlPretty) throws Exception {
		this.htmlPretty = htmlPretty;
		xml = writer;
		xml.setDefaultNamespace(FHIR_NS);
		composeResource(resource);
	}
	
	protected abstract void composeResource(Resource resource) throws Exception;

	protected void composeElementAttributes(Element element) throws Exception {
	  if (style != OutputStyle.CANONICAL)
	    for (String comment : element.getFormatComments())
	      xml.comment(comment, false);
		if (element.getId() != null) 
			xml.attribute("id", element.getId());
	}

	protected void composeTypeAttributes(Type type) throws Exception {
		composeElementAttributes(type);
	}

	protected void composeXhtml(String name, XhtmlNode html) throws Exception {
    if (!Utilities.noString(xhtmlMessage)) {
      xml.open(XhtmlComposer.XHTML_NS, name);
      xml.comment(xhtmlMessage, false);
      xml.close(XhtmlComposer.XHTML_NS, name);
    } else {
		XhtmlComposer comp = new XhtmlComposer();
		// name is also found in the html and should the same
		// ? check that
		boolean oldPretty = xml.isPretty();
		xml.setPretty(htmlPretty);
      comp.setXmlOnly(true);
    if (html.getNodeType() != NodeType.Text)
		xml.namespace(XhtmlComposer.XHTML_NS, null);
		comp.compose(xml, html);
		xml.setPretty(oldPretty);
    }
	}


  protected void composeDomainResource(String name, DomainResource res) throws Exception {
    xml.open(FHIR_NS, name);
    composeResource(res.getResourceType().toString(), res);
    xml.close(FHIR_NS, name);
  }

	protected abstract void composeResource(String name, Resource res) throws Exception;
	
}
