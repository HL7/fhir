
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


import java.io.OutputStream;
import java.util.List;

import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Binary;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xml.IXMLWriter;
import org.hl7.fhir.utilities.xml.XMLWriter;

/**
 * General composer for XML content. You instantiate an XmlComposer object, but you 
 * actually use compose defined on this class
 * 
 * The two classes are separated to keep generated and manually maintained code apart.
 */
public abstract class XmlComposerBase extends ComposerBase  {

	protected IXMLWriter xml;
	protected boolean htmlPretty;

	/**
	 * Compose a resource to a stream, possibly using pretty presentation for a human reader (used in the spec, for example, but not normally in production)
	 */
	@Override
  public void compose(OutputStream stream, Resource resource, boolean pretty) throws Exception {
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, resource, pretty);
		writer.close();
	}

	/**
	 * Compose a resource to a stream, possibly using pretty presentation for a human reader, and maybe a different choice in the xhtml narrative (used in the spec in one place, but should not be used in production)
	 */
	public void compose(OutputStream stream, Resource resource, boolean pretty, boolean htmlPretty) throws Exception {
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, resource, htmlPretty);
		writer.close();
	}

	/**
	 * Compose a bundle to a stream, possibly using pretty presentation for a human reader (used in the spec, for example, but not normally in production)
	 */
	@Override
  public void compose(OutputStream stream, AtomFeed feed, boolean pretty) throws Exception {
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, feed, pretty);
		writer.close();
	}

	/**
	 * Compose a bundle to a stream, possibly using pretty presentation for a human reader, and maybe a different choice in the xhtml narrative (used in the spec in one place, but should not be used in production)
	 */
	public void compose(OutputStream stream, AtomFeed feed, boolean pretty, boolean htmlPretty) throws Exception {
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, feed, htmlPretty);
		writer.close();
	}
	
	/**
	 * Compose a bundle to a stream, possibly using pretty presentation for a human reader (used in the spec, for example, but not normally in production)
	 */
	@Override
  public void compose(OutputStream stream, List<AtomCategory> tags, boolean pretty) throws Exception {
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, tags, pretty);
		writer.close();
	}
	
	/**
	 * Compose a tag list to a stream, possibly using pretty presentation for a human reader, and maybe a different choice in the xhtml narrative (used in the spec in one place, but should not be used in production)
	 */
	public void compose(OutputStream stream, List<AtomCategory> tags, boolean pretty, boolean htmlPretty) throws Exception {
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, tags, htmlPretty);
		writer.close();
	}


	public void compose(IXMLWriter writer, AtomFeed feed, boolean htmlPretty) throws Exception {
		this.htmlPretty = htmlPretty;
		xml = writer;
		xml.setDefaultNamespace(ATOM_NS);
		
	  xml.open(ATOM_NS, "feed");
    if (feed.getTitle() != null)
      xml.element(ATOM_NS, "title", feed.getTitle());
    if (feed.getId() != null)
      xml.element(ATOM_NS, "id", feed.getId());
    for (String n : feed.getLinks().keySet()) {
      xml.attribute("href", feed.getLinks().get(n));
      xml.attribute("rel", n);
      xml.element(ATOM_NS, "link", null);
    }
    if (feed.getTotalResults() != null) {
    	xml.setDefaultNamespace("http://a9.com/-/spec/opensearch/1.1/");
    	xml.element("totalResults", feed.getTotalResults().toString());
    	xml.setDefaultNamespace(ATOM_NS);
    }
    if (feed.getUpdated() != null)
      xml.element(ATOM_NS, "updated", feed.getUpdated().toString());
    if (feed.getAuthorName() != null || feed.getAuthorUri() != null) {
      xml.open(ATOM_NS, "author");
      if (feed.getAuthorName() != null)
        xml.element(ATOM_NS, "name", feed.getAuthorName());
      if (feed.getAuthorUri() != null)
        xml.element(ATOM_NS, "uri", feed.getAuthorUri());
      xml.close(ATOM_NS, "author");
    }
		for (AtomCategory cat : feed.getTags()) {
			xml.attribute("scheme", cat.getScheme());
			xml.attribute("term", cat.getTerm());
			if (!Utilities.noString(cat.getLabel()))
				xml.attribute("label", cat.getLabel());
	    xml.element("category", null);
		}
    
    for (AtomEntry<? extends Resource> e : feed.getEntryList())
      composeEntry(e);
    xml.close(ATOM_NS, "feed");

  
	}
	
  /**
   * Compose a type to a stream (used in the spec, for example, but not normally in production)
   */
  public void compose(OutputStream stream, Type type) throws Exception {
    xml = new XMLWriter(stream, "UTF-8");
    xml.setPretty(true);
    xml.start();
    xml.setDefaultNamespace(FHIR_NS);
    composeType("", type);
    xml.close();
  }

	protected abstract void composeType(String preix, Type type) throws Exception;

	private <T extends Resource>void composeEntry(AtomEntry<T> entry) throws Exception {
		AtomEntry<T> e = entry;
	  if (entry.isDeleted()) {
	    xml.setDefaultNamespace("http://purl.org/atompub/tombstones/1.0");
	    xml.attribute("ref", entry.getId());
	    xml.attribute("when", entry.getUpdated().toString());
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
	    if (e.getTitle() != null)
	      xml.element(ATOM_NS, "title", e.getTitle());
	    if (e.getId() != null)
	      xml.element(ATOM_NS, "id", e.getId());
	    for (String n : e.getLinks().keySet()) {
	      xml.attribute("href", e.getLinks().get(n));
	      xml.attribute("rel", n);
	      xml.element(ATOM_NS, "link", null);
	    }
	    if (e.getUpdated() != null)
	      xml.element(ATOM_NS, "updated", e.getUpdated().toString());

	    if (entry.getAuthorUri() != null  || entry.getAuthorName() != null) {
	      xml.open("author");
	      if (entry.getAuthorName() != null) 
	        xml.element("name", entry.getAuthorName());
	      if (entry.getAuthorUri() != null)
	        xml.element("uri", entry.getAuthorUri());
	      xml.close("author");
	    }
			for (AtomCategory cat : entry.getTags()) {
				xml.attribute("scheme", cat.getScheme());
				xml.attribute("term", cat.getTerm());
				if (!Utilities.noString(cat.getLabel()))
					xml.attribute("label", cat.getLabel());
		    xml.element("category", null);
			}
	    if (e.getPublished() != null)
	      xml.element(ATOM_NS, "published", e.getPublished().toString());
	    
	    xml.attribute("type", "text/xml");
	    xml.open(ATOM_NS, "content");
	    xml.setDefaultNamespace(FHIR_NS); 
	    if (entry.getResource() instanceof Binary)
	      composeBinary("Binary", (Binary) entry.getResource());
	    else
	      composeResource(entry.getResource());
	    xml.setDefaultNamespace(ATOM_NS);
	    xml.close(ATOM_NS, "content");
	    
	    if (e.getSummary() != null) {
	      xml.attribute("type", "xhtml");
	      xml.open(ATOM_NS, "summary");
	      xml.namespace(XhtmlComposer.XHTML_NS, null);
	      boolean oldPretty = xml.isPretty();
	      xml.setPretty(htmlPretty);
	      new XhtmlComposer().compose(xml, e.getSummary());
	      xml.setPretty(oldPretty);
	      xml.close(ATOM_NS, "summary");
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
	
	public void compose(IXMLWriter writer, List<AtomCategory> tags, boolean htmlPretty) throws Exception {
		this.htmlPretty = htmlPretty;
		xml = writer;
		xml.setDefaultNamespace(FHIR_NS);

		xml.open(FHIR_NS, "taglist");
		for (AtomCategory cat : tags) {
			xml.attribute("scheme", cat.getScheme());
			xml.attribute("term", cat.getTerm());
			if (!Utilities.noString(cat.getLabel()))
				xml.attribute("label", cat.getLabel());
			xml.element("category", null);
		}
		xml.close(FHIR_NS, "taglist");
	}

	protected abstract void composeResource(Resource resource) throws Exception;

	protected void composeElementAttributes(Element element) throws Exception {
    for (String comment : element.getXmlComments())
      xml.comment(comment, false);
		if (element.getXmlId() != null) 
			xml.attribute("id", element.getXmlId());
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
		xml.namespace(XhtmlComposer.XHTML_NS, null);
		comp.compose(xml, html);
		xml.setPretty(oldPretty);
    }
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
