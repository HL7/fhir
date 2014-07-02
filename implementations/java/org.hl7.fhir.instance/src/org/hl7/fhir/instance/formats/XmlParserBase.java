package org.hl7.fhir.instance.formats;
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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Binary;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * General parser for XML content. You instantiate an XmlParser of these, but you 
 * actually use parse or parseGeneral defined on this class
 * 
 * The two classes are separated to keep generated and manually maintained code apart.
 */
public abstract class XmlParserBase extends ParserBase implements Parser {

  private boolean parseComments = true;
  
	public boolean isParseComments() {
    return parseComments;
  }

  public void setParseComments(boolean parseComments) {
    this.parseComments = parseComments;
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
	  if (parseComments)
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

	/**
	 * Whether to throw an exception if unknown content is found (or just skip it)
	 */
  private boolean allowUnknownContent;
  
  /**
   * @return Whether to throw an exception if unknown content is found (or just skip it) 
   */
  public boolean isAllowUnknownContent() {
    return allowUnknownContent;
  }
  /**
   * @param allowUnknownContent Whether to throw an exception if unknown content is found (or just skip it)
   */
  public void setAllowUnknownContent(boolean allowUnknownContent) {
    this.allowUnknownContent = allowUnknownContent;
  }
    
  abstract protected Resource parseResource(XmlPullParser xpp) throws Exception;

  /** -- worker routines --------------------------------------------------- */
  
  protected void parseTypeAttributes(XmlPullParser xpp, Type t) throws Exception {
    parseElementAttributes(xpp, t);
  }

  protected void parseElementAttributes(XmlPullParser xpp, Element e) throws Exception {
    if (xpp.getAttributeValue(null, "id") != null) {
      e.setXmlId(xpp.getAttributeValue(null, "id"));
      idMap.put(e.getXmlId(), e);
    }
    if (!comments.isEmpty()) {
      e.getXmlComments().addAll(comments);
      comments.clear();
    }
  }

  protected void parseBackboneAttributes(XmlPullParser xpp, Element e) throws Exception {
  	parseElementAttributes(xpp, e);
  }

  protected void parseResourceAttributes(XmlPullParser xpp, Resource r) throws Exception {
    parseElementAttributes(xpp, r);
  }


  private String pathForLocation(XmlPullParser xpp) {
    return xpp.getPositionDescription();
  }
  

  /**
   * Parse content that may be either a resource or a bundle
   */
  @Override
  public ResourceOrFeed parseGeneral(InputStream input) throws Exception {
    XmlPullParser xpp = loadXml(input);
    ResourceOrFeed r = new ResourceOrFeed();
    
    if (xpp.getNamespace().equals(FHIR_NS) && !xpp.getName().equalsIgnoreCase("Taglist"))
      r.resource = parseResource(xpp);
    else if (xpp.getNamespace().equals(FHIR_NS) && xpp.getName().equalsIgnoreCase("Taglist"))
        r.taglist = parseTagList(xpp);
    else if (xpp.getNamespace().equals(ATOM_NS)) 
      r.feed = parseFeed(xpp);
    else
    	throw new Exception("This does not appear to be a FHIR resource (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return r;    
  }

  /**
   * Parse content that is known to be a resource
   */
  @Override
  public Resource parse(InputStream input) throws Exception {
    XmlPullParser xpp = loadXml(input);
  
    if (xpp.getNamespace() == null)
      throw new Exception("This does not appear to be a FHIR resource (no namespace '"+xpp.getNamespace()+"') (@ /) "+Integer.toString(xpp.getEventType()));
    if (!xpp.getNamespace().equals(FHIR_NS))
      throw new Exception("This does not appear to be a FHIR resource (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return parseResource(xpp);
  }

  /**
   * parse xml that is known to be a resource, and that is already being read by an XML Pull Parser  
   */
  public Resource parse(XmlPullParser xpp) throws Exception {
    if (!xpp.getNamespace().equals(FHIR_NS))
      throw new Exception("This does not appear to be a FHIR resource (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return parseResource(xpp);
  }

  
  protected void unknownContent(XmlPullParser xpp) throws Exception {
    if (!isAllowUnknownContent())
      throw new Exception("Unknown Content "+xpp.getName()+" @ "+pathForLocation(xpp));
  }
  
  protected XhtmlNode parseXhtml(XmlPullParser xpp) throws Exception {
    XhtmlParser prsr = new XhtmlParser();
    return prsr.parseHtmlNode(xpp);
  }



  protected Resource parseBinary(XmlPullParser xpp) throws Exception {
    Binary res = new Binary();
    parseElementAttributes(xpp, res);
    res.setContentType(xpp.getAttributeValue(null, "contentType"));
    int eventType = next(xpp);
    if (eventType == XmlPullParser.TEXT) {
      res.setContent(Base64.decodeBase64(xpp.getText().getBytes()));
      eventType = next(xpp);
    }
    if (eventType != XmlPullParser.END_TAG)
      throw new Exception("Bad String Structure");
    next(xpp);
    return res;
  }

  private AtomFeed parseFeed(XmlPullParser xpp) throws Exception {
    if (!(xpp.getNamespace().equals(ATOM_NS) || (xpp.getNamespace().equals(FHIR_NS) && xpp.getName().equalsIgnoreCase("TagList"))))
      throw new Exception("This does not appear to be an atom feed (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return parseAtom(xpp);
  }

  private List<AtomCategory> parseTagList(XmlPullParser xpp) throws Exception {
  	List<AtomCategory> res = new ArrayList<AtomCategory>();
    if (!xpp.getName().equalsIgnoreCase("Taglist")) //Seems like Taglist, taglist or TagList is being returned
      throw new Exception("This does not appear to be a tag list (wrong name '"+xpp.getName()+"') (@ /)");
    next(xpp);
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.add(new AtomCategory(xpp.getAttributeValue(null, "scheme"), xpp.getAttributeValue(null, "term"), xpp.getAttributeValue(null, "label")));
        skipEmptyElement(xpp);
      } else
        skipElementWithContent(xpp);
  	
      eventType = nextNoWhitespace(xpp);
    }

    return res;  
  }

  private AtomFeed parseAtom(XmlPullParser xpp) throws Exception {
    AtomFeed res = new AtomFeed();
    if (!(xpp.getName().equals("feed")||xpp.getName().equalsIgnoreCase("TagList")))
      throw new Exception("This does not appear to be an atom feed (wrong name '"+xpp.getName()+"') (@ /)");
    xpp.next();
    
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("id"))
        res.setId(parseString(xpp));
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")){
        res.getLinks().put(xpp.getAttributeValue(null, "rel"), xpp.getAttributeValue(null, "href"));
        skipEmptyElement(xpp);
      } else if(eventType == XmlPullParser.START_TAG && xpp.getName().equals("updated"))
        res.setUpdated(parseDate(xpp));
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.getTags().add(new AtomCategory(xpp.getAttributeValue(null, "scheme"), xpp.getAttributeValue(null, "term"), xpp.getAttributeValue(null, "label")));
        skipEmptyElement(xpp);
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("entry"))
        res.getEntryList().add(parseEntry(xpp));
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        xpp.next();
        eventType = nextNoWhitespace(xpp);
        while (eventType != XmlPullParser.END_TAG) {
          if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
            res.setAuthorName(parseString(xpp));
          } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uri"))
            res.setAuthorUri(parseString(xpp));
          else
            throw new Exception("Bad Xml parsing entry.author");
          eventType = nextNoWhitespace(xpp);
        }
        xpp.next();
      }else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("totalResults")){
        res.setTotalResults(parseInt(xpp));
      }
      else
        skipElementWithContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }

    return res;  
  }

  @SuppressWarnings("unchecked")
  private <T extends Resource> AtomEntry<T> parseEntry(XmlPullParser xpp) throws Exception {
    AtomEntry<T> res = new AtomEntry<T>();
    
    xpp.next();    
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("title")) {
        res.setTitle(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("id"))
        res.setId(parseString(xpp));
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.getLinks().put(xpp.getAttributeValue(null, "rel"), xpp.getAttributeValue(null, "href"));
        skipEmptyElement(xpp);
      } else if(eventType == XmlPullParser.START_TAG && xpp.getName().equals("updated"))
        res.setUpdated(parseDate(xpp));
      else if(eventType == XmlPullParser.START_TAG && xpp.getName().equals("published"))
        res.setPublished(parseDate(xpp));
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.getTags().add(new AtomCategory(xpp.getAttributeValue(null, "scheme"), xpp.getAttributeValue(null, "term"), xpp.getAttributeValue(null, "label")));
        skipEmptyElement(xpp);
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("author")) {
        xpp.next();
        eventType = nextNoWhitespace(xpp);
        while (eventType != XmlPullParser.END_TAG) {
          if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
            res.setAuthorName(parseString(xpp));
          } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("uri"))
            res.setAuthorUri(parseString(xpp));
          else
            throw new Exception("Bad Xml parsing entry.author");
          eventType = nextNoWhitespace(xpp);
        }
        xpp.next();
      }
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        xpp.next();
        nextNoWhitespace(xpp);
        XmlParser p = new XmlParser();
        p.setAllowUnknownContent(this.isAllowUnknownContent());
        res.setResource((T)p.parse(xpp));//TODO Refactor architecture to eliminate this unsafe cast and better support generics
        xpp.next();
        nextNoWhitespace(xpp);
        if (xpp.getName().equals("content")){
        	xpp.next();
        }
        
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("summary")) {
        xpp.next();
        nextNoWhitespace(xpp);
        res.setSummary(new XhtmlParser().parseHtmlNode(xpp));
        xpp.next();
        nextNoWhitespace(xpp);
        if(xpp.getName().equals("summary")) {
        	xpp.next();
        }
      } else
        throw new Exception("Bad Xml parsing entry");
      eventType = nextNoWhitespace(xpp);
    }

    xpp.next();
    return res;  
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
  
  private DateAndTime parseDate(XmlPullParser xpp) throws Exception {
    return new DateAndTime(parseString(xpp));    
  }

 
}
