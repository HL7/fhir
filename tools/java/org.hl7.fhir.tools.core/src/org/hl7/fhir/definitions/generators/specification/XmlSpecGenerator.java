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
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.PropertyRepresentation;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;

public class XmlSpecGenerator extends OutputStreamWriter {

	private String defPage;
	private String dtRoot;
	private Definitions definitions;
  private PageProcessor page;
  private String prefix; 

	public XmlSpecGenerator(OutputStream out, String defPage, String dtRoot, PageProcessor page, String prefix) throws UnsupportedEncodingException {
		super(out, "UTF-8");
		this.defPage = defPage;
		this.dtRoot = dtRoot == null ? "" : dtRoot;
		this.definitions = page.getDefinitions();
		this.page = page;
		this.prefix = prefix;
	}

  protected String getBindingLink(ElementDefn e) throws Exception {
    BindingSpecification bs = e.getBinding();
    if (bs == null)
      return "terminologies.html#unbound";
    if (bs.getValueSet() != null) 
      return bs.getValueSet().getUserString("path");
    else if (!Utilities.noString(bs.getReference()))
      return bs.getReference();      
    else 
      return "terminologies.html#unbound";
  }

	public void generate(ElementDefn root, boolean isAbstract) throws Exception {
		write("<pre class=\"spec\">\r\n");

		generateInner(root, true, isAbstract);

		write("</pre>\r\n");
		flush();
		close();
	}

  public void generateExtension(StructureDefinition ed) throws Exception {
    write("<pre class=\"spec\">\r\n");

    generateExtensionInner(ed);

    write("</pre>\r\n");
    flush();
    close();
  }

  private void generateExtensionInner(StructureDefinition ed) throws IOException, Exception {
    ElementDefinition root = ed.getSnapshot().getElement().get(0);
    write("&lt;!-- "+Utilities.escapeXml(ed.getName())+" -->");
    write("<span style=\"float: right\"><a title=\"Documentation for this format\" href=\""+prefix+"xml.html\"><img src=\"help.png\" alt=\"doco\"/></a></span>\r\n");
    String rn = ed.getSnapshot().getElement().get(0).getIsModifier() ? "modifierExtension" : "extension";

    write("\r\n&lt;");
    if (defPage == null)
      write("<span title=\"" + Utilities.escapeXml(root.getDefinition())
          + "\"><b>");
    else
      write("<a href=\"" + (defPage + "#" + root.getName()) + "\" title=\""
          + Utilities.escapeXml(root.getDefinition())
          + "\" class=\"dict\"><b>");
    write(rn);
    if ((defPage == null))
      write("</b></span>");
    else
      write("</b></a>");

    write(" xmlns=\"http://hl7.org/fhir\"\r\n    ");
    generateExtensionAttribute(ed);
    write(" &gt;\r\n");

    List<ElementDefinition> children = getChildren(ed.getSnapshot().getElement(), ed.getSnapshot().getElement().get(0));
    boolean complex = isComplex(children);
    if (!complex)
      write("  &lt;!-- from Element: <a href=\""+prefix+"extensibility.html\">extension</a> -->\r\n");
    for (ElementDefinition child : children)
      generateCoreElem(ed.getSnapshot().getElement(), child, 1, rn, false, complex);

    write("&lt;/");
    write(rn);
    write("&gt;\r\n");
  }
  
	private boolean isComplex(List<ElementDefinition> children) {
	  int c = 0;
	  for (ElementDefinition child : children) {
	    if (child.getPath().equals("Extension.extension"))
	      c++;
	  }
    return c > 1;
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
			write("<a href=\"" + (defPage + "#" + root.getName()) + "\" title=\""
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
		    write("&gt; <span style=\"float: right\"><a title=\"Documentation for this format\" href=\""+prefix+"xml.html\"><img src=\"help.png\" alt=\"doco\"/></a></span>\r\n");
		} else 
		  write("&gt;\r\n");
    if (rn.equals(root.getName()) && resource) {
      if (!Utilities.noString(root.typeCode())) {
        write(" &lt;!-- from <a href=\""+prefix+"resource.html\">Resource</a>: <a href=\""+prefix+"resource.html#id\">id</a>, <a href=\""+prefix+"resource.html#meta\">meta</a>, <a href=\""+prefix+"resource.html#implicitRules\">implicitRules</a>, and <a href=\""+prefix+"resource.html#language\">language</a> -->\r\n");
        if (root.typeCode().equals("DomainResource"))
          write(" &lt;!-- from <a href=\""+prefix+"domainresource.html\">DomainResource</a>: <a href=\""+prefix+"narrative.html#Narrative\">text</a>, <a href=\""+prefix+"references.html#contained\">contained</a>, <a href=\""+prefix+"extensibility.html\">extension</a>, and <a href=\""+prefix+"extensibility.html#modifierExtension\">modifierExtension</a> -->\r\n");
      }
    } else {
      write(" &lt;!-- from Element: <a href=\""+prefix+"extensibility.html\">extension</a> -->\r\n");
    }
		for (ElementDefn elem : root.getElements()) {
		  if (!elem.typeCode().equals("xml:lang") && !elem.isXmlAttribute())
		    generateCoreElem(elem, 1, rn, root.getName(), rn.equals(root.getName()) && resource);
		}

		write("&lt;/");
		write(rn);
		write("&gt;\r\n");
	}

	private void generateExtensionAttribute(StructureDefinition ed) throws Exception {
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

  private String upFirst(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}


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
      write("&lt;<a href=\"" + (defPage + "#" + pathName + "." + en)+ "\" title=\"" + Utilities .escapeXml(elem.getEnhancedDefinition()) 
            + "\" class=\"dict\"><span style=\"text-decoration: underline\">");
		else
			write("&lt;<a href=\"" + (defPage + "#" + pathName + "." + en) + "\" title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\" class=\"dict\">");

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
  			write(" value=\"[<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + definitions.getSrcFile(t.getName())+ ".html#" + t.getName()) + "\">" + t.getName()+ "</a></span>]\"/");
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
				write(" <a href=\""+prefix+"extensibility.html\"><span style=\"color: navy\">See Extensions</span></a> ");
			} else if (elem.getTypes().size() == 1
					&& elem.getTypes().get(0).isWildcardType()) {
				writeCardinality(elem);
		    write(" <span style=\"color: darkgreen\">");
        write("<a href=\""+prefix+"datatypes.html#open\">*</a>");     
		    write("</span>");
				listed = true;
			}

//			if (!Utilities.noString(elem.getProfile())) {
//	      write(" <a href=\""+elem.getProfile()+"\"><span style=\"color: DarkViolet\">StructureDefinition: \""+elem.getProfile().substring(1)+"\"</span></a>");		  
//			}
			write(" ");
			if (elem.getElements().isEmpty() && !sharedDT) {
				if ("See Extensions".equals(elem.getShortDefn())) {
					write(" <a href=\""+prefix+"extensibility.html\"><span style=\"color: navy\">"
							+ Utilities.escapeXml(elem.getShortDefn())
							+ "</span></a> ");
				} else {
				  if (elem.eliminated()) 
				    write("<span style=\"text-decoration: line-through\">");
	        String ref = getBindingLink(elem);
          write("<span style=\"color: navy\"><a href=\""+prefix+ref+"\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a></span>");
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
//				    write(" &lt;!-- <a href=\""+prefix+"extensibility.html\">extension</a>, <a href=\""+prefix+"extensibility.html#modifierExtension\">modifierExtension</a> -->\r\n");
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

  private void generateCoreElem(List<ElementDefinition> elements, ElementDefinition elem, int indent, String pathName, boolean asValue, boolean complex) throws Exception {
    // if (elem.getConformance() == ElementDefn.Conformance.Prohibited)
    // return;
    for (Enumeration<PropertyRepresentation> t : elem.getRepresentation()) 
      if (t.getValue() == PropertyRepresentation.XMLATTR)
        return;
    if (elem.getPath().endsWith(".extension") && !complex)
      return;
    if (complex && elem.getMax().equals("0"))
      return;

    boolean listed = false;
    boolean doneType = false;
    int width = 0;
    List<ElementDefinition> children = getChildren(elements, elem);
    String name = tail(elem.getPath());

    String indentS = "";
    for (int i = 0; i < indent; i++) {
      indentS += " ";
    }
    
    write(indentS);
    
    String en = asValue ? "value[x]" : name;

    if (en.contains("[x]") && elem.getType().size() == 1)
      en = en.replace("[x]", upFirst(elem.getType().get(0).getCode()));

    String closeOut;
    if (elem.hasSlicing()) {
      write("<span style=\"color: navy\">&lt;-- "+en+describeSlicing(elem.getSlicing())+"--&gt;</span>\r\n");
      closeOut = "";
      return;
    } else if (asValue) {
      closeOut = "";
      throw new Error("not done yet");
    } else if (elem.getIsModifier() || elem.getMustSupport()) { 
      write("&lt;<a href=\"" + (defPage + "#" + pathName + "." + en)+ "\" title=\"" + Utilities .escapeXml(getEnhancedDefinition(elem)) 
            + "\" class=\"dict\"><span style=\"text-decoration: underline\">");
      closeOut = "</b></span></a>";
    } else {
      write("&lt;<a href=\"" + (defPage + "#" + pathName + "." + en) + "\" title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\" class=\"dict\">");
      closeOut = "</b></a>";
    }
    write("<b>"+Utilities.escapeXml(en));

    write(closeOut);
    if (complex && elem.getPath().endsWith(".extension")) {
      write(" url=\"");
      write("<span style=\"color: navy\">" + getUrl(children)+"</span>");
      write("\"");
    }
      if (elem.getType().size() == 1 && (definitions.getPrimitives().containsKey(elem.getType().get(0).getCode()))) {
        doneType = true;
        write(" value=\"[<span style=\"color: darkgreen\"><a href=\"" + prefix+dtRoot + definitions.getSrcFile(elem.getType().get(0).getCode())+ ".html#" + elem.getType().get(0).getCode() + "\">" + elem.getType().get(0).getCode()+ "</a></span>]\"/");
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
        write(" <a href=\""+prefix+"extensibility.html\"><span style=\"color: navy\">See Extensions</span></a> ");
      } else {
        write(" <a href=\""+prefix+"none.html\"><span style=\"color: navy\">No Types?</span></a> ");
      }

      write(" ");
      if (children.isEmpty()) {
        if (name.equals("extension") || name.equals("modifierExtension")) {
          write(" <a href=\""+prefix+"extensibility.html\"><span style=\"color: navy\">"
              + Utilities.escapeXml(elem.getShort())
              + "</span></a> ");
        } else {
          if (elem.hasMax() && elem.getMax().equals("0")) 
            write("<span style=\"text-decoration: line-through\">");
          if (elem.hasBinding() && elem.getBinding().hasValueSet()) {
            ValueSet vs = resolveValueSet(elem.getBinding().getValueSet());
            if (vs != null)
              write("<span style=\"color: navy\"><a href=\""+prefix+vs.getUserData("filename")+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");
            else if (elem.getBinding().getValueSet() instanceof UriType)
              write("<span style=\"color: navy\"><a href=\""+((UriType)elem.getBinding().getValueSet()).getValue()+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");          
            else
              write("<span style=\"color: navy\"><a href=\""+((Reference)elem.getBinding().getValueSet()).getReference()+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");          
          } else
            write("<span style=\"color: navy\">" + docPrefix(width, indent, elem)+Utilities.escapeXml(elem.getShort()) + "</span>");
          if (elem.hasMax() && elem.getMax().equals("0")) 
            write("</span>");
        }
        if (children.isEmpty())
          write("<span style=\"color: Gray\"> --&gt;</span>");
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
            generateCoreElem(elements, child, indent + 1, pathName + "." + name, false, false);
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

  private String getUrl(List<ElementDefinition> children) {
    for (ElementDefinition c : children) {
      if (c.getPath().endsWith(".url") && c.hasFixed() && c.getFixed() instanceof UriType)
        return ((UriType) c.getFixed()).asStringValue();
    }
    return "??";
  }

  private String describeSlicing(ElementDefinitionSlicingComponent slicing) {
    CommaSeparatedStringBuilder csv = new CommaSeparatedStringBuilder();
    for (StringType d : slicing.getDiscriminator()) {
      csv.append(d.getValue());
    }
    String s = slicing.getOrdered() ? " in any order" : " in the specified order" + slicing.getRules().getDisplay();
    return " sliced by "+csv.toString()+" "+s;
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
      String tgt = elements.get(i).getPath();
      String src = elem.getPath();
      if (tgt.startsWith(src+".")) {
        if (!tgt.substring(src.length()+1).contains(".")) 
          res.add(elements.get(i));
      } else
        return res;
      i++;
    }
    return res;
  }

  private String getEnhancedDefinition(ElementDefinition elem) {
    if (elem.getIsModifier() && elem.getMustSupport())
      return Utilities.removePeriod(elem.getDefinition()) + " (this element modifies the meaning of other elements, and must be supported)";
    else if (elem.getIsModifier())
      return Utilities.removePeriod(elem.getDefinition()) + " (this element modifies the meaning of other elements)";
    else if (elem.getMustSupport())
      return Utilities.removePeriod(elem.getDefinition()) + " (this element must be supported)";
    else
      return Utilities.removePeriod(elem.getDefinition());
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
        write("<a href=\""+prefix+t.getProfile()+"\"><span style=\"color: DarkViolet\">@"+t.getProfile().substring(1)+"</span></a>");     
      else if (definitions.getConstraints().containsKey(t.getName())) {
        ProfiledType pt = definitions.getConstraints().get(t.getName());
        write("<a href=\"" + (dtRoot + definitions.getSrcFile(pt.getBaseType())
        + ".html#" + pt.getBaseType() + "\">" + pt.getBaseType())+"</a>");
        w = w + pt.getBaseType().length()+2; 
        write("(<a style=\"color:navy\" href=\"" + (dtRoot + definitions.getSrcFile(t.getName())
        + ".html#" + t.getName() + "\">" + t.getName())
        + "</a>)");
      } else
        write("<a href=\"" + (dtRoot + definitions.getSrcFile(t.getName())
            + ".html#" + t.getName() + "\">" + t.getName())
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
            write("<a href=\"" +prefix+"resourcelist.html" + "\">" + p + "</a>");								
          }
          else if (t.getName().equals("Reference") && t.getParams().size() == 1 && !Utilities.noString(t.getProfile()))
            write("<a href=\""+prefix+t.getProfile()+"\"><span style=\"color: DarkViolet\">@"+t.getProfile().substring(1)+"</span></a>");     
          else
            write("<a href=\"" + (dtRoot + definitions.getSrcFile(p)
                + ".html#" + p) + "\">" + p + "</a>");

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
      else if (t.getCode().equals("Extension") && t.hasProfile())
        write("<a href=\""+prefix+t.getProfile()+"\"><span style=\"color: DarkViolet\">@"+t.getProfile().get(0).getValue().substring(1)+"</span></a>");     
      else {
        write("<a href=\"" + prefix+(dtRoot + definitions.getSrcFile(t.getCode())
            + ".html#" + t.getCode() + "\">" + t.getCode())
            + "</a>");
        if (t.getCode().equals("Reference") && t.hasProfile()) {
          write("(");
          String pt = t.getProfile().get(0).getValue();
          if (pt.startsWith("http://hl7.org/fhir/StructureDefinition/") && definitions.hasResource(pt.substring(40))) {
            write("<a href=\"" + prefix+pt.substring(40).toLowerCase() + ".html\">" + pt.substring(40) + "</a>");
          } else {
            StructureDefinition sd = page.getProfiles().get(pt);
            if (sd != null)
              write("<a href=\"" + prefix+sd.getUserString("path") + "\">" + sd.getName() + "</a>");
            else
              write("<a href=\"" + pt + "\">" + pt + "</a>");
          }
          write(")");
        }
      }
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

  public void generate(StructureDefinition resource) {
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
