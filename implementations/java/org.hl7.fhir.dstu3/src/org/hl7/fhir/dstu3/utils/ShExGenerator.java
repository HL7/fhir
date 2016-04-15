package org.hl7.fhir.dstu3.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.apache.jena.atlas.lib.Tuple;
import org.hl7.fhir.dstu3.model.Element;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.ShExGenerator.HTMLLinkPolicy;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.StringUtils;

import org.stringtemplate.v4.*;

public class ShExGenerator {

  public enum HTMLLinkPolicy {
    NONE, EXTERNAL, INTERNAL
  }

  /**
   * this makes internal metadata services available to the generator - retrieving structure definitions, and value set expansion etc
   */
  private IWorkerContext context;

  /**
   * List of references generated per session
   */
    private LinkedList<Pair<StructureDefinition, ElementDefinition>> references;

    public ShExGenerator(IWorkerContext context) {
    super();
    this.context = context;
    this.references = new LinkedList<Pair<StructureDefinition, ElementDefinition>>();
  }
  
  public String generate(HTMLLinkPolicy links, StructureDefinition structure) {
    List<StructureDefinition> list = new ArrayList<StructureDefinition>();
    list.add(structure);
    this.references.clear();
    return generate(links, list);
  }
  
  public class SortById implements Comparator<StructureDefinition> {

    @Override
    public int compare(StructureDefinition arg0, StructureDefinition arg1) {
      return arg0.getId().compareTo(arg1.getId());
    }

  }

  /**
   * this is called externally to generate a set of structures to a single ShEx file
   * generally, it will be called with a single structure, or a long list of structures (all of them)
   *
   * @param links HTML link rendering policy
   * @param structures list of structure definitions to render
   * @return ShEx definition of structures
   */
  public String generate(HTMLLinkPolicy links, List<StructureDefinition> structures) {

    ST shex_def = new ST("$header$\n\n$structuredefs$", '$', '$');

    shex_def.add("header", genHeader());

    Collections.sort(structures, new SortById());
    StringBuilder structuredefs = new StringBuilder();
    for (StructureDefinition sd : structures) {
      structuredefs.append(genStructureDefinition(sd));
    }

    Set<String> seen = new HashSet<String>();
    while(!this.references.isEmpty()) {
      Pair<StructureDefinition, ElementDefinition> sded = this.references.poll();
      StructureDefinition sd = sded.getLeft();
      ElementDefinition ed = sded.getRight();
      if (seen.contains(ed.getPath()))
        break;
      seen.add(ed.getPath());
      structuredefs.append("\n" + genElementReference(sd, ed));
    }

    shex_def.add("structuredefs", structuredefs);
    return shex_def.render();
  }

  /**
   * Generate the ShEx Header
   * @return String representation
   */
  private String genHeader() {
    ST header = new ST("PREFIX $ns$: <$uri$>\nBASE <http://hl7.org/fhir/shape/>", '$', '$');
    header.add("ns", "fhir");
    header.add("uri", "http://hl7.org/fhir/");
    return header.render();
  }

  /**
   * Generate a definition for a referenced element
   * @param sd StructureDefinition in which the element was referenced
   * @param ed Contained element definition
   * @return ShEx representation of element reference
   */
  private String genElementReference(StructureDefinition sd, ElementDefinition ed) {
    ST element_ref_def = new ST("\n<$id$> {\n\ta [fhir:$id$]$elements$\n}\n", '$', '$');
    element_ref_def.add("id", ed.getPath());

    StringBuilder elements = new StringBuilder();
    for (ElementDefinition child: ProfileUtilities.getChildList(sd, ed)) {
      elements.append(genElementDefinition(sd, child));
    }

    element_ref_def.add("elements", elements);
    return element_ref_def.render();
  }

  /**
   * Emit a ShEx definition for the supplied StructureDefinition
   * @param sd Structure definition to emit
   * @return ShEx definition
   */
  private String genStructureDefinition(StructureDefinition sd) {
    ST root_def = new ST(",\n\tfhir:nodeRole [fhir:treeRoot]");
    ST struct_def = new ST("\n<$id$> {\n\ta [<$id$>]$root$$elements$\n}\n", '$', '$');

    // todo: Figure out why this doesn't identify "Account" as a resource.  getResourceNames() returns "Resource", "Resource"
    struct_def.add("root", this.context.getResourceNames().contains(sd.getId()) ? root_def.render() : "");

    struct_def.add("id", sd.getId());
    StringBuilder elements = new StringBuilder();

    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      if(StringUtils.countMatches(ed.getPath(), ".") == 1) {
        elements.append(genElementDefinition(sd, ed));
      }
    }
    struct_def.add("elements", elements);
    return struct_def.render();
  }

  /**
   * Generate a ShEx element definition
   * @param sd Containing structure definition
   * @param ed Containing element definition
   * @return ShEx definition
   */
  private String genElementDefinition(StructureDefinition sd, ElementDefinition ed) {
    String id = ed.hasBase() ? ed.getBase().getPath() : ed.getPath();
    
    List<ElementDefinition> children = ProfileUtilities.getChildList(sd, ed);
    // todo : check for content reference

    ST shex_el =  new ST(",\n\tfhir:$id$ @<fhir:$typ$>$card$", '$', '$');

    String card = "*".equals(ed.getMax()) ? (ed.getMin() == 0 ? "*" : "+") : "?";
    shex_el.add("id", id);
    shex_el.add("card", card);
    if (children.size() > 0) {
      // inline anonymous type
      this.references.add(new ImmutablePair<StructureDefinition, ElementDefinition>(sd, ed));
      shex_el.add("typ", id);
    } else if (ed.getType().size() == 1) {
      if(ed.getType().get(0).getProfile().size() > 0) {
        if (ed.getType().get(0).getProfile().size() != 1) throw new AssertionError("Can't handle multiple profiles");
        shex_el.add("typ", getProfiledType(ed.getType().get(0)));
      } else
        shex_el.add("typ", ed.getType().get(0).getCode());
    } else { 
      // multiple types
      if(id.endsWith("[x]")) {
        return genChoiceTypes(ed, id, card);
      } else {
        return genAlternativeTypes(ed, id, card);
      }
    }
    return shex_el.render();
  }

	/**
     * Generate a set of alternative fhir:reference entries
     * @param ed Containing element definition
     * @param id Element definition identifier
     * @param card Cardinality
     * @return ShEx list of alternative anonymous shapes separated by "OR"
	 */
  static String genAlternativeTypes(ElementDefinition ed, String id, String card) {
    ST shex_alt = new ST(",\n\tfhir:$id$ ( $altEntries$\n\t)$card$", '$', '$');
    List<String> altEntries = new ArrayList<String>();

    shex_alt.add("id", id);

    for(ElementDefinition.TypeRefComponent typ : ed.getType())  {
      altEntries.add(genAltEntry(typ));
    }
    shex_alt.add("altEntries", StringUtils.join(altEntries, " OR\n\t\t"));
    shex_alt.add("card", card);
    return shex_alt.render();
  }

  /**
   * Generate an alternative shape for a reference
   * @param typ shape type
   * @return ShEx equivalent
   */
  static String genAltEntry(ElementDefinition.TypeRefComponent typ) {
    ST shex_alt_entry = new ST("{ fhir:reference @<$ref$Reference> }", '$', '$');

    if(!typ.getCode().equals("Reference")) throw new AssertionError("We do not handle " + typ.getCode() + " alternatives");
    // todo: clean this up.  There must be a better way to get the specific type.
    String ref;
    if(typ.getProfile().size() > 0) {
      String[] els = typ.getProfile().get(0).getValue().split("/");
      ref = els[els.length - 1];
    } else {
      ref = "";
    }
    shex_alt_entry.add("ref", ref);
    return shex_alt_entry.render();
  }

  /**
   * Return the type definition for a profiled type
   * @param typ type to generate the entry for
   * @return string of type
   */
  private String getProfiledType(ElementDefinition.TypeRefComponent typ) {
    return typ.getProfile().get(0).fhirType();
  }

  /**
   * Generate a list of type choices for a "name[x]" style id
   * @param ed containing elmentdefinition
   * @param id choice identifier
   * @return ShEx fragment for the set of choices
   */
  private String genChoiceTypes(ElementDefinition ed, String id, String card) {
    ST shex_choice = new ST(",\n\t($choiceEntries$\n\t)$card$", '$', '$');
    List<String> choiceEntries = new ArrayList<String>();
    String base = id.replace("[x]", "");

    for(ElementDefinition.TypeRefComponent typ : ed.getType())  {
      choiceEntries.add(genChoiceEntry(base, typ));
    }
    shex_choice.add("choiceEntries", StringUtils.join(choiceEntries, " |\n\t"));
    shex_choice.add("card", card);
    return shex_choice.render();
  }

  /**
   * Generate an entry in a choice list
   * @param base base identifier
   * @param typ type/discriminant
   * @return ShEx fragment for choice entry
   */
  private String genChoiceEntry(String base, ElementDefinition.TypeRefComponent typ) {
    ST shex_choice_entry = new ST("\t$base$$ext$ @<$ext$>", '$', '$');

    String ext = typ.getCode();
    shex_choice_entry.add("base", base);
    shex_choice_entry.add("ext", ext);
    return shex_choice_entry.render();
  }

}
