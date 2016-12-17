package org.hl7.fhir.tools.implementations.java;
import static org.apache.commons.lang3.StringUtils.defaultString;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

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
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.Validate;
import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Compartment;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn.SearchType;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.Enumerations.BindingStrength;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

/*
changes for James
- lazy construction of lists
- getX will construct if null
- add hasX
  
*/
public class JavaResourceGenerator extends JavaBaseGenerator {

  /**
   * This property should be committed with a value of false
   * until HAPI 1.6 is released, at which point it will be 
   * removed. Added by JA 2016-05-28
   */
  private static final boolean HAPI_16 = true;

  public enum JavaGenClass { Structure, Type, Resource, BackboneElement, Constraint }
	private JavaGenClass clss;
  private Map<String, String> adornments;
	
	public JavaResourceGenerator(OutputStream out, Definitions definitions, Map<String, String> adornments) throws UnsupportedEncodingException {
		super(out);
		this.definitions = definitions;
		this.adornments = adornments; 
	}

	private Map<ElementDefn, String> typeNames = new HashMap<ElementDefn, String>();
	private List<String> typeNameStrings = new ArrayList<String>();
	private List<ElementDefn> enums = new ArrayList<ElementDefn>();
	private List<String> enumNames = new ArrayList<String>();
	private List<ElementDefn> strucs  = new ArrayList<ElementDefn>();
  private String classname;
  private String allfields;
  private long hashSum;
  private String inheritedHash;


	public Map<ElementDefn, String> getTypeNames() {
		return typeNames;
	}

	public void generate(ElementDefn root, String name, JavaGenClass clss, ProfiledType cd, Date genDate, String version, boolean isAbstract, Map<String, SearchParameterDefn> nameToSearchParamDef, ElementDefn template) throws Exception {
		typeNames.clear();
		typeNameStrings.clear();
		enums.clear();
		strucs.clear();
		enumNames.clear();
		this.clss = clss;

    boolean isRefType = root.getName().equals("Reference");
    
//      ElementDefn elem = root.getElementByName("reference");
//      elem.getTypes().get(0);
//    }
		
		write("package org.hl7.fhir.dstu3.model;\r\n");
		write("\r\n/*\r\n"+Config.FULL_LICENSE_CODE+"*/\r\n\r\n");
		write("// Generated on "+Config.DATE_FORMAT().format(genDate)+" for FHIR v"+version+"\r\n\r\n");
    if (clss != JavaGenClass.Constraint) {
      boolean l = true; // hasList(root);
      boolean h = hasXhtml(root);
      boolean d = hasDecimal(root);
      boolean s = hasString(root);
      boolean e = hasSharedEnums(root);
      if (l || h || d || e) {
        if (l)
          write("import java.util.*;\r\n");
        if (h)
          write("import org.hl7.fhir.utilities.xhtml.XhtmlNode;\r\n");
        write("\r\n");
        if (d)
          write("import java.math.*;\r\n");
        if (s)
          write("import org.hl7.fhir.utilities.Utilities;\r\n");
        if (e)
          write("import org.hl7.fhir.dstu3.model.Enumerations.*;\r\n");
      }
      if (clss == JavaGenClass.Resource) {
        write("import ca.uhn.fhir.model.api.annotation.ResourceDef;\r\n");
        write("import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;\r\n");
      } 
      write("import ca.uhn.fhir.model.api.annotation.Child;\r\n");
      if (HAPI_16) {
        write("import ca.uhn.fhir.model.api.annotation.ChildOrder;\r\n");
      }
      write("import ca.uhn.fhir.model.api.annotation.Description;\r\n");
    }
    if (clss != JavaGenClass.Resource) {
      write("import ca.uhn.fhir.model.api.annotation.DatatypeDef;\r\n");
    }
    write("import ca.uhn.fhir.model.api.annotation.Block;\r\n");
    write("import org.hl7.fhir.instance.model.api.*;\r\n");
    write("import org.hl7.fhir.exceptions.FHIRException;\r\n");
    
    classname = upFirst(name);
    if (adornments.containsKey(classname+".imports")) {
      write("// added from java-adornments.txt:\r\n");
      write(adornments.get(classname+".imports")+"\r\n");
      write("// end addition\r\n");
    }
		jdoc("", root.getDefinition());
		String supertype = root.typeCode();
		
		if (template != null)
		  supertype = template.getName();
		
    if (clss == JavaGenClass.Resource) {
		  
		  if (!isAbstract) {
		    write("@ResourceDef(name=\""+upFirst(name).replace("ListResource", "List")+"\", profile=\"http://hl7.org/fhir/Profile/"+upFirst(name)+"\")\r\n");
		  }
		  
		  if (HAPI_16) {
		    if (template != null) {
	        write("@ChildOrder(names={");
	        boolean first = true; 
	        for (ElementDefn e : root.getElements()) {
	          if (first) {
	            first = false;
	          } else {
	            write(", ");
	          }
	          write("\"");
            write(e.getName());
            write("\"");
	        }
	        write("})\r\n");
		    }
		  }
		  
			String hierarchy;
      if (Utilities.noString(supertype)) {
        hierarchy = "BaseResource implements IAnyResource";
      } else if ("Bundle".equals(upFirst(name))) {
        hierarchy = supertype + " implements IBaseBundle";
      } else if ("Parameters".equals(upFirst(name))) {
        hierarchy = supertype + " implements IBaseParameters";
      } else if ("DomainResource".equals(upFirst(name))) {
        hierarchy = supertype + " implements IBaseHasExtensions, IBaseHasModifierExtensions, IDomainResource";        
      } else if ("Binary".equals(upFirst(name))) {
        hierarchy = "BaseBinary implements IBaseBinary";
      } else if ("CapabilityStatement".equals(upFirst(name))) {
        hierarchy = supertype + " implements IBaseConformance";
      } else if ("OperationOutcome".equals(upFirst(name))) {
        hierarchy = supertype + " implements IBaseOperationOutcome";
      } else {
        hierarchy = supertype;
      }
      write("public "+(isAbstract? "abstract " : "")+"class "+upFirst(name)+" extends "+hierarchy+" ");
    } else if (clss == JavaGenClass.Structure && upFirst(name).equals("Element")) {
      write("public abstract class "+upFirst(name)+" extends Base implements IBaseHasExtensions, IBaseElement ");
      isAbstract = true;
		} else if (clss == JavaGenClass.Structure) {
      write("@DatatypeDef(name=\""+upFirst(name)+"\")\r\n");
      boolean isBackboneElement = upFirst(name).equals("BackboneElement");
      isAbstract = isBackboneElement;
      String absractKeyword = isAbstract ? "abstract " : "";
      String hierarchyKeyword;
      if (name.equals("Extension")) {
        hierarchyKeyword = "BaseExtension implements IBaseExtension<Extension, Type>, IBaseHasExtensions";
      } else if (name.equals("Narrative")) {
        hierarchyKeyword = "BaseNarrative implements INarrative";
      } else {
        hierarchyKeyword = "Element";
      }
      write("public " + absractKeyword + "class " + upFirst(name) + " extends " + hierarchyKeyword+" ");
      if (isBackboneElement) {
        write("implements IBaseBackboneElement ");
      }
		} else if (clss == JavaGenClass.BackboneElement) {
      write("@Block()\r\n");
      write("public class "+upFirst(name)+" extends BackboneElement ");
    } else if (clss == JavaGenClass.Constraint) {
      write("@DatatypeDef(name=\""+upFirst(name)+"\", profileOf=" + upFirst(root.getName()) + ".class)\r\n");
			write("public class "+upFirst(cd.getName())+" extends " + upFirst(root.getName()) + " ");
    } else if (root.getName().equals("Quantity")) {
		  write("@DatatypeDef(name=\""+upFirst(name)+"\")\r\n");
			write("public class "+upFirst(name)+" extends Type implements ICompositeType ");
    } else if (root.getName().equals("Coding")) {
      write("@DatatypeDef(name=\""+upFirst(name)+"\")\r\n");
      write("public class "+upFirst(name)+" extends Type implements IBaseCoding, ICompositeType ");
    } else if (root.getName().equals("Meta")) {
      write("@DatatypeDef(name=\""+upFirst(name)+"\")\r\n");
      write("public class "+upFirst(name)+" extends Type implements IBaseMetaType ");
    } else if (root.getName().equals("Reference")) {
      write("@DatatypeDef(name=\""+upFirst(name)+"\")\r\n");
      write("public class "+upFirst(name)+" extends BaseReference implements IBaseReference, ICompositeType ");
		} else {
      write("@DatatypeDef(name=\""+upFirst(name)+"\")\r\n");
			write("public class "+upFirst(name)+" extends "+(root.typeCode().equals("Structure") ? "Type" : root.typeCode())+" implements ICompositeType ");
		}
		
		write("{\r\n");
		write("\r\n");

		if (clss != JavaGenClass.Constraint) {
			for (ElementDefn e : root.getElements()) {
  			scanNestedTypes(root, root.getName(), e);
			}
			for (ElementDefn e : enums) {
				generateEnum(e);
			}
			for (ElementDefn e : strucs) {
				generateType(e, clss == JavaGenClass.Resource ? JavaGenClass.BackboneElement : JavaGenClass.Structure);
			}

      allfields = "";
      int i = 0;
			for (ElementDefn e : root.getElements()) {
			  if (doGenerateField(e)) {
				  generateField(root, e, "    ", i++);
			  }
			}
	    write("    private static final long serialVersionUID = "+Long.toString(allfields.hashCode())+"L;\r\n\r\n");
	    hashSum = hashSum + allfields.hashCode();

			List<ElementDefn> mandatory = new ArrayList<ElementDefn>();
			generateConstructor(upFirst(name), mandatory, "  ");      
			for (ElementDefn e : root.getElements()) {
        if (doGenerateAccessors(e) && e.isMandatory())
          mandatory.add(e);
      }
	    if (mandatory.size() > 0)
	      generateConstructor(upFirst(name), mandatory, "  ");

	    generateTypeSpecificConstructors(upFirst(name));
	    
			for (ElementDefn e : root.getElements()) {
			  if (doGenerateAccessors(e))
			    generateAccessors(root, e, "    ", upFirst(name));
			}
			
			generateTypeSpecificAccessors(name);
			
			generateChildrenRegister(root, "    ", isAbstract);
      generatePropertyGetterId(root, "    ");
      generatePropertySetterId(root, "    ");
      generatePropertySetterName(root, "    ");
      generatePropertyMaker(root, "    ");
      generateChildAdder(root, "    ", classname);
      generateFhirType(root.getName());
		} else {
      write("    private static final long serialVersionUID = "+inheritedHash+"L;\r\n\r\n");
		}

		generateCopy(root, classname, false, isAbstract);
		generateEquals(root, classname, false, isAbstract);
		generateIsEmpty(root, classname, false, isAbstract);

		if (clss == JavaGenClass.Resource && !isAbstract) {
		  write("  @Override\r\n");
		  write("  public ResourceType getResourceType() {\r\n");
		  write("    return ResourceType."+root.getName()+";\r\n");
		  write("   }\r\n");
		  write("\r\n"); 
		} else if (isAbstract && Utilities.noString(supertype) && clss != JavaGenClass.Structure) {
      write("\r\n"); 
      write("  @Override\r\n"); 
      write("  public String getIdBase() {\r\n"); 
      write("    return getId();\r\n"); 
      write("  }\r\n"); 
      write("  \r\n");
      write("  @Override\r\n");
      write("  public void setIdBase(String value) {\r\n");
      write("    setId(value);\r\n");
      write("  }\r\n");
		  write("  public abstract ResourceType getResourceType();\r\n");
		} else if (isAbstract && Utilities.noString(supertype) && clss == JavaGenClass.Structure) {
      write("  @Override\r\n"); 
      write("  public String getIdBase() {\r\n"); 
      write("    return getId();\r\n"); 
      write("  }\r\n"); 
      write("  \r\n");
      write("  @Override\r\n");
      write("  public void setIdBase(String value) {\r\n");
      write("    setId(value);\r\n");
      write("  }\r\n");
		}		
		// Write resource fields which can be used as constants in client code
		// to refer to standard search params
		if (nameToSearchParamDef != null) {
		  for (SearchParameterDefn sp : nameToSearchParamDef.values()) {
		    
		    String code = sp.getCode();
        
        /* 
         * For composite codes we want to find the two param this is a composite
		     * of. We generate search parameter constants which reference the 
		     * component parts of the composite.  
		     */
        if (sp.getType() == SearchType.composite) {
          
          if (code.endsWith("-[x]")) {
            // partialCode will have "value" in this example
            String partialCode = code.substring(0, code.length() - 4);
            partialCode = partialCode.substring(partialCode.lastIndexOf('-') + 1);
          
            // rootCode will have "component-code"
            String rootCode = code.substring(0, code.indexOf("-" + partialCode));
          
            /*
             * If the composite has the form "foo-bar[x]" we expand this to create 
             * a constant for each of the possible [x] values, so that client have
             * static binding to the individual possibilities. AFAIK this is only
             * used right now in Observation (e.g. for code-value-[x]) 
             */
            for (SearchParameterDefn nextCandidate : nameToSearchParamDef.values()) {
              if (nextCandidate.getCode().startsWith(partialCode)) {
                String nextCompositeCode = rootCode + "-" + nextCandidate.getCode();
                String[] compositeOf = new String[] { rootCode, nextCandidate.getCode() };
                writeSearchParameterField(name, clss, isAbstract, sp, nextCompositeCode, compositeOf, nameToSearchParamDef);
              }
            }
          } else {
            String code0 = sp.getComposites().get(0).getDefinition();
            String code1 = sp.getComposites().get(1).getDefinition();
            SearchParameterDefn comp0 = nameToSearchParamDef.get(code0);
            SearchParameterDefn comp1 = nameToSearchParamDef.get(code1);
            Validate.notNull(comp0, "Couldn't find composite component " + code0 + " - Values are: " + nameToSearchParamDef.keySet());
            Validate.notNull(comp1, "Couldn't find composite component " + code1 + " - Values are: " + nameToSearchParamDef.keySet());
            String[] compositeOf = new String[] { code0, code1 };
            writeSearchParameterField(name, clss, isAbstract, sp, sp.getCode(), compositeOf, nameToSearchParamDef);
          }
  
        } else if (code.contains("[x]")) {
          /*
           * We only know how to handle search parameters with [x] in the name
           * where it's a composite, and the [x] comes last. Are there other possibilities?
           */
          throw new Exception("Unable to generate constant for search parameter: " + code);
        } else {
          writeSearchParameterField(name, clss, isAbstract, sp, code, null, nameToSearchParamDef);
        }
		    
		  }
		}
		
		if (adornments.containsKey(classname)) {
      write("// added from java-adornments.txt:\r\n");
		  write(adornments.get(classname)+"\r\n");
      write("// end addition\r\n");
		}
		write("\r\n");
		write("}\r\n");
		write("\r\n");
		flush();
	}

  private boolean doGenerateField(ElementDefn e) throws Exception {
    String gen = e.getMapping("http://hl7.org/fhir/object-implementation");
    if (!e.isFromTemplate()) {
      if (gen == null)
        return true;
      else
        return !"no-gen-base".equals(gen);
    } else {
      if (gen == null)
        return false;
      else
        return "no-gen-base".equals(gen);
    }
  }

  private boolean doGenerateAccessors(ElementDefn e) throws Exception {
    String gen = e.getMapping("http://hl7.org/fhir/object-implementation");
    if (!e.isFromTemplate()) {
      if (gen == null)
        return true;
      else
        return !"no-gen-base".equals(gen);
    } else 
      return true;
  }

  private void writeSearchParameterField(String name, JavaGenClass clss, boolean isAbstract, SearchParameterDefn sp, String code, String[] theCompositeOf, Map<String, SearchParameterDefn> theNameToSearchParamDef) throws IOException {
    String constName = cleanSpName(code).toUpperCase();
    
    /*
     * SearchParamDefinition (SP_[name])
     */
    write(" /**\r\n"); 
    write("   * Search parameter: <b>" + code + "</b>\r\n"); 
    write("   * <p>\r\n");
    write("   * Description: <b>" + sp.getDescription() + "</b><br>\r\n"); 
    write("   * Type: <b>"+ sp.getType() + "</b><br>\r\n");
    write("   * Path: <b>" + sp.getPathSummary() + "</b><br>\r\n"); 
    write("   * </p>\r\n");
    write("   */\r\n");
    write("  @SearchParamDefinition(name=\"" + code + "\", path=\"" + defaultString(sp.getExpression()) + "\", description=\""+Utilities.escapeJava(sp.getDescription())+"\", type=\""+sp.getType().toString() + "\"");
    if (theCompositeOf != null && theCompositeOf.length > 0) {
      write(", compositeOf={");
      for (int i = 0; i < theCompositeOf.length; i++) {
        if (i > 0) {
          write(", ");
        }
        write("\"" + theCompositeOf[i] + "\"");
      }
      write("}");
    }

    Set<String> providesMembershipIn = new TreeSet<String>();
    for (Compartment next : this.definitions.getCompartments()) {
      for (Entry<ResourceDefn, String> nextEntry : next.getResources().entrySet()) {
        if (nextEntry.getKey().getName().equals(upFirst(name))) {
          String[] parts = nextEntry.getValue().split("\\|");
          for (String nextPart : parts) {
            if (nextPart.trim().equals(code)) {
              providesMembershipIn.add(next.getName());
            }
          }
        }
      }
    }

    if (HAPI_16) {
      if (providesMembershipIn.size() > 0) {
        write(", providesMembershipIn={ ");
        boolean first = true;
        for (String next : providesMembershipIn) {
          if (first) {
            first = false;
          } else {
            write(", ");
          }
          write("@ca.uhn.fhir.model.api.annotation.Compartment(name=\"" + upFirst(next) + "\")");
        }
        write(" }");
      }
      
      Set<String> targets = sp.getWorkingTargets();
      if (targets != null && !targets.isEmpty() && !targets.contains("Any")) {
        write(", target={");
        boolean first = true;
        for (String nextTarget : new TreeSet<String>(targets)) {
          if (first) {
            first = false;
          } else {
            write(", ");
          }
          write(nextTarget);
          write(".class");
        }
        write(" }");
      }
      
    }
    
    write(" )\r\n");
    write("  public static final String SP_"+constName+" = \""+code+"\";\r\n");

    String genericTypes = "";
    if (theCompositeOf != null && theCompositeOf.length > 0) {
      SearchParameterDefn typeDef0 = theNameToSearchParamDef.get(theCompositeOf[0]);
      SearchParameterDefn typeDef1 = theNameToSearchParamDef.get(theCompositeOf[1]);
      genericTypes = "<ca.uhn.fhir.rest.gclient." + upFirst(typeDef0.getType().name()) + "ClientParam" + ", ca.uhn.fhir.rest.gclient." + upFirst(typeDef1.getType().name()) + "ClientParam>";
    }
    
    /*
     * Client parameter ([name])
     */
    write(" /**\r\n"); 
    write("   * <b>Fluent Client</b> search parameter constant for <b>" + code + "</b>\r\n"); 
    write("   * <p>\r\n");
    write("   * Description: <b>" + sp.getDescription() + "</b><br>\r\n"); 
    write("   * Type: <b>"+ sp.getType() + "</b><br>\r\n");
    write("   * Path: <b>" + sp.getPathSummary() + "</b><br>\r\n"); 
    write("   * </p>\r\n");
    write("   */\r\n");
    write("  public static final ca.uhn.fhir.rest.gclient." + upFirst(sp.getType().name()) + "ClientParam" + genericTypes + " " + constName + " = new ca.uhn.fhir.rest.gclient." + upFirst(sp.getType().name()) + "ClientParam" + genericTypes + "(SP_" + constName + ");\r\n\r\n"); 
    
    if (sp.getType() == SearchType.reference && clss == JavaGenClass.Resource && !isAbstract) {
      String incName = upFirst(name) + ":" + code;
      write("/**\r\n"); 
      write("   * Constant for fluent queries to be used to add include statements. Specifies\r\n"); 
      write("   * the path value of \"<b>" + incName + "</b>\".\r\n" );
      write("   */\r\n" );
      write("  public static final ca.uhn.fhir.model.api.Include INCLUDE_" + cleanSpName(code).toUpperCase() + " = new ca.uhn.fhir.model.api.Include(\"" + incName + "\").toLocked();\r\n\r\n");
    }
  }

  private void generateTypeSpecificConstructors(String theName) throws IOException {
    if ("Coding".equals(theName)) {
      write("    /**\r\n"); 
      write(    "     * Convenience constructor\r\n" ); 
      write(    "     * \r\n" );
      write(    "     * @param theSystem The {@link #setSystem(String) code system}\r\n"); 
      write(    "     * @param theCode The {@link #setCode(String) code}\r\n" );
      write(    "     * @param theDisplay The {@link #setDisplay(String) human readable display}\r\n"); 
      write(    "     */\r\n" );
      write(    "      public Coding(String theSystem, String theCode, String theDisplay) {\r\n"); 
      write(    "        setSystem(theSystem);\r\n");
      write(    "        setCode(theCode);\r\n");
      write(    "        setDisplay(theDisplay);\r\n"); 
      write(    "      }\r\n");
    }
    if ("Extension".equals(theName)) {
      write("    /**\r\n"); 
          write("     * Constructor\r\n"); 
          write("     */\r\n"); 
          write("    public Extension(String theUrl) {\r\n"); 
          write("      setUrl(theUrl);\r\n"); 
          write("    }\r\n"); 
          write("\r\n"); 
          write("    /**\r\n"); 
          write("     * Constructor\r\n"); 
          write("     */\r\n"); 
          write("    public Extension(String theUrl, IBaseDatatype theValue) {\r\n"); 
          write("      setUrl(theUrl);\r\n"); 
          write("      setValue(theValue);\r\n"); 
          write("    }\r\n"); 
          write("\r\n");
    } else if ("Reference".equals(theName)) {
      write("    /**\r\n"); 
          write("     * Constructor\r\n"); 
          write("     * \r\n"); 
          write("     * @param theReference The given reference string (e.g. \"Patient/123\" or \"http://example.com/Patient/123\")\r\n"); 
          write("     */\r\n"); 
          write("    public Reference(String theReference) {\r\n"); 
          write("      super(theReference);\r\n"); 
          write("    }\r\n"); 
          write("\r\n"); 
          write("    /**\r\n"); 
          write("     * Constructor\r\n"); 
          write("     * \r\n"); 
          write("     * @param theReference The given reference as an IdType (e.g. \"Patient/123\" or \"http://example.com/Patient/123\")\r\n"); 
          write("     */\r\n"); 
          write("    public Reference(IIdType theReference) {\r\n"); 
          write("      super(theReference);\r\n"); 
          write("    }\r\n"); 
          write("\r\n"); 
          write("    /**\r\n"); 
          write("     * Constructor\r\n"); 
          write("     * \r\n"); 
          write("     * @param theResource The resource represented by this reference\r\n"); 
          write("     */\r\n"); 
          write("    public Reference(IAnyResource theResource) {\r\n"); 
          write("      super(theResource);\r\n"); 
          write("    }\r\n"); 
          write("\r\n");
    } else if ("Quantity".equals(theName)) {
      write(" /**\r\n"); 
          write("   * Convenience constructor\r\n"); 
          write("   * \r\n"); 
          write("   * @param theValue The {@link #setValue(double) value}\r\n"); 
          write("   */\r\n"); 
          write("  public Quantity(double theValue) {\r\n"); 
          write("    setValue(theValue);\r\n"); 
          write("  }\r\n"); 
          write("\r\n"); 
          write("  /**\r\n"); 
          write("   * Convenience constructor\r\n"); 
          write("   * \r\n"); 
          write("   * @param theValue The {@link #setValue(long) value}\r\n"); 
          write("   */\r\n"); 
          write("  public Quantity(long theValue) {\r\n"); 
          write("    setValue(theValue);\r\n"); 
          write("  }\r\n"); 
          write("  \r\n"); 
          write("  /**\r\n"); 
          write("   * Convenience constructor\r\n"); 
          write("   * \r\n"); 
          write("   * @param theComparator The {@link #setComparator(QuantityComparator) comparator}\r\n"); 
          write("   * @param theValue The {@link #setValue(BigDecimal) value}\r\n"); 
          write("   * @param theSystem The {@link #setSystem(String)} (the code system for the units}\r\n"); 
          write("   * @param theCode The {@link #setCode(String)} (the code for the units}\r\n"); 
          write("   * @param theUnit The {@link #setUnit(String)} (the human readable display name for the units}\r\n"); 
          write("   */\r\n"); 
          write("  public Quantity(QuantityComparator theComparator, double theValue, String theSystem, String theCode, String theUnit) {\r\n"); 
          write("    setValue(theValue);\r\n"); 
          write("    setComparator(theComparator);\r\n"); 
          write("    setSystem(theSystem);\r\n"); 
          write("    setCode(theCode);\r\n"); 
          write("    setUnit(theUnit);\r\n"); 
          write("  }\r\n"); 
          write("\r\n"); 
          write("  /**\r\n"); 
          write("   * Convenience constructor\r\n"); 
          write("   * \r\n"); 
          write("   * @param theComparator The {@link #setComparator(QuantityComparator) comparator}\r\n"); 
          write("   * @param theValue The {@link #setValue(BigDecimal) value}\r\n"); 
          write("   * @param theSystem The {@link #setSystem(String)} (the code system for the units}\r\n"); 
          write("   * @param theCode The {@link #setCode(String)} (the code for the units}\r\n"); 
          write("   * @param theUnit The {@link #setUnit(String)} (the human readable display name for the units}\r\n"); 
          write("   */\r\n"); 
          write("  public Quantity(QuantityComparator theComparator, long theValue, String theSystem, String theCode, String theUnit) {\r\n"); 
          write("    setValue(theValue);\r\n"); 
          write("    setComparator(theComparator);\r\n"); 
          write("    setSystem(theSystem);\r\n"); 
          write("    setCode(theCode);\r\n"); 
          write("    setUnit(theUnit);\r\n"); 
          write("  }\r\n"); 
          write("");
    }
  }

  private void generateTypeSpecificAccessors(String name) throws IOException {
    if (upFirst(name).equals("DomainResource")) {
      write("    /**\r\n"); 
          write("     * Returns a list of extensions from this element which have the given URL. Note that\r\n"); 
          write("     * this list may not be modified (you can not add or remove elements from it)\r\n"); 
          write("     */\r\n"); 
          write("    public List<Extension> getExtensionsByUrl(String theUrl) {\r\n"); 
          write("      org.apache.commons.lang3.Validate.notBlank(theUrl, \"theUrl must be provided with a value\");\r\n"); 
          write("      ArrayList<Extension> retVal = new ArrayList<Extension>();\r\n"); 
          write("      for (Extension next : getExtension()) {\r\n"); 
          write("        if (theUrl.equals(next.getUrl())) {\r\n"); 
          write("          retVal.add(next);\r\n"); 
          write("        }\r\n"); 
          write("      }\r\n"); 
          write("      return Collections.unmodifiableList(retVal);\r\n"); 
          write("    }\r\n"); 
          write("\r\n"); 
          write("    /**\r\n"); 
          write("     * Returns a list of modifier extensions from this element which have the given URL. Note that\r\n"); 
          write("     * this list may not be modified (you can not add or remove elements from it)\r\n"); 
          write("     */\r\n"); 
          write("    public List<Extension> getModifierExtensionsByUrl(String theUrl) {\r\n"); 
          write("      org.apache.commons.lang3.Validate.notBlank(theUrl, \"theUrl must be provided with a value\");\r\n"); 
          write("      ArrayList<Extension> retVal = new ArrayList<Extension>();\r\n"); 
          write("      for (Extension next : getModifierExtension()) {\r\n"); 
          write("        if (theUrl.equals(next.getUrl())) {\r\n"); 
          write("          retVal.add(next);\r\n"); 
          write("        }\r\n"); 
          write("      }\r\n"); 
          write("      return Collections.unmodifiableList(retVal);\r\n"); 
          write("    }\r\n"); 
          write("\r\n");
    }
    if (upFirst(name).equals("Element")) {
      write("   /**\r\n"); 
          write("    * Returns an unmodifiable list containing all extensions on this element which \r\n"); 
          write("    * match the given URL.\r\n"); 
          write("    * \r\n"); 
          write("    * @param theUrl The URL. Must not be blank or null.\r\n"); 
          write("    * @return an unmodifiable list containing all extensions on this element which \r\n"); 
          write("    * match the given URL\r\n"); 
          write("    */\r\n"); 
          write("   public List<Extension> getExtensionsByUrl(String theUrl) {\r\n"); 
          write("     org.apache.commons.lang3.Validate.notBlank(theUrl, \"theUrl must not be blank or null\");\r\n"); 
          write("     ArrayList<Extension> retVal = new ArrayList<Extension>();\r\n"); 
          write("     for (Extension next : getExtension()) {\r\n"); 
          write("       if (theUrl.equals(next.getUrl())) {\r\n"); 
          write("         retVal.add(next);\r\n"); 
          write("       }\r\n"); 
          write("     }\r\n"); 
          write("     return java.util.Collections.unmodifiableList(retVal);\r\n"); 
          write("   }\r\n");
      write("  public boolean hasExtension(String theUrl) {\r\n");
      write("    return !getExtensionsByUrl(theUrl).isEmpty(); \r\n");
      write("  }\r\n");
      write("\r\n");
      write("  public String getExtensionString(String theUrl) throws FHIRException {\r\n");
      write("    List<Extension> ext = getExtensionsByUrl(theUrl); \r\n");
      write("    if (ext.isEmpty()) \r\n");
      write("      return null; \r\n");
      write("    if (ext.size() > 1) \r\n");
      write("      throw new FHIRException(\"Multiple matching extensions found\");\r\n");
      write("    if (!ext.get(0).getValue().isPrimitive())\r\n");
      write("      throw new FHIRException(\"Extension could not be converted to a string\");\r\n");
      write("    return ext.get(0).getValue().primitiveValue();\r\n");
      write("  }\r\n");
      write("\r\n");
    }
    if (upFirst(name).equals("Bundle") || upFirst(name).equals("BundleEntryComponent")) {
		  write(" /**\r\n"); 
		      write("   * Returns the {@link #getLink() link} which matches a given {@link BundleLinkComponent#getRelation() relation}. \r\n"); 
		      write("   * If no link is found which matches the given relation, returns <code>null</code>. If more than one\r\n"); 
		      write("   * link is found which matches the given relation, returns the first matching BundleLinkComponent.\r\n"); 
		      write("   * \r\n"); 
		      write("   * @param theRelation\r\n"); 
		      write("   *            The relation, such as \"next\", or \"self. See the constants such as {@link IBaseBundle#LINK_SELF} and {@link IBaseBundle#LINK_NEXT}.\r\n"); 
		      write("   * @return Returns a matching BundleLinkComponent, or <code>null</code>\r\n"); 
		      write("   * @see IBaseBundle#LINK_NEXT\r\n"); 
		      write("   * @see IBaseBundle#LINK_PREV\r\n"); 
		      write("   * @see IBaseBundle#LINK_SELF\r\n"); 
		      write("   */\r\n"); 
		      write("  public BundleLinkComponent getLink(String theRelation) {\r\n"); 
		      write("    org.apache.commons.lang3.Validate.notBlank(theRelation, \"theRelation may not be null or empty\");\r\n"); 
		      write("    for (BundleLinkComponent next : getLink()) {\r\n"); 
		      write("      if (theRelation.equals(next.getRelation())) {\r\n"); 
		      write("        return next;\r\n"); 
		      write("      }\r\n"); 
		      write("    }\r\n"); 
		      write("    return null;\r\n"); 
		      write("  }\r\n"); 
		      write("\r\n"); 
		      write("  /**\r\n"); 
		      write("   * Returns the {@link #getLink() link} which matches a given {@link BundleLinkComponent#getRelation() relation}. \r\n"); 
		      write("   * If no link is found which matches the given relation, creates a new BundleLinkComponent with the\r\n"); 
		      write("   * given relation and adds it to this Bundle. If more than one\r\n"); 
		      write("   * link is found which matches the given relation, returns the first matching BundleLinkComponent.\r\n"); 
		      write("   * \r\n"); 
		      write("   * @param theRelation\r\n"); 
		      write("   *            The relation, such as \"next\", or \"self. See the constants such as {@link IBaseBundle#LINK_SELF} and {@link IBaseBundle#LINK_NEXT}.\r\n"); 
		      write("   * @return Returns a matching BundleLinkComponent, or <code>null</code>\r\n"); 
		      write("   * @see IBaseBundle#LINK_NEXT\r\n"); 
		      write("   * @see IBaseBundle#LINK_PREV\r\n"); 
		      write("   * @see IBaseBundle#LINK_SELF\r\n"); 
		      write("   */\r\n"); 
		      write("  public BundleLinkComponent getLinkOrCreate(String theRelation) {\r\n"); 
		      write("    org.apache.commons.lang3.Validate.notBlank(theRelation, \"theRelation may not be null or empty\");\r\n"); 
		      write("    for (BundleLinkComponent next : getLink()) {\r\n"); 
		      write("      if (theRelation.equals(next.getRelation())) {\r\n"); 
		      write("        return next;\r\n"); 
		      write("      }\r\n"); 
		      write("    }\r\n"); 
		      write("    BundleLinkComponent retVal = new BundleLinkComponent();\r\n"); 
		      write("    retVal.setRelation(theRelation);\r\n"); 
		      write("    getLink().add(retVal);\r\n"); 
		      write("    return retVal;\r\n"); 
		      write("  }\r\n"); 
		      write("");
		}
    if (upFirst(name).equals("HumanName")) {
      write(" /**\r\n"); 
          write("  /**\r\n"); 
          write("   * Returns all repetitions of {@link #getGiven() given name} as a space separated string\r\n"); 
          write("   * \r\n"); 
          write("   * @see DatatypeUtil#joinStringsSpaceSeparated(List)\r\n"); 
          write("   */\r\n"); 
          write("  public String getGivenAsSingleString() {\r\n"); 
          write("    return joinStringsSpaceSeparated(getGiven());\r\n"); 
          write("  }\r\n"); 
          write("\r\n"); 
          write("  /**\r\n"); 
          write("   * Returns all repetitions of {@link #getPrefix() prefix name} as a space separated string\r\n"); 
          write("   * \r\n"); 
          write("   * @see DatatypeUtil#joinStringsSpaceSeparated(List)\r\n"); 
          write("   */\r\n"); 
          write("  public String getPrefixAsSingleString() {\r\n"); 
          write("    return joinStringsSpaceSeparated(getPrefix());\r\n"); 
          write("  }\r\n"); 
          write("\r\n"); 
          write("  /**\r\n"); 
          write("   * Returns all repetitions of {@link #getSuffix() suffix} as a space separated string\r\n"); 
          write("   * \r\n"); 
          write("   * @see DatatypeUtil#joinStringsSpaceSeparated(List)\r\n"); 
          write("   */\r\n"); 
          write("  public String getSuffixAsSingleString() {\r\n"); 
          write("    return joinStringsSpaceSeparated(getSuffix());\r\n"); 
          write("  }\r\n"); 
          write("\r\n"); 
          write("  /**\r\n"); 
          write("   * Returns all of the components of the name (prefix, given, family, suffix) as a single string with a single spaced\r\n"); 
          write("   * string separating each part.\r\n"); 
          write("   * <p>\r\n"); 
          write("   * If none of the parts are populated, returns the {@link #getTextElement() text} element value instead.\r\n"); 
          write("   * </p>\r\n"); 
          write("   */\r\n"); 
          write("  public String getNameAsSingleString() {\r\n"); 
          write("    List<StringType> nameParts = new ArrayList<StringType>();\r\n"); 
          write("    nameParts.addAll(getPrefix());\r\n"); 
          write("    nameParts.addAll(getGiven());\r\n"); 
          write("    nameParts.add(getFamilyElement());\r\n"); 
          write("    nameParts.addAll(getSuffix());\r\n"); 
          write("    if (nameParts.size() > 0) {\r\n"); 
          write("      return joinStringsSpaceSeparated(nameParts);\r\n"); 
          write("    } else {\r\n"); 
          write("      return getTextElement().getValue();\r\n"); 
          write("    }\r\n"); 
          write("  }\r\n"); 
          write("\r\n"); 
          write("  /**\r\n"); 
          write("   * Joins a list of strings with a single space (' ') between each string\r\n"); 
          write("   * \r\n"); 
          write("   * TODO: replace with call to ca.uhn.fhir.util.DatatypeUtil.joinStringsSpaceSeparated when HAPI upgrades to 1.4\r\n"); 
          write("   */\r\n"); 
          write("  private static String joinStringsSpaceSeparated(List<? extends IPrimitiveType<String>> theStrings) {\r\n"); 
          write("    StringBuilder b = new StringBuilder();\r\n"); 
          write("    for (IPrimitiveType<String> next : theStrings) {\r\n"); 
          write("      if (next.isEmpty()) {\r\n"); 
          write("        continue;\r\n"); 
          write("      }\r\n"); 
          write("      if (b.length() > 0) {\r\n"); 
          write("        b.append(' ');\r\n"); 
          write("      }\r\n"); 
          write("      b.append(next.getValue());\r\n"); 
          write("    }\r\n"); 
          write("    return b.toString();\r\n"); 
          write("  }\r\n"); 
          write("");
    }
    if (upFirst(name).equals("Meta")) {
      write("    /**\r\n"); 
          write("     * Convenience method which adds a tag\r\n"); 
          write("     * \r\n"); 
          write("     * @param theSystem The code system\r\n"); 
          write("     * @param theCode The code\r\n"); 
          write("     * @param theDisplay The display name\r\n"); 
          write("     * @return Returns a reference to <code>this</code> for easy chaining\r\n"); 
          write("     */\r\n"); 
          write("    public Meta addTag(String theSystem, String theCode, String theDisplay) {\r\n"); 
          write("     addTag().setSystem(theSystem).setCode(theCode).setDisplay(theDisplay);\r\n"); 
          write("     return this;\r\n"); 
          write("    }\r\n"); 
          write("");
      write("    /**\r\n"); 
          write("     * Convenience method which adds a security tag\r\n"); 
          write("     * \r\n"); 
          write("     * @param theSystem The code system\r\n"); 
          write("     * @param theCode The code\r\n"); 
          write("     * @param theDisplay The display name\r\n"); 
          write("     * @return Returns a reference to <code>this</code> for easy chaining\r\n"); 
          write("     */\r\n"); 
          write("    public Meta addSecurity(String theSystem, String theCode, String theDisplay) {\r\n"); 
          write("     addSecurity().setSystem(theSystem).setCode(theCode).setDisplay(theDisplay);\r\n"); 
          write("     return this;\r\n"); 
          write("    }\r\n"); 
          write("");
          write("   /**\r\n" );
          write(    "   * Returns the first tag (if any) that has the given system and code, or returns\r\n"); 
          write(    "   * <code>null</code> if none\r\n"); 
          write(    "   */\r\n" );
          write(    "  public Coding getTag(String theSystem, String theCode) {\r\n"); 
          write (   "    for (Coding next : getTag()) {\r\n" );
          write  (  "      if (ca.uhn.fhir.util.ObjectUtil.equals(next.getSystem(), theSystem) && ca.uhn.fhir.util.ObjectUtil.equals(next.getCode(), theCode)) {\r\n" ); 
          write (   "        return next;\r\n" ); 
          write (   "      }\r\n" ); 
          write (   "    }\r\n" );
              write(    "    return null;\r\n" ); 
              write(     "  }\r\n" ); 
              write(     "\r\n" );
              write(     "  /**\r\n" );
              write(     "   * Returns the first security label (if any) that has the given system and code, or returns\r\n" ); 
              write(     "   * <code>null</code> if none\r\n"); 
              write(     "   */\r\n" );
              write(     "  public Coding getSecurity(String theSystem, String theCode) {\r\n"); 
              write(     "    for (Coding next : getTag()) {\r\n" );
              write(      "      if (ca.uhn.fhir.util.ObjectUtil.equals(next.getSystem(), theSystem) && ca.uhn.fhir.util.ObjectUtil.equals(next.getCode(), theCode)) {\r\n" ); 
              write(      "        return next;\r\n" ); 
              write(      "      }\r\n" ); 
              write(      "    }\r\n" );
              write(      "    return null;\r\n"); 
              write(      "  }\r\n");
    }
    if (upFirst(name).equals("Period")) {
      write("   /**\r\n");
      write("   * Sets the value for <b>start</b> ()\r\n"); 
      write("   *\r\n");
      write("     * <p>\r\n");
      write("     * <b>Definition:</b>\r\n");
      write("     * The start of the period. The boundary is inclusive.\r\n"); 
      write("     * </p> \r\n"); 
      write("   */\r\n");
      write("  public Period setStart( Date theDate,  TemporalPrecisionEnum thePrecision) {\r\n"); 
      write("    start = new DateTimeType(theDate, thePrecision); \r\n"); 
      write("    return this; \r\n"); 
      write("  }\r\n"); 
      write("\r\n");
      write("   /**\r\n");
      write("   * Sets the value for <b>end</b> ()\r\n"); 
      write("   *\r\n");
      write("     * <p>\r\n");
      write("     * <b>Definition:</b>\r\n");
      write("     * The end of the period. The boundary is inclusive.\r\n"); 
      write("     * </p> \r\n"); 
      write("   */\r\n");
      write("  public Period setEnd( Date theDate,  TemporalPrecisionEnum thePrecision) {\r\n"); 
      write("    end = new DateTimeType(theDate, thePrecision); \r\n"); 
      write("    return this; \r\n"); 
      write("  }\r\n"); 
      write("\r\n");
    }
    if (upFirst(name).equals("Reference")) {
      write(" /**\r\n"); 
          write("   * Convenience setter which sets the reference to the complete {@link IIdType#getValue() value} of the given\r\n"); 
          write("   * reference.\r\n"); 
          write("   *\r\n"); 
          write("   * @param theReference The reference, or <code>null</code>\r\n"); 
          write("   * @return \r\n"); 
          write("   * @return Returns a reference to this\r\n"); 
          write("   */\r\n"); 
          write("  public Reference setReferenceElement(IIdType theReference) {\r\n"); 
          write("    if (theReference != null) {\r\n"); 
          write("      setReference(theReference.getValue());\r\n"); 
          write("    } else {\r\n"); 
          write("      setReference(null);\r\n"); 
          write("    }\r\n"); 
          write("    return this;\r\n"); 
          write("  }\r\n"); 
          write("");
    }
  }

  private void generateFhirType(String path) throws IOException {
    write("  public String fhirType() {\r\n");
    write("    return \""+path+"\";\r\n\r\n");
    write("  }\r\n\r\n");
  }

  private String cleanSpName(String code) {
    StringBuilder b = new StringBuilder();
    for (char c : code.toCharArray())
      if (Character.isLetter(c)) {
        b.append(c);
      } else if (c == '-') {
        b.append('_');
      }
    return b.toString();
  }

  private String pipeSeparate(List<String> paths) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (String p : paths) {
      if (first)
        first = false;
      else
        b.append("|");
      b.append(p);
    }
    return b.toString();
  }

  private void jdoc(String indent, String text) throws IOException {
    write(indent+"/**\r\n");
		write(indent+" * "+text+"\r\n");
		write(indent+" */\r\n");
  }

	private void generateChildrenRegister(ElementDefn p, String indent, boolean isAbstract) throws Exception {
	  write(indent+"  protected void listChildren(List<Property> childrenList) {\r\n");
	  if (!isAbstract)
	    write(indent+"    super.listChildren(childrenList);\r\n");
	  for (ElementDefn e : p.getElements()) {
      if (doGenerateAccessors(e) && !e.typeCode().equals("xhtml"))
	      write(indent+"    childrenList.add(new Property(\""+e.getName()+"\", \""+e.typeCode()+"\", \""+Utilities.escapeJava(e.getDefinition())+"\", 0, java.lang.Integer.MAX_VALUE, "+getElementName(e.getName(), true)+"));\r\n");    
	  }
	  write(indent+"  }\r\n\r\n");  
  }
	
  private void generatePropertyMaker(ElementDefn p, String indent) throws Exception {
    write(indent+"  @Override\r\n");
    write(indent+"  public Base makeProperty(int hash, String name) throws FHIRException {\r\n");
    write(indent+"    switch (hash) {\r\n");
    for (ElementDefn e : p.getElements()) {
      if (doGenerateAccessors(e)) { 
        String tn = typeNames.get(e);
        if (!e.typeCode().equals("xhtml")) {
          write(indent+"    case "+propId(e.getName())+": ");
          String name = e.getName().replace("[x]", "");
          if (isPrimitive(e.typeCode())) {
            if (e.unbounded())
              write(" return add"+upFirst(getElementName(name, false))+"Element();\r\n");
            else
              write(" return get"+upFirst(getElementName(name, false))+"Element();\r\n");
          } else if (e.typeCode().equals("Resource") || e.typeCode().equals("DomainResource")) {
            write("throw new FHIRException(\"Cannot make property "+e.getName()+" as it is not a complex type\"); // "+tn+"\r\n");
          } else if (e.unbounded()) {
            write(" return add"+upFirst(getElementName(name, false))+"(); \r\n");
          } else  {
            write(" return get"+upFirst(getElementName(name, false))+"(); \r\n");
          }
        }
      }
    }
    write(indent+"    default: return super.makeProperty(hash, name);\r\n");
    write(indent+"    }\r\n\r\n");  
    write(indent+"  }\r\n\r\n");  
  }

  private void generatePropertySetterName(ElementDefn p, String indent) throws Exception {
    write(indent+"  @Override\r\n");
    write(indent+"  public void setProperty(String name, Base value) throws FHIRException {\r\n");
    boolean first = true;
    for (ElementDefn e : p.getElements()) {
      if (doGenerateAccessors(e)) { 
        String tn = typeNames.get(e);
        if (first) 
          write(indent+"    ");
        else
          write(indent+"    else ");
        first = false;
        write(           "if (name.equals(\""+e.getName()+"\"))\r\n");
        String name = e.getName().replace("[x]", "");
        String cn = "("+tn+") value";
        if (e.typeCode().equals("xhtml")) {
          cn = "castToXhtml(value)";
        } else if (tn.contains("Enumeration<")) { // enumeration
          cn = "new "+tn.substring(tn.indexOf("<")+1, tn.length()-1)+"EnumFactory().fromType(value)";
        } else if (e.getTypes().size() == 1 && !e.typeCode().equals("*") && !e.getTypes().get(0).getName().startsWith("@")) { 
          cn = "castTo"+upFirst(e.getTypes().get(0).getName())+"(value)";
        } else if (e.getTypes().size() > 0 && !e.getTypes().get(0).getName().startsWith("@")) { 
          cn = "castToType(value)";
        }
        if (e.unbounded()) {
          write(indent+"      this.get"+upFirst(getElementName(name, false))+"().add("+cn+");\r\n");
        } else {
          write(indent+"      this."+getElementName(name, true)+" = "+cn+"; // "+tn+"\r\n");
        }
      }
    }
    if (!first)
      write(indent+"    else\r\n");
    write(indent+"      super.setProperty(name, value);\r\n");
    write(indent+"  }\r\n\r\n");  
  }

  private void generatePropertySetterId(ElementDefn p, String indent) throws Exception {
    write(indent+"  @Override\r\n");
    write(indent+"  public void setProperty(int hash, String name, Base value) throws FHIRException {\r\n");
    write(indent+"    switch (hash) {\r\n");
    for (ElementDefn e : p.getElements()) {
      if (doGenerateAccessors(e)) { 
        String tn = typeNames.get(e);
        String name = e.getName().replace("[x]", "");
        write(indent+"    case "+propId(name)+": // "+name+"\r\n");
        String cn = "("+tn+") value";
        if (e.typeCode().equals("xhtml")) {
          cn = "castToXhtml(value)";
        } if (tn.contains("Enumeration<")) { // enumeration
          cn = "new "+tn.substring(tn.indexOf("<")+1, tn.length()-1)+"EnumFactory().fromType(value)";
        } else if (e.getTypes().size() == 1 && !e.typeCode().equals("*") && !e.getTypes().get(0).getName().startsWith("@")) { 
          cn = "castTo"+upFirst(e.getTypes().get(0).getName())+"(value)";
        } else if (e.getTypes().size() > 0 && !e.getTypes().get(0).getName().startsWith("@")) { 
          cn = "castToType(value)";
        }
        if (e.unbounded()) {
          write(indent+"      this.get"+upFirst(getElementName(name, false))+"().add("+cn+"); // "+tn+"\r\n");
        } else {
          write(indent+"      this."+getElementName(name, true)+" = "+cn+"; // "+tn+"\r\n");
        }
        write(indent+"      break;\r\n");
      }
    }
    write(indent+"    default: super.setProperty(hash, name, value);\r\n");
    write(indent+"    }\r\n\r\n");  
    write(indent+"  }\r\n\r\n");  
  }

  private void generatePropertyGetterId(ElementDefn p, String indent) throws Exception {
    write(indent+"  @Override\r\n");
    write(indent+"  public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {\r\n");
    write(indent+"    switch (hash) {\r\n");
    for (ElementDefn e : p.getElements()) {
      if (doGenerateAccessors(e)) { 
        String tn = typeNames.get(e);
        String name = e.getName().replace("[x]", "");
        write(indent+"    case "+propId(name)+": /*"+name+"*/ ");
        if (e.unbounded()) {
          write("return this."+getElementName(name, true)+" == null ? new Base[0] : this."+getElementName(name, true)+".toArray(new Base[this."+getElementName(name, true)+".size()]); // "+tn+"\r\n");
        } else if (e.typeCode().equals("xhtml")) {
          write("return this."+getElementName(name, true)+" == null ? new Base[0] : new Base[] {new StringType(new XhtmlComposer().composeEx(this."+getElementName(name, true)+"))}; // "+tn+"\r\n");
        } else {
          write("return this."+getElementName(name, true)+" == null ? new Base[0] : new Base[] {this."+getElementName(name, true)+"}; // "+tn+"\r\n");
        }
      }
    }
    write(indent+"    default: return super.getProperty(hash, name, checkValid);\r\n");
    write(indent+"    }\r\n\r\n");  
    write(indent+"  }\r\n\r\n");  
  }

  private String propId(String name) {
    return Integer.toString(name.hashCode());
  }

  private void generateChildAdder(ElementDefn p, String indent, String parent) throws Exception {
    write(indent+"  @Override\r\n");
    write(indent+"  public Base addChild(String name) throws FHIRException {\r\n");
    boolean first = true;
    for (ElementDefn e : p.getElements()) {
      if (doGenerateAccessors(e)) { 
        if (!e.typeCode().equals("xhtml")) { 
          if (e.getTypes().size() <= 1 && !e.typeCode().equals("*")) {
            String tn = typeNames.get(e);
            String name = e.getName();
            String namet = e.getName();
            first = generateChildAddItem(indent, parent, first, e, tn, name, namet);
          } else {
            for (TypeRef t : getTypes(e.getTypes())) {
              String tn = getTypename(t);
              String name = e.getName().replace("[x]", "");
              String namet = e.getName().replace("[x]", upFirst(t.getName()));
              first = generateChildAddItem(indent, parent, first, e, tn, name, namet);
            }
          }
        }
      }
    }
    if (!first)
      write(indent+"    else\r\n");
    write(indent+"      return super.addChild(name);\r\n");
    write(indent+"  }\r\n\r\n");  
  }

  private boolean generateChildAddItem(String indent, String parent, boolean first, ElementDefn e, String tn, String name, String namet) throws IOException {
    if (first) 
      write(indent+"    ");
    else
      write(indent+"    else ");
    first = false;
    write(           "if (name.equals(\""+namet+"\")) {\r\n");
    if (isPrimitive(e.typeCode()))
      write(indent+"      throw new FHIRException(\"Cannot call addChild on a primitive type "+parent+"."+e.getName()+"\");\r\n"); 
    else if (isAbstract(e.typeCode()))
      write(indent+"      throw new FHIRException(\"Cannot call addChild on an abstract type "+parent+"."+e.getName()+"\");\r\n"); 
    else if (e.unbounded()) {
      write(indent+"      return add"+upFirst(getElementName(name, false))+"();\r\n");
    } else {
      write(indent+"      this."+getElementName(name, true)+" = new "+tn+"();\r\n");
      write(indent+"      return this."+getElementName(name, true)+";\r\n");
    }
    write(indent+"    }\r\n");
    return first;
  }

  private List<TypeRef> getTypes(List<TypeRef> types) {
    if (types.size() == 1 && types.get(0).getName().equals("*")) {
      List<TypeRef> t = new ArrayList<TypeRef>();
      t.add(new TypeRef("boolean"));
      t.add(new TypeRef("integer"));
      t.add(new TypeRef("decimal"));
      t.add(new TypeRef("base64Binary"));
      t.add(new TypeRef("instant"));
      t.add(new TypeRef("string"));
      t.add(new TypeRef("uri"));
      t.add(new TypeRef("date"));
      t.add(new TypeRef("dateTime"));
      t.add(new TypeRef("time"));
      t.add(new TypeRef("code"));
      t.add(new TypeRef("oid"));
      t.add(new TypeRef("id"));
      t.add(new TypeRef("unsignedInt"));
      t.add(new TypeRef("positiveInt"));
      t.add(new TypeRef("markdown"));
      t.add(new TypeRef("Annotation"));
      t.add(new TypeRef("Attachment"));
      t.add(new TypeRef("Identifier"));
      t.add(new TypeRef("CodeableConcept"));
      t.add(new TypeRef("Coding"));
      t.add(new TypeRef("Quantity"));
      t.add(new TypeRef("Range"));
      t.add(new TypeRef("Period"));
      t.add(new TypeRef("Ratio"));
      t.add(new TypeRef("SampledData"));
      t.add(new TypeRef("Signature"));
      t.add(new TypeRef("HumanName"));
      t.add(new TypeRef("Address"));
      t.add(new TypeRef("ContactPoint"));
      t.add(new TypeRef("Timing"));
      t.add(new TypeRef("Reference"));
      t.add(new TypeRef("Meta"));
      return t;
    }
    else
      return types;
  }

  private boolean isAbstract(String typeCode) {
    if (typeCode.equals("Resource"))
      return true;
    else
      return false;
  }

  private void generateConstructor(String className, List<ElementDefn> params, String indent) throws IOException {
    write(indent+"/**\r\n");
    write(indent+" * Constructor\r\n");
    write(indent+" */\r\n");
    write(indent+"  public "+className+"(");
    boolean first = true;
    for (ElementDefn e : params) {
      if (!first)
        write(", ");
      first = false;
      String tn = typeNames.get(e);
      String en = getElementName(e.getName(), true);
      write(tn +" "+en);
    }
    write(") {\r\n");
    write(indent+"    super();\r\n");
    for (ElementDefn e : params) {
      String en = getElementName(e.getName(), true);
      write(indent+"    this."+en+" = "+en+";\r\n");      
    }
    write(indent+"  }\r\n\r\n");
  }

  private String upFirst(String name) {
		return name.substring(0,1).toUpperCase()+name.substring(1);
	}
  
	private boolean hasList(ElementDefn root) {
		for (ElementDefn e : root.getElements()) {
			if (!e.getName().equals("text")) {
				if (e.unbounded() || hasListInner(e))
					return true;
			}
		}
		return false;
	}

  private boolean hasDecimal(ElementDefn root) {
    for (ElementDefn e : root.getElements()) {
      if (e.typeCode().equals("decimal") || hasDecimalInner(e))
        return true;
    }
    return false;
  }

  private boolean hasString(ElementDefn root) {
    for (ElementDefn e : root.getElements()) {
      if (e.typeCode().equals("string") || e.typeCode().equals("id") || e.typeCode().equals("code") || e.typeCode().equals("uri") || e.typeCode().equals("oid") || e.typeCode().equals("uuid") || hasString(e))
        return true;
    }
    return false;
  }

  private boolean hasSharedEnums(ElementDefn root) {
    for (ElementDefn e : root.getElements()) {
      if ((e.getBinding() != null && e.getBinding().isShared()) || hasSharedEnums(e))
        return true;
    } 
    return false;
  }

  private boolean hasXhtml(ElementDefn root) {
		for (ElementDefn e : root.getElements()) {
			if (e.isXhtmlElement() || hasXhtmlInner(e))
				return true;
		}
		return false;
	}

	private boolean hasListInner(ElementDefn e) {
		for (ElementDefn c : e.getElements()) {
			if (c.unbounded() || hasListInner(c))
				return true;
		}

		return false;
	}

	private boolean hasXhtmlInner(ElementDefn e) {
		for (ElementDefn c : e.getElements()) {
			if (c.isXhtmlElement() || hasXhtmlInner(c))
				return true;
		}

		return false;
	}

  private boolean hasDecimalInner(ElementDefn e) {
    for (ElementDefn c : e.getElements()) {
      if (c.typeCode().equals("decimal") || hasDecimalInner(c))
        return true;
    }

    return false;
  }

	private void generateEnum(ElementDefn e) throws Exception {
		String tn = typeNames.get(e);
		String tns = tn.substring(tn.indexOf("<")+1);
		tns = tns.substring(0, tns.length()-1);
		BindingSpecification cd = e.getBinding();
		if (cd.isShared())
		  return;
		cd.getValueSet().setUserData("java-generated", true);
		List<DefinedCode> codes = cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true);
		
		write("    public enum "+tns+" {\r\n");
		int l = codes.size();
		int i = 0;
		for (DefinedCode c : codes) {
			i++;
			String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      write("        /**\r\n");
      write("         * "+c.getDefinition()+"\r\n");
      write("         */\r\n");      
			write("        "+cc.toUpperCase()+", \r\n");
		}
    write("        /**\r\n");
    write("         * added to help the parsers with the generic types\r\n");
    write("         */\r\n");      
    write("        NULL;\r\n");


		write("        public static "+tns+" fromCode(String codeString) throws FHIRException {\r\n");
		write("            if (codeString == null || \"\".equals(codeString))\r\n");
		write("                return null;\r\n");
		for (DefinedCode c : codes) {
			String cc = Utilities.camelCase(c.getCode());
			cc = makeConst(cc);
			write("        if (\""+c.getCode()+"\".equals(codeString))\r\n");
			write("          return "+cc+";\r\n");
		}		
    write("        if (Configuration.isAcceptInvalidEnums())\r\n");
    write("          return null;\r\n");
    write("        else\r\n");
    write("          throw new FHIRException(\"Unknown "+tns+" code '\"+codeString+\"'\");\r\n");
		write("        }\r\n");	

		write("        public String toCode() {\r\n");
		write("          switch (this) {\r\n");
		for (DefinedCode c : codes) {
			String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
			write("            case "+cc+": return \""+c.getCode()+"\";\r\n");
		}   
		write("            default: return \"?\";\r\n");
		write("          }\r\n"); 
		write("        }\r\n"); 

    write("        public String getSystem() {\r\n");
    write("          switch (this) {\r\n");
    for (DefinedCode c : codes) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      write("            case "+cc+": return \""+c.getSystem()+"\";\r\n");
    }   
    write("            default: return \"?\";\r\n");
    write("          }\r\n"); 
    write("        }\r\n"); 

    write("        public String getDefinition() {\r\n");
    write("          switch (this) {\r\n");
    for (DefinedCode c : codes) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      write("            case "+cc+": return \""+Utilities.escapeJava(c.getDefinition())+"\";\r\n");
    }   
    write("            default: return \"?\";\r\n");
    write("          }\r\n"); 
    write("        }\r\n"); 

    write("        public String getDisplay() {\r\n");
    write("          switch (this) {\r\n");
    for (DefinedCode c : codes) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      write("            case "+cc+": return \""+Utilities.escapeJava(Utilities.noString(c.getDisplay()) ? c.getCode() : c.getDisplay())+"\";\r\n");
    }   
    write("            default: return \"?\";\r\n");
    write("          }\r\n"); 
    write("        }\r\n"); 

		write("    }\r\n");
		write("\r\n");

		
		write("  public static class "+tns+"EnumFactory implements EnumFactory<"+tns+"> {\r\n");
		write("    public "+tns+" fromCode(String codeString) throws IllegalArgumentException {\r\n");
		
		write("      if (codeString == null || \"\".equals(codeString))\r\n");
    write("            if (codeString == null || \"\".equals(codeString))\r\n");
    write("                return null;\r\n");
    for (DefinedCode c : codes) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      write("        if (\""+c.getCode()+"\".equals(codeString))\r\n");
      write("          return "+tns+"."+cc+";\r\n");
    }   
    write("        throw new IllegalArgumentException(\"Unknown "+tns+" code '\"+codeString+\"'\");\r\n");
    write("        }\r\n"); 
    write("        public Enumeration<"+tns+"> fromType(Base code) throws FHIRException {\r\n");
    write("          if (code == null || code.isEmpty())\r\n");
    write("            return null;\r\n");
    write("          String codeString = ((PrimitiveType) code).asStringValue();\r\n");
    write("          if (codeString == null || \"\".equals(codeString))\r\n");
    write("            return null;\r\n");
    for (DefinedCode c : codes) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      write("        if (\""+c.getCode()+"\".equals(codeString))\r\n");
      write("          return new Enumeration<"+tns+">(this, "+tns+"."+cc+");\r\n");
    }   
    write("        throw new FHIRException(\"Unknown "+tns+" code '\"+codeString+\"'\");\r\n");
    write("        }\r\n"); 

    write("    public String toCode("+tns+" code) {\r\n");
    for (DefinedCode c : codes) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      write("      if (code == "+tns+"."+cc+")\r\n        return \""+c.getCode()+"\";\r\n");
    }
    write("      return \"?\";\r\n"); 
    write("      }\r\n"); 
    
    write("    public String toSystem("+tns+" code) {\r\n");
    write("      return code.getSystem();\r\n");
    write("      }\r\n"); 
    write("    }\r\n"); 
    write("\r\n");
	}

  private void generateType(ElementDefn e, JavaGenClass clss) throws Exception {
		String tn = typeNames.get(e);

		if (clss == JavaGenClass.BackboneElement) {
      write("    @Block()\r\n");
	    write("    public static class "+tn+" extends BackboneElement implements IBaseBackboneElement {\r\n");
		} else {
      write("    @Block()\r\n");
		  write("    public static class "+tn+" extends Element implements IBaseDatatypeElement {\r\n");
		}
		allfields = "";
		int i = 1;
		for (ElementDefn c : e.getElements()) {
			generateField(e, c, "        ", i++);
		}
		write("        private static final long serialVersionUID = "+Long.toString(allfields.hashCode())+"L;\r\n\r\n");
    hashSum = hashSum + allfields.hashCode();

    List<ElementDefn> mandatory = new ArrayList<ElementDefn>();
    generateConstructor(tn, mandatory, "    ");
    for (ElementDefn c : e.getElements()) {
      if (c.isMandatory())
        mandatory.add(c);
    }
    if (mandatory.size() > 0)
      generateConstructor(tn, mandatory, "    ");
		
		for (ElementDefn c : e.getElements()) {
			generateAccessors(e, c, "        ", tn);
		}
		generateTypeSpecificAccessors(tn);
    generateChildrenRegister(e, "      ", false);
    generatePropertyGetterId(e, "    ");
    generatePropertySetterId(e, "    ");
    generatePropertySetterName(e, "    ");
    generatePropertyMaker(e, "    ");
    generateChildAdder(e, "    ", classname);
    generateCopy(e, tn, true, false);
    generateEquals(e, tn, true, false);
    generateIsEmpty(e, tn, true, false);
    generateFhirType(e.getPath());
    if (adornments.containsKey(tn)) {
      write("// added from java-adornments.txt:\r\n");
      write(adornments.get(tn)+"\r\n");
      write("// end addition\r\n");
    }
    write("  }\r\n");
		write("\r\n");

	}

  
  private void generateEquals(ElementDefn e, String tn, boolean owner, boolean isAbstract) throws Exception {
    write("      @Override\r\n");
    write("      public boolean equalsDeep(Base other) {\r\n");
    write("        if (!super.equalsDeep(other))\r\n");
    write("          return false;\r\n");
    write("        if (!(other instanceof "+tn+"))\r\n");
    write("          return false;\r\n");
    write("        "+tn+" o = ("+tn+") other;\r\n");
    write("        return ");
    boolean first = true;
    int col = 18;
    for (ElementDefn c : e.getElements()) {
      if (doGenerateField(c)) {
        if (first)
          first = false;
        else {
          write(" && ");
          col = col+4;
        }
        String name = getElementName(c.getName(), true);
        if (name.endsWith("[x]"))
          name = name.substring(0, name.length()-3);
        write("compareDeep("+name+", o."+name+", true)");
        col = col+21 + name.length()*2;
        if (col > 100) {
          col = 10;
          write("\r\n          ");
        }
      }
    }
    if (first)
      write("true"); 
    write(";\r\n");
    write("      }\r\n\r\n");  
    write("      @Override\r\n");
    write("      public boolean equalsShallow(Base other) {\r\n");
    write("        if (!super.equalsShallow(other))\r\n");
    write("          return false;\r\n");
    write("        if (!(other instanceof "+tn+"))\r\n");
    write("          return false;\r\n");
    write("        "+tn+" o = ("+tn+") other;\r\n");
    write("        return ");
    first = true;
    col = 18;
    for (ElementDefn c : e.getElements()) {
      if (doGenerateField(c)) {
        if (isJavaPrimitive(c)) {
          if (first)
            first = false;
          else {
            write(" && ");
            col = col+4;
          }
          String name = getElementName(c.getName(), true);
          if (name.endsWith("[x]"))
            name = name.substring(0, name.length()-3);
          write("compareValues("+name+", o."+name+", true)");
          col = col+21 + name.length()*2;
          if (col > 100) {
            col = 10;
            write("\r\n          ");
          }
        }
      }
    }
    if (first)
      write("true"); 
    write(";\r\n");
    write("      }\r\n\r\n");  
  }
  
	private void generateCopy(ElementDefn e, String tn, boolean owner, boolean isAbstract) throws Exception {
	  if (isAbstract) {
      write("      public abstract "+tn+" copy();\r\n\r\n");
      write("      public void copyValues("+tn+" dst) {\r\n");
      if (!e.getName().equals("Element") && !e.getName().equals("Resource"))
        write("        super.copyValues(dst);\r\n");
	  } else {
      write("      public "+tn+" copy() {\r\n");
      write("        "+tn+" dst = new "+tn+"();\r\n");
      write("        copyValues(dst);\r\n");
	  }
	  for (ElementDefn c : e.getElements()) {
	    if (doGenerateAccessors(c)) { 
	      String name = getElementName(c.getName(), true);
	      if (c.unbounded()) {
	        write("        if ("+name+" != null) {\r\n");
	        write("          dst."+name+" = new ArrayList<"+typeNames.get(c)+">();\r\n");
	        write("          for ("+typeNames.get(c)+" i : "+name+")\r\n");
	        write("            dst."+name+".add(i.copy());\r\n");
	        write("        };\r\n");
	      } else {
	        if (name.endsWith("[x]"))
	          name = name.substring(0, name.length()-3);
	        write("        dst."+name+" = "+name+" == null ? null : "+name+".copy();\r\n");
	      }
	    }
	  }
    if (!isAbstract) 
      write("        return dst;\r\n");
    write("      }\r\n\r\n");
    if (!owner && !isAbstract) {
      write("      protected "+tn+" typedCopy() {\r\n");
      write("        return copy();\r\n");
      write("      }\r\n\r\n");
      
    }
  }

  private void generateIsEmpty(ElementDefn e, String tn, boolean owner, boolean isAbstract) throws Exception {
    write("      public boolean isEmpty() {\r\n");
    write("        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(");
    int col = 70;
    boolean first = true;
    for (ElementDefn c : e.getElements()) {
      if (doGenerateField(c)) {
        if (first) {
          first = false;
        } else {
          write(", ");
        }
        col = col + 2;
        String name = getElementName(c.getName(), true);
        if (name.endsWith("[x]"))
          name = name.substring(0, name.length()-3);
        write(name);
        col = col + name.length() + 2;
        if (col > 100) {
          col = 10;
          write("\r\n          ");
        }
      }
    }
    write(");\r\n");
    write("      }\r\n\r\n");
  }

  private void scanNestedTypes(ElementDefn root, String path, ElementDefn e) throws Exception {
		String tn = null;
		if (e.typeCode().equals("code") && e.hasBinding()) {
			BindingSpecification cd = e.getBinding();
			if (cd != null && (cd.getBinding() == BindingSpecification.BindingMethod.CodeList)) {
				tn = getCodeListType(cd.getValueSet().getName());
				if (!enumNames.contains(tn)) {
					enumNames.add(tn);
					enums.add(e);
				}
				typeNames.put(e,  "Enumeration<"+tn+">");
			} else if (cd != null && (cd.getBinding() == BindingSpecification.BindingMethod.ValueSet && cd.getStrength() == BindingStrength.REQUIRED)) {
        tn = getCodeListType(cd.getName());
        if (!enumNames.contains(tn)) {
          enumNames.add(tn);
          enums.add(e);
        }
        typeNames.put(e,  "Enumeration<"+tn+">");
      }
		}
		if (tn == null) {
			if (e.getTypes().size() > 0 && !e.usesCompositeType()) {
				tn = e.typeCode();
//				if (clss != JavaGenClass.Resource) {
//					if (tn.equals("boolean")) tn = "Boolean";
//					else if (tn.equals("integer")) tn = "Integer";
//					else if (tn.equals("decimal")) tn = "Decimal";
//					else if (tn.equals("base64Binary")) tn = "Base64Binary";
//					else if (tn.equals("instant")) tn = "Instant";
//					else if (tn.equals("string")) tn = "StringType";
//          else if (tn.equals("uri")) tn = "Uri";
//          else if (tn.equals("xml:lang")) tn = "Code";
//					else if (tn.equals("code")) tn = "Code";
//					else if (tn.equals("oid")) tn = "Oid";
//          else if (tn.equals("uuid")) tn = "Uuid";
//					else if (tn.equals("sid")) tn = "Sid";
//					else if (tn.equals("id")) tn = "Id";
//					else if (tn.equals("date")) tn = "Date";
//					else if (tn.equals("dateTime")) tn = "DateTime";
//					else 
//						tn = getTypeName(e);
//				} else 
				tn = getTypeName(e);
				if (e.typeCode().equals("xml:lang"))
				  tn = "CodeType";
				if (e.getTypes().get(0).isUnboundGenericParam())
					tn = "T";
				else if (e.isXhtmlElement()) 
					tn = "XhtmlNode";
				else if (e.getTypes().get(0).isWildcardType())
					tn ="org.hl7.fhir.dstu3.model.Type";
				else if (definitions.hasPrimitiveType(tn))
				  tn = upFirst(tn)+"Type";

				typeNames.put(e,  tn);
			} else {
				if (e.usesCompositeType()) {
					tn = typeNames.get(getElementForPath(root, e.typeCode().substring(1)));
					typeNames.put(e,  tn);
//				} else if (e.getDeclaredTypeName() != null) {
//					tn = e.getDeclaredTypeName();
//					typeNames.put(e,  tn);
//					System.out.println(tn);
				} else {
					if (e.getDeclaredTypeName() != null) 
						tn = e.getDeclaredTypeName();
					else
						tn = getTitle(e.getName());
					if (tn.equals("Element"))
						tn = "Element_";
					strucs.add(e);
					if (typeNameStrings.contains(tn)) {
						char i = 'A';
						while (typeNameStrings.contains(tn+i))
							i++;
						tn = tn + i;
					}
					typeNames.put(e,  tn);
					typeNameStrings.add(tn);
					for (ElementDefn c : e.getElements()) {
						scanNestedTypes(root, path+getTitle(e.getName()), c);
					}
				}
			}
		}
	}

	private Object getElementForPath(ElementDefn root, String pathname) throws Exception {
		String[] path = pathname.split("\\.");
		if (!path[0].equals(root.getName()))
			throw new Exception("Element Path '"+pathname+"' is not legal in this context");
		ElementDefn res = root;
		for (int i = 1; i < path.length; i++)
		{
			String en = path[i];
			if (en.length() == 0)
				throw new Exception("Improper path "+pathname);
			ElementDefn t = res.getElementByName(definitions, en, true, false);
			if (t == null) {
				throw new Exception("unable to resolve "+pathname);
			}
			res = t; 
		}
		return res;

	}

	private String getCodeListType(String binding) {
		StringBuilder b = new StringBuilder();
		boolean up = true;
		for (char ch: binding.toCharArray()) {
			if (ch == '-')
				up = true;
			else if (up) {
				b.append(Character.toUpperCase(ch));
				up = false;
			}
			else				
				b.append(ch);
		}
		return b.toString();
	}


	private void generateField(ElementDefn root, ElementDefn e, String indent, int order) throws Exception {
		String tn = typeNames.get(e);

		if (e.unbounded()) {
		  jdoc(indent, e.getDefinition());
      writeAttributeAnnotation(indent, e, order, tn);
			if (tn == null && e.usesCompositeType())
				writeWithHash(indent+"protected List<"+root.getName()+"> "+getElementName(e.getName(), true)+";\r\n");
			else {
			  writeWithHash(indent+"protected List<"+tn+"> "+getElementName(e.getName(), true)+";\r\n");
	      if (e.getTypes().size() == 1 && e.typeCode().startsWith("Reference(")) {
	        List<String> params = e.getTypes().get(0).getParams();
	        String rn = params.size() == 1 ? params.get(0) : "Resource";
	        if (rn.equals("Any"))
	          rn = "Resource";
	        else if (rn.equals("List"))
            rn = "ListResource";
	        jdoc(indent, "The actual objects that are the target of the reference ("+e.getDefinition()+")");
	        writeWithHash(indent+"protected List<"+rn+"> "+getElementName(e.getName(), true)+"Target;\r\n");
	        write("\r\n");
	      }
			}
			write("\r\n");
		} else {
      jdoc(indent, e.getDefinition());
      writeAttributeAnnotation(indent, e, order, tn);
      writeWithHash(indent+"protected "+tn+" "+getElementName(e.getName(), true)+";\r\n");
			write("\r\n");
      if (e.getTypes().size() == 1 && e.typeCode().startsWith("Reference(")) {
        List<String> params = e.getTypes().get(0).getParams();
        String rn = params.size() == 1 ? params.get(0) : "Resource";
        if (rn.equals("Any"))
          rn = "Resource";
        else if (rn.equals("List"))
          rn = "ListResource";
        jdoc(indent, "The actual object that is the target of the reference ("+e.getDefinition()+")");
        writeWithHash(indent+"protected "+rn+" "+getElementName(e.getName(), true)+"Target;\r\n");
        write("\r\n");
      }
		}
	}

  private void writeAttributeAnnotation(String indent, ElementDefn e, int order, String tn) throws Exception {
    String elementName = getElementName(e.getName(), true);
    if (elementName.endsWith("_")) {
      // The annotation doesn't need trailing _
      elementName = elementName.substring(0, elementName.length() - 1);
    }
    
    StringBuilder childB = new StringBuilder();
    childB.append(indent);
    childB.append("@Child(name = \"");
    childB.append(elementName);
    childB.append("\", type = {");
    childB.append(getTypeClassList(e, tn));
    childB.append("}, order=");
    childB.append(Integer.toString(order));
    childB.append(", min=");
    childB.append(e.getMinCardinality().toString());
    childB.append(", max=");
    childB.append((e.getMaxCardinality() == Integer.MAX_VALUE ?  "Child.MAX_UNLIMITED" : e.getMaxCardinality().toString()));
    childB.append(", modifier=");
    childB.append(e.isModifier());
    childB.append(", summary=");
    childB.append(e.isSummary());
    childB.append(")\r\n");
    write(childB.toString());
    
    write(indent+"@Description(shortDefinition=\""+Utilities.escapeJava(e.getShortDefn())+"\", formalDefinition=\""+Utilities.escapeJava(e.getDefinition())+"\" )\r\n");
    
    if (HAPI_16) {
      if (e.getBinding() != null) {
        if (e.getBinding().getValueSet() != null && isNotBlank(e.getBinding().getValueSet().getUrl())) {
          write(indent+"@ca.uhn.fhir.model.api.annotation.Binding(valueSet=\"" + e.getBinding().getValueSet().getUrl() + "\")\r\n");
        }
      }
    }
    
  }


  private String getTypeClassList(ElementDefn e, String tn) throws Exception {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (TypeRef tr : e.getTypes()) {
      if (tr.isResourceReference()) {
        for (String p : tr.getParams())
          if (p.equalsIgnoreCase("Any")) {
            b.append("Reference.class");
          } else {
            if (p.equals("List"))
              b.append(p+"Resource.class");
            else
              b.append(p+".class");
          }
      } else if (definitions.hasPrimitiveType(tr.getName())) {
        b.append(upFirst(tr.getName())+"Type.class");
      } else if (tr.getName().startsWith("@")){
        b.append(tn+".class");
      } else if (!tr.getName().equalsIgnoreCase("*") && !tr.getName().equalsIgnoreCase("xhtml")) {
        b.append(getTypename(tr)+".class");
      }
    }
    return b.toString();
  }

  private void writeWithHash(String string) throws IOException {
    allfields = allfields + string;
    write(string);
  }

  private String getSimpleType(String n) {
    if (n.equals("StringType"))
      return "String";
    if (n.equals("CodeType"))
      return "String";
    if (n.equals("MarkdownType"))
      return "String";
    if (n.equals("Base64BinaryType"))
      return "byte[]";
    if (n.equals("UriType"))
      return "String";
    if (n.equals("OidType"))
      return "String";
    if (n.equals("IntegerType"))
      return "int";
    if (n.equals("UnsignedIntType"))
      return "int";
    if (n.equals("PositiveIntType"))
      return "int";
    if (n.equals("BooleanType"))
      return "boolean";
    if (n.equals("DecimalType"))
      return "BigDecimal";
    if (n.equals("DateTimeType"))
      return "Date";
    if (n.equals("DateType"))
      return "Date";
    if (n.equals("IdType"))
      return "String";
    if (n.equals("InstantType"))
      return "Date";
    if (n.equals("TimeType"))
      return "String";
    
    String tns = null;
    if (n.indexOf("<") > 0) {
      tns = n.substring(n.indexOf("<")+1);
      tns = tns.substring(0, tns.length()-1);
    }

    if (tns != null && enumNames.contains(tns))
      return tns;
    
    return "??";
  }
	private void generateAccessors(ElementDefn root, ElementDefn e, String indent, String className) throws Exception {
		String tn = typeNames.get(e);

		boolean isReferenceRefField = (root.getName().equals("Reference") && e.getName().equals("reference"));
		
		String simpleType = getSimpleType(tn);
		if (e.unbounded()) {
		  /*
		   * getXXX()for repeatable type
		   */
		  jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
		  String listGenericType;
		  if (tn == null && e.usesCompositeType()) {
		    listGenericType = root.getName();
		  } else {
		    listGenericType = tn;
		  }
		  write(indent+"public List<"+listGenericType+"> get"+getTitle(getElementName(e.getName(), false))+"() { \r\n");
		  write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
		  write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+listGenericType+">();\r\n");
		  write(indent+"  return this."+getElementName(e.getName(), true)+";\r\n");
		  write(indent+"}\r\n\r\n");

		  /*
		   * setXXX(List<foo>) for repeating type
		   */
		  jdoc(indent, "@return Returns a reference to <code>this</code> for easy method chaining");
		  write(indent+"public " + className + " set"+getTitle(getElementName(e.getName(), false))+"(" + "List<"+listGenericType+"> the" + getTitle(getElementName(e.getName(), false)) + ") { \r\n");
		  write(indent+"  this."+getElementName(e.getName(), true)+" = the" + getTitle(getElementName(e.getName(), false)) + ";\r\n");
		  write(indent+"  return this;\r\n");
		  write(indent+"}\r\n\r\n");

		  /*
		   * hasXXX() for repeatable type
		   */
		  write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"() { \r\n");
		  write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
		  write(indent+"    return false;\r\n");
		  write(indent+"  for ("+tn+" item : this."+getElementName(e.getName(), true)+")\r\n");
		  write(indent+"    if (!item.isEmpty())\r\n");
		  write(indent+"      return true;\r\n");
		  write(indent+"  return false;\r\n");
		  write(indent+"}\r\n");

		  write("\r\n");
		  if (e.getTypes().size() == 1 && (definitions.getPrimitives().containsKey(e.typeCode()) || e.typeCode().equals("xml:lang"))) {
		    /*
		     * addXXXElement() for repeatable primitive
		     */
		    jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
		    write(indent+"public "+tn+" add"+getTitle(getElementName(e.getName(), false))+"Element() {//2 \r\n");
		    write(indent+"  "+tn+" t = new "+tn+"("+( tn.startsWith("Enum") ? "new "+tn.substring(12, tn.length()-1)+"EnumFactory()" : "")+");\r\n");
		    write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
		    write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+tn+">();\r\n");
		    write(indent+"  this."+getElementName(e.getName(), true)+".add(t);\r\n");
		    write(indent+"  return t;\r\n");
		    write(indent+"}\r\n");
		    write("\r\n");

		    /*
		     * addXXX(foo) for repeatable primitive
		     */
		    jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
		    write(indent+"public "+className+" add"+getTitle(getElementName(e.getName(), false))+"("+simpleType+" value) { //1\r\n");
		    write(indent+"  "+tn+" t = new "+tn+"("+( tn.startsWith("Enum") ? "new "+tn.substring(12, tn.length()-1)+"EnumFactory()" : "")+");\r\n");
		    write(indent+"  t.setValue(value);\r\n");
		    write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
		    write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+tn+">();\r\n");
		    write(indent+"  this."+getElementName(e.getName(), true)+".add(t);\r\n");
		    write(indent+"  return this;\r\n");
		    write(indent+"}\r\n");
		    write("\r\n");

		    /*
		     * hasXXX(foo) for repeatable primitive
		     */
		    jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
		    write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"("+simpleType+" value) { \r\n");
		    write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
		    write(indent+"    return false;\r\n");
		    write(indent+"  for ("+tn+" v : this."+getElementName(e.getName(), true)+")\r\n");
		    if (isJavaPrimitive(e) && !tn.startsWith("Enum")) 
		      write(indent+"    if (v.equals(value)) // "+e.typeCode()+"\r\n");
		    else
		      write(indent+"    if (v.getValue().equals(value)) // "+e.typeCode()+"\r\n");
		    write(indent+"      return true;\r\n");
		    write(indent+"  return false;\r\n");
		    write(indent+"}\r\n");
		    write("\r\n");
		  } else {
		    if (!definitions.getBaseResources().containsKey(tn)) {
		      /*
		       * addXXX() for repeatable composite
		       */
		      write(indent+"public "+tn+" add"+getTitle(getElementName(e.getName(), false))+"() { //3\r\n");
		      write(indent+"  "+tn+" t = new "+tn+"();\r\n");
		      write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
		      write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+tn+">();\r\n");
		      write(indent+"  this."+getElementName(e.getName(), true)+".add(t);\r\n");
		      write(indent+"  return t;\r\n");
		      write(indent+"}\r\n");
		      write("\r\n");

		      /*
		       * addXXX(foo) for repeatable composite
		       */
		      write(indent+"public "+className+" add"+getTitle(getElementName(e.getName(), false))+"("+tn+" t) { //3\r\n");
		      write(indent+"  if (t == null)\r\n");
		      write(indent+"    return this;\r\n");
		      write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
		      write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+tn+">();\r\n");
		      write(indent+"  this."+getElementName(e.getName(), true)+".add(t);\r\n");
		      write(indent+"  return this;\r\n");
		      write(indent+"}\r\n");
		      write("\r\n");
		    } else {
		      /*
		       * addXXX(foo) for repeatable composite
		       */
		      write(indent+"public "+className+" add"+getTitle(getElementName(e.getName(), false))+"("+tn+" t) { //3\r\n");
		      write(indent+"  if (t == null)\r\n");
		      write(indent+"    return this;\r\n");
		      write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
		      write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+tn+">();\r\n");
		      write(indent+"  this."+getElementName(e.getName(), true)+".add(t);\r\n");
		      write(indent+"  return this;\r\n");
		      write(indent+"}\r\n");
		      write("\r\n");          
		    }

		    /*
		     * getXXXFirstRep() for repeatable element
		     */
		    if (!"DomainResource".equals(className)) {
		      jdoc(indent, "@return The first repetition of repeating field {@link #"+getElementName(e.getName(), true)+"}, creating it if it does not already exist");
		      write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"FirstRep() { \r\n");
		      write(indent+"  if (get"+getTitle(getElementName(e.getName(), false))+"().isEmpty()) {\r\n");
		      if ((definitions.getPrimitives().containsKey(e.typeCode()))) {
		        write(indent+"    add" + getTitle(getElementName(e.getName(), false)) + "Element();\r\n");
		      } else {
		        write(indent+"    add" + getTitle(getElementName(e.getName(), false)) + "();\r\n");
		      }
		      write(indent+"  }\r\n");
		      write(indent+"  return get"+getTitle(getElementName(e.getName(), false))+"().get(0);\r\n");
		      write(indent+"}\r\n\r\n");
		    }

		    //TODO: remove this - Reference type has a field to hold these things too and 
		    // that's cleaner. Deprecated by JA on 2016-05-28
		    if (e.getTypes().size() == 1 && e.typeCode().startsWith("Reference(")) {
		      List<String> params = e.getTypes().get(0).getParams();
		      String rn = params.size() == 1 ? params.get(0) : "Resource";
		      if (rn.equals("Any"))
		        rn = "Resource";
		      else if (rn.equals("List"))
		        rn = "ListResource";
		      jdoc(indent, "@deprecated Use Reference#setResource(IBaseResource) instead");
		      write(indent+"@Deprecated\r\n");
		      write(indent+"public List<"+rn+"> get"+getTitle(getElementName(e.getName(), false))+"Target() { \r\n");
		      write(indent+"  if (this."+getElementName(e.getName(), true)+"Target == null)\r\n");
		      write(indent+"    this."+getElementName(e.getName(), true)+"Target = new ArrayList<"+rn+">();\r\n");
		      write(indent+"  return this."+getElementName(e.getName(), true)+"Target;\r\n");
		      write(indent+"}\r\n");
		      write("\r\n");
		      if (!rn.equals("Resource")) {
		        jdoc(indent, "@deprecated Use Reference#setResource(IBaseResource) instead");
		        write(indent+"@Deprecated\r\n");
		        write(indent+"public "+rn+" add"+getTitle(getElementName(e.getName(), false))+"Target() { \r\n");
		        write(indent+"  "+rn+" r = new "+rn+"();\r\n");
		        write(indent+"  if (this."+getElementName(e.getName(), true)+"Target == null)\r\n");
		        write(indent+"    this."+getElementName(e.getName(), true)+"Target = new ArrayList<"+rn+">();\r\n");
		        write(indent+"  this."+getElementName(e.getName(), true)+"Target.add(r);\r\n");
		        write(indent+"  return r;\r\n");
		        write(indent+"}\r\n");
		        write("\r\n");
		      }
		    }
		  }
		} else {
      if (isJavaPrimitive(e)) {
        jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+"). This is the underlying object with id, value and extensions. The accessor \"get"+getTitle(getElementName(e.getName(), false))+"\" gives direct access to the value");
        if (isReferenceRefField) {
          /*
           * Reference#getReferenceElement is defined differently in BaseReference.java?
           */
          write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"Element_() { \r\n");
          write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
          write(indent+"    if (Configuration.errorOnAutoCreate())\r\n");
          write(indent+"      throw new Error(\"Attempt to auto-create "+className+"."+getElementName(e.getName(), true)+"\");\r\n");
          write(indent+"    else if (Configuration.doAutoCreate())\r\n");
          write(indent+"      this."+getElementName(e.getName(), true)+" = new "+tn+"("+( tn.startsWith("Enum") ? "new "+tn.substring(12, tn.length()-1)+"EnumFactory()" : "")+"); // bb\r\n");
          write(indent+"  return this."+getElementName(e.getName(), true)+";\r\n");
          write(indent+"}\r\n");
        } else { 
          write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"Element() { \r\n");
          write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
          write(indent+"    if (Configuration.errorOnAutoCreate())\r\n");
          write(indent+"      throw new Error(\"Attempt to auto-create "+className+"."+getElementName(e.getName(), true)+"\");\r\n");
          write(indent+"    else if (Configuration.doAutoCreate())\r\n");
          write(indent+"      this."+getElementName(e.getName(), true)+" = new "+tn+"("+( tn.startsWith("Enum") ? "new "+tn.substring(12, tn.length()-1)+"EnumFactory()" : "")+"); // bb\r\n");
          write(indent+"  return this."+getElementName(e.getName(), true)+";\r\n");
          write(indent+"}\r\n");
        }
        write("\r\n");

        write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"Element() { \r\n");
        write(indent+"  return this."+getElementName(e.getName(), true)+" != null && !this."+getElementName(e.getName(), true)+".isEmpty();\r\n");
        write(indent+"}\r\n");
        write("\r\n");
        write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"() { \r\n");
        write(indent+"  return this."+getElementName(e.getName(), true)+" != null && !this."+getElementName(e.getName(), true)+".isEmpty();\r\n");
        write(indent+"}\r\n");
        write("\r\n");
        jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+"). This is the underlying object with id, value and extensions. The accessor \"get"+getTitle(getElementName(e.getName(), false))+"\" gives direct access to the value");
        write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"Element("+tn+" value) { \r\n");
        write(indent+"  this."+getElementName(e.getName(), true)+" = value;\r\n");
        write(indent+"  return this;\r\n");
        write(indent+"}\r\n");
        write("\r\n");
        jdoc(indent, "@return "+e.getDefinition());
        write(indent+"public "+simpleType+" get"+getTitle(getElementName(e.getName(), false))+"() { \r\n");
        if (e.typeCode().equals("boolean"))
          write(indent+"  return this."+getElementName(e.getName(), true)+" == null || this."+getElementName(e.getName(), true)+".isEmpty() ? false : this."+getElementName(e.getName(), true)+".getValue();\r\n");
        else if (e.typeCode().equals("integer") || e.typeCode().equals("unsignedInt") || e.typeCode().equals("positiveInt"))
          write(indent+"  return this."+getElementName(e.getName(), true)+" == null || this."+getElementName(e.getName(), true)+".isEmpty() ? 0 : this."+getElementName(e.getName(), true)+".getValue();\r\n");
        else
          write(indent+"  return this."+getElementName(e.getName(), true)+" == null ? null : this."+getElementName(e.getName(), true)+".getValue();\r\n");
        write(indent+"}\r\n");
        write("\r\n");
	      generateSetter(e, indent, className, tn, simpleType);

	      // BigDecimal sugar methods 
	      if (simpleType.equals("BigDecimal")) {
          generateSetter(e, indent, className, tn, "long");
	        generateSetter(e, indent, className, tn, "double");
	      }
//	      // code sugar methods
//	      if (e.typeCode().equals("code")) {
//          jdoc(indent, "@return a string code value for "+e.getDefinition());
//          write(indent+"public "+simpleType+" get"+getTitle(getElementName(e.getName(), false))+"AsCode() { \r\n");
//          write(indent+"  return this."+getElementName(e.getName(), true)+" == null ? null : this."+getElementName(e.getName(), true)+".getValue();\r\n");
//          write(indent+"}\r\n");
//          write("\r\n");
//          
//          jdoc(indent, "@param value String value for "+e.getDefinition());
//          write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"AsCode(String value) throws FHIRException { \r\n");
//          write(indent+"  if (!Utilities.noString(value)) \r\n");
//          write(indent+"    this."+getElementName(e.getName(), true)+" = null;\r\n");
//          write(indent+"  else {\r\n");
//          write(indent+"    if (this."+getElementName(e.getName(), true)+" == null)\r\n");
//          write(indent+"      this."+getElementName(e.getName(), true)+" = new "+tn+"("+( tn.startsWith("Enum") ? "new "+tn.substring(12, tn.length()-1)+"EnumFactory()" : "")+");\r\n");
//          write(indent+"    this."+getElementName(e.getName(), true)+".setValue("+(tn.startsWith("Enum") ? tn.substring(12, tn.length()-1)+".fromCode(value)" : "value")+");\r\n");
//          write(indent+"  }\r\n");
//          write(indent+"  return this;\r\n");
//          write(indent+"}\r\n");
//          write("\r\n");
//          
//        }
	      
      } else {
        jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
        write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"() { \r\n");
        if (!tn.equals("Resource") && !tn.equals("Type") && !tn.endsWith(".Type")) {
          write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
          write(indent+"    if (Configuration.errorOnAutoCreate())\r\n");
          write(indent+"      throw new Error(\"Attempt to auto-create "+className+"."+getElementName(e.getName(), true)+"\");\r\n");
          write(indent+"    else if (Configuration.doAutoCreate())\r\n");
          write(indent+"      this."+getElementName(e.getName(), true)+" = new "+tn+"(); // cc\r\n");
        }
        write(indent+"  return this."+getElementName(e.getName(), true)+";\r\n");
        write(indent+"}\r\n");
        write("\r\n");
        if (e.getTypes().size() > 1 && (tn.equals("Type") || !tn.endsWith(".Type"))) {
          for (TypeRef t : e.getTypes()) {
            jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
            String ttn = getTypename(t);
            write(indent+"public "+ttn+" get"+getTitle(getElementName(e.getName(), false))+ttn+"() throws FHIRException { \r\n");
            write(indent+"  if (!(this."+getElementName(e.getName(), true)+" instanceof "+ttn+"))\r\n");
            write(indent+"    throw new FHIRException(\"Type mismatch: the type "+ttn+" was expected, but \"+this."+getElementName(e.getName(), true)+".getClass().getName()+\" was encountered\");\r\n");
            write(indent+"  return ("+ttn+") this."+getElementName(e.getName(), true)+";\r\n");
            write(indent+"}\r\n");
            write("\r\n");
            write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+ttn+"() { \r\n");
            write(indent+"  return this."+getElementName(e.getName(), true)+" instanceof "+ttn+";\r\n");
            write(indent+"}\r\n");
            write("\r\n");
          }
        }
        write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"() { \r\n");
        write(indent+"  return this."+getElementName(e.getName(), true)+" != null && !this."+getElementName(e.getName(), true)+".isEmpty();\r\n");
        write(indent+"}\r\n");
        write("\r\n");
        jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
        write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"("+tn+" value) { \r\n");
        write(indent+"  this."+getElementName(e.getName(), true)+" = value;\r\n");
        write(indent+"  return this;\r\n");
        write(indent+"}\r\n");
        write("\r\n");
        if (e.getTypes().size() == 1 && e.typeCode().startsWith("Reference(")) {
          List<String> params = e.getTypes().get(0).getParams();
          String rn = params.size() == 1 ? params.get(0) : "Resource";
          if (rn.equals("Any"))
            rn = "Resource";
          else if (rn.equals("List"))
            rn = "ListResource";
          jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. ("+e.getDefinition()+")");
          write(indent+"public "+rn+" get"+getTitle(getElementName(e.getName(), false))+"Target() { \r\n");
          if (!rn.equals("Resource")) {
            write(indent+"  if (this."+getElementName(e.getName(), true)+"Target == null)\r\n");
            write(indent+"    if (Configuration.errorOnAutoCreate())\r\n");
            write(indent+"      throw new Error(\"Attempt to auto-create "+className+"."+getElementName(e.getName(), true)+"\");\r\n");
            write(indent+"    else if (Configuration.doAutoCreate())\r\n");
            write(indent+"      this."+getElementName(e.getName(), true)+"Target = new "+rn+"(); // aa\r\n");
          }
          write(indent+"  return this."+getElementName(e.getName(), true)+"Target;\r\n");
          write(indent+"}\r\n");
          write("\r\n");
			    jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. ("+e.getDefinition()+")");
			    write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"Target("+rn+" value) { \r\n");
			    write(indent+"  this."+getElementName(e.getName(), true)+"Target = value;\r\n");
			    write(indent+"  return this;\r\n");
			    write(indent+"}\r\n");
			    write("\r\n");
			  }			  
			}
		}

	}

  private void generateSetter(ElementDefn e, String indent, String className, String tn, String simpleType) throws IOException {
    jdoc(indent, "@param value "+e.getDefinition());
    write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"("+simpleType+" value) { \r\n");
    if ("long".equals(simpleType) || "double".equals(simpleType)) {
      write(indent+"      this."+getElementName(e.getName(), true)+" = new "+tn+"("+( tn.startsWith("Enum") ? "new "+tn.substring(12, tn.length()-1)+"EnumFactory()" : "")+");\r\n");
      write(indent+"    this."+getElementName(e.getName(), true)+".setValue(value);\r\n");      
    } else {
      if (e.getMinCardinality() == 0 && !tn.equals("IntegerType") && !tn.equals("PositiveIntType") && !tn.equals("UnsignedIntType") && !tn.equals("BooleanType")) {
        if (isString(tn))
          write(indent+"  if (Utilities.noString(value))\r\n");
        else
          write(indent+"  if (value == null)\r\n");
        write(indent+"    this."+getElementName(e.getName(), true)+" = null;\r\n");
        write(indent+"  else {\r\n");
      }
      write(indent+"    if (this."+getElementName(e.getName(), true)+" == null)\r\n");
      write(indent+"      this."+getElementName(e.getName(), true)+" = new "+tn+"("+( tn.startsWith("Enum") ? "new "+tn.substring(12, tn.length()-1)+"EnumFactory()" : "")+");\r\n");
      write(indent+"    this."+getElementName(e.getName(), true)+".setValue(value);\r\n");
      if (e.getMinCardinality() == 0 && !tn.equals("IntegerType") && !tn.equals("PositiveIntType") && !tn.equals("UnsignedIntType") && !tn.equals("BooleanType")) {
        write(indent+"  }\r\n");
      }
    }
    write(indent+"  return this;\r\n");
    write(indent+"}\r\n");
    write("\r\n");
  }

  private boolean isString(String tn) {
    return tn.equals("StringType") || tn.equals("CodeType") || tn.equals("IdType") || tn.equals("UriType") || tn.equals("OidType") || tn.equals("UuidType");
  }

  public long getHashSum() {
    return hashSum;
  }

  public void setInheritedHash(String value) {
    inheritedHash = value;
    
  }


}
