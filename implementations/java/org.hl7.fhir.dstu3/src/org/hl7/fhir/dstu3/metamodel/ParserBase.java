package org.hl7.fhir.dstu3.metamodel;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.exceptions.DefinitionException;
import org.hl7.fhir.dstu3.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.ElementDefinition.PropertyRepresentation;
import org.hl7.fhir.dstu3.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.ProfileUtilities;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;
import org.hl7.fhir.utilities.Utilities;

public abstract class ParserBase {

	public static boolean isPrimitive(String code) {
		return Utilities.existsInList(code, 
				"xhtml", "boolean", "integer", "string", "decimal", "uri", "base64Binary", "instant", "date", "dateTime", "time", "code", "oid", "id", "markdown", "unsignedInt", "positiveInt");
	}

	protected IWorkerContext context;
	protected boolean check;

	public ParserBase(IWorkerContext context, boolean check) {
		super();
		this.context = context;
		this.check = check;
	}

	public abstract Element parse(InputStream stream) throws Exception;

	public abstract void compose(Element e, OutputStream destination, OutputStyle style, String base)  throws Exception;

	protected StructureDefinition getDefinition(String ns, String name) throws FHIRFormatError {
    if (ns == null)
      throw new FHIRFormatError("This cannot be parsed as a FHIR object (no namespace)");
    if (name == null)
      throw new FHIRFormatError("This cannot be parsed as a FHIR object (no name)");
	  for (StructureDefinition sd : context.allStructures()) {
	    if (name.equals(sd.getId())) {
	      if(ns.equals(FormatUtilities.FHIR_NS) && !ToolingExtensions.hasExtension(sd, "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace"))
	        return sd;
	      String sns = ToolingExtensions.readStringExtension(sd, "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace");
	      if (ns.equals(sns))
	        return sd;
	    }
	  }
    throw new FHIRFormatError("This does not appear to be a FHIR resource (unknown namespace/name '"+ns+"::"+name+"')");
  }

  
	protected List<Property> getChildProperties(Property property, String elementName, String statedType) throws DefinitionException {
		ElementDefinition ed = property.getDefinition();
		StructureDefinition sd = property.getStructure();
		List<ElementDefinition> children = ProfileUtilities.getChildMap(sd, ed);
		if (children.isEmpty()) {
			// ok, find the right definitions
			String t = null;
			if (ed.getType().size() == 1)
				t = ed.getType().get(0).getCode();
			else if (ed.getType().size() == 0)
				throw new Error("types == 0, and no children found");
			else {
				t = ed.getType().get(0).getCode();
				boolean all = true;
				for (TypeRefComponent tr : ed.getType()) {
					if (!tr.getCode().equals(t)) {
						all = false;
				  	break;
					}
				}
				if (!all) {
				  // ok, it's polymorphic
				  if (ed.hasRepresentation(PropertyRepresentation.TYPEATTR)) {
				    t = statedType;
				    if (t == null && ToolingExtensions.hasExtension(ed, "http://hl7.org/fhir/StructureDefinition/elementdefinition-defaultype"))
				      t = ToolingExtensions.readStringExtension(ed, "http://hl7.org/fhir/StructureDefinition/elementdefinition-defaultype");
				    boolean ok = false;
		        for (TypeRefComponent tr : ed.getType()) 
		          if (tr.getCode().equals(t)) 
		            ok = true;
		         if (!ok)
		           throw new DefinitionException("Type '"+t+"' is not an acceptable type for '"+elementName+"' on property "+property.getDefinition().getPath());
				    
				  } else {
					t = elementName.substring(tail(ed.getPath()).length() - 3);
					if (isPrimitive(lowFirst(t)))
						t = lowFirst(t);
				  }
				}
			}
			if (!"xhtml".equals(t)) {
				sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+t);
				if (sd == null)
					throw new DefinitionException("Unable to find class '"+t+"' for name '"+elementName+"' on property "+property.getDefinition().getPath());
				children = ProfileUtilities.getChildMap(sd, sd.getSnapshot().getElement().get(0));
			}
		}
		List<Property> properties = new ArrayList<Property>();
		for (ElementDefinition child : children) {
			properties.add(new Property(child, sd));
		}
		return properties;
	}

	private String lowFirst(String t) {
		return t.substring(0, 1).toLowerCase()+t.substring(1);
	}

	private String tail(String path) {
		return path.contains(".") ? path.substring(path.lastIndexOf(".")+1) : path;
	}

}
