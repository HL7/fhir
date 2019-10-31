package org.hl7.fhir.tools.publisher;

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.NotImplementedException;
import org.fhir.ucum.UcumException;
import org.hl7.fhir.convertors.SpecDifferenceEvaluator;
import org.hl7.fhir.convertors.SpecDifferenceEvaluator.TypeLinkProvider;
import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.generators.specification.BaseGenerator;
import org.hl7.fhir.definitions.generators.specification.DataTypeTableGenerator;
import org.hl7.fhir.definitions.generators.specification.DictHTMLGenerator;
import org.hl7.fhir.definitions.generators.specification.JsonSpecGenerator;
import org.hl7.fhir.definitions.generators.specification.MappingsGenerator;
import org.hl7.fhir.definitions.generators.specification.ResourceDependencyGenerator;
import org.hl7.fhir.definitions.generators.specification.ResourceTableGenerator;
import org.hl7.fhir.definitions.generators.specification.SvgGenerator;
import org.hl7.fhir.definitions.generators.specification.TerminologyNotesGenerator;
import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.definitions.generators.specification.TurtleSpecGenerator;
import org.hl7.fhir.definitions.generators.specification.XmlSpecGenerator;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.Compartment;
import org.hl7.fhir.definitions.model.ConstraintStructure;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.Definitions.NamespacePair;
import org.hl7.fhir.definitions.model.Dictionary;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.EventDefn;
import org.hl7.fhir.definitions.model.EventUsage;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.Operation.OperationExample;
import org.hl7.fhir.definitions.model.OperationParameter;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.ResourceDefn.FMGApproval;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn.CompositeDefinition;
import org.hl7.fhir.definitions.model.SearchParameterDefn.SearchType;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.W5Entry;
import org.hl7.fhir.definitions.model.WorkGroup;
import org.hl7.fhir.definitions.parsers.OIDRegistry;
import org.hl7.fhir.definitions.validation.ValueSetValidator;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.PathEngineException;
import org.hl7.fhir.igtools.spreadsheets.MappingSpace;
import org.hl7.fhir.igtools.spreadsheets.TypeParser;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r4.conformance.ProfileComparer;
import org.hl7.fhir.r4.conformance.ProfileComparer.ProfileComparison;
import org.hl7.fhir.r4.conformance.ProfileUtilities;
import org.hl7.fhir.r4.conformance.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.r4.context.IWorkerContext.ILoggingService;
import org.hl7.fhir.r4.formats.FormatUtilities;
import org.hl7.fhir.r4.formats.IParser;
import org.hl7.fhir.r4.formats.IParser.OutputStyle;
import org.hl7.fhir.r4.formats.JsonParser;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r4.model.CodeSystem.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.r4.model.CodeType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ConceptMap;
import org.hl7.fhir.r4.model.ContactDetail;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.ElementDefinition;
import org.hl7.fhir.r4.model.ElementDefinition.ConstraintSeverity;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.r4.model.ElementDefinition.ElementDefinitionSlicingDiscriminatorComponent;
import org.hl7.fhir.r4.model.ElementDefinition.SlicingRules;
import org.hl7.fhir.r4.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.r4.model.Enumerations.FHIRVersion;
import org.hl7.fhir.r4.model.Enumerations.SearchParamType;
import org.hl7.fhir.r4.model.ExpressionNode.CollectionStatus;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.ImplementationGuide;
import org.hl7.fhir.r4.model.ImplementationGuide.ImplementationGuideDefinitionPageComponent;
import org.hl7.fhir.r4.model.ImplementationGuide.ImplementationGuideDefinitionResourceComponent;
import org.hl7.fhir.r4.model.MetadataResource;
import org.hl7.fhir.r4.model.NamingSystem;
import org.hl7.fhir.r4.model.NamingSystem.NamingSystemIdentifierType;
import org.hl7.fhir.r4.model.NamingSystem.NamingSystemUniqueIdComponent;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.SearchParameter;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.StructureDefinition.ExtensionContextType;
import org.hl7.fhir.r4.model.StructureDefinition.StructureDefinitionContextComponent;
import org.hl7.fhir.r4.model.StructureDefinition.StructureDefinitionMappingComponent;
import org.hl7.fhir.r4.model.Type;
import org.hl7.fhir.r4.model.TypeDetails;
import org.hl7.fhir.r4.model.UriType;
import org.hl7.fhir.r4.model.UsageContext;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.r4.model.ValueSet.ConceptReferenceDesignationComponent;
import org.hl7.fhir.r4.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r4.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.r4.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.r4.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r4.terminologies.TerminologyClient;
import org.hl7.fhir.r4.terminologies.TerminologyClientR4;
import org.hl7.fhir.r4.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.r4.terminologies.ValueSetUtilities;
import org.hl7.fhir.r4.utils.EOperationOutcome;
import org.hl7.fhir.r4.utils.FHIRPathEngine.IEvaluationContext;
import org.hl7.fhir.r4.utils.IResourceValidator;
import org.hl7.fhir.r4.utils.NarrativeGenerator;
import org.hl7.fhir.r4.utils.NarrativeGenerator.IReferenceResolver;
import org.hl7.fhir.r4.utils.NarrativeGenerator.ResourceWithReference;
import org.hl7.fhir.r4.utils.StructureMapUtilities;
import org.hl7.fhir.r4.utils.ToolingExtensions;
import org.hl7.fhir.r4.utils.Translations;
import org.hl7.fhir.r4.utils.TypesUtilities;
import org.hl7.fhir.r4.utils.TypesUtilities.TypeClassification;
import org.hl7.fhir.r4.utils.TypesUtilities.WildcardInformation;
import org.hl7.fhir.tools.converters.MarkDownPreProcessor;
import org.hl7.fhir.tools.converters.ValueSetImporterV2;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.MarkDownProcessor;
import org.hl7.fhir.utilities.MarkDownProcessor.Dialect;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.Cell;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.Row;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.TableModel;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XhtmlGenerator;
import org.w3c.dom.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class PageProcessor implements Logger, ProfileKnowledgeProvider, IReferenceResolver, ILoggingService, TypeLinkProvider  {


  public enum PageInfoType { PAGE, RESOURCE, OPERATION, VALUESET, CODESYSTEM;

    public String toCode() {
      switch (this) {
      case PAGE: return "page";
      case RESOURCE: return "resource";
      case OPERATION: return "operation";
      case VALUESET: return "valueset";
      case CODESYSTEM: return "codesystem";
      default: return null;
      }
    }
    public static PageInfoType fromCode(String type) throws FHIRException {
      if ("page".equals(type))
        return PAGE;
      if ("resource".equals(type))
        return RESOURCE;
      if ("vs".equals(type))
        return VALUESET;
      if ("cs".equals(type))
        return CODESYSTEM;
      if ("op".equals(type))
        return OPERATION;
      throw new FHIRException("Unknown type '"+type+"'");
    } 
  }

  public class PageInfo {
    private PageInfoType type;
    private String page;
    private String title;
    public PageInfo(PageInfoType type, String page, String title) {
      super();
      this.type = type;
      this.page = page;
      this.title = title;
    }
    public PageInfoType getType() {
      return type;
    }
    public String getTitle() {
      return title;
    }
    public String getPage() {
      return page;
    }
    
  }

  public class PageEvaluationContext implements IEvaluationContext {

    @Override
    public Base resolveConstant(Object appContext, String name, boolean beforeContext) throws PathEngineException {
      return null;
    }

    @Override
    public TypeDetails resolveConstantType(Object appContext, String name) throws PathEngineException {
      return null;
    }

    @Override
    public boolean log(String argument, List<Base> focus) {
      return false;
    }

    @Override
    public FunctionDetails resolveFunction(String functionName) {
      if (functionName.equals("htmlChecks"))
        return new FunctionDetails("check HTML structure", 0, 0);
      return null;
    }

    @Override
    public TypeDetails checkFunction(Object appContext, String functionName, List<TypeDetails> parameters) throws PathEngineException {
      return new TypeDetails(CollectionStatus.SINGLETON, "boolean");
    }

    @Override
    public List<Base> executeFunction(Object appContext, String functionName, List<List<Base>> parameters) {
      List<Base> list = new ArrayList<Base>();
      Base b = new BooleanType(true);
      list.add(b);
      return list;
    }

    @Override
    public Base resolveReference(Object appContext, String url) {
      throw new Error("Not done yet");
    }

    @Override
    public boolean conformsToProfile(Object appContext, Base item, String url) throws FHIRException {
      IResourceValidator val = workerContext.newValidator();
      List<ValidationMessage> valerrors = new ArrayList<ValidationMessage>();
      if (item instanceof Resource) {
        val.validate(appContext, valerrors, (Resource) item, url);
        boolean ok = true;
        for (ValidationMessage v : valerrors)
          ok = ok && v.getLevel().isError();
        return ok;
      }
      throw new NotImplementedException("Not done yet (PageEvaluationContext.conformsToProfile), when item is element");
    }

  }

  public class SectionSorter implements Comparator<String> {

    @Override
    public int compare(String arg0, String arg1) {
      String[] p0 = arg0.split("\\.");
      String[] p1 = arg1.split("\\.");
      for (int i = 0; i < Math.min(p0.length, p1.length); i++) {
        if (Utilities.isInteger(p0[i]) && Utilities.isInteger(p1[i])) {
          int i0 = Integer.parseInt(p0[i]);
          int i1 = Integer.parseInt(p1[i]);
          if (i0 != i1) {
            if (i0 < i1)
              return -1;
            else
              return 1;
          }
        } else {
           int c = p0[i].compareTo(p1[i]);
           if (c != 0)
             return c;
        }
      }
      if (p0.length > p1.length)
        return 1;
      if (p0.length < p1.length)
        return -1;
      return 0;
    }
  }

  private final List<String> suppressedMessages = new ArrayList<String>();
  private Definitions definitions;
  private FolderManager folders;
  private FHIRVersion version;
  private Navigation navigation;
  private final List<PlatformGenerator> referenceImplementations = new ArrayList<PlatformGenerator>();
  private IniFile ini;
  private final Calendar genDate = Calendar.getInstance();
  private final Date start = new Date();
  private final Map<String, String> prevSidebars = new HashMap<String, String>();
  private String buildId;
  private final List<String> orderedResources = new ArrayList<String>();
  private final Map<String, SectionTracker> sectionTrackerCache = new HashMap<String, SectionTracker>();
  private final Map<String, TocEntry> toc = new HashMap<String, TocEntry>();
  private Document v2src;
  private Document v3src;
  private final QaTracker qa = new QaTracker();
  private Map<String, ConceptMap> conceptMaps = new HashMap<String, ConceptMap>();
  private Map<String, StructureDefinition> profiles = new HashMap<String, StructureDefinition>();
  private Map<String, ImplementationGuide> guides = new HashMap<String, ImplementationGuide>();
  private Map<String, Resource> igResources = new HashMap<String, Resource>();
  private Map<String, String> svgs = new HashMap<String, String>();
  private Translations translations = new Translations();
  private BreadCrumbManager breadCrumbManager = new BreadCrumbManager(translations);
  private String publicationType = "Local Build ("+System.getenv("COMPUTERNAME")+")";
  private String publicationNotice = "";
  private OIDRegistry registry;
  private String oid; // technical identifier associated with the page being built
  private HTMLLinkChecker htmlchecker;
  private String baseURL = "http://build.fhir.org/";
  private final String tsServer; // terminology to use
  private BuildWorkerContext workerContext;
//  private List<ValidationMessage> collectedValidationErrors = new ArrayList<ValidationMessage>();
  private List<ValidationMessage> validationErrors = new ArrayList<ValidationMessage>();
  private long lastSecs = 0;
  private Set<String> searchTypeUsage = new HashSet<String>();
  private ValueSetValidator vsValidator;
  boolean forPublication;
  private String resourceCategory;
  private SpecDifferenceEvaluator diffEngine = new SpecDifferenceEvaluator();
  private Bundle typeBundle;
  private Bundle resourceBundle;
  private JsonObject r3r4Outcomes;
  private Map<String, Map<String, PageInfo>> normativePackages = new HashMap<String, Map<String, PageInfo>>();
  private List<String> normativePages = new ArrayList<String>();
  private MarkDownProcessor processor = new MarkDownProcessor(Dialect.COMMON_MARK);

  private Map<String, String> macros = new HashMap<String, String>();
  
  public PageProcessor(String tsServer) throws URISyntaxException, UcumException {
    super();
    this.tsServer = tsServer;
  }

  public final static String DEF_TS_SERVER = "http://tx.fhir.org/r4"; 
//  public final static String DEF_TS_SERVER = "http://local.fhir.org:960/r4";

  public final static String WEB_PUB_NAME = "STU3";
  public final static String CI_PUB_NAME = "Current Build";

  public final static String WEB_PUB_NOTICE =
      "<p style=\"background-color: #ffefef; border:1px solid maroon; padding: 5px; max-width: 790px;\">\r\n"+
       " This is the Current officially released version of FHIR, which is <a href=\"history.html\">R4</a>. <br/>For a full list of available versions, see the <a href=\"http://hl7.org/fhir/directory.html\">Directory of published versions</a>.\r\n"+
      "</p>\r\n"; 

  public final static String CI_PUB_NOTICE = WEB_PUB_NOTICE;
  //      "<p style=\"background-color: #ffefef; border:1px solid maroon; padding: 5px; max-width: 790px;\">\r\n"+
//          "This is the Continuous Integration Build of FHIR (will be incorrect/inconsistent at times). <br/>See the <a href=\"http://hl7.org/fhir/directory.html\">Directory of published versions</a>\r\n"+
//          "</p>\r\n";

  public static final String CODE_LIMIT_EXPANSION = "1000";
  public static final String TOO_MANY_CODES_TEXT_NOT_EMPTY = "This value set has >1000 codes in it. In order to keep the publication size manageable, only a selection (1000 codes) of the whole set of codes is shown";
  public static final String TOO_MANY_CODES_TEXT_EMPTY = "This value set cannot be expanded because of the way it is defined - it has an infinite number of members";
  private static final String NO_CODESYSTEM_TEXT = "This value set refers to code systems that the FHIR Publication Tooling does not support";

  private static final String VS_INC_START = ""; // "<div style=\"background-color: Floralwhite; border:1px solid maroon; padding: 5px;\">";
  private static final String VS_INC_END = ""; // "</div>";

//  private boolean notime;

  private String dictForDt(String dt) throws Exception {
	  File tmp = Utilities.createTempFile("tmp", ".tmp");
	  DictHTMLGenerator gen = new DictHTMLGenerator(new FileOutputStream(tmp), this, "");
	  TypeParser tp = new TypeParser();
	  TypeRef t = tp.parse(dt, false, null, workerContext, true).get(0);

	  ElementDefn e;
	  if (t.getName().equals("Resource"))
	    e = definitions.getBaseResources().get("DomainResource").getRoot();
	  else
	    e = definitions.getElementDefn(t.getName());
	  if (e == null) {
		  gen.close();
		  throw new Exception("unable to find definition for "+ dt);
	  }
	  else {
		  gen.generate(e);
		  gen.close();
	  }
	  String val = TextFile.fileToString(tmp.getAbsolutePath())+"\r\n";
	  tmp.delete();
	  return val;
  }

  private String tsForDt(String dt) throws Exception {
	  File tmp = Utilities.createTempFile("tmp", ".tmp");
	  tmp.deleteOnExit();
	  TerminologyNotesGenerator gen = new TerminologyNotesGenerator(new FileOutputStream(tmp), this);
	  TypeParser tp = new TypeParser();
	  TypeRef t = tp.parse(dt, false, null, workerContext, true).get(0);
	  ElementDefn e = definitions.getElementDefn(t.getName());
	  if (e == null) {
		  gen.close();
		  throw new Exception("unable to find definition for "+ dt);
	  }
	  else {
		  gen.generate("", e);
		  gen.close();
	  }
	  String val = TextFile.fileToString(tmp.getAbsolutePath())+"\r\n";
	  tmp.delete();
	  return val;
  }

  private String treeForDt(String dt) throws Exception {
    DataTypeTableGenerator gen = new DataTypeTableGenerator(folders.dstDir, this, dt, false);
    return new XhtmlComposer(XhtmlComposer.HTML).compose(gen.generate(definitions.getElementDefn(dt), null));
  }

  private String xmlForDt(String dt, String pn) throws Exception {
	  File tmp = Utilities.createTempFile("tmp", ".tmp");
	  XmlSpecGenerator gen = new XmlSpecGenerator(new FileOutputStream(tmp), pn == null ? null : pn.substring(0, pn.indexOf("."))+"-definitions.html", null, this, "");
	  TypeParser tp = new TypeParser();
	  TypeRef t = tp.parse(dt, false, null, workerContext, true).get(0);
	  ElementDefn e = definitions.getElementDefn(t.getName());
	  if (e == null) {
		  gen.close();
		  throw new Exception("unable to find definition for "+ dt);
	  }
	  else {
		  gen.generate(e, e.getName().equals("Element") || e.getName().equals("BackboneElement"), false);
		  gen.close();
	  }
	  String val = TextFile.fileToString(tmp.getAbsolutePath())+"\r\n";
	  tmp.delete();
	  return val;
  }

  private String jsonForDt(String dt, String pn) throws Exception {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    JsonSpecGenerator gen = new JsonSpecGenerator(b, pn == null ? null : pn.substring(0, pn.indexOf("."))+"-definitions.html", null, this, "");
    TypeParser tp = new TypeParser();
    TypeRef t = tp.parse(dt, false, null, workerContext, true).get(0);
    ElementDefn e = definitions.getElementDefn(t.getName());
    if (e == null) {
      gen.close();
      throw new Exception("unable to find definition for "+ dt);
    }
    else {
      gen.generate(e, false, false);
      gen.close();
    }
    String val = new String(b.toByteArray())+"\r\n";
    return val;
  }

  private String ttlForDt(String dt, String pn) throws Exception {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    TurtleSpecGenerator gen = new TurtleSpecGenerator(b, pn == null ? null : pn.substring(0, pn.indexOf("."))+"-definitions.html", null, this, "");
    TypeParser tp = new TypeParser();
    TypeRef t = tp.parse(dt, false, null, workerContext, true).get(0);
    ElementDefn e = definitions.getElementDefn(t.getName());
    if (e == null) {
      gen.close();
      throw new Exception("unable to find definition for "+ dt);
    }
    else {
      gen.generate(e, false);
      gen.close();
    }
    String val = new String(b.toByteArray())+"\r\n";
    return val;
  }

  private String diffForDt(String dt, String pn) throws Exception {
    return diffEngine.getDiffAsHtml(this, definitions.getElementDefn(dt).getProfile());
  }


  private String generateSideBar(String prefix) throws Exception {
    if (prevSidebars.containsKey(prefix))
      return prevSidebars.get(prefix);
    List<String> links = new ArrayList<String>();

    StringBuilder s = new StringBuilder();
    s.append("<div class=\"sidebar\">\r\n");
    s.append("<p><a href=\"http://hl7.org/fhir\" title=\"Fast Healthcare Interoperability Resources - Home Page\"><img border=\"0\" src=\""+prefix+"flame16.png\" style=\"vertical-align: text-bottom\"/></a> "+
      "<a href=\"http://hl7.org/fhir\" title=\"Fast Healthcare Interoperability Resources - Home Page\"><b>FHIR</b></a>&reg; v"+getVersion()+" &copy; <a href=\"http://hl7.org\">HL7</a></p>\r\n");

    for (Navigation.Category c : navigation.getCategories()) {
      if (!"nosidebar".equals(c.getMode())) {
        if (c.getLink() != null) {
          s.append("  <h2><a href=\""+prefix+c.getLink()+".html\">"+c.getName()+"</a></h2>\r\n");
          links.add(c.getLink());
        }
        else
          s.append("  <h2>"+c.getName()+"</h2>\r\n");
        s.append("  <ul>\r\n");
        for (Navigation.Entry e : c.getEntries()) {
          if (e.getLink() != null) {
            links.add(e.getLink());
            s.append("    <li><a href=\""+prefix+e.getLink()+".html\">"+Utilities.escapeXml(e.getName())+"</a></li>\r\n");
          } else
            s.append("    <li>"+e.getName()+"</li>\r\n");
        }
        if (c.getEntries().size() ==0 && c.getLink().equals("resourcelist")) {
          List<String> list = new ArrayList<String>();
          list.addAll(definitions.getResources().keySet());
          Collections.sort(list);

          for (String rn : list) {
          //  if (!links.contains(rn.toLowerCase())) {
              ResourceDefn r = definitions.getResourceByName(rn);
              orderedResources.add(r.getName());
              s.append("    <li><a href=\""+prefix+rn.toLowerCase()+".html\">"+Utilities.escapeXml(r.getName())+"</a></li>\r\n");
          //  }
          }

        }
        s.append("  </ul>\r\n");
      }
    }
    // s.append(SIDEBAR_SPACER);
    s.append("<p><a href=\"http://gforge.hl7.org/gf/project/fhir/\" title=\"GitHub Commit Hash\">Build "+buildId+"</a> (<a href=\"qa.html\">QA Page</a>)</p><p> <a href=\"http://hl7.org\"><img width=\"42\" height=\"50\" border=\"0\" src=\""+prefix+"hl7logo.png\"/></a></p>\r\n");

    s.append("</div>\r\n");
    prevSidebars.put(prefix, s.toString());
    return prevSidebars.get(prefix);
  }

  private String combineNotes(String location, List<String> followUps, String notes, String prefix) throws Exception {
    String s = "";
    if (notes != null && !notes.equals(""))
      s = notes;
    if (followUps.size() > 0)
      if (!s.isEmpty())
        s = s + "\r\n\r\nFollow ups: "+Utilities.asCSV(followUps);
      else
        s = "Follow ups: "+Utilities.asCSV(followUps);
    return processMarkdown(location, s, prefix);
  }

  private String describeMsg(List<String> resources, List<String> aggregations) {
    if (resources.isEmpty() && aggregations.isEmpty())
      return "<font color=\"silver\">--</font>";
    else {
      String s = resources.isEmpty() ? "" : Utilities.asCSV(resources);

      if (aggregations.isEmpty())
        return s;
      else
        return s + "<br/>"+Utilities.asHtmlBr("&nbsp;"+resources.get(0), aggregations)+"";
    }
  }


  public String processPageIncludes(String file, String src, String type, Map<String, String> others, Resource resource, List<String> tabs, String crumbTitle, ImplementationGuideDefn ig, ResourceDefn rd, WorkGroup wg) throws Exception {
    return processPageIncludes(file, src, type, others, file, resource, tabs, crumbTitle, ig, rd, wg);
  }

  public String processPageIncludes(String file, String src, String type, Map<String, String> others, String pagePath, Resource resource, List<String> tabs, String crumbTitle, ImplementationGuideDefn ig, ResourceDefn rd, WorkGroup wg) throws Exception {
    return processPageIncludes(file, src, type, others, pagePath, resource, tabs, crumbTitle, null, ig, rd, wg);
  }

  public String processPageIncludes(String file, String src, String type, Map<String, String> others, String pagePath, Resource resource, List<String> tabs, String crumbTitle, Object object, ImplementationGuideDefn ig, ResourceDefn rd, WorkGroup wg) throws Exception {
    String workingTitle = null;
    int level = ig == null ? file.contains(File.separator) ? 1 : 0 : ig.isCore() ? 0 : 1;
    boolean even = false;
    String name = file.substring(0,file.lastIndexOf("."));
    String searchAdditions = "";

    while (src.contains("<%") || src.contains("[%"))
    {
      int i1 = src.indexOf("<%");
      int i2 = i1 == -1 ? -1 : src.substring(i1).indexOf("%>")+i1;
      if (i1 == -1) {
        i1 = src.indexOf("[%");
        i2 = i1 == -1 ? -1 : src.substring(i1).indexOf("%]")+i1;
      }
      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);

      String[] com = s2.split(" ");
      if (com.length == 4 && com[0].equals("edt")) {
        if (tabs != null)
          tabs.add("tabs-"+com[1]);
        src = s1+orgDT(com[1], xmlForDt(com[1], com[2]), treeForDt(com[1]), umlForDt(com[1], com[3]), umlForDt(com[1], com[3]+"b"), profileRef(com[1]), tsForDt(com[1]), jsonForDt(com[1], com[2]), ttlForDt(com[1], com[2]), diffForDt(com[1], com[2]))+s3;
      } else if (com.length == 3 && com[0].equals("dt")) {
        if (tabs != null)
          tabs.add("tabs-"+com[1]);
        src = s1+orgDT(com[1], xmlForDt(com[1], file), treeForDt(com[1]), umlForDt(com[1], com[2]), umlForDt(com[1], com[2]+"b"), profileRef(com[1]), tsForDt(com[1]), jsonForDt(com[1], file), ttlForDt(com[1], file), diffForDt(com[1], file))+s3;
      } else if (com.length == 2 && com[0].equals("dt.constraints"))
        src = s1+genConstraints(com[1], genlevel(level))+s3;
      else if (com.length == 2 && com[0].equals("dt.restrictions"))
        src = s1+genRestrictions(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dictionary"))
        src = s1+dictForDt(com[1])+s3;
      else if (com[0].equals("othertabs"))
        src = s1 + genOtherTabs(com[1], tabs) + s3;
      else if (com[0].equals("dtheader"))
        src = s1+dtHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("mdtheader"))
        src = s1+mdtHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("edheader"))
        src = s1+edHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("elheader"))
        src = s1+elHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("belheader"))
        src = s1+belHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("extheader"))
        src = s1+extHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("mmheader"))
        src = s1+mmHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("cdheader"))
        src = s1+cdHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("diheader"))
        src = s1+diHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("ctheader"))
        src = s1+ctHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("ucheader"))
        src = s1+ucHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("rrheader"))
        src = s1+rrHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("drheader"))
        src = s1+drHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("adheader"))
        src = s1+adHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("pdheader"))
        src = s1+pdHeader(com.length > 1 ? com[1] : null) + s3;
      else if (com[0].equals("tdheader"))
        src = s1+tdHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("narrheader"))
        src = s1+narrHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("profilesheader"))
        src = s1+profilesHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("refheader"))
        src = s1+refHeader(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("resourcesheader"))
        src = s1+resourcesHeader(com.length > 1 ? com[1] : null)+s3;
//      else if (com[0].equals("formatsheader"))
//        src = s1+formatsHeader(name, com.length > 1 ? com[1] : null)+s3;
//      else if (com[0].equals("resourcesheader"))
//        src = s1+resourcesHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("txheader"))
        src = s1+txHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("sct-vs-list"))
        src = s1+getSnomedCTVsList()+s3;
      else if (com[0].equals("sct-concept-list"))
        src = s1+getSnomedCTConceptList()+s3;
      else if (com[0].equals("txheader0"))
        src = s1+(level > 0 ? "" : txHeader(name, com.length > 1 ? com[1] : null))+s3;
      else if (com[0].equals("fmtheader"))
        src = s1+fmtHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("cmpheader"))
        src = s1+cmpHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("dictheader"))
        src = s1+dictHeader(((Bundle) resource).getId().toLowerCase(), com.length > 1 ? com[1] : "")+s3;
//      else if (com[0].equals("atomheader"))
//        src = s1+atomHeader(name, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("codelist"))
        src = s1+codelist((CodeSystem) resource, com.length > 1 ? com[1] : null, false, true, file)+s3;
      else if (com[0].equals("codelist-nh"))
        src = s1+codelist((CodeSystem) resource, com.length > 1 ? com[1] : null, false, false, file)+s3;
      else if (com[0].equals("linkcodelist"))
        src = s1+codelist((CodeSystem) resource, com.length > 1 ? com[1] : null, true, false, file)+s3;
      else if (com[0].equals("toc"))
        src = s1 + generateToc() + s3;
      else if (com[0].equals("codetoc"))
        src = s1+codetoc(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("resheader")) {
        if (resource != null && resource instanceof StructureDefinition)
          src = s1+resHeader(((StructureDefinition) resource).getId().toLowerCase(), ((StructureDefinition) resource).getId(), com.length > 1 ? com[1] : null)+s3;
        else if (rd != null) {
          src = s1+resHeader(rd.getName().toLowerCase(), rd.getName(), com.length > 1 ? com[1] : null)+s3;
        } else 
          src = s1+s3;
      } else if (com[0].equals("aresheader"))
        src = s1+abstractResHeader("document", "Document", com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("onthispage"))
        src = s1+onThisPage(s2.substring(com[0].length() + 1))+s3;
      else if (com[0].equals("maponthispage"))
          src = s1+mapOnThisPage(null)+s3;
      else if (com[0].equals("res-category")) {
        even = false;
        src = s1+resCategory(s2.substring(com[0].length() + 1))+s3;
      } else if (com[0].equals("res-item")) {
        even = !even;
        src = s1+resItem(com[1], even)+s3;
      } else if (com[0].equals("resdesc")) {
        src = s1+resDesc(com[1])+s3;
      } else if (com[0].equals("rescat")) {
        src = s1+resCat(com.length == 1 ? null : s2.substring(7))+s3;
      } else if (com[0].equals("svg"))
        src = s1+svgs.get(com[1])+s3;
      else if (com[0].equals("diagram"))
        src = s1+new SvgGenerator(this, genlevel(level), null, false, file.contains("datatypes")).generate(folders.srcDir+ com[1], com[2])+s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("v2xref"))
        src = s1 + xreferencesForV2(name, com[1]) + s3;
      else if (com[0].equals("vs-warning"))
        src = s1 + vsWarning((ValueSet) resource) + s3;
      else if (com[0].equals("res-status-special"))
        src = s1 + vsSpecialStatus((DomainResource) resource) + s3;
      else if (com[0].equals("conceptmaplistv2"))
        src = s1 + conceptmaplist("http://terminology.hl7.org/ValueSet/v2-"+(name.contains("|") ? name.substring(0,name.indexOf("|")) : name), com[1]) + s3;
      else if (com[0].equals("conceptmaplistv3"))
        src = s1 + conceptmaplist("http://terminology.hl7.org/ValueSet/v3-"+(name.contains("|") ? name.substring(0,name.indexOf("|")) : name), com[1]) + s3;
      else if (com[0].equals("conceptmaplistvs")) {
        ValueSet vs = (ValueSet) resource;
        String ref;
        if (vs == null) {
          ref = "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file);
        } else {
          ref = vs.getUrl();
        }
        src = s1 + conceptmaplist(ref, com[1]) + s3;
      } else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      } else if (com[0].equals("igregistries")) {
        src = s1+igRegistryList(com[1], com[2])+s3;
      } else if (com[0].equals("dtmappings")) {
        src = s1 + genDataTypeMappings(com[1]) + s3;
      } else if (com[0].equals("dtusage")) {
        src = s1 + genDataTypeUsage(com[1]) + s3;
      }  else if (com[0].equals("v3xref")) {
        src = s1 + xreferencesForV3(name) + s3;
      }  else if (com[0].equals("reflink")) {
        src = s1 + reflink(com[1]) + s3;
      } else if (com[0].equals("setlevel")) {
        level = Integer.parseInt(com[1]);
        src = s1+s3;
      } else if (com[0].equals("w5")) {
          src = s1+genW5("true".equals(com[1]))+s3;
      } else if (com[0].equals("res-ref-list")) {
        src = s1+genResRefList(com[1])+s3;
      } else if (com[0].equals("sclist")) {
        src = s1+genScList(com[1])+s3;
      } else if (com[0].equals("xcm")) {
        src = s1+getXcm(com[1])+s3;
      } else if (com[0].equals("xcmchk")) {
        src = s1+getXcmChk(com[1])+s3;
      } else if (com[0].equals("sstatus")) {
        if (com.length == 1) {
          StandardsStatus ss = ToolingExtensions.getStandardsStatus((DomainResource) resource);
          if (ss == null)
            ss = StandardsStatus.INFORMATIVE;
          if (ss == StandardsStatus.NORMATIVE && ToolingExtensions.hasExtension((DomainResource) resource, ToolingExtensions.EXT_NORMATIVE_VERSION))
            src = s1+"<a href=\""+genlevel(level)+"versions.html#std-process\">"+ss.toDisplay()+"</a> (from v"+ToolingExtensions.readStringExtension((DomainResource) resource, ToolingExtensions.EXT_NORMATIVE_VERSION)+")"+s3;
          else
            src = s1+"<a href=\""+genlevel(level)+"versions.html#std-process\">"+ss.toDisplay()+"</a>"+s3;
        } else
          src = s1+getStandardsStatus(com[1])+s3;
      } else if (com[0].equals("wg")) {
        src = s1+getWgLink(file, wg == null && com.length > 1 ? wg(com[1]) : wg)+s3;
      } else if (com[0].equals("wgt")) {
        src = s1+getWgTitle(wg == null && com.length > 1 ? wg(com[1]) : wg)+s3;
      } else if (com[0].equals("ig.registry")) {
        src = s1+buildIgRegistry(ig, com[1])+s3;
      } else if (com[0].equals("search-link")) {
        src = s1+searchLink(s2)+s3;
      } else if (com[0].equals("search-footer")) {
        src = s1+searchFooter(level)+s3;
      } else if (com[0].equals("search-header")) {
          src = s1+searchHeader(level)+s3;
      } else if (com[0].equals("profileheader")) {
        src = s1+profileHeader(((StructureDefinition) resource).getId().toLowerCase(), com[1], hasExamples((StructureDefinition) resource, ig))+s3;
      } else if (com[0].equals("resource-table")) {
        src = s1+genResourceTable(definitions.getResourceByName(com[1]), genlevel(level))+s3;
      } else if (com[0].equals("dtextras")) {
        src = s1+produceDataTypeExtras(com[1], false)+s3;
      } else if (com[0].equals("dtextensions")) {
        src = s1+produceDataTypeExtras(com[1], true)+s3;
      } else if (com[0].equals("tx")) {
        src = s1+produceDataTypeTx(com[1])+s3;
      } else if (com[0].equals("extension-diff")) {
        StructureDefinition ed = workerContext.fetchResource(StructureDefinition.class, com[1]);
        src = s1+generateExtensionTable(ed, "extension-"+com[1], "false", genlevel(level))+s3;
      } else if (com[0].equals("profile-diff")) {
        ConstraintStructure p = definitions.findProfile(com[1]);
        src = s1 + generateProfileStructureTable(p, true, com[1]+".html", com[1], genlevel(level)) + s3;
      } else if (com[0].equals("example")) {
        String[] parts = com[1].split("\\/");
        Example e = findExample(parts[0], parts[1]);
        src = s1+genExample(e, com.length > 2 ? Integer.parseInt(com[2]) : 0, genlevel(level))+s3;
      } else if (com[0].equals("r3r4transform")) {
        src = s1+dtR3R4Transform(com[1])+s3;
      } else if (com[0].equals("fmm-style")) {
        String fmm = resource == null ? "N/A" :  ToolingExtensions.readStringExtension((DomainResource) resource, ToolingExtensions.EXT_FMM_LEVEL);
        StandardsStatus ss = ToolingExtensions.getStandardsStatus((DomainResource) resource);
        src = s1+fmmBarColorStyle(ss, fmm)+s3;
      } else if (com[0].equals("fmm")) {
        String fmm = resource == null || !(resource instanceof MetadataResource) ? getFmm(com[1], false) : ToolingExtensions.readStringExtension((DomainResource) resource, ToolingExtensions.EXT_FMM_LEVEL);
        StandardsStatus ss = ToolingExtensions.getStandardsStatus((DomainResource) resource);
        if (StandardsStatus.EXTERNAL == ss)
          src = s1+getFmmFromlevel(genlevel(level), "N/A")+s3;
        else if (StandardsStatus.NORMATIVE == ss)
          src = s1+getFmmFromlevel(genlevel(level), "<a href=\""+genlevel(level)+"versions.html#std-process\">N</a>")+s3;
        else
          src = s1+getFmmFromlevel(genlevel(level), fmm)+s3;
      } else if (com[0].equals("fmmna")) {
        String fmm = "N/A";
        src = s1+getFmmFromlevel(genlevel(level), fmm)+s3;
      } else if (com[0].equals("mostlynormative")) {
        String p = null;
        String wt = workingTitle; 
        if (com.length >= 3) {
          if (!com[2].equals("%check"))
            p = com[2]; 
          else if (StandardsStatus.NORMATIVE == ToolingExtensions.getStandardsStatus((DomainResource) resource)) {
            p = resource.getUserString("ballot.package");
            wt = ((MetadataResource) resource).fhirType()+" "+((MetadataResource) resource).present();
          }
        }
        src = s1+(p == null ? "" : getMostlyNormativeNote(genlevel(level), p, com[1], wt, file))+s3;
      } else if (com[0].equals("mixednormative")) {
        String p = null;
        String wt = workingTitle; 
        if (com.length >= 3) {
          if (!com[2].equals("%check"))
            p = com[2]; 
          else if (StandardsStatus.NORMATIVE == ToolingExtensions.getStandardsStatus((DomainResource) resource)) {
            p = resource.getUserString("ballot.package");
            wt = ((MetadataResource) resource).fhirType()+" "+((MetadataResource) resource).present();
          }
        }
        src = s1+(p == null ? "" : getMixedNormativeNote(genlevel(level), p, com[1], wt, file))+s3;
      } else if (com[0].equals("normative")) {
        String p = object instanceof Object ? rd.getNormativePackage() : null;
        String wt = object instanceof Object ? rd.getName()+" Operation " + ((Operation) object).getName() : workingTitle; 
        if (com.length >= 3) {
          if (!com[2].equals("%check"))
            p = com[2]; 
          else if (StandardsStatus.NORMATIVE == ToolingExtensions.getStandardsStatus((DomainResource) resource)) {
            p = resource.getUserString("ballot.package");
            wt = ((MetadataResource) resource).fhirType()+" "+((MetadataResource) resource).present();
          }
        } 
        src = s1+(p == null && com.length != 2 ? "" : getNormativeNote(genlevel(level), p, com[1], wt, file))+s3;
      } else if (com[0].equals("normative-op")) {
        String p = rd.getNormativePackage();
        String wt = rd.getName()+" Operation " + ((Operation) object).getName(); 
        StandardsStatus st = ((Operation) object).getStandardsStatus();
        if (st == null)
          st = ToolingExtensions.getStandardsStatus((DomainResource) resource);
        src = s1+(st == StandardsStatus.NORMATIVE ? getNormativeNote(genlevel(level), p, com[1], wt, file) : "")+s3;
      } else if (com[0].equals("fmmshort")) {
        String fmm = resource == null || !(resource instanceof MetadataResource) ? getFmm(com[1], true) : ToolingExtensions.readStringExtension((DomainResource) resource, ToolingExtensions.EXT_FMM_LEVEL);
        String npr = resource == null || !(resource instanceof MetadataResource) ? getNormativePackageRef(com[1]) : "";
        src = s1+getFmmShortFromlevel(genlevel(level), fmm)+npr+s3;
      } else if (com[0].equals("normative-pages")) {
        src = s1+getNormativeList(genlevel(level), com[1])+s3;
      } else if (s2.startsWith("search-additions\r\n")) {
        searchAdditions = s2.substring(16).trim();
        src = s1+s3;
      } else if (com[0].equals("complinks")) {
        src = s1+(rd == null ? "" : getCompLinks(rd, com.length > 1 ? com[1] : null))+s3;
      } else if (com[0].equals("diff")) {
        String p = com[1];
        String pd = p.contains("#") ? p.substring(0, p.indexOf("#")) : p;
        String t = s2.substring(com[0].length()+com[1].length()+2);
        src = s1+"<a href=\""+p+"\">"+t+"</a> <a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+pd+"&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+pd+"\" no-external=\"true\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a>"+s3;
      } else if (com[0].equals("diffx")) {
        String p1 = com[1];
        String pd = p1.contains("#") ? p1.substring(0, p1.indexOf("#")) : p1;
        String p2 = com[2];
        String t = s2.substring(com[0].length()+com[1].length()+2);
        src = s1+"<a href=\""+p1+"\">"+t+"</a> <a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+p2+"&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+pd+"\" no-external=\"true\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a>"+s3;
      } else if (com[0].equals("StandardsStatus")) {
        src = s1+getStandardsStatusNote(genlevel(level), com[1], com[2], com.length == 4 ? com[3] : null)+s3;
      } else if (com[0].equals("circular-references")) {
        src = s1+buildCircularReferenceList(com[1].equals("null") ? null : Boolean.valueOf(com[1]))+s3;
      } else if (com[0].equals("shortparameterlist")) {
        src = s1+buildShortParameterList(com[1])+s3;
      } else if (com[0].equals("op-example-link")) {
        src = s1+buildOpReferenceList(com[1])+s3;       
      } else if (com[0].equals("diff-analysis")) {
        if ("*".equals(com[1])) {
          updateDiffEngineDefinitions();
          src = s1+diffEngine.getDiffAsHtml(this)+s3;
        } else {
          StructureDefinition sd = workerContext.fetchTypeDefinition(com[1]);
          if (sd == null)
            throw new Exception("diff-analysis not found: "+com[1]);
          src = s1+diffEngine.getDiffAsHtml(this, sd)+s3;
        }
      } else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(name.toUpperCase().substring(0, 1)+name.substring(1))+s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+(workingTitle == null ? Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1)) : workingTitle)+s3;
      else if (com[0].equals("xtitle"))
        src = s1+Utilities.escapeXml(name.toUpperCase().substring(0, 1) + name.substring(1))+s3;
      else if (com[0].equals("name"))
        src = s1+name+s3;
      else if (com[0].equals("name.tail"))
        src = s1+fileTail(name)+s3;
      else if (com[0].equals("piperesources"))
        src = s1+pipeResources()+s3;
      else if (com[0].equals("enteredInErrorTable"))
        src = s1+enteredInErrorTable()+s3;
      else if (com[0].equals("canonicalname"))
        src = s1+makeCanonical(name)+s3;
      else if (com[0].equals("prettyname"))
        src = s1+makePretty(name)+s3;
      else if (com[0].equals("jsonldname"))
        src = s1+makeJsonld(name)+s3;
      else if (com[0].equals("version"))
        src = s1+version+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("maindiv"))
        src = s1+"<div class=\"content\">"+s3;
      else if (com[0].equals("/maindiv"))
        src = s1+"</div>"+s3;
      else if (com[0].equals("v2Index"))
        src = s1+genV2Index()+s3;
      else if (com[0].equals("v2VSIndex"))
        src = s1+genV2VSIndex()+s3;
      else if (com[0].equals("v3Index-cs"))
        src = s1+genV3CSIndex()+s3;
      else if (com[0].equals("v3Index-vs"))
        src = s1+genV3VSIndex()+s3;
      else if (com[0].equals("mappings-table"))
        src = s1+genMappingsTable()+s3;
      else if (com[0].equals("id"))
        src = s1+(name.contains("|") ? name.substring(0,name.indexOf("|")) : name)+s3;
      else if (com[0].equals("ver"))
        src = s1+(name.contains("|") ? name.substring(name.indexOf("|")+1) : "??")+s3;
      else if (com[0].equals("v2Table"))
        src = s1+genV2Table(name)+s3;
      else if (com[0].equals("v2Expansion"))
        src = s1+genV2Expansion(name, genlevel(level))+s3;
      else if (com[0].equals("v2TableVer"))
        src = s1+genV2TableVer(name)+s3;
      else if (com[0].equals("v3CodeSystem"))
        src = s1+genV3CodeSystem(name)+s3;
      else if (com[0].equals("v3ValueSet"))
        src = s1+genV3ValueSet(name)+s3;
      else if (com[0].equals("events"))
        src = s1 + getEventsTable(pagePath)+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
      else if (com[0].equals("allparams"))
        src = s1 + allParamlist() + s3;
//      else if (com[0].equals("bindingtable-codelists"))
//        src = s1 + genBindingTable(true) + s3;
//      else if (com[0].equals("bindingtable"))
//        src = s1 + genBindingsTable() + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
      else if (com[0].equals("valuesetslist"))
        src = s1 + genValueSetsTable(ig) + s3;
      else if (com[0].equals("namespacelist"))
        src = s1 + genNSList() + s3;
      else if (com[0].equals("extensionslist"))
        src = s1 + genExtensionsTable() + s3;
      else if (com[0].equals("igvaluesetslist"))
        src = s1 + genIGValueSetsTable() + s3;
      else if (com[0].equals("conceptmapslist"))
        src = s1 + genConceptMapsTable() + s3;
//      else if (com[0].equals("bindingtable-others"))
//        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("resimplall"))
          src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList(pagePath) + s3;
      else if (com[0].equals("txurl"))
        src = s1 + "http://hl7.org/fhir/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vstxurl"))
        src = s1 + "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("csurl")) {
        if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).getUrl() + s3;
        else {
          CodeSystem cs = (CodeSystem) ((ValueSet) resource).getUserData("cs");
          src = s1 + (cs == null ? "" : cs.getUrl()) + s3;
        }
      } else if (com[0].equals("vsurl")) {
        if (resource == null)
          src = s1 + s3;
        else if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).getUrl() + s3;
        else 
          src = s1 + ((ValueSet) resource).getUrl() + s3;
      } else if (com[0].equals("txdef"))
        src = s1 + generateCodeDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsdef"))
        if (resource instanceof CodeSystem)
          src = s1 + processMarkdown("vsdef", ((CodeSystem) resource).getDescription(), genlevel(level)) + s3;
        else
          src = s1 + processMarkdown("vsdef", ((ValueSet) resource).getDescription(), genlevel(level)) + s3;
      else if (com[0].equals("txoid"))
        src = s1 + generateOID((CodeSystem) resource) + s3;
      else if (com[0].equals("vsoid"))
        src = s1 + generateOID((ValueSet) resource) + s3;
      else if (com[0].equals("txname"))
        src = s1 + Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsname"))
        if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).present() + s3;
        else
          src = s1 + ((ValueSet) resource).present() + s3;
      else if (com[0].equals("vsnamed"))
        if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).getName() + s3;
        else
          src = s1 + ((ValueSet) resource).getName() + s3;
      else if (com[0].equals("vstitle"))
        if (resource instanceof CodeSystem)
          src = s1 + checkTitle(((CodeSystem) resource).getTitle()) + s3;
        else
          src = s1 + checkTitle(((ValueSet) resource).getTitle()) + s3;
      else if (com[0].equals("vsver"))
        if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).getVersion() + s3;
        else
          src = s1 + ((ValueSet) resource).getVersion() + s3;
      else if (com[0].equals("vsref")) {
        src = s1 + Utilities.fileTitle((String) resource.getUserData("filename")) + s3;
      } else if (com[0].equals("txdesc"))
        src = s1 + generateDesc((ValueSet) resource) + s3;
      else if (com[0].equals("vsdesc"))
        src = s1 + (resource != null ? new XhtmlComposer(XhtmlComposer.HTML).compose(((ValueSet) resource).getText().getDiv()) :  generateVSDesc(Utilities.fileTitle(file))) + s3;
      else if (com[0].equals("txusage"))
        src = s1 + generateValueSetUsage((ValueSet) resource, genlevel(level), true) + s3;
      else if (com[0].equals("vsusage"))
        src = s1 + generateValueSetUsage((ValueSet) resource, genlevel(level), true) + s3;
      else if (com[0].equals("csusage"))
        src = s1 + generateCSUsage((CodeSystem) resource, genlevel(level)) + s3;
      else if (com[0].equals("vssummary"))
        src = s1 + "todo" + s3;
      else if (com[0].equals("compartmentlist"))
        src = s1 + compartmentlist() + s3;
      else if (com[0].equals("qa"))
        src = s1 + qa.report(this, validationErrors) + s3;
      else if (com[0].equals("comp-title"))
        src = s1 + compTitle(name) + s3;
      else if (com[0].equals("comp-name"))
        src = s1 + compName(name) + s3;
      else if (com[0].equals("comp-desc"))
        src = s1 + compDesc(name) + s3;
      else if (com[0].equals("comp-uri"))
        src = s1 + compUri(name) + s3;
      else if (com[0].equals("comp-identity"))
        src = s1 + compIdentity(name) + s3;
      else if (com[0].equals("comp-membership"))
        src = s1 + compMembership(name) + s3;
      else if (com[0].equals("comp-resources"))
        src = s1 + compResourceMap(name) + s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(name) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(name, genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist")) {
        if (object instanceof Operation)
          src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(rd.getName().toLowerCase(), type, genlevel(level), crumbTitle) : ig.makeList(name, type, genlevel(level), crumbTitle)) + s3;
        else
          src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(name, type, genlevel(level), crumbTitle) : ig.makeList(name, type, genlevel(level), crumbTitle)) + s3;
      } else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;
      else if (com[0].equals("buildId"))
        src = s1 + buildId + s3;
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;
      else if (com[0].equals("vssource"))
        if (resource instanceof CodeSystem)
          src = s1 + csSource((CodeSystem) resource) + s3;
        else
          src = s1 + vsSource((ValueSet) resource) + s3;
      else if (com[0].equals("vsxref"))
        src = s1 + xreferencesForFhir(name) + s3;
      else if (com[0].equals("vsexpansion"))
        src = s1 + expandValueSet(Utilities.fileTitle(file), resource == null ? null : ((ValueSet) resource), genlevel(level)) + s3;
      else if (com[0].equals("vscld"))
        src = s1 + vsCLD(Utilities.fileTitle(file), resource == null ? null : ((ValueSet) resource), genlevel(level)) + s3;
      else if (com[0].equals("cs-content"))
        src = s1 + csContent(Utilities.fileTitle(file), ((CodeSystem) resource), genlevel(level)) + s3;
      else if (com[0].equals("vsexpansionig"))
        src = s1 + expandValueSetIG((ValueSet) resource, true) + s3;
      else if (com[0].equals("v3expansion"))
        src = s1 + expandV3ValueSet(name) + s3;
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;
      else if (com[0].equals("archive"))
        src = s1 + makeArchives() + s3;
      else if (com[0].equals("pagepath"))
        src = s1 + pagePath + s3;
      else if (com[0].equals("rellink")) {
        if (!pagePath.contains(".html"))
          throw new Error("Invalid link: "+pagePath+" at "+workingTitle);
        src = s1 + Utilities.URLEncode(pagePath) + s3;
      } else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;
      else if (com[0].equals("baseURLn"))
        src = s1 + Utilities.appendForwardSlash(baseURL) + s3;
      else if (com[0].equals("profilelist"))
        src = s1 + genProfilelist() + s3;
      else if (com[0].equals("igprofileslist"))
        src = s1 + genIGProfilelist() + s3;
      else if (com[0].equals("operationslist"))
        src = s1 + genOperationList() + s3;
      else if (com[0].equals("example.profile.link"))
        src = s1 + genExampleProfileLink(resource) + s3;
      else if (com[0].equals("id_regex"))
        src = s1 + FormatUtilities.ID_REGEX + s3;
      else if (com[0].equals("resourcecount"))
        src = s1 + Integer.toString(definitions.getResources().size()) + s3;
      else if (others != null && others.containsKey(com[0]))
        src = s1 + others.get(com[0]) + s3;
      else if (com[0].equals("status-codes"))
        src = s1 + genStatusCodes() + s3;
      else if (com[0].equals("dictionary.name")) {
        String n = name.contains(File.separator) ? name.substring(name.lastIndexOf(File.separator)+1) : name;
        src = s1 + definitions.getDictionaries().get(n).getName() + s3;
//      } else if (com[0].equals("dictionary.view"))
//        src = s1 + ResourceUtilities.representDataElementCollection(this.workerContext, (Bundle) resource, true, "hspc-qnlab-de") + s3;
      } else if (com[0].equals("search-param-pack") && resource instanceof SearchParameter)
        src = s1 + ((SearchParameter) resource).getUserData("pack") + s3;
      else if (com[0].equals("search-param-name") && resource instanceof SearchParameter)
        src = s1 + ((SearchParameter) resource).getName() + s3;
      else if (com[0].equals("search-param-url") && resource instanceof SearchParameter)
        src = s1 + ((SearchParameter) resource).getUrl() + s3;
      else if (com[0].equals("search-param-type") && resource instanceof SearchParameter)
        src = s1 + ((SearchParameter) resource).getType().toCode() + s3;
      else if (com[0].equals("search-param-definition") && resource instanceof SearchParameter)
        src = s1 + ((SearchParameter) resource).getDescription() + s3;
      else if (com[0].equals("search-param-paths") && resource instanceof SearchParameter)
        src = s1 + (((SearchParameter) resource).hasXpath() ? ((SearchParameter) resource).getXpath() : "") + s3;
      else if (com[0].equals("search-param-targets") && resource instanceof SearchParameter) {
        CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
        for (CodeType t : ((SearchParameter) resource).getTarget())
          b.append(t.asStringValue());
        src = s1 + b.toString() + s3;
      }
      else if (com[0].startsWith("!"))
        src = s1 + s3;
      else if (com[0].equals("txsummary"))
        if (resource instanceof CodeSystem)
          src = s1 + txsummary((CodeSystem) resource, genlevel(level)) + s3;
        else
          src = s1 + txsummary((ValueSet) resource, genlevel(level)) + s3;
      else if (com[0].equals("pc.title"))
        src = s1 +Utilities.escapeXml(((ProfileComparer) object).getTitle()) + s3;
      else if (com[0].equals("pc.left"))
        src = s1 + genPCLink(((ProfileComparer) object).getLeftName(), ((ProfileComparer) object).getLeftLink()) + s3;
      else if (com[0].equals("pc.right"))
        src = s1 + genPCLink(((ProfileComparer) object).getRightName(), ((ProfileComparer) object).getRightLink()) + s3;
      else if (com[0].equals("pc.table"))
        src = s1 + genPCTable((ProfileComparer) object) + s3;
      else if (com[0].equals("pc.valuesets"))
        src = s1 + "<p>todo</p>"+s3;
      else if (com[0].equals("cmp.left"))
        src = s1 + genPCLink(((ProfileComparison) object).getLeft().getName(), ((ProfileComparison) object).getLeft().getUserString("path")) + s3;
      else if (com[0].equals("cmp.right"))
        src = s1 + genPCLink(((ProfileComparison) object).getRight().getName(), ((ProfileComparison) object).getRight().getUserString("path")) + s3;
      else if (com[0].equals("cmp.messages"))
        src = s1 + "<p>"+genCmpMessages(((ProfileComparison) object))+"</p>"+s3;
      else if (com[0].equals("cmp.subset"))
        src = s1 + genCompModel(((ProfileComparison) object).getSubset(), "intersection", file.substring(0, file.indexOf(".")), genlevel(level))+s3;
      else if (com[0].equals("cmp.superset"))
        src = s1 + genCompModel(((ProfileComparison) object).getSuperset(), "union", file.substring(0, file.indexOf(".")), genlevel(level))+s3;
      else if (com[0].equals("identifierlist"))
        src = s1 + genIdentifierList()+s3;
      else if (com[0].equals("allsearchparams"))
        src = s1 + genAllSearchParams()+s3;
      else if (com[0].equals("internalsystemlist"))
        src = s1 + genCSList()+s3;
      else if (com[0].equals("internalsystemlistx"))
        src = s1 + genCSListX()+s3;
      else if (com[0].equals("example-usage"))
        src = s1+s3;
      else if (com[0].equals("ig.title"))
        src = s1+ig.getName()+s3;
      else if (com[0].equals("ig.wglink"))
        src = s1+igLink(ig)+s3;
      else if (com[0].equals("ig.wgt"))
        src = s1+ig.getCommittee()+s3;
      else if (com[0].equals("ig.fmm"))
        src = s1+getFmmFromlevel(genlevel(level), ig.getFmm())+s3;
      else if (com[0].equals("ig.ballot"))
        src = s1+ig.getBallot()+s3;
      else if (com[0].equals("operations")) {
        Profile p = (Profile) object;
        src = s1 + genOperations(p.getOperations(), p.getTitle(), p.getId(), false, null, "../", "") + s3;
      } else if (com[0].equals("operations-summary"))
        src = s1 + genOperationsSummary(((Profile) object).getOperations(), rd) + s3;
      else if (com[0].equals("ig.opcount"))
        src = s1 + genOpCount(((Profile) object).getOperations()) + s3;
      else if (com[0].equals("ig-toc"))
        src = s1 + genIgToc(ig) + s3;
      else if (com[0].equals("fhir-path"))
        src = s1 + "../" + s3;
      else if (com[0].equals("vscommittee"))
        src = s1 + vscommittee(resource) + s3;
      else if (com[0].equals("modifier-list"))
        src = s1 + genModifierList() + s3;
      else if (com[0].equals("missing-element-list"))
        src = s1 + genMeaningWhenMissingList() + s3;
      else if (com[0].equals("wgreport"))
        src = s1 + genWGReport() + s3;
      else if (com[0].equals("r3maps-summary"))
        src = s1 + genR3MapsSummary() + s3;
      else if (com[0].equals("wg")) {
        src = s1+(wg == null || !definitions.getWorkgroups().containsKey(wg) ?  "(No assigned work group) ("+wg+" (1))" : "<a _target=\"blank\" href=\""+definitions.getWorkgroups().get(wg).getUrl()+"\">"+definitions.getWorkgroups().get(wg).getName()+"</a> Work Group")+s3;
      } else if (com[0].equals("profile-context"))
        src = s1+getProfileContext((MetadataResource) resource, genlevel(level))+s3;
      else if (com[0].equals("res-list-maturity"))
        src = s1+buildResListByMaturity()+s3;
      else if (com[0].equals("res-list-security"))
        src = s1+buildResListBySecurity()+s3;
      else if (com[0].equals("res-list-fmg"))
        src = s1+buildResListByFMG()+s3;
      else if (com[0].equals("res-list-ballot"))
        src = s1+buildResListByBallot()+s3;
      else if (com[0].equals("res-list-committee"))
        src = s1+buildResListByCommittee()+s3;
      else if (com[0].equals("wglist"))
        src = s1+buildCommitteeList()+s3;
      else if (com[0].equals("opName"))
        src = s1+((Operation) object).getName()+s3;
      else if (com[0].equals("rName"))
        src = s1+rd.getName()+s3;
      else if (com[0].equals("operation-summary"))
        src = s1+((Operation) object).getName()+" summary"+s3;
      else if (com[0].equals("structure-list-index"))
        src = s1+genStructureList()+s3;
      else if (com[0].equals("extension-type-list"))
        src = s1+genExtensionTypeList()+s3;
      else if (com[0].equals("best-practice-list"))
        src = s1+genBestPracticeList()+s3;
      else if (com[0].equals("wildcard-type-list"))
        src = s1+genWildcardTypeList()+s3;
      else if (com[0].startsWith("GF#"))
        src = s1+"<a href=\"https://gforge.hl7.org/gf/project/fhir/tracker/?action=TrackerItemEdit&amp;tracker_item_id="+com[0].substring(3)+"\">"+com[0]+"</a>"+s3;      
      else if (com[0].startsWith("GFT#"))
        src = s1+"<a href=\"https://gforge.hl7.org/gf/project/fhir/tracker/?action=TrackerItemEdit&amp;tracker_item_id="+com[0].substring(3)+"\">Task</a>"+s3;      
      else if (com[0].equals("operation")) {
        Operation op = (Operation) object;
        src = s1+genOperation(op, rd.getName(), rd.getName().toLowerCase(), false, rd.getStatus(), genlevel(level), rd.getNormativePackage())+s3;
      } else if (com[0].equals("past-narrative-link")) {
       if (object == null || !(object instanceof Boolean))  
         src = s1 + s3;
       else
         src = s1 + "<p><a href=\"#DomainResource.text.div-end\">Jump past Narrative</a></p>" + s3;
      } else if (others != null && others.containsKey(s2))
        src = s1+others.get(s2)+s3;
      else  if (com[0].equals("canonical-resources")) 
        src = s1+listCanonicalResources()+s3;
      else if (com[0].equals("special-search-parameters")) { 
        src = s1+listSpecialParameters()+s3;
      } else if (com[0].equals("diff-links-all")) { 
        src = s1+genDiffLinks()+s3;
      } else if (macros.containsKey(com[0])) {
        src = s1+macros.get(com[0])+s3;
      } else
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
    }
    return src;
  }

  private String buildOpReferenceList(String rt) {
    StringBuilder b = new StringBuilder();
    b.append("<ul>\r\n");
    for (String rn : definitions.sortedResourceNames()) {
      ResourceDefn rd = definitions.getResources().get(rn);
      for (Operation op : rd.getOperations()) {
        boolean ok = false;
        for (OperationExample ex : op.getExamples()) {
          if (ex.getContent().contains("&lt;"+rt) || ex.getContent().contains("\""+rt+"\""))
            ok = true;
        }
        for (OperationExample ex : op.getExamples2()) {
          if (ex.getContent().contains("<"+rt) || ex.getContent().contains("\""+rt+"\""))
            ok = true;
        }
        if (ok) {
          b.append(" <li><a href=\""+rn.toLowerCase()+"-operation-"+op.getName().toLowerCase()+".html#examples\">"+rn+"/$"+op.getName()+"</a></li>\r\n");
        }
      }
    }
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String genDiffLinks() {
    StringBuilder b = new StringBuilder();
    b.append("<ul>\r\n");
    pageDiffLinks(b, "resourcelist");
    pageDiffLinks(b, "lifecycle");
    pageDiffLinks(b, "compartmentdefinition");
    pageDiffLinks(b, "rdf");
    pageDiffLinks(b, "terminologies-systems");
    pageDiffLinks(b, "mappings");
    pageDiffLinks(b, "versioning");
    pageDiffLinks(b, "history");
    pageDiffLinks(b, "diff");
    pageDiffLinks(b, "r3maps");
    pageDiffLinks(b, "overview");
    pageDiffLinks(b, "overview-dev");
    pageDiffLinks(b, "overview-clinical");
    pageDiffLinks(b, "overview-arch");
    pageDiffLinks(b, "summary");
    pageDiffLinks(b, "help");
    pageDiffLinks(b, "license");
    pageDiffLinks(b, "credits");
    pageDiffLinks(b, "todo");
    pageDiffLinks(b, "change");
    pageDiffLinks(b, "async");
    pageDiffLinks(b, "graphql");
    pageDiffLinks(b, "documents");
    pageDiffLinks(b, "messaging");
    pageDiffLinks(b, "services");
    pageDiffLinks(b, "storage");
    pageDiffLinks(b, "dosage");
    pageDiffLinks(b, "fivews");
    pageDiffLinks(b, "event");
    pageDiffLinks(b, "request");
    pageDiffLinks(b, "definition");
    pageDiffLinks(b, "downloads");
    pageDiffLinks(b, "versioning");
    pageDiffLinks(b, "validation");
    pageDiffLinks(b, "best-practices");
    pageDiffLinks(b, "mapping-language");
    pageDiffLinks(b, "testing");
    pageDiffLinks(b, "security");
    pageDiffLinks(b, "safety");
    pageDiffLinks(b, "managing");
    pageDiffLinks(b, "resourceguide");
    pageDiffLinks(b, "languages");
    pageDiffLinks(b, "updates");
    pageDiffLinks(b, "pushpull");
    pageDiffLinks(b, "integrated-examples");
    pageDiffLinks(b, "usecases");
    pageDiffLinks(b, "comparison-v2");
    pageDiffLinks(b, "comparison-v3");
    pageDiffLinks(b, "comparison-cda");
    pageDiffLinks(b, "comparison-other");
    for (String n : definitions.sortedResourceNames()) {
      resourceDiffLinks(b, n);
    }
    
    b.append("</ul>\r\n");
    return b.toString();
  }

  private void resourceDiffLinks(StringBuilder b, String n) {
    b.append("<li>");
    b.append("<a href=\""+n.toLowerCase()+".html\">"+n+"</a> <a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+n.toLowerCase()+".html&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+n.toLowerCase()+".html\" no-external=\"true\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a>");
    b.append(", <a href=\""+n.toLowerCase()+"-definitions.html\">Definitions</a> <a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+n.toLowerCase()+"-definitions.html&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+n.toLowerCase()+"-definitions.html\" no-external=\"true\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a>");
    b.append(", <a href=\""+n.toLowerCase()+"-operations.html\">Operations</a> <a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+n.toLowerCase()+"-operations.html&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+n.toLowerCase()+"-operations.html\" no-external=\"true\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a>");
    b.append("</li>\r\n");    
  }

  private void pageDiffLinks(StringBuilder b, String p) {
    b.append("<li><a href=\""+p+".html\">"+p+"</a> <a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+p+".html&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+p+".html\" no-external=\"true\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a></li>\r\n");    
  }

  private String listSpecialParameters() throws FHIRException {
    StringBuilder b = new StringBuilder();
    for (String rn : definitions.sortedResourceNames()) {
      ResourceDefn rd = definitions.getResourceByName(rn);
      for (SearchParameterDefn spd : rd.getSearchParams().values()) {
        if (spd.getType() == SearchType.special) {
          b.append(" <li><code>"+spd.getCode()+"</code> on <a href=\""+rn.toLowerCase()+".html#search\">"+rn+"</a></li>\r\n");
        }
      }
    }
    return b.toString();
  }

  private String buildShortParameterList(String param) throws Exception {
    String[] p1 = param.split("\\:");
    String[] op = p1[0].split("\\/");
    String[] p = p1[1].split("\\,");

    ResourceDefn rd = definitions.getResourceByName(op[0]);
    Operation od = rd.getOperationByName(op[1].substring(1));
    
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    for (OperationParameter pd : od.getParameters()) {
      if (Utilities.existsInList(pd.getName(), p))
        b.append("<tr><td><code>"+pd.getName()+"</code></td><td>"+processMarkdown("short param list", pd.getDoc(), "")+"</td></tr>\r\n");
    }
    b.append("</table>\r\n");
    
    return b.toString();
  }

  private String genBestPracticeList() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    for (ResourceDefn r : definitions.getBaseResources().values())
      genBestPracticeList(b, r);
    for (String s : definitions.sortedResourceNames())
      genBestPracticeList(b, definitions.getResourceByName(s));
    b.append("</table>\r\n");
    return b.toString();
  }

  private void genBestPracticeList(StringBuilder b, ResourceDefn r) throws Exception {
    StringBuilder b1 = new StringBuilder();
    genBestPracticeList(b1, r.getRoot());
    if (b1.length() > 0) {
      b.append("<tr>");
      b.append("<td colspan=\"3\">");
      b.append("<b><a href=\"");
      b.append(r.getName().toLowerCase());
      b.append(".html\">");
      b.append(r.getName());
      b.append("</a></b>");
      b.append("</td>");
      b.append("</tr>\r\n");
      b.append(b1.toString());
    }
  }

  private void genBestPracticeList(StringBuilder b, ElementDefn e) throws Exception {
    for (Invariant inv : e.getInvariants().values()) {
      if ("best-practice".equals(inv.getSeverity())) {
        b.append("<tr>");
        b.append("<td>");
        b.append(inv.getId());
        b.append("</td>");
        b.append("<td>");
        b.append(inv.getEnglish());
        b.append("</td>");
        b.append("<td>");
        b.append(processMarkdown("best-practice-list", inv.getExplanation(), ""));
        b.append("</td>");
        b.append("</tr>\r\n");
      }
    }
    for (ElementDefn c : e.getElements()) {
      genBestPracticeList(b, c);
    }
  }

  private String buildCircularReferenceList(Boolean hierarchy) {
    StringBuilder b = new StringBuilder();
    
    for (String s : sorted(definitions.getResources().keySet())) {
      ResourceDefn t = definitions.getResources().get(s);
      buildCircularReferenceList(b, s, t.getRoot(), t, hierarchy);
    }

    return b.toString();
  }

  private void buildCircularReferenceList(StringBuilder b, String s, ElementDefn t, ResourceDefn rd, Boolean hierarchy) {
    if (t.getHierarchy() == hierarchy)
      for (TypeRef tr : t.getTypes()) {
        if (tr.getName().equals("Reference") || tr.getName().equals("canonical") )
          for (String p : tr.getParams()) { 
            if (s.equals(p)) {
              b.append("<li><a href=\""+s.toLowerCase()+"-definitions.html#"+t.getPath()+"\">");
              b.append(t.getPath());
              b.append("</a>");
              boolean first = true;
              for (String spn : sorted(rd.getSearchParams().keySet())) {
                SearchParameterDefn sp = rd.getSearchParams().get(spn);
                if (pointsAtElement(sp, t)) {
                  if (first) {
                    b.append(" (");
                    first = false;
                  } else
                    b.append(",");
                  b.append(sp.getCode());
                }
              } 
              if (!first) 
                b.append(")");
              b.append("</li>");
            }
          }
      }
    for (ElementDefn e : t.getElements())
      buildCircularReferenceList(b, s, e, rd, hierarchy);    
  }

  
  private boolean pointsAtElement(SearchParameterDefn sp, ElementDefn t) {
    if (sp.getType() != SearchType.reference)
      return false;
    for (String s : sp.getPaths()) {
      if (s.equals(t.getPath()))
        return true;
    }
    return false;
  }

  private void buildChoiceElementList(StringBuilder b, String s, ElementDefn t) throws Exception {
    if (t.getName().contains("[x]")) {
      if (b.length() > 0)
        b.append(",\r\n");
      b.append("    ");
      b.append("\""+t.getPath()+"\": [");
      boolean first = true;
      for (TypeRef tt : t.getTypes()) {
        if (first)
          first = false;
        else
          b.append(", ");
        b.append("\"");
        b.append(tt.getName());
        b.append("\"");
      }
      b.append("]");
    }
    for (ElementDefn e : t.getElements())
      buildChoiceElementList(b, s, e);    
  }

  private String getStandardsStatusNote(String prefix, String value, String type, String pack) throws FHIRException {
    if (pack == null)
      pack = "infrastructure";
    StandardsStatus ss = StandardsStatus.fromCode(value);
    switch (ss) {
    case TRIAL_USE:
      return "<p style=\"border: 1px black solid; background-color: "+ss.getColor()+"; padding: 5px\">\r\n" + 
      "Normative Candidate Note: This "+type.replace("_", " ")+" is not normative - it is still undergoing Trial Use while more experience is gathered.\r\n" + 
      "</p>\r\n";
    case NORMATIVE:
      return ansiNote("This "+type.replace("_", " ")+" has", pack, "");
//        return "<p style=\"border: 1px black solid; background-color: "+ss.getColor()+"; padding: 5px\">\r\n" + 
//          "ANSI Note: This "+type.replace("_", " ")+" is normative content as part of the overall resource for R4 in the <a href=\""+prefix+"ballot-intro.html#"+pack+"\">"+Utilities.capitalize(pack)+" Package</a>.\r\n" + 
//          "</p>\r\n";
    }
    throw new Error("Not done yet");
  }

  private String produceDataTypeTx(String dtname) throws Exception {
    
    TypeDefn dt = definitions.getElementDefn(dtname);
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    
    TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(bs, this); 
    tgen.setNoHeader(true);
    tgen.generate("", dt);
    tgen.close();
    return new String(bs.toByteArray());

  }

  private String getNormativeList(String genlevel, String name) {
    Map<String, PageInfo> map = normativePackages.get(name);
    if (map.size() == 0) {
      return "<p>No Content Yet</p>";
    } else {
      StringBuilder b = new StringBuilder();
      b.append("<div style=\"border: 1px grey solid; padding: 5px\">\r\n");
//      b.append("<ul style=\"column-count: 3\">\r\n");
//      for (String s : sorted(map.keySet())) {
//        b.append("  <li><a href=\""+s+"\">"+map.get(s)+"</a></li>\r\n");
//      }    
//      b.append("</ul></div>\r\n");
      b.append("<table class=\"none\"><tr>\r\n"); 
      // pages, resources, operations, value sets, code systems
      normativeCell(b, map, PageInfoType.PAGE);
      normativeCell(b, map, PageInfoType.RESOURCE);
      normativeCell(b, map, PageInfoType.OPERATION);
      normativeCell(b, map, PageInfoType.VALUESET);
      normativeCell(b, map, PageInfoType.CODESYSTEM);
      b.append("</tr></table></div>\r\n");
      return b.toString();
    }
  }

  private void normativeCell(StringBuilder b, Map<String, PageInfo> map, PageInfoType type) {
    List<PageInfo> list = new ArrayList<PageInfo>();
    for (String s : sorted(map.keySet())) {
      PageInfo p = map.get(s);
      if (p.type == type) {
        list.add(p);
      }
    }
    if (list.size() > 0) {
      b.append("<td>");
      b.append("<p><b>"+Utilities.capitalize(Utilities.pluralize(type.toCode(), list.size()))+"</b></p>");
      b.append("<ul>");
      for (PageInfo p : list) {
        String s = p.getTitle();
        int i = s.toLowerCase().indexOf(type.toCode()+" ");
        if (i > -1)
          s = s.substring(0, i)+s.substring(i+type.toCode().length()+1);
      
        String pn = p.getPage();
        String pnd = Utilities.changeFileExt(pn, "-definitions.html");

        if (pageExists(pnd))
          b.append("  <li><a href=\""+pn+"\">"+s+"</a> "+
              "<a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F"+          pn+"&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+pn+"\" no-external=\"true\" title=\"Difference to R3\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #FBF8D5; padding: 2px 2px 2px 2px\">&Delta;R</a>  "+
              "<a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+pn+"&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+pn+"\" no-external=\"true\" title=\"Difference to last ballot\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a>\r\n"+
              "<br/>+ <a href=\""+pnd+"\">Defns</a>: <a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F"+ pnd+"&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+pnd+"\" no-external=\"true\" title=\"Difference to R3\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #FBF8D5; padding: 2px 2px 2px 2px\">&Delta;R</a>  "+
              "<a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+pnd+"&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+pnd+"\" no-external=\"true\" title=\"Difference to last ballot\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a></li>\r\n");
        else
          b.append("  <li><a href=\""+pn+"\">"+s+"</a> "+
            "<a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F"+          pn+"&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+pn+"\" no-external=\"true\" title=\"Difference to R3\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #FBF8D5; padding: 2px 2px 2px 2px\">&Delta;R</a>  "+
            "<a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+pn+"&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+pn+"\" no-external=\"true\" title=\"Difference to last ballot\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a></li>\r\n");
      }
      b.append("</ul>");      
      b.append("</td>\r\n");
      //         src = s1+"<a href=\"\">diff</a>"+s3;

    }
  }

  private boolean pageExists(String pnd) {
    try {
      return new File(Utilities.path(folders.dstDir, pnd)).exists();
    } catch (IOException e) {
      return false;
    }
  }

  private String ansiNote(String statusDesc, String pack, String genlevel) {
    return ansiNote(statusDesc, pack, genlevel, null);
  }
  
  private String ansiNote(String statusDesc, String pack, String genlevel, String suffix) {
    return
      "<table class=\"none\" style=\"max-width: 790px; padding: 4px; border: 1px silver solid; background-color: #efffef\">\r\n"+
      " <tr>\r\n"+
      "  <td>\r\n"+
      "   <img src=\""+genlevel+"assets/images/ansi-approved.gif\" width=\"55\" height=\"35\"/>\r\n"+ // 
      "  </td>\r\n"+
      "  <td style=\"vertical-align: middle\">\r\n"+
      "   "+statusDesc+" been approved as part of an <a href=\"https://www.ansi.org/\">ANSI</a> standard.\r\n"+  
      "   See the <a href=\""+genlevel+"ansi-"+pack+".html\">"+Utilities.capitalize(pack)+"</a> Package for further details.\r\n"+ (Utilities.noString(suffix) ? "" : suffix)+
      "  </td>\r\n"+
      " </tr>\r\n"+
      "</table>\r\n";
  }
  
  private String getNormativeNote(String genlevel, String pack, String type, String title, String filename) throws Exception {
    if (pack == null)
      pack = "infrastructure";
    if (!filename.contains("-definitions")) {
      Map<String, PageInfo> map = normativePackages.get(pack);
      if (map == null) {
        normativePages.add(filename);
        return ansiNote("This page has", pack, genlevel); 
      }
      map.put(filename, new PageInfo(PageInfoType.fromCode(type), filename, title));
    }
    return ansiNote("This page has", pack, genlevel); 
//          "<p style=\"border: 1px black solid; background-color: #e6ffe6; padding: 5px\">\r\n" + 
//        "ANSI <a href=\""+genlevel+"ansi-"+pack+".html\">"+Utilities.capitalize(pack)+" </a>.\r\n" + 
//        "</p>\r\n" + 
//        "";
  }

  private String getMixedNormativeNote(String genlevel, String pack, String type, String title, String filename) throws Exception {
    if (!filename.contains("-definitions") && !filename.contains("-operations")) {
      Map<String, PageInfo> map = normativePackages.get(pack);
      if (map == null) {
        normativePages.add(filename);
        return "";
      }
      map.put(filename, new PageInfo(PageInfoType.fromCode(type), filename,  title));
    }
    return ansiNote("Some of the content on this page (marked clearly) has", pack, genlevel); 
//        "<p style=\"border: 1px black solid; background-color: #e6ffe6; padding: 5px\">\r\n" + 
//        "ANSI Note: Some of the content on this page (marked clearly) is normative content in the <a href=\""+genlevel+"ansi-"+pack+".html\">"+Utilities.capitalize(pack)+" Package</a>.\r\n" + 
//        "</p>\r\n" + 
//        "";
  }

  private String getMostlyNormativeNote(String genlevel, String pack, String type, String title, String filename) throws Exception {
    if (!filename.contains("-definitions") && !filename.contains("-operations")) {
      Map<String, PageInfo> map = normativePackages.get(pack);
      if (map == null) {
        normativePages.add(filename);
        return "";
      }
      map.put(filename, new PageInfo(PageInfoType.fromCode(type), filename,  title));
    }
    return ansiNote("Most of the content on this page has", pack, genlevel, "The few parts of this page that are not normative are clearly marked"); 
//    return "<p style=\"border: 1px black solid; background-color: #e6ffe6; padding: 5px\">\r\n" + 
//        "ANSI Note: Most of the content on this page is normative content in the <a href=\""+genlevel+"ansi-"+pack+".html\">"+Utilities.capitalize(pack)+" Package</a>.\r\n" + 
//        "Once normative, it will lose it's Maturity Level, and <a href=\"versions.html#change\">breaking changes</a> will no longer be made. \r\n" + 
//        "</p>\r\n" + 
//        "";
  }

  private String buildResListByFMG() throws FHIRException {
    List<String> res = new ArrayList<String>();
    for (ResourceDefn rd : definitions.getBaseResources().values())
      res.add(rd.getName());
    for (ResourceDefn rd : definitions.getResources().values())
      res.add(rd.getName());
    Collections.sort(res);
    
    StringBuilder b = new StringBuilder();
    listByApprovalStatus(b, res, FMGApproval.NOPROPOSAL, "Not yet proposed");
    listByApprovalStatus(b, res, FMGApproval.PENDING, "Pending");
    listByApprovalStatus(b, res, FMGApproval.APPROVED, "Approved");
    return b.toString();
  }

  private void listByApprovalStatus(StringBuilder b, List<String> res, FMGApproval state, String title) throws FHIRException {
    b.append("<p><b>"+title+"</b></p>\r\n");
    b.append("<ul style=\"width: 90%; -moz-column-count: 4; -moz-column-gap: 10px; -webkit-column-count: 4; -webkit-column-gap: 10px; column-count: 4; column-gap: 10px\">\r\n");
    for (String rn : res) {
      ResourceDefn rd = definitions.getResourceByName(rn);
      if (rd.getApproval() == state)
        if (rd.getNormativePackage() != null || rd.getNormativeVersion() != null)
          b.append("  <li><a title=\"[%resdesc "+rn+"%]\" href=\""+rn.toLowerCase()+".html\">"+rn+"</a> <a href=\"versions.html#std-process\"  title=\"Normative Content\" class=\"normative-flag\">N</a></li>\r\n");
        else
          b.append("  <li><a title=\"[%resdesc "+rn+"%]\" href=\""+rn.toLowerCase()+".html\">"+rn+"</a></li>\r\n");
    }
    b.append("</ul>\r\n");
  }

  private String buildResListByMaturity() throws FHIRException {
    List<String> res = new ArrayList<String>();
    for (ResourceDefn rd : definitions.getBaseResources().values())
      res.add(rd.getFmmLevel()+":" +rd.getName());
    for (ResourceDefn rd : definitions.getResources().values())
      res.add(rd.getFmmLevel()+":" +rd.getName());
    Collections.sort(res);
    
    StringBuilder b = new StringBuilder();
    for (int i = 5; i >= 0; i--) {
      b.append("<p><b>Level ");
      b.append(i);
      b.append("</b></p>\r\n<ul style=\"width: 90%; -moz-column-count: 4; -moz-column-gap: 10px; -webkit-column-count: 4; -webkit-column-gap: 10px; column-count: 4; column-gap: 10px\">\r\n");
      for (String rn : res) {
        if (rn.startsWith(Integer.toString(i))) {
          String r = rn.substring(2);
          ResourceDefn rd = definitions.getResourceByName(r);
          if (rd.getNormativePackage() != null || rd.getNormativeVersion() != null)
            b.append("  <li><a title=\"[%resdesc "+r+"%]\" href=\""+r.toLowerCase()+".html\">"+r+"</a> <a href=\"versions.html#std-process\"  title=\"Normative Content\" class=\"normative-flag\">N</a></li>\r\n");
          else
            b.append("  <li><a title=\"[%resdesc "+r+"%]\" href=\""+r.toLowerCase()+".html\">"+r+"</a></li>\r\n");
        }
      }
      b.append("</ul>\r\n");
    }
    return b.toString();
  }

  private String buildResListBySecurity() throws FHIRException {
    List<String> res = new ArrayList<String>();
    for (ResourceDefn rd : definitions.getResources().values())
      res.add((rd.getSecurityCategorization() == null ? "9" : rd.getSecurityCategorization().toIndex())+":" +rd.getName());
    Collections.sort(res);
    
    StringBuilder b = new StringBuilder();
    for (int i = 0; i <= 5; i++) {
      b.append("<p><b>"+secCategory(i));
      b.append("</b></p>\r\n<ul style=\"width: 90%; -moz-column-count: 4; -moz-column-gap: 10px; -webkit-column-count: 4; -webkit-column-gap: 10px; column-count: 4; column-gap: 10px\">\r\n");
      for (String rn : res) {
        if (rn.startsWith(Integer.toString(i))) {
          String r = rn.substring(2);
          ResourceDefn rd = definitions.getResourceByName(r);
          if (rd.getNormativePackage() != null || rd.getNormativeVersion() != null)
            b.append("  <li><a title=\"[%resdesc "+r+"%]\" href=\""+r.toLowerCase()+".html\">"+r+"</a> <a href=\"versions.html#std-process\"  title=\"Normative Content\" class=\"normative-flag\">N</a></li>\r\n");
          else
            b.append("  <li><a title=\"[%resdesc "+r+"%]\" href=\""+r.toLowerCase()+".html\">"+r+"</a></li>\r\n");
        }
      }
      b.append("</ul>\r\n");
    }
    return b.toString();
  }

  private String secCategory(int i) {
    switch (i) {
    case 0: return "Anonymous";
    case 1: return "Business";
    case 2: return "Individual";
    case 3: return "Patient";
    case 4: return "Not Classified";
    case 5: return "Not Applicable";
    default: return "??";
    }
  }

  private String buildResListByBallot() throws FHIRException {
    List<String> res = new ArrayList<String>();
    for (ResourceDefn rd : definitions.getBaseResources().values())
      res.add(rd.getName());
    for (ResourceDefn rd : definitions.getResources().values())
      res.add(rd.getName());
    Collections.sort(res);
    
    StringBuilder b = new StringBuilder();
    StandardsStatus[] values = StandardsStatus.values();
    for (int i = values.length - 1; i >= 0; i--) {
      StandardsStatus next = values[i];
      boolean first = true;
      for (String rn : res) {
        ResourceDefn rd = definitions.getResourceByName(rn);
        if (rd.getStatus() == next) {
          if (first) {
            b.append("<p><b>");
            b.append(next.toDisplay());
            b.append("</b></p>\r\n<ul style=\"width: 90%; -moz-column-count: 4; -moz-column-gap: 10px; -webkit-column-count: 4; -webkit-column-gap: 10px; column-count: 4; column-gap: 10px\">\r\n");
            first = false;
          }
          if (rd.getNormativePackage() != null || rd.getNormativeVersion() != null)
            b.append("  <li><a title=\"[%resdesc "+rn+"%]\" href=\""+rn.toLowerCase()+".html\">"+rn+"</a> <a href=\"versions.html#std-process\" title=\"Normative Content\" class=\"normative-flag\">N</a></li>\r\n");
          else
            b.append("  <li><a title=\"[%resdesc "+rn+"%]\" href=\""+rn.toLowerCase()+".html\">"+rn+"</a></li>\r\n");
        }
      }
      if (!first)
        b.append("</ul>\r\n");
    }
    return b.toString();
  }

  private String buildResListByCommittee() {
    List<String> res = new ArrayList<String>();
    for (ResourceDefn rd : definitions.getBaseResources().values()) 
      res.add(rd.getWg().getName()+":" +rd.getName());
    for (ResourceDefn rd : definitions.getResources().values())
      res.add(rd.getWg().getName()+":" +rd.getName());
    Collections.sort(res);

    StringBuilder b = new StringBuilder();
    for (String s : sorted(definitions.getWorkgroups().keySet())) {
      WorkGroup wg = definitions.getWorkgroups().get(s);
      boolean first = true;
      for (String rn : res) {
        if (rn.startsWith(wg.getName()+":")) {

          if (first) {
            b.append("<p><b>");
            b.append(Utilities.escapeXml(wg.getName()));
            b.append("</b></p>\r\n<ul style=\"width: 90%; -moz-column-count: 4; -moz-column-gap: 10px; -webkit-column-count: 4; -webkit-column-gap: 10px; column-count: 4; column-gap: 10px\">\r\n");
            first = false;
          }

          String r = rn.substring(rn.indexOf(":")+1);
          b.append("  <li><a title=\"[%resdesc "+r+"%]\" href=\""+r.toLowerCase()+".html\">"+r+" [%fmmshort "+r+"%]</a></li>\r\n");
        }
      }
      if (!first)
        b.append("</ul>\r\n");
    }
    return b.toString();
  }

  private String buildCommitteeList() {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (String s : sorted(definitions.getWorkgroups().keySet())) {
      WorkGroup wg = definitions.getWorkgroups().get(s);
      if (first) 
        first = false;
      else
        b.append(", ");
      b.append("<a href=\"");
      b.append(wg.getUrl());
      b.append("\">");
      b.append(Utilities.escapeXml(wg.getName()));
      b.append("</a>");
    }
    return b.toString();
  }

  private WorkGroup wg(String code) {
    return definitions.getWorkgroups().get(code);
  }

  private String dtR3R4Transform(String name) throws Exception {

    File f = new File(Utilities.path(folders.rootDir, "implementations", "r3maps", "R4toR3", name+".map"));
    if (!f.exists())
       throw new Exception("No R3/R4 map exists for "+name);
    String n = name.toLowerCase();
    String status = r3r4StatusForResource(name);
    String fwds = TextFile.fileToString(Utilities.path(folders.rootDir, "implementations", "r3maps", "R3toR4",  r3nameForResource(name)+".map"));
    String bcks = TextFile.fileToString(Utilities.path(folders.rootDir, "implementations", "r3maps", "R4toR3", name+".map"));
    String fwdsStatus =  "";
    String bcksStatus =  "";
    try {
      new StructureMapUtilities(workerContext).parse(fwds, r3nameForResource(name)+".map");
    } catch (FHIRException e) {
      fwdsStatus = "<p style=\"background-color: #ffb3b3; border:1px solid maroon; padding: 5px;\">This script does not compile: "+e.getMessage()+"</p>\r\n";
    }
    try {
      new StructureMapUtilities(workerContext).parse(bcks, name+".map");
    } catch (FHIRException e) {
      bcksStatus = "<p style=\"background-color: #ffb3b3; border:1px solid maroon; padding: 5px;\">This script does not compile: "+e.getMessage()+"</p>\r\n";
    }
    return "<p>Functional status for this map: "+status+" (based on R2 -> R3 -> R2 round tripping)</p>\r\n"+
    "\r\n"+
    "<h4>R3 to R4</h4>\r\n"+
    "\r\n"+
    "<div class=\"mapping\">\r\n"+
    "<pre>\r\n"+
    Utilities.escapeXml(fwds)+"\r\n"+
    "</pre>\r\n"+
    "</div>\r\n"+
    "\r\n"+
    fwdsStatus+"\r\n"+
    "\r\n"+
    "<h4>R4 to R3</h4>\r\n"+
    "\r\n"+
    "<div class=\"mapping\">\r\n"+
    "<pre>\r\n"+
    Utilities.escapeXml(bcks)+"\r\n"+
    "</pre>\r\n"+
    "</div>\r\n"+
    "\r\n"+
    bcksStatus+"\r\n";
  }

  public String r3nameForResource(String name) {
    return name;
  }

  private String genWGReport() throws Exception {

    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append("  <tr><td><b>Resource</b></td><td>FMM</td></tr>\r\n");
    for (String n : sorted(definitions.getWorkgroups().keySet())) {
      WorkGroup wg = definitions.getWorkgroups().get(n);
      b.append(" <tr><td colspan=\"2\"><b>"+n+" ("+wg.getName()+")</b></td></tr>\r\n");
      for (String rn : definitions.sortedResourceNames()) {
        ResourceDefn r = definitions.getResourceByName(rn);
        if (r.getWg() == wg) {
          b.append("  <tr><td><a href=\""+rn.toLowerCase()+".html\">"+rn+"</a></td><td>"+r.getFmmLevel()+"</td></tr>\r\n");
        }
      }
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  public void updateDiffEngineDefinitions() {
    for (BundleEntryComponent be : typeBundle.getEntry()) {
      if (be.getResource() instanceof StructureDefinition) {
        StructureDefinition sd = (StructureDefinition) be.getResource();
        if (!diffEngine.getRevision().getTypes().containsKey(sd.getName()))
          diffEngine.getRevision().getTypes().put(sd.getName(), sd);
      }
    }
    for (BundleEntryComponent be : resourceBundle.getEntry()) {
      if (be.getResource() instanceof StructureDefinition) {
        StructureDefinition sd = (StructureDefinition) be.getResource();
        if (!diffEngine.getRevision().getResources().containsKey(sd.getName()))
          diffEngine.getRevision().getResources().put(sd.getName(), sd);
      }
    }

    for (ValueSet vs : getValueSets().values()) {
      if (!diffEngine.getRevision().getValuesets().containsKey(vs.getUrl()))
        diffEngine.getRevision().getValuesets().put(vs.getUrl(), vs);
      if (vs.getUserData(ToolResourceUtilities.NAME_VS_USE_MARKER) != null) {
        ValueSet evs = null;
        if (vs.hasUserData("expansion"))
          evs = (ValueSet) vs.getUserData("expansion");
        else {
          ValueSetExpansionOutcome vse = getWorkerContext().expandVS(vs, true, false);
          if (vse.getValueset() != null) {
            evs = vse.getValueset();
            vs.setUserData("expansion", evs);
          }
        }
        if (evs != null && !diffEngine.getRevision().getExpansions().containsKey(evs.getUrl())) {
          diffEngine.getRevision().getExpansions().put(evs.getUrl(), evs);
        }
      }
    }

  }

  private Example findExample(String rn, String id) throws Exception {
    ResourceDefn resource = definitions.getResourceByName(rn);
    for (Example e: resource.getExamples()) {
      if (id.equals(e.getId()))
        return e;
    }
    for (Profile p : resource.getConformancePackages()) {
      for (Example e: p.getExamples()) {
        if (id.equals(e.getId()))
          return e;
      }
    }
    for (Profile p : definitions.getPackList()) {
      ImplementationGuideDefn ig = definitions.getIgs().get(p.getCategory());
      for (Example e: p.getExamples()) {
        if (rn.equals(e.getResourceName()))
          if (id.equals(e.getId()))
            return e;
      }
    }
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      if (ig.getIg() != null) {
        for (ImplementationGuideDefinitionResourceComponent res : ig.getIg().getDefinition().getResource()) {
          Example e = (Example) res.getUserData(ToolResourceUtilities.NAME_RES_EXAMPLE);
          if (res.hasExample() && e != null && e.getResourceName().equals(resource.getName()))
            if (id.equals(e.getId()))
              return e;
        }
      }
    }
    return null;
  }

  private String genExample(Example example, int headerLevelContext, String genlevel) throws IOException, EOperationOutcome, FHIRException {
    String xml = XMLUtil.elementToString(example.getXml().getDocumentElement());
    Resource res = new XmlParser().parse(xml);
    if (!(res instanceof DomainResource))
      return "";
    DomainResource dr = (DomainResource) res;
    if (!dr.hasText() || !dr.getText().hasDiv())
      new NarrativeGenerator("", "", workerContext, this).setHeaderLevelContext(headerLevelContext).generate(dr, null);
    return new XhtmlComposer(XhtmlComposer.HTML).compose(dr.getText().getDiv());
  }

  private String genMappingsTable() throws IOException {
    StringBuilder b = new  StringBuilder();
    b.append("<table class=\"rows\">\r\n");
    b.append(" <tr>\r\n");
    b.append("  <th width=\"250\">Name</th>\r\n");
    b.append("  <th>Details</th>\r\n");
    b.append(" </tr>\r\n");
    for (String s : definitions.getMapTypes().keySet()) {
      MappingSpace m = definitions.getMapTypes().get(s);
      if (m.isPublish()) {
        b.append(" <tr>\r\n");
        b.append("  <td><a name=\""+m.getId()+"\"></a>");
        if (m.hasLink())
          b.append("<a href=\""+m.getLink()+"\">"+Utilities.escapeXml(m.getTitle())+"</a></td>\r\n");
        else
          b.append(Utilities.escapeXml(m.getTitle())+"</td>\r\n");
        b.append("  <td>Formal URL: "+s+ (m.getPreamble() != null ? "<br/>"+new XhtmlComposer(false, true).compose(m.getPreamble()) : "")+"</td>\r\n");
        b.append(" </tr>\r\n");
      }        
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private boolean hasExamples(StructureDefinition resource, ImplementationGuideDefn ig) {
    return false;
  }

  private String vscommittee(Resource resource) {
    MetadataResource vs = (MetadataResource) resource;
    WorkGroup wg = definitions.getWorkgroups().get(ToolingExtensions.readStringExtension(vs, ToolingExtensions.EXT_WORKGROUP));
    return wg == null ? "??" : "<a _target=\"blank\" href=\""+wg.getUrl()+"\">"+wg.getName()+"</a> Work Group";
  }

  public String genChoiceElementsJson() throws Exception {
    StringBuilder b = new StringBuilder();
    for (String s : sorted(definitions.getTypes().keySet())) {
      ElementDefn t = definitions.getTypes().get(s);
      buildChoiceElementList(b, s, t);
    }
    
    for (String s : sorted(definitions.getResources().keySet())) {
      ResourceDefn t = definitions.getResources().get(s);
      buildChoiceElementList(b, s, t.getRoot());
    }

    return "{\r\n  \"elements\" : {\r\n"+b.toString()+"\r\n  }\r\n}\r\n";
  }
  
  public String genBackboneElementsJson() throws Exception {
    List<String> classes = new ArrayList<String>();
    listAllbackboneClasses(classes);

    StringBuilder b = new StringBuilder();
    b.append("{\r\n");
    b.append("  \"elements\" : [");
    boolean first = true;
    for (String tn : sorted(definitions.getAllTypeNames())) {
      if (first)
        first = false;
      else
        b.append(",");
      b.append("\r\n");
      ElementDefn ed = definitions.getElementDefn(tn);
      b.append("    { \"class\" : \""+tn+"\", \"source\" : \""+definitions.getSrcFile(tn)+".html#"+tn+"\" }");
    }
    for (String rn : definitions.sortedResourceNames()) {
      for (String pn : classes) {
        if (pn.startsWith(rn+".")) {
          String path = pn.substring(0, pn.indexOf(":"));
          b.append(",\r\n");
          b.append("    { \"class\" : \""+path+"\", \"source\" : \""+rn.toLowerCase()+".html\" }");
        }
      }
    }
    b.append("  ]\r\n}\r\n");
    return b.toString();
  }


  private void listAllbackboneClasses(List<String> classes) {
    for (ResourceDefn r : definitions.getBaseResources().values())
      listAllbackboneClasses(classes, r.getRoot(), r.getName());
    for (ResourceDefn r : definitions.getResources().values())
      listAllbackboneClasses(classes, r.getRoot(), r.getName());
  }

  private void listAllbackboneClasses(List<String> classes, ElementDefn e, String path) {
    for (ElementDefn c : e.getElements()) {
      if (c.getElements().size() > 0) {
        String p = path+"."+c.getName();
        String n = Utilities.capitalize(c.getName());
        if (c.hasStatedType())
          n = c.getStatedType();
        classes.add(p+":"+n);
        listAllbackboneClasses(classes, c, p);
      }
    }
  }

  private String buildIgRegistry(ImplementationGuideDefn ig, String types) throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"codes\">\r\n");
    b.append("<tr><td><b>Id</b></td><td><b>Name</b></td><td><b>Description</b></td></tr>\r\n");
    // examples second:
    boolean example = false;
    while (true) {
      boolean usedPurpose = false;
      for (String type : types.split("\\,")) {
        List<String> ids = new ArrayList<String>();
        Map<String, ImplementationGuideDefinitionResourceComponent> map = new HashMap<String, ImplementationGuideDefinitionResourceComponent>();
        for (ImplementationGuideDefinitionResourceComponent r : ig.getIg().getDefinition().getResource()) {
          Resource ar = (Resource) r.getUserData(ToolResourceUtilities.RES_ACTUAL_RESOURCE);
          if (ar != null && ar.getResourceType().toString().equals(type) && r.hasExample() == example) {
            String id = ar.getId();
            ids.add(id);
            map.put(id, r);
          }
          Example ex = (Example) r.getUserData(ToolResourceUtilities.NAME_RES_EXAMPLE);
          if (ex != null && ex.getResourceName().equals(type) && r.hasExample() == example) {
            String id = ex.getId();
            ids.add(id);
            map.put(id, r);
          }
        }
        if (ids.size() > 0) {
          if (!usedPurpose) {
            b.append("<tr><td colspan=\"3\" style=\"background: #DFDFDF\"><b>"+(example ? "Specification" : "Example")+"</b> </td></tr>\r\n");
            usedPurpose = true;
          }
          Collections.sort(ids);
          b.append("<tr><td colspan=\"3\" style=\"background: #EFEFEF\">"+getTypePluralDesc(type)+"</td></tr>\r\n");
          for (String id : ids) {
            ImplementationGuideDefinitionResourceComponent r = map.get(id);
            b.append("<tr><td><a href=\""+Utilities.changeFileExt(r.getReference().getReference(), ".html")+"\">"+id+"</a></td><td>"+Utilities.escapeXml(r.getName())+"</td><td>"+Utilities.escapeXml(r.getDescription())+"</td></tr>\r\n");
          }
        }
      }
      if (example)
        break;
      else
        example = true;
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String getTypePluralDesc(String type) {
    if (type.equals("CapabilityStatement"))
      return "Capability Statements";
    return Utilities.pluralizeMe(type);
  }

  private String vsWarning(ValueSet resource) throws Exception {
    String warning = ToolingExtensions.readStringExtension(resource, "http://hl7.org/fhir/StructureDefinition/valueset-warning");
    if (Utilities.noString(warning))
      return "";
    return "<div class=\"warning\">\r\n<p><b>Note for Implementer:</b></p>"+processMarkdown("vs-warning", warning, "")+"</div>\r\n";
  }

  private String vsSpecialStatus(DomainResource resource) throws Exception {
    String note = ToolingExtensions.readStringExtension(resource, "http://hl7.org/fhir/StructureDefinition/valueset-special-status");
    if (Utilities.noString(note))
      return "";
    return "<div class=\"warning\">\r\n<p>"+processMarkdown("vsSpecialStatus", note, "")+"</p></div>\r\n";
  }

  private String fileTail(String name) {
    int i = name.lastIndexOf(File.separator);
    return name.substring(i+1);
  }

  private String getWgLink(String filename, WorkGroup wg) {
    if (wg != null) {
      definitions.page(filename).setWgCode(wg.getCode());
      return wg.getUrl() ;
    } else
      return "index.html"; // todo: fix this.
  }

  private String getWgTitle(WorkGroup wg) {
    return wg != null ? wg.getName() : "?wg?";
  }

  private String genIdentifierList() throws Exception {
    StringBuilder b = new StringBuilder();
    for (NamingSystem ns : definitions.getNamingSystems()) {
      b.append("<tr>\r\n");
      String url = getPublisherUrl(ns);
      if (url != null)
        b.append("  <td><a href=\""+url+"\">"+Utilities.escapeXml(ns.getName())+"</a></td>\r\n");
      else
        b.append("  <td>"+Utilities.escapeXml(ns.getName())+"</td>\r\n");
      String uri = getUri(ns);
      String oid = getOid(ns);
      b.append("  <td>"+Utilities.escapeXml(uri)+"</td>\r\n");
      b.append("  <td style=\"color: DarkGrey\">"+(oid == null ? "" : oid)+"</td>\r\n");
      String country = getCountry(ns);
      country = country == null ? "" : " ("+country+")";
      if (ns.hasType()) {
        Coding c = ns.getType().getCoding().get(0);
        if (c == null)
          b.append("  <td>"+Utilities.escapeXml(ns.getType().getText())+country+"</td>\r\n");
        else {
         if (c.getSystem().equals("http://hl7.org/fhir/identifier-type"))
           b.append("  <td><a href=\"valueset-identifier-type.html#"+c.getCode()+"\">"+c.getCode()+"</a>"+country+"</td>\r\n");
         else if (c.getSystem().equals("http://terminology.hl7.org/CodeSystem/v2-0203"))
           b.append("  <td><a href=\"v2/0203/index.html#"+c.getCode()+"\">"+c.getCode()+"</a>"+country+"</td>\r\n");
         else
           throw new Exception("Unknown Identifier Type System");
        }
      } else
        b.append("  <td>"+country+"</td>\r\n");
      b.append("  <td>"+processMarkdown("namingSystem.descrption",  ns.getDescription(), "")+"</td>\r\n");
      b.append("</tr>\r\n");
    }
    return b.toString();
  }

  private String getPublisherUrl(NamingSystem ns) {
    for (ContactDetail c : ns.getContact()) {
      for (ContactPoint cp : c.getTelecom()) {
        if ((cp.getSystem() == ContactPointSystem.URL || cp.getSystem() == null) && (cp.hasValue() && (cp.getValue().startsWith("http:") || cp.getValue().startsWith("https:"))))
          return cp.getValue();
      }
    }
    return null;
  }

  private String getCountry(NamingSystem ns) {
    for (CodeableConcept cc : ns.getJurisdiction()) {
      for (Coding c : cc.getCoding()) {
        if (c.getSystem().equals("urn:iso:std:iso:3166"))
          return c.hasDisplay() ? c.getDisplay() : c.getCode();
      }
    }
    return null;
  }

  private String getOid(NamingSystem ns) {
    for (NamingSystemUniqueIdComponent ui : ns.getUniqueId()) {
      if (ui.getType() == NamingSystemIdentifierType.OID && ui.hasValue())
        return ui.getValue();
    }
    return "";
  }

  private String getUri(NamingSystem ns) {
    for (NamingSystemUniqueIdComponent ui : ns.getUniqueId()) {
      if (ui.getType() == NamingSystemIdentifierType.URI && ui.hasValue())
        return ui.getValue();
    }
    return "";
  }

  private String genCompModel(StructureDefinition sd, String name, String base, String prefix) throws Exception {
    if (sd == null)
      return "<p style=\"color: maroon\">No "+name+" could be generated</p>\r\n";
    return new XhtmlComposer(XhtmlComposer.HTML).compose(new ProfileUtilities(workerContext, null, this).generateTable("??", sd, false, folders.dstDir, false, base, true, prefix, prefix, false, false, null));
  }

  private String genCmpMessages(ProfileComparison cmp) {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append("<tr><td><b>Path</b></td><td><b>Message</b></td></tr>\r\n");
    b.append("<tr><td colspan=\"2\" style=\"background: #eeeeee\">Errors Detected</td></tr>\r\n");
    boolean found = false;
    for (ValidationMessage vm : cmp.getMessages())
      if (vm.getLevel() == IssueSeverity.ERROR || vm.getLevel() == IssueSeverity.FATAL) {
        found = true;
        b.append("<tr><td>"+vm.getLocation()+"</td><td>"+vm.getHtml()+(vm.getLevel() == IssueSeverity.FATAL ? "(<span style=\"color: maroon\">This error terminated the comparison process</span>)" : "")+"</td></tr>\r\n");
      }
    if (!found)
    b.append("<tr><td colspan=\"2\">(None)</td></tr>\r\n");

    boolean first = true;
    for (ValidationMessage vm : cmp.getMessages())
      if (vm.getLevel() == IssueSeverity.WARNING) {
        if (first) {
          first = false;
          b.append("<tr><td colspan=\"2\" style=\"background: #eeeeee\">Warnings about the comparison</td></tr>\r\n");
        }
        b.append("<tr><td>"+vm.getLocation()+"</td><td>"+vm.getHtml()+"</td></tr>\r\n");
      }
    first = true;
    for (ValidationMessage vm : cmp.getMessages())
      if (vm.getLevel() == IssueSeverity.INFORMATION) {
        if (first) {
          b.append("<tr><td colspan=\"2\" style=\"background: #eeeeee\">Notes about differences (e.g. definitions)</td></tr>\r\n");
          first = false;
        }
        b.append("<tr><td>"+vm.getLocation()+"</td><td>"+vm.getHtml()+"</td></tr>\r\n");
      }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String genPCTable(ProfileComparer pc) {
    StringBuilder b = new StringBuilder();

    b.append("<table class=\"grid\">\r\n");
    b.append("<tr>");
    b.append(" <td><b>Left</b></td>");
    b.append(" <td><b>Right</b></td>");
    b.append(" <td><b>Comparison</b></td>");
    b.append(" <td><b>Error #</b></td>");
    b.append(" <td><b>Warning #</b></td>");
    b.append(" <td><b>Hint #</b></td>");
    b.append("</tr>");

    for (ProfileComparison cmp : pc.getComparisons()) {
      b.append("<tr>");
      b.append(" <td><a href=\""+cmp.getLeft().getUserString("path")+"\">"+Utilities.escapeXml(cmp.getLeft().getName())+"</a></td>");
      b.append(" <td><a href=\""+cmp.getRight().getUserString("path")+"\">"+Utilities.escapeXml(cmp.getRight().getName())+"</a></td>");
      b.append(" <td><a href=\""+pc.getId()+"."+cmp.getId()+".html\">Click Here</a></td>");
      b.append(" <td>"+cmp.getErrorCount()+"</td>");
      b.append(" <td>"+cmp.getWarningCount()+"</td>");
      b.append(" <td>"+cmp.getHintCount()+"</td>");
      b.append("</tr>");
    }
    b.append("</table>\r\n");

    return b.toString();
  }

  private String genPCLink(String leftName, String leftLink) {
    return "<a href=\""+leftLink+"\">"+Utilities.escapeXml(leftName)+"</a>";
  }

  private String genScList(String path) throws Exception {
    ResourceDefn r = definitions.getResourceByName(path.substring(0, path.indexOf(".")));
    if (r == null)
      throw new Exception("Unable to process sclist (1): "+path);
    ElementDefn e = r.getRoot().getElementByName(definitions, path.substring(path.indexOf(".")+1), true, false);
    if (e == null)
      throw new Exception("Unable to process sclist (2): "+path);
    if (e.typeCode().equals("boolean"))
      return "true | false";
    else {
      StringBuilder b = new StringBuilder();
      boolean first = true;
      for (ConceptSetComponent inc : e.getBinding().getValueSet().getCompose().getInclude()) {
        CodeSystem cs = definitions.getCodeSystems().get(inc.getSystem());
        if (cs != null) {
          for (ConceptDefinitionComponent cc : cs.getConcept()) {
            if (first)
              first = false;
            else
              b.append(" | ");
            b.append("<span title=\""+cc.getDisplay()+": "+Utilities.escapeXml(cc.getDefinition())+"\">"+cc.getCode()+"</span>");
          }
        }
      }
      return b.toString();
    }
  }

  private String txsummary(ValueSet vs, String prefix) throws Exception {
    String c = "";
    if (vs.hasCopyright())
      c = "<tr><td>Copyright:</td><td>"+processMarkdown("vs.copyright", vs.getCopyright(), prefix)+"</td></tr>\r\n";
    return c;
  }

  private String txsummary(CodeSystem vs, String prefix) throws Exception {
    String c = "";
    if (vs.hasCopyright())
      c = "<tr><td>Copyright:</td><td>"+processMarkdown("cs.copyright", vs.getCopyright(), prefix)+"</td></tr>\r\n";
    return c;
  }

  private String genExampleProfileLink(Resource resource) throws FHIRException {
    if (resource == null || !(resource instanceof StructureDefinition))
      return "";
    StructureDefinition sd = (StructureDefinition) resource;
    if (!sd.hasBaseDefinition())
      return "";
    String pack = "";
    if (sd.hasUserData("pack")) {
      Profile p = (Profile) sd.getUserData("pack");
      ImplementationGuideDefn ig = definitions.getIgs().get(p.getCategory());
      if (Utilities.noString(ig.getHomePage()))
        pack = " ("+ig.getName()+"))";
      else
        pack = " (<a href=\""+ig.getHomePage()+"\">"+ig.getName()+"</a>)";
      if (!p.getTitle().equals(sd.getName()))
        pack = " in <a href=\""+p.getId()+".html\">"+p.getTitle()+"</a> "+pack;
    }
    if (sd.hasUserData("path"))
      return "This example conforms to the <a href=\""+sd.getUserData("path")+"\">profile "+(sd.getName())+"</a>"+pack+".";
    else
      return "This example conforms to the <a href=\""+sd.getId().toLowerCase()+".html\">profile "+(sd.getName())+"</a>"+pack+".";
  }

  private String umlForDt(String dt, String id) throws Exception {
    if ("Timing".equals(dt))
      dt = dt+",BackboneElement";
      
    File tmp = Utilities.createTempFile("tmp", ".tmp");
    tmp.deleteOnExit();
    try {
      String s = "\r\n[diagram]\r\n"+
          "classes="+dt+"\r\n"+
          "element-attributes=true\r\n";
      TextFile.stringToFileNoPrefix(s, tmp.getAbsolutePath());
      return new SvgGenerator(this, "", null, false, true).generate(tmp.getAbsolutePath(), id);
    } finally {
      tmp.delete();
    }
  }

  private String genExtensionsTable() throws Exception {
    StringBuilder s = new StringBuilder();

    s.append("<table class=\"list\">\r\n");
    s.append("<tr>");
    s.append("<td><b>Identity</b></td>");
    s.append("<td><b><a href=\"defining-extensions.html#cardinality\">Conf.</a></b></td>");
    s.append("<td><b>Type</b></td>");
    s.append("<td><b><a href=\"defining-extensions.html#context\">Context</a></b></td>");
    s.append("<td><b><a href=\"versions.html#maturity\">FMM</a></b></td>");
    s.append("</tr>");

    List<String> names = new ArrayList<String>();
    for (StructureDefinition sd : workerContext.getExtensionDefinitions())
      names.add(sd.getUrl());
    Collections.sort(names);
    Set<StructureDefinition> processed = new HashSet<StructureDefinition>();
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      if (ig.isCore()) {
        for (String n : names) {
          StructureDefinition ed = workerContext.fetchResource(StructureDefinition.class, n);
          if (!processed.contains(ed)) {
            processed.add(ed);
            if (ig.getCode().equals(ToolResourceUtilities.getUsage(ed))) {
              genExtensionRow(ig, s, ed);
            }
          }
        }
      }
    }
    s.append("</table>\r\n");
    return s.toString();
  }

  private void genExtensionRow(ImplementationGuideDefn ig, StringBuilder s, StructureDefinition ed) throws Exception {
    s.append("<tr>");
    s.append("<td><a href=\""+ed.getUserString("path")+"\" title=\""+Utilities.escapeXml(ed.getDescription())+"\">"+ed.getId()+"</a></td>");
    s.append("<td>"+displayExtensionCardinality(ed)+"</td>");
    s.append("<td>"+determineExtensionType(ed)+"</td>");
    s.append("<td>");
    boolean first = true;
    for (StructureDefinitionContextComponent ec : ed.getContext()) {
      if (first)
        first = false;
      else
        s.append(",<br/> ");
      if (ec.getType() == ExtensionContextType.ELEMENT) {
        String ref = Utilities.oidRoot(ec.getExpression());
        if (ref.startsWith("@"))
          ref = ref.substring(1);
        if (definitions.hasElementDefn(ref)) 
          s.append("<a href=\""+definitions.getSrcFile(ref)+".html#"+Utilities.oidRoot(ec.getExpression())+"\">"+ec.getExpression()+"</a>");
        else if (definitions.hasResource(ref))
          s.append("<a href=\""+ref.toLowerCase()+".html\">"+ec.getExpression()+"</a>");
        else
          s.append(ec.getExpression());
      } else if (ec.getType() == ExtensionContextType.FHIRPATH) {
          s.append(Utilities.escapeXml(ec.getExpression()));
      } else
        throw new Error("Not done yet");
    }
    s.append("</td>");
    String fmm = ToolingExtensions.readStringExtension(ed, ToolingExtensions.EXT_FMM_LEVEL);
    s.append("<td>"+(Utilities.noString(fmm) ? "0" : fmm)+"</td>");
//    s.append("<td><a href=\"extension-"+ed.getId().toLowerCase()+ ".xml.html\">XML</a></td>");
//    s.append("<td><a href=\"extension-"+ed.getId().toLowerCase()+ ".json.html\">JSON</a></td>");
    s.append("</tr>");
  }

  private String displayExtensionCardinality(StructureDefinition ed) {
    ElementDefinition e = ed.getSnapshot().getElementFirstRep();
    String m = "";
    if (ed.getSnapshot().getElementFirstRep().getIsModifier())
      m = " <b title=\"This is a modifier extension\">M</b>";

    return Integer.toString(e.getMin())+".."+e.getMax()+m;
  }

  private String determineExtensionType(StructureDefinition ed) throws Exception {
    for (ElementDefinition e : ed.getSnapshot().getElement()) {
      if (e.getPath().startsWith("Extension.value") && !"0".equals(e.getMax())) {
        if (e.getType().size() == 1) {
          return "<a href=\""+definitions.getSrcFile(e.getType().get(0).getWorkingCode())+".html#"+e.getType().get(0).getWorkingCode()+"\">"+e.getType().get(0).getWorkingCode()+"</a>";
        } else if (e.getType().size() == 0) {
          return "";
        } else {
          return "(Choice)";
        }
      }


    }
    return "(complex)";
  }

  private String vsSource(ValueSet vs) {
    if (vs == null)
      return "by the FHIR project";
    if (vs == null || vs.getContact().isEmpty() || vs.getContact().get(0).getTelecom().isEmpty() || vs.getContact().get(0).getTelecom().get(0).getSystem() != ContactPointSystem.URL || vs.getContact().get(0).getTelecom().get(0).getValue().startsWith("http://hl7.org/fhir"))
      return "by the FHIR project";
    return " at <a href=\""+vs.getContact().get(0).getTelecom().get(0).getValue()+"\">"+vs.getContact().get(0).getTelecom().get(0).getValue()+"</a>";
  }

  private String csSource(CodeSystem cs) {
    if (cs == null)
      return "by the FHIR project";
    if (cs == null || cs.getContact().isEmpty() || cs.getContact().get(0).getTelecom().isEmpty() || cs.getContact().get(0).getTelecom().get(0).getSystem() != ContactPointSystem.URL || cs.getContact().get(0).getTelecom().get(0).getValue().startsWith("http://hl7.org/fhir"))
      return "by the FHIR project";
    return " at <a href=\""+cs.getContact().get(0).getTelecom().get(0).getValue()+"\">"+cs.getContact().get(0).getTelecom().get(0).getValue()+"</a>";
  }

  private String orgDT(String name, String xml, String tree, String uml1, String uml2, String ref, String ts, String json, String ttl, String diff) {
    StringBuilder b = new StringBuilder();
    b.append("<div id=\"tabs-").append(name).append("\">\r\n");
    b.append(" <ul>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-struc\">Structure</a></li>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-uml\">UML</a></li>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-xml\">XML</a></li>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-json\">JSON</a></li>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-ttl\">Turtle</a></li>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-diff\">R3 Diff</a></li>\r\n");
    b.append("  <li><a href=\"#tabs-"+name+"-all\">All</a></li>\r\n");
    b.append(" </ul>\r\n");
    b.append(" <div id=\"tabs-"+name+"-struc\">\r\n");
    b.append("  <div id=\"tbl\">\r\n");
    b.append("   <p><b>Structure</b></p>\r\n");
    b.append("   <div id=\"tbl-inner\">\r\n");
    b.append("    "+tree+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("\r\n");
    b.append(" <div id=\"tabs-"+name+"-uml\">\r\n");
    b.append("  <div id=\"uml\">\r\n");
    b.append("   <p><b>UML Diagram</b> (<a href=\"formats.html#uml\">Legend</a>)</p>\r\n");
    b.append("   <div id=\"uml-inner\">\r\n");
    b.append("    "+uml1+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("\r\n");
    b.append(" <div id=\"tabs-"+name+"-xml\">\r\n");
    b.append("  <div id=\"xml\">\r\n");
    b.append("   <p><b>XML Template</b></p>\r\n");
    b.append("   <div id=\"xml-inner\">\r\n");
    b.append("    "+xml+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("\r\n");
    b.append(" <div id=\"tabs-"+name+"-json\">\r\n");
    b.append("  <div id=\"json\">\r\n");
    b.append("   <p><b>JSON Template</b></p>\r\n");
    b.append("   <div id=\"json-inner\">\r\n");
    b.append("    "+json+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("\r\n");
    b.append(" <div id=\"tabs-"+name+"-ttl\">\r\n");
    b.append("  <div id=\"ttl\">\r\n");
    b.append("   <p><b>Turtle Template</b></p>\r\n");
    b.append("   <div id=\"ttl-inner\">\r\n");
    b.append("    "+ttl+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("\r\n");
    b.append(" <div id=\"tabs-"+name+"-diff\">\r\n");
    b.append("  <div id=\"diff\">\r\n");
    b.append("   <p><b>Changes since Release 3</b></p>\r\n");
    b.append("   <div id=\"diff-inner\">\r\n");
    b.append("    "+diff+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("\r\n");
    b.append(" <div id=\"tabs-"+name+"-all\">\r\n");
    b.append("  <div id=\"tbla\">\r\n");
    b.append("   <a name=\"tbl-"+name+"\"> </a>\r\n");
    b.append("   <p><b>Structure</b></p>\r\n");
    b.append("   <div id=\"tbl-inner\">\r\n");
    b.append("    "+tree+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append("\r\n");
    b.append("  <div id=\"umla\">\r\n");
    b.append("   <a name=\"uml-"+name+"\"> </a>\r\n");
    b.append("   <p><b>UML Diagram</b> (<a href=\"formats.html#uml\">Legend</a>)</p>\r\n");
    b.append("   <div id=\"uml-inner\">\r\n");
    b.append("    "+uml2+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append("\r\n");
    b.append("  <div id=\"xmla\">\r\n");
    b.append("   <a name=\"xml-"+name+"\"> </a>\r\n");
    b.append("   <p><b>XML Template</b></p>\r\n");
    b.append("   <div id=\"xml-inner\">\r\n");
    b.append("     "+xml+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append("\r\n");
    b.append("  <div id=\"jsona\">\r\n");
    b.append("   <a name=\"json-"+name+"\"> </a>\r\n");
    b.append("   <p><b>JSON Template</b></p>\r\n");
    b.append("   <div id=\"json-inner\">\r\n");
    b.append("     "+json+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append("  <div id=\"ttla\">\r\n");
    b.append("   <a name=\"ttl-"+name+"\"> </a>\r\n");
    b.append("   <p><b>Turtle Template</b></p>\r\n");
    b.append("   <div id=\"ttl-inner\">\r\n");
    b.append("     "+ttl+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append("  <div id=\"diffa\">\r\n");
    b.append("   <a name=\"diff-"+name+"\"> </a>\r\n");
    b.append("   <p><b>Changes since Release 3</b></p>\r\n");
    b.append("   <div id=\"diff-inner\">\r\n");
    b.append("     "+diff+"\r\n");
    b.append("   </div>\r\n");
    b.append("  </div>\r\n");
    b.append(" </div>\r\n");
    b.append("</div>\r\n");
    return b.toString();
  }

  private String allParamlist() {
    ResourceDefn rd = definitions.getBaseResources().get("Resource");
    List<String> names = new ArrayList<String>();
    names.addAll(rd.getSearchParams().keySet());
    Collections.sort(names);
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (String n  : names)
      b.append("<code>"+n+"</code>");
    return b.toString();
  }

  private String makeCanonical(String name) {
    if (name.contains("/"))
      name = name.substring(name.indexOf("/")+1);
    if (name.contains("\\"))
      name = name.substring(name.indexOf("\\")+1);
    int i = name.lastIndexOf(".");
    if (i == -1)
      throw new Error("unable to get canonical name for "+name);
    return name.substring(0, i)+".canonical"+name.substring(i);
  }


  private String makeJsonld(String name) {
    if (name.contains("/"))
      name = name.substring(name.indexOf("/")+1);
    if (name.contains("\\"))
      name = name.substring(name.indexOf("\\")+1);
    int i = name.lastIndexOf(".");
    if (i == -1)
      throw new Error("unable to get pretty name for "+name);
    return Utilities.changeFileExt(name.substring(0, i)+name.substring(i), ".jsonld");
  }

  private String makePretty(String name) {
    if (name.contains("/"))
      name = name.substring(name.indexOf("/")+1);
    if (name.contains("\\"))
      name = name.substring(name.indexOf("\\")+1);
    int i = name.lastIndexOf(".");
    if (i == -1)
      throw new Error("unable to get pretty name for "+name);
    return name.substring(0, i)+name.substring(i);
  }

  private String genIGProfilelist() {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append("  <tr>\r\n");
    b.append("    <td><b>Name</b></td>\r\n");
    b.append("    <td><b>Usage</b></td>\r\n");
    b.append("  </tr>\r\n");

    List<String> names = new ArrayList<String>();
    for (Resource ae : igResources.values()) {
      if (ae instanceof StructureDefinition)
        names.add(ae.getId());
    }
    Collections.sort(names);

    for (String s : names) {
      @SuppressWarnings("unchecked")
      StructureDefinition ae  = (StructureDefinition) igResources.get(s);
      b.append("  <tr>\r\n");
      b.append("    <td><a href=\""+((String) ae.getUserData("path")).replace(".xml", ".html")+"\">"+Utilities.escapeXml(ae.getName())+"</a></td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(ae.getDescription())).append("</td>\r\n");
      b.append(" </tr>\r\n");
    }
    b.append("</table>\r\n");

    return b.toString();
  }

  private String genOperationList() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">");
    b.append(" <tr><td colspan=\"2\"><b>Base Operations (All resource types)</b></td></tr>\r\n");
    for (ResourceDefn r : definitions.getBaseResources().values()) {
      genOperationDetails(b, r.getName(), r.getOperations(), true);
    }
    b.append(" <tr><td colspan=\"2\"><b>Operations Defined by Resource Types</b></td></tr>\r\n");
    for (String n : definitions.sortedResourceNames()) {
      ResourceDefn r = definitions.getResourceByName(n);
      genOperationDetails(b, n, r.getOperations(), false);
    }
//    b.append(" <tr><td colspan=\"2\"><b>Operations Defined by Implementation Guides</b></td></tr>\r\n");
//    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
//      for (Profile p : ig.getProfiles()) {
//        if (!p.getOperations().isEmpty())
//          genOperationDetails(b, ig.getCode()+File.separator+p.getId(), p.getOperations(), false);
//      }
//    }
    b.append("</table>");
    return b.toString();
  }

  private void genOperationDetails(StringBuilder b, String n, List<Operation> oplist, boolean isAbstract) {
    for (Operation op : oplist) {
      b.append("<tr><td><a href=\"").append(n.toLowerCase()).append("-operation-").append(op.getName()).append(".html\">");
      b.append(Utilities.escapeXml(op.getTitle()));
      b.append("</a></td><td>");
      boolean first = true;
      if (op.isSystem()) {
        first = false;
        b.append("[base]/$");
        b.append(op.getName());
      }
      if (op.isType()) {
        if (first)
          first = false;
        else
          b.append(" | ");
        b.append("[base]/");
        if (isAbstract)
          b.append("["+n+"]");
        else
          b.append(n);
        b.append("/$");
        b.append(op.getName());
      }
      if (op.isInstance()) {
        if (first)
          first = false;
        else
          b.append(" | ");
        b.append("[base]/");
        if (isAbstract)
          b.append("["+n+"]");
        else
          b.append(n);
        b.append("/[id]/$");
        b.append(op.getName());
      }
      b.append("</td></tr>");
    }
  }

  private String genProfilelist() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append("  <tr>\r\n");
    b.append("    <td><b>Name</b></td>\r\n");
    b.append("    <td><b>Description</b></td>\r\n");
    b.append("    <td><b>Kind</b></td>\r\n");
    b.append("    <td><b><a href=\"versions.html#Maturity\">FMM</a></b></td>");
    b.append("  </tr>\r\n");

    b.append("  <tr>\r\n");
    b.append("    <td colspan=\"2\"><b>General</b></td>\r\n");
    b.append("  </tr>\r\n");
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getPackMap().keySet());
    Collections.sort(names);
    for (String s : names) {
      Profile ap = definitions.getPackMap().get(s);
      ImplementationGuideDefn ig = definitions.getIgs().get(ap.getCategory());
      b.append("  <tr>\r\n");
      b.append("    <td><a href=\"").append(ig.getPrefix()+ap.getId()).append(".html\">").append(Utilities.escapeXml(ap.getTitle())).append("</a></td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(ap.getDescription())).append("</td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(ap.describeKind())).append("</td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(ap.getFmmLevel())).append("</td>\r\n");
      b.append(" </tr>\r\n");
    }
    for (String n : definitions.sortedResourceNames()) {
      ResourceDefn r = definitions.getResourceByName(n);
      if (!r.getConformancePackages().isEmpty()) {
        b.append("  <tr>\r\n");
        b.append("    <td colspan=\"4\"><b>"+r.getName()+"</b></td>\r\n");
        b.append("  </tr>\r\n");
        for (Profile p : r.getConformancePackages()) {
          ImplementationGuideDefn ig = definitions.getIgs().get(p.getCategory());
          b.append("  <tr>\r\n");
          b.append("    <td><a href=\""+ig.getPrefix()+p.getId()+".html\">"+Utilities.escapeXml(p.getTitle())+"</a></td>\r\n");
          b.append("    <td>"+Utilities.escapeXml(p.getDescription())+"</td>\r\n");
          b.append("    <td>"+Utilities.escapeXml(p.describeKind())+"</td>\r\n");
          b.append("    <td>"+Utilities.escapeXml(p.getFmmLevel())+"</td>\r\n");
          b.append(" </tr>\r\n");
        }
      }
    }
    b.append("</table>\r\n");

    return b.toString();
  }

  private String profileRef(String name) {
    return "Alternate definitions: Resource StructureDefinition (<a href=\""+name+".profile.xml.html\">XML</a>, <a href=\""+name+".profile.json.html\">JSON</a>)";
  }

  private String reflink(String name) {
    for (PlatformGenerator t : referenceImplementations)
      if (t.getName().equals(name))
        return t.getReference(version.toCode());
    return "??";
  }

  private String conceptmaplist(String id, String level) throws FHIRException {
    List<ConceptMap> cmaps = new ArrayList<ConceptMap>();
    for (ConceptMap cm : conceptMaps.values()) {
      if (getCMRef(cm.getSource()).equals(id) || getCMRef(cm.getTarget()).equals(id))
        cmaps.add(cm);
    }
    if (cmaps.size() == 0)
      return "";
    else {
      String prefix = "";
      if (level.equals("l1"))
        prefix = "../";
      else if (level.equals("l2"))
        prefix = "../../";
      else if (level.equals("l3"))
        prefix = "../../../";
      StringBuilder b = new StringBuilder();
      b.append("<p>Concept Maps for this value set:</p>");
      b.append("<table class=\"grid\">\r\n");
      for (ConceptMap cm : cmaps) {
        b.append(" <tr><td>");
        if (cm.getSourceCanonicalType().getValue().equals(id)) {
          b.append("to <a href=\"").append(getValueSetRef(prefix, cm.getTargetCanonicalType().getValue())).append("\">")
                  .append(describeValueSetByRef(cm.getTarget()));
        } else {
          b.append("from <a href=\"").append(getValueSetRef(prefix, cm.getSourceCanonicalType().getValue())).append("\">")
                  .append(describeValueSetByRef(cm.getSource()));
        }
        b.append("</a></td><td><a href=\"").append(prefix).append(cm.getUserData("path")).append("\">").append(cm.getName())
                .append("</a></td><td><a href=\"").append(prefix).append(Utilities.changeFileExt((String) cm.getUserData("path"), ".xml.html"))
                .append("\">XML</a></td><td><a href=\"").append(prefix).append(Utilities.changeFileExt((String) cm.getUserData("path"), ".json.html")).append("\">JSON</a></td></tr>");
      }
      b.append("</table>\r\n");
      return b.toString();
    }
  }

  private String getCMRef(Type target) {
    return target.primitiveValue();
  }

  private String getValueSetRef(String prefix, String ref) {
    ValueSet vs = definitions.getValuesets().get(ref);
    if (vs == null) {
      if (ref.equals("http://snomed.info/id"))
        return "http://snomed.info";
      else
        return ref;
    } else
      return prefix+vs.getUserData("path");
  }

  private String describeValueSetByRef(Type reft) {
    String ref = reft.primitiveValue();
    ValueSet vs = definitions.getValuesets().get(ref);
    if (vs == null) {
      if (ref.equals("http://snomed.info/id"))
        return "Snomed CT";
      else
        return ref;
    } else
      return vs.present();
  }

  private String xreferencesForV2(String name, String level) {
    if (!definitions.getValuesets().containsKey("http://terminology.hl7.org/ValueSet/v2-"+name))
      return ". ";
    String n = definitions.getValuesets().get("http://terminology.hl7.org/ValueSet/v2-"+name).present().replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
    StringBuilder b = new StringBuilder();
    String pfx = "../../";
    if (level.equals("l3"))
      pfx = "../../../";
    ValueSet ae = findRelatedValueset(n, definitions.getValuesets(), "http://hl7.org/fhir/ValueSet/");
    if (ae != null)
      b.append(". Related FHIR content: <a href=\"").append(pfx).append(ae.getUserData("path")).append("\">").append(ae.present()).append("</a>");
    ae = findRelatedValueset(n, definitions.getValuesets(), "http://terminology.hl7.org/ValueSet/v3-");
    if (ae != null)
      b.append(". Related v3 content: <a href=\"").append(pfx).append(ae.getUserData("path")).append("\">").append(ae.present()).append("</a>");
    return b.toString()+". ";
  }

  private String xreferencesForFhir(String name) {
    String n = name.replace("-", "").toLowerCase();
    StringBuilder b = new StringBuilder();
    ValueSet ae = findRelatedValueset(n, definitions.getValuesets(), "http://terminology.hl7.org/ValueSet/v2-");
    if (ae != null)
      b.append(". Related v2 content: <a href=\"").append(ae.getUserData("path")).append("\">").append(ae.present()).append("</a>");
    ae = findRelatedValueset(n, definitions.getValuesets(), "http://terminology.hl7.org/ValueSet/v3-");
    if (ae != null)
      b.append(". Related v3 content: <a href=\"").append(ae.getUserData("path")).append("\">").append(ae.present()).append("</a>");
    return b.toString()+". ";
  }

  private String xreferencesForV3(String name) {
    String n = name.replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
    StringBuilder b = new StringBuilder();
    ValueSet ae = findRelatedValueset(n, definitions.getValuesets(), "http://terminology.hl7.org/ValueSet/v2-");
    String path = "../../";
    if (ae != null)
      b.append(". Related v2 content: <a href=\"").append(path).append(ae.getUserData("path")).append("\">").append(ae.present()).append("</a>");
    ae = findRelatedValueset(n, definitions.getValuesets(), "http://hl7.org/fhir/ValueSet/");
    if (ae != null)
      b.append(". Related FHIR content: <a href=\"").append(path).append(ae.getUserData("path")).append("\">").append(ae.present()).append("</a>");
    return b.toString()+". ";
  }

  private ValueSet findRelatedValueset(String n, Map<String, ValueSet> vslist, String prefix) {
    for (String s : vslist.keySet()) {
      ValueSet ae = vslist.get(s);
      String url = ae.getUrl();
      if (url.startsWith(prefix)) {
        String name = url.substring(prefix.length()).replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
        if (n.equals(name))
          return ae;
        name = ae.present().replace("-", "").replace(" ", "").replace("_", "").toLowerCase();
        if (n.equals(name))
          return ae;
      }
    }
    return null;
  }

  public String genlevel(int level) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < level; i++) {
      b.append("../");
    }
    return b.toString();
  }

  private String compTitle(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getTitle();
  }

  private String compName(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getName().toLowerCase();
  }

  private String compDesc(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getDescription();
  }

  private String compUri(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getUri();
  }

  private String compIdentity(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getIdentity();
  }

  private String compMembership(String name) {
    String n = name.split("\\-")[1];
    return definitions.getCompartmentByName(n).getMembership();
  }

  private String compResourceMap(String name) throws Exception {
    String n = name.split("\\-")[1];
    StringBuilder in = new StringBuilder();
    StringBuilder out = new StringBuilder();
    Map<ResourceDefn, String> map = definitions.getCompartmentByName(n).getResources();
    for (String rn : definitions.sortedResourceNames()) {
      ResourceDefn rd = definitions.getResourceByName(rn);
      String rules = map.get(rd);
      if (Utilities.noString(rules)) {
        out.append(" <li><a href=\"").append(rd.getName().toLowerCase()).append(".html\">").append(rd.getName()).append("</a></li>\r\n");
      } else if (!rules.equals("{def}")) {
        in.append(" <tr><td><a href=\"").append(rd.getName().toLowerCase()).append(".html\">").append(rd.getName()).append("</a></td><td>").append(rules.replace("|", "or")).append("</td></tr>\r\n");
      }
    }
    return "<p>\r\nThe following resources may be in this compartment:\r\n</p>\r\n" +
        "<table class=\"grid\">\r\n"+
        " <tr><td><b>Resource</b></td><td><b>Inclusion Criteria</b></td></tr>\r\n"+
        in.toString()+
        "</table>\r\n"+
        "<p>\r\nA resource is in this compartment if the nominated search parameter (or chain) refers to the patient resource that defines the compartment.\r\n</p>\r\n" +
        "<p>\r\n\r\n</p>\r\n" +
        "<p>\r\nThe following resources are never in this compartment:\r\n</p>\r\n" +
        "<ul>\r\n"+
        out.toString()+
        "</ul>\r\n";
  }

  private String compartmentlist() {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append(" <tr><td><b>Title</b></td><td><b>Description</b></td><td><b>Identity</b></td><td><b>Membership</b></td></tr>\r\n");
    for (Compartment c : definitions.getCompartments()) {
      b.append(" <tr><td><a href=\"compartmentdefinition-").append(c.getName().toLowerCase()).append(".html\">").append(c.getTitle()).append("</a></td><td>")
              .append(Utilities.escapeXml(c.getDescription())).append("</td>").append("<td>").append(Utilities.escapeXml(c.getIdentity())).append("</td><td>").append(Utilities.escapeXml(c.getMembership())).append("</td></tr>\r\n");
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String genV3CodeSystem(String name) throws Exception {
    CodeSystem vs = definitions.getCodeSystems().get("http://terminology.hl7.org/CodeSystem/v3-"+name);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".cs.xml"), vs);
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".cs.canonical.xml"), vs);
    cloneToXhtml(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".cs.xml", folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".cs.xml.html", vs.getName(), vs.getDescription(), 2, false, "v3:cs:"+name, "CodeSystem", null, null, definitions.getWorkgroups().get("vocab"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".cs.json"), vs);
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".cs.canonical.json"), vs);
    jsonToXhtml(Utilities.path(folders.dstDir, "v3", name, "v3-"+name+".cs.json"), Utilities.path(folders.dstDir, "v3", name, "v3-"+name+".cs.json.html"), vs.getName(), vs.getDescription(), 2, r2Json(vs), "v3:cs:"+name, "CodeSystem", null, null, definitions.getWorkgroups().get("vocab"));
    Set<String> langs = findCodeSystemTranslations(vs);
    if (langs.size() > 0) {
      return renderCodeSystemWithLangs(langs, vs, "../../");  
    }

    return new XhtmlComposer(XhtmlComposer.HTML).compose(vs.getText().getDiv());
  }

  private String genV3ValueSet(String name) throws Exception {
    ValueSet vs = definitions.getValuesets().get("http://terminology.hl7.org/ValueSet/v3-"+FormatUtilities.makeId(name));
    if (vs == null)
      throw new Exception("unable to find v3 value set "+name);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml"), vs);
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".canonical.xml"), vs);
    cloneToXhtml(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml", folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".xml.html", vs.present(), vs.getDescription(), 2, false, "v3:vs:"+name, "ValueSet", null, null, definitions.getWorkgroups().get("vocab"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".json"), vs);
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v3"+File.separator+name+File.separator+"v3-"+name+".canonical.json"), vs);
    jsonToXhtml(Utilities.path(folders.dstDir, "v3", name, "v3-"+name+".json"), Utilities.path(folders.dstDir, "v3", name, "v3-"+name+".json.html"), vs.present(), vs.getDescription(), 2, r2Json(vs), "v3:vs:"+name, "ValueSet", null, null, definitions.getWorkgroups().get("vocab"));

    return ""; // use generic value set mechanism instead... new XhtmlComposer(XhtmlComposer.HTML).compose(vs.getText().getDiv()).replace("href=\"v3/", "href=\"../");
  }

  private String genV2TableVer(String name) throws Exception {
    String[] n = name.split("\\|");
    ValueSet vs = definitions.getValuesets().get("http://terminology.hl7.org/ValueSet/v2-"+n[1]+"-"+n[0]);
    CodeSystem cs = (CodeSystem) vs.getUserData("cs");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".vs.xml"), vs);
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".vs.canonical.xml"), vs);
    cloneToXhtml(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".vs.xml", folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".vs.xml.html", vs.present(), vs.getDescription(), 3, false, "v2:tbl"+name, "V2 Table", null, null, definitions.getWorkgroups().get("vocab"));
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".vs.json"), vs);
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".vs.canonical.json"), vs);
    jsonToXhtml(Utilities.path(folders.dstDir, "v2", n[0], n[1], "v2-"+n[0]+"-"+n[1]+".vs.json"), Utilities.path(folders.dstDir, "v2", n[0], n[1], "v2-"+n[0]+"-"+n[1]+".vs.json.html"), vs.present(), vs.getDescription(), 3, r2Json(vs), "v2:tbl"+name, "V2 Table", null, null, definitions.getWorkgroups().get("vocab"));

    if (cs != null) {
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".cs.xml"), cs);
      new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".cs.canonical.xml"), cs);
      cloneToXhtml(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".cs.xml", folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".cs.xml.html", cs.present(), cs.getDescription(), 3, false, "v2:tbl"+name, "V2 Table", null, null, definitions.getWorkgroups().get("vocab"));
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".cs.json"), cs);
      new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+n[0]+File.separator+n[1]+File.separator+"v2-"+n[0]+"-"+n[1]+".cs.canonical.json"), cs);
      jsonToXhtml(Utilities.path(folders.dstDir, "v2", n[0], n[1], "v2-"+n[0]+"-"+n[1]+".cs.json"), Utilities.path(folders.dstDir, "v2", n[0], n[1], "v2-"+n[0]+"-"+n[1]+".cs.json.html"), cs.present(), cs.getDescription(), 3, r2Json(cs), "v2:tbl"+name, "V2 Table", null, null, definitions.getWorkgroups().get("vocab"));
      Set<String> langs = findCodeSystemTranslations(cs);
      if (langs.size() > 0) {
        return renderCodeSystemWithLangs(langs, cs, "../../../");  
      }
    }
    return new XhtmlComposer(XhtmlComposer.HTML).compose(vs.getText().getDiv());
  }

  private String genV2Table(String name) throws Exception {
    ValueSet vs = definitions.getValuesets().get("http://terminology.hl7.org/ValueSet/v2-"+name);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".vs.xml"), vs);
    new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".vs.canonical.xml"), vs);
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".vs.json"), vs);
    new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".vs.canonical.json"), vs);
    cloneToXhtml(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".vs.xml", folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".vs.xml.html", vs.present(), vs.getDescription(), 2, false, "v2:tbl"+name, "V2 Table", null, null, definitions.getWorkgroups().get("vocab"));
    jsonToXhtml(Utilities.path(folders.dstDir, "v2", name, "v2-"+name+".vs.json"), Utilities.path(folders.dstDir, "v2", name, "v2-"+name+".vs.json.html"), vs.present(), vs.getDescription(), 2, r2Json(vs), "v2:tbl"+name, "V2 Table", null, null, definitions.getWorkgroups().get("vocab"));
    CodeSystem cs = definitions.getCodeSystems().get("http://terminology.hl7.org/CodeSystem/v2-"+name);
    if (cs != null) {
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".cs.xml"), cs);
      new XmlParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".cs.canonical.xml"), cs);
      new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".cs.json"), cs);
      new JsonParser().setOutputStyle(OutputStyle.CANONICAL).compose(new FileOutputStream(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".cs.canonical.json"), cs);
      cloneToXhtml(folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".cs.xml", folders.dstDir+"v2"+File.separator+name+File.separator+"v2-"+name+".cs.xml.html", cs.present(), cs.getDescription(), 2, false, "v2:tbl"+name, "V2 Table", null, null, definitions.getWorkgroups().get("vocab"));
      jsonToXhtml(Utilities.path(folders.dstDir, "v2", name, "v2-"+name+".cs.json"), Utilities.path(folders.dstDir, "v2", name, "v2-"+name+".cs.json.html"), cs.present(), cs.getDescription(), 2, r2Json(cs), "v2:tbl"+name, "V2 Table", null, null, definitions.getWorkgroups().get("vocab"));
      Set<String> langs = findCodeSystemTranslations(cs);
      if (langs.size() > 0) {
        return renderCodeSystemWithLangs(langs, cs, "../../");  
      }
    }

    return new XhtmlComposer(XhtmlComposer.HTML).compose(vs.getText().getDiv());
  }

  private String renderCodeSystemWithLangs(Set<String> langs, CodeSystem cs, String prefix) throws Exception {
    Narrative n = cs.getText();
    
    StringBuilder b = new StringBuilder();
    b.append("<div id=\"tabs\" style=\"border-right-style: none;\">\r\n");
    b.append("<ul>\r\n");
    b.append("<li><a href=\"#tabs-all\">All Languages</a></li>\r\n");
    b.append("<li><a href=\"#tabs-en\">English</a></li>\r\n");
    for (String l : sorted(langs))
      b.append("<li><a href=\"#tabs-"+l+"\">"+langDisplay(l)+"</a></li>\r\n");
    b.append("</ul>\r\n");

    b.append("<div id=\"tabs-all\">\r\n");
    cs.setText(null);
    new NarrativeGenerator(prefix, null, workerContext).generate(null, cs, false, "*");
    b.append(new XhtmlComposer(XhtmlComposer.HTML).compose(cs.getText().getDiv()));
    b.append("</div>\r\n");
    
    b.append("<div id=\"tabs-en\">\r\n");
    cs.setText(null);
    new NarrativeGenerator(prefix, null, workerContext).generate(null, cs, false, "en");
    b.append(new XhtmlComposer(XhtmlComposer.HTML).compose(cs.getText().getDiv()));
    b.append("</div>\r\n");
    
    for (String l : sorted(langs)) {
      b.append("<div id=\"tabs-"+l+"\">\r\n");
      String desc = cs.getDescriptionElement().getTranslation(l);
      if (!Utilities.noString(desc))
        b.append(processMarkdown("RenderingCodeSystem", workerContext.translator().translate("render-cs", "Definition", l)+": "+desc, prefix));
      cs.setText(null);
      new NarrativeGenerator(prefix, null, workerContext).generate(null, cs, false, l);
      b.append(new XhtmlComposer(XhtmlComposer.HTML).compose(cs.getText().getDiv()));
      b.append("</div>\r\n");
    }
    b.append("</div>\r\n");

    cs.setText(n);
    return b.toString();
  }

  private String langDisplay(String l) {
    ValueSet vs = getValueSets().get("http://hl7.org/fhir/ValueSet/languages");
    for (ConceptReferenceComponent vc : vs.getCompose().getInclude().get(0).getConcept()) {
      if (vc.getCode().equals(l)) {
        for (ConceptReferenceDesignationComponent cd : vc.getDesignation()) {
          if (cd.getLanguage().equals(l))
            return cd.getValue()+" ("+vc.getDisplay()+")";
        }
        return vc.getDisplay();
      }
    }
    return "??Lang";
  }

  private Set<String> findCodeSystemTranslations(CodeSystem cs) {
    Set<String> res = new HashSet<String>();
    findTranslations(res, cs.getConcept());
    return res;
  }

  private void findTranslations(Set<String> res, List<ConceptDefinitionComponent> list) {
    for (ConceptDefinitionComponent cc : list) {
      for (ConceptDefinitionDesignationComponent cd : cc.getDesignation()) {
        if (cd.hasLanguage())
          res.add(cd.getLanguage());
      }
      Extension ex = cc.getExtensionByUrl(ToolingExtensions.EXT_CS_COMMENT);
      if (ex != null) {
        for (String l : ToolingExtensions.getLanguageTranslations(ex).keySet())
          res.add(l);
      }
      findTranslations(res, cc.getConcept());
    }
  }

  private String r2Json(ValueSet vs) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    json.setSuppressXhtml("Snipped for Brevity");
    json.compose(bytes, vs);
    return new String(bytes.toByteArray());
  }

  private String r2Json(CodeSystem vs) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    IParser json = new JsonParser().setOutputStyle(OutputStyle.PRETTY);
    json.setSuppressXhtml("Snipped for Brevity");
    json.compose(bytes, vs);
    return new String(bytes.toByteArray());
  }

  private void cloneToXhtml(String src, String dst, String name, String description, int level, boolean adorn, String pageType, String crumbTitle, ImplementationGuideDefn ig, ResourceDefn rd, WorkGroup wg) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();

    Document xdoc = builder.parse(new CSFileInputStream(new CSFile(src)));
//    XhtmlGenerator xhtml = new XhtmlGenerator(null);
//    xhtml.generate(xdoc, new CSFile(dst), name, description, level, adorn);

    String n = new File(dst).getName();
    n = n.substring(0, n.length()-9);
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(definitions, genlevel(level)));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, name, description, level, adorn, n+".xml.html");
    String html = ("<%setlevel "+Integer.toString(level)+"%>"+TextFile.fileToString(folders.srcDir + "template-example-xml.html")).replace("<%example%>", b.toString());
    html = processPageIncludes(n+".xml.html", html, pageType, null, n+".xml.html", null, null, crumbTitle,  (adorn && hasNarrative(xdoc)) ? new Boolean(true) : null, ig, rd, wg);
    TextFile.stringToFile(html, dst);
    htmlchecker.registerExternal(dst);
  }

  private boolean hasNarrative(Document xdoc) {
    return XMLUtil.hasNamedChild(XMLUtil.getNamedChild(xdoc.getDocumentElement(), "text"), "div");
  }

  public void jsonToXhtml(String src, String dst, String name, String description, int level, String json, String pageType, String crumbTitle, ImplementationGuideDefn ig, ResourceDefn rd, WorkGroup wg) throws Exception {

    String n = new File(dst).getName();
    n = n.substring(0, n.length()-10);
    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(description) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    String html = ("<%setlevel "+Integer.toString(level)+"%>"+TextFile.fileToString(folders.srcDir + "template-example-json.html")).replace("<%example%>", json);
    html = processPageIncludes(n+".json.html", html, pageType, null, null, null, crumbTitle, ig, rd, wg);
    TextFile.stringToFile(html, dst);
    htmlchecker.registerExternal(dst);
  }


  private String genV2Index() throws IOException {
    return new ValueSetImporterV2(this, validationErrors).getIndex(v2src, true);
  }

  private String genV2VSIndex() throws IOException {
    return new ValueSetImporterV2(this, validationErrors).getIndex(v2src, false);
  }

  private String genV3CSIndex() {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>Name (URI = http://terminology.hl7.org/CodeSystem/v3-...)</b></td><td><b>Description</b></td><td><b>OID</b></td></tr>\r\n");

    List<String> names = new ArrayList<String>();
    Map<String, CodeSystem> map = new HashMap<String, CodeSystem>();

    for (CodeSystem cs : definitions.getCodeSystems().values()) {
      if (cs != null) {
        String n = cs.getUrl();
        if (n.contains("/v3-")) {
          names.add(n);
          map.put(n, cs);
        }
      }
    }
    Collections.sort(names);

    for (String n : names) {
      CodeSystem cs = map.get(n);
      String id = tail(cs.getUrl()).substring(3);
      String oid = CodeSystemUtilities.getOID(cs);
      s.append(" <tr><td><a href=\"v3/").append(id).append("/cs.html\">").append(Utilities.escapeXml(id))
              .append("</a></td><td>").append(Utilities.escapeXml(cs.getDescription())).append("</td><td>").append(oid == null ? "--" : oid).append("</td></tr>\r\n");
    }

    s.append("</table>\r\n");
    return s.toString();
  }

  private String genV3VSIndex() {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>Name (URI = http://terminology.hl7.org/ValueSet/v3-...) </b></td><td><b>Name</b></td><td><b>OID</b></td></tr>\r\n");

    List<String> names = new ArrayList<String>();
    Map<String, ValueSet> map = new HashMap<String, ValueSet>();

    for (ValueSet vs : definitions.getValuesets().values()) {
      String n = vs.getUrl();
      if (n.contains("/v3")) {
        names.add(n);
        map.put(n, vs);
      }
    }
    Collections.sort(names);

    for (String n : names) {
      ValueSet vs = map.get(n);
      String id = tail(vs.getUrl()).substring(3);
      String oid = ValueSetUtilities.getOID(vs);
      if (oid != null && oid.startsWith("urn:oid:"))
        oid = oid.substring(8);
      String[] desc = vs.getDescription().split("\\(OID \\= ");
      s.append(" <tr><td><a href=\"v3/").append(id).append("/vs.html\">").append(Utilities.escapeXml(id))
            .append("</a></td><td>").append(Utilities.escapeXml(vs.getDescription())).append("</td><td>").append(oid == null ? "--" : oid).append("</td></tr>\r\n");
//      s.append(" <tr><td><a href=\"ValueSet/vs-").append(id).append("/index.html\">")
//              .append(id).append("</a></td><td>").append(desc[0]).append("</td><td>").append(oid == null ? "==" : oid).append("</td></tr>\r\n");
    }

    s.append("</table>\r\n");
    return s.toString();
  }

  private String tail(String id) {
    int i = id.lastIndexOf("/");
    return id.substring(i+1);
  }

  private String genDataTypeMappings(String name) throws Exception {
    if (name.equals("primitives")) {
      StringBuilder b = new StringBuilder();
      b.append("<table class=\"grid\">\r\n");
      b.append("<tr>");
      b.append("<td><b>Data Type</b></td>");
      b.append("<td><b>V2</b></td>");
      b.append("<td><b>RIM</b></td>");
      b.append("</tr>");
      List<String> names = new ArrayList<String>();
      names.addAll(definitions.getPrimitives().keySet());
      Collections.sort(names);
      for (String n : names) {
        DefinedCode dc = definitions.getPrimitives().get(n);
        if (dc instanceof PrimitiveType) {
          PrimitiveType pt = (PrimitiveType) dc;
          b.append("<tr>");
          b.append("<td>").append(n).append("</td>");
          b.append("<td>").append(pt.getV2()).append("</td>");
          b.append("<td>").append(pt.getV3()).append("</td>");
          b.append("</tr>");
        }
      }
      b.append("</table>\r\n");
      return b.toString();
    } else {
      List<ElementDefn> list = new ArrayList<ElementDefn>();
      //    list.addAll(definitions.getStructures().values());
      //    list.addAll(definitions.getTypes().values());
      //    list.addAll(definitions.getInfrastructure().values());
      list.add(definitions.getElementDefn(name));
      MappingsGenerator maps = new MappingsGenerator(definitions);
      maps.generate(list);
      return maps.getMappings();
    }
  }

  private String resItem(String name, boolean even) throws Exception {
    String color = even ? "#EFEFEF" : "#FFFFFF";
    if (definitions.hasResource(name)) {
      ResourceDefn r = definitions.getResourceByName(name);
      if (resourceCategory != null && !ToolingExtensions.hasExtension(r.getProfile(), ToolingExtensions.EXT_RESOURCE_CATEGORY)) {
        ToolingExtensions.setStringExtension(r.getProfile(), ToolingExtensions.EXT_RESOURCE_CATEGORY, resourceCategory); 
        ini.setStringProperty("category", r.getName(), resourceCategory, null);
        ini.save();
      }
      return
          "<tr bgcolor=\""+color+"\"><td><a href=\""+name.toLowerCase()+".html\">"+name+"</a></td><td>"+aliases(r.getRoot().getAliases())+"</td><td>"+Utilities.escapeXml(r.getDefinition())+"</td></tr>\r\n";

    } else if (definitions.getBaseResources().containsKey(name)){
      ResourceDefn r = definitions.getBaseResources().get(name);
      return
          "<tr bgcolor=\""+color+"\"><td><a href=\""+name.toLowerCase()+".html\">"+name+"</a></td><td>"+aliases(r.getRoot().getAliases())+"</td><td>"+Utilities.escapeXml(r.getDefinition())+"</td></tr>\r\n";

    } else
      return
          "<tr bgcolor=\""+color+"\"><td>"+name+"</td><td>(Not defined yet)</td><td></td><td></td></tr>\r\n";

  }

  private String resCat(String name) throws Exception {
    resourceCategory = name;
    return "";
  }
  private String resDesc(String name) throws Exception {
    if (definitions.hasResource(name)) {
      ResourceDefn r = definitions.getResourceByName(name);
      if (resourceCategory != null) {
        if (!ToolingExtensions.hasExtension(r.getProfile(), ToolingExtensions.EXT_RESOURCE_CATEGORY)) {
          ToolingExtensions.setStringExtension(r.getProfile(), ToolingExtensions.EXT_RESOURCE_CATEGORY, resourceCategory); 
        }
        IniFile cini = new IniFile(Utilities.path(folders.rootDir, "temp", "categories.ini"));
        if (!resourceCategory.equals(cini.getStringProperty("category", r.getName()))) {
          cini.setStringProperty("category", r.getName(), resourceCategory, null);
          cini.save();
        }
      }
      return Utilities.escapeXml(r.getDefinition());
    } else
      return " ";
  }

  private String aliases(List<String> aliases) {
    if (aliases == null || aliases.size() == 0)
      return "";
    StringBuilder b = new StringBuilder();
    b.append(aliases.get(0));
    for (int i = 1; i < aliases.size() - 1; i++) {
      b.append(", ").append(aliases.get(i));
    }
    return b.toString();
  }

  private String resCategory(String string) {
    String[] parts = string.split("\\|");
    return
        "<tr><td colspan=\"3\"><hr/></td></tr>\r\n"+
        "<tr><th colspan=\"3\">"+parts[0]+"<a name=\""+parts[0].toLowerCase().replace(" ", "")+"\"> </a></th></tr>\r\n"+
        "<tr><td colspan=\"3\">"+Utilities.escapeXml(parts[1])+"</td></tr>\r\n";
  }

  private String onThisPage(String tail) {
    String[] entries = tail.split("\\|");
    StringBuilder b = new StringBuilder();
    b.append("<div class=\"col-3\"><div class=\"itoc\">\r\n<p>On This Page:</p>\r\n");
    for (String e : entries) {
      String[] p = e.split("#");
      if (p.length == 2)
        b.append("<p class=\"link\"><a href=\"#"+p[1]+"\">"+Utilities.escapeXml(p[0])+"</a></p>");
      if (p.length == 1)
        b.append("<p class=\"link\"><a href=\"#\">"+Utilities.escapeXml(p[0])+"</a></p>");
    }
    b.append("\r\n</div></div>\r\n");
    return b.toString();
  }

  public String mapOnThisPage(String mappings) {
    if (mappings == null) {
      List<ElementDefn> list = new ArrayList<ElementDefn>();
      list.addAll(definitions.getStructures().values());
      list.addAll(definitions.getTypes().values());
      list.addAll(definitions.getInfrastructure().values());
      MappingsGenerator maps = new MappingsGenerator(definitions);
      maps.generate(list);
      mappings = maps.getMappingsList();
    }
    if (Utilities.noString(mappings))
      return "";

    String[] entries = mappings.split("\\|");
    StringBuilder b = new StringBuilder();
    b.append("<div class=\"itoc\">\r\n<p>Mappings:</p>\r\n");
    for (String e : entries) {
      String[] p = e.split("#");
      if (p.length == 2)
        b.append("<p class=\"link\"><a href=\"#"+p[1]+"\">"+Utilities.escapeXml(p[0])+"</a></p>");
      if (p.length == 1)
        b.append("<p class=\"link\"><a href=\"#\">"+Utilities.escapeXml(p[0])+"</a></p>");
    }
    b.append("</div>\r\n");
    return b.toString();
  }

  private static class TocSort implements Comparator<String> {

    @Override
	public int compare(String arg0, String arg1) {
      String[] a0 = arg0.split("\\.");
      String[] a1 = arg1.split("\\.");
      for (int i = 0; i < Math.min(a0.length, a1.length); i++) {
        int i0 = Integer.parseInt(a0[i]);
        int i1 = Integer.parseInt(a1[i]);
        if (i0 != i1)
          return i0-i1;
      }
      return (a0.length - a1.length);
    }
  }

  public class TocItem {
    TocEntry entry;
    Row row;
    int depth;
    public TocItem(TocEntry entry, Row row, int depth) {
      super();
      this.entry = entry;
      this.row = row;
      this.depth = depth;
    }
  }


  private String genIgToc(ImplementationGuideDefn ig) throws Exception {
    HierarchicalTableGenerator gen = new HierarchicalTableGenerator(folders.dstDir, false, true);
    return new XhtmlComposer(XhtmlComposer.HTML).compose(gen.generate(ig.genToc(gen), "../", 0, null));
  }

  private String generateToc() throws Exception {
    // return breadCrumbManager.makeToc();
    List<String> entries = new ArrayList<String>();
    entries.addAll(toc.keySet());
    Collections.sort(entries, new SectionSorter());
    Set<String> pages = new HashSet<String>();
    HierarchicalTableGenerator gen = new HierarchicalTableGenerator(folders.dstDir, false, true);
    TableModel model = gen.new TableModel();
    model.getTitles().add(gen.new Title(null, model.getDocoRef(), "Table of Contents", "Table of Contents", null, 0));
    Deque<TocItem> stack = new ArrayDeque<TocItem>();

    for (String s : entries) {
      TocEntry t = toc.get(s);
      if (!t.isIg() && !s.startsWith("?")) {
        String nd = s;
        while (nd.endsWith(".0"))
          nd = nd.substring(0, nd.length()-2);
        int d = Utilities.charCount(nd, '.');
        if (d < 4 && !pages.contains(t.getLink())) {
          String np = getNormativePackageForPage(t.getLink());
          pages.add(t.getLink());
          while (!stack.isEmpty() && stack.getFirst().depth >= d)
            stack.pop();
          Row row = gen.new Row();
          row.setIcon("icon_page.gif", null);
          String td = t.getText();
          if (!stack.isEmpty()) {
            if (td.startsWith(stack.getFirst().entry.getText()+" - "))
              td = td.substring(stack.getFirst().entry.getText().length()+3);
            else if (td.startsWith(stack.getFirst().entry.getText()))
              td = td.substring(stack.getFirst().entry.getText().length());
          }
          Cell cell = gen.new Cell(null, t.getLink(), nd+" "+td, t.getText()+" ", null);
          row.getCells().add(cell);
          if (np != null) {
            cell.addPiece(gen.new Piece(null, " ", null));
            cell.addPiece(gen.new Piece("versions.html#std-process", "basic".equals(np) ?  "(Normative)" : "(Normative / "+Utilities.capitalize(np)+")", null).addStyle("color: #008000"));
            if (np.equals("infrastructure"))
              row.setIcon("icon_page_n_i.gif", null);
            else if (np.equals("conformance"))
              row.setIcon("icon_page_n_c.gif", null);
            else if (np.equals("patient"))
              row.setIcon("icon_page_n_p.gif", null);
            else if (np.equals("observation"))
              row.setIcon("icon_page_n_o.gif", null);
            else 
              row.setIcon("icon_page_n.gif", null);
          } else {            
            cell.addPiece(gen.new Piece(null, " ", null));
            cell.addPiece(gen.new Piece("versions.html#std-process", "(Trial Use)", null).addStyle("color: #b3b3b3"));
          }
          if (stack.isEmpty())
            model.getRows().add(row);
          else
            stack.getFirst().row.getSubRows().add(row);
          stack.push(new TocItem(t,  row, d));
        }
      }
    }
    return new XhtmlComposer(XhtmlComposer.HTML).compose(gen.generate(model, "", 0, null));
  }

  private String getNormativePackageForPage(String page) {
    for (String pn : normativePackages.keySet()) {
      Map<String, PageInfo> m = normativePackages.get(pn);
      for (String s : m.keySet()) {
        if (s.equals(page) || s.replace(".", "-definitions.").equals(page))
          return pn;
      }
    }
    if (normativePages.contains(page))
      return "basic";
    return null;
  }

  private int rootInd(String s) {
    if (s.contains("."))
      s = s.substring(0, s.indexOf("."));
    return !Utilities.isInteger(s) || s.contains("?") ? 100 : Integer.parseInt(s);
  }

  private String generateCSUsage(CodeSystem cs, String prefix) throws Exception {
    StringBuilder b = new StringBuilder();
    for (ValueSet vs : definitions.getValuesets().values()) {
      boolean uses = false;
      for (ConceptSetComponent inc : vs.getCompose().getInclude()) {
        if (inc.hasSystem() && inc.getSystem().equals(cs.getUrl()))
          uses = true;
      }
      for (ConceptSetComponent inc : vs.getCompose().getExclude()) {
        if (inc.hasSystem() && inc.getSystem().equals(cs.getUrl()))
          uses = true;
      }
      if (uses) {
        if (vs.hasUserData("external.url"))
          b.append(" <li>ValueSet: <a href=\"").append(vs.getUserString("external.url")).append("\">").append(vs.present()).append("</a> (").append(Utilities.escapeXml(vs.getDescription())).append(")</li>\r\n");
        else if (!vs.hasUserData("path"))
          b.append(" <li>ValueSet: <a href=\"").append(prefix+"valueset-"+vs.getId()).append("\">").append(vs.present()).append("</a> (").append(Utilities.escapeXml(vs.getDescription())).append(")</li>\r\n");
        else
          b.append(" <li>ValueSet: <a href=\"").append(prefix+vs.getUserString("path")).append("\">").append(vs.present()).append("</a> (").append(Utilities.escapeXml(vs.getDescription())).append(")</li>\r\n");
      }
    }
    if (b.length() == 0)
      return "<p>\r\nThis Code system is not currently used\r\n</p>\r\n";
    else
      return "<p>\r\nThis Code system is used in the following value sets:\r\n</p>\r\n<ul>\r\n"+b.toString()+"</ul>\r\n";
  }

  private String generateValueSetUsage(ValueSet vs, String prefix, boolean addTitle) throws Exception {
    List<String> items = new ArrayList<>();
    if (vs.hasUrl()) {
      for (CodeSystem cs : getCodeSystems().values()) {
        if (cs != null) {
          if (vs.getUrl().equals(cs.getValueSet())) {
            addItem(items, "<li>CodeSystem: This value set is the designated 'entire code system' value set for <a href=\""+ prefix+cs.getUserString("path") + "\">"+cs.getName()+"</a> "+"</li>\r\n");
          }
        }
      }
    }

    for (ConceptMap cm : getConceptMaps().values()) {
      if (cm.hasSourceUriType() && cm.getSourceUriType().equals(vs.getUrl())) {
        addItem(items, "<li>ConceptMap: Translation source in <a href=\""+prefix+cm.getUserString("path")+"\">"+cm.present()+"</a> "+"</li>\r\n");
      } else if (cm.hasSourceCanonicalType() && (cm.getSourceCanonicalType().getValue().equals(vs.getUrl()) || vs.getUrl().endsWith("/"+cm.getSourceCanonicalType().getValue()))) {
        addItem(items, "<li>ConceptMap: Translation source in <a href=\""+prefix+cm.getUserString("path")+"\">"+cm.getName()+"</a> "+"</li>\r\n");
      }
    }
    for (ConceptMap cm : getConceptMaps().values()) {
      if (cm.hasTargetUriType() && cm.getTargetUriType().equals(vs.getUrl())) {
        addItem(items, "<li>ConceptMap: Translation target in <a href=\""+prefix+cm.getUserString("path")+"\">"+cm.present()+"</a> "+"</li>\r\n");
      } else if (cm.hasTargetCanonicalType() && (cm.getTargetCanonicalType().getValue().equals(vs.getUrl()) || vs.getUrl().endsWith("/"+cm.getTargetCanonicalType().getValue()))) {
        addItem(items, "<li>ConceptMap: Translation target ConceptMap <a href=\""+prefix+cm.getUserString("path")+"\">"+cm.getName()+"</a> "+"</li>\r\n");
      }
    }

    for (ResourceDefn r : definitions.getBaseResources().values()) {
      scanForUsage(items, vs, r.getRoot(), r.getName().toLowerCase()+"-definitions.html", prefix);
      scanForOperationUsage(items, vs, r, r.getName().toLowerCase()+"-operation-", prefix);
      scanForProfileUsage(items, vs, r, prefix);
    }
    for (ResourceDefn r : definitions.getResources().values()) {
      scanForUsage(items, vs, r.getRoot(), r.getName().toLowerCase()+"-definitions.html", prefix);
      scanForOperationUsage(items, vs, r, r.getName().toLowerCase()+"-operation-", prefix);
      scanForProfileUsage(items, vs, r, prefix);
    }
    for (ElementDefn e : definitions.getInfrastructure().values()) {
        scanForUsage(items, vs, e, definitions.getSrcFile(e.getName())+"-definitions.html", prefix);
    }
    for (ElementDefn e : definitions.getTypes().values())
      if (!definitions.dataTypeIsSharedInfo(e.getName())) {
        scanForUsage(items, vs, e, definitions.getSrcFile(e.getName())+"-definitions.html", prefix);
      }
    for (ElementDefn e : definitions.getStructures().values())
      if (!definitions.dataTypeIsSharedInfo(e.getName()))
        scanForUsage(items, vs, e, definitions.getSrcFile(e.getName())+"-definitions.html", prefix);


    for (StructureDefinition sd : workerContext.getExtensionDefinitions()) {
      scanForUsage(items, vs, sd, sd.getUserString("path"), prefix);
    }

    for (ValueSet vsi : definitions.getValuesets().values()) {
      String path = (String) vsi.getUserData("path");
      if (vs.hasCompose()) {
        for (ConceptSetComponent t : vs.getCompose().getInclude()) {
          for (UriType uri : t.getValueSet()) {
            if (uri.getValue().equals(vs.getUrl()))
              addItem(items, "<li>ValueSet: Included in <a href=\""+prefix+path+"\">"+Utilities.escapeXml(vs.present())+"</a></li>\r\n");
          }
        }
        for (ConceptSetComponent t : vs.getCompose().getExclude()) {
          for (UriType uri : t.getValueSet()) {
            if (uri.getValue().equals(vs.getUrl()))
              addItem(items, "<li>ValueSet: Excluded from  <a href=\""+prefix+path+"\">"+Utilities.escapeXml(vs.present())+"</a></li>\r\n");
          }
        }
//        for (ConceptSetComponent t : vsi.getCompose().getInclude()) {
//          if (vs.hasCodeSystem() && t.getSystem().equals(vs.getCodeSystem().getSystem()))
//            b.append(" <li>Included in Valueset <a href=\""+prefix+path+"\">"+Utilities.escapeXml(vs.getName())+"</a></li>\r\n");
//        }
//        for (ConceptSetComponent t : vsi.getCompose().getExclude()) {
//          if (vs.hasCodeSystem() && t.getSystem().equals(vs.getCodeSystem().getSystem()))
//            b.append(" <li>Excluded in Valueset <a href=\""+prefix+path+"\">"+Utilities.escapeXml(vs.getName())+"</a></li>\r\n");
//        }
      }
    }
    if (ini.getPropertyNames(vs.getUrl()) != null) {
      for (String n : ini.getPropertyNames(vs.getUrl()))
        addItem(items, "<li>"+ini.getStringProperty(vs.getUrl(), n)+"</li>\r\n");
    }
    if (items.size() == 0)
      return "<p>\r\nThis value set is not currently used\r\n</p>\r\n";
    else {
      StringBuilder b = new StringBuilder();
      for (String s : items)
        b.append(" " +s);
      return (addTitle ? "<p>\r\nThis value set is used in the following places:\r\n</p>\r\n" : "")+"<ul>\r\n"+b.toString()+"</ul>\r\n";
    }
  }

  private void addItem(List<String> items, String value) {
    if (!items.contains(value))
      items.add(value);
  }

  private void scanForUsage(List<String> items, ValueSet vs, StructureDefinition exd, String path, String prefix) {
    for (ElementDefinition ed : exd.getSnapshot().getElement()) {
      if (ed.hasBinding()) {
        if (isValueSetMatch(ed.getBinding().getValueSet(), vs))
          addItem(items, "<li>Extension: <a href=\""+prefix+path+"\">"+exd.getUrl()+": "+Utilities.escapeXml(exd.getName())+"</a> ("+ed.typeSummary()+" / "+getBindingTypeDesc(ed.getBinding(), prefix)+")</li>\r\n");
      }
    }
  }

  private void scanForOperationUsage(List<String> items, ValueSet vs, ResourceDefn r, String page, String prefix) {
    for (Operation op : r.getOperations()) {
      for (OperationParameter p : op.getParameters()) {
        if (p.getBs() != null && p.getBs().getValueSet() == vs) {
          addItem(items, "<li>Operation: <a href=\""+prefix+page+op.getName()+".html"+"\"> Parameter $"+op.getName()+"."+p.getName()+"</a> ("+p.getFhirType()+" /: "+getBindingTypeDesc(p.getBs(), prefix)+")</li>\r\n");
        }
      }
    }
  }

  private void scanForProfileUsage(List<String> items, ValueSet vs, ResourceDefn r, String prefix) {
    for (Profile ap : r.getConformancePackages()) {
      for (ConstraintStructure p : ap.getProfiles()) {
        for (ElementDefinition ed : p.getResource().getSnapshot().getElement()) {
          if (ed.hasBinding()) {
            if (isValueSetMatch(ed.getBinding().getValueSet(), vs))
              addItem(items, "<li>Profile: <a href=\""+prefix+p.getId()+".html\"> "+p.getTitle()+": "+ed.getPath()+"</a> ("+ed.typeSummary()+" / "+getBindingTypeDesc(ed.getBinding(), prefix)+")</li>\r\n");
          }
        }
      }
    }
  }

  private boolean isValueSetMatch(String ref, ValueSet vs) {
    if (ref == null)
      return false;
    return ref.endsWith("/"+vs.getId());
  }

  private String getBindingTypeDesc(ElementDefinitionBindingComponent binding, String prefix) {
    if (binding.getStrength() == null)
      return "";
    else
      return "<a href=\""+prefix+"terminologies.html#"+binding.getStrength().toCode()+"\">"+binding.getStrength().getDisplay()+"</a>";
  }

  private String getBindingTypeDesc(BindingSpecification binding, String prefix) {
    if (binding.hasMax())
      throw new Error("Max binding not handled yet");
    if (binding.getStrength() == null)
      return "";
    else
      return "(<a href=\""+prefix+"terminologies.html#"+binding.getStrength().toCode()+"\">"+binding.getStrength().getDisplay()+"</a>)";
  }

  private void scanForUsage(List<String> items, ValueSet vs, ElementDefn e, String ref, String prefix) {
    scanForUsage(items, vs, e, "", ref, prefix);

  }

  private void scanForUsage(List<String> items, ValueSet vs, ElementDefn e, String path, String ref, String prefix) {
    path = path.equals("") ? e.getName() : path+"."+e.getName();
    if (e.hasBinding() && e.getBinding().getValueSet() == vs) {
      addItem(items, "<li>Resource: <a href=\""+prefix+ref+"#"+path+"\">"+path+"</a> "+getBSTypeDesc(e, e.getBinding(), prefix)+"</li>\r\n");
    }
    if (e.hasBinding() && e.getBinding().getMaxValueSet() == vs) {
      addItem(items, "<li>Max ValueSet: <a href=\""+prefix+ref+"#"+path+"\">"+path+"</a> "+getBSTypeDesc(e, e.getBinding(), prefix)+"</li>\r\n");
    }
    for (ElementDefn c : e.getElements()) {
      scanForUsage(items, vs, c, path, ref, prefix);
    }
  }

  private String getBSTypeDesc(ElementDefn ed, BindingSpecification cd, String prefix) {
    if (cd == null || cd.getStrength() == null) // partial build
      return "Unknown";
    return "("+ed.typeCode()+" / <a href=\""+prefix+"terminologies.html#"+cd.getStrength().toCode()+"\">"+cd.getStrength().getDisplay()+"</a>)";
  }

  private String generateCodeDefinition(String name) {
    throw new Error("fix this");
//    BindingSpecification cd = definitions.getBindingByURL("#"+name);
//    return Utilities.escapeXml(cd.getDefinition());
  }

  private String generateValueSetDefinition(String name) {
    throw new Error("fix this");
//    BindingSpecification cd = definitions.getBindingByURL(name);
//    if (cd == null)
//      return definitions.getExtraValuesets().get(name).getDescription();
//    else
//      return Utilities.escapeXml(cd.getDefinition());
  }

  private void generateCode(BindingSpecification cd, StringBuilder s, boolean hasSource, boolean hasId, boolean hasComment, boolean hasDefinition, boolean hasParent, int level, DefinedCode c) {
    String id = hasId ? "<td>"+fixNull(c.getId())+"</td>" : "";
    String src = "";
    if (hasSource) {
      if (Utilities.noString(c.getSystem())) {
        src = "<td></td>";
      } else {
        String url = c.getSystem();
        url = fixUrlReference(url);
        src = "<td><a href=\""+url+"\">"+codeSystemDescription(c.getSystem())+"</a></td>";
      }
    }
    String lvl = hasParent ? "<td>"+Integer.toString(level)+"</td>" : "";
    String indent = "";
    for (int i = 1; i < level; i++)
      indent = indent + "&nbsp;&nbsp;";
    if (hasComment)
      s.append("    <tr>"+id+src+lvl+"<td>"+indent+Utilities.escapeXml(c.getCode())+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>"+Utilities.escapeXml(c.getComment())+"</td></tr>\r\n");
    else if (hasDefinition)
      s.append("    <tr>"+id+src+lvl+"<td>"+indent+Utilities.escapeXml(c.getCode())+"</td><td colspan=\"2\">"+Utilities.escapeXml(c.getDefinition())+"</td></tr>\r\n");
    else
      s.append("    <tr>"+id+src+lvl+"<td colspan=\"3\">"+indent+Utilities.escapeXml(c.getCode())+"</td></tr>\r\n");

    for (DefinedCode ch : c.getChildCodes()) {
      generateCode(cd, s, hasSource, hasId, hasComment, hasDefinition, hasParent, level+1, ch);
    }
  }

  private String fixNull(String id) {
    return id == null ? "" : id;
  }

  private String codeSystemDescription(String system) {
    return system.substring(system.lastIndexOf("/")+1);
  }

  private String genProfileConstraints(StructureDefinition res) throws Exception {
    StringBuilder b = new StringBuilder();
    for (ElementDefinition e : res.getSnapshot().getElement()) {
      for (ElementDefinitionConstraintComponent inv : e.getConstraint()) {
        if (!e.getPath().contains("."))
          b.append("<li><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b>: "+Utilities.escapeXml(inv.getHuman())+" (xpath: <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
        else
          b.append("<li><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b>: On "+e.getPath()+": "+Utilities.escapeXml(inv.getHuman())+" (xpath on "+presentPath(e.getPath())+": <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
      }
    }
    if (b.length() > 0)
      return "<p>Constraints</p><ul>"+b+"</ul>";
    else
      return "";
  }

  private String genExtensionConstraints(StructureDefinition ed) throws Exception {
    StringBuilder b = new StringBuilder();
    for (ElementDefinition e : ed.getSnapshot().getElement()) {
      for (ElementDefinitionConstraintComponent inv : e.getConstraint()) {
        if (!e.getPath().contains("."))
          b.append("<li><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b>: "+Utilities.escapeXml(inv.getHuman())+" (xpath: <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
        else
          b.append("<li><b title=\"Formal Invariant Identifier\">"+inv.getKey()+"</b>: On "+e.getPath()+": "+Utilities.escapeXml(inv.getHuman())+" (xpath on "+presentPath(e.getPath())+": <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getXpath())+"</span>)</li>");
      }
    }
    if (b.length() > 0)
      return "<p>Constraints</p><ul>"+b+"</ul>";
    else
      return "";
  }

  private String genResourceTable(ResourceDefn res, String prefix) throws Exception {
    ResourceTableGenerator gen = new ResourceTableGenerator(folders.dstDir, this, res.getName()+"-definitions.html", false);
    return new XhtmlComposer(XhtmlComposer.HTML).compose(gen.generate(res, prefix));
  }

  private String genResourceConstraints(ResourceDefn res, String prefix) throws Exception {
    ElementDefn e = res.getRoot();
    Map<String, String> invs = new HashMap<String, String>();
    generateConstraintsTable(res.getName(), e, invs, true, prefix);
    List<String> ids = new ArrayList<String>();
    for (String n : invs.keySet()) {
      ids.add(n);
    }
    Collections.sort(ids, new ConstraintsSorter());
    StringBuilder b = new StringBuilder();
    for (String n : ids) {
      b.append(invs.get(n));
    }
    if (b.length() > 0)
      return "<a name=\"invs\"> </a>\r\n<h3>Constraints</h3><table class=\"grid\"><tr><td width=\"60\"><b>id</b></td><td><b>Level</b></td><td><b>Location</b></td><td><b>Description</b></td><td><b><a href=\""+prefix+"fhirpath.html\">Expression</a></b></td></tr>"+b+"</table>";
    else
      return "";
  }

  private String genRestrictions(String name) throws Exception {
    StringBuilder b = new StringBuilder();
    StringBuilder b2 = new StringBuilder();
    for (ProfiledType c : definitions.getConstraints().values()) {
      if (c.getBaseType().equals(name)) {
        b.append("<a name=\""+c.getName()+"\"> </a><a name=\""+c.getName().toLowerCase()+"\"> </a>\r\n");
        b2.append(" <tr><td>"+c.getName()+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>StructureDefinition (<a href=\""+c.getName().toLowerCase()+
            ".profile.xml.html\">XML</a>, <a href=\""+c.getName().toLowerCase()+".profile.json.html\">JSON</a>)</td>");
        b2.append("<td>"+genDataTypeUsage(c.getName())+"</td>");
        b2.append("</tr>\r\n");
      }
    }
    if (b.length() > 0)
      return b.toString()+"<table class=\"list\">\r\n"+b2.toString()+"</table>\r\n";
    else
      return "";
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

  private String genConstraints(String name, String prefix) throws Exception {
    Map<String, String> invs = new HashMap<String, String>();
    if (definitions.getConstraints().containsKey(name)) {
      ProfiledType cnst = definitions.getConstraints().get(name);
      generateConstraintsTable(name, cnst, invs, true, prefix);
    } else {
      ElementDefn e = definitions.getElementDefn(name);
      generateConstraintsTable(name, e, invs, true, prefix);
    }
    List<String> ids = new ArrayList<String>();
    for (String n : invs.keySet()) {
      ids.add(n);
    }
    Collections.sort(ids, new ConstraintsSorter());
    StringBuilder b = new StringBuilder();
    for (String n : ids) {
      b.append(invs.get(n));
    }
    if (b.length() > 0)
      return "<a name=\""+name+"-inv\"> </a><table class=\"grid\"><tr><td width=\"60\"><b>id</b></td><td><b>Level</b></td><td><b>Location</b></td><td><b>Description</b></td><td><b><a href=\""+prefix+"fhirpath.html\">Expression</a></b></td></tr>"+b+"</table>";
    else
      return "";
  }

  private void generateConstraints(String path, ElementDefn e, Map<String, String> invs, boolean base, String prefix) throws Exception {
    for (Invariant inv : e.getInvariants().values()) {
      String s = "";
      if (base)
        s = "<li>"+presentLevel(inv)+" <b title=\"Formal Invariant Identifier\">"+inv.getId()+"</b>: "+Utilities.escapeXml(inv.getEnglish())+" (<a href=\"http://hl7.org/fhirpath\">expression</a>: <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getExpression())+"</span>)";
      else
        s = "<li>"+presentLevel(inv)+" <b title=\"Formal Invariant Identifier\">"+inv.getId()+"</b>: On "+path+": "+Utilities.escapeXml(inv.getEnglish())+" (<a href=\"http://hl7.org/fhirpath\">expression</a> on "+presentPath(path)+": <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getExpression())+"</span>)";
      if (!Utilities.noString(inv.getExplanation())) 
        s = s + ". This is (only) a best practice guideline because: <blockquote>"+processMarkdown("best practice guideline", inv.getExplanation(), prefix)+"</blockquote>";
      invs.put(inv.getId(), s+"</li>");
    }
    for (ElementDefn c : e.getElements()) {
      generateConstraints(path + "." + c.getName(), c, invs, false, prefix);
    }
  }

  private void generateConstraintsTable(String path, ElementDefn e, Map<String, String> invs, boolean base, String prefix) throws Exception {
    for (Invariant inv : e.getInvariants().values()) {
      String s = "";
      if (base)
        s = "<tr><td><b title=\"Formal Invariant Identifier\">"+inv.getId()+"</b></td><td>"+presentLevel(inv)+"</td><td>(base)</td><td>"+Utilities.escapeXml(inv.getEnglish())+"</td><td><span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getExpression())+"</span>";
      else
        s = "<tr><td><b title=\"Formal Invariant Identifier\">"+inv.getId()+"</b></td><td>"+presentLevel(inv)+"</td><td>"+path+"</td><td>"+Utilities.escapeXml(inv.getEnglish())+"</td><td><span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(inv.getExpression())+"</span>";
      if (!Utilities.noString(inv.getExplanation())) 
        s = s + "<br/>This is (only) a best practice guideline because: <blockquote>"+processMarkdown("best practice guideline", inv.getExplanation(), prefix)+"</blockquote>";
      s = s + "</td></tr>";
      invs.put(inv.getId(), s);
    }
    for (ElementDefn c : e.getElements()) {
      generateConstraintsTable(path + "." + c.getName(), c, invs, false, prefix);
    }
  }

  private String presentLevel(Invariant inv) {
    if ("warning".equals(inv.getSeverity()))
      return "<a href=\"conformance-rules.html#warning\" style=\"color: Chocolate\">Warning</a> ";
    if ("best-practice".equals(inv.getSeverity()))
      return "<a href=\"conformance-rules.html#best-practice\" style=\"color: DarkGreen\">Guideline</a> ";
    return "<a href=\"conformance-rules.html#rule\" style=\"color: Maroon\">Rule</a> ";
  }

  private void generateConstraints(String path, ProfiledType pt, Map<String, String> invs, boolean base, String prefix) {
    invs.put("sqty-1", "<li><a href=\"conformance-rules.html#rule\" style=\"color: Maroon\">Rule</a> <b title=\"Formal Invariant Identifier\">sqty-1</b>: "+Utilities.escapeXml(pt.getInvariant().getEnglish())+" (<a href=\"http://hl7.org/fhirpath\">expression</a>: <span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(pt.getInvariant().getExpression())+"</span>)</li>");
  }
  private void generateConstraintsTable(String path, ProfiledType pt, Map<String, String> invs, boolean base, String prefix) {
    String s = definitions.getTLAs().get(pt.getName().toLowerCase());
    invs.put(s+"-1", "<tr><td><b title=\"Formal Invariant Identifier\">"+s+"-1</b></td><td><a href=\"conformance-rules.html#rule\" style=\"color: Maroon\">Rule</a></td><td>(base)</td><td>"+Utilities.escapeXml(pt.getInvariant().getEnglish())+"</td><td><span style=\"font-family: Courier New, monospace\">"+Utilities.escapeXml(pt.getInvariant().getExpression())+"</span>)</td></tr>");
  }

  private String presentPath(String path) {
//    String[] parts = path.split("\\.");
//    StringBuilder s = new StringBuilder();
//    for (String p : parts) {
//      if (s.length() > 0)
//        s.append("/");
//      s.append("f:" + p);
//    }
//    return s.toString();
      return path;
  }

  private String pageHeader(String n) {
    return "<div class=\"navtop\"><ul class=\"navtop\"><li class=\"spacerright\" style=\"width: 500px\"><span>&nbsp;</span></li></ul></div>\r\n";
  }

  private String dtHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Data Types", "datatypes.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "datatypes-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "datatypes-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "datatypes-mappings.html", mode==null || "mappings".equals(mode)));
    b.append(makeHeaderTab("Profiles and Extensions", "datatypes-extras.html", mode==null || "extras".equals(mode)));
    b.append(makeHeaderTab("R3 Conversions", "datatypes-version-maps.html", mode==null || "conversions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String mdtHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("MetaData Types", "metadatatypes.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "metadatatypes-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "metadatatypes-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "metadatatypes-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String mmHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Module Metadata", "modulemetadata.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "modulemetadata-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "modulemetadata-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "modulemetadata-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String cdHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Contact Detail", "contactdetail.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "contactdetail-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "contactdetail-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "contactdetail-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String diHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Dosage Instruction Detail", "dosage.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "dosage-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "dosage-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "dosage-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String ctHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Contributor", "contributor.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "contributor-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "contributor-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "contributor-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String ucHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("UsageContext", "usagecontext.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "usagecontext-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "usagecontext-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "usagecontext-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String rrHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("RelatedResource", "relatedartifact.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "relatedartifact-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "relatedartifact-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "relatedartifact-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String drHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Data Requirement", "datarequirement.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "datarequirement-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "datarequirement-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "datarequirement-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String adHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Action Definition", "actiondefinition.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "actiondefinition-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "actiondefinition-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "actiondefinition-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String pdHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Parameter Definition", "parameterdefinition.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "parameterdefinition-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "parameterdefinition-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "parameterdefinition-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String tdHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Trigger Definition", "triggerdefinition.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "triggerdefinition-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "triggerdefinition-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "triggerdefinition-mappings.html", mode==null || "mappings".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String edHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Element Definition", "elementdefinition.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "elementdefinition-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "elementdefinition-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", "elementdefinition-mappings.html", mode==null || "mappings".equals(mode)));
    b.append(makeHeaderTab("Extensions", "elementdefinition-extras.html", mode==null || "extensions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String elHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Element", "element.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "element-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Extensions", "element-extras.html", mode==null || "extensions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String belHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Element", "backboneelement.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "backboneelement-definitions.html", mode==null || "definitions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String extHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Extensibility", "extensibility.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Defining Extensions", "defining-extensions.html", mode==null || "defining".equals(mode)));
    b.append(makeHeaderTab("Examples", "extensibility-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "extensibility-definitions.html", mode==null || "definitions".equals(mode)));
    b.append(makeHeaderTab("Registry", "extensibility-registry.html", mode==null || "registry".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String narrHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Narrative", "narrative.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "narrative-example.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "narrative-definitions.html", mode==null || "definitions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String profilesHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Profiling FHIR", "profiling.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "profiling-examples.html", mode==null || "examples".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String resourcesHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Resource Definitions", "resource.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Examples", "resources-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "resources-definitions.html", mode==null || "definitions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String refHeader(String mode) {
    StringBuilder b = new StringBuilder();
    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("References", "references.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", "references-definitions.html", mode==null || "definitions".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }



//  private String resourcesHeader(String n, String mode) {
//      if (n.contains("-"))
//      n = n.substring(0, n.indexOf('-'));
//    StringBuilder b = new StringBuilder();
//    b.append("<div class=\"navtop\">");
//    b.append("<ul class=\"navtop\"><li class=\"spacerleft\"><span>&nbsp;</span></li>");
//    if (mode == null || mode.equals("content"))
//      b.append("<li class=\"selected\"><span>Content</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+".html\">Content</a></span></li>");
//    if ("definitions".equals(mode))
//      b.append("<li class=\"selected\"><span>Detailed Descriptions</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-definitions.html\">Detailed Descriptions</a></span></li>");
//    b.append("<li class=\"spacerright\" style=\"width: 270px\"><span>&nbsp;</span></li>");
//    b.append("</ul></div>\r\n");
//    return b.toString();
//  }

//  private String formatsHeader(String n, String mode) {
//    if (n.contains("-"))
//      n = n.substring(0, n.indexOf('-'));
//    StringBuilder b = new StringBuilder();
//    b.append("<div class=\"navtop\">");
//    b.append("<ul class=\"navtop\"><li class=\"spacerleft\"><span>&nbsp;</span></li>");
//    if (mode == null || mode.equals("content"))
//      b.append("<li class=\"selected\"><span>Content</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+".html\">Content</a></span></li>");
//    if ("examples".equals(mode))
//      b.append("<li class=\"selected\"><span>Examples</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-examples.html\">Examples</a></span></li>");
//    if ("definitions".equals(mode))
//      b.append("<li class=\"selected\"><span>Detailed Descriptions</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-definitions.html\">Detailed Descriptions</a></span></li>");
//    b.append("<li class=\"spacerright\" style=\"width: 270px\"><span>&nbsp;</span></li>");
//    b.append("</ul></div>\r\n");
//    return b.toString();
//  }

  private String profileHeader(String n, String mode, boolean hasExamples) {
    StringBuilder b = new StringBuilder();

    if (n.endsWith(".xml"))
      n = n.substring(0, n.length()-4);

    b.append("<ul class=\"nav nav-tabs\">");

    b.append(makeHeaderTab("Content", n+".html", mode==null || "base".equals(mode)));
    if (hasExamples)
      b.append(makeHeaderTab("Examples", n+"-examples.html", mode==null || "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
//    if (!isDict && !n.equals("elementdefinition-de")) // todo: do this properly
//      b.append(makeHeaderTab("HTML Form", n+"-questionnaire.html", "questionnaire".equals(mode)));
    b.append(makeHeaderTab("XML", n+".profile.xml.html", "xml".equals(mode)));
    b.append(makeHeaderTab("JSON", n+".profile.json.html", "json".equals(mode)));

    b.append("</ul>\r\n");

    return b.toString();
  }

  private String dictHeader(String n, String mode) {
    StringBuilder b = new StringBuilder();

    if (n.endsWith(".xml"))
      n = n.substring(0, n.length()-4);

    b.append("<ul class=\"nav nav-tabs\">");

    b.append(makeHeaderTab("Content", n+".html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("XML", n+".xml.html", "xml".equals(mode)));
    b.append(makeHeaderTab("JSON", n+".json.html", "json".equals(mode)));

    b.append("</ul>\r\n");

    return b.toString();
  }

  private String extDefnHeader(String n, String mode) {
    StringBuilder b = new StringBuilder();

    if (n.endsWith(".xml"))
      n = n.substring(0, n.length()-4);

    b.append("<ul class=\"nav nav-tabs\">");

    b.append(makeHeaderTab("Content", n+".html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
    b.append(makeHeaderTab("XML", n+".xml.html", "xml".equals(mode)));
    b.append(makeHeaderTab("JSON", n+".json.html", "json".equals(mode)));

    b.append("</ul>\r\n");

    return b.toString();
  }

  private String txHeader(String n, String mode) {
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));
    StringBuilder b = new StringBuilder();
    String pfx = "";
    if ("l1".equals(mode))
      pfx = "../";
    if ("l2".equals(mode))
      pfx = "../../";
    if ("l3".equals(mode))
      pfx = "../../../";

    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Using Codes", pfx + "terminologies.html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("Code Systems", pfx + "terminologies-systems.html", "systems".equals(mode)));
    b.append(makeHeaderTab("Value Sets", pfx + "terminologies-valuesets.html", "valuesets".equals(mode)));
    b.append(makeHeaderTab("Concept Maps", pfx + "terminologies-conceptmaps.html", "conceptmaps".equals(mode)));
    b.append(makeHeaderTab("Identifier Systems", pfx + "identifier-registry.html", "idsystems".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String fmtHeader(String n, String mode) {
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));
    StringBuilder b = new StringBuilder();
    String pfx = "";
    if ("l1".equals(mode))
      pfx = "../";
    if ("l2".equals(mode))
      pfx = "../../";
    if ("l3".equals(mode))
      pfx = "../../../";

    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Formats", pfx + "formats.html", mode==null || "base".equals(mode)));
    b.append(makeHeaderTab("XML", pfx + "xml.html", "xml".equals(mode)));
    b.append(makeHeaderTab("JSON", pfx + "json.html", "json".equals(mode)));
    b.append(makeHeaderTab("ND-JSON", pfx + "nd-json.html", "ndjson".equals(mode)));
    b.append(makeHeaderTab("RDF", pfx + "rdf.html", "rdf".equals(mode)));
    b.append("</ul>\r\n");
    return b.toString();
  }

  private String cmpHeader(String n, String mode) {
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));
    StringBuilder b = new StringBuilder();
    String pfx = "";
    if ("l1".equals(mode))
      pfx = "../";
    if ("l2".equals(mode))
      pfx = "../../";
    if ("l3".equals(mode))
      pfx = "../../../";

    b.append("<ul class=\"nav nav-tabs\">");
    b.append(makeHeaderTab("Comparison Appendix", pfx + "comparison.html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("V2 Messaging", pfx + "comparison-v2.html", "v2".equals(mode)));
    b.append(makeHeaderTab("V3 (Messaging)", pfx + "comparison-v3.html", "v3".equals(mode)));
    b.append(makeHeaderTab("CDA", pfx + "comparison-cda.html", "cda".equals(mode)));
    b.append(makeHeaderTab("Other", pfx + "comparison-other.html", "misc".equals(mode)));

    b.append("</ul>\r\n");
    return b.toString();
  }

//  private String atomHeader(String n, String mode) {
//    if (n.contains("-"))
//      n = n.substring(0, n.indexOf('-'));
//    StringBuilder b = new StringBuilder();
//    b.append("<div class=\"navtop\">");
//    b.append("<ul class=\"navtop\"><li class=\"spacerleft\"><span>&nbsp;</span></li>");
//    if (mode == null || mode.equals("content"))
//      b.append("<li class=\"selected\"><span>Content</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+".html\">Content</a></span></li>");
//    if ("examples".equals(mode))
//      b.append("<li class=\"selected\"><span>Examples</span></li>");
//    else
//      b.append("<li class=\"nselected\"><span><a href=\""+n+"-examples.html\">Examples</a></span></li>");
//    b.append("<li class=\"spacerright\" style=\"width: 370px\"><span>&nbsp;</span></li>");
//    b.append("</ul></div>\r\n");
//    return b.toString();
//  }

  private String codelist(CodeSystem cs, String mode, boolean links, boolean heading, String source) throws Exception {
    if (cs == null)
      cs = definitions.getCodeSystems().get(mode);
    if (cs == null)
      throw new Exception("No Code system for "+mode+" from "+source);
    boolean hasComments = false;
    for (ConceptDefinitionComponent c : cs.getConcept())
      hasComments = hasComments || checkHasComment(c);

    StringBuilder b = new StringBuilder();
    if (heading && !Utilities.noString(cs.getDescription()))
      b.append("<h3>"+cs.getDescription()+"</h3>\r\n");
    b.append("<table class=\"codes\">\r\n");
    for (ConceptDefinitionComponent c : cs.getConcept()) {
      genCodeItem(links, hasComments, b, c);
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private void genCodeItem(boolean links, boolean hasComments, StringBuilder b, ConceptDefinitionComponent c) {
    if (hasComments)
      b.append(" <tr><td>"+(links ? "<a href=\"#"+c.getCode()+"\">"+c.getCode()+"</a>" : c.getCode())+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>"+Utilities.escapeXml(ToolingExtensions.getCSComment(c))+"</td></tr>\r\n");
    else
      b.append(" <tr><td>"+(links ? "<a href=\"#"+c.getCode()+"\">"+c.getCode()+"</a>" : c.getCode())+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>\r\n");
    for (ConceptDefinitionComponent cc : c.getConcept()) {
      genCodeItem(links, hasComments, b, cc);
    }
  }

  private boolean checkHasComment(ConceptDefinitionComponent c) {
    if (ToolingExtensions.getCSComment(c) != null)
      return true;
    for (ConceptDefinitionComponent cc : c.getConcept())
      if (checkHasComment(cc))
        return true;
    return false;
  }

  private String codetoc(String n) throws Exception {
    CodeSystem cs = definitions.getCodeSystems().get(n);
    if (cs == null)
      throw new Exception("Unable to find code system '"+n+"'");

    StringBuilder b = new StringBuilder();
    for (ConceptDefinitionComponent c : cs.getConcept())
      b.append("<a href=\"#"+c.getCode()+"\">"+c.getDisplay()+"</a><br/>\r\n");
    return b.toString();
  }

  private String makeHeaderTab(String tabName, String path, Boolean selected)
  {
    StringBuilder b = new StringBuilder();

    if(!selected)
    {
      b.append("<li>");
      b.append(String.format("<a href=\"%s\">%s</a>", path, tabName));
    }
    else
    {
      b.append("<li class=\"active\">");
      b.append(String.format("<a href=\"#\">%s</a>", tabName));
    }

    b.append("</li>");

    return b.toString();
  }

  private String resHeader(String n, String title, String mode) throws Exception {
    StringBuilder b = new StringBuilder();
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));

    ResourceDefn res = definitions.getResourceByName(title);
    boolean hasOps = !res.getOperations().isEmpty();
    boolean isAbstract = res.isAbstract();
    b.append("<ul class=\"nav nav-tabs\">");

    b.append(makeHeaderTab("Content", n+".html", mode==null || "content".equals(mode)));
    if (!isAbstract)
      b.append(makeHeaderTab("Examples", n+"-examples.html", "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    if (!isAbstract)
      b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
    if (!isAbstract)
      b.append(makeHeaderTab("Profiles &amp; Extensions", n+"-profiles.html", "profiles".equals(mode)));
//    if (!isAbstract)
//      b.append(makeHeaderTab("HTML Form", n+"-questionnaire.html", "questionnaire".equals(mode)));
    if (hasOps)
      b.append(makeHeaderTab("Operations", n+"-operations.html", "operations".equals(mode)));
    if (new File(Utilities.path(folders.rootDir, "implementations", "r3maps", "R4toR3", title+".map")).exists())
      b.append(makeHeaderTab("R3 Conversions", n+"-version-maps.html", "conversion".equals(mode)));
    b.append("</ul>\r\n");

    return b.toString();
  }

  private String lmHeader(String n, String title, String mode, boolean hasXMlJson) throws Exception {
    StringBuilder b = new StringBuilder();
    n = n.toLowerCase();

    b.append("<ul class=\"nav nav-tabs\">");

    b.append(makeHeaderTab("Content", n+".html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("Implementations", n+"-implementations.html", "examples".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    b.append(makeHeaderTab("Mappings", n+"-mappings.html", "mappings".equals(mode)));
    if (hasXMlJson) {
      b.append(makeHeaderTab("XML", n+".profile.xml.html", "xml".equals(mode)));
      b.append(makeHeaderTab("JSON", n+".profile.json.html", "json".equals(mode)));
    }
    b.append("</ul>\r\n");

    return b.toString();
  }

  private String abstractResHeader(String n, String title, String mode) throws Exception {
    StringBuilder b = new StringBuilder();
    if (n.contains("-"))
      n = n.substring(0, n.indexOf('-'));

    boolean hasOps = !definitions.getResourceByName(title).getOperations().isEmpty();
    boolean hasExamples = !definitions.getResourceByName(title).getExamples().isEmpty();
    b.append("<ul class=\"nav nav-tabs\">");

    b.append(makeHeaderTab("Content", n+".html", mode==null || "content".equals(mode)));
    b.append(makeHeaderTab("Detailed Descriptions", n+"-definitions.html", "definitions".equals(mode)));
    if (hasExamples)
      b.append(makeHeaderTab("Examples", n+"-examples.html", "operations".equals(mode)));
    if (hasOps)
      b.append(makeHeaderTab("Operations", n+"-operations.html", "operations".equals(mode)));

    b.append("</ul>\r\n");

    return b.toString();
  }

  private String genCodeSystemsTable() throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getCodeSystems().keySet());


//    for (String n : definitions.getBindings().keySet()) {
//      if ((definitions.getBindingByName(n).getBinding() == Binding.CodeList && !definitions.getBindingByName(n).getVSSources().contains("")) ||
//          (definitions.getBindingByName(n).getBinding() == Binding.Special))
//        names.add(definitions.getBindingByName(n).getReference().substring(1));
//    }
//
////  not this one      Logical Interactions (RESTful framework)  http://hl7.org/fhir/rest-operations 2.16.840.1.113883.6.308
//    s.append(" <tr><td><a href=\""+cd.getReference().substring(1)+".html\">http://hl7.org/fhir/"+cd.getReference().substring(1)+"</a></td><td>"+Utilities.escapeXml(cd.getDefinition())+"</td></tr>\r\n");
//
    Collections.sort(names);
    for (String n : names) {
      if (n.startsWith("http://hl7.org") && !n.startsWith("http://terminology.hl7.org/CodeSystem/v2") && !n.startsWith("http://terminology.hl7.org/CodeSystem/v3")) {
        // BindingSpecification cd = definitions.getBindingByReference("#"+n);
        CodeSystem ae = definitions.getCodeSystems().get(n);
        if (ae == null)
          s.append(" <tr><td><a href=\"" + "??" + ".html\">").append(n).append("</a></td><td>").append("??").append("</td></tr>\r\n");
        else {
          s.append(" <tr><td><a href=\"").append(ae.getUserData("path")).append("\">").append(n).append("</a></td><td>").append(ae.getDescription()).append("</td></tr>\r\n");
        }
      }
    }
    s.append("</table>\r\n");
    return s.toString();
  }

  private String genConceptMapsTable() throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    s.append(" <tr><td><b>Name</b></td><td><b>Source</b></td><td><b>Target</b></td></tr>\r\n");
    List<String> sorts = new ArrayList<String>();
    sorts.addAll(conceptMaps.keySet());
    Collections.sort(sorts);

    for (String sn : sorts) {
      ConceptMap ae = conceptMaps.get(sn);
      //String n = sn.substring(23);
      ConceptMap cm = ae;
      s.append(" <tr><td><a href=\"").append(ae.getUserData("path")).append("\">").append(cm.getName()).append("</a></td>")
              .append("<td><a href=\"").append(getValueSetRef("", cm.hasSourceCanonicalType() ? (cm.getSourceCanonicalType()).getValue() : cm.getSourceUriType().asStringValue())).append("\">").append(describeValueSetByRef(cm.getSource())).append("</a></td>")
              .append("<td><a href=\"").append(getValueSetRef("", cm.hasTargetCanonicalType() ? (cm.getTargetCanonicalType()).getValue() : cm.getTargetUriType().asStringValue())).append("\">").append(describeValueSetByRef(cm.getTarget())).append("</a></td></tr>\r\n");
    }
    s.append("</table>\r\n");
    return s.toString();
  }


  @SuppressWarnings("unchecked")
  private String genIGValueSetsTable() throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    s.append(" <tr><td><b>Name</b></td><td><b>Definition</b></td><td><b>Source</b></td><td></td></tr>\r\n");
    List<String> namespaces = new ArrayList<String>();
    Map<String, ValueSet> vslist = new HashMap<String, ValueSet>();
    for (String sn : igResources.keySet()) {
      if (igResources.get(sn) instanceof ValueSet) {
        vslist.put(sn, (ValueSet) igResources.get(sn));
        String n = getNamespace(sn);
        if (!namespaces.contains(n))
          namespaces.add(n);
      }
    }
    Collections.sort(namespaces);
    for (String n : namespaces)
      generateVSforNS(s, n, definitions.getValuesets(), false, null);
    s.append("</table>\r\n");
    return s.toString();
  }

  private String genValueSetsTable(ImplementationGuideDefn ig) throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    s.append(" <tr><td><b>Name</b></td><td><b>Definition</b></td><td><b>Source</b></td><td><b>Id</b></td></tr>\r\n");
    List<String> namespaces = new ArrayList<String>();
    Map<String, ValueSet> vslist = new HashMap<String, ValueSet>();
    for (String sn : definitions.getValuesets().keySet()) {
      ValueSet vs = definitions.getValuesets().get(sn);
      vslist.put(vs.getUrl(), vs);
      String n = getNamespace(sn);
      if (!n.equals("http://hl7.org/fhir/ValueSet") && !namespaces.contains(n) && !sn.startsWith("http://terminology.hl7.org/ValueSet/v2-") && !sn.startsWith("http://terminology.hl7.org/ValueSet/v3-"))
        namespaces.add(n);
    }
    for (String sn : definitions.getExtraValuesets().keySet()) {
      ValueSet vs = definitions.getExtraValuesets().get(sn);
      vslist.put(vs.getUrl(), vs);
    }
    Collections.sort(namespaces);
    generateVSforNS(s, "http://hl7.org/fhir/ValueSet", vslist, true, ig);
    for (String n : namespaces)
      generateVSforNS(s, n, definitions.getValuesets(), true, ig);
    s.append("</table>\r\n");
    return s.toString();
  }

  private void generateVSforNS(StringBuilder s, String ns, Map<String, ValueSet> vslist, boolean hasId, ImplementationGuideDefn ig) throws FHIRException {
    List<String> sorts = new ArrayList<String>();
    for (String sn : vslist.keySet()) {
      ValueSet vs = vslist.get(sn);
      ImplementationGuideDefn vig = (ImplementationGuideDefn) vs.getUserData(ToolResourceUtilities.NAME_RES_IG);
      if (ig == vig) {
        String n = getNamespace(sn);
        if (ns.equals(n) && !sn.startsWith("http://terminology.hl7.org/ValueSet/v2-") && !sn.startsWith("http://terminology.hl7.org/ValueSet/v3-"))
          sorts.add(sn);
      }
    }
    if (!sorts.isEmpty()) {
      s.append(" <tr><td colspan=\"5\" style=\"background: #DFDFDF\"><b>Namespace: </b>"+ns+"</td></tr>\r\n");
      Collections.sort(sorts);
      for (String sn : sorts) {
        ValueSet ae = vslist.get(sn);
        String n = getTail(sn);
        ValueSet vs = ae;
        if (wantPublish(vs)) {
          String path = ae.hasUserData("external.url") ? ae.getUserString("external.url") : pathTail(Utilities.changeFileExt(ae.getUserString("path"), ".html"));
          s.append(" <tr><td><a href=\""+path+"\">"+n+"</a>");
          if (StandardsStatus.NORMATIVE == ToolingExtensions.getStandardsStatus(vs))
            s.append(" <a href=\"versions.html#std-process\" title=\"Normative Content\" class=\"normative-flag\">N</a>");
          s.append("</td><td>"+Utilities.escapeXml(vs.getDescription())+"</td><td>"+sourceSummary(vs)+"</td>");
          if (hasId)
            s.append("<td>"+Utilities.oidTail(ValueSetUtilities.getOID(ae))+"</td>");
          s.append("</tr>\r\n");
        }
      }
    }
  }

  private String pathTail(String path) {
    if (path.contains("/"))
      return path.substring(path.lastIndexOf("/")+1);
    else if (path.contains(File.separator))
      return path.substring(path.lastIndexOf(File.separator)+1);
    else
      return path;
  }

  private String usageSummary(ValueSet vs) throws FHIRException {
    String s = (String) vs.getUserData(ToolResourceUtilities.NAME_SPEC_USAGE);
    if (Utilities.noString(s))
      return "??";
    else {
      String[] ps = s.split("\\,");
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (String p : ps) {
        if (!definitions.getIgs().containsKey(p))
          b.append(p);
        else if (!Utilities.noString(definitions.getIgs().get(p).getHomePage()))
          b.append("<a href=\""+definitions.getIgs().get(p).getCode()+"/"+definitions.getIgs().get(p).getHomePage()+"\" title=\""+definitions.getIgs().get(p).getName()+"\">"+p+"</a>");
        else
          b.append("<span title=\""+definitions.getIgs().get(p).getCode()+"/"+definitions.getIgs().get(p).getName()+"\">"+p+"</span>");
      }
      return b.toString();
    }
  }

  private boolean wantPublish(ValueSet vs) {
    String s = (String) vs.getUserData(ToolResourceUtilities.NAME_SPEC_USAGE);
    if (Utilities.noString(s))
      return true;
    else {
      String[] ps = s.split("\\,");
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (String p : ps) {
        if (!definitions.getIgs().containsKey(p))
          return true;
        else
          return true;
      }
      return false;
    }
  }

  private String getTail(String sn) {
    return sn.substring(getNamespace(sn).length()+1);
  }

  private String getNamespace(String sn) {
    return sn.contains("/") ? sn.substring(0, sn.lastIndexOf("/")) : sn;
  }

  private String sourceSummary(ValueSet vs) {
    StringBuilder b = new StringBuilder();
    List<String> done = new ArrayList<String>();
    if (vs.hasCompose())
      for (ConceptSetComponent c : vs.getCompose().getInclude()) {
        String uri = c.getSystem();
        String n = "Other";
        if (uri != null) {
          if ("http://snomed.info/sct".equals(uri)) n = "SNOMED CT";
          if ("http://loinc.org".equals(uri)) n = "LOINC";
          if ("http://dicom.nema.org/resources/ontology/DCM".equals(uri)) n = "DICOM";
          if ("http://hl7.org/fhir/resource-types".equals(uri)) n = "FHIR";
          if ("http://hl7.org/fhir/restful-interaction".equals(uri)) n = "FHIR";
          if ("http://unitsofmeasure.org".equals(uri)) n = "FHIR";
          if (uri.startsWith("http://terminology.hl7.org/CodeSystem/v3-"))  n = "V3";
          else if (uri.startsWith("http://terminology.hl7.org/CodeSystem/v2-"))  n = "V2";
          else if (uri.startsWith("http://hl7.org/fhir"))  n = "Internal";
        }
        if (!done.contains(n))
          b.append(", ").append(n);
        done.add(n);
      }
    return b.length() == 0 ? "" : b.substring(2);
  }


//  private String genBindingsTable() {
//    assert(false);
//    StringBuilder s = new StringBuilder();
//    s.append("<table class=\"codes\">\r\n");
//    s.append(" <tr><td><b>Name</b></td><td><b>Definition</b></td><td><b>Type</b></td><td><b>Reference</b></td></tr>\r\n");
//    List<String> names = new ArrayList<String>();
//    for (String n : definitions.getBindings().keySet()) {
//      names.add(n);
//    }
//    Collections.sort(names);
//    for (String n : names) {
//      if (!n.startsWith("*")) {
//        BindingSpecification cd = definitions.getBindingByName(n);
//        if (cd.getElementType() != ElementType.Unknown) {
//          s.append(" <tr><td>"+Utilities.escapeXml(cd.getName())+"</td><td>"+Utilities.escapeXml(cd.getDefinition())+"</td><td>");
//          if (cd.getBinding() == Binding.Reference)
//            s.append("Reference");
//          else if (cd.getElementType() == ElementType.Simple)
//            s.append("Code List");
//          else if (cd.getBinding() == Binding.Unbound)
//            s.append("??");
//          else
//            s.append("Value Set");
//
//          if (cd.getBinding() == Binding.Special) {
//
//            if (cd.getName().equals("MessageEvent"))
//              s.append("</td><td><a href=\"message-events.html\">http://hl7.org/fhir/valueset/message-events.html</a></td></tr>\r\n");
//            else if (cd.getName().equals("ResourceType"))
//              s.append("</td><td><a href=\"resource-types.html\">http://hl7.org/fhir/valueset/resource-types.html</a></td></tr>\r\n");
//            else if (cd.getName().equals("DataType"))
//              s.append("</td><td><a href=\"data-types.html\">http://hl7.org/fhir/valueset/data-types.html</a></td></tr>\r\n");
//            else if (cd.getName().equals("FHIRDefinedType"))
//              s.append("</td><td><a href=\"defined-types.html\">http://hl7.org/fhir/valueset/defined-types.html</a></td></tr>\r\n");
//            else
//              s.append("</td><td>???</td></tr>\r\n");
//
//          } else if (cd.getBinding() == Binding.CodeList)
//            s.append("</td><td><a href=\""+cd.getReference().substring(1)+".html\">http://hl7.org/fhir/"+cd.getReference().substring(1)+"</a></td></tr>\r\n");
//          else if (cd.getBinding() == Binding.ValueSet) {
//            if (cd.getReferredValueSet() != null) {
//              if (cd.getReference().startsWith("http://terminology.hl7.org/CodeSystem/v3-vs"))
//                s.append("</td><td><a href=\"v3/"+cd.getReference().substring(26)+"/index.html\">"+cd.getReference()+"</a></td></tr>\r\n");
//              else if (cd.getReference().startsWith("http://hl7.org/fhir"))
//                s.append("</td><td><a href=\""+cd.getReference().substring(23)+".html\">"+cd.getReference()+"</a></td></tr>\r\n");
//              else
//                s.append("</td><td><a href=\""+cd.getReference()+".html\">"+cd.getReferredValueSet().getUrl()+"</a></td></tr>\r\n");
//            } else
//              s.append("</td><td><a href=\""+cd.getReference()+".html\">??</a></td></tr>\r\n");
//          } else if (cd.hasReference())
//            s.append("</td><td><a href=\""+cd.getReference()+"\">"+Utilities.escapeXml(cd.getDescription())+"</a></td></tr>\r\n");
//          else if (Utilities.noString(cd.getDescription()))
//            s.append("</td><td style=\"color: grey\">??</td></tr>\r\n");
//          else
//            s.append("</td><td>? "+Utilities.escapeXml(cd.getBinding().toString())+": "+Utilities.escapeXml(cd.getDescription())+"</td></tr>\r\n");
//        }
//      }
//    }
//    s.append("</table>\r\n");
//    return s.toString();
//  }
//
//  private String genBindingTable(boolean codelists) {
//    StringBuilder s = new StringBuilder();
//    s.append("<table class=\"codes\">\r\n");
//    List<String> names = new ArrayList<String>();
//    for (String n : definitions.getBindings().keySet()) {
//      if ((codelists && definitions.getBindingByName(n).getBinding() == Binding.CodeList) || (!codelists && definitions.getBindingByName(n).getBinding() != Binding.CodeList))
//       names.add(n);
//    }
//    Collections.sort(names);
//    for (String n : names) {
//      if (!n.startsWith("*")) {
//        BindingSpecification cd = definitions.getBindingByName(n);
//        if (cd.getBinding() == Binding.CodeList || cd.getBinding() == Binding.Special)
//          s.append("  <tr><td title=\""+Utilities.escapeXml(cd.getDefinition())+"\">"+cd.getName()+"<br/><font color=\"grey\">http://hl7.org/fhir/sid/"+cd.getReference().substring(1)+"</font></td><td>");
//        else
//          s.append("  <tr><td title=\""+Utilities.escapeXml(cd.getDefinition())+"\">"+cd.getName()+"</td><td>");
//        if (cd.getBinding() == Binding.Unbound) {
//          s.append("Definition: "+Utilities.escapeXml(cd.getDefinition()));
//        } else if (cd.getBinding() == Binding.CodeList) {
//          assert(cd.getStrength() == BindingStrength.REQUIRED);
//          s.append("Required codes: ");
//          s.append("    <table class=\"codes\">\r\n");
//          boolean hasComment = false;
//          boolean hasDefinition = false;
//          for (DefinedCode c : cd.getCodes()) {
//            hasComment = hasComment || c.hasComment();
//            hasDefinition = hasDefinition || c.hasDefinition();
//          }
//          for (DefinedCode c : cd.getCodes()) {
//            if (hasComment)
//              s.append("    <tr><td>"+Utilities.escapeXml(c.getCode())+"</td><td>"+Utilities.escapeXml(c.getDefinition())+"</td><td>"+Utilities.escapeXml(c.getComment())+"</td></tr>");
//            else if (hasDefinition)
//              s.append("    <tr><td>"+Utilities.escapeXml(c.getCode())+"</td><td colspan=\"2\">"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
//            else
//              s.append("    <tr><td colspan=\"3\">"+Utilities.escapeXml(c.getCode())+"</td></tr>");
//          }
//          s.append("    </table>\r\n");
//        } else if (cd.getBinding() == Binding.ValueSet) {
//          if (cd.hasReference())
//            s.append("<a href=\""+cd.getReference()+"\">Value Set "+cd.getDescription()+"</a>");
//          else
//            s.append("Value Set "+cd.getDescription());
//          s.append(" (<a href=\"terminologies.html#"+cd.getStrength().toCode()+"\">"+cd.getStrength().getDisplay()+"</a>)");
//        } else if (cd.getBinding() == Binding.Reference) {
//            s.append("See <a href=\""+cd.getReference()+"\">"+cd.getReference()+"</a>");
//        } else if (cd.getBinding() == Binding.Special) {
//          if (cd.getName().equals("MessageEvent"))
//            s.append("See the <a href=\"message.html#Events\"> Event List </a>in the messaging framework");
//          else if (cd.getName().equals("ResourceType"))
//            s.append("See the <a href=\"terminologies.html#ResourceType\"> list of defined Resource Types</a>");
//          else if (cd.getName().equals("FHIRContentType"))
//            s.append("See the <a href=\"terminologies.html#fhircontenttypes\"> list of defined Resource and Data Types</a>");
//          else
//            s.append("<a href=\"datatypes.html\">Any defined data Type name</a> (including <a href=\"resource.html#Resource\">Resource</a>)");
//        }
//        s.append("</td></tr>\r\n");
//      }
//
//    }
//    s.append("</table>\r\n");
//    return s.toString();
//  }

  private String getEventsTable(String resource) throws Exception {
    List<String> codes = new ArrayList<String>();
    codes.addAll(definitions.getEvents().keySet());
    Collections.sort(codes);
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><th>Code</th><th>Category</th><th>Description</th><th>Request Resources</th><th>Response Resources</th><th>Notes</th></tr>\r\n");
    for (String c : codes) {
      EventDefn e = definitions.getEvents().get(c);
      if (e.getUsages().size() == 1) {
        EventUsage u = e.getUsages().get(0);
        s.append(" <tr><td>"+e.getCode()+"<a name=\""+e.getCode()+"\"> </a></td><td>"+(e.getCategory() == null ? "??" : e.getCategory().toString())+"</td><td>"+e.getDefinition()+"</td>");
        s.append("<td>"+describeMsg(u.getRequestResources(), u.getRequestAggregations())+"</td><td>"+
            describeMsg(u.getResponseResources(), u.getResponseAggregations())+"</td><td>"+combineNotes(resource, e.getFollowUps(), u.getNotes(), "")+"</td></tr>\r\n");
      } else {
        boolean first = true;
        for (EventUsage u : e.getUsages()) {
          if (first)
            s.append(" <tr><td rowspan=\""+Integer.toString(e.getUsages().size())+"\">"+e.getCode()+"</td><td rowspan=\""+Integer.toString(e.getUsages().size())+"\">"+e.getDefinition()+"</td>");
          else
            s.append(" <tr>");
          first = false;
          s.append("<td>"+describeMsg(u.getRequestResources(), u.getRequestAggregations())+"</td><td>"+
              describeMsg(u.getResponseResources(), u.getResponseAggregations())+"</td><td>"+
              combineNotes(resource, e.getFollowUps(), u.getNotes(), "")+"</td></tr>\r\n");
        }
      }
    }
    s.append("</table>\r\n");
    return s.toString();
  }

  private String genResCodes() {
    StringBuilder html = new StringBuilder();
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getKnownResources().keySet());
    Collections.sort(names);
    for (String n : names) {
      DefinedCode c = definitions.getKnownResources().get(n);
      String htmlFilename = c.getComment();

      html.append("  <tr><td><a href=\""+htmlFilename+".html\">"+c.getCode()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
    }
    return html.toString();
  }

  private String genDTCodes() {
    StringBuilder html = new StringBuilder();
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getTypes().keySet());
    names.addAll(definitions.getStructures().keySet());
    names.addAll(definitions.getInfrastructure().keySet());
    Collections.sort(names);
    for (String n : names) {
      if (!definitions.dataTypeIsSharedInfo(n)) {
        ElementDefn c = definitions.getTypes().get(n);
        if (c == null)
          c = definitions.getStructures().get(n);
        if (c == null)
          c = definitions.getInfrastructure().get(n);
        if (c.getName().equals("Extension"))
          html.append("  <tr><td><a href=\"extensibility.html\">"+c.getName()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
        else if (c.getName().equals("Narrative"))
          html.append("  <tr><td><a href=\"narrative.html#"+c.getName()+"\">"+c.getName()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
        else if (c.getName().equals("Reference") )
          html.append("  <tr><td><a href=\"references.html#"+c.getName()+"\">"+c.getName()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
        else if (c.getName().equals("canonical") )
          html.append("  <tr><td><a href=\"references.html#"+c.getName()+"\">"+c.getName()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
        else
          html.append("  <tr><td><a href=\"datatypes.html#"+c.getName()+"\">"+c.getName()+"</a></td><td>"+Utilities.escapeXml(c.getDefinition())+"</td></tr>");
      }
    }
    return html.toString();
  }

  private String genResImplList() {
    StringBuilder html = new StringBuilder();
    List<String> res = new ArrayList<String>();
    for (ResourceDefn n: definitions.getResources().values())
      res.add(n.getName());
    for (DefinedCode c : definitions.getKnownResources().values()) {
      if (res.contains(c.getComment()))
        html.append("  <tr><td>"+c.getCode()+"</td><td></td><td><a href=\""+c.getComment()+".xsd\">Schema</a></td><td><a href=\""+c.getComment()+".xml\">Example</a></td><td><a href=\""+c.getComment()+".json\">JSON Example</a></td>\r\n");
    }
    return html.toString();

  }

  private String genReferenceImplList(String location) throws Exception {
    StringBuilder s = new StringBuilder();
    for (PlatformGenerator gen : referenceImplementations) {
      if (gen.wantListAsDownload())
        s.append("<tr><td><a href=\""+gen.getReference(version.toCode())+"\">"+gen.getTitle()+"</a></td><td>"+processMarkdown(location, gen.getDescription(version.toCode(), buildId), "")+"</td></tr>\r\n");
    }
    return s.toString();
  }


  String processPageIncludesForPrinting(String file, String src, Resource resource, ImplementationGuideDefn ig) throws Exception {
    boolean even = false;
    List<String> tabs = new ArrayList<String>();

    while (src.contains("<%") || src.contains("[%"))
	  {
		  int i1 = src.indexOf("<%");
		  int i2 = src.indexOf("%>");
		  if (i1 == -1) {
			  i1 = src.indexOf("[%");
			  i2 = src.indexOf("%]");
		  }

      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);
      String name = file.substring(0,file.indexOf("."));

      String[] com = s2.split(" ");
      if (com.length == 3 && com[0].equals("edt")) {
        if (tabs != null)
          tabs.add("tabs-"+com[1]);
        src = s1+orgDT(com[1], xmlForDt(com[1], com[2]), treeForDt(com[1]), umlForDt(com[1], com[2]), umlForDt(com[1], com[2]+"b"), profileRef(com[1]), tsForDt(com[1]), jsonForDt(com[1], com[2]), ttlForDt(com[1], com[2]), diffForDt(com[1], com[2]))+s3;
      } else if (com.length == 2 && com[0].equals("dt")) {
        if (tabs != null)
          tabs.add("tabs-"+com[1]);
        src = s1+xmlForDt(com[1], null)+tsForDt(com[1])+s3;
      } else if (com.length == 2 && com[0].equals("dt.constraints"))
        src = s1+genConstraints(com[1], "")+s3;
      else if (com.length == 2 && com[0].equals("dt.restrictions"))
        src = s1+genRestrictions(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dictionary"))
        src = s1+dictForDt(com[1])+s3;
      else if (com[0].equals("pageheader") || com[0].equals("dtheader") || com[0].equals("mdtheader") || com[0].equals("edheader") || com[0].equals("mmheader") ||
          com[0].equals("drheader") || com[0].equals("elheader") || com[0].equals("belheader") || com[0].equals("extheader") || com[0].equals("narrheader") ||
          com[0].equals("formatsheader") || com[0].equals("resourcesheader") || com[0].equals("txheader") || com[1].equals("txheader0") ||
          com[0].equals("refheader") || com[0].equals("extrasheader") || com[0].equals("profilesheader") || com[0].equals("fmtheader") ||
          com[0].equals("igheader") || com[0].equals("cmpheader") || com[0].equals("atomheader") || com[0].equals("dictheader") ||
          com[0].equals("adheader") || com[0].equals("pdheader") || com[0].equals("tdheader") || com[0].equals("cdheader") || com[0].equals("diheader") ||
          com[0].equals("ctheader") || com[0].equals("ucheader") || com[0].equals("rrheader"))
        src = s1+s3;
      else if (com[0].equals("resheader"))
        src = s1+resHeader(name, "Document", com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("aresheader"))
        src = s1+abstractResHeader(name, "Document", com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("codelist"))
        src = s1+codelist((CodeSystem) resource, com.length > 1 ? com[1] : null, false, true, file)+s3;
      else if (com[0].equals("codelist-nh"))
        src = s1+codelist((CodeSystem) resource, com.length > 1 ? com[1] : null, false, false, file)+s3;
      else if (com[0].equals("linkcodelist"))
        src = s1+codelist((CodeSystem) resource, com.length > 1 ? com[1] : null, true, false, file)+s3;
      else if (com[0].equals("sct-vs-list"))
        src = s1+getSnomedCTVsList()+s3;
      else if (com[0].equals("sct-concept-list"))
        src = s1+getSnomedCTConceptList()+s3;
      else if (com[0].equals("codetoc"))
        src = s1+codetoc(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("res-category")) {
        src = s1+resCategory(s2.substring(com[0].length()+1))+s3;
        even = false;
      } else if (com[0].equals("res-item")) {
        even = !even;
        src = s1+resItem(com[1], even)+s3;
      } else if (com[0].equals("resdesc")) {
        src = s1+resDesc(com[1])+s3;
      } else if (com[0].equals("rescat")) {
        src = s1+resCat(com.length == 1 ? null : s2.substring(7))+s3;
      } else if (com[0].equals("w5"))
        src = s1+genW5("true".equals(com[1]))+s3;
      else if (com[0].equals("vs-warning"))
        src = s1 + vsWarning((ValueSet) resource) + s3;
      else if (com[0].equals("res-status-special"))
        src = s1 + vsSpecialStatus((DomainResource) resource) + s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else  if (com[0].equals("conceptmaplistvs")) {
        throw new Error("Fix this");
//        BindingSpecification bs = definitions.getBindingByName(Utilities.fileTitle(file));
//        String ref;
//        if (bs == null) {
//          ref = "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file);
//        } else {
//          ref = bs.getReference();
//          if (ref.startsWith("valueset-"))
//            ref = ref.substring(9);
//          ref = "http://hl7.org/fhir/ValueSet/"+ref;
//        }
//        src = s1 + conceptmaplist(ref, com[1]) + s3;
      }  else if (com[0].equals("dtmappings"))
        src = s1 + genDataTypeMappings(com[1]) + s3;
      else if (com[0].equals("dtusage"))
        src = s1 + genDataTypeUsage(com[1]) + s3;
      else if (com[0].equals("othertabs"))
        src = s1 + genOtherTabs(com[1], tabs) + s3;
      else if (com[0].equals("toc"))
        src = s1 + generateToc() + s3;
      else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1))+s3;
      else if (com[0].equals("xtitle"))
        src = s1+Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1))+s3;
      else if (com[0].equals("name"))
        src = s1+name+s3;
      else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("maindiv"))
        src = s1+s3;
      else if (com[0].equals("/maindiv"))
        src = s1+s3;
      else if (com[0].equals("enteredInErrorTable"))
        src = s1+enteredInErrorTable()+s3;
      else if (com[0].equals("events"))
        src = s1 + getEventsTable(file)+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
//      else if (com[0].equals("bindingtable-codelists"))
//        src = s1 + genBindingTable(true) + s3;
//      else if (com[0].equals("bindingtable"))
//        src = s1 + genBindingsTable() + s3;
//      else if (com[0].equals("bindingtable-others"))
//        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
      else if (com[0].equals("valuesetslist"))
        src = s1 + genValueSetsTable(ig) + s3;
      else if (com[0].equals("igvaluesetslist"))
        src = s1 + genIGValueSetsTable() + s3;
      else if (com[0].equals("namespacelist"))
        src = s1 + genNSList() + s3;
      else if (com[0].equals("resimplall"))
        src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList(file) + s3;
      else if (com[0].equals("txurl"))
        src = s1 + "http://hl7.org/fhir/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vstxurl"))
        src = s1 + "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("csurl")) {
        if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).getUrl() + s3;
        else
          src = s1 + ((ValueSet) resource).getUrl() + s3;
      } else if (com[0].equals("vsurl")) {
        if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).getUrl() + s3;
        else
          src = s1 + ((ValueSet) resource).getUrl() + s3;
      } else if (com[0].equals("txdef"))
        src = s1 + generateCodeDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsxref"))
        src = s1 + xreferencesForFhir(name) + s3;
      else if (com[0].equals("vsdef"))
        if (resource instanceof CodeSystem)
          src = s1 + Utilities.escapeXml(((CodeSystem) resource).getDescription()) + s3;
        else
          src = s1 + Utilities.escapeXml(((ValueSet) resource).getDescription()) + s3;
      else if (com[0].equals("txusage"))
        src = s1 + generateValueSetUsage((ValueSet) resource, genlevel(0), true) + s3;
      else if (com[0].equals("vsusage"))
        src = s1 + generateValueSetUsage((ValueSet) resource, genlevel(0), true) + s3;
      else if (com[0].equals("csusage"))
        src = s1 + generateCSUsage((CodeSystem) resource, genlevel(0)) + s3;
      else if (com[0].equals("vssummary"))
        src = s1 + "todo" + s3;
      else if (com[0].equals("piperesources"))
        src = s1+pipeResources()+s3;
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;
//      else if (com[0].equals("vsexpansion"))
//        src = s1 + expandValueSet(Utilities.fileTitle(file), resource == null ? null : (ValueSet) resource) + s3;
      else if (com[0].equals("vsexpansionig"))
        src = s1 + expandValueSetIG((ValueSet) resource, true) + s3;
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;
      else if (com[0].startsWith("!"))
        src = s1 + s3;
      else if (macros.containsKey(com[0])) {
        src = s1+macros.get(com[0])+s3;
      } else
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
    }
    return src;
  }

  private String genOtherTabs(String mode, List<String> tabs) {
    StringBuilder b = new StringBuilder();
    if (tabs != null) {
      if (mode.equals("setup")) {
        for (String s : tabs)
          b.append("$( '#"+s+"' ).tabs({ active: currentTabIndex, activate: function( event, ui ) { store(ui.newTab.index()); } });\r\n");
      }
      if (mode.equals("store")) {
        for (String s : tabs)
          b.append("  $( '#"+s+"' ).tabs('option', 'active', currentTab);\r\n");
      }
    }
    return b.toString();
  }

  private String generateOID(ValueSet vs) throws Exception {
    if (vs == null)
      return "";
    return unUrn(ValueSetUtilities.getOID(vs));
  }

  private String generateOID(CodeSystem cs) throws Exception {
    if (cs == null)
      return "";
    return unUrn(CodeSystemUtilities.getOID(cs));
  }

  private String unUrn(String oid) {
    if (oid == null)
      return "";
    if (oid.startsWith("urn:oid:"))
      return oid.substring(8);
    return oid;
  }

  private String generateDesc(ValueSet vs) {
    throw new Error("Fix this");
//    BindingSpecification cd = definitions.getBindingByReference("#"+fileTitle);
//    List<String> vslist = cd.getVSSources();
//    StringBuilder b = new StringBuilder();
//    if (vslist.contains("")) {
//      b.append("This value set defines its own codes");
//      vslist.remove(0);
//      if (vslist.size() > 0)
//        b.append(" and includes codes taken from");
//    } else
//      b.append("This is a value set with codes taken from ");
//    int i = 0;
//    for (String n : cd.getVSSources()) {
//      i++;
//      if (Utilities.noString(n)) {
//        //b.append("Codes defined internally");
//      } else {
//        String an = fixUrlReference(n);
//        b.append("<a href=\""+an+"\">"+n+"</a>");
//      }
//      if (i == vslist.size() - 1)
//        b.append(" and ");
//      else if (vslist.size() > 1 && i != vslist.size() )
//        b.append(", ");
//    }
//    return b.toString()+":";
  }

  private String fixUrlReference(String n) {
    if (n.startsWith("urn:ietf:rfc:"))
      return "http://tools.ietf.org/html/rfc"+n.split("\\:")[3];
    if (definitions.getCodeSystems().containsKey(n))
      return (String) definitions.getCodeSystems().get(n).getUserData("path");
    return n;
  }

  private String expandValueSetIG(ValueSet vs, boolean heirarchy) throws Exception {
    if (!hasDynamicContent(vs))
      return "";
    try {
      ValueSetExpansionOutcome result = workerContext.expandVS(vs, true, heirarchy);
      if (result.getError() != null)
        return "<hr/>\r\n"+VS_INC_START+"<!--1-->"+processExpansionError(result.getError())+VS_INC_END;
      ValueSet exp = result.getValueset();
      if (exp == vs)
        throw new Exception("Expansion cannot be the same instance");
      exp.setCompose(null);
      exp.setText(null);
      exp.setDescription("Value Set Contents (Expansion) for "+vs.present()+" at "+Config.DATE_FORMAT().format(new Date()));
      new NarrativeGenerator("", "", workerContext, this).setTooCostlyNoteEmpty(TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(TOO_MANY_CODES_TEXT_NOT_EMPTY).generate(exp, null);
      return "<hr/>\r\n"+VS_INC_START+""+new XhtmlComposer(XhtmlComposer.HTML).compose(exp.getText().getDiv())+VS_INC_END;
    } catch (Exception e) {
      return "<hr/>\r\n"+VS_INC_START+"<!--2-->"+processExpansionError(e.getMessage())+VS_INC_END;
    }
  }

  private String processExpansionError(String error) {
    if (error.contains("Too many codes"))
      return TOO_MANY_CODES_TEXT_NOT_EMPTY;
    if (error.contains("unable to provide support"))
      return NO_CODESYSTEM_TEXT;
    return "This value set could not be expanded by the publication tooling: "+Utilities.escapeXml(error);
  }

  private String expandValueSet(String fileTitle, ValueSet vs, String prefix) throws Exception {
    if (vs == null)
      throw new Exception("no vs?");
    if (hasUnfixedContent(vs)) {
      String s = "<p>&nbsp;</p>\r\n<a name=\"expansion\"> </a>\r\n<h2>Expansion</h2>\r\n<p>This expansion generated "+new SimpleDateFormat("dd MMM yyyy").format(genDate.getTime())+"</p>\r\n";
      return s + expandVS(vs, prefix, "");
    } else
      return "";
  }

  private boolean hasUnfixedContent(ValueSet vs) {
    if (vs.hasExpansion())
      return true;
    if (vs.hasCompose()) {
      for (ConceptSetComponent inc : vs.getCompose().getInclude()) {
        if (inc.hasValueSet())
          return true;
        if (inc.hasFilter() || !inc.hasConcept())
          return true;
      }
      for (ConceptSetComponent exc : vs.getCompose().getExclude())
        if (exc.hasFilter() || !exc.hasConcept())
          return true;
    }
    return false;
  }

  private String csContent(String fileTitle, CodeSystem cs, String prefix) throws Exception {
    Set<String> langs = findCodeSystemTranslations(cs);
    if (langs.size() > 0) 
      return renderCodeSystemWithLangs(langs, cs, "");  
    else if (cs.hasText() && cs.getText().hasDiv())
      return new XhtmlComposer(XhtmlComposer.HTML).compose(cs.getText().getDiv());
    else
      return "not done yet";
  }

  private String vsCLD(String fileTitle, ValueSet vs, String prefix) throws Exception {
    if (vs == null)
      throw new Exception("no vs?");
    ValueSet vs1 = vs.copy();
    vs1.setExpansion(null);
    vs1.setText(null);
    ImplementationGuideDefn ig = (ImplementationGuideDefn) vs.getUserData(ToolResourceUtilities.NAME_RES_IG);
    new NarrativeGenerator(prefix, "", workerContext, this).setTooCostlyNoteNotEmpty(TOO_MANY_CODES_TEXT_NOT_EMPTY).generate(null, vs1, null, false);
    return "<hr/>\r\n"+VS_INC_START+""+new XhtmlComposer(XhtmlComposer.HTML).compose(vs1.getText().getDiv())+VS_INC_END;
  }

  private String expandV3ValueSet(String name) throws Exception {
    ValueSet vs = definitions.getValuesets().get("http://terminology.hl7.org/ValueSet/v3-"+name);
    return expandVS(vs, "../../", "v3/"+name);
  }

  public ValueSet expandValueSet(ValueSet vs, boolean heirarchy) throws Exception {
    ValueSetExpansionOutcome result = workerContext.expandVS(vs, true, heirarchy);
    if (result.getError() != null)
      return null;
    else
      return result.getValueset();
  }


  private String stack(Exception e) {
    StringBuilder b = new StringBuilder();
    for (StackTraceElement s : e.getStackTrace()) {
      b.append("<br/>&nbsp;&nbsp;"+s.toString());
    }
    return b.toString();
  }

  private boolean hasDynamicContent(ValueSet vs) {
    if (vs.hasCompose()) {
      for (ConceptSetComponent t : vs.getCompose().getInclude()) {
        if (t.hasValueSet())
          return true;
        if (t.hasFilter())
          return true;
        if (!t.hasConcept())
          return true;
      }
      for (ConceptSetComponent t : vs.getCompose().getExclude()) {
        if (t.hasValueSet())
          return true;
        if (t.hasFilter())
          return true;
        if (!t.hasConcept())
          return true;
      }
    }
    return false;
  }

  private String generateVSDesc(String fileTitle) throws Exception {
    throw new Error("Fix this");
//    BindingSpecification cd = definitions.getBindingByName(fileTitle);
//    if (cd == null)
//      return new XhtmlComposer(XhtmlComposer.HTML).compose(definitions.getExtraValuesets().get(fileTitle).getText().getDiv());
//    else if (cd.getReferredValueSet().hasText() && cd.getReferredValueSet().getText().hasDiv())
//      return new XhtmlComposer(XhtmlComposer.HTML).compose(cd.getReferredValueSet().getText().getDiv());
//    else
//      return cd.getReferredValueSet().getDescription();
  }

  String processPageIncludesForBook(String file, String src, String type, Resource resource, ImplementationGuideDefn ig, WorkGroup wg) throws Exception {
    String workingTitle = null;
    int level = 0;
    boolean even = false;
    List<String> tabs = new ArrayList<String>();

    while (src.contains("<%") || src.contains("[%"))
	  {
		  int i1 = src.indexOf("<%");
		  int i2 = i1 == -1 ? -1 : src.substring(i1).indexOf("%>")+i1;
		  if (i1 == -1) {
			  i1 = src.indexOf("[%");
			  i2 = i1 == -1 ? -1 : src.substring(i1).indexOf("%]")+i1;
		  }

      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);
      String name = file.substring(0,file.indexOf("."));

      String[] com = s2.split(" ");
      if (com.length == 3 && com[0].equals("edt")) {
        if (tabs != null)
          tabs.add("tabs-"+com[1]);
        src = s1+orgDT(com[1], xmlForDt(com[1], com[2]), treeForDt(com[1]), umlForDt(com[1], com[2]), umlForDt(com[1], com[2]+"b"), profileRef(com[1]), tsForDt(com[1]), jsonForDt(com[1], com[2]), ttlForDt(com[1], com[2]), diffForDt(com[1], com[2]))+s3;
      } else if (com.length == 3 && com[0].equals("dt")) {
        if (tabs != null)
          tabs.add("tabs-"+com[1]);
        src = s1+xmlForDt(com[1], null)+tsForDt(com[1])+s3;
      } else if (com.length == 2 && com[0].equals("dt.constraints"))
        src = s1+genConstraints(com[1], genlevel(level))+s3;
      else if (com.length == 2 && com[0].equals("dt.restrictions"))
        src = s1+genRestrictions(com[1])+s3;
      else if (com.length == 2 && com[0].equals("dictionary"))
        src = s1+dictForDt(com[1])+s3;
      else if (com[0].equals("pageheader") || com[0].equals("dtheader") || com[0].equals("mdtheader") || com[0].equals("edheader") || com[0].equals("mmheader") ||
          com[0].equals("drheader") ||com[0].equals("elheader") || com[0].equals("belheader") || com[0].equals("extheader") || com[0].equals("resourcesheader") ||
          com[0].equals("formatsheader") || com[0].equals("narrheader") || com[0].equals("refheader") ||  com[0].equals("extrasheader") || com[0].equals("profilesheader") ||
          com[0].equals("txheader") || com[0].equals("txheader0") || com[0].equals("fmtheader") || com[0].equals("igheader") ||
          com[0].equals("cmpheader") || com[0].equals("atomheader") || com[0].equals("dictheader") || com[0].equals("ctheader") ||
          com[0].equals("adheader") || com[0].equals("pdheader") || com[0].equals("tdheader") || com[0].equals("cdheader") || com[0].equals("diheader") ||
          com[0].equals("ucheader") || com[0].equals("rrheader"))
        src = s1+s3;
      else if (com[0].equals("resheader"))
        src = s1+s3;
      else if (com[0].equals("aresheader"))
        src = s1+s3;
      else if (com[0].equals("othertabs"))
        src = s1 + genOtherTabs(com[1], tabs) + s3;
      else if (com[0].equals("diff")) {
        String p = com[1];
        String pd = p.contains("#") ? p.substring(0, p.indexOf("#")) : p;
        String t = s2.substring(com[0].length()+com[1].length()+2);
        src = s1+"<a href=\""+p+"\">"+t+"</a> <a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+pd+"&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+pd+"\" no-external=\"true\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a>"+s3;
      } else if (com[0].equals("diffx")) {
        String p1 = com[1];
        String pd = p1.contains("#") ? p1.substring(0, p1.indexOf("#")) : p1;
        String p2 = com[2];
        String t = s2.substring(com[0].length()+com[1].length()+2);
        src = s1+"<a href=\""+p1+"\">"+t+"</a> <a href=\"http://services.w3.org/htmldiff?doc1=http%3A%2F%2Fhl7.org%2Ffhir%2F2018May%2F"+p2+"&amp;doc2=http%3A%2F%2Fbuild.fhir.org%2F"+pd+"\" no-external=\"true\" style=\"border: 1px solid lightgrey; white-space: nowrap; background-color: #EDFDFE; padding: 2px 2px 2px 2px\">&Delta;B</a>"+s3;
      }  else if (com[0].equals("dtmappings"))
        src = s1 + genDataTypeMappings(com[1]) + s3;
      else if (com[0].equals("sct-vs-list"))
        src = s1+getSnomedCTVsList()+s3;
      else if (com[0].equals("sct-concept-list"))
        src = s1+getSnomedCTConceptList()+s3;
      else if (com[0].equals("circular-references")) 
      src = s1+buildCircularReferenceList(com[1].equals("null") ? null : Boolean.valueOf(com[1]))+s3;
      else if (com[0].equals("dtusage"))
        src = s1 + genDataTypeUsage(com[1]) + s3;
      else if (com[0].equals("w5"))
        src = s1+genW5("true".equals(com[1]))+s3;
      else if (com[0].equals("codelist"))
        src = s1+codelist((CodeSystem) resource, com.length > 1 ? com[1] : null, false, true, file)+s3;
      else if (com[0].equals("codelist-nh"))
        src = s1+codelist((CodeSystem) resource, com.length > 1 ? com[1] : null, false, false, file)+s3;
      else if (com[0].equals("linkcodelist"))
        src = s1+codelist((CodeSystem) resource, com.length > 1 ? com[1] : null, true, false, file)+s3;
      else if (com[0].equals("codetoc"))
        src = s1+codetoc(com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("vs-warning"))
        src = s1 + vsWarning((ValueSet) resource) + s3;
      else if (com[0].equals("res-status-special"))
        src = s1 + vsSpecialStatus((DomainResource) resource) + s3;
      else if (com[0].equals("maponthispage"))
          src = s1+s3;
      else if (com[0].equals("onthispage"))
          src = s1+s3;
      else if (com[0].equals("conceptmaplistvs")) {
        ValueSet vs = (ValueSet) resource;
        String ref;
        if (vs == null) {
          ref = "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file);
        } else {
          ref = vs.getUrl();
        }
        src = s1 + conceptmaplist(ref, com[1]) + s3;
      }  else if (com[0].equals("res-category")) {
        src = s1+resCategory(s2.substring(com[0].length()+1))+s3;
        even = false;
      } else if (com[0].equals("res-item")) {
        even = !even;
        src = s1+resItem(com[1], even)+s3;
      } else if (com[0].equals("resdesc")) {
        src = s1+resDesc(com[1])+s3;
      } else if (com[0].equals("rescat")) {
        src = s1+resCat(com.length == 1 ? null : s2.substring(7))+s3;
      } else if (com[0].equals("sidebar"))
        src = s1+s3;
      else if (com[0].equals("svg"))
        src = s1+svgs.get(com[1])+s3;
      else if (com[0].equals("diagram"))
        src = s1+new SvgGenerator(this, genlevel(level), null, false, file.contains("datatypes")).generate(folders.srcDir+ com[1], com[2])+s3;
      else if (com[0].equals("file"))
        src = s1+/*TextFile.fileToString(folders.srcDir + com[1]+".html")+*/s3;
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }  else if (com[0].equals("reflink")) {
        src = s1 + reflink(com[1]) + s3;
      } else if (com[0].equals("res-ref-list")) {
        src = s1+genResRefList(com[1])+s3;
      } else if (com[0].equals("sclist")) {
        src = s1+genScList(com[1])+s3;
      } else if (com[0].equals("xcm")) {
        src = s1+getXcm(com[1])+s3;
      } else if (com[0].equals("xcmchk")) {
        src = s1+getXcmChk(com[1])+s3;
      } else if (com[0].equals("fmm")) {
        src = s1+getFmm(com[1], false)+s3;
      } else if (com[0].equals("fmmshort")) {
        src = s1+getFmmShort(com[1])+s3;
      } else if (com[0].equals("sstatus")) {
        src = s1+getStandardsStatus(com[1])+s3;
      } else if (com[0].equals("wg")) {
        src = s1+getWgLink(file, wg == null && com.length > 0 ? wg(com[1]) : wg)+s3;
      } else if (com[0].equals("wgt")) {
        src = s1+getWgTitle(wg == null && com.length > 0 ? wg(com[1]) : wg)+s3;
      } else if (com[0].equals("search-link")) {
        src = s1+searchLink(s2)+s3;
      } else if (com[0].equals("search-footer")) {
        src = s1+searchFooter(level)+s3;
      } else if (com[0].equals("search-header")) {
          src = s1+searchHeader(level)+s3;
      } else if (com[0].equals("toc")) {
        src = s1 + generateToc() + s3;
      } else if (com[0].equals("igregistries")) {
          src = s1+igRegistryList(com[1], com[2])+s3;
      } else if (com[0].equals("ig.registry")) {
        src = s1+buildIgRegistry(ig, com[1])+s3;
      } else if (com[0].equals("dtextras")) {
        src = s1+produceDataTypeExtras(com[1], true)+s3;
      } else if (com[0].equals("dtextensions")) {
        src = s1+produceDataTypeExtras(com[1], false)+s3;
      } else if (com[0].equals("resource-table")) {
        src = s1+genResourceTable(definitions.getResourceByName(com[1]), genlevel(level))+s3;
      } else if (com[0].equals("profile-diff")) {
        ConstraintStructure p = definitions.findProfile(com[1]);
        src = s1 + generateProfileStructureTable(p, true, com[1]+".html", com[1], genlevel(level)) + s3;
      } else if (com[0].equals("example")) {
        String[] parts = com[1].split("\\/");
        Example e = findExample(parts[0], parts[1]);
        src = s1+genExample(e, com.length > 2 ? Integer.parseInt(com[2]) : 0, genlevel(level))+s3;
      } else if (com[0].equals("extension-diff")) {
        StructureDefinition ed = workerContext.fetchResource(StructureDefinition.class, com[1]);
        src = s1+generateExtensionTable(ed, "extension-"+com[1], "false", genlevel(level))+s3;
      } else if (com[0].equals("setlevel")) {
        level = Integer.parseInt(com[1]);
        src = s1+s3;
      } else if (com[0].equals("r3r4transform")) {
        src = s1+dtR3R4Transform(com[1])+s3;
      } else if (com[0].equals("normative-pages")) {
        src = s1+getNormativeList(genlevel(level), com[1])+s3;
      } else if (com[0].equals("tx")) {
        src = s1+produceDataTypeTx(com[1])+s3;
      } else if (com[0].equals("normative")) {
        src = s1+s3;
      } else if (com[0].equals("mostlynormative")) {
        src = s1+s3;
      } else if (com[0].equals("mixednormative")) {
        src = s1+s3;
      } else if (com[0].equals("StandardsStatus")) {
        src = s1+getStandardsStatusNote(genlevel(level), com[1], com[2], com.length == 4 ? com[3] : null)+s3;
      } else if (com[0].equals("diff-analysis")) {
        if ("*".equals(com[1])) {
          updateDiffEngineDefinitions();
          src = s1+diffEngine.getDiffAsHtml(this)+s3;
        } else {
          StructureDefinition sd = workerContext.fetchTypeDefinition(com[1]);
          if (sd == null)
            throw new Exception("diff-analysis not found: "+com[1]);
          src = s1+diffEngine.getDiffAsHtml(this, sd)+s3;
        }
      } else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
      else if (com[0].equals("header"))
        src = s1+s3;
      else if (com[0].equals("newheader"))
        src = s1+s3;
      else if (com[0].equals("newheader1"))
        src = s1+s3;
      else if (com[0].equals("footer"))
        src = s1+s3;
      else if (com[0].equals("newfooter"))
        src = s1+s3;
      else if (com[0].equals("footer1"))
        src = s1+s3;
      else if (com[0].equals("footer2"))
        src = s1+s3;
      else if (com[0].equals("footer3"))
        src = s1+s3;
      else if (com[0].equals("title"))
        src = s1+(workingTitle == null ? Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1)) : workingTitle)+s3;
      else if (com[0].equals("xtitle"))
        src = s1+Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1))+s3;
      else if (com[0].equals("name"))
        src = s1+name+s3;
      else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("maindiv"))
        src = s1+s3;
      else if (com[0].equals("/maindiv"))
        src = s1+s3;
      else if (com[0].equals("events"))
        src = s1 + getEventsTable(file)+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("enteredInErrorTable"))
        src = s1+enteredInErrorTable()+s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
//      else if (com[0].equals("bindingtable-codelists"))
//        src = s1 + genBindingTable(true) + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
      else if (com[0].equals("valuesetslist"))
        src = s1 + genValueSetsTable(ig) + s3;
      else if (com[0].equals("igvaluesetslist"))
        src = s1 + genIGValueSetsTable() + s3;
      else if (com[0].equals("namespacelist"))
        src = s1 + s3;
      else if (com[0].equals("conceptmapslist"))
        src = s1 + genConceptMapsTable() + s3;
//      else if (com[0].equals("bindingtable"))
//        src = s1 + genBindingsTable() + s3;
//      else if (com[0].equals("bindingtable-others"))
//        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("vsxref"))
        src = s1 + xreferencesForFhir(name) + s3;
      else if (com[0].equals("resimplall"))
        src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList(file) + s3;
      else if (com[0].equals("txurl"))
        src = s1 + "http://hl7.org/fhir/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vstxurl"))
        src = s1 + "http://hl7.org/fhir/ValueSet/"+Utilities.fileTitle(file) + s3;
      else if (com[0].equals("csurl")) {
        if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).getUrl() + s3;
        else
          src = s1 + ((ValueSet) resource).getUrl() + s3;
      } else if (com[0].equals("vsurl")) {
        if (resource == null) 
          src = s1 + s3;
        else if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).getUrl() + s3;
        else
          src = s1 + ((ValueSet) resource).getUrl() + s3;
      } else if (com[0].equals("txdef"))
        src = s1 + generateCodeDefinition(Utilities.fileTitle(file)) + s3;
      else if (com[0].equals("vsexpansion"))
        src = s1 + expandValueSet(Utilities.fileTitle(file), resource == null ? null : (ValueSet) resource, genlevel(level)) + s3;
      else if (com[0].equals("vsexpansionig"))
        src = s1 + expandValueSetIG((ValueSet) resource, true) + s3;
      else if (com[0].equals("vsdef"))
        if (resource instanceof CodeSystem)
          src = s1 + Utilities.escapeXml(((CodeSystem) resource).getDescription()) + s3;
        else
          src = s1 + Utilities.escapeXml(((ValueSet) resource).getDescription()) + s3;
      else if (com[0].equals("txoid"))
        src = s1 + generateOID((CodeSystem) resource) + s3;
      else if (com[0].equals("vsoid"))
        src = s1 + generateOID((ValueSet) resource) + s3;
      else if (com[0].equals("txname"))
        src = s1 + Utilities.fileTitle(file) + s3;
      else if (com[0].equals("vsname"))
        if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).present() + s3;
        else
          src = s1 + ((ValueSet) resource).present() + s3;
      else if (com[0].equals("vsnamed"))
        if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).getName() + s3;
        else
          src = s1 + ((ValueSet) resource).getName() + s3;
      else if (com[0].equals("vstitle"))
        if (resource instanceof CodeSystem)
          src = s1 + checkTitle(((CodeSystem) resource).getTitle()) + s3;
        else
          src = s1 + checkTitle(((ValueSet) resource).getTitle()) + s3;
      else if (com[0].equals("vsver"))
        if (resource instanceof CodeSystem)
          src = s1 + ((CodeSystem) resource).getVersion() + s3;
        else
          src = s1 + ((ValueSet) resource).getVersion() + s3;
      else if (com[0].equals("vsref")) {
        src = s1 + Utilities.fileTitle((String) resource.getUserData("filename")) + s3;
      } else if (com[0].equals("txdesc"))
        src = s1 + generateDesc((ValueSet) resource) + s3;
      else if (com[0].equals("vsdesc"))
        src = s1 + (resource != null ? Utilities.escapeXml(((ValueSet) resource).getDescription()) :  generateVSDesc(Utilities.fileTitle(file))) + s3;
      else if (com[0].equals("txusage"))
        src = s1 + generateValueSetUsage((ValueSet) resource, genlevel(level), true) + s3;
      else if (com[0].equals("vsusage"))
        src = s1 + generateValueSetUsage((ValueSet) resource, genlevel(level), true) + s3;
      else if (com[0].equals("csusage"))
        src = s1 + generateCSUsage((CodeSystem) resource, genlevel(level)) + s3;
      else if (com[0].equals("v2Index"))
        src = s1+genV2Index()+s3;
      else if (com[0].equals("v2VSIndex"))
        src = s1+genV2VSIndex()+s3;
      else if (com[0].equals("v3Index-cs"))
        src = s1+genV3CSIndex()+s3;
      else if (com[0].equals("v3Index-vs"))
        src = s1+genV3VSIndex()+s3;
      else if (com[0].equals("mappings-table"))
        src = s1+genMappingsTable()+s3;
      else if (com[0].equals("vssummary"))
        src = s1 + "todo" + s3;
      else if (com[0].equals("compartmentlist"))
        src = s1 + compartmentlist() + s3;
      else if (com[0].equals("comp-title"))
        src = s1 + compTitle(name) + s3;
      else if (com[0].equals("comp-desc"))
        src = s1 + compDesc(name) + s3;
      else if (com[0].equals("comp-uri"))
        src = s1 + compUri(name) + s3;
      else if (com[0].equals("comp-identity"))
        src = s1 + compIdentity(name) + s3;
      else if (com[0].equals("comp-membership"))
        src = s1 + compMembership(name) + s3;
      else if (com[0].equals("comp-resources"))
        src = s1 + compResourceMap(name) + s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(name) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(name, genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist")) {
        String crumbTitle = (workingTitle == null ? Utilities.escapeXml(name.toUpperCase().substring(0, 1)+name.substring(1)) : workingTitle);
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(name, type, genlevel(level), crumbTitle) : ig.makeList(name, type, genlevel(level), crumbTitle)) + s3;
      }else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;
      else if (com[0].equals("buildId"))
        src = s1 + buildId + s3;
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;
      else if (com[0].equals("piperesources"))
        src = s1+pipeResources()+s3;
      else if (com[0].equals("archive"))
        src = s1 + makeArchives() + s3;
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;
      else if (com[0].equals("profilelist"))
        src = s1 + genProfilelist() + s3;
      else if (com[0].equals("extensionslist"))
        src = s1 + genExtensionsTable() + s3;
      else if (com[0].equals("igprofileslist"))
        src = s1 + genIGProfilelist() + s3;
      else if (com[0].equals("operationslist"))
        src = s1 + genOperationList() + s3;
      else if (com[0].equals("id_regex"))
        src = s1 + FormatUtilities.ID_REGEX + s3;
      else if (com[0].equals("allparams"))
        src = s1 + allParamlist() + s3;
      else if (com[0].equals("resourcecount"))
        src = s1 + Integer.toString(definitions.getResources().size()) + s3;
      else if (com[0].equals("status-codes"))
        src = s1 + genStatusCodes() + s3;
      else if (com[0].equals("dictionary.name"))
        src = s1 + definitions.getDictionaries().get(name) + s3;
//      else if (com[0].equals("dictionary.view"))
//        src = s1 + ResourceUtilities.representDataElementCollection(this.workerContext, (Bundle) resource, true, "hspc-QuantitativeLab-dataelements") + s3;
      else if (com[0].startsWith("!"))
        src = s1 + s3;
      else if (com[0].equals("identifierlist"))
        src = s1 + genIdentifierList()+s3;
      else if (com[0].equals("allsearchparams"))
        src = s1 + genAllSearchParams()+s3;
      else if (com[0].equals("internalsystemlist"))
        src = s1 + genCSList()+s3;
      else if (com[0].equals("internalsystemlistx"))
        src = s1 + genCSListX()+s3;
      else if (com[0].equals("baseURLn"))
        src = s1 + Utilities.appendForwardSlash(baseURL)+s3;
      else if (com[0].equals("ig.title"))
        src = s1+ig.getName()+s3;
      else if (com[0].equals("ig.wglink"))
        src = s1+igLink(ig)+s3;
      else if (com[0].equals("ig.wgt"))
        src = s1+ig.getCommittee()+s3;
      else if (com[0].equals("ig.fmm"))
        src = s1+ig.getFmm()+s3;
      else if (com[0].equals("comp-name"))
        src = s1 + compName(name) + s3;
      else if (com[0].equals("ig.ballot"))
        src = s1+ig.getBallot()+s3;
      else if (com[0].equals("fhir-path"))
        src = s1 + "../" + s3;
      else if (com[0].equals("modifier-list"))
        src = s1 + genModifierList() + s3;
      else if (com[0].equals("missing-element-list"))
        src = s1 + genMeaningWhenMissingList() + s3;
      else if (com[0].equals("wgreport"))
        src = s1 + genWGReport() + s3;
      else if (com[0].equals("r3maps-summary"))
        src = s1 + genR3MapsSummary() + s3;
      else if (com[0].equals("res-list-maturity"))
        src = s1+buildResListByMaturity()+s3;
      else if (com[0].equals("res-list-security"))
        src = s1+buildResListBySecurity()+s3;
      else if (com[0].equals("res-list-fmg"))
        src = s1+buildResListByFMG()+s3;
      else if (com[0].equals("res-list-ballot"))
        src = s1+buildResListByBallot()+s3;
      else if (com[0].equals("res-list-committee"))
        src = s1+buildResListByCommittee()+s3;
      else if (com[0].equals("wglist"))
        src = s1+buildCommitteeList()+s3;
      else if (com[0].equals("structure-list-index"))
        src = s1+genStructureList()+s3;
      else if (com[0].equals("best-practice-list"))
        src = s1+genBestPracticeList()+s3;
      else if (com[0].equals("extension-type-list"))
        src = s1+genExtensionTypeList()+s3;
      else if (com[0].equals("wildcard-type-list"))
        src = s1+genWildcardTypeList()+s3;
      else if (com[0].startsWith("GF#"))
        src = s1+"<a href=\"https://gforge.hl7.org/gf/project/fhir/tracker/?action=TrackerItemEdit&amp;tracker_item_id="+com[0].substring(3)+"\">"+com[0]+"</a>"+s3;      
      else if (com[0].startsWith("GFT#"))
        src = s1+"<a href=\"https://gforge.hl7.org/gf/project/fhir/tracker/?action=TrackerItemEdit&amp;tracker_item_id="+com[0].substring(3)+"\">Task</a>"+s3;      
      else  if (com[0].equals("canonical-resources")) 
        src = s1+listCanonicalResources()+s3;      
      else if (com[0].equals("special-search-parameters")) { 
        src = s1+listSpecialParameters()+s3;
      } else if (macros.containsKey(com[0])) {
        src = s1+macros.get(com[0])+s3;
      } else
        throw new Exception("Instruction <%"+s2+"%> not understood parsing page "+file);
    }
    return src;
  }


  private String checkTitle(String title) {
    return title == null ? "" : Utilities.escapeXml(title);
  }

  private String genResExtLink(ResourceDefn resource) {
    boolean isAbstract = resource.isAbstract();

    if (isAbstract)
      return "See the ";
    else
      return "See the <a href=\""+resource.getName().toLowerCase()+"-profiles.html\">Profiles &amp; Extensions</a> and the ";
  }

  public class SnomedConceptUsage {
    private String code;
    private String display;
    private List<ValueSet> valueSets = new ArrayList<ValueSet>();

    public SnomedConceptUsage(String code, String display, ValueSet vs) {
      this.code = code;
      this.display = display;
      valueSets.add(vs);
    }

    public String getDisplay() {
      return display;
    }

    public List<ValueSet> getValueSets() {
      return valueSets;
    }

    public void update(String display, ValueSet vs) {
      if (Utilities.noString(this.display))
        this.display = display;
      if (valueSets.contains(vs))
        valueSets.add(vs);
    }
  }

  private String getSnomedCTConceptList() throws Exception {
    Map<String, SnomedConceptUsage> concepts = new HashMap<String, SnomedConceptUsage>();
    for (ValueSet vs : definitions.getValuesets().values()) {
      for (ConceptSetComponent cc : vs.getCompose().getInclude())
        if (cc.hasSystem() && cc.getSystem().equals("http://snomed.info/sct")) {
          for (ConceptReferenceComponent c : cc.getConcept()) {
            String d = c.hasDisplay() ? c.getDisplay() : workerContext.getCodeDefinition("http://snomed.info/sct", c.getCode()).getDisplay();
            if (concepts.containsKey(c.getCode()))
              concepts.get(c.getCode()).update(d, vs);
            else
              concepts.put(c.getCode(), new SnomedConceptUsage(c.getCode(), d, vs));
          }
          for (ConceptSetFilterComponent c : cc.getFilter()) {
            if (c.getProperty().equals("concept")) {
              ConceptDefinitionComponent def = workerContext.getCodeDefinition("http://snomed.info/sct", c.getValue());
              if (def==null) {
                throw new Exception("Unable to retrieve definition for SNOMED code: " + c.getValue());
              }
              String d = def.getDisplay();
              if (concepts.containsKey(c.getValue()))
                concepts.get(c.getValue()).update(d, vs);
              else
                concepts.put(c.getValue(), new SnomedConceptUsage(c.getValue(), d, vs));
            }
          }
        }
    }
    List<String> sorts = new ArrayList<String>();
    for (String s : concepts.keySet())
      sorts.add(s);
    Collections.sort(sorts);
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"codes\">\r\n");
    b.append(" <tr><td><b>Code</b></td><td><b>Display</b></td><td>ValueSets</td></tr>\r\n");
    for (String s : sorts) {
      SnomedConceptUsage usage = concepts.get(s);
      b.append(" <tr>\r\n   <td>"+s+"</td>\r\n    <td>"+Utilities.escapeXml(usage.getDisplay())+"</td>\r\n    <td>");
      boolean first = true;
      for (ValueSet vs : usage.getValueSets()) {
        if (first)
          first = false;
        else
          b.append("<br/>");
        String path = (String) vs.getUserData("path");
        b.append(" <a href=\""+pathTail(Utilities.changeFileExt(path, ".html"))+"\">"+Utilities.escapeXml(vs.present())+"</a>");
      }
      b.append("</td>\r\n  </tr>\r\n");
    }
    b.append("</table>\r\n");
    return b.toString();
  }


  private String getSnomedCTVsList() throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"codes\">\r\n");
    s.append(" <tr><td><b>Name</b></td><td><b>Definition</b></td><td><b>CLD</b></td><td>Usage</td></tr>\r\n");

    List<String> sorts = new ArrayList<String>();
    for (ValueSet vs : definitions.getValuesets().values()) {
      if (referencesSnomed(vs))
        sorts.add(vs.getUrl());
    }
    Collections.sort(sorts);

    for (String sn : sorts) {
      ValueSet vs = definitions.getValuesets().get(sn);
      String path = (String) vs.getUserData("path");
      s.append(" <tr>\r\n  <td><a href=\""+pathTail(Utilities.changeFileExt(path, ".html"))+"\">"+Utilities.escapeXml(vs.present())+"</a></td>\r\n  <td>"+Utilities.escapeXml(vs.getDescription())+"</td>\r\n");
      s.append("  <td>"+summariseSCTCLD(vs)+"</td>\r\n");
      s.append("  <td>"+generateValueSetUsage(vs, "", false)+"</td>\r\n");
      s.append(" </tr>\r\n");
    }
    s.append("</table>\r\n");
    return s.toString();
  }

  private String summariseSCTCLD(ValueSet vs) {
    boolean hasNonSCT = false;
    for (ConceptSetComponent cc : vs.getCompose().getInclude()) {
      if (!"http://snomed.info/sct".equals(cc.getSystem()))
        hasNonSCT = true;
    }
    StringBuilder b = new StringBuilder();
    b.append("<ul>");
    for (ConceptSetComponent cc : vs.getCompose().getInclude()) {
      if ("http://snomed.info/sct".equals(cc.getSystem())) {
        if (!cc.hasConcept() && !cc.hasFilter()) {
          b.append("<li>any SCT concept</li>");
        } else if (cc.hasConcept()) {
          b.append("<li>"+Integer.toString(cc.getConcept().size())+" enumerated concepts</li>");
        } else {
          if (cc.getFilter().size() != 1 || !cc.getFilter().get(0).getProperty().equals("concept"))
            b.append("<li>ERROR!</li>");
          else {
            ConceptDefinitionComponent def = workerContext.getCodeDefinition("http://snomed.info/sct", cc.getFilter().get(0).getValue());
            b.append("<li>"+cc.getFilter().get(0).getOp().toCode()+" "+(def == null ? cc.getFilter().get(0).getValue() : Utilities.escapeXml(def.getDisplay()))+"</li>");
          }
        }
      }
    }
    if (hasNonSCT)
      b.append("<li>other code systems</li>");
    b.append("</ul>");
    return b.toString();
  }

  private boolean referencesSnomed(ValueSet vs) {
    for (ConceptSetComponent cc : vs.getCompose().getInclude())
      if (cc.hasSystem() && cc.getSystem().equals("http://snomed.info/sct"))
        return true;
    for (ConceptSetComponent cc : vs.getCompose().getExclude())
      if (cc.hasSystem() && cc.getSystem().equals("http://snomed.info/sct"))
        return true;
    return false;
  }

  private String searchFooter(int level) {
    return "<a style=\"color: #b8dcf9\" href=\"http://hl7.org/fhir/search.cfm\">Search</a>";
  }

  private String searchHeader(int level) {

    return "<div id=\"hl7-nav\"><a id=\"hl7-logo\" no-external=\"true\" href=\"http://hl7.org/fhir/search.cfm\"><img alt=\"Search FHIR\" src=\"./"+genlevel(level)+"assets/images/search.png\"/></a></div>";
  }


  private String searchLink(String s2) {
    if (s2.equals("search-link"))
      return "<a href=\"search.cfm\">Search this specification</a>";
    else
      return s2.substring(11)+" <a href=\"search.cfm\">search this specification</a>";
  }

  private String igRegistryList(String purpose, String type) throws Exception {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      if (!ig.isCore()) {
        boolean found = false;
        if ("terminology".equals(purpose)) {
          for (ValueSet vs : definitions.getValuesets().values()) {
            if (vs.getUserData(ToolResourceUtilities.NAME_RES_IG) == ig)
              found = true;
          }
          for (ConceptMap cm : conceptMaps.values()) {
            if (cm.getUserData(ToolResourceUtilities.NAME_RES_IG) == ig)
              found = true;
          }
        } else if ("extension".equals(purpose)) {
          for (StructureDefinition ex : workerContext.getExtensionDefinitions()) {
            if (ig.getCode().equals(ToolResourceUtilities.getUsage(ex))) {
              found = true;
            }
          }
        } else if ("profile".equals(purpose)) {
          for (StructureDefinition ex : workerContext.getProfiles()) {
            if (ig.getCode().equals(ToolResourceUtilities.getUsage(ex))) {
              found = true;
            }
          }
        } else
          throw new Exception("Purpose "+purpose+" not supported yet");
        ImplementationGuideDefinitionPageComponent p = ig.getRegistryPage(type);
        if (found && p != null) {
          if (first)
            first = false;
          else
            b.append(" | ");
          b.append("<a href=\"");
          b.append(ig.getCode());
          b.append("/"+p.getNameUrlType().getValue()+"#"+purpose+"\">");
          b.append(ig.getBrief());
          b.append("</a>");
        }
      }
    }
    return b.toString();
  }


  private String igLink(ImplementationGuideDefn ig) {
    WorkGroup wg = definitions.getWorkgroups().get(ig.getCommittee());
    return wg == null ? "?"+ig.getCommittee()+"?" : wg.getUrl();
  }

  public String pipeResources() {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (String n : definitions.sortedResourceNames()) {
      if (first)
        first = false;
      else
        b.append("|");
      b.append(n);
    }
    return b.toString();
  }

  private String enteredInErrorTable() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"grid\">\r\n");
    b.append("<tr><td><b>Resource</b></td><td><b>Status</b></td></tr>");
    for (String n : definitions.sortedResourceNames()) {
      String s = definitions.getResourceByName(n).getEnteredInErrorStatus();
      b.append("<tr><td><a href=\""+n.toLowerCase()+".html\">"+n+"</a></td><td>"+Utilities.escapeXml(s)+"</td></tr>");
    }
    b.append("</table>\r\n");
    return b.toString();
  }

  private String genDataTypeUsage(String tn) {
    StringBuilder b = new StringBuilder();
    for (ElementDefn e : definitions.getTypes().values()) {
      if (usesType(e, tn)) {
        b.append(", <a href=\"").append(definitions.getSrcFile(e.getName())+".html#"+e.getName()).append("\">").append(e.getName()).append("</a>");
      }
    }
    List<String> resources = new ArrayList<String>();
    for (ResourceDefn e : definitions.getResources().values()) {
      if (usesType(e.getRoot(), tn)) {
        resources.add(e.getName());
      }
    }
    for (ResourceDefn e : definitions.getBaseResources().values()) {
      if (usesType(e.getRoot(), tn)) {
        resources.add(e.getName());
      }
    }
    Collections.sort(resources);
    for (String n : resources)
      b.append(", <a href=\"").append(definitions.getSrcFile(n)+".html#"+n.toLowerCase()).append("\">").append(n).append("</a>");

    if (b.toString().length() < 2)
      return "(not used as yet)";

    String s = b.toString().substring(2);
    int i = s.lastIndexOf(", ");
    if ( i > 1)
      s = s.substring(0, i)+" and"+s.substring(i+1);
    return s;
  }

  private boolean usesType(ElementDefn e, String tn) {
    if (usesType(e.getTypes(), tn))
      return true;
    for (ElementDefn c : e.getElements())
      if (usesType(c, tn))
        return true;
    return false;
  }

  private boolean usesType(List<TypeRef> types, String tn) {
    for (TypeRef t : types) {
      if (t.getName().equals(tn))
        return true;
      // no need to check parameters
    }
    return false;
  }

  String processResourceIncludes(String name, ResourceDefn resource, String xml, String json, String ttl, String tx, String dict, String src, String mappings, String mappingsList, String type, String pagePath, ImplementationGuideDefn ig, Map<String, String> otherValues, WorkGroup wg, Map<String, String> examples) throws Exception {
    String workingTitle = Utilities.escapeXml(resource.getName());
    List<String> tabs = new ArrayList<String>();
    int level = (ig == null || ig.isCore()) ? 0 : 1;

    while (src.contains("<%") || src.contains("[%"))
    {
      int i1 = src.indexOf("<%");
      int i2 = src.indexOf("%>");
      if (i1 == -1) {
        i1 = src.indexOf("[%");
        i2 = src.indexOf("%]");
      }
      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);

      String[] com = s2.split(" ");
      String searchAdditions = "";
      if (com[0].equals("resheader"))
        src = s1+resHeader(name, resource.getName(), com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("aresheader"))
        src = s1+abstractResHeader(name, resource.getName(), com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("lmheader"))
        src = s1+lmHeader(name, resource.getName(), com.length > 1 ? com[1] : null, false)+s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }
      else if (com[0].equals("complinks"))
        src = s1+getCompLinks(resource, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("othertabs"))
        src = s1 + genOtherTabs(com[1], tabs) + s3;
      else if (com[0].equals("svg"))
        src = s1+new SvgGenerator(this, genlevel(level), resource.getLayout(), true, false).generate(resource, com[1])+s3;
      else if (com[0].equals("normative")) {
        String np = null;
        if (com[2].equals("%check") || com[2].equals("%check-op")) {
          StandardsStatus st = resource.getStatus();
          boolean mixed = false;
          if (com[2].equals("%check-op") && st == StandardsStatus.NORMATIVE) {
            for (Operation op : resource.getOperations()) {
              if (op.getStandardsStatus() != null)
                mixed = true;
            }
          }
          if (st != null && (resource.getNormativePackage() != null || resource.getNormativeVersion() != null)) {
            if (mixed)
              np = getMixedNormativeNote(genlevel(level), resource.getNormativePackage(), com[1], workingTitle, name+".html")+s3;
            else
              np = getNormativeNote(genlevel(level), resource.getNormativePackage(), com[1], workingTitle, name+".html")+s3;
          }
        } else 
          np = getNormativeNote(genlevel(level), resource.getNormativePackage(), com[1], workingTitle, name+".html");
        if (np == null)  
          src = s1+s3;
        else
          src = s1+np+s3;
      } else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+name);
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(resource.getName())+s3;
      else if (com[0].equals("maponthispage"))
          src = s1+mapOnThisPage(mappingsList)+s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+workingTitle+s3;
      else if (com[0].equals("xtitle"))
        src = s1+Utilities.escapeXml(resource.getName())+s3;
      else if (com[0].equals("status"))
        src = s1+resource.getStatus()+s3;
      else if (com[0].equals("draft-note"))
        src = s1+getDraftNote(resource)+s3;
      else if (com[0].equals("introduction"))
        src = s1+loadXmlNotes(name, "introduction", true, resource.getRoot().getDefinition(), resource, tabs, null, wg)+s3;
      else if (com[0].equals("notes"))
        src = s1+loadXmlNotes(name, "notes", false, null, resource, tabs, null, wg)+s3;
      else if (com[0].equals("examples"))
        src = s1+produceExamples(resource)+s3;
      else if (com[0].equals("profilelist"))
        src = s1+produceProfiles(resource)+s3;
      else if (com[0].equals("extensionlist"))
        src = s1+produceExtensions(resource)+s3;
      else if (com[0].equals("extensionreflist"))
        src = s1+produceRefExtensions(resource)+s3;
      else if (com[0].equals("searchextensionlist"))
        src = s1+produceSearchExtensions(resource)+s3;
      else if (com[0].equals("wg"))
        src = s1+(resource.getWg() == null ?  "null" : resource.getWg().getUrl())+s3;
      else if (com[0].equals("wgt"))
        src = s1+(resource.getWg() == null ?  "null" : resource.getWg().getName())+s3;
      else if (com[0].equals("fmm"))
        if (resource.getNormativeVersion() != null)
          src = s1+"<a href=\"versions.html#maturity\">Maturity Level</a>: <a href=\"versions.html#std-process\">N</a>"+s3;
        else
          src = s1+"<a href=\"versions.html#maturity\">Maturity Level</a>: "+resource.getFmmLevel()+""+s3;
      else if (com[0].equals("sec-cat"))
        src = s1+(resource.getSecurityCategorization() == null ? "" : "<a href=\"security.html#SecPrivConsiderations\">Security Category</a>: "+resource.getSecurityCategorization().toDisplay())+s3;
      else if (com[0].equals("sstatus")) 
        src = s1+getStandardsStatus(resource.getName())+s3;
      else if (com[0].equals("example-list"))
        src = s1+produceExampleList(resource)+s3;
      else if (com[0].equals("name"))
        src = s1+name+s3;
      else if (com[0].equals("cname"))
        src = s1+resource.getName()+s3;
      else if (com[0].equals("search-additions")) {
        searchAdditions = s2.substring(16).trim();
        src = s1+s3;
      } else if (com[0].equals("search"))
        src = s1+getSearch(resource, searchAdditions )+s3;
      else if (com[0].equals("asearch"))
        src = s1+getAbstractSearch(resource, searchAdditions)+s3;
      else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("definition"))
        src = s1+processMarkdown("resource.definition", resource.getRoot().getDefinition(), "", true)+s3;
      else if (com[0].equals("xml"))
        src = s1+xml+s3;
      else if (com[0].equals("json"))
        src = s1+json+s3;
      else if (com[0].equals("ttl"))
        src = s1+ttl+s3;
      else if (com[0].equals("tx"))
        src = s1+tx+s3;
      else if (com[0].equals("inv"))
        src = s1+genResourceConstraints(resource, genlevel(level))+s3;
      else if (com[0].equals("resource-table"))
        src = s1+genResourceTable(resource, genlevel(level))+s3;
      else if (com[0].equals("plural"))
        src = s1+Utilities.pluralizeMe(name)+s3;
      else if (com[0].equals("dictionary"))
        src = s1+dict+s3;
      else if (com[0].equals("mappings"))
          src = s1+mappings+s3;
      else if (com[0].equals("mappingslist"))
          src = s1+mappingsList+s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(name) + s3;
      else if (com[0].equals("ext-link"))
        src = s1 + getExtensionsLink(resource) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(name, genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist"))
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(name, type, genlevel(level), workingTitle) : ig.makeList(name, type, genlevel(level), workingTitle)) + s3;
      else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;
      else if (com[0].equals("buildId"))
        src = s1 + buildId + s3;
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;
      else if (com[0].equals("atitle"))
        src = s1 + abstractResourceTitle(resource) + s3;
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;
      else if (com[0].equals("example-header"))
        src = s1 + loadXmlNotesFromFile(Utilities.path(folders.srcDir, name.toLowerCase(), name+"-examples-header.xml"), false, null, resource, tabs, null, wg)+s3;
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;
      else if (com[0].equals("resref"))
        src = s1 + getReferences(resource.getName()) + s3;
      else if (com[0].equals("pagepath"))
        src = s1 + pagePath + s3;
      else if (com[0].equals("rellink")) {
        if (!pagePath.contains(".html"))
          throw new Error("Invalid link: "+pagePath+" at "+workingTitle);
        src = s1 + Utilities.URLEncode(pagePath) + s3;
      } else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;
      else if (com[0].equals("baseURLn"))
        src = s1 + Utilities.appendForwardSlash(baseURL) + s3;
      else if (com[0].equals("operations")) {
        List<Operation> oplist = resource.getOperations(); 
        String n = resource.getName(); 
        String id = resource.getName().toLowerCase();
        boolean mixed = false;
        if (resource.getStatus() == StandardsStatus.NORMATIVE) {
          for (Operation op : resource.getOperations()) {
            if (op.getStandardsStatus() != null)
              mixed = true;
          }
        }
        src = s1 + genOperations(oplist, n, id, mixed, resource.getStatus(), "", resource.getNormativePackage()) + s3;
      } else if (com[0].equals("operations-summary"))
        src = s1 + genOperationsSummary(resource.getOperations(), resource) + s3;
      else if (com[0].equals("opcount"))
        src = s1 + genOpCount(resource.getOperations()) + s3;
      else if (com[0].startsWith("!"))
        src = s1 + s3;
      else if (com[0].equals("search-footer"))
        src = s1+searchFooter(level)+s3;
      else if (com[0].equals("search-header"))
        src = s1+searchHeader(level)+s3;
      else if (com[0].equals("diff-analysis"))
        src = s1+diffEngine.getDiffAsHtml(this, resource.getProfile())+s3;
      else if (com[0].equals("r3r4transforms"))
        src = s1+getR3r4transformNote(resource.getName())+s3;
      else if (com[0].equals("fmm-style"))
        src = s1+fmmBarColorStyle(resource)+s3;
      else if (otherValues.containsKey(com[0]))
        src = s1+otherValues.get(com[0])+s3;
      else if (com[0].equals("lmimplementations"))
        src = s1+genImplementationList(resource)+s3; 
      else if (com[0].equals("json-schema"))
        src = s1+jsonSchema(resource.getName())+s3; 
      else if (com[0].equals("dependency-graph"))
        src = s1+genDependencyGraph(resource, genlevel(level))+s3; 
      else if (com[0].equals("logical-mappings"))
        src = s1+genLogicalMappings(resource, genlevel(level))+s3; 
      else if (com[0].equals("no-extensions-base-warning"))
        src = s1+genNoExtensionsWarning(resource)+s3; 
      else if (com[0].equals("res-ext-link"))  
        src = s1+genResExtLink(resource)+s3;
      else if (com[0].equals("resurl")) {
        if (isAggregationEndpoint(resource.getName()))
          src = s1+s3;
        else
          src = s1+"<p>The resource name as it appears in a  RESTful URL is <a href=\"http.html#root\">[root]</a>/"+name+"/</p>"+s3;
      } else if (macros.containsKey(com[0])) {
        src = s1+macros.get(com[0])+s3;
      } else
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+name);

    }
    return src;
  }

  private String genNoExtensionsWarning(ResourceDefn resource) {
    boolean hasExtensions = resource.getRoot().typeCode().equals("DomainResource");
    if (hasExtensions)
      return "";
    else
      return "<p>Resources of type "+resource.getName()+" do not have extensions at the root element, but extensions MAY be present on the elements in the resource</p>\r\n";
  }

  private String genImplementationList(ResourceDefn logical) throws FHIRException {
    String url = getLogicalMappingUrl(logical);
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"lines\">\r\n");
    for (String s : sorted(definitions.getResources().keySet())) {
      ResourceDefn rd = definitions.getResourceByName(s);
      StructureDefinition sd = rd.getProfile();
      String code = null;
      for (StructureDefinitionMappingComponent m : sd.getMapping()) {
        if (m.getUri().equals(url))
          code = m.getIdentity();
      }
      if (code != null) {
        if (hasLogicalMapping(sd, logical, code)) {
          b.append(" <tr>\r\n");
          b.append("  <td><a href=\""+rd.getName().toLowerCase()+".html\">"+rd.getName()+"</a>&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
          b.append("  <td><a href=\""+rd.getName().toLowerCase()+"-mappings.html#"+url+"\">Mappings</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>\r\n");
          b.append("  <td><a href=\""+rd.getName().toLowerCase()+"-examples.html\">Examples</a></td>\r\n");
          b.append(" </tr>\r\n");
        }
      }
    }
    
    b.append("</table>");    
    return b.toString();
  }

  private String genLogicalMappings(ResourceDefn logical, String genlevel) throws FHIRException {
    String url = getLogicalMappingUrl(logical);
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"lmap\">");
    b.append(" <tr>");
    b.append("  <th></th>\r\n");
    List<ElementDefn> elements = new ArrayList<ElementDefn>();
    listAllElements(elements, logical.getRoot().getName(), logical.getRoot());
    for (ElementDefn e : elements) {
      if (logical.getRoot().getElements().contains(e))
        b.append("<td><a href=\""+logical.getName().toLowerCase()+"-definitions.html#"+e.getPath()+"\">"+e.getName()+"</a></td>\r\n");
      else
        b.append("<td><a href=\""+logical.getName().toLowerCase()+"-definitions.html#"+e.getPath()+"\">."+e.getName()+"</a></td>\r\n");
    }      
    b.append(" </tr>\r\n");

    boolean any = false;
    for (String s : sorted(definitions.getResources().keySet())) {
      ResourceDefn rd = definitions.getResourceByName(s);
      StructureDefinition sd = rd.getProfile();
      String code = null;
      for (StructureDefinitionMappingComponent m : sd.getMapping()) {
        if (m.getUri().equals(url))
          code = m.getIdentity();
      }
      if (code != null) {
        if (hasLogicalMapping(sd, logical, code)) {
          any = true;
          b.append(" <tr>\r\n");
          b.append("  <td><a href=\""+rd.getName().toLowerCase()+".html\">"+rd.getName()+"</a></td>\r\n");
          for (ElementDefn e : elements) {
            populateLogicalMappingColumn(b, logical.getRoot().getName(), rd.getName().toLowerCase()+"-definitions.html#", e, sd, rd.getName(), code, url);
          }
          b.append(" </tr>\r\n");
        }
      }
    }
    b.append("</table>\r\n");
    if (any)
      return "<a name=\"mappings\"></a><h3>Mappings</h3>\r\n\r\n"+ b.toString();
    else
      return "";
  }

  public String getLogicalMappingUrl(ResourceDefn logical) {
    String url = null;
    if (logical.getName().equals("fivews"))
      url = "http://hl7.org/fhir/fivews";
    else
      url = "http://hl7.org/fhir/workflow";
    return url;
  }

  private void listAllElements(List<ElementDefn> elements,  String path, ElementDefn logical) {
    for (ElementDefn c : logical.getElements()) {
      c.setPath(path+"."+c.getName());
      elements.add(c);
      listAllElements(elements, c.getPath(), c);
    }
  }

  private class LogicalModelSupportInformation {
    int elementcount;
    boolean extension;
    boolean nameChanged;
    boolean typeMismatch;
    boolean cardinalityProblem;
    List<String> notes = new ArrayList<String>();
  }
  private final static String LOGICAL_MAPPING_MISMATCH_COLOR = "#ffebe6";
  private final static String LOGICAL_MAPPING_NAMECHANGE_COLOR = "#e6ffff";
  private final static String LOGICAL_MAPPING_EXTENSION_COLOR = "#ffffe6";
  private final static String LOGICAL_MAPPING_MAPPED_COLOR = "#ffffff";
  private final static String LOGICAL_MAPPING_NOTMAPPED_COLOR = "#f2f2f2";
  
  private void populateLogicalMappingColumn(StringBuilder b, String n, String page, ElementDefn e, StructureDefinition sd, String rn, String code, String url) {
    LogicalModelSupportInformation info = new LogicalModelSupportInformation();

    for (ElementDefinition ed : sd.getSnapshot().getElement()) { 
      for (ElementDefinitionMappingComponent m : ed.getMapping()) {
        if (m.getIdentity().equals(code)) {
          String s = m.getMap();
          for (String p : s.split("\\,")) {
            String f = p.contains("{") ? p.substring(0, p.indexOf("{")) : p;
            if (f.equals(e.getPath())) {
              checkMapping(info, e, ed);
           }
          }
        }
      }
    }
    
    for (StructureDefinition ext : workerContext.getExtensionDefinitions()) {
      boolean ok = false;
      for (StructureDefinitionContextComponent ec : ext.getContext()) {
        if (ec.getType() == ExtensionContextType.ELEMENT) {
          if (rn.equals(ec.getExpression()))
            ok = true;
        }
        if (ok) {
          String map = getWorkflowMapping(ext, url);
          if (map != null) {
            for (String p : map.split("\\,")) {
              String f = p.contains("{") ? p.substring(0, p.indexOf("{")) : p;
              if (f.equals(e.getPath())) {
                checkExtMapping(info, e, ext);
              }
            }
          }
        }
      }
    }

    // color: 
    //   one color when supported and aligned
    //   one color when not aligned
    //   one coor when color extension  
    //   blank for no mapping
    String color;
    if (info.typeMismatch || info.cardinalityProblem) 
      color = LOGICAL_MAPPING_MISMATCH_COLOR;
    else if (info.nameChanged) 
      color = LOGICAL_MAPPING_NAMECHANGE_COLOR;
    else if (info.extension) 
      color = LOGICAL_MAPPING_EXTENSION_COLOR;
    else if (info.elementcount> 0)
      color = LOGICAL_MAPPING_MAPPED_COLOR;
    else 
      color = LOGICAL_MAPPING_NOTMAPPED_COLOR;
    
    StringBuilder ns = new StringBuilder();
    for (String s : info.notes) {
      if (ns.length() > 0)
        ns.append("; ");
      ns.append(s);
    }
    b.append("  <td style=\"background-color: "+color+"\" title=\""+ns.toString()+"\">");
    if (info.elementcount > 0)
      b.append(info.elementcount);
    else if (info.extension)
      b.append("E");
    b.append(" ");
    if (info.nameChanged)
      b.append("N");
    if (info.typeMismatch)
      b.append("T");
    if (info.cardinalityProblem)
      b.append("C");
    b.append("</td>\r\n");
  }

  private String getWorkflowMapping(StructureDefinition ext, String url) {
    String code = null;
    for (StructureDefinitionMappingComponent m : ext.getMapping()) {
      if (m.getUri().equals(url))
        code = m.getIdentity();
    }
    if (code != null) {
      for (ElementDefinitionMappingComponent m : ext.getSnapshot().getElementFirstRep().getMapping()) {
        if (m.getIdentity().equals(code)) {
          return m.getMap();
        }
      }           
    }
    return null;
  }

  private void checkMapping(LogicalModelSupportInformation info, ElementDefn logical, ElementDefinition resource) {
    info.elementcount++;
    String s = logical.getPath()+" : "+logical.typeCode()+" ["+logical.describeCardinality()+"] =&gt; "+resource.getPath()+" : "+resource.typeSummary()+" ["+resource.getMin()+".."+resource.getMax()+"]";
    if (!logical.getName().equals(edPathTail(resource.getPath()))) {
      info.nameChanged = true;
    }
    String cardinalityError = checkCardinality(logical, resource);
    if (!Utilities.noString(cardinalityError)) {
      info.cardinalityProblem = true;
      s = s + " " +cardinalityError;
    }
    String typeError = checkType(logical, resource);
    if (!Utilities.noString(typeError)) {
      info.typeMismatch = true;
      s = s + " " +typeError;
    }
    info.notes.add(s);
  }

  private Object edPathTail(String path) {
    return path.substring(path.lastIndexOf(".")+1);
  }

  private void checkExtMapping(LogicalModelSupportInformation info, ElementDefn logical, StructureDefinition extension) {
    info.extension = true;
    ElementDefinition e = extension.getSnapshot().getElementFirstRep();
    ElementDefinition v = null;
    for (ElementDefinition ed : extension.getSnapshot().getElement()) {
      if ("Extension.value[x]".equals(ed.getBase().getPath()))
        v = ed;
    }
    String s = logical.getPath()+" : "+logical.typeCode()+" ["+logical.describeCardinality()+"] =&gt; Extension "+tail(extension.getUrl())+" : "+v.typeSummary()+" ["+e.getMin()+".."+e.getMax()+"]";
    String cardinalityError = checkCardinality(logical, e);
    if (!Utilities.noString(cardinalityError)) {
      info.cardinalityProblem = true;
      s = s + " " +cardinalityError;
    }
    String typeError = checkType(logical, v);
    if (!Utilities.noString(typeError)) {
      info.typeMismatch = true;
      s = s + " " +typeError;
    }
    info.notes.add(s);
  }



  private String checkType(ElementDefn logical, ElementDefinition resource) {
    String s = "";
    for (TypeRefComponent rt : resource.getType()) {
      if (!checkType(logical, rt)) {
        String m = "The type '"+rt.getWorkingCode()+"' is not legal according to the pattern ("+resource.typeSummary()+" vs "+logical.typeCode()+") ";
        s = Utilities.noString(s) ? m : s + ", "+m;
      }
    }
    return s;
  }

  private boolean checkType(ElementDefn logical, TypeRefComponent rt) {
    for (TypeRef lt : logical.getTypes()) {
      if (lt.getName().equals(rt.getWorkingCode()))
        return true;
    }
    return false;
  }

  private String checkCardinality(ElementDefn logical, ElementDefinition resource) {
    String s = "";
    if (resource.getMin() < logical.getMinCardinality())
      s = "Minimum Cardinality Violation (pattern = 1, resource = 0)";
    if (!resource.getMax().equals("1") && logical.getMaxCardinality() == 1)
      s = Utilities.noString(s) ? "Maximum Cardinality Violation (pattern = 1, resource = *)" : s + ", Maximum Cardinality Violation (pattern = 1, resource = *)";
    return s;
  }

  private boolean hasLogicalMapping(StructureDefinition sd, ResourceDefn logical, String code) {
    for (ElementDefinition ed : sd.getSnapshot().getElement()) { 
      for (ElementDefinitionMappingComponent m : ed.getMapping()) {
        if (m.getIdentity().equals(code)) {
          String s = m.getMap();
          if (s.equals(logical.getRoot().getName()) || s.startsWith(logical.getRoot().getName()+"."))
            return true;
        }
      }
    }
    return false;
  }

  private void addLogicalElementColumn(StringBuilder b, boolean root, ElementDefn e) {
    for (ElementDefn c : e.getElements()) 
      addLogicalElementColumn(b, false, c);    
  }

  private String jsonSchema(String name) throws FileNotFoundException, IOException {
    return Utilities.escapeXml(TextFile.fileToString(folders.tmpResDir+name+".schema.json"));
  }

  private String genDependencyGraph(ResourceDefn resource, String prefix) throws Exception {    
    ElementDefn e = resource.getRoot();
    ResourceDependencyGenerator gen = new ResourceDependencyGenerator(folders.dstDir, this, resource.getName()+"-definitions.html", false, resource.getFmmLevel(), resource.getStatus());
    return "<p>Dependency Graph for "+resource.getName()+" FMM level "+resource.getFmmLevel()+"</p>" + new XhtmlComposer(XhtmlComposer.HTML).compose(gen.generate(e, prefix));

  }

  private String genExampleList(Map<String, String> examples) {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"list\">\r\n");
    for (String n : examples.keySet()) {
      b.append("<tr><td>"+examples.get(n)+"</td><td><a href=\""+n+".xml.html\">XML</a></td><td><a href=\""+n+".json.html\">JSON</a></td></tr>\r\n");
    }
    b.append("</table>\r\n");
    return b.toString();
  }


  private String fmmBarColorStyle(ResourceDefn resource) {
    if (resource.getNormativePackage() != null || resource.getNormativeVersion() != null)
      return "colsn";
    else {
    return fmmBarColorStyle(resource.getStatus(), resource.getFmmLevel());
    }
  }

  public String fmmBarColorStyle(StandardsStatus status, String fmm) {
    if (status == null)
      return "0".equals(fmm) ? "colsd" : "colstu";
    switch (status) {
    case DRAFT: return "colsd";
    case TRIAL_USE: return "0".equals(fmm) ? "colsd" : "colstu"; 
    case NORMATIVE: return "colsn";
    case INFORMATIVE: return "colsi";
    case EXTERNAL: return "colse";
    default:
      return "colsi";
    }
  }

  private String getR3r4transformNote(String name) throws IOException {
    StringBuilder b = new StringBuilder();
    if (new File(Utilities.path(folders.rootDir, "implementations", "r3maps", "R3toR4", name+".map")).exists()) {
      String st = r3r4StatusForResource(name);
      return "<p>See <a href=\""+name.toLowerCase()+"-version-maps.html\">R3 &lt;--&gt; R4 Conversion Maps</a> (status = "+st+")</p>\r\n";
    } else
    return "";
  }

  private String getCompLinks(ResourceDefn resource, String param) {
    List<String> names = new ArrayList<String>();
    for (Compartment comp : definitions.getCompartments()) {
      if (comp.getResources().containsKey(resource) && !Utilities.noString(comp.getResources().get(resource)))
        names.add(comp.getName());
    }
    StringBuilder b = new StringBuilder();
    b.append("<a href=\"compartmentdefinition.html\">Compartments</a>: ");
    if ("n/a".equals(param))
      b.append("N/A");
    else {
      if (names.isEmpty())
        b.append("Not linked to any defined compartments");
      else {
        Collections.sort(names);
        boolean first = true;
        for (String name : names) {
          if (first)
            first = false;
          else
            b.append(", ");
          b.append("<a href=\"compartmentdefinition-"+name.toLowerCase()+".html\">"+definitions.getCompartmentByName(name).getTitle()+"</a>");
        }
      }
    }
    return b.toString();
  }

  private String getDraftNote(ResourceDefn resource) {
    if ("draft".equals(resource.getStatus()))
      return "<p style=\"background-color: salmon; border:1px solid maroon; padding: 5px;\">This resource is <a href=\"history.html#levels\">marked as a draft</a>.</p>";
    else
      return "";
  }

  public String getDraftNote(StructureDefinition definition) {
    if ("draft".equals(definition.getStatus().toCode()))
      return "<p style=\"background-color: salmon; border:1px solid maroon; padding: 5px;\">This artefact is <a href=\"history.html#levels\">marked as a draft</a>.</p>";
    else
      return "";
  }

  private String getDraftNote(Profile pack, String prefix) {
    if ("draft".equals(pack.metadata("publication.status")))
      return "<p style=\"background-color: salmon; border:1px solid maroon; padding: 5px;\">This profile is <a href=\""+prefix+"history.html#levels\">marked as a draft</a>.</p>";
    else
      return "";
  }

  private String abstractResourceTitle(ResourceDefn resource) {
    if (resource.getName().equals("Resource"))
      return "Base Resource Definitions";
    else
      return resource.getName() + " Resource";
  }

  private String genOpCount(List<Operation> oplist) {
    return Integer.toString(oplist.size()) + (oplist.size() == 1 ? " operation" : " operations");
  }

  private String genOperationsSummary(List<Operation> oplist, ResourceDefn resource) throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table class=\"list\">\r\n");
    for (Operation op : oplist) {
      b.append("<tr><td><a href=\""+resource.getName().toLowerCase()+"-operation-"+ op.getName()+".html\">$"+Utilities.escapeXml(op.getName())+"</a></td><td>"+Utilities.escapeXml(op.getTitle())+"</td>");
      if (resource.getStatus() == StandardsStatus.NORMATIVE) {
        if (op.getStandardsStatus() == null)
          b.append("<td><a class=\""+resource.getStatus().toCode()+"-flag\" href=\"versions.html#std-process\">"+resource.getStatus().toDisplay()+"</a></td>");
        else
          b.append("<td><a class=\""+op.getStandardsStatus().toCode()+"-flag\" href=\"versions.html#std-process\">"+op.getStandardsStatus().toDisplay()+"</a></td>");
      }
      
      b.append("</tr>\r\n");
    }
    b.append("</table>\r\n");
    return b.toString();
  }
  
  private String genOperation(Operation op, String n, String id, boolean mixed, StandardsStatus resStatus, String prefix, String np) throws Exception {
   
    StringBuilder b = new StringBuilder();
    genOperationInner(n, id, mixed, resStatus, prefix, np, b, op, false);
    return b.toString();
  }

  private String genOperations(List<Operation> oplist, String n, String id, boolean mixed, StandardsStatus resStatus, String prefix, String np) throws Exception {
    
    StringBuilder b = new StringBuilder();
    for (Operation op : oplist) {
      genOperationInner(n, id, mixed, resStatus, prefix, np, b, op, true);
    }
    return b.toString();
  }

  public void genOperationInner(String n, String id, boolean mixed, StandardsStatus resStatus, String prefix, String np, StringBuilder b, Operation op, boolean header) throws Exception {
    if (header)
      b.append("<h3>").append(Utilities.escapeXml(op.getTitle())).append("<a name=\"").append(op.getName()).append("\"> </a></h3>\r\n");
    if (mixed)
      b.append(opStandardsStatusNotice(n, op.getStandardsStatus(), resStatus, np, prefix)+"\r\n");
    b.append(processMarkdown(n, op.getDoco(), prefix, true)+"\r\n");
    b.append("<p>The official URL for this operation definition is</p>\r\n<pre> http://hl7.org/fhir/OperationDefinition/"+n+"-"+op.getName()+"</pre>\r\n");
    b.append("<p><a href=\"operation-"+id+"-"+op.getName().toLowerCase()+".html\">Formal Definition</a> (as a <a href=\""+prefix+"operationdefinition.html\">OperationDefinition</a>).</p>\r\n");
    if (op.isSystem())
      b.append("<p>URL: [base]/$").append(op.getName()).append("</p>\r\n");
    if (op.isType())
      b.append("<p>URL: [base]/").append(checkWrap(n)).append("/$").append(op.getName()).append("</p>\r\n");
    if (op.isInstance())
      b.append("<p>URL: [base]/").append(checkWrap(n)).append("/[id]/$").append(op.getName()).append("</p>\r\n");
    if (op.getIdempotent())
      b.append("<p>This is an idempotent operation</p>\r\n");
    else
      b.append("<p>This is <b>not</b> an idempotent operation</p>\r\n");
    if (!op.getParameters().isEmpty()) {
      b.append("<table class=\"grid\">\r\n");
      if (hasParameters(op.getParameters(), "In")) {
        genParameterHeader(b, "In");
        for (OperationParameter p : op.getParameters())
          genOperationParameter(n, "In", "", b, op, p, prefix);
      }
      if (hasParameters(op.getParameters(), "Out")) {
        genParameterHeader(b, "Out");
        for (OperationParameter p : op.getParameters())
          genOperationParameter(n, "Out", "", b, op, p, prefix);
      }
      b.append("</table>\r\n");
    }
    b.append(processMarkdown(n, op.getFooter(), prefix)).append("\r\n");
    if (op.getExamples().size() > 0) {
      b.append("<a name=\"examples\"> </a>\r\n<h4>Examples</h4>\r\n");
      boolean needsHr = false;
      boolean hasHr = false;
      for (OperationExample ex : op.getExamples())
        if (!ex.isResponse()) {
          needsHr = true;
          renderExample(b, ex, "Request");
        }
     
      for (OperationExample ex : op.getExamples())
        if (ex.isResponse()) {
          if (needsHr && !hasHr) {
            hasHr = true;
            b.append("<hr/>\r\n");
          }
          renderExample(b, ex, "Response");
        }
    }
    if (!Utilities.noString(op.getFooter2())) {
      b.append(processMarkdown(n, op.getFooter2(), prefix)).append("\r\n");
      boolean needsHr = false;
      boolean hasHr = false;
      if (op.getExamples2().size() > 0) {
        for (OperationExample ex : op.getExamples2())
          if (!ex.isResponse()) {
            needsHr = true;
            renderExample(b, ex, "Request");
          }
        for (OperationExample ex : op.getExamples2())
          if (ex.isResponse()) {
            if (needsHr && !hasHr) {
              hasHr = true;
              b.append("<hr/>\r\n");
            }
            renderExample(b, ex, "Response");
          }            
      }      
    }
    b.append("<p>&nbsp;</p>");
  }

  private String opStandardsStatusNotice(String n, StandardsStatus opStatus, StandardsStatus resStatus, String pack, String prefix) {
    if (resStatus == StandardsStatus.NORMATIVE && opStatus == StandardsStatus.TRIAL_USE)
      return "<p style=\"border: 1px black solid; background-color: #ffe6e6; padding: 5px\">\r\n" + 
        "Normative Candidate Note: Though the resource is a candidate for normative for R4, this operation is not included. It's status will remain 'Trial Use' while more experience is gathered.\r\n" + 
        "</p>\r\n";
    if (resStatus == StandardsStatus.NORMATIVE && opStatus == null)
      return ansiNote("This operation has", pack, "");
    else
    return "";
//    return "<p style=\"border: 1px black solid; background-color: #e6ffe6; padding: 5px\">\r\n" + 
//    "ANSI Note: This operation is normative content in the <a href=\""+prefix+"ansi-"+pack+".html\">"+Utilities.capitalize(pack)+" Package</a>.\r\n" + 
//    "</p>\r\n" + 
//    "";
  }

  private String checkWrap(String n) {
    if (n.equals("Resource"))
      return "[Resource]";
    else
      return n;
  }

  private void renderExample(StringBuilder b, OperationExample ex, String type) throws Exception {
    if (Utilities.noString(ex.getComment()))
      b.append("<p>"+type+":</p>\r\n");
    else
      b.append("<p>"+ processMarkdown("op-example", type+": "+Utilities.capitalize(ex.getComment()), "")+"</p>\r\n");

    b.append("<pre>\r\n");
    String[] lines = ex.getContent().split("\\r\\n");
    for (String l : lines) {
      if (l.startsWith("$bundle ")) {
        b.append(Utilities.escapeXml("<Bundle xml=\"http://hl7.org/fhir\">\r\n"));
        b.append(Utilities.escapeXml("  <id value=\""+UUID.randomUUID().toString().toLowerCase()+"\"/>\r\n"));
        b.append(Utilities.escapeXml("  <type value=\"searchset\"/>\r\n"));
        Example e = getExampleByRef(l.substring(8));
        addExample(b, e);
        for (Example x : e.getInbounds()) {
          addExample(b, x);
        }
        b.append(Utilities.escapeXml("</Bundle>\r\n"));
      } else {
        b.append(l);
        b.append("\r\n");
      }
    }
    b.append("</pre>\r\n");
  }

  private void addExample(StringBuilder b, Example x) throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
    b.append(Utilities.escapeXml("  <entry>\r\n"));
    b.append(Utilities.escapeXml("    <fullUrl value=\"http://hl7.org/fhir/"+x.getResourceName()+"/"+x.getId()+"\"/>\r\n"));
    b.append(Utilities.escapeXml("    <resource>\r\n"));
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    DOMSource source = new DOMSource(x.getXml());
    StringWriter writer =  new StringWriter();
    transformer.transform(source, new StreamResult(writer));
    String[] lines = writer.getBuffer().toString().split("\\n");
    for (String l : lines) {
      b.append("     ");
      if (l.contains("xmlns:xsi=")) {
        b.append(Utilities.escapeXml(l.substring(0, l.indexOf("xmlns:xsi=")-1)));
        b.append(">\r\n");
      } else {
        b.append(Utilities.escapeXml(l));
        b.append("\r\n");
      }
    }
    b.append(Utilities.escapeXml("    </resource>\r\n"));
    b.append(Utilities.escapeXml("  </entry>\r\n"));
  }

  private Example getExampleByRef(String bundle) throws Exception {
    String[] parts = bundle.split("\\/");
    ResourceDefn r = definitions.getResourceByName(parts[0]);
    for (Example e : r.getExamples()) {
      if (e.getId().equals(parts[1]))
        return e;
    }
    throw new Exception("unable to resolve "+bundle);
  }

  private boolean hasParameters(List<OperationParameter> parameters, String mode) {
    for (OperationParameter p : parameters) {
      if (mode.equalsIgnoreCase(p.getUse()))
        return true;
    }
    return false;
  }

  private void genParameterHeader(StringBuilder b, String mode) {
    b.append("<tr><td colspan=\"6\"><b>").append(mode).append(" Parameters:</b></td></tr>\r\n");
    b.append("<tr><td>");
    b.append("<b>Name</b>");
    b.append("</td><td>");
    b.append("<b>Cardinality</b>");
    b.append("</td><td>");
    b.append("<b>Type</b>");
    b.append("</td><td>");
    b.append("<b>Binding</b>");
    b.append("</td><td>");
    b.append("<b>Profile</b>");
    b.append("</td><td>");
    b.append("<b>Documentation</b>");
    b.append("</td></tr>");
  }

  private void genOperationParameter(String resource, String mode, String path, StringBuilder b, Operation op, OperationParameter p, String prefix) throws Exception {
    if (!Utilities.noString(p.getUse()) && !mode.equalsIgnoreCase(p.getUse()))
      return;
    
    b.append("<tr><td>");
    b.append(path+p.getName());
    b.append("</td><td>");
    b.append(p.describeCardinality());
    b.append("</td><td>");
    boolean firstt = true;
    String tl = p.getFhirType();
    boolean isRes = true;
    for (String tx : tl.split("\\|")) {
      String t = tx.trim();
      if (firstt) firstt = false; else b.append(" | ");
      String st = p.getSearchType();
      if (definitions.hasResource(t)) {
        b.append("<a href=\"");
        b.append(prefix);
        b.append(t.toLowerCase());
        b.append(".html\">");
        b.append(t);
        b.append("</a>");
      } else if (definitions.hasPrimitiveType(t)) {
        isRes = false;
        b.append("<a href=\""+prefix+"datatypes.html#");
        b.append(t);
        b.append("\">");
        b.append(t);
        b.append("</a>");
        if (!Utilities.noString(st)) {
          b.append("<br/>(<a href=\""+(prefix == null ? "" : prefix)+"search.html#");
          b.append(st);
          b.append("\">");
          b.append(st);
          b.append("</a>)");
        }
      } else if (definitions.hasElementDefn(t)) {
        isRes = false;
        b.append("<a href=\"");
        b.append(prefix);
        b.append(definitions.getSrcFile(t));
        b.append(".html#");
        b.append(t);
        b.append("\">");
        b.append(t);
        b.append("</a>");

      } else if (t.startsWith("Reference(")) {
        isRes = false;
        b.append("<a href=\""+prefix+"references.html#Reference\">Reference</a>");
        String pn = t.substring(0, t.length()-1).substring(10);
        b.append("(");
        boolean first = true;
        for (String tn : pn.split("\\|")) {
          if (first)
            first = false;
          else
            b.append("|");
          b.append("<a href=\"");
          b.append(prefix);
          if (tn.equals("Any"))
            b.append("resourcelist");
          else
            b.append(tn.toLowerCase());
          b.append(".html\">");
          b.append(tn);
          b.append("</a>");
        }
        b.append(")");
      } else if (!t.equals("Tuple")) {
        b.append(t);
      }
    }
    b.append("</td><td>");
    if (p.getBs() != null && p.getBs().getBinding() != BindingMethod.Unbound) {
      b.append("<a href=\""+BaseGenerator.getBindingLink(prefix, p.getBs())+"\">"+(p.getBs().getValueSet() != null ? p.getBs().getValueSet().present() : p.getBs().getName())+"</a>");
      if (p.getBs().hasMax())
        throw new Error("Max binding not handled yet");

      b.append(" (<a href=\""+prefix+"terminologies.html#"+p.getBs().getStrength().toCode()+"\">"+p.getBs().getStrength().getDisplay()+"</a>)");
    }
    b.append("</td><td>");
    if (!Utilities.noString(p.getProfile())) {
      StructureDefinition sd = profiles.get(p.getProfile());
      if (sd != null)
        b.append("<a href=\""+prefix+sd.getUserString("path")+"\">"+sd.getName()+"</a>");
      else
        b.append(p.getProfile()+" (unknown)");
    }
    b.append("</td><td>");
    b.append(processMarkdown(resource, p.getDoc(), prefix));
    if (p.getName().equals("return") && isOnlyOutParameter(op.getParameters(), p) && isRes)
      b.append("<p>Note: as this is the only out parameter, it is a resource, and it has the name 'return', the result of this operation is returned directly as a resource</p>");
    b.append("</td></tr>");
    if (p.getParts() != null)
      for (OperationParameter pp : p.getParts())
        genOperationParameter(resource, mode, path+p.getName()+".", b, op, pp, prefix);
  }


  private boolean isOnlyOutParameter(List<OperationParameter> parameters, OperationParameter p) {
    for (OperationParameter q : parameters)
      if (q != p && q.getUse().equals("out"))
        return false;
    return p.getUse().equals("out");
  }

  private String getReferences(String name) throws Exception {
    List<String> refs = new ArrayList<String>();
    for (String tn : definitions.sortedTypeNames()) {
      checkReferences(name, refs, tn, definitions.getElementDefn(tn));
    }
    for (String rn : definitions.sortedResourceNames()) {
      checkReferences(name, refs, rn, definitions.getResourceByName(rn).getRoot());
    }
    if (refs.size() == 1)
      return "<p>This resource is referenced by "+renderRef(refs.get(0), name)+"</p>\r\n";
    else if (refs.size() > 1)
      return "<p>This resource is referenced by "+asLinks(refs, name)+"</p>\r\n";
    else
      return "";
  }

  public void checkReferences(String name, List<String> refs, String rn, ElementDefn r) throws FHIRException {
    if (usesReference(r, name)) {
      refs.add(rn);
    }
    if (name.equals("CodeSystem") && Utilities.existsInList(rn, "ValueSet", "ConceptMap", "Coding") && !refs.contains(rn))
      refs.add(rn);
  }

  private String renderRef(String ref, String name) {
    if (ref.equals(name))
      return "itself";
    else
      return "<a href=\""+definitions.getSrcFile(ref)+".html#"+ref+"\">"+ref+"</a>";
  }

  private String asLinks(List<String> refs, String name) {
    StringBuilder b = new StringBuilder();
    for (int i = 0; i < refs.size(); i++) {
      if (i == refs.size() - 1)
        b.append(" and ");
      else if (i > 0)
        b.append(", ");
      b.append(renderRef(refs.get(i), name));
    }
    return b.toString();
  }

  private boolean usesReference(ElementDefn e, String name) {
    if (usesReference(e.getTypes(), name))
      return true;
    for (ElementDefn c : e.getElements()) {
      if (usesReference(c, name))
        return true;
    }
    return false;
  }

  private boolean usesReference(List<TypeRef> types, String name) {
    for (TypeRef t : types) {
      if (t.getName().equals("Reference") || t.getName().equals("canonical") ) {
        for (String p : t.getParams()) {
          if (p.equals(name))
            return true;
        }
      }
    }
    return false;
  }

  /*
  private String prepWikiName(String name) {
    return Utilities.noString(name) ? "Index" : Utilities.capitalize(Utilities.fileTitle(name));
  }
  */

  private String getSearch(ResourceDefn resource, String searchAdditions) {
    if (resource.getSearchParams().size() == 0)
      return "";
    else {
      StandardsStatus st = resource.getStatus();
      
      StringBuilder b = new StringBuilder();
      b.append("<h2>Search Parameters</h2>\r\n");
      if (resource.getName().equals("Query"))
        b.append("<p>Search parameters for this resource. The <a href=\"#all\">common parameters</a> also apply.</p>\r\n");
      else
        b.append("<p>Search parameters for this resource. The <a href=\"search.html#all\">common parameters</a> also apply. See <a href=\"search.html\">Searching</a> for more information about searching in REST, messaging, and services.</p>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Name</b></td><td><b>Type</b></td><td><b>Description</b></td><td><b>Expression</b></td><td><b>In Common</b></td></tr>\r\n");
      List<String> names = new ArrayList<String>();
      names.addAll(resource.getSearchParams().keySet());
      Collections.sort(names);
      for (String name : names)  {
        SearchParameterDefn p = resource.getSearchParams().get(name);
        String pp = presentPaths(p.getPaths());
        String sst = (p.getStandardsStatus() == null || p.getStandardsStatus() == st) ? "" : makeStandardsStatusRef(p.getStandardsStatus());
        
        b.append("<tr><td><a name=\"sp-").append(p.getCode()).append("\"> </a>").append(p.getCode()).append(sst).append("</td><td><a href=\"search.html#").append(p.getType()).append("\">").append(p.getType()).append("</a></td><td>")
                .append(Utilities.escapeXml(p.getDescription())).append("</td><td>").append(p.getType() == SearchType.composite ? getCompositeExpression(p) : Utilities.escapeXml(p.getExpression())).append(p.getType() == SearchType.reference ? p.getTargetTypesAsText() : "")
                .append("</td><td>").append(presentOthers(p)).append("</td></tr>\r\n");
      }
      b.append(searchAdditions);
      b.append("</table>\r\n");
      return b.toString();
    }
  }

  private String makeStandardsStatusRef(StandardsStatus ss) {
    if (ss == null)
      return "";
    return " <a href=\"versions.html#std-process\" title=\""+ss.toDisplay()+" Content\" style=\"padding-left: 3px; padding-right: 3px; border: 1px grey solid; font-weight: bold; color: black; background-color: "+ss.getColor()+"\">"+ss.getAbbrev()+"</a>";
  }

  private String getCompositeExpression(SearchParameterDefn p) {
    StringBuilder b = new StringBuilder();
    b.append("On ");
    b.append(Utilities.escapeXml(p.getExpression()));
    b.append(":");
    for (CompositeDefinition cp : p.getComposites()) {
      b.append("<br/>&nbsp;&nbsp;");
      b.append(cp.getDefinition());
      b.append(": ");
      b.append(Utilities.escapeXml(cp.getExpression()));
    }
    return b.toString();
  }

  private Object presentOthers(SearchParameterDefn p) {
    if (p.getOtherResources().isEmpty())
      return "";
    StringBuilder b = new StringBuilder();
    b.append("<a href=\"searchparameter-registry.html#"+p.getCommonId()+"\">"+Integer.toString(p.getOtherResources().size())+" Resources</a>");
    return b.toString();
  }

  private String getAbstractSearch(ResourceDefn resource, String searchAdditions) {
    if (resource.getSearchParams().size() == 0)
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<h2>Search Parameters</h2>\r\n");
      b.append("<p>Common search parameters defined by this resource. See <a href=\"search.html\">Searching</a> for more information about searching in REST, messaging, and services.</p>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Name</b></td><td><b>Type</b></td><td><b>Description</b></td><td><b>Paths</b></td></tr>\r\n");
      List<String> names = new ArrayList<String>();
      names.addAll(resource.getSearchParams().keySet());
      Collections.sort(names);
      for (String name : names)  {
        SearchParameterDefn p = resource.getSearchParams().get(name);
        b.append("<tr><td>").append(p.getCode()).append("</td><td><a href=\"search.html#").append(p.getType()).append("\">").append(p.getType())
                .append("</a></td><td>").append(Utilities.escapeXml(p.getDescription())).append("</td><td>").append(presentPaths(p.getPaths())).append(p.getType() == SearchType.reference ? p.getTargetTypesAsText() : "").append("</td></tr>\r\n");
      }
      b.append(searchAdditions);
      b.append("</table>\r\n");
      return b.toString();
    }
  }

  private String getSearch(Profile pack) {
    if (pack.getSearchParameters().size() == 0)
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<h2>Search Parameters</h2>\r\n");
      b.append("<p>Search parameters defined by this structure. See <a href=\"search.html\">Searching</a> for more information about searching in REST, messaging, and services.</p>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Name</b></td><td><b>Type</b></td><td><b>Description</b></td><td><b>Paths</b></td></tr>\r\n");
      List<String> names = new ArrayList<String>();
      for (SearchParameter t : pack.getSearchParameters())
        names.add(t.getName());
      Collections.sort(names);
      for (String name : names)  {
        SearchParameter p = null;
        for (SearchParameter t : pack.getSearchParameters())
          if (t.getName().equals(name))
            p = t;
        b.append("<tr><td>").append(p.getName()).append("</td><td><a href=\"search.html#").append(p.getType().toCode()).append("\">").append(p.getType().toCode())
                .append("</a></td><td>").append(Utilities.escapeXml(p.getDescription())).append("</td><td>").append(p.getXpath() == null ? "" : p.getXpath()).append("</td></tr>\r\n");
      }
      b.append("</table>\r\n");
      return b.toString();
    }
  }

  private String presentPaths(List<String> paths) {
    if (paths == null || paths.size() == 0)
      return "";
    if (paths.size() == 1)
      return paths.get(0);
    StringBuilder b = new StringBuilder();
    for (String p : paths) {
      if (b.length() != 0)
        b.append(", ");
      b.append(p);
    }
    return b.toString();
  }

  private String produceExamples(ResourceDefn resource) {
    StringBuilder s = new StringBuilder();
    for (Example e: resource.getExamples()) {
        s.append("<tr><td>").append(Utilities.escapeXml(e.getDescription())).append("</td><td><a href=\"")
                .append(e.getTitle()).append(".xml\">source</a></td><td><a href=\"").append(e.getTitle()).append(".xml.html\">formatted</a></td></tr>");
    }
    return s.toString();
  }

  private class CSPair {
    Profile p;
    ConstraintStructure cs;
    public CSPair(Profile p, ConstraintStructure cs) {
      super();
      this.p = p;
      this.cs = cs;
    }
  }

  private String produceProfiles(ResourceDefn resource) {
    int count = 0;
    Map<String, CSPair> map = new HashMap<String, CSPair>();
    for (Profile ap: resource.getConformancePackages()) {
      for (ConstraintStructure cs : ap.getProfiles()) {
        if (coversResource(cs, resource.getName()))
          map.put(cs.getTitle(), new CSPair(ap, cs));
      }
    }
    for (Profile ap: definitions.getPackList()) {
      for (ConstraintStructure cs : ap.getProfiles()) {
        if (coversResource(cs, resource.getName()))
          map.put(cs.getTitle(), new CSPair(ap, cs));
      }
    }

    StringBuilder b = new StringBuilder();
    for (String s : sorted(map.keySet())) {
      CSPair cs = map.get(s);
      ImplementationGuideDefn ig = definitions.getIgs().get(cs.p.getCategory());
      count++;
      b.append("  <tr>\r\n");
      String ref = (ig.isCore() ? "" : ig.getCode()+File.separator)+cs.cs.getId()+".html";
      b.append("    <td><a href=\"").append(ref).append("\">").append(Utilities.escapeXml(cs.cs.getTitle())).append("</a></td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(cs.p.getDescription())).append("</td>\r\n");
      ref = (ig.isCore() ? "" : ig.getCode()+File.separator)+cs.p.getId().toLowerCase()+".html";
      b.append("    <td><a href=\"").append(ref).append("\">").append(Utilities.escapeXml(cs.p.getTitle())).append("</a></td>\r\n");
      b.append(" </tr>\r\n");
    }
    if (count == 0)
      return "<p>No Profiles defined for this resource</p>";
    return 
      "<table class=\"list\">\r\n"+
      " <tr><td><b>Profile</b></td><td><b>Description</b></td><td><b>Context</b></td></tr>\r\n"+
      b.toString()+
      "</table>\r\n";

  }

  private String produceExtensions(ResourceDefn resource) {
    int count = 0;
    Map<String, StructureDefinition> map = new HashMap<String, StructureDefinition>();
    for (StructureDefinition sd : workerContext.getExtensionDefinitions()) {
      boolean inc = false;
      for (StructureDefinitionContextComponent ec : sd.getContext()) {
        if (ec.getType() == ExtensionContextType.ELEMENT) {
          inc = inc || (ec.getExpression().equals(resource.getName()) || ec.getExpression().startsWith(resource.getName()+"."));
        }
        if (inc)
          map.put(sd.getId(), sd);
      }
    }

    StringBuilder b = new StringBuilder();
    for (String s : sorted(map.keySet())) {
      StructureDefinition cs = map.get(s);
      count++;
      b.append("  <tr>\r\n");
      String ref = cs.getUserString("path");
      b.append("    <td><a href=\"").append(ref).append("\">").append(Utilities.escapeXml(cs.getId())).append("</a></td>\r\n");
      b.append("    <td>").append(presentContext(cs, resource.getName())).append("</td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(cs.getName())).append("</td>\r\n");
      Profile ap = (Profile) cs.getUserData("profile");
      if (ap == null)
        b.append("    <td></td>\r\n");
      else {
        ImplementationGuideDefn ig = definitions.getIgs().get(ap.getCategory());
        b.append("    <td>for <a href=\""+ig.getPrefix()+ ap.getId()+".html\">"+Utilities.escapeXml(ap.getTitle())+"</a></td>\r\n");
      }
      b.append(" </tr>\r\n");
    }

    if (count == 0)
      b.append("<tr><td>No Extensions defined for this resource</td></tr>");

    map.clear();
    
    for (StructureDefinition sd : workerContext.getExtensionDefinitions()) {
      boolean inc = false;
      for (StructureDefinitionContextComponent ec : sd.getContext()) 
        inc = inc || (ec.getExpression().equals("Resource") || ec.getExpression().equals("DomainResource") || ec.getExpression().equals("Any"));
      if (inc)
        map.put(sd.getId(), sd);
    }

    b.append("<tr><td colspan=\"4\">Extensions for all resources or elements</td></tr>");
    for (String s : sorted(map.keySet())) {
      StructureDefinition cs = map.get(s);
      count++;
      b.append("  <tr>\r\n");
      String ref = cs.getUserString("path");
      b.append("    <td colspan=\"2\"><a href=\"").append(ref).append("\">").append(Utilities.escapeXml(cs.getId())).append("</a></td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(cs.getName())).append("</td>\r\n");
      Profile ap = (Profile) cs.getUserData("profile");
      if (ap == null)
        b.append("    <td></td>\r\n");
      else {
        ImplementationGuideDefn ig = definitions.getIgs().get(ap.getCategory());
        b.append("    <td>for <a href=\""+ig.getPrefix()+ ap.getId()+".html\">"+Utilities.escapeXml(ap.getTitle())+"</a></td>\r\n");
      }
      b.append(" </tr>\r\n");
    }


    return b.toString();
  }

  private Object presentContext(StructureDefinition cs, String resource) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (StructureDefinitionContextComponent c : cs.getContext()) {
        if (appliesTo(c, resource)) {
          if (first) first = false; else b.append(", ");
          if (c.getType() != ExtensionContextType.ELEMENT) 
            b.append("<a href=\"codesystem-extension-context-type.html#"+c.getType().toCode()+"\">"+c.getType().toCode()+"</a>: ");
          b.append("<code>"+c.getExpression()+"</code>");
      }
    }
    return b.toString();
  }

  private boolean appliesTo(StructureDefinitionContextComponent c, String resource) {
    switch (c.getType()) {
    case ELEMENT: return c.getExpression().startsWith(resource+".") || c.getExpression().equals(resource);
    case EXTENSION: return false;
    case FHIRPATH: 
      if (c.getExpression().startsWith(resource+"."))
        return true;
      if (c.getExpression().contains(".") && definitions.hasResource(c.getExpression().split("\\.")[0]))
        return false;
      return true; // maybe it applies..
    default:
      return false;
    }
  }

  private String produceRefExtensions(ResourceDefn resource) {
    int count = 0;
    Map<String, StructureDefinition> map = new HashMap<String, StructureDefinition>();
    for (StructureDefinition sd : workerContext.getExtensionDefinitions()) {
      boolean refers  = false;
      for (ElementDefinition ed : sd.getSnapshot().getElement()) {
        for (TypeRefComponent tr : ed.getType()) {
          for (UriType u : tr.getTargetProfile())
            if (u.getValue().endsWith("/"+resource.getName()))
              refers = true;
        }
        if (refers)
          map.put(sd.getId(), sd);
      }
    }

    StringBuilder b = new StringBuilder();
    for (String s : sorted(map.keySet())) {
      StructureDefinition cs = map.get(s);
      count++;
      b.append("  <tr>\r\n");
      String ref = cs.getUserString("path");
      b.append("    <td><a href=\"").append(ref).append("\">").append(Utilities.escapeXml(cs.getId())).append("</a></td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(cs.getName())).append("</td>\r\n");
      Profile ap = (Profile) cs.getUserData("profile");
      if (ap == null)
        b.append("    <td></td>\r\n");
      else {
        ImplementationGuideDefn ig = definitions.getIgs().get(ap.getCategory());
        b.append("    <td>for <a href=\""+ig.getPrefix()+ ap.getId()+".html\">"+Utilities.escapeXml(ap.getTitle())+"</a></td>\r\n");
      }
      b.append(" </tr>\r\n");
    }
    if (count == 0)
      b.append("<tr><td colspan=\"\3\">No Extensions refer to this resource</td></tr>");

    map.clear();
    for (StructureDefinition sd : workerContext.getExtensionDefinitions()) {
      boolean refers  = false;
      for (ElementDefinition ed : sd.getSnapshot().getElement()) {
        for (TypeRefComponent tr : ed.getType()) {
          for (UriType u : tr.getTargetProfile())
            if (u.getValue().endsWith("/Any") || u.getValue().endsWith("/Resource") || u.getValue().endsWith("/DomainResource"))
              refers = true;
        }
        if (refers)
          map.put(sd.getId(), sd);
      }
    }

    b.append("<tr><td colspan=\"\3\">Extensions that refer to Any resource</td></tr>");
    for (String s : sorted(map.keySet())) {
      StructureDefinition cs = map.get(s);
      count++;
      b.append("  <tr>\r\n");
      String ref = cs.getUserString("path");
      b.append("    <td><a href=\"").append(ref).append("\">").append(Utilities.escapeXml(cs.getId())).append("</a></td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(cs.getName())).append("</td>\r\n");
      Profile ap = (Profile) cs.getUserData("profile");
      if (ap == null)
        b.append("    <td></td>\r\n");
      else {
        ImplementationGuideDefn ig = definitions.getIgs().get(ap.getCategory());
        b.append("    <td>for <a href=\""+ig.getPrefix()+ ap.getId()+".html\">"+Utilities.escapeXml(ap.getTitle())+"</a></td>\r\n");
      }
      b.append(" </tr>\r\n");
    }

    return b.toString();
  }

  private String produceDataTypeExtras(String tn, boolean profiles) {
    int count = 0;
    Map<String, StructureDefinition> map = new HashMap<String, StructureDefinition>();
    for (StructureDefinition sd : workerContext.getExtensionDefinitions()) {
      boolean inc = false;
      for (StructureDefinitionContextComponent ec : sd.getContext()) {
        if (ec.getType() == ExtensionContextType.ELEMENT) {
          inc = inc || matchesType(tn, ec.getExpression());
        }
      }
      if (inc)
        map.put(sd.getId(), sd);
    }

    StringBuilder b = new StringBuilder();
    b.append("  <tr><td colspan=\"3\"><b>Extensions</b> (+ see <a href=\"element-extras.html\">extensions on all Elements</a>)</td></tr>\r\n");
    for (String s : sorted(map.keySet())) {
      StructureDefinition cs = map.get(s);
      count++;
      b.append("  <tr>\r\n");
      String ref = cs.getUserString("path");
      b.append("    <td><a href=\"").append(ref).append("\">").append(Utilities.escapeXml(cs.getId())).append("</a></td>\r\n");
      b.append("    <td>").append(Utilities.escapeXml(cs.getName())).append("</td>\r\n");
      Profile ap = (Profile) cs.getUserData("profile");
      if (ap == null)
        b.append("    <td></td>\r\n");
      else {
        ImplementationGuideDefn ig = definitions.getIgs().get(ap.getCategory());
        b.append("    <td>for <a href=\""+ig.getPrefix()+ ap.getId()+".html\">"+Utilities.escapeXml(ap.getTitle())+"</a></td>\r\n");
      }
      b.append(" </tr>\r\n");
    }
    if (count == 0)
      b.append("<tr><td>No Extensions defined for "+(tn.equals("primitives")? "primitive types" : "this type")+" (though <a href=\"element-extensions.html\">extensions on all Elements</a>)</td></tr>");

    if (profiles) {
      count = 0;
      Map<String, CSPair> pmap = new HashMap<String, CSPair>();
      for (Profile ap: definitions.getPackList()) {
        for (ConstraintStructure cs : ap.getProfiles()) {
          if (coversType(cs, tn))
            pmap.put(cs.getTitle(), new CSPair(ap, cs));
        }
      }

      b.append("  <tr><td colspan=\"3\"><b>Profiles</b></td></tr>\r\n");
      for (String s : sorted(pmap.keySet())) {
        CSPair cs = pmap.get(s);
        ImplementationGuideDefn ig = definitions.getIgs().get(cs.p.getCategory());
        count++;
        b.append("  <tr>\r\n");
        String ref = (ig.isCore() ? "" : ig.getCode()+File.separator)+cs.cs.getId()+".html";
        b.append("    <td><a href=\"").append(ref).append("\">").append(Utilities.escapeXml(cs.cs.getTitle())).append("</a></td>\r\n");
        b.append("    <td>").append(Utilities.escapeXml(cs.p.getDescription())).append("</td>\r\n");
        ref = (ig.isCore() ? "" : ig.getCode()+File.separator)+cs.p.getId().toLowerCase()+".html";
        b.append("    <td>for <a href=\"").append(ref).append("\">").append(Utilities.escapeXml(cs.p.getTitle())).append("</a></td>\r\n");
        b.append(" </tr>\r\n");
      }
      if (count == 0)
        b.append("<tr><td>No Profiles defined for for "+(tn.equals("primitives")? "primitive types" : "this type")+"</td></tr>");
    }
    return b.toString();
  }

  private boolean matchesType(String tn, String context) {
    if (tn.equals("primitives")) {
      for (String n : definitions.getPrimitives().keySet())
        if (context.equals(n) || context.startsWith(n+"."))
          return true;
      return false;
    } else
      return context.equals(tn) || context.startsWith(tn+".");
  }

  private boolean coversType(ConstraintStructure item, String tn) {
    return matchesType(tn, item.getResource().getType());
}


  private String produceSearchExtensions(ResourceDefn resource) {
    int count = 0;
    Map<String, SearchParameter> map = new HashMap<String, SearchParameter>();

    for (Profile cp : getDefinitions().getPackList()) {
      addSearchParams(map, cp, resource.getName());
    }
    for (Profile cp : resource.getConformancePackages()) {
      addSearchParams(map, cp, resource.getName());
    }

    StringBuilder b = new StringBuilder();
    for (String s : sorted(map.keySet())) {
      SearchParameter sp = map.get(s);
      count++;
      b.append("<tr>"+
        "<td>"+sp.getCode()+"</td>"+
        "<td><a href=\"search.html#"+sp.getType().toCode()+"\">"+sp.getType().toCode()+"</a></td>"+
        "<td>"+Utilities.escapeXml(sp.getDescription())+"<br/>"+
        "<b>Expression:</b> "+Utilities.escapeXml(sp.getExpression())+"</td></tr>\r\n");
    }
    if (count == 0)
      b.append("<tr><td>No Search Extensions defined for this resource</td></tr>");

    return b.toString();
  }

  private boolean isExtension(ConstraintStructure item, String name) {
    if (item.getDefn() != null && item.getDefn().getName().equals("Extension"))
      return true;
    if (item.getDefn() == null && item.getResource() != null && item.getResource().getType().equals("Extension"))
      return true;
    return false;
  }

  private boolean coversResource(ConstraintStructure item, String rn) {
    if (item.getDefn() != null && item.getDefn().getName().equals(rn))
      return true;
    if (item.getDefn() == null && item.getResource() != null && item.getResource().getType().equals(rn))
      return true;
    return false;
}

  private void produceProfileLine(StringBuilder s, ImplementationGuideDefn ig, boolean started, Profile ap) {
    if (!started)
      s.append("  <tr><td colspan=\"2\"><b>"+Utilities.escapeXml(ig.getName())+"</b></td></tr>\r\n");
    s.append("  <tr>\r\n");
    String ref = (ig.isCore() ? "" : ig.getCode()+File.separator)+ap.getId().toLowerCase()+".html";
    if (("profile".equals(ap.metadata("navigation")) || !ig.isCore()) && ap.getProfiles().size() == 1)
      ref = (ig.isCore() ? "" : ig.getCode()+File.separator)+ap.getProfiles().get(0).getId()+".html";
    s.append("    <td><a href=\"").append(ref).append("\">").append(Utilities.escapeXml(ap.getTitle())).append("</a></td>\r\n");
    s.append("    <td>").append(Utilities.escapeXml(ap.getDescription())).append("</td>\r\n");
    s.append(" </tr>\r\n");
  }

  private String produceExampleList(ResourceDefn resource) throws Exception {
    if (resource.getName().equals("StructureDefinition")) {
      return produceStructureDefinitionExamples(resource);
    } else {
      StringBuilder s = new StringBuilder();
      s.append("<table class=\"list\">\r\n");
      s.append("<tr><td><b>Example Name</b></td><td><b>id</b></td><td colspan=\"4\"><b>Format</b></td></tr>\r\n");
      for (Example e: resource.getExamples()) {
        if (e.isRegistered() && Utilities.noString(e.getIg()))
          produceExampleListEntry(s, e, null, null);
      }
      for (Profile p : resource.getConformancePackages()) {
        for (Example e: p.getExamples()) {
          produceExampleListEntry(s, e, p, null);
        }
      }
      for (Profile p : definitions.getPackList()) {
        ImplementationGuideDefn ig = definitions.getIgs().get(p.getCategory());
        for (Example e: p.getExamples()) {
          String rn = e.getResourceName();
          if (Utilities.noString(rn))
            rn = e.getXml().getDocumentElement().getNodeName();
          if (rn.equals(resource.getName()))
            produceExampleListEntry(s, e, p, ig);
        }
      }
      for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
        if (ig.getIg() != null) {
          for (ImplementationGuideDefinitionResourceComponent res : ig.getIg().getDefinition().getResource()) {
            Example e = (Example) res.getUserData(ToolResourceUtilities.NAME_RES_EXAMPLE);
            if (res.hasExample() && e != null && e.getResourceName().equals(resource.getName()))
              produceExampleListEntry(s, res, ig);
          }
        }
      }
      s.append("<tr><td colspan=\"4\">&nbsp;</td></tr></table>\r\n");
      return s.toString();
    }
  }

  private void produceExampleListEntry(StringBuilder s, ImplementationGuideDefinitionResourceComponent res, ImplementationGuideDefn ig) throws Exception {
    String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;
    String n = res.getReference().getReference();
    s.append("<tr><td><a href=\""+prefix+Utilities.changeFileExt(n, ".html")+"\">"+Utilities.escapeXml(res.getDescription())+"</a></td>");
    s.append("<td>"+res.getId()+"</td>");
    s.append("<td><a href=\""+prefix+Utilities.changeFileExt(n, ".xml.html")+"\">XML</a></td>");
    s.append("<td><a href=\""+prefix+Utilities.changeFileExt(n, ".json.html")+"\">JSON</a></td>");
    s.append("<td><a href=\""+prefix+Utilities.changeFileExt(n, ".ttl.html")+"\">Turtle</a></td>");
    s.append("<td>from <a href=\""+ig.getHomePage()+"\">"+Utilities.escapeXml(ig.getName())+"</a> IG</td>");
    s.append("</tr>");
  }

  private void produceExampleListEntry(StringBuilder s, Example e, Profile pack, ImplementationGuideDefn ig) {
    String prefix = (ig == null || ig.isCore()) ? "" : ig.getCode()+File.separator;
    if (e.getTitle().equals("capabilitystatement-base") || e.getTitle().equals("capabilitystatement-base2") || e.getTitle().equals("profiles-resources"))
      s.append("<tr><td>"+Utilities.escapeXml(e.getDescription())+"</td>");
    else
      s.append("<tr><td><a href=\""+prefix+e.getTitle()+".html\">"+Utilities.escapeXml(e.getDescription())+"</a></td>");
    s.append("<td>"+e.getId()+"</td>");
    s.append("<td><a href=\""+prefix+e.getTitle()+".xml.html\">XML</a></td>");
    s.append("<td><a href=\""+prefix+e.getTitle()+".json.html\">JSON</a></td>");
    s.append("<td><a href=\""+prefix+e.getTitle()+".ttl.html\">Turtle</a></td>");
    if (pack == null)
      s.append("<td></td>");
    else
      s.append("<td>for Profile <a href=\""+prefix+pack.getId()+".html\">"+Utilities.escapeXml(pack.getTitle())+"</a></td>");
    s.append("</tr>");
  }

  private String produceStructureDefinitionExamples(ResourceDefn resource) throws Exception {
    StringBuilder s = new StringBuilder();

    s.append("<div id=\"tabs\">\r\n");
    s.append("<ul>\r\n");
    s.append("  <li><a href=\"#tabs-1\">Base Types</a></li>\r\n");
    s.append("  <li><a href=\"#tabs-2\">Resources</a></li>\r\n");
    s.append("  <li><a href=\"#tabs-3\">Constraints</a></li>\r\n");
    s.append("  <li><a href=\"#tabs-4\">Extensions</a></li>\r\n");
    s.append("  <li><a href=\"#tabs-5\">Examples</a></li>\r\n");
    s.append("</ul>\r\n");
    s.append("<div id=\"tabs-1\">\r\n");

    // base types
    s.append("<table class=\"list\">\r\n");
    genStructureExampleCategory(s, "Abstract Types", "3");
    genStructureExample(s, "element.html", "element.profile", "element", "Element");
    genStructureExample(s, "backboneelement.html", "backboneelement.profile", "backboneelement", "BackBoneElement");
    genStructureExample(s, "resource.html", "resource.profile", "resource", "Resource");
    genStructureExample(s, "domainresource.html", "domainresource.profile", "domainresource", "DomainResource");

    genStructureExampleCategory(s, "Primitive Types", "3");
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getPrimitives().keySet());
    Collections.sort(names);
    for (String n : names) {
      DefinedCode dc = definitions.getPrimitives().get(n);
      genStructureExample(s, "datatypes.html#"+dc.getCode(), dc.getCode().toLowerCase()+".profile", dc.getCode().toLowerCase(), dc.getCode());
    }

    genStructureExampleCategory(s, "Data Types", "3");
    names.clear();
    names.addAll(definitions.getTypes().keySet());
    names.addAll(definitions.getStructures().keySet());
    names.addAll(definitions.getInfrastructure().keySet());
    Collections.sort(names);
    for (String n : names) {
      org.hl7.fhir.definitions.model.TypeDefn t = definitions.getTypes().get(n);
      if (t == null)
        t = definitions.getStructures().get(n);
      if (t == null)
        t = definitions.getInfrastructure().get(n);
      genStructureExample(s, getLinkFor("", t.getName()), t.getName().toLowerCase()+".profile",  t.getName().toLowerCase(), t.getName());
    }
    s.append("</table>\r\n");

    s.append("</div>\r\n");
    s.append("<div id=\"tabs-2\">\r\n");

    s.append("<table class=\"list\">\r\n");

    genStructureExampleCategory(s, "Resources", "3");
    for (String n : definitions.sortedResourceNames()) {
      ResourceDefn r = definitions.getResources().get(n);
      genStructureExample(s, r.getName().toLowerCase()+".html", r.getName().toLowerCase()+".profile", r.getName().toLowerCase(), r.getName());
    }
    s.append("</table>\r\n");

    s.append("</div>\r\n");
    s.append("<div id=\"tabs-3\">\r\n");

    s.append("<table class=\"list\">\r\n");
    Map<String, ConstraintStructure> constraints = new HashMap<String, ConstraintStructure>();
    for (Profile pp : definitions.getPackList()) {
      for (ConstraintStructure p : pp.getProfiles())
        constraints.put(p.getId(), p);
    }
    for (String rn : definitions.sortedResourceNames())
      for (Profile ap: definitions.getResourceByName(rn).getConformancePackages())
        for (ConstraintStructure p : ap.getProfiles())
          constraints.put(p.getId(), p);
    names.clear();
    names.addAll(constraints.keySet());
    Collections.sort(names);
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      boolean started = false;
      for (String n : names) {
        ConstraintStructure p = constraints.get(n);
        if (ig == p.getUsage()) {
          if (!started) {
            started = true;
            genStructureExampleCategory(s, ig.getName(), "3");
          }
          String prefix = ig.isCore() ? "" : ig.getCode()+"/";
          genStructureExample(s, prefix+ p.getId().toLowerCase()+".html", prefix+ p.getId().toLowerCase()+".profile", p.getId().toLowerCase(), p.getTitle());
        }
      }
    }
    s.append("</table>\r\n");

    s.append("</div>\r\n");
    s.append("<div id=\"tabs-4\">\r\n");

    s.append("<table class=\"list\">\r\n");
    names.clear();
    for (StructureDefinition sd : workerContext.getExtensionDefinitions())
      names.add(sd.getUrl());
    Collections.sort(names);
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      boolean started = false;
      for (String n : names) {
        StructureDefinition ed = workerContext.fetchResource(StructureDefinition.class, n);
        if (ig.getCode().equals(ToolResourceUtilities.getUsage(ed))) {
          if (!started) {
            started = true;
            genStructureExampleCategory(s, ig.getName(), "3");
          }
          String prefix = ig.isCore() ? "" : ig.getCode()+"/";
          genStructureExample(s, prefix+ "extension-"+ed.getId().toLowerCase()+".html", prefix+ "extension-"+ed.getId().toLowerCase(), ed.getId().toLowerCase(), ed.getUrl().startsWith("http://hl7.org/fhir/StructureDefinition/") ? ed.getUrl().substring(40) : ed.getUrl(), ed.getName());
        }
      }
    }
    s.append("</table>\r\n");

    s.append("</div>\r\n");
    s.append("<div id=\"tabs-5\">\r\n");
    s.append("<table class=\"list\">\r\n");
    for (Example e: resource.getExamples()) {
      if (e.isRegistered() && Utilities.noString(e.getIg()))
        produceExampleListEntry(s, e, null, null);
    }
    for (Profile p : resource.getConformancePackages()) {
      for (Example e: p.getExamples()) {
        produceExampleListEntry(s, e, p, null);
      }
    }
    for (Profile p : definitions.getPackList()) {
      ImplementationGuideDefn ig = definitions.getIgs().get(p.getCategory());
      for (Example e: p.getExamples()) {
        String rn = e.getResourceName();
        if (Utilities.noString(rn))
          rn = e.getXml().getDocumentElement().getNodeName();
        if (rn.equals(resource.getName()))
          produceExampleListEntry(s, e, p, ig);
      }
    }
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      if (ig.getIg() != null) {
        for (ImplementationGuideDefinitionResourceComponent res : ig.getIg().getDefinition().getResource()) {
          Example e = (Example) res.getUserData(ToolResourceUtilities.NAME_RES_EXAMPLE);
          if (res.hasExample() && e != null && e.getResourceName().equals(resource.getName()))
            produceExampleListEntry(s, res, ig);
        }
      }
    }
    s.append("</table>\r\n");

    s.append("</div>\r\n");
    s.append("</div>\r\n");
    s.append("\r\n");

    s.append("<script src=\"external/jquery/jquery.js\"> </script>\r\n");
    s.append("<script src=\"jquery-ui.min.js\"> </script>\r\n");
    s.append("<script>\r\n");
    s.append("try {\r\n");
    s.append("  var currentTabIndex = sessionStorage.getItem('fhir-sdelist-tab-index');\r\n");
    s.append("}\r\n");
    s.append("catch(exception){\r\n");
    s.append("}\r\n");
    s.append("\r\n");
    s.append("if (!currentTabIndex)\r\n");
    s.append("  currentTabIndex = '0';\r\n");
    s.append("  \r\n");
    s.append("$( '#tabs' ).tabs({\r\n");
    s.append("         active: currentTabIndex,\r\n");
    s.append("         activate: function( event, ui ) {\r\n");
    s.append("             var active = $('.selector').tabs('option', 'active');\r\n");
    s.append("             currentTabIndex = ui.newTab.index();\r\n");
    s.append("             document.activeElement.blur();\r\n");
    s.append("             try {\r\n");
    s.append("               sessionStorage.setItem('fhir-sdelist-tab-index', currentTabIndex);\r\n");
    s.append("             }\r\n");
    s.append("             catch(exception){\r\n");
    s.append("             }\r\n");
    s.append("         }\r\n");
    s.append("     });\r\n");
    s.append("</script>\r\n");
    s.append("\r\n");

    return s.toString();
  }

  private void genStructureExampleCategory(StringBuilder s, String heading, String span) {
    s.append("<tr>");
    s.append("<td colspan=\""+span+"\"><b>"+Utilities.escapeXml(heading)+"</b></td>");
    s.append("</tr>");
  }

  private void genStructureExample(StringBuilder s, String link, String fmtlink, String basename, String description) {
    genStructureExample(s, link, fmtlink, basename, description, null);
  }

  private void genStructureExample(StringBuilder s, String link, String fmtlink, String basename, String description, String tail) {
    s.append("<tr>");
    s.append("<td><a href=\""+link+"\">"+Utilities.escapeXml(description)+"</a> "+(Utilities.noString(tail) ? "" : Utilities.escapeXml(tail))+"</td>");
    s.append("<td><a href=\""+fmtlink+ ".xml.html\">XML</a></td>");
    s.append("<td><a href=\""+fmtlink+ ".json.html\">JSON</a></td>");
    s.append("</tr>");
  }

  private static final String HTML_PREFIX1 = "<div xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/1999/xhtml ../../schema/fhir-xhtml.xsd\" xmlns=\"http://www.w3.org/1999/xhtml\">";
  private static final String HTML_PREFIX2 = "<div xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/1999/xhtml ../schema/fhir-xhtml.xsd\" xmlns=\"http://www.w3.org/1999/xhtml\">";
  private static final String HTML_SUFFIX = "</div>\r\n";

  public String loadXmlNotesFromFile(String filename, boolean checkHeaders, String definition, ResourceDefn r, List<String> tabs, ImplementationGuideDefn ig, WorkGroup wg) throws Exception {
    if (!new CSFile(filename).exists()) {
      TextFile.stringToFile(HTML_PREFIX1+"\r\n<!-- content goes here -->\r\n\r\n"+HTML_SUFFIX, filename);
      return "";
    }

    String res;
    String cnt = TextFile.fileToString(filename);
    Map<String, String> others = new HashMap<String, String>();
    others.put("definition", definition);
    cnt = processPageIncludes(filename, cnt, "notes", others, null, tabs, "--", ig, r, wg).trim()+"\r\n";
    if (cnt.startsWith("<div")) {
      if (!cnt.startsWith(HTML_PREFIX1) && !cnt.startsWith(HTML_PREFIX2))
        throw new Exception("unable to process start xhtml content "+filename+" : \r\n"+cnt.substring(0, HTML_PREFIX1.length())+" - should be \r\n'"+HTML_PREFIX1+"' or \r\n'"+HTML_PREFIX2+"'");
      else if (!cnt.endsWith(HTML_SUFFIX))
        throw new Exception("unable to process end xhtml content "+filename+" : "+cnt.substring(cnt.length()-HTML_SUFFIX.length()));
      else if (cnt.startsWith(HTML_PREFIX2))
        res = cnt.substring(HTML_PREFIX2.length(), cnt.length()-(HTML_SUFFIX.length())).trim();
      else
        res = cnt.substring(HTML_PREFIX1.length(), cnt.length()-(HTML_SUFFIX.length())).trim();
    } else {
      res = HTML_PREFIX1+cnt+HTML_SUFFIX;
      TextFile.stringToFile(res, filename);
    }
    if (checkHeaders) {
      checkFormat(filename, res, r);

    }
    return res;

  }

  private void checkFormat(String filename, String res, ResourceDefn r) throws FHIRException  {
    XhtmlNode doc;
    try {
      doc = new XhtmlParser().parse("<div>"+res+"</div>", null).getFirstElement();
    if (doc.getFirstElement() == null || !doc.getFirstElement().getName().equals("div"))
      log("file \""+filename+"\": root element should be 'div'", LogMessageType.Error);
    else if (doc.getFirstElement() == null) {
      log("file \""+filename+"\": there is no 'Scope and Usage'", LogMessageType.Error);
    } else {
      XhtmlNode scope = null;
      XhtmlNode context = null;
      for (XhtmlNode x : doc.getChildNodes()) {
        if (x.getNodeType() == NodeType.Element) {
          if (!x.getName().equals("div")) {
            log("file \""+filename+"\": all child elements of the root div should be 'div's too (found '"+x.getName()+"')", LogMessageType.Error);
            return;
          } else if (x.getChildNodes().isEmpty()) {
            log("file \""+filename+"\": div/div["+Integer.toString(doc.getChildNodes().indexOf(x))+"] must have at least an h2", LogMessageType.Error);
            return;
          } else if (!isFirstChildElementH2(x)) {
            log("file \""+filename+"\": div/div["+Integer.toString(doc.getChildNodes().indexOf(x))+"] must start with an h2", LogMessageType.Error);
            return;
          } else {
            XhtmlNode fn = getH2Element(x);
            String s = fn.allText();
            if (! ((s.equals("Scope and Usage")) || (s.equals("Boundaries and Relationships")) || (s.equals("Background and Context")) ) ) {
              log("file \""+filename+"\": div/div["+Integer.toString(doc.getChildNodes().indexOf(x))+"]/h2 must be either 'Scope and Usage', 'Boundaries and Relationships', or 'Background and Context'", LogMessageType.Error);
              return;
            } else {
              if (scope == null) {
                if (s.equals("Scope and Usage")) {
                  scope = x;
                  if (r != null)
                    r.setRequirements(new XhtmlComposer(XhtmlComposer.HTML).composePlainText(x));
                } else {
                  log("file \""+filename+"\": 'Scope and Usage' must come first", LogMessageType.Error);
                  return;
                }
                if (s.equals("Boundaries and Relationships")) {
                  if (context != null) {
                    log("file \""+filename+"\": 'Boundaries and Relationships' must come first before 'Background and Context'", LogMessageType.Error);
                    return;
                  }
                }

                if (s.equals("Background and Context"))
                  context = x;
              }
            }
            boolean found = false;
            for (XhtmlNode n : x.getChildNodes()) {
              if (!found)
                found = n == fn;
              else {
                if ("h1".equals(n.getName()) || "h2".equals(n.getName())) {
                  log("file \""+filename+"\": content of a <div> inner section cannot contain h1 or h2 headings", LogMessageType.Error);
                  return;
                }
              }
            }
          }
        }
      }
    }
    List<String> allowed = Arrays.asList("div", "h2", "h3", "h4", "h5", "i", "b", "code", "pre", "blockquote", "p", "a", "img", "table", "thead", "tbody", "tr", "th", "td", "ol", "ul", "li", "br", "span", "em", "strong");
    iterateAllChildNodes(doc, allowed);
    } catch (Exception e) {
      throw new FHIRException("Error processing "+filename+": "+e.getMessage(), e);
    }
  }

  private XhtmlNode getH2Element(XhtmlNode x) {
    XhtmlNode c = x.getFirstElement();
    while (c.getName().equals("a") || c.getName().equals("blockquote") )
      c = x.getNextElement(c);
    return c;
  }

  private boolean isFirstChildElementH2(XhtmlNode x) {
    XhtmlNode c = x.getFirstElement();
    while (c.getName().equals("a") || c.getName().equals("blockquote") )
      c = x.getNextElement(c);
    return c != null && c.getName().equals("h2");
  }

  private boolean iterateAllChildNodes(XhtmlNode node, List<String> allowed) {
    for (XhtmlNode n : node.getChildNodes()) {
      if (n.getNodeType() == NodeType.Element) {
        if (!allowed.contains(n.getName())) {
          log("Markup uses non permitted name "+n.getName(), LogMessageType.Error);
          return false;
        }
        if (!iterateAllChildNodes(n, allowed))
          return false;
      }
    }
    return true;
  }

  public String loadXmlNotes(String name, String suffix, boolean checkHeaders, String definition, ResourceDefn resource, List<String> tabs, ImplementationGuideDefn ig, WorkGroup wg) throws Exception {
    String filename;
    if (definitions.hasLogicalModel(name)) {
      LogicalModel lm = definitions.getLogicalModel(name);
      filename = Utilities.changeFileExt(lm.getSource(), "-"+suffix+".xml");
    } else
      filename = folders.srcDir + name+File.separatorChar+name+"-"+suffix+".xml";
    return loadXmlNotesFromFile(filename, checkHeaders, definition, resource, tabs, ig, wg);
  }

  private String loadXmlNotes(String name, String suffix, boolean checkHeaders, String definition, StructureDefinition sd, List<String> tabs, ImplementationGuideDefn ig, WorkGroup wg) throws Exception {
    String filename;
    if (definitions.hasLogicalModel(name)) {
      LogicalModel lm = definitions.getLogicalModel(name);
      filename = Utilities.changeFileExt(lm.getSource(), "-"+suffix+".xml");
    } else
      filename = folders.srcDir + name+File.separatorChar+name+"-"+suffix+".xml";
    return loadXmlNotesFromFile(filename, checkHeaders, definition, null, tabs, ig, wg);
  }

  public String processProfileIncludes(String filename, String fileid, Profile pack, ConstraintStructure profile, String xml, String json, String tx, String src, String master, String path, String intro, String notes, ImplementationGuideDefn ig, boolean isDict, boolean hasNarrative) throws Exception {
    String workingTitle = null;

    int level = (ig == null || ig.isCore()) ? 0 : 1;

    while (src.contains("<%") || src.contains("[%"))
    {
      int i1 = src.indexOf("<%");
      int i2 = src.indexOf("%>");
      if (i1 == -1) {
        i1 = src.indexOf("[%");
        i2 = src.indexOf("%]");
      }
      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);

      String[] com = s2.split(" ");
      if (com[0].equals("profileheader"))
        src = s1+profileHeader(fileid, com.length > 1 ? com[1] : "", hasExamples(pack))+s3;
      else if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }      else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+filename);
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(pack.metadata("name"))+s3;
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+(workingTitle == null ? Utilities.escapeXml("StructureDefinition: "+profile.getTitle()) : workingTitle)+s3;
      else if (com[0].equals("xtitle"))
        src = s1+(workingTitle == null ? Utilities.escapeXml("StructureDefinition: "+profile.getTitle()) : Utilities.escapeXml(workingTitle))+s3;
      else if (com[0].equals("profiletitle"))
        src = s1+Utilities.escapeXml(pack.metadata("name"))+s3;
      else if (com[0].equals("filetitle"))
        src = s1+(filename.contains(".") ? filename.substring(0, filename.lastIndexOf(".")) : filename)+s3;
      else if (com[0].equals("name"))
        src = s1+filename+s3;
      else if (com[0].equals("date")) {
        if (!Utilities.noString(pack.metadata("date"))) {
          Date d = new SimpleDateFormat("yyyy-MM-dd").parse(pack.metadata("date"));
          src = s1+Config.DATE_FORMAT().format(d)+s3;
        }
        else
          src = s1+"[no date]"+s3;
      } else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("definition"))
        src = s1+Utilities.escapeXml(pack.metadata("description"))+s3;
      else if (com[0].equals("status"))
        src = s1+describeStatus(pack.metadata("status"))+s3;
      else if (com[0].equals("author"))
        src = s1+Utilities.escapeXml(pack.metadata("author.name"))+s3;
      else if (com[0].equals("xml"))
        src = s1+xml+s3;
      else if (com[0].equals("json"))
        src = s1+json+s3;
      else if (com[0].equals("profiledesc")) {
        src = s1+Utilities.escapeXml(profile.getResource().getDescription())+s3;
      } else if (com[0].equals("tx"))
        src = s1+tx+s3;
      else if (com[0].equals("inv"))
        src = s1+genProfileConstraints(profile.getResource())+s3;
      else if (com[0].equals("plural"))
        src = s1+Utilities.pluralizeMe(filename)+s3;
      else if (com[0].equals("notes"))
        src = s1+"todo" /*Utilities.fileToString(folders.srcDir + filename+File.separatorChar+filename+".html")*/ +s3;
      else if (com[0].equals("dictionary"))
        src = s1+"todo"+s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(filename) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(filename, genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist"))
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(filename, "profile:"+path, genlevel(0), profile.getResource().getName()) : ig.makeList(filename, "profile:"+path, genlevel(level), profile.getResource().getName())) + s3;
      else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;
      else if (com[0].equals("buildId"))
        src = s1 + buildId + s3;
      else if (com[0].equals("level"))
        src = s1 + genlevel(0) + s3;
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;
      else if (com[0].equals("profileurl"))
        src = s1 + profile.getResource().getUrl() + s3;
      else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;
      else if (com[0].equals("baseURLn"))
        src = s1 + Utilities.appendForwardSlash(baseURL) + s3;
      else if (com[0].equals("base-link"))
        src = s1 + baseLink(profile.getResource(), genlevel(level)) + s3;
      else if (com[0].equals("profile-structure-table-diff"))
        src = s1 + generateProfileStructureTable(profile, true, filename, pack.getId(), genlevel(level)) + s3;
      else if (com[0].equals("profile-structure-table"))
        src = s1 + generateProfileStructureTable(profile, false, filename, pack.getId(), genlevel(level)) + s3;
      else if (com[0].equals("maponthispage"))
        src = s1+mapOnPageProfile(profile.getResource())+s3;
      else if (com[0].equals("mappings"))
        src = s1+mappingsProfile(profile.getResource())+s3;
      else if (com[0].equals("definitions"))
        src = s1+definitionsProfile(profile.getResource(), genlevel(level))+s3;
      else if (com[0].equals("profile.review"))
        src = s1+profileReviewLink(profile)+s3;
      else if (com[0].equals("profile.datadictionary"))
        src = s1+profileDictionaryLink(profile)+s3;
      else if (com[0].equals("profile.tx"))
        src = s1+getTerminologyNotes(profile.getResource(), level)+s3;
      else if (com[0].equals("profile.inv"))
        src = s1+getInvariantList(profile.getResource())+s3;
      else if (com[0].equals("draft-note"))
        src = s1+getDraftNote(pack, genlevel(level))+s3;
      else if (com[0].equals("pagepath"))
        src = s1+filename+s3;
      else if (com[0].equals("rellink")) {
        if (!filename.contains(".html"))
          src = s1+filename+".html"+s3;
        else
          src = s1+filename+s3;
      } else if (com[0].equals("schematron"))
        src = s1+(isDict ? "<i>None</i>" : "<a href=\""+filename+".sch\">Schematron</a>")+s3;
      else if (com[0].equals("summary"))
        src = s1+generateHumanSummary(profile.getResource(), genlevel(level))+s3;
      else if (com[0].equals("profile-examples"))
        src = s1+generateProfileExamples(pack, profile)+s3;
      else if (com[0].equals("profile-extensions-table"))
        src = s1+"<p><i>Todo</i></p>"+s3;
      else if (com[0].equals("definitionsonthispage"))
        src = s1+"<p><i>Todo</i></p>"+s3;
      else if (com[0].equals("profile.intro"))
        src = s1 +genProfileDoco(pack, intro)+ s3;
      else if (com[0].equals("profile.notes"))
        src = s1 +genProfileDoco(pack, notes)+ s3;
      else if (com[0].equals("search-footer"))
        src = s1+searchFooter(level)+s3;
      else if (com[0].equals("search-header"))
        src = s1+searchHeader(level)+s3;
      else if (com[0].startsWith("!"))
        src = s1 + s3;
      else if (com[0].equals("wg")) {
        String wg = profile.getWg().getCode();
        if (Utilities.noString(wg))
          pack.getWg();
        if (Utilities.noString(wg) && profile.getDefn() != null)
          wg = profile.getDefn().getWg().getCode();
        if (wg == null || !definitions.getWorkgroups().containsKey(wg))
          src = s1+"(No assigned work group) ("+wg+") (4)"+s3;
        else
          src = s1+ "<a _target=\"blank\" href=\""+definitions.getWorkgroups().get(wg).getUrl()+"\">"+definitions.getWorkgroups().get(wg).getName()+"</a> Work Group"+s3;
      } else if (com[0].equals("fmm-style")) {
        String fmm = ToolingExtensions.readStringExtension(profile.getResource(), ToolingExtensions.EXT_FMM_LEVEL);
        StandardsStatus ss = ToolingExtensions.getStandardsStatus(profile.getResource());
        src = s1+fmmBarColorStyle(ss, fmm)+s3;
      } else if (com[0].equals("fmm")) {
        String fmm = profile.getFmm();
        if (Utilities.noString(fmm))
            fmm = pack.getFmmLevel();
        src = s1+getFmmFromlevel(genlevel(level), fmm)+s3;
      } else if (com[0].equals("profile-context"))
        src = s1+getProfileContext(pack.getCandidateResource(), genlevel(level))+s3;
      else if (com[0].equals("sstatus")) {
        StandardsStatus ss = ToolingExtensions.getStandardsStatus(profile.getResource());
        if (ss == null)
          ss = StandardsStatus.INFORMATIVE;
        if (ss == StandardsStatus.NORMATIVE && ToolingExtensions.hasExtension(profile.getResource(), ToolingExtensions.EXT_NORMATIVE_VERSION))
          src = s1+"<a href=\""+genlevel(level)+"versions.html#std-process\">"+ss.toDisplay()+"</a> (from v"+ToolingExtensions.readStringExtension(profile.getResource(), ToolingExtensions.EXT_NORMATIVE_VERSION)+")"+s3;
        else
          src = s1+"<a href=\""+genlevel(level)+"versions.html#std-process\">"+ss.toDisplay()+"</a>"+s3;
      } else if (com[0].equals("past-narrative-link")) {
        if (hasNarrative)  
          src = s1 + s3;
        else
          src = s1 + "<p><a href=\"#DomainResource.text.div-end\">Jump past Narrative</a></p>" + s3;
       } else if (com[0].equals("resurl")) {
         if (Utilities.noString(pack.metadata("id")))
           src = s1+s3;
         else
           src = s1+"The id of this profile is "+pack.metadata("id")+s3;
      } else if (macros.containsKey(com[0])) {
        src = s1+macros.get(com[0])+s3;
      } else
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+filename);
    }
    return src;
  }

  private String getProfileContext(MetadataResource mr, String prefix) throws DefinitionException {
    NarrativeGenerator gen = new NarrativeGenerator(prefix, "", workerContext, this);
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (UsageContext uc :  mr.getUseContext()) {
      String vs = gen.genType(uc.getValue());
      if (vs != null)
        b.append(gen.gen(uc.getCode())+": "+vs);
    }
    for (CodeableConcept cc : mr.getJurisdiction()) {
      b.append("Country: "+gen.displayCodeableConcept(cc));
    }
    if (mr.getExperimental()) {
      if (isTrialUse(mr))
        b.append("Not yet ready for Production use");
      else
        b.append("Not Intended for Production use");
    }
    if (b.length() == 0)
      return "<a href=\""+prefix+"metadatatypes.html#UsageContext\">Use Context</a>: Any";
    else
      return "<a href=\""+prefix+"metadatatypes.html#UsageContext\">Use Context</a>: "+b.toString();
  }

  private boolean isTrialUse(MetadataResource mr) {
    String s = ToolingExtensions.readStringExtension(mr, "http://hl7.org/fhir/StructureDefinition/structuredefinition-standards-status");
    return s == null ? false : s.toLowerCase().contains("trial");
  }

  private String generateProfileExamples(Profile pack, ConstraintStructure profile) {
    if (pack.getExamples().size() == 0)
      return "";
    StringBuilder s = new StringBuilder();
    s.append("<p>Example List:</p>\r\n<table class=\"list\">\r\n");
    for (Example e: pack.getExamples()) {
      if (e.isRegistered())
        produceExampleListEntry(s, e, null, null);
    }
    s.append("<tr><td colspan=\"4\">&nbsp;</td></tr></table>\r\n");
    return s.toString();

  }

  private boolean hasExamples(Profile pack) {
    return pack.getExamples().size() > 0;
  }

  private String genProfileDoco(Profile ap, String doco) {
    if ("profile".equals(ap.metadata("navigation")) && ap.getProfiles().size() == 1)
      return doco;
    else
      return "";
  }

  private String profileDictionaryLink(ConstraintStructure profile) {
    String uri = ToolingExtensions.readStringExtension(profile.getResource(), "http://hl7.org/fhir/StructureDefinition/datadictionary");
    if (Utilities.noString(uri))
      return "<!-- no uri -->";
    Dictionary dict = definitions.getDictionaries().get(uri);
    if (dict == null)
      return "<p>This profile specifies that the value of the "+profile.getResource().getSnapshot().getElement().get(0).getPath()+
          " resource must be a valid Observation as defined in the data dictionary (Unknown? - "+uri+").</p>";
    else
      return "<p>This profile specifies that the value of the "+profile.getResource().getSnapshot().getElement().get(0).getPath()+
          " resource must be a valid Observation as defined in the data dictionary <a href=\""+uri+".html\">"+dict.getName()+"</a>.</p>";
  }

  private String generateHumanSummary(StructureDefinition profile, String prefix) {
    try {
      if (profile.getDifferential() == null)
        return "<p>No Summary, as this profile has no differential</p>";

      // references
      List<String> refs = new ArrayList<String>(); // profile references
      // extensions (modifier extensions)
      List<String> ext = new ArrayList<String>(); // extensions
      // slices
      List<String> slices = new ArrayList<String>(); // Fixed Values
      // numbers - must support, required, prohibited, fixed
      int supports = 0;
      int requiredOutrights = 0;
      int requiredNesteds = 0;
      int fixeds = 0;
      int prohibits = 0;

      for (ElementDefinition ed : profile.getDifferential().getElement()) {
        if (ed.getPath().contains(".")) {
          if (ed.getMin() == 1)
            if (parentChainHasOptional(ed, profile))
              requiredNesteds++;
            else
              requiredOutrights++;
          if ("0".equals(ed.getMax()))
            prohibits++;
          if (ed.getMustSupport())
            supports++;
          if (ed.hasFixed())
            fixeds++;

          for (TypeRefComponent t : ed.getType()) {
            if (t.hasProfile() && !definitions.hasType(t.getProfile().get(0).getValue().substring(40))) {
              if (ed.getPath().endsWith(".extension"))
                tryAdd(ext, summariseExtension(t.getProfile().get(0).getValue(), false, prefix));
              else if (ed.getPath().endsWith(".modifierExtension"))
                tryAdd(ext, summariseExtension(t.getProfile().get(0).getValue(), true, prefix));
              else
                tryAdd(refs, describeProfile(t.getProfile().get(0).getValue(), prefix));
            }
            if (t.hasTargetProfile()) {
              tryAdd(refs, describeProfile(t.getTargetProfile().get(0).getValue(), prefix));
            }
          }

          if (ed.hasSlicing() && !ed.getPath().endsWith(".extension") && !ed.getPath().endsWith(".modifierExtension"))
            tryAdd(slices, describeSlice(ed.getPath(), ed.getSlicing()));
        }
      }
      StringBuilder res = new StringBuilder("<a name=\"summary\"> </a>\r\n<p><b>\r\nSummary\r\n</b></p>\r\n");
      if (ToolingExtensions.hasExtension(profile, "http://hl7.org/fhir/StructureDefinition/structuredefinition-summary")) {
        res.append(processMarkdown("Profile.summary", ToolingExtensions.readStringExtension(profile, "http://hl7.org/fhir/StructureDefinition/structuredefinition-summary"), prefix));
      }
      if (supports + requiredOutrights + requiredNesteds + fixeds + prohibits > 0) {
        boolean started = false;
        res.append("<p>");
        if (requiredOutrights > 0 || requiredNesteds > 0) {
          started = true;
          res.append("Mandatory: "+Integer.toString(requiredOutrights)+" "+(requiredOutrights > 1 ? Utilities.pluralizeMe("element") : "element"));
          if (requiredNesteds > 0)
            res.append(" (+"+Integer.toString(requiredNesteds)+" nested mandatory "+(requiredNesteds > 1 ? Utilities.pluralizeMe("element") : "element")+")");
        }
        if (supports > 0) {
          if (started)
            res.append("<br/> ");
          started = true;
          res.append("Must-Support: "+Integer.toString(supports)+" "+(supports > 1 ? Utilities.pluralizeMe("element") : "element"));
        }
        if (fixeds > 0) {
          if (started)
            res.append("<br/> ");
          started = true;
          res.append("Fixed Value: "+Integer.toString(fixeds)+" "+(fixeds > 1 ? Utilities.pluralizeMe("element") : "element"));
        }
        if (prohibits > 0) {
          if (started)
            res.append("<br/> ");
          started = true;
          res.append("Prohibited: "+Integer.toString(prohibits)+" "+(prohibits > 1 ? Utilities.pluralizeMe("element") : "element"));
        }
        res.append("</p>");
      }
      if (!refs.isEmpty()) {
        res.append("<p><b>Structures</b></p>\r\n<p>This structure refers to these other structures:</p>\r\n<ul>\r\n");
        for (String s : refs)
          res.append(s);
        res.append("\r\n</ul>\r\n\r\n");
      }
      if (!ext.isEmpty()) {
        res.append("<p><b>Extensions</b></p>\r\n<p>This structure refers to these extensions:</p>\r\n<ul>\r\n");
        for (String s : ext)
          res.append(s);
        res.append("\r\n</ul>\r\n\r\n");
      }
      if (!slices.isEmpty()) {
        res.append("<p><b>Slices</b></p>\r\n<p>This structure defines the following <a href=\""+prefix+"profiling.html#slices\">Slices</a>:</p>\r\n<ul>\r\n");
        for (String s : slices)
          res.append(s);
        res.append("\r\n</ul>\r\n\r\n");
      }
      return res.toString();
    } catch (Exception e) {
      return "<p><i>"+Utilities.escapeXml(e.getMessage())+"</i></p>";
    }
  }

  private boolean parentChainHasOptional(ElementDefinition ed, StructureDefinition profile) {
    if (!ed.getPath().contains("."))
      return false;

    ElementDefinition match = (ElementDefinition) ed.getUserData(ProfileUtilities.DERIVATION_POINTER);
    if (match == null)
      return true; // really, we shouldn't get here, but this appears to be common in the existing profiles?
      // throw new Error("no matches for "+ed.getPath()+"/"+ed.getName()+" in "+profile.getUrl());

    while (match.getPath().contains(".")) {
      if (match.getMin() == 0) {
        return true;
      }
      match = getElementParent(profile.getSnapshot().getElement(), match);
    }

    return false;
  }

  private ElementDefinition getElementParent(List<ElementDefinition> list, ElementDefinition element) {
    String targetPath = element.getPath().substring(0, element.getPath().lastIndexOf("."));
    int index = list.indexOf(element) - 1;
    while (index >= 0) {
      if (list.get(index).getPath().equals(targetPath))
        return list.get(index);
      index--;
    }
    return null;
  }

  private String describeSlice(String path, ElementDefinitionSlicingComponent slicing) {
    if (!slicing.hasDiscriminator())
      return "<li>There is a slice with no discriminator at "+path+"</li>\r\n";
    String s = "";
    if (slicing.getOrdered())
      s = "ordered";
    if (slicing.getRules() != SlicingRules.OPEN)
      s = Utilities.noString(s) ? slicing.getRules().getDisplay() : s+", "+ slicing.getRules().getDisplay();
    if (!Utilities.noString(s))
      s = " ("+s+")";
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (ElementDefinitionSlicingDiscriminatorComponent d : slicing.getDiscriminator())
      b.append(d.getType().toCode()+":"+d.getPath());
    if (slicing.getDiscriminator().size() == 1)
      return "<li>The element "+path+" is sliced based on the value of "+b.toString()+s+"</li>\r\n";
    else
      return "<li>The element "+path+" is sliced based on the values of "+b.toString()+s+"</li>\r\n";
  }

  private void tryAdd(List<String> ext, String s) {
    if (!Utilities.noString(s) && !ext.contains(s))
      ext.add(s);
  }

  private String summariseExtension(String url, boolean modifier, String prefix) throws Exception {
    StructureDefinition ed = workerContext.getExtensionStructure(null, url);
    if (ed == null)
      return "<li>unable to summarise extension "+url+" (no extension found)</li>";
    if (ed.getUserData("path") == null)
      return "<li><a href=\""+prefix+"extension-"+ed.getId().toLowerCase()+".html\">"+url+"</a>"+(modifier ? " (<b>Modifier</b>) " : "")+"</li>\r\n";
    else
      return "<li><a href=\""+prefix+ed.getUserString("path")+"\">"+url+"</a>"+(modifier ? " (<b>Modifier</b>) " : "")+"</li>\r\n";
  }

  private String describeProfile(String url, String prefix) throws Exception {
    if (url.startsWith("http://hl7.org/fhir/StructureDefinition/") && (definitions.hasType(url.substring(40)) || definitions.hasResource(url.substring(40)) || "Resource".equals(url.substring(40))))
      return null;

    StructureDefinition ed = workerContext.fetchResource(StructureDefinition.class, url);
    if (ed == null)
      return "<li>unable to summarise profile "+url+" (no profile found)</li>";
    return "<li><a href=\""+prefix+ed.getUserString("path")+"\">"+url+"</a></li>\r\n";
  }

  private String describeReference(ElementDefinitionBindingComponent binding) throws FHIRException {
    if (binding.hasValueSet()) {
      ValueSet vs = workerContext.fetchResource(ValueSet.class, binding.getValueSet());
      String disp = vs != null ?  vs.present() : "??";
      return "<a href=\""+(vs == null ? binding.getValueSet() : vs.getUserData("filename"))+"\">"+disp+"</a>";
    } else 
      return "??";
  }

  private String summariseValue(Type fixed) throws Exception {
    if (fixed instanceof org.hl7.fhir.r4.model.PrimitiveType)
      return ((org.hl7.fhir.r4.model.PrimitiveType) fixed).asStringValue();
    if (fixed instanceof CodeableConcept)
      return summarise((CodeableConcept) fixed);
    if (fixed instanceof Quantity)
      return summarise((Quantity) fixed);
    throw new Exception("Generating text summary of fixed value not yet done for type "+fixed.getClass().getName());
  }

  private String summarise(Quantity quantity) {
    String cu = "";
    if ("http://unitsofmeasure.org/".equals(quantity.getSystem()))
      cu = " (UCUM: "+quantity.getCode()+")";
    if ("http://snomed.info/sct".equals(quantity.getSystem()))
      cu = " (SNOMED CT: "+quantity.getCode()+")";
    return quantity.getValue().toString()+quantity.getUnit()+cu;
  }

  private String summarise(CodeableConcept cc) throws Exception {
    if (cc.getCoding().size() == 1 && cc.getText() == null) {
      return summarise(cc.getCoding().get(0));
    } else if (cc.getCoding().size() == 0 && cc.hasText()) {
      return "\"" + cc.getText()+"\"";
    } else
      throw new Exception("too complex to describe");
  }

  private String summarise(Coding coding) throws Exception {
    if ("http://snomed.info/sct".equals(coding.getSystem()))
      return "SNOMED CT code "+coding.getCode()+ (coding.getDisplay() == null ? "" : "(\""+coding.getDisplay()+"\")");
    if ("http://loinc.org".equals(coding.getSystem()))
      return "LOINC code "+coding.getCode()+ (coding.getDisplay() == null ? "" : "(\""+coding.getDisplay()+"\")");
    CodeSystem vs = workerContext.fetchResource(CodeSystem.class, coding.getSystem());
    if (vs != null) {
      return "<a href=\""+vs.getUserData("filename")+"#"+coding.getCode()+"\">"+coding.getCode()+"</a>"+(coding.getDisplay() == null ? "" : "(\""+coding.getDisplay()+"\")");
    }
    throw new Exception("Unknown system "+coding.getSystem()+" generating fixed value description");
  }

  private String root(String path) {
    return path.contains(".") ? path.substring(0, path.lastIndexOf('.')) : path;
  }

  public String processExtensionIncludes(String filename, StructureDefinition ed, String xml, String json, String ttl, String tx, String src, String pagePath, ImplementationGuideDefn ig) throws Exception {
    String workingTitle = null;
    int level = ig.isCore() ? 0 : 1;

    while (src.contains("<%") || src.contains("[%"))
    {
      int i1 = src.indexOf("<%");
      int i2 = src.indexOf("%>");
      if (i1 == -1) {
        i1 = src.indexOf("[%");
        i2 = src.indexOf("%]");
      }
      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);

      String[] com = s2.split(" ");
      if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("extDefnHeader"))
        src = s1+extDefnHeader(filename, com.length > 1 ? com[1] : null)+s3;
      else if (com[0].equals("extension-table"))
        src = s1+generateExtensionTable(ed, filename, com[1], genlevel(level))+s3;
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }      else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+filename);
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(ed.getName())+s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+(workingTitle == null ? Utilities.escapeXml(ed.getName()) : workingTitle)+s3;
      else if (com[0].equals("xtitle"))
        src = s1+"Extension: "+Utilities.escapeXml(ed.getName())+s3;
      else if (com[0].equals("filetitle"))
        src = s1+(filename.contains(".") ? filename.substring(0, filename.lastIndexOf(".")) : filename)+s3;
      else if (com[0].equals("name"))
        src = s1+filename+s3;
      else if (com[0].equals("date")) {
        if (ed.hasDate())
          src = s1+ed.getDateElement().toHumanDisplay()+s3;
        else
          src = s1+"[no date]"+s3;
      } else if (com[0].equals("version"))
        src = s1+ini.getStringProperty("FHIR", "version")+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("definition"))
        src = s1+processor.process(Utilities.escapeXml(ed.getDescription()), "Definition on "+ed.getId())+s3;
      else if (com[0].equals("ext-comments")) {
        if (ed.getDifferential().getElementFirstRep().hasComment())
          src = s1+"<p><b>Comment</b>: "+processor.process(Utilities.escapeXml(ed.getDifferential().getElementFirstRep().getComment()), "Definition on "+ed.getId())+"</p>"+s3;
        else
          src = s1+s3;
      } else if (com[0].equals("status"))
        src = s1+(ed.getStatus() == null ? "??" : ed.getStatus().toCode())+s3;
      else if (com[0].equals("author"))
        src = s1+Utilities.escapeXml(ed.getPublisher())+s3;
      else if (com[0].equals("xml"))
        src = s1+xml+s3;
      else if (com[0].equals("json"))
        src = s1+json+s3;
      else if (com[0].equals("ttl"))
        src = s1+ttl+s3;
      else if (com[0].equals("tx"))
        src = s1+tx+s3;
      else if (com[0].equals("inv"))
        src = s1+genExtensionConstraints(ed)+s3;
      else if (com[0].equals("plural"))
        src = s1+Utilities.pluralizeMe(filename)+s3;
      else if (com[0].equals("notes"))
        src = s1+"todo" /*Utilities.fileToString(folders.srcDir + filename+File.separatorChar+filename+".html")*/ +s3;
      else if (com[0].equals("dictionary"))
        src = s1+definitionsProfile(ed, genlevel(level))+s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(filename) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(filename, genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist")) {
        String crumbTitle = ed.getUrl();
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(filename, "extension:"+ed.getName(), genlevel(level), crumbTitle) : ig.makeList(filename, "extension:"+ed.getName(), genlevel(level), crumbTitle))+ s3;
      } else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;
      else if (com[0].equals("buildId"))
        src = s1 + buildId + s3;
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;
      else if (com[0].equals("pagepath"))
        src = s1 + pagePath + s3;
      else if (com[0].equals("extensionurl"))
        src = s1 + ed.getUrl() + s3;
      else if (com[0].equals("rellink")) {
        if (!pagePath.contains(".html"))
          throw new Error("Invalid link: "+pagePath+" at "+workingTitle);
        src = s1 + Utilities.URLEncode(pagePath) + s3;
      } else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;
      else if (com[0].equals("baseURLn"))
        src = s1 + Utilities.appendForwardSlash(baseURL) + s3;
      else if (com[0].equals("mappings"))
        src = s1+mappingsExtension(ed)+s3;
      else if (com[0].equals("definitions"))
        src = s1+definitionsExtension(ed, "")+s3;
      else if (com[0].equals("pubdetails")) {
        src = s1+"Extension maintained by: " +Utilities.escapeXml(ed.getPublisher())+s3;
      } else if (com[0].equals("extref"))
        src = s1+""+s3;
      else if (com[0].equals("context-info"))
        src = s1+describeExtensionContext(ed)+s3;
      else if (com[0].equals("ext-name"))
        src = s1+Utilities.escapeXml(ed.getName())+s3;
      else if (com[0].equals("search-footer"))
        src = s1+searchFooter(level)+s3;
      else if (com[0].equals("search-header"))
        src = s1+searchHeader(level)+s3;
      else if (com[0].startsWith("!"))
        src = s1 + s3;
      else if (com[0].equals("wg")) {
        String wg = ToolingExtensions.readStringExtension(ed, ToolingExtensions.EXT_WORKGROUP);
        src = s1+(wg == null || !definitions.getWorkgroups().containsKey(wg) ?  "(No assigned work group) ("+wg+") (3)" : "<a _target=\"blank\" href=\""+definitions.getWorkgroups().get(wg).getUrl()+"\">"+definitions.getWorkgroups().get(wg).getName()+"</a> Work Group")+s3;
      } else if (com[0].equals("fmm-style"))  {
        String fmm = ed == null ? "N/A" :  ToolingExtensions.readStringExtension(ed, ToolingExtensions.EXT_FMM_LEVEL);
        StandardsStatus ss = ToolingExtensions.getStandardsStatus(ed);
        src = s1+fmmBarColorStyle(ss, fmm)+s3;
      } else if (com[0].equals("fmm")) {
        String fmm = ToolingExtensions.readStringExtension(ed, ToolingExtensions.EXT_FMM_LEVEL);
        StandardsStatus ss = ToolingExtensions.getStandardsStatus(ed);
        if (StandardsStatus.EXTERNAL == ss)
          src = s1+getFmmFromlevel(genlevel(level), "N/A")+s3;
        else if (StandardsStatus.NORMATIVE == ss)
          src = s1+getFmmFromlevel(genlevel(level), "<a href=\""+genlevel(level)+"versions.html#std-process\">N</a>")+s3;
        else
          src = s1+getFmmFromlevel(genlevel(level), fmm)+s3;
      } else if (com[0].equals("sstatus")) {
        StandardsStatus ss = ToolingExtensions.getStandardsStatus(ed);
        if (ss == null)
          ss = StandardsStatus.INFORMATIVE;
        src = s1+"<a href=\""+genlevel(level)+"versions.html#std-process\">Informative</a>"+s3;
      } else if (com[0].equals("profile-context"))
        src = s1+getProfileContext(ed, genlevel(level))+s3;
      else if (macros.containsKey(com[0])) {
        src = s1+macros.get(com[0])+s3;
      } else
        throw new Exception("Instruction <%"+s2+"%> not understood parsing resource "+filename);
    }
    return src;
  }

  private String describeExtensionContext(StructureDefinition ed) {
    return "<p>Context of Use: "+ProfileUtilities.describeExtensionContext(ed)+"</p>";
  }

  private String generateExtensionTable(StructureDefinition ed, String filename, String full, String prefix) throws Exception {
    return new XhtmlComposer(XhtmlComposer.HTML).compose(new ProfileUtilities(workerContext, null, this).generateExtensionTable(filename, ed, folders.dstDir, false, full.equals("true"), prefix, prefix, null));
  }


  private String getTerminologyNotes(StructureDefinition profile, int level) throws FHIRException {
    List<String> txlist = new ArrayList<String>();
    Map<String, ElementDefinitionBindingComponent> txmap = new HashMap<String, ElementDefinitionBindingComponent>();
    for (ElementDefinition ed : profile.getSnapshot().getElement()) {
      if (ed.hasBinding() && !"0".equals(ed.getMax())) {
        String path = ed.getPath();
        if (ed.getType().size() == 1 && ed.getType().get(0).getWorkingCode().equals("Extension"))
          path = path + "<br/>"+ed.getType().get(0).getProfile();
        txlist.add(path);
        txmap.put(path, ed.getBinding());
      }
    }
    if (txlist.isEmpty())
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<h4>Terminology Bindings</h4>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Path</b></td><td><b>Name</b></td><td><b>Conformance</b></td><td><b>ValueSet</b></td></tr>\r\n");
      for (String path : txlist)  {
        txItem(level, txmap, b, path);
      }
      b.append("</table>\r\n");
      return b.toString();

    }
  }

  public void txItem(int level, Map<String, ElementDefinitionBindingComponent> txmap, StringBuilder b, String path) throws FHIRException {
    ElementDefinitionBindingComponent tx = txmap.get(path);
    String vss = "";
    String vsn = "?ext";
    if (tx.hasValueSet()) {
      if (tx.hasValueSet()) {
        String uri = tx.getValueSet();
        if (uri.contains("|"))
          uri = uri.substring(0, uri.indexOf("|"));
        ValueSet vs = definitions.getValuesets().get(uri);
        if (vs == null) {
          if (uri.startsWith("http://hl7.org/fhir/ValueSet/")) {
            vss = "<a href=\""+genlevel(level)+"valueset-"+uri.substring(29)+".html\">"+Utilities.escapeXml(uri.substring(29))+"</a><!-- b -->";
          } else {
            vss = "<a href=\""+genlevel(level)+uri+"\">"+Utilities.escapeXml(uri)+"</a><!-- c -->";
          }
        } else {
          vss = "<a href=\""+genlevel(level)+vs.getUserData("path")+"\">"+Utilities.escapeXml(vs.present())+"</a><!-- d -->";
          vsn = vs.present();
        }
      }
    }
    if (vsn.equals("?ext"))
      System.out.println("No value set at "+path);
    b.append("<tr><td>").append(path).append("</td><td>").append(Utilities.escapeXml(vsn)).append("</td><td><a href=\"").
              append(genlevel(level)).append("terminologies.html#").append(tx.getStrength() == null ? "" : tx.getStrength().toCode()).
              append("\">").append(tx.getStrength() == null ? "" : tx.getStrength().toCode()).append("</a></td><td>").append(vss).append("</td></tr>\r\n");
  }

  private String getInvariantList(StructureDefinition profile) throws FHIRException, Exception {
    List<String> txlist = new ArrayList<String>();
    Map<String, List<ElementDefinitionConstraintComponent>> txmap = new HashMap<String, List<ElementDefinitionConstraintComponent>>();
    for (ElementDefinition ed : profile.getSnapshot().getElement()) {
      if (!"0".equals(ed.getMax())) {
        List<ElementDefinitionConstraintComponent> list = new ArrayList<ElementDefinition.ElementDefinitionConstraintComponent>();
        for (ElementDefinitionConstraintComponent t : ed.getConstraint()) {
          if (!t.hasSource()) {
            list.add(t);
          }
        }
        if (!list.isEmpty()) {
          txlist.add(ed.getPath());
          txmap.put(ed.getPath(), list);
        }
      }
    }
    if (txlist.isEmpty())
      return "";
    else {
      StringBuilder b = new StringBuilder();
      b.append("<h4>Constraints</h4>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td width=\"60\"><b>Id</b></td><td><b>Path</b></td><td><b>Details</b></td><td><b>Requirements</b></td></tr>\r\n");
      for (String path : txlist)  {
        List<ElementDefinitionConstraintComponent> invs = txmap.get(path);
        for (ElementDefinitionConstraintComponent inv : invs) {
          b.append("<tr>"+presentLevel(inv)+" <td>").append(inv.getKey()).append("</td><td>").append(path).append("</td><td>").append(Utilities.escapeXml(inv.getHuman()))
           .append("<br/><a href=\"http://hl7.org/fhirpath\">Expression</a>: ").append(Utilities.escapeXml(inv.getExpression())).append("</td><td>").append(Utilities.escapeXml(inv.getRequirements()));
           if (inv.hasExtension(ToolingExtensions.EXT_BEST_PRACTICE_EXPLANATION)) 
              b.append(". This is (only) a best practice guideline because: <blockquote>"+processMarkdown("best practice guideline", inv.getExtensionString(ToolingExtensions.EXT_BEST_PRACTICE_EXPLANATION), "")+"</blockquote>");
          
          b.append("</td></tr>\r\n");
        }
      }
      b.append("</table>\r\n");
      return b.toString();

    }
  }

  private String presentLevel(ElementDefinitionConstraintComponent inv) {
    if (inv.getSeverity() == ConstraintSeverity.WARNING) {
      if (inv.hasExtension(ToolingExtensions.EXT_BEST_PRACTICE))
        return "<a href=\"conformance-rules.html#best-practice\" style=\"color: DarkGreen\">Guideline</a> ";
      else
        return "<a href=\"conformance-rules.html#warning\" style=\"color: Chocolate\">Warning</a> ";
    }
    if (inv.getSeverity() == ConstraintSeverity.ERROR) 
      return "<a href=\"conformance-rules.html#rule\" style=\"color: Maroon\">Rule</a> ";

    // not stated as an error or a warning
    return "<a href=\"conformance-rules.html#rule\" style=\"color: Maroon\">Rule</a> ";
  }

  private String profileReviewLink(ConstraintStructure profile) {
    if (!profile.getUsage().isReview())
      return "";
    String s = Utilities.changeFileExt((String) profile.getResource().getUserData("filename"), "-review.xls");
    return "Use the <a href=\""+s+"\">Review Spreadsheet</a> to comment on this profile.";
  }

  /*
  private String profileExampleList(ProfileDefn profile, Map<String, Example> examples, String example) {
    if (examples == null || examples.isEmpty())
      return "<p>No Examples Provided.</p>";
    else if (examples.size() == 1)
      return example;
    else{
      StringBuilder s = new StringBuilder();

      boolean started = false;
      List<String> names = new ArrayList<String>();
      names.addAll(examples.keySet());
      Collections.sort(names);
      for (String n : names) {
        Example e = examples.get(n);
        if (!started)
          s.append("<p>Example Index:</p>\r\n<table class=\"list\">\r\n");
        started = true;
        if (e.getFileTitle().equals("capabilitystatement-base") || e.getFileTitle().equals("capabilitystatement-base2") || e.getFileTitle().equals("profiles-resources"))
          s.append("<tr><td>"+Utilities.escapeXml(e.getDescription())+"</td>");
        else
          s.append("<tr><td><a href=\""+e.getFileTitle()+".html\">"+Utilities.escapeXml(e.getDescription())+"</a></td>");
        s.append("<td><a href=\""+e.getFileTitle()+".xml.html\">XML</a></td>");
        s.append("<td><a href=\""+e.getFileTitle()+".json.html\">JSON</a></td>");
        s.append("</tr>");
      }

      //  }
      if (started)
        s.append("</table>\r\n");
      return s.toString();
    }
  }
  */

  private String mappingsProfile(StructureDefinition source) throws IOException {
    MappingsGenerator m = new MappingsGenerator(definitions);
    m.generate(source);
    return m.getMappings();
  }

  private String mappingsExtension(StructureDefinition ed) throws IOException {
    MappingsGenerator m = new MappingsGenerator(definitions);
    m.generate(ed);
    return m.getMappings();
  }

  private String definitionsProfile(StructureDefinition source, String prefix) throws Exception {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    DictHTMLGenerator d = new DictHTMLGenerator(b, this, prefix);
    d.generate(source);
    d.close();
    return b.toString();
  }

  private String definitionsExtension(StructureDefinition ed, String prefix) throws Exception {
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    DictHTMLGenerator d = new DictHTMLGenerator(b, this, prefix);
    d.generate(ed);
    d.close();
    return b.toString();
  }

  private String mapOnPageProfile(StructureDefinition source) {
    if (source.getMapping().size() < 2)
      return "";
    StringBuilder b = new StringBuilder();
    b.append("<div class=\"itoc\">\r\n<p>Mappings:</p>\r\n");
    for (StructureDefinitionMappingComponent map : source.getMapping()) {
      b.append("<p class=\"link\"><a href=\"#").append(map.getIdentity()).append("\">").append(map.getName()).append("</a></p>");
    }
    b.append("</div>\r\n");
    return b.toString();
  }

  private String baseLink(StructureDefinition structure, String prefix) throws Exception {
    if (!structure.hasBaseDefinition())
      return "";
    if (structure.getBaseDefinition().startsWith("http://hl7.org/fhir/StructureDefinition/")) {
      String name = structure.getBaseDefinition().substring(40);
      if (definitions.hasResource(name))
        return "<a href=\""+prefix+name.toLowerCase()+".html\">"+name+"</a>";
      else if (definitions.hasElementDefn(name))
        return "<a href=\""+prefix+definitions.getSrcFile(name)+".html#"+name+"\">"+name+"</a>";
      else {
        StructureDefinition p = definitions.getSnapShotForBase(structure.getBaseDefinition());
        if (p == null)
          return "??"+name;
        else
          return "<a href=\""+prefix+p.getUserString("path")+"\">"+p.getName()+"</a>";
      }
    } else {
      String[] parts = structure.getBaseDefinition().split("#");
      StructureDefinition profile = new ProfileUtilities(workerContext, null, null).getProfile(structure, parts[0]);
      if (profile != null) {
        if (parts.length == 2) {
          return "<a href=\""+prefix+profile.getUserData("filename")+"."+parts[1]+".html\">the structure "+parts[1]+"</a> in <a href=\""+profile.getUserData("filename")+".html\">the "+profile.getName()+" profile</a>";
        } else {
          return "<a href=\""+prefix+profile.getUserData("filename")+".html\">the "+profile.getName()+" profile</a>";
        }
      } else
        return "<a href=\""+structure.getBaseDefinition()+"\">"+structure.getBaseDefinition()+"</a>";
    }
  }

  private String generateProfileStructureTable(ConstraintStructure profile, boolean diff, String filename, String baseName, String prefix) throws Exception {
    String fn = filename.contains(".") ? filename.substring(0, filename.indexOf('.')) : filename;
    String deffile = fn+"-definitions.html";
    return new XhtmlComposer(XhtmlComposer.HTML).compose(new ProfileUtilities(workerContext, null, this).generateTable(deffile, profile.getResource(), diff, folders.dstDir, false, baseName, !diff, prefix, prefix, false, false, null));
  }

  private boolean isAggregationEndpoint(String name) {
    return definitions.getAggregationEndpoints().contains(name.toLowerCase());
  }

  private String makeArchives() throws Exception {
    IniFile ini = new IniFile(folders.rootDir+"publish.ini");
    StringBuilder s = new StringBuilder();
    s.append("<h2>Archived Versions of FHIR</h2>");
    s.append("<p>These archives only keep the more significant past versions of FHIR, and only the book form, and are provided for purposes of supporting html diff tools. A full archive history of everything is available <a href=\"http://wiki.hl7.org/index.php?title=FHIR\">through the HL7 gForge archives</a>.</p>");
    s.append("<ul>");
    if (ini.getPropertyNames("Archives") != null) {
      for (String v : ini.getPropertyNames("Archives")) {
        s.append("<li><a href=\"http://www.hl7.org/implement/standards/FHIR/v").append(v).append("/index.htm\">Version ").append(v).append("</a>, ")
                .append(ini.getStringProperty("Archives", v)).append("</li>");
        if (!definitions.getPastVersions().contains(v))
          definitions.getPastVersions().add(v);
      }
    }
    s.append("</ul>");
    return s.toString();
  }


  private String describeStatus(String s) {
    if (s.equals("draft"))
      return "as a draft";
    if (s.equals("testing"))
      return "for testing";
    if (s.equals("production"))
      return "for production use";
    if (s.equals("withdrawn"))
      return "as withdrawn from use";
    if (s.equals("superceded"))
      return "as superceded";
    return "with unknown status '" +s+'"';
  }

  public Definitions getDefinitions() {
    return definitions;
  }

  public FolderManager getFolders() {
    return folders;
  }
  public FHIRVersion getVersion() {
    return version;
  }

  public Navigation getNavigation() {
    return navigation;
  }

  public List<PlatformGenerator> getReferenceImplementations() {
    return referenceImplementations;
  }

  public IniFile getIni() {
    return ini;
  }

  public void setDefinitions(Definitions definitions) throws Exception {
    this.definitions = definitions;
    breadCrumbManager.setDefinitions(definitions);
    TerminologyClient client;
    try {
      client = new TerminologyClientR4(tsServer);
      client.setTimeout(30000);
    } catch(Exception e) {
      System.out.println("Warning @ PageProcessor client initialize: " + e.getLocalizedMessage());
      client = null;
    }

    workerContext = new BuildWorkerContext(definitions, client, definitions.getCodeSystems(), definitions.getValuesets(), conceptMaps, profiles, guides, folders.rootDir);
    workerContext.setDefinitions(definitions);
    workerContext.setLogger(this);
    log("Load Terminology Cache from "+Utilities.path(folders.rootDir, "vscache"), LogMessageType.Process);
    workerContext.initTS(Utilities.path(folders.rootDir, "vscache"));
    log("  .. loaded", LogMessageType.Process);
    vsValidator = new ValueSetValidator(workerContext, definitions.getVsFixups(), definitions.getStyleExemptions());
    breadCrumbManager.setContext(workerContext);

  }

  public void setVersion(FHIRVersion version) {
    this.version = version;
    workerContext.setVersion(version.toCode());
  }

  public void setFolders(FolderManager folders) throws Exception {
    this.folders = folders;
    htmlchecker = new HTMLLinkChecker(this, validationErrors, baseURL);
    r3r4Outcomes = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(Utilities.path(folders.rootDir, "implementations", "r3maps", "outcomes.json")));
    for (File f : new File(Utilities.path(folders.rootDir, "tools", "macros")).listFiles()) {
      if (f.getAbsolutePath().endsWith(".html")) {
        macros.put(Utilities.fileTitle(f.getName()), TextFile.fileToString(f));
      }
    }
  }

  public void setIni(IniFile ini) {
    this.ini = ini;
    for (String s : ini.getPropertyNames("page-titles")) {
      definitions.getPageTitles().put(s, ini.getStringProperty("page-titles", s));
    }
  }

  public HTMLLinkChecker getHTMLChecker() {
    return htmlchecker;
  }

  public Calendar getGenDate() {
    return genDate;
  }


  @Override
  public void log(String content, LogMessageType type) {
    if (suppressedMessages.contains(content) && (type == LogMessageType.Hint || type == LogMessageType.Warning))
      return;
    if (type == LogMessageType.Process) {
      Date stop = new Date();
      long l1 = start.getTime();
      long l2 = stop.getTime();
      long diff = l2 - l1;
      long secs = diff / 1000;
      float tmp = diff - lastSecs;
      float gap = tmp / 1000;
      lastSecs = diff;
      MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
      // mem.gc();
      long used = mem.getHeapMemoryUsage().getUsed() / (1024 * 1024);
      System.out.println(String.format("%1$-74s", content)+" "+String.format("%1$5s", Float.toString(gap))+" "+String.format("%1$3s", Long.toString(secs))+"sec "+String.format("%1$4s", Long.toString(used))+"MB");
    } else
      System.out.println(content);
  }

//  public void logNoEoln(String content) {
//    System.out.print(content);
//    notime = true;
//  }


  public void setNavigation(Navigation navigation) {
    this.navigation = navigation;
  }

  public List<String> getOrderedResources() {
    return orderedResources;
  }

  public Map<String, SectionTracker> getSectionTrackerCache() {
    return sectionTrackerCache;
  }

  public Map<String, TocEntry> getToc() {
    return toc;
  }

  public String getBuildId() {
    return buildId;
  }

  public void setBuildId(String buildId) {
    this.buildId = buildId;
  }

  public Document getV2src() {
    return v2src;
  }

  public void setV2src(Document v2src) {
    this.v2src = v2src;
  }

  public Document getV3src() {
    return v3src;
  }

  public void setV3src(Document v3src) {
    this.v3src = v3src;
  }

  public QaTracker getQa() {
    return qa;
  }

  private void addToValuesets(Bundle atom, ValueSet vs) {
    atom.getEntry().add(new BundleEntryComponent().setResource(vs).setFullUrl("http://hl7.org/fhir/"+vs.fhirType()+"/"+vs.getId()));
  }

  public Map<String, CodeSystem> getCodeSystems() {
    return definitions.getCodeSystems();
  }

  public Map<String, ValueSet> getValueSets() {
    return definitions.getValuesets();
  }

  public Map<String, ConceptMap> getConceptMaps() {
    return conceptMaps;
  }

  public Map<String, String> getSvgs() {
    return svgs;
  }

  public BreadCrumbManager getBreadCrumbManager() {
    return breadCrumbManager;
  }

  public String getPublicationNotice() {
    return publicationNotice;
  }

  public void setPublicationNotice(String publicationNotice) {
    this.publicationNotice = publicationNotice;
  }

  public String getPublicationType() {
    return publicationType;
  }

  public void setPublicationType(String publicationType) {
    this.publicationType = publicationType;
  }

  public void setRegistry(OIDRegistry registry) {
    this.registry = registry;

  }

  public OIDRegistry getRegistry() {
    return registry;
  }

  public void setId(String id) {
    this.oid = id;
  }

  public List<String> getSuppressedMessages() {
    return suppressedMessages;
  }

  public void loadSnomed() throws Exception {
    workerContext.loadSnomed(Utilities.path(folders.srcDir, "snomed", "snomed.xml"));
  }

  public void saveSnomed() throws Exception {
    workerContext.saveSnomed(Utilities.path(folders.srcDir, "snomed", "snomed.xml"));
    workerContext.saveLoinc(Utilities.path(folders.srcDir, "loinc", "loinc.xml"));
  }

  public void loadLoinc() throws Exception {
    log("Load Loinc", LogMessageType.Process);
    workerContext.loadLoinc(Utilities.path(folders.srcDir, "loinc", "loinc.xml"));
  }

  public Map<String, StructureDefinition> getProfiles() {
    return profiles;
  }

  public String getBaseURL() {
    return baseURL;
  }

  public void setBaseURL(String baseURL) {
    this.baseURL = !baseURL.endsWith("/") ? baseURL : baseURL + "/";
    if ("http://hl7-fhir.github.io".equals(this.baseURL)) // work around for a build script issue? see GF#12664
      this.baseURL = "http://build.fhir.org";
  }

  @Override
  public boolean isDatatype(String type) {
    return definitions.hasPrimitiveType(type) || (definitions.hasElementDefn(type) && !definitions.hasResource(type));
  }

  @Override
  public boolean isResource(String type) {
    return definitions.hasResource(type);
  }

  @Override
  public boolean hasLinkFor(String type) {
    return isDatatype(type) || definitions.hasResource(type) || definitions.getBaseResources().containsKey(type);
  }

  @Override
  public String getLinkFor(String corePath, String type) {
    if (definitions.hasResource(type) || definitions.getBaseResources().containsKey(type))
      return collapse(corePath, type.toLowerCase()+".html");
    else if (definitions.hasType(type))
      return collapse(corePath, definitions.getSrcFile(type)+".html#"+type);
    else if (profiles.containsKey(type) && profiles.get(type).hasUserData("path"))
      return collapse(corePath, profiles.get(type).getUserString("path"));
    else if (profiles.containsKey("http://hl7.org/fhir/StructureDefinition/"+type) && profiles.get("http://hl7.org/fhir/StructureDefinition/"+type).hasUserData("path"))
      return collapse(corePath, profiles.get("http://hl7.org/fhir/StructureDefinition/"+type).getUserString("path"));
    else
      return collapse(corePath, type.toLowerCase()+".html");
  }

  private String collapse(String corePath, String link) {
    if (Utilities.noString(corePath))
      return link;
    return corePath+link;
  }

  public Translations getTranslations() {
    return translations;
  }

  public void setTranslations(Translations translations) {
    this.translations = translations;
  }

  public String processMarkdown(String location, String text, String prefix) throws Exception {
    return processMarkdown(location, text, prefix, false);
  }
  
  public String processMarkdown(String location, String text, String prefix, boolean enforceFullStop) throws Exception {
    if (text == null)
      return "";
    // 1. custom FHIR extensions
    text = MarkDownPreProcessor.process(definitions, workerContext, validationErrors, text, location, prefix);
    if (enforceFullStop && !text.endsWith("."))
      text = text + ".";

    // 2. markdown
    String s = processor.process(checkEscape(text), location);
    return s;
  }

  private String checkEscape(String text) {
    if (Utilities.noString(text))
      return "";
    if (text.startsWith("```"))
      return text.substring(3);
    
    StringBuilder b = new StringBuilder();
    boolean escaping = true;
    for (int i = 0; i < text.length(); i++) {
      if (i < text.length()-3 && "```".equals(text.substring(i, i+3)))
        escaping = !escaping;
      char c = text.charAt(i);
      if (escaping && c == '<')
        b.append("&lt;");
      else if (escaping && c == '>')
        b.append("&gt;");
      else if (escaping && c == '&')
        b.append("&amp;");
      else if (escaping && c == '"')
        b.append("&quot;");
      else 
        b.append(c);
    }   
    return b.toString();
  }

  public BuildWorkerContext getWorkerContext() {
    return workerContext;
  }

  public Map<String, Resource> getIgResources() {
    return igResources;
  }


  @Override
  public BindingResolution resolveBinding(StructureDefinition profile, ElementDefinitionBindingComponent binding, String path) throws FHIRException {
    if (!binding.hasValueSet()) {
      BindingResolution br = new BindingResolution();
      br.url = "terminologies.html#unbound";
      br.display = "(unbound)";
      return br;
    } else {
      return resolveBinding(profile, binding.getValueSet(), binding.getDescription(), path);
    }
  }
  
  public BindingResolution resolveBinding(StructureDefinition profile, String ref, String path) throws FHIRException {
    return resolveBinding(profile, ref, null, path);
  }
  
  public BindingResolution resolveBinding(StructureDefinition profile, String ref, String description, String path) throws FHIRException {
    BindingResolution br = new BindingResolution();
    if (ref.contains("|"))
      ref = ref.substring(0 , ref.indexOf("|"));
    if (ref.startsWith("http://terminology.hl7.org/ValueSet/v3-")) {
      br.url = "v3/"+ref.substring(39)+"/vs.html";
      br.display = ref.substring(39);
    } else if (definitions.getValuesets().containsKey(ref)) {
      ValueSet vs = definitions.getValuesets().get(ref);
      br.url = vs.getUserString("path");
      br.display = vs.present();
    } else if (ref.startsWith("ValueSet/")) {
      ValueSet vs = definitions.getValuesets().get(ref.substring(8));
      if (vs == null) {
        br.url = ref.substring(9)+".html";
        br.display = ref.substring(9);
      } else {
        br.url = vs.getUserString("path");
        br.display = vs.present();
      }
    } else if (ref.startsWith("http://hl7.org/fhir/ValueSet/")) {
      ValueSet vs = definitions.getValuesets().get(ref);
      if (vs == null)
        vs = definitions.getExtraValuesets().get(ref);
      if (vs != null) {
        br.url = vs.getUserString("path");
        if (Utilities.noString(br.url))
          br.url = ref.substring(23)+".html";
        br.display = vs.present();
      } else if (ref.substring(23).equals("use-context")) { // special case because this happens before the value set is created
        br.url = "valueset-"+ref.substring(23)+".html";
        br.display = "Context of Use ValueSet";
      } else if (ref.startsWith("http://terminology.hl7.org/ValueSet/v3-")) {
        br.url = "v3/"+ref.substring(26)+"/index.html";
        br.display = ref.substring(26);
      }  else if (ref.startsWith("http://terminology.hl7.org/ValueSet/v2-")) {
        br.url = "v2/"+ref.substring(26)+"/index.html";
        br.display = ref.substring(26);
      }  else if (ref.startsWith("#")) {
        br.url = null;
        br.display = ref;
      } else {
        br.url = ref;
        br.display = "????";
        getValidationErrors().add(
            new ValidationMessage(Source.Publisher, IssueType.NOTFOUND, -1, -1, path, "Unresolved Value set "+ref, IssueSeverity.WARNING));
      }
    } else {
      br.url = ref;
      if (ref.equals("http://tools.ietf.org/html/bcp47"))
        br.display = "IETF BCP-47";
      else if (ref.equals("http://www.rfc-editor.org/bcp/bcp13.txt"))
        br.display = "IETF BCP-13";
      else if (ref.equals("http://www.ncbi.nlm.nih.gov/nuccore?db=nuccore"))
        br.display = "NucCore";
      else if (ref.equals("https://rtmms.nist.gov/rtmms/index.htm#!rosetta"))
        br.display = "Rosetta";
      else if (ref.equals("http://www.iso.org/iso/country_codes.htm"))
        br.display = "ISO Country Codes";
      else if (ref.equals("http://www.ncbi.nlm.nih.gov/clinvar/variation"))
        br.display = "ClinVar";
      else if (!Utilities.noString(description))
        br.display = description;
      else
        br.display = "????";
    }
    return br;
  }

  @Override
  public String getLinkForProfile(StructureDefinition profile, String url) {
    String fn;
    if (url.equals("http://hl7.org/fhir/markdown"))  // magic
      return "narrative.html#markdown|markdown";

    if (!url.startsWith("#")) {
      String[] path = url.split("#");
      profile = new ProfileUtilities(workerContext, null, null).getProfile(null, path[0]);
//      if (profile == null && url.startsWith("StructureDefinition/"))
//        return "hspc-"+url.substring(8)+".html|"+url.substring(8);
    }
    if (profile != null) {
      fn = profile.getUserString("path");
      if (fn == null) {
        fn = profile.getUserString("filename");
        if (fn != null) {
          fn = Utilities.changeFileExt(fn, ".html");
        }
      }
      if (fn == null)
        return "|??";
      return fn+"|"+profile.getName();
    }
    return null;
  }

  public String processConformancePackageIncludes(Profile pack, String src, String intro, String notes, String resourceName, ImplementationGuideDefn ig) throws Exception {
    String workingTitle = null;
    int level = (ig == null || ig.isCore()) ? 0 : 1;
    //boolean even = false;

    while (src.contains("<%") || src.contains("[%"))
    {
      int i1 = src.indexOf("<%");
      int i2 = src.indexOf("%>");
      if (i1 == -1) {
        i1 = src.indexOf("[%");
        i2 = src.indexOf("%]");
      }
      String s1 = src.substring(0, i1);
      String s2 = src.substring(i1 + 2, i2).trim();
      String s3 = src.substring(i2+2);

      String[] com = s2.split(" ");
      if (com[0].equals("file"))
        src = s1+TextFile.fileToString(folders.srcDir + com[1]+".html")+s3;
      else if (com[0].equals("settitle")) {
        workingTitle = s2.substring(9).replace("{", "<%").replace("}", "%>");
        src = s1+s3;
      }  else if (com[0].equals("reflink")) {
        src = s1 + reflink(com[1]) + s3;
      } else if (com[0].equals("setlevel")) {
        level = Integer.parseInt(com[1]);
        src = s1+s3;
      } else if (com.length != 1)
        throw new Exception("Instruction <%"+s2+"%> not understood parsing profile "+pack.getId());
      else if (com[0].equals("pageheader"))
        src = s1+pageHeader(pack.getId().toUpperCase().substring(0, 1)+pack.getId().substring(1))+s3;
      else if (com[0].equals("newheader"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader.html")+s3;
      else if (com[0].equals("newheader1"))
        src = s1+TextFile.fileToString(folders.srcDir + "newheader1.html")+s3;
      else if (com[0].equals("footer"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer.html")+s3;
      else if (com[0].equals("newfooter"))
        src = s1+TextFile.fileToString(folders.srcDir + "newfooter.html")+s3;
      else if (com[0].equals("footer1"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer1.html")+s3;
      else if (com[0].equals("footer2"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer2.html")+s3;
      else if (com[0].equals("footer3"))
        src = s1+TextFile.fileToString(folders.srcDir + "footer3.html")+s3;
      else if (com[0].equals("title"))
        src = s1+(workingTitle == null ? Utilities.escapeXml(pack.getTitle()) : workingTitle)+s3;
      else if (com[0].equals("xtitle"))
        src = s1+Utilities.escapeXml(pack.getId().toUpperCase().substring(0, 1)+pack.getId().substring(1))+s3;
      else if (com[0].equals("name"))
        src = s1+pack.getId()+s3;
      else if (com[0].equals("package.intro"))
        src = s1+(intro == null ? pack.metadata("description") : intro) +s3;
      else if (com[0].equals("package.notes"))
        src = s1+(notes == null ? "" : notes) +s3;
      else if (com[0].equals("canonicalname"))
        src = s1+makeCanonical(pack.getId())+s3;
      else if (com[0].equals("prettyname"))
        src = s1+makePretty(pack.getId())+s3;
      else if (com[0].equals("version"))
        src = s1+version+s3;
      else if (com[0].equals("gendate"))
        src = s1+Config.DATE_FORMAT().format(new Date())+s3;
      else if (com[0].equals("maindiv"))
        src = s1+"<div class=\"content\">"+s3;
      else if (com[0].equals("/maindiv"))
        src = s1+"</div>"+s3;
      else if (com[0].equals("v2Index"))
        src = s1+genV2Index()+s3;
      else if (com[0].equals("v2VSIndex"))
        src = s1+genV2VSIndex()+s3;
      else if (com[0].equals("v3Index-cs"))
        src = s1+genV3CSIndex()+s3;
      else if (com[0].equals("v3Index-vs"))
        src = s1+genV3VSIndex()+s3;
      else if (com[0].equals("mappings-table"))
        src = s1+genMappingsTable()+s3;
      else if (com[0].equals("id"))
        src = s1+pack.getId()+s3;
      else if (com[0].equals("events"))
        src = s1 + getEventsTable(pack.getId())+ s3;
      else if (com[0].equals("resourcecodes"))
        src = s1 + genResCodes() + s3;
      else if (com[0].equals("datatypecodes"))
        src = s1 + genDTCodes() + s3;
      else if (com[0].equals("allparams"))
        src = s1 + allParamlist() + s3;
//      else if (com[0].equals("bindingtable-codelists"))
//        src = s1 + genBindingTable(true) + s3;
//      else if (com[0].equals("bindingtable"))
//        src = s1 + genBindingsTable() + s3;
      else if (com[0].equals("codeslist"))
        src = s1 + genCodeSystemsTable() + s3;
//      else if (com[0].equals("valuesetslist"))
//        src = s1 + genValueSetsTable() + s3;
      else if (com[0].equals("igvaluesetslist"))
        src = s1 + genIGValueSetsTable() + s3;
      else if (com[0].equals("namespacelist"))
        src = s1 + genNSList() + s3;
      else if (com[0].equals("conceptmapslist"))
        src = s1 + genConceptMapsTable() + s3;
//      else if (com[0].equals("bindingtable-others"))
//        src = s1 + genBindingTable(false) + s3;
      else if (com[0].equals("resimplall"))
          src = s1 + genResImplList() + s3;
      else if (com[0].equals("impllist"))
        src = s1 + genReferenceImplList(pack.getId()) + s3;
      else if (com[0].equals("breadcrumb"))
        src = s1 + breadCrumbManager.make(pack.getId()) + s3;
      else if (com[0].equals("navlist"))
        src = s1 + breadCrumbManager.navlist(pack.getId(), genlevel(level)) + s3;
      else if (com[0].equals("breadcrumblist"))
        src = s1 + ((ig == null || ig.isCore()) ? breadCrumbManager.makelist(pack.getId(), "profile:"+resourceName+"/"+pack.getId(), genlevel(level), pack.getTitle()): ig.makeList(pack.getId(), "profile:"+resourceName+"/"+pack.getId(), genlevel(level), pack.getTitle())) + s3;
      else if (com[0].equals("year"))
        src = s1 + new SimpleDateFormat("yyyy").format(new Date()) + s3;
      else if (com[0].equals("buildId"))
        src = s1 + buildId + s3;
      else if (com[0].equals("pub-type"))
        src = s1 + publicationType + s3;
      else if (com[0].equals("pub-notice"))
        src = s1 + publicationNotice + s3;
      else if (com[0].equals("level"))
        src = s1 + genlevel(level) + s3;
      else if (com[0].equals("pagepath"))
        src = s1 + pack.getId() + s3;
      else if (com[0].equals("rellink")) {
        if (pack.getId().contains(".html"))
          throw new Error("Invalid link: "+pack.getId()+" at "+workingTitle);
        src = s1 + Utilities.URLEncode(pack.getId()+".html") + s3;
      } else if (com[0].equals("baseURL"))
        src = s1 + Utilities.URLEncode(baseURL) + s3;
      else if (com[0].equals("description"))
        src = s1 + Utilities.escapeXml(pack.getDescription()) + s3;
      else if (com[0].equals("package-content"))
        src = s1 + getPackageContent(pack, genlevel(level)) + s3;
      else if (com[0].equals("search-footer"))
        src = s1+searchFooter(level)+s3;
      else if (com[0].equals("search-header"))
        src = s1+searchHeader(level)+s3;
      else if (com[0].equals("package.search"))
        src = s1+getSearch(pack)+s3;
      else if (com[0].startsWith("!"))
        src = s1 + s3;
      else if (com[0].equals("wg")) {
        String wg = pack.getWg();
        if (Utilities.noString(wg) && definitions.hasResource(resourceName))
          wg = definitions.getResourceByName(resourceName).getWg().getCode();
        if (wg == null || !definitions.getWorkgroups().containsKey(wg))
          src = s1+"(No assigned work group) ("+wg+") (4)"+s3;
        else
          src = s1+ "<a _target=\"blank\" href=\""+definitions.getWorkgroups().get(wg).getUrl()+"\">"+definitions.getWorkgroups().get(wg).getName()+"</a> Work Group"+s3;
      } else if (macros.containsKey(com[0])) {
        src = s1+macros.get(com[0])+s3;
      } else
        throw new Exception("Instruction <%"+s2+"%> not understood parsing profile "+pack.getId());
    }
    return src;
  }

  private String getPackageContent(Profile pack, String prefix) throws Exception {
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"lines\">");
    if (pack.getProfiles().size() > 0) {
      s.append("<tr><td colspan=\"2\"><b>Profiles</b>: </td></tr>");
      for (ConstraintStructure p : pack.getProfiles())
        s.append("<tr><td><a href=\"").append(p.getId()).append(".html\">").append(Utilities.escapeXml(p.getTitle()))
                .append("</a></td><td>").append(Utilities.escapeXml(p.getResource().getDescription())).append("</td></tr>");
    }
    if (pack.getExtensions().size() > 0) {
      s.append("<tr><td colspan=\"2\"><b>Extensions</b>: </td></tr>");
      for (StructureDefinition ed : pack.getExtensions())
        s.append("<tr><td><a name=\"extension-").append(prefix+ed.getId()).append("\"> </a><a href=\"extension-").append(ed.getId().toLowerCase()).append(".html\">").append(Utilities.escapeXml(ed.getId()))
                .append("</a></td><td><b>").append(Utilities.escapeXml(ed.getName())).append("</b> : ").append(processMarkdown(pack.getId(), ed.getDescription(), prefix)).append("</td></tr>");
    }
    if (pack.getExamples().size() > 0) {
      s.append("<tr><td colspan=\"2\"><b>Examples</b>: </td></tr>");
      for (Example ex : pack.getExamples())
        s.append("<tr><td><a href=\"").append(ex.getTitle()).append(".html\">").append(Utilities.escapeXml(Utilities.changeFileExt(ex.getName(), "")))
                .append("</a></td><td>").append(processMarkdown(pack.getId(), ex.getDescription(), prefix)).append("</td></tr>");
    }
    s.append("</table>");

    if (pack.getSearchParameters().size() > 0) {
      // search parameters
      StringBuilder b = new StringBuilder();
      b.append("<a name=\"search\"> </a>\r\n");
      b.append("<h3>Search Parameters</h3>\r\n");
      b.append("<p>Search parameters defined by this package. See <a href=\""+prefix+"search.html\">Searching</a> for more information about searching in REST, messaging, and services.</p>\r\n");
      b.append("<table class=\"list\">\r\n");
      b.append("<tr><td><b>Name</b></td><td><b>Type</b></td><td><b>Description</b></td><td><b>Paths</b></td><td><b>Source</b></td></tr>\r\n");
      List<String> names = new ArrayList<String>();
      for (SearchParameter sp : pack.getSearchParameters())
        names.add(sp.getName());
      Collections.sort(names);
      for (String name : names)  {
        SearchParameter p = null;
        for (SearchParameter sp : pack.getSearchParameters())
          if (name.equals(sp.getName()))
            p = sp;
        b.append("<tr><td>"+p.getName()+"</td><td><a href=\""+prefix+"search.html#"+p.getType().toCode()+"\">"+p.getType().toCode()+"</a></td>" +
            "<td>"+Utilities.escapeXml(p.getDescription())+"</td><td>"+(p.hasXpath() ? p.getXpath() : "")+(p.getType() == SearchParamType.REFERENCE && p.hasTarget() ? asText(p.getTarget()) : "")+"</td>" +
            "<td><a href=\""+p.getId()+".xml.html\">XML</a> / <a href=\""+p.getId()+".json.html\">JSON</a></td></tr>\r\n");
      }
      b.append("</table>\r\n");
      s.append(b.toString());
    }
    return s.toString();
  }

  private String asText(List<CodeType> target) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (CodeType rn : target) {
      if (first) {
        first = false;
        b.append("<br/>(");
      } else
        b.append(", ");
      final String value = rn.getValue();
      if ("Any".equals(value))
        b.append("Any");
       else
        b.append("<a href=\"").append(value.toLowerCase()).append(".html\">").append(value).append("</a>");
    }
    if (!first)
      b.append(")");
    return b.toString();
  }

  private String genW5(boolean types) throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table border=\"1\">\r\n<tr>\r\n");
    List<W5Entry> items = new ArrayList<W5Entry>();
    for (W5Entry e : definitions.getW5list())
      if (e.isDisplay())
        items.add(e);

    b.append("<td>Resource</td>");
    for (W5Entry e : items) {
      b.append("<td><span title=\"").append(Utilities.escapeXml(definitions.getW5s().get(e.getCode()).getDescription())).append("\">").append(e.getCode()).append("</span></td>");

    }
    b.append("</tr>\r\n");
    processW5(b, items, "clinical", types);
    processW5(b, items, "administrative", types);
    processW5(b, items, "workflow", types);
    processW5(b, items, "infrastructure", types);
    processW5(b, items, "conformance", types);
    processW5(b, items, "financial", types);

    b.append("</table>\r\n");

    return b.toString();
  }

  private void processW5(StringBuilder b, List<W5Entry> items, String cat, boolean types) throws Exception {
    b.append("<tr><td colspan=\"").append(Integer.toString(items.size() + 1)).append("\"><b>")
            .append(Utilities.escapeXml(definitions.getW5s().get(cat).getDescription())).append("</b></td></tr>\r\n");
    for (String rn : definitions.sortedResourceNames()) {
      ResourceDefn r = definitions.getResourceByName(rn);
      if (r.getRoot().getW5().startsWith(cat)) {
        b.append("<tr>\r\n <td>").append(rn).append(" (").append(r.getFmmLevel()).append(")").append("</td>\r\n");
        for (W5Entry e : items) {
          b.append(" <td>");
          addMatchingFields(b, r.getRoot().getElements(), r.getRoot().getName(), e.getCode(), true, types);
          b.append("</td>\r\n");
        }
        b.append("</tr>\r\n");
      }
    }
  }

  private boolean addMatchingFields(StringBuilder b, List<ElementDefn> elements, String path, String name, boolean first, boolean types) {
    for (ElementDefn ed : elements) {
      if (name.equals(ed.getW5())) {
        if (first) first = false; else b.append("<br/>");
        describeField(b, ed, types);
      }
      first = addMatchingFields(b, ed.getElements(), path+"."+ed.getName(), name, first, types);
    }
    return first;
  }

  private void describeField(StringBuilder b, ElementDefn ed, boolean types) {
    b.append(ed.getName());
    if (ed.unbounded())
      b.append("*");
    if (types) {
      b.append(" : ");
      b.append(patch(ed.typeCode()));
    }
  }

  private String patch(String typeCode) {
    if (typeCode.startsWith("Reference("))
      return typeCode.substring(0, typeCode.length()-1).substring(10);
    else
      return typeCode;
  }

  private String genStatusCodes() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<table border=\"1\">\r\n");
    int colcount = 0;
    for (ArrayList<String> row: definitions.getStatusCodes().values()) {
      int rc = 0;
      for (int i = 0; i < row.size(); i++) 
        if (!Utilities.noString(row.get(i))) 
          rc = i;
      if (rc > colcount)
        colcount = rc;
    }
//    b.append("<tr>");
//    b.append("<td>Path</td>");
//    for (int i = 0; i < colcount; i++)
//      b.append("<td>c").append(Integer.toString(i + 1)).append("</td>");
//    b.append("</tr>\r\n");

    List<String> names = new ArrayList<String>();
    for (String n : definitions.getStatusCodes().keySet())
       names.add(n);
    Collections.sort(names);

    ArrayList<String> row = definitions.getStatusCodes().get("@code");
    b.append("<tr>");
    b.append("<td><b>code</b></td>");
    for (int i = 0; i < colcount; i++)
      b.append("<td><b><a href=\"codesystem-resource-status.html#resource-status-"+row.get(i)+"\">").append(row.get(i)).append("</a></b></td>");
    b.append("</tr>\r\n");      
    row = definitions.getStatusCodes().get("@codes");
    b.append("<tr>");
    b.append("<td><b>stated codes</b></td>");
    for (int i = 0; i < colcount; i++)
      b.append("<td>").append(i < row.size() ? row.get(i) : "").append("</td>");
    b.append("</tr>\r\n");      

    b.append("<tr>");
    b.append("<td>actual codes</td>");
    for (int i = 0; i < colcount; i++) {
      Set<String> codeset = new HashSet<String>();
      for (String n : names) {
        if (!n.startsWith("@")) {
          row = definitions.getStatusCodes().get(n);
          String c = row.get(i);
          if (!Utilities.noString(c)) {
            codeset.add(c);
          }
        }
      }
      b.append("<td>").append(separated(codeset, ", ")).append("</td>");
    }
    b.append("</tr>\r\n");      

    row = definitions.getStatusCodes().get("@issues");
    b.append("<tr>");
    b.append("<td><b>Issues?</b></td>");
    for (int i = 0; i < colcount; i++) {
      String s = i < row.size() ? row.get(i) : "";
      b.append("<td").append(Utilities.noString(s) ? "" : " style=\"background-color: #ffcccc\"").append(">").append(s).append("</td>");
    }
    b.append("</tr>\r\n");      
    
    for (String n : names) {
      if (!n.startsWith("@")) {
        b.append("<tr>");
        ElementDefn ed = getElementDefn(n);
        if (ed == null || !ed.isModifier())
          b.append("<td>").append(linkToPath(n)).append("</td>");
        else
          b.append("<td><b>").append(linkToPath(n)).append("</b></td>");
        row = definitions.getStatusCodes().get(n);
        for (int i = 0; i < colcount; i++)
          b.append("<td>").append(i < row.size() ? row.get(i) : "").append("</td>");
        b.append("</tr>\r\n");
      }
    }

    b.append("</table>\r\n");
    CodeSystem cs = getCodeSystems().get("http://hl7.org/fhir/resource-status");
    row = definitions.getStatusCodes().get("@code");
    for (int i = 0; i < colcount; i++) {
      String code = row.get(i);
      String definition = CodeSystemUtilities.getCodeDefinition(cs, code);
      Set<String> dset = new HashSet<String>();
      for (String n : names) {
        if (!n.startsWith("@")) {
          ArrayList<String> rowN = definitions.getStatusCodes().get(n);
          String c = rowN.get(i);
          String d = getDefinition(n, c);
          if (!Utilities.noString(d))
            dset.add(d);
        }
      }
      b.append("<hr/>\r\n");
      b.append("<h4>").append(code).append("</h4>\r\n");
      b.append("<p>").append(Utilities.escapeXml(definition)).append("</p>\r\n");
      b.append("<p>Definitions for matching codes:</p>\r\n");
      b.append("<ul>\r\n");
      for (String s : sorted(dset))
        b.append("<li>").append(Utilities.escapeXml(s)).append("</li>\r\n");
      b.append("</ul>\r\n");
    }
    
    return b.toString();
  }

  private String getDefinition(String n, String c) {
    ElementDefn e = null;
    try {
      e = definitions.getElementByPath(n.split("\\."), "Status Codes", true);
    } catch (Exception ex) {
      throw new Error("Unable to find "+n, ex);
    }
    if (e == null) {
      throw new Error("Unable to find "+n);
    }
    if (e.getBinding() == null)
      return null;
    List<DefinedCode> t;
    try {
      t = e.getBinding().getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true);
    } catch (Exception e1) {
      return null;
    }
    if (t == null)
      return null;
    for (DefinedCode d : t) {
      if (d.getCode().equals(c))
        return d.getDefinition();
    }
    return null;
  }

  private String linkToPath(String n) {
    if (n.contains(".")) {
      return "<a href=\""+n.substring(0, n.indexOf(".")).toLowerCase()+"-definitions.html#"+n+"\">"+n+"</a>";
    }
    return n;
  }

  private Object separated(Set<String> set, String sep) {
    CommaSeparatedStringBuilder cb = new CommaSeparatedStringBuilder(sep);
    for (String s : sorted(set))
      cb.append(s);
    return cb.toString();
  }

  private ElementDefn getElementDefn(String n) throws Exception {
    String[] path = n.split("\\.");
    ElementDefn ed = definitions.getElementDefn(path[0]);
    for (int i = 1; i < path.length; i++) {
      if (ed == null)
        return null;
      ed = ed.getElementByName(definitions, path[i], true, false);
    }
    return ed;
  }

  public String expandVS(ValueSet vs, String prefix, String base) {
    try {
      ValueSetExpansionOutcome result = workerContext.expandVS(vs, true, true);
      if (result.getError() != null)
        return "<hr/>\r\n"+VS_INC_START+"<!--3-->"+processExpansionError(result.getError())+VS_INC_END;

      if (result.getValueset() == null)
        return "<hr/>\r\n"+VS_INC_START+"<!--4-->"+processExpansionError("(no error returned)")+VS_INC_END;
      ValueSet exp = result.getValueset();
      if (exp == vs)
        throw new Exception("Expansion cannot be the same instance");
      exp.setCompose(null);
      exp.setText(null);
      exp.setDescription("Value Set Contents (Expansion) for "+vs.present()+" at "+Config.DATE_FORMAT().format(new Date()));

      int i = countContains(exp.getExpansion().getContains());
      IniFile sini = new IniFile(Utilities.path(folders.rootDir, "temp", "stats.ini"));
      sini.setIntegerProperty("valuesets", vs.getId(), i, null);
      sini.save();
      new NarrativeGenerator(prefix, base, workerContext, this).setTooCostlyNoteEmpty(TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(TOO_MANY_CODES_TEXT_NOT_EMPTY).generate(null, exp, vs, false);
      return "<hr/>\r\n"+VS_INC_START+""+new XhtmlComposer(XhtmlComposer.HTML).compose(exp.getText().getDiv())+VS_INC_END;
    } catch (Exception e) {
      e.printStackTrace();
      return "<hr/>\r\n"+VS_INC_START+"<!--5-->"+processExpansionError(e instanceof NullPointerException ? "NullPointerException" : e.getMessage())+" "+Utilities.escapeXml(stack(e))+VS_INC_END;
    }
  }

private int countContains(List<ValueSetExpansionContainsComponent> list) {
    int i = list.size();
    for (ValueSetExpansionContainsComponent c : list) {
      if (c.hasContains())
        i = i + countContains(c.getContains());
    }
    return i;
  }

//  public List<ValidationMessage> getCollectedValidationErrors() {
//    return collectedValidationErrors;
//  }

  public List<ValidationMessage> getValidationErrors() {
    return validationErrors;
  }

  private String genResRefList(String n) throws Exception {
    ResourceDefn e = definitions.getResourceByName(n);
    StringBuilder b = new StringBuilder();
    b.append("<ul>\r\n");
    for (ElementDefn c : e.getRoot().getElements())
      genResRefItem(b, n.toLowerCase(), n, c);
    b.append("</ul>\r\n");
    return b.toString();
  }

  private void genResRefItem(StringBuilder b, String base, String path, ElementDefn e) {
    path = path+"."+e.getName();
    if (e.typeCode().startsWith("Reference(")) {
      b.append(" <li><a href=\"");
      b.append(base);
      b.append("-definitions.html#");
      b.append(path);
      b.append("\">");
      b.append(path);
      b.append("</a></li>\r\n");
    }
    for (ElementDefn c : e.getElements())
      genResRefItem(b, base, path, c);
  }

  public Set<String> getSearchTypeUsage() {
    searchTypeUsage.add("id:token");
    return searchTypeUsage;
  }

  private String getStandardsStatus(String resourceName) throws FHIRException {
    ResourceDefn rd = definitions.getResourceByName(resourceName);
    if (rd == null)
      throw new FHIRException("unable to find resource '"+resourceName+"'");
    if (rd.getNormativeVersion() != null)
      return "&nbsp;<a href=\"versions.html#std-process\" title=\"Standard Status\">Normative</a> (from v"+rd.getNormativeVersion()+")";
    else if (rd.getNormativePackage() != null) {
      return "&nbsp;<a href=\"versions.html#std-process\" title=\"Standard Status\">Normative</a>";
    } else
      return "&nbsp;<a href=\"versions.html#std-process\" title=\"Standard Status\">"+rd.getStatus().toDisplay()+"</a>";
  }
  private String getFmm(String resourceName, boolean hideNormative) throws Exception {
    ResourceDefn rd = definitions.getResourceByName(resourceName);
    if (rd == null)
      throw new Exception("unable to find resource '"+resourceName+"'");
    if (rd.getNormativePackage() != null || rd.getNormativeVersion() != null) {
      if (hideNormative)
        return "n/a";
      else
        return "&nbsp;<a href=\"versions.html#maturity\" title=\"Maturity Level\">Normative</a>";
    } else
      return "&nbsp;<a href=\"versions.html#maturity\" title=\"Maturity Level\">"+rd.getFmmLevel()+"</a>";
  }
  
  private String getNormativePackageRef(String resourceName) throws Exception {
    ResourceDefn rd = definitions.getResourceByName(resourceName);
    if (rd == null)
      throw new Exception("unable to find resource '"+resourceName+"'");
    if (rd.getNormativePackage() == null && rd.getNormativeVersion() == null)
      return "";
    else
      return " <a href=\"versions.html#std-process\" title=\"Normative Content\" class=\"normative-flag\">N</a>";
  }
  private String getFmmShort(String resourceName) throws Exception {
    ResourceDefn rd = definitions.getResourceByName(resourceName);
    if (rd == null)
      throw new Exception("unable to find resource '"+resourceName+"'");
    if (rd.getNormativePackage() != null || rd.getNormativeVersion() != null)
      return "<a href=\"versions.html#std-process\" style=\"color: maroon; hover: maroon; visited; maroon; opacity: 0.7\" title=\"Maturity Level\">"+rd.getFmmLevel()+"</a>"+
        "<a href=\"versions.html#std-process\" title=\"Normative Content\" class=\"normative-flag\">N</a>";
    else
      return "<a href=\"versions.html#std-process\" style=\"color: maroon; hover: maroon; visited; maroon; opacity: 0.7\" title=\"Maturity Level\">"+rd.getFmmLevel()+"</a>";
  }
  private String getFmmFromlevel(String prefix, String level) throws Exception {
    return "&nbsp;<a href=\""+prefix+"versions.html#maturity\" title=\"Maturity Level\">Maturity Level</a>: "+(Utilities.noString(level) ? "0" : level);
  }

  private String getFmmShortFromlevel(String prefix, String level) throws Exception {
    if (level == "n/a")
      return "";
    else
      return "<a href=\""+prefix+"versions.html#std-process\" style=\"color: maroon; hover: maroon; visited; maroon; opacity: 0.7\" title=\"Maturity Level\">"+(Utilities.noString(level) ? "0" : level)+"</a>";
  }

  private String getXcm(String param) {
    if (searchTypeUsage.contains(param))
      return "<span style=\"font-weight: bold\">Y</span> ";
    else
      return "<span style=\"color: grey\">N</span>";
  }

  private String getXcmChk(String param) {
    boolean used = false;
    if (searchTypeUsage.contains(param+":number"))
      used = true;
    if (searchTypeUsage.contains(param+":date"))
      used = true;
    if (searchTypeUsage.contains(param+":reference"))
      used = true;
    if (searchTypeUsage.contains(param+":quantity"))
      used = true;
    if (searchTypeUsage.contains(param+":uri"))
      used = true;
    if (searchTypeUsage.contains(param+":string"))
      used = true;
    if (searchTypeUsage.contains(param+":token"))
      used = true;
    if (used)
      throw new Error("data type "+param+" is used in search after all");
    return "";
  }

  private String genCSList() throws FHIRException {
    StringBuilder b = new StringBuilder();
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getCodeSystems().keySet());
    Collections.sort(names);
    for (String n : names) {
      CodeSystem cs = definitions.getCodeSystems().get(n);
      if (cs != null) {
        if (cs.getUrl().startsWith("http://hl7.org/fhir") && !cs.getUrl().startsWith("http://terminology.hl7.org/CodeSystem/v2-") && !cs.getUrl().startsWith("http://terminology.hl7.org/CodeSystem/v3-")) {
          b.append("  <tr>\r\n");
          b.append("    <td><a href=\""+cs.getUserString("path")+"\">"+cs.getName()+"</a>");
          if (StandardsStatus.NORMATIVE == ToolingExtensions.getStandardsStatus(cs))
            b.append(" <a href=\"versions.html#std-process\" title=\"Normative Content\" class=\"normative-flag\">N</a>");
          b.append("</td>\r\n");
          b.append("    <td>"+(cs.hasTitle() ? cs.getTitle()+": " : "")+Utilities.escapeXml(cs.getDescription())+"</td>\r\n");
          String oid = CodeSystemUtilities.getOID(cs);
          b.append("    <td>"+(oid == null ? "" : oid)+"</td>\r\n");
          b.append("  </tr>\r\n");
        }
      }
    }
    return b.toString();
  }

  private String genCSListX() throws FHIRException {
    StringBuilder b = new StringBuilder();
    List<String> names = new ArrayList<String>();
    names.addAll(definitions.getCodeSystems().keySet());
    Collections.sort(names);
    for (String n : names) {
      CodeSystem cs = definitions.getCodeSystems().get(n);
      if (cs != null) {
        if (cs.getUrl().startsWith("http://terminology.hl7.org/CodeSystem") && !cs.getUrl().startsWith("http://terminology.hl7.org/CodeSystem/v2-") && !cs.getUrl().startsWith("http://terminology.hl7.org/CodeSystem/v3-")) {
          b.append("  <tr>\r\n");
          b.append("    <td><a href=\""+cs.getUserString("path")+"\">"+cs.getName()+"</a>");
          if (StandardsStatus.NORMATIVE == ToolingExtensions.getStandardsStatus(cs))
            b.append(" <a href=\"versions.html#std-process\" title=\"Normative Content\" class=\"normative-flag\">N</a>");
          b.append("</td>\r\n");
          b.append("    <td>"+(cs.hasTitle() ? cs.getTitle()+": " : "")+Utilities.escapeXml(cs.getDescription())+"</td>\r\n");
          String oid = CodeSystemUtilities.getOID(cs);
          b.append("    <td>"+(oid == null ? "" : oid)+"</td>\r\n");
          b.append("  </tr>\r\n");
        }
      }
    }
    return b.toString();
  }

  public ValueSetValidator getVsValidator() {
    return vsValidator;
  }

  public void clean() {
    // recover some memory. Keep only what is needed for validation
//    definitions = null;
    navigation = null;
    ini = null;
    prevSidebars.clear();
    orderedResources.clear();
    sectionTrackerCache.clear();
    toc.clear();;
    v2src = null;
    v3src = null;
    igResources.clear();
    svgs.clear();
    translations = null;
    registry = null;
    htmlchecker = null;
    searchTypeUsage = null;
    vsValidator = null;
    suppressedMessages.clear();
    definitions.clean();
    referenceImplementations.clear();
    
    conceptMaps = null;
    profiles = null;
    igResources = null;
    searchTypeUsage = null;
    diffEngine = null;
    typeBundle = null;
    resourceBundle = null;
    r3r4Outcomes = null;
    normativePackages = null;
    processor = null;

    
    System.gc();
  }

  public void clean2() {
    if (definitions.getCodeSystems() != null) 
      definitions.getCodeSystems().clear();
    if (definitions.getValuesets() != null) 
      definitions.getValuesets().clear();
    System.gc();
  }

  private String genNSList() throws Exception {
    StringBuilder b = new StringBuilder();
    b.append("<p>Redirects on this page:</p>\r\n");
    b.append("<ul>\r\n");
    b.append(" <li>Resources</li>\r\n");
    b.append(" <li>Data Types</li>\r\n");
    b.append(" <li>Code Systems</li>\r\n");
    b.append(" <li>Value Sets</li>\r\n");
    b.append(" <li>Extensions</li>\r\n");
    b.append(" <li>Profiles</li>\r\n");
    b.append(" <li>Naming Systems</li>\r\n");
    b.append(" <li>Examples</li>\r\n");
    b.append(" <li>Compartments</li>\r\n");
    b.append(" <li>Data Elements</li>\r\n");
    b.append(" <li>Search Parameters</li>\r\n");
    b.append(" <li>Implementation Guides</li>\r\n");
    b.append(" <li>SIDs</li>\r\n");
    b.append(" <li>Others From publish.ini</li>\r\n");
    b.append("</ul>\r\n");
    b.append("<table class=\"grid\">\r\n");
    b.append(" <tr><td><b>URL</b></td><td><b>Thing</b></td><td><b>Page</b></td></tr>");

    for (String n : definitions.sortedResourceNames())
      definitions.addNs("http://hl7.org/fhir/"+n, n+" Resource", n.toLowerCase()+".html");
    for (String n : definitions.getTypes().keySet())
      definitions.addNs("http://hl7.org/fhir/"+n, "Data Type "+n, definitions.getSrcFile(n)+".html#"+n);
    for (String n : definitions.getStructures().keySet())
      definitions.addNs("http://hl7.org/fhir/"+n, "Data Type "+n, definitions.getSrcFile(n)+".html#"+n);
    for (String n : definitions.getPrimitives().keySet())
      definitions.addNs("http://hl7.org/fhir/"+n, "Primitive Data Type "+n, definitions.getSrcFile(n)+".html#"+n);
    for (String n : definitions.getConstraints().keySet())
      definitions.addNs("http://hl7.org/fhir/"+n, "Data Type Profile "+n, definitions.getSrcFile(n)+".html#"+n);
    for (String n : definitions.getInfrastructure().keySet())
      definitions.addNs("http://hl7.org/fhir/"+n, "Data Type "+n, definitions.getSrcFile(n)+".html#"+n);
    for (CodeSystem cs : getCodeSystems().values())
      if (cs != null && cs.getUrl().startsWith("http://hl7.org/fhir"))
        definitions.addNs(cs.getUrl(), "CodeSystem "+cs.getName(), cs.getUserString("path"));
    for (ValueSet vs : getValueSets().values())
      if (vs.getUrl().startsWith("http://hl7.org/fhir"))
        definitions.addNs(vs.getUrl(), "ValueSet "+vs.present(), vs.getUserString("path"));
    for (ConceptMap cm : getConceptMaps().values())
      if (cm.getUrl().startsWith("http://hl7.org/fhir"))
        definitions.addNs(cm.getUrl(), "Concept Map"+cm.getName(), cm.getUserString("path"));
    for (StructureDefinition sd : profiles.values())
      if (sd.getUrl().startsWith("http://hl7.org/fhir") && !definitions.getResourceTemplates().containsKey(sd.getName()))
        definitions.addNs(sd.getUrl(), "Profile "+sd.getName(), sd.getUserString("path"));
    for (StructureDefinition sd : workerContext.getExtensionDefinitions())
      if (sd.getUrl().startsWith("http://hl7.org/fhir"))
        definitions.addNs(sd.getUrl(), "Profile "+sd.getName(), sd.getUserString("path"));
    for (NamingSystem nss : definitions.getNamingSystems()) {
      String url = null;
      definitions.addNs("http://hl7.org/fhir/NamingSystem/"+nss.getId(), "System "+nss.getName(), nss.getUserString("path"));
      for (NamingSystemUniqueIdComponent t : nss.getUniqueId()) {
        if (t.getType() == NamingSystemIdentifierType.URI)
          url = t.getValue();
      }
      if (url != null && url.startsWith("http://hl7.org/fhir"))
        definitions.addNs(url, "System "+nss.getName(), nss.getUserString("path"));
    }
    for (String n : ini.getPropertyNames("redirects")) {
      String[] parts = ini.getStringProperty("redirects", n).split("\\;");
      definitions.addNs(n, "System "+parts[0], parts[1]);
    }
    for (ImplementationGuideDefn ig : definitions.getIgs().values()) {
      if (!ig.isCore()) {
        definitions.addNs("http://hl7.org/fhir/ImplementationGuide/"+ig.getCode(), ig.getName(), ig.getHomePage());
        definitions.addNs("http://hl7.org/fhir/"+ig.getCode(), ig.getName(), ig.getHomePage());
      }
    }
    for (Compartment t : definitions.getCompartments()) {
      definitions.addNs(t.getUri(), t.getName(), "compartmentdefinition.html#"+t.getName());
    }

    List<String> list = new ArrayList<String>();
    list.addAll(definitions.getRedirectList().keySet());
    Collections.sort(list);
    for (String url : list) {
      NamespacePair p = definitions.getRedirectList().get(url);
      if (!url.contains("MetadataResource")) {
        b.append(" <tr><td>"+Utilities.escapeXml(url)+"</td><td>"+hsplt(Utilities.escapeXml(p.desc))+"</td><td><a href=\""+p.page+"\">"+hsplt(Utilities.escapeXml(p.page))+"</a></td></tr>\r\n");
      }
    }
    b.append("</table>\r\n");
    b.append("<p>"+Integer.toString(list.size())+" Entries</p>\r\n");
    return b.toString();
  }

  private String hsplt(String s) {
    return s.replace(".", "\u200B.").replace("-", "\u200B-").replace("/", "\u200B/");
  }

  public boolean isForPublication() {
    return forPublication;
  }

  public void setForPublication(boolean forPublication) {
    this.forPublication = forPublication;
  }

  public void loadUcum() throws UcumException, IOException {
    workerContext.loadUcum(Utilities.path(folders.srcDir, "ucum-essence.xml"));
  }

  @Override
  public boolean prependLinks() {
    return true;
  }

  
  private String genModifierList() {
    StringBuilder b = new StringBuilder();
    for (String s : sorted(definitions.getTypes().keySet()))
      checkForModifiers(b, s, definitions.getTypes().get(s));
    for (String s : sorted(definitions.getStructures().keySet()))
      checkForModifiers(b, s, definitions.getStructures().get(s));
    for (String s : sorted(definitions.getInfrastructure().keySet()))
      checkForModifiers(b, s, definitions.getInfrastructure().get(s));
    for (String s : sorted(definitions.getBaseResources().keySet()))
      checkForModifiers(b, s, definitions.getBaseResources().get(s).getRoot());
    for (String s : sorted(definitions.getResources().keySet()))
      checkForModifiers(b, s, definitions.getResources().get(s).getRoot());
    return b.toString();
  }

  private void checkForModifiers(StringBuilder b, String path, ElementDefn e) {
    if (e.isModifier() && !e.getName().equals("status")) {
      b.append(" <li><a href=\""+definitions.getSrcFile(path.substring(0, path.indexOf(".")))+"-definitions.html#"+path+"\">"+path+"</a></li>\r\n");
    }
    for (ElementDefn c : e.getElements())
      checkForModifiers(b, path+"."+c.getName(), c);
  }

  private boolean isFromMetadataResource(String path, ElementDefn e) {
    return Utilities.existsInList(path.contains(".") ? path.split("\\.")[0] : path,
        "ActivityDefinition", "CapabilityStatement", "CodeSystem", "CompartmentDefinition", "ConceptMap", "DataElement", "ExpansionProfile", "GraphDefinition", "ImplementationGuide", "Library", "Measure", "MessageDefinition", "OperationDefinition", "PlanDefinition", "Questionnaire", "SearchParameter", 
        "ServiceDefinition", "StructureDefinition", "StructureMap", "TestScript", "ValueSet")
        && Utilities.existsInList(e.getName(), "status", "experimental");
  }

  private List<String> sorted(Set<String> keySet) {
    List<String> res = new ArrayList<String>();
    res.addAll(keySet);
    Collections.sort(res);
    return res;
  }

  private String genMeaningWhenMissingList() throws Exception {
    StringBuilder b = new StringBuilder();
    for (String s : sorted(definitions.getTypes().keySet()))
      checkForMeaningWhenMissing(b, s, definitions.getTypes().get(s));
    for (String s : sorted(definitions.getStructures().keySet()))
      checkForMeaningWhenMissing(b, s, definitions.getStructures().get(s));
    for (String s : sorted(definitions.getInfrastructure().keySet()))
      checkForMeaningWhenMissing(b, s, definitions.getInfrastructure().get(s));
    for (String s : sorted(definitions.getBaseResources().keySet()))
      checkForMeaningWhenMissing(b, s, definitions.getBaseResources().get(s).getRoot());
    for (String s : sorted(definitions.getResources().keySet()))
      checkForMeaningWhenMissing(b, s, definitions.getResources().get(s).getRoot());
    return b.toString();
  }

  private void checkForMeaningWhenMissing(StringBuilder b, String path, ElementDefn e) throws Exception {
    if (e.hasMeaningWhenMissing()) {
      b.append(" <li><a href=\""+definitions.getSrcFile(path.substring(0, path.indexOf(".")))+"-definitions.html#"+path+"\">"+path+"</a>: "+Utilities.escapeXml(e.getMeaningWhenMissing())+"</li>\r\n");
    }
//    if (e.getDefaultValue() != null) {
//      b.append(" <li><a href=\""+definitions.getSrcFile(path.substring(0, path.indexOf(".")))+"-definitions.html#"+path+"\">"+path+"</a>: "+renderType(e.getDefaultValue())+"</li>\r\n");
//    }
    for (ElementDefn c : e.getElements())
      checkForMeaningWhenMissing(b, path+"."+c.getName(), c);
  }

  @SuppressWarnings("rawtypes")
  private String renderType(Type v) throws Exception {
    if (v instanceof org.hl7.fhir.r4.model.PrimitiveType)
      return ((org.hl7.fhir.r4.model.PrimitiveType) v).asStringValue();
    throw new Exception("unhandled default value");
  }

  private String genAllSearchParams() throws Exception {
    List<SearchParameter> splist = new ArrayList<SearchParameter>();

    for (ResourceDefn rd : getDefinitions().getBaseResources().values())
      addSearchParams(splist, rd);
    for (ResourceDefn rd : getDefinitions().getResources().values())
      addSearchParams(splist, rd);
    for (Profile cp : getDefinitions().getPackList()) {
      addSearchParams(splist, cp);
    }
    StringBuilder b = new StringBuilder();
    genSearchParams(b, splist, "Resource");
    genSearchParams(b, splist, "DomainResource");
    genCommonSearchParams(b, splist);
    for (String n : definitions.sortedResourceNames())
      genSearchParams(b, splist, n);
    return b.toString();
  }

  private void genSearchParams(StringBuilder b, List<SearchParameter> splist, String base) throws Exception {
    List<SearchParameter> list = new ArrayList<SearchParameter>();
    for (SearchParameter sp : splist) {
      for (CodeType ct : sp.getBase())
        if (ct.asStringValue().equals(base)) {
          boolean found = false;
          for (SearchParameter spt : list)
            if (spt == sp)
              found = true;
          if (!found)
            list.add(sp);
        }
    }
    Collections.sort(list, new SearchParameterListSorter());
    if (list.size() > 0) {
      StandardsStatus bss = definitions.getResourceByName(base).getStatus();
      b.append("<tr><td colspan=\"5\" style=\"background-color: #dddddd\"><b><a href=\""+base.toLowerCase()+".html\">"+base+"</a><a name=\""+base.toLowerCase()+"\"> </a></b>"+makeStandardsStatusRef(bss)+"</td></tr>\r\n");
      for (SearchParameter sp : list) {
        String ss = "";
        StandardsStatus spss = ToolingExtensions.getStandardsStatus(sp);
        if (spss != null && spss != bss)
          ss = makeStandardsStatusRef(spss);
        if (sp.getBase().size() > 1) {
          SearchParameterDefn spd = definitions.getResourceByName(base).getSearchParams().get(sp.getCode());
          b.append("<tr><td>"+sp.getCode()+ss+"</td><td><a href=\"search.html#"+sp.getType().toCode()+"\">"+sp.getType().toCode()+"</a></td><td>"+sp.getId()+"</td><td>"+processMarkdown("allsearchparams", spd.getDescription(), "")+"</td><td>"+Utilities.escapeXml(spd.getExpression()).replace(".", ".&#8203;")+"</td></tr>\r\n");
        } else
          b.append("<tr><td>"+sp.getCode()+ss+"</td><td><a href=\"search.html#"+sp.getType().toCode()+"\">"+sp.getType().toCode()+"</a></td><td>"+sp.getId()+"</td><td>"+processMarkdown("allsearchparams", sp.getDescription(), "")+"</td><td>"+Utilities.escapeXml(sp.getExpression()).replace(".", ".&#8203;")+"</td></tr>\r\n");
      }
    }
  }

  public class SearchParameterListSorter implements Comparator<SearchParameter> {

    @Override
    public int compare(SearchParameter sp0, SearchParameter sp1) {
      return sp0.getCode().compareTo(sp1.getCode());
    }
  }


  private void genCommonSearchParams(StringBuilder b, List<SearchParameter> splist) throws Exception {
    List<SearchParameter> list = new ArrayList<SearchParameter>();
    for (SearchParameter sp : splist) {
      if (sp.getBase().size() > 1) {
        boolean found = false;
        for (SearchParameter spt : list)
          if (spt == sp)
            found = true;
        if (!found)
          list.add(sp);
      }
    }
    Collections.sort(list, new SearchParameterListSorter());
    if (list.size() > 0) {
      b.append("<tr><td colspan=\"5\" style=\"background-color: #dddddd\"><b>Common Search Parameters<a name=\"common\"> </a></b></td></tr>\r\n");
      for (SearchParameter sp : list) {
        b.append("<tr><td>"+sp.getCode()+"<a name=\""+sp.getId()+"\"> </a>"+makeStandardsStatusRef(ToolingExtensions.getStandardsStatus(sp))+"</td><td><a href=\"search.html#"+sp.getType().toCode()+"\">"+sp.getType().toCode()+"</a></td><td>"+sp.getId()+"</td><td>"+processMarkdown("allsearchparams", sp.getDescription(), "")+"</td><td>"+Utilities.escapeXml(sp.getExpression()).replace(".", ".&#8203;")+"</td></tr>\r\n");
      }
    }
  }

  private void addSearchParams(List<SearchParameter> splist, ResourceDefn rd) {
    if (rd.getConformancePack() == null) {
      for (SearchParameterDefn spd : rd.getSearchParams().values()) {
        splist.add(spd.getResource());
      }
    } else
      addSearchParams(splist, rd.getConformancePack());
  }

  private void addSearchParams(List<SearchParameter> splist, Profile conformancePack) {
    for (SearchParameter sp : conformancePack.getSearchParameters()) {
      splist.add(sp);
    }
  }

  private void addSearchParams(Map<String, SearchParameter> spmap, Profile conformancePack, String rn) {
    for (SearchParameter sp : conformancePack.getSearchParameters()) {
      boolean ok = false;
      for (CodeType c : sp.getBase()) {
        ok = ok || rn.equals(c.getValue());
      }
      if (ok) {
        spmap.put(sp.getId(), sp);
      }
    }
  }

  @Override
  public ResourceWithReference resolve(String url) {
    String[] parts = url.split("\\/");

    if (parts.length == 2 && definitions.hasResource(parts[0]) && parts[1].matches(FormatUtilities.ID_REGEX)) {
      Example ex = null;
      try {
        ex = findExample(parts[0], parts[1]);
      } catch (Exception e) {
      }
      if (ex != null)
        return new ResourceWithReference(parts[0].toLowerCase()+"-"+parts[1].toLowerCase()+".html", null);
    }
//    System.out.println("Reference to undefined resource: \""+url+"\"");
    return new ResourceWithReference("todo.html", null);
  }

  public SpecDifferenceEvaluator getDiffEngine() {
    if (diffEngine == null)
      return null;
    return diffEngine; 
  }

  public Bundle getTypeBundle() {
    return typeBundle;
  }

  public void setTypeBundle(Bundle typeBundle) {
    this.typeBundle = typeBundle;
  }

  public Bundle getResourceBundle() {
    return resourceBundle;
  }

  public void setResourceBundle(Bundle resourceBundle) {
    this.resourceBundle = resourceBundle;
  }

  private IEvaluationContext evaluationContext;

  public IEvaluationContext getExpressionResolver() {
    if (evaluationContext == null)
      evaluationContext = new PageEvaluationContext();
    return evaluationContext;
  }

  @Override
  public void logMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void logDebugMessage(LogCategory category, String message) {
//     System.out.println(message);
  }


  public class ResourceSummary {

    boolean mapped;
    int testCount;
    int executeFailCount;
    int roundTripFailCount;
    int r4ValidationFailCount;
    int r4ValidationErrors;
    public boolean isMapped() {
      return mapped;
    }
    public void setMapped(boolean mapped) {
      this.mapped = mapped;
    }
    public int getTestCount() {
      return testCount;
    }
    public void setTestCount(int testCount) {
      this.testCount = testCount;
    }
    public int getExecuteFailCount() {
      return executeFailCount;
    }
    public void setExecuteFailCount(int executeFailCount) {
      this.executeFailCount = executeFailCount;
    }
    public int getRoundTripFailCount() {
      return roundTripFailCount;
    }
    public void setRoundTripFailCount(int roundTripFailCount) {
      this.roundTripFailCount = roundTripFailCount;
    }
    public int getR4ValidationFailCount() {
      return r4ValidationFailCount;
    }
    public void setR4ValidationFailCount(int r3ValidationFailCount) {
      this.r4ValidationFailCount = r3ValidationFailCount;
    }
    public int getR4ValidationErrors() {
      return r4ValidationErrors;
    }
    public void setR4ValidationErrors(int r3ValidationErrors) {
      this.r4ValidationErrors = r3ValidationErrors;
    }
    public int executePct() {
      return (executeCount() * 100) / testCount;
    }
    private int executeCount() {
      return testCount - executeFailCount;
    }
    public int roundTripPct() {
      return executeCount() == 0 ? 0 : ((executeCount() - roundTripFailCount) * 100) / executeCount();
    }
    public int r4ValidPct() {
      return executeCount() == 0 ? 0 : ((executeCount() - r4ValidationFailCount) * 100) / executeCount();
    }

  }

  private String genR3MapsSummary() throws IOException {
    StringBuilder b = new StringBuilder();
    for (String n : definitions.sortedResourceNames()) {
      ResourceSummary rs = getResourceSummary(n);
      if (rs.isMapped()) {
        b.append("<tr><td><a href=\""+n.toLowerCase()+"-version-maps.html\">"+n+"</a></td>");
        b.append("<td>"+Integer.toString(rs.getTestCount())+"</td>");
        b.append("<td style=\"background-color: "+mapsBckColor(rs.executePct(), "#ffaaaa", "#ffffff")+"\">"+Integer.toString(rs.executePct())+"</td>");
        b.append("<td style=\"background-color: "+mapsBckColor(rs.roundTripPct(), "#ffcccc", "#ffffff")+"\">"+Integer.toString(rs.roundTripPct())+"</td>");
        b.append("<td style=\"background-color: "+mapsBckColor(rs.r4ValidPct(), "#ffcccc", "#ffffff")+"\">"+Integer.toString(rs.r4ValidPct())+"</td>");
        if (rs.getR4ValidationErrors() > 0)
          b.append("<td>"+Integer.toString(rs.getR4ValidationErrors())+"</td>");
        else
          b.append("<td></td>");

      } else {
        b.append("<tr><td>"+n+"</td>");
        b.append("<td colspan=\"54\" style=\"background-color: #efefef\">No r2:r3 maps available</td>");
      }
      b.append("</tr>");
    }
    return b.toString();
  }

  private ResourceSummary getResourceSummary(String n) throws IOException {
    ResourceSummary rs = new ResourceSummary();
    JsonObject r = r3r4Outcomes.getAsJsonObject(n);
    if (r != null && (new File(Utilities.path(folders.rootDir, "implementations", "r3maps", "R4toR3", n+".map")).exists())) {
      rs.setMapped(true);
      for (Entry<String, JsonElement> e : r.entrySet()) {
        JsonObject el = (JsonObject) e.getValue();
        rs.testCount++;
        JsonPrimitive p = el.getAsJsonPrimitive("execution");
        if (p!= null && !p.isBoolean())
          rs.executeFailCount++;
        if (el.has("r4.errors")) {
          rs.r4ValidationFailCount++;
          if (el.getAsJsonArray("r3.errors") != null)
            rs.r4ValidationErrors = rs.r4ValidationErrors+el.getAsJsonArray("r3.errors").size();
          else
            rs.r4ValidationErrors = rs.r4ValidationErrors;
        }
        if (el.has("round-trip"))
          rs.roundTripFailCount++;
      }
    } else
      rs.setMapped(false);
    return rs;
  }

  private String mapsBckColor(int pct, String badColor, String goodColor) {
    return pct == 100 ? goodColor : badColor;
  }

  public String r3r4StatusForResource(String name) throws IOException {
    ResourceSummary rs = getResourceSummary(name);
    if (!rs.isMapped())
      return "Not Mapped";

    StringBuilder b = new StringBuilder();
    b.append(rs.getTestCount());
    b.append(rs.getTestCount() == 1 ? " test" : " tests");
    if (rs.getExecuteFailCount() == 0)
      b.append(" that all execute ok.");
    else
      b.append(" <span style=\"background-color: #ffcccc\">of which "+Integer.toString(rs.getExecuteFailCount())+" fail to execute</span>.");
    if (rs.getTestCount() - rs.getExecuteFailCount() > 0) {
      if (rs.getRoundTripFailCount() == 0)
        b.append(" All tests pass round-trip testing ");
      else
        b.append(" <span style=\"background-color: #ccffcc\">"+Integer.toString(rs.getRoundTripFailCount())+" fail round-trip testing</span>");
      if (rs.getR4ValidationFailCount() == 0)
        b.append(" and all r3 resources are valid.");
      else
        b.append(" and <span style=\"background-color: #E0B0FF\">"+Integer.toString(rs.getR4ValidationFailCount())+" r3 resources are invalid ("+Integer.toString(rs.getR4ValidationErrors())+" errors).</span>");
    }
    return b.toString();

  }

  @Override
  public String getLink(String typeName) {
    if (definitions.hasType(typeName))
      return definitions.getSrcFile(typeName)+".html#"+typeName;
    if (definitions.hasResource(typeName))
      return typeName.toLowerCase()+".html#"+typeName;
    return null;
  }

  public String getR3R4ValidationErrors(String name) {
    StringBuilder b = new StringBuilder();
    JsonObject r = r3r4Outcomes.getAsJsonObject(name);
    if (r != null) {
      boolean first = true;
      for (Entry<String, JsonElement> e : r.entrySet()) {
        JsonObject el = (JsonObject) e.getValue();
        if (el.has("r3.errors")) {
          if (first) {
            first = false;
            b.append("<table class=\"grid\">\r\n");
          }
          b.append(" <tr>\r\n");
          b.append("  <td>");
          b.append(e.getKey());
          b.append("</td>\r\n");
          JsonArray arr = el.getAsJsonArray("r3.errors");
          b.append("  <td>");
          b.append("   <ul>");
          for (JsonElement n : arr) {
            b.append("    <li>");
            b.append(n.getAsString());
            b.append("</li>\r\n");
          }
          b.append("   </ul>\r\n");
          b.append("  </td>\r\n");
          b.append(" </tr>\r\n");
        }
      }
      if (!first) {
        b.append("</table>\r\n");        
      }
      if (first)
        b.append("<p>No validation errors - all conversions are clean</p>\r\n");
    } else
      b.append("<p>n/a</p>\r\n");
    return b.toString();
  }

  public Map<String, Map<String, PageInfo>> getNormativePackages() {
    return normativePackages;
  }

  private String genStructureList() {
    StringBuilder b = new StringBuilder();
    b.append("<tr>");
    int i = 0;
    for (String s : sorted(definitions.getStructures().keySet())) {
      TypeDefn td = definitions.getStructures().get(s);
      if (td.typeCode().equals("Structure")) {
        b.append("<td style=\"background-color: ");
        b.append(td.getStandardsStatus().getColor());
        b.append("\"><a href=\"");
        b.append(definitions.getSrcFile(s)+".html#"+s);
        b.append("\">");
        b.append(s);      
        b.append("</a></td>");
        i++;
        if (i == 4) {
          b.append("</tr>");
          b.append("<tr>");
          i = 0;
        }
      }
    }
    b.append("</tr>");
    return b.toString();
  }
  
  private String genWildcardTypeList() {
    StringBuilder b = new StringBuilder();
    TypeClassification tc = null;
    boolean first = true;
    for (WildcardInformation wi : TypesUtilities.wildcards()) {
      if (tc != wi.getClassification()) {
        if (first)
          first = false;
        else
          b.append("</ul>\r\n");
        tc = wi.getClassification();
        b.append("<b>"+Utilities.pluralize(tc.toDisplay(), 2)+"</b>\r\n");
        b.append("<ul class=\"dense\">\r\n");
      }
      b.append("<li><a href=\"");
      b.append(definitions.getSrcFile(wi.getTypeName())+".html#"+wi.getTypeName());
      b.append("\">");
      b.append(wi.getTypeName());      
      b.append("</a></li>\r\n");
    }
    b.append("</ul>\r\n");
    return b.toString();
  }
  
  private String genExtensionTypeList() {
    StringBuilder b = new StringBuilder();
    for (WildcardInformation wi : TypesUtilities.wildcards()) {
      b.append("<li>value");
      b.append(Utilities.capitalize(wi.getTypeName()));
      b.append(": <a href=\"");
      b.append(definitions.getSrcFile(wi.getTypeName())+".html#"+wi.getTypeName());
      b.append("\">");
      b.append(wi.getTypeName());      
      b.append("</a>");
      if (!Utilities.noString(wi.getComment())) {
        b.append(" " +wi.getComment());      
      }
      b.append("</li>");
    }
    return b.toString();
  }

  private String genV2Expansion(String name, String prefix) throws Exception {
    ValueSet vs = definitions.getValuesets().get("http://terminology.hl7.org/ValueSet/v2-"+name);
    CodeSystem cs = definitions.getCodeSystems().get("http://terminology.hl7.org/CodeSystem/v2-"+name);
    if (cs != null && !hasInactiveCodes(cs)) 
      return "";
    StringBuilder b = new StringBuilder();
    b.append("<p>A current expansion is provided for this table because it is not simply 'all codes in the table', or because some codes have been deprecated</p>\r\n");
    b.append(expandValueSet(name, vs, prefix));
    return b.toString();
  }

  private boolean hasInactiveCodes(CodeSystem cs) {
    for (ConceptDefinitionComponent cc : cs.getConcept()) {
      if (CodeSystemUtilities.isDeprecated(cs, cc))
        return true;
    }
    return false;
  }

  private String listCanonicalResources() throws FHIRException {
    StringBuilder b = new StringBuilder();
    b.append("<ul style=\"column-count: 3\">\r\n");
    for (String rn : definitions.sortedResourceNames()) {
     if (definitions.getResourceByName(rn).getTemplate() != null)
        b.append("<li><a href=\""+rn.toLowerCase()+".html\">"+rn+"</a></li>\r\n");
    }
    b.append("</ul>\r\n");
    return b.toString();
  }
  
  private String getExtensionsLink(ResourceDefn res) {
    if (res.getRoot().typeCode().equals("DomainResource"))
      return " + <a href=\""+res.getName().toLowerCase()+"-profiles.html\">see the extensions</a>";
    else
      return "";
  }

 
}
