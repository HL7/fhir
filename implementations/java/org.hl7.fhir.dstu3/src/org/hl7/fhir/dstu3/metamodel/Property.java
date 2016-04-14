package org.hl7.fhir.dstu3.metamodel;

import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;

public class Property {

	private ElementDefinition definition;
	private StructureDefinition structure;

	public Property(ElementDefinition definition, StructureDefinition structure) {
		this.definition = definition;
		this.structure = structure;
	}

	public String getName() {
		return definition.getPath().substring(definition.getPath().lastIndexOf(".")+1);
	}

	public ElementDefinition getDefinition() {
		return definition;
	}

	public String getType() {
		if (definition.getType().size() == 0)
			return null;
		else if (definition.getType().size() > 1)
			throw new Error("logic error, gettype when types > 1");
		else
			return definition.getType().get(0).getCode();
	}

	public String getType(String elementName) {
		if (definition.getType().size() == 0)
			return null;
		else if (definition.getType().size() > 1) {
			String t = definition.getType().get(0).getCode();
			boolean all = true;
			for (TypeRefComponent tr : definition.getType()) {
				if (!t.equals(tr.getCode()))
					all = false;
			}
			if (all)
				return t;
			String tail = definition.getPath().substring(definition.getPath().lastIndexOf(".")+1);
			if (tail.endsWith("[x]") && elementName.startsWith(tail.substring(0, tail.length()-3))) {
				String name = elementName.substring(tail.length()-3);
				return ParserBase.isPrimitive(lowFirst(name)) ? lowFirst(name) : name;				
			} else
        throw new Error("logic error, gettype when types > 1, name mismatch at "+definition.getPath());
		} else
			return definition.getType().get(0).getCode();
	}

  public boolean hasType(String elementName) {
    if (definition.getType().size() == 0)
      return false;
    else if (definition.getType().size() > 1) {
      String t = definition.getType().get(0).getCode();
      boolean all = true;
      for (TypeRefComponent tr : definition.getType()) {
        if (!t.equals(tr.getCode()))
          all = false;
      }
      if (all)
        return true;
      String tail = definition.getPath().substring(definition.getPath().lastIndexOf(".")+1);
      if (tail.endsWith("[x]") && elementName.startsWith(tail.substring(0, tail.length()-3))) {
        String name = elementName.substring(tail.length()-3);
        return true;        
      } else
        return false;
    } else
      return true;
  }

	public StructureDefinition getStructure() {
		return structure;
	}

	public boolean isPrimitive() {
		return definition.getType().size() == 1 && ParserBase.isPrimitive(definition.getType().get(0).getCode());
	}

	private String lowFirst(String t) {
		return t.substring(0, 1).toLowerCase()+t.substring(1);
	}

	public boolean isResource() {
		return definition.getType().size() == 1 && definition.getType().get(0).getCode().equals("Resource");
	}

	public boolean isList() {
	  return !definition.getMax().equals("1");
	}

  public String getScopedPropertyName() {
    return definition.getBase().getPath();
  }

  public String getNamespace() {
    if (ToolingExtensions.hasExtension(definition, "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace"))
      return ToolingExtensions.readStringExtension(definition, "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace");
    if (ToolingExtensions.hasExtension(structure, "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace"))
      return ToolingExtensions.readStringExtension(structure, "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace");
    return FormatUtilities.FHIR_NS;
  }


}
