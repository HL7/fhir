package org.hl7.fhir.definitions.generators.specification;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.TextStreamWriter;
import org.hl7.fhir.utilities.Utilities;

public class SchematronGenerator  extends TextStreamWriter {
			
	private PageProcessor page;

  private class Assert {
    private String test;
    private String message; 
  }
  
	private class Rule {
	  private String name; 
    private List<Assert> asserts = new ArrayList<Assert>();	  
    private void assrt(String test, String message) {
      Assert a = new Assert();
      a.test = test;
      a.message = message;
      asserts.add(a);
    }
	}
	private class Section {
	  private String title;
	  private List<Rule> rules = new ArrayList<Rule>();
	  
	  private Rule rule(String name) {
	    for (Rule r : rules) {
	      if (r.name.equals(name))
	        return r;
	    }
	    Rule r = new Rule();
	    r.name = name;
	    rules.add(r);
	    return r;
	  }
	}
	
  public SchematronGenerator(OutputStream out, PageProcessor page) throws UnsupportedEncodingException {
    super(out);
    this.page = page;
  }

  private List<Section> sections = new ArrayList<SchematronGenerator.Section>();
  
	public void generate(Definitions definitions) throws Exception {
    insertGlobalRules();
    for (ResourceDefn root : definitions.getResources().values()) {
      Section s = new Section();
      s.title = root.getName();
      sections.add(s);
      ArrayList<String> parents = new ArrayList<String>();
      generateInvariants(s, null, root.getRoot(), definitions, parents, root.getName());
      // root.getName()
    }
	  dump(null);	  
	}

	private void dump(String rn) throws IOException {
	  ln("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	  ln_i("<sch:schema xmlns:sch=\"http://purl.oclc.org/dsdl/schematron\" queryBinding=\"xslt2\">");
	  ln("<sch:ns prefix=\"f\" uri=\"http://hl7.org/fhir\"/>");
	  ln("<sch:ns prefix=\"h\" uri=\"http://www.w3.org/1999/xhtml\"/>");
	  if (rn != null) {
	    ln("<!-- ");
	    ln("  This file contains just the constraints for the resource "+rn);
	    ln("  It is provided for documentation purposes. When actually validating,");
	    ln("  always use fhir-invariants.sch (because of the way containment works)");
	    ln("  Alternatively you can use this file to build a smaller version of");
	    ln("  fhir-invariants.sch (the contents are identical; only include those ");
	    ln("  resources relevant to your implementation).");
	    ln("-->");
	  }

	  for (Section s : sections) {
	    ln_i("<sch:pattern>");
	    ln("<sch:title>"+s.title+"</sch:title>");
	    for (Rule r : s.rules) {
	      ln_i("<sch:rule context=\"//"+r.name+"\">");
	      for (Assert a : r.asserts) 
	        ln("<sch:assert test=\""+Utilities.escapeXml(a.test)+"\">"+Utilities.escapeXml(a.message)+"</sch:assert>");
        ln_o("</sch:rule>");
	    }
	    ln_o("</sch:pattern>");
	  }  
	  ln_o("</sch:schema>");
	  flush();
	  close();
	}

  private void insertGlobalRules() throws IOException {
    Section s = new Section();
    s.title = "Global";
    sections.add(s);
    s.rule("f:*").assrt("@value|f:*|h:div", "global-1: All FHIR elements must have a @value or children");
	}

  public void generate(ResourceDefn root, Definitions definitions) throws Exception {
    insertGlobalRules();
    Section s = new Section();
    s.title = root.getName();
    sections.add(s);
    ArrayList<String> parents = new ArrayList<String>();
    generateInvariants(s, null, root.getRoot(), definitions, parents, root.getName());
    dump(root.getName());
  }

	private ElementDefn getType(TypeRef tr, Definitions definitions) throws Exception {
    String tn = tr.getName();
    if (definitions.getPrimitives().containsKey(tn) || isSpecialType(tn) || tn.contains("@") || tn.equals("xml:lang")) 
      return null;
    
    if (definitions.getConstraints().containsKey(tn)) 
      return definitions.getElementDefn(definitions.getConstraints().get(tn).getBaseType());
    else
      return definitions.getElementDefn(tn);    
	}
	
	private void genChildren(Section section, String path, String typeCode, ElementDefn ed, Definitions definitions, List<String> parents) throws Exception {
	  if (!path.contains("//")) {
	    ArrayList<String> l = new ArrayList<String>(parents);
	    l.add(typeCode);

	    for (ElementDefn cd : ed.getElements()) {
	      if (!Utilities.noString(cd.typeCode()) && l.contains(cd.typeCode())) {
	        // well, we've recursed. What's going to happen now is that we're going to write this as // because we're going to keep recursing.
	        // the next call will write this rule, and then terminate
	        generateInvariants(section, path+"/", cd, definitions, l, cd.getName());
	      } else
	        generateInvariants(section, path, cd, definitions, l, cd.getName());
	    }
	  }
	}
	
	private void generateInvariants(Section section, String path, ElementDefn ed, Definitions definitions, List<String> parents, String name) throws Exception {
    if (definitions.getBaseResources().containsKey(ed.typeCode()))
      generateInvariants(section, path, definitions.getBaseResources().get(ed.typeCode()).getRoot(), definitions, parents, name);
        
	  //logger.log("generate: "+path+" ("+parents.toString()+")");
	  if (name.contains("("))
	    name = name.substring(0, name.indexOf("("));
    if (ed.getElements().size() > 0) {
	    path = path == null ? "f:"+name : path + "/f:"+name;
	    genInvs(section, path, ed);
	    genChildren(section, path, null, ed, definitions, parents);
	  } else {
	    for (TypeRef tr : ed.typeCode().equals("*") ? allTypes() : ed.getTypes()) {
	      String en = name.replace("[x]", Utilities.capitalize(tr.summary()));
	      if (en.contains("("))
	        en = en.substring(0, en.indexOf("("));
	      String sPath = path == null ? "f:"+en : path + "/f:"+en;
	      genInvs(section, sPath, ed);
	      ElementDefn td = getType(tr, definitions);
	      if (td != null) {
	        genInvs(section, sPath, td);
	        genChildren(section, sPath, tr.summary(), td, definitions, parents);
	      }
	    }
	  }
	}

  private List<TypeRef> allTypes() {
    return new ArrayList<TypeRef>();
  }

  private void genInvs(Section section, String path, ElementDefn ed) throws Exception {
    
    int c = 0;
    for (Invariant inv : ed.getInvariants().values()) {
      if (inv.getFixedName() == null || path.endsWith(inv.getFixedName()))
        c++;
    }
    if (c > 0) {
      Rule r = section.rule(path);
	    for (Invariant inv : ed.getInvariants().values()) {
	      if (inv.getFixedName() == null || path.endsWith(inv.getFixedName())) {
	        if (inv.getXpath().contains("&lt;") || inv.getXpath().contains("&gt;"))
	          throw new Exception("error in xpath - do not escape xml characters in the xpath in the excel spreadsheet");
	        r.assrt(inv.getXpath().replace("\"", "'"), inv.getId()+": "+inv.getEnglish());
	      }
	    }
	  }
  }

  private boolean isSpecialType(String tn) {
    return tn.equals("xhtml");
  }

//	private void generateElement(String name, ElementDefn e) throws IOException {
//		writeEntry(name+"."+e.getName(), e.getMinCardinality(), e.getMaxCardinality(), e.getTypes(), e.getBindingName(), e);
//		for (ElementDefn c : e.getElements())	{
//		   generateElement(name+"."+e.getName(), c);
//		}
//		
//	}
//
//	private void writeEntry(String path, Integer min, Integer max, List<TypeRef> types, String conceptDomain, ElementDefn e) throws IOException {
//		write("      <elementDefinition>\r\n");
//		write("        <name>"+Utilities.escapeXml(path)+"</name>\r\n");
//		write("        <cardinality>\r\n");
//		write("          <minOccurs>"+min.toString()+"</minOccurs>\r\n");
//		if (max == null)
//			write("          <maxOccurs>unbounded</maxOccurs>\r\n");
//		else
//			write("          <maxOccurs>"+max.toString()+"</maxOccurs>\r\n");
//		write("        </cardinality>\r\n");
//		write("        <condition>"+Utilities.escapeXml(e.getCondition())+"</condition>\r\n");
//		if (types != null && types.size() > 0)
//		{ 
//			write("        <types>\r\n");
//			for (TypeRef t : types)
//			{
//				if (t.hasParams()) {
//					write("          <type>"+t.getName()+"(");
//		            boolean firstp = true;
//		            for (String p : t.getParams()) {
//		            	if (!firstp)
//		            		write("|");
//		            	write(p);
//		            	firstp = false;
//		            }
//					write(")</type>\r\n");
//				}
//				else
//					write("          <type>"+t.getName()+"</type>\r\n");
//			}
//			
//			write("        </types>\r\n");
//		}
//		if (hasValue(conceptDomain))
//			write("        <conceptDomain>"+Utilities.escapeXml(conceptDomain)+"</conceptDomain>\r\n");
//		write("        <mustUnderstand>"+Boolean.toString(e.isMustUnderstand())+"</mustUnderstand>\r\n");
//		write("        <definition>"+Utilities.escapeXml(e.getDefinition())+"</definition>\r\n");
//		write("        <requirements>"+Utilities.escapeXml(e.getRequirements())+"</requirements>\r\n");
//		if (hasValue(e.getComments()))
//			write("        <comments>"+Utilities.escapeXml(e.getComments())+"</comments>\r\n");
//		write("        <rim>"+Utilities.escapeXml(e.getRimMapping())+"</rim>\r\n");
//		if (hasValue(e.getV2Mapping()))
//			write("        <v2>"+Utilities.escapeXml(e.getV2Mapping())+"</v2>\r\n");
//		if (hasValue(e.getTodo()))
//			write("        <todo>"+Utilities.escapeXml(e.getTodo())+"</todo>\r\n");
//		write("      </elementDefinition>\r\n");
//		
//	}
//	
//	
//	private boolean hasValue(String s) {
//		return s != null && !"".equals(s);
//	}
//
//	public void setConceptDomains(Map<String, BindingSpecification> conceptDomains) {
//		this.conceptDomains = conceptDomains;
//		
//	}
}
