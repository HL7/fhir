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


import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.instance.model.Binary;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.Resource.ResourceMetaComponent;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

import com.google.gson.stream.JsonWriter;

/**
 * General composer for JSON content. You instantiate an JsonComposer object, but you 
 * actually use compose defined on this class
 * 
 * The two classes are separated to keep generated and manually maintained code apart.
 */

public abstract class JsonComposerBase extends ComposerBase {

	protected JsonCreator json;
	private boolean htmlPretty;
  protected boolean canonical;

	/**
	 * Compose a resource to a stream, possibly using pretty presentation for a human reader (used in the spec, for example, but not normally in production)
	 */
	@Override
  public void compose(OutputStream stream, Resource resource, boolean pretty) throws Exception {
		checkCanBePretty(pretty);
		OutputStreamWriter osw = new OutputStreamWriter(stream, "UTF-8");
		JsonCreator writer;
		if (canonical)
      writer = new JsonCreatorCanonical(osw);
		else
		  writer = new JsonCreatorGson(osw);
    writer.setIndent(pretty ? "  ":"");
		writer.beginObject();
		compose(writer, resource);
		writer.endObject();
		writer.finish();
		osw.flush();
	}

	protected void checkCanBePretty(boolean pretty) throws Exception {
	  // ok. 	  
  }

	/**
	 * Compose a resource using a pre-existing JsonWriter
	 */
	public void compose(JsonCreator writer, Resource resource) throws Exception {
		json = writer;
		composeResource(resource);
	}

	/**
	 * No-Op for now
	 * 
	 */
	@Override
  public void compose(OutputStream writer, ResourceMetaComponent meta, boolean pretty) throws Exception {
		checkCanBePretty(pretty);
		throw new Error("Not done yet");
	}

  public void compose(OutputStream stream, Type type, boolean pretty) throws Exception {
  	checkCanBePretty(pretty);
    OutputStreamWriter osw = new OutputStreamWriter(stream, "UTF-8");
    if (canonical)
      json = new JsonCreatorCanonical(osw);
    else
      json = new JsonCreatorGson(osw);
    json.setIndent(pretty ? "  ":"");
    json.beginObject();
    composeTypeInner(type);
    json.endObject();
    json.finish();
    osw.flush();
  }
  	
  protected abstract void composeResource(Resource resource) throws Exception;
  protected abstract void composeTypeInner(Type type) throws Exception;

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
	  	if (e.hasExtensions() || !Utilities.noString(e.getXmlId()))
	  		return true;
	  }
	  return false;
  }

  public void setCanonical(boolean canonical) {
    this.canonical = canonical;
    
  }

	protected boolean makeComments(Element element) {
		return !canonical && !element.getXmlComments().isEmpty();
	}
	
  protected void composeDomainResource(String name, DomainResource e) throws Exception {
	  openObject(name);
	  composeResource(e);
	  close();
	  
  }

  protected abstract void composeType(String prefix, Type type) throws Exception;

  protected void composeExtensions(List<Extension> extensions) throws Exception {
  	Set<String> handled = new HashSet<String>();
  	for (Extension e : extensions) {
  		if (!handled.contains(e.getUrl())) {
  			handled.add(e.getUrl());
  			openArray(e.getUrl());
  			for (Extension ex : extensions) {
  				if (ex.getUrl().equals(e.getUrl())) {
  					openObject(null);
  					if (e.getValue() != null)
  					composeType("value", e.getValue());
  					if (ex.getExtension().size() > 0)
  					  composeExtensions(ex.getExtension());
  					close();
  				}
  			}
  			closeArray();
  		}
  		
  	}
  }

}
