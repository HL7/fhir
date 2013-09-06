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

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.instance.model.Integer;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

public abstract class JsonParserBase extends ParserBase implements Parser {

  abstract protected Resource parseResource(JSONObject json) throws Exception;

  private JSONObject loadJson(InputStream input) throws Exception {
    return new JSONObject(TextFile.streamToString(input));
  }
  
  public ResourceOrFeed parseGeneral(InputStream input) throws Exception {
    JSONObject json = loadJson(input);
    ResourceOrFeed r = new ResourceOrFeed();
    
    if (json.has("feed"))
      r.feed = parseAtom(json.getJSONObject("feed"));
    else  
      r.resource = parseResource(json);
    return r;    
  }

  public Resource parse(InputStream input) throws Exception {
    JSONObject json = loadJson(input);
  
    return parseResource(json);
  }

  public Resource parse(JSONObject json) throws Exception {
    return parseResource(json);
  }

  protected void parseElementProperties(JSONObject json, Element e) throws Exception {
    if (json.has("_id"))
      e.setXmlId(json.getString("_id"));
    if (!Utilities.noString(e.getXmlId()))
      idMap.put(e.getXmlId(), e);
  }

  protected abstract void parseResourceProperties(JSONObject json, Resource r) throws Exception;
  
  protected XhtmlNode parseXhtml(String value) throws Exception {
    XhtmlParser prsr = new XhtmlParser();
    return prsr.parse(value, "div").getChildNodes().get(0);
  }

  protected Resource parseBinary(JSONObject json) throws Exception {
    Binary res = new Binary();
    parseResourceProperties(json, res);
    res.setContentType(json.getString("contentType"));
    res.setContent(Base64.decodeBase64(json.getString("content").getBytes()));
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
    if (json.has("author")) {
      JSONObject author = json.getJSONArray("author").getJSONObject(0);
      if (author.has("name"))
        res.setAuthorName(author.getString("name"));
      if (author.has("uri"))
        res.setAuthorUri(author.getString("uri"));
    }
    if (json.has("link")) {
      JSONArray array = json.getJSONArray("link");
      for (int i = 0; i < array.length(); i++) {
        parseLink(res.getLinks(), array.getJSONObject(i));
      }
    }
    if (json.has("category")) {
      JSONObject cat = json.getJSONArray("category").getJSONObject(0);
      if (cat.has("term") && cat.has("scheme") && cat.getString("scheme").equals("http://hl7.org/fhir/tag"))
        res.getTags().put(cat.getString("term"), cat.has("label") ? cat.getString("label") : null);
    }
    JSONArray array = json.getJSONArray("entry");
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
    if (json.has("link")) {
      JSONArray array = json.getJSONArray("links");
      for (int i = 0; i < array.length(); i++) {
        parseLink(res.getLinks(), array.getJSONObject(i));
      }
    }
    if (json.has("author")) {
      JSONObject author = json.getJSONArray("author").getJSONObject(0);
      if (author.has("name"))
        res.setAuthorName(author.getString("name"));
      if (author.has("uri"))
        res.setAuthorUri(author.getString("uri"));
    }
    if (json.has("category")) {
      JSONObject cat = json.getJSONArray("category").getJSONObject(0);
      if (cat.has("term") && cat.has("scheme") && cat.getString("scheme").equals("http://hl7.org/fhir/tag"))
        res.getTags().put(cat.getString("term"), cat.has("label") ? cat.getString("label") : null);
    }
    if (json.has("summary"))
      res.setSummary(new XhtmlParser().parse(json.getString("summary"), "div").getChildNodes().get(0));
    if (json.has("content"))
      res.setResource(new JsonParser().parse(json.getJSONObject("content")));
    return res;
  }
  
  
}
