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
package org.hl7.fhir.utilities.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XhtmlGeneratorAdorner.XhtmlGeneratorAdornerState;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class XhtmlGenerator {

	private static final int LINE_LIMIT = 85;
  private XhtmlGeneratorAdorner adorner;
	
  public XhtmlGenerator(XhtmlGeneratorAdorner adorner) {
    super();
    this.adorner = adorner;
  }

  public String generateInsert(Document doc, String name, String desc) throws Exception {
    StringWriter out = new StringWriter();
    
    out.write("<div class=\"example\">\r\n");
    out.write("<p>Example Instance \""+name+"\""+(desc == null ? "" : ": "+Utilities.escapeXml(desc))+"</p>\r\n"); 
    out.write("<pre class=\"xml\">\r\n");

    XhtmlGeneratorAdornerState state = null; // adorner == null ? new XhtmlGeneratorAdornerState("", "") : adorner.getState(this, null, null);
    for (int i = 0; i < doc.getChildNodes().getLength(); i++)
      writeNode(out, doc.getChildNodes().item(i), state, 0);
    
    out.write("</pre>\r\n");
    out.write("</div>\r\n");
    out.flush();
    return out.toString();
  }

  public void generate(Document doc, File xhtml, String name, String desc, int level, boolean adorn) throws Exception {
    adorn = true; // till the xml trick is working
    
    FileOutputStream outs = new FileOutputStream(xhtml);
    OutputStreamWriter out = new OutputStreamWriter(outs);
    
    out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n");
    out.write("<head>\r\n");
    out.write(" <title>Example Instance for "+name+"</title>\r\n");
    out.write(" <link rel=\"stylesheet\" href=\"");
    for (int i = 0; i < level; i++)
      out.write("../");
    out.write("fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n");
    if (!adorn) {
      out.write(" <link rel=\"stylesheet\" href=\"");
      for (int i = 0; i < level; i++)
        out.write("../");
      out.write("./assets/css/xml.css\" type=\"text/css\" media=\"screen\"/>\r\n");
      
      out.write(" <script src=\"");
      for (int i = 0; i < level; i++)
        out.write("../");
      out.write("./assets/js/xml.js\"></script>\r\n");
      out.write("<script>\r\n  hljs.tabReplace = '  '; // 2 spaces\r\n  hljs.initHighlightingOnLoad();\r\n</script>\r\n");      
    }
    out.write("</head>\r\n");
    
    out.write("<body>\r\n");
    out.write("<p>&nbsp;</p>\r\n"); 
    out.write("<div class=\"example\">\r\n");
    out.write("<p>"+Utilities.escapeXml(desc)+"</p>\r\n"); 
    out.write("<p><a href=\""+xhtml.getName().substring(0, xhtml.getName().length()-5)+"\">Raw XML</a> (<a href=\""+canonicalName(xhtml.getName().substring(0, xhtml.getName().length()-5))+"\">Canonical</a>)</p>\r\n");
    if (adorn) {
      out.write("<pre class=\"xml\">\r\n");

      XhtmlGeneratorAdornerState state = null; // adorner == null ? new XhtmlGeneratorAdornerState("", "") : adorner.getState(this, null, null);
      for (int i = 0; i < doc.getChildNodes().getLength(); i++)
        writeNode(out, doc.getChildNodes().item(i), state, level);
      out.write("</pre>\r\n");
    } else {
      out.write("<code class=\"xml\">\r\n");
      for (int i = 0; i < doc.getChildNodes().getLength(); i++)
        writeNodePlain(out, doc.getChildNodes().item(i), level);
      
      out.write("</code>\r\n");      
    }
    out.write("</div>\r\n");
    out.write("</body>\r\n");
    out.write("</html>\r\n");
    out.flush();
    outs.close();
  }

  private String canonicalName(String name) {
    int i = name.lastIndexOf(".");
    if (i == -1)
      throw new Error("unable to get canonical name for "+name);
    return name.substring(0, i)+".canonical"+name.substring(i);
  }

  public void generate(Document doc, OutputStream xhtml, String name, String desc, int level, boolean adorn, String filename) throws Exception {
    adorn = true; // till the xml trick is working
    
		OutputStreamWriter out = new OutputStreamWriter(xhtml);
		
    out.write("<div class=\"example\">\r\n");
    out.write("<p>"+Utilities.escapeXml(desc)+"</p>\r\n"); 
    out.write("<p><a href=\""+filename.substring(0, filename.length()-5)+"\">Raw XML</a></p>\r\n");
    if (adorn) {
      out.write("<pre class=\"xml\">\r\n");

      XhtmlGeneratorAdornerState state = null; // adorner == null ? new XhtmlGeneratorAdornerState("", "") : adorner.getState(this, null, null);
	  	for (int i = 0; i < doc.getChildNodes().getLength(); i++)
	  		writeNode(out, doc.getChildNodes().item(i), state, level);
      out.write("</pre>\r\n");
    } else {
      out.write("<code class=\"xml\">\r\n");
      for (int i = 0; i < doc.getChildNodes().getLength(); i++)
        writeNodePlain(out, doc.getChildNodes().item(i), level);
      
      out.write("</code>\r\n");      
    }
    out.write("</div>\r\n");
		out.flush();
	}

  private void writeNodePlain(Writer out, Node node, int level) throws Exception {
    if (node.getNodeType() == Node.ELEMENT_NODE)
      writeElementPlain(out, (Element) node, level);
    else if (node.getNodeType() == Node.TEXT_NODE)
      writeTextPlain(out, (Text) node, level);
    else if (node.getNodeType() == Node.COMMENT_NODE)
      writeCommentPlain(out, (Comment) node, level);
    else if (node.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE)
      writeProcessingInstructionPlain(out, (ProcessingInstruction) node);
    else if (node.getNodeType() != Node.ATTRIBUTE_NODE)
      throw new Exception("Unhandled node type");
  }

	private void writeNode(Writer out, Node node, XhtmlGeneratorAdornerState state, int level) throws Exception {
		if (node.getNodeType() == Node.ELEMENT_NODE)
			writeElement(out, (Element) node, state, level);
		else if (node.getNodeType() == Node.TEXT_NODE)
			writeText(out, (Text) node, level);
		else if (node.getNodeType() == Node.COMMENT_NODE)
			writeComment(out, (Comment) node, level);
		else if (node.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE)
			writeProcessingInstruction(out, (ProcessingInstruction) node);
		else if (node.getNodeType() != Node.ATTRIBUTE_NODE)
			throw new Exception("Unhandled node type");
	}

  private void writeProcessingInstruction(Writer out, ProcessingInstruction node) {
    
    
  }

  private void writeProcessingInstructionPlain(Writer out, ProcessingInstruction node) {
    
    
  }

  private void writeComment(Writer out, Comment node, int level) throws DOMException, IOException {
    out.write("<span class=\"xmlcomment\">&lt;!-- "+escapeHtml(Utilities.escapeXml(node.getTextContent()), level)+" --&gt;</span>");
  }

  private void writeCommentPlain(Writer out, Comment node, int level) throws DOMException, IOException {
    out.write("<!-- "+Utilities.escapeXml(node.getTextContent())+" -->");
  }

  private void writeText(Writer out, Text node, int level) throws DOMException, IOException {
    out.write("<b>"+escapeHtml(Utilities.escapeXml(node.getTextContent()), level)+"</b>");
  }

  private void writeTextPlain(Writer out, Text node, int level) throws DOMException, IOException {
    out.write(Utilities.escapeXml(node.getTextContent()));
  }

  private void writeElement(Writer out, Element node, XhtmlGeneratorAdornerState state, int level) throws Exception {
    out.write("<span class=\"xmltag\">&lt;"+node.getNodeName()+"</span>");
    if (node.hasAttributes()) {
      out.write("<span class=\"xmlattr\">");
      XhtmlGeneratorAdornerState newstate = adorner == null ? new XhtmlGeneratorAdornerState("", "") : adorner.getState(this, state, node);
      for (int i = 0; i < node.getAttributes().getLength(); i++) {
        if (adorner != null) {
          XhtmlGeneratorAdornerState attrState = adorner.getAttributeMarkup(this, newstate, node, node.getAttributes().item(i).getNodeName(), node.getAttributes().item(i).getTextContent());
          out.write(" "+node.getAttributes().item(i).getNodeName()+"=\"<span class=\"xmlattrvalue\">"+attrState.getPrefix()+escapeHtml(Utilities.escapeXml(node.getAttributes().item(i).getTextContent()), level)+attrState.getSuffix()+"</span>\"");
        } else
          out.write(" "+node.getAttributes().item(i).getNodeName()+"=\"<span class=\"xmlattrvalue\">"+escapeHtml(Utilities.escapeXml(node.getAttributes().item(i).getTextContent()), level)+"</span>\"");
      }
      out.write("</span>");

    }
    if (node.hasChildNodes()) {
      out.write("<span class=\"xmltag\">&gt;</span>");
      XhtmlGeneratorAdornerState newstate = adorner == null ? new XhtmlGeneratorAdornerState("", "") : adorner.getState(this, state, node);
      if (newstate.isSuppress())
        out.write("<span class=\"xmlcomment\">&lt;!-- "+escapeHtml(newstate.getSupressionMessage(), level)+" --&gt;</span>");
      else {
        out.write(newstate.getPrefix());
        for (int i = 0; i < node.getChildNodes().getLength(); i++)
          writeNode(out, node.getChildNodes().item(i), newstate, level+2);

        out.write(newstate.getSuffix());
      }
      out.write("<span class=\"xmltag\">&lt;/"+node.getNodeName()+"&gt;</span>");
    }
    else 
      out.write("<span class=\"xmltag\">/&gt;</span>");
	}
	
  private void writeElementPlain(Writer out, Element node, int level) throws Exception {
    out.write("<"+node.getNodeName());
    if (node.hasAttributes()) {
      for (int i = 0; i < node.getAttributes().getLength(); i++) {
        out.write(" "+node.getAttributes().item(i).getNodeName()+"=\""+Utilities.escapeXml(node.getAttributes().item(i).getTextContent())+"\"");
      }
    }
    if (node.hasChildNodes()) {
      out.write(">");
      for (int i = 0; i < node.getChildNodes().getLength(); i++)
        writeNodePlain(out, node.getChildNodes().item(i), level+2);
      out.write("</"+node.getNodeName()+">");
    }
    else 
      out.write("/>");
  }
  
	private String escapeHtml(String doco, int indent) {
		if (doco == null)
			return "";
		
		int i = 0;
		StringBuilder b = new StringBuilder();
		for (char c : doco.toCharArray()) {
		  i++;
      if (c == '\r' || c == '\n')
        i = 0;
		  if ((i > LINE_LIMIT && c == ' ') || (i > LINE_LIMIT + 15)) {
		    b.append("\r\n");
		    for (int j = 0; j < indent; j++)
	        b.append(" ");
		    i = 0;		    
		  }
		  if (c == '<')
			  b.append("&lt;");
		  else if (c == '>')
			  b.append("&gt;");
		  else if (c == '&')
			  b.append("&amp;");
		  else if (c == '\t')
			  b.append("  ");
		  else if (c == '\'')
			  b.append("&apos;");
		  else if (c == '"')
			  b.append("&quot;");
		  else 
			  b.append(c);
		}		
		return b.toString();
	}	
}
