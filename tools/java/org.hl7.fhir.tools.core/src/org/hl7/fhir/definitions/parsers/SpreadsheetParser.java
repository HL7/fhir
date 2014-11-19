package org.hl7.fhir.definitions.parsers;

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
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.generators.specification.ProfileGenerator;
import org.hl7.fhir.definitions.model.ConformancePackage.ConformancePackageSourceType;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.EventDefn;
import org.hl7.fhir.definitions.model.EventDefn.Category;
import org.hl7.fhir.definitions.model.EventUsage;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.Example.ExampleType;
import org.hl7.fhir.definitions.model.ConformancePackage;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.OperationParameter;
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.definitions.model.RegisteredProfile;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameter;
import org.hl7.fhir.definitions.model.SearchParameter.SearchType;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.formats.FormatUtilities;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Base64BinaryType;
import org.hl7.fhir.instance.model.CodeType;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.ExtensionDefinition;
import org.hl7.fhir.instance.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.instance.model.ElementDefinition.BindingConformance;
import org.hl7.fhir.instance.model.ExtensionDefinition.ExtensionContext;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.IdType;
import org.hl7.fhir.instance.model.InstantType;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.OidType;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.TimeType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.UuidType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.tools.publisher.BreadCrumbManager.Page;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.XLSXmlParser;
import org.hl7.fhir.utilities.XLSXmlParser.Sheet;

import com.trilead.ssh2.crypto.Base64;

public class SpreadsheetParser {

	private String name;
	private XLSXmlParser xls;
	private List<EventDefn> events = new ArrayList<EventDefn>();
	private boolean isProfile;
	private String profileExtensionBase;
	private Definitions definitions;
	private String title;
	private String folder;
	private Logger log;
	private String sheetname;
	private BindingNameRegistry registry;
  private String dataTypesFolder;
  private String txFolder;
  private String version; 
  private WorkerContext context;
  private Calendar genDate;
  private boolean isAbstract;
  private Map<String, BindingSpecification> bindings; // when parsing profiles
  
	public SpreadsheetParser(InputStream in, String name,	Definitions definitions, String root, Logger log, BindingNameRegistry registry, String version, WorkerContext context, Calendar genDate, boolean isAbstract) throws Exception {
		this.name = name;
		xls = new XLSXmlParser(in, name);
		this.definitions = definitions;
		if (name.indexOf('-') > 0)
			title = name.substring(0, name.indexOf('-'));
		else if (name.indexOf('.') > 0)
			title = name.substring(0, name.indexOf('.'));
		else
		  title = name;
		this.folder = root + title + File.separator;
    this.dataTypesFolder =  root + "datatypes" + File.separator;
    this.txFolder =  root + "terminologies" + File.separator;
		this.log = log;
		this.registry = registry;
		this.version = version;
		this.context = context;
		this.genDate = genDate;
		this.isAbstract = isAbstract;
	}


	public TypeDefn parseCompositeType() throws Exception {
		isProfile = false;
		return parseCommonTypeColumns().getRoot();
	}

	private Sheet loadSheet(String name) {
	  sheetname = name;
	  return xls.getSheets().get(name);
	}

	private ResourceDefn parseCommonTypeColumns() throws Exception {
		ResourceDefn resource = new ResourceDefn();
		
		Sheet sheet = loadSheet("Bindings");
		Map<String, BindingSpecification> typeLocalBindings = null;
		if (sheet != null)
			typeLocalBindings = readBindings(sheet);
			
		sheet = loadSheet("Invariants");
		Map<String,Invariant> invariants = null;
		if (sheet != null)
			invariants = readInvariants(sheet);
		
		sheet = loadSheet("Data Elements");
		if (sheet == null)
		  throw new Exception("No Sheet found for Data Elements");
		for (int row = 0; row < sheet.rows.size(); row++) {
			processLine(resource, sheet, row, invariants, false);
		}

		if (invariants != null) {
		  for (Invariant inv : invariants.values()) {
		    if (Utilities.noString(inv.getContext())) 
		      log.log("Type "+resource.getRoot().getName()+" Invariant "+inv.getId()+" has no context", LogMessageType.Warning);
		    else {
		      ElementDefn ed = findContext(resource.getRoot(), inv.getContext(), "Type "+resource.getRoot().getName()+" Invariant "+inv.getId()+" Context");
		      if (ed.getName().endsWith("[x]") && !inv.getContext().endsWith("[x]"))
		        inv.setFixedName(inv.getContext().substring(inv.getContext().lastIndexOf(".")+1));
		      ed.getInvariants().put(inv.getId(), inv);
		      if (Utilities.noString(inv.getXpath()))
		        log.log("Type "+resource.getRoot().getName()+" Invariant "+inv.getId()+" ("+inv.getEnglish()+") has no XPath statement", LogMessageType.Warning);
		      else if (inv.getXpath().contains("\""))
		        log.log("Type "+resource.getRoot().getName()+" Invariant "+inv.getId()+" ("+inv.getEnglish()+") contains a \" character", LogMessageType.Warning);
		    }
		  }
		}
		
		//TODO: Will fail if type has no root. - GG: so? when could that be
		// EK: Future types. But those won't get there.
		if( typeLocalBindings != null)
			resource.getRoot().getNestedBindings().putAll(typeLocalBindings);
		
		scanNestedTypes(resource, resource.getRoot(), resource.getName());
		
		return resource;
	}
	
	
	private void scanNestedTypes(ResourceDefn parent, ElementDefn root, String parentName) throws Exception
	{
		for( ElementDefn element : root.getElements() )
		{
			if( element.hasNestedElements() )
			{	
				String nestedTypeName;
				
				ElementDefn newCompositeType = new ElementDefn();
		
				// If user has given an explicit name, use it, otherwise  automatically
				// generated name for this nested type
				if( element.typeCode().startsWith("=") ) {
				  if (isProfile)
				    throw new Exception("Cannot use '=' types in profiles on "+parentName);
				  element.setStatedType(element.typeCode().substring(1));
					nestedTypeName = element.typeCode().substring(1);
				} else {
					nestedTypeName = parentName + Utilities.capitalize(element.getName());
					newCompositeType.setAnonymousTypedGroup(true);
				}
				
				// Add Component to the actually generated name to avoid
				// confusing between the element name and the element's type
				newCompositeType.setName(nestedTypeName+"Component");
				newCompositeType.setDefinition("A nested type in " + parent.getName() + ": "
								+ element.getDefinition() );
				newCompositeType.getElements().addAll(element.getElements());
			
				if( parent.getRoot().getNestedTypes().containsKey(nestedTypeName) )
					throw new Exception("Nested type " + nestedTypeName + 
							" already exist in resource " + parent.getName());
				
				parent.getRoot().getNestedTypes().put(nestedTypeName, newCompositeType );

				// Clear out the name of the local type, so old code
				// will not see a change.
				element.getTypes().clear();
				element.setDeclaredTypeName(newCompositeType.getName());
				
				scanNestedTypes( parent, element, nestedTypeName);
			}
			
			resolveElementReferences(parent, element);
		}
	}
	
	private void resolveElementReferences(ResourceDefn parent, ElementDefn root)
			throws Exception 
	{
		for (TypeRef ref : root.getTypes()) {
			if (ref.isElementReference()) {
				ElementDefn referredElement = parent.getRoot().getElementByName(ref.getName().substring(1));

				if (referredElement == null)
					throw new Exception("Element reference " + ref.getName()+ " cannot be found in type " + parent.getName());

				if (referredElement.getDeclaredTypeName() == null)
					throw new Exception("Element reference "+ ref.getName()+ " in "+ parent.getName()
					   + " refers to an anonymous group of elements. Please specify names with the '=<name>' construct in the typename column.");

				ref.setResolvedTypeName(referredElement.getDeclaredTypeName());
			}
		}
	}
	
	
	public ResourceDefn parseResource() throws Exception {
	  isProfile = false;
	  ResourceDefn root = parseCommonTypeColumns();

	  readEvents(loadSheet("Events"));
	  readSearchParams(root, loadSheet("Search"), false);
	  readPackages(root, loadSheet("Packages")); 
    readExamples(root, loadSheet("Examples"));
	  readOperations(root, loadSheet("Operations"));

	  return root;
	}

	private void readOperations(ResourceDefn root, Sheet sheet) throws Exception {
	  if (sheet != null) {
      for (int row = 0; row < sheet.rows.size(); row++) {
        String name = sheet.getColumn(row, "Name");
        String use = sheet.getColumn(row, "Use"); 
        String doco = sheet.getColumn(row, "Documentation");
				
				if (name != null && !name.equals("") && !name.startsWith("!")) {
	        if (!name.contains(".")) {
	          boolean system = false;
	          boolean type = false;
	          boolean instance = false;
	          for (String c : use.split("\\|")) {
	            c = c.trim();
	            if ("system".equalsIgnoreCase(c))
	              system = true;
	            else if ("resource".equalsIgnoreCase(c))
	              type = true;
	            else if ("instance".equalsIgnoreCase(c))
	              instance = true;
	            else 
	              throw new Exception("unknown operation use code "+c);
	          }
	          root.getOperations().put(name, new Operation(name, system, type, instance, sheet.getColumn(row, "Type"), sheet.getColumn(row, "Title"), doco, sheet.getColumn(row, "Footer")));
	        } else {
	          String[] parts = name.split("\\.");
	          if (!use.equals("in") && !use.equals("out"))
	            throw new Exception("Only allowed types are 'in' or 'out' at "+getLocation(row));
	          Operation operation = root.getOperations().get(parts[0]);
	          if (operation == null)
	            throw new Exception("Unknown Operation '"+parts[0]+"' at "+getLocation(row));
	          String type = sheet.getColumn(row, "Type");
	          String profile = sheet.getColumn(row, "Profile");
	          String min = sheet.getColumn(row, "Min");
	          String max = sheet.getColumn(row, "Max");
	          operation.getParameters().add(new OperationParameter(parts[1], use, doco, Integer.parseInt(min), max, type, profile));
	        }
	      }
      }
	  }
	}


	private ExampleType parseExampleType(String s, int row) throws Exception {
	  if (s==null || "".equals(s))
	    return ExampleType.XmlFile;
	  if ("tool".equals(s))
	    return ExampleType.Tool;
	  if ("xml".equals(s))
	    return ExampleType.XmlFile;
	  if ("csv".equals(s))
	    return ExampleType.CsvFile;
	  throw new Exception("Unknown Example Type '" + s + "': " + getLocation(row));
	}

	  
	private void readPackages(ResourceDefn defn, Sheet sheet) throws Exception {
    if (sheet != null) {
      for (int row = 0; row < sheet.rows.size(); row++) {
        String name = sheet.getColumn(row, "Name");
        if (name != null && !name.equals("") && !name.startsWith("!")) {
          ConformancePackage pack = new ConformancePackage();
          pack.setTitle(name);
          pack.setDescription(sheet.getColumn(row, "Description"));
          pack.setName(sheet.getColumn(row, "Filename"));
          pack.setSource(checkFile(sheet, row, "Source", false, pack.getName())); // todo-profile
          String type = sheet.getColumn(row, "Type");
          if ("bundle".equalsIgnoreCase(type))
            pack.setSourceType(ConformancePackageSourceType.Bundle);
          else if ("spreadsheet".equalsIgnoreCase(type))
            pack.setSourceType(ConformancePackageSourceType.Spreadsheet);
          else
            throw new Exception("Unknown source type: "+type+" at "+getLocation(row));
          String example = checkFile(sheet, row, "Example", true, null); // todo-profile
          if (example != null)
            pack.getExamples().add(new Example(example, Utilities.fileTitle(example), "General Example for "+pack.getName(), new File(example), ExampleType.XmlFile, false));
          defn.getConformancePackages().add(pack);
        }
      }
    }
  }


  private String checkFile(Sheet sheet, int row, String column, boolean canBeNull, String defaultValue) throws Exception {
    String name = sheet.getColumn(row, column);
    if (Utilities.noString(name))
      name = defaultValue;
    
    if (Utilities.noString(name)) {
      if (!canBeNull)
        throw new Exception("Missing filename for '"+column+"' at "+getLocation(row));
      return null;
    }
    String filename = Utilities.path(folder, name);
    if (!(new File(filename).exists()))
      throw new Exception("Unable to find source file "+name);
    return filename;
  }


  private Map<String,Invariant> readInvariants(Sheet sheet)
			throws Exception {
		Map<String,Invariant> result = new HashMap<String,Invariant>();
		
		for (int row = 0; row < sheet.rows.size(); row++) {
			Invariant inv = new Invariant();

			String s = sheet.getColumn(row, "Id");
			if (!s.startsWith("!")) {
			  inv.setId(s);
			  inv.setName(sheet.getColumn(row, "Name"));
			  inv.setContext(sheet.getColumn(row, "Context"));
			  inv.setEnglish(sheet.getColumn(row, "English"));
			  inv.setXpath(sheet.getColumn(row, "XPath"));
			  inv.setSeverity(sheet.getColumn(row, "Severity"));
			  if (!Utilities.noString(sheet.getColumn(row,  "Schematron")))
			    log.log("Value found for schematron "+getLocation(row), LogMessageType.Hint);  
			  inv.setOcl(sheet.getColumn(row, "OCL"));
			  if (s == null || s.equals("")
			      || result.containsKey(s))
			    throw new Exception("duplicate or missing invariant id "
			        + getLocation(row));
			  result.put(s, inv);
			}
		}
		
		return result;
	}

  private void readSearchParams(ResourceDefn root2, Sheet sheet, boolean forProfile) throws Exception {
    
    if (sheet != null)
      for (int row = 0; row < sheet.rows.size(); row++) {

        if (!sheet.hasColumn(row, "Name"))
          throw new Exception("Search Param has no name "+ getLocation(row));
        String n = sheet.getColumn(row, "Name");
        if (!n.startsWith("!")) {

          if (!sheet.hasColumn(row, "Type"))
            throw new Exception("Search Param "+root2.getName()+"/"+n+" has no type "+ getLocation(row));
          if (n.endsWith("-before") || n.endsWith("-after"))
            throw new Exception("Search Param "+root2.getName()+"/"+n+" includes relative time "+ getLocation(row));
          if (root2.getSearchParams().containsKey(n))
            throw new Exception("Search Param "+root2.getName()+"/"+n+": duplicate name "+ getLocation(row));
          String d = sheet.getColumn(row, "Description");
          SearchType t = readSearchType(sheet.getColumn(row, "Type"), row);
          List<String> pn = new ArrayList<String>(); 
          SearchParameter sp = null;
          if (t == SearchType.composite) {
            String[] pl = sheet.getColumn(row, "Path").split("\\&");
            if (Utilities.noString(d)) 
              throw new Exception("Search Param "+root2.getName()+"/"+n+" has no description "+ getLocation(row));
            for (String pi : pl) {
              String p = pi.trim();
              if (!root2.getSearchParams().containsKey(p)) {
                boolean found = false;
                if (p.endsWith("[x]"))
                  for (String pan : root2.getSearchParams().keySet()) {
                    if (pan.startsWith(p.substring(0,  p.length()-3)))
                      found = true;
                  }
                if (!found)                
                  throw new Exception("Composite Search Param "+root2.getName()+"/"+n+"  refers to an unknown component "+p+" at "+ getLocation(row));
              }
              pn.add(p);
              sp = new SearchParameter(n, d, t);
              sp.getComposites().addAll(pn);
            }
          } else {
            String[] pl = sheet.getColumn(row, "Path").split("\\|");
            for (String pi : pl) {
              String p = pi.trim();
              ElementDefn e = null;
              if (!Utilities.noString(p) && !p.startsWith("!")) {
                e = root2.getRoot().getElementForPath(p, definitions, "search param", true); 
              }
              if (Utilities.noString(d) && e != null)
                d = e.getShortDefn();
              if (d == null)
                throw new Exception("Search Param "+root2.getName()+"/"+n+" has no description "+ getLocation(row));
              if (e != null)
                pn.add(p);
              if (t == SearchType.reference) {
                if (e == null && !forProfile && !sheet.hasColumn(row, "Target Types"))
                  throw new Exception("Search Param "+root2.getName()+"/"+n+" of type reference has wrong path "+ getLocation(row));
                if (!forProfile && e != null && (!e.typeCode().startsWith("Reference(") && !e.typeCode().startsWith("uri|Reference(")))
                  throw new Exception("Search Param "+root2.getName()+"/"+n+" wrong type. The search type is reference, but the element type is "+e.typeCode());
              } else if (e != null && e.typeCode().startsWith("Reference("))
                throw new Exception("Search Param "+root2.getName()+"/"+n+" wrong type. The search type is "+t.toString()+", but the element type is "+e.typeCode());
            }
            if (!forProfile && t == SearchType.reference && pn.size() == 0 && !sheet.hasColumn(row, "Target Types"))
              throw new Exception("Search Param "+root2.getName()+"/"+n+" of type reference has no path(s) "+ getLocation(row));

            sp = new SearchParameter(n, d, t);
            sp.getPaths().addAll(pn);
            if (!Utilities.noString(sheet.getColumn(row, "Target Types"))) {
              sp.setManualTypes(sheet.getColumn(row, "Target Types").split("\\,"));
            }
          }


          root2.getSearchParams().put(n, sp);
        }
      }
	}

	private SearchType readSearchType(String s, int row) throws Exception {
		if ("number".equals(s))
			return SearchType.number;
		if ("string".equals(s))
			return SearchType.string;
		if ("date".equals(s))
			return SearchType.date;
		if ("reference".equals(s))
			return SearchType.reference;
    if ("token".equals(s))
      return SearchType.token;
    if ("composite".equals(s))
      return SearchType.composite;
    if ("quantity".equals(s))
      return SearchType.quantity;
		throw new Exception("Unknown Search Type '" + s + "': " + getLocation(row));
	}

 
	// Adds bindings to global definition.bindings. Returns list of
	// newly found bindings in the sheet.
	private Map<String, BindingSpecification> readBindings(Sheet sheet) throws Exception {
		Map<String, BindingSpecification> result = new HashMap<String,BindingSpecification>();
	
		for (int row = 0; row < sheet.rows.size(); row++) {
		  String bindingName = sheet.getColumn(row, "Binding Name"); 
		  
		  // Ignore bindings whose name start with "!"
		  if (bindingName.startsWith("!")) continue;
	      
			BindingSpecification cd = new BindingSpecification();

			cd.setName(bindingName);
			cd.setDefinition(sheet.getColumn(row, "Definition"));
			cd.setBinding(BindingsParser.readBinding(sheet.getColumn(row, "Binding")));
			cd.setReference(sheet.getColumn(row, "Reference"));
	     if (!cd.getBinding().equals(Binding.Unbound) && Utilities.noString(cd.getReference())) 
         throw new Exception("binding "+cd.getName()+" is missing a reference");

      cd.setDescription(sheet.getColumn(row, "Description"));
      cd.setExample(parseBoolean(sheet.getColumn(row, "Example"), row, false));
      if (isProfile || cd.getBinding() == Binding.Reference) {
        cd.setExtensible(parseFullBoolean(sheet.getColumn(row, "Extensible"), row, false));
        cd.setConformance(BindingConformance.fromCode(sheet.getColumn(row, "Conformance")));
      }

			cd.setId(registry.idForName(cd.getName()));
			cd.setSource(name);
      cd.setUri(sheet.getColumn(row, "Uri"));
      String oid = sheet.getColumn(row, "Oid");
      if (!Utilities.noString(oid))
        cd.setVsOid(oid); // no cs oid in this case
      cd.setStatus(ValueSet.ValuesetStatus.fromCode(sheet.getColumn(row, "Status")));
      cd.setWebSite(sheet.getColumn(row, "Website"));
      cd.setEmail(sheet.getColumn(row, "Email"));
      cd.setCopyright(sheet.getColumn(row, "Copyright"));
      cd.setV2Map(sheet.getColumn(row, "v2"));
      cd.setV3Map(sheet.getColumn(row, "v3"));

			if (cd.getBinding() == BindingSpecification.Binding.CodeList) {
				Sheet codes = xls.getSheets().get(
						cd.getReference().substring(1));
				if (codes == null)
					throw new Exception("code source sheet not found for "+ cd.getName() + ": " + cd.getReference());
				parseCodes(cd.getCodes(), codes);
			}
			
			if (cd.getBinding() == Binding.ValueSet && !Utilities.noString(cd.getReference())) {
			  if (cd.getReference().startsWith("http://hl7.org/fhir")) {
			    // ok, it's a reference to a value set defined within this build. Since it's an absolute 
			    // reference, it's into the base infrastructure. That's not loaded yet, so we will try
			    // to resolve it later
			  } else if (new File(Utilities.appendSlash(folder)+cd.getReference()+".xml").exists()) {
			    XmlParser p = new XmlParser();
			    FileInputStream input = new FileInputStream(Utilities.appendSlash(folder)+cd.getReference()+".xml");
	        cd.setReferredValueSet((ValueSet) p.parse(input));
			  } else if (new File(Utilities.appendSlash(folder)+cd.getReference()+".json").exists()) {
			    JsonParser p = new JsonParser();
			    FileInputStream input = new FileInputStream(Utilities.appendSlash(folder)+cd.getReference()+".json");
			    cd.setReferredValueSet((ValueSet) p.parse(input));
			  } else if (new File(Utilities.appendSlash(dataTypesFolder)+cd.getReference()+".xml").exists()) {
			    XmlParser p = new XmlParser();
			    FileInputStream input = new FileInputStream(Utilities.appendSlash(dataTypesFolder)+cd.getReference()+".xml");
			    cd.setReferredValueSet((ValueSet) p.parse(input));
			  } else if (new File(Utilities.appendSlash(dataTypesFolder)+cd.getReference()+".json").exists()) {
			    JsonParser p = new JsonParser();
			    FileInputStream input = new FileInputStream(Utilities.appendSlash(dataTypesFolder)+cd.getReference()+".json");
			    cd.setReferredValueSet((ValueSet) p.parse(input));
        } else if (new File(Utilities.appendSlash(txFolder)+cd.getReference()+".xml").exists()) {
          XmlParser p = new XmlParser();
          FileInputStream input = new FileInputStream(Utilities.appendSlash(txFolder)+cd.getReference()+".xml");
          cd.setReferredValueSet((ValueSet) p.parse(input));
        } else if (new File(Utilities.appendSlash(txFolder)+cd.getReference()+".json").exists()) {
          JsonParser p = new JsonParser();
          FileInputStream input = new FileInputStream(Utilities.appendSlash(txFolder)+cd.getReference()+".json");
          cd.setReferredValueSet((ValueSet) p.parse(input));
			  } else
			    throw new Exception("Unable to find source for "+cd.getReference()+" ("+Utilities.appendSlash(folder)+cd.getReference()+".xml/json)");
			  if (cd.getReferredValueSet() != null)
			    cd.getReferredValueSet().setId(FormatUtilities.makeId(cd.getReference()));
			}
			if (definitions.getBindingByName(cd.getName()) != null) {
				throw new Exception("Definition of binding '"
						+ cd.getName()
						+ "' in "
						+ name
						+ " clashes with previous definition in "
						+ definitions.getBindingByName(cd.getName())
								.getSource());
			}
			definitions.getBindings().put(cd.getName(), cd);
			result.put(cd.getName(), cd);
		}
		
		return result;
	}

	private Boolean parseFullBoolean(String s, int row, boolean b) throws Exception {
    s = s.toLowerCase();
    if (s == null || s.equals(""))
      return null;
    else if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes")
        || s.equalsIgnoreCase("true") || s.equalsIgnoreCase("1"))
      return true;
    else if (s.equals("false") || s.equals("0") || s.equals("f")
        || s.equals("n") || s.equals("no"))
      return false;
    else
      throw new Exception("unable to process boolean value: " + s + " in " + getLocation(row));
  }


  private void parseCodes(List<DefinedCode> codes, Sheet sheet)
			throws Exception {
		for (int row = 0; row < sheet.rows.size(); row++) {
			DefinedCode c = new DefinedCode();
			c.setId(sheet.getColumn(row, "Id"));
			c.setCode(sheet.getColumn(row, "Code"));
      c.setDisplay(sheet.getColumn(row, "Display"));
      c.setSystem(sheet.getColumn(row, "System"));
			c.setDefinition(Utilities.appendPeriod(processDefinition(sheet.getColumn(row, "Definition"))));
      c.setComment(sheet.getColumn(row, "Comment"));
      c.setParent(sheet.getColumn(row, "Parent"));
      c.setV2Map(sheet.getColumn(row, "v2"));
      c.setV3Map(sheet.getColumn(row, "v3"));
      for (String ct : sheet.columns) 
        if (ct.startsWith("Display:") && !Utilities.noString(sheet.getColumn(row, ct)))
          c.getLangs().put(ct.substring(8), sheet.getColumn(row, ct));
      if (Utilities.noString(c.getId()) && Utilities.noString(c.getSystem()))
        throw new Exception("code has no id or system ("+sheet.title+") "+getLocation(row));
			codes.add(c);
		}
	}

	private String processDefinition(String definition) {
    
    return definition.replace("$version$", version);
  }


	public void parseConformancePackage(ConformancePackage ap, Definitions definitions) throws Exception {
	  try {
	    isProfile = true;
	    Sheet sheet = loadSheet("Bindings");
	    if (sheet != null)
	      bindings = readBindings(sheet);

	    sheet = loadSheet("Metadata");
	    for (int row = 0; row < sheet.rows.size(); row++) {
	      String n = sheet.getColumn(row, "Name");
	      String v = sheet.getColumn(row, "Value");
	      if (n != null && v != null) {
	        if (ap.getMetadata().containsKey(n))
	          ap.getMetadata().get(n).add(v);
	        else {
	          ArrayList<String> vl = new ArrayList<String>();
	          vl.add(v);
	          ap.getMetadata().put(n, vl);
	        }
	      }
	    }
      if (ap.hasMetadata("name"))
        ap.setTitle(ap.metadata("name"));
      if (!ap.hasMetadata("id"))
        throw new Exception("Error parsing "+ap.getId()+"/"+ap.getTitle()+" no 'id' found in metadata");
      
	    this.profileExtensionBase = ap.metadata("extension.uri");

	    List<String> namedSheets = new ArrayList<String>();

	    if (ap.getMetadata().containsKey("published.structure")) {
	      for (String n : ap.getMetadata().get("published.structure")) {
	        if (!Utilities.noString(n))
	          ap.getProfiles().add(parseProfileSheet(definitions, ap, n, namedSheets, true));
	      }
	    }

	    int i = 0;
	    while (i < namedSheets.size()) {
	      ap.getProfiles().add(parseProfileSheet(definitions, ap, namedSheets.get(i), namedSheets, false));
	      i++;
	    }

	    sheet = loadSheet("Extensions");
	    if (sheet != null) {
	      int row = 0;
	      while (row < sheet.rows.size()) {
	        if (sheet.getColumn(row, "Code").startsWith("!"))
	          row++;
	        else 
	          row = processExtension(null, sheet, row, definitions, ap.metadata("extension.uri"), ap);
	      }
	    }

	  } catch (Exception e) {
	    throw new Exception("exception parsing pack "+ap.getName()+": "+e.getMessage(), e);
	  }
	}


  private ProfileDefn parseProfileSheet(Definitions definitions, ConformancePackage ap, String n, List<String> namedSheets, boolean published) throws Exception {
    Sheet sheet;
    ResourceDefn resource = new ResourceDefn();
    resource.setPublishedInProfile(published);

		sheet = loadSheet(n+"-Inv");
	  Map<String,Invariant> invariants = null;
    if (sheet != null) {
	    invariants = readInvariants(sheet);
	  } else {
	  	invariants = new HashMap<String,Invariant>();
		}
		
    sheet = loadSheet(n);
    if (sheet == null)
      throw new Exception("The Profile referred to a tab by the name of '"+n+"', but no tab by the name could be found");
    for (int row = 0; row < sheet.rows.size(); row++) {
      ElementDefn e = processLine(resource, sheet, row, invariants, true);
      if (e != null) 
        for (TypeRef t : e.getTypes()) {
          if (t.getProfile() != null && !t.getName().equals("Extension") && t.getProfile().startsWith("#")) { 
            if (!namedSheets.contains(t.getProfile().substring(1)))
              namedSheets.add(t.getProfile().substring(1));      
          }
        }
    }
    
    sheet = loadSheet(n + "-Extensions");
    if (sheet != null) {
      int row = 0;
      while (row < sheet.rows.size()) {
        if (sheet.getColumn(row, "Code").startsWith("!"))
          row++;
        else
          row = processExtension(resource.getRoot().getElementByName("extensions"), sheet, row, definitions, ap.metadata("extension.uri"), ap);
      }
    }
    sheet = loadSheet(n+"-Search");
    if (sheet != null) {
      readSearchParams(resource, sheet, true);
    }

		if (invariants != null) {
			for (Invariant inv : invariants.values()) {
			  if (Utilities.noString(inv.getContext())) 
			    log.log("Type "+resource.getRoot().getName()+" Invariant "+inv.getId()+" has no context", LogMessageType.Warning);
			  else {
			    ElementDefn ed = findContext(resource.getRoot(), inv.getContext(), "Type "+resource.getRoot().getName()+" Invariant "+inv.getId()+" Context");
			    // TODO: Need to resolve context based on element name, not just path
			    if (ed.getName().endsWith("[x]") && !inv.getContext().endsWith("[x]"))
			      inv.setFixedName(inv.getContext().substring(inv.getContext().lastIndexOf(".")+1));
			    ed.getInvariants().put(inv.getId(), inv);
			    if (Utilities.noString(inv.getXpath()))
		        log.log("Type "+resource.getRoot().getName()+" Invariant "+inv.getId()+" ("+inv.getEnglish()+") has no XPath statement", LogMessageType.Warning);
			    else if (inv.getXpath().contains("\""))
	          log.log("Type "+resource.getRoot().getName()+" Invariant "+inv.getId()+" ("+inv.getEnglish()+") contains a \" character", LogMessageType.Warning);
			  }
			}
		}

    resource.getRoot().setProfileName(n);
		ProfileDefn p = new ProfileDefn(ap.getName()+'-'+n, resource.getName(), resource);
    return p;
  }

	private void readExamples(ResourceDefn defn, Sheet sheet) throws Exception {
		if (sheet != null) {
			for (int row = 0; row < sheet.rows.size(); row++) {
				String name = sheet.getColumn(row, "Name");
				if (name != null && !name.equals("") && !name.startsWith("!")) {
				  String id  = sheet.getColumn(row, "Identity");
					String desc = sheet.getColumn(row, "Description");
					if (desc == null || desc.equals(""))
						throw new Exception("Example " + name + " has no description parsing " + this.name);
					String filename = sheet.getColumn(row, "Filename");
					File file = new CSFile(folder + filename);
					String type = sheet.getColumn(row, "Type");
					if (!file.exists() && !("tool".equals(type) || isSpecialType(type)))
						throw new Exception("Example " + name + " file '" + file.getAbsolutePath() + "' not found parsing " + this.name);
					String pn = sheet.getColumn(row, "Profile"); //todo-profile: rename this
					if (Utilities.noString(pn)) {
					  defn.getExamples().add(new Example(name, id, desc, file, 
					      parseExampleType(type, row),
					      parseBoolean(sheet.getColumn(row, "In Book"), row, false)));
					} else {
					  ConformancePackage ap = null;
					  for (ConformancePackage r : defn.getConformancePackages()) {
					    if (r.getTitle().equals(pn))
					      ap = r;
					  }
					  if (ap == null)
					    throw new Exception("Example " + name + " profile '" + pn + "' not found parsing " + this.name);
					  ap.getExamples().add(new Example(filename, id, desc, file, parseExampleType(type, row), parseBoolean(sheet.getColumn(row, "In Book"), row, false)));
					}
				}
			}
		}
		if (defn.getExamples().size() == 0 && !isAbstract) {
			File file = new CSFile(folder + title + "-example.xml");
			if (!file.exists())
				throw new Exception("Example (file '" + file.getAbsolutePath()
						+ "') not found parsing " + this.name);
			defn.getExamples().add(
					new Example("General", "example", "Example of " + title, file, ExampleType.XmlFile, true));
		}		
	}

	
	
	
	private boolean isSpecialType(String type) {
    return false;
  }


  private void readEvents(Sheet sheet) throws Exception {
		if (sheet != null) {
			for (int row = 0; row < sheet.rows.size(); row++) {
				String code = sheet.getColumn(row, "Event Code");
				if (code != null && !code.equals("") && !code.startsWith("!")) {
					EventDefn e = new EventDefn();
					events.add(e);
					e.setCode(code);
					e.setDefinition(Utilities.appendPeriod(sheet.getColumn(row, "Description")));
					e.setCategory(readCategory(sheet.getColumn(row, "Category")));
					EventUsage u = new EventUsage();
					e.getUsages().add(u);
					u.setNotes(sheet.getColumn(row, "Notes"));
					for (String s : sheet.getColumn(row, "Request Resources")
							.split(",")) {
						s = s.trim();
						if (s != "")
							u.getRequestResources().add(s);
					}
					for (String s : sheet
							.getColumn(row, "Request Aggregations").split("\n")) {
						s = s.trim();
						if (s != "")
							u.getRequestAggregations().add(s);
					}
					for (String s : sheet.getColumn(row, "Response Resources")
							.split(",")) {
						s = s.trim();
						if (s != "")
							u.getResponseResources().add(s);
					}
					for (String s : sheet.getColumn(row,
							"Response Aggregations").split("\n")) {
						s = s.trim();
						if (s != "")
							u.getResponseAggregations().add(s);
					}
					for (String s : sheet.getColumn(row, "Follow Ups").split(
							",")) {
						s = s.trim();
						if (s != "")
							e.getFollowUps().add(s);
					}

				}
			}
		}
	}

	private Category readCategory(String s) throws Exception {
    if (Utilities.noString(s))
     return null;
    if (s.equalsIgnoreCase("Consequence"))
      return Category.Consequence;
    if (s.equalsIgnoreCase("Currency"))
      return Category.Currency;
    if (s.equalsIgnoreCase("Notification"))
      return Category.Notification;
    throw new Exception("unknown event category "+s);
  }


  private ElementDefn processLine(ResourceDefn root, Sheet sheet, int row, Map<String, Invariant> invariants, boolean profile) throws Exception {
		ElementDefn e;
		String path = sheet.getColumn(row, "Element");
		if (path.startsWith("!"))
		  return null;
		if (Utilities.noString(path)) 
      throw new Exception("Error reading definitions - no path found @ " + getLocation(row));
		  
		if (path.contains("#"))
			throw new Exception("Old path style @ " + getLocation(row));

    String profileName = isProfile ? sheet.getColumn(row, "Profile Name") : "";
    String discriminator = isProfile ? sheet.getColumn(row, "Discriminator") : "";
		if (!Utilities.noString(profileName) && Utilities.noString(discriminator) && (path.endsWith(".extension") || path.endsWith(".modifierExtension")))
		  discriminator = "url";
		  
		boolean isRoot = !path.contains(".");
		
		if (isRoot) {
			if (root.getRoot() != null)
				throw new Exception("Definitions in " + getLocation(row)+ " contain two roots: " + path + " in "+ root.getName());

			root.setName(path);
			e = new TypeDefn();
			e.setName(path);
			root.setRoot((TypeDefn) e);
		} else {
			e = makeFromPath(root.getRoot(), path, row, profileName, true);
		}

		String tasks = sheet.getColumn(row, "gForge");
		if (!Utilities.noString(tasks)) {
		  for (String t : tasks.split(","))
		    e.getTasks().add(t);
		}
		
		if (e.getName().startsWith("@")) {
		  e.setName(e.getName().substring(1));
		  e.setXmlAttribute(true);
		}
		String c = sheet.getColumn(row, "Card.");
		if (c == null || c.equals("")) {
			if (!isRoot && !profile)
				throw new Exception("Missing cardinality");
			if (isRoot) {
			  e.setMinCardinality(1);
			  e.setMaxCardinality(1);
			}
		} else {
			String[] card = c.split("\\.\\.");
			if (card.length != 2 || !Utilities.IsInteger(card[0]) || (!"*".equals(card[1]) && !Utilities.IsInteger(card[1])))
				throw new Exception("Unable to parse Cardinality '" + c + "' " + c + " in " + getLocation(row) + " on " + path);
			e.setMinCardinality(Integer.parseInt(card[0]));
			e.setMaxCardinality("*".equals(card[1]) ? null : Integer.parseInt(card[1]));
		}
		if (profileName.startsWith("#"))
		  throw new Exception("blah: "+profileName);
		e.setProfileName(profileName);
		e.setSliceDescription(isProfile ? sheet.getColumn(row, "Slice Description") : ""); 
		for (String d : discriminator.split("\\,"))
		  if (!Utilities.noString(d))
		    e.getDiscriminator().add(d);
		doAliases(sheet, row, e);
    if (sheet.hasColumn(row, "Must Understand"))
      throw new Exception("Column 'Must Understand' has been renamed to 'Is Modifier'");

		e.setIsModifier(parseBoolean(sheet.getColumn(row, "Is Modifier"), row, false));
		if (isProfile)
		  e.setMustSupport(parseBoolean(sheet.getColumn(row, "Must Support"), row, false));
    e.setSummaryItem(parseBoolean(sheet.getColumn(row, "Summary"), row, false));
    e.setRegex(sheet.getColumn(row, "Regex"));
    String uml = sheet.getColumn(row, "UML");
    if (uml != null) {
      if (uml.contains(";")) {
        String[] parts = uml.split("\\;");
        e.setSvgLeft(Integer.parseInt(parts[0]));
        e.setSvgTop(Integer.parseInt(parts[1]));
        if (parts.length > 2)
          e.setSvgWidth(Integer.parseInt(parts[2]));
        e.setUmlDir("");
      } else if (uml.startsWith("break:")) {
        e.setUmlBreak(true);
        e.setUmlDir(uml.substring(6));
      } else {
        e.setUmlDir(uml);
      }
    }
		String s = sheet.getColumn(row, "Condition");
		if (s != null && !s.equals(""))
			throw new Exception("Found Condition in spreadsheet "+ getLocation(row));
		s = sheet.getColumn(row, "Inv.");
		if (s != null && !s.equals("")) {
		  for (String sn : s.split(",")) {
		    Invariant inv = invariants.get(sn);
		    if (inv == null)
		      throw new Exception("unable to find Invariant '" + sn + "' "   + getLocation(row));
		    e.getStatedInvariants().add(inv);
		  }
		}

		TypeParser tp = new TypeParser();
		e.getTypes().addAll(tp.parse(sheet.getColumn(row, "Type"), isProfile, profileExtensionBase));

		if (sheet.hasColumn(row, "Concept Domain"))
			throw new Exception("Column 'Concept Domain' has been retired in "
					+ path);

		e.setBindingName(sheet.getColumn(row, "Binding"));
		if (e.hasBindingName()) { 
		  BindingSpecification binding = definitions.getBindingByName(e.getBindingName());
      if (binding != null && !binding.getUseContexts().contains(name))
        binding.getUseContexts().add(name);
	    }
    if (!Utilities.noString(sheet.getColumn(row, "Short Label")))
      e.setShortDefn(sheet.getColumn(row, "Short Label"));
    else // todo: make this a warning when a fair chunk of the spreadsheets have been converted 
      e.setShortDefn(sheet.getColumn(row, "Short Name"));
    
		e.setDefinition(Utilities.appendPeriod(processDefinition(sheet.getColumn(row, "Definition"))));
		
		if (isRoot) {
			root.setDefinition(e.getDefinition());
		} 
		
		if (isProfile)
		  e.setMaxLength(sheet.getColumn(row, "Max Length"));
		e.setRequirements(Utilities.appendPeriod(sheet.getColumn(row, "Requirements")));
		e.setComments(Utilities.appendPeriod(sheet.getColumn(row, "Comments")));
    for (String n : definitions.getMapTypes().keySet()) {
      e.addMapping(n, sheet.getColumn(row, definitions.getMapTypes().get(n).getColumnName()));
    }
		e.setTodo(Utilities.appendPeriod(sheet.getColumn(row, "To Do")));
		e.setExample(processValue(sheet, row, "Example", e));
		e.setCommitteeNotes(Utilities.appendPeriod(sheet.getColumn(row, "Committee Notes")));
		e.setDisplayHint(sheet.getColumn(row, "Display Hint"));
		if (isProfile) {
      e.setFixed(processValue(sheet, row, "Value", e));
      e.setPattern(processValue(sheet, row, "Pattern", e));
		}
		return e;
	}

	private Type processValue(Sheet sheet, int row, String column, ElementDefn e) throws Exception {
    String source = sheet.getColumn(row, column);
    if (Utilities.noString(source))
      return null;  
	  if (e.getTypes().size() != 1) 
      throw new Exception("Unable to process "+column+" unless a single type is speciifed (types = "+e.typeCode()+") "+getLocation(row));
    if (source.startsWith("{")) {
      JsonParser json = new JsonParser();
      return json.parseType(source, e.typeCode());
    } else if (source.startsWith("<")) {
      XmlParser xml = new XmlParser();
      return xml.parseType(source, e.typeCode());
    } else {
      String type = e.typeCode();
      if (type.equals("string"))
        return new StringType(source);
      if (type.equals("boolean"))
        return new BooleanType(Boolean.valueOf(source)); 
      if (type.equals("integer"))
        return new IntegerType(Integer.valueOf(source)); 
      if (type.equals("decimal"))
        return new DecimalType(new BigDecimal(source)); 
      if (type.equals("base64Binary"))
        return new Base64BinaryType(Base64.decode(source.toCharArray()));  
      if (type.equals("instant"))
        return new InstantType(new DateAndTime(source)); 
      if (type.equals("uri"))
        return new UriType(source); 
      if (type.equals("date"))
        return new DateType(new DateAndTime(source)); 
      if (type.equals("dateTime"))
        return new DateTimeType(new DateAndTime(source)); 
      if (type.equals("time"))
        return new TimeType(source); 
      if (type.equals("code"))
        return new CodeType(source); 
      if (type.equals("oid"))
        return new OidType(source); 
      if (type.equals("uuid"))
        return new UuidType(source); 
      if (type.equals("id"))
        return new IdType(source); 
      throw new Exception("Unable to process primitive value provided for "+column+" - unhandled type "+type+" @ " +getLocation(row));
    }
  }


  private void doAliases(Sheet sheet, int row, ElementDefn e) throws Exception {
		String aliases = sheet.getColumn(row, "Aliases");
		if (!Utilities.noString(aliases))
		  if (aliases.contains(";")) {
		    for (String a : aliases.split(";"))
		      e.getAliases().add(a.trim());
		  } else {
	      for (String a : aliases.split(","))
	        e.getAliases().add(a.trim());
		  }
	}

  private int processExtension(ElementDefn extensions, Sheet sheet, int row,	Definitions definitions, String uri, ConformancePackage ap) throws Exception {
	  // first, we build the extension definition
	  ExtensionDefinition ex = new ExtensionDefinition();
	  String name = sheet.getColumn(row, "Code");
	  String context = null;
	  if (Utilities.noString(name))
	    throw new Exception("No code found on Extension");
	  
	  if (name.contains("."))
	    throw new Exception("Extension Definition Error: Extension names cannot contain '.': "+name);
	
	  ex.setUrl(uri+name);
    ex.setId(tail(ex.getUrl()));
	  ap.getExtensions().add(ex);
	  if (context == null) {
      ex.setContextType(readContextType(sheet.getColumn(row, "Context Type"), row));
      ex.addContext(sheet.getColumn(row, "Context"));
	  }
	  ex.setDisplay(sheet.getColumn(row, "Display"));
	  
	  ElementDefn exe = new ElementDefn();
	  exe.setName(sheet.getColumn(row, "Code"));
	  
    parseExtensionElement(sheet, row, definitions, exe);
    ex.setName(exe.getShortDefn());
    ex.setDescription(exe.getDefinition());

    ex.setPublisher(ap.metadata("author.name"));
    if (ap.hasMetadata("author.reference"))
      ex.getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, ap.metadata("author.reference")));
    //  <code> opt Zero+ Coding assist with indexing and finding</code>
    if (ap.hasMetadata("date"))
      ex.setDateElement(Factory.newDateTime(ap.metadata("date").substring(0, 10)));
    else
      ex.setDate(new DateAndTime(genDate));

    if (ap.hasMetadata("status")) 
      ex.setStatus(ExtensionDefinition.ResourceProfileStatus.fromCode(ap.metadata("status")));
   
    row++;
    if (!ex.getUrl().startsWith("http://hl7.org/fhir/ExtensionDefinition/"))
      System.out.println("extension "+ex.getUrl());
    
    while (row < sheet.getRows().size() && sheet.getColumn(row, "Code").startsWith(name+".")) {
      String n = sheet.getColumn(row, "Code");
      ElementDefn p = findContext(exe, n.substring(0, n.lastIndexOf(".")), "Extension Definition "+name);
      ElementDefn child = new ElementDefn();
      p.getElements().add(child);
      child.setName(n.substring(n.lastIndexOf(".")+1));
      parseExtensionElement(sheet, row, definitions, child);
      row++;
    }
	  
	  if (extensions != null) {
	    // if we got an extensions element, split this in.... 

	    ElementDefn e;
	    String path = sheet.getColumn(row, "Code");
	    e = makeExtension(extensions, path, row, definitions);
	    e.setMinCardinality(exe.getMinCardinality());
	    e.setMaxCardinality(exe.getMaxCardinality());
	    e.setCondition(exe.getCondition());
	    e.setBindingName(sheet.getColumn(row, "Binding"));
	    e.setIsModifier(exe.isModifier());
	    e.setDefinition(exe.getDefinition());
	    e.setRequirements(exe.getRequirements());
	    e.setComments(exe.getComments());
	    e.getMappings().putAll(exe.getMappings());
	    e.setTodo(exe.getTodo());
	    e.setExample(exe.getExample());
	    e.setCommitteeNotes(exe.getCommitteeNotes());
	    e.setIsModifier(exe.isModifier());



	    e.getTypes().clear();
	    e.getElementByName("definition").setFixed(new UriType(uri));
	    e.getElementByName("ref").ban();
	    if (e.isModifier())
	      e.getElementByName("mustUnderstand").setFixed(new BooleanType(true));
	    else
	      e.getElementByName("mustUnderstand").ban();
	    ElementDefn v = e.getElementByName("value[x]");
	    v.setShortDefn(sheet.getColumn(row, "Short Name"));
	    e.setShortDefn("");
	    v.getTypes().clear();
	    String t = sheet.getColumn(row, "Type");
	    if (t.equals(""))
	      v.ban();
	    else {
	      TypeParser tp = new TypeParser();
	      v.getTypes().addAll(tp.parse(t, true, profileExtensionBase));
	    }
	    e.getElements().remove(e.getElementByName("extension"));
	  }
    new ProfileGenerator(definitions, null).convertElements(exe, ex, null);
	  this.context.seeExtensionDefinition(ex);
	  return row;
	}

  private String tail(String url) {
    return url.substring(url.lastIndexOf("/")+1);
  }

  private void parseExtensionElement(Sheet sheet, int row, Definitions definitions, ElementDefn exe) throws Exception {
    String[] card = sheet.getColumn(row, "Card.").split("\\.\\.");
    if (card.length != 2 || !Utilities.IsInteger(card[0])
        || (!"*".equals(card[1]) && !Utilities.IsInteger(card[1])))
      throw new Exception("Unable to parse Cardinality "
          + sheet.getColumn(row, "Card.") + " in " + getLocation(row));
    exe.setMinCardinality(Integer.parseInt(card[0]));
    exe.setMaxCardinality("*".equals(card[1]) ? null : Integer.parseInt(card[1]));
    exe.setCondition(sheet.getColumn(row, "Condition"));
    exe.setBindingName(sheet.getColumn(row, "Binding"));
    exe.setIsModifier(parseBoolean(sheet.getColumn(row, "Must Understand"), row, false));
    exe.setDefinition(Utilities.appendPeriod(processDefinition(sheet.getColumn(row, "Definition"))));
    exe.setRequirements(Utilities.appendPeriod(sheet.getColumn(row, "Requirements")));
    exe.setComments(Utilities.appendPeriod(sheet.getColumn(row, "Comments")));
		doAliases(sheet, row, exe);
    for (String n : definitions.getMapTypes().keySet()) {
      exe.addMapping(n, sheet.getColumn(row, definitions.getMapTypes().get(n).getColumnName()));
    }
		String tasks = sheet.getColumn(row, "gForge");
		if (!Utilities.noString(tasks)) {
		  for (String t : tasks.split(","))
		    exe.getTasks().add(t);
		}		
		exe.setMaxLength(sheet.getColumn(row, "Max Length"));
    exe.setTodo(Utilities.appendPeriod(sheet.getColumn(row, "To Do")));
    exe.setExample(processValue(sheet, row, "Example", exe));
    exe.setCommitteeNotes(Utilities.appendPeriod(sheet.getColumn(row, "Committee Notes")));
    exe.setShortDefn(sheet.getColumn(row, "Short Label"));
    String s = sheet.getColumn(row, "Must Understand").toLowerCase();
    if (s.equals("false") || s.equals("0") || s.equals("f")
        || s.equals("n") || s.equals("no"))
      exe.setIsModifier(false);
    else if (s.equals("true") || s.equals("1") || s.equals("t")
        || s.equals("y") || s.equals("yes"))
      exe.setIsModifier(true);
    else if (!"".equals(s))
      throw new Exception("unable to process Must Understand flag: " + s
          + " in " + getLocation(row));
    exe.getTypes().addAll(new TypeParser().parse(sheet.getColumn(row, "Type"), false, profileExtensionBase));
  }

	private ExtensionContext readContextType(String value, int row) throws Exception {
    if (value.equals("Resource"))
      return ExtensionContext.RESOURCE;
    if (value.equals("DataType") || value.equals("Data Type"))
      return ExtensionContext.DATATYPE;
    if (value.equals("Elements"))
      return ExtensionContext.RESOURCE;
    if (value.equals("Mapping"))
      return ExtensionContext.MAPPING;
    if (value.equals("Extension"))
      return ExtensionContext.EXTENSION;
	  
    throw new Exception("Unable to read context type '"+value+"' at "+getLocation(row));
  }

	 private ElementDefn findContext(ElementDefn root, String pathname, String source) throws Exception {
	    String[] path = pathname.split("\\.");
	    
	    if (!path[0].equals(root.getName()))
	      throw new Exception("Element Path '" + pathname + "' is not legal found at " + source);
	    ElementDefn res = root;
	    for (int i = 1; i < path.length; i++) {
	      String en = path[i];
	      if (en.length() == 0)
	        throw new Exception("Improper path " + pathname + " found at " + source);
	      if (en.charAt(en.length() - 1) == '*') 
	        throw new Exception("no-list wrapper found at " + source);

	      ElementDefn t = res.getElementByName(en, true);

	      if (t == null) {
          throw new Exception("Reference to undefined Element "+ pathname+ " found at " + source);
	      }
	      res = t;
	    }
	    return res;
	  }
  private ElementDefn makeFromPath(ElementDefn root, String pathname,
			int row, String profileName, boolean allowMake) throws Exception {
		String[] path = pathname.split("\\.");
		boolean n = false;
		if (!path[0].equals(root.getName()))
			throw new Exception("Element Path '" + pathname
					+ "' is not legal in this context in " + getLocation(row));
		ElementDefn res = root;
		for (int i = 1; i < path.length; i++) {
			String en = path[i];
			if (en.length() == 0)
				throw new Exception("Improper path " + pathname + " in "
						+ getLocation(row));
			if (en.charAt(en.length() - 1) == '*') {
				throw new Exception("no list wrapper found " + getLocation(row));
			}
			ElementDefn t = res.getElementByName(en);

			boolean isUnpickingElement = t != null && (i == path.length - 1)
					&& !t.getProfileName().equals("")
					&& !t.getProfileName().equals(profileName);

			if (t == null || isUnpickingElement) {
				if (n)
					throw new Exception("Internal Logic error " + pathname
							+ " @ " + getLocation(row));
				n = true;
				if (i < path.length - 1)
					throw new Exception("Encounter Element "+ pathname+ " before all the elements in the path are defined in "+ getLocation(row));
			  if (!allowMake)
          throw new Exception("Reference to undefined Element "+ pathname+ " in "+ getLocation(row));
			    
				t = new ElementDefn();
				t.setName(en);
				res.getElements().add(t);
			}
			res = t;
		}
		if (!n)
			throw new Exception("Duplicate Row name " + pathname + " @ "
					+ getLocation(row));
		return res;
	}

	private ElementDefn makeExtension(ElementDefn root, String pathname,
			int row, Definitions definitions) throws Exception {
		String[] path = pathname.split("\\.");

		ElementDefn res = root;
		for (int i = 0; i < path.length; i++) {
			String en = path[i];

			ElementDefn t = res.getElementByProfileName(en);
			if (t == null) {
				if (i < path.length - 1)
					throw new Exception(
							"Encounter Element "
									+ pathname
									+ " before all the elements in the path are defined in "
									+ getLocation(row));
				t = new ElementDefn(definitions.getInfrastructure().get(
						"Extensions"));
				t.setName("extension");
				t.setProfileName(en);
				t.getElementByName("code").setFixed(new CodeType(en));
				res.getElements().add(t);
			}
			res = t;
		}
		return res;
	}

	protected boolean parseBoolean(String s, int row, boolean def) throws Exception {
		s = s.toLowerCase();
		if (s == null || s.equals(""))
			return def;
		else if (s.equalsIgnoreCase("y") || s.equalsIgnoreCase("yes")
				|| s.equalsIgnoreCase("true") || s.equalsIgnoreCase("1"))
			return true;
		else if (s.equals("false") || s.equals("0") || s.equals("f")
				|| s.equals("n") || s.equals("no"))
			return false;
		else
			throw new Exception("unable to process boolean value: " + s
					+ " in " + getLocation(row));
	}

	private String getLocation(int row) {
		return name + ", sheet \""+sheetname+"\", row " + Integer.toString(row + 2);
	}

	public List<EventDefn> getEvents() {
		return events;
	}


  public String getFolder() {
    return folder;
  }


  public void setFolder(String folder) {
    this.folder = folder;
  }

	

}
