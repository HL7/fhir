package org.hl7.fhir.convertors;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class CDAUtilities {

	private Document doc;

	public CDAUtilities(InputStream stream) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();

		doc = builder.parse(stream);
		basicChecks();
	}

	private void basicChecks() throws Exception {
		Element e = doc.getDocumentElement(); 
		rule(e.getNamespaceURI().equals("urn:hl7-org:v3"), "CDA namespace must be ");
		rule(e.getNodeName().equals("ClinicalDocument"), "CDA root name must be ClinicalDocument");

	}

	private void rule(boolean test, String message) throws Exception {
		if (!test) 
			throw new Exception(message);

	}

	public Element getElement() {
		return doc.getDocumentElement();
	}

	public void checkTemplateId(Element e, String templateId) throws Exception {
		rule(hasTemplateId(e, templateId), "Template Id '"+templateId+"' not found");
		
	}

	public Element getChild(Element e, String[] names) throws Exception {
		for (String n : names) {
			if (e == null)
				return null;
			e = getChild(e, n);
		}
		return e;
	}

	public Element getChild(Element element, String name) throws Exception {
		
		Element e = null;
		Node n = element.getFirstChild();
		while (n != null) {
			if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals(name)) {
				if (e == null) {
					e = (Element) n;
				} else {
					throw new Exception("multiple matches found for "+name);
				}
			}
			n = n.getNextSibling();
		}
		return e;
	}

	public List<Element> getChildren(Element element, String name) {
		List<Element> l = new ArrayList<Element>();
		Node n = element.getFirstChild();
		while (n != null) {
			if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals(name)) {
				l.add((Element) n);
			}
			n = n.getNextSibling();
		}
		return l;
  }

	public Element getDescendent(Element element, String path) throws Exception {
		String[] p = path.split("\\/");
		return getDescendent(element, p);
  }

	public Element getDescendent(Element e, String[] path) throws Exception {
		for (String n : path) {
			if (e == null)
				return e;
			e = getChild(e, n);
		}
		return e;
  }

	public boolean hasTemplateId(Element e, String tid) {
		if (e == null)
			return false;
		boolean found = false;
		Node n = e.getFirstChild();
		while (n != null && !found) {
			if (n.getNodeType() == Node.ELEMENT_NODE && n.getNodeName().equals("templateId") && tid.equals(((Element) n).getAttribute("root")))
				found = true;
			n = n.getNextSibling();
		}
		return found;
  }

	public String getStatus(Element act) throws Exception {
		if (act == null)
			return null;
	  Element sc = getChild(act, "statusCode");
	  if (sc == null)
	    return null;
	  else
	  	return sc.getAttribute("code");
  }

	public String getSeverity(Element observation) throws Exception {
	  for (Element e : getChildren(observation,  "entryRelationship")) {
	  	Element child = getChild(e, "observation");
	  	if (hasTemplateId(child, "2.16.840.1.113883.10.20.22.4.8"))
	  		return getChild(child,  "value").getAttribute("code");
	  }
	  return null;
  }

}
