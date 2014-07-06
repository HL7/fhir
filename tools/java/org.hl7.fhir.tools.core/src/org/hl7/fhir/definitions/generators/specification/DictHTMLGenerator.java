package org.hl7.fhir.definitions.generators.specification;
/*
Copyright (c) 2011-2014, HL7, Inc
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
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.model.Id;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionMappingComponent;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.Profile.ProfileMappingComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;

public class DictHTMLGenerator  extends OutputStreamWriter {

	private Definitions definitions;
	private PageProcessor page;
	
	public DictHTMLGenerator(OutputStream out, PageProcessor page) throws UnsupportedEncodingException {
		super(out, "UTF-8");
		this.definitions = page.getDefinitions();
		this.page = page;
	}

  public void generate(Profile profile) throws Exception {
    if (!profile.getExtensionDefn().isEmpty()) {
      write("<p><a name=\"i0\"><b>Extensions</b></a></p>\r\n");
      write("<table class=\"dict\">\r\n");
      
      for (ProfileExtensionDefnComponent e : profile.getExtensionDefn()) {
         generateExtension(profile, e);
      }
      write("</table>\r\n");
      
    }
    int i = 1;
    for (ProfileStructureComponent s : profile.getStructure()) {
      write("<p><a name=\"i"+Integer.toString(i)+"\"><b>"+s.getNameSimple()+"</b></a></p>\r\n");
      write("<table class=\"dict\">\r\n");
      
      for (ElementComponent ec : s.getSnapshot().getElement()) {
        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+s.getNameSimple()+"."+ec.getPathSimple()+"\"> </a><b>"+ec.getPathSimple()+"</b></td></tr>\r\n");
        generateElementInner(profile, ec.getDefinition());
      }
      write("</table>\r\n");
      i++;      
    }
    flush();
    close();
  }

  private void generateExtension(Profile profile, ProfileExtensionDefnComponent e) throws Exception {
    write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\"extension."+e.getCodeSimple()+"\"> </a><b>Extension "+e.getCodeSimple()+"</b></td></tr>\r\n");
    generateElementInner(profile, e.getElement().get(0).getDefinition());
    if (e.getElement().size() > 1) {
      for (int i = 1; i < e.getElement().size(); i++) {
        ElementComponent ec = e.getElement().get(i);
        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\"extension."+ec.getPathSimple()+"\"> </a><b>&nbsp;"+ec.getPathSimple()+"</b></td></tr>\r\n");
        generateElementInner(profile, ec.getDefinition());
      }
    }
  }
    
  private void generateElementInner(Profile profile, ElementDefinitionComponent d) throws Exception {
    tableRow("Definition", null, d.getFormalSimple());
    tableRow("Control", "conformance-rules.html#conformance", describeCardinality(d) + summariseConditions(d.getCondition()));
    tableRowNE("Binding", "terminologies.html", describeBinding(d));
    if (d.getNameReference() != null)
      tableRow("Type", null, "See "+d.getNameReferenceSimple());
    else
      tableRowNE("Type", "datatypes.html", describeTypes(d.getType()));
    tableRow("Is Modifier", "conformance-rules.html#ismodifier", displayBoolean(d.getIsModifierSimple()));
    tableRow("Must Support", "conformance-rules.html#mustsupport", displayBoolean(d.getMustSupportSimple()));
    tableRow("Requirements", null, d.getRequirementsSimple());
    tableRow("Aliases", null, describeAliases(d.getSynonym()));
    tableRow("Comments", null, d.getCommentsSimple());
    tableRow("Max Length", null, d.getMaxLength() == null ? null : Integer.toString(d.getMaxLengthSimple()));
    tableRow("Fixed Value", null, encodeValue(d.getValue()));
    tableRow("Example", null, encodeValue(d.getExample()));
    tableRowNE("Invariants", null, invariants(d.getConstraint()));
    tableRow("LOINC Code", null, getMapping(profile, d, Definitions.LOINC_MAPPING));
    tableRow("SNOMED-CT Code", null, getMapping(profile, d, Definitions.SNOMED_MAPPING));
  }

  private String encodeValue(Type value) throws Exception {
    if (value == null)
      return null;
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    new XmlComposer().compose(b, value);
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
    b.append("<a href=\"");
    b.append(GeneratorUtils.getSrcFile(t.getCodeSimple()));
    b.append(".html#");
    b.append(t.getCodeSimple());
    b.append("\">");
    b.append(t.getCodeSimple());
    b.append("</a>");
  }

  private String invariants(List<ElementDefinitionConstraintComponent> constraints) {
    if (constraints.isEmpty())
      return null;
    StringBuilder s = new StringBuilder();
    if (constraints.size() > 0) {
      s.append("<b>Defined on this element</b><br/>\r\n");
      List<String> ids = new ArrayList<String>();
      for (ElementDefinitionConstraintComponent id : constraints)
        ids.add(id.getKeySimple());
      Collections.sort(ids);
      boolean b = false;
      for (String id : ids) {
        ElementDefinitionConstraintComponent inv = getConstraint(constraints, id);
        if (b)
          s.append("<br/>");
        else
          b = true;
        s.append("<b title=\"Formal Invariant Identifier\">Inv-"+id+"</b>: "+Utilities.escapeXml(inv.getHumanSimple())+" (xpath: "+Utilities.escapeXml(inv.getXpathSimple())+")");
      }
    }
    
    return s.toString();
  }

  private ElementDefinitionConstraintComponent getConstraint(List<ElementDefinitionConstraintComponent> constraints, String id) {
    for (ElementDefinitionConstraintComponent c : constraints)
      if (c.getKeySimple().equals(id))
        return c;
    return null;
  }

  private String describeAliases(List<String_> synonym) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (String_ s : synonym) 
      b.append(s.getValue());
    return b.toString();
  }

  private String getMapping(Profile profile, ElementDefinitionComponent d, String uri) {
    String id = null;
    for (ProfileMappingComponent m : profile.getMapping()) {
      if (m.getUriSimple().equals(uri))
        id = m.getIdentitySimple();
    }
    if (id == null)
      return null;
    for (ElementDefinitionMappingComponent m : d.getMapping()) {
      if (m.getIdentitySimple().equals(id))
        return m.getMapSimple();
    }
    return null;
  }

  private String summariseConditions(List<Id> conditions) {
    if (conditions.isEmpty())
      return "";
    else
      return " ?";
  }

  private String describeCardinality(ElementDefinitionComponent d) {
    if (d.getMax() == null)
      return Integer.toString(d.getMinSimple()) + "..?";
    else
      return Integer.toString(d.getMinSimple()) + ".." + d.getMaxSimple();
  }

  public void generate(ElementDefn root) throws Exception
	{
		write("<table class=\"dict\">\r\n");
		writeEntry(root.getName(), "1..1", "", "", root);
		for (ElementDefn e : root.getElements()) {
		   generateElement(root.getName(), e);
		}
		write("</table>\r\n");
		write("\r\n");
		flush();
		close();
	}

	private void generateElement(String name, ElementDefn e) throws Exception {
		writeEntry(name+"."+e.getName(), e.describeCardinality(), describeType(e), e.getBindingName(), e);
		for (ElementDefn c : e.getElements())	{
		   generateElement(name+"."+e.getName(), c);
		}
	}

	private void writeEntry(String path, String cardinality, String type, String conceptDomain, ElementDefn e) throws Exception {
		write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+path.replace("[", "_").replace("]", "_")+"\"> </a><b>"+path+"</b></td></tr>\r\n");
		tableRow("Definition", null, e.getDefinition());
		tableRow("Control", "conformance-rules.html#conformance", cardinality + (e.hasCondition() ? ": "+  e.getCondition(): ""));
		tableRowNE("Binding", "terminologies.html", describeBinding(e));
		if (!Utilities.noString(type) && type.startsWith("@"))
		  tableRowNE("Type", null, "<a href=\"#"+type.substring(1)+"\">See "+type.substring(1)+"</a>");
		else
		  tableRowNE("Type", "datatypes.html", type);
		tableRow("Is Modifier", "conformance-rules.html#ismodifier", displayBoolean(e.isModifier()));
		tableRow("Requirements", null, e.getRequirements());
    tableRow("Aliases", null, toSeperatedString(e.getAliases()));
    if (e.isSummaryItem())
      tableRow("Summary", "search.html#summary", Boolean.toString(e.isSummaryItem()));
    tableRow("Comments", null, e.getComments());
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

  private String describeBinding(ElementDefn e) throws Exception {

	  if (!e.hasBinding())
	    return null;
	  
	  StringBuilder b = new StringBuilder();
	  BindingSpecification cd =  definitions.getBindingByName(e.getBindingName());
    b.append(cd.getName()+": ");
    b.append(TerminologyNotesGenerator.describeBinding(cd, page));
//    if (cd.getBinding() == Binding.Unbound)
//      b.append(" (Not Bound to any codes)");
//    else
//      b.append(" ("+(cd.getExtensibility() == null ? "--" : "<a href=\"terminologies.html#extensibility\">"+cd.getExtensibility().toString().toLowerCase())+"</a>/"+
//          "<a href=\"terminologies.html#conformance\">"+(cd.getBindingStrength() == null ? "--" : cd.getBindingStrength().toString().toLowerCase())+"</a>)");
    return b.toString();
  }

  private String describeBinding(ElementDefinitionComponent d) throws Exception {

    if (d.getBinding() == null)
      return null;
    else
      return TerminologyNotesGenerator.describeBinding(d.getBinding(), page);
  }

  private String invariants(Map<String, Invariant> invariants, List<Invariant> stated) {
	  StringBuilder s = new StringBuilder();
	  if (invariants.size() > 0) {
	    s.append("<b>Defined on this element</b><br/>\r\n");
	    List<Integer> ids = new ArrayList<Integer>();
	    for (String id : invariants.keySet())
	      ids.add(Integer.parseInt(id));
	    Collections.sort(ids);
	    boolean b = false;
	    for (Integer i : ids) {
	      Invariant inv = invariants.get(i.toString());
	      if (b)
	        s.append("<br/>");
	      s.append("<b title=\"Formal Invariant Identifier\">Inv-"+i.toString()+"</b>: "+Utilities.escapeXml(inv.getEnglish())+" (xpath: "+Utilities.escapeXml(inv.getXpath())+")");
	      b = true;
	    }
	  }
    if (stated.size() > 0) {
      if (s.length() > 0)
        s.append("<br/>");
      s.append("<b>Affect this element</b><br/>\r\n");
      boolean b = false;
      for (Invariant id : stated) {
        if (b)
          s.append("<br/>");
        s.append("<b>Inv-"+id.getId().toString()+"</b>: "+Utilities.escapeXml(id.getEnglish())+" (xpath: "+Utilities.escapeXml(id.getXpath())+")");
        b = true;
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
	      write("  <tr><td><a href=\""+defRef+"\">"+name+"</a></td><td>"+Utilities.escapeXml(value)+"</td></tr>\r\n");
		  else
		    write("  <tr><td>"+name+"</td><td>"+Utilities.escapeXml(value)+"</td></tr>\r\n");
		}
	}

	
  private void tableRowNE(String name, String defRef, String value) throws IOException {
    if (value != null && !"".equals(value))
      if (defRef != null) 
        write("  <tr><td><a href=\""+defRef+"\">"+name+"</a></td><td>"+value+"</td></tr>\r\n");
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
		      b.append("<a href=\"datatypes.html#open\">*</a>");
		    else
		      b.append("<a href=\""+typeLink(t.getName())+"\">"+t.getName()+"</a>");
		    if (t.hasParams()) {
		      b.append("(");
		      boolean firstp = true;
		      for (String p : t.getParams()) {
		        if (!firstp)
		          b.append(" | ");
		        if (definitions.getFutureResources().containsKey(p))
		          b.append("<span title=\"This resource is not been defined yet\">"+p+"</span>");
		        else
		          b.append("<a href=\""+typeLink(p)+"\">"+p+"</a>");
		        firstp = false;
		      }
		      b.append(")");
		    }		  first = false;
		  }
		}
		return b.toString();
	}

  private String typeLink(String name) throws Exception {
    String srcFile = GeneratorUtils.getSrcFile(name);
    if (srcFile.equalsIgnoreCase(name))
      return srcFile+ ".html";
    else
      return srcFile+ ".html#" + name;
  }
	
}
