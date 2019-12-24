package org.hl7.fhir.definitions.generators.specification;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.SchematronWriter;
import org.hl7.fhir.utilities.xml.SchematronWriter.Rule;
import org.hl7.fhir.utilities.xml.SchematronWriter.SchematronType;
import org.hl7.fhir.utilities.xml.SchematronWriter.Section;

public class SchematronGenerator {
			
	private PageProcessor page;
  
  public SchematronGenerator(PageProcessor page) throws UnsupportedEncodingException {
    super();
    this.page = page;
  }
 
	public void generate(OutputStream out, Definitions definitions) throws Exception {
    SchematronWriter sch = new SchematronWriter(out, SchematronType.ALL_RESOURCES, "All Resources");
    insertGlobalRules(sch);
    for (String rn : definitions.sortedResourceNames()) {
      ResourceDefn root = definitions.getResources().get(rn);
      Section s = sch.section(root.getName());
      ArrayList<String> parents = new ArrayList<String>();
      generateInvariants(s, null, root.getRoot(), definitions, parents, root.getName());
    }
    Set<StructureDefinition> processed = new HashSet<StructureDefinition>(); 
    for (StructureDefinition exd : page.getWorkerContext().getExtensionDefinitions()) {
      if (exd.getSnapshot().getElement().get(0).hasConstraint() && !processed.contains(exd)) {
        processed.add(exd);
        Section s = sch.section("Extension: "+exd.getName());
        Rule r = s.rule("f:"+(exd.getSnapshot().getElementFirstRep().getIsModifier() ? "modifierExtension" : "extension")+"[@url='"+exd.getUrl()+"']");
        for (ElementDefinitionConstraintComponent inv : exd.getSnapshot().getElement().get(0).getConstraint()) {
          if (!isGlobal(inv.getKey()))
            r.assrt(inv.getXpath().replace("\"", "'"), inv.getKey()+": "+inv.getHuman());
        }
      }
    }
    sch.dump();	 
    sch.close();
	}

  private boolean isGlobal(String key) {
    return Utilities.existsInList(key, "ele-1", "ext-1");
  }

  private void insertGlobalRules(SchematronWriter sch) throws IOException {
    Section s = sch.section("Global");
    s.rule("f:*").assrt("@value|f:*|h:div", "global-1: All FHIR elements must have a @value or children");
    s.rule("f:extension").assrt("exists(f:extension)!=exists(f:*[starts-with(local-name(.), 'value')])", "ext-1: Must have either extensions or value[x], not both");
    s.rule("f:modifierExtension").assrt("exists(f:extension)!=exists(f:*[starts-with(local-name(.), 'value')])", "ext-1: Must have either extensions or value[x], not both");
	}

  public void generate(OutputStream out, ResourceDefn root, Definitions definitions) throws Exception {
    SchematronWriter sch = new SchematronWriter(out, SchematronType.RESOURCE, root.getName());
    insertGlobalRules(sch);
    Section s = sch.section(root.getName());
    ArrayList<String> parents = new ArrayList<String>();
    generateInvariants(s, null, root.getRoot(), definitions, parents, root.getName());
    sch.dump();
    sch.close();
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
	    path = path == null ? "f:"+name : path + (recursesToSelf(ed) ? "/" : "") + "/f:"+name;
	    genInvs(section, path, ed);
	    genChildren(section, path, null, ed, definitions, parents);
	  } else {
	    for (TypeRef tr : ed.typeCode().equals("*") ? allTypes() : ed.getTypes()) {
	      String en = name;
	      if (en.endsWith("[x]")) {
	        if (definitions.getConstraints().containsKey(tr.getName()))
            en = en.replace("[x]", definitions.getConstraints().get(tr.getName()).getBaseType());
	        else
	          en = en.replace("[x]", Utilities.capitalize(tr.summary()));
	      }
	      if (en.contains("("))
	        en = en.substring(0, en.indexOf("("));
	      if (en.equals("div"))
	        en = "h:"+en;
	      else
          en = "f:"+en;	        
	      String sPath = path == null ? en : path + "/"+en;
	      genInvs(section, sPath, ed);
	      ElementDefn td = getType(tr, definitions);
	      if (td != null) {
	        genInvs(section, sPath, td);
	        genChildren(section, sPath, tr.summary(), td, definitions, parents);
	      }
	    }
	  }
	}

  private boolean recursesToSelf(ElementDefn ed) {
    return recursesToSelf("@"+ed.getPath(), ed.getElements());
  }

  private boolean recursesToSelf(String path, List<ElementDefn> elements) {
    for (ElementDefn ed : elements) {
      if (path.equals(ed.typeCode()))
        return true;
     if (recursesToSelf(path, ed.getElements()))
       return true;
    }
    return false;
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
        if (!Utilities.existsInList(inv.getSeverity(), "warning", "best-practice")) {
          if (inv.getFixedName() == null || path.endsWith(inv.getFixedName())) {
            if (!isGlobal(inv.getId())) {
              if (inv.getXpath().contains("&lt;") || inv.getXpath().contains("&gt;"))
                throw new Exception("error in xpath - do not escape xml characters in the xpath in the excel spreadsheet");
              r.assrt(inv.getXpath().replace("\"", "'"), inv.getId()+": "+inv.getEnglish());
            }
          }
        }
      }
    }
  }

  private boolean isSpecialType(String tn) {
    return tn.equals("xhtml");
  }

}
