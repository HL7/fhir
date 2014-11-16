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
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ResourceSlicingRules;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.ExtensionDefinition;
import org.hl7.fhir.instance.model.ExtensionDefinition.ExtensionDefinitionMappingComponent;
import org.hl7.fhir.instance.model.IdType;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ProfileMappingComponent;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.utils.WorkerContext.ExtensionDefinitionResult;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;

import com.github.rjeschke.txtmark.Processor;

public class DictHTMLGenerator  extends OutputStreamWriter {

	private Definitions definitions;
	private ProfileUtilities utilities;
	private PageProcessor page;
	
	public DictHTMLGenerator(OutputStream out, PageProcessor page) throws UnsupportedEncodingException {
	  super(out, "UTF-8");
	  this.definitions = page.getDefinitions();
	  this.page = page;
	  this.utilities = new ProfileUtilities(page.getWorkerContext());
	}

	public void generate(Profile profile) throws Exception {
	  int i = 1;
	  write("<table class=\"dict\">\r\n");

	  for (ElementDefinition ec : profile.getSnapshot().getElement()) {
	    if (isProfiledExtension(ec)) {
	      String name = profile.getName()+"."+ makePathLink(ec);
	      String title = ec.getPath() + " ("+(ec.getType().get(0).getProfile().startsWith("#") ? profile.getUrl() : "")+ec.getType().get(0).getProfile()+")";
	      write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
	      ExtensionDefinitionResult extDefn = page.getWorkerContext().getExtensionDefinition(null, ec.getType().get(0).getProfile());
	      if (extDefn == null)
	        generateElementInner(profile,  null, ec);
	      else
	        generateElementInner(null, extDefn.getExtensionDefinition(), extDefn.getElementDefinition());
	    } else {
	      String name = profile.getName()+"."+ makePathLink(ec);
	      String title = ec.getPath() + (ec.getName() == null ? "" : "(" +ec.getName() +")");
	      write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
	      generateElementInner(profile, null, ec);
	      if (ec.getSlicing() != null)
	        generateSlicing(profile, ec.getSlicing());
	    }
	  }
	  write("</table>\r\n");
	  i++;      
    flush();
    close();
  }

  public void generate(ExtensionDefinition ed) throws Exception {
    int i = 1;
    write("<p><a name=\"i"+Integer.toString(i)+"\"><b>"+ed.getName()+"</b></a></p>\r\n");
    write("<table class=\"dict\">\r\n");

    for (ElementDefinition ec : ed.getElement()) {
      if (isProfiledExtension(ec)) {
        String name = makePathLink(ec);
        String title = ec.getPath() + " ("+(ec.getType().get(0).getProfile().startsWith("#") ? ed.getUrl() : "")+ec.getType().get(0).getProfile()+")";
        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
        ExtensionDefinitionResult extDefn = page.getWorkerContext().getExtensionDefinition(null, ec.getType().get(0).getProfile());
        if (extDefn == null)
          generateElementInner(null, ed, ec);
        else
          generateElementInner(null, extDefn.getExtensionDefinition(), extDefn.getElementDefinition());
      } else {
        String name = makePathLink(ec);
        String title = ec.getPath() + (ec.getName() == null ? "" : "(" +ec.getName() +")");
        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
        generateElementInner(null, ed, ec);
        //          if (ec.getSlicing() != null)
        //            generateSlicing(profile, ec.getSlicing());
      }
    }
    write("</table>\r\n");
    i++;      
    flush();
    close();
  }

  private void generateSlicing(Profile profile, ElementDefinitionSlicingComponent slicing) throws IOException {
    StringBuilder b = new StringBuilder();
    if (slicing.getOrdered())
      b.append("<li>ordered</li>");
    else
      b.append("<li>unordered</li>");
    if (slicing.getRules() != null && slicing.getRules() != ResourceSlicingRules.NULL)
      b.append("<li>"+slicing.getRules().getDisplay()+"</li>");
    if (!slicing.getDiscriminator().isEmpty()) {
      b.append("<li>discriminators: ");
      boolean first = true;
      for (IdType s : slicing.getDiscriminator()) {
        if (first)
          first = false;
        else
          b.append(", ");
        b.append(s.asStringValue());
      }
      b.append("</li>");
    }
    tableRow("Slicing", "profiling.html#slicing", "This element introduces a set of slices. The slicing rules are: <ul> "+b.toString()+"</ul>");
  }

  private String makePathLink(ElementDefinition element) {
    if (element.getName() == null)
      return element.getPath();
    if (!element.getPath().contains("."))
      return element.getName();
    return element.getPath().substring(0, element.getPath().lastIndexOf("."))+"."+element.getName();
  }
  
  private boolean isProfiledExtension(ElementDefinition ec) {
    return ec.getType().size() == 1 && ec.getType().get(0).getCode().equals("Extension") && ec.getType().get(0).getProfile() != null;
  }

  private void generateExtension(ExtensionDefinition ed) throws Exception {
    write("<p><a name=\"i0\"><b>Extension</b></a></p>\r\n");
    write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\"extension."+ed.getUrl()+"\"> </a><b>Extension "+ed.getName()+"</b></td></tr>\r\n");
    generateElementInner(null, ed, ed.getElement().get(0));
    if (ed.getElement().size() > 1) {
      for (int i = 1; i < ed.getElement().size(); i++) {
        ElementDefinition ec = ed.getElement().get(i);
        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\"extension."+ec.getPath()+"\"> </a><b>&nbsp;"+ec.getPath()+"</b></td></tr>\r\n");
        generateElementInner(null, ed, ec);
      }
    }
    write("</table>\r\n");
  }
    
  private void generateElementInner(Profile profile, ExtensionDefinition ed, ElementDefinition d) throws Exception {
    tableRowMarkdown("Definition", d.getFormal());
    tableRow("Control", "conformance-rules.html#conformance", describeCardinality(d) + summariseConditions(d.getCondition()));
    tableRowNE("Binding", "terminologies.html", describeBinding(d));
    if (d.getNameReference() != null)
      tableRow("Type", null, "See "+d.getNameReference());
    else
      tableRowNE("Type", "datatypes.html", describeTypes(d.getType()));
    tableRow("Is Modifier", "conformance-rules.html#ismodifier", displayBoolean(d.getIsModifier()));
    tableRow("Must Support", "conformance-rules.html#mustSupport", displayBoolean(d.getMustSupport()));
    tableRowMarkdown("Requirements", d.getRequirements());
    tableRow("Aliases", null, describeAliases(d.getSynonym()));
    tableRowMarkdown("Comments", d.getComments());
    tableRow("Max Length", null, d.getMaxLengthElement() == null ? null : Integer.toString(d.getMaxLength()));
    tableRowNE("Fixed Value", null, encodeValue(d.getFixed()));
    tableRowNE("Pattern Value", null, encodeValue(d.getPattern()));
    tableRow("Example", null, encodeValue(d.getExample()));
    tableRowNE("Invariants", null, invariants(d.getConstraint()));
    tableRow("LOINC Code", null, getMapping(profile, ed, d, Definitions.LOINC_MAPPING));
    tableRow("SNOMED-CT Code", null, getMapping(profile, ed, d, Definitions.SNOMED_MAPPING));
   }

  private String encodeValue(Type value) throws Exception {
    if (value == null)
      return null;
    if (value instanceof PrimitiveType)
      return Utilities.escapeXml(((PrimitiveType) value).asStringValue());
    
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    new XmlComposer().compose(bs, value);
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
    b.append("<a href=\"");
    b.append(GeneratorUtils.getSrcFile(t.getCode(), false));
    b.append(".html#");
    String type = t.getCode();
    if (type.equals("*"))
      b.append("open");
    else 
      b.append(t.getCode());
    b.append("\">");
    b.append(t.getCode());
    b.append("</a>");
    if (t.getProfile() != null) {
      b.append("<a href=\"todo.html\">");
      b.append("(Profile = "+t.getProfile()+")");
      b.append("</a>");
      
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
        s.append("<b title=\"Formal Invariant Identifier\">Inv-"+id+"</b>: "+Utilities.escapeXml(inv.getHuman())+" (xpath: "+Utilities.escapeXml(inv.getXpath())+")");
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

  private String getMapping(Profile profile, ExtensionDefinition ed, ElementDefinition d, String uri) {
    String id = null;
    if (profile != null)
      for (ProfileMappingComponent m : profile.getMapping()) {
        if (m.getUri().equals(uri))
          id = m.getIdentity();
      }
    else
      for (ExtensionDefinitionMappingComponent m : ed.getMapping()) {
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
    else
      return " ?";
  }

  private String describeCardinality(ElementDefinition d) {
    if (d.getMax() == null && d.getMinElement() == null)
      return "";
    else if (d.getMax() == null)
      return Integer.toString(d.getMin()) + "..?";
    else
      return Integer.toString(d.getMin()) + ".." + d.getMax();
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
		tableRowNE("Definition", null, page.processMarkdown(e.getDefinition()));
		tableRow("Control", "conformance-rules.html#conformance", cardinality + (e.hasCondition() ? ": "+  e.getCondition(): ""));
		tableRowNE("Binding", "terminologies.html", describeBinding(e));
		if (!Utilities.noString(type) && type.startsWith("@"))
		  tableRowNE("Type", null, "<a href=\"#"+type.substring(1)+"\">See "+type.substring(1)+"</a>");
		else
		  tableRowNE("Type", "datatypes.html", type);
		tableRow("Is Modifier", "conformance-rules.html#ismodifier", displayBoolean(e.isModifier()));
		tableRowNE("Requirements", null, page.processMarkdown(e.getRequirements()));
    tableRow("Aliases", null, toSeperatedString(e.getAliases()));
    if (e.isSummaryItem())
      tableRow("Summary", "search.html#summary", Boolean.toString(e.isSummaryItem()));
    tableRowNE("Comments", null, page.processMarkdown(e.getComments()));
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

  private String describeBinding(ElementDefinition d) throws Exception {

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

  private void tableRowMarkdown(String name, String value) throws Exception {
    String text;
    if (value == null)
      text = "";
    else {
      text = value.replace("||", "\r\n\r\n");
      while (text.contains("[[[")) {
        String left = text.substring(0, text.indexOf("[[["));
        String linkText = text.substring(text.indexOf("[[[")+3, text.indexOf("]]]"));
        String right = text.substring(text.indexOf("]]]")+3);
        String url = "";
        String[] parts = linkText.split("\\#");
        Profile p = utilities.getProfile(null, parts[0]);
        if (p != null)
          url = p.getTag("filename")+".html";
        else if (definitions.hasResource(linkText)) {
          url = linkText.toLowerCase()+".html#";
        } else if (definitions.hasElementDefn(linkText)) {
          url = GeneratorUtils.getSrcFile(linkText, false)+".html#"+linkText;
        } else if (definitions.hasPrimitiveType(linkText)) {
          url = "datatypes.html#"+linkText;
        } else {
          System.out.println("Error: Unresolved logical URL "+linkText);
          //        throw new Exception("Unresolved logical URL "+url);
        }
        text = left+"["+linkText+"]("+url+")"+right;
      }
    }
    write("  <tr><td>"+name+"</td><td>"+Processor.process(Utilities.escapeXml(text))+"</td></tr>\r\n");
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
    String srcFile = GeneratorUtils.getSrcFile(name, false);
    if (srcFile.equalsIgnoreCase(name))
      return srcFile+ ".html";
    else
      return srcFile+ ".html#" + name;
  }
	
}
