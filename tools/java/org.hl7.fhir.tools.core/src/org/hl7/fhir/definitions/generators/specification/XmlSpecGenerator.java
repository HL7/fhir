package org.hl7.fhir.definitions.generators.specification;

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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.ExtensionDefinition;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.Utilities;

public class XmlSpecGenerator extends OutputStreamWriter {

	private String defPage;
	private String dtRoot;
	private Definitions definitions;
  private PageProcessor page;

	public XmlSpecGenerator(OutputStream out, String defPage, String dtRoot, PageProcessor page) throws UnsupportedEncodingException {
		super(out, "UTF-8");
		this.defPage = defPage;
		this.dtRoot = dtRoot == null ? "" : dtRoot;
		this.definitions = page.getDefinitions();
		this.page = page;
	}

	public void generate(ElementDefn root, boolean isAbstract) throws Exception {
		write("<pre class=\"spec\">\r\n");

		generateInner(root, true, isAbstract);

		write("</pre>\r\n");
		flush();
		close();
	}

  public void generate(ExtensionDefinition ed) throws Exception {
    write("<pre class=\"spec\">\r\n");

    generateInner(ed);

    write("</pre>\r\n");
    flush();
    close();
  }

  private void generateInner(ExtensionDefinition ed) throws IOException, Exception {
    ElementDefinition root = ed.getElement().get(0);
    write("&lt;!-- "+Utilities.escapeXml(ed.getName())+" -->");
    write("<span style=\"float: right\"><a title=\"Documentation for this format\" href=\"xml.html\"><img src=\"help.png\" alt=\"doco\"/></a></span>\r\n");
    String rn = ed.getElement().get(0).getIsModifier() ? "modifierExtension" : "extension";

    write("\r\n&lt;");
    if (defPage == null)
      write("<span title=\"" + Utilities.escapeXml(root.getFormal())
          + "\"><b>");
    else
      write("<a href=\"" + (defPage + "#" + root.getName()).replace("[", "_").replace("]", "_") + "\" title=\""
          + Utilities.escapeXml(root.getFormal())
          + "\" class=\"dict\"><b>");
    write(rn);
    if ((defPage == null))
      write("</b></span>");
    else
      write("</b></a>");

    write(" xmlns=\"http://hl7.org/fhir\"\r\n    ");
    generateExtensionAttribute(ed);
    write(" &gt;\r\n");

    if (ed.getElement().size() == 1) {
      generateCoreElem(ed.getElement(), ed.getElement().get(0), 1, "Extension", true);
    } else {
      List<ElementDefinition> children = getChildren(ed.getElement(), ed.getElement().get(0));
      for (ElementDefinition child : children)
        generateCoreElem(ed.getElement(), child, 1, rn, false);
    }

    write("&lt;/");
    write(rn);
    write("&gt;\r\n");
  }
  
	private void generateInner(ElementDefn root, boolean resource, boolean isAbstract) throws IOException, Exception {
		String rn;
		if (root.getName().equals("Extension")) 
		  rn = "extension|modifierExtension";
		else if (root.getName().equals("Meta")) 
      rn = "meta";
		else if (root.getTypes().size() > 0 && (root.getTypes().get(0).getName().equals("Type")
				|| (root.getTypes().get(0).getName().equals("Structure"))) || isAbstract)
			rn = "[name]";
		else 
			rn = root.getName();

		write("&lt;");
		if (defPage == null)
			write("<span title=\"" + Utilities.escapeXml(root.getDefinition())
					+ "\"><b>");
		else
			write("<a href=\"" + (defPage + "#" + root.getName()).replace("[", "_").replace("]", "_") + "\" title=\""
					+ Utilities.escapeXml(root.getDefinition())
					+ "\" class=\"dict\"><b>");
		write(rn);
		if ((defPage == null))
			write("</b></span>");
		else
			write("</b></a>");

		boolean hasXmlLang = false;
    for (ElementDefn elem : root.getElements()) {
      hasXmlLang = hasXmlLang || elem.typeCode().equals("xml:lang");
    }
		if (hasXmlLang)
		  write(" xml:lang?");
		if (resource)
	    write(" xmlns=\"http://hl7.org/fhir\"");
		else
	    write(" xmlns=\"http://hl7.org/fhir\"");
		for (ElementDefn elem : root.getElements()) {
		  if (elem.isXmlAttribute()) {
		    generateAttribute(elem);
		  }
		}
		if (resource) {
		    write("&gt; <span style=\"float: right\"><a title=\"Documentation for this format\" href=\"xml.html\"><img src=\"help.png\" alt=\"doco\"/></a></span>\r\n");
		} else 
		  write("&gt;\r\n");
    if (rn.equals(root.getName()) && resource) {
      if (!Utilities.noString(root.typeCode())) {
        write(" &lt;!-- from <a href=\"resource.html\">Resource</a>: <a href=\"resource.html#id\">id</a>, <a href=\"resource.html#meta\">meta</a>, <a href=\"resource.html#implicitRules\">implicitRules</a>, and <a href=\"resource.html#language\">language</a> -->\r\n");
        if (root.typeCode().equals("DomainResource"))
          write(" &lt;!-- from <a href=\"domainresource.html\">DomainResource</a>: <a href=\"narrative.html#Narrative\">text</a>, <a href=\"references.html#contained\">contained</a>, <a href=\"extensibility.html\">extension</a>, and <a href=\"extensibility.html#modifierExtension\">modifierExtension</a> -->\r\n");
      }
    } else {
      write(" &lt;!-- from Element: <a href=\"extensibility.html\">extension</a> -->\r\n");
    }
		for (ElementDefn elem : root.getElements()) {
		  if (!elem.typeCode().equals("xml:lang") && !elem.isXmlAttribute())
		    generateCoreElem(elem, 1, rn, root.getName(), rn.equals(root.getName()) && resource);
		}

		write("&lt;/");
		write(rn);
		write("&gt;\r\n");
	}

	private void generateExtensionAttribute(ExtensionDefinition ed) throws Exception {
    write(" url=\"");
    write("<span style=\"color: navy\">" + ed.getUrl()+"</span>");
    write("\"");
  }

	private void generateAttribute(ElementDefn elem) throws Exception {
    write(" "+elem.getName()+"=\"");

    if (Utilities.isURL(elem.getShortDefn()))
      write("<span style=\"color: navy\"><a href=\""+Utilities.escapeXml(elem.getShortDefn())+"\">" + Utilities.escapeXml(elem.getShortDefn())+"</a></span>");
    else
      write("<span style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn())+"</span>");
    String t = elem.typeCode();
    write(" (<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + definitions.getSrcFile(t)+ ".html#" + t) + "\">" + t + "</a></span>)\"");
  }

//  public void generate(ProfileDefn profile, String root) throws Exception {
//		write("<pre class=\"spec\"> <span style=\"float: right\"><a title=\"Documentation for this format\" href=\"xml.html\"><img src=\"help.png\" alt=\"doco\"/></a></span>\r\n");
//
//		ResourceDefn r = profile.getResource();
//		write("<span style=\"color: Gray\">&lt;!--<a name=\"" + r.getRoot().getProfileName() + "\"> </a><span style=\"color: Darkviolet\">"+Utilities.escapeXml(r.getRoot().getProfileName())+"</span> --&gt;</span>\r\n");
//		generateInner(r.getRoot(), false, false);
//		write("\r\n");
//
////		if (profile.getExtensions().size() > 0) {
////			write("<span style=\" color: Gray\">&lt;!-- <span style=\"color: Darkviolet\">Extensions</span> --&gt;</span>\r\n");
////			for (ExtensionDefn ex : profile.getExtensions()) {
////				generateExtension(ex, profile, definitions, root, 0);
////        write("\r\n");
////			}
////		}
//
//		write("</pre>\r\n");
//		flush();
//		close();
//	}

/*	private void generateExtension(ExtensionDefn ex, ProfileDefn profile, Definitions definitions, String root, int indent) throws Exception {
	  String n = (indent == 0) ? "extension" : "extension"; // in case contained extensions have a different name
	  String ind = Utilities.padLeft("", ' ', indent);  
    if (indent == 0) {
      write(ind+"<a name=\""+ex.getCode()+"\">&lt;!--</a> ");
      writeCardinality(ex.getDefinition());
      write("  ");
      write("<span style=\"color: navy\">" + Utilities.escapeXml("Context: "+ex.getType().toString()+" = "+ex.getContext()) + "</span>");
      write(" -->\r\n");
    }

    if (ex.getDefinition().isModifier1())
	    write(ind+"&lt;<span style=\"text-decoration: underline\" title=\"" + Utilities.escapeXml(ex.getDefinition().getEnhancedDefinition()) + "\"><b>"+n+"</b></span>");
	  else
	    write(ind+"&lt;<span title=\"" + Utilities.escapeXml(ex.getDefinition().getDefinition()) + "\"><b>"+n+"</b></span>");
    write(" <b>url</b>=\"<span style=\"color: maroon\">"+ (indent == 0 ? root  : "") + "#"+ ex.getCode() + "</span>\"&gt;");
    if (indent != 0) {
      write(ind+"<a name=\""+ex.getCode()+"\"> </a>&lt;!-- ");
      writeCardinality(ex.getDefinition());
      write(" -->");
    }
    write("\r\n");

//		write(" &lt;<b>definition</b>><span style=\" color: Gray\">&lt;!-- </span> <span style=\"color: brown;\"><b>1..1</b></span> <span style=\"color: darkgreen;\"><a href=\"datatypes.html#uri\">uri</a></span> <span style=\"color: navy\">where registered</span> <span style=\" color: Gray\">--&gt;</span>&lt;/definition>\r\n");
//		write(" &lt;<b>ref</b>&gt; <span style=\"color: navy\"><span style=\"color: darkgreen;\"><a href=\"references.html#idref\">Ref</a></span> to a "
//				+ ex.getContext()
//				+ " ("
//				+ ex.getType().toString()
    //				+ ")</span>\">  \r\n");
    //		if (ex.getDefinition().isMustUnderstand())
    //			write(" &lt;<b>mustUnderstand</b>>true&lt;/mustUnderstand>\r\n");
    if (ex.getChildren().isEmpty()) {
      if (ex.getDefinition().getTypes().size() == 0 ) {
        write(" <span title=\"" + Utilities.escapeXml(ex.getDefinition().getDefinition()) + "\">");
        write("<span style=\" color: Gray\">&lt;!-- </span>");
        write("<span style=\"color: navy\">"+getExtensionTargetList(ex, profile)+"</span> ");
        write("<span style=\" color: Gray\">--> </span>");
        write("</span>\r\n");
      } else {
        String t = null; 

        String vn = "value[x]";
        if (ex.getDefinition().getTypes().size() == 1) {
          t = ex.getDefinition().typeCode();
          if (t.startsWith("Reference("))
            t = "Reference";
          vn = "value" + upFirst(t);
        }

        write(ind+"  &lt;<span title=\"" + Utilities.escapeXml(ex.getDefinition().getDefinition()) + "\"><b>" + vn + "</b></span>");
        if (ex.getDefinition().getTypes().size() == 1
            && definitions.hasElementDefn(t)) {
          write("&gt;");
          write("<span style=\" color: Gray\">&lt;!-- </span>");
          writeTypeLinks(ex.getDefinition(), 0);
          if (Utilities.isURL(ex.getDefinition().getShortDefn()))
            write("<span style=\"color: navy\"><a href=\""+Utilities.escapeXml(ex.getDefinition().getShortDefn())+"\">" + Utilities.escapeXml(ex.getDefinition().getShortDefn())+"</a></span>");
          else
            write(" <span style=\"color: navy\">"+Utilities.escapeXml(ex.getDefinition().getShortDefn())+"</span>");
          write(" <span style=\" color: Gray\">--&gt; </span>&lt;/" + vn + ">\r\n");
        } else if (ex.getDefinition().getTypes().size() == 1) {
          write(" value=\"[<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(t, false)+ ".html#" + t) + "\">" + t+ "</a></span>]\"/>");
          write("<span style=\" color: Gray\">&lt;!-- </span>");
          write("<span style=\"color: navy\">"
              + Utilities.escapeXml(ex.getDefinition().getShortDefn())
              + "</span>");
          write("<span style=\" color: Gray\"> --></span>\r\n");
        } else {
          write("&gt;");
          write("[todo: type and short defn]&lt;/" + vn + ">\r\n");
        }
      }
    }
		for (ExtensionDefn child : ex.getChildren())
		  generateExtension(child, profile, definitions, root, indent+2);
		write(ind+"&lt;/"+n+">\r\n");
	}
  
	private String getExtensionTargetList(ExtensionDefn ex, ProfileDefn profile) {
	  StringBuilder s = new StringBuilder();
    for (ExtensionDefn t : profile.getExtensions()) {
      if (t.getType() == ContextType.Extension && t.getContext().equals(profile.metadata("extension.uri")+"#"+ex.getCode()))
        s.append(", #"+t.getCode());
    }
    if (s.length() > 0)
      return "Extensions: "+s.substring(2);
    else
      return "Other extensions as defined";
  }
*/

  private String upFirst(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/*
	 */

	// private void generateElem(ElementDefn elem, int indent, String rootName,
	// String pathName) throws Exception {
	// // if ((!elem.unbounded() && 1 == elem.getMaxCardinality()) ||
	// elem.isNolist() || Config.SUPPRESS_WRAPPER_ELEMENTS)
	// generateCoreElem(elem, indent, rootName, pathName);
	// // else
	// // generateWrapperElem(elem, indent, rootName, pathName);
	//
	// }

	// private void generateWrapperElem(ElementDefn elem, int indent, String
	// rootName, String pathName) throws Exception {
	// for (int i= 0; i < indent; i++)
	// {
	// write(" ");
	// }
	// write("&lt;"+Utilities.pluralizeMe(elem.getName()));
	// write(" <span style=\"color: darkgreen\">type=\"list\"</span>&gt;  <span style=\"color: Gray\">&lt;!-- "+elem.textForCardinality()+" --&gt;</span>\r\n");
	//
	// generateCoreElem(elem, indent+1, rootName, pathName);
	//
	// for (int i= 0; i < indent; i++)
	// {
	// write(" ");
	// }
	// write("&lt;/"+Utilities.pluralizeMe(elem.getName())+"&gt;\r\n");
	// }

	private void generateCoreElem(ElementDefn elem, int indent,	String rootName, String pathName, boolean backbone) throws Exception {
		// if (elem.getConformance() == ElementDefn.Conformance.Prohibited)
		// return;

		boolean listed = false;
		boolean doneType = false;
		int width = 0;
    // If this is an unrolled element, show its profile name
    if (elem.getProfileName() != null
        && !elem.getProfileName().equals("")) {
      for (int i = 0; i < indent; i++)
        write(" ");
      write("<span style=\"color: Gray\">&lt;!--</span><span style=\"color: blue\">\"" + elem.getProfileName() + "\":</span>  <span style=\"color: Gray\"> --&gt;</span>\r\n");
    }

		for (int i = 0; i < indent; i++) {
			write(" ");
		}
		if (elem.isInherited())
			write("<i class=\"inherited\">");

		String en = elem.getName();

		if (en.contains("[x]") && elem.getTypes().size() == 1
				&& !elem.getTypes().get(0).isWildcardType())
			en = en.replace("[x]", elem.typeCode());

		if (defPage == null) {
			if (elem.isModifier() || elem.getMustSupport())
  		  write("&lt;<span style=\"text-decoration: underline\" title=\"" + Utilities.escapeXml(elem.getEnhancedDefinition())	+ "\">");
			else
				write("&lt;<span title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\">");
		} else if (elem.isModifier() || elem.getMustSupport()) 
      write("&lt;<a href=\"" + (defPage + "#" + pathName + "." + en).replace("[", "_").replace("]", "_")+ "\" title=\"" + Utilities .escapeXml(elem.getEnhancedDefinition()) 
            + "\" class=\"dict\"><span style=\"text-decoration: underline\">");
		else
			write("&lt;<a href=\"" + (defPage + "#" + pathName + "." + en).replace("[", "_").replace("]", "_") + "\" title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\" class=\"dict\">");

		// element contains xhtml
		if (!elem.getTypes().isEmpty() && elem.getTypes().get(0).isXhtml()) {
			write("<b title=\""
					+ Utilities.escapeXml(elem.getDefinition())
					+ "\">div</b>" +  ((elem.isModifier() || elem.getMustSupport()) ? "</span>" : "") 
					+ (defPage == null ? "</span>" : "</a>") 
					+ " xmlns=\"http://www.w3.org/1999/xhtml\"&gt; <span style=\"color: Gray\">&lt;!--</span> <span style=\"color: navy\">"
					+ Utilities.escapeXml(elem.getShortDefn())
					+ "</span><span style=\"color: Gray\">&lt; --&gt;</span> &lt;/div&gt;\r\n");
		}
		// element has a constraint which fixes its value
		else if (elem.hasFixed()) {
			if (defPage == null) {
				write(en+"</span>&gt;");
			} else if (elem.isModifier() || elem.isMustSupport())
				write(en + "</span></a>&gt;");
			else
				write(en + "</a>&gt;");

//			if (elem.typeCode().equals("CodeableConcept"))
//				write(renderCodeableConcept(indent, elem.getValue()) + "&lt;/" + en + "&gt;\r\n");
//			else if (elem.typeCode().equals("Quantity"))
//				write(renderQuantity(indent, elem.getValue()) + "&lt;" + en + "/&gt;\r\n");
//			else
//				write(elem.getValue() + "&lt;" + en + "/&gt;\r\n");
       write(renderType(indent, elem.getFixed()) + "&lt;" + en + "/&gt;\r\n");
		} else {
			write("<b>" + en);
			if (defPage == null) {
				write("</b></span>");
			} else if (elem.isModifier() || elem.getMustSupport())
				write("</b></span></a>");
			else
				write("</b></a>");
			if (elem.getTypes().size() == 1 && (definitions.getPrimitives().containsKey(elem.typeCode()))) {
			  doneType = true;
			  TypeRef t = elem.getTypes().get(0);
  			write(" value=\"[<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + definitions.getSrcFile(t.getName())+ ".html#" + t.getName()).replace("[", "_").replace("]", "_") + "\">" + t.getName()+ "</a></span>]\"/");
			}
			write("&gt;");

			boolean sharedDT = definitions.dataTypeIsSharedInfo(elem.typeCode());

			// For simple elements without nested content, render the
			// optionality etc. within a comment
			if (elem.getElements().isEmpty() && !sharedDT)
				write("<span style=\"color: Gray\">&lt;!--</span>");
			// if (elem.getConformance() != ElementDefn.Conformance.Unstated)
			// {
			// write(" ");
			// write("<a href=\"xml.html#Control\" class=\"cf\">" +
			// elem.getConformance().code() + "</a>");
			// }

			if (elem.usesCompositeType()) {
				// Contents of element are defined elsewhere in the same
				// resource
				writeCardinality(elem);

				if (elem.usesCompositeType()) {
					write(" <span style=\"color: darkgreen\">");
					write("Content as for " + elem.typeCode().substring(1)
							+ "</span>");
				}
				listed = true;
			} else 
			  if (!elem.getTypes().isEmpty()
					&& !(elem.getTypes().size() == 1 && elem.getTypes().get(0)
							.isWildcardType()) && !sharedDT) {
				writeCardinality(elem);
				listed = true;
				if (!doneType) {
				  width = writeTypeLinks(elem, indent);
				}
			} else if (elem.getName().equals("extension")) {
				write(" <a href=\"extensibility.html\"><span style=\"color: navy\">See Extensions</span></a> ");
			} else if (elem.getTypes().size() == 1
					&& elem.getTypes().get(0).isWildcardType()) {
				writeCardinality(elem);
		    write(" <span style=\"color: darkgreen\">");
        write("<a href=\"datatypes.html#open\">*</a>");     
		    write("</span>");
				listed = true;
			}

//			if (!Utilities.noString(elem.getProfile())) {
//	      write(" <a href=\""+elem.getProfile()+"\"><span style=\"color: DarkViolet\">Profile: \""+elem.getProfile().substring(1)+"\"</span></a>");		  
//			}
			write(" ");
			if (elem.getElements().isEmpty() && !sharedDT) {
				if ("See Extensions".equals(elem.getShortDefn())) {
					write(" <a href=\"extensibility.html\"><span style=\"color: navy\">"
							+ Utilities.escapeXml(elem.getShortDefn())
							+ "</span></a> ");
				} else {
				  if (elem.eliminated()) 
				    write("<span style=\"text-decoration: line-through\">");
				  BindingSpecification bs = definitions.getBindingByName(elem.getBindingName());
				  if (bs != null && bs.getBinding() != Binding.Unbound && !Utilities.noString(bs.getReference())) { 
				    if (bs.getBinding() == Binding.CodeList || bs.getBinding() == Binding.Special)
				      write("<span style=\"color: navy\"><a href=\""+bs.getReference().substring(1)+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a></span>");
				    else if (bs.getReference().startsWith("http://hl7.org/fhir")) {
				      if (bs.getReference().startsWith("http://hl7.org/fhir/v3/vs/")) {
				        ValueSet vs = page.getValueSets().get(bs.getReference()); // night be null in a partial build
	              String pp = (String) vs.getUserData("path");
				        write("<a href=\""+(vs == null ? "??" : pp.replace(File.separatorChar, '/'))+"\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
				      } else if (bs.getReference().startsWith("http://hl7.org/fhir/v2/vs/")) {
	                ValueSet vs = page.getValueSets().get(bs.getReference());
	                String pp = (String) vs.getUserData("path");
	                write("<a href=\""+(vs == null ? "??" : pp.replace(File.separatorChar, '/'))+"\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn())+ "</a>");
				      } else if (bs.getReference().startsWith("http://hl7.org/fhir/vs/")) {
				        BindingSpecification bs1 = page.getDefinitions().getBindingByReference("#"+bs.getReference().substring(23), bs);
				        if (bs1 != null)
                  write("<a href=\""+bs.getReference().substring(23)+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
                else
                  write("<a href=\"valueset-"+bs.getReference().substring(23)+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
				      } else
				        throw new Exception("Internal reference "+bs.getReference()+" not handled yet");
				    } else
				      write("<span style=\"color: navy\"><a href=\""+bs.getReference()+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a></span>");				  
				  } else
					  write("<span style=\"color: navy\">" + docPrefix(width, indent, elem)+Utilities.escapeXml(elem.getShortDefn()) + "</span>");
          if (elem.eliminated()) 
            write("</span>");
				}
			} else {
				if (elem.unbounded() && !listed) { // isNolist()) {
					if (elem.usesCompositeType()) {
						write(" <span style=\"color: Gray\">&lt;!--");
						writeCardinality(elem);
	          if (elem.eliminated()) 
	            write("<span style=\"text-decoration: line-through\">");
	          write("" + Utilities.escapeXml(elem.getShortDefn()));
	          if (elem.eliminated()) 
	            write("</span>");
	          write(" --&gt;</span>");
					} else if (elem.hasShortDefn()) {
						write(" <span style=\"color: Gray\">&lt;!--");
						writeCardinality(elem);
	          if (elem.eliminated()) 
	            write("<span style=\"text-decoration: line-through\">");
	          write(" " + Utilities.escapeXml(elem.getShortDefn()));
	          if (elem.eliminated()) 
	            write("</span>");
	          write(" --&gt;</span>");
					} else {
						write(" <span style=\"color: Gray\">&lt;!--");
						writeCardinality(elem);
						write(" --&gt;</span>");
					}
				} else if (elem.hasShortDefn()) {
					  write(" <span style=\"color: Gray\">&lt;!--");
            writeCardinality(elem);
            if (elem.eliminated()) 
              write("<span style=\"text-decoration: line-through\">");
            write(" "+Utilities.escapeXml(elem.getShortDefn()));
            if (elem.eliminated()) 
              write("</span>");
            write(" --&gt;</span>");
				}
				write("\r\n");

				if (elem.getMaxCardinality() == null || elem.getMaxCardinality() > 0) {
				  // if we want extension/modifierExtension shown explicitly
//				  if (backbone) {
//			      for (int i = 0; i < indent; i++)
//			        write(" ");
//				    write(" &lt;!-- <a href=\"extensibility.html\">extension</a>, <a href=\"extensibility.html#modifierExtension\">modifierExtension</a> -->\r\n");
//				  }
				  if (sharedDT) {
				    ElementDefn sdt = definitions.getElementDefn(elem
				        .typeCode());
				    for (ElementDefn child : sdt.getElements()) {
				      generateCoreElem(child, indent + 1, rootName, pathName+ "." + en, backbone); //sdt.getName());
				    }
				  } else {
				    for (ElementDefn child : elem.getElements()) {
				      generateCoreElem(child, indent + 1, rootName, pathName + "." + en, backbone);
				    }
				  }
				}

				for (int i = 0; i < indent; i++) {
					write(" ");
				}
			}

			if (elem.getElements().isEmpty() && !sharedDT)
				write("<span style=\"color: Gray\"> --&gt;</span>");
			if (!doneType) {
			  write("&lt;/");
			  write(en);
			  write("&gt;");
			}
			if (elem.isInherited())
				write("</i>");
			write("\r\n");

		}
	}

  private void generateCoreElem(List<ElementDefinition> elements, ElementDefinition elem, int indent, String pathName, boolean asValue) throws Exception {
    // if (elem.getConformance() == ElementDefn.Conformance.Prohibited)
    // return;

    boolean listed = false;
    boolean doneType = false;
    int width = 0;
    List<ElementDefinition> children = getChildren(elements, elem);
    boolean isExtension = elem.getPath().equals("Extension") || elem.getPath().startsWith("Extension.");
    String name = isExtension ? "extension" : tail(elem.getPath());

    String indentS = "";
    for (int i = 0; i < indent; i++) {
      indentS += " ";
    }
    write(indentS+"&lt;!-- from Element: <a href=\"extensibility.html\">extension</a> -->\r\n");
    
    write(indentS);
    
    String en = asValue ? "value[x]" : name;

    if (en.contains("[x]") && elem.getType().size() == 1)
      en = en.replace("[x]", upFirst(elem.getType().get(0).getCode()));

    String closeOut;
    if (asValue) {
      closeOut = "";
    } else if (elem.getIsModifier() || elem.getMustSupport()) { 
      write("&lt;<a href=\"" + (defPage + "#" + pathName + "." + en).replace("[", "_").replace("]", "_")+ "\" title=\"" + Utilities .escapeXml(getEnhancedDefinition(elem)) 
            + "\" class=\"dict\"><span style=\"text-decoration: underline\">");
      closeOut = "</b></span></a>";
    } else {
      write("&lt;<a href=\"" + (defPage + "#" + pathName + "." + en).replace("[", "_").replace("]", "_") + "\" title=\"" + Utilities.escapeXml(elem.getFormal()) + "\" class=\"dict\">");
      closeOut = "</b></a>";
    }
    if (isExtension) {
      if (!asValue) {
        write("<b>extension"+closeOut+" url=\"<span style=\"color: navy\">"+tail(elem.getPath())+"</span>\"");
        write("&gt; <span style=\"color: Gray\">&lt;!--</span>");
        writeCardinality(elem);
        write(" ");
        
        if (elem.hasBinding() && elem.getBinding().hasReference()) {
          ValueSet vs = resolveValueSet(elem.getBinding().getReference());
          if (vs != null)
            write("<span style=\"color: navy\"><a href=\""+vs.getUserData("filename")+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");
          else
            write("<span style=\"color: navy\"><a href=\""+elem.getBinding().getReference()+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");          
        } else
          write("<span style=\"color: navy\">" + docPrefix(width, indent, elem)+Utilities.escapeXml(elem.getShort()) + "</span>");
        write(" <span style=\"color: Gray\">--!&gt; </span>\r\n");
        indentS += " ";
      } else 
        indentS = ""; // already written
        
      if (elem.getType().size() == 1 && (definitions.getPrimitives().containsKey(elem.getType().get(0).getCode()))) {
        write(indentS+"&lt;value");
        doneType = true;
        write(Utilities.capitalize(elem.getType().get(0).getCode())+" value=\"[<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + definitions.getSrcFile(elem.getType().get(0).getCode())+ ".html#" + elem.getType().get(0).getCode()) + "\">" + elem.getType().get(0).getCode()+ "</a></span>]\"/&gt;");
      } else if (elem.getType().size() > 1) {
        write(indentS+"&lt;value[x]> ");
        write("<span style=\"color: Gray\">&lt;!--</span> ");
        boolean first = true;
        for (TypeRefComponent t : elem.getType()) {
          if (first)
            first = false;
          else
            write(" | ");
          write("<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + definitions.getSrcFile(t.getCode())+ ".html#" + t) + "\">" + t+ "</a></span>");
        }
        write("<span style=\"color: Gray\"> --!&gt; </span>");
        write("/&lt;value[x]> ");
      } else if (elem.getType().size() == 1) {
        write(indentS+"&lt;value");
        write(Utilities.capitalize(elem.getType().get(0).getCode())+"&gt; <span style=\"color: Gray\">&lt;!--</span> <span style=\"color: darkgreen\"><a href=\"" + (dtRoot + definitions.getSrcFile(elem.getType().get(0).getCode())+ ".html#" + elem.getType().get(0).getCode()) + "\">" + elem.getType().get(0).getCode()+ "</a></span><span style=\"color: Gray\"> --!&gt; </span>");
        write("/&lt;value"+Utilities.capitalize(elem.getType().get(0).getCode())+"&gt; ");
      } else {
        for (ElementDefinition child : children) {
          generateCoreElem(elements, child, indent + 1, pathName + "." + name, false);
        }
      }
      if (!asValue) 
        write("\r\n" + indentS.substring(0, indentS.length()-1)+"&lt;/extension&gt;");
      
    } else {
      write(closeOut+" ");
      if (elem.getType().size() == 1 && (definitions.getPrimitives().containsKey(elem.getType().get(0).getCode()))) {
        doneType = true;
        write(" value=\"[<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + definitions.getSrcFile(elem.getType().get(0).getCode())+ ".html#" + elem.getType().get(0).getCode()) + "\">" + elem.getType().get(0).getCode()+ "</a></span>]\"/");
      }
      write("&gt;");

      // For simple elements without nested content, render the
      // optionality etc. within a comment
      if (children.isEmpty())
        write("<span style=\"color: Gray\">&lt;!--</span>");

      //    if (usesCompositeType(elem)) {
      //      // Contents of element are defined elsewhere in the same
      //      // resource
      //      writeCardinality(elem);
      //
      //      write(" <span style=\"color: darkgreen\">");
      //      write("Content as for " + typeCode(elem).substring(1) + "</span>");
      //      listed = true;
      //    } else 
      if (!elem.getType().isEmpty()) {
        writeCardinality(elem);
        listed = true;
        if (!doneType) {
          width = writeTypeLinks(elem, indent);
        }
      } else if (tail(elem.getPath()).equals("extension")) {
        write(" <a href=\"extensibility.html\"><span style=\"color: navy\">See Extensions</span></a> ");
      } else {
        write(" <a href=\"none.html\"><span style=\"color: navy\">No Types?</span></a> ");
      }

      write(" ");
      if (children.isEmpty()) {
        if (name.equals("extension") || name.equals("modifierExtension")) {
          write(" <a href=\"extensibility.html\"><span style=\"color: navy\">"
              + Utilities.escapeXml(elem.getShort())
              + "</span></a> ");
        } else {
          if (elem.hasMax() && elem.getMax().equals("0")) 
            write("<span style=\"text-decoration: line-through\">");
          if (elem.hasBinding() && elem.getBinding().hasReference()) {
            ValueSet vs = resolveValueSet(elem.getBinding().getReference());
            if (vs != null)
              write("<span style=\"color: navy\"><a href=\""+vs.getUserData("filename")+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");
            else
              write("<span style=\"color: navy\"><a href=\""+elem.getBinding().getReference()+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");          
          } else
            write("<span style=\"color: navy\">" + docPrefix(width, indent, elem)+Utilities.escapeXml(elem.getShort()) + "</span>");
          if (elem.hasMax() && elem.getMax().equals("0")) 
            write("</span>");
        }
      } else {
        if ("*".equals(elem.getMax()) && !listed) { // isNolist()) {
          if (elem.hasShort()) {
            write(" <span style=\"color: Gray\">&lt;!--");
            writeCardinality(elem);
            if (elem.hasMax() && elem.getMax().equals("0")) 
              write("<span style=\"text-decoration: line-through\">");
            write(" " + Utilities.escapeXml(elem.getShort()));
            if (elem.hasMax() && elem.getMax().equals("0")) 
              write("</span>");
            write(" --&gt;</span>");
          } else {
            write(" <span style=\"color: Gray\">&lt;!--");
            writeCardinality(elem);
            write(" --&gt;</span>");
          }
        } else if (elem.hasShort()) {
          write(" <span style=\"color: Gray\">&lt;!--");
          writeCardinality(elem);
          if (elem.hasMax()  && elem.getMax().equals("0")) 
            write("<span style=\"text-decoration: line-through\">");
          write(" "+Utilities.escapeXml(elem.getShort()));
          if (elem.hasMax() && elem.getMax().equals("0")) 
            write("</span>");
          write(" --&gt;</span>");
        }
        write("\r\n");

        if (elem.getMax() == null || !elem.getMax().equals("0")) {
          // if we want extension/modifierExtension shown explicitly
          //          if (backbone) {
          //            for (int i = 0; i < indent; i++)
          //              write(" ");
          //            write(" &lt;!-- <a href=\"extensibility.html\">extension</a>, <a href=\"extensibility.html#modifierExtension\">modifierExtension</a> -->\r\n");
          //          }
          for (ElementDefinition child : children) {
            generateCoreElem(elements, child, indent + 1, pathName + "." + name, false);
          }
        }
      }

      for (int i = 0; i < indent; i++) {
        write(" ");
      }

      if (children.isEmpty())
        write("<span style=\"color: Gray\"> --&gt;</span>");
      if (!doneType) {
        write("&lt;/");
        write(en);
        write("&gt;");
      }
    }
    write("\r\n");
  }

  private ValueSet resolveValueSet(Type reference) {
    //            else if (bs.getReference().startsWith("http://hl7.org/fhir")) {
    //              if (bs.getReference().startsWith("http://hl7.org/fhir/v3/vs/")) {
    //                AtomEntry<ValueSet> vs = page.getValueSets().get(bs.getReference()); // night be null in a partial build
    //                write("<a href=\""+(vs == null ? "??" : vs.getLinks().get("path").replace(File.separatorChar, '/'))+"\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
    //              } else if (bs.getReference().startsWith("http://hl7.org/fhir/v2/vs/")) {
    //                  AtomEntry<ValueSet> vs = page.getValueSets().get(bs.getReference());
    //                  write("<a href=\""+(vs == null ? "??" : vs.getLinks().get("path").replace(File.separatorChar, '/'))+"\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn())+ "</a>");
    //              } else if (bs.getReference().startsWith("http://hl7.org/fhir/vs/")) {
    //                BindingSpecification bs1 = page.getDefinitions().getBindingByReference("#"+bs.getReference().substring(23), bs);
    //                if (bs1 != null)
    //                  write("<a href=\""+bs.getReference().substring(23)+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
    //                else
    //                  write("<a href=\"valueset-"+bs.getReference().substring(23)+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
    //              } else
    //                throw new Exception("Internal reference "+bs.getReference()+" not handled yet");
    // TODO Auto-generated method stub
    return null;
  }

  private String tail(String path) {
    return path.contains(".") ? path.substring(path.lastIndexOf(".")+1) : path;
  }

  private List<ElementDefinition> getChildren(List<ElementDefinition> elements, ElementDefinition elem) {
    int i = elements.indexOf(elem)+1;
    List<ElementDefinition> res = new ArrayList<ElementDefinition>();
    while (i < elements.size()) {
      if (elements.get(i).getPath().startsWith(elem.getPath()+".")) 
        res.add(elements.get(i));
      else
        return res;
      i++;
    }
    return res;
  }

  private String getEnhancedDefinition(ElementDefinition elem) {
    if (elem.getIsModifier() && elem.getMustSupport())
      return Utilities.removePeriod(elem.getFormal()) + " (this element modifies the meaning of other elements, and must be supported)";
    else if (elem.getIsModifier())
      return Utilities.removePeriod(elem.getFormal()) + " (this element modifies the meaning of other elements)";
    else if (elem.getMustSupport())
      return Utilities.removePeriod(elem.getFormal()) + " (this element must be supported)";
    else
      return Utilities.removePeriod(elem.getFormal());
  }

  private String docPrefix(int widthSoFar, int indent, ElementDefn elem) {
    if (widthSoFar + elem.getShortDefn().length()+8+elem.getName().length() > 105) {
      String ret = "\r\n  ";
      for (int i = 0; i < indent+2; i++)
        ret = ret + " ";

      return ret;
    }
    else
      return "";
  }

  private String docPrefix(int widthSoFar, int indent, ElementDefinition elem) {
    if (widthSoFar + (elem.getShort() == null ? 0 : elem.getShort().length())+8+elem.getPath().length() > 105) {
      String ret = "\r\n  ";
      for (int i = 0; i < indent+2; i++)
        ret = ret + " ";

      return ret;
    }
    else
      return "";
  }

  private int writeTypeLinks(ElementDefn elem, int indent) throws Exception {
    write(" <span style=\"color: darkgreen\">");
    int i = 0;
    int w = indent + 12 + elem.getName().length(); // this is wrong if the type is an attribute, but the wrapping concern shouldn't apply in this case, so this is ok
    for (TypeRef t : elem.getTypes()) {
      if (i > 0) {
        write("|");
        w++;
      }
      if (w + t.getName().length() > 80) {
        write("\r\n  ");
        for (int j = 0; j < indent; j++)
          write(" ");
        w = indent+2;
      }
      w = w + t.getName().length(); // again, could be wrong if this is an extension, but then it won't wrap
      if (t.isXhtml() || t.getName().equals("list"))
        write(t.getName());
      else if (t.getName().equals("Extension") && t.getParams().size() == 0 && !Utilities.noString(t.getProfile()))
        write("<a href=\""+t.getProfile()+"\"><span style=\"color: DarkViolet\">@"+t.getProfile().substring(1)+"</span></a>");     
      else
        write("<a href=\"" + (dtRoot + definitions.getSrcFile(t.getName())
            + ".html#" + t.getName() + "\">" + t.getName()).replace("[", "_").replace("]", "_")
            + "</a>");
      if (t.hasParams()) {
        write("(");
        boolean firstp = true;
        for (String p : t.getParams()) {
          if (!firstp) {
            write("|");
            w++;
          }

          // again, p.length() could be wrong if this is an extension, but then it won't wrap
          if (w + p.length() > 80) {
            write("\r\n  ");
            for (int j = 0; j < indent; j++)
              write(" ");
            w = indent+2;
          }
          w = w + p.length(); 
          
          // TODO: Display action and/or profile information
					if (p.equals("Any")) {
            write("<a href=\"" + "resourcelist.html" + "\">" + p + "</a>");								
          }
          else if (t.getName().equals("Reference") && t.getParams().size() == 1 && !Utilities.noString(t.getProfile()))
            write("<a href=\""+t.getProfile()+"\"><span style=\"color: DarkViolet\">@"+t.getProfile().substring(1)+"</span></a>");     
          else
            write("<a href=\"" + (dtRoot + definitions.getSrcFile(p)
                + ".html#" + p).replace("[", "_").replace("]", "_") + "\">" + p + "</a>");

          firstp = false;
        }
        write(")");
        w++;
      }

      i++;
    }
    write("</span>");
    return w;
  }

  private int writeTypeLinks(ElementDefinition elem, int indent) throws Exception {
    write(" <span style=\"color: darkgreen\">");
    int i = 0;
    int w = indent + 12 + elem.getPath().length(); // this is wrong if the type is an attribute, but the wrapping concern shouldn't apply in this case, so this is ok
    for (TypeRefComponent t : elem.getType()) {
      if (i > 0) {
        write("|");
        w++;
      }
      if (w + t.getCode().length() > 80) {
        write("\r\n  ");
        for (int j = 0; j < indent; j++)
          write(" ");
        w = indent+2;
      }
      w = w + t.getCode().length(); // again, could be wrong if this is an extension, but then it won't wrap
      if (t.getCode().equals("list"))
        write(t.getCode());
      else if (t.getCode().equals("Extension") && !Utilities.noString(t.getProfile()))
        write("<a href=\""+t.getProfile()+"\"><span style=\"color: DarkViolet\">@"+t.getProfile().substring(1)+"</span></a>");     
      else
        write("<a href=\"" + (dtRoot + definitions.getSrcFile(t.getCode())
            + ".html#" + t.getCode() + "\">" + t.getCode())
            + "</a>");
      i++;
    }
    write("</span>");
    return w;
  }

	private void writeCardinality(ElementDefn elem) throws IOException {
		if (elem.getStatedInvariants().size() > 0)
			write(" <span style=\"color: brown\" title=\""
					+ Utilities.escapeXml(getInvariants(elem)) + "\"><b><img alt=\"??\" src=\"lock.png\"/> "
					+ elem.describeCardinality() + "</b></span>");
		else
			write(" <span style=\"color: brown\"><b>"
					+ elem.describeCardinality() + "</b></span>");
	}

  private void writeCardinality(ElementDefinition elem) throws IOException {
    if (elem.getConstraint().size() > 0)
      write(" <span style=\"color: brown\" title=\""
          + Utilities.escapeXml(getInvariants(elem)) + "\"><b><img alt=\"??\" src=\"lock.png\"/> "
          + describeCardinality(elem) + "</b></span>");
    else
      write(" <span style=\"color: brown\"><b>"
          + describeCardinality(elem) + "</b></span>");
  }

	private String describeCardinality(ElementDefinition elem) {
    return (elem.getMinElement() == null ? "" : Integer.toString(elem.getMin())) + ".."+(elem.getMax() == null ? "" : elem.getMax());
  }

  private String getInvariants(ElementDefn elem) {
		StringBuilder b = new StringBuilder();
		boolean first = true;
		for (Invariant i : elem.getStatedInvariants()) {
			if (!first)
				b.append("; ");
			first = false;
			b.append(i.getId()+": "+i.getEnglish());
		}

		return b.toString();
	}

  private String getInvariants(ElementDefinition elem) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (ElementDefinitionConstraintComponent i : elem.getConstraint()) {
      if (!first)
        b.append("; ");
      first = false;
      b.append(i.getKey()+": "+i.getHuman());
    }

    return b.toString();
  }

  private String renderType(int indent, Type value) throws Exception {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < indent-2; i++)
      b.append(" ");
    String ind = b.toString();

    XmlParser xml = new XmlParser();
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    xml.setOutputStyle(OutputStyle.PRETTY);
    xml.compose(bs, null, value);
    bs.close();
    String[] result = bs.toString().split("\\r?\\n");
    b = new StringBuilder();
    for (String s : result) {
      if (s.startsWith(" ")) // eliminate the wrapper content 
        b.append("\r\n  "+ind + Utilities.escapeXml(s));
    }
    return b.toString()+"\r\n"+ind;  
  }

  public void generate(Profile resource) {
    // TODO Auto-generated method stub
    
  }
  
	// code ### | text
//	private String renderCodeableConcept(int indent, CodeableConcept value)
//			throws Exception {
//		StringBuilder s = new StringBuilder();
//		for (int i = 0; i < indent; i++)
//			s.append(" ");
//		String ind = s.toString();
//		s = new StringBuilder();
//		String[] parts = value.split("\\|");
//		
//
//		if (parts[0].length() > 0) {
//		  String[] parts2 = parts[0].split("#");
//		  s.append("\r\n" + ind + "  &lt;coding&gt;");
//		  if (parts2.length > 0 && parts2[0].length() > 0)
//		    s.append("\r\n" + ind + "    &lt;code value=\"" + parts2[0] + "\"/&gt;");
//      if (parts2.length > 1 && parts2[1].length() > 0)                   
//		    s.append("\r\n" + ind + "    &lt;system value=\"" + parts2[1] + "\"/&gt;");
//      if (parts2.length > 2 && parts2[2].length() > 0)
//		    s.append("\r\n" + ind + "    &lt;display value=\"" + parts2[2] + "\"/&gt;");
//      s.append("\r\n" + ind + "  &lt;/coding&gt;");
//	  }
//    if (parts.length > 1 && parts[1].length() > 0)
//      s.append("\r\n" + ind + "  &lt;text value=\"" + parts[1] + "\"/&gt;");
//		s.append("\r\n" + ind);
//		return s.toString();
//	}
//
//	private String renderQuantity(int indent, String value) throws Exception {
//		StringBuilder s = new StringBuilder();
//		for (int i = 0; i < indent; i++)
//			s.append(" ");
//		String ind = s.toString();
//		s = new StringBuilder();
//		String f = null;
//		if (!Character.isDigit(value.charAt(0))) {
//			f = value.substring(0, 1);
//			value = value.substring(1);
//		}
//		String[] parts = value.split(" ");
//		if (parts.length != 2)
//			throw new Exception("unable to parse fixed quantity value " + value);
//		String v = parts[0];
//		String u = parts[1];
//		s.append("\r\n" + ind + "  &lt;value&gt;" + v + "&lt;/value&gt;");
//		if (f != null)
//			s.append("\r\n" + ind + "  &lt;status&gt;"
//					+ Utilities.escapeXml(Utilities.escapeXml(f))
//					+ "&lt;/status&gt;");
//		s.append("\r\n" + ind + "  &lt;units&gt;" + u + "&lt;/units&gt;");
//		s.append("\r\n" + ind + "  &lt;code&gt;" + u + "&lt;/code&gt;");
//		s.append("\r\n" + ind
//				+ "  &lt;system&gt;urn:hl7-org:sid/ucum&lt;/system&gt;");
//		s.append("\r\n" + ind);
//		return s.toString();
//	}
//

}
