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

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Binary;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
  
  private JsonObject loadJson(String input) throws Exception {
    return parser.parse(input).getAsJsonObject();
  }

  /**
   * Parse content that is known to be a resource
   */
  @Override
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
    if (json != null && json.has("id"))
      e.setXmlId(json.get("id").getAsString());
    if (!Utilities.noString(e.getXmlId()))
      idMap.put(e.getXmlId(), e);
    if (json.has("fhir_comments")) {
      JsonArray array = json.getAsJsonArray("fhir_comments");
      for (int i = 0; i < array.size(); i++) {
        e.getXmlComments().add(array.get(i).getAsString());
      }
    }
  }
  
  protected XhtmlNode parseXhtml(String value) throws Exception {
    XhtmlParser prsr = new XhtmlParser();
    return prsr.parse(value, "div").getChildNodes().get(0);
  }
//
//  protected Resource parseBinary(JsonObject json) throws Exception {
//    Binary res = new Binary();
//    parseResourceProperties(json, res);
//    res.setContentType(json.get("contentType").getAsString());
//    res.setContent(Base64.decodeBase64(json.get("content").getAsString().getBytes()));
//    return res;
//  }

  private void parseLink(Map<String, String> links, JsonObject json) throws Exception {
    if (json.has("href") && json.has("rel"))
    links.put(json.get("rel").getAsString(), json.get("href").getAsString());    
  }

  public Type parseType(String source, String type) throws Exception {
    JsonObject json = loadJson(source);
    return parseType(json, type);
  }

  protected abstract Type parseType(JsonObject json, String type) throws Exception;
  
  protected DomainResource parseDomainResource(JsonObject json) throws Exception {
	  return (DomainResource) parseResource(json);
  }

  protected abstract Type parseType(String prefix, JsonObject json) throws Exception;
  protected abstract boolean hasTypeName(JsonObject json, String prefix);

  protected void parseExtensions(JsonObject json, List<Extension> extensions, boolean inExtension) throws Exception {
	  for (Entry<String, JsonElement> p : json.entrySet()) {
	  	if (p.getKey().contains(":") || (inExtension && !(p.getKey().startsWith("value") || p.getKey().startsWith("_value")))) {
	  		// it's an extension
	  		if (!(p.getValue() instanceof JsonArray))
	  			throw new Exception("The property "+p.getKey()+" looks like an extension, but isn't a JSON array (it's a "+p.getValue().getClass().getName()+")");
	  		JsonArray arr = (JsonArray) p.getValue();
	  		for (int i = 0; i < arr.size(); i++) {
	  			Extension ex = new Extension();
	  			ex.setUrl(p.getKey());
	  			JsonObject obj = (JsonObject) arr.get(i);
	  			if (hasTypeName(obj, "value"))
	  			  ex.setValue(parseType("value", obj));
	  			parseExtensions(obj, ex.getExtension(), true);
	  			extensions.add(ex);
	  		}
	  	}
	  }
  }
  
}
