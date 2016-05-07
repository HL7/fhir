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
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.ElementDefinition.PropertyRepresentation;
import org.hl7.fhir.dstu3.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.ProfileUtilities;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.dstu3.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.Utilities;

public abstract class ParserBase {

  interface IErrorNotifier {
    
  }
  public enum ValidationPolicy { NONE, QUICK, EVERYTHING }

	public static boolean isPrimitive(String code) {
		return Utilities.existsInList(code, 
				"xhtml", "boolean", "integer", "string", "decimal", "uri", "base64Binary", "instant", "date", "dateTime", 
				"time", "code", "oid", "id", "markdown", "unsignedInt", "positiveInt", "xhtml", "base64Binary");
	}

	protected IWorkerContext context;
	protected ValidationPolicy policy;
  protected List<ValidationMessage> errors;

	public ParserBase(IWorkerContext context) {
		super();
		this.context = context;
		policy = ValidationPolicy.NONE;
	}

	public void setupValidation(ValidationPolicy policy, List<ValidationMessage> errors) {
	  this.policy = policy;
	  this.errors = errors;
	}
	
	public abstract Element parse(InputStream stream) throws Exception;

	public abstract void compose(Element e, OutputStream destination, OutputStyle style, String base)  throws Exception;

	
	public void logError(int line, int col, String path, IssueType type, String message, IssueSeverity level) throws FHIRFormatError {
	  if (policy == ValidationPolicy.EVERYTHING) {
	    ValidationMessage msg = new ValidationMessage(Source.InstanceValidator, type, line, col, path, message, level);
	    errors.add(msg);
	  } else if (level == IssueSeverity.FATAL || (level == IssueSeverity.ERROR && policy == ValidationPolicy.QUICK))
	    throw new FHIRFormatError(message+String.format(" at line %d col %d", line, col));
	}
	
	
	protected StructureDefinition getDefinition(int line, int col, String ns, String name) throws FHIRFormatError {
    if (ns == null) {
      logError(line, col, name, IssueType.STRUCTURE, "This cannot be parsed as a FHIR object (no namespace)", IssueSeverity.FATAL);
      return null;
    }
    if (name == null) {
      logError(line, col, name, IssueType.STRUCTURE, "This cannot be parsed as a FHIR object (no name)", IssueSeverity.FATAL);
      return null;
  	}
	  for (StructureDefinition sd : context.allStructures()) {
	    if (name.equals(sd.getIdElement().getIdPart())) {
	      if((ns == null || ns.equals(FormatUtilities.FHIR_NS)) && !ToolingExtensions.hasExtension(sd, "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace"))
	        return sd;
	      String sns = ToolingExtensions.readStringExtension(sd, "http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace");
	      if (ns != null && ns.equals(sns))
	        return sd;
	    }
	  }
	  logError(line, col, name, IssueType.STRUCTURE, "This does not appear to be a FHIR resource (unknown namespace/name '"+ns+"::"+name+"')", IssueSeverity.FATAL);
	  return null;
  }

	protected StructureDefinition getDefinition(int line, int col, String name) throws FHIRFormatError {
    if (name == null) {
      logError(line, col, name, IssueType.STRUCTURE, "This cannot be parsed as a FHIR object (no name)", IssueSeverity.FATAL);
      return null;
  	}
	  for (StructureDefinition sd : context.allStructures()) {
	    if (name.equals(sd.getIdElement().getIdPart())) {
	      return sd;
	    }
	  }
	  logError(line, col, name, IssueType.STRUCTURE, "This does not appear to be a FHIR resource (unknown name '"+name+"')", IssueSeverity.FATAL);
	  return null;
  }

  
	}
