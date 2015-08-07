package org.hl7.fhir.definitions.generators.specification;

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
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;

public class JsonSpecGenerator extends OutputStreamWriter {

  private String defPage;
  private String dtRoot;
  private Definitions definitions;
  private PageProcessor page;
  private String prefix;

  public JsonSpecGenerator(OutputStream out, String defPage, String dtRoot, PageProcessor page, String prefix) throws UnsupportedEncodingException {
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

  public void generate(ElementDefn root, boolean resource, boolean isAbstract) throws Exception {
    write("<pre class=\"spec\">\r\n");

    generateInner(root, resource, isAbstract);

    write("</pre>\r\n");
    flush();
    close();
  }

  public void generateExtension(StructureDefinition ed) throws Exception {
    write("<pre class=\"spec\">\r\n");

    generateInner(ed, true);

    write("</pre>\r\n");
    flush();
    close();
  }

  private void generateInner(StructureDefinition ed, boolean extensionDefinition) throws IOException, Exception {
    ElementDefinition root = ed.getSnapshot().getElement().get(0);
    String rn = ed.getSnapshot().getElement().get(0).getIsModifier() ? "modifierExtension" : "extension";
    write("{ // <span style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(ed.getName()) + "</span>\r\n");
    
    List<ElementDefinition> children = getChildren(ed.getSnapshot().getElement(), ed.getSnapshot().getElement().get(0));
    boolean complex = isComplex(children) && extensionDefinition;
    if (!complex)
      write("  // from Element: <a href=\""+prefix+"extensibility.html\">extension</a>\r\n");
    
    int c = 0;
    int l = lastChild(children);
    for (ElementDefinition child : children)
      if (child.hasSlicing())
        generateCoreElemSliced(ed.getSnapshot().getElement(), child, children, 2, rn, false, child.getType().get(0), ++c == l, complex);
      else if (wasSliced(child, children))
        ; // nothing
      else if (child.getType().size() == 1)
        generateCoreElem(ed.getSnapshot().getElement(), child, 2, rn, false, child.getType().get(0), ++c == l, complex);
      else {
        write("<span style=\"color: Gray\">// value[x]: <span style=\"color: navy; opacity: 0.8\">" +Utilities.escapeXml(child.getShort()) + "</span>. One of these "+Integer.toString(child.getType().size())+":</span>\r\n");
        for (TypeRefComponent t : child.getType())
          generateCoreElem(ed.getSnapshot().getElement(), child, 2, rn, false, t, ++c == l, false);
      }
    write("  }\r\n");
  }

  private boolean wasSliced(ElementDefinition child, List<ElementDefinition> children) {
    String path = child.getPath();
    for (ElementDefinition c : children) {
      if (c == child)
        break;
      if (c.getPath().equals(path) && c.hasSlicing())
        return true;
    }
    return false;
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
    if (root.getTypes().size() > 0 && (root.getTypes().get(0).getName().equals("Type")
        || root.getName().equals("Extension") || (root.getTypes().get(0).getName().equals("Structure")) && root.getName().equals("Element") || root.getName().equals("BackboneElement") || root.getName().equals("Meta")))
      rn = null;
    else if (isAbstract)
      rn = "[name]";
    else
      rn = root.getName();

    
    write("{<span style=\"float: right\"><a title=\"Documentation for this format\" href=\""+prefix+"json.html\"><img src=\"help.png\" alt=\"doco\"/></a></span>\r\n");
    if (rn != null) {
      write("  \"resourceType\" : \"");
      if (defPage == null)
        write("<span title=\"" + Utilities.escapeXml(root.getDefinition())
            + "\"><b>");
      else
        write("<a href=\"" + (defPage + "#" + root.getName()) + "\" title=\""
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
        write("  // from <a href=\""+prefix+"resource.html\">Resource</a>: <a href=\""+prefix+"resource.html#id\">id</a>, <a href=\""+prefix+"resource.html#meta\">meta</a>, <a href=\""+prefix+"resource.html#implicitRules\">implicitRules</a>, and <a href=\""+prefix+"resource.html#language\">language</a>\r\n");
        if (root.typeCode().equals("DomainResource"))
          write("  // from <a href=\""+prefix+"domainresource.html\">DomainResource</a>: <a href=\""+prefix+"narrative.html#Narrative\">text</a>, <a href=\""+prefix+"references.html#contained\">contained</a>, <a href=\""+prefix+"extensibility.html\">extension</a>, and <a href=\""+prefix+"extensibility.html#modifierExtension\">modifierExtension</a>\r\n");
      }
    } else if (!resource) {
      write("  // from Element: <a href=\""+prefix+"extensibility.html\">extension</a>\r\n");
    }
//    if (root.getName().equals("Extension")) {
//      ElementDefn urld = root.getElements().get(0);
//      write("  \"<span title=\"" + Utilities.escapeXml(urld.getDefinition()) + "\"><a href=\"" + (defPage + "#Extension.url") + "\" title=\"" + Utilities.escapeXml(urld.getDefinition()) + "\" class=\"dict\">");
//      write("&lt;url&gt;</a></span>");
//      write("\" : { // <span style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(root.getElements().get(0).getShortDefn()) + "</span>\r\n");
//      generateCoreElem(root.getElements().get(1), 2, rn, root.getName(), false, true);      
//      write("  }\r\n");
//    } else {
      int c = 0;
      for (ElementDefn elem : root.getElements()) {
        generateCoreElem(elem, 1, rn, root.getName(), root.getName().equals(rn) && resource, ++c == root.getElements().size());
//      }
    }

    write("}\r\n");
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
          generateCoreElemDetails(elem, indent, rootName, pathName, backbone, last, width, en.replace("[x]", nameForType(t.getName())), t, false);
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

  private CharSequence nameForType(String type) {
    if (definitions.getConstraints().containsKey(type))
      return definitions.getConstraints().get(type).getBaseType();
    else 
      return Utilities.capitalize(type);
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
    tr.add(new TypeRef().setName("ContactPoint"));
    tr.add(new TypeRef().setName("Schedule"));
    tr.add(new TypeRef().setName("Reference"));
    return tr;
  }

  private void generateCoreElemDetails(ElementDefn elem, int indent, String rootName, String pathName, boolean backbone, boolean last, int width, String en, TypeRef type, boolean doco) throws Exception {
    if (elem.getName().equals("extension")) {
      write("  (<a href=\""+prefix+"extensibility.html\">Extensions</a> - see <a href=\""+prefix+"json.html#extensions\">JSON page</a>)\r\n");
      return;
    }
    if (elem.getName().equals("modifierExtension")) {
      write("  (<a href=\""+prefix+"extensibility.html#modifier\">Modifier Extensions</a> - see <a href=\""+prefix+"json.html#modifier\">JSON page</a>)\r\n");
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
    } else if (elem.isModifier() || elem.getMustSupport()) 
      write("\"<a href=\"" + (defPage + "#" + pathName + "." + en)+ "\" title=\"" + Utilities .escapeXml(elem.getEnhancedDefinition()) 
          + "\" class=\"dict\"><span style=\"text-decoration: underline\">");
    else
      write("\"<a href=\"" + (defPage + "#" + pathName + "." + en) + "\" title=\"" + Utilities.escapeXml(elem.getDefinition()) + "\" class=\"dict\">");
    
    if (defPage == null) {
      write(en+"</span>");
    } else if (elem.isModifier() || elem.getMustSupport())
      write(en + "</span></a>");
    else
      write(en + "</a>");

    write("\" : ");

    // 2. value
    boolean delayedCloseArray = false;
    boolean sharedDT = definitions.dataTypeIsSharedInfo(elem.typeCode());
    if (elem.getMaxCardinality() != null && elem.getMaxCardinality() > 1) 
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
      write("&lt;<span style=\"color: darkgreen\"><a href=\"" + prefix+(dtRoot + definitions.getSrcFile(type.getName())+ ".html#" + type.getName()) + "\">" + type.getName()+ "</a></span>&gt;");
      if (!(type.getName().equals("integer") || type.getName().equals("boolean") || type.getName().equals("decimal")))
        write("\"");
    } else {
      write("{");
      width = writeTypeLinks(elem, indent, type);
      write(" }");
    } 

    if (!delayedCloseArray && (elem.getMaxCardinality() != null && elem.getMaxCardinality() > 1)) 
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
        write(" <a href=\""+prefix+"extensibility.html\"><span style=\"color: navy; opacity: 0.8\">See Extensions</span></a>");
      } else if ("See Extensions".equals(elem.getShortDefn())) {
        write(" <a href=\""+prefix+"extensibility.html\"><span style=\"color: navy; opacity: 0.8\">"
            + Utilities.escapeXml(elem.getShortDefn())
            + "</span></a>");
      } else {
        String ref = getBindingLink(elem);
        write("<span style=\"color: navy; opacity: 0.8\"><a href=\""+prefix+ref+"\" style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShortDefn()) + "</a></span>");
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
      if (delayedCloseArray && elem.getMaxCardinality() != null && elem.getMaxCardinality() > 1) 
        write("]");
      if (!last && delayedCloseArray)
        write(",");
    }

    write("\r\n");
  }

  private void generateCoreElemSliced(List<ElementDefinition> elements, ElementDefinition elem, List<ElementDefinition> children, int indent, String pathName, boolean asValue, TypeRefComponent type, boolean last, boolean complex) throws Exception {
    String name =  tail(elem.getPath());
    String en = asValue ? "value[x]" : name;
    if (en.contains("[x]"))
      en = en.replace("[x]", upFirst(type.getCode()));
    boolean unbounded = elem.hasMax() && elem.getMax().equals("*");

    if (!unbounded)
      throw new Exception("slicing on non-lists not supported yet");
    
    String indentS = "";
    for (int i = 0; i < indent; i++) {
      indentS += "  ";
    }
    write(indentS);
    write("\"<a href=\"" + (defPage + "#" + pathName + "." + en)+ "\" title=\"" + Utilities .escapeXml(getEnhancedDefinition(elem)) 
    + "\" class=\"dict\"><span style=\"text-decoration: underline\">"+en+"</span></a>\" : ");
    write("[ // <span style=\"color: navy\">"+describeSlicing(elem.getSlicing())+"</span>");
//    write(" <span style=\"color: Gray\">//</span>");
//    writeCardinality(elem);
    write("\r\n");
    
    List<ElementDefinition> slices = getSlices(elem, children);
    int c = 0;
    for (ElementDefinition slice : slices) {
      write(indentS+"  ");
      write("{ // <span style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(slice.getShort()) + "</span>");
      write(" <span style=\"color: Gray\">//</span>");
      writeCardinality(slice);
      write("\r\n");
      
      List<ElementDefinition> extchildren = getChildren(elements, slice);
      boolean extcomplex = isComplex(extchildren) && complex;
      if (!extcomplex) {
        write(indentS+"  ");
        write("  // from Element: <a href=\""+prefix+"extensibility.html\">extension</a>\r\n");
      }
      
      int cc = 0;
      int l = lastChild(extchildren);
      for (ElementDefinition child : extchildren)
        if (child.hasSlicing())
          generateCoreElemSliced(elements, child, children, indent+2, pathName+"."+en, false, child.getType().get(0), ++cc == l, extcomplex);
        else if (wasSliced(child, children))
          ; // nothing
        else if (child.getType().size() == 1)
          generateCoreElem(elements, child, indent+2, pathName+"."+en, false, child.getType().get(0), ++cc == l, extcomplex);
        else {
          write("<span style=\"color: Gray\">// value[x]: <span style=\"color: navy; opacity: 0.8\">" +Utilities.escapeXml(child.getShort()) + "</span>. One of these "+Integer.toString(child.getType().size())+":</span>\r\n");
          for (TypeRefComponent t : child.getType())
            generateCoreElem(elements, child, indent+2, pathName+"."+en, false, t, ++cc == l, false);
        }
      c++;
      write(indentS);
      if (c == slices.size())
        write("  }\r\n");
      else
        write("  },\r\n");

    }
    write(indentS);
    if (last)
      write("]\r\n");
    else
      write("],\r\n");
  }

  private int lastChild(List<ElementDefinition> extchildren) {
    int l = extchildren.size();
    while (l > 0 && "0".equals(extchildren.get(l-1).getMax()))
      l--;
    return l;
  }

  private List<ElementDefinition> getSlices(ElementDefinition elem, List<ElementDefinition> children) {
    List<ElementDefinition> slices = new ArrayList<ElementDefinition>();
    for (ElementDefinition child : children) {
      if (child != elem && child.getPath().equals(elem.getPath()))
        slices.add(child);
    }
    return slices;
  }

  private String describeSlicing(ElementDefinitionSlicingComponent slicing) {
    CommaSeparatedStringBuilder csv = new CommaSeparatedStringBuilder();
    for (StringType d : slicing.getDiscriminator()) {
      csv.append(d.getValue());
    }
    String s = slicing.getOrdered() ? " in any order" : " in the specified order" + slicing.getRules().getDisplay();
    return " sliced by "+csv.toString()+" "+s;
  }


  @SuppressWarnings("rawtypes")
  private void generateCoreElem(List<ElementDefinition> elements, ElementDefinition elem, int indent, String pathName, boolean asValue, TypeRefComponent type, boolean last, boolean complex) throws Exception {
    if (elem.getPath().endsWith(".id"))
      return;
    if (!complex && elem.getPath().endsWith(".extension"))
      return;
    
    if (elem.getMax().equals("0"))
      return;
    
    String indentS = "";
    for (int i = 0; i < indent; i++) {
      indentS += "  ";
    }
    write(indentS);

    
    
    List<ElementDefinition> children = getChildren(elements, elem);
    String name =  tail(elem.getPath());
    String en = asValue ? "value[x]" : name;
    if (en.contains("[x]"))
      en = en.replace("[x]", upFirst(type.getCode()));
    boolean unbounded = elem.hasMax() && elem.getMax().equals("*");
    // 1. name
    write("\"<a href=\"" + (defPage + "#" + pathName + "." + en)+ "\" title=\"" + Utilities .escapeXml(getEnhancedDefinition(elem)) 
        + "\" class=\"dict\"><span style=\"text-decoration: underline\">"+en+"</span></a>\" : ");
    
    // 2. value
    boolean delayedCloseArray = false;
    if (unbounded) 
      write("[");

    if (type == null) {
      // inline definition
      assert(children.size() > 0);
      write("{");
      delayedCloseArray = true;
    } else if (definitions.getPrimitives().containsKey(type.getCode())) {
      if (!(type.getCode().equals("integer") || type.getCode().equals("boolean") || type.getCode().equals("decimal")))
        write("\"");
      if (elem.hasFixed()) 
        write(Utilities.escapeJson(((PrimitiveType) elem.getFixed()).asStringValue()));
      else
        write("&lt;<span style=\"color: darkgreen\"><a href=\"" + prefix+(dtRoot + definitions.getSrcFile(type.getCode())+ ".html#" + type.getCode()) + "\">" + type.getCode()+ "</a></span>&gt;");
      if (!(type.getCode().equals("integer") || type.getCode().equals("boolean") || type.getCode().equals("decimal")))
        write("\"");
    } else {
      write("{ ");
      write("<span style=\"color: darkgreen\"><a href=\"" + prefix+(dtRoot + definitions.getSrcFile(type.getCode())+ ".html#" + type.getCode()) + "\">" + type.getCode()+ "</a></span>");
      if (type.hasProfile()) {
        if (type.getProfile().get(0).getValue().startsWith("http://hl7.org/fhir/StructureDefinition/")) {
          String t = type.getProfile().get(0).getValue().substring(40);
          if (definitions.hasType(t))
            write("(<span style=\"color: darkgreen\"><a href=\"" + prefix+(dtRoot + definitions.getSrcFile(t)+ ".html#" + t) + "\">" + t+ "</a></span>)");
          else if (definitions.hasResource(t))
            write("(<span style=\"color: darkgreen\"><a href=\"" + prefix+dtRoot + t.toLowerCase()+ ".html\">" + t+ "</a></span>)");
          else
            write("("+t+")");
        } else
          write("("+type.getProfile()+")");
      }
      write(" }");
    } 

    if (!delayedCloseArray) {
      if (unbounded) 
        write("]");
      if (!last)
        write(",");
    }
    
    write(" <span style=\"color: Gray\">//</span>");

    // 3. optionality
    writeCardinality(elem);

    // 4. doco
    if (!elem.hasFixed()) {
      if (elem.hasBinding() && elem.getBinding().hasValueSet()) {
        ValueSet vs = resolveValueSet(elem.getBinding().getValueSet());
        if (vs != null)
          write("<span style=\"color: navy; opacity: 0.8\"><a href=\""+prefix+vs.getUserData("filename")+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");
        else if (elem.getBinding().getValueSet() instanceof UriType)
          write("<span style=\"color: navy; opacity: 0.8\"><a href=\""+((UriType)elem.getBinding().getValueSet()).getValue()+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");          
        else
          write("<span style=\"color: navy; opacity: 0.8\"><a href=\""+((Reference)elem.getBinding().getValueSet()).getReference()+".html\" style=\"color: navy\">" + Utilities.escapeXml(elem.getShort()) + "</a></span>");          
      } else
        write("<span style=\"color: navy; opacity: 0.8\">" + Utilities.escapeXml(elem.getShort()) + "</span>");
    }

    write("\r\n");

    if (delayedCloseArray) {
      int c = 0;
      for (ElementDefinition child : children) {
        if (child.getType().size() == 1)
          generateCoreElem(elements, child, indent + 1, pathName + "." + name, false, child.getType().get(0), ++c == children.size(), false);
        else {
          write("<span style=\"color: Gray\">// value[x]: <span style=\"color: navy; opacity: 0.8\">" +Utilities.escapeXml(child.getShort()) + "</span>. One of these "+Integer.toString(child.getType().size())+":</span>\r\n");
          for (TypeRefComponent t : child.getType())
            generateCoreElem(elements, child, indent + 1, pathName + "." + name, false, t, ++c == children.size(), false);
        }
      }
      write("}]");
      if (!last)
        write(",");
    }
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
      if (elements.get(i).getPath().startsWith(elem.getPath()+".")) {
        String tgt = elements.get(i).getPath();
        String src = elem.getPath();
        if (tgt.startsWith(src+".")) {
          if (!tgt.substring(src.length()+1).contains(".")) 
            res.add(elements.get(i));
        }
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

  /*
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
  */

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
        throw new Error("this sholdn't happen");
//        write("\r\n  ");
//        for (int j = 0; j < indent; j++)
//          write(" ");
//        w = indent+2;
      }
      w = w + t.getName().length(); // again, could be wrong if this is an extension, but then it won't wrap
      if (t.isXhtml() || t.getName().equals("list"))
        write(t.getName());
      else if (t.getName().equals("Extension") && t.getParams().size() == 0 && !Utilities.noString(t.getProfile()))
        write("<a href=\""+prefix+t.getProfile()+"\"><span style=\"color: DarkViolet\">@"+t.getProfile().substring(1)+"</span></a>");     
      else if (definitions.getConstraints().containsKey(t.getName())) {
        ProfiledType pt = definitions.getConstraints().get(t.getName());
        write("<a href=\"" + prefix+(dtRoot + definitions.getSrcFile(pt.getBaseType())
        + ".html#" + pt.getBaseType() + "\">" + pt.getBaseType())+"</a>");
        w = w + pt.getBaseType().length()+2; 
        write("(<a style=\"color:navy\" href=\"" + prefix+(dtRoot + definitions.getSrcFile(t.getName())
        + ".html#" + t.getName() + "\">" + t.getName())
        + "</a>)");
      } else
        write("<a href=\"" + prefix+(dtRoot + definitions.getSrcFile(t.getName())
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
            write("<a href=\"" + prefix+"resourcelist.html" + "\">" + p + "</a>");								
          }
          else if (t.getName().equals("Reference") && t.getParams().size() == 1 && !Utilities.noString(t.getProfile()))
            write("<a href=\""+prefix+t.getProfile()+"\"><span style=\"color: DarkViolet\">@"+t.getProfile().substring(1)+"</span></a>");     
          else
            write("<a href=\"" + prefix+(dtRoot + definitions.getSrcFile(p)
                + ".html#" + p) + "\">" + p + "</a>");

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

    /*
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
  */

  private void writeCardinality(ElementDefn elem) throws IOException {
    if (elem.getStatedInvariants().size() > 0)
      write(" <span style=\"color: brown\" title=\""+Utilities.escapeXml(getInvariants(elem))+ "\"><b>C?</b></span>"); 
    if (elem.getMinCardinality() > 0)
      write(" <span style=\"color: brown\" title=\"This element is required\"><b>R!</b></span> ");
  }

  private void writeCardinality(ElementDefinition elem) throws IOException {
    if (elem.getConstraint().size() > 0)
      write(" <span style=\"color: brown\" title=\""
          + Utilities.escapeXml(getInvariants(elem)) + "\"><b>C?</b></span>");
    if (elem.getMin() > 0)
      write(" <span style=\"color: brown\" title=\"This element is required\"><b>R!</b></span> ");
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

 /*
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
  */

}
