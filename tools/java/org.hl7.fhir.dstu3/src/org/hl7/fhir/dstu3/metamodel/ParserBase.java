package org.hl7.fhir.dstu3.metamodel;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.exceptions.DefinitionException;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.ProfileUtilities;
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

	public abstract void compose(Element e, OutputStream destination, OutputStyle style, String identity)  throws Exception;

	protected List<Property> getChildProperties(Property property, String elementName) throws DefinitionException {
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
					if (!tr.getCode().equals(t))
						all = false;
				}
				if (!all) {
					t = elementName.substring(tail(ed.getPath()).length() - 3);
					if (isPrimitive(lowFirst(t)))
						t = lowFirst(t);
				}
			}
			if (!"xhtml".equals(t)) {
				sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+t);
				if (sd == null)
					throw new DefinitionException("Unable to find "+t);
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
