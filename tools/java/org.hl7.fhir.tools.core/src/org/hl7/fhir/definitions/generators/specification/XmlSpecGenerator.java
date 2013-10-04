package org.hl7.fhir.definitions.generators.specification;

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
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.ExtensionDefn.ContextType;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ExtensionDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.utilities.Utilities;

public class XmlSpecGenerator extends OutputStreamWriter {

	private String defPage;
	private String dtRoot;
	private Definitions definitions;

	public XmlSpecGenerator(OutputStream out, String defPage, String dtRoot,
			Definitions definitions) throws UnsupportedEncodingException {
		super(out, "UTF-8");
		this.defPage = defPage;
		this.dtRoot = dtRoot == null ? "" : dtRoot;
		this.definitions = definitions;
	}

	public void generate(ElementDefn root) throws Exception {
		write("<pre class=\"spec\">\r\n");

		generateInner(root, true);

		write("</pre>\r\n");
		flush();
		close();
	}

	private void generateInner(ElementDefn root, boolean resource) throws IOException, Exception {
		String rn;
		if (root.getTypes().size() > 0 && (root.getTypes().get(0).getName().equals("Type")
				|| (root.getTypes().get(0).getName().equals("Structure")) && !root.getName().equals("Extension")))
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
		write(" xmlns=\"http://hl7.org/fhir\"&gt;\r\n");
    if (rn.equals(root.getName()) && resource) {
      write(" &lt;!-- from <a href=\"resources.htm\">Resource</a>: <a href=\"extensibility.htm\">extension</a>, <a href=\"narrative.htm#Narrative\">narrative</a>, and <a href=\"references.htm#contained\">contained</a> -->\r\n");
    } else {
      write(" &lt;!-- from Element: <a href=\"extensibility.htm\">extension</a> -->\r\n");
    }
		for (ElementDefn elem : root.getElements()) {
		  if (!elem.typeCode().equals("xml:lang"))
		    generateCoreElem(elem, 1, rn, root.getName());
		}

		write("&lt;/");
		write(rn);
		write("&gt;\r\n");
	}

	public void generate(ProfileDefn profile) throws Exception {
		write("<pre class=\"spec\">\r\n");

		if (profile.getResources().size() > 0) {
			write("<span style=\"color: Gray\">&lt;!-- <span style=\"color: Darkviolet\">Resources</span> --&gt;</span>\r\n");
			for (ResourceDefn r : profile.getResources()) {
	      write("<span style=\"color: Gray\">&lt;!--<a name=\"" + r.getRoot().getProfileName() + "\"> </a><span style=\"color: Darkviolet\">"+Utilities.escapeXml(r.getRoot().getProfileName())+"</span> --&gt;</span>\r\n");
				generateInner(r.getRoot(), false);
        write("\r\n");
			}
		}

		if (profile.getExtensions().size() > 0) {
			write("<span style=\" color: Gray\">&lt;!-- <span style=\"color: Darkviolet\">Extensions</span> --&gt;</span>\r\n");
			for (ExtensionDefn ex : profile.getExtensions()) {
				generateExtension(ex, profile, definitions, profile.getMetadata().get("extension.uri").get(0));
        write("\r\n");
			}
		}

		write("</pre>\r\n");
		flush();
		close();
	}

	private void generateExtension(ExtensionDefn ex, ProfileDefn profile, Definitions definitions, String root)
			throws Exception {
	  if (ex.getDefinition().isModifier())
	    write("&lt;<span style=\"text-decoration: underline\" title=\"" + Utilities.escapeXml(ex.getDefinition().getEnhancedDefinition()) + "\"><b>extension</b></span>&gt;");
	  else
	    write("&lt;<span title=\"" + Utilities.escapeXml(ex.getDefinition().getDefinition()) + "\"><b>extension</b></span>&gt;");
	  write("<a name=\""+ex.getCode()+"\"> </a>&lt;!-- ");
	  
		writeCardinality(ex.getDefinition());
		write(" ");
    write("<span style=\"color: navy\">" + Utilities.escapeXml("Context: "+ex.getType().toString()+" = "+ex.getContext()) + "</span>");
		write(" -->\r\n");
		write(" &lt;<b>url</b> value=\"<span style=\"color: maroon\">"+ root + "#"+ex.getCode() + "</span>\"/&gt;\r\n");
//		write(" &lt;<b>definition</b>><span style=\" color: Gray\">&lt;!-- </span> <span style=\"color: brown;\"><b>1..1</b></span> <span style=\"color: darkgreen;\"><a href=\"datatypes.htm#uri\">uri</a></span> <span style=\"color: navy\">where registered</span> <span style=\" color: Gray\">--&gt;</span>&lt;/definition>\r\n");
//		write(" &lt;<b>ref</b>&gt; <span style=\"color: navy\"><span style=\"color: darkgreen;\"><a href=\"references.htm#idref\">Ref</a></span> to a "
//				+ ex.getContext()
//				+ " ("
//				+ ex.getType().toString()
//				+ ")</span>\">  \r\n");
//		if (ex.getDefinition().isMustUnderstand())
//			write(" &lt;<b>mustUnderstand</b>>true&lt;/mustUnderstand>\r\n");
		if (ex.getDefinition().getTypes().size() == 0) {
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
        if (t.startsWith("Resource("))
          t = "ResourceReference";
        vn = "value" + upFirst(t);
		  }

		  write(" &lt;<span title=\"" + Utilities.escapeXml(ex.getDefinition().getDefinition()) + "\"><b>" + vn + "</b></span>");
		  if (ex.getDefinition().getTypes().size() == 1
		      && definitions.hasElementDefn(t)) {
		    write("&gt;");
		    write("<span style=\" color: Gray\">&lt;!-- </span>");
		    write(" <span style=\"color: brown;\"><b>0..1</b></span> ");
		    writeTypeLinks(ex.getDefinition());
		    write(" <span style=\"color: navy\">"+Utilities.escapeXml(ex.getDefinition().getShortDefn())+"</span>");
		    write(" <span style=\" color: Gray\">--&gt; </span>&lt;/" + vn + ">\r\n");
		  } else if (ex.getDefinition().getTypes().size() == 1) {
		    write(" value=\"[<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(t)+ ".htm#" + t) + "\">" + t+ "</a></span>]\"/>");
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
		write("&lt;/extension>\r\n");
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

	private void generateCoreElem(ElementDefn elem, int indent,
			String rootName, String pathName) throws Exception {
		// if (elem.getConformance() == ElementDefn.Conformance.Prohibited)
		// return;

		boolean listed = false;
		boolean doneType = false;
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
			if (elem.isModifier() || elem.isMustSupport())
  		  write("&lt;<span style=\"text-decoration: underline\" title=\"" + Utilities.escapeXml(elem.getEnhancedDefinition())	+ "\">");
			else
				write("&lt;<span title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\">");
		} else if (elem.isModifier() || elem.isMustSupport()) 
      write("&lt;<a href=\"" + (defPage + "#" + pathName + "." + en).replace("[", "_").replace("]", "_")+ "\" title=\"" + Utilities .escapeXml(elem.getEnhancedDefinition()) 
            + "\" class=\"dict\"><span style=\"text-decoration: underline\">");
		else
			write("&lt;<a href=\"" + (defPage + "#" + pathName + "." + en).replace("[", "_").replace("]", "_") + "\" title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\" class=\"dict\">");

		// element contains xhtml
		if (!elem.getTypes().isEmpty() && elem.getTypes().get(0).isXhtml()) {
			write("<b title=\""
					+ Utilities.escapeXml(elem.getDefinition())
					+ "\">div</b>" +  ((elem.isModifier() || elem.isMustSupport()) ? "</span>" : "") 
					+ (defPage == null ? "</span>" : "</a>") 
					+ " xmlns=\"http://www.w3.org/1999/xhtml\"&lt; <span style=\"color: Gray\">&lt;!--</span> <span style=\"color: navy\">"
					+ Utilities.escapeXml(elem.getShortDefn())+getIsSummaryFlag(elem)
					+ "</span><span style=\"color: Gray\">&lt; --&gt;</span> &lt;/div&gt;\r\n");
		}
		// element has a constraint which fixes its value
		else if (elem.hasValue()) {
			if (defPage == null) {
				write(en+"</span>&gt;");
			} else if (elem.isModifier() || elem.isMustSupport())
				write(en + "</span></a>&gt;");
			else
				write(en + "</a>&gt;");

			if (elem.typeCode().equals("CodeableConcept"))
				write(renderCodeableConcept(indent, elem.getValue()) + "&lt;/" + en + "&gt;\r\n");
			else if (elem.typeCode().equals("Quantity"))
				write(renderQuantity(indent, elem.getValue()) + "&lt;" + en + "/&gt;\r\n");
			else
				write(elem.getValue() + "&lt;" + en + "/&gt;\r\n");
		} else {
			write("<b>" + en);
			if (defPage == null) {
				write("</b></span>");
			} else if (elem.isModifier() || elem.isMustSupport())
				write("</b></span></a>");
			else
				write("</b></a>");
			// if (elem.isXmlIDRef())
			// write(" idref=\"<span style=\"color: navy\" title=\""+Utilities.escapeXml(elem.getDefinition())+"\">["+elem.getShortDefn()+"]</span>\"/");
			if (elem.getTypes().size() == 1 && (definitions.getPrimitives().containsKey(elem.typeCode()) || elem.typeCode().equals("idref"))) {
			  doneType = true;
			  TypeRef t = elem.getTypes().get(0);
			  if (elem.typeCode().equals("idref"))
          write(" value=\"[<span style=\"color: darkgreen\"><a href=\"references.htm#idref\">" + t.getName()+ "</a></span>]\"/");
			  else
  			  write(" value=\"[<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(t.getName())+ ".htm#" + t.getName()).replace("[", "_").replace("]", "_") + "\">" + t.getName()+ "</a></span>]\"/");
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
			// write("<a href=\"xml.htm#Control\" class=\"cf\">" +
			// elem.getConformance().code() + "</a>");
			// }

			if (elem.hasAggregation()) {
				write(" aggregated");
			}

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
			} else if (!elem.getTypes().isEmpty()
					&& !(elem.getTypes().size() == 1 && elem.getTypes().get(0)
							.isWildcardType()) && !sharedDT) {
				writeCardinality(elem);
				listed = true;
				if (!doneType) {
				  writeTypeLinks(elem);
				}
			} else if (elem.getName().equals("extension")) {
				write(" <a href=\"extensibility.htm\"><span style=\"color: navy\">See Extensions</span></a> ");
			} else if (elem.getTypes().size() == 1
					&& elem.getTypes().get(0).isWildcardType()) {
				writeCardinality(elem);
		    write(" <span style=\"color: darkgreen\">");
        write("<a href=\"datatypes.htm#open\">*</a>");     
		    write("</span>");
				listed = true;
			}

//			if (!Utilities.noString(elem.getProfile())) {
//	      write(" <a href=\""+elem.getProfile()+"\"><span style=\"color: DarkViolet\">Profile: \""+elem.getProfile().substring(1)+"\"</span></a>");		  
//			}
			write(" ");
			if (elem.getElements().isEmpty() && !sharedDT) {
				if ("See Extensions".equals(elem.getShortDefn())) {
					write(" <a href=\"extensibility.htm\"><span style=\"color: navy\">"
							+ Utilities.escapeXml(elem.getShortDefn())+getIsSummaryFlag(elem)
							+ "</span></a> ");
				} else {
					// if (!elem.isXmlIDRef())
				  if (elem.getMaxCardinality() != null && elem.getMaxCardinality() == 0) 
				    write("<span style=\"text-decoration: line-through\">");
				  BindingSpecification bs = definitions.getBindingByName(elem.getBindingName());
				  if (bs != null && bs.getBinding() != Binding.Unbound && !Utilities.noString(bs.getReference())) { 
				    if (bs.getBinding() == Binding.CodeList || bs.getBinding() == Binding.Special)
				      write("<span style=\"color: navy\"><a href=\""+bs.getReference().substring(1)+".htm\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn())+getIsSummaryFlag(elem) + "</a></span>");
				    else if (bs.getReference().startsWith("http://hl7.org/fhir")) {
				      if (bs.getReference().startsWith("http://hl7.org/fhir/v3/vs/"))
				        write("<a href=\"v3/"+bs.getReference().substring(26)+"/index.htm\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn())+getIsSummaryFlag(elem) + "</a>");
				      else if (bs.getReference().startsWith("http://hl7.org/fhir/vs/"))
				        write("<a href=\""+bs.getReference().substring(23)+".htm\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn())+getIsSummaryFlag(elem) + "</a>");
				      else
				        throw new Exception("Internal reference "+bs.getReference()+" not handled yet");
				    } else
				      write("<span style=\"color: navy\"><a href=\""+bs.getReference()+".htm\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn())+getIsSummaryFlag(elem) + "</a></span>");				  
				  } else
					  write("<span style=\"color: navy\">" + Utilities.escapeXml(elem.getShortDefn())+getIsSummaryFlag(elem) + "</span>");
          if (elem.getMaxCardinality() != null && elem.getMaxCardinality() == 0) 
            write("</span>");
				}
			} else {
				if (elem.unbounded() && !listed) { // isNolist()) {
					if (elem.usesCompositeType()) {
						write(" <span style=\"color: Gray\">&lt;!--");
						writeCardinality(elem);
	          if (elem.getMaxCardinality() != null && elem.getMaxCardinality() == 0) 
	            write("<span style=\"text-decoration: line-through\">");
	          write("" + Utilities.escapeXml(elem.getShortDefn())+getIsSummaryFlag(elem));
	          if (elem.getMaxCardinality() != null && elem.getMaxCardinality() == 0) 
	            write("</span>");
	          write(" --&gt;</span>");
					} else if (elem.hasShortDefn()) {
						write(" <span style=\"color: Gray\">&lt;!--");
						writeCardinality(elem);
	          if (elem.getMaxCardinality() != null && elem.getMaxCardinality() == 0) 
	            write("<span style=\"text-decoration: line-through\">");
	          write(" " + Utilities.escapeXml(elem.getShortDefn())+getIsSummaryFlag(elem));
	          if (elem.getMaxCardinality() != null && elem.getMaxCardinality() == 0) 
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
            if (elem.getMaxCardinality() != null && elem.getMaxCardinality() == 0) 
              write("<span style=\"text-decoration: line-through\">");
            write(" "+Utilities.escapeXml(elem.getShortDefn())+getIsSummaryFlag(elem));
            if (elem.getMaxCardinality() != null && elem.getMaxCardinality() == 0) 
              write("</span>");
            write(" --&gt;</span>");
				}
				write("\r\n");

				if (elem.getMaxCardinality() == null || elem.getMaxCardinality() > 0) {
				  if (sharedDT) {
				    ElementDefn sdt = definitions.getElementDefn(elem
				        .typeCode());
				    for (ElementDefn child : sdt.getElements()) {
				      generateCoreElem(child, indent + 1, rootName, pathName+ "." + en); //sdt.getName());
				    }
				  } else {
				    for (ElementDefn child : elem.getElements()) {
				      generateCoreElem(child, indent + 1, rootName, pathName + "." + en);
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

  private String getIsSummaryFlag(ElementDefn elem) {
    if (elem.isSummaryItem())
      return "<span title=\"This element is included in a summary view (See Search/Query)\" style=\"color: Navy\"> §</span>";
    else 
      return "";
  }

  private void writeTypeLinks(ElementDefn elem) throws Exception {
    write(" <span style=\"color: darkgreen\">");
    int i = 0;
    int d = elem.getTypes().size() / 2;
    for (TypeRef t : elem.getTypes()) {
      if (i > 0)
        write("|");
      if (elem.getTypes().size() > 5 && i == d)
        write("\r\n              ");
      if (t.isXhtml() || t.getName().equals("list"))
        write(t.getName());
      else if (t.getName().equals("Extension") && t.getParams().size() == 0 && !Utilities.noString(elem.getProfile()))
        write("<a href=\""+elem.getProfile()+"\"><span style=\"color: DarkViolet\">@"+elem.getProfile().substring(1)+"</span></a>");     
      else
        write("<a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(t.getName())
            + ".htm#" + t.getName() + "\">" + t.getName()).replace("[", "_").replace("]", "_")
            + "</a>");
      if (t.hasParams()) {
        write("(");
        boolean firstp = true;
        for (String p : t.getParams()) {
          if (!firstp)
            write("|");

          // TODO: There has to be an aggregation
          // specification per t.getParams()
          if (elem.hasAggregation()) {
            // TODO: This should link to the documentation
            // of the profile as specified
            // in the aggregation. For now it links to the
            // base resource.
            write("<a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(p)
                + ".htm#" + p + "\">"
                + elem.getAggregation()).replace("[", "_").replace("]", "_") + "</a>");
          } 
          else if( definitions.getFutureResources().containsKey(p)) 
              write("<a title=\"This resource has not been defined yet\">" + p + "</a>");                
          else if (p.equals("Any")) {
            write("<a href=\"" + "resourcelist.htm" + "\">" + p + "</a>");								
          }
          else if (t.getName().equals("Resource") && t.getParams().size() == 1 && !Utilities.noString(elem.getProfile()))
            write("<a href=\""+elem.getProfile()+"\"><span style=\"color: DarkViolet\">@"+elem.getProfile().substring(1)+"</span></a>");     
          else
            write("<a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(p)
                + ".htm#" + p).replace("[", "_").replace("]", "_") + "\">" + p + "</a>");

          firstp = false;
        }
        write(")");
      }

      i++;
    }
    write("</span>");
  }

	private void writeCardinality(ElementDefn elem) throws IOException {
		if (elem.getStatedInvariants().size() > 0)
			write(" <span style=\"color: deeppink\" title=\""
					+ Utilities.escapeXml(getInvariants(elem)) + "\"><b>"
					+ elem.describeCardinality() + "</b></span>");
		else
			write(" <span style=\"color: brown\"><b>"
					+ elem.describeCardinality() + "</b></span>");
	}

	private String getInvariants(ElementDefn elem) {
		StringBuilder b = new StringBuilder();
		boolean first = true;
		for (Invariant i : elem.getStatedInvariants()) {
			if (!first)
				b.append("; ");
			first = false;
			b.append("Inv-"+i.getId()+": "+i.getEnglish());
		}

		return b.toString();
	}

	// code ### | text
	private String renderCodeableConcept(int indent, String value)
			throws Exception {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < indent; i++)
			s.append(" ");
		String ind = s.toString();
		s = new StringBuilder();
		String[] parts = value.split("\\|");
		

		if (parts[0].length() > 0) {
		  String[] parts2 = parts[0].split("#");
		  s.append("\r\n" + ind + "  &lt;coding&gt;");
		  if (parts2.length > 0 && parts2[0].length() > 0)
		    s.append("\r\n" + ind + "    &lt;code value=\"" + parts2[0] + "\"/&gt;");
      if (parts2.length > 1 && parts2[1].length() > 0)
		    s.append("\r\n" + ind + "    &lt;system value=\"" + parts2[1] + "\"/&gt;");
      if (parts2.length > 2 && parts2[2].length() > 0)
		    s.append("\r\n" + ind + "    &lt;display value=\"" + parts2[2] + "\"/&gt;");
      s.append("\r\n" + ind + "  &lt;/coding&gt;");
	  }
    if (parts.length > 1 && parts[1].length() > 0)
      s.append("\r\n" + ind + "  &lt;text value=\"" + parts[1] + "\"/&gt;");
		s.append("\r\n" + ind);
		return s.toString();
	}

	private String renderQuantity(int indent, String value) throws Exception {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < indent; i++)
			s.append(" ");
		String ind = s.toString();
		s = new StringBuilder();
		String f = null;
		if (!Character.isDigit(value.charAt(0))) {
			f = value.substring(0, 1);
			value = value.substring(1);
		}
		String[] parts = value.split(" ");
		if (parts.length != 2)
			throw new Exception("unable to parse fixed quantity value " + value);
		String v = parts[0];
		String u = parts[1];
		s.append("\r\n" + ind + "  &lt;value&gt;" + v + "&lt;/value&gt;");
		if (f != null)
			s.append("\r\n" + ind + "  &lt;status&gt;"
					+ Utilities.escapeXml(Utilities.escapeXml(f))
					+ "&lt;/status&gt;");
		s.append("\r\n" + ind + "  &lt;units&gt;" + u + "&lt;/units&gt;");
		s.append("\r\n" + ind + "  &lt;code&gt;" + u + "&lt;/code&gt;");
		s.append("\r\n" + ind
				+ "  &lt;system&gt;urn:hl7-org:sid/ucum&lt;/system&gt;");
		s.append("\r\n" + ind);
		return s.toString();
	}


}
