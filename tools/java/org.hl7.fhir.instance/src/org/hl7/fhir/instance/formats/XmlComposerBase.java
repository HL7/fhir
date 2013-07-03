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
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.codec.binary.Base64;
import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.instance.model.Integer;
import org.hl7.fhir.instance.model.CarePlan.CarePlanStatusEnumFactory;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.*;
import org.hl7.fhir.utilities.xml.*;

public abstract class XmlComposerBase extends XmlBase {

	protected IXMLWriter xml;
	protected boolean htmlPretty;

	public void compose(OutputStream stream, Resource resource, boolean pretty) throws Exception {
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, resource, pretty);
		writer.close();
	}

	public void compose(OutputStream stream, Resource resource, boolean pretty, boolean htmlPretty) throws Exception {
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, resource, htmlPretty);
		writer.close();
	}

	public void compose(OutputStream stream, AtomFeed feed, boolean pretty) throws Exception {
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, feed, pretty);
		writer.close();
	}

	public void compose(OutputStream stream, AtomFeed feed, boolean pretty, boolean htmlPretty) throws Exception {
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, feed, htmlPretty);
		writer.close();
	}


	public void compose(IXMLWriter writer, AtomFeed feed, boolean htmlPretty) throws Exception {
		this.htmlPretty = htmlPretty;
		xml = writer;
		xml.setDefaultNamespace(FHIR_NS);
		
	  xml.open("feed");
	  xml.element("title", feed.getTitle());
	  xml.element("id", feed.getId());
//	  if (feed.isSearch) {
//	    xml.setDefaultNamespace("http://purl.org/atompub/tombstones/1.0");
//	    xml.element("totalResults", inttostr(feed.SearchTotal));
//	    xml.Namespace := ATOM_NS;
//	  }

	  for (String name : feed.getLinks().keySet()) {
	    xml.attribute("href", feed.getLinks().get(name));
	    xml.attribute("rel", name);
	    xml.element("link", null);
	  }
	  
	  xml.element(FHIR_NS, "updated", dateToXml(feed.getUpdated()));
	  if (feed.getAuthorUri() != null || feed.getAuthorName() != null) {
	    xml.open("author");
	    if (feed.getAuthorName() != null) 
	      xml.element("name", feed.getAuthorName());
	    if (feed.getAuthorUri() != null)
	      xml.element("uri", feed.getAuthorUri());
	    xml.close("author");
	  }
	  for (AtomEntry e : feed.getEntryList())
	    composeEntry(e);
	  xml.close("feed");		
	}
	
	private void composeEntry(AtomEntry entry) throws Exception {
	  if (entry.isDeleted()) {
	    xml.setDefaultNamespace("http://purl.org/atompub/tombstones/1.0");
	    xml.attribute("ref", entry.getId());
	    xml.attribute("when", dateToXml(entry.getUpdated()));
	    xml.open("deleted-entry");
	    for (String name : entry.getLinks().keySet()) {
	      xml.attribute("href", entry.getLinks().get(name));
	      xml.attribute("rel", name);
	      xml.element("link", null);
	    }
//	    if (entry.getoriginalId <> "") then
//	    begin
//	      xml.open("source");
//	      xml.element("id", entry.originalId);
//	      xml.close("source");
//	    end;
	    if (entry.getAuthorUri() != null || entry.getAuthorName() != null) {
	      xml.open("by");
	      if (entry.getAuthorName() != null)
	        xml.element("name", entry.getAuthorName());
	      if (entry.getAuthorUri() != null)
	        xml.element("uri", entry.getAuthorUri());
	      xml.close("by");
	    }
	    xml.close("deleted-entry");
	    xml.setDefaultNamespace(ATOM_NS);
	  } else {
	    xml.setDefaultNamespace(ATOM_NS);
	    xml.open("entry");
	    xml.element("title", entry.getTitle());
	    xml.element("id", entry.getId());
	    for (String name : entry.getLinks().keySet()) {
	      xml.attribute("href", entry.getLinks().get(name));
	      xml.attribute("rel", name);
	      xml.element("link", null);
	    }
	    xml.element("updated", dateToXml(entry.getUpdated()));
	    if (entry.getPublished() != null)
	      xml.element("published", dateToXml(entry.getPublished()));
//	    if. (entry.originalId <> "") then
//	    begin
//	      xml.open("source");
//	      xml.element("id", entry.originalId);
//	      xml.close("source");
//	    end;
	    if (entry.getAuthorUri() != null  || entry.getAuthorName() != null) {
	      xml.open("author");
	      if (entry.getAuthorName() != null) 
	        xml.element("name", entry.getAuthorName());
	      if (entry.getAuthorUri() != null)
	        xml.element("uri", entry.getAuthorUri());
	      xml.close("author");
	    }
			for (String uri : entry.getTags().keySet()) {
				xml.attribute("scheme", "http://hl7.org/fhir/tag");
				xml.attribute("term", uri);
				String label = entry.getTags().get(uri);
				if (!Utilities.noString(label))
					xml.attribute("label", label);
		    xml.element("category", null);
			}
	    
	    xml.attribute("type", "text/xml");
	    xml.open("content");
	    xml.setDefaultNamespace(FHIR_NS); 
	    if (entry.getResource() instanceof Binary)
	      composeBinary("Binary", (Binary) entry.getResource());
	    else
	      composeResource(entry.getResource());
	    xml.setDefaultNamespace(ATOM_NS);
	    xml.close("content");
	    if (entry.getSummary() != null) {
	      xml.attribute("type", "xhtml");
	      xml.open("summary");
	      xml.setDefaultNamespace(XhtmlComposer.XHTML_NS);
	      composeXhtml("summary", entry.getSummary());
	      xml.setDefaultNamespace(ATOM_NS);
	      xml.close("summary");
	    }
	    xml.close("entry");
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
		if (element.getXmlId() != null) 
			xml.attribute("id", element.getXmlId());
	}

	protected void composeTypeAttributes(Type type) throws Exception {
		composeElementAttributes(type);
	}

	protected void composeXhtml(String name, XhtmlNode html) throws Exception {
		XhtmlComposer comp = new XhtmlComposer();
		// name is also found in the html and should the same
		// ? check that
		boolean oldPretty = xml.isPretty();
		xml.setPretty(htmlPretty);
		xml.namespace(XhtmlComposer.XHTML_NS, null);
		comp.compose(xml, html);
		xml.setPretty(oldPretty);
	}


	protected void composeBinary(String name, Binary element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.attribute("contentType", element.getContentType());
			xml.open(FHIR_NS, name);
			xml.text(toString(element.getContent()));
			xml.close(FHIR_NS, name);
		}    

	}

}
