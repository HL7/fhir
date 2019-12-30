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
import java.util.HashSet;
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
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.utils.TypesUtilities;
import org.hl7.fhir.tools.implementations.java.JavaResourceGenerator.JavaGenClass;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;

/*
changes for James
- lazy construction of lists
- getX will construct if null
- add hasX
  
*/
public class JavaPatternIntfGenerator extends JavaBaseGenerator {

  /**
   * This property should be committed with a value of false
   * until HAPI 1.6 is released, at which point it will be 
   * removed. Added by JA 2016-05-28
   */
  private static final boolean HAPI_16 = true;

	private JavaGenClass clss;
  private Map<String, String> adornments;
  private Map<String, String> enumInfo;
	
	public JavaPatternIntfGenerator(OutputStream out, Definitions definitions, Map<String, String> adornments, Map<String, String> enumInfo) throws UnsupportedEncodingException {
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

   
//      ElementDefn elem = root.getElementByName("reference");
//      elem.getTypes().get(0);
//    }
		
		write("package org.hl7.fhir.r5.patterns;\r\n");
    startMark(version, genDate);
    write("import org.hl7.fhir.r5.model.*;\r\n");
    write("import org.hl7.fhir.r5.model.Enumeration;\r\n");
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
    write("import org.hl7.fhir.exceptions.FHIRException;\r\n");
    
    classname = upFirst(name);
    if (adornments.containsKey(classname+".imports")) {
      write("// added from java-adornments.txt:\r\n");
      write(adornments.get(classname+".imports")+"\r\n");
      write("// end addition\r\n");
    }
		jdoc("", root.getDefinition());
		
		write("public interface "+upFirst(name)+" extends PatternBase ");
		
		write("{\r\n");
		write("\r\n");

		if (clss != JavaGenClass.Constraint) {
			for (ElementDefn e : root.getElements()) {
  			scanNestedTypes(root, root.getName(), e);
			}
			for (ElementDefn e : enums) {
				generateEnum(e, upFirst(name));
			}
			for (ElementDefn e : strucs) {
				generateType(e, clss == JavaGenClass.Resource ? JavaGenClass.BackboneElement : JavaGenClass.Structure);
			}
    
			for (ElementDefn e : root.getElements()) {
			  if (doGenerateAccessors(e))
			    generateAccessors(root, e, "    ", upFirst(name));
			}
			
			generateTypeSpecificAccessors(name);
			generateFhirType(root.getName());
		} else {
      write("    private static final long serialVersionUID = "+inheritedHash+"L;\r\n\r\n");
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
    write("  public String fhirType();\r\n\r\n");
  }

  private void jdoc(String indent, String text) throws IOException {
    write(indent+"/**\r\n");
		write(indent+" * "+text+"\r\n");
		write(indent+" */\r\n");
  }

  private String upFirst(String name) {
		return name.substring(0,1).toUpperCase()+name.substring(1);
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

	private void generateEnum(ElementDefn e, String name) throws Exception {
		String tn = typeNames.get(e);
		String tns = tn.substring(tn.indexOf("<")+1);
		tns = tns.substring(0, tns.length()-1);
		BindingSpecification cd = e.getBinding();
		if (cd.isShared())
		  return;
		cd.getValueSet().setUserData("java-generated", true);
		List<DefinedCode> codes = cd.getAllCodes(definitions.getCodeSystems(), definitions.getValuesets(), true);

    String url = cd.getValueSet().getUrl();
    CommaSeparatedStringBuilder el = new CommaSeparatedStringBuilder();

		write("    public enum "+tns+" {\r\n");
		int l = codes.size();
		int i = 0;
		for (DefinedCode c : codes) {
			i++;
			String cc = Utilities.camelCase(c.getCode());
      cc = makeConst(cc);
      el.append(cc);
      write("        /**\r\n");
      write("         * "+c.getDefinition()+"\r\n");
      write("         */\r\n");      
			write("        "+cc.toUpperCase()+", \r\n");
		}
    write("        /**\r\n");
    write("         * added to help the parsers with the generic types\r\n");
    write("         */\r\n");      
    write("        NULL;\r\n");
    el.append("NULL");


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

		
		write("  public class "+tns+"EnumFactory implements EnumFactory<"+tns+"> {\r\n");
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
    write("          if (code == null)\r\n");
    write("            return null;\r\n");
    write("          if (code.isEmpty())\r\n");
    write("            return new Enumeration<"+tns+">(this);\r\n");
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
    enumInfo.put("org.hl7.fhir.r5.model."+name+"."+tns, url+"|"+el.toString());
	}

  private void generateType(ElementDefn e, JavaGenClass clss) throws Exception {
		String tn = typeNames.get(e);

		if (clss == JavaGenClass.BackboneElement) {
	    write("    public interface "+tn+" extends PatternBase {\r\n");
		} else {
		  write("    public interface "+tn+" extends PatternBase {\r\n");
		}
		for (ElementDefn c : e.getElements()) {
			generateAccessors(e, c, "        ", tn);
		}
    if (adornments.containsKey(tn)) {
      write("// added from java-adornments.txt:\r\n");
      write(adornments.get(tn)+"\r\n");
      write("// end addition\r\n");
    }
    write("  }\r\n");
		write("\r\n");

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
		  write(indent+"public List<"+listGenericType+"> get"+getTitle(getElementName(e.getName(), false))+"() throws FHIRException;\r\n\r\n");

		  /*
		   * setXXX(List<foo>) for repeating type
		   */
		  jdoc(indent, "@return Returns a reference to <code>this</code> for easy method chaining");
		  write(indent+"public " + className + " set"+getTitle(getElementName(e.getName(), false))+"(" + "List<"+listGenericType+"> the" + getTitle(getElementName(e.getName(), false)) + ") throws FHIRException;\r\n\r\n");

		  /*
		   * hasXXX() for repeatable type
		   */
      jdoc(indent, "@return whether there is more than zero values for "+getElementName(e.getName(), false));
      write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"();\r\n");
      jdoc(indent, "@return minimum allowed cardinality for "+getElementName(e.getName(), false)+". Note that with patterns, this may be different for the underlying resource");
      write(indent+"public int get"+getTitle(getElementName(e.getName(), false))+"Min();\r\n");
      jdoc(indent, "@return maximum allowed cardinality for "+getElementName(e.getName(), false)+". Note that with patterns, this may be different for the underlying resource");
      write(indent+"public int get"+getTitle(getElementName(e.getName(), false))+"Max();\r\n");

		  write("\r\n");
		  if (e.getTypes().size() == 1 && (definitions.getPrimitives().containsKey(e.typeCode()) || e.typeCode().equals("xml:lang") || e.typeCode().startsWith("canonical("))) {
		    /*
		     * addXXXElement() for repeatable primitive
		     */
		    jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
		    write(indent+"public "+tn+" add"+getTitle(getElementName(e.getName(), false))+"Element() throws FHIRException;\r\n");
		    write("\r\n");

		    /*
		     * addXXX(foo) for repeatable primitive
		     */
		    jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
		    write(indent+"public "+className+" add"+getTitle(getElementName(e.getName(), false))+"("+simpleType+" value) throws FHIRException;\r\n");
		    write("\r\n");

		    /*
		     * hasXXX(foo) for repeatable primitive
		     */
		    jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
		    write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"("+simpleType+" value)  throws FHIRException;\r\n");
		    write("\r\n");
		  } else {
		    if (!definitions.getBaseResources().containsKey(tn)) {
		      /*
		       * addXXX() for repeatable composite
		       */
		      write(indent+"public "+tn+" add"+getTitle(getElementName(e.getName(), false))+"() throws FHIRException;\r\n");
		      write("\r\n");

		      /*
		       * addXXX(foo) for repeatable composite
		       */
		      write(indent+"public "+className+" add"+getTitle(getElementName(e.getName(), false))+"("+tn+" t) throws FHIRException;\r\n");
		      write("\r\n");
		    } else {
		      /*
		       * addXXX(foo) for repeatable composite
		       */
		      write(indent+"public "+className+" add"+getTitle(getElementName(e.getName(), false))+"("+tn+" t) throws FHIRException;\r\n");
		      write("\r\n");          
		    }

		    /*
		     * getXXXFirstRep() for repeatable element
		     */
		    if (!"DomainResource".equals(className)) {
		      jdoc(indent, "@return The first repetition of repeating field {@link #"+getElementName(e.getName(), true)+"}, creating it if it does not already exist");
		      write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"FirstRep() throws FHIRException;\r\n\r\n");
		    }
		  }
		} else {
      if (isJavaPrimitive(e) || (e.getTypes().size() == 1 && e.typeCode().startsWith("canonical("))) {
        jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+"). This is the underlying object with id, value and extensions. The accessor \"get"+getTitle(getElementName(e.getName(), false))+"\" gives direct access to the value");
        if (isReferenceRefField) {
          /*
           * Reference#getReferenceElement is defined differently in BaseReference.java?
           */
          write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"Element_() throws FHIRException;\r\n");
        } else { 
          write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"Element() throws FHIRException;\r\n");
        }
        write("\r\n");

        jdoc(indent, "@return whether there is more than zero values for "+getElementName(e.getName(), false));
        write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"();\r\n");
        jdoc(indent, "@return minimum allowed cardinality for "+getElementName(e.getName(), false)+". Note that with patterns, this may be different for the underlying resource");
        write(indent+"public int get"+getTitle(getElementName(e.getName(), false))+"Min() throws FHIRException;\r\n");
        jdoc(indent, "@return maximum allowed cardinality for "+getElementName(e.getName(), false)+". Note that with patterns, this may be different for the underlying resource");
        write(indent+"public int get"+getTitle(getElementName(e.getName(), false))+"Max() throws FHIRException;\r\n");

        write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"Element();\r\n");
        write("\r\n");
        jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+"). This is the underlying object with id, value and extensions. The accessor \"get"+getTitle(getElementName(e.getName(), false))+"\" gives direct access to the value");
        write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"Element("+tn+" value) throws FHIRException;\r\n");
        write("\r\n");
        jdoc(indent, "@return "+e.getDefinition());
        write(indent+"public "+simpleType+" get"+getTitle(getElementName(e.getName(), false))+"() throws FHIRException;\r\n");
        write("\r\n");
	      generateSetter(e, indent, className, tn, simpleType);

	      // BigDecimal sugar methods 
	      if (simpleType.equals("BigDecimal")) {
          generateSetter(e, indent, className, tn, "long");
	        generateSetter(e, indent, className, tn, "double");
	      }

      } else {
        jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
        write(indent+"public "+tn+" get"+getTitle(getElementName(e.getName(), false))+"() throws FHIRException ;\r\n");
        write("\r\n");
        if (e.getTypes().size() > 1 && (tn.equals("Type") || !tn.endsWith(".Type"))) {
          for (TypeRef t : e.getTypes()) {
            jdoc(indent, "@return {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
            String ttn = getTypename(t);
            write(indent+"public "+ttn+" get"+getTitle(getElementName(e.getName(), false))+ttn+"() throws FHIRException;\r\n");
            write("\r\n");
            write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+ttn+"();\r\n");
            write("\r\n");
          }
        }
        jdoc(indent, "@return whether there is more than zero values for "+getElementName(e.getName(), false));
        write(indent+"public boolean has"+getTitle(getElementName(e.getName(), false))+"();\r\n");
        jdoc(indent, "@return minimum allowed cardinality for "+getElementName(e.getName(), false)+". Note that with patterns, this may be different for the underlying resource");
        write(indent+"public int get"+getTitle(getElementName(e.getName(), false))+"Min();\r\n");
        jdoc(indent, "@return maximum allowed cardinality for "+getElementName(e.getName(), false)+". Note that with patterns, this may be different for the underlying resource");
        write(indent+"public int get"+getTitle(getElementName(e.getName(), false))+"Max();\r\n");

        jdoc(indent, "@param value {@link #"+getElementName(e.getName(), true)+"} ("+e.getDefinition()+")");
        write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"("+tn+" value) throws FHIRException;\r\n");
        write("\r\n");
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

  private void generateSetter(ElementDefn e, String indent, String className, String tn, String simpleType) throws IOException {
    jdoc(indent, "@param value "+e.getDefinition());
    write(indent+"public "+className+" set"+getTitle(getElementName(e.getName(), false))+"("+simpleType+" value) throws FHIRException;\r\n");
    write("\r\n");
  }

  public long getHashSum() {
    return hashSum;
  }

  public void setInheritedHash(String value) {
    inheritedHash = value;
    
  }


}
