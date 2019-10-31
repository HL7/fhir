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

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.CommonSearchParameter;
import org.hl7.fhir.definitions.model.ConstraintStructure;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.OperationParameter;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn.CompositeDefinition;
import org.hl7.fhir.definitions.model.SearchParameterDefn.SearchType;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.W5Entry;
import org.hl7.fhir.definitions.model.WorkGroup;
import org.hl7.fhir.definitions.validation.FHIRPathUsage;
import org.hl7.fhir.dstu2.model.ValueSet;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.igtools.spreadsheets.TypeParser;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r4.conformance.ProfileUtilities;
import org.hl7.fhir.r4.conformance.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.r4.formats.FormatUtilities;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.Constants;
import org.hl7.fhir.r4.model.ContactDetail;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.ElementDefinition;
import org.hl7.fhir.r4.model.ElementDefinition.AggregationMode;
import org.hl7.fhir.r4.model.ElementDefinition.ConstraintSeverity;
import org.hl7.fhir.r4.model.ElementDefinition.DiscriminatorType;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionBaseComponent;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.r4.model.ElementDefinition.PropertyRepresentation;
import org.hl7.fhir.r4.model.ElementDefinition.SlicingRules;
import org.hl7.fhir.r4.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.r4.model.Enumerations.BindingStrength;
import org.hl7.fhir.r4.model.Enumerations.FHIRVersion;
import org.hl7.fhir.r4.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r4.model.Enumerations.SearchParamType;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Factory;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.IntegerType;
import org.hl7.fhir.r4.model.MarkdownType;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r4.model.OperationDefinition;
import org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterBindingComponent;
import org.hl7.fhir.r4.model.OperationDefinition.OperationDefinitionParameterComponent;
import org.hl7.fhir.r4.model.OperationDefinition.OperationKind;
import org.hl7.fhir.r4.model.OperationDefinition.OperationParameterUse;
import org.hl7.fhir.r4.model.SearchParameter;
import org.hl7.fhir.r4.model.SearchParameter.SearchComparator;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.StructureDefinition.StructureDefinitionDifferentialComponent;
import org.hl7.fhir.r4.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r4.model.StructureDefinition.StructureDefinitionMappingComponent;
import org.hl7.fhir.r4.model.StructureDefinition.StructureDefinitionSnapshotComponent;
import org.hl7.fhir.r4.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.r4.model.UriType;
import org.hl7.fhir.r4.utils.NarrativeGenerator;
import org.hl7.fhir.r4.utils.ToolingExtensions;
import org.hl7.fhir.r4.utils.TypesUtilities;
import org.hl7.fhir.tools.converters.MarkDownPreProcessor;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class ProfileGenerator {

  public enum SnapShotMode {
    None, 
    Resource,
    DataType
  } 

  private BuildWorkerContext context;
  private Definitions definitions;
  private List<FHIRPathUsage> fpUsages;

  // status
  // note that once we start slicing, the slices keep their own maps, but all share the master pathname list
  private final Map<String, ElementDefinition> paths = new HashMap<String, ElementDefinition>();
  private final List<String> pathNames = new ArrayList<String>();
  private ProfileKnowledgeProvider pkp;
  private Calendar genDate;
  private FHIRVersion version;
  private Bundle dataElements;
  private String rootFolder;

  private static class SliceHandle {
    private String name;
    private Map<String, ElementDefinition> paths = new HashMap<String, ElementDefinition>();
  }

  public ProfileGenerator(Definitions definitions, BuildWorkerContext context, ProfileKnowledgeProvider pkp, Calendar genDate, FHIRVersion version, Bundle dataElements, List<FHIRPathUsage> fpUsages, String rootFolder) throws FHIRException {
    super();
    this.definitions = definitions;
    this.context = context;
    this.pkp = pkp;
    this.genDate = genDate;
    this.version = version;
    this.dataElements = dataElements;
    this.fpUsages = fpUsages;
    this.rootFolder = rootFolder;
    if (dataElements != null) {
      for (BundleEntryComponent be : dataElements.getEntry()) {
        if (be.getResource() instanceof StructureDefinition)
          des.put(be.getResource().getId(), (StructureDefinition) be.getResource());
      }
    }
  }

  private Map<String, StructureDefinition> des = new HashMap<String, StructureDefinition>();
  private static int extensionCounter;
  private static int profileCounter = 0;
  
  private void generateElementDefinition(StructureDefinition source, ElementDefinition ed, ElementDefinition parent) throws Exception {
    String id = ed.getPath().replace("[x]", "X");
    if (id.length() > 61)
      id = id.substring(0, 61);

    if (!id.contains("."))
      return; // throw new Exception("Don't generate data element for root of resources or types");
    if (!ed.hasType())
      return; // throw new Exception("Don't generate data element for reference elements");
    if (Utilities.existsInList(ed.getType().get(0).getCode(), "Element", "BackboneElement"))
      return; // throw new Exception("Don't generate data element for elements that are not leaves");
          
    StructureDefinition de;
    if (des.containsKey(id)) {
      de = des.get(id);
      // do it again because we now have more information to generate with
      de.getSnapshot().getElement().clear();
      de.getExtension().clear();
    } else {
      de = new StructureDefinition();
      de.setId("de-"+id);
      des.put(id, de);
      de.setUrl("http://hl7.org/fhir/StructureDefinition/"+de.getId());
      
      if (de.getId().contains("."))
        definitions.addNs(de.getUrl(), "Data Element "+ed.getPath(), definitions.getSrcFile(id.substring(0, id.indexOf(".")))+"-definitions.html#"+id);
      if (dataElements != null)
        dataElements.addEntry().setResource(de).setFullUrl(de.getUrl());
    }
      
    if (!de.hasMeta())
      de.setMeta(new Meta());
    de.getMeta().setLastUpdatedElement(new InstantType(genDate));
    de.setVersion(version.toCode());
    de.setName(ed.getPath());
    de.setStatus(PublicationStatus.DRAFT);
    de.setExperimental(true);
    de.setTitle(de.getName());
    de.setDate(genDate.getTime());
    de.setPublisher("HL7 FHIR Standard");
    de.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    de.setDescription("Data Element for "+ed.getPath());
    de.setPurpose("Data Elements are defined for each element to assist in questionnaire construction etc");
    de.setFhirVersion(version);
    de.setKind(StructureDefinitionKind.LOGICAL);
    de.setType("DataElement");
    de.setAbstract(false);
    de.setType(de.getName());
    de.setBaseDefinition("http://hl7.org/fhir/StructureDefinition/Element");
    de.setDerivation(TypeDerivationRule.SPECIALIZATION);
    de.getMapping().addAll(source.getMapping());
    ElementDefinition ted = ed.copy();
    de.getSnapshot().addElement(ted);
    ted.makeBase();
  }


  private String tail(String path) {
    int i = path.lastIndexOf(".");
    return i < 0 ? path : path.substring(i + 1);
  }


  public StructureDefinition generate(PrimitiveType type) throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setId(type.getCode());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ type.getCode());
    p.setKind(StructureDefinitionKind.PRIMITIVETYPE);
    p.setAbstract(false);
    p.setUserData("filename", type.getCode().toLowerCase());
    p.setUserData("path", "datatypes.html#"+type.getCode());
    p.setBaseDefinition("http://hl7.org/fhir/StructureDefinition/Element");
    p.setType(type.getCode());
    p.setDerivation(TypeDerivationRule.SPECIALIZATION);
    p.setFhirVersion(version);
    p.setVersion(version.toCode());
    ToolingExtensions.setStandardsStatus(p, StandardsStatus.NORMATIVE, "4.0.0");

    
    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(type.getCode());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+type.getCode()+" Type: "+type.getDefinition());
    p.setDate(genDate.getTime());
    p.setStatus(PublicationStatus.fromCode("active")); // normative now

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    ElementDefinition ec = new ElementDefinition();
    p.getDifferential().getElement().add(ec);
    ec.setId(type.getCode());
    ec.setPath(type.getCode());
    ec.setShort("Primitive Type " +type.getCode());
    ec.setDefinition(type.getDefinition());
    ec.setComment(type.getComment());
    ec.setMin(0);
    ec.setMax("*");
    ec = new ElementDefinition();
    p.getDifferential().getElement().add(ec);
    ec.setId(type.getCode()+".value");
    ec.setPath(type.getCode()+".value");
    ec.addRepresentation(PropertyRepresentation.XMLATTR);
    ec.setShort("Primitive value for " +type.getCode());
    ec.setDefinition("Primitive value for " +type.getCode());
    ec.setMin(0);
    ec.setMax("1");
    TypeRefComponent t = ec.addType();
    t.getFormatCommentsPre().add("Note: special primitive values have a FHIRPath system type. e.g. this is compiler magic (a)");
    t.setCode(Constants.NS_SYSTEM_TYPE+type.getFHIRPathType());
    ToolingExtensions.addUrlExtension(t, ToolingExtensions.EXT_FHIR_TYPE, type.getCode());
    if (!Utilities.noString(type.getRegex())) {
      ToolingExtensions.addStringExtension(t, ToolingExtensions.EXT_REGEX, type.getRegex());
    }
    addSpecificDetails(type, ec);

    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    ElementDefinition ec1 = new ElementDefinition(true, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    p.getSnapshot().getElement().add(ec1);
    ec1.setId(type.getCode());
    ec1.setPath(type.getCode());
    ec1.setShort("Primitive Type " +type.getCode());
    ec1.setDefinition(type.getDefinition());
    ec1.setComment(type.getComment());
    ec1.setMin(0);
    ec1.setMax("*");
    ec1.makeBase();
    addElementConstraints("Element", ec1);

    ElementDefinition ec2 = new ElementDefinition(true, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    p.getSnapshot().getElement().add(ec2);
    ec2.setId(type.getCode()+".id");
    ec2.setPath(type.getCode()+".id");
    ec2.addRepresentation(PropertyRepresentation.XMLATTR);
    ec2.setDefinition("unique id for the element within a resource (for internal references)");
    ec2.setMin(0);
    ec2.setMax("1");
    ec2.setShort("xml:id (or equivalent in JSON)");
    TypeRefComponent tr = ec2.addType();
    t.getFormatCommentsPre().add("Note: special primitive values have a FHIRPath system type. e.g. this is compiler magic (b)");
    tr.setCode(Constants.NS_SYSTEM_TYPE+ "String"); 
    ToolingExtensions.addUrlExtension(tr, ToolingExtensions.EXT_FHIR_TYPE, "string");

    generateElementDefinition(p, ec2, ec1);
    ec2.makeBase("Element.id", 0, "1");

    makeExtensionSlice("extension", p, p.getSnapshot(), null, type.getCode());

    ElementDefinition ec3 = new ElementDefinition(true, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    p.getSnapshot().getElement().add(ec3);
    ec3.setId(type.getCode()+".value");
    ec3.setPath(type.getCode()+".value");
    ec3.addRepresentation(PropertyRepresentation.XMLATTR);
    ec3.setDefinition("The actual value");
    ec3.setMin(0);
    ec3.setMax("1");
    ec3.setShort("Primitive value for " +type.getCode());
    ec3.makeBase();
    t = ec3.addType();
    t.setCodeElement(new UriType());
    t.getFormatCommentsPre().add("Note: special primitive values have a FHIRPath system type. e.g. this is compiler magic (c)");
    t.setCode(Constants.NS_SYSTEM_TYPE+type.getFHIRPathType());
    ToolingExtensions.addUrlExtension(t, ToolingExtensions.EXT_FHIR_TYPE, type.getCode());
    if (!Utilities.noString(type.getRegex()))
      ToolingExtensions.addStringExtension(t, ToolingExtensions.EXT_REGEX, type.getRegex());
    addSpecificDetails(type, ec3);
    generateElementDefinition(p, ec3, ec);

    containedSlices.clear();

    addElementConstraintToSnapshot(p);
    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }

  public class ElementDefinitionConstraintSorter implements Comparator<ElementDefinitionConstraintComponent> {

    @Override
    public int compare(ElementDefinitionConstraintComponent arg0, ElementDefinitionConstraintComponent arg1) {
      String k0 = arg0.getKey();
      String k1 = arg1.getKey();
      if (k0.contains("-") && k1.contains("-")) {
        String p0 = k0.substring(0, k0.indexOf("-"));
        String i0 = k0.substring(k0.indexOf("-")+1);
        String p1 = k1.substring(0, k1.indexOf("-"));
        String i1 = k1.substring(k1.indexOf("-")+1);
        if (Utilities.isInteger(i0) && Utilities.isInteger(i1) && p0.equals(p1)) {
          return Integer.compare(Integer.parseInt(i0),  Integer.parseInt(i1));
        }
      }
      return k0.compareTo(k1);
    }
  }

  private void addElementConstraintToSnapshot(StructureDefinition sd) {
    for (ElementDefinition ed : sd.getSnapshot().getElement())
      addElementConstraint(sd, ed);
    for (ElementDefinition ed : sd.getSnapshot().getElement())
      addExtensionConstraint(sd, ed);
    // to help with unit tests..
    ElementDefinitionConstraintSorter edcs = new ElementDefinitionConstraintSorter(); 
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      Collections.sort(ed.getConstraint(), edcs);
    }
  }


  private boolean hasSystemType(ElementDefinition ed) {
    for (TypeRefComponent t : ed.getType()) {
      if (t.hasCode() && t.getCode().startsWith("http://hl7.org/fhirpath/System."))
        return true;
    }
    return false;
  }


  private void addElementConstraints(String name, ElementDefinition ed) throws Exception {
    if (definitions.hasPrimitiveType(name) || name.equals("Type") || name.equals("Logical"))
      addElementConstraints("Element", ed);
    else if (name.equals("Structure"))
      addElementConstraints("BackboneElement", ed);
    else {
      ElementDefn element = definitions.getElementDefn(name);
      if (!Utilities.noString(element.typeCode()))
        addElementConstraints(element.typeCode(), ed);
      convertConstraints(element, ed, name);
    }
  }
  
  private void addSpecificDetails(PrimitiveType type, ElementDefinition ed) throws FHIRFormatError {
    if (type.getCode().equals("integer")) {
      ed.setMinValue(new IntegerType(-2147483648));
      ed.setMaxValue(new IntegerType(2147483647));       
    }
    if (type.getCode().equals("string")) {
      ed.setMaxLength(1024 * 1024);
    }    
  }

  public StructureDefinition generateXhtml() throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setId("xhtml");
    p.setUrl("http://hl7.org/fhir/StructureDefinition/xhtml");
    p.setKind(StructureDefinitionKind.PRIMITIVETYPE);
    p.setAbstract(false);
    p.setUserData("filename", "xhtml");
    p.setUserData("path", "narrative.html#xhtml");
    p.setBaseDefinition("http://hl7.org/fhir/StructureDefinition/Element");
    p.setType("xhtml");
    p.setDerivation(TypeDerivationRule.SPECIALIZATION);
    p.setFhirVersion(version);
    p.setVersion(version.toCode());
    ToolingExtensions.setStandardsStatus(p, StandardsStatus.NORMATIVE, "4.0.0");

    
    ToolResourceUtilities.updateUsage(p, "core");
    p.setName("xhtml");
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for xhtml Type");
    p.setDate(genDate.getTime());
    p.setStatus(PublicationStatus.fromCode("active")); 

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    ElementDefinition ec = new ElementDefinition();
    p.getDifferential().getElement().add(ec);
    ec.setId("xhtml");
    ec.setPath("xhtml");
    ec.setShort("Primitive Type " +"xhtml");
    ec.setDefinition("XHTML");
    ec.setMin(0);
    ec.setMax("*");
    ec = new ElementDefinition();
    p.getDifferential().getElement().add(ec);
    ec.setId("xhtml"+".extension");
    ec.setPath("xhtml"+".extension");
    ec.setMax("0");
    ec = new ElementDefinition();
    p.getDifferential().getElement().add(ec);
    ec.setId("xhtml"+".value");
    ec.setPath("xhtml"+".value");
    ec.addRepresentation(PropertyRepresentation.XHTML);
    ec.setShort("Actual xhtml");
    ec.setDefinition("Actual xhtml");
    ec.setMin(1);
    ec.setMax("1");
    TypeRefComponent t = ec.addType();
    t.setCodeElement(new UriType());
    t.getFormatCommentsPre().add("Note: special primitive values have a FHIRPath system type. e.g. this is compiler magic (d)");
    t.setCode(Constants.NS_SYSTEM_TYPE+"String");
    ToolingExtensions.addUrlExtension(t, ToolingExtensions.EXT_FHIR_TYPE, "string");

    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    ElementDefinition ec1 = new ElementDefinition(true, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    p.getSnapshot().getElement().add(ec1);
    ec1.setId("xhtml");
    ec1.setPath("xhtml");
    ec1.setShort("Primitive Type " +"xhtml");
    ec1.setDefinition("XHTML");
    ec1.setMin(0);
    ec1.setMin(0);
    ec1.setMax("*");
    ec1.makeBase();
    generateElementDefinition(p, ec1, null);

    ElementDefinition ec2 = new ElementDefinition(true, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    p.getSnapshot().getElement().add(ec2);
    ec2.setId("xhtml.id");
    ec2.setPath("xhtml.id");
    ec2.addRepresentation(PropertyRepresentation.XMLATTR);
    ec2.setDefinition("unique id for the element within a resource (for internal references)");
    ec2.setMin(0);
    ec2.setMax("1");
    ec2.setShort("xml:id (or equivalent in JSON)");
    TypeRefComponent tr = ec2.addType();
    t.getFormatCommentsPre().add("Note: special primitive values have a FHIRPath system type. e.g. this is compiler magic (e)");
    tr.setCode(Constants.NS_SYSTEM_TYPE+ "String"); 
    ToolingExtensions.addUrlExtension(t, ToolingExtensions.EXT_FHIR_TYPE, "string");
    generateElementDefinition(p, ec2, ec1);
    ec2.makeBase("Element.id", 0, "1");

    ElementDefinition ex = makeExtensionSlice("extension", p, p.getSnapshot(), null, "xhtml");
    ex.setMax("0");
    
    ElementDefinition ec3 = new ElementDefinition(true, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    p.getSnapshot().getElement().add(ec3);
    ec3.setId("xhtml.value");
    ec3.setPath("xhtml.value");
    ec3.addRepresentation(PropertyRepresentation.XHTML);
    ec3.setShort("Actual xhtml");
    ec3.setDefinition("Actual xhtml");
    ec3.setMin(1);
    ec3.setMax("1");
    t = ec3.addType();
    t.setCodeElement(new UriType());
    t.getFormatCommentsPre().add("Note: special primitive values have a FHIRPath system type. e.g. this is compiler magic (f)");
    t.setCode(Constants.NS_SYSTEM_TYPE+"String");
    ToolingExtensions.addUrlExtension(t, ToolingExtensions.EXT_FHIR_TYPE, "string");
    ec3.makeBase();
    generateElementDefinition(p, ec3, ec);

    containedSlices.clear();
    addElementConstraintToSnapshot(p);

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }

  private String prefix(String prefix, String value) {
    if (value == null)
      return prefix;
    if (value.startsWith(prefix))
      return value;
    return prefix + value;
  }

  public StructureDefinition generate(DefinedStringPattern type) throws Exception {

    StructureDefinition p = new StructureDefinition();
    p.setId(type.getCode());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ type.getCode());
    p.setBaseDefinition("http://hl7.org/fhir/StructureDefinition/"+ type.getBase());
    p.setType(type.getCode());
    p.setDerivation(TypeDerivationRule.SPECIALIZATION);
    p.setKind(StructureDefinitionKind.PRIMITIVETYPE);
    p.setAbstract(false);
    p.setUserData("filename", type.getCode().toLowerCase());
    p.setUserData("path", "datatypes.html#"+type.getCode());
    p.setFhirVersion(version);
    p.setVersion(version.toCode());
    ToolingExtensions.setStandardsStatus(p, StandardsStatus.NORMATIVE, "4.0.0");

    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(type.getCode());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+type.getCode()+" type: "+type.getDefinition());
    p.setDate(genDate.getTime());
    p.setStatus(PublicationStatus.fromCode("active")); 

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    ElementDefinition ec1 = new ElementDefinition();
    p.getDifferential().getElement().add(ec1);
    ec1.setId(type.getCode());
    ec1.setPath(type.getCode());

    ec1.setShort("Primitive Type " +type.getCode());
    ec1.setDefinition(type.getDefinition());
    ec1.setComment(type.getComment());
    ec1.setMin(0);
    ec1.setMax("*");

    ElementDefinition ec2 = new ElementDefinition();
    p.getDifferential().getElement().add(ec2);
    ec2.setId(type.getCode()+".value");
    ec2.setPath(type.getCode()+".value");
    ec2.addRepresentation(PropertyRepresentation.XMLATTR);

    ec2.setShort("Primitive value for " +type.getCode());
    ec2.setDefinition("Primitive value for " +type.getCode());
    ec2.setMin(0);
    ec2.setMax("1");
    TypeRefComponent t = ec2.addType();
    t.setCodeElement(new UriType());
    t.getFormatCommentsPre().add("Note: special primitive values have a FHIRPath system type. e.g. this is compiler magic (g)");
    t.setCode(Constants.NS_SYSTEM_TYPE+type.getFHIRPathType());
    ToolingExtensions.addUrlExtension(t, ToolingExtensions.EXT_FHIR_TYPE, type.getCode());
    if (!Utilities.noString(type.getRegex())) {
      ToolingExtensions.addStringExtension(t, ToolingExtensions.EXT_REGEX, type.getRegex());
    }
    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    ElementDefinition ecA = new ElementDefinition(true, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    p.getSnapshot().getElement().add(ecA);
    ecA.setId(type.getCode());
    ecA.setPath(type.getCode());

    ecA.setShort("Primitive Type " +type.getCode());
    ecA.setDefinition(type.getDefinition());
    ecA.setComment(type.getComment());
    ecA.setMin(0);
    ecA.setMax("*");
    ecA.makeBase(type.getCode(), 0, "*");
    addElementConstraints("Element", ecA);

    ElementDefinition ecid = new ElementDefinition(true, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    p.getSnapshot().getElement().add(ecid);
    ecid.setId(type.getCode()+".id");
    ecid.setPath(type.getCode()+".id");
    ecid.addRepresentation(PropertyRepresentation.XMLATTR);
    ecid.setDefinition("unique id for the element within a resource (for internal references)");
    ecid.setMin(0);
    ecid.setMax("1");
    ecid.setShort("xml:id (or equivalent in JSON)");
    TypeRefComponent tr = ecid.addType();
    tr.getFormatCommentsPre().add("Note: special primitive values have a FHIRPath system type. e.g. this is compiler magic (h)");
    tr.setCode(Constants.NS_SYSTEM_TYPE+ "String"); 
    ToolingExtensions.addUrlExtension(tr, ToolingExtensions.EXT_FHIR_TYPE, "string");
    ecid.makeBase("Element.id", 0, "1");

    makeExtensionSlice("extension", p, p.getSnapshot(), null, type.getCode());

    ElementDefinition ecB = new ElementDefinition(true, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    p.getSnapshot().getElement().add(ecB);
    ecB.setPath(type.getCode()+".value");
    ecB.setId(type.getCode()+".value");
    ecB.addRepresentation(PropertyRepresentation.XMLATTR);

    ecB.setDefinition("Primitive value for " +type.getCode());
    ecB.setShort("Primitive value for " +type.getCode());
    ecB.setMin(0);
    ecB.setMax("1");
    ecB.makeBase(type.getBase()+".value", 0, "1");
    t = ecB.addType();
    t.getFormatCommentsPre().add("Note: special primitive values have a FHIRPath system type. e.g. this is compiler magic (i)");
    t.setCode(Constants.NS_SYSTEM_TYPE+"String");
    ToolingExtensions.addUrlExtension(t, ToolingExtensions.EXT_FHIR_TYPE, "string");
    if (!Utilities.noString(type.getRegex()))
      ToolingExtensions.addStringExtension(t, ToolingExtensions.EXT_REGEX, type.getRegex());
    generateElementDefinition(p, ecB, ecA);

    containedSlices.clear();
    addElementConstraintToSnapshot(p);

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }

  public StructureDefinition generate(TypeDefn t) throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setId(t.getName());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ t.getName());
    p.setKind(StructureDefinitionKind.COMPLEXTYPE);
    p.setAbstract(t.getName().equals("Element") || t.getName().equals("BackboneElement") );
    p.setUserData("filename", t.getName().toLowerCase());
    p.setUserData("path", definitions.getSrcFile(t.getName())+".html#"+t.getName());
    assert !Utilities.noString(t.typeCode());
    String b = (t.typeCode().equals("Type") ? "Element" : t.typeCode().equals("Structure") ? "BackboneElement" : t.typeCode());
    if (!Utilities.noString(b)) {
      p.setBaseDefinition("http://hl7.org/fhir/StructureDefinition/"+b);
      p.setDerivation(TypeDerivationRule.SPECIALIZATION);
    }
    p.setType(t.getName());
    p.setFhirVersion(version);
    p.setVersion(version.toCode());
    ToolingExtensions.setStandardsStatus(p, t.getStandardsStatus(), t.getNormativeVersion());

    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(t.getName());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+t.getName()+" Type: "+t.getDefinition());
    p.setPurpose(t.getRequirements());
    p.setDate(genDate.getTime());
    p.setStatus(t.getStandardsStatus() == StandardsStatus.NORMATIVE ?  PublicationStatus.fromCode("active") : PublicationStatus.fromCode("draft")); 


    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    defineElement(null, p, p.getDifferential().getElement(), t, t.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true, "Element", b, false);
    p.getDifferential().getElement().get(0).setIsSummaryElement(null);
    
    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    defineElement(null, p, p.getSnapshot().getElement(), t, t.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.DataType, true, "Element", b, true);
    for (ElementDefinition ed : p.getSnapshot().getElement())
      if (ed.getBase().getPath().equals(ed.getPath()) && ed.getPath().contains("."))
        generateElementDefinition(p, ed, getParent(ed, p.getSnapshot().getElement()));

    containedSlices.clear();
    addElementConstraintToSnapshot(p);

    p.getDifferential().getElement().get(0).getType().clear();
    p.getSnapshot().getElement().get(0).getType().clear();
    p.getSnapshot().getElement().get(0).setIsSummaryElement(null);

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }

  public StructureDefinition generate(ProfiledType pt, List<ValidationMessage> issues) throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setId(pt.getName());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ pt.getName());
    p.setBaseDefinition("http://hl7.org/fhir/StructureDefinition/"+pt.getBaseType());
    p.setKind(StructureDefinitionKind.COMPLEXTYPE);
    p.setType(pt.getBaseType());
    p.setDerivation(TypeDerivationRule.CONSTRAINT);
    p.setAbstract(false);
    p.setUserData("filename", pt.getName().toLowerCase());
    p.setUserData("path", "datatypes.html#"+pt.getName());
    p.setFhirVersion(version);
    p.setVersion(version.toCode());
    ToolingExtensions.setStandardsStatus(p, StandardsStatus.NORMATIVE, "4.0.0");
    p.setStatus(PublicationStatus.fromCode("active")); 

    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(pt.getName());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for Type "+pt.getName()+": "+pt.getDefinition());
    p.setDescription(pt.getDefinition());
    p.setDate(genDate.getTime());

    // first, the differential
    p.setName(pt.getName());
    ElementDefinition e = new ElementDefinition();
    String idroot = e.getId();
    e.setPath(pt.getBaseType());
//    e.setSliceName(pt.getName());
    e.setShort(pt.getDefinition());
    e.setDefinition(preProcessMarkdown(pt.getDescription(), "??"));
    e.setMin(0);
    e.setMax("*");
    e.setIsModifier(false);

    String s = definitions.getTLAs().get(pt.getName().toLowerCase());
    if (s == null)
      throw new Exception("There is no TLA for '"+pt.getName()+"' in fhir.ini");
    ElementDefinitionConstraintComponent inv = new ElementDefinitionConstraintComponent();
    inv.setKey(s+"-1");
    inv.setRequirements(pt.getInvariant().getRequirements());
    inv.setSeverity(ConstraintSeverity.ERROR);
    inv.setHuman(pt.getInvariant().getEnglish());
    if (!"n/a".equals(pt.getInvariant().getExpression())) {
      fpUsages.add(new FHIRPathUsage(pt.getName(), pt.getName(), pt.getName(), null, pt.getInvariant().getExpression()));
      inv.setExpression(pt.getInvariant().getExpression());
    }
    inv.setXpath(pt.getInvariant().getXpath());
    e.getConstraint().add(inv);
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    p.getDifferential().getElement().add(e);

    StructureDefinition base = getTypeSnapshot(pt.getBaseType());

    if (!pt.getRules().isEmpty()) {
      // need to generate a differential based on the rules. 
      // throw new Exception("todo");
      for (String rule : pt.getRules().keySet()) {
        String[] parts = rule.split("\\.");
        String value = pt.getRules().get(rule);
        ElementDefinition er = findElement(p.getDifferential(), pt.getBaseType()+'.'+parts[0]); 
        if (er == null) { 
          er = new ElementDefinition();
          er.setId(pt.getBaseType()+':'+p.getId()+'.'+parts[0]);
          er.setPath(pt.getBaseType()+'.'+parts[0]);
          p.getDifferential().getElement().add(er);
        }
        if (parts[1].equals("min"))
          er.setMin(Integer.parseInt(value));
        else if (parts[1].equals("max"))
          er.setMax(value);
        else if (parts[1].equals("defn"))
          er.setDefinition(preProcessMarkdown(value, "er"));

      }
      List<String> errors = new ArrayList<String>();
      new ProfileUtilities(context, null, pkp).sortDifferential(base, p, p.getName(), errors);
      for (String se : errors)
        issues.add(new ValidationMessage(Source.ProfileValidator, IssueType.STRUCTURE, -1, -1, p.getUrl(), se, IssueSeverity.WARNING));
    }

    reset();

    // now, the snapshot
    new ProfileUtilities(context, issues, pkp).generateSnapshot(base, p, "http://hl7.org/fhir/StructureDefinition/"+pt.getBaseType(), "http://hl7.org/fhir", p.getName());
    for (ElementDefinition ed : p.getSnapshot().getElement())
      generateElementDefinition(p, ed, getParent(ed, p.getSnapshot().getElement()));

    p.getDifferential().getElement().get(0).getType().clear();
    p.getSnapshot().getElement().get(0).getType().clear();
    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addTag("h2").addText("Data type "+pt.getName());
    div.addTag("p").addText(pt.getDefinition());
    div.addTag("h3").addText("Rule");
    div.addTag("p").addText(pt.getInvariant().getEnglish());
    div.addTag("p").addText("XPath:");
    div.addTag("blockquote").addTag("pre").addText(pt.getInvariant().getXpath());
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    addElementConstraintToSnapshot(p);

    new ProfileUtilities(context, issues, pkp).setIds(p, false);
    checkHasTypes(p);
    return p;
  }

  private ElementDefinition findElement(StructureDefinitionDifferentialComponent differential, String path) {
    for (ElementDefinition ed : differential.getElement()) {
      if (ed.getPath().equals(path))
        return ed;
    }
    return null;
  }

  private ElementDefinition getParent(ElementDefinition e, List<ElementDefinition> elist) {
    String p = e.getPath();
    if (!p.contains("."))
      return null;
    p = p.substring(0, p.lastIndexOf("."));
    int i = elist.indexOf(e);
    i--;
    while (i > -1) {
      if (elist.get(i).getPath().equals(p)) {
        return elist.get(i);
      }
      i--;
    }
    return null;
  }

  private StructureDefinition getTypeSnapshot(String baseType) throws Exception {
    StructureDefinition p = definitions.getElementDefn(baseType).getProfile();
    if (p != null &&  p.hasSnapshot())
      return p;
    throw new Exception("Unable to find snapshot for "+baseType);
  }

  public StructureDefinition generate(Profile pack, ResourceDefn r, String usage, boolean logical) throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setId(r.getRoot().getName());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ r.getRoot().getName());
    if (logical)
      p.setKind(StructureDefinitionKind.LOGICAL);
    else
      p.setKind(StructureDefinitionKind.RESOURCE);
    IniFile cini = new IniFile(Utilities.path(rootFolder, "temp", "categories.ini"));
    String cat = cini.getStringProperty("category", r.getName());
    if (!Utilities.noString(cat))
      ToolingExtensions.setStringExtension(p, ToolingExtensions.EXT_RESOURCE_CATEGORY, cat); 
    p.setAbstract(r.isAbstract());
    assert !Utilities.noString(r.getRoot().typeCode());
    if (!Utilities.noString(r.getRoot().typeCode())) {
      p.setBaseDefinition("http://hl7.org/fhir/StructureDefinition/"+r.getRoot().typeCode());
      p.setDerivation(TypeDerivationRule.SPECIALIZATION);
      if (r.getTemplate() != null)
        ToolingExtensions.addStringExtension(p.getBaseDefinitionElement(), ToolingExtensions.EXT_CODE_GENERATION_PARENT, r.getTemplate().getName());
    }
    p.setType(r.getRoot().getName());
    p.setUserData("filename", r.getName().toLowerCase());
    p.setUserData("path", r.getName().toLowerCase()+".html");
    p.setTitle(pack.metadata("display"));
    p.setFhirVersion(version);
    p.setVersion(version.toCode());
    ToolingExtensions.setStandardsStatus(p, r.getStatus(), r.getNormativeVersion());

    if (r.getFmmLevel() != null)
      ToolingExtensions.addIntegerExtension(p, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(r.getFmmLevel()));
    if (r.getSecurityCategorization() != null)
      ToolingExtensions.addCodeExtension(p, ToolingExtensions.EXT_SEC_CAT, r.getSecurityCategorization().toCode());
    ToolResourceUtilities.updateUsage(p, usage);
    p.setName(r.getRoot().getName());
    p.setPublisher("Health Level Seven International"+(r.getWg() == null ? "" : " ("+r.getWg().getName()+")"));
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    if (r.getWg() != null)
      p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, r.getWg().getUrl()));
    ToolingExtensions.setCodeExtension(p, ToolingExtensions.EXT_WORKGROUP, r.getWg().getCode());
    p.setDescription(r.getDefinition());
    p.setPurpose(r.getRoot().getRequirements());
    if (!p.hasPurpose())
      p.setPurpose(r.getRoot().getRequirements());
    p.setDate(genDate.getTime());
    p.setStatus(r.getStatus() == StandardsStatus.NORMATIVE ?  PublicationStatus.fromCode("active") : PublicationStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    defineElement(null, p, p.getDifferential().getElement(), r.getRoot(), r.getRoot().getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true, "BackboneElement", r.getRoot().typeCode(), false);

    reset();
    // now. the snapshot'
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    defineElement(null, p, p.getSnapshot().getElement(), r.getRoot(), r.getRoot().getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.Resource, true, "BackboneElement", r.getRoot().typeCode(), true);
    for (ElementDefinition ed : p.getSnapshot().getElement())
      generateElementDefinition(p, ed, getParent(ed, p.getSnapshot().getElement()));

    if (!logical) {
      List<String> names = new ArrayList<String>();
      names.addAll(r.getSearchParams().keySet());
      Collections.sort(names);
      // 1st, non composites
      for (String pn : names) {
        SearchParameterDefn sp = r.getSearchParams().get(pn);
        if (sp.getType() != SearchType.composite)
          pack.getSearchParameters().add(makeSearchParam(p, r.getName()+"-"+pn.replace("_", ""), r.getName(), sp, r));
      }
      for (String pn : names) {
        SearchParameterDefn sp = r.getSearchParams().get(pn);
        if (sp.getType() == SearchType.composite)
          pack.getSearchParameters().add(makeSearchParam(p, r.getName()+"-"+pn.replace("_", ""), r.getName(), sp, r));
      }
    }
    containedSlices.clear();
    addElementConstraintToSnapshot(p);

    p.getDifferential().getElement().get(0).getType().clear();
    p.getSnapshot().getElement().get(0).getType().clear();
    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }

  private void reset() {
    paths.clear();
  }

  public StructureDefinition generate(Profile pack, ConstraintStructure profile, ResourceDefn resource, String id, ImplementationGuideDefn usage, List<ValidationMessage> issues, ResourceDefn baseResource) throws Exception {

    try {
      return generate(pack, profile, resource, id, null, usage, issues, baseResource);
    } catch (Exception e) {
      throw new Exception("Error processing profile '"+id+"': "+e.getMessage(), e);
    }
  }

  public StructureDefinition generate(Profile pack, ConstraintStructure profile, ResourceDefn resource, String id, String html, ImplementationGuideDefn usage, List<ValidationMessage> issues, ResourceDefn baseResource) throws Exception {
    if (profile.getResource() != null)
      return profile.getResource();

    StructureDefinition p = new StructureDefinition();
    p.setId(FormatUtilities.makeId(id));
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ id);
    if (usage != null && !usage.isCore()) {
      if (!id.startsWith(usage.getCode()+"-"))
        throw new Exception("Error: "+id+" must start with "+usage.getCode()+"-");
    }

    if (!resource.getRoot().getTypes().isEmpty() && (resource.getRoot().getTypes().get(0).getProfile() != null))
      p.setBaseDefinition(resource.getRoot().getTypes().get(0).getProfile());
    else
      p.setBaseDefinition("http://hl7.org/fhir/StructureDefinition/"+resource.getName());
    if (definitions.hasType(resource.getName()))
      p.setKind(StructureDefinitionKind.COMPLEXTYPE);
    else
      p.setKind(StructureDefinitionKind.RESOURCE);
    p.setType(resource.getName());
    p.setDerivation(TypeDerivationRule.CONSTRAINT);
    p.setAbstract(false);
    p.setUserData("filename", id);
    p.setUserData("path", ((usage == null || usage.isCore()) ? "" : usage.getCode()+File.separator)+id+".html");
    p.setTitle(pack.metadata("display"));
    p.setFhirVersion(version);
    p.setVersion(version.toCode());

    if (pack.hasMetadata("summary-"+profile.getTitle()))
      ToolingExtensions.addMarkdownExtension(p, "http://hl7.org/fhir/StructureDefinition/structuredefinition-summary", pack.metadata("summary-"+profile.getTitle()));
    ToolResourceUtilities.updateUsage(p, usage.getCode());
    p.setName(pack.metadata("name"));
    p.setPublisher(pack.metadata("author.name"));
    if (pack.hasMetadata("author.reference"))
      p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, pack.metadata("author.reference")));
    //  <code> opt Zero+ Coding assist with indexing and finding</code>
    p.setDescription(resource.getRoot().getShortDefn());    
    if (!p.hasDescriptionElement() && pack.hasMetadata("description"))
      p.setDescription(preProcessMarkdown(pack.metadata("description"), "pack.description"));
    p.setPurpose(resource.getRoot().getRequirements());
    if (!p.hasPurpose() && pack.hasMetadata("requirements"))
      p.setPurpose(pack.metadata("requirements"));

    p.setExperimental(Utilities.existsInList(pack.metadata("Experimental"), "y", "Y", "true", "TRUE", "1"));
    
    if (pack.hasMetadata("date"))
      p.setDateElement(Factory.newDateTime(pack.metadata("date").substring(0, 10)));
    else
      p.setDate(genDate.getTime());

    if (pack.hasMetadata("fmm-level"))
      ToolingExtensions.addIntegerExtension(p, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(pack.getFmmLevel()));
    else if (pack.hasMetadata("fmm"))
      ToolingExtensions.addIntegerExtension(p, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(pack.metadata("fmm")));
    else if (!Utilities.noString(resource.getFmmLevel()))
      ToolingExtensions.addIntegerExtension(p, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(resource.getFmmLevel()));
    else if (baseResource != null && !Utilities.noString(baseResource.getFmmLevel()))
      ToolingExtensions.addIntegerExtension(p, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(baseResource.getFmmLevel()));
    if (pack.hasMetadata("workgroup"))
      ToolingExtensions.setCodeExtension(p, ToolingExtensions.EXT_WORKGROUP, pack.getWg());
    else if (resource.getWg() != null) 
      ToolingExtensions.setCodeExtension(p, ToolingExtensions.EXT_WORKGROUP, resource.getWg().getCode());      
    else if (baseResource != null && baseResource.getWg() != null) 
      ToolingExtensions.setCodeExtension(p, ToolingExtensions.EXT_WORKGROUP, baseResource.getWg().getCode());      
    if (pack.hasMetadata("Standards-Status")) 
      ToolingExtensions.setStandardsStatus(p, StandardsStatus.fromCode(pack.metadata("Standards-Status")), null);
    else
      ToolingExtensions.setStandardsStatus(p, resource.getStatus(), null);
    
    if (pack.hasMetadata("status")) 
      p.setStatus(PublicationStatus.fromCode(pack.metadata("status")));
    if (pack.getMetadata().containsKey("code"))
      for (String s : pack.getMetadata().get("code")) 
        if (!Utilities.noString(s))
          p.getKeyword().add(Factory.makeCoding(s));

    if (pack.hasMetadata("datadictionary"))
      ToolingExtensions.setStringExtension(p, "http://hl7.org/fhir/StructureDefinition/datadictionary", pack.metadata("datadictionary"));

    Set<String> containedSlices = new HashSet<String>();

    p.setDifferential(new StructureDefinitionDifferentialComponent());
    defineElement(pack, p, p.getDifferential().getElement(), resource.getRoot(), resource.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true, null, null, false);
    List<String> names = new ArrayList<String>();
    names.addAll(resource.getSearchParams().keySet());
    Collections.sort(names);
    for (String pn : names) {
      pack.getSearchParameters().add(makeSearchParam(p, pack.getId()+"-"+resource.getName()+"-"+pn, resource.getName(), resource.getSearchParams().get(pn), resource));
    }
    StructureDefinition base = definitions.getSnapShotForBase(p.getBaseDefinition());

    List<String> errors = new ArrayList<String>();
    new ProfileUtilities(context, null, pkp).sortDifferential(base, p, p.getName(), errors);
    for (String s : errors)
      issues.add(new ValidationMessage(Source.ProfileValidator, IssueType.STRUCTURE, -1, -1, p.getUrl(), s, IssueSeverity.WARNING));
    reset();
    // ok, c is the differential. now we make the snapshot
    new ProfileUtilities(context, issues, pkp).generateSnapshot(base, p, "http://hl7.org/fhir/StructureDefinition/"+p.getType(), "http://hl7.org/fhir", p.getName());
    reset();

    p.getDifferential().getElement().get(0).getType().clear();
    p.getSnapshot().getElement().get(0).getType().clear();
    addElementConstraintToSnapshot(p);

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    new ProfileUtilities(context, issues, pkp).setIds(p, false);
    checkHasTypes(p);
    return p;
  }

  private String preProcessMarkdown(String text, String location) throws Exception {
    return MarkDownPreProcessor.process(definitions, context, null, text, location, null);
  }

  private SearchParamType getSearchParamType(SearchParameterDefn.SearchType type) {
    switch (type) {
    case number:
      return SearchParamType.NUMBER;
    case string:
      return SearchParamType.STRING;
    case date:
      return SearchParamType.DATE;
    case reference:
      return SearchParamType.REFERENCE;
    case token:
      return SearchParamType.TOKEN;
    case uri:
      return SearchParamType.URI;
    case composite:
      return SearchParamType.COMPOSITE;
    case quantity:
      return SearchParamType.QUANTITY;
    case special:
      return SearchParamType.SPECIAL;
    }
    return null;
  }

  public SearchParameter makeSearchParam(StructureDefinition p, String id, String rn, SearchParameterDefn spd, ResourceDefn rd) throws Exception  {
    boolean shared;
    boolean created = true;
    SearchParameter sp;
    if (definitions.getCommonSearchParameters().containsKey(rn+"::"+spd.getCode())) {
      shared = true;
      CommonSearchParameter csp = definitions.getCommonSearchParameters().get(rn+"::"+spd.getCode());
      if (csp.getDefinition() == null) {
        sp = new SearchParameter();
        csp.setDefinition(sp);
        sp.setId(csp.getId());
      } else { 
        created = false;
        sp = csp.getDefinition();
      }
    } else {
      shared = false;
      sp = new SearchParameter();
      sp.setId(id.replace("[", "").replace("]", ""));
    }
    spd.setCommonId(sp.getId());
    if (created) {
      sp.setUrl("http://hl7.org/fhir/SearchParameter/"+sp.getId());
      sp.setVersion(Constants.VERSION);
      if (context.getSearchParameter(sp.getUrl()) != null)
        throw new Exception("Duplicated Search Parameter "+sp.getUrl());
      context.cacheResource(sp);
      spd.setResource(sp);
      definitions.addNs(sp.getUrl(), "Search Parameter: "+sp.getName(), rn.toLowerCase()+".html#search");
      sp.setStatus(spd.getStandardsStatus() == StandardsStatus.NORMATIVE ? PublicationStatus.fromCode("active") : PublicationStatus.fromCode("draft"));
      StandardsStatus sst = ToolingExtensions.getStandardsStatus(sp);
      if (sst == null || (spd.getStandardsStatus() == null && spd.getStandardsStatus().isLowerThan(sst)))
        ToolingExtensions.setStandardsStatus(sp, spd.getStandardsStatus(), spd.getNormativeVersion());
      sp.setExperimental(p.getExperimental());
      sp.setName(spd.getCode());
      sp.setCode(spd.getCode());
      sp.setDate(genDate.getTime());
      sp.setPublisher(p.getPublisher());
      for (ContactDetail tc : p.getContact()) {
        ContactDetail t = sp.addContact();
        if (tc.hasNameElement())
          t.setNameElement(tc.getNameElement().copy());
        for (ContactPoint ts : tc.getTelecom())
          t.getTelecom().add(ts.copy());
      }
      if (!definitions.hasResource(p.getType()) && !p.getType().equals("Resource") && !p.getType().equals("DomainResource"))
        throw new Exception("unknown resource type "+p.getType());
      sp.setType(getSearchParamType(spd.getType()));
//      if (sp.getType() == SearchParamType.REFERENCE && spd.isHierarchy()) {
//        sp.addModifier(SearchParameter.SearchModifierCode.BELOW);
//        sp.addModifier(SearchParameter.SearchModifierCode.ABOVE);
//      }
      if (shared) {
        sp.setDescription("Multiple Resources: \r\n\r\n* ["+rn+"]("+rn.toLowerCase()+".html): " + spd.getDescription()+"\r\n");
      } else
        sp.setDescription(preProcessMarkdown(spd.getDescription(), "Search Description"));
      if (!Utilities.noString(spd.getExpression())) 
        sp.setExpression(spd.getExpression());
//      addModifiers(sp);
      addComparators(sp);
      String xpath = Utilities.noString(spd.getXPath()) ? new XPathQueryGenerator(this.definitions, null, null).generateXpath(spd.getPaths()) : spd.getXPath();
      if (xpath != null) {
        if (xpath.contains("[x]"))
          xpath = convertToXpath(xpath);
        sp.setXpath(xpath);
        sp.setXpathUsage(spd.getxPathUsage());
      }
      if (sp.getType() == SearchParamType.COMPOSITE) {
        for (CompositeDefinition cs : spd.getComposites()) {
          SearchParameterDefn cspd = findSearchParameter(rd, cs.getDefinition());
          if (cspd != null)
            sp.addComponent().setExpression(cs.getExpression()).setDefinition(cspd.getUrl());
          else
            sp.addComponent().setExpression(cs.getExpression()).setDefinition("http://hl7.org/fhir/SearchParameter/"+rn+"-"+cs.getDefinition());
        }
        sp.setMultipleOr(false);
      } 
      sp.addBase(p.getType());
    } else {
      if (sp.getType() != getSearchParamType(spd.getType()))
        throw new FHIRException("Type mismatch on common parameter: expected "+sp.getType().toCode()+" but found "+getSearchParamType(spd.getType()).toCode());
      if (!sp.getDescription().contains("["+rn+"]("+rn.toLowerCase()+".html)"))
        sp.setDescription(sp.getDescription()+"* ["+rn+"]("+rn.toLowerCase()+".html): " + spd.getDescription()+"\r\n");
//      Extension ext = sp.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/SearchParameter-label");
//      ext.addExtension("resource", new CodeType(spd.getDescription()));
//      ext.addExtension("description", new MarkdownType(spd.getDescription()));
      if (!Utilities.noString(spd.getExpression()) && !sp.getExpression().contains(spd.getExpression())) 
        sp.setExpression(sp.getExpression()+" | "+spd.getExpression());
      String xpath = new XPathQueryGenerator(this.definitions, null, null).generateXpath(spd.getPaths());
      if (xpath != null) {
        if (xpath.contains("[x]"))
          xpath = convertToXpath(xpath);
        if (sp.getXpath() != null && !sp.getXpath().contains(xpath)) 
          sp.setXpath(sp.getXpath()+" | " +xpath);
        if (sp.getXpathUsage() != spd.getxPathUsage()) 
          throw new FHIRException("Usage mismatch on common parameter: expected "+sp.getXpathUsage().toCode()+" but found "+spd.getxPathUsage().toCode());
      }
      boolean found = false;
      for (CodeType ct : sp.getBase())
        found = found || p.getType().equals(ct.asStringValue());
      if (!found)
        sp.addBase(p.getType());
    }
    spd.setUrl(sp.getUrl());
    for(String target : spd.getWorkingTargets()) {
      if("Any".equals(target) == true) {   	  
        for(String resourceName : definitions.sortedResourceNames()) {
          boolean found = false;
          for (CodeType st : sp.getTarget())
            found = found || st.asStringValue().equals(resourceName);
          if (!found)
            sp.addTarget(resourceName);
        }
      }
      else {
        boolean found = false;
        for (CodeType st : sp.getTarget())
          found = found || st.asStringValue().equals(target);
        if (!found)
          sp.addTarget(target);
      }
    }

    return sp;
  }


  private SearchParameterDefn findSearchParameter(ResourceDefn rd, String definition) {
    for (SearchParameterDefn spd : rd.getSearchParams().values()) {
      if (spd.getCode().equals(definition))
        return spd;
    }
    return null;
  }


  private void addComparators(SearchParameter sp) {
    if (sp.getType() == SearchParamType.NUMBER || sp.getType() == SearchParamType.DATE || sp.getType() == SearchParamType.QUANTITY) {
      sp.addComparator(SearchComparator.EQ);
      sp.addComparator(SearchComparator.NE);
      sp.addComparator(SearchComparator.GT);
      sp.addComparator(SearchComparator.GE);
      sp.addComparator(SearchComparator.LT);
      sp.addComparator(SearchComparator.LE);
      sp.addComparator(SearchComparator.SA);
      sp.addComparator(SearchComparator.EB);
      sp.addComparator(SearchComparator.AP);
    }
  }


  private void addModifiers(SearchParameter sp) {
    sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.MISSING); // on everything
    switch (sp.getType()) {
    case STRING: 
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.EXACT);
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.CONTAINS);
      return;
    case TOKEN: 
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.TEXT);
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.NOT);
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.IN);
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.NOTIN);
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.BELOW);
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.ABOVE);
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.OFTYPE);
      return;
    case REFERENCE: 
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.TYPE);
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.IDENTIFIER);
      if (isCircularReference(sp)) {
        sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.BELOW);
        sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.ABOVE);
      }
      return;
    case URI: 
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.BELOW);
      sp.addModifier(org.hl7.fhir.r4.model.SearchParameter.SearchModifierCode.ABOVE);
      return;
     // no modifiers for these
    case NUMBER: 
    case DATE: 
    case COMPOSITE:
    case QUANTITY: 
    case SPECIAL: 
    default:
      return;
    }
  }


  private boolean isCircularReference(SearchParameter sp) {
    try {
      ElementDefn e = definitions.getElementByPath(sp.getExpression().split("\\."), "search parameter analysis", true);
      return e != null && e.hasHierarchy() && e.getHierarchy();
    } catch (Exception e) {
      return false;
      
    }
  }


  private String convertToXpath(String xpath) {
    String[] parts = xpath.split("\\/");
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (String p : parts) {
      if (first)
        first = false;
      else
        b.append("/");
      if (p.startsWith("f:")) {
        String v = p.substring(2);
        if (v.endsWith("[x]"))
          b.append("*[starts-with(local-name(.), '"+v.replace("[x]", "")+"')]");
        else
          b.append(p);
      }        
      else
        b.append(p);
    }
    return b.toString();
  }

  private ElementDefinitionBindingComponent generateBinding(BindingSpecification src) throws Exception {
    if (src == null)
      
      return null;
    ElementDefinitionBindingComponent dst = new ElementDefinitionBindingComponent();
    dst.setDescription(src.getDefinition());
    if (src.getBinding() != BindingMethod.Unbound) {
      dst.setStrength(src.getStrength());    
      dst.setValueSet(buildValueSetReference(src));
      if (src.hasMax()) {
        dst.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-maxValueSet").setValue(new CanonicalType(src.getMaxReference() != null ? src.getMaxReference() : src.getMaxValueSet().getUrl()));
      }
    } else {
      dst.setStrength(BindingStrength.EXAMPLE);    
    }
    dst.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-bindingName").setValue(new StringType(src.getName()));
    if (src.isShared())
      dst.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-isCommonBinding").setValue(new BooleanType(true));
    return dst;
  }

  private String buildValueSetReference(BindingSpecification src) throws Exception {
    String v = ""; 
    if (src.getStrength() == BindingStrength.REQUIRED) {
      if (src.getValueSet() != null && src.getValueSet().hasVersion()) {
        v = "|"+src.getValueSet().getVersion();
      } else {
        v = "|"+version.toCode();
      }
    }
    switch (src.getBinding()) {
    case Unbound: return null;
    case CodeList:
      if (src.getValueSet()!= null)
        return src.getValueSet().getUrl()+v;
      else if (src.getReference().startsWith("#"))
        return "http://hl7.org/fhir/ValueSet/"+src.getReference().substring(1)+v;
      else
        throw new Exception("not done yet");
    case ValueSet: 
      if (!Utilities.noString(src.getReference()))
        if (src.getReference().startsWith("http"))
          return src.getReference()+v;
        else if (src.getValueSet()!= null)
          return src.getValueSet().getUrl()+v;
        else if (src.getReference().startsWith("valueset-"))
          return "http://hl7.org/fhir/ValueSet/"+src.getReference().substring(9)+v;
        else
          return "http://hl7.org/fhir/ValueSet/"+src.getReference()+v;
      else
        return null; // throw new Exception("not done yet");
    case Special: 
      return "http://hl7.org/fhir/ValueSet/"+src.getReference().substring(1)+v;
    default: 
      throw new Exception("not done yet");
    }
  }

  /**
   * note: snapshot implies that we are generating a resource or a data type; for other profiles, the snapshot is generated elsewhere
   */
  private ElementDefinition defineElement(Profile ap, StructureDefinition p, List<ElementDefinition> elements, ElementDefn e, String path, Set<String> slices, List<SliceHandle> parentSlices, SnapShotMode snapshot, boolean root, String defType, String inheritedType, boolean defaults) throws Exception 
  {
    boolean handleDiscriminator = true;
    if (!Utilities.noString(e.getProfileName()) && !e.getDiscriminator().isEmpty() && !slices.contains(path)) {
      handleDiscriminator = false;
      // hey, we jumped straight into the slices with setting up the slicing (allowed in the spreadsheets, but not otherwise)
      ElementDefinition slicer = new ElementDefinition();
      elements.add(slicer);
      slicer.setId(path);
      slicer.setPath(path);
      processDiscriminator(e, path, slicer);
      if (e.getMaxCardinality() != null)
        slicer.setMax(e.getMaxCardinality() == Integer.MAX_VALUE ? "*" : e.getMaxCardinality().toString());
      
      slices.add(path);
    }

    // todo for task 12259
//    if (ap != null) {
//      String base = isImplicitTypeConstraint(path);
//      if (base != null) {
//        ElementDefinition typeConstrainer = new ElementDefinition(ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
//        elements.add(typeConstrainer);
//        typeConstrainer.setId(base);
//        typeConstrainer.setPath(base);
//        String type = path.substring(base.length()-3);
//        if (definitions.hasPrimitiveType(Utilities.uncapitalize(type)))
//          type = Utilities.uncapitalize(type);
//        typeConstrainer.addType().setCode(type);
//      }
//    }
    ElementDefinition ce = new ElementDefinition(defaults, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    elements.add(ce);
//    todo ce.setId(path.substring(path.indexOf(".")+1));

    if (e.getStandardsStatus() != null)
      ToolingExtensions.setStandardsStatus(ce, e.getStandardsStatus(), e.getNormativeVersion());

    ce.setId(path);
    ce.setPath(path);

    if (e.isXmlAttribute())
      ce.addRepresentation(PropertyRepresentation.XMLATTR);
    List<SliceHandle> myParents = new ArrayList<ProfileGenerator.SliceHandle>();
    myParents.addAll(parentSlices);

    // If this element has a profile name, and this is the first of the
    // slicing group, add a slicing group "entry" (= first slice member,
    // which holds Slicing information)
    if (e.hasDescriminator() || !Utilities.noString(e.getProfileName())) {
      if (e.getDiscriminator().size() > 0 && !slices.contains(path) && handleDiscriminator) {
        processDiscriminator(e, path, ce);
        slices.add(path);
      }
      if (!Utilities.noString(e.getProfileName())) {
        SliceHandle hnd = new SliceHandle();
        hnd.name = path; // though this it not used?
        myParents.add(hnd);
        if (path.contains(".")) { // We don't want a slice name on the root
          ce.setSliceName(e.getProfileName());
          ce.setId(ce.getId()+":"+e.getProfileName());
        }
      }
    }

    if (Utilities.existsInList(ce.getPath(), "Element.extension", "DomainResource.extension", "DomainResource.modifierExtension") && !ce.hasSlicing() && !ce.hasSliceName()) {
      ce.getSlicing().setDescription("Extensions are always sliced by (at least) url").setRules(SlicingRules.OPEN).addDiscriminator().setType(DiscriminatorType.VALUE).setPath("url");
    }
    if (!Utilities.noString(inheritedType) && snapshot != SnapShotMode.None) {
      ElementDefn inh = definitions.getElementDefn(inheritedType);
      buildDefinitionFromElement(path, ce, inh, ap, p, inheritedType);
    } else if (path.contains(".") && Utilities.noString(e.typeCode()) && snapshot != SnapShotMode.None)
      addElementConstraints(defType, ce);
    buildDefinitionFromElement(path, ce, e, ap, p, null);
    if (!Utilities.noString(e.getStatedType())) {
      ToolingExtensions.addStringExtension(ce, "http://hl7.org/fhir/StructureDefinition/structuredefinition-explicit-type-name", e.getStatedType());
    }

    if (!root) {
      if (e.typeCode().startsWith("@"))  {
        ce.setContentReference("#"+getIdForPath(elements, e.typeCode().substring(1)));
      } else if (Utilities.existsInList(path, "Element.id", "Extension.url") || path.endsWith(".id")) {
        TypeRefComponent tr = ce.addType();
        tr.getFormatCommentsPre().add("Note: special primitive values have a FHIRPath system type. e.g. this is compiler magic (j)");
        tr.setCode(Constants.NS_SYSTEM_TYPE+ "String"); 
        if (path.equals("Extension.url")) {
          ToolingExtensions.addUrlExtension(tr, ToolingExtensions.EXT_FHIR_TYPE, "uri");
        } else {
          ToolingExtensions.addUrlExtension(tr, ToolingExtensions.EXT_FHIR_TYPE, "string");
        }
      } else {
        List<TypeRef> expandedTypes = new ArrayList<TypeRef>();
        for (TypeRef t : e.getTypes()) {
          // Expand any Resource(A|B|C) references
          if (t.hasParams() && !Utilities.existsInList(t.getName(), "Reference", "canonical")) {
            throw new Exception("Only resource types can specify parameters.  Path " + path + " in profile " + p.getName());
          }
          if (t.getParams().size() > 1)
          {
            if (t.getProfile() != null && t.getParams().size() !=1) {
              throw new Exception("Cannot declare profile on a resource reference declaring multiple resource types.  Path " + path + " in profile " + p.getName());
            }
            for(String param : t.getParams()) {
              TypeRef childType = new TypeRef(t.getName());
              childType.getParams().add(param);
              childType.getAggregations().addAll(t.getAggregations());
              expandedTypes.add(childType);
            }
          } else if (t.isWildcardType()) {
            // this list is filled out manually because it may be running before the types referred to have been loaded
            for (String n : TypesUtilities.wildcardTypes()) 
              expandedTypes.add(new TypeRef(n));

          } else if (!t.getName().startsWith("=")) {
            if (definitions.isLoaded() && (!definitions.hasResource(t.getName()) && !definitions.hasType(t.getName()) 
                && !definitions.hasElementDefn(t.getName()) && !definitions.getBaseResources().containsKey(t.getName()) &&!t.getName().equals("xhtml") )) {
              throw new Exception("Bad Type '"+t.getName()+"' at "+path+" in profile "+p.getUrl());
            }
            expandedTypes.add(t);
          }
        }
        if (expandedTypes.isEmpty()) {
          if (defType != null)
            ce.addType().setCode(defType);
        } else for (TypeRef t : expandedTypes) {
          String profile = null;
          String tc = null;
          if (definitions.getConstraints().containsKey(t.getName())) {
            ProfiledType pt = definitions.getConstraints().get(t.getName());
            tc= pt.getBaseType();
            profile = "http://hl7.org/fhir/StructureDefinition/"+pt.getName();
          } else {
            tc = t.getName();
            profile = t.getProfile();
          }
          TypeRefComponent type = ce.getType(tc);
          if (profile == null && t.hasParams()) {
            profile = t.getParams().get(0);
          }
          if (profile != null) {
            if (type.getCode().equals("Extension")) {
              // check that the extension is being used correctly:
              StructureDefinition ext = context.getExtensionStructure(null, profile);
              if (ext == null) {
                throw new Exception("Unable to resolve extension definition: " + profile);
              }
              boolean srcMod = ext.getSnapshot().getElement().get(0).getIsModifier();
              boolean tgtMod = e.isModifier();
              if (srcMod && !tgtMod)
                throw new Exception("The extension '"+profile+"' is a modifier extension, but is being used as if it is not a modifier extension");
              if (!srcMod && tgtMod)
                throw new Exception("The extension '"+profile+"' is not a modifier extension, but is being used as if it is a modifier extension");
            }
            String pr;
            if (profile.startsWith("http:") || profile.startsWith("#")) {
              pr = profile;
            } else 
              pr = "http://hl7.org/fhir/StructureDefinition/" + (profile.equals("Any") ? "Resource" : profile);
            if (type.getCode().equals("Reference") || type.getCode().equals("canonical") ) {
              type.addTargetProfile(pr);
              if (e.hasHierarchy())
                ToolingExtensions.addBooleanExtension(type, ToolingExtensions.EXT_HIERARCHY, e.getHierarchy());
            } else
              type.addProfile(pr);
          }

          for (String aggregation : t.getAggregations()) {
            type.addAggregation(AggregationMode.fromCode(aggregation));
          }	      	
        }
      }
    }
    String w5 = translateW5(e.getW5());
    if (w5 != null)
      addMapping(p, ce, "http://hl7.org/fhir/fivews", w5, ap);
    if (e.isTranslatable())
      ce.addExtension("http://hl7.org/fhir/StructureDefinition/elementdefinition-translatable", new BooleanType(true));
    if (!Utilities.noString(e.getOrderMeaning()))
        ce.setOrderMeaning(e.getOrderMeaning());
    
    if (e.hasBinding()) {
      ce.setBinding(generateBinding(e.getBinding()));
    }

    if (snapshot != SnapShotMode.None && !e.getElements().isEmpty()) {    
      //      makeExtensionSlice("extension", p, c, e, path);
      //      if (snapshot == SnapShotMode.Resource) { 
      //        makeExtensionSlice("modifierExtension", p, c, e, path);

      //        if (!path.contains(".")) {
      //          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResources().get("Resource").getRoot().getElementByName("language")));
      //          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResources().get("DomainResource").getRoot().getElementByName("text")));
      //          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResources().get("DomainResource").getRoot().getElementByName("contained")));
      //        }
      //      }
    }
    if (defaults)
      ce.makeBase();
    Set<String> containedSlices = new HashSet<String>();
    if (snapshot != SnapShotMode.None) {
      if (!root && Utilities.noString(e.typeCode())) {
        if (snapshot == SnapShotMode.Resource)
          defineAncestorElements("BackboneElement", path, snapshot, containedSlices, p, elements, defType, defaults);
        else
          defineAncestorElements("Element", path, snapshot, containedSlices, p, elements, defType, defaults);
      } else if (root && !Utilities.noString(e.typeCode())) 
        defineAncestorElements(e.typeCode(), path, snapshot, containedSlices, p, elements, defType, defaults);
    }
    for (ElementDefn child : e.getElements()) 
      defineElement(ap, p, elements, child, path+"."+child.getName(), containedSlices, myParents, snapshot, false, defType, null, defaults);

    return ce;
  }

  private String translateW5(String w5) {
    if (w5 == null)
      return null;
    W5Entry e = definitions.getW5s().get(w5);
    return e == null ? null : e.getFiveWs();
  }

  private String isImplicitTypeConstraint(String path) throws Exception {
    if (!path.contains(".")) 
      return null;
    String t = path.substring(0, path.indexOf("."));
    ElementDefn tt = definitions.getElementDefn(t);
    return isImplicitTypeConstraint(tt.getName(), tt, path);
  }

  private String isImplicitTypeConstraint(String path, ElementDefn tt, String s) {
    if (path.equals(s))
      return null;
    if (path.contains("[x]")) {
      String base = path.substring(0, path.indexOf("["));
      if (s.startsWith(base) && !s.substring(base.length()).contains("."))
        return path;
    }
    if (s.equals(path+".extension"))
      return null;
    for (ElementDefn e : tt.getElements()) {
      String ans = isImplicitTypeConstraint(path+"."+e.getName(), e, s);
      if (ans != null)
        return ans;
    }
    return null;
  }

  private void buildDefinitionFromElement(String path, ElementDefinition ce, ElementDefn e, Profile ap, StructureDefinition p, String inheritedType) throws Exception {
    if (!Utilities.noString(e.getComments()))
      ce.setComment(preProcessMarkdown(e.getComments(), "Element Comments"));
    if (!Utilities.noString(e.getShortDefn()))
      ce.setShort(e.getShortDefn());
    if (!Utilities.noString(e.getDefinition())) {
      ce.setDefinition(preProcessMarkdown(e.getDefinition(), "Element Definition"));
      if (!Utilities.noString(e.getShortDefn()))
        ce.setShort(e.getShortDefn());
    }
    if (path.contains(".") && !Utilities.noString(e.getRequirements())) 
      ce.setRequirements(preProcessMarkdown(e.getRequirements(), "Element Requirements"));
    if (e.hasMustSupport())
      ce.setMustSupport(e.isMustSupport());

    if (!Utilities.noString(e.getMaxLength())) 
      ce.setMaxLength(Integer.parseInt(e.getMaxLength())); 

    // no purpose here
    if (e.getMinCardinality() != null)
      ce.setMin(e.getMinCardinality());
    if (e.getMaxCardinality() != null)
      ce.setMax(e.getMaxCardinality() == Integer.MAX_VALUE ? "*" : e.getMaxCardinality().toString());

    // we don't know mustSupport here
    if (e.hasModifier())
      ce.setIsModifier(e.isModifier());
    if (ce.getIsModifier())
      ce.setIsModifierReason(e.getModifierReason());
    
    // ce.setConformance(getType(e.getConformance()));
    for (Invariant id : e.getStatedInvariants()) 
      ce.addCondition(id.getId());

    ce.setFixed(e.getFixed());
    ce.setPattern(e.getPattern());
//    ce.setDefaultValue(e.getDefaultValue());
    ce.setMeaningWhenMissing(e.getMeaningWhenMissing());
    if (e.getExample() != null)
      ce.addExample().setLabel("General").setValue(e.getExample());
    for (Integer i : e.getOtherExamples().keySet()) {
      Extension ex = ce.addExtension();
      ex.setUrl("http://hl7.org/fhir/StructureDefinition/structuredefinition-example");
      ex.addExtension().setUrl("index").setValue(new StringType(i.toString()));
      ex.addExtension().setUrl("exValue").setValue(e.getOtherExamples().get(i));      
    }
    for (String s : e.getAliases())
      ce.addAlias(s);

    if (e.hasSummaryItem() && ce.getPath().contains("."))
      ce.setIsSummaryElement(Factory.newBoolean(e.isSummary()));

    for (String n : definitions.getMapTypes().keySet()) {
      addMapping(p, ce, n, e.getMapping(n), null);
    }
    if (ap != null) {
      for (String n : ap.getMappingSpaces().keySet()) {
        addMapping(p, ce, n, e.getMapping(n), ap);
      }
    }
    ToolingExtensions.addDisplayHint(ce, e.getDisplayHint());

    convertConstraints(e, ce, inheritedType);
    // we don't have anything to say about constraints on resources
  }

  private void convertConstraints(ElementDefn e, ElementDefinition ce, String source) throws FHIRException {
    for (String in : e.getInvariants().keySet()) {
      ElementDefinitionConstraintComponent con = new ElementDefinitionConstraintComponent();
      Invariant inv = e.getInvariants().get(in);
      con.setKey(inv.getId());
      if (!con.hasKey()) {
        profileCounter++;
        con.setKey("prf-"+Integer.toString(profileCounter ));
      }
      con.setRequirements(inv.getRequirements());
      if (Utilities.noString(inv.getSeverity()))
        con.setSeverity(ConstraintSeverity.ERROR);
      else if (inv.getSeverity().equals("best-practice")) {
        con.setSeverity(ConstraintSeverity.WARNING);
        ToolingExtensions.addBooleanExtension(con, ToolingExtensions.EXT_BEST_PRACTICE, true);
        if (Utilities.noString(inv.getExplanation()))
          throw new FHIRException("Best Practice Invariants need to have an explanation");
        con.addExtension().setUrl(ToolingExtensions.EXT_BEST_PRACTICE_EXPLANATION).setValue(new MarkdownType(inv.getExplanation()));
      } else
        con.setSeverity(ConstraintSeverity.fromCode(inv.getSeverity()));
      con.setHuman(inv.getEnglish());
      con.setXpath(inv.getXpath());
      if (source != null) {
        con.setSource("http://hl7.org/fhir/StructureDefinition/"+source);
      }
      if (!"n/a".equals(inv.getExpression()))
        con.setExpression(inv.getExpression());
      ce.getConstraint().add(con);
    }
  }

  private String getIdForPath(List<ElementDefinition> list, String path) throws Exception {
    for (ElementDefinition ed : list)
      if (ed.getPath().equals(path))
        return ed.getId();
    throw new Exception("Unable to resolve "+path);
  }

  private void processDiscriminator(ElementDefn e, String path, ElementDefinition ce) throws Exception {
    ce.setSlicing(new ElementDefinitionSlicingComponent());
    ce.getSlicing().setDescription(e.getSliceDescription());
    String[] d = e.getDiscriminator().get(0).split("\\|");
    if (d.length >= 1)
      ce.getSlicing().addDiscriminator(ProfileUtilities.interpretR2Discriminator(d[0].trim(), false));
    if (d.length >= 2)
      ce.getSlicing().setOrdered(Boolean.parseBoolean(d[1].trim()));
    else
      ce.getSlicing().setOrdered(false);
    if (d.length >= 3)
      ce.getSlicing().setRules(SlicingRules.fromCode(d[2].trim()));
    else
      ce.getSlicing().setRules(SlicingRules.OPEN);
    for (int i = 1; i < e.getDiscriminator().size(); i++) { // we've already process the first in the list
      String s = e.getDiscriminator().get(i).trim();
      if (s.contains("|"))
        throw new Exception("illegal discriminator \""+s+"\" at "+path);
      ce.getSlicing().addDiscriminator(ProfileUtilities.interpretR2Discriminator(s, false));
    }
  }

  private String actualTypeName(String type) {
    if (type.equals("Type"))
      return "Element";
    if (type.equals("Structure"))
      return "BackboneElement";
    return type;
  }
  private void defineAncestorElements(String type, String path, SnapShotMode snapshot, Set<String> containedSlices, StructureDefinition p, List<ElementDefinition> elements, String dt, boolean defaults) throws Exception {
    ElementDefn e = definitions.getElementDefn(actualTypeName(type));
    if (!Utilities.noString(e.typeCode()))
      defineAncestorElements(e.typeCode(), path, snapshot, containedSlices, p, elements, dt, defaults);

    for (ElementDefn child : e.getElements()) {
      ElementDefinition ed = defineElement(null, p, elements, child, path+"."+child.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), snapshot, false, dt, null, defaults);
      if (!ed.hasBase())
        ed.setBase(new ElementDefinitionBaseComponent());
      ed.getBase().setPath(e.getName()+"."+child.getName());
      if (child.getMinCardinality() != null)
        ed.getBase().setMin(child.getMinCardinality());
      if (child.getMaxCardinality() != null)
        ed.getBase().setMax(child.getMaxCardinality() == Integer.MAX_VALUE ? "*" : child.getMaxCardinality().toString());
      if (snapshot == SnapShotMode.DataType && ed.getPath().endsWith(".extension") && !ed.hasSlicing())
        ed.getSlicing().setDescription("Extensions are always sliced by (at least) url").setRules(SlicingRules.OPEN).addDiscriminator().setType(DiscriminatorType.VALUE).setPath("url");
    }
  }

  /*
   *     // resource
    // domain resource
    for (ElementDefn child : definitions.getBaseResources().get("DomainResource").getRoot().getElements()) 
      defineElement(null, p, p.getSnapshot(), child, r.getRoot().getName()+"."+child.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.Resource);

   */
  /*
  private String registerMapping(ConformancePackage ap, StructureDefinition p, String m) {
    for (StructureDefinitionMappingComponent map : p.getMapping()) {
      if (map.getUri().equals(m))
        return map.getIdentity();
    }
    StructureDefinitionMappingComponent map = new StructureDefinitionMappingComponent();
    MappingSpace space = definitions.getMapTypes().get(m);
    if (space != null)
      map.setIdentity(space.getId());
    else
      map.setIdentity("m" + Integer.toString(p.getMapping().size()+1));
    map.setUri(m);
    String name = ap.metadata(m+"-name");
    if (Utilities.noString(name) && space != null)
      name = space.getTitle();
    if (!Utilities.noString(name))
      map.setName(name);
    String comments = ap.metadata(m+"-comments");
    if (Utilities.noString(comments) && space != null)
        comments = space.getPreamble();
    if (!Utilities.noString(comments))
      map.setComments(comments);
    return map.getIdentity();
  }
   */


  private ElementDefinition addElementConstraint(StructureDefinition sd, ElementDefinition ed) {
    if (!ed.getPath().contains(".") && sd.getKind() == StructureDefinitionKind.RESOURCE)
      return ed;
    
    if (hasSystemType(ed))
      return ed;
    if (isResource(ed))
      return ed;
    if (hasConstraint(ed, "ele-1")) 
      return ed;
    
    ElementDefinitionConstraintComponent inv = ed.addConstraint();
    inv.setKey("ele-1");
    inv.setSeverity(ConstraintSeverity.ERROR);
    inv.setHuman("All FHIR elements must have a @value or children");
    inv.setExpression("hasValue() or (children().count() > id.count())");
    inv.setXpath("@value|f:*|h:div");
    inv.setSource("http://hl7.org/fhir/StructureDefinition/Element");
    return ed;  
  }
  
  private boolean isResource(ElementDefinition ed) {
    for (TypeRefComponent t : ed.getType()) {
      if ("Resource".equals(t.getCode()))
        return true;
    }
    return false;

  }


  private ElementDefinition addExtensionConstraint(StructureDefinition sd, ElementDefinition ed) {    
    if (!typeIsExtension(ed))
      return ed;
    if (hasConstraint(ed, "ext-1")) 
      return ed;
    
    ElementDefinitionConstraintComponent inv = ed.addConstraint();
    inv.setKey("ext-1");
    inv.setSeverity(ConstraintSeverity.ERROR);
    inv.setHuman("Must have either extensions or value[x], not both");
    inv.setExpression("extension.exists() != value.exists()");
    inv.setXpath("exists(f:extension)!=exists(f:*[starts-with(local-name(.), \"value\")])");
    inv.setSource("http://hl7.org/fhir/StructureDefinition/Extension");
    return ed;  
  }
  
  
  private boolean typeIsExtension(ElementDefinition ed) {
    return ed.getType().size() == 1 && ed.getType().get(0).getCode().equals("Extension");
  }


  private boolean hasConstraint(ElementDefinition ed, String id) {
    for (ElementDefinitionConstraintComponent inv : ed.getConstraint()) {
      if (id.equals(inv.getKey()))
        return true;
    }
    return false;
  }


  private ElementDefinition makeExtensionSlice(String extensionName, StructureDefinition p, StructureDefinitionSnapshotComponent c, ElementDefn e, String path) throws URISyntaxException, Exception {
    ElementDefinition ex = createBaseDefinition(p, path, definitions.getBaseResources().get("DomainResource").getRoot().getElementByName(definitions, extensionName, false, false, null));
    c.getElement().add(ex);
    if (!ex.hasBase())
      ex.setBase(new ElementDefinitionBaseComponent());
    ex.getBase().setPath("Element.extension");
    ex.getBase().setMin(0);
    ex.getBase().setMax("*");
    
    ElementDefinitionConstraintComponent inv = ex.addConstraint();
    inv.setKey("ext-1");
    inv.setSeverity(ConstraintSeverity.ERROR);
    inv.setHuman("Must have either extensions or value[x], not both");
    inv.setExpression("extension.exists() != value.exists()");
    inv.setXpath("exists(f:extension)!=exists(f:*[starts-with(local-name(.), 'value')])");
    inv.setSource("http://hl7.org/fhir/StructureDefinition/Extension");
    
    return ex;
  }

  private void addMapping(StructureDefinition p, ElementDefinition definition, String target, String map, Profile pack) {
    if (!Utilities.noString(map)) {
      String id;
      if (pack != null && pack.getMappingSpaces().containsKey(target))
        id = pack.getMappingSpaces().get(target).getId();
      else
        id = definitions.getMapTypes().get(target).getId();

      if (!mappingExists(p, id)) {
        StructureDefinitionMappingComponent pm = new StructureDefinitionMappingComponent();
        p.getMapping().add(pm);
        pm.setIdentity(id);
        pm.setUri(target);
        if (pack != null && pack.getMappingSpaces().containsKey(target))
          pm.setName(pack.getMappingSpaces().get(target).getTitle());
        else
          pm.setName(definitions.getMapTypes().get(target).getTitle());
      }
      boolean found = false;
      for (ElementDefinitionMappingComponent m : definition.getMapping()) {
        found = found || (m.getIdentity().equals(id) && m.getMap().equals(map)); 
      }
      if (!found) {
        ElementDefinitionMappingComponent m = new ElementDefinitionMappingComponent();
        m.setIdentity(id);
        m.setMap(map);
        definition.getMapping().add(m);
      }
    }
  }


  private boolean mappingExists(StructureDefinition p, String id) {
    for (StructureDefinitionMappingComponent t : p.getMapping()) {
      if (id.equals(t.getIdentity()))
        return true;
    }
    return false;
  }

  private ElementDefinition createBaseDefinition(StructureDefinition p, String path, ElementDefn src) throws Exception {
    ElementDefinition ce = new ElementDefinition(true, ElementDefinition.NOT_MODIFIER, ElementDefinition.NOT_IN_SUMMARY);
    ce.setId(path+"."+src.getName());
    ce.setPath(path+"."+src.getName());
    ce.setShort(src.getShortDefn());
    ce.setDefinition(preProcessMarkdown(src.getDefinition(), "ELement Definition"));
    ce.setComment(preProcessMarkdown(src.getComments(), "Element Comments"));
    ce.setRequirements(preProcessMarkdown(src.getRequirements(), "Element Reqiurements"));
    for (String a : src.getAliases())
      ce.addAlias(a);
    ce.setMin(src.getMinCardinality());
    if (src.getMaxCardinality() != null)
      ce.setMax(src.getMaxCardinality() == Integer.MAX_VALUE ? "*" : src.getMaxCardinality().toString());
    ce.getType(src.typeCode());
    // this one should never be used
    if (!Utilities.noString(src.getTypes().get(0).getProfile())) {
      if (ce.getType().equals("Reference") || ce.getType().equals("canonical") ) throw new Error("Should not happen");
      ce.getType().get(0).addProfile(src.getTypes().get(0).getProfile());
    }
    // todo? conditions, constraints, binding, mapping
    if (src.hasModifier())
      ce.setIsModifier(src.isModifier());
    if (ce.getIsModifier())
      ce.setIsModifierReason(src.getModifierReason());
    if (src.hasSummaryItem())
      ce.setIsSummaryElement(Factory.newBoolean(src.isSummary()));
    for (Invariant id : src.getStatedInvariants()) 
      ce.addCondition(id.getId());
    return ce;
  }

  public ConstraintStructure wrapProfile(StructureDefinition profile) throws Exception {
    return new ConstraintStructure(profile, definitions.getUsageIG((String) profile.getUserData(ToolResourceUtilities.NAME_SPEC_USAGE), "generating profile "+profile.getId()), wg(ToolingExtensions.readStringExtension(profile, ToolingExtensions.EXT_WORKGROUP)), fmm(profile), profile.getExperimental());
  }

  private String fmm(StructureDefinition ed) {
    return ToolingExtensions.readStringExtension(ed, ToolingExtensions.EXT_FMM_LEVEL); // default fmm level
  }

  private WorkGroup wg(String code) {
    return definitions.getWorkgroups().get(code);
  }

  public void convertElements(ElementDefn src, StructureDefinition ed, String path) throws Exception {
    ElementDefinition dst = new ElementDefinition();
    if (!ed.hasDifferential())
      ed.setDifferential(new StructureDefinitionDifferentialComponent());
    ed.getDifferential().getElement().add(dst);
    String thisPath = path == null ? "Extension" : path;
    dst.setId(thisPath);
    dst.setPath(thisPath);
    if (!Utilities.noString(src.getProfileName()))
      dst.setSliceName(src.getProfileName());

    dst.setShort(src.getShortDefn());
    dst.setDefinition(preProcessMarkdown(src.getDefinition(), "Element Definition"));
    dst.setComment(preProcessMarkdown(src.getComments(), "Element Comments"));
    if (src.getMaxCardinality() != null) {
      if (src.getMaxCardinality() == Integer.MAX_VALUE)
        dst.setMax("*");
      else
        dst.setMax(src.getMaxCardinality().toString());
    }
    if (src.getMinCardinality() != null)
      dst.setMin(src.getMinCardinality());
    if (src.getFixed() != null)
      dst.setFixed(src.getFixed());
    if (src.hasMustSupport())
      dst.setMustSupport(src.isMustSupport());
    if (src.hasModifier())
      dst.setIsModifier(src.isModifier());
    if (dst.getIsModifier())
      dst.setIsModifierReason(src.getModifierReason());
    if (src.hasSummaryItem() && dst.getPath().contains("."))
      dst.setIsSummaryElement(Factory.newBoolean(src.isSummary()));
    for (Invariant id : src.getStatedInvariants()) 
      dst.addCondition(id.getId());

    // dDst.
    for (TypeRef t : src.getTypes()) {
      if (t.hasParams()) {
        for (String tp : t.getParams()) {
          ElementDefinition.TypeRefComponent type = dst.getType(t.getName());
          String pr = t.hasProfile() ? t.getProfile() :
             // this should only happen if t.getParams().size() == 1
            "http://hl7.org/fhir/StructureDefinition/"+(tp.equals("Any") ? "Resource" : tp);
          if (type.getCode().equals("Reference") || type.getCode().equals("canonical") )
            type.addTargetProfile(pr); 
          else
            type.addProfile(pr);
        }
      } else if (t.isWildcardType()) {
        for (String n : TypesUtilities.wildcardTypes()) 
          dst.getType(n);
      } else {
        if (definitions != null && definitions.getConstraints().containsKey(t.getName())) {
         ProfiledType ct = definitions.getConstraints().get(t.getName());
         ElementDefinition.TypeRefComponent type = dst.getType(ct.getBaseType());
         type.addProfile("http://hl7.org/fhir/StructureDefinition/"+ct.getName());
        } else if ("Extension.url".equals(path)) {
          // juat don't populate it 
//          ElementDefinition.TypeRefComponent tt = dst.addType();
//          tt.setCodeElement(new UriType());
//          tt.getFormatCommentsPre().add("Note: special primitive values do not have an assigned type. e.g. this is compiler magic. XML, JSON and RDF types provided by extension");
//          ToolingExtensions.addStringExtension(tt.getCodeElement(), ToolingExtensions.EXT_JSON_TYPE, "string");
//          ToolingExtensions.addStringExtension(tt.getCodeElement(), ToolingExtensions.EXT_XML_TYPE, "xs:anyURI");
//          ToolingExtensions.addStringExtension(tt.getCodeElement(), ToolingExtensions.EXT_RDF_TYPE, "xs:anyURI");          
        } else {
          ElementDefinition.TypeRefComponent type = dst.getType(t.getName());
          if (t.hasProfile())
            if (type.getWorkingCode().equals("Reference"))
              type.addTargetProfile(t.getProfile()); 
            else
              type.addProfile(t.getProfile());
        }
      }
    }
    if (definitions != null) { // igtodo - catch this
      for (String mu : definitions.getMapTypes().keySet()) {
        if (src.hasMapping(mu)) {
          addMapping(ed, dst, mu, src.getMapping(mu), null);
        }
      }
    }
    for (String in : src.getInvariants().keySet()) {
      ElementDefinitionConstraintComponent con = new ElementDefinitionConstraintComponent();
      Invariant inv = src.getInvariants().get(in);
      con.setKey(inv.getId());
      if (!con.hasKey()) {
        extensionCounter++;
        con.setKey("exd-"+Integer.toString(extensionCounter));
      }
      con.setRequirements(inv.getRequirements());
      if (Utilities.noString(inv.getSeverity()))
        con.setSeverity(ConstraintSeverity.ERROR);
      else
        con.setSeverity(ConstraintSeverity.fromCode(inv.getSeverity()));
      con.setHuman(inv.getEnglish());
      con.setXpath(inv.getXpath());
      if (!"n/a".equals(inv.getExpression()))
        con.setExpression(inv.getExpression());
      dst.getConstraint().add(con);
    }

    if (src.hasBinding())
      dst.setBinding(generateBinding(src.getBinding()));
    if (src.getElements().isEmpty()) {
      if (path == null)
        throw new Exception("?error parsing extension");
    } else {
      ElementDefn url = src.getElements().get(0);
      if (!url.getName().equals("url"))
        throw new Exception("first child of extension should be 'url', not "+url.getName()+" for structure definition "+ed.getUrl());
      convertElements(url, ed, thisPath+".url");
      // this pair might leave elements out of order, but we're going to sort them later
      if (!hasValue(src)) {
        ElementDefn value = new ElementDefn();
        value.setName("value[x]");
        value.setMinCardinality(0);
        value.setMaxCardinality(0);
        convertElements(value, ed, thisPath+".value[x]");        
      } else {
        ElementDefn ext = new ElementDefn();
        ext.setName("extension"); // can't have an extension if you have a value
        ext.setMaxCardinality(0);
        convertElements(ext, ed, thisPath+".extension");        
      }
      if (src.getElements().size() == 2 && 
          src.getElements().get(0).getName().equals("url") &&
          src.getElements().get(1).getName().equals("value[x]")) {
        ElementDefn value = src.getElements().get(1);
        value.setMinCardinality(1);
        convertElements(value, ed, thisPath+".value[x]");
      } else {
        for (ElementDefn child : src.getElements()) {
          if (child != url) {
            if (child.getName().startsWith("value") && !child.getName().startsWith("valueSet"))
              convertElements(child, ed, thisPath+"."+child.getName());
            else {
              if (child.getElements().size() == 0 || !child.getElements().get(0).getName().equals("url")) {
                ElementDefn childUrl = new ElementDefn();
                childUrl.setName("url");
                childUrl.setXmlAttribute(true);
                childUrl.getTypes().add(new TypeRef("uri"));
                childUrl.setFixed(new UriType(child.getName()));
                child.getElements().add(0, childUrl);
              }
              if (!hasValue(child)) {
                ElementDefn value = new ElementDefn();
                value.setName("value[x]");
                value.setMinCardinality(0);
                value.setMaxCardinality(0);
                child.getElements().add(value);
              }
              convertElements(child, ed, thisPath+".extension");
            }
          }
        }
      }
    }
  }

  private boolean hasValue(ElementDefn child) {
    for (ElementDefn v : child.getElements()) {
      if (v.getName().startsWith("value") && !child.getName().startsWith("valueSet"))
        return true;
    }
    return false;
  }

  public OperationDefinition generate(String name, String id, String resourceName, Operation op, ResourceDefn rd) throws Exception {
    OperationDefinition opd = new OperationDefinition();
    op.setResource(opd);
    if (Utilities.noString(op.getFmm()))
      ToolingExtensions.addIntegerExtension(opd, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(rd.getFmmLevel()));
    else
      ToolingExtensions.addIntegerExtension(opd, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(op.getFmm()));
    ToolingExtensions.setStandardsStatus(opd, op.getStandardsStatus() == null ? rd.getStatus() : op.getStandardsStatus(), op.getNormativeVersion());
    opd.setId(FormatUtilities.makeId(id));
    opd.setUrl("http://hl7.org/fhir/OperationDefinition/"+id);
    opd.setName(op.getTitle());
    opd.setVersion(Constants.VERSION);
    opd.setPublisher("HL7 (FHIR Project)");
    opd.addContact().getTelecom().add(org.hl7.fhir.r4.model.Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    opd.getContact().get(0).getTelecom().add(org.hl7.fhir.r4.model.Factory.newContactPoint(ContactPointSystem.EMAIL, "fhir@lists.hl7.org"));
    opd.setDescription(preProcessMarkdown(op.getDoco(), "Operation Documentation"));
    opd.setStatus(op.getStandardsStatus() == StandardsStatus.NORMATIVE ?  PublicationStatus.ACTIVE : PublicationStatus.DRAFT);
    opd.setDate(genDate.getTime());
    if (op.getKind().toLowerCase().equals("operation"))
      opd.setKind(OperationKind.OPERATION);
    else if (op.getKind().toLowerCase().equals("query"))
      opd.setKind(OperationKind.QUERY);
    else {
      throw new Exception("Unrecognized operation kind: '" + op.getKind() + "' for operation " + name);
    }
    opd.setCode(op.getName());
    opd.setComment(preProcessMarkdown(op.getFooter(), "Operation Comments"));
    opd.setSystem(op.isSystem());
    opd.addResource(resourceName);
    opd.setType(op.isType()); 
    opd.setInstance(op.isInstance());
    if (op.getIdempotent() == null)
      throw new Error("Operation "+opd.getId()+" is not marked as Idempotent or not");
    for (OperationParameter p : op.getParameters()) {
      produceOpParam(op.getName(), opd.getParameter(), p, null);
    }
    NarrativeGenerator gen = new NarrativeGenerator("", "", context);
    gen.generate(opd, null);
    return opd;
  }

  private void produceOpParam(String path, List<OperationDefinitionParameterComponent> opd, OperationParameter p, OperationParameterUse defUse) throws Exception {
    OperationDefinitionParameterComponent pp = new OperationDefinitionParameterComponent();
    pp.setName(p.getName());
    if (p.getUse().equals("in"))
      pp.setUse(OperationParameterUse.IN);
    else if (p.getUse().equals("out"))
      pp.setUse(OperationParameterUse.OUT);
    else if (path.contains("."))
      pp.setUse(defUse);
    else
      throw new Exception("Unable to determine parameter use: "+p.getUse()+" at "+path+"."+p.getName()); // but this is validated elsewhere
    pp.setDocumentation(preProcessMarkdown(p.getDoc(), "Operation Parameter Doco"));
    pp.setMin(p.getMin());
    pp.setMax(p.getMax());
    if (p.getBs() != null) {
      if (p.getBs().hasMax())
        throw new Error("Max binding not handled yet");
      pp.setBinding(new OperationDefinitionParameterBindingComponent().setStrength(p.getBs().getStrength()).setValueSet(buildValueSetReference(p.getBs())));
    }
    if (!Utilities.noString(p.getProfile())) {
      pp.addTargetProfile(p.getProfile());
    }
    opd.add(pp);
    if (p.getFhirType().equals("Tuple")) {
      for (OperationParameter part : p.getParts()) {
        produceOpParam(path+"."+p.getName(), pp.getPart(), part, pp.getUse());
      }
    } else {
      List<TypeRef> trs = new TypeParser().parse(p.getFhirType(), false, null, null, false);
      if (trs.size() > 1) {
        if (p.getSearchType() != null)
          pp.setSearchType(SearchParamType.fromCode(p.getSearchType()));
        pp.setType("Element");
        for (TypeRef tr : trs) {
          pp.addExtension(ToolingExtensions.EXT_ALLOWED_TYPE, new UriType(tr.getName()));
          if (tr.getParams().size() > 0)
            throw new Error("Multiple types for an operation parameter, where one is a reference, is not supported by the build tools");
        }        
      } else {
        TypeRef tr = trs.get(0);
        if (definitions.getConstraints().containsKey(tr.getName())) {
          ProfiledType pt = definitions.getConstraints().get(tr.getName());
          pp.setType(pt.getBaseType().equals("*") ? "Type" : pt.getBaseType());
          pp.addTargetProfile("http://hl7.org/fhir/StructureDefinition/"+pt.getName());
        } else { 
          if (p.getSearchType() != null)
            pp.setSearchType(SearchParamType.fromCode(p.getSearchType()));
          pp.setType(tr.getName().equals("*") ? "Type" : tr.getName());
          if (tr.getParams().size() == 1 && !tr.getParams().get(0).equals("Any"))
            pp.addTargetProfile("http://hl7.org/fhir/StructureDefinition/"+tr.getParams().get(0));
        } 
      }
    }
  }

  private void checkHasTypes(StructureDefinition p) {
    for (ElementDefinition ed : p.getSnapshot().getElement()) {
      if (ed.getPath().contains(".")) {
        if (!ed.hasType() && !ed.hasContentReference() && !(ed.getPath().equals("Resource") || ed.getPath().equals("Element")) && !ed.hasRepresentation())
          throw new Error("No Type on "+ed.getPath());
      } else {
        if (ed.hasType())
          throw new Error("Type on "+ed.getPath());
      }
    }
  }

  public StructureDefinition generateLogicalModel(ImplementationGuideDefn igd, ResourceDefn r) throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setId(r.getRoot().getName());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ r.getRoot().getName());
    p.setKind(StructureDefinitionKind.LOGICAL);
    p.setAbstract(false);
    p.setUserData("filename", r.getName().toLowerCase());
    p.setUserData("path", igd.getPrefix()+ r.getName().toLowerCase()+".html");
    p.setTitle(r.getName());
    p.setFhirVersion(version);
    p.setVersion(version.toCode());
    p.setType(r.getRoot().getName());
    ToolingExtensions.setStandardsStatus(p, r.getStatus(), null);

    ToolResourceUtilities.updateUsage(p, igd.getCode());
    p.setName(r.getRoot().getName());
    p.setPublisher("Health Level Seven International"+(r.getWg() == null ? " "+igd.getCommittee() : " ("+r.getWg().getName()+")"));
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    if (r.getWg() != null) {
      p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, r.getWg().getUrl()));
      ToolingExtensions.setCodeExtension(p, ToolingExtensions.EXT_WORKGROUP, r.getWg().getCode());
    }
    p.setDescription("Logical Model: "+r.getDefinition());
    p.setPurpose(r.getRoot().getRequirements());
    if (!p.hasPurpose())
      p.setPurpose(r.getRoot().getRequirements());
    p.setDate(genDate.getTime());
    p.setStatus(PublicationStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    defineElement(null, p, p.getSnapshot().getElement(), r.getRoot(), r.getRoot().getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true, "Element", "Element", true);
    addElementConstraintToSnapshot(p);

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    return p;
  }


}
