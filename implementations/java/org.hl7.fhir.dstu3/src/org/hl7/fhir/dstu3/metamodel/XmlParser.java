package org.hl7.fhir.dstu3.metamodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.NotImplementedException;
import org.hl7.fhir.dstu3.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.ElementDefinition.PropertyRepresentation;
import org.hl7.fhir.dstu3.model.Enumeration;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XMLWriter;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class XmlParser extends ParserBase {

  
  public XmlParser(IWorkerContext context, boolean check) {
    super(context, check);
  }

  public Element parse(InputStream stream) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(stream);
    org.w3c.dom.Element base = doc.getDocumentElement();
    String ns = base.getNamespaceURI();
    String name = base.getNodeName();
    StructureDefinition sd = getDefinition(ns, name);

    Element result = new Element(base.getNodeName(), new Property(sd.getSnapshot().getElement().get(0), sd));
    result.setType(base.getNodeName());
    parseChildren(base.getNodeName(), base, result);
    result.numberChildren();
    return result;
  }

  public Element parse(org.w3c.dom.Element base, String type) throws Exception {
    StructureDefinition sd = getDefinition(FormatUtilities.FHIR_NS, type);
    Element result = new Element(base.getNodeName(), new Property(sd.getSnapshot().getElement().get(0), sd));
    result.setType(base.getNodeName());
    parseChildren(base.getNodeName(), base, result);
    result.numberChildren();
    return result;
  }

  private void parseChildren(String path, org.w3c.dom.Element node, Element context) throws Exception {
  	reapComments(node, context);
    List<Property> properties = getChildProperties(context.getProperty(), context.getName(), XMLUtil.getXsiType(node));
    List<org.w3c.dom.Node> processed = new ArrayList<org.w3c.dom.Node>();
    for (Property property : properties) {
      if (isAttr(property)) {
      	Attr attr = node.getAttributeNode(property.getName());
        if (attr != null) {
      	processed.add(attr);
      	if (attr != null)
      		if (property.getName().equals("value"))
      			context.setValue(attr.getValue());
      		else
      	    context.getChildren().add(new Element(property.getName(), property, property.getType(), attr.getValue()));
        }
      } else if (property.isPrimitive() && "xhtml".equals(property.getType())) {
      	org.w3c.dom.Element div = XMLUtil.getNamedChild(node, property.getName());
      	processed.add(div);
      	XhtmlNode xhtml = new XhtmlParser().parseHtmlNode(div);
  	    context.getChildren().add(new Element("div", property, "xhtml", new XhtmlComposer().compose(xhtml)));
      } else {
        List<org.w3c.dom.Element> children = new ArrayList<org.w3c.dom.Element>();
      	XMLUtil.getNamedChildrenWithWildcard(node, property.getName(), children);
      	processed.addAll(children);
      	for (org.w3c.dom.Element child : children) {
    			Element n = new Element(child.getNodeName(), property);
    			context.getChildren().add(n);
    			if (property.isResource())
    				parseResource(path+"."+property.getName(), child, n);
    			else
    			  parseChildren(path+"."+property.getName(), child, n);
      	}
      }
    }
    if (check) {
      org.w3c.dom.Element child = XMLUtil.getFirstChild(node);
      while (child != null) {
        if (!processed.contains(child))
          throw new Exception("Unexpected element at "+path+"."+child.getNodeName());
        child = XMLUtil.getNextSibling(child);
      }
      NamedNodeMap am = node.getAttributes();
      for (int i = 0; i < am.getLength(); i++) {
        if (!processed.contains(am.item(i)) && !am.item(i).getNodeName().startsWith("xmlns"))
          throw new Exception("Unexpected element at "+path+".@"+am.item(i).getNodeName());
      }
    }
  }

  private void parseResource(String string, org.w3c.dom.Element container, Element parent) throws Exception {
  	org.w3c.dom.Element res = XMLUtil.getFirstChild(container);
    String name = res.getNodeName();
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+name);
    if (sd == null)
      throw new FHIRFormatError("Contained resource does not appear to be a FHIR resource (unknown name '"+res.getNodeName()+"')");
    Element result = new Element(res.getNodeName(), new Property(sd.getSnapshot().getElement().get(0), sd));
    result.setType(res.getNodeName());
    parseChildren(res.getNodeName(), res, result);
    parent.getChildren().add(result);
	}

	private void reapComments(org.w3c.dom.Element element, Element context) {
	  Node node = element.getPreviousSibling();
	  while (node != null && node.getNodeType() != Node.ELEMENT_NODE) {
	  	if (node.getNodeType() == Node.COMMENT_NODE)
	  		context.getComments().add(0, node.getTextContent());
	  	node = node.getPreviousSibling();
	  }
		node = element.getLastChild();
		while (node != null && node.getNodeType() != Node.ELEMENT_NODE) {
			node = node.getPreviousSibling();
		}
		while (node != null) {
			if (node.getNodeType() == Node.COMMENT_NODE)
				context.getComments().add(node.getTextContent());
			node = node.getNextSibling();
		}
	}

	private boolean isAttr(Property property) {
	  for (Enumeration<PropertyRepresentation> r : property.getDefinition().getRepresentation()) {
	    if (r.getValue() == PropertyRepresentation.XMLATTR) {
	    	return true;
	    }
	  }
	return false;
	}

  private boolean isText(Property property) {
    for (Enumeration<PropertyRepresentation> r : property.getDefinition().getRepresentation()) {
      if (r.getValue() == PropertyRepresentation.XMLTEXT) {
        return true;
      }
    }
    return false;
  }

	@Override
  public void compose(Element e, OutputStream stream, OutputStyle style, String base) throws Exception {
    XMLWriter xml = new XMLWriter(stream, "UTF-8");
    xml.setPretty(style == OutputStyle.PRETTY);
    xml.start();
    xml.setDefaultNamespace(e.getProperty().getNamespace());
    composeElement(xml, e, e.getType());
    xml.end();

  }

  private void composeElement(XMLWriter xml, Element element, String elementName) throws IOException {
    for (String s : element.getComments()) {
      xml.comment(s, true);
    }
    if (isText(element.getProperty())) {
      xml.enter(elementName);
      xml.text(element.getValue());
      xml.exit(elementName);      
    } else if (element.getProperty().isPrimitive() || (element.hasType() && ParserBase.isPrimitive(element.getType()))) {
      if (element.getType().equals("xhtml")) {
        xml.enter(elementName);
        xml.escapedText(element.getValue());
        xml.exit(elementName);
      } else if (isText(element.getProperty())) {
        xml.text(element.getValue());
      } else {
        xml.attribute("value", element.getValue());
        xml.element(elementName);
      }
    } else {
      for (Element child : element.getChildren()) {
        if (isAttr(child.getProperty()))
          xml.attribute(child.getName(), child.getValue());
      }
      xml.enter(elementName);
      for (Element child : element.getChildren()) {
        if (isText(child.getProperty()))
          xml.text(child.getValue());
        else if (!isAttr(child.getProperty()))
          composeElement(xml, child, child.getName());
      }
      xml.exit(elementName);
    }
  }

}
