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
package org.hl7.fhir.utilities.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class XmlGenerator {


	private XMLWriter xml;
	
	public void generate(Element element, File file, String defaultNamespace, String elementName) throws Exception {
		
		OutputStream stream = new FileOutputStream(file);
		
		
		xml = new XMLWriter(stream, "UTF-8");
		xml.start();
		xml.setDefaultNamespace(defaultNamespace);

		xml.open(defaultNamespace, elementName);
		processContents(element);
		xml.close();
		xml.flush();
	}
	
	public void generate(Element element, File file) throws Exception {
		OutputStream stream = new FileOutputStream(file);
		
		xml = new XMLWriter(stream, "UTF-8");
		xml.start();
		xml.setDefaultNamespace(element.getNamespaceURI());
		processElement(element);
		xml.flush();
	}
	
	private void processContents(Element element) throws Exception {
		Node node = element.getFirstChild();
		while (node != null) {
			switch (node.getNodeType()) {
			case Node.ELEMENT_NODE:
				processElement((Element) node);
				break;
			case Node.TEXT_NODE:
				processText(node);
				break;
			case Node.COMMENT_NODE:
				processComment((Comment) node);
				break;
			default:
				throw new Exception("unhandled node type "+Integer.toString(node.getNodeType()));
			}
				
		    node = node.getNextSibling();
		}
	}
	
	private void processComment(Comment node) throws Exception {
		xml.comment(node.getNodeValue(), true);
	}

	private void processElement(Element element) throws Exception {
		if (!element.getNamespaceURI().equals(xml.getDefaultNamespace()))
			xml.setDefaultNamespace(element.getNamespaceURI());

		processAttributes(element);
		xml.open(element.getNamespaceURI(), element.getLocalName());
	
		processContents(element);
		
		xml.close();
	}

	private void processText(Node node) throws Exception {
		xml.text(node.getNodeValue());
	}

	private void processAttributes(Element element) throws Exception {
		NamedNodeMap nodes = element.getAttributes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node attr = nodes.item(i);
			if (attr.getNamespaceURI() != null) {
				//xml.namespace(attr.getNamespaceURI());
     			//xml.attribute(attr.getNamespaceURI(), attr.getLocalName(), attr.getNodeValue());
			} else if (attr.getLocalName() != null)
//        xml.attribute("xmlns", attr.getNodeValue());
//			else
     			xml.attribute(attr.getLocalName(), attr.getNodeValue());
		}
		
	}


}
