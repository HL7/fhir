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
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.conformance.ProfileUtilities;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.ElementDefinition;
import org.hl7.fhir.r5.model.ElementDefinition.AggregationMode;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionExampleComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionSlicingDiscriminatorComponent;
import org.hl7.fhir.r5.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.r5.model.Enumeration;
import org.hl7.fhir.r5.model.IdType;
import org.hl7.fhir.r5.model.PrimitiveType;
import org.hl7.fhir.r5.model.StringType;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionMappingComponent;
import org.hl7.fhir.r5.model.Type;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.Utilities;

public class DictHTMLGenerator  extends OutputStreamWriter {

	private Definitions definitions;
	private PageProcessor page;
	private String prefix;
	
	public DictHTMLGenerator(OutputStream out, PageProcessor page, String prefix) throws UnsupportedEncodingException {
	  super(out, "UTF-8");
	  this.definitions = page.getDefinitions();
	  this.page = page;
	  this.prefix = prefix;
	}

	public void generate(StructureDefinition profile) throws Exception {
	  int i = 1;
	  write("<table class=\"dict\">\r\n");

	  for (ElementDefinition ec : profile.getSnapshot().getElement()) {
	    if (false && isProfiledExtension(ec)) {
	      String name = profile.getId()+"."+ makePathLink(ec);
        StructureDefinition extDefn = page.getWorkerContext().getExtensionStructure(null, ec.getType().get(0).getProfile().get(0).getValue());
	      if (extDefn == null) {
	        String title = ec.getPath() + " ("+(ec.getType().get(0).getProfile().get(0).getValue().startsWith("#") ? profile.getUrl() : "")+ec.getType().get(0).getProfile()+")";
	        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
	        generateElementInner(profile,  ec, 1, null);
	      } else {
	        String title = ec.getPath() + " (<a href=\""+prefix+(extDefn.hasUserData("path") ? extDefn.getUserData("path") : "extension-"+extDefn.getId().toLowerCase()+".html")+
	            "\">"+(ec.getType().get(0).getProfile().get(0).getValue().startsWith("#") ? profile.getUrl() : "")+ec.getType().get(0).getProfile()+"</a>)";
	        write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+name+"\"> </a><b>"+title+"</b></td></tr>\r\n");
	        ElementDefinition valueDefn = getExtensionValueDefinition(extDefn);
	        generateElementInner(extDefn, extDefn.getSnapshot().getElement().get(0), valueDefn == null ? 2 : 3, valueDefn);
	      }
	    } else {
	      String name = profile.getId()+"."+ makePathLink(ec);
	      String title = ec.getPath() + (!ec.hasSliceName() ? "" : "(" +ec.getSliceName() +")");
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
        String title = ec.getPath() + " ("+(ec.getType().get(0).getProfile().get(0).getValue().startsWith("#") ? ed.getUrl() : "")+ec.getType().get(0).getProfile()+")";
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
        String title = ec.getPath() + (!ec.hasSliceName() ? "" : "(" +ec.getSliceName() +")");
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
      for (ElementDefinitionSlicingDiscriminatorComponent s : slicing.getDiscriminator()) {
        if (first)
          first = false;
        else
          b.append(", ");
        b.append(s.getType().toCode()+":"+s.getPath());
      }
      b.append("</li>");
    }
    tableRowNE("Slicing", "profiling.html#slicing", "This element introduces a set of slices. The slicing rules are: <ul> "+b.toString()+"</ul>");
  }

  private String makePathLink(ElementDefinition element) {
    return element.getId();
  }
  
  private boolean isProfiledExtension(ElementDefinition ec) {
    return ec.getType().size() == 1 && "Extension".equals(ec.getType().get(0).getWorkingCode()) && ec.getType().get(0).hasProfile();
  }

  private void generateElementInner(StructureDefinition profile, ElementDefinition d, int mode, ElementDefinition value) throws Exception {
    tableRow("Element Id", null, d.getId());
    tableRowNE("Definition", null, page.processMarkdown(profile.getName(), d.getDefinition(), prefix));
    tableRowNE("Note", null, businessIdWarning(profile.getName(), tail(d.getPath())));
    tableRow("Cardinality", "conformance-rules.html#cardinality", describeCardinality(d) + summariseConditions(d.getCondition()));
    tableRowNE("Terminology Binding", "terminologies.html", describeBinding(d));
    if (d.hasContentReference())
      tableRow("Type", null, "See "+d.getContentReference().substring(1));
    else
      tableRowNE("Type", "datatypes.html", describeTypes(d.getType()) + processSecondary(mode, value));
    if (d.getPath().endsWith("[x]"))
      tableRowNE("[x] Note", null, "See <a href=\""+prefix+"formats.html#choice\">Choice of Data Types</a> for further information about how to use [x]");
    if (d.getIsModifier())
      tableRow("Is Modifier", "conformance-rules.html#ismodifier", displayBoolean(d.getIsModifier()) + " (Reason: "+d.getIsModifierReason()+")");
    else
      tableRow("Is Modifier", "conformance-rules.html#ismodifier", displayBoolean(d.getIsModifier()));
    tableRow("Must Support", "conformance-rules.html#mustSupport", displayBoolean(d.getMustSupport()));
    tableRowNE("Requirements",  null, page.processMarkdown(profile.getName(), d.getRequirements(), prefix));
    tableRowHint("Alternate Names", "Other names by which this resource/element may be known", null, describeAliases(d.getAlias()));
    tableRowNE("Comments",  null, page.processMarkdown(profile.getName(), d.getComment(), prefix));
    tableRow("Max Length", null, !d.hasMaxLengthElement() ? null : Integer.toString(d.getMaxLength()));
    tableRowNE("Default Value", null, encodeValue(d.getDefaultValue()));
    tableRowNE("Meaning if Missing", null, d.getMeaningWhenMissing());
    tableRowNE("Element Order Meaning", null, d.getOrderMeaning());
    tableRowNE("Fixed Value", null, encodeValue(d.getFixed()));
    tableRowNE("Pattern Value", null, encodeValue(d.getPattern()));
    tableRowNE("Example", null, encodeValues(d.getExample()));
    tableRowNE("Invariants", null, invariants(d.getConstraint(), profile));
    tableRow("LOINC Code", null, getMapping(profile, d, Definitions.LOINC_MAPPING));
    tableRow("SNOMED-CT Code", null, getMapping(profile, d, Definitions.SNOMED_MAPPING));
   }

  private String encodeValues(List<ElementDefinitionExampleComponent> examples) throws Exception {
    StringBuilder b = new StringBuilder();
    boolean first = false;
    for (ElementDefinitionExampleComponent ex : examples) {
      if (first)
        first = false;
      else
        b.append("<br/>");
      b.append("<b>"+Utilities.escapeXml(ex.getLabel())+"</b>:"+encodeValue(ex.getValue())+"\r\n");
    }
    return b.toString();
    
  }

  private String processSecondary(int mode, ElementDefinition value) throws Exception {
    switch (mode) {
    case 1 : return "";
    case 2 : return "  (Complex Extension)";
    case 3 : return "  (Extension Type: "+describeTypes(value.getType())+")";
    default: return "";
    }
  }

  private String businessIdWarning(String resource, String name) {
    if (name.equals("identifier"))
      return "This is a business identifier, not a resource identifier (see <a href=\""+prefix+"resource.html#identifiers\">discussion</a>)";
    if (name.equals("version")) // && !resource.equals("Device"))
      return "This is a business versionId, not a resource version id (see <a href=\""+prefix+"resource.html#versions\">discussion</a>)";
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
      return "";
    
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
    String tc = t.getWorkingCode();
    if (tc == null)
      return;
    if (tc.startsWith("="))
      return;
    
    if (tc.startsWith("xs:")) {
      b.append(tc);
    } else {
      b.append("<a href=\"");
      b.append(prefix);         
      b.append(definitions.getSrcFile(tc));
      b.append(".html#");
      String type = tc;
      if (type.equals("*"))
        b.append("open");
      else 
        b.append(tc);
      b.append("\">");
      b.append(tc);
      b.append("</a>");
    }
    if (t.hasProfile() || t.hasTargetProfile()) {
      b.append("(");
      boolean first = true;
      if (t.hasProfile()) {
        first = false;
        addProfileReference(b, t.getProfile().get(0).getValue());
      }
      if (t.hasTargetProfile()) {
        if (!first)
          b.append(", ");
        addProfileReference(b, t.getTargetProfile().get(0).getValue());
      }
      if (!t.getAggregation().isEmpty()) {
        b.append(" : ");
        boolean firstMode = true;
        for (Enumeration<AggregationMode> a :t.getAggregation()) {
          if (!firstMode)
            b.append(", ");
          b.append(" <a href=\"" + prefix + "codesystem-resource-aggregation-mode.html#content\">" + a.getValueAsString() + "</a>");
          firstMode = false;
        }
      }
      b.append(")");
    }
  }

  private void addProfileReference(StringBuilder b, String s) {
    StructureDefinition p = page.getWorkerContext().fetchResource(StructureDefinition.class, s);
    if (p == null)
      b.append(s);
    else {
      if (p.hasBaseDefinition() )
        b.append("<a href=\""+prefix+p.getUserString("path")+"\" title=\""+s+"\">");
      else if (p.getKind() == StructureDefinitionKind.COMPLEXTYPE || p.getKind() == StructureDefinitionKind.PRIMITIVETYPE)
        b.append("<a href=\""+prefix+definitions.getSrcFile(p.getName())+ ".html#" + p.getName()+"\" title=\""+p.getName()+"\">");
      else // if (p.getKind() == StructureDefinitionType.RESOURCE)
        b.append("<a href=\""+prefix+p.getName().toLowerCase()+".html\">");
      b.append(p.getName());
      b.append("</a>");
    }
  }

  private String invariants(List<ElementDefinitionConstraintComponent> constraints, StructureDefinition sd) throws FHIRException, Exception {
    if (constraints.isEmpty())
      return null;
    StringBuilder s = new StringBuilder();
    if (constraints.size() > 0) {
      listInvariants(constraints, s, false, "Defined on this element", sd);
      listInvariants(constraints, s, true, "Inherited by this element", sd);
    }
    if (s.length() > 0)
      s.append("</table>\r\n");
    
    return s.toString();
  }

  public class ConstraintsSorter implements Comparator<String> {

    @Override
    public int compare(String s0, String s1) {
    String[] parts0 = s0.split("\\-");
    String[] parts1 = s1.split("\\-");
    if (parts0.length != 2 || parts1.length != 2)
      return s0.compareTo(s1);
    int comp = parts0[0].compareTo(parts1[0]);
    if (comp == 0 && Utilities.isInteger(parts0[1]) && Utilities.isInteger(parts1[1]))
      return new Integer(parts0[1]).compareTo(new Integer(parts1[1]));
    else
      return parts0[1].compareTo(parts1[1]);
    }

  }

  public void listInvariants(List<ElementDefinitionConstraintComponent> constraints, StringBuilder s, boolean inherited, String title, StructureDefinition sd) throws FHIRException, Exception {
    List<String> ids = new ArrayList<String>();
    for (ElementDefinitionConstraintComponent id : constraints) {
      if (inherited == isInherited(id, sd))
        ids.add(id.getKey());
    }
    if (ids.size() > 0) {
    Collections.sort(ids, new ConstraintsSorter());
    if (s.length() == 0)
      s.append("<table class=\"dict\">\r\n");
    s.append("<tr><td colspan=\"4\"><b>"+title+"</b></td></tr>\r\n");
    for (String id : ids) {
      ElementDefinitionConstraintComponent inv = getConstraint(constraints, id);
      s.append("<tr><td width=\"60px\"><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b></td><td>"+presentLevel(inv)+"</td><td>"+Utilities.escapeXml(inv.getHuman())+"</td><td><span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getExpression())+"</span>");
      if (inv.hasExtension("http://hl7.org/fhir/StructureDefinition/elementdefinition-bestpractice")) 
        s.append("<br/>This is (only) a best practice guideline because: <blockquote>"+page.processMarkdown("best practice guideline", inv.getExtensionString("http://hl7.org/fhir/StructureDefinition/elementdefinition-bestpractice-explanation"), prefix)+"</blockquote>");
      s.append("</td></tr>");
    }
    }
  }

  private String presentLevel(ElementDefinitionConstraintComponent inv) {
    if (inv.hasExtension("http://hl7.org/fhir/StructureDefinition/elementdefinition-bestpractice"))
      return "<a href=\""+prefix+"conformance-rules.html#best-practice\" style=\"color: DarkGreen\">Guideline</a>";
    if ("warning".equals(inv.getSeverity().toCode()))
      return "<a href=\""+prefix+"conformance-rules.html#warning\" style=\"color: Chocolate\">Warning</a>";
    return "<a href=\""+prefix+"conformance-rules.html#rule\" style=\"color: Maroon\">Rule</a>";
  }

  private boolean isInherited(ElementDefinitionConstraintComponent id, StructureDefinition sd) {
    if (id.hasUserData(ProfileUtilities.IS_DERIVED)) {
      Boolean b = (Boolean) id.getUserData(ProfileUtilities.IS_DERIVED);
      return b.booleanValue();
    } else {
      //  if it was snapshotted in process? can't happen? - only happens on extensions... no id too, and then definitely inherited. see https://xkcd.com/2200/
      return true;
    }
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
      if (m.hasUri() && m.getUri().equals(uri))
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
		writeEntry(root.getName(), "0..*", describeType(root), null, root, root.getName(), true);
		for (ElementDefn e : root.getElements()) {
		   generateElement(root.getName(), e, root.getName(), false);
		}
		write("</table>\r\n");
		write("\r\n");
		flush();
		close();
	}

	private void generateElement(String name, ElementDefn e, String resourceName, boolean root) throws Exception {
		writeEntry(name+"."+e.getName(), e.describeCardinality(), describeType(e), e.getBinding(), e, resourceName, root);
		for (ElementDefn c : e.getElements())	{
		   generateElement(name+"."+e.getName(), c, resourceName, false);
		}
	}

	private void writeEntry(String path, String cardinality, String type, BindingSpecification bs, ElementDefn e, String resourceName, boolean root) throws Exception {
		write("  <tr><td colspan=\"2\" class=\"structure\"><a name=\""+path.replace("[", "_").replace("]", "_")+"\"> </a><b>"+path+"</b></td></tr>\r\n");
		if (e.getStandardsStatus() != null && !path.contains("."))
      tableRowStyled("Standards Status", "versions.html#std-process", getStandardsStatusNote(e.getStandardsStatus(), root), getStandardsStatusStyle(e.getStandardsStatus()));
    if (e.getStandardsStatus() == StandardsStatus.DEPRECATED && path.contains("."))
      tableRowStyled("Standards Status", "versions.html#std-process", getStandardsStatusNote(e.getStandardsStatus(), root), getStandardsStatusStyle(e.getStandardsStatus()));
    tableRow("Element Id", null, e.getPath());
    tableRowNE("Definition", null, page.processMarkdown(path, e.getDefinition(), prefix));
    tableRowNE("Note", null, businessIdWarning(resourceName, e.getName()));
		tableRow("Cardinality", "conformance-rules.html#cardinality", cardinality + (e.hasCondition() ? ": "+  e.getCondition(): ""));
		tableRowNE("Terminology Binding", "terminologies.html", describeBinding(path, e));
		if (!path.contains("."))
      tableRowNE("Type", "datatypes.html", type);
		else if (!Utilities.noString(type) && type.startsWith("@"))
		  tableRowNE("Type", null, "<a href=\"#"+type.substring(1)+"\">See "+type.substring(1)+"</a>");
		else
		  tableRowNE("Type", "datatypes.html", type);
		if (e.typeCode().contains("Reference("))
      tableRowNE("Patterns", "patterns.html", patternAnalysis(e));
		if (e.hasHierarchy())
	    tableRow("Hierarchy", "references.html#circular", e.getHierarchy() ? "This reference is part of a strict Hierarchy" : "This reference may point back to the same instance (including transitively)");
    if (path.endsWith("[x]"))
      tableRowNE("[x] Note", null, "See <a href=\""+prefix+"formats.html#choice\">Choice of Data Types</a> for further information about how to use [x]");
    if (e.isModifier())
      tableRow("Is Modifier", "conformance-rules.html#ismodifier", displayBoolean(e.isModifier()) + " (Reason: "+e.getModifierReason()+")");
    else
  		tableRow("Is Modifier", "conformance-rules.html#ismodifier", displayBoolean(e.isModifier()));
//    tableRowNE("Default Value", null, encodeValue(e.getDefaultValue()));
    tableRowNE("Meaning if Missing", null, e.getMeaningWhenMissing());
    tableRowNE("Element Order Meaning", null, e.getOrderMeaning());

    tableRowNE("Requirements", null, page.processMarkdown(path, e.getRequirements(), prefix));
		tableRowHint("Alternate Names", "Other names by which this resource/element may be known", null, toSeperatedString(e.getAliases()));
    if (e.hasSummaryItem())
      tableRow("Summary", "search.html#summary", Boolean.toString(e.isSummaryItem()));
    tableRowNE("Comments", null, page.processMarkdown(path, e.getComments(), prefix));
    tableRowNE("Invariants", null, invariants(e.getInvariants(), e.getStatedInvariants()));
    tableRow("LOINC Code", null, e.getMapping(Definitions.LOINC_MAPPING));
    tableRow("SNOMED-CT Code", null, e.getMapping(Definitions.SNOMED_MAPPING));
		tableRow("To Do", null, e.getTodo());
	}
	
	private String patternAnalysis(ElementDefn e) {
	  StringBuilder b = new StringBuilder();
	  boolean first = true;
	  for (TypeRef tr : e.getTypes()) {
	    if (tr.getPatterns() != null) {
	      if (first) first = false; else b.append("<br/>\r\n");
	      if (tr.getPatterns().isEmpty())
	        b.append(tr.summary()+": No common pattern");
	      else {
	        CommaSeparatedStringBuilder cb = new CommaSeparatedStringBuilder();
	        for (String s : tr.getPatterns()) {
	          cb.append("<a href=\""+s.toLowerCase()+".html#"+s+"\">"+s+"</a>");
	        }
	        if (tr.getPatterns().size() == 0)
            b.append(tr.summary()+": Common pattern = "+cb.toString());
	        else
	          b.append(tr.summary()+": Common patterns = "+cb.toString());
	      }
	    }
	  }
	  return b.toString();
	}

  private String getStandardsStatusStyle(StandardsStatus status) {
    return "background-color: "+status.getColor();
  }

  private String getStandardsStatusNote(StandardsStatus status, boolean root) {
    return "This element has a standards status of \""+status.toDisplay()+"\""+ (!root ? " which is different from the status of the whole resource" : "");  
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
	  
	  // name (description): [[ValueSet]], Strength = [[]]
    StringBuilder b = new StringBuilder();
    BindingSpecification cd =  e.getBinding();
    if (cd.getValueSet() == null) {
      if (!Utilities.noString(cd.getReference()))
        b.append("<a href=\""+prefix+cd.getReference()+"\">"+e.getBinding().getName()+"</a>: ");
      else
        b.append("<a href=\""+prefix+"terminologies.html#unbound\">"+e.getBinding().getName()+"</a>: ");
    } else  
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
//
//  
//  for (String id : ids) {
//    ElementDefinitionConstraintComponent inv = getConstraint(constraints, id);
//    s.append("<tr><td><b title=\"Formal Invariant Identifier\">"+inv.getId()+"</b></td><td>"+presentLevel(inv)+"</td><td>(base)</td><td>"+Utilities.escapeXml(inv.getHuman())+"</td><td><span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getExpression())+"</span>");
//    if (inv.hasExtension("http://hl7.org/fhir/StructureDefinition/elementdefinition-bestpractice")) 
//      s.append("<br/>This is (only) a best practice guideline because: <blockquote>"+page.processMarkdown("best practice guideline", inv.getExtensionString("http://hl7.org/fhir/StructureDefinition/elementdefinition-bestpractice-explanation"), prefix)+"</blockquote>");
//    s.append("</td></tr>");
//  }
//  }

  private String invariants(Map<String, Invariant> invariants, List<Invariant> stated) throws Exception {
    
    List<String> done = new ArrayList<String>();
    StringBuilder s = new StringBuilder();
    if (invariants.size() + stated.size() > 0) {
      s.append("<table class=\"dict\">\r\n");
      if (invariants.size() > 0) {
        s.append("<tr><td colspan=\"4\"><b>Defined on this element</b></td></tr>\r\n");
        List<String> ids = new ArrayList<String>();
        for (String id : invariants.keySet())
          ids.add(id);
        Collections.sort(ids, new ConstraintsSorter());
        for (String i : ids) {
          Invariant inv = invariants.get(i);
          done.add(inv.getId());
          s.append("<tr><td width=\"60px\"><b title=\"Formal Invariant Identifier\">"+inv.getId()+"</b></td><td>"+presentLevel(inv)+"</td><td>"+Utilities.escapeXml(inv.getEnglish())+"</td><td><span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getExpression())+"</span>");
          if (!Utilities.noString(inv.getExplanation())) 
            s.append("<br/>This is (only) a best practice guideline because: <blockquote>"+page.processMarkdown("best practice guideline", inv.getExplanation(), prefix)+"</blockquote>");
          s.append("</td></tr>");
        }
      }
      if (stated.size() > 0) {
        s.append("<tr><td colspan=\"4\"><b>Affect this element</b></td></tr>\r\n");
        boolean b = false;
        for (Invariant id : stated) {
          if (!done.contains(id.getId())) {
            s.append("<tr><td width=\"60px\"><b title=\"Formal Invariant Identifier\">"+id.getId()+"</b></td><td>"+presentLevel(id)+"</td><td>"+Utilities.escapeXml(id.getEnglish())+"</td><td><span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(id.getExpression())+"</span>");
            if (!Utilities.noString(id.getExplanation())) 
              s.append("<br/>This is (only) a best practice guideline because: <blockquote>"+page.processMarkdown("best practice guideline", id.getExplanation(), prefix)+"</blockquote>");
            s.append("</td></tr>");
          }
        }
      }
      s.append("</table>\r\n");
    }
	  
    return s.toString();
  }

  private String presentLevel(Invariant inv) {
    if ("warning".equals(inv.getSeverity()))
      return "<a href=\""+prefix+"conformance-rules.html#warning\" style=\"color: Chocolate\">Warning</a> ";
    if ("best-practice".equals(inv.getSeverity()))
      return "<a href=\""+prefix+"conformance-rules.html#best-practice\" style=\"color: DarkGreen\">Guideline</a> ";
    return "<a href=\""+prefix+"conformance-rules.html#rule\" style=\"color: Maroon\">Rule</a> ";
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

  private void tableRowStyled(String name, String defRef, String value, String style) throws IOException {
    if (defRef != null) 
      write("  <tr style=\""+style+"\"><td><a href=\""+prefix+defRef+"\">"+name+"</a></td><td>"+value+"</td></tr>\r\n");
    else
      write("  <tr style=\""+style+"\"><td>"+name+"</td><td>"+value+"</td></tr>\r\n");
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
		    String tn = t.getName();
		    if (tn.equals("Type"))
		      tn = "Element";
        if (tn.equals("*"))
		      b.append("<a href=\""+prefix+"datatypes.html#open\">*</a>");
		    else
		      b.append("<a href=\""+prefix+typeLink(tn)+"\">"+tn+"</a>");
		    if (t.hasParams()) {
		      b.append("(");
		      boolean firstp = true;
		      for (String p : t.getParams()) {
            if (!firstp)
              b.append(" | ");
            firstp = false;
		        if (definitions.hasLogicalModel(p)) {
              b.append("<a href=\""+prefix+typeLink(p)+"\">"+p+"</a>[");
              boolean firstpn = true;
              for (String pn : definitions.getLogicalModel(p).getImplementations()) {
                if (!firstpn)
                  b.append(", ");
                firstpn = false;
                b.append("<a href=\""+prefix+typeLink(pn)+"\">"+pn+"</a>");
              }		          
              b.append("]");
		        } else {
		          b.append("<a href=\""+prefix+typeLink(p)+"\">"+p+"</a>");
		        }
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
