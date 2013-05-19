package org.hl7.fhir.tools.publisher;

/*
 Copyright (c) 2011-2013, HL7, Inc
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.generators.specification.DictHTMLGenerator;
import org.hl7.fhir.definitions.generators.specification.MappingsGenerator;
import org.hl7.fhir.definitions.generators.specification.ProfileGenerator;
import org.hl7.fhir.definitions.generators.specification.SchematronGenerator;
import org.hl7.fhir.definitions.generators.specification.TerminologyNotesGenerator;
import org.hl7.fhir.definitions.generators.specification.XmlSpecGenerator;
import org.hl7.fhir.definitions.generators.xsd.SchemaGenerator;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.EventDefn;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.Example.ExampleType;
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.definitions.model.RegisteredProfile;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.definitions.parsers.SourceParser;
import org.hl7.fhir.definitions.validation.ProfileValidator;
import org.hl7.fhir.definitions.validation.ResourceValidator;
import org.hl7.fhir.definitions.validation.ResourceValidator.Level;
import org.hl7.fhir.definitions.validation.ValidationMessage;
import org.hl7.fhir.instance.formats.AtomComposer;
import org.hl7.fhir.instance.formats.JsonComposer;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Contact.ContactSystem;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.CodeSelectionMode;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValuesetKind;
import org.hl7.fhir.instance.model.ValueSet.ValuesetStatus;
import org.hl7.fhir.tools.implementations.ECoreOclGenerator;
import org.hl7.fhir.tools.implementations.csharp.CSharpGenerator;
import org.hl7.fhir.tools.implementations.delphi.DelphiGenerator;
import org.hl7.fhir.tools.implementations.java.JavaGenerator;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.SchemaInputSource;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlDocument;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XhtmlGenerator;
import org.hl7.fhir.utilities.xml.XmlGenerator;
import org.json.JSONObject;
import org.tigris.subversion.javahl.SVNClient;
import org.tigris.subversion.javahl.Status;
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
public class Publisher {

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
    public ExampleReference(String type, String id) {
      super();
      this.type = type;
      this.id = id;
    }
    public String describe() {
      return type+"|"+id;
    }
    public String getType() {
      return type;
    }
    public String getId() {
      return id;
    }
    
  }

  private SourceParser prsr;
	private PageProcessor page = new PageProcessor();
	private BookMaker book;
  private JavaGenerator javaReferencePlatform;

  private long revNumber;
	private boolean isGenerate;
	private boolean web;
	private AtomFeed profileFeed;
  private List<Fragment> fragments = new ArrayList<Publisher.Fragment>();
  private Map<String, String> xmls = new HashMap<String, String>();
  private Map<String, Long> dates = new HashMap<String, Long>();
  private Map<String, Boolean> buildFlags = new HashMap<String, Boolean>();
  private IniFile cache;
  private WebMaker wm;
  
	public static void main(String[] args) {
		//
		Publisher pub = new Publisher();
		
		if( args.length == 0 )
		{
			System.out.println("Please specify the FHIR directory");
			return;
		}
		pub.isGenerate = !(args.length > 1 && hasParam(args, "-nogen"));
		pub.web = (args.length > 1 && hasParam(args, "-web"));
		try {
      pub.execute(args[0]);
    } catch (Exception e) {
      e.printStackTrace();
      File f;
      try {
        f = new File("fhir-error-dump.txt");
        PrintStream p = new PrintStream(f);
        e.printStackTrace(p);
        System.out.println("Stack Trace saved as fhir-error-dump.txt");
      } catch (IOException e1) {
      }
    }
	}

	private void checkSubversion(String folder) throws Exception {
	  SVNClient svnClient = new SVNClient();
	  Status [] status = svnClient.status(folder, true, false, true);

	  for(Status stat : status)
	    revNumber = (revNumber < stat.getRevisionNumber()) ? stat.getRevisionNumber() : revNumber;
	   page.setSvnRevision(Long.toString(revNumber));
	}
	
	private static boolean hasParam(String[] args, String param) {
		for (String a : args)
			if (a.equals(param))
				return true;
		return false;
	}

	public void execute(String folder) throws Exception {

		log("Publish FHIR in folder " + folder+" @ "+Config.DATE_FORMAT().format(page.getGenDate().getTime()));
		checkSubversion(folder);
    
		registerReferencePlatforms();

		if (initialize(folder)) {
	    log("Version "+page.getVersion()+"-"+page.getSvnRevision());

	    cache = new IniFile(page.getFolders().rootDir+"temp"+File.separator+"build.cache");
		  boolean doAny = false;
	    for (String n : dates.keySet()) {
	      Long d = cache.getLongProperty("dates", n);
	      boolean b = d == null || (dates.get(n) > d);
	      cache.setLongProperty("dates", n, dates.get(n).longValue(), null);
	      buildFlags.put(n.toLowerCase(), b);
	      doAny = doAny || b;
	    }
	    if (!doAny)
        buildFlags.put("all", true); // nothing - build all
	    cache.save();
	    
	    if (!buildFlags.get("all"))
	      log("Partial Build (if you want a full build, just run the build again)");
	    Utilities.createDirectory(page.getFolders().dstDir);
			
			if (isGenerate) {
			  if (buildFlags.get("all"))
			    Utilities.clearDirectory(page.getFolders().dstDir);
				Utilities.createDirectory(page.getFolders().dstDir + "html");
				Utilities.createDirectory(page.getFolders().dstDir + "examples");
        Utilities.clearDirectory(page.getFolders().rootDir+"temp"+File.separator+"hl7"+File.separator+"web");
        Utilities.clearDirectory(page.getFolders().rootDir+"temp"+File.separator+"hl7"+File.separator+"dload");
			}
			prsr.parse(page.getGenDate(), page.getVersion());
			defineSpecialValues();

			if (validate()) {
				if (isGenerate) {
					String eCorePath = page.getFolders().dstDir + "ECoreDefinitions.xml";
					generateECore(prsr.getECoreParseResults(), eCorePath);
					produceSpecification(eCorePath);
				}
				validateXml();
		    if (buildFlags.get("all")) 
  		    produceQA();
				log("Finished publishing FHIR @ "+Config.DATE_FORMAT().format(Calendar.getInstance().getTime()));
			} else
				log("Didn't publish FHIR due to errors @ "+Config.DATE_FORMAT().format(Calendar.getInstance().getTime()));
		}
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
            c.setId(getCodeId("resorucetype", s));
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
	      if (!(bs.getName().equals("DataType") || bs.getName().equals("FHIRDefinedType") || bs.getName().equals("ResourceType") || bs.getName().equals("MessageEvent"))) 
	        log("unprocessed special type "+bs.getName());
	    }
	  }
    prsr.getRegistry().commit();
  }

  private String getCodeId(String q, String name) {
    return prsr.getRegistry().idForQName(q, name);
  }

  private void generateECore(
			org.hl7.fhir.definitions.ecore.fhir.Definitions eCoreDefinitions,
			String filename) throws IOException {
		Resource resource = new XMLResourceImpl();
		Map<String, String> options = new HashMap<String, String>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8");
		options.put(XMLResource.OPTION_XML_VERSION, "1.0");

		resource.getContents().add(eCoreDefinitions);
		resource.save(new FileOutputStream(filename), options);
	}

	private void registerReferencePlatforms() {
		page.getReferenceImplementations().add(new DelphiGenerator());
		javaReferencePlatform = new JavaGenerator();
    page.getReferenceImplementations().add(javaReferencePlatform);
		page.getReferenceImplementations().add(new CSharpGenerator());
		page.getReferenceImplementations().add(new ECoreOclGenerator());
	}

  public boolean checkFile(String purpose, String dir, String file, List<String> errors, String category) {
    CSFile f = new CSFile(dir+file);
    if (!f.exists()) {
      errors.add("Unable to find "+purpose+" file "+file+" in "+dir);
      return false;
    } else {
      long d = f.lastModified();
      if (!dates.containsKey(category) || d > dates.get(category))
        dates.put(category, d);
      return true;
    }
  }

	private boolean initialize(String folder) throws Exception {
		page.setDefinitions(new Definitions());
		page.setFolders(new FolderManager(folder));

		log("Checking Source for " + folder);

		List<String> errors = new ArrayList<String>();

		Utilities.checkFolder(page.getFolders().rootDir, errors);
		if (checkFile("required", page.getFolders().rootDir,"publish.ini", errors, "all")) {
			checkFile("required", page.getFolders().srcDir,"navigation.xml", errors, "all");
			page.setIni(new IniFile(page.getFolders().rootDir + "publish.ini"));
			page.setVersion(page.getIni().getStringProperty("FHIR", "version"));

			prsr = new SourceParser(page, folder,page.getDefinitions(), web);
			prsr.checkConditions(errors, dates);

			Utilities.checkFolder(page.getFolders().xsdDir, errors);
			for (PlatformGenerator gen : page.getReferenceImplementations())
				Utilities.checkFolder(page.getFolders().implDir(gen.getName()),errors);
			checkFile("required", page.getFolders().srcDir, "fhir-all.xsd", errors, "all");
			checkFile("required", page.getFolders().srcDir, "header.htm", errors, "all");
			checkFile("required", page.getFolders().srcDir, "footer.htm", errors, "all");
			checkFile("required", page.getFolders().srcDir, "template.htm", errors, "all");
			checkFile("required", page.getFolders().srcDir, "template-book.htm", errors, "all");
			//Utilities.checkFolder(page.getFolders().dstDir, errors);

			for (String n : page.getIni().getPropertyNames("support"))
				checkFile("support", page.getFolders().srcDir, n, errors, "all");
			for (String n : page.getIni().getPropertyNames("images"))
				checkFile("image", page.getFolders().imgDir, n, errors, "all");
			for (String n : page.getIni().getPropertyNames("schema"))
				checkFile("schema", page.getFolders().srcDir, n, errors, "all");
			for (String n : page.getIni().getPropertyNames("pages"))
				checkFile("page", page.getFolders().srcDir, n, errors, "page-"+n);
		}
		
		if (errors.size() > 0)
			log("Unable to publish FHIR specification:");
		for (String e : errors) {
			log(e);
		}
		return errors.size() == 0;
	}

	private boolean validate() throws Exception {
		log("Validating");
		ResourceValidator val = new ResourceValidator(page.getDefinitions());

		List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
    for (String n : page.getDefinitions().getTypes().keySet())
      errors.addAll(val.checkStucture(n, page.getDefinitions().getTypes().get(n)));
    for (String n : page.getDefinitions().getStructures().keySet())
      errors.addAll(val.checkStucture(n, page.getDefinitions().getStructures().get(n)));
		for (String n : page.getDefinitions().sortedResourceNames())
			errors.addAll(val.check(n, page.getDefinitions().getResources().get(n)));
    for (String n : page.getDefinitions().getBindings().keySet())
      errors.addAll(val.check(n, page.getDefinitions().getBindingByName(n)));

    for (String rname : page.getDefinitions().sortedResourceNames()) {
      ResourceDefn r = page.getDefinitions().getResources().get(rname); 
      checkExampleLinks(errors, r);
   }
		
   for (ValidationMessage e : errors) {
     if (e.getLevel() == Level.Hint) {
       System.out.println(e.getLevel().toString()+": "+e.getMessage());
       page.getQa().hint(e.getMessage());
     }
    }
   for (ValidationMessage e : errors) {
     if (e.getLevel() == Level.Warning) {
       System.out.println(e.getLevel().toString()+": "+e.getMessage());
       page.getQa().warning(e.getMessage());
     }
    }
   int t = 0;
   for (ValidationMessage e : errors) {
     if (e.getLevel() == Level.Error) {
       System.out.println(e.getLevel().toString()+": "+e.getMessage());
       t++;
     }
		}
		return t == 0;
	}

	
	
	private void checkExampleLinks(List<ValidationMessage> errors, ResourceDefn r) throws Exception {
	  for (Example e : r.getExamples()) {
	    try {
	      if (e.getXml() != null) {
	        List<ExampleReference> refs = new ArrayList<ExampleReference>(); 
	        listLinks(e.getXml().getDocumentElement(), refs);
	        for (ExampleReference ref : refs) {
	          if (!ref.getId().startsWith("cid:") && !ref.getId().startsWith("urn:") && !ref.getId().startsWith("http:") && !resolveLink(ref)) { 
	            errors.add(new ValidationMessage("Unable to resolve example reference to "+ref.describe()+" in "+e.getPath()
	                  +"\r\n   Possible Ids: "+listTargetIds(ref.getType()), Level.Error));
	          }
	        }
	      } 
	    } catch (Exception ex) {
	      throw new Exception("Error checking example "+e.getFileTitle()+":"+ex.getMessage(), ex);
	    }
	  }
  }

  private String listTargetIds(String type) throws Exception {
    StringBuilder b = new StringBuilder();
    ResourceDefn r = page.getDefinitions().getResourceByName(type);
    if (r != null) {
      for (Example e : r.getExamples()) {
        if (!Utilities.noString(e.getId()))
          b.append(e.getId()+", ");
        if (e.getXml().getDocumentElement().getLocalName().equals("feed")) {
          List<Element> entries = new ArrayList<Element>();
          XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", entries);
          for (Element c : entries) {
            String id = XMLUtil.getNamedChild(c, "id").getTextContent();
            if (id.startsWith("http://hl7.org/fhir/") && id.contains("@"))
              b.append(id.substring(id.indexOf("@")+1)+", ");
            else
              b.append(id+", ");
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
        if (e.getXml().getDocumentElement().getLocalName().equals("feed")) {
          List<Element> entries = new ArrayList<Element>();
          XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", entries);
          for (Element c : entries) {
            String _id = XMLUtil.getNamedChild(c, "id").getTextContent();
            if (id.equals(_id) || _id.equals("http://hl7.org/fhir/"+ref.getType().toLowerCase()+"/@"+id))
              return true;
          }
        }
      }
    }
    return false;
  }

  private String extractId(String id, String type) throws Exception {
    String[] parts = id.split("/");
    if (parts.length < 2)
      throw new Exception("The example reference '"+id+"' is not valid (not enough path parts");
    if (!parts[0].equals(type.toLowerCase()))
      throw new Exception("The example reference '"+id+"' is not valid (the type portion doesn't match the specified type '"+type+"')");
    if (!parts[1].startsWith("@"))
      throw new Exception("The example reference '"+id+"' is not valid (the id doesn't start with @)");
    if (parts[1].length() < 2 || parts[1].length() > 37)
      throw new Exception("The example reference '"+id+"' is not valid (id length 1 - 36)");
    if (!parts[1].substring(1).matches("[a-z0-9\\-\\.]{1,36}"))
      throw new Exception("The example reference '"+id+"' is not valid (id doesn't match regular expression for id)");
    if (parts.length > 2) {
      if (!parts[2].equals("history"))
        throw new Exception("The example reference '"+id+"' is not valid");
      if (parts.length != 4 || !parts[3].startsWith("@")) 
        throw new Exception("The example reference '"+id+"' is not valid");
      if (parts[3].length() < 2 || parts[3].length() > 37)
        throw new Exception("The example reference '"+id+"' is not valid (version id length 1 - 36)");
      if (!parts[3].substring(1).matches("[a-z0-9\\-\\.]{1,36}"))
        throw new Exception("The example reference '"+id+"' is not valid (version id doesn't match regular expression for id)");
    }
    return parts[1].substring(1);
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
          throw new Exception("Unable to find resource definition for "+n);
        List<Element> nodes = new ArrayList<Element>();
        nodes.add(xml);
        listLinks("/f:"+n, r.getRoot(), nodes, refs);
        
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
          refs.add(new ExampleReference(XMLUtil.getNamedChild(m, "type").getAttribute("value"), XMLUtil.getNamedChild(m, "reference").getAttribute("value")));
        }
      }
    }    
    for (org.hl7.fhir.definitions.model.ElementDefn c : d.getElements()) {
      List<Element> cset = new ArrayList<Element>();
      for (Element p : set) 
        XMLUtil.getNamedChildren(p, c.getName(), cset);
      listLinks(path+"/f:"+c.getName(), c, cset, refs);
    }
  }

//  private List<Element> xPathQuery(String path, Element e) throws Exception {
//    NamespaceContext context = new NamespaceContextMap("f", "http://hl7.org/fhir", "h", "http://www.w3.org/1999/xhtml", "a", );
//
//    XPathFactory factory = XPathFactory.newInstance();
//    XPath xpath = factory.newXPath();
//    xpath.setNamespaceContext(context);
//    XPathExpression expression= xpath.compile(path);
//    NodeList resultNodes = (NodeList)expression.evaluate(e, XPathConstants.NODESET);
//    List<Element> result = new ArrayList<Element>();
//    for (int i = 0; i < resultNodes.getLength(); i++) {
//      result.add((Element) resultNodes.item(i));
//    }
//    return result;
//  }

  private void produceSpecification(String eCorePath) throws Exception {
		page.setNavigation(new Navigation());
		page.getNavigation().parse(page.getFolders().srcDir + "navigation.xml");
		book = new BookMaker(page);

		XMIResource resource = new XMIResourceImpl();
		resource.load(new CSFileInputStream(eCorePath), null);
		org.hl7.fhir.definitions.ecore.fhir.Definitions eCoreDefs = (org.hl7.fhir.definitions.ecore.fhir.Definitions) resource.getContents().get(0);

		log("Produce Schemas");
    new SchemaGenerator().generate(page.getDefinitions(), page.getIni(), page.getFolders().tmpResDir, page.getFolders().xsdDir, page.getFolders().dstDir, 
          page.getFolders().srcDir, page.getVersion(), Config.DATE_FORMAT().format(page.getGenDate().getTime()));

		for (PlatformGenerator gen : page.getReferenceImplementations()) {
			log("Produce " + gen.getName() + " Reference Implementation");

			String destDir = page.getFolders().dstDir;
			String implDir = page.getFolders().implDir(gen.getName());

			if (!gen.isECoreGenerator())
				gen.generate(page.getDefinitions(), destDir, implDir, page.getVersion(), page.getGenDate().getTime(), page, page.getSvnRevision());
			else
				gen.generate(eCoreDefs, destDir, implDir, page);
		}
		for (PlatformGenerator gen : page.getReferenceImplementations()) {
			if (gen.doesCompile()) {
				log("Compile " + gen.getName() + " Reference Implementation");
				if (!gen.compile(page.getFolders().rootDir, new ArrayList<String>())) {
					// log("Compile " + gen.getName() + " failed, still going on.");
					throw new Exception("Compile " + gen.getName() + " failed");
				}
			}
		}

    log("Produce Schematrons");
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
		log("Produce Content");
		produceSpec();

		if (buildFlags.get("all")) {
		  if (web) {
	      if (!new File(page.getFolders().archiveDir).exists())
	        throw new Exception("Unable to build HL7 copy with no archive directory (sync svn at one level up the tree)");

		    log("Produce HL7 copy");
		    wm = new WebMaker(page.getFolders(), page.getVersion(), page.getIni());
		    wm.produceHL7Copy();
		  }
      if (new File(page.getFolders().archiveDir).exists()) {
		    log("Produce Archive copy");
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
				if (f.endsWith(".htm")) {
					String src = TextFile.fileToString(fn.getAbsolutePath());
					String srcn = src
							.replace(
									"Warning: FHIR is a draft specification that is still undergoing development prior to balloting as a full HL7 standard",
									"This is an old version of FHIR retained for archive purposes. Do not use for anything else");
					if (!srcn.equals(src))
						c++;
					srcn = srcn.replace("<body>",
							"<body><div class=\"watermark\"/>").replace(
							"<body class=\"book\">",
							"<body class=\"book\"><div class=\"watermark\"/>");
					zip.addFileSource(f, srcn);
					// Utilities.stringToFile(srcn, target+File.separator+f);
				} else if (f.endsWith(".css")) {
					String src = TextFile.fileToString(fn.getAbsolutePath());
					src = src.replace("#fff", "lightcyan");
					zip.addFileSource(f, src);
					// Utilities.stringToFile(srcn, target+File.separator+f);
				} else
					zip.addFileName(f, fn.getAbsolutePath());
			} else if (!fn.getAbsolutePath().endsWith("v2") && !fn.getAbsolutePath().endsWith("v3") ) {
				// used to put stuff in sub-directories. clean them out if they
				// still exist
				Utilities.clearDirectory(fn.getAbsolutePath());
				fn.delete();
			}
		}
		if (c < 3)
			throw new Exception("header note replacement in archive failed");
		zip.close();
	}

	private void produceSpec() throws Exception {
	  if (buildFlags.get("all")) {

	    for (String n : page.getIni().getPropertyNames("support"))
	      Utilities.copyFile(new CSFile(page.getFolders().srcDir + n),
	          new CSFile(page.getFolders().dstDir + n));
	    for (String n : page.getIni().getPropertyNames("images"))
	      Utilities.copyFile(new CSFile(page.getFolders().imgDir + n),
	          new CSFile(page.getFolders().dstDir + n));


	    profileFeed = new AtomFeed();
	    profileFeed.setId("http://hl7.org/fhir/profile/resources");
	    profileFeed.setTitle("Resources as Profiles");
	    profileFeed.getLinks().put("self", "http://hl7.org/implement/standards/fhir/profiles-resources.xml");
	    profileFeed.setUpdated(Calendar.getInstance());
	    for (String n : page.getDefinitions().getDiagrams().keySet()) {
	      log(" ...diagram "+n);
	      page.getImageMaps().put(n, new DiagramGenerator(page).generateFromSource(n, page.getFolders().srcDir + page.getDefinitions().getDiagrams().get(n)));
	    }

      generateValueSets();
      generateCodeSystems();
	    log(" ...profiles");
	  }
	  for (String rname : page.getDefinitions().sortedResourceNames()) {
	    if (wantBuild(rname)) {
	      ResourceDefn r = page.getDefinitions().getResources().get(rname); 
	      produceResource1(r);      
	    }
	  }
	  if (buildFlags.get("all")) {
	    produceBaseProfile();
	  }
	  for (String rname : page.getDefinitions().sortedResourceNames()) {
	    if (wantBuild(rname)) {
	      ResourceDefn r = page.getDefinitions().getResources().get(rname); 
	      log(" ...resource "+r.getName());
	      produceResource2(r);
	    }
	  }

	  if (buildFlags.get("all")) {
	    new AtomComposer().compose(new FileOutputStream(page.getFolders().dstDir + "profiles-resources.xml"), profileFeed, true, false);
	    new JsonComposer().compose(new FileOutputStream(page.getFolders().dstDir + "profiles-resources.json"), profileFeed);
	    // all the profiles are already individually in the examples, so no need to add this one to them as well
	    // Utilities.copyFile(new CSFile(page.getFolders().dstDir + "profiles-resources.xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + "profiles-resources.xml"));
	    cloneToXhtml("profiles-resources", "Base Resources defined as profiles (implementation assistance, for derivation and product development)");
	  }
	  for (String n : page.getIni().getPropertyNames("pages")) {
	    if (buildFlags.get("all") || buildFlags.get("page-"+n.toLowerCase())) {
	      log(" ...page "+n);
	      producePage(n, page.getIni().getStringProperty("pages", n));
	    }
	  }
	  if (buildFlags.get("all")) {
	    log(" ...check Fragments");
	    checkFragments();
	    for (String n : page.getDefinitions().getProfiles().keySet()) {
	      log(" ...profile "+n);
	      produceProfile(n, page.getDefinitions().getProfiles().get(n), null);
	    }

      produceV2();
      produceV3();
      
      log(" ...zips");
	    ZipGenerator zip = new ZipGenerator(page.getFolders().dstDir + "examples.zip");
	    zip.addFiles(page.getFolders().dstDir + "examples" + File.separator, "", null);
	    zip.close();

	    zip = new ZipGenerator(page.getFolders().dstDir + "examples-json.zip");
	    zip.addFiles(page.getFolders().dstDir, "", ".json");
	    zip.close();

	    log(" ...zip");
	    produceZip();

	    
	    log("Produce Book Form");
	    book.produce();
	  }
	  else 
	    log("Partial Build - terminating now");
	}

  private void produceQA() throws Exception {
    page.getQa().countDefinitions(page.getDefinitions());
    
    String src = TextFile.fileToString(page.getFolders().srcDir+ "qa.htm");
    TextFile.stringToFile(page.processPageIncludes("qa.htm", src), page.getFolders().dstDir+"qa.htm");
    
    if (web) {
      page.getQa().commit(page.getFolders().rootDir);
      wm.addPage("qa.htm");
    }
  }

  private void produceV3() throws Exception {
    log(" ...v3 Code Systems");
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    page.setV3src(builder.parse(new CSFileInputStream(new CSFile(page.getFolders().srcDir + "v3"+File.separator+"source.xml"))));
    
    Utilities.createDirectory(page.getFolders().dstDir + "v3");
    Utilities.clearDirectory(page.getFolders().dstDir + "v3");
    String src = TextFile.fileToString(page.getFolders().srcDir+ "v3"+File.separator+"template.htm");
    TextFile.stringToFile(page.processPageIncludes("v3/template.htm", src), page.getFolders().dstDir+"terminologies-v3.htm");
    src = TextFile.fileToString(page.getFolders().srcDir+ "v3"+File.separator+"template.htm");
    cachePage("terminologies-v3.htm", page.processPageIncludesForBook("v3/template.htm", src));
    
    Element e = XMLUtil.getFirstChild(page.getV3src().getDocumentElement());
    while (e != null) {
      if (e.getNodeName().equals("codeSystem")) {
        Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "header"), "responsibleGroup");
        if (r != null && "Health Level 7".equals(r.getAttribute("organizationName"))) {
          String id = e.getAttribute("name");
          Utilities.createDirectory(page.getFolders().dstDir + "v3"+File.separator+id);
          Utilities.clearDirectory(page.getFolders().dstDir + "v3"+File.separator+id);
          src = TextFile.fileToString(page.getFolders().srcDir+ "v3"+File.separator+"template-cs.htm");
          TextFile.stringToFile(page.processPageIncludes(id+".htm", src), page.getFolders().dstDir + "v3"+File.separator+id+File.separator+"index.htm");
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
    
  }
  
  private void produceV2() throws Exception {
    log(" ...v2 Tables");
    
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    page.setV2src(builder.parse(new CSFileInputStream(new CSFile(page.getFolders().srcDir + "v2"+File.separator+"source.xml"))));

    Utilities.createDirectory(page.getFolders().dstDir + "v2");
    Utilities.clearDirectory(page.getFolders().dstDir + "v2");
    String src = TextFile.fileToString(page.getFolders().srcDir+ "v2"+File.separator+"template.htm");
    TextFile.stringToFile(page.processPageIncludes("v2/template.htm", src), page.getFolders().dstDir + "terminologies-v2.htm");
    src = TextFile.fileToString(page.getFolders().srcDir+ "v2"+File.separator+"template.htm");
    cachePage("terminologies-v2.htm", page.processPageIncludesForBook("v2/template.htm", src));
    
    Element e = XMLUtil.getFirstChild(page.getV2src().getDocumentElement());
    while (e != null) {
      String st = e.getAttribute("state");
      if ("include".equals(st)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        Utilities.createDirectory(page.getFolders().dstDir + "v2"+File.separator+id);
        Utilities.clearDirectory(page.getFolders().dstDir + "v2"+File.separator+id);
        src = TextFile.fileToString(page.getFolders().srcDir+ "v2"+File.separator+"template-tbl.htm");
        TextFile.stringToFile(page.processPageIncludes(id+".htm", src), page.getFolders().dstDir + "v2"+File.separator+id+File.separator+"index.htm");
      } else if ("versioned".equals(st)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        Utilities.createDirectory(page.getFolders().dstDir + "v2"+File.separator+id);
        Utilities.clearDirectory(page.getFolders().dstDir + "v2"+File.separator+id);
        List<String> versions = new ArrayList<String>();
        Element c = XMLUtil.getFirstChild(e);
        while (c != null) {
          if (XMLUtil.getFirstChild(c) != null && !versions.contains(c.getAttribute("namespace"))) {
            versions.add(c.getAttribute("namespace"));
          }
          c = XMLUtil.getNextSibling(c);
        }
        for (String ver : versions) {
          Utilities.createDirectory(page.getFolders().dstDir + "v2"+File.separator+id+File.separator+ver);
          Utilities.clearDirectory(page.getFolders().dstDir + "v2"+File.separator+id+File.separator+ver);
          src = TextFile.fileToString(page.getFolders().srcDir+ "v2"+File.separator+"template-tbl-ver.htm");
          TextFile.stringToFile(page.processPageIncludes(id+"|"+ver+".htm", src), page.getFolders().dstDir + "v2"+File.separator+id+File.separator+ver+File.separator+"index.htm");
        }        
      }
      e = XMLUtil.getNextSibling(e);
    }
        
  }

  private boolean wantBuild(String rname) {
    rname = rname.toLowerCase();
    return buildFlags.get("all") || (!buildFlags.containsKey(rname) || buildFlags.get(rname));
  }

  private void produceBaseProfile() throws Exception {
    ProfileDefn p = new ProfileDefn();
    p.putMetadata("id", "fhir.types");
    p.putMetadata("name", "Base Type Profiles");
    p.putMetadata("author.name", "FHIR Specification");
    p.putMetadata("author.ref", "http://hl7.org/fhir");
    p.putMetadata("description", "Basic Profile for the FHIR types - distributed to assist with implementation");
    p.putMetadata("status", "testing");
    p.putMetadata("date", new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "US")).format(new Date()));
    for (ElementDefn e : page.getDefinitions().getTypes().values())
      p.getElements().add(e);
    ProfileGenerator pgen = new ProfileGenerator(page.getDefinitions());
    Profile rp = pgen.generate(p, new FileOutputStream(page.getFolders().dstDir + "types.profile.xml"), "<div>Type definitions from <a href=\"http://hl7.org/fhir/datatypes.htm\">FHIR Specification</a></div>", false);
    Utilities.copyFile(new CSFile(page.getFolders().dstDir + "types.profile.xml"), new CSFile(page.getFolders().dstDir+ "examples" + File.separator + "types.profile.xml"));
    addToResourceFeed(rp, "types");
    saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir+ "html" + File.separator + "datatypes.htm"));    
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
      s.append("<test id=\""+Integer.toString(i)+"\" page=\""+f.getPage()+"\" type=\""+f.getType()+"\">\r\n");
      s.append(f.getXml());
      s.append("</test>\r\n");
      i++;
    }  
    s.append("</tests>\r\n");
    String err = javaReferencePlatform.checkFragments(page.getFolders().dstDir, s.toString());
    if (err == null)
      throw new Exception("Unable to process outcome of checking fragments");
    if (!err.startsWith("<results"))
      throw new Exception(err);

    XmlPullParser xpp = loadXml(new ByteArrayInputStream(err.getBytes()));
    nextNoWhitespace(xpp);
    xpp.next();
    nextNoWhitespace(xpp);
    
    while (xpp.getEventType() == XmlPullParser.START_TAG && xpp.getName().equals("result")) {
      String id = xpp.getAttributeValue(null, "id");
      String outcome = xpp.getAttributeValue(null, "outcome");
      if (!"ok".equals(outcome)) {
        Fragment f = fragments.get(Integer.parseInt(id));
        String msg = "Fragment Error in page "+f.getPage()+": "+xpp.getAttributeValue(null, "msg")+" for\r\n"+f.getXml();
        log(msg);
        errors.add(msg);
      }
      xpp.next();
      nextNoWhitespace(xpp);
    }
    if (errors.size() > 0) 
      throw new Exception("Fragment Errors prevent publication from continuing");
  }

  private void produceZip() throws Exception {
		File f = new CSFile(page.getFolders().dstDir + "fhir-spec.zip");
		if (f.exists())
			f.delete();
		ZipGenerator zip = new ZipGenerator(page.getFolders().tmpResDir
				+ "fhir-spec.zip");
		zip.addFiles(page.getFolders().dstDir, "site\\", null);
    zip.addFileName("index.htm", page.getFolders().srcDir+"redirect.htm");
		zip.close();
		Utilities.copyFile(new CSFile(page.getFolders().tmpResDir
				+ "fhir-spec.zip"), f);
	}

	private void produceSchemaZip() throws Exception {
		char sc = File.separatorChar;
		File f = new CSFile(page.getFolders().dstDir + "fhir-all-xsd.zip");
		if (f.exists())
			f.delete();
		ZipGenerator zip = new ZipGenerator(page.getFolders().tmpResDir
				+ "fhir-all-xsd.zip");
		zip.addFiles(page.getFolders().dstDir, "", ".xsd");
		zip.addFiles(page.getFolders().dstDir, "", ".sch");
		zip.addFiles(page.getFolders().rootDir + "tools" + sc + "schematron" + sc, "", ".xsl");
		zip.close();
		Utilities.copyFile(new CSFile(page.getFolders().tmpResDir + "fhir-all-xsd.zip"), f);
	}

  private void produceResource1(ResourceDefn resource) throws Exception {
    File tmp = File.createTempFile("tmp", ".tmp");
    tmp.deleteOnExit();
    String n = resource.getName().toLowerCase();
    
    XmlSpecGenerator gen = new XmlSpecGenerator(new FileOutputStream(tmp), n + "-definitions.htm", null, page.getDefinitions());
    gen.generate(resource.getRoot());
    gen.close();
    String xml = TextFile.fileToString(tmp.getAbsolutePath());

    xmls.put(n, xml);
    generateProfile(resource, n, xml, true);
  }
  
  private void produceResource2(ResourceDefn resource) throws Exception {
	  File tmp = File.createTempFile("tmp", ".tmp");
	  tmp.deleteOnExit();
	  String n = resource.getName().toLowerCase();
	  String xml = xmls.get(n);

	  TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(new FileOutputStream(tmp), page);
	  tgen.generate(resource.getRoot(), page.getDefinitions().getBindings());
	  tgen.close();
	  String tx = TextFile.fileToString(tmp.getAbsolutePath());

	  DictHTMLGenerator dgen = new DictHTMLGenerator(new FileOutputStream(tmp), page.getDefinitions());
	  dgen.generate(resource.getRoot());
	  dgen.close();
	  String dict = TextFile.fileToString(tmp.getAbsolutePath());

	  MappingsGenerator mgen = new MappingsGenerator();
	  mgen.generate(resource);
	  String mappings = mgen.getMappings();
	  String mappingsList = mgen.getMappingsList();
	  
	  page.getImageMaps().put(n, new DiagramGenerator(page).generate(resource, n));

	  for (RegisteredProfile p : resource.getProfiles())
		  produceProfile(p.getFilename(), p.getProfile(), p.getExamplePath());

	  for (Example e : resource.getExamples()) {
		  try {
			  processExample(e);
		  } catch (Exception ex) {
			  throw new Exception("processing "+e.getFileTitle(), ex);
			  //		    throw new Exception(ex.getMessage()+" processing "+e.getFileTitle());
		  }
	  }

    String prefix = page.getNavigation().getIndexPrefixForFile(n+".htm");
    if (Utilities.noString(prefix))
      prefix = "3."+Integer.toString(page.getOrderedResources().indexOf(resource.getName())+1);
    SectionTracker st = new SectionTracker(prefix);
    page.getSectionTrackerCache().put(n, st);

    String src = TextFile.fileToString(page.getFolders().srcDir+ "template.htm");
		src = insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList), st, n+".htm");
		TextFile.stringToFile(src, page.getFolders().dstDir + n + ".htm");
			
    String pages = page.getIni().getStringProperty("resource-pages", n);
    if (!Utilities.noString(pages)) {
      for (String p : pages.split(",")) {
        producePage(p, n);
      }
    }
		
		src = TextFile.fileToString(page.getFolders().srcDir+ "template-examples.htm");
		TextFile.stringToFile(insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList), st, n + "-examples.htm"), page.getFolders().dstDir + n + "-examples.htm");
		src = TextFile.fileToString(page.getFolders().srcDir + "template-definitions.htm");
		TextFile.stringToFile(insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList), st, n + "-definitions.htm"), page.getFolders().dstDir + n + "-definitions.htm");
		src = TextFile.fileToString(page.getFolders().srcDir + "template-mappings.htm");
		TextFile.stringToFile(insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList), st, n + "-mappings.htm"), page.getFolders().dstDir + n + "-mappings.htm");
		src = TextFile.fileToString(page.getFolders().srcDir + "template-explanations.htm");
		TextFile.stringToFile(insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList), st, n + "-explanations.htm"), page.getFolders().dstDir + n + "-explanations.htm");
		src = TextFile.fileToString(page.getFolders().srcDir + "template-profiles.htm");
		TextFile.stringToFile(insertSectionNumbers(page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList), st, n + "-profiles.htm"), page.getFolders().dstDir + n + "-profiles.htm");

		src = TextFile.fileToString(page.getFolders().srcDir + "template-book.htm").replace("<body>", "<body style=\"margin: 10px\">");
		src = page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList);
		cachePage(n + ".htm", src);
		src = TextFile.fileToString(page.getFolders().srcDir + "template-book-ex.htm").replace("<body>", "<body style=\"margin: 10px\">");
		src = page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList);
		cachePage(n + "Ex.htm", src);
		src = TextFile.fileToString(page.getFolders().srcDir + "template-book-defn.htm").replace("<body>", "<body style=\"margin: 10px\">");
		src = page.processResourceIncludes(n, resource, xml, tx, dict, src, mappings, mappingsList);
		cachePage(n + "-definitions.htm", src);
		cachePage(n + "Defn.htm", src);

		// xml to json
		// todo - fix this up
		// JsonGenerator jsongen = new JsonGenerator();
		// jsongen.generate(new CSFile(page.getFolders().dstDir+n+".xml"), new
		// File(page.getFolders().dstDir+n+".json"));

		tmp.delete();

	}

  private void cloneToXhtml(String n, String description) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document xdoc = builder.parse(new CSFileInputStream(new CSFile(page
				.getFolders().dstDir + n + ".xml")));
		XhtmlGenerator xhtml = new XhtmlGenerator(null);
		xhtml.generate(xdoc,
				new CSFile(page.getFolders().dstDir + n + ".xml.htm"), n
						.toUpperCase().substring(0, 1) + n.substring(1),
				description);
	}

	private void processExample(Example e) throws Exception {
		if (e.getType() == ExampleType.Tool)
			return;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		String n = e.getFileTitle();

		if (!e.getPath().exists())
		  throw new Exception("unable to find example file");

		// strip the xsi: stuff. seems to need double processing in order to
		// delete namespace crap
		Document xdoc = e.getXml() == null ? builder.parse(new CSFileInputStream(e.getPath())) : e.getXml();
		XmlGenerator xmlgen = new XmlGenerator();
		if (xdoc.getDocumentElement().getLocalName().equals("feed"))
		  xmlgen.generate(xdoc.getDocumentElement(), new CSFile(page.getFolders().dstDir + n + ".xml"), "http://www.w3.org/2005/Atom", xdoc.getDocumentElement().getLocalName());
		else {
		  xmlgen.generate(xdoc.getDocumentElement(), new CSFile(page.getFolders().dstDir + n + ".xml"), "http://hl7.org/fhir", xdoc.getDocumentElement().getLocalName());
		}

		// generate the json version (use the java reference platform)
    try {
      javaReferencePlatform.convertToJson(page.getFolders().dstDir, page.getFolders().dstDir + n + ".xml", page.getFolders().dstDir + n + ".json");
    } catch (Throwable t) {
      System.out.println("Error processing "+page.getFolders().dstDir + n + ".xml");
      t.printStackTrace(System.err);
      TextFile.stringToFile(t.getMessage(), page.getFolders().dstDir + n + ".json");
    }
    String json;
    try {
      json = Utilities.escapeXml(new JSONObject(TextFile.fileToString(page.getFolders().dstDir + n + ".json")).toString(2));
    } catch (Throwable t) {
      t.printStackTrace(System.err);
      json = t.getMessage();
    }
    
    String head = 
    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\r\n<head>\r\n <title>"+Utilities.escapeXml(e.getDescription())+"</title>\r\n <link rel=\"Stylesheet\" href=\"fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n"+
    "</head>\r\n<body>\r\n<p>&nbsp;</p>\r\n<div class=\"example\">\r\n<p>"+Utilities.escapeXml(e.getDescription())+"</p>\r\n<pre class=\"json\">\r\n";
    String tail = "\r\n</pre>\r\n</div>\r\n</body>\r\n</html>\r\n";
    TextFile.stringToFile(head+json+tail, page.getFolders().dstDir + n + ".json.htm");
    e.setJson("<div class=\"example\">\r\n<p>"+Utilities.escapeXml(e.getDescription())+"</p>\r\n<pre class=\"json\">\r\n"+json+"\r\n</pre>\r\n</div>\r\n");  

		// reload it now, xml to xhtml of xml
		builder = factory.newDocumentBuilder();
		xdoc = builder.parse(new CSFileInputStream(new CSFile(page.getFolders().dstDir + n + ".xml")));
		XhtmlGenerator xhtml = new XhtmlGenerator(new ExampleAdorner(page.getDefinitions()));
		xhtml.generate(xdoc, new CSFile(page.getFolders().dstDir + n + ".xml.htm"), n.toUpperCase().substring(0, 1) + n.substring(1), Utilities.noString(e.getId()) ? e.getDescription() : e.getDescription()+" (id = \""+e.getId()+"\")");
		if (e.isInBook()) {
			XhtmlDocument d = new XhtmlParser().parse(new CSFileInputStream(page.getFolders().dstDir + n + ".xml.htm"), "html");
			XhtmlNode pre = d.getElement("html").getElement("body").getElement("div");
			e.setXhtm(new XhtmlComposer().compose(pre));
		}
		if (!Utilities.noString(e.getId()))
      Utilities.copyFile(new CSFile(page.getFolders().dstDir + n + ".xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + n + "("+e.getId()+").xml"));
		else
		  Utilities.copyFile(new CSFile(page.getFolders().dstDir + n + ".xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + n + ".xml"));
	}

	private void generateProfile(ResourceDefn root, String n, String xmlSpec, boolean addBase)	throws Exception, FileNotFoundException {
		ProfileDefn p = new ProfileDefn();
		p.putMetadata("id", root.getName().toLowerCase());
		p.putMetadata("name", n);
		p.putMetadata("author.name", "todo (committee)");
		p.putMetadata("author.ref", "todo");
		p.putMetadata("description", "Basic Profile. "+ root.getRoot().getDefinition());
		p.putMetadata("status", "testing");
		p.putMetadata("date", new SimpleDateFormat("yyyy-MM-dd", new Locale("en", "US")).format(new Date()));
		p.getResources().add(root);
		ProfileGenerator pgen = new ProfileGenerator(page.getDefinitions());
		Profile rp = pgen.generate(p, new FileOutputStream(page.getFolders().dstDir + n + ".profile.xml"), xmlSpec, addBase);
		Utilities.copyFile(new CSFile(page.getFolders().dstDir + n+ ".profile.xml"), new CSFile(page.getFolders().dstDir+ "examples" + File.separator + n + ".profile.xml"));
		if (buildFlags.get("all"))
		  addToResourceFeed(rp, root.getName().toLowerCase());
		saveAsPureHtml(rp, new FileOutputStream(page.getFolders().dstDir+ "html" + File.separator + n + ".htm"));
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

  private void addToResourceFeed(Profile profile, String id) {
    AtomEntry e = new AtomEntry();
    e.setId("http://hl7.org/fhir/profile/" + id);
    e.getLinks().put("self", "http://hl7.org/implement/standards/fhir/" + id+ ".profile.xml");
    e.setTitle("Resource \"" + id+ "\" as a profile (to help derivation)");
    e.setUpdated(page.getGenDate());
    e.setPublished(page.getGenDate());
    e.setAuthorName("HL7, Inc");
    e.setAuthorUri("http://hl7.org");
    e.setCategory("Profile");
    e.setResource(profile);
    e.setSummary(profile.getText().getDiv());
    profileFeed.getEntryList().add(e);
  }

  private void addToResourceFeed(ValueSet vs, String id) {
    AtomEntry e = new AtomEntry();
    e.setId("http://hl7.org/fhir/valueset/" + id);
    e.getLinks().put("self", "http://hl7.org/implement/standards/fhir/valueset/" + id);
    e.setTitle("Valueset \"" + id+ "\" to support automated processing");
    e.setUpdated(page.getGenDate());
    e.setPublished(page.getGenDate());
    e.setAuthorName("HL7, Inc");
    e.setAuthorUri("http://hl7.org");
    e.setResource(vs);
    e.setSummary(vs.getText().getDiv());
    profileFeed.getEntryList().add(e);
  }

	private void produceProfile(String filename, ProfileDefn profile, String example)
			throws Exception {
		File tmp = File.createTempFile("tmp", ".tmp");
		tmp.deleteOnExit();

		// you have to validate a profile, because it has to be merged with it's
		// base resource to fill out all the missing bits
		validateProfile(profile);

		XmlSpecGenerator gen = new XmlSpecGenerator(new FileOutputStream(tmp), null, "http://hl7.org/fhir/", page.getDefinitions());
		gen.generate(profile);
		gen.close();
		String xml = TextFile.fileToString(tmp.getAbsolutePath());

		ProfileGenerator pgen = new ProfileGenerator(page.getDefinitions());
		pgen.generate(profile, new FileOutputStream(page.getFolders().dstDir + filename + ".profile.xml"), xml, false);
		Utilities.copyFile(new CSFile(page.getFolders().dstDir + filename + ".profile.xml"), new CSFile(page.getFolders().dstDir + "examples" + File.separator + filename + ".profile.xml"));

		TerminologyNotesGenerator tgen = new TerminologyNotesGenerator(new FileOutputStream(tmp), page);
		tgen.generate(profile);
		tgen.close();
		String tx = TextFile.fileToString(tmp.getAbsolutePath());
		
		String exXml = "<p><i>No Example Provided</i></p>";
		if (example != null) {
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    factory.setNamespaceAware(true);
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document xdoc = builder.parse(new CSFileInputStream(example));
	    // strip namespace - see below
	    XmlGenerator xmlgen = new XmlGenerator();
	    xmlgen.generate(xdoc.getDocumentElement(), tmp, "http://hl7.org/fhir", xdoc.getDocumentElement().getLocalName());
	    builder = factory.newDocumentBuilder();
	    xdoc = builder.parse(new CSFileInputStream(tmp.getAbsolutePath()));
	    XhtmlGenerator xhtml = new XhtmlGenerator(null);
	    exXml = xhtml.generateInsert(xdoc, "Profile Example", null);
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
		String src = TextFile.fileToString(page.getFolders().srcDir
				+ "template-profile.htm");
		src = page.processProfileIncludes(filename, profile, xml, tx, src, exXml);
		book.getPages().put(filename+".htm", new XhtmlParser().parse(src, "html"));
		TextFile.stringToFile(src, page.getFolders().dstDir + filename + ".htm");
		//
		// src = Utilities.fileToString(page.getFolders().srcDir +
		// "template-print.htm").replace("<body>",
		// "<body style=\"margin: 20px\">");
		// src = processResourceIncludes(n, root, xml, tx, dict, src);
		// Utilities.stringToFile(src, page.getFolders().dstDir +
		// "print-"+n+".htm");
		// Utilities.copyFile(umlf, new
		// File(page.getFolders().dstDir+n+".png"));
		// src = Utilities.fileToString(page.getFolders().srcDir +
		// "template-book.htm").replace("<body>",
		// "<body style=\"margin: 10px\">");
		// src = processResourceIncludes(n, root, xml, tx, dict, src);
		// cachePage(n+".htm", src);
		//
		// xml to xhtml of xml
		// first pass is to strip the xsi: stuff. seems to need double
		// processing in order to delete namespace crap
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xdoc = builder.parse(new CSFileInputStream(
				page.getFolders().dstDir + filename + ".profile.xml"));
		XmlGenerator xmlgen = new XmlGenerator();
		xmlgen.generate(xdoc.getDocumentElement(), tmp, "http://hl7.org/fhir",
				xdoc.getDocumentElement().getLocalName());

		// reload it now
		builder = factory.newDocumentBuilder();
		xdoc = builder.parse(new CSFileInputStream(tmp.getAbsolutePath()));
		XhtmlGenerator xhtml = new XhtmlGenerator(null);
		xhtml.generate(xdoc, new CSFile(page.getFolders().dstDir + filename
				+ ".profile.xml.htm"), "Profile", profile.metadata("name"));
		// // xml to json
		// JsonGenerator jsongen = new JsonGenerator();
		// jsongen.generate(new CSFile(page.getFolders().dstDir+n+".xml"), new
		// File(page.getFolders().dstDir+n+".json"));
		//
		tmp.delete();

	}

	private void validateProfile(ProfileDefn profile)
			throws FileNotFoundException, Exception {
		for (ResourceDefn c : profile.getResources()) {
			Profile resource = loadResourceProfile(c.getName());
			ProfileValidator v = new ProfileValidator();
			v.setCandidate(c);
			v.setProfile(resource);
			v.setTypes(loadResourceProfile("types"));
			List<String> errors = v.evaluate();
			if (errors.size() > 0)
				throw new Exception("Error validating "+ profile.metadata("name") + ": " + errors.toString());
		}
	}

	// private void produceFutureResource(String n) throws Exception {
	// ElementDefn e = new ElementDefn();
	// e.setName(page.getIni().getStringProperty("future-resources", n));
	// }

	private Profile loadResourceProfile(String name)
			throws FileNotFoundException, Exception {
		XmlParser xml = new XmlParser();
		try {
		return (Profile) xml.parse(new CSFileInputStream(page.getFolders().dstDir + name.toLowerCase() + ".profile.xml"));
		} catch (Exception e) {
		  throw new Exception("error parsing "+name, e);
		}
	}

	private void producePage(String file, String logicalName) throws Exception {
		String src = TextFile.fileToString(page.getFolders().srcDir + file);
		src = page.processPageIncludes(file, src);
		// before we save this page out, we're going to figure out what it's index is, and number the headers if we can
		
		if (!Utilities.noString(logicalName)) {
		  if (!page.getSectionTrackerCache().containsKey(logicalName)) {
		    String prefix = page.getNavigation().getIndexPrefixForFile(logicalName+".htm");
		    if (Utilities.noString(prefix))
		      throw new Exception("No indexing home for logical place "+logicalName);
		    page.getSectionTrackerCache().put(logicalName, new SectionTracker(prefix));
		  }
	    TextFile.stringToFile(src, page.getFolders().dstDir + file);    
		  src = insertSectionNumbers(src, page.getSectionTrackerCache().get(logicalName), file);
		}
		TextFile.stringToFile(src, page.getFolders().dstDir + file);		

		src = TextFile.fileToString(page.getFolders().srcDir + file).replace("<body>", "<body style=\"margin: 10px\">");
		src = page.processPageIncludesForBook(file, src);
		cachePage(file, src);
	}

	private String insertSectionNumbers(String src, SectionTracker st, String link) throws Exception  {
    try {
//      TextFile.stringToFile(src, "c:\\temp\\text.htm");
      XhtmlDocument doc = new XhtmlParser().parse(src, "html");
      insertSectionNumbersInNode(doc, st, link);
      return new XhtmlComposer().compose(doc);
    } catch (Exception e) {
      throw new Exception("Exception processing "+link+": "+e.getMessage(), e);
    } 
  }

  private void insertSectionNumbersInNode(XhtmlNode node, SectionTracker st, String link) throws Exception {
    if (node.getNodeType() == NodeType.Element && (node.getName().equals("h1") || node.getName().equals("h2") || node.getName().equals("h3") ||
         node.getName().equals("h4") || node.getName().equals("h5") || node.getName().equals("h6"))) {
      String v = st.getIndex(Integer.parseInt(node.getName().substring(1)));
      TocEntry t = new TocEntry(v, node.allText(), link);
      page.getToc().put(v, t);      
      node.addText(" ");
      XhtmlNode span = node.addTag("span");
      span.setAttribute("class", "sectioncount");
      span.addText(v);
      XhtmlNode a = span.addTag("a");
      a.setAttribute("name", v);
      a.addText(" "); // bug in some browsers?
    }
    if (node.getNodeType() == NodeType.Document || (node.getNodeType() == NodeType.Element && !(node.getName().equals("div") && "sidebar".equals(node.getAttribute("class"))))) {
      for (XhtmlNode n : node.getChildNodes()) {
        insertSectionNumbersInNode(n, st, link);
      }
    }
  }

  private void cachePage(String filename, String source) throws Exception {
		try {
			// log("parse "+filename);
			XhtmlDocument src = new XhtmlParser().parse(source, "html");
			scanForFragments(filename, src);
      book.getPages().put(filename, src);
		} catch (Exception e) {
			throw new Exception("error parsing page " + filename + ": "
					+ e.getMessage() + " in source\r\n" + source);
		}
	}

	private void scanForFragments(String filename, XhtmlNode node ) throws Exception {
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
    fragments .add(f);
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
		public LSInput resolveResource(final String type,
				final String namespaceURI, final String publicId,
				String systemId, final String baseURI) {
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

	private void validateXml() throws Exception {
    if (buildFlags.get("all"))
  	  produceCoverageWarnings();
		log("Validating XML");
		log(".. Loading schemas");
		StreamSource[] sources = new StreamSource[2];
		sources[0] = new StreamSource(new CSFileInputStream(
				page.getFolders().dstDir + "fhir-all.xsd"));
		sources[1] = new StreamSource(new CSFileInputStream(
				page.getFolders().dstDir + "fhir-atom.xsd"));
		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		schemaFactory.setErrorHandler(new MyErrorHandler(false));
		schemaFactory.setResourceResolver(new MyResourceResolver(page
				.getFolders().dstDir));
		Schema schema = schemaFactory.newSchema(sources);
		log(".... done");

		for (String rname : page.getDefinitions().sortedResourceNames()) {
		  ResourceDefn r = page.getDefinitions().getResources().get(rname); 
		  if (wantBuild(rname)) {
		    for (Example e : r.getExamples()) {
		      String n = e.getFileTitle();
		      log(" ...validate " + n);
		      validateXmlFile(schema, n);
		    }
		  }
		}
		if (buildFlags.get("all")) {
		  log(" ...validate " + "profiles-resources");
		  validateXmlFile(schema, "profiles-resources");
		}
		log("Reference Platform Validation.");

		for (String rname : page.getDefinitions().sortedResourceNames()) {
		  ResourceDefn r = page.getDefinitions().getResources().get(rname); 
		  if (wantBuild(rname)) {
		    for (Example e : r.getExamples()) {
		      String n = e.getFileTitle();
		      log(" ...test " + n);
		      validateRoundTrip(schema, n);
		    }
		  }
    }
    if (buildFlags.get("all")) {
      log(" ...test " + "profiles-resources");
      validateRoundTrip(schema, "profiles-resources");
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
      log("The path "+path+e.getName()+" is not covered by any example");
      page.getQa().notCovered(path+e.getName());
    }
    for (ElementDefn c : e.getElements()) {
      produceCoverageWarning(path+e.getName()+"/", c);
    }    
  }

  private void validateXmlFile(Schema schema, String n) throws Exception {
		char sc = File.separatorChar;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(false);
		factory.setSchema(schema);
		DocumentBuilder builder = factory.newDocumentBuilder();
		MyErrorHandler err = new MyErrorHandler(true);
		builder.setErrorHandler(err);
		Document doc = builder.parse(new CSFileInputStream(new CSFile(page.getFolders().dstDir + n + ".xml")));
		if (err.getErrors().size() > 0)
			throw new Exception("Resource Example " + n	+ " failed schema validation");
		File tmpTransform = File.createTempFile("tmp", ".xslt");
		tmpTransform.deleteOnExit();
		File tmpOutput = File.createTempFile("tmp", ".xml");
		tmpOutput.deleteOnExit();
		String sch = doc.getDocumentElement().getNodeName().toLowerCase();
		if (sch.equals("feed"))
			sch = "fhir-atom";

		try {
		  Utilities.transform(page.getFolders().rootDir + "tools"+sc+"schematron"+sc,	page.getFolders().dstDir + sch + ".sch", page.getFolders().rootDir + "tools"+sc+"schematron"+sc+"iso_svrl_for_xslt2.xsl",	tmpTransform.getAbsolutePath());
		  Utilities.transform(page.getFolders().rootDir + "tools"+sc+"schematron"+sc,	page.getFolders().dstDir + n + ".xml", tmpTransform.getAbsolutePath(), tmpOutput.getAbsolutePath());
		} catch (Throwable t) {
		  throw new Exception("Error validating "+page.getFolders().dstDir + n + ".xml with schematrons", t);
		}

		factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		builder = factory.newDocumentBuilder();
		doc = builder.parse(new CSFileInputStream(tmpOutput.getAbsolutePath()));
		NodeList nl = doc.getDocumentElement().getElementsByTagNameNS("http://purl.oclc.org/dsdl/svrl", "failed-assert");
		if (nl.getLength() > 0) {
			page.log("Schematron Validation Failed for " + n + ".xml:");
			for (int i = 0; i < nl.getLength(); i++) {
				Element e = (Element) nl.item(i);
				page.log("  @" + e.getAttribute("location") + ": "+ e.getTextContent());
			}
		}
	}

	private void validateRoundTrip(Schema schema, String n) throws Exception {
		for (PlatformGenerator gen : page.getReferenceImplementations()) {
			if (gen.doesTest()) {
				gen.loadAndSave(page.getFolders().dstDir, page.getFolders().dstDir + n + ".xml", page.getFolders().tmpResDir + "tmp.xml");
				compareXml(n, gen.getName(), page.getFolders().dstDir + n	+ ".xml", page.getFolders().tmpResDir + "tmp.xml");
			}
		}
	}

	private void compareXml(String t, String n, String fn1, String fn2)
			throws Exception {
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
		File tmp1 = File.createTempFile("xml", ".xml");
		tmp1.deleteOnExit();
		xmlgen.generate(doc1.getDocumentElement(), tmp1, doc1
				.getDocumentElement().getNamespaceURI(), doc1
				.getDocumentElement().getLocalName());
		File tmp2 = File.createTempFile("xml", ".xml");
		tmp2.deleteOnExit();
		xmlgen.generate(doc2.getDocumentElement(), tmp2, doc2
				.getDocumentElement().getNamespaceURI(), doc2
				.getDocumentElement().getLocalName());

		if (!TextFile.fileToString(tmp1.getAbsolutePath()).equals(
				TextFile.fileToString(tmp2.getAbsolutePath()))) {
			page.log("file " + t+ " did not round trip perfectly in XML in platform " + n);
			if (new CSFile(System.getenv("ProgramFiles(X86)")+sc+"WinMerge"+sc+"WinMergeU.exe")
					.exists()) {

				List<String> command = new ArrayList<String>();
				command.add("\""+System.getenv("ProgramFiles(X86)")+sc+"WinMerge"+sc+"WinMergeU.exe"+"\" \""
						+ tmp1.getAbsolutePath()
						+ "\" \""
						+ tmp2.getAbsolutePath() + "\"");

				ProcessBuilder builder = new ProcessBuilder(command);
				builder.directory(new CSFile(page.getFolders().rootDir));
				final Process process = builder.start();
				process.waitFor();
			}

		}
	}

	private void stripWhitespaceAndComments(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) node;
			Map<String, String> attrs = new HashMap<String, String>();
			for (int i = e.getAttributes().getLength() - 1; i >= 0; i--) {
				attrs.put(e.getAttributes().item(i).getNodeName(), e
						.getAttributes().item(i).getNodeValue());
				e.removeAttribute(e.getAttributes().item(i).getNodeName());
			}
			for (String n : attrs.keySet()) {
				e.setAttribute(n, attrs.get(n));
			}
		}
		for (int i = node.getChildNodes().getLength() - 1; i >= 0; i--) {
			Node c = node.getChildNodes().item(i);
			if (c.getNodeType() == Node.TEXT_NODE
					&& c.getTextContent().trim().length() == 0)
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

  public void log(String content) {
    page.log(content);
  }

//  public void logNoEoln(String content) {
//    page.logNoEoln(content);
//  }

  private void generateValueSets() throws Exception {
    log(" ...value sets");
    for (BindingSpecification bs : page.getDefinitions().getBindings().values())
      if (bs.getBinding() == Binding.ValueSet && bs.getReferredValueSet() != null)
        generateValueSet(bs.getReference(), bs);
  }
  
  private void generateValueSet(String name, BindingSpecification cd) throws Exception {
    String n;
    if (name.startsWith("valueset-"))
      n = name.substring(9);
    else
      n = name;
    cd.getReferredValueSet().setIdentifierSimple("http://hl7.org/fhir/valuesets/"+n);
    addToResourceFeed(cd.getReferredValueSet(), n);
    
    // todo - create the redirect
    TextFile.stringToFile(page.processPageIncludes(cd.getName()+".htm", TextFile.fileToString(page.getFolders().srcDir+"template-vs.htm")), page.getFolders().dstDir+name+".htm");
    String src = page.processPageIncludesForBook(cd.getName()+".htm", TextFile.fileToString(page.getFolders().srcDir+"template-vs-book.htm"));
    cachePage(name+".htm", src);
    
    JsonComposer json = new JsonComposer();
    json.compose(new FileOutputStream(page.getFolders().dstDir+name+".json"), cd.getReferredValueSet());
    XmlComposer xml = new XmlComposer();
    xml.compose(new FileOutputStream(page.getFolders().dstDir+name+".xml"), cd.getReferredValueSet(), true);
    cloneToXhtml(name, "Definition for Value Set"+cd.getReferredValueSet().getNameSimple());
    
      
  }
  private void generateCodeSystems() throws Exception {
    log(" ...code lists");
    for (BindingSpecification bs : page.getDefinitions().getBindings().values())
      if (bs.getBinding() == Binding.CodeList || bs.getBinding() == Binding.Special)
        generateCodeSystem(bs.getReference().substring(1)+".htm", bs);
  }
  
  private void generateCodeSystem(String filename, BindingSpecification cd) throws Exception {
    TextFile.stringToFile(page.processPageIncludes(filename, TextFile.fileToString(page.getFolders().srcDir+"template-tx.htm")), page.getFolders().dstDir+filename);
    String src = page.processPageIncludesForBook(filename, TextFile.fileToString(page.getFolders().srcDir+"template-tx-book.htm"));
    cachePage(filename, src);

    ValueSet vs = new ValueSet();
    vs.setIdentifierSimple("http://hl7.org/fhir/"+Utilities.fileTitle(filename));
    // no version?? vs.setVersion(...
    vs.setNameSimple(cd.getName());
    vs.setPublisherSimple("HL7 (FHIR Project)");
    vs.getTelecom().add(org.hl7.fhir.instance.model.Factory.newContact(ContactSystem.url, "http://hl7.org/fhir"));
    vs.getTelecom().add(org.hl7.fhir.instance.model.Factory.newContact(ContactSystem.email, "fhir@lists.hl7.org"));
    vs.setDescriptionSimple(Utilities.noString(cd.getDescription()) ? cd.getDefinition() : cd.getDefinition()+"\r\n\r\n"+cd.getDescription());
    vs.setStatusSimple(ValuesetStatus.draft); // until we publish DSTU, then .review
    vs.setDate(org.hl7.fhir.instance.model.Factory.nowDateTime());
    vs.setText(Factory.newNarrative(NarrativeStatus.generated, cd.getDescription()));
    if (cd.isValueSet()) {
      vs.setKindSimple(ValuesetKind.composition);
      vs.setCompose(vs.new ValueSetComposeComponent());
      for (String n : cd.getVSSources()) {
        ConceptSetComponent cc = vs.new ConceptSetComponent();
        vs.getCompose().getInclude().add(cc);
        cc.setSystemSimple(new URI(n));
        cc.setModeSimple(CodeSelectionMode.code);
        for (DefinedCode c : cd.getCodes()) {
          if (n.equals(c.getSystem()))
            cc.getCode().add(org.hl7.fhir.instance.model.Factory.newCode(c.getCode()));
        }
      }
    }
    else {
      vs.setKindSimple(ValuesetKind.codeMinussystem);
      vs.setDefine(vs.new ValueSetDefineComponent());
      vs.getDefine().setSystemSimple(new URI("http://hl7.org/fhir/"+Utilities.fileTitle(filename)));
      for (DefinedCode c : cd.getCodes()) {
        ValueSetDefineConceptComponent d = vs.new ValueSetDefineConceptComponent();
        vs.getDefine().getConcept().add(d);
        d.setCodeSimple(c.getCode());
        if (!Utilities.noString(c.getDisplay()))
          d.setDisplaySimple(c.getDisplay());
        if (!Utilities.noString(c.getDefinition()))
          d.setDefinitionSimple(c.getDefinition());       
      }
    }
    addToResourceFeed(vs, Utilities.fileTitle(filename));

    JsonComposer json = new JsonComposer();
    json.compose(new FileOutputStream(page.getFolders().dstDir+Utilities.changeFileExt(filename, ".json")), vs);
    XmlComposer xml = new XmlComposer();
    xml.compose(new FileOutputStream(page.getFolders().dstDir+Utilities.changeFileExt(filename, ".xml")), vs, true);
    cloneToXhtml(Utilities.fileTitle(filename), "Definition for Value Set"+vs.getNameSimple());

  }

}
