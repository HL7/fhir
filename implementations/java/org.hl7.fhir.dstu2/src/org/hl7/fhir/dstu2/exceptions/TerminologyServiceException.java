package org.hl7.fhir.dstu2.exceptions;

import org.hl7.fhir.dstu2.exceptions.FHIRException;

public class TerminologyServiceException extends FHIRException {

	public TerminologyServiceException() {
		super();
	}

	public TerminologyServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public TerminologyServiceException(String message) {
		super(message);
	}

	public TerminologyServiceException(Throwable cause) {
		super(cause);
	}

}
