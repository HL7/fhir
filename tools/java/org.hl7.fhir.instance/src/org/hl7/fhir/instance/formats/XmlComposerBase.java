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
import java.util.List;

import org.hl7.fhir.instance.model.Binary;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.DomainResource;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.Resource.ResourceMetaComponent;
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
  protected boolean canonical;
   
  
	public boolean isCanonical() {
    return canonical;
  }

  public void setCanonical(boolean canonical) {
    this.canonical = canonical;
  }

  /**
	 * Compose a resource to a stream, possibly using pretty presentation for a human reader (used in the spec, for example, but not normally in production)
	 */
	@Override
  public void compose(OutputStream stream, Resource resource, boolean pretty) throws Exception {
    if (canonical && pretty)
      throw new Exception("Do not use pretty = true if canonical = true");
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
    if (canonical && pretty)
      throw new Exception("Do not use pretty = true if canonical = true");
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
  public void compose(OutputStream stream, ResourceMetaComponent meta, boolean pretty) throws Exception {
    if (canonical && pretty)
      throw new Exception("Do not use pretty = true if canonical = true");
    throw new Error("not done yet");
//		XMLWriter writer = new XMLWriter(stream, "UTF-8");
//		writer.setPretty(pretty);
//		writer.start();
//		compose(writer, meta, pretty);
//		writer.close();
	}
	
	/**
	 * Compose a tag list to a stream, possibly using pretty presentation for a human reader, and maybe a different choice in the xhtml narrative (used in the spec in one place, but should not be used in production)
	 */
	public void compose(OutputStream stream, List<Coding> tags, boolean pretty, boolean htmlPretty) throws Exception {
    if (canonical && (pretty || htmlPretty))
      throw new Exception("Do not use pretty = true if canonical = true");
		XMLWriter writer = new XMLWriter(stream, "UTF-8");
		writer.setPretty(pretty);
		writer.start();
		compose(writer, tags, htmlPretty);
		writer.close();
	}


  /**
   * Compose a type to a stream (used in the spec, for example, but not normally in production)
   */
  public void compose(OutputStream stream, Type type) throws Exception {
    xml = new XMLWriter(stream, "UTF-8");
    xml.setPretty(!canonical);
    xml.start();
    xml.setDefaultNamespace(FHIR_NS);
    composeType("value", type);
    xml.close();
  }

	protected abstract void composeType(String prefix, Type type) throws Exception;

	public void compose(IXMLWriter writer, Resource resource, boolean htmlPretty) throws Exception {
		this.htmlPretty = htmlPretty;
		xml = writer;
		xml.setDefaultNamespace(FHIR_NS);
		composeResource(resource);
	}
	
	public void compose(IXMLWriter writer, List<Coding> tags, boolean htmlPretty) throws Exception {
		this.htmlPretty = htmlPretty;
		xml = writer;
		xml.setDefaultNamespace(FHIR_NS);

		xml.open(FHIR_NS, "taglist");
		for (Coding cat : tags) {
//			xml.attribute("scheme", cat.getScheme());
//			xml.attribute("term", cat.getTerm());
//			if (!Utilities.noString(cat.getDisplay()))
//				xml.attribute("label", cat.getDisplay());
//			xml.element("category", null);
		}
		xml.close(FHIR_NS, "taglist");
	}

	protected abstract void composeResource(Resource resource) throws Exception;

	protected void composeElementAttributes(Element element) throws Exception {
	  if (!canonical)
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
      comp.setXmlOnly(true);
      xml.namespace(XhtmlComposer.XHTML_NS, null);
      comp.compose(xml, html);
      xml.setPretty(oldPretty);
    }
	}


//	protected void composeBinary(String name, Binary element) throws Exception {
//		if (element != null) {
//			composeElementAttributes(element);
//			xml.attribute("contentType", element.getContentType());
//			xml.open(FHIR_NS, name);
//			xml.text(toString(element.getContent()));
//			xml.close(FHIR_NS, name);
//		}    
//	}

  @Override
  public void compose(OutputStream stream, Type type, boolean pretty) throws Exception {
    XMLWriter writer = new XMLWriter(stream, "UTF-8");
    writer.setPretty(pretty);
    writer.start();
    xml = writer;
    composeType("value", type);
    writer.close();
  }

  protected void composeDomainResource(String name, DomainResource res) throws Exception {
    xml.open(FHIR_NS, name);
    composeResource(res.getResourceType().toString(), res);
    xml.close(FHIR_NS, name);
}

	protected abstract void composeResource(String name, Resource res) throws Exception;
	
}