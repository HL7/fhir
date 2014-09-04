package org.hl7.fhir.tools.publisher;

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
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.lang.model.util.ElementFilter;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.generators.specification.DataTypeTableGenerator;
import org.hl7.fhir.definitions.generators.specification.DictHTMLGenerator;
import org.hl7.fhir.definitions.generators.specification.MappingsGenerator;
import org.hl7.fhir.definitions.generators.specification.ProfileGenerator;
import org.hl7.fhir.definitions.generators.specification.ResourceTableGenerator;
import org.hl7.fhir.definitions.generators.specification.ReviewSpreadsheetGenerator;
import org.hl7.fhir.definitions.generators.specification.SchematronGenerator;
import org.hl7.fhir.definitions.generators.specification.SvgGenerator;
import org.hl7.fhir.definitions.generators.specification.TerminologyNotesGenerator;
import org.hl7.fhir.definitions.generators.specification.XPathQueryGenerator;
import org.hl7.fhir.definitions.generators.specification.XmlSpecGenerator;
import org.hl7.fhir.definitions.generators.xsd.SchemaGenerator;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.Compartment;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.EventDefn;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.Example.ExampleType;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.OperationParameter;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.RegisteredProfile;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameter;
import org.hl7.fhir.definitions.model.SearchParameter.SearchType;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.definitions.parsers.SourceParser;
import org.hl7.fhir.definitions.parsers.SpreadsheetParser;
import org.hl7.fhir.definitions.validation.ConceptMapValidator;
import org.hl7.fhir.definitions.validation.OldProfileValidator;
import org.hl7.fhir.definitions.validation.ResourceValidator;
import org.hl7.fhir.definitions.validation.ValueSetValidator;
import org.hl7.fhir.instance.formats.JsonComposer;
import org.hl7.fhir.instance.formats.ResourceOrFeed;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.OperationDefinition;
import org.hl7.fhir.instance.model.ConceptMap.ConceptEquivalence;
import org.hl7.fhir.instance.model.ConceptMap.ConceptMapElementComponent;
import org.hl7.fhir.instance.model.ConceptMap.ConceptMapElementMapComponent;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Conformance.ConformanceRestComponent;
import org.hl7.fhir.instance.model.Conformance.ConformanceRestResourceComponent;
import org.hl7.fhir.instance.model.Conformance.ConformanceRestResourceSearchParamComponent;
import org.hl7.fhir.instance.model.Conformance.ConformanceStatementStatus;
import org.hl7.fhir.instance.model.Conformance.ResourceInteractionComponent;
import org.hl7.fhir.instance.model.Conformance.RestfulConformanceMode;
import org.hl7.fhir.instance.model.Conformance.SystemInteractionComponent;
import org.hl7.fhir.instance.model.Conformance.SystemRestfulInteraction;
import org.hl7.fhir.instance.model.Conformance.TypeRestfulInteraction;
import org.hl7.fhir.instance.model.Contact.ContactSystem;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.OperationDefinition.OperationDefinitionParameterComponent;
import org.hl7.fhir.instance.model.OperationDefinition.OperationKind;
import org.hl7.fhir.instance.model.OperationDefinition.OperationParameterUse;
import org.hl7.fhir.instance.model.OperationDefinition.ResourceProfileStatus;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Questionnaire;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.instance.model.ValueSet.FilterOperator;
import org.hl7.fhir.instance.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValuesetStatus;
import org.hl7.fhir.instance.utils.LoincToDEConvertor;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.QuestionnaireBuilder;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.instance.utils.Translations;
import org.hl7.fhir.instance.validation.InstanceValidator;
import org.hl7.fhir.instance.validation.ProfileValidator;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.tools.implementations.XMLToolsGenerator;
import org.hl7.fhir.tools.implementations.csharp.CSharpGenerator;
import org.hl7.fhir.tools.implementations.delphi.DelphiGenerator;
import org.hl7.fhir.tools.implementations.emf.EMFGenerator;
import org.hl7.fhir.tools.implementations.java.JavaGenerator;
import org.hl7.fhir.tools.implementations.javascript.JavaScriptGenerator;
import org.hl7.fhir.tools.implementations.objectivec.ObjectiveCGenerator;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.SchemaInputSource;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlDocument;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.NamespaceContextMap;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XhtmlGenerator;
import org.hl7.fhir.utilities.xml.XmlGenerator;
import org.tigris.subversion.javahl.ClientException;
import org.tigris.subversion.javahl.SVNClient;
import org.tigris.subversion.javahl.Status;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * This is the entry point for the publication method for FHIR The general order
 * of publishing is Check that everything we expect to find is found Load the
 * page.getDefinitions() Produce the specification 1. reference implementations
 * 2. schemas 4. final specification Validate the XML
 * 
 * @author Grahame
 * 
 */
public class Publisher implements URIResolver {

  public class Fragment {
    private String type;
    private String xml;
    private String page;

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getXml() {
      return xml;
    }

    public void setXml(String xml) {
      this.xml = xml;
    }

    public String getPage() {
      return page;
    }

    public void setPage(String page) {
      this.page = page;
    }

  }

  public class ExampleReference {
    private String type;
    private String id;
    private String path;

    public ExampleReference(String type, String id, String path) {
      super();
      this.type = type;
      this.id = id;
      this.path = path;
    }

    public String describe() {
      return type + "|" + id;
    }

    public String getType() {
      return type;
    }

    public String getId() {
      return id;
    }

    public String getPath() {
      return path;
    }

  }

  private SourceParser prsr;
  private PageProcessor page;
  // private BookMaker book;

  private JavaGenerator javaReferencePlatform;

  private boolean isGenerate;
  private boolean noArchive;
  private boolean web;
  private String diffProgram;
  private AtomFeed profileFeed;
  private AtomFeed typeFeed;
  private AtomFeed valueSetsFeed;
  private AtomFeed conceptMapsFeed;
  private AtomFeed v2Valuesets;
  private AtomFeed v3Valuesets;
  private List<Fragment> fragments = new ArrayList<Publisher.Fragment>();
  private Map<String, String> xmls = new HashMap<String, String>();
  private Map<String, Long> dates = new HashMap<String, Long>();
  private Map<String, Boolean> buildFlags = new HashMap<String, Boolean>();
  private IniFile cache;
  private WebMaker wm;

  public static void main(String[] args) throws Exception {
    //
   
    Publisher pub = new Publisher();
    pub.page = new PageProcessor();
    pub.isGenerate = !(args.length > 1 && hasParam(args, "-nogen"));
    pub.noArchive = (args.length > 1 && hasParam(args, "-noarchive"));
    pub.web = (args.length > 1 && hasParam(args, "-web"));
    pub.diffProgram = getNamedParam(args, "-diff");
    if (hasParam(args, "-name"))
      pub.page.setPublicationType(getNamedParam(args, "-name"));
    if (hasParam(args, "-url"))
      pub.page.setBaseURL(getNamedParam(args, "-url"));
    if (pub.web) {
      pub.page.setPublicationType("Development Version");
      pub.page.setPublicationNotice(PageProcessor.PUB_NOTICE);
    }
    try {
      String dir = hasParam(args, "-folder") ? getNamedParam(args, "-folder") : System.getProperty("user.dir");
      String igName = hasParam(args, "-ig") ? getNamedParam(args, "-ig") : null;
      
      pub.execute(dir, igName);
    } catch (Exception e) {
      System.out.println("Error running build: " + e.getMessage());
      File f;
      try {
        String errorFile = Utilities.appendSlash(System.getProperty("user.dir")) + "fhir-error-dump.txt";
        f = new File(errorFile);
        PrintStream p = new PrintStream(f);
        e.printStackTrace(p);
        System.out.println("Stack Trace saved as " +  errorFile);
      } catch (IOException e1) {
      }
      if (hasParam(args, "-debug"))
        e.printStackTrace();

      // Error status code set in case of any exception
      System.exit(1);
    }
  }

  /**
   * Invokes the SVN API to find out the current revision number for SVN,
   * returns ????
   * 
   * @param folder
   * @return the revision number, or "????" if SVN was not available
   */
  private static String checkSubversion(String folder) {

    SVNClient svnClient = new SVNClient();
    Status[] status;
    try {
      status = svnClient.status(folder, true, false, true);
      long revNumber = 0;
      for (Status stat : status)
        revNumber = (revNumber < stat.getRevisionNumber()) ? stat.getRevisionNumber() : revNumber;
      return Long.toString(revNumber);
    } catch (ClientException e) {
      System.out.println("Warning @ Unable to read the SVN version number: " + e.getMessage() );
      return "????";
    }

  }

  private static boolean hasParam(String[] args, String param) {
    for (String a : args)
      if (a.equals(param))
        return true;
    return false;
  }

  private static String getNamedParam(String[] args, String param) {
    boolean found = false;
    for (String a : args) {
      if (found)
        return a;
      if (a.equals(param)) {
        found = true;
      }
    }
    return null;
  }

  /**
   * Entry point to the publisher. This classes Java Main() calls this function
   * to actually produce the specification
   * 
   * @param folder
   * @throws Exception
   */
  public void execute(String folder, String igName) throws Exception {
    if (igName != null) {
      page.setIg(ImplementationGuideDetails.loadFromFile(igName));
      page.log("Publish FHIR IG from "+igName+" using FHIR source in folder " + folder + " @ " + Config.DATE_FORMAT().format(page.getGenDate().getTime()), LogMessageType.Process);
    } else {
      page.log("Publish FHIR in folder " + folder + " @ " + Config.DATE_FORMAT().format(page.getGenDate().getTime()), LogMessageType.Process);
      if (web) 
        page.log("Build final copy for HL7 web site", LogMessageType.Process);
      else
        page.log("Build local copy", LogMessageType.Process);
    }
    page.setFolders(new FolderManager(folder));

    if (page.hasIG()) {
      String path = page.getIg().getOutputFolder();
      if (!path.endsWith(File.separator))
        path = path + File.separator;
      page.getFolders().dstDir = path;
    }
    
    if (isGenerate)
      page.setSvnRevision(checkSubversion(folder));
    registerReferencePlatforms();

    if (initialize(folder)) {
      page.log("Version " + page.getVersion() + "-" + page.getSvnRevision(), LogMessageType.Hint);

      cache = new IniFile(page.getFolders().rootDir + "temp" + File.separator + "build.cache");
      loadSuppressedMessages(page.getFolders().rootDir);
      boolean doAny = false;
      for (String n : dates.keySet()) {
        Long d = cache.getLongProperty("dates", n);
        boolean b = d == null || (dates.get(n) > d);
        cache.setLongProperty("dates", n, dates.get(n).longValue(), null);
        buildFlags.put(n.toLowerCase(), b);
        doAny = doAny || b;
      }
      if (!doAny || !(new File(page.getFolders().dstDir + "qa.html").exists()))
        buildFlags.put("all", true); // nothing - build all
      buildFlags.put("all", true); // override partial bild until it can figured out
      cache.save();

      if (!buildFlags.get("all"))
        page.log("Partial Build (if you want a full build, just run the build again)", LogMessageType.Process);
      Utilities.createDirectory(page.getFolders().dstDir);
      Utilities.deleteTempFiles();

      page.getBreadCrumbManager().parse(page.getFolders().srcDir + "heirarchy.xml");
      page.loadSnomed();
      page.loadLoinc();

      prsr.parse(page.getGenDate(), page.getVersion());
      
      if (page.hasIG()) {
        loadIG();
      }
      if (buildFlags.get("all")) {
        copyStaticContent();
      }
      defineSpecialValues();     
      loadValueSets1();


      if (validate()) {
        processProfiles();
        if (page.hasIG()) {
          processIGFiles();
        }
        if (isGenerate) {
          page.log("Clear Directory", LogMessageType.Process);
          if (buildFlags.get("all"))
            Utilities.clearDirectory(page.getFolders().dstDir);
          Utilities.createDirectory(page.getFolders().dstDir + "html");
          Utilities.createDirectory(page.getFolders().dstDir + "examples");
          Utilities.clearDirectory(page.getFolders().rootDir + "temp" + File.separator + "hl7" + File.separator + "web");
          Utilities.clearDirectory(page.getFolders().rootDir + "temp" + File.separator + "hl7" + File.separator + "dload");

          String eCorePath = page.getFolders().dstDir + "ECoreDefinitions.xml";
          generateECore(prsr.getECoreParseResults(), eCorePath);
          produceSpecification(eCorePath);
        } 
        validateXml();
        if (isGenerate && buildFlags.get("all"))
          produceQA();
        page.log("Finished publishing FHIR @ " + Config.DATE_FORMAT().format(Calendar.getInstance().getTime()), LogMessageType.Process);
      } else {
        page.log("Didn't publish FHIR due to errors @ " + Config.DATE_FORMAT().format(Calendar.getInstance().getTime()), LogMessageType.Process);
        throw new Exception("Errors executing build. Details logged.");
      }
    }
  }

  private void loadIG() throws FileNotFoundException, Exception {
    for (String folder : page.getIg().getInputFolders()) {
      File[] files = new File(folder).listFiles();
      if (files != null) {
        for (File f : files) {
          if (f.getName().endsWith(".xml")) {
            ResourceOrFeed rf = new XmlParser().parseGeneral(new FileInputStream(f));
            if (rf.getFeed() != null) {
              for (AtomEntry<? extends org.hl7.fhir.instance.model.Resource> ae : rf.getFeed().getEntryList()) {
                loadIgResource(ae);
              }
            } else {
              AtomEntry<org.hl7.fhir.instance.model.Resource> ae = new AtomEntry<org.hl7.fhir.instance.model.Resource>();
              ae.setId(page.getIg().getBaseUri()+rf.getResource().getResourceType().toString()+"/"+Utilities.fileTitle(f.getAbsolutePath()));
              ae.setResource(rf.getResource());
              ae.setTitle(f.getName());
              ae.getLinks().put("path", page.getIg().getFilePrefix()+f.getName());
              ae.getResource().setTag("filename", ae.getLinks().get("path"));
              loadIgResource(ae);
            }
          }
        }
      }
    }
  }

  
  private void processIGFiles() throws Exception {
    page.log("Processing IG Examples", LogMessageType.Process);
    List<Profile> profiles = listProfiles(page.getIgResources());
    Map<Profile, List<Resource>> examples = new HashMap<Profile, List<Resource>>();
    for (Profile p : profiles)
      examples.put(p, new ArrayList<Resource>());
      
    // anything that's not a profile, we're going to check it against any profiles we have 
//    for (AtomEntry<? extends org.hl7.fhir.instance.model.Resource> ae : page.getIgResources().values()) {
//      org.hl7.fhir.instance.model.Resource r = ae.getResource();
//      for (Profile p : profiles) {
//        if (new InstanceValidator(page.getWorkerContext()).isValid(p, r)) {
//          List<Resource> ex = examples.get(p);
//          ex.add(r);
//        }
//      }
//    }
  }

  @SuppressWarnings("unchecked")
  private List<Profile> listProfiles(Map<String, AtomEntry<? extends Resource>> igResources) throws Exception {
    List<Profile> list = new ArrayList<Profile>();
    for (AtomEntry<? extends Resource> ae : igResources.values()) 
      if (ae.getResource() instanceof Profile) {
        processProfile((AtomEntry<Profile>) ae);
        list.add((Profile) ae.getResource());
      }
    return list;
  }

  @SuppressWarnings("unchecked")
  private void loadIgResource(AtomEntry<? extends org.hl7.fhir.instance.model.Resource> ae) {
    page.getIgResources().put(ae.getId(), ae);
    if (ae.getResource() instanceof ValueSet) {
      ValueSet vs = (ValueSet) ae.getResource();
      if (vs.getDefine() != null)
        page.getCodeSystems().put(vs.getDefine().getSystemSimple(), (AtomEntry<ValueSet>) ae);
      page.getValueSets().put(ae.getId(), (AtomEntry<ValueSet>) ae);
    }
    if (ae.getResource() instanceof ConceptMap) 
      page.getConceptMaps().put(ae.getId(), (AtomEntry<ConceptMap>) ae);
    
    if (ae.getResource() instanceof Profile) 
      page.getProfiles().put(ae.getId(), (AtomEntry<Profile>) ae);
  }

  @SuppressWarnings("unchecked")
  private void processProfiles() throws Exception {
    page.log(" ...process profiles", LogMessageType.Process);
    // first, for each type and resource, we build it's master profile
    for (DefinedCode t : page.getDefinitions().getPrimitives().values()) {
      if (t instanceof PrimitiveType)
        genPrimitiveTypeProfile((PrimitiveType) t);
      else
        genPrimitiveTypeProfile((DefinedStringPattern) t);
    }
    for (TypeDefn t : page.getDefinitions().getTypes().values())
      genTypeProfile(t);
    for (TypeDefn t : page.getDefinitions().getStructures().values())
      genTypeProfile(t);
    for (TypeDefn t : page.getDefinitions().getInfrastructure().values())
      genTypeProfile(t);
    
    for (ResourceDefn r : page.getDefinitions().getResources().values()) { 
      r.setProfile(new ProfileGenerator(page.getDefinitions(), page.getWorkerContext()).generate(r, page.getGenDate()));
      ResourceTableGenerator rtg = new ResourceTableGenerator(page.getFolders().dstDir, page, null, true);
      r.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
      r.getProfile().getText().getDiv().getChildNodes().add(rtg.generate(r.getRoot()));
    }
    
    for (ProfiledType pt : page.getDefinitions().getConstraints().values()) {
      genProfiledTypeProfile(pt);
    }
    
    if (page.hasIG()) {
      for (AtomEntry<? extends Resource> rd : page.getIgResources().values()) {
        if (rd.getResource() instanceof Profile) {
          ProfileDefn pd = new ProfileDefn();
          pd.setSource((Profile) rd.getResource());
          page.getDefinitions().getProfiles().put(pd.getSource().getUrlSimple(), pd);
        }
      }
    }
    // we have profiles scoped by resources, and stand alone profiles
    for (ProfileDefn p : page.getDefinitions().getProfiles().values())
      processProfile(p, p.metadata("id"));
    for (ResourceDefn r : page.getDefinitions().getResources().values())       
      for (RegisteredProfile p : r.getProfiles())      
        processProfile(p.getProfile(), p.getDestFilenameNoExt());
                                       
    // now, validate the profiles
    for (ProfileDefn p : page.getDefinitions().getProfiles().values())
      validateProfile(p);
    for (ResourceDefn r : page.getDefinitions().getResources().values())       
      for (RegisteredProfile p : r.getProfiles())      
        validateProfile(p.getProfile());
    if (page.hasIG()) 
      for (AtomEntry<? extends Resource> rd : page.getIgResources().values()) {
        if (rd.getResource() instanceof Profile) {
          validateProfile((AtomEntry<Profile>) rd);
        }
      } 
  }

  private void validateProfile(AtomEntry<Profile> rd) throws Exception {
    ProfileValidator pv = new ProfileValidator();
    pv.setProfiles(page.getProfiles());
    List<String> errors = pv.validate(rd.getResource());
    if (errors.size() > 0) {
      for (String e : errors)
        page.log(e, LogMessageType.Error);
      throw new Exception("Error validating " + rd.getTitle());
    }
  }

  private void validateProfile(ProfileDefn p) throws Exception {
    ProfileValidator pv = new ProfileValidator();
    pv.setProfiles(page.getProfiles());
    List<String> errors = pv.validate(p.getSource());
    if (errors.size() > 0) {
      for (String e : errors)
        page.log(e, LogMessageType.Error);
      throw new Exception("Error validating " + p.metadata("name"));
    }
  }

  private void genProfiledTypeProfile(ProfiledType pt) throws Exception {
    Profile profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext()).generate(pt, page.getGenDate());
    page.getProfiles().put(profile.getUrlSimple(), genWrapper(profile));
    pt.setProfile(profile);
    // todo: what to do in the narrative?
  }

  private AtomEntry<Profile> genWrapper(Profile profile) {
    AtomEntry<Profile> e = new AtomEntry<Profile>();
    e.setId(profile.getUrlSimple());
    e.setResource(profile);
    return e;
  }

  private void genPrimitiveTypeProfile(PrimitiveType t) throws Exception {
    Profile profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext()).generate(t, page.getGenDate());
    page.getProfiles().put(profile.getUrlSimple(), genWrapper(profile));
//    DataTypeTableGenerator dtg = new DataTypeTableGenerator(page.getFolders().dstDir, page, t.getCode(), true);
//    t.setProfile(profile);
//    t.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
//    t.getProfile().getText().getDiv().getChildNodes().add(dtg.generate(t));
  }


  private void genPrimitiveTypeProfile(DefinedStringPattern t) throws Exception {
    Profile profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext()).generate(t, page.getGenDate());
    page.getProfiles().put(profile.getUrlSimple(), genWrapper(profile));
//    DataTypeTableGenerator dtg = new DataTypeTableGenerator(page.getFolders().dstDir, page, t.getCode(), true);
//    t.setProfile(profile);
//    t.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
//    t.getProfile().getText().getDiv().getChildNodes().add(dtg.generate(t));
  }


  private void genTypeProfile(TypeDefn t) throws Exception {
    Profile profile = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext()).generate(t, page.getGenDate());
    page.getProfiles().put(profile.getUrlSimple(), genWrapper(profile));
    t.setProfile(profile);
    DataTypeTableGenerator dtg = new DataTypeTableGenerator(page.getFolders().dstDir, page, t.getName(), true);
    t.getProfile().getText().setDiv(new XhtmlNode(NodeType.Element, "div"));
    t.getProfile().getText().getDiv().getChildNodes().add(dtg.generate(t));
  }

  private void processProfile(ProfileDefn profile, String filename) throws Exception {
    // they've either been loaded from spreadsheets, or from profile declarations
    // what we're going to do:
    //  create Profile structures if needed (create differential definitions from spreadsheets)
    if (profile.getSource() == null) {
      Profile p = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext()).generate(profile, profile.metadata("id"), page.getGenDate());
      profile.setSource(p);
      page.getProfiles().put(p.getUrlSimple(), genWrapper(p));
    } else {
      // special case: if the profile itself doesn't claim a date, it's date is the date of this publication
      if (profile.getSource().getDate() == null)
        profile.getSource().setDateSimple(new DateAndTime(page.getGenDate()));
      for (ProfileStructureComponent c : profile.getSource().getStructure()) {
        if (c.getBase() != null && !hasSnapshot(profile.getSource(), c)) {
          // cause it probably doesn't, coming from the profile directly
          ProfileStructureComponent base = getSnapShotForProfile(c.getBaseSimple());
          new ProfileUtilities(page.getWorkerContext()).generateSnapshot(base, c, c.getBaseSimple().split("#")[0], profile.getSource().getNameSimple());
        }
        page.getProfiles().put(profile.getSource().getUrlSimple(), genWrapper(profile.getSource()));
      }
    }
    if (!Utilities.noString(filename))
      profile.getSource().setTag("filename", filename);
  }

  public ProfileStructureComponent getSnapShotForProfile(String base) throws Exception {
    String[] parts = base.split("#");
    if (parts[0].startsWith("http://hl7.org/fhir/Profile/") && parts.length == 1) {
      String name = base.substring(28);
      if (page.getDefinitions().hasResource(name)) 
        return page.getDefinitions().getSnapShotForType(name);
      else if (page.getDefinitions().hasType(name)) {
        TypeDefn t = page.getDefinitions().getElementDefn(name);
        for (ProfileStructureComponent s : t.getProfile().getStructure())
          if (s.getSnapshot() != null)
            return s;
        throw new Exception("unable to find snapshot for "+name);
      } else
        throw new Exception("unable to find base definition for "+name);
    }
    Profile p = page.getDefinitions().getProfileByURL(parts[0]);
    if (p == null)
      throw new Exception("unable to find base definition for "+base);
    if (parts.length == 1) {
      if (p.getStructure().size() != 1)
        throw new Exception("Profile "+base+" has multiple structures");
      if (p.getStructure().get(0).getSnapshot() == null)
        throw new Exception("Profile "+base+" has no snapshot"); // or else we could fill it in? 
      return p.getStructure().get(0);
    }
    for (ProfileStructureComponent s : p.getStructure()) {
      if (s.getSnapshot() == null && p.getNameSimple().equals(parts[1])) {
        ProfileDefn profile = page.getDefinitions().getProfiles().get(parts[0]);
        processProfile(profile, null);
      }
      if (s.getSnapshot() != null && p.getNameSimple().equals(parts[1]))
        return s;
    }
    throw new Exception("Unable to find snapshot for "+base);
  }

  
  private void processProfile(AtomEntry<Profile> ae) throws Exception {
    if (ae.getResource().getDate() == null)
      ae.getResource().setDateSimple(new DateAndTime(page.getGenDate()));
    for (ProfileStructureComponent c : ae.getResource().getStructure()) {
      if (c.getBase() != null && !hasSnapshot(ae.getResource(), c)) {
        // cause it probably doesn't, coming from the profile directly
        ProfileStructureComponent base;
        base = getIgProfile(c.getBaseSimple());
        if (base == null)
          base = page.getDefinitions().getSnapShotForProfile(c.getBaseSimple());
        new ProfileUtilities(page.getWorkerContext()).generateSnapshot(base, c, c.getBaseSimple().split("#")[0], ae.getResource().getNameSimple());
      }
      page.getProfiles().put(ae.getResource().getUrlSimple(), ae);
    }
  }
  public ProfileStructureComponent getIgProfile(String base) throws Exception {
    String[] parts = base.split("#");
    AtomEntry<Profile> p = getIGProfileByURL(parts[0]);
    if (p == null)
      return null;
    
    processProfile(p); // this is recursive, but will terminate at the root
    if (parts.length == 1) {
      if (p.getResource().getStructure().size() != 1)
        throw new Exception("Profile "+base+" has multiple structures");
      if (p.getResource().getStructure().get(0).getSnapshot() == null)
        throw new Exception("Profile "+base+" has no snapshot"); // or else we could fill it in? 
      return p.getResource().getStructure().get(0);
    }
    for (ProfileStructureComponent s : p.getResource().getStructure())
      if (s.getSnapshot() != null && p.getResource().getNameSimple().equals(parts[1]))
        return s;
    throw new Exception("Unable to find snapshot for "+base);
  }

  @SuppressWarnings("unchecked")
  private AtomEntry<Profile> getIGProfileByURL(String url) {
    if (url.contains("#"))
      url = url.substring(0, url.indexOf('#'));
    for (AtomEntry<? extends Resource> ae : page.getIgResources().values()) {
      if (ae.getResource() instanceof Profile) {
        Profile p = (Profile) ae.getResource();
        if (p.getUrlSimple().equals(url))
          return (AtomEntry<Profile>) ae;
      }
    }
    return null;
  }

  private boolean hasSnapshot(Profile source, ProfileStructureComponent c) {
    return c.getSnapshot() != null;
  }

  private void loadSuppressedMessages(String rootDir) throws Exception {
    InputStreamReader r = new InputStreamReader(new FileInputStream(rootDir + "suppressed-messages.txt"));
    StringBuilder b = new StringBuilder();
    while (r.ready()) {
      char c = (char) r.read();
      if (c == '\r' || c == '\n') {
        if (b.length() > 0)
          page.getSuppressedMessages().add(b.toString());
        b = new StringBuilder();
      } else
        b.append(c);
    }
    r.close();
  }

  private void loadValueSets1() throws Exception {
    buildFeedsAndMaps();

    page.log(" ...vocab #1", LogMessageType.Process);
    analyseV2();
    analyseV3();
    if (isGenerate) {
      generateConformanceStatement(true, "base");
      generateConformanceStatement(false, "base2");
    }
    generateCodeSystemsPart1();
    generateValueSetsPart1();
    if (page.hasIG())
      generateIGValueSetsPart1();
    for (BindingSpecification cd : page.getDefinitions().getBindings().values()) {
      if (cd.getBinding() == Binding.ValueSet && !Utilities.noString(cd.getReference()) && cd.getReference().startsWith("http://hl7.org/fhir")) {
        if (!page.getDefinitions().getValuesets().containsKey(cd.getReference()))
          throw new Exception("Reference " + cd.getReference() + " canot be resolved");
        cd.setReferredValueSet(page.getDefinitions().getValuesets().get(cd.getReference()));
      }
    }
  }

  private void loadValueSets2() throws Exception {
    page.log(" ...vocab #2", LogMessageType.Process);
    generateCodeSystemsPart2();
    generateValueSetsPart2();
    if (page.hasIG())
      generateIGValueSetsPart2();
    page.saveSnomed();
    page.log(" ...resource ValueSet", LogMessageType.Process);
    ResourceDefn r = page.getDefinitions().getResources().get("ValueSet");
    if (isGenerate) {
      produceResource1(r);
      produceResource2(r);
    }
    if (isGenerate) {
      /// regenerate. TODO: this is silly - need to generate before so that xpaths are populated. but need to generate now to fill them properly
      generateConformanceStatement(true, "base");
      generateConformanceStatement(false, "base2");
    }
    
  }
  private void buildFeedsAndMaps() {
    profileFeed = new AtomFeed();
    profileFeed.setId("http://hl7.org/fhir/profile/resources");
    profileFeed.setTitle("Resources as Profiles");
    profileFeed.getLinks().put("self", "http://hl7.org/implement/standards/fhir/profiles-resources.xml");
    profileFeed.setUpdated(DateAndTime.now());
    typeFeed = new AtomFeed();
    typeFeed.setId("http://hl7.org/fhir/profile/types");
    typeFeed.setTitle("Resources as Profiles");
    typeFeed.getLinks().put("self", "http://hl7.org/implement/standards/fhir/profiles-types.xml");
    typeFeed.setUpdated(profileFeed.getUpdated());

    valueSetsFeed = new AtomFeed();
    valueSetsFeed.setId("http://hl7.org/fhir/profile/valuesets");
    valueSetsFeed.setTitle("FHIR Core Valuesets");
    valueSetsFeed.getLinks().put("self", "http://hl7.org/implement/standards/fhir/valuesets.xml");
    valueSetsFeed.setUpdated(profileFeed.getUpdated());

    conceptMapsFeed = new AtomFeed();
    conceptMapsFeed.setId("http://hl7.org/fhir/profile/conceptmaps");
    conceptMapsFeed.setTitle("FHIR Core Concept Maps");
    conceptMapsFeed.getLinks().put("self", "http://hl7.org/implement/standards/fhir/conceptmaps.xml");
    conceptMapsFeed.setUpdated(profileFeed.getUpdated());

    v2Valuesets = new AtomFeed();
    v2Valuesets.setId("http://hl7.org/fhir/v2/valuesets");
    v2Valuesets.setTitle("v2 tables as ValueSets");
    v2Valuesets.getLinks().put("self", "http://hl7.org/implement/standards/fhir/v2-tables.xml");
    v2Valuesets.setUpdated(DateAndTime.now());
    page.setV2Valuesets(v2Valuesets);
    v3Valuesets = new AtomFeed();
    v3Valuesets.setId("http://hl7.org/fhir/v3/valuesets");
    v3Valuesets.setTitle("v3 Code Systems and ValueSets");
    v3Valuesets.getLinks().put("self", "http://hl7.org/implement/standards/fhir/v3-valuesets.xml");
    v3Valuesets.setUpdated(DateAndTime.now());
    page.setv3Valuesets(v3Valuesets);
  }

  private void generateConformanceStatement(boolean full, String name) throws Exception {
    Conformance conf = new Conformance();
    conf.setIdentifierSimple("http://hl7.org/fhir/conformance-" + name);
    conf.setVersionSimple(page.getVersion() + "-" + page.getSvnRevision());
    conf.setNameSimple("Base FHIR Conformance Statement " + (full ? "(Full)" : "(Empty)"));
    conf.setPublisherSimple("FHIR Project Team");
    conf.getTelecom().add(Factory.newContact(ContactSystem.url, "http://hl7.org/fhir"));
    conf.setStatusSimple(ConformanceStatementStatus.draft);
    conf.setDateSimple(new DateAndTime(page.getGenDate().getTime()));
    conf.setFhirVersionSimple(page.getVersion());
    conf.setAcceptUnknownSimple(false);
    conf.getFormat().add(Factory.newCode("xml"));
    conf.getFormat().add(Factory.newCode("json"));
    ConformanceRestComponent rest = new Conformance.ConformanceRestComponent();
    conf.getRest().add(rest);
    rest.setModeSimple(RestfulConformanceMode.server);
    if (full) {
      rest.setDocumentationSimple("All the functionality defined in FHIR");
      conf.setDescriptionSimple("This is the base conformance statement for FHIR. It represents a server that provides the full set of functionality defined by FHIR. It is provided to use as a template for system designers to build their own conformance statements from");
    } else {
      rest.setDocumentationSimple("An empty conformance statement");
      conf.setDescriptionSimple("This is the base conformance statement for FHIR. It represents a server that provides the none of the functionality defined by FHIR. It is provided to use as a template for system designers to build their own conformance statements from. A conformance profile has to contain something, so this contains a read of a Conformance Statement");
    }
    if (full) {
      genConfOp(conf, rest, SystemRestfulInteraction.transaction);
      genConfOp(conf, rest, SystemRestfulInteraction.historysystem);
      genConfOp(conf, rest, SystemRestfulInteraction.searchsystem);

      for (String rn : page.getDefinitions().sortedResourceNames()) {
        ResourceDefn rd = page.getDefinitions().getResourceByName(rn);
        ConformanceRestResourceComponent res = new Conformance.ConformanceRestResourceComponent();
        rest.getResource().add(res);
        res.setTypeSimple(rn);
        res.setProfile(Factory.makeResourceReference("http://hl7.org/fhir/" + rn));
        genConfOp(conf, res, TypeRestfulInteraction.read);
        genConfOp(conf, res, TypeRestfulInteraction.vread);
        genConfOp(conf, res, TypeRestfulInteraction.update);
        genConfOp(conf, res, TypeRestfulInteraction.delete);
        genConfOp(conf, res, TypeRestfulInteraction.historyinstance);
        genConfOp(conf, res, TypeRestfulInteraction.validate);
        genConfOp(conf, res, TypeRestfulInteraction.historytype);
        genConfOp(conf, res, TypeRestfulInteraction.create);
        genConfOp(conf, res, TypeRestfulInteraction.searchtype);

        for (SearchParameter i : rd.getSearchParams().values()) {
          res.getSearchParam().add(makeSearchParam(conf, rn, i));
        }
      }
    } else {
      ConformanceRestResourceComponent res = new Conformance.ConformanceRestResourceComponent();
      rest.getResource().add(res);
      res.setTypeSimple("Conformance");
      genConfOp(conf, res, TypeRestfulInteraction.read);
    }

    NarrativeGenerator gen = new NarrativeGenerator("", page.getWorkerContext());
    gen.generate(conf);
    new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + "conformance-" + name + ".xml"), conf, true, true);
    cloneToXhtml("conformance-" + name + "", "Basic Conformance Statement", true, "resource-instance:Conformance");
    new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + "conformance-" + name + ".json"), conf, true);

    jsonToXhtml("conformance-" + name, "Base Conformance Statement", resource2Json(conf), "resource-instance:Conformance");

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + "conformance-" + name + ".xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator
        + "conformance-" + name + ".xml"));
    if (buildFlags.get("all"))
      addToResourceFeed(conf, "conformance-" + name + "", profileFeed);
  }

  private ConformanceRestResourceSearchParamComponent makeSearchParam(Conformance p, String rn, SearchParameter i) {
    ConformanceRestResourceSearchParamComponent result = new Conformance.ConformanceRestResourceSearchParamComponent();
    result.setNameSimple(i.getCode());
    result.setDefinitionSimple("http://hl7.org/fhir/Profile/" + rn);
    result.setTypeSimple(getSearchParamType(i.getType()));
    result.setDocumentation(Factory.newString_(i.getDescription()));
    i.setXPath(new XPathQueryGenerator(page.getDefinitions(), page, page.getQa()).generateXpath(i.getPaths())); // used elsewhere later
    return result;
  }

  private Conformance.SearchParamType getSearchParamType(SearchType type) {
    switch (type) {
    case number:
      return Conformance.SearchParamType.number;
    case string:
      return Conformance.SearchParamType.string;
    case date:
      return Conformance.SearchParamType.date;
    case reference:
      return Conformance.SearchParamType.reference;
    case token:
      return Conformance.SearchParamType.token;
    case composite:
      return Conformance.SearchParamType.composite;
    case quantity:
      return Conformance.SearchParamType.quantity;
    }
    return null;
  }

  private void genConfOp(Conformance conf, ConformanceRestResourceComponent res, TypeRestfulInteraction op) {
    ResourceInteractionComponent t = new ResourceInteractionComponent();
    t.setCodeSimple(op);
    res.getInteraction().add(t);
  }

  private void genConfOp(Conformance conf, ConformanceRestComponent res, SystemRestfulInteraction op) {
    SystemInteractionComponent t = new SystemInteractionComponent();
    t.setCodeSimple(op);
    res.getInteraction().add(t);
  }

  private IniFile ini;

  private void defineSpecialValues() throws Exception {
    for (BindingSpecification bs : page.getDefinitions().getBindings().values()) {
      if (bs.getBinding() == Binding.Special) {
        if (bs.getName().equals("DataType") || bs.getName().equals("FHIRDefinedType")) {
          List<String> codes = new ArrayList<String>();
          for (TypeRef t : page.getDefinitions().getKnownTypes())
            codes.add(t.getName());
          Collections.sort(codes);
          for (String s : codes) {
            if (!page.getDefinitions().dataTypeIsSharedInfo(s)) {
              DefinedCode c = new DefinedCode();
              c.setCode(s);
              c.setId(getCodeId("datatype", s));
              if (page.getDefinitions().getPrimitives().containsKey(s))
                c.setDefinition(page.getDefinitions().getPrimitives().get(s).getDefinition());
              else if (page.getDefinitions().getConstraints().containsKey(s))
                c.setDefinition(page.getDefinitions().getConstraints().get(s).getDefinition());
              else if (page.getDefinitions().getElementDefn(s) != null)
                c.setDefinition(page.getDefinitions().getElementDefn(s).getDefinition());
              bs.getCodes().add(c);
            }
          }
        }
        if (bs.getName().equals("ResourceType") || bs.getName().equals("FHIRDefinedType")) {
          List<String> codes = new ArrayList<String>();
          codes.addAll(page.getDefinitions().getKnownResources().keySet());
          Collections.sort(codes);
          for (String s : codes) {
            DefinedCode c = page.getDefinitions().getKnownResources().get(s);
            c.setId(getCodeId("resourcetype", s));
            bs.getCodes().add(c);
          }
        }
        if (bs.getName().equals("MessageEvent")) {
          List<String> codes = new ArrayList<String>();
          codes.addAll(page.getDefinitions().getEvents().keySet());
          Collections.sort(codes);
          for (String s : codes) {
            DefinedCode c = new DefinedCode();
            EventDefn e = page.getDefinitions().getEvents().get(s);
            c.setCode(s);
            c.setId(getCodeId("messageevent", s));
            c.setDefinition(e.getDefinition());
            bs.getCodes().add(c);
          }
        }
        if (!(bs.getName().equals("DataType") || bs.getName().equals("FHIRDefinedType") || bs.getName().equals("ResourceType") || bs.getName().equals(
            "MessageEvent")))
          page.log("unprocessed special type " + bs.getName(), LogMessageType.Error);
      }
    }
    prsr.getRegistry().commit();
  }

  private String getCodeId(String q, String name) {
    return prsr.getRegistry().idForQName(q, name);
  }

  private void generateECore(org.hl7.fhir.definitions.ecore.fhir.Definitions eCoreDefinitions, String filename) throws IOException {
    org.eclipse.emf.ecore.xmi.XMLResource resource = new XMLResourceImpl();
    Map<String, String> options = new HashMap<String, String>();
    options.put(org.eclipse.emf.ecore.xmi.XMLResource.OPTION_ENCODING, "UTF-8");
    options.put(org.eclipse.emf.ecore.xmi.XMLResource.OPTION_XML_VERSION, "1.0");

    resource.getContents().add(eCoreDefinitions);
    resource.save(new FileOutputStream(filename), options);
  }

  private void registerReferencePlatforms() {
    javaReferencePlatform = new JavaGenerator(page.getFolders());
    page.getReferenceImplementations().add(javaReferencePlatform);
    page.getReferenceImplementations().add(new CSharpGenerator());
    page.getReferenceImplementations().add(new DelphiGenerator());
    page.getReferenceImplementations().add(new ObjectiveCGenerator());
    page.getReferenceImplementations().add(new XMLToolsGenerator());
    page.getReferenceImplementations().add(new JavaScriptGenerator());
    page.getReferenceImplementations().add(new EMFGenerator());
    
    // page.getReferenceImplementations().add(new ECoreOclGenerator());
  }

  public boolean checkFile(String purpose, String dir, String file, List<String> errors, String category) throws IOException {
    CSFile f = new CSFile(dir + file);
    if (file.contains("*"))
      return true;
    
    if (!f.exists()) {
      errors.add("Unable to find " + purpose + " file " + file + " in " + dir);
      return false;
    } else if (category != null) {
      long d = f.lastModified();
      if (!dates.containsKey(category) || d > dates.get(category))
        dates.put(category, d);
      return true;
    } else
      return true;
  }

  private boolean initialize(String folder) throws Exception {
    page.setDefinitions(new Definitions());

    page.log("Checking Source for " + folder, LogMessageType.Process);

    List<String> errors = new ArrayList<String>();

    Utilities.checkFolder(page.getFolders().rootDir, errors);
    if (checkFile("required", page.getFolders().rootDir, "publish.ini", errors, "all")) {
      checkFile("required", page.getFolders().srcDir, "navigation.xml", errors, "all");
      page.setIni(new IniFile(page.getFolders().rootDir + "publish.ini"));
      page.setVersion(page.getIni().getStringProperty("FHIR", "version"));

      prsr = new SourceParser(page, folder, page.getDefinitions(), web);
      prsr.checkConditions(errors, dates);
      page.setRegistry(prsr.getRegistry());

      Utilities.checkFolder(page.getFolders().xsdDir, errors);
      for (PlatformGenerator gen : page.getReferenceImplementations())
        Utilities.checkFolder(page.getFolders().implDir(gen.getName()), errors);
      checkFile("required", page.getFolders().srcDir, "heirarchy.xml", errors, "all");
      checkFile("required", page.getFolders().srcDir, "fhir-all.xsd", errors, "all");
      checkFile("required", page.getFolders().srcDir, "header.html", errors, "all");
      checkFile("required", page.getFolders().srcDir, "footer.html", errors, "all");
      checkFile("required", page.getFolders().srcDir, "template.html", errors, "all");
      checkFile("required", page.getFolders().srcDir, "template-book.html", errors, "all");
      checkFile("required", page.getFolders().srcDir, "mappingSpaces.xml", errors, "all");
      // Utilities.checkFolder(page.getFolders().dstDir, errors);

      if (page.getIni().getPropertyNames("support") != null)
        for (String n : page.getIni().getPropertyNames("support"))
          checkFile("support", page.getFolders().srcDir, n, errors, "all");
      for (String n : page.getIni().getPropertyNames("images"))
        checkFile("image", page.getFolders().imgDir, n, errors, "all");
      for (String n : page.getIni().getPropertyNames("schema"))
        checkFile("schema", page.getFolders().srcDir, n, errors, "all");
      for (String n : page.getIni().getPropertyNames("pages"))
        checkFile("page", page.getFolders().srcDir, n, errors, "page-" + n);
      for (String n : page.getIni().getPropertyNames("files"))
        checkFile("file", page.getFolders().rootDir, n, errors, "page-" + n);
    }
    if (checkFile("translations", page.getFolders().rootDir + "implementations" + File.separator, "translations.xml", errors, null)) {
      // schema check
      checkBySchema(page.getFolders().rootDir + "implementations" + File.separator + "translations.xml", new String[] {page.getFolders().rootDir + "implementations" + File.separator + "translations.xsd"});
      Utilities.copyFile(page.getFolders().rootDir + "implementations" + File.separator + "translations.xml", page.getFolders().dstDir + "translations.xml");
      page.getTranslations().setLang("en");
      page.getTranslations().load(page.getFolders().rootDir + "implementations" + File.separator + "translations.xml");
    }

    if (errors.size() > 0)
      page.log("Unable to publish FHIR specification:", LogMessageType.Error);
    for (String e : errors) {
      page.log(e, LogMessageType.Error);
    }
    return errors.size() == 0;
  }

  private boolean validate() throws Exception {
    page.log("Validating", LogMessageType.Process);
    ResourceValidator val = new ResourceValidator(page.getDefinitions(), page.getTranslations(), page.getCodeSystems());

    List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    for (String n : page.getDefinitions().getTypes().keySet())
      errors.addAll(val.checkStucture(n, page.getDefinitions().getTypes().get(n)));
    for (String n : page.getDefinitions().getStructures().keySet())
      errors.addAll(val.checkStucture(n, page.getDefinitions().getStructures().get(n)));
    for (String n : page.getDefinitions().sortedResourceNames())
      if (hasBuildFlag("page-" + n.toLowerCase()))
        errors.addAll(val.check(n, page.getDefinitions().getResources().get(n)));
    if (hasBuildFlag("all"))
      for (String n : page.getDefinitions().getBindings().keySet())
        errors.addAll(val.check(n, page.getDefinitions().getBindingByName(n)));
    errors.addAll(val.checkBindings(page.getDefinitions().getBindings()));

    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname);
      checkExampleLinks(errors, r);
    }
    val.report();
    // val.dumpParams();
    int hintCount = 0;
    int warningCount = 0;
    for (ValidationMessage e : errors) {
      if (e.getLevel() == IssueSeverity.information) {
        page.log(e.summary(), LogMessageType.Hint);
        page.getQa().hint(e.summary());
        hintCount++;
      }
    }
    for (ValidationMessage e : errors) {
      if (e.getLevel() == IssueSeverity.warning) {
        page.log(e.summary(), LogMessageType.Warning);
        page.getQa().warning(e.summary());
        warningCount++;
      }
    }
    int errorCount = 0;
    for (ValidationMessage e : errors) {
      if (e.getLevel() == IssueSeverity.error || e.getLevel() == IssueSeverity.fatal) {
        page.log(e.summary(), LogMessageType.Error);
        errorCount++;
      }
    }
    page.log("Errors: " + Integer.toString(errorCount) + ". Warnings: " + Integer.toString(warningCount) + ". Hints: " + Integer.toString(hintCount),
        LogMessageType.Process);

    return errorCount == 0;
  }

  private boolean hasBuildFlag(String n) {
    return (buildFlags.containsKey("all") && buildFlags.get("all")) || (buildFlags.containsKey(n) && buildFlags.get(n));
  }

  private boolean wantBuild(String rname) {
    rname = rname.toLowerCase();
    return buildFlags.get("all") || (!buildFlags.containsKey(rname) || buildFlags.get(rname));
  }

  private void checkExampleLinks(List<ValidationMessage> errors, ResourceDefn r) throws Exception {
    for (Example e : r.getExamples()) {
      try {
        if (e.getXml() != null) {
          List<ExampleReference> refs = new ArrayList<ExampleReference>();
          listLinks(e.getXml().getDocumentElement(), refs);
          for (ExampleReference ref : refs) {
            if (!ref.getId().startsWith("cid:") && !ref.getId().startsWith("urn:") && !ref.getId().startsWith("http:") && !resolveLink(ref)) {
              errors.add(new ValidationMessage(Source.ExampleValidator, "business-rule", ref.getPath(), "Unable to resolve example reference to "
                  + ref.describe() + " in " + e.getPath() + "\r\n   Possible Ids: " + listTargetIds(ref.getType()), IssueSeverity.error));
            }
          }
        }
      } catch (Exception ex) {
        throw new Exception("Error checking example " + e.getFileTitle() + ":" + ex.getMessage(), ex);
      }
    }
  }

  private String listTargetIds(String type) throws Exception {
    StringBuilder b = new StringBuilder();
    ResourceDefn r = page.getDefinitions().getResourceByName(type);
    if (r != null) {
      for (Example e : r.getExamples()) {
        if (!Utilities.noString(e.getId()))
          b.append(e.getId() + ", ");
        if (e.getXml() != null) {
          if (e.getXml().getDocumentElement().getLocalName().equals("feed")) {
            List<Element> entries = new ArrayList<Element>();
            XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", entries);
            for (Element c : entries) {
              String id = XMLUtil.getNamedChild(c, "id").getTextContent();
              if (id.startsWith("http://hl7.org/fhir/") && id.contains("@"))
                b.append(id.substring(id.indexOf("@") + 1) + ", ");
              else
                b.append(id + ", ");
            }
          }
        }
      }
    } else
      b.append("(unknown resource type)");
    return b.toString();
  }

  private boolean resolveLink(ExampleReference ref) throws Exception {
    if (ref.getId().startsWith("#"))
      return true;
    if (!page.getDefinitions().hasResource(ref.getType()))
      return false;
    ResourceDefn r = page.getDefinitions().getResourceByName(ref.getType());
    for (Example e : r.getExamples()) {
      if (!ref.getId().startsWith("#")) {
        String id = extractId(ref.getId(), ref.getType());
        if (id.equals(e.getId()))
          return true;
        if (e.getXml() != null) {
          if (e.getXml().getDocumentElement().getLocalName().equals("feed")) {
            List<Element> entries = new ArrayList<Element>();
            XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", entries);
            for (Element c : entries) {
              String _id = XMLUtil.getNamedChild(c, "id").getTextContent();
              if (id.equals(_id) || _id.equals("http://hl7.org/fhir/" + ref.getType() + "/" + id))
                return true;
            }
          }
        }
      }
    }
    return false;
  }

  private String extractId(String id, String type) throws Exception {
    String[] parts = id.split("/");
    if (parts.length < 2)
      throw new Exception("The example reference '" + id + "' is not valid (not enough path parts");
    if (!parts[0].equals(type))
      throw new Exception("The example reference '" + id + "' is not valid (the type portion doesn't match the specified type '" + type + "')");
    if (parts[1].startsWith("@"))
      throw new Exception("The example reference '" + id + "' is not valid (the id shouldn't start with @)");
    if (parts[1].length() < 1 || parts[1].length() > 36)
      throw new Exception("The example reference '" + id + "' is not valid (id length 1 - 36)");
    if (!parts[1].matches("[a-z0-9\\-\\.]{1,36}"))
      throw new Exception("The example reference '" + id + "' is not valid (id doesn't match regular expression for id)");
    if (parts.length > 2) {
      if (!parts[2].equals("history"))
        throw new Exception("The example reference '" + id + "' is not valid");
      if (parts.length != 4 || parts[3].startsWith("@"))
        throw new Exception("The example reference '" + id + "' is not valid");
      if (parts[3].length() < 1 || parts[3].length() > 36)
        throw new Exception("The example reference '" + id + "' is not valid (version id length 1 - 36)");
      if (!parts[3].matches("[a-z0-9\\-\\.]{1,36}"))
        throw new Exception("The example reference '" + id + "' is not valid (version id doesn't match regular expression for id)");
    }
    return parts[1];
  }

  private void listLinks(Element xml, List<ExampleReference> refs) throws Exception {
    if (xml.getLocalName().equals("feed")) {
      Element n = XMLUtil.getFirstChild(xml);
      while (n != null) {
        if (n.getLocalName().equals("entry")) {
          Element c = XMLUtil.getNamedChild(n, "content");
          listLinks(XMLUtil.getFirstChild(c), refs);
        }
        n = XMLUtil.getNextSibling(n);
      }
    } else {
      String n = xml.getLocalName();
      if (!n.equals("Binary")) {
        ResourceDefn r = page.getDefinitions().getResourceByName(n);
        if (r == null)
          throw new Exception("Unable to find resource definition for " + n);
        List<Element> nodes = new ArrayList<Element>();
        nodes.add(xml);
        listLinks("/f:" + n, r.getRoot(), nodes, refs);

        Element e = XMLUtil.getFirstChild(xml);
        while (e != null) {
          if (e.getNodeName().equals("contained")) {
            listLinks(XMLUtil.getFirstChild(e), refs);
          }
          e = XMLUtil.getNextSibling(e);
        }

      }
    }
  }

  private void listLinks(String path, org.hl7.fhir.definitions.model.ElementDefn d, List<Element> set, List<ExampleReference> refs) throws Exception {
    if (d.typeCode().contains("Resource") && !d.typeCode().equals("Resource")) {
      for (Element m : set) {
        if (XMLUtil.getNamedChild(m, "type") != null && XMLUtil.getNamedChild(m, "reference") != null) {
          refs.add(new ExampleReference(XMLUtil.getNamedChild(m, "type").getAttribute("value"), XMLUtil.getNamedChild(m, "reference").getAttribute("value"),
              path));
        }
      }
    }
    for (org.hl7.fhir.definitions.model.ElementDefn c : d.getElements()) {
      List<Element> cset = new ArrayList<Element>();
      for (Element p : set)
        XMLUtil.getNamedChildren(p, c.getName(), cset);
      listLinks(path + "/f:" + c.getName(), c, cset, refs);
    }
  }

  // private List<Element> xPathQuery(String path, Element e) throws Exception {
  // NamespaceContext context = new NamespaceContextMap("f",
  // "http://hl7.org/fhir", "h", "http://www.w3.org/1999/xhtml", "a", );
  //
  // XPathFactory factory = XPathFactory.newInstance();
  // XPath xpath = factory.newXPath();
  // xpath.setNamespaceContext(context);
  // XPathExpression expression= xpath.compile(path);
  // NodeList resultNodes = (NodeList)expression.evaluate(e,
  // XPathConstants.NODESET);
  // List<Element> result = new ArrayList<Element>();
  // for (int i = 0; i < resultNodes.getLength(); i++) {
  // result.add((Element) resultNodes.item(i));
  // }
  // return result;
  // }

  private void produceSpecification(String eCorePath) throws Exception {
    page.setNavigation(new Navigation());
    page.getNavigation().parse(page.getFolders().srcDir + "navigation.xml");

    XMIResource resource = new XMIResourceImpl();
    resource.load(new CSFileInputStream(eCorePath), null);
    org.hl7.fhir.definitions.ecore.fhir.Definitions eCoreDefs = (org.hl7.fhir.definitions.ecore.fhir.Definitions) resource.getContents().get(0);

    page.log("Produce Schemas", LogMessageType.Process);
    new SchemaGenerator().generate(page.getDefinitions(), page.getIni(), page.getFolders().tmpResDir, page.getFolders().xsdDir, page.getFolders().dstDir,
        page.getFolders().srcDir, page.getVersion(), Config.DATE_FORMAT().format(page.getGenDate().getTime()));

    for (PlatformGenerator gen : page.getReferenceImplementations()) {
      page.log("Produce " + gen.getName() + " Reference Implementation", LogMessageType.Process);

      String destDir = page.getFolders().dstDir;
      String implDir = page.getFolders().implDir(gen.getName());

      if (!gen.isECoreGenerator())
        gen.generate(page.getDefinitions(), destDir, implDir, page.getVersion(), page.getGenDate().getTime(), page, page.getSvnRevision());
      else
        gen.generate(eCoreDefs, destDir, implDir, page.getVersion(), page.getGenDate().getTime(), page, page.getSvnRevision());
    }
    for (PlatformGenerator gen : page.getReferenceImplementations()) {
      if (gen.doesCompile()) {
        page.log("Compile " + gen.getName() + " Reference Implementation", LogMessageType.Process);
        if (!gen.compile(page.getFolders().rootDir, new ArrayList<String>(), page)) {
          // Must always be able to compile Java to go on. Also, if we're
          // building
          // the web build, all generators that can compile, must compile
          // without error.
          if (gen.getName().equals("java") || web)
            throw new Exception("Compile " + gen.getName() + " failed");
          else
            page.log("Compile " + gen.getName() + " failed, still going on.", LogMessageType.Error);
        }
      }
    }

    page.log("Produce Schematrons", LogMessageType.Process);
    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname);
      String n = r.getName().toLowerCase();
      SchematronGenerator sch = new SchematronGenerator(new FileOutputStream(page.getFolders().dstDir + n + ".sch"), page);
      sch.generate(r.getRoot(), page.getDefinitions());
      sch.close();
    }

    SchematronGenerator sg = new SchematronGenerator(new FileOutputStream(page.getFolders().dstDir + "fhir-atom.sch"), page);
    sg.generate(page.getDefinitions());
    sg.close();

    produceSchemaZip();
    page.log("Produce Content", LogMessageType.Process);
    produceSpec();

    if (buildFlags.get("all")) {
      if (web) {
        if (!new File(page.getFolders().archiveDir).exists())
          throw new Exception("Unable to build HL7 copy with no archive directory (sync svn at one level up the tree)");

        page.log("Produce HL7 copy", LogMessageType.Process);
        wm = new WebMaker(page.getFolders(), page.getVersion(), page.getIni(), page.getDefinitions());
        wm.produceHL7Copy();
      }
      if (new File(page.getFolders().archiveDir).exists() && !noArchive && !page.hasIG()) {
        page.log("Produce Archive copy", LogMessageType.Process);
        produceArchive();
      }
    }
  }

  private void produceArchive() throws Exception {
    String target = page.getFolders().archiveDir + "v" + page.getVersion() + ".zip";
    File tf = new CSFile(target);
    if (tf.exists())
      tf.delete();

    ZipGenerator zip = new ZipGenerator(target);

    int c = 0;
    String[] files = new CSFile(page.getFolders().dstDir).list();
    for (String f : files) {
      File fn = new CSFile(page.getFolders().dstDir + f);
      if (!fn.isDirectory()) {
        if (f.endsWith(".html")) {
          String src = TextFile.fileToString(fn.getAbsolutePath());
          String srcn = src.replace("<!-- achive note -->",
              "This is an old version of FHIR retained for archive purposes. Do not use for anything else");
          if (!srcn.equals(src))
            c++;
          srcn = srcn.replace("<body>", "<body><div class=\"watermark\"/>").replace("<body class=\"book\">", "<body class=\"book\"><div class=\"watermark\"/>");
          zip.addFileSource(f, srcn, false);
          // Utilities.stringToFile(srcn, target+File.separator+f);
        } else if (f.endsWith(".css")) {
          String src = TextFile.fileToString(fn.getAbsolutePath());
          src = src.replace("#fff", "lightcyan");
          zip.addFileSource(f, src, false);
          // Utilities.stringToFile(srcn, target+File.separator+f);
        } else
          zip.addFileName(f, fn.getAbsolutePath(), false);
      } else if (!fn.getAbsolutePath().endsWith("v2") && !fn.getAbsolutePath().endsWith("v3")) {
        // used to put stuff in sub-directories. clean them out if they
        // still exist
        // Utilities.clearDirectory(fn.getAbsolutePath());
        // fn.delete();
      }
    }
    if (c < 3)
      throw new Exception("header note replacement in archive failed"); // so
                                                                        // check
                                                                        // the
                                                                        // syntax
                                                                        // of
                                                                        // the
                                                                        // string
                                                                        // constant
                                                                        // above
    zip.close();
  }

  private void produceSpec() throws Exception {
    if (buildFlags.get("all")) {

      copyStaticContent();

    }

    loadValueSets2();

    for (String rname : page.getDefinitions().sortedResourceNames()) {
      if (!rname.equals("ValueSet") && wantBuild(rname)) {
        ResourceDefn r = page.getDefinitions().getResources().get(rname);
        produceResource1(r);
      }
    }
    if (buildFlags.get("all")) {
      page.log("Base profiles", LogMessageType.Process);
      produceBaseProfile();
    }
    for (String rname : page.getDefinitions().sortedResourceNames()) {
      if (!rname.equals("ValueSet") && wantBuild(rname)) {
        ResourceDefn r = page.getDefinitions().getResources().get(rname);
        page.log(" ...resource " + r.getName(), LogMessageType.Process);
        produceResource2(r);
      }
    }

    for (Compartment c : page.getDefinitions().getCompartments()) {
      if (buildFlags.get("all")) {
        page.log(" ...compartment " + c.getName(), LogMessageType.Process);
        produceCompartment(c);
      }
    }

    for (String n : page.getIni().getPropertyNames("pages")) {
      if (buildFlags.get("all") || buildFlags.get("page-" + n.toLowerCase())) {
        page.log(" ...page " + n, LogMessageType.Process);
        producePage(n, page.getIni().getStringProperty("pages", n));
      }
    }
    if (page.hasIG() && !Utilities.noString(page.getIg().getHomePage())) {
      Utilities.copyFile(page.getFolders().dstDir+"index.html", page.getFolders().dstDir+"home.html");
      produceIgPage(page.getIg().getHomePage(), "index.html", "Index");
      for (String p : page.getIg().getPages())
        produceIgPage(p, new File(p).getName(), Utilities.fileTitle(p));
      producePage("ig-valuesets.html", "Value Sets");
      producePage("ig-profiles.html", "Profiles");
    }
    
    int i = 0;
    for (String n : page.getIni().getPropertyNames("sid")) {
      page.log(" ...sid " + n, LogMessageType.Process);
      produceSid(i, n, page.getIni().getStringProperty("sid", n));
      i++;
    }
    if (buildFlags.get("all")) {
      page.log(" ...check Fragments", LogMessageType.Process);
      checkFragments();
      for (String n : page.getDefinitions().getProfiles().keySet()) {
        if (!n.startsWith("http://")) {
          page.log(" ...profile " + n, LogMessageType.Process);
          produceProfile(n, page.getDefinitions().getProfiles().get(n), null, null, null);
        }
      }
      if (page.hasIG()) {
        for (AtomEntry<? extends Resource> ae: page.getIgResources().values()) {
          if (ae.getResource() instanceof Profile) {
            String n = Utilities.fileTitle(ae.getLinks().get("path")).replace(".xml", "");
            Profile p = (Profile) ae.getResource();
            ProfileDefn pd = new ProfileDefn();
            pd.setSource(p);


            page.log(" ...profile " + n, LogMessageType.Process);
            produceProfile(n, pd, null, null, null);
          }
        }
      }

      produceV2();
      produceV3();

      page.log(" ...collections ", LogMessageType.Process);
      
      new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + "profiles-resources.xml"), profileFeed, true, false);
      new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + "profiles-resources.json"), profileFeed, true);
      cloneToXhtml("profiles-resources",
          "Base Resources defined as profiles (implementation assistance, for for validation, derivation and product development)", false, "summary-instance");
      jsonToXhtml("profiles-resources",
          "Base Resources defined as profiles (implementation assistance, for for validation, derivation and product development)", resource2Json(profileFeed), "summary-instance");
      new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + "profiles-types.xml"), typeFeed, true, false);
      new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + "profiles-types.json"), typeFeed, true);
      cloneToXhtml("profiles-types", "Base Types defined as profiles (implementation assistance, for validation, derivation and product development)", false,
          "summary-instance");
      jsonToXhtml("profiles-types", "Base Types defined as profiles (implementation assistance, for validation, derivation and product development)",
          resource2Json(typeFeed), "summary-instance");
      
      int ec = 0;
      for (AtomEntry<? extends org.hl7.fhir.instance.model.Resource> e : valueSetsFeed.getEntryList()) {
        ValueSet vs = (ValueSet) e.getResource();
        if (!vs.getIdentifierSimple().equals(e.getId())) {
          ec++;
          page.log("Valueset id mismatch: atom entry has '"+e.getId()+"', but value set is '"+vs.getIdentifierSimple()+"'", LogMessageType.Error);
        }
      }
      if (ec > 0)
        throw new Exception("Cannot continue due to value set mis-identification");
      
      new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + "valuesets.xml"), valueSetsFeed, true, false);
      Utilities.copyFile(page.getFolders().dstDir + "valuesets.xml", page.getFolders().dstDir + "examples" + File.separator + "valuesets.xml");
      new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + "valuesets.json"), valueSetsFeed, true);
      cloneToXhtml("valuesets", "Base Valuesets (implementation assistance, for validation, derivation and product development)", false, "summary-instance");
      jsonToXhtml("valuesets", "Base Valuesets (implementation assistance, for validation, derivation and product development)", resource2Json(valueSetsFeed), "summary-instance");

      new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + "conceptmaps.xml"), conceptMapsFeed, true, false);
      Utilities.copyFile(page.getFolders().dstDir + "conceptmaps.xml", page.getFolders().dstDir + "examples" + File.separator + "conceptmaps.xml");
      new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + "conceptmaps.json"), conceptMapsFeed, true);
      cloneToXhtml("conceptmaps", "Base concept maps (implementation assistance, for validation, derivation and product development)", false,
          "summary-instance");
      jsonToXhtml("conceptmaps", "Base concept maps (implementation assistance, for validation, derivation and product development)",
          resource2Json(conceptMapsFeed), "summary-instance");

      new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + "v2-tables.xml"), v2Valuesets, true, false);
      Utilities.copyFile(page.getFolders().dstDir + "v2-tables.xml", page.getFolders().dstDir + "examples" + File.separator + "v2-tables.xml");
      new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + "v2-tables.json"), v2Valuesets, true);
      cloneToXhtml("v2-tables", "V2 Tables defined as value sets (implementation assistance, for derivation and product development)", false,
          "summary-instance");
      jsonToXhtml("v2-tables", "V2 Tables defined as value sets (implementation assistance, for derivation and product development)",
          resource2Json(v2Valuesets), "summary-instance");
      new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + "v3-codesystems.xml"), v3Valuesets, true, false);
      Utilities.copyFile(page.getFolders().dstDir + "v3-codesystems.xml", page.getFolders().dstDir + "examples" + File.separator + "v3-codesystems.xml");
      new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + "v3-codesystems.json"), v3Valuesets, true);
      cloneToXhtml("v3-codesystems", "v3 Code Systems defined as value sets (implementation assistance, for derivation and product development)", false,
          "summary-instance");
      jsonToXhtml("v3-codesystems", "v3 Code Systems defined as value sets (implementation assistance, for derivation and product development)",
          resource2Json(v3Valuesets), "summary-instance");

      page.log("....validator", LogMessageType.Process);
      ZipGenerator zip = new ZipGenerator(page.getFolders().dstDir + "validation.zip");
      zip.addFileName("profiles-types.xml", page.getFolders().dstDir + "profiles-types.xml", false);
      zip.addFileName("profiles-types.json", page.getFolders().dstDir + "profiles-types.json", false);
      zip.addFileName("profiles-resources.xml", page.getFolders().dstDir + "profiles-resources.xml", false);
      zip.addFileName("profiles-resources.json", page.getFolders().dstDir + "profiles-resources.json", false);
      zip.addFileName("valuesets.xml", page.getFolders().dstDir + "valuesets.xml", false);
      zip.addFileName("valuesets.json", page.getFolders().dstDir + "valuesets.json", false);
      zip.addFileName("v2-tables.xml", page.getFolders().dstDir + "v2-tables.xml", false);
      zip.addFileName("v2-tables.json", page.getFolders().dstDir + "v2-tables.json", false);
      zip.addFileName("v3-codesystems.xml", page.getFolders().dstDir + "v3-codesystems.xml", false);
      zip.addFileName("v3-codesystems.json", page.getFolders().dstDir + "v3-codesystems.json", false);
      zip.addFiles(page.getFolders().dstDir, "", ".xsd", null);
      zip.addFiles(page.getFolders().dstDir, "", ".sch", null);
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "tools", "schematron", ""), "", ".xsl", null);
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "tools", "schematron", ""), "", ".xslt", null);
      zip.close();

      zip = new ZipGenerator(page.getFolders().dstDir + "validator.zip");
      zip.addFileName("readme.txt", Utilities.path(page.getFolders().srcDir, "tools", "readme.txt"), false);
      zip.addFileName("org.hl7.fhir.validator.jar", Utilities.path(page.getFolders().dstDir, "org.hl7.fhir.validator.jar"), false);
      zip.addFileName("validation.zip", page.getFolders().dstDir + "validation.zip", false);
      zip.addFiles(Utilities.path(page.getFolders().rootDir, "tools", "schematron", ""), "", ".zip", null); // saxon
                                                                                                            // too
                                                                                                            // -
                                                                                                            // always
                                                                                                            // make
                                                                                                            // this
                                                                                                            // last
      zip.close();

      zip = new ZipGenerator(page.getFolders().dstDir + "all-valuesets.zip");
      zip.addFileName("valuesets.xml", page.getFolders().dstDir + "valuesets.xml", false);
      zip.addFileName("valuesets.json", page.getFolders().dstDir + "valuesets.json", false);
      zip.addFileName("conceptmaps.xml", page.getFolders().dstDir + "conceptmaps.xml", false);
      zip.addFileName("conceptmaps.json", page.getFolders().dstDir + "conceptmaps.json", false);
      zip.addFileName("v2-tables.xml", page.getFolders().dstDir + "v2-tables.xml", false);
      zip.addFileName("v2-tables.json", page.getFolders().dstDir + "v2-tables.json", false);
      zip.addFileName("v3-codesystems.xml", page.getFolders().dstDir + "v3-codesystems.xml", false);
      zip.addFileName("v3-codesystems.json", page.getFolders().dstDir + "v3-codesystems.json", false);
      zip.close();

      page.log(" ...zips", LogMessageType.Process);
      zip = new ZipGenerator(page.getFolders().dstDir + "examples.zip");
      zip.addFiles(page.getFolders().dstDir + "examples" + File.separator, "", null, null);
      zip.close();

      zip = new ZipGenerator(page.getFolders().dstDir + "examples-json.zip");
      zip.addFiles(page.getFolders().dstDir, "", ".json", null);
      zip.close();

      page.log(" ...final zip", LogMessageType.Process);
      produceZip();

      page.log("Produce .epub Form", LogMessageType.Process);
      page.getEpub().produce();
    } else
      page.log("Partial Build - terminating now", LogMessageType.Error);
  }


  private void copyStaticContent() throws IOException, Exception {
    if (page.getIni().getPropertyNames("support") != null)
      for (String n : page.getIni().getPropertyNames("support")) {
        Utilities.copyFile(new CSFile(page.getFolders().srcDir + n), new CSFile(page.getFolders().dstDir + n));
        page.getEpub().registerFile(n, "Support File", EPubManager.determineType(n));
      }
    for (String n : page.getIni().getPropertyNames("images")) {
      copyImage(page.getFolders().imgDir, n);
    }
    if (page.hasIG()) {
      for (String path : page.getIg().getImageSources()) {
        String folder = Utilities.getDirectoryForFile(path);
        String file = path.substring(folder.length()+1);
        copyImage(folder, file);
      }
    }
    for (String n : page.getIni().getPropertyNames("files")) {
      Utilities.copyFile(new CSFile(page.getFolders().rootDir + n), new CSFile(page.getFolders().dstDir + page.getIni().getStringProperty("files", n)));
      page.getEpub().registerFile(page.getIni().getStringProperty("files", n), "Support File",
          EPubManager.determineType(page.getIni().getStringProperty("files", n)));
    }

    page.log("Copy HTML templates", LogMessageType.Process);
    Utilities.copyDirectory(page.getFolders().rootDir + page.getIni().getStringProperty("html", "source"), page.getFolders().dstDir, page.getEpub());
    TextFile.stringToFile("\r\n[FHIR]\r\nFhirVersion=" + page.getVersion() + "." + page.getSvnRevision() + "\r\nversion=" + page.getVersion()
        + "\r\nrevision=" + page.getSvnRevision() + "\r\ndate=" + new SimpleDateFormat("yyyyMMddHHmmss").format(page.getGenDate().getTime()),
        Utilities.path(page.getFolders().dstDir, "version.info"));

    for (String n : page.getDefinitions().getDiagrams().keySet()) {
      page.log(" ...diagram " + n, LogMessageType.Process);
      page.getSvgs().put(n, TextFile.fileToString(page.getFolders().srcDir + page.getDefinitions().getDiagrams().get(n)));
    }
  }

  private void copyImage(String folder, String n) throws IOException {
    if (n.contains("*")) {
      final String filter = n.replace("?", ".?").replace("*", ".*?");
      File[] files = new File(folder).listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
          return name.matches(filter);
        }
      });
      for (File f : files) {
        Utilities.copyFile(f, new CSFile(page.getFolders().dstDir + f.getName()));
        page.getEpub().registerFile(f.getName(), "Support File", EPubManager.determineType(n));
      }
    } else {
      Utilities.copyFile(new CSFile(folder + n), new CSFile(page.getFolders().dstDir + n));
      page.getEpub().registerFile(n, "Support File", EPubManager.determineType(n));
    }
  }

  /** this is only used when generating xhtml of json **/
  private String resource2Json(AtomFeed profileFeed2) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    JsonComposer json = new JsonComposer();
    json.setSuppressXhtml("Snipped for Brevity");
    json.compose(bytes, profileFeed, true);
    return new String(bytes.toByteArray());
  }

  private String resource2Json(org.hl7.fhir.instance.model.Resource r) throws Exception {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    JsonComposer json = new JsonComposer();
    json.setSuppressXhtml("Snipped for Brevity");
    json.compose(bytes, r, true);
    return new String(bytes.toByteArray());
  }

  private void produceQA() throws Exception {
    page.getQa().countDefinitions(page.getDefinitions());

    String src = TextFile.fileToString(page.getFolders().srcDir + "qa.html");
    TextFile.stringToFile(page.processPageIncludes("qa.html", src, "page", null, null), page.getFolders().dstDir + "qa.html");

    if (web) {
      page.getQa().commit(page.getFolders().rootDir);
      wm.addPage("qa.html");
    }
  }

  private static String nodeToString(Element node) throws Exception {
    StringBuilder b = new StringBuilder();
    Node n = node.getFirstChild();
    while (n != null) {
      if (n.getNodeType() == Node.ELEMENT_NODE) {
        b.append(nodeToString((Element) n));
      } else if (n.getNodeType() == Node.TEXT_NODE) {
        b.append(Utilities.escapeXml(n.getTextContent()));
      }
      n = n.getNextSibling();
    }
    if (node.getNodeName().equals("p"))
      b.append("<br/>\r\n");
    return b.toString();
  }

  private static String nodeToText(Element node) throws Exception {
    StringBuilder b = new StringBuilder();
    Node n = node.getFirstChild();
    while (n != null) {
      if (n.getNodeType() == Node.ELEMENT_NODE) {
        b.append(nodeToText((Element) n));
      } else if (n.getNodeType() == Node.TEXT_NODE) {
        b.append(n.getTextContent());
      }
      n = n.getNextSibling();
    }
    if (node.getNodeName().equals("p"))
      b.append("\r\n");
    return b.toString();
  }

  private class CodeInfo {
    boolean select;
    String code;
    String display;
    String definition;
    String textDefinition;
    boolean deprecated;

    List<String> parents = new ArrayList<String>();
    List<CodeInfo> children = new ArrayList<CodeInfo>();

    public void write(int lvl, StringBuilder s, ValueSet vs, List<ValueSetDefineConceptComponent> list, ValueSetDefineConceptComponent owner,
        Map<String, ValueSetDefineConceptComponent> handled) throws Exception {
      if (!select && children.size() == 0)
        return;

      if (handled.containsKey(code)) {
        if (owner == null)
          throw new Exception("Error handling poly-heirarchy - subsequent mention is on the root");
        // ToolingExtensions.addParentCode(handled.get(code),
        // owner.getCodeSimple());
        ToolingExtensions.addSubsumes(owner, code);
        s.append(" <tr><td>" + Integer.toString(lvl) + "</td><td>");
        for (int i = 1; i < lvl; i++)
          s.append("&nbsp;&nbsp;");
        s.append("<a href=\"#" + Utilities.escapeXml(Utilities.nmtokenize(code)) + "\">" + Utilities.escapeXml(code) + "</a></td><td></td><td></td></tr>\r\n");
      } else {
        ValueSetDefineConceptComponent concept = new ValueSet.ValueSetDefineConceptComponent();
        handled.put(code, concept);
        concept.setCodeSimple(code);
        concept.setDisplaySimple(display);
        concept.setDefinitionSimple(textDefinition);
        concept.setAbstractSimple(!select);
        String d = "";
        if (deprecated) {
          ToolingExtensions.markDeprecated(concept);
          d = " <b><i>Deprecated</i></b>";
        }

        list.add(concept);

        s.append(" <tr" + (deprecated ? " style=\"background: #EFEFEF\"" : "") + "><td>" + Integer.toString(lvl) + "</td><td>");
        for (int i = 1; i < lvl; i++)
          s.append("&nbsp;&nbsp;");
        if (select) {
          s.append(Utilities.escapeXml(code) + "<a name=\"" + Utilities.escapeXml(Utilities.nmtokenize(code)) + "\"> </a>" + d + "</td><td>"
              + Utilities.escapeXml(display) + "</td><td>");
        } else
          s.append("<span style=\"color: grey\"><i>(" + Utilities.escapeXml(code) + ")</i></span>" + d + "</td><td><a name=\""
              + Utilities.escapeXml(Utilities.nmtokenize(code)) + "\">&nbsp;</a></td><td>");
        if (definition != null)
          s.append(definition);
        s.append("</td></tr>\r\n");
        for (CodeInfo child : children) {
          child.write(lvl + 1, s, vs, concept.getConcept(), concept, handled);
        }
      }
    }
  }

  private ValueSet buildV3CodeSystem(String id, String date, Element e) throws Exception {
    StringBuilder s = new StringBuilder();
    ValueSet vs = new ValueSet();
    vs.setIdentifierSimple("http://hl7.org/fhir/v3/vs/" + id);
    vs.setNameSimple("v3 Code System " + id);
    vs.setPublisherSimple("HL7, Inc");
    vs.getTelecom().add(Factory.newContact(ContactSystem.url, "http://hl7.org"));
    vs.setStatusSimple(ValuesetStatus.active);
    ValueSetDefineComponent def = new ValueSet.ValueSetDefineComponent();
    vs.setDefine(def);
    def.setCaseSensitiveSimple(true);
    def.setSystemSimple("http://hl7.org/fhir/v3/" + id);

    Element r = XMLUtil.getNamedChild(e, "releasedVersion");
    if (r != null) {
      s.append("<p>Release Date: " + r.getAttribute("releaseDate") + "</p>\r\n");
      vs.setDateSimple(new DateAndTime(r.getAttribute("releaseDate")));
    }
    r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "description"), "text");
    if (r == null)
      r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "definition"), "text");
    if (r != null) {
      s.append("<h2>Description</h2>\r\n");
      s.append("<p>" + nodeToString(r) + "</p>\r\n");
      s.append("<hr/>\r\n");
      vs.setDescriptionSimple(XMLUtil.htmlToXmlEscapedPlainText(r));
    } else
      vs.setDescriptionSimple("? not found");

    List<CodeInfo> codes = new ArrayList<CodeInfo>();
    // first, collate all the codes
    Element c = XMLUtil.getFirstChild(XMLUtil.getNamedChild(e, "releasedVersion"));
    while (c != null) {
      if (c.getNodeName().equals("concept")) {
        CodeInfo ci = new CodeInfo();
        ci.select = !"false".equals(c.getAttribute("isSelectable"));
        r = XMLUtil.getNamedChild(c, "code");
        ci.code = r == null ? null : r.getAttribute("code");
        r = XMLUtil.getNamedChild(c, "printName");
        ci.display = r == null ? null : r.getAttribute("text");
        r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(c, "annotations"), "documentation"), "definition"), "text");
        ci.definition = r == null ? null : nodeToString(r);
        ci.textDefinition = r == null ? null : nodeToText(r).trim();
        ci.deprecated = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(c, "annotations"), "appInfo"), "deprecationInfo") != null;
        List<Element> pl = new ArrayList<Element>();
        XMLUtil.getNamedChildren(c, "conceptRelationship", pl);
        for (Element p : pl) {
          if (p.getAttribute("relationshipName").equals("Specializes"))
            ci.parents.add(XMLUtil.getFirstChild(p).getAttribute("code"));
        }
        if (!"retired".equals(XMLUtil.getNamedChild(c, "code").getAttribute("status")))
          codes.add(ci);
      }
      c = XMLUtil.getNextSibling(c);
    }

    // now, organise the heirarchy
    for (CodeInfo ci : codes) {
      for (String p : ci.parents) {
        CodeInfo pi = null;
        for (CodeInfo cip : codes) {
          if (cip.code != null && cip.code.equals(p))
            pi = cip;
        }
        if (pi != null)
          pi.children.add(ci);
      }
    }

    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>Level</b></td><td><b>Code</b></td><td><b>Display</b></td><td><b>Definition</b></td></tr>\r\n");
    Map<String, ValueSetDefineConceptComponent> handled = new HashMap<String, ValueSet.ValueSetDefineConceptComponent>();
    for (CodeInfo ci : codes) {
      if (ci.parents.size() == 0) {
        ci.write(1, s, vs, def.getConcept(), null, handled);
      }
    }
    s.append("</table>\r\n");

    vs.setText(new Narrative());
    vs.getText().setStatusSimple(NarrativeStatus.generated);
    vs.getText().setDiv(new XhtmlParser().parse("<div>" + s.toString() + "</div>", "div").getElement("div"));
    new ValueSetValidator(page.getWorkerContext()).validate(vs, false);

    return vs;
  }

  private void analyseV3() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    page.setV3src(builder.parse(new CSFileInputStream(new CSFile(page.getFolders().srcDir + "v3" + File.separator + "source.xml"))));
    String dt = null;
    Map<String, ValueSet> codesystems = new HashMap<String, ValueSet>();

    IniFile ini = new IniFile(page.getFolders().srcDir + "v3" + File.separator + "valuesets.ini");

    Element e = XMLUtil.getFirstChild(page.getV3src().getDocumentElement());
    while (e != null) {

      if (e.getNodeName().equals("header")) {
        Element d = XMLUtil.getNamedChild(e, "renderingInformation");
        if (d != null)
          dt = d.getAttribute("renderingTime");
      }

      if (e.getNodeName().equals("codeSystem")) {
        Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "header"), "responsibleGroup");
        if (!ini.getBooleanProperty("Exclude", e.getAttribute("name"))) {
          String id = e.getAttribute("name");
          if (r != null && "Health Level 7".equals(r.getAttribute("organizationName")) || ini.getBooleanProperty("CodeSystems", id)) {
            AtomEntry<ValueSet> ae = new AtomEntry<ValueSet>();
            ae.setId("http://hl7.org/fhir/v3/" + id);
            ae.getLinks().put("self", "http://hl7.org/fhir/v3/" + id);
            ae.getLinks().put("path", "v3" + File.separator + id + File.separator + "index.html");
            ae.getLinks().put("oid", e.getAttribute("codeSystemId"));
            ValueSet vs = buildV3CodeSystem(id, dt, e);
            ae.setResource(vs);
            if (vs.getDate() != null)
              ae.setUpdated(vs.getDate().getValue().expandTime());
            else
              ae.setUpdated(DateAndTime.now());
            ae.setTitle(vs.getDescriptionSimple());
            ae.setAuthorName(vs.getPublisherSimple());
            page.getV3Valuesets().getEntryList().add(ae);
            page.getDefinitions().getValuesets().put(vs.getIdentifierSimple(), vs);
            page.getDefinitions().getCodeSystems().put(vs.getDefine().getSystemSimple(), vs);
            page.getValueSets().put(vs.getIdentifierSimple(), ae);
            page.getCodeSystems().put(vs.getDefine().getSystemSimple().toString(), ae);
            codesystems.put(e.getAttribute("codeSystemId"), vs);
          } // else if (r == null)
            // page.log("unowned code system: "+id);
        }
      }

      if (e.getNodeName().equals("valueSet")) {
        String iniV = ini.getStringProperty("ValueSets", e.getAttribute("name"));
        if (iniV != null) {
          String id = e.getAttribute("name");
          AtomEntry<ValueSet> ae = new AtomEntry<ValueSet>();
          ae.setId("http://hl7.org/fhir/v3/vs/" + id);
          ae.getLinks().put("self", "http://hl7.org/fhir/v3/vs/" + id);
          ae.getLinks().put("path", "v3" + File.separator + "vs" + File.separator + id + File.separator + "index.html");
          ValueSet vs;
          if (iniV.equals("1"))
            vs = buildV3ValueSet(id, dt, e, codesystems, ini);
          else if (iniV.startsWith("->")) {
            vs = buildV3ValueSetAsCodeSystem(id, e, iniV.substring(2));
          } else
            throw new Exception("unhandled value set specifier in ini file");
          ae.getLinks().put("oid", e.getAttribute("id"));
          ae.setResource(vs);
          if (vs.getDate() != null)
            ae.setUpdated(vs.getDate().getValue().expandTime());
          else
            ae.setUpdated(DateAndTime.now());
          ae.setTitle(vs.getDescriptionSimple());
          ae.setAuthorName(vs.getPublisherSimple());
          page.getV3Valuesets().getEntryList().add(ae);
          page.getValueSets().put(vs.getIdentifierSimple(), ae);
          page.getDefinitions().getValuesets().put(vs.getIdentifierSimple(), vs);
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
  }

  private ValueSet buildV3ValueSetAsCodeSystem(String id, Element e, String csname) throws DOMException, Exception {
    ValueSet vs = new ValueSet();
    vs.setIdentifierSimple("http://hl7.org/fhir/v3/vs/" + id);
    vs.setNameSimple(id);
    Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "description"),
        "text");
    if (r != null) {
      vs.setDescriptionSimple(XMLUtil.htmlToXmlEscapedPlainText(r) + " (OID = " + e.getAttribute("id") + ")");
    } else {
      vs.setDescriptionSimple("?? (OID = " + e.getAttribute("id") + ")");
    }
    vs.setPublisherSimple("HL7 v3");
    vs.getTelecom().add(Factory.newContact(ContactSystem.url, "http://www.hl7.org"));
    vs.setStatusSimple(ValuesetStatus.active);

    r = XMLUtil.getNamedChild(e, "version");
    if (r != null) 
      vs.setVersionSimple(r.getAttribute("versionDate"));
    String[] parts = csname.split("\\&");
    ValueSetComposeComponent compose = new ValueSet.ValueSetComposeComponent();
    vs.setCompose(compose);
    for (String cs : parts) {
      if (cs.contains(":")) {
        compose.addInclude().setSystemSimple(cs);
      } else if (cs.contains(".")) {
        String[] csp = cs.split("\\.");
        if (csp.length != 2)
          throw new Exception("unhandled value set specifier "+cs+" in ini file");
        ConceptSetComponent inc = compose.addInclude();
        inc.setSystemSimple("http://hl7.org/fhir/v3/"+csp[0]);
        inc.addFilter().setPropertySimple("concept").setOpSimple(FilterOperator.isa).setValueSimple(csp[1]);
      } else {
        compose.addInclude().setSystemSimple("http://hl7.org/fhir/v3/"+cs);
      }
    }
    
    NarrativeGenerator gen = new NarrativeGenerator("../../../", page.getWorkerContext());
    gen.generate(vs);
    new ValueSetValidator(page.getWorkerContext()).validate(vs, false);
    return vs;
  }
  
  private ValueSet buildV3ValueSet(String id, String dt, Element e, Map<String, ValueSet> codesystems, IniFile vsini) throws DOMException, Exception {
    ValueSet vs = new ValueSet();
    vs.setIdentifierSimple("http://hl7.org/fhir/v3/vs/" + id);
    vs.setNameSimple(id);
    Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "description"),
        "text");
    if (r != null) {
      vs.setDescriptionSimple(XMLUtil.htmlToXmlEscapedPlainText(r) + " (OID = " + e.getAttribute("id") + ")");
    } else {
      vs.setDescriptionSimple("?? (OID = " + e.getAttribute("id") + ")");
    }
    vs.setPublisherSimple("HL7 v3");
    vs.getTelecom().add(Factory.newContact(ContactSystem.url, "http://www.hl7.org"));
    vs.setStatusSimple(ValuesetStatus.active);

    r = XMLUtil.getNamedChild(e, "version");
    if (r != null) {
      vs.setVersionSimple(r.getAttribute("versionDate"));

      // ok, now the content
      ValueSetComposeComponent compose = new ValueSet.ValueSetComposeComponent();
      vs.setCompose(compose);
      Element content = XMLUtil.getNamedChild(r, "content");
      if (content == null)
        throw new Exception("Unable to find content for ValueSet " + id);
      ValueSet cs = codesystems.get(content.getAttribute("codeSystem"));
      if (cs == null)
        throw new Exception("Error Processing ValueSet " + id + ", unable to resolve code system '"
            + XMLUtil.getNamedChild(e, "supportedCodeSystem").getTextContent() + "'");
      ConceptSetComponent imp = new ValueSet.ConceptSetComponent();

      if (XMLUtil.hasNamedChild(content, "combinedContent")) {
        if (!id.equals("SecurityControlObservationValue"))
          throw new Exception("check logic; this is fragile code, and each value set needs manual review- id is "+id);
        Element part = XMLUtil.getFirstChild(XMLUtil.getNamedChild(content, "combinedContent"));
        while (part != null) {
          if (part.getNodeName().equals("unionWithContent"))
            compose.addImportSimple("http://hl7.org/fhir/v3/vs/" + XMLUtil.getNamedChild(part, "valueSetRef").getAttribute("name"));
          else
            throw new Exception("unknown value set construction method");
          part = XMLUtil.getNextSibling(part);
        }
      } else {
        // simple value set
        compose.getInclude().add(imp);
        imp.setSystemSimple(cs.getDefine().getSystemSimple());

        if (!XMLUtil.getNamedChild(r, "supportedCodeSystem").getTextContent().equals(content.getAttribute("codeSystem")))
          throw new Exception("Unexpected codeSystem oid on content for ValueSet " + id + ": expected '"
              + XMLUtil.getNamedChild(r, "supportedCodeSystem").getTextContent() + "', found '" + content.getAttribute("codeSystem") + "'");

        List<String> codes = new ArrayList<String>();

        Element cnt = XMLUtil.getFirstChild(content);
        while (cnt != null) {
          if (cnt.getNodeName().equals("codeBasedContent") && (XMLUtil.getNamedChild(cnt, "includeRelatedCodes") != null)) {
            // common case: include a child and all or some of it's descendants
            ConceptSetFilterComponent f = new ValueSet.ConceptSetFilterComponent();
            f.setOpSimple(FilterOperator.isa);
            f.setPropertySimple("concept");
            f.setValueSimple(cnt.getAttribute("code"));
            imp.getFilter().add(f);
          } else if (cnt.getNodeName().equals("codeBasedContent") && cnt.hasAttribute("code")) {
            codes.add(cnt.getAttribute("code"));
          }
          cnt = XMLUtil.getNextSibling(cnt);
        }
        if (vsini.getStringProperty("Order", id) != null) {
          List<String> order = new ArrayList<String>();
          for (String s : vsini.getStringProperty("Order", id).split("\\,")) {
            order.add(s);
          }
          for (String c : order) {
            if (codes.contains(c))
              imp.addCodeSimple(c);
          }
          for (String c : codes) {
            if (!order.contains(c))
              imp.addCodeSimple(c);
          }
        } else
          for (String c : codes) {
            imp.addCodeSimple(c);
          }
      }
    }
    NarrativeGenerator gen = new NarrativeGenerator("../../../", page.getWorkerContext());
    gen.generate(vs);
    new ValueSetValidator(page.getWorkerContext()).validate(vs, false);
    return vs;

  }

  private void produceV3() throws Exception {
    page.log(" ...v3 Code Systems", LogMessageType.Process);

    Utilities.createDirectory(page.getFolders().dstDir + "v3");
    Utilities.clearDirectory(page.getFolders().dstDir + "v3");
    String src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template.html");
    TextFile.stringToFile(
        addSectionNumbers("terminologies-v3.html", "terminologies-v3", page.processPageIncludes("terminologies-v3.html", src, "page", null, null), null),
        page.getFolders().dstDir + "terminologies-v3.html");
    src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template.html");
    cachePage("terminologies-v3.html", page.processPageIncludesForBook("terminologies-v3.html", src, "page", null), "V3 Terminologes");
    IniFile ini = new IniFile(page.getFolders().srcDir + "v3" + File.separator + "valuesets.ini");

    Element e = XMLUtil.getFirstChild(page.getV3src().getDocumentElement());
    while (e != null) {
      if (e.getNodeName().equals("codeSystem")) {
        if (!ini.getBooleanProperty("Exclude", e.getAttribute("name"))) {
          Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "header"), "responsibleGroup");
          if (r != null && "Health Level 7".equals(r.getAttribute("organizationName")) || ini.getBooleanProperty("CodeSystems", e.getAttribute("name"))) {
            String id = e.getAttribute("name");
            Utilities.createDirectory(page.getFolders().dstDir + "v3" + File.separator + id);
            Utilities.clearDirectory(page.getFolders().dstDir + "v3" + File.separator + id);
            src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template-cs.html");

            String sf = page.processPageIncludes(id + ".html", src, "v3Vocab", null, "v3" + File.separator + id + File.separator + "index.html", null);
            sf = addSectionNumbers("v3" + id + ".html", "template-v3", sf, Utilities.oidTail(e.getAttribute("codeSystemId")));
            TextFile.stringToFile(sf, page.getFolders().dstDir + "v3" + File.separator + id + File.separator + "index.html");
            page.getEpub().registerExternal("v3" + File.separator + id + File.separator + "index.html");
          }
        }
      }
      if (e.getNodeName().equals("valueSet")) {
        if (ini.getStringProperty("ValueSets", e.getAttribute("name")) != null) {
          String id = e.getAttribute("name");
          Utilities.createDirectory(page.getFolders().dstDir + "v3" + File.separator + "vs" + File.separator + id);
          Utilities.clearDirectory(page.getFolders().dstDir + "v3" + File.separator + "vs" + File.separator + id);
          src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template-vs.html");
          String sf = page.processPageIncludes(id + ".html", src, "v3Vocab", null, "v3" + File.separator + "vs" + File.separator + id + File.separator + "index.html", null);
          sf = addSectionNumbers("v3" + id + ".html", "template-v3", sf, Utilities.oidTail(e.getAttribute("id")));
          TextFile.stringToFile(sf, page.getFolders().dstDir + "v3" + File.separator + "vs" + File.separator + id + File.separator + "index.html");
          page.getEpub().registerExternal("v3" + File.separator + "vs" + File.separator + id + File.separator + "index.html");
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
  }

  private ValueSet buildV2Valueset(String id, Element e) throws Exception {
    ValueSet vs = new ValueSet();
    vs.setIdentifierSimple("http://hl7.org/fhir/v2/vs/" + id);
    vs.setNameSimple("v2 table " + id);
    vs.setPublisherSimple("HL7, Inc");
    vs.getTelecom().add(Factory.newContact(ContactSystem.url, "http://hl7.org"));
    vs.setStatusSimple(ValuesetStatus.active);
    vs.setDateSimple(new DateAndTime("2011-01-28")); // v2.7 version
    ValueSetDefineComponent def = new ValueSet.ValueSetDefineComponent();
    vs.setDefine(def);
    def.setCaseSensitiveSimple(true);
    def.setSystemSimple("http://hl7.org/fhir/v2/" + id);
    StringBuilder s = new StringBuilder();

    String desc = "";
    // we use the latest description of the table
    Element c = XMLUtil.getFirstChild(e);
    Map<String, String> codes = new HashMap<String, String>();
    while (c != null) {
      desc = c.getAttribute("desc");
      vs.setDescriptionSimple("FHIR Value set/code system definition for HL7 v2 table " + id + " ( " + desc + ")");
      vs.setNameSimple("v2 " + desc);

      Element g = XMLUtil.getFirstChild(c);
      while (g != null) {
        codes.put(g.getAttribute("code"), g.getAttribute("desc"));
        g = XMLUtil.getNextSibling(g);
      }
      c = XMLUtil.getNextSibling(c);
    }
    s.append("<p>" + Utilities.escapeXml(desc) + "</p>\r\n");
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>Code</b></td><td><b>Description</b></td><td><b>Version</b></td></tr>\r\n");
    List<String> cs = new ArrayList<String>();
    cs.addAll(codes.keySet());
    Collections.sort(cs);
    for (String cd : cs) {
      String min = null;
      String max = null;
      c = XMLUtil.getFirstChild(e);
      while (c != null) {
        Element g = XMLUtil.getFirstChild(c);
        while (g != null) {
          if (cd.equals(g.getAttribute("code"))) {
            if (min == null)
              min = c.getAttribute("version");
            max = c.getAttribute("version");
          }
          g = XMLUtil.getNextSibling(g);
        }
        c = XMLUtil.getNextSibling(c);
      }
      String ver = ("2.1".equals(min) ? "from v2.1" : "added v" + min) + ("2.7".equals(max) ? "" : ", removed after v" + max);
      ValueSetDefineConceptComponent concept = new ValueSet.ValueSetDefineConceptComponent();
      concept.setCodeSimple(cd);
      concept.setDisplaySimple(codes.get(cd)); // we deem the v2 description to
                                               // be display name, not
                                               // definition. Open for
                                               // consideration
      if (!("2.7".equals(max)))
        ToolingExtensions.markDeprecated(concept);
      def.getConcept().add(concept);
      String nm = Utilities.nmtokenize(cd);
      s.append(" <tr><td>" + Utilities.escapeXml(cd) + "<a name=\"" + Utilities.escapeXml(nm) + "\"> </a></td><td>" + Utilities.escapeXml(codes.get(cd))
          + "</td><td>" + ver + "</td></tr>\r\n");
    }
    s.append("</table>\r\n");
    vs.setText(new Narrative());
    vs.getText().setStatusSimple(NarrativeStatus.additional); // because we add
                                                              // v2 versioning
                                                              // information
    vs.getText().setDiv(new XhtmlParser().parse("<div>" + s.toString() + "</div>", "div").getElement("div"));
    new ValueSetValidator(page.getWorkerContext()).validate(vs, false);
    return vs;
  }

  private ValueSet buildV2ValuesetVersioned(String id, String version, Element e) throws Exception {
    StringBuilder s = new StringBuilder();

    ValueSet vs = new ValueSet();
    vs.setIdentifierSimple("http://hl7.org/fhir/v2/vs/" + id + "/" + version);
    vs.setNameSimple("v2 table " + id + ", Version " + version);
    vs.setPublisherSimple("HL7, Inc");
    vs.getTelecom().add(Factory.newContact(ContactSystem.url, "http://hl7.org"));
    vs.setStatusSimple(ValuesetStatus.active);
    vs.setDateSimple(new DateAndTime("2011-01-28")); // v2.7 version
    ValueSetDefineComponent def = new ValueSet.ValueSetDefineComponent();
    vs.setDefine(def);
    def.setCaseSensitiveSimple(true);
    def.setSystemSimple("http://hl7.org/fhir/v2/" + id + "/" + version);

    String desc = "";
    String minlim = null;
    String maxlim = null;

    // we use the latest description of the table
    Element c = XMLUtil.getFirstChild(e);
    Map<String, String> codes = new HashMap<String, String>();
    while (c != null) {
      if (version.equals(c.getAttribute("namespace"))) {
        if (minlim == null)
          minlim = c.getAttribute("version");
        maxlim = c.getAttribute("version");
        desc = c.getAttribute("desc");
        vs.setDescriptionSimple("FHIR Value set/code system definition for HL7 v2 table " + id + " ver " + version + " ( " + desc + ")");
        Element g = XMLUtil.getFirstChild(c);
        while (g != null) {
          codes.put(g.getAttribute("code"), g.getAttribute("desc"));
          g = XMLUtil.getNextSibling(g);
        }
      }
      c = XMLUtil.getNextSibling(c);
    }

    s.append("<p>" + Utilities.escapeXml(desc) + "</p>\r\n");
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>Code</b></td><td><b>Description</b></td><td><b>Version</b></td></tr>\r\n");
    List<String> cs = new ArrayList<String>();
    cs.addAll(codes.keySet());
    Collections.sort(cs);
    for (String cd : cs) {
      String min = null;
      String max = null;
      c = XMLUtil.getFirstChild(e);
      while (c != null) {
        if (version.equals(c.getAttribute("namespace"))) {
          Element g = XMLUtil.getFirstChild(c);
          while (g != null) {
            if (cd.equals(g.getAttribute("code"))) {
              if (min == null)
                min = c.getAttribute("version");
              max = c.getAttribute("version");
            }
            g = XMLUtil.getNextSibling(g);
          }
        }
        c = XMLUtil.getNextSibling(c);
      }
      String ver = (minlim.equals(min) ? "from v" + minlim : "added v" + min) + (maxlim.equals(max) ? "" : ", removed after v" + max);
      ValueSetDefineConceptComponent concept = new ValueSet.ValueSetDefineConceptComponent();
      concept.setCodeSimple(cd);
      concept.setDisplaySimple(codes.get(cd)); // we deem the v2 description to
                                               // be display name, not
                                               // definition. Open for
                                               // consideration
      def.getConcept().add(concept);
      s.append(" <tr><td>" + Utilities.escapeXml(cd) + "<a name=\"" + Utilities.escapeXml(Utilities.nmtokenize(cd)) + "\"> </a></td><td>"
          + Utilities.escapeXml(codes.get(cd)) + "</td><td>" + ver + "</td></tr>\r\n");
    }
    s.append("</table>\r\n");
    vs.setText(new Narrative());
    vs.getText().setStatusSimple(NarrativeStatus.additional); // because we add
                                                              // v2 versioning
                                                              // information
    vs.getText().setDiv(new XhtmlParser().parse("<div>" + s.toString() + "</div>", "div").getElement("div"));
    new ValueSetValidator(page.getWorkerContext()).validate(vs, false);
    return vs;
  }

  private void analyseV2() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    page.setV2src(builder.parse(new CSFileInputStream(new CSFile(page.getFolders().srcDir + "v2" + File.separator + "source.xml"))));

    Element e = XMLUtil.getFirstChild(page.getV2src().getDocumentElement());
    while (e != null) {
      String st = e.getAttribute("state");
      if ("include".equals(st)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        AtomEntry ae = new AtomEntry();
        ae.getLinks().put("self", "v2" + File.separator + id + File.separator + "index.html");
        ae.getLinks().put("path", "v2" + File.separator + id + File.separator + "index.html");
        ValueSet vs = buildV2Valueset(id, e);
        ae.setResource(vs);
        page.getDefinitions().getValuesets().put(vs.getIdentifierSimple(), vs);
        page.getDefinitions().getCodeSystems().put(vs.getDefine().getSystemSimple(), vs);
        page.getValueSets().put(vs.getIdentifierSimple(), ae);
        page.getCodeSystems().put(vs.getDefine().getSystemSimple().toString(), ae);
      } else if ("versioned".equals(st)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        List<String> versions = new ArrayList<String>();
        Element c = XMLUtil.getFirstChild(e);
        while (c != null) {
          if (XMLUtil.getFirstChild(c) != null && !versions.contains(c.getAttribute("namespace"))) {
            versions.add(c.getAttribute("namespace"));
          }
          c = XMLUtil.getNextSibling(c);
        }
        for (String ver : versions) {
          AtomEntry ae = new AtomEntry();
          ae.getLinks().put("self", "v2" + File.separator + id + File.separator + ver + File.separator + "index.html");
          ae.getLinks().put("path", "v2" + File.separator + id + File.separator + ver + File.separator + "index.html");
          ValueSet vs = buildV2ValuesetVersioned(id, ver, e);
          ae.setResource(vs);
          page.getDefinitions().getValuesets().put(vs.getIdentifierSimple(), vs);
          page.getDefinitions().getCodeSystems().put(vs.getDefine().getSystemSimple(), vs);
          page.getValueSets().put(vs.getIdentifierSimple(), ae);
          page.getCodeSystems().put(vs.getDefine().getSystemSimple().toString(), ae);
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
  }

  private void produceV2() throws Exception {
    page.log(" ...v2 Tables", LogMessageType.Process);

    Utilities.createDirectory(page.getFolders().dstDir + "v2");
    Utilities.clearDirectory(page.getFolders().dstDir + "v2");
    String src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template.html");
    TextFile.stringToFile(
        addSectionNumbers("terminologies-v2.html", "terminologies-v2", page.processPageIncludes("terminologies-v2.html", src, "v2Vocab", null, null), null),
        page.getFolders().dstDir + "terminologies-v2.html");
    src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template.html");
    cachePage("terminologies-v2.html", page.processPageIncludesForBook("v2/template.html", src, "v2Vocab", null), "V2 Terminologies");

    Element e = XMLUtil.getFirstChild(page.getV2src().getDocumentElement());
    while (e != null) {
      String st = e.getAttribute("state");
      if ("include".equals(st)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        String iid = id;
        while (iid.startsWith("0"))
          iid = iid.substring(1);
        Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
        Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
        src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template-tbl.html");
        String sf = page.processPageIncludes(id + ".html", src, "v2Vocab", null, "v2" + File.separator + id + File.separator + "index.html", null);
        sf = addSectionNumbers("v2" + id + ".html", "template-v2", sf, iid);
        TextFile.stringToFile(sf, page.getFolders().dstDir + "v2" + File.separator + id + File.separator + "index.html");
        page.getEpub().registerExternal("v2" + File.separator + id + File.separator + "index.html");
      } else if ("versioned".equals(st)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        String iid = id;
        while (iid.startsWith("0"))
          iid = iid.substring(1);
        Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
        Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
        List<String> versions = new ArrayList<String>();
        Element c = XMLUtil.getFirstChild(e);
        while (c != null) {
          if (XMLUtil.getFirstChild(c) != null && !versions.contains(c.getAttribute("namespace"))) {
            versions.add(c.getAttribute("namespace"));
          }
          c = XMLUtil.getNextSibling(c);
        }
        int i = 0;
        for (String ver : versions) {
          if (!Utilities.noString(ver)) {
            i++;
            Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id + File.separator + ver);
            Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id + File.separator + ver);
            src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template-tbl-ver.html");
            String sf = page.processPageIncludes(id + "|" + ver + ".html", src, "v2Vocab", null, "v2" + File.separator + id + File.separator + ver + File.separator + "index.html", null);
            sf = addSectionNumbers("v2" + id + "." + ver + ".html", "template-v2", sf, iid + "." + Integer.toString(i));
            TextFile.stringToFile(sf, page.getFolders().dstDir + "v2" + File.separator + id + File.separator + ver + File.separator + "index.html");
            page.getEpub().registerExternal("v2" + File.separator + id + File.separator + ver + File.separator + "index.html");
          }
        }
      }
      e = XMLUtil.getNextSibling(e);
    }

  }

  private void produceBaseProfile() throws Exception {
     
    for (TypeDefn e : page.getDefinitions().getTypes().values())
      produceTypeProfile(e);
    for (TypeDefn e : page.getDefinitions().getInfrastructure().values())
      produceTypeProfile(e);
    for (TypeDefn e : page.getDefinitions().getStructures().values())
      produceTypeProfile(e);
    for (ProfiledType c : page.getDefinitions().getConstraints().values())
      produceProfiledTypeProfile(c);
  }

  private void produceProfiledTypeProfile(ProfiledType pt) throws Exception {
    String fn = pt.getName() + ".profile.xml";
    Profile rp = pt.getProfile();
    
    new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + fn), rp, true, false);
    new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".json")), rp, true);

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + fn), new CSFile(Utilities.path(page.getFolders().dstDir, "examples", fn)));
    addToResourceFeed(rp, pt.getName().toLowerCase(), typeFeed);
    cloneToXhtml(pt.getName() + ".profile", "Profile for " + pt.getName(), false, "profile-instance:type:" + pt.getName());
    jsonToXhtml(pt.getName() + ".profile", "Profile for " + pt.getName(), resource2Json(rp), "profile-instance:type:" + pt.getName());
  }

  private void produceTypeProfile(TypeDefn type) throws Exception {
//    ProfileDefn p = new ProfileDefn();
//    p.putMetadata("id", type.getName());
//    p.putMetadata("name", "Basic Profile for " + type.getName());
//    p.putMetadata("author.name", "FHIR Specification");
//    p.putMetadata("author.ref", "http://hl7.org/fhir");
//    p.putMetadata("description", "Basic Profile for " + type.getName() + " for validation support");
//    p.putMetadata("status", "draft");
//    p.putMetadata("date", new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "US")).format(new Date()));
//    p.getElements().add(type);
//    ProfileGenerator pgen = new ProfileGenerator(page.getDefinitions());
//    String fn = "type-" + type.getName() + ".profile.xml";
//    Profile rp = pgen.generate(p, "type-" + type.getName() + ".profile", "<div>Type definition for " + type.getName() + " from <a href=\"http://hl7.org/fhir/datatypes.html#" + type.getName()
//        + "\">FHIR Specification</a></div>");
    
    String fn = type.getName() + ".profile.xml";
    Profile rp = type.getProfile();
    
    new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + fn), rp, true, false);
    new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(fn, ".json")), rp, true);

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + fn), new CSFile(Utilities.path(page.getFolders().dstDir, "examples", fn)));
    addToResourceFeed(rp, type.getName().toLowerCase(), typeFeed);
    // saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir+ "html"
    // + File.separator + "datatypes.html"));
    cloneToXhtml(type.getName() + ".profile", "Profile for " + type.getName(), false, "profile-instance:type:" + type.getName());
    jsonToXhtml(type.getName() + ".profile", "Profile for " + type.getName(), resource2Json(rp), "profile-instance:type:" + type.getName());
  }

  protected XmlPullParser loadXml(InputStream stream) throws Exception {
    BufferedInputStream input = new BufferedInputStream(stream);
    XmlPullParserFactory factory = XmlPullParserFactory.newInstance(System.getProperty(XmlPullParserFactory.PROPERTY_NAME), null);
    factory.setNamespaceAware(true);
    XmlPullParser xpp = factory.newPullParser();
    xpp.setInput(input, "UTF-8");
    xpp.next();
    return xpp;
  }

  protected int nextNoWhitespace(XmlPullParser xpp) throws Exception {
    int eventType = xpp.getEventType();
    while (eventType == XmlPullParser.TEXT && xpp.isWhitespace())
      eventType = xpp.next();
    return eventType;
  }

  private void checkFragments() throws Exception {
    List<String> errors = new ArrayList<String>();
    StringBuilder s = new StringBuilder();
    s.append("<tests>\r\n");
    int i = 0;
    for (Fragment f : fragments) {
      s.append("<test id=\"" + Integer.toString(i) + "\" page=\"" + f.getPage() + "\" type=\"" + f.getType() + "\">\r\n");
      s.append(f.getXml());
      s.append("</test>\r\n");
      i++;
    }
    s.append("</tests>\r\n");
    String err = javaReferencePlatform.checkFragments(page.getFolders().dstDir, s.toString(), false);
    if (err == null)
      throw new Exception("Unable to process outcome of checking fragments");
    if (!err.startsWith("<results"))
      throw new Exception(err);

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document errDoc = builder.parse(new ByteArrayInputStream(err.getBytes()));

    Element result = XMLUtil.getFirstChild(errDoc.getDocumentElement());

    while (result != null) {
      String id = result.getAttribute("id");
      String outcome = result.getAttribute("outcome");
      if (!"ok".equals(outcome)) {
        Fragment f = fragments.get(Integer.parseInt(id));
        String msg = "Fragment Error in page " + f.getPage() + ": " + result.getAttribute("msg") + " for\r\n" + f.getXml();
        page.log(msg, LogMessageType.Error);
        page.log("", LogMessageType.Error);
        errors.add(msg);
      }
      result = XMLUtil.getNextSibling(result);
    }
    if (errors.size() > 0)
      throw new Exception("Fragment Errors prevent publication from continuing");
  }

  private void produceZip() throws Exception {
    File f = new CSFile(page.getFolders().dstDir + "fhir-spec.zip");
    if (f.exists())
      f.delete();
    ZipGenerator zip = new ZipGenerator(page.getFolders().tmpResDir + "fhir-spec.zip");
    zip.addFiles(page.getFolders().dstDir, "site/", null, ".zip");
    zip.addFolder(Utilities.path(page.getFolders().rootDir, "tools", "html", ""), "site/", true);
    zip.addFileName("index.html", page.getFolders().srcDir + "redirect.html", false);
    zip.close();
    Utilities.copyFile(new CSFile(page.getFolders().tmpResDir + "fhir-spec.zip"), f);
  }

  private void produceSchemaZip() throws Exception {
    char sc = File.separatorChar;
    File f = new CSFile(page.getFolders().dstDir + "fhir-all-xsd.zip");
    if (f.exists())
      f.delete();
    ZipGenerator zip = new ZipGenerator(page.getFolders().tmpResDir + "fhir-all-xsd.zip");
    zip.addFiles(page.getFolders().dstDir, "", ".xsd", null);
    zip.addFiles(page.getFolders().dstDir, "", ".sch", null);
    zip.addFiles(page.getFolders().rootDir + "tools" + sc + "schematron" + sc, "", ".xsl", "");
    zip.close();
    Utilities.copyFile(new CSFile(page.getFolders().tmpResDir + "fhir-all-xsd.zip"), f);
  }

  private void produceResource1(ResourceDefn resource) throws Exception {
    File tmp = Utilities.createTempFile("tmp", ".tmp");
    String n = resource.getName().toLowerCase();

    XmlSpecGenerator gen = new XmlSpecGenerator(new FileOutputStream(tmp), n + "-definitions.html", null, page);
    gen.generate(resource.getRoot());
    gen.close();
    String xml = TextFile.fileToString(tmp.getAbsolutePath());

    xmls.put(n, xml);
    generateProfile(resource, n, xml);
  }

  private void produceResource2(ResourceDefn resource) throws Exception {
    File tmp = Utilities.createTempFile("tmp", ".tmp");
    String n = resource.getName().toLowerCase();
    String xml = xmls.get(n);

    TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(new FileOutputStream(tmp), page);
    tgen.generate(resource.getRoot(), page.getDefinitions().getBindings());
    tgen.close();
    String tx = TextFile.fileToString(tmp.getAbsolutePath());

    DictHTMLGenerator dgen = new DictHTMLGenerator(new FileOutputStream(tmp), page);
    dgen.generate(resource.getRoot());
    dgen.close();
    String dict = TextFile.fileToString(tmp.getAbsolutePath());

    MappingsGenerator mgen = new MappingsGenerator(page.getDefinitions());
    mgen.generate(resource);
    String mappings = mgen.getMappings();
    String mappingsList = mgen.getMappingsList();

    SvgGenerator svg = new SvgGenerator(page);
    svg.generate(resource, page.getFolders().dstDir + n + ".svg");

    for (RegisteredProfile p : resource.getProfiles())
      p.setResource(produceProfile(p.getDestFilename(), p.getProfile(), p.getFilepath(), resource.getName(), p.getExamples()));

    Profile profile = (Profile) profileFeed.getById("http://hl7.org/fhir/profile/" + resource.getName().toLowerCase()).getResource();
    for (Example e : resource.getExamples()) {
      try {
        processExample(e, resource, profile);
      } catch (Exception ex) {
        throw new Exception("processing " + e.getFileTitle(), ex);
        // throw new Exception(ex.getMessage()+" processing "+e.getFileTitle());
      }
    }
    try {
      processQuestionnaire(resource, profile);
    } catch (Exception e) {
      page.log("Questionnaire Generation Failed: "+e.getMessage(), LogMessageType.Error);
    }

    String prefix = page.getBreadCrumbManager().getIndexPrefixForResource(resource.getName());
    SectionTracker st = new SectionTracker(prefix);
    st.start("");
    page.getSectionTrackerCache().put(n, st);

    String src = TextFile.fileToString(page.getFolders().srcDir + "template.html");
    src = insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "resource", n + ".html"), st, n + ".html");
    TextFile.stringToFile(src, page.getFolders().dstDir + n + ".html");

    String pages = page.getIni().getStringProperty("resource-pages", n);
    if (!Utilities.noString(pages)) {
      for (String p : pages.split(",")) {
        producePage(p, n);
      }
    }

    src = TextFile.fileToString(page.getFolders().srcDir + "template-examples.html");
    TextFile.stringToFile(
        insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "res-Examples", n + "-examples.html"), st, n + "-examples.html"),
        page.getFolders().dstDir + n + "-examples.html");
    page.getEpub().registerFile(n + "-examples.html", "Examples for " + resource.getName(), EPubManager.XHTML_TYPE);
    src = TextFile.fileToString(page.getFolders().srcDir + "template-definitions.html");
    TextFile.stringToFile(
        insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "res-Formal Definitions", n + "-definitions.html"), st, n
            + "-definitions.html"), page.getFolders().dstDir + n + "-definitions.html");
    page.getEpub().registerFile(n + "-definitions.html", "Formal Definitions for " + resource.getName(), EPubManager.XHTML_TYPE);
    src = TextFile.fileToString(page.getFolders().srcDir + "template-mappings.html");
    TextFile.stringToFile(
        insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "res-Mappings", n + "-mappings.html"), st, n + "-mappings.html"),
        page.getFolders().dstDir + n + "-mappings.html");
    page.getEpub().registerFile(n + "-mappings.html", "Formal Mappings for " + resource.getName(), EPubManager.XHTML_TYPE);
    src = TextFile.fileToString(page.getFolders().srcDir + "template-explanations.html");
    TextFile.stringToFile(
        insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "res-Design Notes", n + "-explanations.html"), st, n
            + "-explanations.html"), page.getFolders().dstDir + n + "-explanations.html");
    page.getEpub().registerFile(n + "-explanations.html", "Design Notes for " + resource.getName(), EPubManager.XHTML_TYPE);
    src = TextFile.fileToString(page.getFolders().srcDir + "template-profiles.html");
    TextFile.stringToFile(
        insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "res-Profiles", n + "-profiles.html"), st, n + "-profiles.html"),
        page.getFolders().dstDir + n + "-profiles.html");
    page.getEpub().registerFile(n + "-profiles.html", "Profiles for " + resource.getName(), EPubManager.XHTML_TYPE);

    if (!resource.getOperations().isEmpty()) {
      src = TextFile.fileToString(page.getFolders().srcDir + "template-operations.html");
      TextFile.stringToFile(
          insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "res-Operations", n + "-operations.html"), st, n
              + "-operations.html"), page.getFolders().dstDir + n + "-operations.html");
      page.getEpub().registerFile(n + "-operations.html", "Operations for " + resource.getName(), EPubManager.XHTML_TYPE);
      
      for (Operation t : resource.getOperations().values()) {
        produceOperation(resource, t);
      }
    }
    src = TextFile.fileToString(page.getFolders().srcDir + "template-book.html").replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "resource", n + ".html");
    cachePage(n + ".html", src, "Resource " + resource.getName());
//    src = TextFile.fileToString(page.getFolders().srcDir + "template-book-ex.html").replace("<body>", "<body style=\"margin: 10px\">");
//    src = page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "res-Examples");
    // cachePage(n + "Ex.html", src,
    // "Resource Examples for "+resource.getName());
    src = TextFile.fileToString(page.getFolders().srcDir + "template-book-defn.html").replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList, "res-Formal Definitions", n + "-definitions.html");
    cachePage(n + "-definitions.html", src, "Resource Definitions for " + resource.getName());

    // xml to json
    // todo - fix this up
    // JsonGenerator jsongen = new JsonGenerator();
    // jsongen.generate(new CSFile(page.getFolders().dstDir+n+".xml"), new
    // File(page.getFolders().dstDir+n+".json"));

    tmp.delete();
    // because we'll pick up a little more information as we process the
    // resource
    Profile p = generateProfile(resource, n, xml);
    generateQuestionnaire(n, p);
  }

  private void produceOperation(ResourceDefn r, Operation op) throws Exception {
    String name = r.getName().toLowerCase()+"-"+op.getName();
    OperationDefinition opd = new OperationDefinition();
//    opd.setIdentifierSimple("http://hl7.org/fhir/operation-"+r.getname().toLowerCase()+"-"+op.getName());
    opd.setTitleSimple(op.getTitle());
    opd.setPublisherSimple("HL7 (FHIR Project)");
    opd.getTelecom().add(org.hl7.fhir.instance.model.Factory.newContact(ContactSystem.url, "http://hl7.org/fhir"));
    opd.getTelecom().add(org.hl7.fhir.instance.model.Factory.newContact(ContactSystem.email, "fhir@lists.hl7.org"));
    opd.setDescriptionSimple(op.getDoco());
    opd.setStatusSimple(ResourceProfileStatus.draft);
    opd.setDateSimple(new DateAndTime(page.getGenDate()));
    opd.setKindSimple(OperationKind.operation);
    opd.setNameSimple(op.getName());
    opd.setNotesSimple(op.getFooter());
    opd.setSystemSimple(op.isSystem());
    if (op.isType())
      opd.getType().add(new CodeType().setValue(r.getName()));
    opd.setInstanceSimple(op.isInstance());
    for (OperationParameter p : op.getParameters()) {
      OperationDefinitionParameterComponent pp = new OperationDefinitionParameterComponent();
      pp.setNameSimple(p.getName());
      if (p.getUse().equals("in"))
        pp.setUseSimple(OperationParameterUse.in);
      else if (p.getUse().equals("out"))
        pp.setUseSimple(OperationParameterUse.out);
      else
        throw new Exception("Unable to determine parameter use: "+p.getUse()); // but this is validated elsewhere
      // todo: min and max
      pp.setDocumentationSimple(p.getDoc());
      pp.setMinSimple(p.getMin());
      pp.setMaxSimple(p.getMax());
      Coding cc = new Coding();
      cc.setSystemSimple("http://hl7.org/fhir/vs/defined-types");
      cc.setCodeSimple(p.getType());
      pp.setType(cc);
      opd.getParameter().add(pp);
    }
    NarrativeGenerator gen = new NarrativeGenerator("", page.getWorkerContext());
    gen.generate(opd);

    new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + "operation-" + name + ".xml"), opd, true, true);
    cloneToXhtml("operation-" + name + "", "Operation Definition", true, "resource-instance:OperationDefinition");
    new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + "operation-" + name + ".json"), opd, true);
    jsonToXhtml("operation-" + name, "Operation Definition", resource2Json(opd), "resource-instance:OperationDefinition");
    Utilities.copyFile(new CSFile(page.getFolders().dstDir + "operation-" + name + ".xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + "operation-" + name + ".xml"));
    if (buildFlags.get("all"))
      addToResourceFeed(opd, "operation-" + name + "", profileFeed);

    // now, we create an html page from the narrative
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example.html").replace("<%example%>", new XhtmlComposer().compose(opd.getText().getDiv()));
    html = page.processPageIncludes("operation-" + name + ".html", html, "resource-instance:OperationDefinition", null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + "operation-" + name + ".html");
    // head =
    // "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\r\n<head>\r\n <title>"+Utilities.escapeXml(e.getDescription())+"</title>\r\n <link rel=\"Stylesheet\" href=\"fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n"+
    // "</head>\r\n<body>\r\n<p>&nbsp;</p>\r\n<p>"+Utilities.escapeXml(e.getDescription())+"</p>\r\n"+
    // "<p><a href=\""+n+".xml.html\">XML</a> <a href=\""+n+".json.html\">JSON</a></p>\r\n";
    // tail = "\r\n</body>\r\n</html>\r\n";
    // TextFile.stringToFile(head+narrative+tail, page.getFolders().dstDir + n +
    // ".html");
    page.getEpub().registerExternal("operation-" + name + ".html");
    page.getEpub().registerExternal("operation-" + name + ".json.html");
    page.getEpub().registerExternal("operation-" + name + ".xml.html");
  }

  private void generateQuestionnaire(String n, Profile p) throws Exception {
    QuestionnaireBuilder b = new QuestionnaireBuilder(page.getWorkerContext());
    Questionnaire q = b.buildQuestionnaire(p);
    
    new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + n + ".questionnaire.xml"), q, true, false);
    new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + n + ".questionnaire.json"), q, true);

  }

  private void jsonToXhtml(String n, String description, String json, String pageType) throws Exception {
    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(description) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example-json.html").replace("<%example%>", json);
    html = page.processPageIncludes(n + ".json.html", html, pageType, null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".json.html");
//    page.getEpub().registerFile(n + ".json.html", description, EPubManager.XHTML_TYPE);
    page.getEpub().registerExternal(n + ".json.html");
  }

  private void cloneToXhtml(String n, String description, boolean adorn, String pageType) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();

    Document xdoc = builder.parse(new CSFileInputStream(new CSFile(page.getFolders().dstDir + n + ".xml")));
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(page.getDefinitions()));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, n.toUpperCase().substring(0, 1) + n.substring(1), description, 0, adorn, n + ".xml.html");
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example-xml.html").replace("<%example%>", b.toString());
    html = page.processPageIncludes(n + ".xml.html", html, pageType, null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".xml.html");

//    page.getEpub().registerFile(n + ".xml.html", description, EPubManager.XHTML_TYPE);
    page.getEpub().registerExternal(n + ".xml.html");
  }

  private void processQuestionnaire(ResourceDefn resource, Profile profile) throws Exception {
    Questionnaire q = new QuestionnaireBuilder(page.getWorkerContext()).buildQuestionnaire(profile);
    
    new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + resource.getName().toLowerCase() + ".questionnaire.json"), q, true);
    new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + resource.getName().toLowerCase() + ".questionnaire.xml"), q, true);
    
    String json = "<div class=\"example\">\r\n<p>Generated Questionnaire for "+resource.getName()+"</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(new JsonComposer().composeString(q, true)) + "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example-json.html").replace("<%example%>", json);
    html = page.processPageIncludes(resource.getName().toLowerCase() + ".questionnaire.json.html", html, "resource-questionnaire:" + resource.getName(), null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + resource.getName().toLowerCase() + ".questionnaire.json.html");

    String xml = "<div class=\"example\">\r\n<p>Generated Questionnaire for "+resource.getName()+"</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(new XmlComposer().composeString(q, true)) + "\r\n</pre>\r\n</div>\r\n";
    html = TextFile.fileToString(page.getFolders().srcDir + "template-example-xml.html").replace("<%example%>", xml);
    html = page.processPageIncludes(resource.getName().toLowerCase() + ".questionnaire.xml.html", html, "resource-questionnaire:" + resource.getName(), null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + resource.getName().toLowerCase() + ".questionnaire.xml.html");

    File tmpTransform = Utilities.createTempFile("tmp", ".html");
    Utilities.saxonTransform(
          Utilities.path(page.getFolders().rootDir, "implementations", "xmltools"), // directory for xslt references  
          page.getFolders().dstDir + resource.getName().toLowerCase() + ".questionnaire.xml",  // source to run xslt on 
          Utilities.path(page.getFolders().rootDir, "implementations", "xmltools", "QuestionnaireToHTML.xslt"), // xslt file to run 
          tmpTransform.getAbsolutePath(), // file to produce
          this // handle to self to implement URI resolver for terminology fetching
    );
       
    // now, generate the form
    html = TextFile.fileToString(page.getFolders().srcDir + "template-questionnaire.html").replace("<%questionnaire%>", loadHtmlForm(tmpTransform.getAbsolutePath()));
    html = page.processPageIncludes(resource.getName().toLowerCase() + ".questionnaire.html", html, "resource-questionnaire:" + resource.getName(), null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + resource.getName().toLowerCase() + ".questionnaire.html");
    
    page.getEpub().registerExternal(resource.getName().toLowerCase() + ".questionnaire.html");
    page.getEpub().registerExternal(resource.getName().toLowerCase() + ".questionnaire.json.html");
    page.getEpub().registerExternal(resource.getName().toLowerCase() + ".questionnaire.xml.html");
  }
  
  private String loadHtmlForm(String path) throws Exception {
    String form = TextFile.fileToString(path);
    form = form.replace("<!--header insertion point-->", "\r\n"+TextFile.fileToString(Utilities.path(page.getFolders().srcDir, "newheader.html"))+"\r\n");
    form = form.replace("<!--body top insertion point-->", "\r\n"+TextFile.fileToString(Utilities.path(page.getFolders().srcDir, "newnavbar.html"))+"<p>\r\nThis is an example form generated from the questionnaire. See also the <a href=\"<%name%>.xml.html\">XML</a> or <a href=\"<%name%>.json.html\">JSON</a> format\r\n</p>\r\n");
    form = form.replace("<!--body bottom insertion point-->", "\r\n"+TextFile.fileToString(Utilities.path(page.getFolders().srcDir, "newfooter.html"))+"\r\n");
    return form;
  }

  private void processExample(Example e, ResourceDefn resource, Profile profile) throws Exception {
    if (e.getType() == ExampleType.Tool)
      return;

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc;
    String narrative;
    
    String n = e.getFileTitle();


      if (!e.getPath().exists())
        throw new Exception("unable to find example file");

      // strip the xsi: stuff. seems to need double processing in order to
      // delete namespace crap
      xdoc = e.getXml() == null ? builder.parse(new CSFileInputStream(e.getPath())) : e.getXml();
      XmlGenerator xmlgen = new XmlGenerator();
      if (xdoc.getDocumentElement().getLocalName().equals("feed"))
        xmlgen.generate(xdoc.getDocumentElement(), new CSFile(page.getFolders().dstDir + n + ".xml"), "http://www.w3.org/2005/Atom", xdoc.getDocumentElement()
            .getLocalName());
      else {
        xmlgen.generate(xdoc.getDocumentElement(), new CSFile(page.getFolders().dstDir + n + ".xml"), "http://hl7.org/fhir", xdoc.getDocumentElement()
            .getLocalName());
      }

      // check the narrative. We generate auto-narrative. If the resource didn't
      // have it's own original narrative, then we save it anyway
      // todo: this uses the version of the resource in the generator, not the current one. This needs to be moved to the compiled code when it's stable
      org.hl7.fhir.instance.model.Resource r;
      try {
        XmlParser xml = new XmlParser();
        XhtmlNode combined = new XhtmlNode(NodeType.Element, "div");
        ResourceOrFeed rf = xml.parseGeneral(new CSFileInputStream(page.getFolders().dstDir + n + ".xml"));
        boolean wantSave = false;
        if (rf.getFeed() != null) {
          for (AtomEntry<? extends org.hl7.fhir.instance.model.Resource> ae : rf.getFeed().getEntryList()) {
            r = ae.getResource();
            wantSave = wantSave || (r.getText() == null || r.getText().getDiv() == null);
            if (true /*(r.getText() == null || r.getText().getDiv() == null) || !web */) {
              NarrativeGenerator gen = new NarrativeGenerator("", page.getWorkerContext().clone(new SpecificationInternalClient(page, rf.getFeed())));
              gen.generate(r);
            }
            if (r.getText() != null && r.getText().getDiv() != null) {
              combined.getChildNodes().add(r.getText().getDiv());
              combined.addTag("hr");
            }  
            if (rf.getFeed().isDocument()) {
              NarrativeGenerator gen = new NarrativeGenerator("", page.getWorkerContext().clone(new SpecificationInternalClient(page, null)));
              combined = gen.generateDocumentNarrative(rf.getFeed());
            }
          }
          narrative = new XhtmlComposer().compose(combined);
          if (true /*wantSave*/) {
            new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + n + ".xml"), rf.getFeed(), true, true);
            xdoc = builder.parse(new CSFileInputStream(page.getFolders().dstDir + n + ".xml"));
          }
          r = null;
        } else {
          r = rf.getResource();
          wantSave = r.getText() == null || r.getText().getDiv() == null;
          if (wantSave/* || !web */) {
            NarrativeGenerator gen = new NarrativeGenerator("", page.getWorkerContext().clone(new SpecificationInternalClient(page, null)));
            gen.generate(r);
          }
          if (r.getText() != null && r.getText().getDiv() != null) {
            narrative = new XhtmlComposer().compose(r.getText().getDiv());
            if (true /*wantSave*/) {
              new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + n + ".xml"), r, true, true);
              xdoc = builder.parse(new CSFileInputStream(page.getFolders().dstDir + n + ".xml"));
            }
          } else
            narrative = "&lt;-- No Narrative for this resource --&gt;";
        }
      } catch (Exception ex) {
        XhtmlNode xhtml = new XhtmlNode(NodeType.Element, "div");
        xhtml.addTag("p").setAttribute("style", "color: maroon").addText("Error processing narrative: " + ex.getMessage());
        narrative = new XhtmlComposer().compose(xhtml);
        r = null;
      }

      if (r instanceof ValueSet) {
        ValueSet vs = (ValueSet) r;
        new ValueSetValidator(page.getWorkerContext()).validate(vs, false);
        if (vs.getIdentifier() == null)
          throw new Exception("Value set example " + e.getPath().getAbsolutePath() + " has no identifier");
        AtomEntry<ValueSet> ae = new AtomEntry<ValueSet>();
        ae.getLinks().put("self", n + ".html");
        ae.getLinks().put("path", n + ".html");
        ae.setResource(vs);
        if (vs.getIdentifierSimple().startsWith("http:"))
          page.getValueSets().put(vs.getIdentifierSimple(), ae);
        if (vs.getDefine() != null) {
          page.getCodeSystems().put(vs.getDefine().getSystemSimple().toString(), ae);
        }
        addToResourceFeed(vs, vs.getIdentifierSimple(), valueSetsFeed);
        page.getDefinitions().getValuesets().put(vs.getIdentifierSimple(), vs);
        if (vs.getDefine() != null) {
          page.getDefinitions().getCodeSystems().put(vs.getDefine().getSystemSimple(), vs);
        }
      } else if (r instanceof ConceptMap) {
        ConceptMap cm = (ConceptMap) r;
        new ConceptMapValidator(page.getDefinitions(), e.getPath().getAbsolutePath()).validate(cm, false);
        if (cm.getIdentifier() == null)
          throw new Exception("Value set example " + e.getPath().getAbsolutePath() + " has no identifier");
        addToResourceFeed(cm, cm.getIdentifierSimple(), conceptMapsFeed);
        page.getDefinitions().getConceptMaps().put(cm.getIdentifierSimple(), cm);
        AtomEntry ae = new AtomEntry();
        ae.getLinks().put("self", n + ".html");
        ae.getLinks().put("path", n + ".html");
        ae.setResource(cm);
        page.getConceptMaps().put(cm.getIdentifierSimple(), ae);
      }

    String json;
    // generate the json version (use the java reference platform)
    try {
     json = javaReferencePlatform.convertToJson(page.getFolders().dstDir, page.getFolders().dstDir + n + ".xml", page.getFolders().dstDir + n + ".json");
    } catch (Throwable t) {
      System.out.println("Error processing " + page.getFolders().dstDir + n + ".xml");
      t.printStackTrace(System.err);
      TextFile.stringToFile(t.getMessage(), page.getFolders().dstDir + n + ".json");
      json = t.getMessage();
    }

    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml(e.getDescription()) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)
        + "\r\n</pre>\r\n</div>\r\n";
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example-json.html").replace("<%example%>", json);
    html = page.processPageIncludes(n + ".json.html", html, "resource-instance:" + resource.getName(), null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".json.html");
    
    page.getEpub().registerExternal(n + ".json.html");
    e.setJson(json);

    // reload it now, xml to xhtml of xml
    builder = factory.newDocumentBuilder();
    xdoc = builder.parse(new CSFileInputStream(new CSFile(page.getFolders().dstDir + n + ".xml")));
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(page.getDefinitions()));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, n.toUpperCase().substring(0, 1) + n.substring(1), Utilities.noString(e.getId()) ? e.getDescription() : e.getDescription()
        + " (id = \"" + e.getId() + "\")", 0, true, n + ".xml.html");
    html = TextFile.fileToString(page.getFolders().srcDir + "template-example-xml.html").replace("<%example%>", b.toString());
    html = page.processPageIncludes(n + ".xml.html", html, "resource-instance:" + resource.getName(), null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".xml.html");
    if (e.isInBook()) {
      XhtmlDocument d = new XhtmlParser().parse(new CSFileInputStream(page.getFolders().dstDir + n + ".xml.html"), "html");
      XhtmlNode pre = d.getElement("html").getElement("body").getElement("div");
      e.setXhtm(b.toString());
    }
    if (!Utilities.noString(e.getId()))
      Utilities.copyFile(new CSFile(page.getFolders().dstDir + n + ".xml"),
          new CSFile(page.getFolders().dstDir + "examples" + File.separator + n + "(" + e.getId() + ").xml"));
    else
      Utilities.copyFile(new CSFile(page.getFolders().dstDir + n + ".xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + n + ".xml"));

    // now, we create an html page from the narrative
    html = TextFile.fileToString(page.getFolders().srcDir + "template-example.html").replace("<%example%>", narrative);
    html = page.processPageIncludes(n + ".html", html, "resource-instance:" + resource.getName(), null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + n + ".html");
    // head =
    // "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\r\n<head>\r\n <title>"+Utilities.escapeXml(e.getDescription())+"</title>\r\n <link rel=\"Stylesheet\" href=\"fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n"+
    // "</head>\r\n<body>\r\n<p>&nbsp;</p>\r\n<p>"+Utilities.escapeXml(e.getDescription())+"</p>\r\n"+
    // "<p><a href=\""+n+".xml.html\">XML</a> <a href=\""+n+".json.html\">JSON</a></p>\r\n";
    // tail = "\r\n</body>\r\n</html>\r\n";
    // TextFile.stringToFile(head+narrative+tail, page.getFolders().dstDir + n +
    // ".html");
    page.getEpub().registerExternal(n + ".html");
    page.getEpub().registerExternal(n + ".json.html");
    page.getEpub().registerExternal(n + ".xml.html");
  }

  private String buildLoincExample(String filename) throws FileNotFoundException, Exception {
    LoincToDEConvertor conv = new LoincToDEConvertor();
    conv.setDefinitions(Utilities.path(page.getFolders().srcDir, "loinc", "loincS.xml"));
    conv.process();
    XmlComposer xml = new XmlComposer();
    xml.compose(new FileOutputStream(Utilities.path(page.getFolders().dstDir, filename+".xml")), conv.getFeed(), false);
    JsonComposer json = new JsonComposer();
    json.compose(new FileOutputStream(Utilities.path(page.getFolders().dstDir, filename+".json")), conv.getFeed(), false);
    return "Loinc Narrative";
  }

  private Profile generateProfile(ResourceDefn root, String n, String xmlSpec) throws Exception, FileNotFoundException {
    Profile rp = root.getProfile();
    page.getProfiles().put(root.getName(), genWrapper(rp));
    new XmlComposer().compose(new FileOutputStream(page.getFolders().dstDir + n + ".profile.xml"), rp, true, false);
    new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + n + ".profile.json"), rp, true);

    Utilities.copyFile(new CSFile(page.getFolders().dstDir + n + ".profile.xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + n
        + ".profile.xml"));
    if (buildFlags.get("all"))
      addToResourceFeed(rp, root.getName().toLowerCase(), profileFeed);
    saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir + "html" + File.separator + n + ".html"));
    cloneToXhtml(n + ".profile", "Profile for " + n, true, "profile-instance:resource:" + root.getName());
    jsonToXhtml(n + ".profile", "Profile for " + n, resource2Json(rp), "profile-instance:resource:" + root.getName());
    return rp;
  }

  private void saveAsPureHtml(Profile resource, FileOutputStream stream) throws Exception {
    XhtmlDocument html = new XhtmlDocument();
    html.setNodeType(NodeType.Document);
    html.addComment("Generated by automatically by FHIR Tooling");
    XhtmlNode doc = html.addTag("html");
    XhtmlNode head = doc.addTag("head");
    XhtmlNode work = head.addTag("title");
    work.addText("test title");
    work = head.addTag("link");
    work.setAttribute("rel", "Stylesheet");
    work.setAttribute("href", "/css/fhir.css");
    work.setAttribute("type", "text/css");
    work.setAttribute("media", "screen");
    work = doc.addTag("body");
    if ((resource.getText() != null) && (resource.getText().getDiv() != null)) {
      work.getAttributes().putAll(resource.getText().getDiv().getAttributes());
      work.getChildNodes().addAll(resource.getText().getDiv().getChildNodes());
    }
    XhtmlComposer xml = new XhtmlComposer();
    xml.setPretty(false);
    xml.compose(stream, html);
  }

  private void addToResourceFeed(org.hl7.fhir.instance.model.Resource resource, String id, AtomFeed dest) throws Exception {
    AtomEntry<? extends org.hl7.fhir.instance.model.Resource> byId = dest.getById("http://hl7.org/fhir/profile/" + id);
    if (byId != null)
      dest.getEntryList().remove(byId);

    AtomEntry e = new AtomEntry();
    e.setId("http://hl7.org/fhir/profile/" + id);
    e.getLinks().put("self", "http://hl7.org/implement/standards/fhir/" + id + ".profile.xml");
    e.setTitle("\"" + id + "\" as a profile (to help derivation)");
    e.setUpdated(new DateAndTime(page.getGenDate()));
    e.setPublished(new DateAndTime(page.getGenDate()));
    e.setAuthorName("HL7, Inc (FHIR Project)");
    e.setAuthorUri("http://hl7.org/fhir");
    e.setResource(resource);
    if (resource.getText() == null || resource.getText().getDiv() == null)
      throw new Exception("Example Resource " + id + " does not have any narrative");
    e.setSummary(resource.getText().getDiv());
    dest.getEntryList().add(e);
  }

  private void addToResourceFeed(ValueSet vs, String id, AtomFeed dest) throws Exception {
    String wid;
    if (id.contains(":"))
      wid = id;
    else
      wid = "http://hl7.org/fhir/vs/" + id;
    
    if (dest.getById(wid) != null)
      throw new Exception("Attempt to add duplicate value set " + id);

    AtomEntry e = new AtomEntry();
    e.setId(wid);
    e.getLinks().put("self", "http://hl7.org/implement/standards/fhir/valueset/" + id);
    e.setTitle("Valueset \"" + id + "\" to support automated processing");
    e.setUpdated(new DateAndTime(page.getGenDate()));
    e.setPublished(new DateAndTime(page.getGenDate()));
    e.setAuthorName("HL7, Inc (FHIR Project)");
    e.setAuthorUri("http://hl7.org/fhir");
    e.setResource(vs);
    if (vs.getText() == null || vs.getText().getDiv() == null)
      throw new Exception("Example Value Set " + id + " does not have any narrative");
    e.setSummary(vs.getText().getDiv());
    dest.getEntryList().add(e);
  }

  private void addToResourceFeed(ConceptMap cm, String id, AtomFeed dest) throws Exception {
    if (dest.getById("http://hl7.org/fhir/conceptmap/" + id) != null)
      throw new Exception("Attempt to add duplicate concept map " + id);

    AtomEntry<ConceptMap> e = new AtomEntry<ConceptMap>();
    e.setId("http://hl7.org/fhir/conceptmap/" + id);
    e.getLinks().put("self", "http://hl7.org/implement/standards/fhir/conceptmap/" + id);
    e.setTitle("Concept Map \"" + id + "\" to support automated processing");
    e.setUpdated(new DateAndTime(page.getGenDate()));
    e.setPublished(new DateAndTime(page.getGenDate()));
    e.setAuthorName("HL7, Inc (FHIR Project)");
    e.setAuthorUri("http://hl7.org/fhir");
    e.setResource(cm);
    if (cm.getText() == null || cm.getText().getDiv() == null)
      throw new Exception("Example Concept Map " + id + " does not have any narrative");
    e.setSummary(cm.getText().getDiv());
    dest.getEntryList().add(e);
  }

  private void addToResourceFeed(Conformance conf, String id, AtomFeed dest) throws Exception {
    if (dest.getById(id) != null)
      throw new Exception("Attempt to add duplicate value set " + id);

    AtomEntry<Conformance> e = new AtomEntry<Conformance>();
    e.setId("http://hl7.org/fhir/conformance/" + id);
    e.getLinks().put("self", "http://hl7.org/implement/standards/fhir/conformance/" + id);
    e.setTitle("\"" + id + "\" - to help with system development");
    e.setUpdated(new DateAndTime(page.getGenDate()));
    e.setPublished(new DateAndTime(page.getGenDate()));
    e.setAuthorName("HL7, Inc (FHIR Project)");
    e.setAuthorUri("http://hl7.org/fhir");
    e.setResource(conf);
    e.setSummary(conf.getText().getDiv());
    dest.getEntryList().add(e);
  }

  private Profile produceProfile(String filename, ProfileDefn profile, String filePath, String master, Map<String, Example> examples) throws Exception {
    File tmp = Utilities.createTempFile("tmp", ".tmp");
    String title = filename.contains(".") ? filename.substring(0, filename.lastIndexOf(".")) : filename;

    // you have to validate a profile, because it has to be merged with it's
    // base resource to fill out all the missing bits
//    validateProfile(profile);

    XmlSpecGenerator gen = new XmlSpecGenerator(new FileOutputStream(tmp), null, "http://hl7.org/fhir/", page);
    gen.generate(profile, "http://hl7.org/fhir/Profile/"+title);
    gen.close();
    String xml = TextFile.fileToString(tmp.getAbsolutePath());

    ProfileGenerator pgen = new ProfileGenerator(page.getDefinitions(), page.getWorkerContext());
    Profile p = pgen.generate(profile, title, xml, page.getGenDate());
    XmlComposer comp = new XmlComposer();
    comp.compose(new FileOutputStream(page.getFolders().dstDir + title + ".profile.xml"), p, true, false);
    Utilities.copyFile(new CSFile(page.getFolders().dstDir + title + ".profile.xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + title
        + ".profile.xml"));
    JsonComposer jcomp = new JsonComposer();
    jcomp.compose(new FileOutputStream(page.getFolders().dstDir + title + ".profile.json"), p, true);
    // Utilities.copyFile(new CSFile(page.getFolders().dstDir + title +
    // ".profile.json"), new CSFile(page.getFolders().dstDir + "examples" +
    // File.separator + title + ".profile.json"));

    TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(new FileOutputStream(tmp), page);
    tgen.generate(profile, page.getDefinitions().getBindings());
    tgen.close();
    String tx = TextFile.fileToString(tmp.getAbsolutePath());

    String introAndNotesPath = Utilities.path(page.getFolders().rootDir, "profiles");
    if (master != null)
      introAndNotesPath = Utilities.path(page.getFolders().rootDir, "source", master.toLowerCase());
    
    String intro = null;
    if (profile.getMetadata().containsKey("introduction") && !Utilities.noString(profile.getMetadata().get("introduction").get(0))) {
      intro = page.loadXmlNotesFromFile(introAndNotesPath + File.separator + profile.getMetadata().get("introduction").get(0), false, null, null);
    }
    String notes = null;
    if (profile.getMetadata().containsKey("notes") && !Utilities.noString(profile.getMetadata().get("notes").get(0)))
      notes = page.loadXmlNotesFromFile(introAndNotesPath + File.separator + profile.getMetadata().get("notes").get(0), false, null, null);

    String exXml = "<p><i>No Example Provided</i></p>";
    if (examples != null) {
      for (String en : examples.keySet()) {
        String ep = examples.get(en).getPath().getAbsolutePath();
        String n = Utilities.changeFileExt(en, "");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xdoc = builder.parse(new CSFileInputStream(ep));
        // strip namespace - see below
        XmlGenerator xmlgen = new XmlGenerator();
        File dst = new File(page.getFolders().dstDir + en);
        xmlgen.generate(xdoc.getDocumentElement(), dst, xdoc.getDocumentElement().getLocalName().equals("feed") ? "http://www.w3.org/2005/Atom"
            : "http://hl7.org/fhir", xdoc.getDocumentElement().getLocalName());
        builder = factory.newDocumentBuilder();
        xdoc = builder.parse(new CSFileInputStream(dst.getAbsolutePath()));
        XhtmlGenerator xhtml = new XhtmlGenerator(null);
        exXml = xhtml.generateInsert(xdoc, "Example for Profile " + profile.metadata("name"), null);
        cloneToXhtml(n, "Example for Profile " + profile.metadata("name"), true, "profile-instance:example");

        String json;
        // generate the json version (use the java reference platform)
        try {
          json = javaReferencePlatform.convertToJson(page.getFolders().dstDir, page.getFolders().dstDir + n + ".xml", page.getFolders().dstDir + n + ".json");
        } catch (Throwable t) {
          System.out.println("Error processing " + page.getFolders().dstDir + n + ".xml");
          t.printStackTrace(System.err);
          TextFile.stringToFile(t.getMessage(), page.getFolders().dstDir + n + ".json");
          json = t.getMessage();
        }

        String head = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\r\n<head>\r\n <title>"
            + Utilities.escapeXml("Example for Profile " + profile.metadata("name"))
            + "</title>\r\n <link rel=\"Stylesheet\" href=\"fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n"
            + "</head>\r\n<body>\r\n<p>&nbsp;</p>\r\n<div class=\"example\">\r\n<p>" + Utilities.escapeXml("Example for Profile " + profile.metadata("name"))
            + "</p>\r\n<p><a href=\"" + n + ".json\">Raw JSON</a></p>\r\n<pre class=\"json\">\r\n";
        String tail = "\r\n</pre>\r\n</div>\r\n</body>\r\n</html>\r\n";
        TextFile.stringToFile(head + Utilities.escapeXml(json) + tail, page.getFolders().dstDir + n + ".json.html");
        page.getEpub().registerFile(n + ".json.html", "Example for Profile " + profile.metadata("name"), EPubManager.XHTML_TYPE);
      }
    }
    //
    // DictHTMLGenerator dgen = new DictHTMLGenerator(new
    // FileOutputStream(tmp));
    // dgen.generate(root);
    // String dict = Utilities.fileToString(tmp.getAbsolutePath());
    //
    //
    // File xmlf = new
    // File(page.getFolders().srcDir+n+File.separatorChar+"example.xml");
    // File umlf = new CSFile(page.getFolders().imgDir+n+".png");
    //
    String src = TextFile.fileToString(page.getFolders().srcDir + "template-profile.html");
    src = page.processProfileIncludes(filename, profile, xml, tx, src, exXml, intro, notes, master, title + ".html", null, filename, examples);
    page.getEpub().registerFile(title + ".html", "Profile " + profile.getSource().getNameSimple(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + title + ".html");
    
    // now, generate a page for each structure definition
    for (ProfileStructureComponent s : profile.getSource().getStructure()) {
      String fn = Utilities.changeFileExt(filename, "."+Utilities.getFileNameForName(s.getNameSimple()))+".html";
      src = TextFile.fileToString(page.getFolders().srcDir + "template-profile-constraint.html");
      src = page.processProfileIncludes(fn, profile, "", tx, src, exXml, intro, notes, master, title + ".html", s, filename, examples);
      page.getEpub().registerFile(fn, "Profile " + profile.getSource().getNameSimple()+ " structure " +s.getNameSimple(), EPubManager.XHTML_TYPE);
      TextFile.stringToFile(src, page.getFolders().dstDir + fn);
    }
    
    src = TextFile.fileToString(page.getFolders().srcDir + "template-profile-mappings.html");
    src = page.processProfileIncludes(filename, profile, xml, tx, src, exXml, intro, notes, master, title + ".html", null, filename, examples);
    page.getEpub().registerFile(title + "-mappings.html", "Mappings for Profile " + profile.getSource().getNameSimple(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + title + "-mappings.html");
    
    src = TextFile.fileToString(page.getFolders().srcDir + "template-profile-examples.html");
    src = page.processProfileIncludes(filename, profile, xml, tx, src, exXml, intro, notes, master, title + ".html", null, filename, examples);
    page.getEpub().registerFile(title + "-examples.html", "Examples for Profile " + profile.getSource().getNameSimple(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + title + "-examples.html");
    
    src = TextFile.fileToString(page.getFolders().srcDir + "template-profile-definitions.html");
    src = page.processProfileIncludes(filename, profile, xml, tx, src, exXml, intro, notes, master, title + ".html", null, filename, examples);
    page.getEpub().registerFile(title + "-definitions.html", "Definitions for Profile " + profile.getSource().getNameSimple(), EPubManager.XHTML_TYPE);
    TextFile.stringToFile(src, page.getFolders().dstDir + title + "-definitions.html");
    
    new ReviewSpreadsheetGenerator().generate(page.getFolders().dstDir + title + "-review.xmlss", "HL7 FHIR Project", page.getGenDate(), profile.getSource());
    
    //
    // src = Utilities.fileToString(page.getFolders().srcDir +
    // "template-print.html").replace("<body>",
    // "<body style=\"margin: 20px\">");
    // src = processResourceIncludes(n, root, xml, tx, dict, src);
    // Utilities.stringToFile(src, page.getFolders().dstDir +
    // "print-"+n+".html");
    // Utilities.copyFile(umlf, new
    // File(page.getFolders().dstDir+n+".png"));
    // src = Utilities.fileToString(page.getFolders().srcDir +
    // "template-book.html").replace("<body>",
    // "<body style=\"margin: 10px\">");
    // src = processResourceIncludes(n, root, xml, tx, dict, src);
    // cachePage(n+".html", src);
    //
    // xml to xhtml of xml
    // first pass is to strip the xsi: stuff. seems to need double
    // processing in order to delete namespace crap
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(new CSFileInputStream(page.getFolders().dstDir + title + ".profile.xml"));
    XmlGenerator xmlgen = new XmlGenerator();
    xmlgen.generate(xdoc.getDocumentElement(), tmp, "http://hl7.org/fhir", xdoc.getDocumentElement().getLocalName());

    // reload it now
    builder = factory.newDocumentBuilder();
    xdoc = builder.parse(new CSFileInputStream(tmp.getAbsolutePath()));
    XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(page.getDefinitions()));
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    xhtml.generate(xdoc, b, "Profile", profile.metadata("name"), 0, true, title + ".profile.xml.html");
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-profile-example-xml.html").replace("<%example%>", b.toString());
    html = page.processProfileIncludes(title + ".profile.xml.html", profile, "", "", html, "", "", "", master, title + ".html", null, filename, examples);
    TextFile.stringToFile(html, page.getFolders().dstDir + title + ".profile.xml.html");

    page.getEpub().registerFile(title + ".profile.xml.html", "Profile", EPubManager.XHTML_TYPE);
    String n = title + ".profile";
    String json = resource2Json(p);
    json = "<div class=\"example\">\r\n<p>" + Utilities.escapeXml("Profile for " + profile.metadata("description")) + "</p>\r\n<pre class=\"json\">\r\n" + Utilities.escapeXml(json)+ "\r\n</pre>\r\n</div>\r\n";
    html = TextFile.fileToString(page.getFolders().srcDir + "template-profile-example-json.html").replace("<%example%>", json);
    html = page.processProfileIncludes(title + ".profile.json.html", profile, "", "", html, "", "", "", master, title + ".html", null, filename, examples);
    TextFile.stringToFile(html, page.getFolders().dstDir + title + ".profile.json.html");
    //    page.getEpub().registerFile(n + ".json.html", description, EPubManager.XHTML_TYPE);
    page.getEpub().registerExternal(n + ".json.html");
    tmp.delete();
    return p;
  }

//  private void validateProfile(ProfileDefn profile) throws FileNotFoundException, Exception {
//    for (ResourceDefn c : profile.getResources()) {
//      Profile resource = loadResourceProfile(c.getName());
//      ProfileValidator v = new ProfileValidator();
//      v.setCandidate(c);
//      v.setProfile(resource);
//      v.setTypes(typeFeed);
//      List<String> errors = v.evaluate();
//      if (errors.size() > 0)
//        throw new Exception("Error validating " + profile.metadata("name") + ": " + errors.toString());
//    }
//  }

  // private void produceFutureResource(String n) throws Exception {
  // ElementDefn e = new ElementDefn();
  // e.setName(page.getIni().getStringProperty("future-resources", n));
  // }



  private Profile loadResourceProfile(String name) throws FileNotFoundException, Exception {
    XmlParser xml = new XmlParser();
    try {
      return (Profile) xml.parse(new CSFileInputStream(page.getFolders().dstDir + name.toLowerCase() + ".profile.xml"));
    } catch (Exception e) {
      throw new Exception("error parsing " + name, e);
    }
  }

  private void produceIgPage(String source, String file, String logicalName) throws Exception {
    String src = TextFile.fileToString(source);
    src = page.processPageIncludes(file, src, "page", null, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    if (Utilities.noString(logicalName))
      logicalName = Utilities.fileTitle(file);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);
    src = addSectionNumbers(file, logicalName, src, null);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);

    src = TextFile.fileToString(source).replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "page", null);
    cachePage(file, src, logicalName);
  }
  
  private void producePage(String file, String logicalName) throws Exception {
    String src = TextFile.fileToString(page.getFolders().srcDir + file);
    src = page.processPageIncludes(file, src, "page", null, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    if (Utilities.noString(logicalName))
      logicalName = Utilities.fileTitle(file);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);
    src = addSectionNumbers(file, logicalName, src, null);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);

    src = TextFile.fileToString(page.getFolders().srcDir + file).replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "page", null);
    cachePage(file, src, logicalName);
  }

  private void produceSid(int i, String logicalName, String file) throws Exception {
    String src = TextFile.fileToString(page.getFolders().srcDir + file);
    String dstName = Utilities.path(page.getFolders().dstDir, "sid", logicalName, "index.html");
    src = page.processPageIncludes(dstName, src, "sid:" + logicalName, null, null);
    // before we save this page out, we're going to figure out what it's index
    // is, and number the headers if we can

    Utilities.createDirectory(Utilities.path(page.getFolders().dstDir, "sid", logicalName));
    TextFile.stringToFile(src, dstName);
    src = addSectionNumbers(file, "sid:terminologies-systems", src, "3." + Integer.toString(i));
    TextFile.stringToFile(src, dstName);
  }

  private String addSectionNumbers(String file, String logicalName, String src, String id) throws Exception {
    if (!page.getSectionTrackerCache().containsKey(logicalName)) {
      // String prefix =
      // page.getNavigation().getIndexPrefixForFile(logicalName+".html");
      String prefix = page.getBreadCrumbManager().getIndexPrefixForFile(logicalName + ".html");
      if (Utilities.noString(prefix))
        throw new Exception("No indexing home for logical place " + logicalName);
      page.getSectionTrackerCache().put(logicalName, new SectionTracker(prefix));
    }
    SectionTracker st = page.getSectionTrackerCache().get(logicalName);
    st.start(id);
    src = insertSectionNumbers(src, st, file);
    return src;
  }

  private void produceCompartment(Compartment c) throws Exception {

    String logicalName = "compartment-" + c.getName();
    String file = logicalName + ".html";
    String src = TextFile.fileToString(page.getFolders().srcDir + "template-compartment.html");
    src = page.processPageIncludes(file, src, "compartment", null, null);

    // String prefix = "";
    // if
    // (!page.getSectionTrackerCache().containsKey("compartment-"+c.getName()))
    // {
    // prefix = page.getNavigation().getIndexPrefixForFile(logicalName+".html");
    // if (Utilities.noString(prefix))
    // throw new Exception("No indexing home for logical place "+logicalName);
    // }
    // page.getSectionTrackerCache().put(logicalName, new
    // SectionTracker(prefix));

    // TextFile.stringToFile(src, page.getFolders().dstDir + file);
    // src = insertSectionNumbers(src,
    // page.getSectionTrackerCache().get(logicalName), file);

    TextFile.stringToFile(src, page.getFolders().dstDir + file);

    src = TextFile.fileToString(page.getFolders().srcDir + "template-compartment.html").replace("<body>", "<body style=\"margin: 10px\">");
    src = page.processPageIncludesForBook(file, src, "compartment", null);
    cachePage(file, src, "Compartments");
  }

  private String insertSectionNumbers(String src, SectionTracker st, String link) throws Exception {
    try {
      // TextFile.stringToFile(src, "c:\\temp\\text.html");
      XhtmlDocument doc = new XhtmlParser().parse(src, "html");
      insertSectionNumbersInNode(doc, st, link);
      return new XhtmlComposer().compose(doc);
    } catch (Exception e) {
			System.out.println(e.getMessage());
      TextFile.stringToFile(src, "c:\\temp\\dump.html");
      throw new Exception("Exception inserting section numbers in " + link + ": " + e.getMessage(), e);
    }
  }

  private void insertSectionNumbersInNode(XhtmlNode node, SectionTracker st, String link) throws Exception {
    if (node.getNodeType() == NodeType.Element
        && (node.getName().equals("h1") || node.getName().equals("h2") || node.getName().equals("h3") || node.getName().equals("h4")
            || node.getName().equals("h5") || node.getName().equals("h6"))) {
      String v = st.getIndex(Integer.parseInt(node.getName().substring(1)));
      TocEntry t = new TocEntry(v, node.allText(), link);
      page.getToc().put(v, t);
      node.addText(0, " ");
      XhtmlNode span = node.addTag(0, "span");
      span.setAttribute("class", "sectioncount");
      span.addText(v);
      XhtmlNode a = span.addTag("a");
      a.setAttribute("name", v);
      a.addText(" "); // bug in some browsers?
    }
    if (node.getNodeType() == NodeType.Document
        || (node.getNodeType() == NodeType.Element && !(node.getName().equals("div") && "sidebar".equals(node.getAttribute("class"))))) {
      for (XhtmlNode n : node.getChildNodes()) {
        insertSectionNumbersInNode(n, st, link);
      }
    }
  }

  private void cachePage(String filename, String source, String title) throws Exception {
    try {
      // page.log("parse "+filename);
      XhtmlDocument src = new XhtmlParser().parse(source, "html");
      scanForFragments(filename, src);
      // book.getPages().put(filename, src);
      page.getEpub().registerFile(filename, title, EPubManager.XHTML_TYPE);
    } catch (Exception e) {
      throw new Exception("error parsing page " + filename + ": " + e.getMessage() + " in source\r\n" + source);
    }
  }

  private void scanForFragments(String filename, XhtmlNode node) throws Exception {
    if (node != null && (node.getNodeType() == NodeType.Element || node.getNodeType() == NodeType.Document)) {
      if (node.getNodeType() == NodeType.Element && node.getName().equals("pre") && node.getAttribute("fragment") != null) {
        processFragment(filename, node, node.getAttribute("fragment"));
      }
      for (XhtmlNode child : node.getChildNodes())
        scanForFragments(filename, child);
    }
  }

  private void processFragment(String filename, XhtmlNode node, String type) throws Exception {
    String xml = new XhtmlComposer().compose(node);
    Fragment f = new Fragment();
    f.setType(type);
    f.setXml(Utilities.unescapeXml(xml));
    f.setPage(filename);
    fragments.add(f);
  }

  public class MyErrorHandler implements ErrorHandler {

    private boolean trackErrors;
    private List<String> errors = new ArrayList<String>();

    public MyErrorHandler(boolean trackErrors) {
      this.trackErrors = trackErrors;
    }

    @Override
    public void error(SAXParseException arg0) throws SAXException {
      if (trackErrors) {
        System.out.println("error: " + arg0.toString());
        errors.add(arg0.toString());
      }

    }

    @Override
    public void fatalError(SAXParseException arg0) throws SAXException {
      System.out.println("fatal error: " + arg0.toString());

    }

    @Override
    public void warning(SAXParseException arg0) throws SAXException {
      // System.out.println("warning: " + arg0.toString());

    }

    public List<String> getErrors() {
      return errors;
    }

  }

  public class MyResourceResolver implements LSResourceResolver {

    private String dir;

    public MyResourceResolver(String dir) {
      this.dir = dir;
    }

    @Override
    public LSInput resolveResource(final String type, final String namespaceURI, final String publicId, String systemId, final String baseURI) {
      // System.out.println(type+", "+namespaceURI+", "+publicId+", "+systemId+", "+baseURI);
      try {
        if (!new CSFile(dir + systemId).exists())
          return null;
        return new SchemaInputSource(new CSFileInputStream(new CSFile(dir + systemId)), publicId, systemId, namespaceURI);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return null;
      }
    }
  }

  static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  private void checkBySchema(String fileToCheck, String[] schemaSource) throws Exception {
    StreamSource[] sources = new StreamSource[schemaSource.length];
    int i = 0;
    for (String s : schemaSource) {
      sources[i] = new StreamSource(new CSFileInputStream(s));
      i++;
    }
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    schemaFactory.setErrorHandler(new MyErrorHandler(false));
    schemaFactory.setResourceResolver(new MyResourceResolver(page.getFolders().dstDir));
    Schema schema = schemaFactory.newSchema(sources);

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    factory.setValidating(false);
    factory.setSchema(schema);
    DocumentBuilder builder = factory.newDocumentBuilder();
    MyErrorHandler err = new MyErrorHandler(true);
    builder.setErrorHandler(err);
    builder.parse(new CSFileInputStream(new CSFile(fileToCheck)));
    if (err.getErrors().size() > 0)
      throw new Exception("File " + fileToCheck + " failed schema validation");
  }
  
  int errorCount = 0;
  int warningCount = 0;
  int informationCount = 0;
  
  private void validateXml() throws Exception {
    if (buildFlags.get("all") && isGenerate)
      produceCoverageWarnings();
    page.log("Validating XML", LogMessageType.Process);
    page.log(".. Loading schemas", LogMessageType.Process);
    StreamSource[] sources = new StreamSource[2];
    sources[0] = new StreamSource(new CSFileInputStream(page.getFolders().dstDir + "fhir-all.xsd"));
    sources[1] = new StreamSource(new CSFileInputStream(page.getFolders().dstDir + "fhir-atom.xsd"));
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    schemaFactory.setErrorHandler(new MyErrorHandler(false));
    schemaFactory.setResourceResolver(new MyResourceResolver(page.getFolders().dstDir));
    Schema schema = schemaFactory.newSchema(sources);
    InstanceValidator validator = new InstanceValidator(page.getWorkerContext());
    validator.setSuppressLoincSnomedMessages(true);
    page.log(".... done", LogMessageType.Process);

    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname);
      if (wantBuild(rname)) {
        for (Example e : r.getExamples()) {
          String n = e.getFileTitle();
          page.log(" ...validate " + n, LogMessageType.Process);
          validateXmlFile(schema, n, validator, null);
        }
        for (RegisteredProfile e : r.getProfiles()) {
          String n = e.getTitle() + ".profile";
          page.log(" ...validate " + n, LogMessageType.Process);
          validateXmlFile(schema, n, validator, null);
          for (String en : e.getExamples().keySet()) {
            page.log(" ...validate " + en, LogMessageType.Process);
            validateXmlFile(schema, Utilities.changeFileExt(en, ""), validator, e.getResource()); // validates the example against it's base definitions
          }
        }
      }
    }

    if (buildFlags.get("all")) {
      for (String n : page.getDefinitions().getProfiles().keySet()) {
        page.log(" ...profile " + n, LogMessageType.Process);
        validateXmlFile(schema, n + ".profile", validator, null);
      }

      page.log(" ...validate " + "profiles-resources", LogMessageType.Process);
      validateXmlFile(schema, "profiles-resources", validator, null);

      page.log(" ...validate " + "profiles-types", LogMessageType.Process);
      validateXmlFile(schema, "profiles-types", validator, null);

      page.log(" ...validate " + "valuesets", LogMessageType.Process);
      validateXmlFile(schema, "valuesets", validator, null);

      page.log(" ...validate " + "v2-tables", LogMessageType.Process);
      validateXmlFile(schema, "v2-tables", validator, null);
      page.log(" ...validate " + "v3-codesystems", LogMessageType.Process);
      validateXmlFile(schema, "v3-codesystems", validator, null);
    }
    page.saveSnomed();

    page.log("Summary: Errors="+Integer.toString(errorCount)+", Warnings="+Integer.toString(warningCount)+", Hints="+Integer.toString(informationCount), LogMessageType.Error);
    if (errorCount > 0)
      throw new Exception("Resource Examples failed instance validation");

    page.log("Reference Platform Validation", LogMessageType.Process);

    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname);
      if (wantBuild(rname)) {
        for (Example e : r.getExamples()) {
          String n = e.getFileTitle();
          page.log(" ...test " + n, LogMessageType.Process);
          validateRoundTrip(schema, n);
        }
      }
    }
    if (buildFlags.get("all")) {
      page.log(" ...test " + "profiles-resources", LogMessageType.Process);
      validateRoundTrip(schema, "profiles-resources");
    }
    for (String rn : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResourceByName(rn);
      for (SearchParameter sp : r.getSearchParams().values()) {
        if (!sp.isWorks() && !sp.getCode().equals("_id")) {
//          page.log(
//              "Search Parameter '" + rn + "." + sp.getCode() + "' had no found values in any example. Consider reviewing the path (" + sp.getXPath() + ")",
//              LogMessageType.Warning);
          page.getQa().warning(
              "Search Parameter '" + rn + "." + sp.getCode() + "' had no fond values in any example. Consider reviewing the path (" + sp.getXPath() + ")");
        }
      }
    }
  }

  private void produceCoverageWarnings() throws Exception {
    for (ElementDefn e : page.getDefinitions().getStructures().values())
      produceCoverageWarning("", e);
    for (ElementDefn e : page.getDefinitions().getTypes().values())
      produceCoverageWarning("", e);
    for (String s : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn e = page.getDefinitions().getResourceByName(s);
      produceCoverageWarning("", e.getRoot());
    }
  }

  private void produceCoverageWarning(String path, ElementDefn e) {

    if (!e.isCoveredByExample() && !Utilities.noString(path)) {
//      page.log("The resource path " + path + e.getName() + " is not covered by any example", LogMessageType.Warning);
      page.getQa().notCovered(path + e.getName());
    }
    for (ElementDefn c : e.getElements()) {
      produceCoverageWarning(path + e.getName() + "/", c);
    }
  }

  private void validateXmlFile(Schema schema, String n, InstanceValidator validator, Profile profile) throws Exception {
    char sc = File.separatorChar;
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    factory.setValidating(false);
    factory.setSchema(schema);
    DocumentBuilder builder = factory.newDocumentBuilder();
    MyErrorHandler err = new MyErrorHandler(true);
    builder.setErrorHandler(err);
    Document doc = builder.parse(new CSFileInputStream(new CSFile(page.getFolders().dstDir + n + ".xml")));
    errorCount = errorCount + err.getErrors().size();

    File tmpTransform = Utilities.createTempFile("tmp", ".xslt");
    File tmpOutput = Utilities.createTempFile("tmp", ".xml");
    String sch = doc.getDocumentElement().getNodeName().toLowerCase();
    if (sch.equals("feed"))
      sch = "fhir-atom";

    try {
      Utilities.saxonTransform(page.getFolders().rootDir + "tools" + sc + "schematron" + sc, page.getFolders().dstDir + sch + ".sch", page.getFolders().rootDir
          + "tools" + sc + "schematron" + sc + "iso_svrl_for_xslt2.xsl", tmpTransform.getAbsolutePath(), null);
      Utilities.saxonTransform(page.getFolders().rootDir + "tools" + sc + "schematron" + sc, page.getFolders().dstDir + n + ".xml",
          tmpTransform.getAbsolutePath(), tmpOutput.getAbsolutePath(), null);
    } catch (Throwable t) {
//      throw new Exception("Error validating " + page.getFolders().dstDir + n + ".xml with schematrons", t);
    }

    factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    builder = factory.newDocumentBuilder();
    doc = builder.parse(new CSFileInputStream(tmpOutput.getAbsolutePath()));
    NodeList nl = doc.getDocumentElement().getElementsByTagNameNS("http://purl.oclc.org/dsdl/svrl", "failed-assert");
    if (nl.getLength() > 0) {
      page.log("Schematron Validation Failed for " + n + ".xml:", LogMessageType.Error);
      for (int i = 0; i < nl.getLength(); i++) {
        Element e = (Element) nl.item(i);
        page.log("  @" + e.getAttribute("location") + ": " + e.getTextContent(), LogMessageType.Error);
        errorCount++;
      }
    }

    // now, finally, we validate the resource ourselves.
    // the build tool validation focuses on codes and identifiers
    List<ValidationMessage> issues = new ArrayList<ValidationMessage>();
    // todo.... validator.validateInstance(issues, root);
    // if (profile != null)
    // validator.validateInstanceByProfile(issues, root, profile);
    for (ValidationMessage m : issues) {
      if (!m.getLevel().equals(IssueSeverity.information) && !m.getLevel().equals(IssueSeverity.warning))
        page.log("  " + m.summary(), typeforSeverity(m.getLevel()));

      if (m.getLevel() == IssueSeverity.warning)
        warningCount++;
      else if (m.getLevel() == IssueSeverity.information)
        informationCount++;
      else 
        errorCount++;
    }
  }

  private LogMessageType typeforSeverity(IssueSeverity level) {
    switch (level) {
    case error:
      return LogMessageType.Error;
    case fatal:
      return LogMessageType.Error;
    case information:
      return LogMessageType.Hint;
    case warning:
      return LogMessageType.Warning;
    default:
      return LogMessageType.Error;
    }
  }

  private void validateRoundTrip(Schema schema, String n) throws Exception {
    for (PlatformGenerator gen : page.getReferenceImplementations()) {
      if (gen.doesTest()) {
        gen.loadAndSave(page.getFolders().dstDir, page.getFolders().dstDir + n + ".xml", page.getFolders().tmpDir + n + "-tmp.xml");
        testSearchParameters(page.getFolders().dstDir + n + ".xml");
        compareXml(n, gen.getName(), page.getFolders().dstDir + n + ".xml", page.getFolders().tmpDir + n + "-tmp.xml");
      }
    }
  }

  private void testSearchParameters(String filename) throws Exception {
    // load the xml
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xml = builder.parse(new CSFileInputStream(new CSFile(filename)));

    if (xml.getDocumentElement().getNodeName().equals("feed")) {
      Element child = XMLUtil.getFirstChild(xml.getDocumentElement());
      while (child != null) {
        if (child.getNodeName().equals("entry")) {
          Element grandchild = XMLUtil.getFirstChild(xml.getDocumentElement());
          while (grandchild != null) {
            if (grandchild.getNodeName().equals("content"))
              testSearchParameters(XMLUtil.getFirstChild(grandchild));
            grandchild = XMLUtil.getNextSibling(grandchild);
          }
        }
        child = XMLUtil.getNextSibling(child);
      }
    } else
      testSearchParameters(xml.getDocumentElement());
  }

  private void testSearchParameters(Element e) throws Exception {
    ResourceDefn r = page.getDefinitions().getResourceByName(e.getNodeName());
    for (SearchParameter sp : r.getSearchParams().values()) {

      if (sp.getXPath() != null) {
        try {
          NamespaceContext context = new NamespaceContextMap("f", "http://hl7.org/fhir", "h", "http://www.w3.org/1999/xhtml", "a",
              "http://www.w3.org/2005/Atom");

          XPathFactory factory = XPathFactory.newInstance();
          XPath xpath = factory.newXPath();
          xpath.setNamespaceContext(context);
          XPathExpression expression;
          expression = xpath.compile("/" + sp.getXPath());
          NodeList resultNodes = (NodeList) expression.evaluate(e, XPathConstants.NODESET);
          if (resultNodes.getLength() > 0)
            sp.setWorks(true);
        } catch (Exception e1) {
          page.log("Xpath \"" + sp.getXPath() + "\" execution failed: " + e1.getMessage(), LogMessageType.Error);
        }
      }
    }
  }

  private void compareXml(String t, String n, String fn1, String fn2) throws Exception {
    char sc = File.separatorChar;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setNamespaceAware(true);
    dbf.setCoalescing(true);
    dbf.setIgnoringElementContentWhitespace(true);
    dbf.setIgnoringComments(true);
    DocumentBuilder db = dbf.newDocumentBuilder();

    Document doc1 = db.parse(new CSFile(fn1));
    doc1.normalizeDocument();
    stripWhitespaceAndComments(doc1);

    Document doc2 = db.parse(new CSFile(fn2));
    doc2.normalizeDocument();
    stripWhitespaceAndComments(doc2);

    XmlGenerator xmlgen = new XmlGenerator();
    File tmp1 = Utilities.createTempFile("xml", ".xml");
    xmlgen.generate(doc1.getDocumentElement(), tmp1, doc1.getDocumentElement().getNamespaceURI(), doc1.getDocumentElement().getLocalName());
    File tmp2 = Utilities.createTempFile("xml", ".xml");
    xmlgen.generate(doc2.getDocumentElement(), tmp2, doc2.getDocumentElement().getNamespaceURI(), doc2.getDocumentElement().getLocalName());

    if (!TextFile.fileToString(tmp1.getAbsolutePath()).equals(TextFile.fileToString(tmp2.getAbsolutePath()))) {
      page.log("file " + t + " did not round trip perfectly in XML in platform " + n, LogMessageType.Warning);
      String diff = diffProgram != null ? diffProgram : System.getenv("ProgramFiles(X86)") + sc + "WinMerge" + sc + "WinMergeU.exe";
      if (new CSFile(diff).exists()) {
        List<String> command = new ArrayList<String>();
        command.add("\"" + diff + "\" \"" + tmp1.getAbsolutePath() + "\" \"" + tmp2.getAbsolutePath() + "\"");

        ProcessBuilder builder = new ProcessBuilder(command);
        builder.directory(new CSFile(page.getFolders().rootDir));
        final Process process = builder.start();
        process.waitFor();
      } else {
        // no diff program
        page.log("Files for diff: '" + fn1 + "' and '" + fn2 + "'", LogMessageType.Warning);
      }
    }
  }

  private void stripWhitespaceAndComments(Node node) {
    if (node.getNodeType() == Node.ELEMENT_NODE) {
      Element e = (Element) node;
      Map<String, String> attrs = new HashMap<String, String>();
      for (int i = e.getAttributes().getLength() - 1; i >= 0; i--) {
        attrs.put(e.getAttributes().item(i).getNodeName(), e.getAttributes().item(i).getNodeValue());
        e.removeAttribute(e.getAttributes().item(i).getNodeName());
      }
      for (String n : attrs.keySet()) {
        e.setAttribute(n, attrs.get(n));
      }
    }
    for (int i = node.getChildNodes().getLength() - 1; i >= 0; i--) {
      Node c = node.getChildNodes().item(i);
      if (c.getNodeType() == Node.TEXT_NODE && c.getTextContent().trim().length() == 0)
        node.removeChild(c);
      else if (c.getNodeType() == Node.TEXT_NODE)
        c.setTextContent(c.getTextContent().trim());
      else if (c.getNodeType() == Node.COMMENT_NODE)
        node.removeChild(c);
      else if (c.getNodeType() == Node.ELEMENT_NODE)
        stripWhitespaceAndComments(c);
    }
    if (node.getNodeType() == Node.ELEMENT_NODE) {
      node.appendChild(node.getOwnerDocument().createTextNode("\r\n"));
    }

  }

  // public void logNoEoln(String content) {
  // page.logNoEoln(content);
  // }

  @SuppressWarnings("unchecked")
  private void generateIGValueSetsPart1() throws Exception {
    for (AtomEntry<? extends Resource> ae : page.getIgResources().values()) {
      if (ae.getResource() instanceof ValueSet) {
        ValueSet vs = (ValueSet) ae.getResource();
        page.getValueSets().put(vs.getIdentifierSimple(), (AtomEntry<ValueSet>) ae);
        page.getDefinitions().getValuesets().put(vs.getIdentifierSimple(), vs);
        if (vs.getDefine() != null) {
          page.getCodeSystems().put(vs.getDefine().getSystemSimple(), (AtomEntry<ValueSet>) ae);
          page.getDefinitions().getCodeSystems().put(vs.getDefine().getSystemSimple(), vs);
        }
        
      }
    }
  }
  private void generateValueSetsPart1() throws Exception {
    page.log(" ...value sets", LogMessageType.Process);
    for (BindingSpecification bs : page.getDefinitions().getBindings().values()) {
      if (Utilities.noString(bs.getOid()))
        bs.setOid(PageProcessor.OID_VS + page.getRegistry().idForName(bs.getName()));
      if (bs.getBinding() == Binding.ValueSet && bs.getReferredValueSet() != null && !bs.getReference().startsWith("http://hl7.org/fhir"))
        generateValueSetPart1(bs.getReference(), bs);
    }
    for (String n : page.getDefinitions().getExtraValuesets().keySet()) {
      ValueSet vs = page.getDefinitions().getExtraValuesets().get(n);
      generateValueSetPart1(n, vs, n, page.getRegistry().idForName(n));
    }
  }

  private void generateIGValueSetsPart2() throws Exception {
    for (AtomEntry<? extends Resource> ae : page.getIgResources().values()) {
      if (ae.getResource() instanceof ValueSet) {
        ValueSet vs = (ValueSet) ae.getResource();
        String name = Utilities.fileTitle(ae.getLinks().get("path"));
        String title = vs.getNameSimple();
        
        if (vs.getText() == null || vs.getText().getDiv() == null || vs.getText().getDiv().allChildrenAreText()
            && (Utilities.noString(vs.getText().getDiv().allText()) || !vs.getText().getDiv().allText().matches(".*\\w.*")))
          new NarrativeGenerator("", page.getWorkerContext()).generate(vs);
        
        new ValueSetValidator(page.getWorkerContext()).validate(vs, true);
        
        addToResourceFeed(vs, vs.getIdentifierSimple(), valueSetsFeed);

        String sf = page.processPageIncludes(title + ".html", TextFile.fileToString(page.getFolders().srcDir + "template-vs-ig.html"), "valueSet", null, name+".html", vs);
        sf = addSectionNumbers(title + ".html", "template-valueset", sf, "??");
        TextFile.stringToFile(sf, page.getFolders().dstDir + name + ".html");
        
        String src = page.processPageIncludesForBook(title + ".html", TextFile.fileToString(page.getFolders().srcDir + "template-vs-ig-book.html"), "valueSet", vs);
        cachePage(name + ".html", src, "Value Set " + title);
        page.setId(null);

        JsonComposer json = new JsonComposer();
        json.compose(new FileOutputStream(page.getFolders().dstDir+name + ".json"), vs, true);
        XmlComposer xml = new XmlComposer();
        xml.compose(new FileOutputStream(page.getFolders().dstDir+name + ".xml"), vs, true);
        cloneToXhtml(name, "Definition for Value Set" + vs.getNameSimple(), false, "valueset-instance");
        jsonToXhtml(name, "Definition for Value Set" + vs.getNameSimple(), resource2Json(vs), "valueset-instance");
      }
    }
  }
  
  private void generateValueSetsPart2() throws Exception {
    page.log(" ...value sets (2)", LogMessageType.Process);
    for (BindingSpecification bs : page.getDefinitions().getBindings().values()) {
      if (bs.getBinding() == Binding.ValueSet && bs.getReferredValueSet() != null && !bs.getReference().startsWith("http://hl7.org/fhir"))
        generateValueSetPart2(bs.getReference(), bs.getName(), bs.getOid());
    }
    for (String n : page.getDefinitions().getExtraValuesets().keySet())
      generateValueSetPart2(n, n, page.getRegistry().idForName(n));
  }

  private void generateValueSetPart1(String name, BindingSpecification cd) throws Exception {
    String n;
    if (name.startsWith("valueset-"))
      n = name.substring(9);
    else
      n = name;
    cd.getReferredValueSet().setIdentifierSimple("http://hl7.org/fhir/vs/" + n);
    ValueSet vs = cd.getReferredValueSet();
    generateValueSetPart1(n, vs, name, cd.getOid());
  }

  private void generateValueSetPart1(String name, ValueSet vs, String path, String oid) throws Exception {
    if (vs.getText() == null) {
      vs.setText(new Narrative());
      vs.getText().setStatusSimple(NarrativeStatus.empty);
    }
    if (vs.getText().getDiv() == null) {
      vs.getText().setDiv(new XhtmlNode(NodeType.Element));
      vs.getText().getDiv().setName("div");
    }

    AtomEntry ae = new AtomEntry();
    ae.getLinks().put("self", "??");
    ae.getLinks().put("path", path + ".html");
    ae.getLinks().put("oid", oid);
    ae.setResource(vs);
    page.getValueSets().put(vs.getIdentifierSimple(), ae);
    page.getDefinitions().getValuesets().put(vs.getIdentifierSimple(), vs);
    if (vs.getDefine() != null) {
      page.getCodeSystems().put(vs.getDefine().getSystemSimple(), ae);
      page.getDefinitions().getCodeSystems().put(vs.getDefine().getSystemSimple(), vs);
    }
  }

  private void generateValueSetPart2(String name, String title, String id) throws Exception {
    String n;
    if (name.startsWith("valueset-"))
      n = name.substring(9);
    else
      n = name;
    AtomEntry ae = page.getValueSets().get("http://hl7.org/fhir/vs/" + n);
    ValueSet vs = (ValueSet) ae.getResource();

    if (vs.getText().getDiv().allChildrenAreText()
        && (Utilities.noString(vs.getText().getDiv().allText()) || !vs.getText().getDiv().allText().matches(".*\\w.*")))
      new NarrativeGenerator("", page.getWorkerContext()).generate(vs);
    new ValueSetValidator(page.getWorkerContext()).validate(vs, true);

    if (isGenerate) {
      addToResourceFeed(vs, n, valueSetsFeed);

      ae.getLinks().put("path", name + ".html");
      page.setId(id);
      String sf = page.processPageIncludes(title + ".html", TextFile.fileToString(page.getFolders().srcDir + "template-vs.html"), "valueSet", null, name+".html", null);
      sf = addSectionNumbers(title + ".html", "template-valueset", sf, Utilities.oidTail(id));

      TextFile.stringToFile(sf, page.getFolders().dstDir + name + ".html");
      String src = page.processPageIncludesForBook(title + ".html", TextFile.fileToString(page.getFolders().srcDir + "template-vs-book.html"), "valueSet", null);
      cachePage(name + ".html", src, "Value Set " + title);
      page.setId(null);

      JsonComposer json = new JsonComposer();
      json.compose(new FileOutputStream(page.getFolders().dstDir + name + ".json"), vs, true);
      XmlComposer xml = new XmlComposer();
      xml.compose(new FileOutputStream(page.getFolders().dstDir + name + ".xml"), vs, true);
      cloneToXhtml(name, "Definition for Value Set" + vs.getNameSimple(), false, "valueset-instance");
      jsonToXhtml(name, "Definition for Value Set" + vs.getNameSimple(), resource2Json(vs), "valueset-instance");
    }
  }

  private void generateCodeSystemsPart1() throws Exception {
    page.log(" ...code lists", LogMessageType.Process);
    for (BindingSpecification bs : page.getDefinitions().getBindings().values()) {
      if (Utilities.noString(bs.getOid()))
        bs.setOid(page.getRegistry().idForName(bs.getName()));
      if (bs.getBinding() == Binding.CodeList || bs.getBinding() == Binding.Special)
        generateCodeSystemPart1(bs.getReference().substring(1) + ".html", bs);
    }
  }

  private void generateCodeSystemsPart2() throws Exception {
    page.log(" ...code lists (2)", LogMessageType.Process);
    for (BindingSpecification bs : page.getDefinitions().getBindings().values())
      if (bs.getBinding() == Binding.CodeList || bs.getBinding() == Binding.Special)
        generateCodeSystemPart2(bs.getReference().substring(1) + ".html", bs);
  }

  private void generateCodeSystemPart1(String filename, BindingSpecification cd) throws Exception {
    ValueSet vs = new ValueSet();
    if (Utilities.noString(cd.getUri()))
      vs.setIdentifierSimple("http://hl7.org/fhir/vs/" + Utilities.fileTitle(filename));
    else
      vs.setIdentifierSimple(cd.getUri());
    // no version?? vs.setVersion(...
    vs.setNameSimple(cd.getName());
    vs.setPublisherSimple("HL7 (FHIR Project)");
    vs.getTelecom().add(
        org.hl7.fhir.instance.model.Factory.newContact(ContactSystem.url, Utilities.noString(cd.getWebSite()) ? "http://hl7.org/fhir" : cd.getWebSite()));
    vs.getTelecom().add(
        org.hl7.fhir.instance.model.Factory.newContact(ContactSystem.email, Utilities.noString(cd.getEmail()) ? "fhir@lists.hl7.org" : cd.getEmail()));
    vs.setDescriptionSimple(Utilities.noString(cd.getDescription()) ? cd.getDefinition() : cd.getDefinition() + "\r\n\r\n" + cd.getDescription());
    if (!Utilities.noString(cd.getCopyright()))
      vs.setCopyrightSimple(cd.getCopyright());

    vs.setStatusSimple(ValuesetStatus.draft); // until we publish DSTU, then
                                              // .review
    vs.setDate(org.hl7.fhir.instance.model.Factory.nowDateTime());

    for (String n : cd.getVSSources()) {
      if (Utilities.noString(n)) {
        if (vs.getDefine() == null) {
          vs.setDefine(new ValueSet.ValueSetDefineComponent());
          vs.getDefine().setCaseSensitiveSimple(true);
          vs.getDefine().setSystemSimple("http://hl7.org/fhir/" + Utilities.fileTitle(filename));
        }
        for (DefinedCode c : cd.getChildCodes()) {
          if (Utilities.noString(c.getSystem()))
            addCode(vs, vs.getDefine().getConcept(), c);
        }
      } else {
        if (vs.getCompose() == null)
          vs.setCompose(new ValueSet.ValueSetComposeComponent());
        ConceptSetComponent cc = new ValueSet.ConceptSetComponent();
        vs.getCompose().getInclude().add(cc);
        cc.setSystemSimple(n);
        for (DefinedCode c : cd.getCodes()) {
          if (n.equals(c.getSystem())) {
            CodeType nc = org.hl7.fhir.instance.model.Factory.newCode(c.getCode());
            cc.getCode().add(nc);
            if (!Utilities.noString(c.getComment()))
              ToolingExtensions.addComment(nc, c.getComment());
            if (!Utilities.noString(c.getDefinition()))
              ToolingExtensions.addDefinition(nc, c.getDefinition());
          }
        }
      }
    }

    new ValueSetValidator(page.getWorkerContext()).validate(vs, true);
    cd.setReferredValueSet(vs);
    AtomEntry<ValueSet> e = new AtomEntry<ValueSet>();
    e.setResource(vs);
    e.getLinks().put("self", Utilities.changeFileExt(filename, ".html"));
    e.getLinks().put("path", Utilities.changeFileExt(filename, ".html"));
    e.getLinks().put("oid", cd.getOid());
    if (vs.getDefine() != null)
      page.getCodeSystems().put(vs.getDefine().getSystemSimple(), e);
    page.getValueSets().put(vs.getIdentifierSimple(), e);
    page.getDefinitions().getValuesets().put(vs.getIdentifierSimple(), vs);
    if (vs.getDefine() != null)
      page.getDefinitions().getCodeSystems().put(vs.getDefine().getSystemSimple(), vs);
  }

  private void generateConceptMapV2(BindingSpecification cd, String filename, String src, String srcCS) throws Exception {
    ConceptMap cm = new ConceptMap();
    cm.setIdentifierSimple("http://hl7.org/fhir/cm/v2/" + Utilities.fileTitle(filename));
    // no version?? vs.setVersion(...
    cm.setNameSimple("v2 map for " + cd.getName());
    cm.setPublisherSimple("HL7 (FHIR Project)");
    cm.getTelecom().add(
        org.hl7.fhir.instance.model.Factory.newContact(ContactSystem.url, Utilities.noString(cd.getWebSite()) ? "http://hl7.org/fhir" : cd.getWebSite()));
    cm.getTelecom().add(
        org.hl7.fhir.instance.model.Factory.newContact(ContactSystem.email, Utilities.noString(cd.getEmail()) ? "fhir@lists.hl7.org" : cd.getEmail()));
    if (!Utilities.noString(cd.getCopyright()))
      cm.setCopyrightSimple(cd.getCopyright());

    Set<String> tbls = new HashSet<String>();
    cm.setStatusSimple(ConceptMap.ValuesetStatus.draft); // until we publish
                                                         // DSTU, then .review
    cm.setDate(org.hl7.fhir.instance.model.Factory.nowDateTime());
    cm.setSource(Factory.makeResourceReference(src));
    cm.setTarget(Factory.makeResourceReference(cd.getV2Map()));
    for (DefinedCode c : cd.getCodes()) {
      if (!Utilities.noString(c.getV2Map())) {
        for (String m : c.getV2Map().split(",")) {
          ConceptMapElementComponent cc = new ConceptMap.ConceptMapElementComponent();
          cc.setCodeSystemSimple(srcCS);
          cc.setCodeSimple(c.getCode());
          ConceptMapElementMapComponent map = new ConceptMap.ConceptMapElementMapComponent();
          cc.getMap().add(map);
          cm.getElement().add(cc);
          String[] n = m.split("\\(");
          if (n.length > 1)
            map.setCommentsSimple(n[1].substring(0, n[1].length() - 1));
          n = n[0].split("\\.");
          tbls.add(n[0].substring(1));
          map.setCodeSystemSimple("http://hl7.org/fhir/v2/" + n[0].substring(1));
          map.setCodeSimple(n[1].trim());
          if (n[0].charAt(0) == '=')
            map.setEquivalenceSimple(ConceptEquivalence.equal);
          if (n[0].charAt(0) == '~')
            map.setEquivalenceSimple(ConceptEquivalence.equivalent);
          if (n[0].charAt(0) == '>')
            map.setEquivalenceSimple(ConceptEquivalence.narrower);
          if (n[0].charAt(0) == '<')
            map.setEquivalenceSimple(ConceptEquivalence.wider);
        }
      }
    }
    StringBuilder b = new StringBuilder();
    boolean first = false;
    for (String s : tbls) {
      if (first)
        b.append(", ");
      first = false;
      b.append(s);
    }
    cm.setDescriptionSimple("v2 Map (" + b.toString() + ")");
    NarrativeGenerator gen = new NarrativeGenerator("", page.getWorkerContext());
    gen.generate(cm);

    JsonComposer json = new JsonComposer();
    json.compose(new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v2.json")), cm, true);
    String n = Utilities.changeFileExt(filename, "-map-v2");
    jsonToXhtml(n, cm.getNameSimple(), resource2Json(cm), "conceptmap-instance");
    XmlComposer xml = new XmlComposer();
    xml.compose(new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v2.xml")), cm, true);
    cloneToXhtml(n, cm.getNameSimple(), false, "conceptmap-instance");

    // now, we create an html page from the narrative
    String narrative = new XhtmlComposer().compose(cm.getText().getDiv());
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example.html").replace("<%example%>", narrative);
    html = page.processPageIncludes(Utilities.changeFileExt(filename, "-map-v2.html"), html, "conceptmap-instance", null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v2.html"));

    AtomEntry<ConceptMap> e = new AtomEntry<ConceptMap>();
    e.setResource(cm);
    e.setId(cm.getIdentifierSimple());
    e.getLinks().put("self", Utilities.changeFileExt(filename, "-map-v2.html"));
    e.getLinks().put("path", Utilities.changeFileExt(filename, "-map-v2.html"));
    conceptMapsFeed.getEntryList().add(e);
    page.getConceptMaps().put(cm.getIdentifierSimple(), e);
    page.getEpub().registerFile(n + ".html", cm.getNameSimple(), EPubManager.XHTML_TYPE);
    page.getEpub().registerFile(n + ".json.html", cm.getNameSimple(), EPubManager.XHTML_TYPE);
    page.getEpub().registerFile(n + ".xml.html", cm.getNameSimple(), EPubManager.XHTML_TYPE);
  }

  private void generateConceptMapV3(BindingSpecification cd, String filename, String src, String srcCS) throws Exception {
    ConceptMap cm = new ConceptMap();
    cm.setIdentifierSimple("http://hl7.org/fhir/cm/v3/" + Utilities.fileTitle(filename));
    // no version?? vs.setVersion(...
    cm.setNameSimple("v3 map for " + cd.getName());
    cm.setPublisherSimple("HL7 (FHIR Project)");
    cm.getTelecom().add(
        org.hl7.fhir.instance.model.Factory.newContact(ContactSystem.url, Utilities.noString(cd.getWebSite()) ? "http://hl7.org/fhir" : cd.getWebSite()));
    cm.getTelecom().add(
        org.hl7.fhir.instance.model.Factory.newContact(ContactSystem.email, Utilities.noString(cd.getEmail()) ? "fhir@lists.hl7.org" : cd.getEmail()));
    if (!Utilities.noString(cd.getCopyright()))
      cm.setCopyrightSimple(cd.getCopyright());

    Set<String> tbls = new HashSet<String>();
    cm.setStatusSimple(ConceptMap.ValuesetStatus.draft); // until we publish
                                                         // DSTU, then .review
    cm.setDate(org.hl7.fhir.instance.model.Factory.nowDateTime());
    cm.setSource(Factory.makeResourceReference(src));
    cm.setTarget(Factory.makeResourceReference(cd.getV3Map()));
    for (DefinedCode c : cd.getCodes()) {
      if (!Utilities.noString(c.getV3Map())) {
        for (String m : c.getV3Map().split(",")) {
          ConceptMapElementComponent cc = new ConceptMap.ConceptMapElementComponent();
          cc.setCodeSystemSimple(srcCS);
          cc.setCodeSimple(c.getCode());
          ConceptMapElementMapComponent map = new ConceptMap.ConceptMapElementMapComponent();
          cc.getMap().add(map);
          cm.getElement().add(cc);
          String[] n = m.split("\\(");
          if (n.length > 1)
            map.setCommentsSimple(n[1].substring(0, n[1].length() - 1));
          n = n[0].split("\\.");
          tbls.add(n[0].substring(1));
          map.setCodeSystemSimple("http://hl7.org/fhir/v3/" + n[0].substring(1));
          map.setCodeSimple(n[1]);
          if (n[0].charAt(0) == '=')
            map.setEquivalenceSimple(ConceptEquivalence.equal);
          if (n[0].charAt(0) == '~')
            map.setEquivalenceSimple(ConceptEquivalence.equivalent);
          if (n[0].charAt(0) == '>')
            map.setEquivalenceSimple(ConceptEquivalence.narrower);
          if (n[0].charAt(0) == '<')
            map.setEquivalenceSimple(ConceptEquivalence.wider);
        }
      }
    }
    StringBuilder b = new StringBuilder();
    boolean first = false;
    for (String s : tbls) {
      if (first)
        b.append(", ");
      first = false;
      b.append(s);
    }
    cm.setDescriptionSimple("v3 Map (" + b.toString() + ")");
    NarrativeGenerator gen = new NarrativeGenerator("", page.getWorkerContext());
    gen.generate(cm);
    JsonComposer json = new JsonComposer();
    json.compose(new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v3.json")), cm, true);
    String n = Utilities.changeFileExt(filename, "-map-v3");
    jsonToXhtml(n, cm.getNameSimple(), resource2Json(cm), "conceptmap-instance");
    XmlComposer xml = new XmlComposer();
    xml.compose(new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v3.xml")), cm, true);
    cloneToXhtml(n, cm.getNameSimple(), false, "conceptmap-instance");

    // now, we create an html page from the narrative
    String narrative = new XhtmlComposer().compose(cm.getText().getDiv());
    String html = TextFile.fileToString(page.getFolders().srcDir + "template-example.html").replace("<%example%>", narrative);
    html = page.processPageIncludes(Utilities.changeFileExt(filename, "-map-v3.html"), html, "conceptmap-instance", null, null);
    TextFile.stringToFile(html, page.getFolders().dstDir + Utilities.changeFileExt(filename, "-map-v3.html"));

    AtomEntry<ConceptMap> e = new AtomEntry<ConceptMap>();
    e.setResource(cm);
    e.setId(cm.getIdentifierSimple());
    e.getLinks().put("self", Utilities.changeFileExt(filename, "-map-v3.html"));
    e.getLinks().put("path", Utilities.changeFileExt(filename, "-map-v3.html"));
    conceptMapsFeed.getEntryList().add(e);
    page.getConceptMaps().put(cm.getIdentifierSimple(), e);
    page.getEpub().registerFile(n + ".html", cm.getNameSimple(), EPubManager.XHTML_TYPE);
    page.getEpub().registerFile(n + ".json.html", cm.getNameSimple(), EPubManager.XHTML_TYPE);
    page.getEpub().registerFile(n + ".xml.html", cm.getNameSimple(), EPubManager.XHTML_TYPE);
  }

  private void generateCodeSystemPart2(String filename, BindingSpecification cd) throws Exception {
    AtomEntry e = null;
    if (Utilities.noString(cd.getUri()))
      e = page.getValueSets().get("http://hl7.org/fhir/vs/" + Utilities.fileTitle(filename));
    else
      e = page.getValueSets().get(cd.getUri());
    ValueSet vs = (ValueSet) e.getResource();

    if (!Utilities.noString(cd.getV2Map()))
      generateConceptMapV2(cd, filename, vs.getIdentifierSimple(), "http://hl7.org/fhir/" + Utilities.fileTitle(filename));
    if (!Utilities.noString(cd.getV3Map()))
      generateConceptMapV3(cd, filename, vs.getIdentifierSimple(), "http://hl7.org/fhir/" + Utilities.fileTitle(filename));

    new NarrativeGenerator("", page.getWorkerContext()).generate(vs);

    if (isGenerate) {
      addToResourceFeed(vs, Utilities.fileTitle(filename), valueSetsFeed);

      String sf = page.processPageIncludes(filename, TextFile.fileToString(page.getFolders().srcDir + "template-tx.html"), "codeSystem", null, null);
      sf = addSectionNumbers(filename + ".html", "template-valueset", sf, Utilities.oidTail(cd.getOid()));
      TextFile.stringToFile(sf, page.getFolders().dstDir + filename);
      String src = page.processPageIncludesForBook(filename, TextFile.fileToString(page.getFolders().srcDir + "template-tx-book.html"), "codeSystem", null);
      cachePage(filename, src, "Code System " + vs.getNameSimple());

      JsonComposer json = new JsonComposer();
      json.compose(new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, ".json")), vs, true);
      XmlComposer xml = new XmlComposer();
      xml.compose(new FileOutputStream(page.getFolders().dstDir + Utilities.changeFileExt(filename, ".xml")), vs, true);
      cloneToXhtml(Utilities.fileTitle(filename), "Definition for Value Set" + vs.getNameSimple(), false, "valueset-instance");
      jsonToXhtml(Utilities.fileTitle(filename), "Definition for Value Set" + vs.getNameSimple(), resource2Json(vs), "valueset-instance");
    }
  }

  private void addCode(ValueSet vs, List<ValueSetDefineConceptComponent> list, DefinedCode c) {
    ValueSetDefineConceptComponent d = new ValueSet.ValueSetDefineConceptComponent();
    list.add(d);
    d.setCodeSimple(c.getCode());
    if (!Utilities.noString(c.getDisplay()))
      d.setDisplaySimple(c.getDisplay());
    if (!Utilities.noString(c.getDefinition()))
      d.setDefinitionSimple(c.getDefinition());
    for (DefinedCode g : c.getChildCodes()) {
      addCode(vs, d.getConcept(), g);
    }
  }

  public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
    String query = url.getQuery();
    String[] pairs = query.split("&");
    for (String pair : pairs) {
        int idx = pair.indexOf("=");
        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
    }
    return query_pairs;
}
  @Override
  public javax.xml.transform.Source resolve(String href, String base) throws TransformerException {
    if (!href.startsWith("http://fhir.healthintersections.com.au/open/ValueSet/$expand"))
      return null;
    try {
      Map<String, String> params = splitQuery(new URL(href));
      ValueSet vs = page.getValueSets().get(params.get("identifier")).getResource();
      if (vs == null) {
        page.log("unable to resolve "+params.get("identifier"), LogMessageType.Process);
        return null;
      }
      vs = page.expandValueSet(vs);
      if (vs == null) {
        page.log("unable to expand "+params.get("identifier"), LogMessageType.Process);
        return null;
      }
      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      new XmlComposer().compose(bytes, vs, false);
      return new StreamSource(new ByteArrayInputStream(bytes.toByteArray()));
    } catch (Exception e) {
      throw new TransformerException(e);
    }
  }

}
