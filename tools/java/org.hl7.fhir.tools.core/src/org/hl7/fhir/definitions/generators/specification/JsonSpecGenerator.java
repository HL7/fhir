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
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.ExtensionDefinition;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.Utilities;

public class JsonSpecGenerator extends OutputStreamWriter {

  private String defPage;
  private String dtRoot;
  private Definitions definitions;
  private PageProcessor page;

  public JsonSpecGenerator(OutputStream out, String defPage, String dtRoot,
      PageProcessor page) throws UnsupportedEncodingException {
    super(out, "UTF-8");
    this.defPage = defPage;
    this.dtRoot = dtRoot == null ? "" : dtRoot;
    this.definitions = page.getDefinitions();
    this.page = page;
  }

  public void generate(ElementDefn root, boolean resource, boolean isAbstract) throws Exception {
    write("<pre class=\"spec\">\r\n");

    generateInner(root, resource, isAbstract);

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
    write("// "+Utilities.escapeXml(ed.getName()));
    write("<span style=\"float: right\"><a title=\"Documentation for this format\" href=\"json.html\"><img src=\"help.png\" alt=\"doco\"/></a></span>\r\n");
    String rn = ed.getElement().get(0).getIsModifier() ? "modifierExtension" : "extension";

    write("\r\n{\r\n");
    if (defPage == null)
      write("  \"resourceType\" : <span title=\"" + Utilities.escapeXml(root.getFormal())
          + "\"<b>");
    else
      write("  \"resourceType\" : <a href=\"" + (defPage + "#" + root.getName()).replace("[", "_").replace("]", "_") + "\" title=\""
          + Utilities.escapeXml(root.getFormal())
          + "\" class=\"dict\"><b>");
    write(rn);
    if ((defPage == null))
      write("</b></span>");
    else
      write("</b></a>");

    generateExtensionAttribute(ed);
    write(" \",\r\n");

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
    if (root.getTypes().size() > 0 && (root.getTypes().get(0).getName().equals("Type")
        || root.getName().equals("Extension") || (root.getTypes().get(0).getName().equals("Structure")) && root.getName().equals("Element") || root.getName().equals("BackboneElement")))
      rn = null;
    else if (isAbstract)
      rn = "[name]";
    else
      rn = root.getName();

    
    if (root.getName().equals("Extension")) 
      write("<span style=\"float: right\"><a title=\"Documentation for this format\" href=\"json.html\"><img src=\"help.png\" alt=\"doco\"/></a></span>\r\n");
    else
      write("{<span style=\"float: right\"><a title=\"Documentation for this format\" href=\"json.html\"><img src=\"help.png\" alt=\"doco\"/></a></span>\r\n");
    if (rn != null) {
      write("  \"resourceType\" : \"");
      if (defPage == null)
        write("<span title=\"" + Utilities.escapeXml(root.getDefinition())
            + "\"><b>");
      else
        write("<a href=\"" + (defPage + "#" + root.getName()).replace("[", "_").replace("]", "_") + "\" title=\""
            + Utilities.escapeXml(root.getDefinition())
            + "\" class=\"dict\"><b>");
      write(rn);
      if ((defPage == null))
        write("</b></span>\",\r\n");
      else
        write("</b></a>\",\r\n");
    }

    if ((root.getName().equals(rn) || "[name]".equals(rn)) && resource) {
      if (!Utilities.noString(root.typeCode())) {
        write("  // from <a href=\"resource.html\">Resource</a>: <a href=\"resource.html#id\">id</a>, <a href=\"resource.html#meta\">meta</a>, <a href=\"resource.html#implicitRules\">implicitRules</a>, and <a href=\"resource.html#language\">language</a>\r\n");
        if (root.typeCode().equals("DomainResource"))
          write("  // from <a href=\"domainresource.html\">DomainResource</a>: <a href=\"narrative.html#Narrative\">text</a>, <a href=\"references.html#contained\">contained</a>, <a href=\"extensibility.html\">extension</a>, and <a href=\"extensibility.html#modifierExtension\">modifierExtension</a>\r\n");
      }
    } else if (!resource) {
      write("  // from Element: <a href=\"extensibility.html\">extension</a>\r\n");
    }
    if (root.getName().equals("Extension")) {
      ElementDefn urld = root.getElements().get(0);
      write("  \"<span title=\"" + Utilities.escapeXml(urld.getDefinition()) + "\"><a href=\"" + (defPage + "#Extension.url") + "\" title=\"" + Utilities.escapeXml(urld.getDefinition()) + "\" class=\"dict\">");
      write("&lt;url&gt;</a></span>");
      write("\" : { // <span style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(root.getElements().get(0).getShortDefn()) + "</span>\r\n");
      generateCoreElem(root.getElements().get(1), 2, rn, root.getName(), false, true);      
      write("  }\r\n");
    } else {
      int c = 0;
      for (ElementDefn elem : root.getElements()) {
        generateCoreElem(elem, 1, rn, root.getName(), root.getName().equals(rn) && resource, ++c == root.getElements().size());
      }
    }

    write("}\r\n");
  }

  private void generateExtensionAttribute(ExtensionDefinition ed) throws Exception {
    write(" url=\"");
    write("<span style=\"color: navy; opacity: 0.8\">" + ed.getUrl()+"</span>");
    write("\"");
  }


  private String upFirst(String s) {
    return s.substring(0, 1).toUpperCase() + s.substring(1);
  }


  private void generateCoreElem(ElementDefn elem, int indent,	String rootName, String pathName, boolean backbone, boolean last) throws Exception {
    // base pattern: "name" : "value" // optionality documentation

    int width = 0;

    // If this is an unrolled element, show its profile name
    if (elem.getProfileName() != null && !elem.getProfileName().equals("")) {
      for (int i = 0; i < indent; i++)
        write("  ");
      write("<span style=\"color: Gray\">// </span><span style=\"color: blue\">\"" + elem.getProfileName() + "\":</span>\r\n");
    }

    if (elem.getMaxCardinality() != null && elem.getMaxCardinality() == 0) 
      write("<span style=\"text-decoration: line-through\">");

    String en = elem.getName();

    if (en.contains("[x]") && elem.getTypes().size() == 1	&& !elem.getTypes().get(0).isWildcardType())
      en = en.replace("[x]", elem.typeCode());

    if (en.contains("[x]")) {
      // 1. name
      for (int i = 0; i < indent; i++) {
        write("  ");
      }
      if (elem.getTypes().size() > 1) { 	  
        write("<span style=\"color: Gray\">// "+en+": <span style=\"color: navy; opacity: 0.8\">" + docPrefix(width, indent, elem)+Utilities.escapeXml(elem.getShortDefn()) + "</span>. One of these "+Integer.toString(elem.getTypes().size())+":</span>\r\n");
        for (TypeRef t : elem.getTypes())
          generateCoreElemDetails(elem, indent, rootName, pathName, backbone, last, width, en.replace("[x]", upFirst(t.getName())), t, false);
      } else {
        List<TypeRef> tr = getWildcardTypes();
        write("<span style=\"color: Gray\">// "+en+": <span style=\"color: navy; opacity: 0.8\">" + docPrefix(width, indent, elem)+Utilities.escapeXml(elem.getShortDefn()) + "</span>. One of these "+Integer.toString(tr.size())+":</span>\r\n");
        for (TypeRef t : tr)
          generateCoreElemDetails(elem, indent, rootName, pathName, backbone, last, width, en.replace("[x]", upFirst(t.getName())), t, false);	      
      }
    } else {
      generateCoreElemDetails(elem, indent, rootName, pathName, backbone, last, width, en, elem.getTypes().isEmpty() ? null : elem.getTypes().get(0), true);
    }
  }

  private List<TypeRef> getWildcardTypes() {
    List<TypeRef> tr = new ArrayList<TypeRef>();
    tr.add(new TypeRef().setName("integer"));
    tr.add(new TypeRef().setName("decimal"));
    tr.add(new TypeRef().setName("dateTime"));
    tr.add(new TypeRef().setName("date"));
    tr.add(new TypeRef().setName("instant"));
    tr.add(new TypeRef().setName("string"));
    tr.add(new TypeRef().setName("uri"));
    tr.add(new TypeRef().setName("boolean"));
    tr.add(new TypeRef().setName("code"));
    tr.add(new TypeRef().setName("base64Binary"));
    tr.add(new TypeRef().setName("Coding"));
    tr.add(new TypeRef().setName("CodeableConcept"));
    tr.add(new TypeRef().setName("Attachment"));
    tr.add(new TypeRef().setName("Identifier"));
    tr.add(new TypeRef().setName("Quantity"));
    tr.add(new TypeRef().setName("Range"));
    tr.add(new TypeRef().setName("Period"));
    tr.add(new TypeRef().setName("Ratio"));
    tr.add(new TypeRef().setName("HumanName"));
    tr.add(new TypeRef().setName("Address"));
    tr.add(new TypeRef().setName("Contact"));
    tr.add(new TypeRef().setName("Schedule"));
    tr.add(new TypeRef().setName("Reference"));
    return tr;
  }

  private void generateCoreElemDetails(ElementDefn elem, int indent, String rootName, String pathName, boolean backbone, boolean last, int width, String en, TypeRef type, boolean doco) throws Exception {
    if (elem.getName().equals("extension")) {
      write("  (<a href=\"extensibility.html\">Extensions</a> - see <a href=\"json.html#extensions\">JSON page</a>)\r\n");
      return;
    }
    if (elem.getName().equals("modifierExtension")) {
      write("  (<a href=\"extensibility.html#modifier\">Modifier Extensions</a> - see <a href=\"json.html#modifier\">JSON page</a>)\r\n");
      return;
    }
    
    // 1. name
    for (int i = 0; i < indent; i++) {
      write("  ");
    }
    
    if (defPage == null) {
      if (elem.isModifier() || elem.isMustSupport())
        write("\"<span style=\"text-decoration: underline\" title=\"" + Utilities.escapeXml(elem.getEnhancedDefinition())	+ "\">");
      else
        write("\"<span title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\">");
    } else if (elem.isModifier() || elem.isMustSupport()) 
      write("\"<a href=\"" + (defPage + "#" + pathName + "." + en).replace("[", "_").replace("]", "_")+ "\" title=\"" + Utilities .escapeXml(elem.getEnhancedDefinition()) 
          + "\" class=\"dict\"><span style=\"text-decoration: underline\">");
    else
      write("\"<a href=\"" + (defPage + "#" + pathName + "." + en).replace("[", "_").replace("]", "_") + "\" title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\" class=\"dict\">");
    
    if (defPage == null) {
      write(en+"</span>");
    } else if (elem.isModifier() || elem.isMustSupport())
      write(en + "</span></a>");
    else
      write(en + "</a>");

    write("\" : ");

    // 2. value
    boolean delayedCloseArray = false;
    boolean sharedDT = definitions.dataTypeIsSharedInfo(elem.typeCode());
    if (elem.getMaxCardinality() == null || elem.getMaxCardinality() > 1) 
      write("[");

    if (elem.usesCompositeType()) {
      write("{ <span style=\"color: darkgreen\">");
      write("Content as for " + elem.typeCode().substring(1)
          + "</span> }");
    } else if (type == null) {
      // inline definition
      assert(elem.getElements().size() > 0);
      write("{");
      delayedCloseArray = true;
    } else if (type.isXhtml()) {
      // element contains xhtml
      write("\"(Escaped XHTML)\"");
    } else if (definitions.getPrimitives().containsKey(type.getName())) {
      if (!(type.getName().equals("integer") || type.getName().equals("boolean") || type.getName().equals("decimal")))
        write("\"");
      write("&lt;<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(type.getName(), false)+ ".html#" + type.getName()).replace("[", "_").replace("]", "_") + "\">" + type.getName()+ "</a></span>&gt;");
      if (!(type.getName().equals("integer") || type.getName().equals("boolean") || type.getName().equals("decimal")))
        write("\"");
    } else {
      write("{");
      width = writeTypeLinks(elem, indent, type);
      write(" }");
    } 

    if (!delayedCloseArray && (elem.getMaxCardinality() == null || elem.getMaxCardinality() > 1)) 
      write("]");
    if (!last && !delayedCloseArray)
      write(",");

    if (!elem.hasFixed() && doco) {
      write(" <span style=\"color: Gray\">//</span>");

      // 3. optionality
      writeCardinality(elem);

      // 4. doco
      write(" ");

      if (elem.getName().equals("extension")) {
        write(" <a href=\"extensibility.html\"><span style=\"color: navy; opacity: 0.8\">See Extensions</span></a>");
      } else if ("See Extensions".equals(elem.getShortDefn())) {
        write(" <a href=\"extensibility.html\"><span style=\"color: navy; opacity: 0.8\">"
            + Utilities.escapeXml(elem.getShortDefn())
            + "</span></a>");
      } else {
        BindingSpecification bs = definitions.getBindingByName(elem.getBindingName());
        if (bs != null && bs.getBinding() != Binding.Unbound && !Utilities.noString(bs.getReference())) { 
          if (bs.getBinding() == Binding.CodeList || bs.getBinding() == Binding.Special)
            write("<span style=\"color: navy; opacity: 0.8\"><a href=\""+bs.getReference().substring(1)+".html\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a></span>");
          else if (bs.getReference().startsWith("http://hl7.org/fhir")) {
            if (bs.getReference().startsWith("http://hl7.org/fhir/v3/vs/")) {
              ValueSet vs = page.getValueSets().get(bs.getReference()); // night be null in a partial build
              String pp = (String) vs.getUserData("path");
              write("<a href=\""+(vs == null ? "??" : pp.replace(File.separatorChar, '/'))+"\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
            } else if (bs.getReference().startsWith("http://hl7.org/fhir/v2/vs/")) {
              ValueSet vs = page.getValueSets().get(bs.getReference());
              String pp = (String) vs.getUserData("path");
              write("<a href=\""+(vs == null ? "??" : pp.replace(File.separatorChar, '/'))+"\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn())+ "</a>");
            } else if (bs.getReference().startsWith("http://hl7.org/fhir/vs/")) {
              BindingSpecification bs1 = page.getDefinitions().getBindingByReference("#"+bs.getReference().substring(23), bs);
              if (bs1 != null)
                write("<a href=\""+bs.getReference().substring(23)+".html\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
              else
                write("<a href=\"valueset-"+bs.getReference().substring(23)+".html\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
            } else
              throw new Exception("Internal reference "+bs.getReference()+" not handled yet");
          } else
            write("<span style=\"color: navy; opacity: 0.8\"><a href=\""+bs.getReference()+".html\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a></span>");				  
        } else
          write("<span style=\"color: navy; opacity: 0.8\">" + docPrefix(width, indent, elem)+Utilities.escapeXml(elem.getShortDefn()) + "</span>");
      }
    }
    if (elem.getMaxCardinality() != null && elem.getMaxCardinality() == 0) 
      write("</span>");

    if (elem.getElements().size() > 0) {
      write("\r\n");
      int c = 0;
      for (ElementDefn child : elem.getElements()) {
        generateCoreElem(child, indent + 1, rootName, pathName + "." + en, backbone, ++c == elem.getElements().size());
      }

      for (int i = 0; i < indent; i++) {
        write("  ");
      }
    }
    if (elem.getElements().size() > 0) {
      write("}");
      if (delayedCloseArray && elem.getMaxCardinality() == null || elem.getMaxCardinality() > 1) 
        write("]");
      if (!last && delayedCloseArray)
        write(",");
    }

    write("\r\n");
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
    write(indentS+"// from Element: <a href=\"extensibility.html\">extension</a> -->\r\n");

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
        write("<b>extension"+closeOut+" url=\"<span style=\"color: navy; opacity: 0.8\">"+tail(elem.getPath())+"</span>\"");
        write("&gt; <span style=\"color: Gray\">&lt;!--</span>");
        writeCardinality(elem);
        write(" ");

        if (elem.hasBinding() && elem.getBinding().hasReference()) {
          ValueSet vs = resolveValueSet(elem.getBinding().getReference());
          if (vs != null)
            write("<span style=\"color: navy; opacity: 0.8\"><a href=\""+vs.getUserData("filename")+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");
          else
            write("<span style=\"color: navy; opacity: 0.8\"><a href=\""+elem.getBinding().getReference()+".html\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");          
        } else
          write("<span style=\"color: navy; opacity: 0.8\">" + docPrefix(width, indent, elem)+Utilities.escapeXml(elem.getShort()) + "</span>");
        write(" <span style=\"color: Gray\">--!&gt; </span>\r\n");
        indentS += " ";
      } else 
        indentS = ""; // already written

      if (elem.getType().size() == 1 && (definitions.getPrimitives().containsKey(elem.getType().get(0).getCode()))) {
        write(indentS+"&lt;value");
        doneType = true;
        write(Utilities.capitalize(elem.getType().get(0).getCode())+" value=\"[<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(elem.getType().get(0).getCode(), false)+ ".html#" + elem.getType().get(0).getCode()) + "\">" + elem.getType().get(0).getCode()+ "</a></span>]\"/&gt;");
      } else if (elem.getType().size() > 1) {
        write(indentS+"&lt;value[x]> ");
        write("<span style=\"color: Gray\">&lt;!--</span> ");
        boolean first = true;
        for (TypeRefComponent t : elem.getType()) {
          if (first)
            first = false;
          else
            write(" | ");
          write("<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(t.getCode(), false)+ ".html#" + t) + "\">" + t+ "</a></span>");
        }
        write("<span style=\"color: Gray\"> --!&gt; </span>");
        write("/&lt;value[x]> ");
      } else if (elem.getType().size() == 1) {
        write(indentS+"&lt;value");
        write(Utilities.capitalize(elem.getType().get(0).getCode())+"&gt; <span style=\"color: Gray\">&lt;!--</span> <span style=\"color: darkgreen\"><a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(elem.getType().get(0).getCode(), false)+ ".html#" + elem.getType().get(0).getCode()) + "\">" + elem.getType().get(0).getCode()+ "</a></span><span style=\"color: Gray\"> --!&gt; </span>");
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
        write(" value=\"[<span style=\"color: darkgreen\"><a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(elem.getType().get(0).getCode(), false)+ ".html#" + elem.getType().get(0).getCode()) + "\">" + elem.getType().get(0).getCode()+ "</a></span>]\"/");
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
        write(" <a href=\"extensibility.html\"><span style=\"color: navy; opacity: 0.8\">See Extensions</span></a> ");
      } else {
        write(" <a href=\"none.html\"><span style=\"color: navy; opacity: 0.8\">No Types?</span></a> ");
      }

      write(" ");
      if (children.isEmpty()) {
        if (name.equals("extension") || name.equals("modifierExtension")) {
          write(" <a href=\"extensibility.html\"><span style=\"color: navy; opacity: 0.8\">"
              + Utilities.escapeXml(elem.getShort())
              + "</span></a> ");
        } else {
          if (elem.hasMax() && elem.getMax().equals("0")) 
            write("<span style=\"text-decoration: line-through\">");
          if (elem.hasBinding() && elem.getBinding().hasReference()) {
            ValueSet vs = resolveValueSet(elem.getBinding().getReference());
            if (vs != null)
              write("<span style=\"color: navy; opacity: 0.8\"><a href=\""+vs.getUserData("filename")+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");
            else
              write("<span style=\"color: navy; opacity: 0.8\"><a href=\""+elem.getBinding().getReference()+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");          
          } else
            write("<span style=\"color: navy; opacity: 0.8\">" + docPrefix(width, indent, elem)+Utilities.escapeXml(elem.getShort()) + "</span>");
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
    //                write("<a href=\""+(vs == null ? "??" : vs.getLinks().get("path").replace(File.separatorChar, '/'))+"\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
    //              } else if (bs.getReference().startsWith("http://hl7.org/fhir/v2/vs/")) {
    //                  AtomEntry<ValueSet> vs = page.getValueSets().get(bs.getReference());
    //                  write("<a href=\""+(vs == null ? "??" : vs.getLinks().get("path").replace(File.separatorChar, '/'))+"\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn())+ "</a>");
    //              } else if (bs.getReference().startsWith("http://hl7.org/fhir/vs/")) {
    //                BindingSpecification bs1 = page.getDefinitions().getBindingByReference("#"+bs.getReference().substring(23), bs);
    //                if (bs1 != null)
    //                  write("<a href=\""+bs.getReference().substring(23)+".html\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
    //                else
    //                  write("<a href=\"valueset-"+bs.getReference().substring(23)+".html\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a>");
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

  private int writeTypeLinks(ElementDefn elem, int indent, TypeRef t) throws Exception {
    write(" <span style=\"color: darkgreen\">");
    int i = 0;
    int w = indent + 12 + elem.getName().length(); // this is wrong if the type is an attribute, but the wrapping concern shouldn't apply in this case, so this is ok
//     for (TypeRef t : elem.getTypes()) {
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
        write("<a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(t.getName(), false)
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
            write("<a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(p, false)
                + ".html#" + p).replace("[", "_").replace("]", "_") + "\">" + p + "</a>");

          firstp = false;
        }
        write(")");
        w++;
      }

      i++;
    //}
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
        write("<a href=\"" + (dtRoot + GeneratorUtils.getSrcFile(t.getCode(), false)
            + ".html#" + t.getCode() + "\">" + t.getCode())
            + "</a>");
      i++;
    }
    write("</span>");
    return w;
  }

  private void writeCardinality(ElementDefn elem) throws IOException {
    if (elem.getStatedInvariants().size() > 0)
      write(" <span style=\"color: brown\" title=\""+Utilities.escapeXml(getInvariants(elem))+ "\"><b>C?</b></span>"); 
    if (elem.getMinCardinality() > 0)
      write(" <span style=\"color: brown\" title=\"This element is required\"><b>R!</b></span>");
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
      b.append("Inv-"+i.getId()+": "+i.getEnglish());
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
      b.append("Inv-"+i.getKey()+": "+i.getHuman());
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

}
