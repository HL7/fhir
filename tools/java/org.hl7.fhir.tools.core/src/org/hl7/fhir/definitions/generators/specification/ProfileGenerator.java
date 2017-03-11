package org.hl7.fhir.definitions.generators.specification;
import java.io.File;
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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.validation.FHIRPathUsage;
import org.hl7.fhir.dstu3.conformance.ProfileUtilities;
import org.hl7.fhir.dstu3.conformance.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.model.BooleanType;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.CodeType;
import org.hl7.fhir.dstu3.model.ContactDetail;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.dstu3.model.DataElement;
import org.hl7.fhir.dstu3.model.DataElement.DataElementStringency;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.ElementDefinition.AggregationMode;
import org.hl7.fhir.dstu3.model.ElementDefinition.ConstraintSeverity;
import org.hl7.fhir.dstu3.model.ElementDefinition.DiscriminatorType;
import org.hl7.fhir.dstu3.model.ElementDefinition.ElementDefinitionBaseComponent;
import org.hl7.fhir.dstu3.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.dstu3.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.dstu3.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.dstu3.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.dstu3.model.ElementDefinition.PropertyRepresentation;
import org.hl7.fhir.dstu3.model.ElementDefinition.SlicingRules;
import org.hl7.fhir.dstu3.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu3.model.Enumerations.BindingStrength;
import org.hl7.fhir.dstu3.model.Enumerations.PublicationStatus;
import org.hl7.fhir.dstu3.model.Enumerations.SearchParamType;
import org.hl7.fhir.dstu3.model.Extension;
import org.hl7.fhir.dstu3.model.Factory;
import org.hl7.fhir.dstu3.model.InstantType;
import org.hl7.fhir.dstu3.model.IntegerType;
import org.hl7.fhir.dstu3.model.Meta;
import org.hl7.fhir.dstu3.model.Narrative;
import org.hl7.fhir.dstu3.model.Narrative.NarrativeStatus;
import org.hl7.fhir.dstu3.model.OperationDefinition;
import org.hl7.fhir.dstu3.model.OperationDefinition.OperationDefinitionParameterBindingComponent;
import org.hl7.fhir.dstu3.model.OperationDefinition.OperationDefinitionParameterComponent;
import org.hl7.fhir.dstu3.model.OperationDefinition.OperationKind;
import org.hl7.fhir.dstu3.model.OperationDefinition.OperationParameterUse;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.SearchParameter;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionDifferentialComponent;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionMappingComponent;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionSnapshotComponent;
import org.hl7.fhir.dstu3.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.spreadsheets.TypeParser;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.tools.converters.MarkDownPreProcessor;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
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
  private String version;
  private Bundle dataElements;

  private static class SliceHandle {
    private String name;
    private Map<String, ElementDefinition> paths = new HashMap<String, ElementDefinition>();
  }

  public ProfileGenerator(Definitions definitions, BuildWorkerContext context, ProfileKnowledgeProvider pkp, Calendar genDate, String version, Bundle dataElements, List<FHIRPathUsage> fpUsages) {
    super();
    this.definitions = definitions;
    this.context = context;
    this.pkp = pkp;
    this.genDate = genDate;
    this.version = version;
    this.dataElements = dataElements;
    this.fpUsages = fpUsages;
    if (dataElements != null) {
      for (BundleEntryComponent be : dataElements.getEntry()) {
        if (be.getResource() instanceof DataElement)
          des.put(be.getResource().getId(), (DataElement) be.getResource());
      }
    }
  }

  private Map<String, DataElement> des = new HashMap<String, DataElement>();
  private static int extensionCounter;
  private static int profileCounter = 0;
  
  private void generateElementDefinition(ElementDefinition ed, ElementDefinition parent) throws Exception {
    String id = ed.getPath().replace("[x]", "X");
    if (id.length() > 64)
      id = id.substring(0, 64);
    
    DataElement de;
    if (des.containsKey(id)) {
      de = des.get(id);
      // do it again because we now have more information to generate with
      de.getElement().clear();
      de.getExtension().clear();
    } else {
      de = new DataElement();
      de.setId(id);
      des.put(id, de);
      de.setUrl("http://hl7.org/fhir/DataElement/"+de.getId());
      if (de.getId().contains("."))
        definitions.addNs(de.getUrl(), "Data Element "+ed.getPath(), definitions.getSrcFile(de.getId().substring(0, de.getId().indexOf(".")))+"-definitions.html#"+de.getId());
      if (dataElements != null)
        dataElements.addEntry().setResource(de).setFullUrl(de.getUrl());
    }
    
    if (ed.hasBase())
      throw new Exception("attempt to add derived element to data elements");
    
    if (!de.hasMeta())
      de.setMeta(new Meta());
    de.getMeta().setLastUpdatedElement(new InstantType(genDate));
    de.setName(ed.getSliceName());
    de.setStatus(PublicationStatus.DRAFT);
    de.setExperimental(true);
    de.setStringency(DataElementStringency.FULLYSPECIFIED);
    // re-enable this when the extension is defined post DSTU-2
//    if (parent != null) {
//      Extension ext = de.addExtension();
//      ext.setUrl("http://hl7.org/fhir/StructureDefinition/dataelement-relationship");
//      Extension ext2 = ext.addExtension();
//      ext2.setUrl("type");
//      ext2.setValue(new CodeType("composed"));
//      ext2 = ext.addExtension();
//      ext2.setUrl("cardinality");
//      ext2.setValue(new StringType("1"));
//      ext2 = ext.addExtension();
//      ext2.setUrl("target");
//      ext2.setValue(new UriType("http://hl7.org/fhir/DataElement/"+parent.getPath()));
//    }
    de.addElement(ed);
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

    
    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(type.getCode());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+type.getCode()+" Type: "+type.getDefinition());
    p.setDate(genDate.getTime());
    p.setStatus(PublicationStatus.fromCode("draft")); // DSTU

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
    t.getFormatCommentsPre().add("Note: primitive values do not have an assigned type. e.g. this is compiler magic. XML, JSON and RDF types provided by extension");
    t.setCodeElement(new UriType());
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_JSON_TYPE, type.getJsonType());
    String xst = type.getSchemaType().replace(", ", " OR ");
    if (xst.contains("xs:"))
      xst = xst.replace("xs:", "xsd:");
    if (!xst.startsWith("xsd:"))
      xst = "xsd:" + xst;
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_XML_TYPE, xst);
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_RDF_TYPE, xst.replace("anyURI", "string"));
    if (!Utilities.noString(type.getRegex())) {
      ToolingExtensions.addStringExtension(t, ToolingExtensions.EXT_REGEX, type.getRegex());
    }
    addSpecificDetails(type, ec);

    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    ElementDefinition ec1 = new ElementDefinition();
    p.getSnapshot().getElement().add(ec1);
    ec1.setId(type.getCode());
    ec1.setPath(type.getCode());
    ec1.setShort("Primitive Type " +type.getCode());
    ec1.setDefinition(type.getDefinition());
    ec1.setComment(type.getComment());
    ec1.setMin(0);
    ec1.setMax("*");
    addElementConstraints("Element", ec1);
    generateElementDefinition(ec1, null);

    ElementDefinition ec2 = new ElementDefinition();
    p.getSnapshot().getElement().add(ec2);
    ec2.setId(type.getCode()+".id");
    ec2.setPath(type.getCode()+".id");
    ec2.addRepresentation(PropertyRepresentation.XMLATTR);
    ec2.setDefinition("unique id for the element within a resource (for internal references)");
    ec2.setMin(0);
    ec2.setMax("1");
    ec2.setShort("xml:id (or equivalent in JSON)");
    ec2.getType().add(new TypeRefComponent().setCode("string"));
    generateElementDefinition(ec2, ec1);
    ec2.makeBase("Element.id", 0, "1");

    makeExtensionSlice("extension", p, p.getSnapshot(), null, type.getCode());

    ElementDefinition ec3 = new ElementDefinition();
    p.getSnapshot().getElement().add(ec3);
    ec3.setId(type.getCode()+".value");
    ec3.setPath(type.getCode()+".value");
    ec3.addRepresentation(PropertyRepresentation.XMLATTR);
    ec3.setDefinition("The actual value");
    ec3.setMin(0);
    ec3.setMax("1");
    ec3.setShort("Primitive value for " +type.getCode());
    t = ec3.addType();
    t.setCodeElement(new UriType());
    t.getFormatCommentsPre().add("Note: primitive values do not have an assigned type. e.g. this is compiler magic. XML, JSON and RDF types provided by extension");
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_JSON_TYPE, type.getJsonType());
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_XML_TYPE, xst);
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_RDF_TYPE, xst.replace("anyURI", "string"));
    if (!Utilities.noString(type.getRegex()))
      ToolingExtensions.addStringExtension(t, ToolingExtensions.EXT_REGEX, type.getRegex());
    addSpecificDetails(type, ec3);
    generateElementDefinition(ec3, ec);

    containedSlices.clear();

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }

  private void addElementConstraints(String name, ElementDefinition ed) throws Exception {
    if (definitions.hasPrimitiveType(name) || name.equals("Type") || name.equals("Structure") || name.equals("Logical"))
      addElementConstraints("Element", ed);
    else {
      ElementDefn element = definitions.getElementDefn(name);
      if (!Utilities.noString(element.typeCode()))
        addElementConstraints(element.typeCode(), ed);
      convertConstraints(element, ed, name);
    }
  }
  
  private void addSpecificDetails(PrimitiveType type, ElementDefinition ed) {
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

    
    ToolResourceUtilities.updateUsage(p, "core");
    p.setName("xhtml");
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for xhtml Type");
    p.setDate(genDate.getTime());
    p.setStatus(PublicationStatus.fromCode("draft")); // DSTU

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
    t.getFormatCommentsPre().add("Note: primitive values do not have an assigned type. e.g. this is compiler magic. XML, JSON and RDF types provided by extension");
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_JSON_TYPE, "string");
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_XML_TYPE, "xhtml:div");
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_RDF_TYPE, "string");

    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    ElementDefinition ec1 = new ElementDefinition();
    p.getSnapshot().getElement().add(ec1);
    ec1.setId("xhtml");
    ec1.setPath("xhtml");
    ec1.setShort("Primitive Type " +"xhtml");
    ec1.setDefinition("XHTML");
    ec1.setMin(0);
    ec1.setMin(0);
    ec1.setMax("*");
    generateElementDefinition(ec1, null);

    ElementDefinition ec2 = new ElementDefinition();
    p.getSnapshot().getElement().add(ec2);
    ec2.setId("xhtml.id");
    ec2.setPath("xhtml.id");
    ec2.addRepresentation(PropertyRepresentation.XMLATTR);
    ec2.setDefinition("unique id for the element within a resource (for internal references)");
    ec2.setMin(0);
    ec2.setMax("1");
    ec2.setShort("xml:id (or equivalent in JSON)");
    ec2.getType().add(new TypeRefComponent().setCode("string"));
    generateElementDefinition(ec2, ec1);
    ec2.makeBase("Element.id", 0, "1");

    ElementDefinition ex = makeExtensionSlice("extension", p, p.getSnapshot(), null, "xhtml");
    ex.setMax("0");
    
    ElementDefinition ec3 = new ElementDefinition();
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
    t.getFormatCommentsPre().add("Note: primitive values do not have an assigned type. e.g. this is compiler magic. XML, JSON and RDF types provided by extension");
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_JSON_TYPE, "string");
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_XML_TYPE, "xhtml:div");
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_RDF_TYPE, "string");
    generateElementDefinition(ec3, ec);

    containedSlices.clear();

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

    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(type.getCode());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+type.getCode()+" type: "+type.getDefinition());
    p.setDate(genDate.getTime());
    p.setStatus(PublicationStatus.fromCode("draft")); // DSTU

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
    t.getFormatCommentsPre().add("Note: primitive values do not have an assigned type. e.g. this is compiler magic. XML, JSON and RDF types provided by extension");
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_JSON_TYPE, type.getJsonType());
    String xst = type.getSchema().replace("xs:", "xsd:").replace("+", "");
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_XML_TYPE, xst);
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_RDF_TYPE, xst.replace("anyURI", "string"));
    if (!Utilities.noString(type.getRegex())) {
      ToolingExtensions.addStringExtension(t, ToolingExtensions.EXT_REGEX, type.getRegex());
    }
    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    ElementDefinition ecA = new ElementDefinition();
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

    ElementDefinition ecid = new ElementDefinition();
    p.getSnapshot().getElement().add(ecid);
    ecid.setId(type.getCode()+".id");
    ecid.setPath(type.getCode()+".id");
    ecid.addRepresentation(PropertyRepresentation.XMLATTR);
    ecid.setDefinition("unique id for the element within a resource (for internal references)");
    ecid.setMin(0);
    ecid.setMax("1");
    ecid.setShort("xml:id (or equivalent in JSON)");
    ecid.getType().add(new TypeRefComponent().setCode("string"));
    ecid.makeBase("Element.id", 0, "1");

    makeExtensionSlice("extension", p, p.getSnapshot(), null, type.getCode());

    ElementDefinition ecB = new ElementDefinition();
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
    t.setCodeElement(new UriType());
    t.getFormatCommentsPre().add("Note: primitive values do not have an assigned type. e.g. this is compiler magic. XML, JSON and RDF types provided by extension");
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_JSON_TYPE, type.getJsonType());
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_XML_TYPE, xst);
    ToolingExtensions.addStringExtension(t.getCodeElement(), ToolingExtensions.EXT_RDF_TYPE, xst.replace("anyURI", "string"));
    if (!Utilities.noString(type.getRegex()))
      ToolingExtensions.addStringExtension(t, ToolingExtensions.EXT_REGEX, type.getRegex());
//    generateElementDefinition(ecB, ecA);

    containedSlices.clear();

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
    p.setUserData("path", "datatypes.html#"+t.getName());
    assert !Utilities.noString(t.typeCode());
    String b = (t.typeCode().equals("Type") || t.typeCode().equals("Structure")) ? "Element" : t.typeCode();
    if (!Utilities.noString(b)) {
      p.setBaseDefinition("http://hl7.org/fhir/StructureDefinition/"+b);
      p.setDerivation(TypeDerivationRule.SPECIALIZATION);
    }
    p.setType(t.getName());
    p.setFhirVersion(version);

    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(t.getName());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+t.getName()+" Type");
    p.setPurpose(t.getRequirements());
    p.setDate(genDate.getTime());
    p.setStatus(PublicationStatus.fromCode("draft")); // DSTU


    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    defineElement(null, p, p.getDifferential().getElement(), t, t.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true, "Element", b);
    p.getDifferential().getElement().get(0).setIsSummaryElement(null);
    
    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    defineElement(null, p, p.getSnapshot().getElement(), t, t.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.DataType, true, "Element", b);
    for (ElementDefinition ed : p.getSnapshot().getElement())
      if (!ed.hasBase())
        generateElementDefinition(ed, getParent(ed, p.getSnapshot().getElement()));

    containedSlices.clear();

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

    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(pt.getName());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+pt.getName()+" Resource");
    p.setDescription(pt.getDefinition());
    p.setDate(genDate.getTime());
    p.setStatus(PublicationStatus.fromCode("draft")); // DSTU

    // first, the differential
    p.setName(pt.getName());
    ElementDefinition e = new ElementDefinition();
    String idroot = e.getId();
    e.setPath(pt.getBaseType());
    e.setSliceName(pt.getName());
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
    new ProfileUtilities(context, issues, pkp).generateSnapshot(base, p, "http://hl7.org/fhir/StructureDefinition/"+pt.getBaseType(), p.getName());
//    for (ElementDefinition ed : p.getSnapshot().getElement())
//      generateElementDefinition(ed, getParent(ed, p.getSnapshot().getElement()));

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

    if (r.getFmmLevel() != null)
      ToolingExtensions.addIntegerExtension(p, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(r.getFmmLevel()));
    ToolResourceUtilities.updateUsage(p, usage);
    p.setName(r.getRoot().getName());
    p.setPublisher("Health Level Seven International"+(r.getWg() == null ? "" : " ("+r.getWg().getName()+")"));
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    if (r.getWg() != null)
      p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, r.getWg().getUrl()));
    ToolingExtensions.setCodeExtension(p, ToolingExtensions.EXT_WORKGROUP, r.getWg().getCode());
    p.setDescription("Base StructureDefinition for "+r.getRoot().getName()+" Resource");
    p.setPurpose(r.getRoot().getRequirements());
    if (!p.hasPurpose())
      p.setPurpose(r.getRoot().getRequirements());
    p.setDate(genDate.getTime());
    p.setStatus(PublicationStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    defineElement(null, p, p.getDifferential().getElement(), r.getRoot(), r.getRoot().getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true, "BackboneElement", r.getRoot().typeCode());

    reset();
    // now. the snapshot'
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    defineElement(null, p, p.getSnapshot().getElement(), r.getRoot(), r.getRoot().getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.Resource, true, "BackboneElement", r.getRoot().typeCode());
    for (ElementDefinition ed : p.getSnapshot().getElement())
      if (!ed.hasBase() && !logical)
        generateElementDefinition(ed, getParent(ed, p.getSnapshot().getElement()));

    if (!logical) {
      List<String> names = new ArrayList<String>();
      names.addAll(r.getSearchParams().keySet());
      Collections.sort(names);
      for (String pn : names) {
        pack.getSearchParameters().add(makeSearchParam(p, r.getName()+"-"+pn.replace("_", ""), r.getName(), r.getSearchParams().get(pn)));
      }
    }
    containedSlices.clear();

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

  public StructureDefinition generate(Profile pack, ConstraintStructure profile, ResourceDefn resource, String id, ImplementationGuideDefn usage, List<ValidationMessage> issues) throws Exception {

    try {
      return generate(pack, profile, resource, id, null, usage, issues);
    } catch (Exception e) {
      throw new Exception("Error processing profile '"+id+"': "+e.getMessage(), e);
    }
  }

  public StructureDefinition generate(Profile pack, ConstraintStructure profile, ResourceDefn resource, String id, String html, ImplementationGuideDefn usage, List<ValidationMessage> issues) throws Exception {
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

    if (pack.hasMetadata("date"))
      p.setDateElement(Factory.newDateTime(pack.metadata("date").substring(0, 10)));
    else
      p.setDate(genDate.getTime());

    if (pack.hasMetadata("fmm-level"))
      ToolingExtensions.addIntegerExtension(p, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(pack.getFmmLevel()));
    if (pack.hasMetadata("workgroup"))
      ToolingExtensions.setCodeExtension(p, ToolingExtensions.EXT_WORKGROUP, pack.getWg());
    
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
    defineElement(pack, p, p.getDifferential().getElement(), resource.getRoot(), resource.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true, null, null);
    List<String> names = new ArrayList<String>();
    names.addAll(resource.getSearchParams().keySet());
    Collections.sort(names);
    for (String pn : names) {
      pack.getSearchParameters().add(makeSearchParam(p, pack.getId()+"-"+resource.getName()+"-"+pn, resource.getName(), resource.getSearchParams().get(pn)));
    }
    StructureDefinition base = definitions.getSnapShotForBase(p.getBaseDefinition());

    List<String> errors = new ArrayList<String>();
    new ProfileUtilities(context, null, pkp).sortDifferential(base, p, p.getName(), errors);
    for (String s : errors)
      issues.add(new ValidationMessage(Source.ProfileValidator, IssueType.STRUCTURE, -1, -1, p.getUrl(), s, IssueSeverity.WARNING));
    reset();
    // ok, c is the differential. now we make the snapshot
    new ProfileUtilities(context, issues, pkp).generateSnapshot(base, p, "http://hl7.org/fhir/StructureDefinition/"+p.getType(), p.getName());
    reset();

    p.getDifferential().getElement().get(0).getType().clear();
    p.getSnapshot().getElement().get(0).getType().clear();

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
    }
    return null;
  }

  public SearchParameter makeSearchParam(StructureDefinition p, String id, String rn, SearchParameterDefn spd) throws Exception  {
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
      if (context.getSearchParameters().containsKey(sp.getUrl()))
        throw new Exception("Duplicated Search Parameter "+sp.getUrl());
      context.getSearchParameters().put(sp.getUrl(), sp);
      definitions.addNs(sp.getUrl(), "Search Parameter: "+sp.getName(), rn.toLowerCase()+".html#search");
      sp.setStatus(p.getStatus());
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
      if (shared)
        sp.setDescription("Multiple Resources: \r\n\r\n* ["+rn+"]("+rn.toLowerCase()+".html): " + spd.getDescription()+"\r\n");
      else
        sp.setDescription(preProcessMarkdown(spd.getDescription(), "Search Description"));
      if (!Utilities.noString(spd.getExpression())) 
        sp.setExpression(spd.getExpression());
      String xpath = Utilities.noString(spd.getXPath()) ? new XPathQueryGenerator(this.definitions, null, null).generateXpath(spd.getPaths()) : spd.getXPath();
      if (xpath != null) {
        if (xpath.contains("[x]"))
          xpath = convertToXpath(xpath);
        sp.setXpath(xpath);
        sp.setXpathUsage(spd.getxPathUsage());
      }
      if (sp.getType() == SearchParamType.COMPOSITE) {
        for (CompositeDefinition cs : spd.getComposites()) {
          sp.addComponent().setExpression(cs.getExpression()).setDefinition(new Reference("http://hl7.org/fhir/SearchParameter/"+rn+"-"+cs.getDefinition()));
        }
      } 
      sp.addBase(p.getType());
    } else {
      if (sp.getType() != getSearchParamType(spd.getType()))
        throw new FHIRException("Type mismatch on common parameter: expected "+sp.getType().toCode()+" but found "+getSearchParamType(spd.getType()).toCode());
      sp.setDescription(sp.getDescription()+"* ["+rn+"]("+rn.toLowerCase()+".html): " + spd.getDescription()+"\r\n");
      if (!Utilities.noString(spd.getExpression())) 
        sp.setExpression(sp.getExpression()+" | "+spd.getExpression());
      String xpath = new XPathQueryGenerator(this.definitions, null, null).generateXpath(spd.getPaths());
      if (xpath != null) {
        if (xpath.contains("[x]"))
          xpath = convertToXpath(xpath);
        sp.setXpath(sp.getXpath()+" | " +xpath);
        if (sp.getXpathUsage() != spd.getxPathUsage()) 
          throw new FHIRException("Usage mismatch on common parameter: expected "+sp.getXpathUsage().toCode()+" but found "+spd.getxPathUsage().toCode());
      }
      boolean found = false;
      for (CodeType ct : sp.getBase())
        found = found || ct.asStringValue().equals(p.getType());
      if (!found)
        sp.addBase(p.getType());
    }
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
      dst.setValueSet(buildReference(src));
      if (src.hasMax()) {
        dst.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-maxValueSet").setValue(new Reference(src.getMaxReference() != null ? src.getMaxReference() : src.getMaxValueSet().getUrl()));
      }
    } else {
      dst.setStrength(BindingStrength.EXAMPLE);    
    }
    dst.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-bindingName").setValue(new StringType(src.getName()));
    if (src.isShared())
      dst.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-isCommonBinding").setValue(new BooleanType(true));
    return dst;
  }

  private Type buildReference(BindingSpecification src) throws Exception {
    switch (src.getBinding()) {
    case Unbound: return null;
    case CodeList:
      if (src.getValueSet()!= null)
        return Factory.makeReference(src.getValueSet().getUrl());
      else if (src.getReference().startsWith("#"))
        return Factory.makeReference("http://hl7.org/fhir/ValueSet/"+src.getReference().substring(1));
      else
        throw new Exception("not done yet");
    case ValueSet: 
      if (!Utilities.noString(src.getReference()))
        if (src.getReference().startsWith("http"))
          return Factory.makeReference(src.getReference());
        else if (src.getValueSet()!= null)
          return Factory.makeReference(src.getValueSet().getUrl());
        else if (src.getReference().startsWith("valueset-"))
          return Factory.makeReference("http://hl7.org/fhir/ValueSet/"+src.getReference().substring(9));
        else
          return Factory.makeReference("http://hl7.org/fhir/ValueSet/"+src.getReference());
      else
        return null; // throw new Exception("not done yet");
    case Reference: return Factory.newUri(src.getReference());
    case Special: 
      return Factory.makeReference("http://hl7.org/fhir/ValueSet/"+src.getReference().substring(1));
    default: 
      throw new Exception("not done yet");
    }
  }

  /**
   * note: snapshot implies that we are generating a resource or a data type; for other profiles, the snapshot is generated elsewhere
   */
  private ElementDefinition defineElement(Profile ap, StructureDefinition p, List<ElementDefinition> elements, ElementDefn e, String path, Set<String> slices, List<SliceHandle> parentSlices, SnapShotMode snapshot, boolean root, String defType, String inheritedType) throws Exception 
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

    ElementDefinition ce = new ElementDefinition();
    elements.add(ce);
//    todo ce.setId(path.substring(path.indexOf(".")+1));

    ce.setId(path);
    ce.setPath(path);

    if (e.isXmlAttribute())
      ce.addRepresentation(PropertyRepresentation.XMLATTR);
    List<SliceHandle> myParents = new ArrayList<ProfileGenerator.SliceHandle>();
    myParents.addAll(parentSlices);

    // If this element has a profile name, and this is the first of the
    // slicing group, add a slicing group "entry" (= first slice member,
    // which holds Slicing information)
    if (!Utilities.noString(e.getProfileName())) {
      if (e.getDiscriminator().size() > 0 && !slices.contains(path) && handleDiscriminator) {
        processDiscriminator(e, path, ce);
        slices.add(path);
      }
      SliceHandle hnd = new SliceHandle();
      hnd.name = path; // though this it not used?
      myParents.add(hnd);
      if (path.contains(".")) { // We don't want a slice name on the root
        ce.setSliceName(e.getProfileName());
        ce.setId(ce.getId()+":"+e.getProfileName());
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
    if (!Utilities.noString(e.getStatedType()))
      ToolingExtensions.addStringExtension(ce, "http://hl7.org/fhir/StructureDefinition/structuredefinition-explicit-type-name", e.getStatedType());


    if (!root) {
      if (e.typeCode().startsWith("@"))  {
        ce.setContentReference("#"+getIdForPath(elements, e.typeCode().substring(1)));
      } else {
        List<TypeRef> expandedTypes = new ArrayList<TypeRef>();
        for (TypeRef t : e.getTypes()) {
          // Expand any Resource(A|B|C) references
          if(t.hasParams() && !"Reference".equals(t.getName())) {
            throw new Exception("Only resource types can specify parameters.  Path " + path + " in profile " + p.getName());
          }
          if(t.getParams().size() > 1)
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
            for (String n : TypeParser.wildcardTypes()) 
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
          TypeRefComponent type = new TypeRefComponent();
          String profile = null;
          if (definitions.getConstraints().containsKey(t.getName())) {
            ProfiledType pt = definitions.getConstraints().get(t.getName());
            type.setCode(pt.getBaseType());
            profile = "http://hl7.org/fhir/StructureDefinition/"+pt.getName();
          } else {
            type.setCode(t.getName());
            profile = t.getProfile();
          }
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
            if (type.getCode().equals("Reference"))
              type.setTargetProfile(pr);
            else
              type.setProfile(pr);
          }

          for (String aggregation : t.getAggregations()) {
            type.addAggregation(AggregationMode.fromCode(aggregation));
          }	      	

          ce.getType().add(type);
        }
      }
    }
    if (e.getW5() != null)
      addMapping(p, ce, "http://hl7.org/fhir/w5", e.getW5(), ap);
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
    Set<String> containedSlices = new HashSet<String>();
    if (snapshot != SnapShotMode.None) {
      if (!root && Utilities.noString(e.typeCode())) {
        if (snapshot == SnapShotMode.Resource)
          defineAncestorElements("BackboneElement", path, snapshot, containedSlices, p, elements, defType);
        else
          defineAncestorElements("Element", path, snapshot, containedSlices, p, elements, defType);
      } else if (root && !Utilities.noString(e.typeCode())) 
        defineAncestorElements(e.typeCode(), path, snapshot, containedSlices, p, elements, defType);
    }
    for (ElementDefn child : e.getElements()) 
      defineElement(ap, p, elements, child, path+"."+child.getName(), containedSlices, myParents, snapshot, false, defType, null);

    return ce;
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
    
    // ce.setConformance(getType(e.getConformance()));
    for (Invariant id : e.getStatedInvariants()) 
      ce.addCondition(id.getId());

    ce.setFixed(e.getFixed());
    ce.setPattern(e.getPattern());
    ce.setDefaultValue(e.getDefaultValue());
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
      else
        con.setSeverity(ConstraintSeverity.fromCode(inv.getSeverity()));
      con.setHuman(inv.getEnglish());
      con.setXpath(inv.getXpath());
      con.setSource(source);
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
      ce.getSlicing().addDiscriminator(ProfileUtilities.interpretR2Discriminator(d[0].trim()));
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
      ce.getSlicing().addDiscriminator(ProfileUtilities.interpretR2Discriminator(s));
    }
  }

  private String actualTypeName(String type) {
    if (type.equals("Type"))
      return "Element";
    if (type.equals("Structure"))
      return "Element";
    return type;
  }
  private void defineAncestorElements(String type, String path, SnapShotMode snapshot, Set<String> containedSlices, StructureDefinition p, List<ElementDefinition> elements, String dt) throws Exception {
    ElementDefn e = definitions.getElementDefn(actualTypeName(type));
    if (!Utilities.noString(e.typeCode()))
      defineAncestorElements(e.typeCode(), path, snapshot, containedSlices, p, elements, dt);

    for (ElementDefn child : e.getElements()) {
      ElementDefinition ed = defineElement(null, p, elements, child, path+"."+child.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), snapshot, false, dt, null);
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


  private ElementDefinition makeExtensionSlice(String extensionName, StructureDefinition p, StructureDefinitionSnapshotComponent c, ElementDefn e, String path) throws URISyntaxException, Exception {
    ElementDefinition ex = createBaseDefinition(p, path, definitions.getBaseResources().get("DomainResource").getRoot().getElementByName(definitions, extensionName, false, false));
    c.getElement().add(ex);
    if (!ex.hasBase())
      ex.setBase(new ElementDefinitionBaseComponent());
    ex.getBase().setPath("Element.extension");
    ex.getBase().setMin(0);
    ex.getBase().setMax("*");
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
    ElementDefinition ce = new ElementDefinition();
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
    ce.getType().add(new TypeRefComponent());
    ce.getType().get(0).setCode(src.typeCode());
    // this one should never be used
    if (!Utilities.noString(src.getTypes().get(0).getProfile())) {
      if (ce.getType().equals("Reference")) throw new Error("Should not happen");
      ce.getType().get(0).setProfile(src.getTypes().get(0).getProfile());
    }
    // todo? conditions, constraints, binding, mapping
    if (src.hasModifier())
      ce.setIsModifier(src.isModifier());
    if (src.hasSummaryItem())
      ce.setIsSummaryElement(Factory.newBoolean(src.isSummary()));
    for (Invariant id : src.getStatedInvariants()) 
      ce.addCondition(id.getId());
    return ce;
  }

  public ConstraintStructure wrapProfile(StructureDefinition profile) throws Exception {
    return new ConstraintStructure(profile, definitions.getUsageIG((String) profile.getUserData(ToolResourceUtilities.NAME_SPEC_USAGE), "generating profile "+profile.getId()));
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
    if (src.hasSummaryItem() && dst.getPath().contains("."))
      dst.setIsSummaryElement(Factory.newBoolean(src.isSummary()));
    for (Invariant id : src.getStatedInvariants()) 
      dst.addCondition(id.getId());

    // dDst.
    for (TypeRef t : src.getTypes()) {
      if (t.hasParams()) {
        for (String tp : t.getParams()) {
          ElementDefinition.TypeRefComponent type = new ElementDefinition.TypeRefComponent();
          type.setCode(t.getName());
          String pr = t.hasProfile() ? t.getProfile() :
             // this should only happen if t.getParams().size() == 1
            "http://hl7.org/fhir/StructureDefinition/"+(tp.equals("Any") ? "Resource" : tp);
          if (type.getCode().equals("Reference"))
            type.setTargetProfile(pr); 
          else
            type.setProfile(pr);
          dst.getType().add(type);
        }
      } else if (t.isWildcardType()) {
        for (String n : TypeParser.wildcardTypes()) 
          dst.addType().setCode(n);
      } else {
        ElementDefinition.TypeRefComponent type = new ElementDefinition.TypeRefComponent();
        if (definitions != null && definitions.getConstraints().containsKey(t.getName())) {
         ProfiledType ct = definitions.getConstraints().get(t.getName());
         type.setCode(ct.getBaseType());
         type.setProfile("http://hl7.org/fhir/StructureDefinition/"+ct.getName());
        } else {
          type.setCode(t.getName());
          if (t.hasProfile())
            if (type.getCode().equals("Reference"))
              type.setTargetProfile(t.getProfile()); 
            else
              type.setProfile(t.getProfile());
        }
        dst.getType().add(type);
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

  public OperationDefinition generate(String name, String id, String resourceName, Operation op) throws Exception {
    OperationDefinition opd = new OperationDefinition();
    opd.setId(FormatUtilities.makeId(id));
    opd.setUrl("http://hl7.org/fhir/OperationDefinition/"+id);
    opd.setName(op.getTitle());
    opd.setPublisher("HL7 (FHIR Project)");
    opd.addContact().getTelecom().add(org.hl7.fhir.dstu3.model.Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    opd.getContact().get(0).getTelecom().add(org.hl7.fhir.dstu3.model.Factory.newContactPoint(ContactPointSystem.EMAIL, "fhir@lists.hl7.org"));
    opd.setDescription(preProcessMarkdown(op.getDoco(), "Operation Documentation"));
    opd.setStatus(PublicationStatus.DRAFT);
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
    for (OperationParameter p : op.getParameters()) {
      produceOpParam(op.getName(), opd.getParameter(), p, null);
    }
    NarrativeGenerator gen = new NarrativeGenerator("", "", context);
    gen.generate(opd);
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
      pp.setBinding(new OperationDefinitionParameterBindingComponent().setStrength(p.getBs().getStrength()).setValueSet(buildReference(p.getBs())));
    }
    Reference ref = new Reference();
    if (!Utilities.noString(p.getProfile())) {
      ref.setReference(p.getProfile());
      pp.setProfile(ref);
    }
    opd.add(pp);
    if (p.getFhirType().equals("Tuple")) {
      for (OperationParameter part : p.getParts()) {
        produceOpParam(path+"."+p.getName(), pp.getPart(), part, pp.getUse());
      }
    } else {
      TypeRef tr = new TypeParser().parse(p.getFhirType(), false, null, null, false).get(0);
      if (definitions.getConstraints().containsKey(tr.getName())) {
        ProfiledType pt = definitions.getConstraints().get(tr.getName());
        pp.setType(pt.getBaseType().equals("*") ? "Type" : pt.getBaseType());
        pp.setProfile(new Reference().setReference("http://hl7.org/fhir/StructureDefinition/"+pt.getName()));
      } else { 
        if (p.getSearchType() != null)
          pp.setSearchType(SearchParamType.fromCode(p.getSearchType()));
        pp.setType(tr.getName().equals("*") ? "Type" : tr.getName());
        if (tr.getParams().size() == 1 && !tr.getParams().get(0).equals("Any"))
          pp.setProfile(new Reference().setReference("http://hl7.org/fhir/StructureDefinition/"+tr.getParams().get(0)));
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
    defineElement(null, p, p.getSnapshot().getElement(), r.getRoot(), r.getRoot().getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true, "Element", "Element");

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    return p;
  }


}
