package org.hl7.fhir.instance.formats;
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

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AtomParser extends XmlBase {
  
  public AtomFeed parse(InputStream input) throws Exception {
    XmlPullParser xpp = loadXml(input);
  
    if (!xpp.getNamespace().equals(ATOM_NS))
      throw new Exception("This does not appear to be an atom feed (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return parseAtom(xpp);
  }
  
  public AtomFeed parse(XmlPullParser xpp) throws Exception {
    if (!xpp.getNamespace().equals(ATOM_NS))
      throw new Exception("This does not appear to be an atom feed (wrong namespace '"+xpp.getNamespace()+"') (@ /)");
    return parseAtom(xpp);
  }

  public AtomFeed parse(JSONObject json) throws Exception {
    return parseAtom(json);
  }

  private String parseString(XmlPullParser xpp) throws Exception {
    String res = null;
    if (xpp.next() == XmlPullParser.TEXT) {
      res = xpp.getText();
      if (xpp.next() != XmlPullParser.END_TAG)
        throw new Exception("Bad String Structure");
    }
    xpp.next();
    return res;
  }
  
  private int parseInt(XmlPullParser xpp) throws Exception {
    int res = -1;
    String textNode = parseString(xpp);
    res = Integer.parseInt(textNode);
    return res;
  }
  
  private AtomFeed parseAtom(XmlPullParser xpp) throws Exception {
    AtomFeed res = new AtomFeed();
    if (!xpp.getName().equals("feed"))
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
      else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("entry"))
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
        throw new Exception("Bad Xml parsing Atom Feed");
      eventType = nextNoWhitespace(xpp);
    }

    return res;  
  }

  private AtomFeed parseAtom(JSONObject json) throws Exception {
    AtomFeed res = new AtomFeed();
    if (json.has("title"))
      res.setTitle(json.getString("title"));
    if (json.has("id"))
      res.setId(json.getString("id"));
    if (json.has("updated"))
      res.setUpdated(xmlToDate(json.getString("updated")));
    if (json.has("authors")) {
      JSONObject author = json.getJSONArray("authors").getJSONObject(0);
      if (author.has("name"))
        res.setAuthorName(author.getString("name"));
      if (author.has("uri"))
        res.setAuthorUri(author.getString("uri"));
    }
    if (json.has("links")) {
      JSONArray array = json.getJSONArray("links");
      for (int i = 0; i < array.length(); i++) {
        parseLink(res.getLinks(), array.getJSONObject(i));
      }
    }
    JSONArray array = json.getJSONArray("entries");
    for (int i = 0; i < array.length(); i++) {
      res.getEntryList().add(parseEntry(array.getJSONObject(i)));
    }
    return res;  
  }

  private void parseLink(Map<String, String> links, JSONObject json) throws Exception {
    if (json.has("href") && json.has("rel"))
    links.put(json.getString("rel"), json.getString("href"));    
  }

  private AtomEntry parseEntry(JSONObject json) throws Exception {
    AtomEntry res = new AtomEntry();
    if (json.has("title"))
      res.setTitle(json.getString("title"));
    if (json.has("id"))
      res.setId(json.getString("id"));
    if (json.has("updated"))
      res.setUpdated(xmlToDate(json.getString("updated")));
    if (json.has("published"))
      res.setPublished(xmlToDate(json.getString("published")));
    if (json.has("links")) {
      JSONArray array = json.getJSONArray("links");
      for (int i = 0; i < array.length(); i++) {
        parseLink(res.getLinks(), array.getJSONObject(i));
      }
    }
    if (json.has("authors")) {
      JSONObject author = json.getJSONArray("authors").getJSONObject(0);
      if (author.has("name"))
        res.setAuthorName(author.getString("name"));
      if (author.has("uri"))
        res.setAuthorUri(author.getString("uri"));
    }
    if (json.has("categories")) {
      JSONObject cat = json.getJSONArray("categories").getJSONObject(0);
      if (cat.has("term") && cat.has("scheme") && cat.getString("scheme").equals("http://hl7.org/fhir/tag"))
        res.getTags().put(cat.getString("term"), cat.has("label") ? cat.getString("label") : null);
    }
    if (json.has("summary"))
      res.setSummary(new XhtmlParser().parse(json.getString("summary"), "div").getChildNodes().get(0));
    if (json.has("content"))
      res.setResource(new JsonParser().parse(json.getJSONObject("content")));
    return res;
  }
  
  private AtomEntry parseEntry(XmlPullParser xpp) throws Exception {
    AtomEntry res = new AtomEntry();
    
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
        if ("http://hl7.org/fhir/tag".equals(xpp.getAttributeValue(null, "scheme"))) 
          res.getTags().put(xpp.getAttributeValue(null, "term"), xpp.getAttributeValue(null, "label"));
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
        res.setResource(p.parse(xpp));
        xpp.next();
        nextNoWhitespace(xpp);
        xpp.next();
        
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("summary")) {
        xpp.next();
        nextNoWhitespace(xpp);
        res.setSummary(new XhtmlParser().parseHtmlNode(xpp));
        xpp.next();
        nextNoWhitespace(xpp);
        xpp.next();
      } else
        throw new Exception("Bad Xml parsing entry");
      eventType = nextNoWhitespace(xpp);
    }

    xpp.next();
    return res;  
  }

  private Calendar parseDate(XmlPullParser xpp) throws Exception {
    return xmlToDate(parseString(xpp));    
  }

 

}
