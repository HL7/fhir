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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.List;

import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
/**
 * General parser for JSON content. You instantiate an JsonParser of these, but you 
 * actually use parse or parseGeneral defined on this class
 * 
 * The two classes are separated to keep generated and manually maintained code apart.
 */
public abstract class JsonParserBase extends ParserBase implements IParser {
	
  @Override
  public ParserType getType() {
	  return ParserType.JSON;
  }

	private static com.google.gson.JsonParser  parser = new com.google.gson.JsonParser();

  // -- in descendent generated code --------------------------------------
  
  abstract protected Resource parseResource(JsonObject json) throws Exception;
  abstract protected Type parseType(JsonObject json, String type) throws Exception;
  abstract protected Type parseType(String prefix, JsonObject json) throws Exception;
  abstract protected boolean hasTypeName(JsonObject json, String prefix);
  abstract protected void composeResource(Resource resource) throws Exception;
  abstract protected void composeTypeInner(Type type) throws Exception;

  /* -- entry points --------------------------------------------------- */

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

  @Override
  public Type parseType(InputStream input, String type) throws Exception {
    JsonObject json = loadJson(input);
    return parseType(json, type);
  }

  /**
   * Compose a resource to a stream, possibly using pretty presentation for a human reader (used in the spec, for example, but not normally in production)
   */
  @Override
  public void compose(OutputStream stream, Resource resource) throws Exception {
    OutputStreamWriter osw = new OutputStreamWriter(stream, "UTF-8");
    if (style == OutputStyle.CANONICAL)
      json = new JsonCreatorCanonical(osw);
    else
      json = new JsonCreatorGson(osw);
    json.setIndent(style == OutputStyle.PRETTY ? "  " : "");
    json.beginObject();
    composeResource(resource);
    json.endObject();
    json.finish();
    osw.flush();
    osw.close();
  }

  /**
   * Compose a resource using a pre-existing JsonWriter
   */
  public void compose(JsonCreator writer, Resource resource) throws Exception {
    json = writer;
    composeResource(resource);
  }
  
  @Override
  public void compose(OutputStream stream, Type type, String rootName) throws Exception {
    OutputStreamWriter osw = new OutputStreamWriter(stream, "UTF-8");
    if (style == OutputStyle.CANONICAL)
      json = new JsonCreatorCanonical(osw);
    else
      json = new JsonCreatorGson(osw);
    json.setIndent(style == OutputStyle.PRETTY ? "  " : "");
    json.beginObject();
    composeTypeInner(type);
    json.endObject();
    json.finish();
    osw.flush();
    osw.close();
  }
    

  
  /* -- json routines --------------------------------------------------- */

  protected JsonCreator json;
  private boolean htmlPretty;
  
  private JsonObject loadJson(InputStream input) throws Exception {
    return parser.parse(TextFile.streamToString(input)).getAsJsonObject();
  }
  
//  private JsonObject loadJson(String input) throws Exception {
//    return parser.parse(input).getAsJsonObject();
//  }
//  
  protected void parseElementProperties(JsonObject json, Element e) throws Exception {
    if (json != null && json.has("id"))
      e.setId(json.get("id").getAsString());
    if (!Utilities.noString(e.getId()))
      idMap.put(e.getId(), e);
    if (json.has("fhir_comments") && handleComments) {
      JsonArray array = json.getAsJsonArray("fhir_comments");
      for (int i = 0; i < array.size(); i++) {
        e.getFormatComments().add(array.get(i).getAsString());
      }
    }
  }
  
  protected XhtmlNode parseXhtml(String value) throws Exception {
    XhtmlParser prsr = new XhtmlParser();
    return prsr.parse(value, "div").getChildNodes().get(0);
  }
  
  protected DomainResource parseDomainResource(JsonObject json) throws Exception {
	  return (DomainResource) parseResource(json);
  }

	protected void writeNull(String name) throws Exception {
		json.nullValue();
	}
	protected void prop(String name, String value) throws Exception {
		if (name != null)
			json.name(name);
		json.value(value);
	}

  protected void prop(String name, java.lang.Boolean value) throws Exception {
    if (name != null)
      json.name(name);
    json.value(value);
  }

  protected void prop(String name, BigDecimal value) throws Exception {
    if (name != null)
      json.name(name);
    json.value(value);
  }

  protected void prop(String name, java.lang.Integer value) throws Exception {
    if (name != null)
      json.name(name);
    json.value(value);
  }

	protected void composeXhtml(String name, XhtmlNode html) throws Exception {
		if (!Utilities.noString(xhtmlMessage)) {
      prop(name, "<div>!-- "+xhtmlMessage+" --></div>");
		} else {
		XhtmlComposer comp = new XhtmlComposer();
		comp.setPretty(htmlPretty);
		  comp.setXmlOnly(true);
		prop(name, comp.compose(html));
		}
	}

	protected void open(String name) throws Exception {
		if (name != null) 
			json.name(name);
		json.beginObject();
	}

	protected void close() throws Exception {
		json.endObject();
	}

	protected void openArray(String name) throws Exception {
		if (name != null) 
			json.name(name);
		json.beginArray();
	}

	protected void closeArray() throws Exception {
		json.endArray();
	}

	protected void openObject(String name) throws Exception {
		if (name != null) 
			json.name(name);
		json.beginObject();
	}

	protected void closeObject() throws Exception {
		json.endObject();
	}

//  protected void composeBinary(String name, Binary element) throws Exception {
//    if (element != null) {
//      prop("resourceType", "Binary");
//      if (element.getXmlId() != null)
//        prop("id", element.getXmlId());
//      prop("contentType", element.getContentType());
//      prop("content", toString(element.getContent()));
//    }    
//    
//  }

  protected boolean anyHasExtras(List<? extends Element> list) {
	  for (Element e : list) {
	  	if (e.hasExtension() || !Utilities.noString(e.getId()))
	  		return true;
	  }
	  return false;
  }

	protected boolean makeComments(Element element) {
		return !handleComments && (style != OutputStyle.CANONICAL) && !element.getFormatComments().isEmpty();
	}
	
  protected void composeDomainResource(String name, DomainResource e) throws Exception {
	  openObject(name);
	  composeResource(e);
	  close();
	  
  }

  protected abstract void composeType(String prefix, Type type) throws Exception;


}
