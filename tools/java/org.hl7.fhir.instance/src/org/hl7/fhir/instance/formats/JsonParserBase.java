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
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Binary;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
/**
 * General parser for JSON content. You instantiate an JsonParser of these, but you 
 * actually use parse or parseGeneral defined on this class
 * 
 * The two classes are separated to keep generated and manually maintained code apart.
 */
public abstract class JsonParserBase extends ParserBase implements Parser {

  abstract protected Resource parseResource(JsonObject json) throws Exception;
  private static com.google.gson.JsonParser  parser = new com.google.gson.JsonParser();

  private JsonObject loadJson(InputStream input) throws Exception {
    return parser.parse(TextFile.streamToString(input)).getAsJsonObject();
  }
  
  /**
   * Parse content that may be either a resource or a bundle
   */
  public ResourceOrFeed parseGeneral(InputStream input) throws Exception {
    JsonObject json = loadJson(input);
    ResourceOrFeed r = new ResourceOrFeed();
    
    if (json.has("feed"))
      r.feed = parseAtom(json.getAsJsonObject("feed"));
    else  
      r.resource = parseResource(json);
    return r;    
  }

  /**
   * Parse content that is known to be a resource
   */
  public Resource parse(InputStream input) throws Exception {
    JsonObject json = loadJson(input);
  
    return parseResource(json);
  }

  /**
   * parse xml that is known to be a resource, and that has already been read into a JSON object  
   */
  public Resource parse(JsonObject json) throws Exception {
    return parseResource(json);
  }

  protected void parseElementProperties(JsonObject json, Element e) throws Exception {
    if (json != null && json.has("_id"))
      e.setXmlId(json.get("_id").getAsString());
    if (!Utilities.noString(e.getXmlId()))
      idMap.put(e.getXmlId(), e);
  }

  protected abstract void parseResourceProperties(JsonObject json, Resource r) throws Exception;
  
  protected XhtmlNode parseXhtml(String value) throws Exception {
    XhtmlParser prsr = new XhtmlParser();
    return prsr.parse(value, "div").getChildNodes().get(0);
  }

  protected Resource parseBinary(JsonObject json) throws Exception {
    Binary res = new Binary();
    parseResourceProperties(json, res);
    res.setContentType(json.get("contentType").getAsString());
    res.setContent(Base64.decodeBase64(json.get("content").getAsString().getBytes()));
    return res;
  }

  private AtomFeed parseAtom(JsonObject json) throws Exception {
    AtomFeed res = new AtomFeed();
    if (json.has("title"))
      res.setTitle(json.get("title").getAsString());
    if (json.has("id"))
      res.setId(json.get("id").getAsString());
    if (json.has("updated"))
      res.setUpdated(new DateAndTime(json.get("updated").getAsString()));
    if (json.has("author")) {
      JsonObject author = json.getAsJsonArray("author").get(0).getAsJsonObject();
      if (author.has("name"))
        res.setAuthorName(author.get("name").getAsString());
      if (author.has("uri"))
        res.setAuthorUri(author.get("uri").getAsString());
    }
    if (json.has("link")) {
      JsonArray array = json.getAsJsonArray("link");
      for (int i = 0; i < array.size(); i++) {
        parseLink(res.getLinks(), array.get(i).getAsJsonObject());
      }
    }
    if (json.has("category")) {
      JsonObject cat = json.getAsJsonArray("category").get(0).getAsJsonObject();
      if (cat.has("term") && cat.has("scheme"))
        res.getTags().add(new AtomCategory(cat.get("scheme").getAsString(), cat.get("term").getAsString(), cat.has("label") ? cat.get("label").getAsString() : null));
    }
    JsonArray array = json.getAsJsonArray("entry");
    for (int i = 0; i < array.size(); i++) {
      res.getEntryList().add(parseEntry(array.get(i).getAsJsonObject()));
    }
    return res;  
  }

  private void parseLink(Map<String, String> links, JsonObject json) throws Exception {
    if (json.has("href") && json.has("rel"))
    links.put(json.get("rel").getAsString(), json.get("href").getAsString());    
  }

  private <T extends Resource> AtomEntry<T> parseEntry(JsonObject json) throws Exception {
    AtomEntry<T> res = new AtomEntry<T>();
    if (json.has("title"))
      res.setTitle(json.get("title").getAsString());
    if (json.has("id"))
      res.setId(json.get("id").getAsString());
    if (json.has("updated"))
      res.setUpdated(new DateAndTime(json.get("updated").getAsString()));
    if (json.has("published"))
      res.setPublished(new DateAndTime(json.get("published").getAsString()));
    if (json.has("link")) {
      JsonArray array = json.getAsJsonArray("link");
      for (int i = 0; i < array.size(); i++) {
        parseLink(res.getLinks(), array.get(i).getAsJsonObject());
      }
    }
    if (json.has("author")) {
      JsonObject author = json.getAsJsonArray("author").get(0).getAsJsonObject();
      if (author.has("name"))
        res.setAuthorName(author.get("name").getAsString());
      if (author.has("uri"))
        res.setAuthorUri(author.get("uri").getAsString());
    }
    if (json.has("category")) {
      JsonObject cat = json.getAsJsonArray("category").get(0).getAsJsonObject();
      if (cat.has("term") && cat.has("scheme"))
        res.getTags().add(new AtomCategory(cat.get("scheme").getAsString(), cat.get("term").getAsString(), cat.has("label") ? cat.get("label").getAsString() : null));
    }
    if (json.has("summary"))
      res.setSummary(new XhtmlParser().parse(json.get("summary").getAsString(), "div").getChildNodes().get(0));
    if (json.has("content"))
      res.setResource((T)new JsonParser().parse(json.getAsJsonObject("content")));//TODO Architecture needs to be refactor to prevent this unsafe cast and better support generics
    return res;
  }
  
  
}
