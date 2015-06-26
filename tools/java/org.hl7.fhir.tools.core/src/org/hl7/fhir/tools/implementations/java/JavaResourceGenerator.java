package org.hl7.fhir.tools.implementations.java;
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

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;

/*
changes for James
- lazy construction of lists
- getX will construct if null
- add hasX
  
*/
public class JavaResourceGenerator extends JavaBaseGenerator {

	public enum JavaGenClass { Structure, Type, Resource, BackboneElement, Constraint }
	private JavaGenClass clss;
	
	public JavaResourceGenerator(OutputStream out, Definitions definitions) throws UnsupportedEncodingException {
		super(out);
		this.definitions = definitions;
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

	public void generate(ElementDefn root, String name, JavaGenClass clss, ProfiledType cd, Date genDate, String version, boolean isAbstract, Map<String, SearchParameterDefn> map) throws Exception {
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
		
		write("package org.hl7.fhir.instance.model;\r\n");
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
          write("import org.hl7.fhir.instance.model.Enumerations.*;\r\n");
      }
      if (clss == JavaGenClass.Resource) {
        write("import org.hl7.fhir.instance.model.annotations.ResourceDef;\r\n");
        write("import org.hl7.fhir.instance.model.annotations.SearchParamDefinition;\r\n");
      } 
      write("import org.hl7.fhir.instance.model.annotations.Child;\r\n");
      write("import org.hl7.fhir.instance.model.annotations.Description;\r\n");
    }
    if (clss != JavaGenClass.Resource) {
      write("import org.hl7.fhir.instance.model.annotations.DatatypeDef;\r\n");
    }
    write("import org.hl7.fhir.instance.model.annotations.Block;\r\n");
    write("import org.hl7.fhir.instance.model.api.*;\r\n");
    
		jdoc("", root.getDefinition());
		classname = upFirst(name);
		if (clss == JavaGenClass.Resource) {
		  
		  if (!isAbstract) {
		    write("@ResourceDef(name=\""+upFirst(name).replace("_", "")+"\", profile=\"http://hl7.org/fhir/Profile/"+upFirst(name)+"\")\r\n");
		  }
		  
			String hierarchy;
      if (Utilities.noString(root.typeCode())) {
        hierarchy = "BaseResource implements IAnyResource";
      } else if ("Bundle".equals(upFirst(name))) {
        hierarchy = root.typeCode() + " implements IBaseBundle";
      } else if ("Parameters".equals(upFirst(name))) {
        hierarchy = root.typeCode() + " implements IBaseParameters";
      } else if ("DomainResource".equals(upFirst(name))) {
        hierarchy = root.typeCode() + " implements IBaseHasExtensions, IBaseHasModifierExtensions, IDomainResource";        
      } else if ("Binary".equals(upFirst(name))) {
        hierarchy = "BaseBinary implements IBaseBinary";        
      } else {
        hierarchy = root.typeCode();
      }
      write("public "+(isAbstract? "abstract " : "")+"class "+upFirst(name)+" extends "+hierarchy+" ");
    } else if (clss == JavaGenClass.Structure && upFirst(name).equals("Element")) {
      write("public abstract class "+upFirst(name)+" extends Base implements IBaseHasExtensions ");
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
      write("@DatatypeDef(name=\""+upFirst(name)+"\")\r\n");
			write("public class "+upFirst(cd.getName())+" extends "+upFirst(root.getName())+" ");
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
			write("public class "+upFirst(name)+" extends Type implements ICompositeType ");
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
				generateField(root, e, "    ", i++);
			}
	    write("    private static final long serialVersionUID = "+Long.toString(allfields.hashCode())+"L;\r\n\r\n");
	    hashSum = hashSum + allfields.hashCode();

			List<ElementDefn> mandatory = new ArrayList<ElementDefn>();
			generateConstructor(upFirst(name), mandatory, "  ");      
			for (ElementDefn e : root.getElements()) {
        if (e.isMandatory())
          mandatory.add(e);
      }
	    if (mandatory.size() > 0)
	      generateConstructor(upFirst(name), mandatory, "  ");

	    generateTypeSpecificConstructors(isRefType);
	    
			for (ElementDefn e : root.getElements()) {
  			generateAccessors(root, e, "    ", upFirst(name));
			}
			
			generateTypeSpecificAccessors(name, clss);
			
			generateChildrenRegister(root, "    ", isAbstract);
		} else
      write("    private static final long serialVersionUID = "+inheritedHash+"L;\r\n\r\n");

		generateCopy(root, classname, false, isAbstract);
		generateEquals(root, classname, false, isAbstract);
		generateIsEmpty(root, classname, false, isAbstract);

		if (clss == JavaGenClass.Resource && !isAbstract) {
		  write("  @Override\r\n");
		  write("  public ResourceType getResourceType() {\r\n");
		  write("    return ResourceType."+root.getName()+";\r\n");
		  write("   }\r\n");
		  write("\r\n"); 
		} else if (isAbstract && Utilities.noString(root.typeCode()) && clss != JavaGenClass.Structure) {
		  write("  public abstract ResourceType getResourceType();\r\n");
		}
		
		
		if (map != null) {
		  for (SearchParameterDefn sp : map.values()) {
		    write("  @SearchParamDefinition(name=\""+sp.getCode()+"\", path=\""+pipeSeparate(sp.getPaths())+"\", description=\""+Utilities.escapeJava(sp.getDescription())+"\", type=\""+sp.getType().toString()+"\" )\r\n");
		    write("  public static final String SP_"+clean(sp.getCode()).toUpperCase()+" = \""+sp.getCode()+"\";\r\n");
		  }
		}
		write("\r\n");
		write("}\r\n");
		write("\r\n");
		flush();
	}

  private void generateTypeSpecificConstructors(boolean isRefType) throws IOException {
    //@formatter:off
    if (isRefType) {
      write("    /**\r\n" + 
          "     * Constructor\r\n" + 
          "     * \r\n" + 
          "     * @param theReference The given reference string (e.g. \"Patient/123\" or \"http://example.com/Patient/123\")\r\n" + 
          "     */\r\n" + 
          "    public Reference(String theReference) {\r\n" + 
          "      super(theReference);\r\n" + 
          "    }\r\n" + 
          "\r\n" + 
          "    /**\r\n" + 
          "     * Constructor\r\n" + 
          "     * \r\n" + 
          "     * @param theReference The given reference as an IdType (e.g. \"Patient/123\" or \"http://example.com/Patient/123\")\r\n" + 
          "     */\r\n" + 
          "    public Reference(IdType theReference) {\r\n" + 
          "      super(theReference);\r\n" + 
          "    }\r\n" + 
          "\r\n" + 
          "    /**\r\n" + 
          "     * Constructor\r\n" + 
          "     * \r\n" + 
          "     * @param theResource The resource represented by this reference\r\n" + 
          "     */\r\n" + 
          "    public Reference(IAnyResource theResource) {\r\n" + 
          "      super(theResource);\r\n" + 
          "    }\r\n" + 
          "\r\n");
    }
    //@formatter:on
  }

  private void generateTypeSpecificAccessors(String name, JavaGenClass clss) throws IOException {
    if (clss == JavaGenClass.Resource && upFirst(name).equals("Bundle")) {
      //@formatter:off
		  write(" /**\r\n" + 
		      "   * Returns the {@link #getLink() link} which matches a given {@link BundleLinkComponent#getRelation() relation}. \r\n" + 
		      "   * If no link is found which matches the given relation, returns <code>null</code>. If more than one\r\n" + 
		      "   * link is found which matches the given relation, returns the first matching BundleLinkComponent.\r\n" + 
		      "   * \r\n" + 
		      "   * @param theRelation\r\n" + 
		      "   *            The relation, such as \"next\", or \"self. See the constants such as {@link IBaseBundle#LINK_SELF} and {@link IBaseBundle#LINK_NEXT}.\r\n" + 
		      "   * @return Returns a matching BundleLinkComponent, or <code>null</code>\r\n" + 
		      "   * @see IBaseBundle#LINK_NEXT\r\n" + 
		      "   * @see IBaseBundle#LINK_PREV\r\n" + 
		      "   * @see IBaseBundle#LINK_SELF\r\n" + 
		      "   */\r\n" + 
		      "  public BundleLinkComponent getLink(String theRelation) {\r\n" + 
		      "    org.apache.commons.lang3.Validate.notBlank(theRelation, \"theRelation may not be null or empty\");\r\n" + 
		      "    for (BundleLinkComponent next : getLink()) {\r\n" + 
		      "      if (theRelation.equals(next.getRelation())) {\r\n" + 
		      "        return next;\r\n" + 
		      "      }\r\n" + 
		      "    }\r\n" + 
		      "    return null;\r\n" + 
		      "  }\r\n" + 
		      "\r\n" + 
		      "  /**\r\n" + 
		      "   * Returns the {@link #getLink() link} which matches a given {@link BundleLinkComponent#getRelation() relation}. \r\n" + 
		      "   * If no link is found which matches the given relation, creates a new BundleLinkComponent with the\r\n" + 
		      "   * given relation and adds it to this Bundle. If more than one\r\n" + 
		      "   * link is found which matches the given relation, returns the first matching BundleLinkComponent.\r\n" + 
		      "   * \r\n" + 
		      "   * @param theRelation\r\n" + 
		      "   *            The relation, such as \"next\", or \"self. See the constants such as {@link IBaseBundle#LINK_SELF} and {@link IBaseBundle#LINK_NEXT}.\r\n" + 
		      "   * @return Returns a matching BundleLinkComponent, or <code>null</code>\r\n" + 
		      "   * @see IBaseBundle#LINK_NEXT\r\n" + 
		      "   * @see IBaseBundle#LINK_PREV\r\n" + 
		      "   * @see IBaseBundle#LINK_SELF\r\n" + 
		      "   */\r\n" + 
		      "  public BundleLinkComponent getLinkOrCreate(String theRelation) {\r\n" + 
		      "    org.apache.commons.lang3.Validate.notBlank(theRelation, \"theRelation may not be null or empty\");\r\n" + 
		      "    for (BundleLinkComponent next : getLink()) {\r\n" + 
		      "      if (theRelation.equals(next.getRelation())) {\r\n" + 
		      "        return next;\r\n" + 
		      "      }\r\n" + 
		      "    }\r\n" + 
		      "    BundleLinkComponent retVal = new BundleLinkComponent();\r\n" + 
		      "    retVal.setRelation(theRelation);\r\n" + 
		      "    getLink().add(retVal);\r\n" + 
		      "    return retVal;\r\n" + 
		      "  }\r\n" + 
		      "");
		  //@formatter:on
		}
  }

  private String clean(String code) {
    StringBuilder b = new StringBuilder();
    for (char c : code.toCharArray())
      if (Character.isLetter(c))
        b.append(c);
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

	private void generateChildrenRegister(ElementDefn p, String indent, boolean isAbstract) throws IOException {
	  write(indent+"  protected void listChildren(List<Property> childrenList) {\r\n");
	  if (!isAbstract)
	    write(indent+"    super.listChildren(childrenList);\r\n");
	  for (ElementDefn e : p.getElements()) {
	    if (!e.typeCode().equals("xhtml"))
	      write(indent+"    childrenList.add(new Property(\""+e.getName()+"\", \""+e.typeCode()+"\", \""+Utilities.escapeJava(e.getDefinition())+"\", 0, java.lang.Integer.MAX_VALUE, "+getElementName(e.getName(), true)+"));\r\n");    
	  }
	  write(indent+"  }\r\n\r\n");  
  }

  private void generateConstructor(String className, List<ElementDefn> params, String indent) throws IOException {
    write(indent+"/*\r\n");
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
		List<DefinedCode> codes = cd.getAllCodes();
		
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
    write("         * added to help the parsers\r\n");
    write("         */\r\n");      
    write("        NULL;\r\n");


		write("        public static "+tns+" fromCode(String codeString) throws Exception {\r\n");
		write("            if (codeString == null || \"\".equals(codeString))\r\n");
		write("                return null;\r\n");
		for (DefinedCode c : codes) {
			String cc = Utilities.camelCase(c.getCode());
			cc = makeConst(cc);
			write("        if (\""+c.getCode()+"\".equals(codeString))\r\n");
			write("          return "+cc+";\r\n");
		}		
		write("        throw new Exception(\"Unknown "+tns+" code '\"+codeString+\"'\");\r\n");
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
    write("    public String toCode("+tns+" code) {\r\n");
    for (DefinedCode c : codes) {
      String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      write("      if (code == "+tns+"."+cc+")\r\n        return \""+c.getCode()+"\";\r\n");
    }
    write("      return \"?\";\r\n"); 
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
    generateChildrenRegister(e, "      ", false);
    generateCopy(e, tn, true, false);
    generateEquals(e, tn, true, false);
    generateIsEmpty(e, tn, true, false);
    write("  }\r\n");
		write("\r\n");

	}

  
  private void generateEquals(ElementDefn e, String tn, boolean owner, boolean isAbstract) throws IOException {
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
    if (first)
      write("true"); 
    write(";\r\n");
    write("      }\r\n\r\n");  
  }
  
	private void generateCopy(ElementDefn e, String tn, boolean owner, boolean isAbstract) throws IOException {
	  if (isAbstract) {
      write("      public abstract "+tn+" copy();\r\n\r\n");
      write("      public void copyValues("+tn+" dst) {\r\n");
	  } else {
      write("      public "+tn+" copy() {\r\n");
      write("        "+tn+" dst = new "+tn+"();\r\n");
      write("        copyValues(dst);\r\n");
	  }
    for (ElementDefn c : e.getElements()) {
      String params = "";
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
    if (!isAbstract) 
      write("        return dst;\r\n");
    write("      }\r\n\r\n");
    if (!owner && !isAbstract) {
      write("      protected "+tn+" typedCopy() {\r\n");
      write("        return copy();\r\n");
      write("      }\r\n\r\n");
      
    }
  }

  private void generateIsEmpty(ElementDefn e, String tn, boolean owner, boolean isAbstract) throws IOException {
    write("      public boolean isEmpty() {\r\n");
    write("        return super.isEmpty() && ");
    boolean first = true;
    int col = 34;
    for (ElementDefn c : e.getElements()) {
      if (first)
        first = false;
      else {
        write(" && ");
        col = col+4;
      }
      String name = getElementName(c.getName(), true);
      if (name.endsWith("[x]"))
        name = name.substring(0, name.length()-3);
      write("("+name+" == null || "+name+".isEmpty())");
      col = col+25 + name.length()*2;
      if (col > 100) {
        col = 10;
        write("\r\n          ");
      }
    }
      write(";\r\n");
    write("      }\r\n\r\n");
  }

  private void scanNestedTypes(ElementDefn root, String path, ElementDefn e) throws Exception {
		String tn = null;
		if (e.typeCode().equals("code") && e.hasBinding()) {
			BindingSpecification cd = e.getBinding();
			if (cd != null && cd.getBinding() == BindingSpecification.BindingMethod.CodeList) {
				tn = getCodeListType(cd.getValueSet().getName());
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
					tn ="org.hl7.fhir.instance.model.Type";
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
			ElementDefn t = res.getElementByName(en);
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
            rn = "List_";
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
          rn = "List_";
        jdoc(indent, "The actual object that is the target of the reference ("+e.getDefinition()+")");
        writeWithHash(indent+"protected "+rn+" "+getElementName(e.getName(), true)+"Target;\r\n");
        write("\r\n");
      }
		}
	}

  private void writeAttributeAnnotation(String indent, ElementDefn e, int order, String tn) throws Exception {
    write(indent+"@Child(name = \""+getElementName(e.getName(), true)+"\", type = {"+getTypeClassList(e, tn)+
        "}, order="+Integer.toString(order)+", min="+e.getMinCardinality().toString()+", max="+(e.getMaxCardinality() == Integer.MAX_VALUE ?  "Child.MAX_UNLIMITED" : e.getMaxCardinality().toString())+")\r\n");
    write(indent+"@Description(shortDefinition=\""+Utilities.escapeJava(e.getShortDefn())+"\", formalDefinition=\""+Utilities.escapeJava(e.getDefinition())+"\" )\r\n");
  }


  private String getTypeClassList(ElementDefn e, String tn) throws Exception {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (TypeRef tr : e.getTypes()) {
      if (tr.isResourceReference()) {
        for (String p : tr.getParams())
          if (!p.equalsIgnoreCase("Any"))
            if (p.equals("List"))
              b.append(p+"_.class");
            else
              b.append(p+".class");
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
		
		if (e.unbounded()) {
		  jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
			if (tn == null && e.usesCompositeType()) {
				write(indent+"public List<"+root.getName()+"> get"+getTitle(getElementName(e.getName(), false))+"() { \r\n");
        write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
        write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+root.getName()+">();\r\n");
			} else {
			  write(indent+"public List<"+tn+"> get"+getTitle(getElementName(e.getName(), false))+"() { \r\n");
			  write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
			  write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+tn+">();\r\n");
			}
			write(indent+"  return this."+getElementName(e.getName(), true)+";\r\n");
			write(indent+"}\r\n\r\n");
      write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"() { \r\n");
      write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
      write(indent+"    return false;\r\n");
      write(indent+"  for ("+tn+" item : this."+getElementName(e.getName(), true)+")\r\n");
      write(indent+"    if (!item.isEmpty())\r\n");
      write(indent+"      return true;\r\n");
      write(indent+"  return false;\r\n");
      write(indent+"}\r\n");
			
      write("\r\n");
      jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
      if (e.getTypes().size() == 1 && (definitions.getPrimitives().containsKey(e.typeCode()) || e.typeCode().equals("xml:lang"))) {
        write("    // syntactic sugar\r\n");
        write(indent+"public "+tn+" add"+getTitle(getElementName(e.getName(), false))+"Element() {//2 \r\n");
        write(indent+"  "+tn+" t = new "+tn+"("+( tn.startsWith("Enum") ? "new "+tn.substring(12, tn.length()-1)+"EnumFactory()" : "")+");\r\n");
        write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
        write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+tn+">();\r\n");
        write(indent+"  this."+getElementName(e.getName(), true)+".add(t);\r\n");
        write(indent+"  return t;\r\n");
        write(indent+"}\r\n");
        write("\r\n");
        jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
        write(indent+"public "+className+" add"+getTitle(getElementName(e.getName(), false))+"("+getSimpleType(tn)+" value) { //1\r\n");
        write(indent+"  "+tn+" t = new "+tn+"("+( tn.startsWith("Enum") ? "new "+tn.substring(12, tn.length()-1)+"EnumFactory()" : "")+");\r\n");
        write(indent+"  t.setValue(value);\r\n");
        write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
        write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+tn+">();\r\n");
        write(indent+"  this."+getElementName(e.getName(), true)+".add(t);\r\n");
        write(indent+"  return this;\r\n");
        write(indent+"}\r\n");
        write("\r\n");
        jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
        write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"("+getSimpleType(tn)+" value) { \r\n");
        write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
        write(indent+"    return false;\r\n");
        write(indent+"  for ("+tn+" v : this."+getElementName(e.getName(), true)+")\r\n");
        if (isJavaPrimitive(e)) 
          write(indent+"    if (v.equals(value)) // "+e.typeCode()+"\r\n");
        else
          write(indent+"    if (v.getValue().equals(value)) // "+e.typeCode()+"\r\n");
        write(indent+"      return true;\r\n");
        write(indent+"  return false;\r\n");
        write(indent+"}\r\n");
        write("\r\n");
      } else {
        if (!definitions.getBaseResources().containsKey(tn)) {
          write("    // syntactic sugar\r\n");
          write(indent+"public "+tn+" add"+getTitle(getElementName(e.getName(), false))+"() { //3\r\n");
          write(indent+"  "+tn+" t = new "+tn+"();\r\n");
          write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
          write(indent+"    this."+getElementName(e.getName(), true)+" = new ArrayList<"+tn+">();\r\n");
          write(indent+"  this."+getElementName(e.getName(), true)+".add(t);\r\n");
          write(indent+"  return t;\r\n");
          write(indent+"}\r\n");
          write("\r\n");
          write("    // syntactic sugar\r\n");
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

        if (e.getTypes().size() == 1 && e.typeCode().startsWith("Reference(")) {

          List<String> params = e.getTypes().get(0).getParams();
          String rn = params.size() == 1 ? params.get(0) : "Resource";
          if (rn.equals("Any"))
            rn = "Resource";
          else if (rn.equals("List"))
            rn = "List_";
          

          jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. "+e.getDefinition()+")");
          write(indent+"public List<"+rn+"> get"+getTitle(getElementName(e.getName(), false))+"Target() { \r\n");
          write(indent+"  if (this."+getElementName(e.getName(), true)+"Target == null)\r\n");
          write(indent+"    this."+getElementName(e.getName(), true)+"Target = new ArrayList<"+rn+">();\r\n");
          write(indent+"  return this."+getElementName(e.getName(), true)+"Target;\r\n");
          write(indent+"}\r\n");
          write("\r\n");
          if (!rn.equals("Resource")) {
            write("    // syntactic sugar\r\n");
            jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. "+e.getDefinition()+")");
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
        if (!isReferenceRefField) {
          /*
           * Don't generate Reference#getReferenceElement because this method is defined in
           * BaseReference.java
           */
  	      write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"Element() { \r\n");
          write(indent+"  if (this."+getElementName(e.getName(), true)+" == null)\r\n");
          write(indent+"    if (Configuration.errorOnAutoCreate())\r\n");
          write(indent+"      throw new Error(\"Attempt to auto-create "+className+"."+getElementName(e.getName(), true)+"\");\r\n");
          write(indent+"    else if (Configuration.doAutoCreate())\r\n");
          write(indent+"      this."+getElementName(e.getName(), true)+" = new "+tn+"("+( tn.startsWith("Enum") ? "new "+tn.substring(12, tn.length()-1)+"EnumFactory()" : "")+"); // bb\r\n");
  	      write(indent+"  return this."+getElementName(e.getName(), true)+";\r\n");
  	      write(indent+"}\r\n");
  	      write("\r\n");
        }
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
	      write(indent+"public "+getSimpleType(tn)+" get"+getTitle(getElementName(e.getName(), false))+"() { \r\n");
	      if (e.typeCode().equals("boolean"))
          write(indent+"  return this."+getElementName(e.getName(), true)+" == null || this."+getElementName(e.getName(), true)+".isEmpty() ? false : this."+getElementName(e.getName(), true)+".getValue();\r\n");
	      else if (e.typeCode().equals("integer") || e.typeCode().equals("unsignedInt") || e.typeCode().equals("positiveInt"))
          write(indent+"  return this."+getElementName(e.getName(), true)+" == null || this."+getElementName(e.getName(), true)+".isEmpty() ? 0 : this."+getElementName(e.getName(), true)+".getValue();\r\n");
	      else
	        write(indent+"  return this."+getElementName(e.getName(), true)+" == null ? null : this."+getElementName(e.getName(), true)+".getValue();\r\n");
	      write(indent+"}\r\n");
	      write("\r\n");
	      jdoc(indent, "@param value "+e.getDefinition());
	      write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"("+getSimpleType(tn)+" value) { \r\n");
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
        write(indent+"  return this;\r\n");
        write(indent+"}\r\n");
	      write("\r\n");
			  
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
		        write(indent+"public "+ttn+" get"+getTitle(getElementName(e.getName(), false))+ttn+"() throws Exception { \r\n");
		        write(indent+"  if (!(this."+getElementName(e.getName(), true)+" instanceof "+ttn+"))\r\n");
		        write(indent+"    throw new Exception(\"Type mismatch: the type "+ttn+" was expected, but \"+this."+getElementName(e.getName(), true)+".getClass().getName()+\" was encountered\");\r\n");
		        write(indent+"  return ("+ttn+") this."+getElementName(e.getName(), true)+";\r\n");
		        write(indent+"}\r\n");
		        write("\r\n");
            write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+ttn+"() throws Exception { \r\n");
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
            rn = "List_";
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
