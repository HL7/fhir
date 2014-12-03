package org.hl7.fhir.instance.validation;

import java.util.List;

import org.hl7.fhir.instance.model.Profile;
import org.w3c.dom.Element;

public interface IResourceValidator {

	public abstract List<ValidationMessage> validateInstance(Element elem,
	    Profile profile, String uri) throws Exception;

}