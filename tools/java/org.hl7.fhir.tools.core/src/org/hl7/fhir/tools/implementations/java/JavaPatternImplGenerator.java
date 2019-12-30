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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.tools.implementations.java.JavaResourceGenerator.JavaGenClass;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;

/*
changes for James
- lazy construction of lists
- getX will construct if null
- add hasX
  
*/
public class JavaPatternImplGenerator extends JavaBaseGenerator {

  private static final boolean HAPI_16 = true;
	private JavaGenClass clss;
  private Map<String, String> adornments;
  private Map<String, String> enumInfo;
	
	public JavaPatternImplGenerator(OutputStream out, Definitions definitions, Map<String, String> adornments, Map<String, String> enumInfo) throws UnsupportedEncodingException {
		super(out);
		this.definitions = definitions;
		this.adornments = adornments; 
		this.enumInfo = enumInfo;
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

	public void generate(ElementDefn root, String name, JavaGenClass clss, ProfiledType cd, Date genDate, String version, boolean isAbstract, Map<String, SearchParameterDefn> nameToSearchParamDef, ElementDefn template, String patternName, ElementDefn impl) throws Exception {
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
		
		write("package org.hl7.fhir.r5.patterns;\r\n");
    startMark(version, genDate);
    if (clss != JavaGenClass.Constraint) {
      boolean l = true; // hasList(root);
      boolean h = hasXhtml(root);
      boolean d = hasDecimal(root);
      boolean s = hasString(root);
      boolean e = hasSharedEnums(root);
      if (l || h || d || e) {
        if (l)
          write("import java.util.*;\r\n");
        if (h) {
          write("import org.hl7.fhir.utilities.xhtml.NodeType;\r\n");
          write("import org.hl7.fhir.utilities.xhtml.XhtmlNode;\r\n");
        }
        write("\r\n");
        if (d)
          write("import java.math.*;\r\n");
        if (s)
          write("import org.hl7.fhir.utilities.Utilities;\r\n");
        if (e)
          write("import org.hl7.fhir.r5.model.Enumerations.*;\r\n");
      }
    }
    write("import org.hl7.fhir.r5.model.*;\r\n");
    write("import org.hl7.fhir.r5.model.Enumeration;\r\n");
    write("import org.hl7.fhir.exceptions.FHIRException;\r\n");
    
    classname = upFirst(name);
		String supertype = root.typeCode();
		
		if (template != null)
		  supertype = template.getName();
		  
    write("\r\n");
		write("public class "+name+patternName+"Impl extends PatternBaseImpl implements "+patternName+" ");
		  
		write("{\r\n");
		write("\r\n");
      
		write("  private "+name+" wrapped;\r\n");
    write("\r\n");
    write("  public "+name+patternName+"Impl("+name+" wrapped) {\r\n");
    write("    super(wrapped);\r\n");
    write("    this.wrapped = wrapped;\r\n");
    write("  }\r\n");
    write("\r\n");
		  
		  
		if (clss != JavaGenClass.Constraint) {
			for (ElementDefn e : root.getElements()) {
  			scanNestedTypes(root, root.getName(), e);
			}
//			for (ElementDefn e : enums) {
//				generateEnum(e, upFirst(name));
//			}
			for (ElementDefn e : strucs) {
				generateType(e, clss == JavaGenClass.Resource ? JavaGenClass.BackboneElement : JavaGenClass.Structure, impl, name);
			}
      
			for (ElementDefn e : root.getElements()) {
			  if (doGenerateAccessors(e))
			    generateAccessors(root, e, resolveTarget(root, e, impl), "    ", patternName, name);
			}
			
			generateFhirType(name);
		} else {
      write("    private static final long serialVersionUID = "+inheritedHash+"L;\r\n\r\n");
		}


		write("\r\n");
		write("}\r\n");
		write("\r\n");
		flush();
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

  private void generateFhirType(String name) throws IOException {
    write("  public String fhirType() {\r\n     return \""+name+"\";\r\n}\r\n\r\n");
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

  private String asCommaText(List<TypeRef> types) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    Set<String> tset = new HashSet<String>();
    for (TypeRef t : types) {
      if (!tset.contains(t.getName())) {
        b.append("\""+t.getName()+"\"");
        tset.add(t.getName());
      }
    }
    return b.toString();
  }

  private String propId(String name) {
    return Integer.toString(name.hashCode());
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
      if (Utilities.existsInList(e.typeCode(), "string", "id", "code", "uri", "oid", "uuid", "url", "canonical") || hasString(e))
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


  private void generateType(ElementDefn e, JavaGenClass clss, ElementDefn impl, String resName) throws Exception {
		String tn = typeNames.get(e);

    write("    public class "+tn+"Impl extends PatternBaseImpl implements EventPerformerComponent {\r\n");
    write("      private Element wrapped;\r\n");
    write("\r\n");
    write("       public "+tn+"Impl(Element wrapped) {\r\n");
    write("         super(wrapped);\r\n");
    write("         this.wrapped = wrapped;\r\n");
    write("       }\r\n");
    write("\r\n");
		for (ElementDefn c : e.getElements()) {
			generateAccessors(e, c, resolveTarget(e, c, impl), "        ", tn, resName);
		}
    write("  }\r\n");
		write("\r\n");

	}


  private ElementDefn resolveTarget(ElementDefn e, ElementDefn c, ElementDefn impl) {
    String path = e.getName()+"."+c.getName();
    for (ElementDefn n : impl.getElements()) {
      String wm = getWorkflowMapping(n);
      if (wm != null && wm.equals(path))
        return n;
    }
    return null;
  }

  private String getWorkflowMapping(ElementDefn n) {
    for (String m : n.getMappings().keySet()) {
      if ("http://hl7.org/fhir/workflow".equals(m))
        return n.getMapping(m);
    }
    return null;
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
			} else if (isEnum(cd)) {
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
					tn ="org.hl7.fhir.r5.model.Type";
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
    if (n.equals("UrlType"))
      return "String";
    if (n.equals("CanonicalType"))
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
	private void generateAccessors(ElementDefn root, ElementDefn e, ElementDefn impl, String indent, String className, String resName) throws Exception {
		String tn = typeNames.get(e);

		boolean isReferenceRefField = (root.getName().equals("Reference") && e.getName().equals("reference"));
    String cvtB = getConversion1(e, impl);
    String cvtR = getConversion2(e, impl);
		String cvtE = Utilities.noString(cvtB) ? "" : ")";
		
		String simpleType = getSimpleType(tn);
    write(indent+"public int get"+getTitle(getElementName(e.getName(), false))+"Min() {\r\n");
    if (impl == null)
      write(indent+"  return 0;\r\n");
    else
      write(indent+"  return "+impl.getMinCardinality()+";\r\n");
    write(indent+"}\r\n\r\n");
    write(indent+"public int get"+getTitle(getElementName(e.getName(), false))+"Max() {\r\n");
    if (impl == null)
      write(indent+"  return 0;\r\n");
    else
      write(indent+"  return "+impl.getMaxCardinality()+";\r\n");
    write(indent+"}\r\n\r\n");
		if (e.unbounded()) {
		  /*
		   * getXXX()for repeatable type
		   */
		  String listGenericType;
		  if (tn == null && e.usesCompositeType()) {
		    listGenericType = root.getName();
		  } else {
		    listGenericType = tn;
		  }
		  write(indent+"public List<"+listGenericType+"> get"+getTitle(getElementName(e.getName(), false))+"() throws FHIRException {\r\n");
		  if (impl == null)
	      write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
		  else if (impl.unbounded())
        write(indent+"  return wrapped.get"+getTitle(getElementName(impl.getName(), false))+"();\r\n");
		  else {
        write(indent+"  ArrayList<"+listGenericType+"> list = new ArrayList<"+listGenericType+">();\r\n");
        write(indent+"  list.add("+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+"()"+cvtE+");\r\n");
        write(indent+"  return list;\r\n");
		  }
      write(indent+"}\r\n\r\n");

		  /*
		   * setXXX(List<foo>) for repeating type
		   */
		  write(indent+"public " + className + " set"+getTitle(getElementName(e.getName(), false))+"(" + "List<"+listGenericType+"> the" + getTitle(getElementName(e.getName(), false)) + ") throws FHIRException {\r\n\r\n");
      if (impl == null)
        write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
      else {
        if (impl.unbounded())
          write(indent+"  wrapped.set"+getTitle(getElementName(impl.getName(), false))+"("+cvtR+"the" + getTitle(getElementName(e.getName(), false)) + ""+cvtE+");\r\n");
        else
          write(indent+"  wrapped.set"+getTitle(getElementName(impl.getName(), false))+"("+cvtR+"the" + getTitle(getElementName(e.getName(), false)) + ".get(0)"+cvtE+");\r\n");
        write(indent+"  return this;\r\n\r\n");
      }
      write(indent+"}\r\n\r\n");

		  /*
		   * hasXXX() for repeatable type
		   */
		  write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"() {\r\n");
      if (impl == null)
        write(indent+"  return false;\r\n");
      else
        write(indent+"  return wrapped.has"+getTitle(getElementName(impl.getName(), false))+"();\r\n");
      write(indent+"}\r\n\r\n");

		  write("\r\n");
		  if (e.getTypes().size() == 1 && (definitions.getPrimitives().containsKey(e.typeCode()) || e.typeCode().equals("xml:lang") || e.typeCode().startsWith("canonical("))) {
		    /*
		     * addXXXElement() for repeatable primitive
		     */
		    write(indent+"public "+tn+" add"+getTitle(getElementName(e.getName(), false))+"Element() throws FHIRException {\r\n");
        write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
	      write(indent+"}\r\n\r\n");
		    write("\r\n");

		    /*
		     * addXXX(foo) for repeatable primitive
		     */
		    write(indent+"public "+className+" add"+getTitle(getElementName(e.getName(), false))+"("+simpleType+" value) throws FHIRException {\r\n");
        write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
	      write(indent+"}\r\n\r\n");
		    write("\r\n");

		    /*
		     * hasXXX(foo) for repeatable primitive
		     */
		    write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"("+simpleType+" value) {\r\n");
		    if (impl == null)
		      write(indent+"  return false;\r\n");
		    else
		      write(indent+"  return wrapped.has"+getTitle(getElementName(impl.getName(), false))+"(value);\r\n");
	      write(indent+"}\r\n\r\n");
		    write("\r\n");
		  } else {
		    if (!definitions.getBaseResources().containsKey(tn)) {
		      /*
		       * addXXX() for repeatable composite
		       */
		      write(indent+"public "+tn+" add"+getTitle(getElementName(e.getName(), false))+"() throws FHIRException {\r\n");
	        write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
		      write(indent+"}\r\n\r\n");
		      write("\r\n");

		      /*
		       * addXXX(foo) for repeatable composite
		       */
		      write(indent+"public "+className+" add"+getTitle(getElementName(e.getName(), false))+"("+tn+" t) throws FHIRException {\r\n");
	        write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
		      write(indent+"}\r\n\r\n");
		      write("\r\n");
		    } else {
		      /*
		       * addXXX(foo) for repeatable composite
		       */
		      write(indent+"public "+className+" add"+getTitle(getElementName(e.getName(), false))+"("+tn+" t) throws FHIRException {\r\n");
	        write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
		      write(indent+"}\r\n\r\n");
		      write("\r\n");          
		    }

		    /*
		     * getXXXFirstRep() for repeatable element
		     */
		    if (!"DomainResource".equals(className)) {
		      write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"FirstRep() throws FHIRException {\r\n\r\n");
	        if (impl == null)
	          write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
	        else if (impl.unbounded())
  		      write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+"FirstRep()"+cvtE+";\r\n");
	        else 
            write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+"()"+cvtE+";\r\n");
		      write(indent+"}\r\n\r\n");
		    }
		  }
		} else {
      if (isJavaPrimitive(e) || (e.getTypes().size() == 1 && e.typeCode().startsWith("canonical("))) {
        if (isReferenceRefField) {
          /*
           * Reference#getReferenceElement is defined differently in BaseReference.java?
           */
          write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"Element_() throws FHIRException {\r\n");
          if (impl == null)
            write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
          else if (impl.unbounded())
            write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+"ElementFirstRep()"+cvtE+";\r\n");
          else
            write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+"Element_()"+cvtE+";\r\n");
          write(indent+"}\r\n\r\n");
        } else { 
          write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"Element() throws FHIRException {\r\n");
          if (impl == null)
            write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
          else if (impl.unbounded())
            write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+"ElementFirstRep()"+cvtE+";\r\n");
          else
            write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+"Element()"+cvtE+";\r\n");
          write(indent+"}\r\n\r\n");
        }
        write("\r\n");

        write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"Element() {\r\n");
        if (impl == null)
          write(indent+"  return false;\r\n");
        else
          write(indent+"  return wrapped.has"+getTitle(getElementName(impl.getName(), false))+"Element();\r\n");
        write(indent+"}\r\n\r\n");
        write("\r\n");
        write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"() {\r\n");
        if (impl == null)
          write(indent+"  return false;\r\n");
        else
          write(indent+"  return wrapped.has"+getTitle(getElementName(impl.getName(), false))+"();\r\n");
        write(indent+"}\r\n\r\n");
        write("\r\n");
        write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"Element("+tn+" value) throws FHIRException {\r\n");
        if (impl == null)
          write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
        else {
          if (impl.unbounded()) {
            write(indent+"  wrapped.set"+getTitle(getElementName(impl.getName(), false))+"Element(value);\r\n");
          } else {
            write(indent+"  wrapped.set"+getTitle(getElementName(impl.getName(), false))+"Element(value);\r\n");            
          }
          write(indent+"  return this;\r\n\r\n");
        }
        write(indent+"}\r\n\r\n");
        write("\r\n");
        write(indent+"public "+simpleType+" get"+getTitle(getElementName(e.getName(), false))+"() throws FHIRException {\r\n");
        if (impl == null)
          write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
        else if (impl.unbounded())
          write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+"FirstRep()"+cvtE+";\r\n");
        else
          write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+"()"+cvtE+";\r\n");
        write(indent+"}\r\n\r\n");
	      generateSetter(e, indent, className, tn, simpleType, impl, resName, cvtR, cvtE);

	      // BigDecimal sugar methods 
	      if (simpleType.equals("BigDecimal")) {
          generateSetter(e, indent, className, tn, "long", impl, resName, cvtR, cvtE);
	        generateSetter(e, indent, className, tn, "double", impl, resName, cvtR, cvtE);
	      }

      } else {
        write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"() throws FHIRException {\r\n");
        if (impl == null)
          write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
        else if (impl.unbounded())
          write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+(isPrimitive(impl.typeCode()) ? "Element" : "")+"FirstRep()"+cvtE+";\r\n");
        else
          write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+(isPrimitive(impl.typeCode()) ? "Element" : "")+"()"+cvtE+";\r\n");
        write(indent+"}\r\n\r\n");
        if (e.getTypes().size() > 1 && (tn.equals("Type") || !tn.endsWith(".Type"))) {
          for (TypeRef t : e.getTypes()) {
            String ttn = getTypename(t);
            TypeRef ti = impl == null ? null : getTypeFromImplementation(impl, t);
            write(indent+"public "+ttn+" get"+getTitle(getElementName(e.getName(), false))+ttn+"() throws FHIRException {\r\n");
            if (impl == null)
              write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
            else if (ti == null)
              write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' does not support the type '"+ttn+"' for the resource '"+resName+"'\");\r\n");
            else
              write(indent+"  return "+cvtB+"wrapped.get"+getTitle(getElementName(impl.getName(), false))+ttn+"()"+cvtE+";\r\n");
            write(indent+"}\r\n\r\n");
            write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+ttn+"() { \r\n");
            if (impl == null || ti == null)
              write(indent+"  return false;\r\n");
            else
              write(indent+"  return wrapped.has"+getTitle(getElementName(impl.getName(), false))+ttn+"(); // tt2\r\n");
            write(indent+"}\r\n\r\n");
          }
        }
        write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"() {\r\n");
        if (impl == null)
          write(indent+"  return false;\r\n");
        else
          write(indent+"  return wrapped.has"+getTitle(getElementName(impl.getName(), false))+"();\r\n");
        write(indent+"}\r\n\r\n");
        write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"("+tn+" value) throws FHIRException {\r\n");     if (impl == null)
          write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
        else {
          if (impl.unbounded()) {
            write(indent+"  wrapped.get"+getTitle(getElementName(impl.getName(), false))+"().clear();\r\n");
            write(indent+"  if (value != null)\r\n");
            write(indent+"    wrapped.get"+getTitle(getElementName(impl.getName(), false))+"().add(value);\r\n");
          } else
            write(indent+"  wrapped.set"+getTitle(getElementName(impl.getName(), false))+"(value);\r\n");
          write(indent+"  return this;\r\n\r\n");
        }
        write(indent+"}\r\n\r\n");
        if (e.getTypes().size() == 1 && e.typeCode().startsWith("Reference(")) {
          List<String> params = e.getTypes().get(0).getParams();
          String rn = params.size() == 1 ? params.get(0) : "Resource";
          if (rn.equals("Any"))
            rn = "Resource";
          else if (rn.equals("List"))
            rn = "ListResource";
			  }			  
			}
		}

	}

  private TypeRef getTypeFromImplementation(ElementDefn impl, TypeRef t) {
    for (TypeRef ti : impl.getTypes()) {
      if (ti.getName().equals(t.getName()))
        return ti;
    }
    return null;
  }

  private String getConversion1(ElementDefn e, ElementDefn impl) {
    if ( e == null || e.getTypes().size() != 1 || impl == null || impl.getTypes().size() != 1)
      return "";
    String tp = e.getTypes().get(0).getName();
    String ti = impl.getTypes().get(0).getName();
    if (tp.equals(ti)) 
      return "";
    return "convert"+Utilities.capitalize(ti)+"To"+Utilities.capitalize(tp)+"(";
  }

  private String getConversion2(ElementDefn e, ElementDefn impl) {
    if ( e == null || e.getTypes().size() != 1 || impl == null || impl.getTypes().size() != 1)
      return "";
    String tp = e.getTypes().get(0).getName();
    String ti = impl.getTypes().get(0).getName();
    if (tp.equals(ti)) 
      return "";
    return "convert"+Utilities.capitalize(tp)+"To"+Utilities.capitalize(ti)+"(";
  }

  private void generateSetter(ElementDefn e, String indent, String className, String tn, String simpleType, ElementDefn impl, String resName, String cvtR, String cvtE) throws IOException {
    write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"("+simpleType+" value) throws FHIRException {\r\n");
    if (impl == null)
      write(indent+"  throw new FHIRException(\"The pattern property '"+e.getName()+"' is not supported in '"+resName+"'\");\r\n");
    else {
      if (impl.unbounded()) {
        write(indent+"  wrapped.get"+getTitle(getElementName(impl.getName(), false))+"().clear();\r\n");
        write(indent+"  if (value != null)\r\n");
        write(indent+"    wrapped.set"+getTitle(getElementName(impl.getName(), false))+"("+cvtR+"value"+cvtE+");\r\n");
      } else {
        write(indent+"  wrapped.set"+getTitle(getElementName(impl.getName(), false))+"("+cvtR+"value"+cvtE+");\r\n");
      }
      write(indent+"  return this;\r\n\r\n");
    }
    write("}\r\n\r\n");
  }

  private boolean isString(String tn) {
    return tn.equals("StringType") || tn.equals("CodeType") || tn.equals("IdType") || tn.equals("UriType") || tn.equals("OidType") || tn.equals("CanonicalType") || tn.equals("UrlType") || tn.equals("UuidType");
  }

  public long getHashSum() {
    return hashSum;
  }

  public void setInheritedHash(String value) {
    inheritedHash = value;
    
  }


}
