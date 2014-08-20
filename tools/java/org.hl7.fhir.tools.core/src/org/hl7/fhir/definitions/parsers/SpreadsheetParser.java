package org.hl7.fhir.definitions.parsers;

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
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.EventDefn;
import org.hl7.fhir.definitions.model.OperationParameter;
import org.hl7.fhir.definitions.model.EventDefn.Category;
import org.hl7.fhir.definitions.model.EventUsage;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.Example.ExampleType;
import org.hl7.fhir.definitions.model.ExtensionDefn;
import org.hl7.fhir.definitions.model.ExtensionDefn.ContextType;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.definitions.model.RegisteredProfile;
import org.hl7.fhir.definitions.model.RegisteredProfile.ProfileInputType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameter;
import org.hl7.fhir.definitions.model.SearchParameter.SearchType;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.XLSXmlParser;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.XLSXmlParser.Sheet;

public class SpreadsheetParser {

	private String name;
	private XLSXmlParser xls;
	private List<EventDefn> events = new ArrayList<EventDefn>();
	private boolean isProfile;
	private Definitions definitions;
	private String title;
	private String folder;
	private Logger log;
	private String sheetname;
	private BindingNameRegistry registry;
  private String dataTypesFolder;
  private String txFolder;

	public SpreadsheetParser(InputStream in, String name,	Definitions definitions, String root, Logger log, BindingNameRegistry registry) throws Exception {
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
			processLine(resource, sheet, row, invariants);
		}
		
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
	  readSearchParams(root, loadSheet("Search"));
	  readProfiles(root, loadSheet("Profiles"));
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
          root.getOperations().put(name, new Operation(name, system, type, instance, sheet.getColumn(row, "Title"), doco, sheet.getColumn(row, "Footer")));
        } else {
          String[] parts = name.split("\\.");
          if (!use.equals("in") && !use.equals("out"))
            throw new Exception("Only allowed types are 'in' or 'out' at "+getLocation(row));
          Operation operation = root.getOperations().get(parts[0]);
          if (operation == null)
            throw new Exception("Unknown Operation '"+parts[0]+"' at "+getLocation(row));
          String type = sheet.getColumn(row, "Type");
          String min = sheet.getColumn(row, "Min");
          String max = sheet.getColumn(row, "Max");
          operation.getParameters().add(new OperationParameter(parts[1], use, doco, Integer.parseInt(min), max, type));
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

	  
	private void readProfiles(ResourceDefn defn, Sheet sheet) throws Exception {
    if (sheet != null) {
      for (int row = 0; row < sheet.rows.size(); row++) {
        String name = sheet.getColumn(row, "Name");
        if (name != null && !name.equals("") && !name.startsWith("!")) {
          String desc = sheet.getColumn(row, "Description");
          if (desc == null || desc.equals(""))
            throw new Exception("Profile " + name + " has no description parsing " + this.name);
          String title = sheet.getColumn(row, "Filename");
          String source = sheet.getColumn(row, "Source");
          if (Utilities.noString(source))
            source = title;
          String etitle = sheet.getColumn(row, "Example");
          ProfileInputType type = readProfileInputType(sheet.getColumn(row, "Type"));
          File file = new CSFile(folder + source);
          if (!file.exists())
            throw new Exception("Profile " + name + " file '" + file.getAbsolutePath() + "' not found parsing " + this.name);
          File efile = null;
          if (!Utilities.noString(etitle)) {
            efile = new CSFile(folder + etitle);
            if (!efile.exists())
              throw new Exception("Profile Example " + name + " file '" + efile.getAbsolutePath() + "' not found parsing " + this.name);
          }
          RegisteredProfile rp = new RegisteredProfile(name, desc, title, source, file.getAbsolutePath(), type);
          if (efile != null)
            rp.getExamples().put(etitle, new Example(etitle, Utilities.fileTitle(etitle), "General Example for "+title, efile, ExampleType.XmlFile, true));
          defn.getProfiles().add(rp);
        }
      }
    }
  }


  private ProfileInputType readProfileInputType(String column) throws Exception {
    if ("spreadsheet".equals(column)) 
      return ProfileInputType.Spreadsheet;
    if ("profile".equals(column)) 
      return ProfileInputType.Profile;
    throw new Exception("Unknown value for Profile Filename Type: "+column);
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

  private void readSearchParams(ResourceDefn root2, Sheet sheet) throws Exception {
    root2.getSearchParams().put("_id", new SearchParameter("_id","The logical resource id associated with the resource (must be supported by all servers)",SearchType.token));
    root2.getSearchParams().put("_language", new SearchParameter("_language","The stated language of the resource",SearchType.token).addPath("language"));

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
              if (!Utilities.noString(p) && !p.startsWith("!"))
                e = root2.getRoot().getElementForPath(p, definitions, "search param"); 
              if (Utilities.noString(d) && e != null)
                d = e.getShortDefn();
              if (d == null)
                throw new Exception("Search Param "+root2.getName()+"/"+n+" has no description "+ getLocation(row));
              if (e != null)
                pn.add(p);
              if (t == SearchType.reference) {
                if (e == null)
                  throw new Exception("Search Param "+root2.getName()+"/"+n+" of type reference has wrong path "+ getLocation(row));
                if (!e.typeCode().startsWith("Resource(") && !e.typeCode().startsWith("uri|Resource("))
                  throw new Exception("Search Param "+root2.getName()+"/"+n+" wrong type. The search type is reference, but the element type is "+e.typeCode());
              } else if (e != null && e.typeCode().startsWith("Resource("))
                throw new Exception("Search Param "+root2.getName()+"/"+n+" wrong type. The search type is "+t.toString()+", but the element type is "+e.typeCode());
            }
            if (t == SearchType.reference && pn.size() == 0)
              throw new Exception("Search Param "+root2.getName()+"/"+n+" of type reference has no path(s) "+ getLocation(row));

            sp = new SearchParameter(n, d, t);
            sp.getPaths().addAll(pn);
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
			cd.setId(registry.idForName(cd.getName()));
			cd.setSource(name);
      cd.setUri(sheet.getColumn(row, "Uri"));
      cd.setOid(sheet.getColumn(row, "Oid"));
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

	private void parseCodes(List<DefinedCode> codes, Sheet sheet)
			throws Exception {
		for (int row = 0; row < sheet.rows.size(); row++) {
			DefinedCode c = new DefinedCode();
			c.setId(sheet.getColumn(row, "Id"));
			c.setCode(sheet.getColumn(row, "Code"));
      c.setDisplay(sheet.getColumn(row, "Display"));
      c.setSystem(sheet.getColumn(row, "System"));
			c.setDefinition(Utilities.appendPeriod(sheet.getColumn(row, "Definition")));
      c.setComment(sheet.getColumn(row, "Comment"));
      c.setParent(sheet.getColumn(row, "Parent"));
      c.setV2Map(sheet.getColumn(row, "v2"));
      c.setV3Map(sheet.getColumn(row, "v3"));
      if (Utilities.noString(c.getId()) && Utilities.noString(c.getSystem()))
        throw new Exception("code has no id or system ("+sheet.title+") "+getLocation(row));
			codes.add(c);
		}
	}

	public ProfileDefn parseProfile(Definitions definitions) throws Exception {
	  try {
		isProfile = true;
		ProfileDefn p = new ProfileDefn();

		Sheet sheet = loadSheet("Metadata");
		for (int row = 0; row < sheet.rows.size(); row++) {
			String n = sheet.getColumn(row, "Name");
			String v = sheet.getColumn(row, "Value");
			if (n != null && v != null) {
				if (p.getMetadata().containsKey(n))
					p.getMetadata().get(n).add(v);
				else {
					ArrayList<String> vl = new ArrayList<String>();
					vl.add(v);
					p.getMetadata().put(n, vl);
				}
			}
		}

    sheet = loadSheet("Invariants");
    Map<String,Invariant> invariants = null;
    if (sheet != null)
      invariants = readInvariants(sheet);
		
    List<String> namedSheets = new ArrayList<String>();
    
		if (p.getMetadata().containsKey("profile")) {
		  for (String n : p.getMetadata().get("profile")) {
		    if (!Utilities.noString(n))
		    parseProfileSheet(definitions, p, invariants, n, namedSheets);
		  }
		}
    
		int i = 0;
    while (i < namedSheets.size()) {
      parseProfileSheet(definitions, p, invariants, namedSheets.get(i), namedSheets);
      i++;
    }

    sheet = loadSheet("Extensions");
    if (sheet != null) {
      for (int row = 0; row < sheet.rows.size(); row++) {
        if (!sheet.getColumn(row, "Code").startsWith("!"))
          processExtension(null,
            sheet, row, definitions,
            p.metadata("extension.uri"), p.getExtensions());
      }
    }

    sheet = loadSheet("Bindings");
    if (sheet != null)
      p.getBindings().addAll(readBindings(sheet).values());
      
		return p;
	  } catch (Exception e) {
	    throw new Exception("exception parsing "+name, e);
	  }
	}


  private void parseProfileSheet(Definitions definitions, ProfileDefn p, Map<String, Invariant> invariants, String n, List<String> namedSheets) throws Exception {
    Sheet sheet;
    ResourceDefn resource = new ResourceDefn();
    sheet = loadSheet(n);
    if (sheet == null)
      throw new Exception("The Profile referred to a tab by the name of '"+n+"', but no tab by the name could be found");
    for (int row = 0; row < sheet.rows.size(); row++) {
      ElementDefn e = processLine(resource, sheet, row, invariants);
      if (e != null && e.getStatedProfile() != null && e.typeCode().startsWith("Resource(") && e.getStatedProfile().startsWith("#")) 
        if (!namedSheets.contains(e.getStatedProfile().substring(1)))
          namedSheets.add(e.getStatedProfile().substring(1));      
    }
    sheet = loadSheet(n + "-Extensions");
    if (sheet != null) {
      for (int row = 0; row < sheet.rows.size(); row++) {
        if (!sheet.getColumn(row, "Code").startsWith("!"))
          processExtension(
            resource.getRoot().getElementByName("extensions"),
            sheet, row, definitions,
            p.metadata("extension.uri"), p.getExtensions());
      }
    }
    resource.getRoot().setProfileName(n);
    p.getResources().add(resource);
  }

	private void readExamples(ResourceDefn defn, Sheet sheet) throws Exception {
		if (sheet != null) {
			for (int row = 0; row < sheet.rows.size(); row++) {
				String name = sheet.getColumn(row, "Name");
				if (name != null && !name.equals("")) {
				  String id  = sheet.getColumn(row, "Identity");
					String desc = sheet.getColumn(row, "Description");
					if (desc == null || desc.equals(""))
						throw new Exception("Example " + name + " has no description parsing " + this.name);
					String filename = sheet.getColumn(row, "Filename");
					File file = new CSFile(folder + filename);
					String type = sheet.getColumn(row, "Type");
					if (!file.exists() && !("tool".equals(type) || isSpecialType(type)))
						throw new Exception("Example " + name + " file '" + file.getAbsolutePath() + "' not found parsing " + this.name);
					String pn = sheet.getColumn(row, "Profile");
					if (Utilities.noString(pn)) {
					defn.getExamples().add(new Example(name, id, desc, file, 
							parseExampleType(type, row),
							parseBoolean(sheet.getColumn(row, "In Book"), row, false)));
					} else {
					  RegisteredProfile rp = null;
					  for (RegisteredProfile r : defn.getProfiles()) {
					    if (r.getName().equals(pn))
		            rp = r;
					  }
					  if (rp == null)
              throw new Exception("Example " + name + " profile '" + pn + "' not found parsing " + this.name);
					  rp.getExamples().put(filename, new Example(filename, id, desc, file, parseExampleType(type, row), parseBoolean(sheet.getColumn(row, "In Book"), row, false)));
					}
				}
			}
		}
		if (defn.getExamples().size() == 0) {
			File file = new CSFile(folder + title + "-example.xml");
			if (!file.exists())
				throw new Exception("Example (file '" + file.getAbsolutePath()
						+ "') not found parsing " + this.name);
			defn.getExamples().add(
					new Example("General", "example", "Example of " + title, file, ExampleType.XmlFile,
							true));
		}		
	}

	
	
	
	private boolean isSpecialType(String type) {
    return false;
  }


  private void readEvents(Sheet sheet) throws Exception {
		if (sheet != null) {
			for (int row = 0; row < sheet.rows.size(); row++) {
				String code = sheet.getColumn(row, "Event Code");
				if (code != null && !code.equals("")) {
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


  private ElementDefn processLine(ResourceDefn root, Sheet sheet, int row, Map<String, Invariant> invariants) throws Exception {
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
			if (!isRoot)
				throw new Exception("Missing cardinality");
			e.setMinCardinality(1);
			e.setMaxCardinality(1);
		} else {
			String[] card = c.split("\\.\\.");
			if (card.length != 2 || !Utilities.IsInteger(card[0]) || (!"*".equals(card[1]) && !Utilities.IsInteger(card[1])))
				throw new Exception("Unable to parse Cardinality '" + c + "' " + c + " in " + getLocation(row) + " on " + path);
			e.setMinCardinality(Integer.parseInt(card[0]));
			e.setMaxCardinality("*".equals(card[1]) ? null : Integer.parseInt(card[1]));
		}
		e.setProfileName(profileName);
		e.setDiscriminator(discriminator);
		String aliases = sheet.getColumn(row, "Aliases");
		if (!Utilities.noString(aliases))
		  if (aliases.contains(";")) {
		    for (String a : aliases.split(";"))
		      e.getAliases().add(a.trim());
		  } else {
	      for (String a : aliases.split(","))
	        e.getAliases().add(a.trim());
		  }
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
          e.setSvgWidth(Integer.parseInt(parts[0]));
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

		String t = sheet.getColumn(row, "Type");
		TypeParser tp = new TypeParser();
		e.getTypes().addAll(tp.parse(t));
		
		e.setStatedProfile(sheet.getColumn(row, "Profile"));
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
    
		e.setDefinition(Utilities.appendPeriod(sheet.getColumn(row, "Definition")));
		
		if (isRoot) {
			root.setDefinition(e.getDefinition());
		} 
		
		e.setRequirements(Utilities.appendPeriod(sheet.getColumn(row, "Requirements")));
		e.setComments(Utilities.appendPeriod(sheet.getColumn(row, "Comments")));
    for (String n : definitions.getMapTypes().keySet()) {
      e.addMapping(n, sheet.getColumn(row, definitions.getMapTypes().get(n).getColumnName()));
    }
		e.setTodo(Utilities.appendPeriod(sheet.getColumn(row, "To Do")));
		e.setExample(sheet.getColumn(row, "Example"));
		e.setCommitteeNotes(Utilities.appendPeriod(sheet.getColumn(row, "Committee Notes")));
		e.setDisplayHint(sheet.getColumn(row, "Display Hint"));
		if (isProfile) {
			e.setValue(sheet.getColumn(row, "Value"));
			e.setAggregation(sheet.getColumn(row, "Aggregation"));
		}
		return e;
	}




  private void processExtension(ElementDefn extensions, Sheet sheet, int row,	Definitions definitions, String uri, List<ExtensionDefn> extensionList) throws Exception {
	  // first, we build the extension definition
	  org.hl7.fhir.definitions.model.ExtensionDefn ex = new org.hl7.fhir.definitions.model.ExtensionDefn();
	  String name = sheet.getColumn(row, "Code");
	  String context = null;
	  if (Utilities.noString(name))
	    throw new Exception("No code found on Extension");
	  
	  if (name.contains(".")) {
	    context = name.substring(0, name.lastIndexOf("."));
	    name = name.substring(name.lastIndexOf(".")+1);
	  }
	  ex.setCode(name);
	  if (context == null) {
      ex.setType(readContextType(sheet.getColumn(row, "Context Type"), row));
      ex.setContext(sheet.getColumn(row, "Context"));
	  }
	  ElementDefn exe = new ElementDefn();
	  exe.setName(sheet.getColumn(row, "Code"));
	  ex.setDefinition(exe);
	  
    if (!"".equals(sheet.getColumn(row, "Concept Domain")))
      throw new Exception(
          "Element definition Concept Domain is not supported");

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
    exe.setDefinition(Utilities.appendPeriod(sheet.getColumn(row, "Definition")));
    exe.setRequirements(Utilities.appendPeriod(sheet.getColumn(row, "Requirements")));
    exe.setComments(Utilities.appendPeriod(sheet.getColumn(row, "Comments")));
    for (String n : definitions.getMapTypes().keySet()) {
      exe.addMapping(n, sheet.getColumn(row, definitions.getMapTypes().get(n).getColumnName()));
    }
    exe.setTodo(Utilities.appendPeriod(sheet.getColumn(row, "To Do")));
    exe.setExample(sheet.getColumn(row, "Example"));
    exe.setCommitteeNotes(Utilities.appendPeriod(sheet.getColumn(row, "Committee Notes")));
    exe.setShortDefn(sheet.getColumn(row, "Short Name"));
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
    exe.getTypes().addAll(new TypeParser().parse(sheet.getColumn(row, "Type")));
	  
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
	    e.getElementByName("definition").setValue(uri);
	    e.getElementByName("ref").ban();
	    if (e.isModifier())
	      e.getElementByName("mustUnderstand").setValue("true");
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
	      v.getTypes().addAll(tp.parse(t));
	    }
	    e.getElements().remove(e.getElementByName("extension"));
	  }
    if (context != null) {
      ExtensionDefn pex = findExtension(extensionList, context.split("\\."), 0);
      if (pex == null)
        throw new Exception("unable to find extension "+context);
      pex.getChildren().add(ex);      
      ex.setContext(uri+ex.getContext());
      ex.setType(ContextType.Extension);
    } else
	    extensionList.add(ex);
	}


  private ExtensionDefn findExtension(List<ExtensionDefn> extensionList, String[] names, int cursor) {
    for (ExtensionDefn ppex : extensionList) {
      if (ppex.getCode().equals(names[cursor])) {
        if (cursor == names.length - 1)
          return ppex;
        else 
          return findExtension(ppex.getChildren(), names, cursor+1);
      }
    }
    return null;
  }

	private ExtensionDefn.ContextType readContextType(String value, int row) throws Exception {
    if (value.equals("Resource"))
      return ExtensionDefn.ContextType.Resource;
    if (value.equals("DataType") || value.equals("Data Type"))
      return ExtensionDefn.ContextType.DataType;
    if (value.equals("Elements"))
      return ExtensionDefn.ContextType.Elements;
    if (value.equals("Mapping"))
      return ExtensionDefn.ContextType.Mapping;
    if (value.equals("Extension"))
      return ExtensionDefn.ContextType.Extension;
	  
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
				t.getElementByName("code").setValue(en);
				res.getElements().add(t);
			}
			res = t;
		}
		return res;
	}

	protected boolean parseBoolean(String s, int row, boolean def)
			throws Exception {
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
