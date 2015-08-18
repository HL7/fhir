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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.IdType;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionMappingComponent;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;

public class DictHTMLGenerator  extends OutputStreamWriter {

	private Definitions definitions;
	private ProfileUtilities utilities;
	private PageProcessor page;
	private String prefix;
	
	public DictHTMLGenerator(OutputStream out, PageProcessor page, String prefix) throws UnsupportedEncodingException {
	  super(out, "UTF-8");
	  this.definitions = page.getDefinitions();
	  this.page = page;
	  this.utilities = new ProfileUtilities(page.getWorkerContext());
	  this.prefix = prefix;
	}

	public void generate(StructureDefinition profile) throws Exception {
	  int i = 1;
	  write("<table class=\"dict\">\r\n");

	  for (ElementDefinition ec : profile.getSnapshot().getElement()) {
	    if (isProfiledExtension(ec)) {
	      String name = profile.getId()+"."+ makePathLink(ec);
        StructureDefinition extDefn = page.getWorkerContext().getExtensionStructure(null, ec.getType().get(0).getProfile().get(0).getValue());
	      if (extDefn == null) {
	        String title = ec.getPath() + " ("+(ec.getType().get(0).getProfile().get(0).getValue().startsWith("#") ? profile.getUrl() : "")+ec.getType().get(0).getProfile()+")";
	        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
	        generateElementInner(profile,  ec, 1, null);
	      } else {
	        String title = ec.getPath() + " (<a href=\""+prefix+(extDefn.hasUserData("path") ? extDefn.getUserData("path") : "extension-"+extDefn.getId().toLowerCase()+".html")+
	            "\">"+(ec.getType().get(0).getProfile().get(0).getValue().startsWith("#") ? profile.getUrl() : "")+ec.getType().get(0).getProfile().get(0).getValue()+"</a>)";
	        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
	        ElementDefinition valueDefn = getExtensionValueDefinition(extDefn);
	        generateElementInner(extDefn, extDefn.getSnapshot().getElement().get(0), valueDefn == null ? 2 : 3, valueDefn);
	      }
	    } else {
	      String name = profile.getId()+"."+ makePathLink(ec);
	      String title = ec.getPath() + (!ec.hasName() ? "" : "(" +ec.getName() +")");
	      write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
	      generateElementInner(profile, ec, 1, null);
	      if (ec.hasSlicing())
	        generateSlicing(profile, ec.getSlicing());
	    }
	  }
	  write("</table>\r\n");
	  i++;      
    flush();
    close();
  }

  private ElementDefinition getExtensionValueDefinition(StructureDefinition extDefn) {
    for (ElementDefinition ed : extDefn.getSnapshot().getElement()) {
      if (ed.getPath().startsWith("Extension.value"))
        return ed;
    }
    return null;
  }

  public void generateExtension(StructureDefinition ed) throws Exception {
    int i = 1;
    write("<p><a name=\"i"+Integer.toString(i)+"\"><b>"+ed.getName()+"</b></a></p>\r\n");
    write("<table class=\"dict\">\r\n");

    for (ElementDefinition ec : ed.getSnapshot().getElement()) {
      if (isProfiledExtension(ec)) {
        String name = makePathLink(ec);
        String title = ec.getPath() + " ("+(ec.getType().get(0).getProfile().get(0).getValue().startsWith("#") ? ed.getUrl() : "")+ec.getType().get(0).getProfile().get(0).getValue()+")";
        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+prefix+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
        StructureDefinition extDefn = page.getWorkerContext().getExtensionStructure(null, ec.getType().get(0).getProfile().get(0).getValue());
        if (extDefn == null)
          generateElementInner(ed, ec, 1, null);
        else { 
          ElementDefinition valueDefn = getExtensionValueDefinition(extDefn);
          generateElementInner(extDefn, extDefn.getSnapshot().getElement().get(0), valueDefn == null ? 2 : 3, valueDefn);
        }
      } else {
        String name = makePathLink(ec);
        String title = ec.getPath() + (!ec.hasName() ? "" : "(" +ec.getName() +")");
        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
        generateElementInner(ed, ec, 1, null);
        //          if (ec.hasSlicing())
        //            generateSlicing(profile, ec.getSlicing());
      }
    }
    write("</table>\r\n");
    i++;      
    flush();
    close();
  }

  private void generateSlicing(StructureDefinition profile, ElementDefinitionSlicingComponent slicing) throws IOException {
    StringBuilder b = new StringBuilder();
    if (slicing.getOrdered())
      b.append("<li>ordered</li>");
    else
      b.append("<li>unordered</li>");
    if (slicing.hasRules())
      b.append("<li>"+slicing.getRules().getDisplay()+"</li>");
    if (!slicing.getDiscriminator().isEmpty()) {
      b.append("<li>discriminators: ");
      boolean first = true;
      for (StringType s : slicing.getDiscriminator()) {
        if (first)
          first = false;
        else
          b.append(", ");
        b.append(s.asStringValue());
      }
      b.append("</li>");
    }
    tableRowNE("Slicing", "profiling.html#slicing", "This element introduces a set of slices. The slicing rules are: <ul> "+b.toString()+"</ul>");
  }

  private String makePathLink(ElementDefinition element) {
    if (!element.hasName())
      return element.getPath();
    if (!element.getPath().contains("."))
      return element.getName();
    return element.getPath().substring(0, element.getPath().lastIndexOf("."))+"."+element.getName();
  }
  
  private boolean isProfiledExtension(ElementDefinition ec) {
    return ec.getType().size() == 1 && ec.getType().get(0).getCode().equals("Extension") && ec.getType().get(0).hasProfile();
  }

  private void generateElementInner(StructureDefinition profile, ElementDefinition d, int mode, ElementDefinition value) throws Exception {
    tableRowNE("Definition", null, page.processMarkdown(profile.getName(), d.getDefinition(), prefix));
    tableRowNE("Note", null, businessIdWarning(tail(d.getPath())));
    tableRow("Control", "conformance-rules.html#conformance", describeCardinality(d) + summariseConditions(d.getCondition()));
    tableRowNE("Binding", "terminologies.html", describeBinding(d));
    if (d.hasNameReference())
      tableRow("Type", null, "See "+d.getNameReference());
    else
      tableRowNE("Type", "datatypes.html", describeTypes(d.getType()) + processSecondary(mode, value));
    if (d.getPath().endsWith("[x]"))
      tableRowNE("[x] Note", null, "See <a href=\""+prefix+"formats.html#choice\">Choice of Data Types</a> for further information about how to use [x]");
    tableRow("Is Modifier", "conformance-rules.html#ismodifier", displayBoolean(d.getIsModifier()));
    tableRow("Must Support", "conformance-rules.html#mustSupport", displayBoolean(d.getMustSupport()));
    tableRowNE("Requirements",  null, page.processMarkdown(profile.getName(), d.getRequirements(), prefix));
    tableRowHint("Alternate Names", "Other names by which this resource/element may be known", null, describeAliases(d.getAlias()));
    tableRowNE("Comments",  null, page.processMarkdown(profile.getName(), d.getComments(), prefix));
    tableRow("Max Length", null, !d.hasMaxLengthElement() ? null : Integer.toString(d.getMaxLength()));
    tableRowNE("Default Value", null, encodeValue(d.getDefaultValue()));
    tableRowNE("Meaning if Missing", null, d.getMeaningWhenMissing());
    tableRowNE("Fixed Value", null, encodeValue(d.getFixed()));
    tableRowNE("Pattern Value", null, encodeValue(d.getPattern()));
    tableRow("Example", null, encodeValue(d.getExample()));
    tableRowNE("Invariants", null, invariants(d.getConstraint()));
    tableRow("LOINC Code", null, getMapping(profile, d, Definitions.LOINC_MAPPING));
    tableRow("SNOMED-CT Code", null, getMapping(profile, d, Definitions.SNOMED_MAPPING));
   }

  private String processSecondary(int mode, ElementDefinition value) throws Exception {
    switch (mode) {
    case 1 : return "";
    case 2 : return "  (Complex Extension)";
    case 3 : return "  (Extension Type: "+describeTypes(value.getType())+")";
    default: return "";
    }
  }

  private String businessIdWarning(String name) {
    if (name.equals("identifier"))
      return "This is a business identifer, not a resource identifier (see <a href=\""+prefix+"resource.html#identifiers\">discussion</a>)";
    if (name.equals("version"))
      return "This is a business versionId, not a resource identifier (see <a href=\""+prefix+"resource.html#versions\">discussion</a>)";
    return null;
  }

  private String tail(String path) {
    if (path.contains("."))
      return path.substring(0, path.indexOf("."));
    else
      return path;
  }

  private String encodeValue(Type value) throws Exception {
    if (value == null || value.isEmpty())
      return null;
    if (value instanceof PrimitiveType)
      return Utilities.escapeXml(((PrimitiveType) value).asStringValue());
    
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    XmlParser parser = new XmlParser();
    parser.setOutputStyle(OutputStyle.PRETTY);
    parser.compose(bs, null, value);
    String[] lines = bs.toString().split("\\r?\\n");
    StringBuilder b = new StringBuilder();
    for (String s : lines) {
      if (!Utilities.noString(s) && !s.startsWith("<?")) { // eliminate the xml header 
        b.append(Utilities.escapeXml(s).replace(" ", "&nbsp;")+"<br/>");
      }
    }
    return b.toString();  
    
  }

  private String describeTypes(List<TypeRefComponent> types) throws Exception {
    if (types.isEmpty())
      return null;
    StringBuilder b = new StringBuilder();
    if (types.size() == 1)
      describeType(b, types.get(0));
    else {
      boolean first = true;
      b.append("Choice of: ");
      for (TypeRefComponent t : types) {
        if (first)
          first = false;
        else
          b.append(", ");
        describeType(b, t);
      }
    }
    return b.toString();
  }

  private void describeType(StringBuilder b, TypeRefComponent t) throws Exception {
    if (t.getCode().startsWith("="))
      return;
    
    if (t.getCode().startsWith("xs:")) {
      b.append(t.getCode());
    } else {
      b.append("<a href=\"");
      b.append(prefix);         
      b.append(definitions.getSrcFile(t.getCode()));
      b.append(".html#");
      String type = t.getCode();
      if (type.equals("*"))
        b.append("open");
      else 
        b.append(t.getCode());
      b.append("\">");
      b.append(t.getCode());
      b.append("</a>");
    }
    if (t.hasProfile()) {
      b.append("(");
      boolean first = true;
      for (UriType pt : t.getProfile()) {
        if (first)
          first = false;
        else
          b.append(", ");
        StructureDefinition p = page.getWorkerContext().getProfiles().get(pt.asStringValue());
        if (p == null)
          b.append(pt.asStringValue());
        else {
          if (p.hasConstrainedType() )
            b.append("<a href=\""+prefix+p.getUserString("path")+"\" title=\""+pt.getValue()+"\">");
          else if (p.getKind() == StructureDefinitionKind.DATATYPE)
            b.append("<a href=\""+prefix+definitions.getSrcFile(p.getName())+ ".html#" + p.getName()+"\" title=\""+p.getName()+"\">");
          else // if (p.getKind() == StructureDefinitionType.RESOURCE)
            b.append("<a href=\""+prefix+p.getName().toLowerCase()+".html\">");
          b.append(p.getName());
          b.append("</a>");
        }      
      }
      b.append(")");
    }
  }

  private String invariants(List<ElementDefinitionConstraintComponent> constraints) {
    if (constraints.isEmpty())
      return null;
    StringBuilder s = new StringBuilder();
    if (constraints.size() > 0) {
      s.append("<b>Defined on this element</b><br/>\r\n");
      List<String> ids = new ArrayList<String>();
      for (ElementDefinitionConstraintComponent id : constraints)
        ids.add(id.getKey());
      Collections.sort(ids);
      boolean b = false;
      for (String id : ids) {
        ElementDefinitionConstraintComponent inv = getConstraint(constraints, id);
        if (b)
          s.append("<br/>");
        else
          b = true;
        s.append("<b title=\"Formal Invariant Identifier\">"+id+"</b>: "+Utilities.escapeXml(inv.getHuman())+" (xpath: "+Utilities.escapeXml(inv.getXpath())+")");
      }
    }
    
    return s.toString();
  }

  private ElementDefinitionConstraintComponent getConstraint(List<ElementDefinitionConstraintComponent> constraints, String id) {
    for (ElementDefinitionConstraintComponent c : constraints)
      if (c.getKey().equals(id))
        return c;
    return null;
  }

  private String describeAliases(List<StringType> synonym) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (StringType s : synonym) 
      b.append(s.getValue());
    return b.toString();
  }

  private String getMapping(StructureDefinition profile, ElementDefinition d, String uri) {
    String id = null;
    for (StructureDefinitionMappingComponent m : profile.getMapping()) {
      if (m.getUri().equals(uri))
        id = m.getIdentity();
    }
    if (id == null)
      return null;
    for (ElementDefinitionMappingComponent m : d.getMapping()) {
      if (m.getIdentity().equals(id))
        return m.getMap();
    }
    return null;
  }

  private String summariseConditions(List<IdType> conditions) {
    if (conditions.isEmpty())
      return "";
    else {
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (IdType t : conditions)
        b.append(t.getValue());
      return " This element is affected by the following invariants: "+b.toString();
    }
  }

  private String describeCardinality(ElementDefinition d) {
    if (!d.hasMax() && d.getMinElement() == null)
      return "";
    else if (d.getMax() == null)
      return Integer.toString(d.getMin()) + "..?";
    else
      return Integer.toString(d.getMin()) + ".." + d.getMax();
  }

  public void generate(ElementDefn root) throws Exception
	{
		write("<table class=\"dict\">\r\n");
		writeEntry(root.getName(), "1..1", "", null, root);
		for (ElementDefn e : root.getElements()) {
		   generateElement(root.getName(), e);
		}
		write("</table>\r\n");
		write("\r\n");
		flush();
		close();
	}

	private void generateElement(String name, ElementDefn e) throws Exception {
		writeEntry(name+"."+e.getName(), e.describeCardinality(), describeType(e), e.getBinding(), e);
		for (ElementDefn c : e.getElements())	{
		   generateElement(name+"."+e.getName(), c);
		}
	}

	private void writeEntry(String path, String cardinality, String type, BindingSpecification bs, ElementDefn e) throws Exception {
		write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+path.replace("[", "_").replace("]", "_")+"\"> </a><b>"+path+"</b></td></tr>\r\n");
		tableRowNE("Definition", null, page.processMarkdown(path, e.getDefinition(), prefix));
    tableRowNE("Note", null, businessIdWarning(e.getName()));
		tableRow("Control", "conformance-rules.html#conformance", cardinality + (e.hasCondition() ? ": "+  e.getCondition(): ""));
		tableRowNE("Binding", "terminologies.html", describeBinding(path, e));
		if (!Utilities.noString(type) && type.startsWith("@"))
		  tableRowNE("Type", null, "<a href=\"#"+type.substring(1)+"\">See "+type.substring(1)+"</a>");
		else
		  tableRowNE("Type", "datatypes.html", type);
    if (path.endsWith("[x]"))
      tableRowNE("[x] Note", null, "See <a href=\""+prefix+"formats.html#choice\">Choice of Data Types</a> for further information about how to use [x]");
		tableRow("Is Modifier", "conformance-rules.html#ismodifier", displayBoolean(e.isModifier()));
    tableRowNE("Default Value", null, encodeValue(e.getDefaultValue()));
    tableRowNE("Meaning if Missing", null, e.getMeaningWhenMissing());

		tableRowNE("Requirements", null, page.processMarkdown(path, e.getRequirements(), prefix));
		tableRowHint("Alternate Names", "Other names by which this resource/element may be known", null, toSeperatedString(e.getAliases()));
    if (e.hasSummaryItem())
      tableRow("Summary", "search.html#summary", Boolean.toString(e.isSummaryItem()));
    tableRowNE("Comments", null, page.processMarkdown(path, e.getComments(), prefix));
    tableRowNE("Invariants", null, invariants(e.getInvariants(), e.getStatedInvariants()));
    tableRow("LOINC Code", null, e.getMapping(Definitions.LOINC_MAPPING));
    tableRow("SNOMED-CT Code", null, e.getMapping(Definitions.SNOMED_MAPPING));
		tableRow("To Do", null, e.getTodo());
		if (e.getTasks().size() > 0) {
	    tableRowNE("gForge Tasks", null, tasks(e.getTasks()));
		}
	}
	
  private String tasks(List<String> tasks) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (String t : tasks) {
      if (first)
        first = false;
      else
        b.append(", ");
      b.append("<a href=\"http://gforge.hl7.org/gf/project/fhir/tracker/?action=TrackerItemEdit&amp;tracker_item_id=");
      b.append(t);
      b.append("\">");
      b.append(t);
      b.append("</a>");
    }
    return b.toString();
  }

  private String describeBinding(String path, ElementDefn e) throws Exception {

	  if (!e.hasBinding())
	    return null;
	  
	  StringBuilder b = new StringBuilder();
	  BindingSpecification cd =  e.getBinding();
	  if (cd.getValueSet() != null)
      b.append(cd.getValueSet().getName()+": ");
	  else if (!Utilities.noString(cd.getReference()))
      b.append("<a href=\""+prefix+cd.getReference()+"\">"+e.getBinding().getName()+"</a>: ");
	  else
      b.append("<a href=\""+prefix+"terminologies.html#unbound\">"+e.getBinding().getName()+"</a>: ");
	    
    b.append(TerminologyNotesGenerator.describeBinding(prefix, cd, page));
//    if (cd.getBinding() == Binding.Unbound)
//      b.append(" (Not Bound to any codes)");
//    else
//      b.append(" ("+(cd.getExtensibility() == null ? "--" : "<a href=\"terminologies.html#extensibility\">"+cd.getExtensibility().toString().toLowerCase())+"</a>/"+
//          "<a href=\"terminologies.html#conformance\">"+(cd.getBindingStrength() == null ? "--" : cd.getBindingStrength().toString().toLowerCase())+"</a>)");
    return b.toString();
  }

  private String describeBinding(ElementDefinition d) throws Exception {

    if (!d.hasBinding())
      return null;
    else
      return TerminologyNotesGenerator.describeBinding(prefix, d.getBinding(), page);
  }

  private String invariants(Map<String, Invariant> invariants, List<Invariant> stated) {
    
    List<String> done = new ArrayList<String>();
	  StringBuilder s = new StringBuilder();
	  if (invariants.size() > 0) {
	    s.append("<b>Defined on this element</b><br/>\r\n");
	    List<String> ids = new ArrayList<String>();
	    for (String id : invariants.keySet())
	      ids.add(id);
	    Collections.sort(ids);
	    boolean b = false;
	    for (String i : ids) {
	      Invariant inv = invariants.get(i);
	      done.add(inv.getId());
	      if (b)
	        s.append("<br/>");
	      s.append("<b title=\"Formal Invariant Identifier\">"+i+"</b>: "+Utilities.escapeXml(inv.getEnglish())+" (xpath: "+Utilities.escapeXml(inv.getXpath())+")");
	      b = true;
	    }
	  }
    if (stated.size() > 0) {
      if (s.length() > 0)
        s.append("<br/>");
      s.append("<b>Affect this element</b><br/>\r\n");
      boolean b = false;
      for (Invariant id : stated) {
        if (!done.contains(id.getId())) {
          if (b)
            s.append("<br/>");
          s.append("<b>"+id.getId().toString()+"</b>: "+Utilities.escapeXml(id.getEnglish())+" (xpath: "+Utilities.escapeXml(id.getXpath())+")");
          b = true;
        }
      }
    }
	  
    return s.toString();
  }

  private String toSeperatedString(List<String> list) {
	  if (list.size() == 0)
	    return "";
	  else {
	    StringBuilder s = new StringBuilder();
	    boolean first = true;
	    for (String v : list) {
	      if (!first)
	        s.append("; ");
	      first = false;
	      s.append(v);
	    }
	    return s.toString();
	  }
	  
  }

  private String displayBoolean(boolean mustUnderstand) {
		if (mustUnderstand)
			return "true";
		else
			return null;
	}

 
  private void tableRow(String name, String defRef, String value) throws IOException {
    if (value != null && !"".equals(value)) {
      if (defRef != null) 
        write("  <tr><td><a href=\""+prefix+defRef+"\">"+name+"</a></td><td>"+Utilities.escapeXml(value)+"</td></tr>\r\n");
      else
        write("  <tr><td>"+name+"</td><td>"+Utilities.escapeXml(value)+"</td></tr>\r\n");
    }
  }

  
  private void tableRowHint(String name, String hint, String defRef, String value) throws IOException {
    if (value != null && !"".equals(value)) {
      if (defRef != null) 
        write("  <tr><td><a href=\""+prefix+defRef+"\" title=\""+Utilities.escapeXml(hint)+"\">"+name+"</a></td><td>"+Utilities.escapeXml(value)+"</td></tr>\r\n");
      else
        write("  <tr><td title=\""+Utilities.escapeXml(hint)+"\">"+name+"</td><td>"+Utilities.escapeXml(value)+"</td></tr>\r\n");
    }
  }

  
  private void tableRowNE(String name, String defRef, String value) throws IOException {
    if (value != null && !"".equals(value))
      if (defRef != null) 
        write("  <tr><td><a href=\""+prefix+defRef+"\">"+name+"</a></td><td>"+value+"</td></tr>\r\n");
      else
        write("  <tr><td>"+name+"</td><td>"+value+"</td></tr>\r\n");
  }


	private String describeType(ElementDefn e) throws Exception {
		StringBuilder b = new StringBuilder();
		boolean first = true;
		if (e.typeCode().startsWith("@")) {
      b.append("<a href=\"#"+e.typeCode().substring(1)+"\">See "+e.typeCode().substring(1)+"</a>");		  
		} else {
		  for (TypeRef t : e.getTypes())
		  {
		    if (!first)
		      b.append("|");
		    if (t.getName().equals("*"))
		      b.append("<a href=\""+prefix+"datatypes.html#open\">*</a>");
		    else
		      b.append("<a href=\""+prefix+typeLink(t.getName())+"\">"+t.getName()+"</a>");
		    if (t.hasParams()) {
		      b.append("(");
		      boolean firstp = true;
		      for (String p : t.getParams()) {
		        if (!firstp)
		          b.append(" | ");
            b.append("<a href=\""+prefix+typeLink(p)+"\">"+p+"</a>");
		        firstp = false;
		      }
		      b.append(")");
		    }		  first = false;
		  }
		}
		return b.toString();
	}

  private String typeLink(String name) throws Exception {
    String srcFile = definitions.getSrcFile(name);
    if (srcFile.equalsIgnoreCase(name))
      return srcFile+ ".html";
    else
      return srcFile+ ".html#" + name;
  }
	
}
